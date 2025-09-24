package com.github.misato1119.attendanceapp.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.misato1119.attendanceapp.entity.User;
import com.github.misato1119.attendanceapp.form.UserForm;
import com.github.misato1119.attendanceapp.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserRegistrationService {
	// UserRepositoryをインスタンス化する
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	// ユーザー登録
	public void UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Transactional
	public void registerUser(UserForm form) {
		User user = new User();
		user.setEmployeeNumber(form.getEmployeeNumber());
		user.setUsername(form.getUsername());
		user.setEmail(form.getEmail());
		// パスワードのハッシュ化
		String encoredPassword = passwordEncoder.encode(form.getPassword());
		user.setPassword(encoredPassword);
		user.setRole("USER");
		user.setDeleted(false);
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		
		userRepository.save(user);
	}
}
