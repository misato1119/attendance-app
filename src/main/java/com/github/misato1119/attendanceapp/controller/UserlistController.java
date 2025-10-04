package com.github.misato1119.attendanceapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.github.misato1119.attendanceapp.entity.User;
import com.github.misato1119.attendanceapp.repository.UserRepository;
import com.github.misato1119.attendanceapp.service.UserlistService;




@Controller
public class UserlistController {
	@Autowired
	private UserlistService userlistService;
	
	@Autowired
	private UserRepository userRepository;
	
	// ユーザー一覧画面表示
	@GetMapping("/user/list")
	public String userlist(Model model) {
		// ユーザーデータ取得
		List<User> userlist =  userlistService.findAll();

		// modelにユーザーデータ格納
		model.addAttribute("userlist", userlist);
		
		return "user/list";
	}
	
	// ユーザー情報の編集画面表示
	@GetMapping("/user/edit/{id}")
	public String useredit(@PathVariable Long id, Model model) {
		User user = userRepository.findById(id)
		        .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
		model.addAttribute("user", user);
		return "user/edit";
	}
	
	// ユーザー情報編集画面から申請ボタン押下
	@PostMapping("/user/update")
	public String usereditComplete(@ModelAttribute("user") User user) {
		userlistService.updateUser(user);
		return "redirect:/user/list";
	}
	
	
	
	
	

}
