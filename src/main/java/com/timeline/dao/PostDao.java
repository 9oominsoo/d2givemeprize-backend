package com.timeline.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	public int findLiked(Map<String, Object> map) {
		return sqlSession.selectOne("post.selectLike", map);
	}
	
	public int likePheed(Map<String, Object> map) {
		return sqlSession.insert("post.insertLike", map);
	}
	
	public int unlikePheed(Map<String, Object> map) {
		return sqlSession.delete("post.deleteLike", map);
	}
}
