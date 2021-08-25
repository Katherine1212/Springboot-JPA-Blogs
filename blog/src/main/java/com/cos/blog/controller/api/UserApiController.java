package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDTO;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	@Autowired
	private UserService userService;

	
	@PostMapping("/auth/joinProc")
	public ResponseDTO<Integer> save(@RequestBody User user) {
		user.setRole(RoleType.USER);
		/* int result= userService.regist(user); */
		userService.regist(user);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
	// login
	
	/* 전통적인 방식(시큐리티 안 씀)
	 * @PostMapping("/api/user/login") // HttpSession을 login 매개변수로 받아도 되고 아니면 상단에
	 * 선언해도 됨. public ResponseDTO<Integer> login(@RequestBody User user, HttpSession session){
	 * System.out.println("Login호출호출"); User principal= userService.login(user); //
	 * principal: 접근 주체 if(principal != null) { session.setAttribute("principal",
	 * principal); }
	 * 
	 * return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1); }
	 */
}
