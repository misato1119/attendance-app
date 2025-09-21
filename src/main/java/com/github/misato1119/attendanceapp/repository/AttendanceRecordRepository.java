package com.github.misato1119.attendanceapp.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.misato1119.attendanceapp.entity.AttendanceRecord;
import com.github.misato1119.attendanceapp.entity.User;

//findByIdを使うとPK検索ができるインターフェース
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long>{
	
	// 特定ユーザーの勤怠一覧を取得する
	List<AttendanceRecord> findByUser(User user);
	
	// 特定ユーザーの特定日付の勤怠を取得する
	AttendanceRecord findByUserAndWorkDate(Optional<User> user, LocalDate workDate);
	
	// 削除フラグがfalse（削除されていない）の勤怠を取得する
	List<AttendanceRecord> findByUserAndDeletedFalse(User user);
	
} 
