package com.github.misato1119.attendanceapp.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.misato1119.attendanceapp.entity.AttendanceRecord;
import com.github.misato1119.attendanceapp.entity.User;
import com.github.misato1119.attendanceapp.repository.AttendanceRecordRepository;
import com.github.misato1119.attendanceapp.repository.UserRepository;

@Service
public class AttendanceService {

	// AttendanceRecordRepositoryをインスタンス化する
	@Autowired
	private AttendanceRecordRepository attendanceRecordRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	// 出勤打刻
	public void clockIn(String employeeNumber) {
		User user = userRepository.findByEmployeeNumber(employeeNumber)
	            .orElseThrow(() -> new UsernameNotFoundException("社員番号が存在しません: " + employeeNumber));


		LocalDate today = LocalDate.now();
		AttendanceRecord record = attendanceRecordRepository.findByUserAndWorkDate(user, today);
		if(record == null) {
			record = new AttendanceRecord();
			record.setUser(user);
			record.setWorkDate(today);
		}
		record.setClockIn(LocalTime.now());
		record.setDeleted(false);
		
		attendanceRecordRepository.save(record);
	}
	
	// 退勤打刻
	public String clockOut(String employeeNumber) {
		User user = userRepository.findByEmployeeNumber(employeeNumber)
	            .orElseThrow(() -> new UsernameNotFoundException("社員番号が存在しません: " + employeeNumber));

		
		LocalDate today = LocalDate.now();
		AttendanceRecord record = attendanceRecordRepository.findByUserAndWorkDate(user, today);
		
		if(record != null) {
			record.setClockOut(LocalTime.now());
		}
		
		// 勤怠時間を算出
		if(record != null && record.getClockIn() != null) {
			long minutes = java.time.Duration.between(record.getClockIn(), record.getClockOut()).toMinutes();
			double hours = minutes / 60.0;
			record.setWorkingHours(hours);
			
			// 画面表示用に勤怠時間のフォーマットを変更する
			int h = (int)hours;
			int m = (int)Math.round((hours - h) * 60);
			String disPlayTime = h + "時間" + m + "分";
			
			attendanceRecordRepository.save(record);
			
			return disPlayTime;
			
		}
		return null;
		
	}
	
}
