package com.example.blog.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
public class Board {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	@Id
	private int id;
	
	@Column(nullable=false,length=100)
	private String title;
	
	@Lob //대용량 데이터
	private String content; //섬머노트 라이브러리 - 일반적인 글이 <html> 태그가 섞여서 디자인되어 들어감

	@ColumnDefault("0")
	private int count;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	@ManyToOne // Many = Board, User = One - 한명의 유저는 여러개의 개시글을 쓸 수 있다. 그 외) OneToOne, OneToMany
	@JoinColumn(name="userId")//필드값은 userId로 만들어진다
	private User user; // DB는 오브젝트를 저장 불가. FK(foreign key), 자바는 오브젝트를 저장할 수 있다.
	
	@OneToMany(mappedBy="board",fetch = FetchType.EAGER) // mappedBy가 있으면 연관관계의 주인이 아니다(난 FK가 아니다) DB에 컬롬 만들지 마라
	//@JoinColumn(name="replyId")는 필요 없음. 이유: 원자값이 아님(원자값: 값 하나. ','등으로 2개 이상 안됨) 답글 달릴때 마다 id를 추가할 수 없음으로
	private List<Reply> reply;
}
