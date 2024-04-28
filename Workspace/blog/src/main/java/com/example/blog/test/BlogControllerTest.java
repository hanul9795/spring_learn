package com.example.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//24.04.28
//spring은 특정 어노테이션이 붙어있는 클래스 파일들을 new해서 스프링 컨테이너에 관리 (모든 클래스 메모리에 new X) ex)Controller
@RestController
public class BlogControllerTest {
	
	//http://localhost:8080/test/hello
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}
}
