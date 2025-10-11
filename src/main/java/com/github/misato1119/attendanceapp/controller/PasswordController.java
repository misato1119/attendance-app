package com.github.misato1119.attendanceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.github.misato1119.attendanceapp.entity.User;
import com.github.misato1119.attendanceapp.form.PasswordForm;
import com.github.misato1119.attendanceapp.repository.UserRepository;


@Controller
public class PasswordController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    // パスワード変更画面の表示
    @GetMapping("/mypage/password_change")
    public String showPasswordChangeForm(Model model) {
		// ログイン中のユーザーを取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String employeeNumber = auth.getName();
		
    	model.addAttribute("passwordForm", new PasswordForm());
    	model.addAttribute("employeeNumber", employeeNumber);
        return "mypage/password_change";
    }
    
	@PostMapping("/mypage/password_change")
	public String passwordChange(@ModelAttribute PasswordForm passwordForm, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String employeeNumber = auth.getName();
		
		User user = userRepository.findByEmployeeNumber(employeeNumber)
				.orElseThrow(() -> new IllegalArgumentException("ユーザーが存在しません"));
		
		// 現在のパスワードが一致するかの確認
		if(!passwordEncoder.matches(passwordForm.getConfirmPassword(), user.getPassword())) {
			model.addAttribute("error", "現在のパスワードが正しくありません");
			return "mypage/password_change";
		}
		
		// 新しいパスワードと新しいパスワード（確認）が一致するかの確認
		if(!passwordForm.getNewPassword().equals(passwordForm.getConfirmPassword())) {
			model.addAttribute("error", "新しいパスワードと確認用パスワードが一致しません");
			return "mypage/password_change";
		}
		
		// パスワード更新
		user.setPassword(passwordEncoder.encode(passwordForm.getNewPassword()));
		userRepository.save(user);
		
		
		return "mypage/password_complete";
	}
	
}
