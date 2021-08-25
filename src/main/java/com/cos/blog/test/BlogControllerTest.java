package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
/*스프링이 com.cos.blog pkg 이하를 스캔해서 모든 파일을 메모리에 new 하는건 아님.
 * 특정 어노테이션이 붙어있는 class 파일들을 new해서(Ioc) 스프링 컨테이너에 관리*/
public class BlogControllerTest {
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}
}
