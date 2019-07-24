package com.timeline.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.timeline.vo.PosttagVo;
import com.timeline.vo.UserVo;

@Repository
public class TagDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<UserVo> searchFriends(int userNo){
		return sqlSession.selectList("tag.selectFriends", userNo);
	}
	
	/*
	public int sharePost(PosttagVo vo) {
		return sqlSession.insert("tag.insertPosttag", vo);
	}
	*/
}
