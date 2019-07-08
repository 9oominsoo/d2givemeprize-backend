package com.timeline.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.timeline.vo.PostVo;
import com.timeline.vo.UserVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int writePheed(PostVo vo) {
		return sqlSession.insert("post.insertPost", vo);
	}
	
	public List<PostVo> loadPheed(){
		List<PostVo> list = new ArrayList<PostVo>();
		list = sqlSession.selectList("post.selectPost");
		return list;
	}
	
	public List<PostVo> loadMyPheed(UserVo vo){
		return sqlSession.selectList("post.selectMyPost", vo);
	}
}
