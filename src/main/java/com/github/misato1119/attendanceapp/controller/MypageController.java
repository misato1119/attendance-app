package com.github.misato1119.attendanceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.github.misato1119.attendanceapp.entity.User;
import com.github.misato1119.attendanceapp.repository.UserRepository;
import com.github.misato1119.attendanceapp.service.MypageService;



@Controller
public class MypageController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MypageService mypageService;
	
	// mypageの表示
	@GetMapping("/mypage/mypage")
	public String mypageShow(Model model, Authentication auth) {
	    String employeeNumber = auth.getName(); // ログイン中の社員番号取得
	    User user = userRepository.findByEmployeeNumber(employeeNumber)
	            .orElseThrow(() -> new IllegalArgumentException("社員番号が存在しません: " + employeeNumber));
	    model.addAttribute("user", user);
	    return "mypage/mypage";
	}

	// 編集ページの表示
	@GetMapping("/mypage/edit")
	public String edit(Model model, Authentication auth) {
	    String employeeNumber = auth.getName(); // ログイン中の社員番号取得
	    User user = userRepository.findByEmployeeNumber(employeeNumber)
	            .orElseThrow(() -> new IllegalArgumentException("社員番号が存在しません: " + employeeNumber));
	    model.addAttribute("user", user);
		return "mypage/edit";
	}
	
	// 編集画面から申請ボタン押下
	@PostMapping("/mypage/update")
	public String update(@ModelAttribute("user") User user) {
		mypageService.updateUser(user);
		return "redirect:/mypage/mypage";
	}
	
	
}
