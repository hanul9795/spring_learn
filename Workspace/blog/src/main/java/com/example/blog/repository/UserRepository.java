package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.model.User;

//data access object
//Di
//자동으로 bean으로 등록이 된다. 
//@Repository 생략 가능
public interface UserRepository extends JpaRepository<User,Integer> { //User table을 관리하는 repository이며 primary key 형식은 int다
	
}
