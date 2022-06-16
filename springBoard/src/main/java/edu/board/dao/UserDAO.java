package edu.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.board.vo.UserVo;

//0616  회원가입하기 15.
@Repository
public class UserDAO {
	
	// 0616  회원가입하기 18.
	@Autowired
	SqlSession sqlSession;
	
	
	// 0616  회원가입하기 16.
	public int insert(UserVo vo) {
		
		// 0616  회원가입하기 19.       // 0616  회원가입하기 23. (매개변수 넣기)
		int result = sqlSession.insert("edu.board.mapper.userMapper.insert",vo);
		
		// 0616  회원가입하기 17.  // 0616  회원가입하기 24. (result return.)
		return result;
	}
}
