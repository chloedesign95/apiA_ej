package edu.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.board.dao.UserDAO;
import edu.board.vo.UserVo;

//0616  회원가입하기 11. UserServiceImple 클래스 생성
@Service
public class UserServiceImple implements UserService {
	
	// 0616  회원가입하기 25
	@Autowired
	UserDAO userDao;
	
	// 0616 회원가입하기 12.
	@Override
	public int insertUser(UserVo vo) {
		
		// 0616  회원가입하기 26
		int result = userDao.insert(vo);
		
		return 0;
	}
}
