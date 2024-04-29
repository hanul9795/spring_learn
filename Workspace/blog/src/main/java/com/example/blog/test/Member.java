package com.example.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
@Data // @Getter + @Setter
//@AllArgsConstructor //모든 필드를 다 쓰는 생성자 생성
@NoArgsConstructor //빈 생성자
//@RequiredArgsConstructor //final 붙은 변수에 대한 생성자 생성 final 없으면 안 만듬
public class Member {
	private  int id;
	private  String username;
	private  String password;
	private  String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
}
