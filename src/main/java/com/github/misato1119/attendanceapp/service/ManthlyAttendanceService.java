package com.github.misato1119.attendanceapp.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.misato1119.attendanceapp.entity.AttendanceRecord;
import com.github.misato1119.attendanceapp.entity.User;
import com.github.misato1119.attendanceapp.repository.AttendanceRecordRepository;
import com.github.misato1119.attendanceapp.repository.UserRepository;

@Service
public class ManthlyAttendanceService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AttendanceRecordRepository attendanceRecordRepository;
	
	// 月次勤怠データ取得
	public List<AttendanceRecord> findByMonth(String employeeNumber, YearMonth ym){
		// 社員番号からUserを取得
		User user = userRepository.findByEmployeeNumber(employeeNumber)
				.orElseThrow(() -> new UsernameNotFoundException("社員番号が存在しません: " + employeeNumber));
		
		LocalDate start = ym.atDay(1);
		LocalDate end = ym.atEndOfMonth();
		return attendanceRecordRepository.findByUserAndWorkDateBetween(user, start, end);
	}
	
	// 合計勤務時間取得
	public double calculateTotalWorkingHours(List<AttendanceRecord> records) {
		return records.stream()
				.filter(r -> r.getWorkingHours() != null)
				.mapToDouble(AttendanceRecord::getWorkingHours)
				.sum();
	}
	
	
}
