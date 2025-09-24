package com.github.misato1119.attendanceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@GetMapping("/clock")
	public String showClockPage() {
	    return "attendance/clock"; 
	}
	
	// 出勤打刻
	@PostMapping("/clockin")
	public String clockIn(Model model) {

		// Authenticationはログイン情報を持っているオブジェクト
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("ログイン中ユーザー: " + authentication.getName());
		System.out.println("権限: " + authentication.getAuthorities());
		String employeeNumber = authentication.getName();
		
		// 出勤打刻をDBに登録
		attendanceService.clockIn(employeeNumber);
		model.addAttribute("message", "出勤打刻しました");
		// 結果画面に戻る
		return "attendance/result";
	}
	
	// 退勤打刻
	@PostMapping("/clockout")
	public String clockOut(Model model) {

		// Authenticationはログイン情報を持っているオブジェクト
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String employeeNumber = authentication.getName();
		String workingTime = attendanceService.clockOut(employeeNumber);
		
		// 退勤打刻をDBに登録し、勤務時間（○時間△分）を取得。
		model.addAttribute("message", "退勤打刻しました");
		model.addAttribute("workingTime", workingTime);
		
		// 結果画面に戻る
		return "attendance/result";
	}
	
}
