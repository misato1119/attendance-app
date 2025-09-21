package com.github.misato1119.attendanceapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.misato1119.attendanceapp.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String employeeNumber) throws UsernameNotFoundException{
		var user = userRepository.findByEmployeeNumber(employeeNumber)
				.orElseThrow(() -> new UsernameNotFoundException("社員番号が見つかりません" + employeeNumber));
		
		return User.builder()
				.username(user.getEmployeeNumber())
				.password(user.getPassword())
				.roles(user.getRole())
				.build();
	}
}
