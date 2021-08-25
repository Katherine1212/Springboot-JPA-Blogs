package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Board;



// 자동으로 bean 등록.  @Repository 생략 가능
public interface BoardRepository extends JpaRepository<Board, Integer> { // user에 fk는 인티저
	
}
