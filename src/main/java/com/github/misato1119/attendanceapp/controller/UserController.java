package com.github.misato1119.attendanceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.github.misato1119.attendanceapp.form.UserForm;
import com.github.misato1119.attendanceapp.service.UserRegistrationService;

import jakarta.validation.Valid;



@Controller
public class UserController {
	@Autowired
	private UserRegistrationService userService;
	
	// 登録画面表示
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "userRegister/register";
	}
	
	// 登録画面から登録ボタン押下
	@PostMapping("/register/confirm")
	public String confirmRegister(@Valid @ModelAttribute UserForm userForm,
								BindingResult result,
								Model model) {
		if(result.hasErrors()) {
			return "userRegister/register";
		}
		
		model.addAttribute("userForm", userForm);
		return "userRegister/confirm";
	}
	
	// 登録確認画面から送信ボタン押下
	@PostMapping("/register/complete")
	public String completeRegister(@ModelAttribute UserForm userForm, 
			  				   Model model) {
		userService.registerUser(userForm);
		return "userRegister/complete";
	}
	
	

}
