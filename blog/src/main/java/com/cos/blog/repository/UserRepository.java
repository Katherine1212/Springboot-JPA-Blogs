package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;


// 자동으로 bean 등록.  @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> { // user에 fk는 인티저
	Optional<User> findByUsername(String username);
	/*
	 * 1. JPA Naming 전략 select * from user where username=?1 and username=?2; User
	 * findByUsernameAndPassword(String username, String password);
	 * 
	 * 2. native query전략
	 * 
	 * @Query(value = "select * from user where username=? and password=?",
	 * nativeQuery= true) User login(String id, String password);
	 * 
	 * 
	 */
}
