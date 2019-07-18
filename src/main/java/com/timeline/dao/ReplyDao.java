package com.timeline.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.timeline.vo.PostVo;
import com.timeline.vo.ReplyVo;
import com.timeline.vo.ReplytagVo;

@Repository
public class ReplyDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int writeReply(ReplyVo vo) {
		return sqlSession.insert("reply.insertReply", vo);
	}
	
	public int shareReply(ReplytagVo vo) {
		return sqlSession.insert("tag.insertReplytag", vo);
	}
	
	public List<ReplyVo> detailReply(PostVo vo){
		return sqlSession.selectList("reply.selectReply", vo);
	}
	
	public int findLiked(Map<String, Object> map) {
		return sqlSession.selectOne("reply.selectLike", map);
	}
	
	public int likePheed(Map<String, Object> map) {
		return sqlSession.insert("reply.insertLike", map);
	}
	
	public int unlikePheed(Map<String, Object> map) {
		return sqlSession.delete("reply.deleteLike", map);
	}

}
