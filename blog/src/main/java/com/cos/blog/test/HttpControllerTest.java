package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/* 사용자가 요청-> HTML파일로 응답
 * @Controller*/

// 사용자가 요청-> Data를 응답
@RestController
public class HttpControllerTest {
	private static final String TAG= "HttpControllerTest";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		/* Member m= new Member(1, "ssar", "1234", "ssar@naver.com"); */
		Member m= Member.builder().username("ssar").email("ssal@naver.com").build();
		System.out.println(TAG+"getter: "+m.getId());
		m.setId(50000);
		System.out.println(TAG+"setter: "+m.getId());
		
		return "lombok test finish";
	}
	
	// 인터넷 브라우저 요청은 무조건 GET요청만 가능하다!
	
	// GET http://localhost:8000/blog/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) {

		return "GET 요청"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	/* 
	 * @RequestParam으로도 받을 수 있으나, 그냥 위에처럼 class의 getter접근해도 ok
	public String getTest(@RequestParam int id, @RequestParam String username, @RequestParam String password, @RequestParam String email) {
		return "GET 요청"+id+","+username+","+password+","+email;
	}
	*/
	
	// POST http://localhost:8000/blog/http/post (insert)
	/*
	@PostMapping("/http/post")
	public String postTest(Member m) {
		return "POST 요청"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	@PostMapping("/http/post") 
	// text를 보낼 때는 꼭 @RequestBody 
  	public String postTest(@RequestBody String text) { 
  		return "POST 요청: "+text; 
  	}
	 */
	@PostMapping("/http/post") // text/plain, application/json
	public String postTest(@RequestBody Member m) { // MessageConverter(스프링부트)가 자동으로 맵핑처리
		return "POST 요청: "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	// PUT http://localhost:8000/blog/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "PUT 요청"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	// DELETE http://localhost:8000/blog/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "DELETE 요청";
	}
}
