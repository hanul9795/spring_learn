package com.example.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;



//스프링이 컴포넌트 스캔을 통해 Vean에 등록을 해줌. = IoC해줌
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional  //밑부분이 정상적으로 모두 실행되면 commit
	public void 회원가입(User user) {
			userRepository.save(user);
	}
	
	@Transactional(readOnly=true)//select할때 트랜젝션 시작, 서비스 종료시에 트랜젝션 종료 (정합성 유지 가능)
	public User 로그인(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
	}
}
