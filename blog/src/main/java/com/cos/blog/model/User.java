package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// ORM-> Java(다른 언어) Object-> 테이블로 맵핑하는 기술
@Entity // User클래스를 읽고 자동으로 MySQL에 테이블 생성
/* @DynamicInsert - insert 시 null 인 필드 채워주기*/
public class User {
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 플젝에서 연결된 DB의 넘버링 전략 따라감 
	private int user_id; // 오라클-시퀀스, mysql- auto_increment

	@Column(nullable = false, length = 30, unique = true)
	private String username;
	
	@Column(nullable = false, length = 100) // hash로 pw를 암호화 하려면 길게 잡을 필요 있음
	private String password;
	
	@Column(nullable = false, length = 50, unique = true)
	private String email;
	
	/* @ColumnDefault(" 'user' ")
	private String role; // Enum을 쓰는게 맞음. Enum쓰면 도메인 만들 수 있다.(ex. ADMIN, USER등 권한 부여가능) */
	
	@Enumerated(EnumType.STRING) // DB는 RoleType이란게 없음
	private RoleType role;
	
	
	@CreationTimestamp
	private Timestamp createDate;
}
