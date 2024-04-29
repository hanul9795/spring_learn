package com.example.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //파일 리턴
public class TempControllerTest {
	
	@GetMapping("temp/home")
	public String tempHome(){
		System.out.println("tempHome()");
		//파일 리턴 기본 경로: src/main/resources/static
		//지금상태 기본 경로: src/main/resources/statichome.html
		//정상 리턴을 위한 리턴값 return "/home.html" -> src/main/resources/static/home.html
		return "/home.html";
		}
	@GetMapping("temp/Img")
	public String templmg() {
		return "/a.png";
	}
	
	@GetMapping("temp/jsp")
	public String tempjsp() {
		//prefix: /WEB-INF/views/
		//suffix: .jsp
		//풀네임: /WEB-INF/views/NewFile.jsp
		return "NewFile";
		//이럴 때는 prefix와 suffix가 다 자동으로 붙기 때문에 파일 이름만 써야 한다
		//NewFile.jsp.jsp 혹은 /WEB-INF/views//NewFile.jsp이 되어 불러오지 못할 수 없다.
	}
}

