package com.github.misato1119.attendanceapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.misato1119.attendanceapp.entity.User;
import com.github.misato1119.attendanceapp.repository.UserRepository;

@Service
public class MypageService {
	@Autowired
	UserRepository userRepository;
	
	// ID検索
	public Optional<User> findById(Long id){
		return userRepository.findById(id);
	}
	
	public User updateUser(User user) {
		// 既存のデータを取得
				User existing = userRepository.findById(user.getId())
								.orElseThrow(() -> new IllegalArgumentException("このユーザーIDは存在しません: " + user.getId()));
				
				existing.setUsername(user.getUsername());
				existing.setEmail(user.getEmail());
				
				userRepository.save(existing);
				
				return existing;
	}

}
