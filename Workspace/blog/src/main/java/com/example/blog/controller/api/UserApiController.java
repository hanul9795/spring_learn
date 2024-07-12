package com.example.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.service.UserService;

import jakarta.servlet.http.HttpSession;

import com.example.blog.DTO.ResponseDTO;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/user")
	public ResponseDTO<Integer> save(@RequestBody User user) { //username,password,email
		System.out.println("UserApiController: save호출됨");
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		//실제로 DB에 insert하고 아래에서 return되면 됨
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1);
		
	}
	
	@PostMapping("/api/user/login")
	public ResponseDTO<Integer> login(@RequestBody User user,HttpSession session){
		System.out.println("UserApiController: login호출됨");
		User principal =  userService.로그인(user);//principal - 접근 주체
		
		if(principal != null) {
			session.setAttribute("principal", principal);
		}
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1);
	}
}
