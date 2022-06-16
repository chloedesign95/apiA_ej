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
	
	// 0616  ȸ�������ϱ� 13.
	@Autowired
	UserService userService;
	

	@RequestMapping(value="/register.do", method=RequestMethod.GET)
	public String register(HttpServletRequest request, HttpSession session) {
		HashMap<String,String> test = new HashMap<String,String>();
		
		test.put("name","ȫ�浿");
		test.put("age","20");
		
		session = request.getSession();
		
		session.setAttribute("user", test);
		
		return "user/register";
	}
	// 0616 ȸ������ �� ���� �߱� 03. 
	@RequestMapping(value="/register.do", method=RequestMethod.POST)
	public String register(UserVo vo, Model model) {
		/* �Ķ���͸� �޼ҵ�� ���޹޴� ����� �Ű������� ���� �Ķ���� Ű�� ���ߴ� ����� �Ű����� VO�� �ʵ� ���� ���ߴ� ����� �ִ�.)
		
		/*model.addAttribute("name",name);
		model.addAttribute("age",age);
		model.addAttribute("addr",addr);
		model.addAttribute("phone",phone);*/
		
		model.addAttribute("vo", vo);
		
		return "user/info";
		
	}
	
	 // 0616  ȸ�������ϱ� 03. ȸ������. controller 
	@RequestMapping(value="/join.do", method=RequestMethod.GET)
	public String join() {
		
		 // 0616  ȸ�������ϱ� 04. 
		return "user/join";
	}
	
	 // 0616  ȸ�������ϱ� 05.
	@RequestMapping(value="/join.do", method=RequestMethod.POST)
	public String join(UserVo vo) {
	
		// 0616  ȸ�������ϱ� 14.
		int result = userService.insertUser(vo);
		
		
	 // 0616  ȸ�������ϱ� 08 
		return "redirect:/"; //�����̷�Ʈ �Ҷ��� ���� ���ڿ��� redirect: Ű���� �ڷ� url��� ���� �ش�.
	}
}









