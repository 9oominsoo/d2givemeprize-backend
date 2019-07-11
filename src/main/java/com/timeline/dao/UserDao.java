package com.timeline.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.timeline.vo.UserRelationVo;
import com.timeline.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired 
	private SqlSession sqlSession;
	
	public UserVo logIn(UserVo vo) {
		return sqlSession.selectOne("user.selectLoginUser", vo);
	}
	
	public int signUp(UserVo vo) {
		return sqlSession.insert("user.insertUser", vo);
	}
	
	public UserVo checkDupId(UserVo vo) {
		return sqlSession.selectOne("user.selectUserId", vo);
	}
	
	public UserVo findUser(int userNo) {
		return sqlSession.selectOne("user.selectUser", userNo);
	}
	
	public UserRelationVo checkUserRelation(UserRelationVo vo) {
		return sqlSession.selectOne("user.selectUserRelation", vo);
	}
	
	public int follow(UserRelationVo vo) {
		return sqlSession.insert("user.insertUserRelation", vo);
	}
	
	public int unfollow(UserRelationVo vo) {
		return sqlSession.delete("user.deleteUserRelation", vo);
	}
}
