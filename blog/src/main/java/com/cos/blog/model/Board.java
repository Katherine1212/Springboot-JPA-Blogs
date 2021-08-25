package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int boardId;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob
	private String content;

	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER) // Many= One, User= One
	@JoinColumn(name = "userId")
	private User user; // DB는 Object를 저장 할 수 없다. FK, 자바는 Obj를 저장 가능.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) 
	// mappedBy 연관관계의 주인이 아니다(FK가 아님), DB에 생성하지 마세요
	// 기본 fetch= FetchType.LAZY
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
