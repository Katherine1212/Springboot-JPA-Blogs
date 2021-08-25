package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @RequiredArgsConstructor // final붙은 애들 만들어짐
@Data
/* @AllArgsConstructor */
@NoArgsConstructor
public class Member {
	private  int id;
	private String username;
	private String password;
	private String email;
	
	@Builder
	// 값 넣을 때 매개변수 순서 안 지켜도 됨.
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

}
