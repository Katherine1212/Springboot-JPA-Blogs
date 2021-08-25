package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping("/auth/joinFrom")
	public String joinForm() {
		
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "user/loginForm";
	}
	
	/* 인증이 안 된 사용자들이 출입할 수 있는 경로를 /auth/**허용
	 * 그냥 주소가 /이면 index.jsp가 뜨도록
	 * static 이하에 있는 /js/**, /css/**, /image/** 
	 * */ 
}
