	package com.github.misato1119.attendanceapp.entity;
	
	import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
	
	/**
	 * 勤怠記録エンティティ。
	 * <p>
	 * attendance_recordsテーブルとマッピングされる。
	 * 勤務日、出退勤時刻、勤務時間、削除フラグを保持する。
	 * また、論理削除フラグと作成日時・更新日時、作成者、更新者を持つ
	 * <p>
	 * ユーザーと1対多の関係を持ち、各レコードは1人のユーザーに紐づく。
	 */
	
	@Entity
	@Table(name = "attendance_records",
				  uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "work_date"}))
	@Data   // getter,setter,toStringなどが自動で作られるようにするためのアノテーション
	public class AttendanceRecord {
		// 勤怠レコードID
		@Id        // 主キー
		@GeneratedValue(strategy = GenerationType.IDENTITY)    // AUTO_INCREMEN
		private Long id;
		
		// ユーザーID（FK: users.id）
		@ManyToOne  // 多 対 １
		@JoinColumn(name = "user_id", nullable = false)
		private User user;
		
		// 勤務日
		@Column(name = "work_date", nullable = false)
		private LocalDate workDate;
		
		// 出勤時刻
		@Column(name = "clock_in", nullable = true)
		private LocalTime clockIn;
		
		// 退勤時刻
		@Column(name = "clock_out", nullable = true)
		private LocalTime clockOut;
		
		// 勤務時間
		@Column(name = "working_hours", nullable = true)
		private Double workingHours;
		
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
		
		// 登録者
		@ManyToOne  // 多 対 １
		@JoinColumn(name = "created_by")
		private User createdBy;
		
		// 更新者
		@ManyToOne  // 多 対 １
		@JoinColumn(name = "updated_by")
		private User updatedBy;
		
	
	}
