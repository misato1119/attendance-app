package com.github.misato1119.attendanceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.misato1119.attendanceapp.service.AttendanceService;


@Controller
@RequestMapping("/attendance")
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceService;
	
	// (仮)固定の社員番号（ログイン機能を作ったら差し替え）
	private final String EMPLOYEE_NUMBER = "E001";
	
	@GetMapping("/clock")
	public String showClockPage() {
	    return "attendance/clock"; 
	}
	
	// 出勤打刻
	@PostMapping("/clockin")
	public String clockIn(Model model) {
		// 出勤打刻をDBに登録
		attendanceService.clockIn(EMPLOYEE_NUMBER);
		model.addAttribute("message", "出勤打刻しました");
		
		// 結果画面に戻る
		return "attendance/result";
	}
	
	// 退勤打刻
	@PostMapping("/clockout")
	public String clockOut(Model model) {
		// 退勤打刻をDBに登録し、勤務時間（○時間△分）を取得。
		String workingTime = attendanceService.clockOut(EMPLOYEE_NUMBER);
		model.addAttribute("message", "退勤打刻しました");
		model.addAttribute("workingTime", workingTime);
		
		// 結果画面に戻る
		return "attendance/result";
	}
	
}
