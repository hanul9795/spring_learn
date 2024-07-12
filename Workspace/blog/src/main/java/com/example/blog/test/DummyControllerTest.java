package com.example.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;

import jakarta.transaction.Transactional;

//html 파일이 아니라 data를 리턴해주는 Controller = RestController
@RestController
public class DummyControllerTest {
	
	@Autowired  //UserRepository 타입으로 스프링이 관리하는 객체가 있다면 이곳에 넣어라  의존성 주입 - DI
	private UserRepository userRepository;
	
	//save = id를 전달하지 않으면 insert를 하고 
	//save = id 전달하고 해당 id 데이터가 있으면 update하주고
	//save = id 전달하고 해당 id 데이터가 없으면 incert한다
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
			return new IllegalArgumentException("삭제에 실패했습니다. 해당 id는 존재하지 않습니다. id : " + id);}
		}); // 최신 업데이트로 인해 존재하지 않는 id를 삭제하려 할 때 오류가 발생되지 않기 때문에 한번 검색해서 존재 여부를 판단해줘야 한다.
		
		userRepository.deleteById(id);
		return "삭제 되었습니다. id: " + id;
	}
	
	@Transactional //save없어도 저장이 된다 - 함수 종료시 자동 commit이 된다
	@PutMapping("dummy/user/{id}") //Put과 Get다르면 상관x
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { //json 데이터 요청 -> Java 오브젝트로 변환 (MessageConverter의 Jackson이 변환) 이때 사용되는 annotation은 @RequestBody
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
		return new IllegalArgumentException("수정에 실패하였습니다.");});//자바 매개변수 안에 함수 불가
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user); //incert때 사용 하지만 다른 값들이 null로 변환됨으로 사용 잘 안함
		
		//더티 체킹 - 
		return user;
	}
	@GetMapping("/dummy/user")
	public List<User> list(){
		return userRepository.findAll();
	}
	//한 페이지당 2건에 데이터를 리턴받아 사용
	@GetMapping("/dummy/user/page")
	public List<User> pageList(@PageableDefault(size=2,sort="id",direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable); //List->Page, .getContent()삭제
		
		if(pagingUser.isLast()) {}
		List<User> users = pagingUser.getContent();
		return users; 
	}
	//{id}주소로 파라미터를 전달 받을 수 있음
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//user/4를 찾으면 만약에 db에서 못팢으면 user가 null이 됨
		//그렇게 되면 return null이 되면 프로그램에 문제가 생김
		//따라서 Optional로 User객체를 감싸서 가져올꺼니 null인지 아닌지 판단해서 return해라 - infById의 리턴값은 Optional
		//get() null이던 아니던 포함.
		//User user = userRepository.findById(id).orElseGet(new Supplier<User>() 빈 User 객체 리턴
		//User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>()  오류값 반환
		//interface를 new하려면 익명 클래스를 만들어야 함
//		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//			@Override
//			public User get() {
//				return new User();
//			}
//		}); //만약 값이 없으면 밑부분 new User();이 실행 - 빈 객체를 리턴 - 빈객체는 null 이 아님
//		
//		return user;
//	}
		
		
		
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
			return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);}
		});
		//요청 : 웹 브라우저
		//user 객체 = 자바 오브젝트
		//웹 브라우저는 자바의 객체를 이해할 수 없다
		//이해수 있도럭 객체를 json파일로 변환 기존 (Gson 라이브러리가 데이터변환)
		//스프링부트 = MessageConverter가 응답시 자동 작동
		//자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson라이브러리를 호출해서 user오브젝트를 json으로 변환하여 브라우저에게 줌
		 
		return user;
		}
//람다식
	//User user = userRepository.findById(id).orElseThrow(()->{
	//		return new IllegalArgumentException("해당 사용자는 없습니다.");});
	
	
	//AOP
	//http://localhost:8000/blog/dummy/join(요청)
	//http의 body에 username, password, email 데이터를 가지고 (요청)
	
	//매개변수 사용할 때 변수명을 같게 하든가(String username), 다르게하려면 @ReauestParam("username") String u가 가능
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id : " + user.getId());	//안들어가서 신경 x
		System.out.println("username : " +user.getUsername());
		System.out.println("password : " +user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate()); //@CreationTimestamp 작동해서 자동으로 incert
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원 가입이 완료되었습니다.";
	}
}
