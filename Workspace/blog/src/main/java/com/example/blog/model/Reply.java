package com.example.blog.model;

import java.security.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length =200)
	private String content;
	
	@ManyToOne //여러개의 답변이 하나의 개시글에 달릴 수 있다
	@JoinColumn(name="boardId") //User테이블의 id값 참조
	private Board board;
	
	@ManyToOne //여러개의 답변을 하나의 유저가 적을 수 있다
	@JoinColumn(name="userId") //Board 테이블의 id값 참조
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
}
