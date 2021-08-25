package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.persistence.Entity;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@RestController // data를 리턴해주는 controller
public class DummyControllerTest {
	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;

	// -------------------------------------------------Join
	// http://localhost:8000/blog/dummy/join
	// http의 body에 username, password, email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join(User user) { // Data는 Key= value 형태로 받아줌(약속된 규칙)3
		System.out.println("ID는"+user.getUser_id());
		System.out.println("username은 "+user.getUsername());
		System.out.println("password은 "+user.getPassword());
		System.out.println("email은 "+user.getEmail());
		System.out.println("role은 "+user.getRole());
		System.out.println("createDate는 "+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원 가입 완료";
	}
	// -------------------------------------------------List
	// {id}주소로 파라미터 전달 받음.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user= userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException(id+" 해당 유저는 없습니다.");
			}
		});
		
		/* 람다식
		User user= userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException(id+" 해당 유저는 없습니다.");
		});
		*/
		
		/* 빈 객체를 넣어주는 것 보다 throw 하는 것이 낫다
		 * User user= userRepository.findById(id).orElseGet(new Supplier<User>() {
		 * 
		 * @Override public User get() { // DB에서 못찾으면 null대신 빈 객체를 넣어줌 return new
		 * User(); } });
		 */
		
		/* 요청: 웹브라우저
		 user 객체= 자바 오브젝트
		 변환(웹 브라우저가 이해 할 수 있는 데이터로 변환) ex. JSON
		 이전 버전 GSON을 통해 변환했으나 스프링부트는 MessageConverter가 응답시에 자동으로 작동
		 if. 자바 obj리턴 시 Messageconverter가 자동으로 Jackson라이브러리 호출해서 user obj를 json으로 변환하여 웹에 전송
		*/
		return user;
	}
	
	
	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 1page당 2건의 데이터 반환
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		/* list<User> users= userRepository.findAll(pageable).getContent; */
		Page<User> pagingUser= userRepository.findAll(pageable);
		List<User> users= pagingUser.getContent();
		return users;
	}
	
	// -------------------------------------------------Update
	@Transactional // save 호출 하지 않아도 업데이트 됨. 함수 종료 시 자동 commit
	@PutMapping("/dummy/user/{user_id}")
	public User updateUser(@PathVariable int user_id, @RequestBody User reqestUser) {
		System.out.println("id은 "+user_id);
		System.out.println("password은 "+reqestUser.getPassword());
		System.out.println("email은 "+reqestUser.getEmail());
		
		User user= userRepository.findById(user_id).orElseThrow(()->{
			return new IllegalArgumentException("수정 실패");
		});
		
		user.setPassword(reqestUser.getPassword());
		user.setEmail(reqestUser.getEmail());
		
		/*
		 * save 함수는 id를 전달하지 않으면 insert, id 전달하면 해당 id대한
		 * 데이터가 있으면 update, 데이터가 없으면 insert
		 * userRepository.save(user);
		 */
		
		// 더티체킹- 한번에 커밋
		
		return user;
	}
	
	// -------------------------------------------------delete
	@DeleteMapping("/dummy/user/{user_id}")
	public String deleteUser(@PathVariable int user_id) {
		try {
			userRepository.deleteById(user_id);		
		}catch(EmptyResultDataAccessException e) {
			return "삭제 실패하였습니다. 없는 유저입니다.";
		}
		
		return "삭제 되었습니다.";
	}
}
