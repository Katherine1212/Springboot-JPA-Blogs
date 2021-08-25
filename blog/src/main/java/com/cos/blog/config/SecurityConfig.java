package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

// bean 등록-> spring container에서 객체를 관리 할 수 있게 하는 것
@Configuration // Bean등록(IoC)
@EnableWebSecurity // 시큐리티 필터 등록
// 시큐리티 필터 추가=> 스프링 시큐리티가 활성화 되어있지만 어떤 설정을 이 파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근 시 권한 및 인증 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	// 1.  Bean 어노테이션은 메서드에 붙여서 객체 생성시 사용
	@Bean // Bean등록(IoC)
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	/* 2. 시큐리티 로그인 시 어떤 암호화로 인코딩하여 pw비교할건지 알려줘야함
	 * 시큐리티가 대신 로그인 해주는데 password를 가로채기하는데 해당 password가 어떤 hash로 회원가입 되어있는지 알아야 
	 * 같은 해쉬로 암호화 하여 DB에 있는 해쉬랑 비교가능*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	// 3. 필터링
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable() // csrf 토큰 비활성화(test시 걸어두면 좋음)
			.authorizeRequests()
			.antMatchers("/","/auth/**", "/js/**", "/css/**", "/image/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청 오는 로그인 가로채서 대신 로그인
				.defaultSuccessUrl("/");
	}
}
