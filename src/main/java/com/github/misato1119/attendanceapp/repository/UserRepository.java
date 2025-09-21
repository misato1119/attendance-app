package com.github.misato1119.attendanceapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.misato1119.attendanceapp.entity.User;

// findByIdを使うとPK検索ができるインターフェース
public interface UserRepository extends JpaRepository<User, Long>{
	
	// 社員番号で検索する機能追加
	Optional<User> findByEmployeeNumber(String employeeNumber);
}
