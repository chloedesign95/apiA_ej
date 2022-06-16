package edu.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.board.vo.UserVo;

//0616  ȸ�������ϱ� 15.
@Repository
public class UserDAO {
	
	// 0616  ȸ�������ϱ� 18.
	@Autowired
	SqlSession sqlSession;
	
	
	// 0616  ȸ�������ϱ� 16.
	public int insert(UserVo vo) {
		
		// 0616  ȸ�������ϱ� 19.       // 0616  ȸ�������ϱ� 23. (�Ű����� �ֱ�)
		int result = sqlSession.insert("edu.board.mapper.userMapper.insert",vo);
		
		// 0616  ȸ�������ϱ� 17.  // 0616  ȸ�������ϱ� 24. (result return.)
		return result;
	}
}
