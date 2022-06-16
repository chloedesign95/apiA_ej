package edu.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SampleController {
	
	@RequestMapping(value="/sample1.do", method = RequestMethod.GET)
	public String sample1(Model model) {
		
		model.addAttribute("key","안녕하세요");
		
		
		
		return "sample/sample01";
	}
	
	@RequestMapping(value="/sample2.do")
	public String sample2(String testData,Model model) {
		/*
		 spring mvc에서 파라미터를 받는 방법은 매개변수 명을 파라미터 명과 일치시키 던가
		 @RequestParam 어노테이션을 사용하면 된다.
		 이때 파라미터는 기본적으로 문자열 데이터를 가지나 기본타입에 대해서는 자동 형변환이 가능하다.
		 만약 형변환 할 수 없는 데이터가 넘어오는 경우 요청 오류가 날 수 있으므로 주의 해야한다. 
		 
		 */
		model.addAttribute("key", "hello");
		System.out.println("testData:::::::"+testData);
		return "sample/sample02";
	}
}




