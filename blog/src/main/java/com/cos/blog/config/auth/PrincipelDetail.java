package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

// 스프링 시큐리티가 로그인 요청 가로채서 로그인 진행 후 완료되면 UserDetails 타입의 obj를 시큐리티의 고유한 세션 저장소에 저장
@Getter
public class PrincipelDetail implements UserDetails{
	private User user; // composition(품고 있음)
	
	public PrincipelDetail(User user) {
		this.user= user;
	}
	
	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지를 리턴. (true:만료 안됨,  false:만료)
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	// 계정이 잠기지 않았는지를 리턴
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	// 비밀번호가 만료되지 않았는지를 리턴
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	// 계정 활성화가 되어있는지를 리턴
	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	// 계정의 권한 목록을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors= new ArrayList<>();
		/*
		 * collectors.add(new GrantedAuthority() {
		 * 
		 * @Override public String getAuthority() { return "ROLE_"+user.getRole(); //
		 * "ROLE_" 규칙임 꼭!넣을 것! -> 반환 ROLE_USER } });
		 */
		collectors.add(()->{return "ROLE_"+user.getRole();}); // 람다식으로 표현
		return collectors;
	}

}
