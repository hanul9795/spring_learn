package com.example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping({"","/"})
	public String index() {
		// /Web-INF/views/index.jsp	yml surfix, prefix
		return "Minji";
	}
}
