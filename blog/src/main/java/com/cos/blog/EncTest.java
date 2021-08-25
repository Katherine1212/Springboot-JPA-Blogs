package com.cos.blog;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {
	public void hashProtected() {
		String encPassword= new BCryptPasswordEncoder().encode("1234");
		System.out.println("1234Hash"+encPassword);
	}
}
