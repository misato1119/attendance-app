package com.github.misato1119.attendanceapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/register/**").permitAll() // 登録機能は誰でもアクセス可能
				.requestMatchers("/login").permitAll() // ログイン画面は誰でもアクセス可能
				.anyRequest().authenticated() // そのほかの画面はログイン必要
				)
			.formLogin(form -> form
					.loginPage("/login")	// ログインページのURL
					.defaultSuccessUrl("/home", true)	// ログイン成功時の遷移先
					.permitAll()	// 誰でも遷移可能
					)
			.logout(logout -> logout
					.logoutUrl("/logout")	// ログアウトのURL
					.logoutSuccessUrl("/login?logout")
					.permitAll()
					);
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
