package com.github.misato1119.attendanceapp.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserForm {
	@NotBlank(message = "社員番号を入力してください")
	private String employeeNumber;
	
	@NotBlank(message = "ユーザー名を入力してください")
	@Size(max = 50, message = "ユーザー名は50文字以内で入力してください")
	private String username;
	
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "正しいメールアドレスを入力してください")
	private String email;
	
	@NotBlank(message = "パスワードを入力してください")
	@Size(min = 6, message = "パスワードは6文字以上で入力してください")
	private String password;
	
	// getter & setter
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
