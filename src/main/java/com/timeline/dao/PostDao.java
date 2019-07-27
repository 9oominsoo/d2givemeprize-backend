package com.timeline.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.timeline.vo.AlarmPheedVo;
import com.timeline.vo.PostVo;
import com.timeline.vo.PostfileVo;
import com.timeline.vo.PosttagVo;
import com.timeline.vo.UserVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int writePheed(PostVo vo) {
		return sqlSession.insert("post.insertPost", vo);
	}
	
	public int storeImg(PostfileVo vo) {
		return sqlSession.insert("post.insertFilepath", vo);
	}
	
	public int sharePost(PosttagVo vo) {
		return sqlSession.insert("tag.insertPosttag", vo);
	}
	
	public int storeAlarmPheed(AlarmPheedVo vo) {
		return sqlSession.insert("tag.insertPostAlarmPheed", vo);
	}
	
	public List<PostVo> loadPheed(){
		return sqlSession.selectList("post.selectPost");
	}
	
	public List<PostVo> loadMyPheed(UserVo vo){
		return sqlSession.selectList("post.selectMyPost", vo);
	}
	
	public PostVo detailPheed(PostVo vo) {
		return sqlSession.selectOne("post.selectDistinctPost", vo);
	}
	
	public List<PostfileVo> loadPheedImg(int postNo) {
		return sqlSession.selectList("post.selectPostImg", postNo);
	}
	
	public int hitPheed(PostVo vo) {
		return sqlSession.update("post.updateHit", vo);
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
