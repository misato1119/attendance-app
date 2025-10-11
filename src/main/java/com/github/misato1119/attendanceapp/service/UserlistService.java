package com.github.misato1119.attendanceapp.service;

import java.util.List;
import java.util.Optional;

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
	
	// id検索
	public Optional<User> findById(Long id){
		return userRepository.findById(id);
	}
	
	// ユーザー情報のアップデート
	public User updateUser(User user) {
		// 既存のデータを取得
		User existing = userRepository.findById(user.getId())
						.orElseThrow(() -> new IllegalArgumentException("このユーザーIDは存在しません: " + user.getId()));
		
		existing.setUsername(user.getUsername());
		existing.setEmail(user.getEmail());
		existing.setRole(user.getRole());
		existing.setDeleted(user.isDeleted());
		
		userRepository.save(existing);
		
		return existing;
	}
}
