package com.github.misato1119.attendanceapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	// ログイン画面表示
	@GetMapping("/login")
	public String showLoginForm() {
		return "login/login";
	}
}
