package com.github.misato1119.attendanceapp.controller;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.misato1119.attendanceapp.entity.AttendanceRecord;
import com.github.misato1119.attendanceapp.service.ManthlyAttendanceService;




@Controller
public class ManthlyAttendanceController {
	@Autowired
	private ManthlyAttendanceService manthlyAttendanceService;

	@GetMapping("/monthlyAttendance/monthlyAttendance")
	public String monthlyAttendanceShow(Model model) {
		// 1年分の年月を格納するList
		List<YearMonth> month = new ArrayList();
		
		// 今月を取得
		YearMonth thisMonth = YearMonth.now();
		
		// 今月から過去12か月の年月を格納する
		for(int i = 0; i < 12; i++) {
			month.add(thisMonth.minusMonths(i));
		}
		
		model.addAttribute("month", month);

		return "monthlyAttendance/monthlyAttendance";
	}
	
	@GetMapping("/monthlyAttendance/check")
	public String monthlyCheck(@RequestParam ("targetMonth") String targetMonth,
								Model model) {
		// 選択した年月を変数に格納
		YearMonth ym = YearMonth.parse(targetMonth);
		
		// ログインユーザー取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String employeeNumber = auth.getName();
		
		// 勤怠データを取得
		List<AttendanceRecord> records = manthlyAttendanceService.findByMonth(employeeNumber, ym);
		
		// modelに勤怠データを格納
		model.addAttribute("records", records);
		model.addAttribute("targetMonth", ym);
		
		return "monthlyAttendance/check";
	}
	
	
	
}
