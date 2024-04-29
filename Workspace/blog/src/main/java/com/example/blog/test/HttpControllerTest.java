package com.example.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(데이터)
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//Member m = new Member(1,"ssar","1234","email");
		Member m = Member.builder().username("ssar").password("1234").email("email").build();
		System.out.println(TAG+"getter : " + m.getUsername() );
		m.setUsername("hanul");
		System.out.println(TAG+"setter : " +  m.getUsername());
		return "lombok test 완료";
	}
	
	
	//http://localhost:8080/http/get(selete)
	@GetMapping("/http/get")
	//public String getTest(@RequestParam int id, @RequestParam String username) {
	public String getTest(Member m) { // id=1&username=ssar&password=1234&email=ssar@nate.com를(쿼리 스트링) Member에 넣는 것을 Spring이 해줌 MessageConverter(스프링부트) - 들어온 데이터 자동 파싱
		
		return "get 요청 : " + m.getId() + ", "+ m.getUsername()+ ", "+ m.getPassword()+ ", "+ m.getEmail();
	}
	//http://localhost:8080/http/post(incert)
	//인터넷 브라우저 요청은 무조건 get 요청밖에 되지 않는다
	@PostMapping("/http/post")
	 //raw = text/plain == String text, application/json == Member m
	public String postTest(@RequestBody Member m) {//MessageConver+ter(스프링부트) - 들어온 데이터 자동 파싱
		return "post 요청 : " + m.getId() + ", "+ m.getUsername()+ ", "+ m.getPassword()+ ", "+ m.getEmail();
	}
	//	(update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : " + m.getId() + ", "+ m.getUsername()+ ", "+ m.getPassword()+ ", "+ m.getEmail();
		}
	//http://localhost:8080/http/delete(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
