package com.github.misato1119.attendanceapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.misato1119.attendanceapp.entity.User;
import com.github.misato1119.attendanceapp.repository.UserRepository;

@Service
public class UserlistService {
	@Autowired
	UserRepository userRepository;

	// 全件取得
	public List<User> findAll(){
		return userRepository.findAll();
	}
}
