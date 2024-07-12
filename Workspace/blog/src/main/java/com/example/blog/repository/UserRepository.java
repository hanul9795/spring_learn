package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.blog.model.User;

//data access object
//Di
//자동으로 bean으로 등록이 된다. 
//@Repository 생략 가능
public interface UserRepository extends JpaRepository<User,Integer> { //User table을 관리하는 repository이며 primary key 형식은 int다
		//JPA Naming 쿼리 전략
	
	User findByUsernameAndPassword(String username, String password);
	//해당 함수는 실제로는 없으나 이런 형식으로 만들면
	//SELECT * FROM user WHERE username=?(매개변수 username 1번째 매개변수)AND password=?(매개변수 password)

	//@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
	//User login(String username, String password);
}
