package edu.board.controller;

import java.net.http.HttpRequest;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.board.service.UserService;
import edu.board.vo.UserVo;

@RequestMapping(value="/user")
@Controller
public class UserController {
	
	// 0616  회원가입하기 13.
	@Autowired
	UserService userService;
	

	@RequestMapping(value="/register.do", method=RequestMethod.GET)
	public String register(HttpServletRequest request, HttpSession session) {
		HashMap<String,String> test = new HashMap<String,String>();
		
		test.put("name","홍길동");
		test.put("age","20");
		
		session = request.getSession();
		
		session.setAttribute("user", test);
		
		return "user/register";
	}
	// 0616 회원가입 후 정보 뜨기 03. 
	@RequestMapping(value="/register.do", method=RequestMethod.POST)
	public String register(UserVo vo, Model model) {
		/* 파라미터를 메소드로 전달받는 방법은 매개변수의 명을 파라미터 키와 맞추는 방법과 매개변수 VO의 필드 명을 맞추는 방법이 있다.)
		
		/*model.addAttribute("name",name);
		model.addAttribute("age",age);
		model.addAttribute("addr",addr);
		model.addAttribute("phone",phone);*/
		
		model.addAttribute("vo", vo);
		
		return "user/info";
		
	}
	
	 // 0616  회원가입하기 03. 회원가입. controller 
	@RequestMapping(value="/join.do", method=RequestMethod.GET)
	public String join() {
		
		 // 0616  회원가입하기 04. 
		return "user/join";
	}
	
	 // 0616  회원가입하기 05.
	@RequestMapping(value="/join.do", method=RequestMethod.POST)
	public String join(UserVo vo) {
	
		// 0616  회원가입하기 14.
		int result = userService.insertUser(vo);
		
		
	 // 0616  회원가입하기 08 
		return "redirect:/"; //리다이렉트 할때는 리턴 문자열에 redirect: 키워드 뒤로 url경로 값을 준다.
	}
}









