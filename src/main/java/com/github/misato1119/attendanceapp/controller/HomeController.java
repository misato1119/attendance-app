package com.github.misato1119.attendanceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.misato1119.attendanceapp.entity.User;
import com.github.misato1119.attendanceapp.repository.UserRepository;

@Controller
public class HomeController {
	@Autowired
	private UserRepository userRepository;
	
	// ログイン画面表示
	@GetMapping("/common/home")
	public String showHome(Model model, Authentication authentication) {
		// 社員番号を取得
		String employeeNumber = authentication.getName();
		
		// 社員番号から社員名を取得
		User user = userRepository.findByEmployeeNumber(employeeNumber)
					.orElseThrow(() -> new UsernameNotFoundException("社員番号が存在しません:" + employeeNumber));
		
		// 画面表示用にユーザー名セット
		model.addAttribute("displayName", user.getUsername());

		return "common/home";
	}
	
}
