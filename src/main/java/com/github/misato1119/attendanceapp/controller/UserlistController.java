package com.github.misato1119.attendanceapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.misato1119.attendanceapp.entity.User;
import com.github.misato1119.attendanceapp.service.UserlistService;


@Controller
public class UserlistController {
	@Autowired
	private UserlistService userlistService;
	
	@GetMapping("/user/list")
	public String userlist(Model model) {
		// ユーザーデータ取得
		List<User> userlist =  userlistService.findAll();

		// modelにユーザーデータ格納
		model.addAttribute("userlist", userlist);
		
		return "user/list";
	}
	

}
