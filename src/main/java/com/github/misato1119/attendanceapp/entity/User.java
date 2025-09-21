package com.github.misato1119.attendanceapp.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ユーザー情報エンティティ。
 * <p>
 * usersテーブルとマッピングされる。
 * システムにログイン可能なユーザーを表し、社員番号・パスワード・権限を保持する。
 * <p>
 * また、論理削除フラグと作成日時・更新日時を持つ
 */

@Entity
@Table(name = "users")
@Data
public class User {
	// ユーザーID
	@Id        // 主キー
	@GeneratedValue(strategy = GenerationType.IDENTITY)    // AUTO_INCREMENT
	private Long id;
	
	// 社員番号
	@Column(name = "employee_number", nullable = false, unique = true, length = 20)
	private String employeeNumber;
	
	// ユーザー名
	@Column(name = "username", nullable = false, length = 20)
	private String username;
	
	// メールアドレス
	@Column(name = "email", nullable = false, unique = true, length = 100)
	private String email;
	
	// パスワード
	@Column(nullable = false, length = 100)
	private String password;
	
	// 権限
	@Column(nullable = false, length = 20)
	private String role;
	
	// 削除フラグ
	@Column(name = "is_deleted", nullable = false)
	private boolean deleted = false;
	
	// 登録日時(自動設定)
	@CreationTimestamp    // 自動設定にするためのアノテーション
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	// 更新日時（自動更新）
	@UpdateTimestamp    // 自動更新にするためのアノテーション
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}
