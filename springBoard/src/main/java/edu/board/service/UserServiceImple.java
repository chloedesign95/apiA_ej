package edu.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.board.dao.UserDAO;
import edu.board.vo.UserVo;

//0616  ȸ�������ϱ� 11. UserServiceImple Ŭ���� ����
@Service
public class UserServiceImple implements UserService {
	
	// 0616  ȸ�������ϱ� 25
	@Autowired
	UserDAO userDao;
	
	// 0616 ȸ�������ϱ� 12.
	@Override
	public int insertUser(UserVo vo) {
		
		// 0616  ȸ�������ϱ� 26
		int result = userDao.insert(vo);
		
		return 0;
	}
}
