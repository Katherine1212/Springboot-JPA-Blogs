package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔 통해서 Bean에 등록(IoC를 해줌)
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder; // DI가 되어 주입
	@Transactional
	public void regist(User user) {
		String rawPassword= user.getPassword(); // 원문
		String encPaasowrd= encoder.encode(rawPassword); // hash화
		user.setPassword(encPaasowrd);
		user.setRole(RoleType.USER);
		userRepository.save(user);
		 
		/*
		 * try { userRepository.save(user); return 1; }catch(Exception e) {
		 * e.printStackTrace(); System.out.println("가입가입"+e.getMessage()); } return -1;
		 */
	}
	
	/*
	 * @Transactional(readOnly = true) // select 시 트랜젝션 시작, 서비스 종료시 트랜젝션 종료(정합성)
	 * public User login(User user) { //
	 * System.out.println("@"+user.getUsername()+"1"+user.getPassword()); return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */
}
