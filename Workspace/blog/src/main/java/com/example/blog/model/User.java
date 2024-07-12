package com.example.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//orm -> Java(혹은 다른언어)object -> 테이블로 매핑해주는 기술
@Entity //User클래스가 MySQL에 자동으로 테이블로 생성된다
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert //insert시에 null 인 필드 제외
public class User {
	
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다 - 
	private int id; //오라클: 시퀀스, MYSQL: auto_increment - 기본키
	
	@Column(nullable = false, length = 30, unique = true) //nullable = null값 사용 가능 여부, length = 최대 길이
	private String username; // 아이디
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//DB는 RoleType이 없다 따라서 EnumType이 String란것을 알려줘야 한다
	@Enumerated(EnumType.STRING)
	//@ColumnDefault(" 'user' ")
	private RoleType role; //Enum을 쓰는게 좋음 -도메인 범위를 정하는 것이 좋음 //ADMIN,USER
	
	@CreationTimestamp //시간 자동 입력
	private Timestamp createDate;
	
}
