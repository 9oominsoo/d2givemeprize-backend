package com.timeline.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.timeline.vo.AlarmPheedVo;
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
	
	public int reReply(ReplyVo vo) {
		return sqlSession.insert("reply.insertReReply", vo);
	}
	
	public List<ReplyVo> loadReReply(int replyNo) {
		return sqlSession.selectList("reply.selectReReply", replyNo);
	}
	
	public int shareReply(ReplytagVo vo) {
		return sqlSession.insert("tag.insertReplytag", vo);
	}
	
	public int storeAlarmPheed(AlarmPheedVo vo) {
		return sqlSession.insert("tag.insertAlarmPheed", vo);
	}
	
	public int findGroupNo(int parentReplyNo) {
		return sqlSession.selectOne("reply.selectGroupNo", parentReplyNo);
	}
	
	public int findOrderNo(int groupNo) {
		return sqlSession.selectOne("reply.selectOrderNo", groupNo);
	}
	
	public List<ReplyVo> detailReply(PostVo vo){
		return sqlSession.selectList("reply.selectReply", vo);
	}
	
	public int findLiked(Map<String, Object> map) {
		return sqlSession.selectOne("reply.selectLike", map);
	}
	
	public int likeReply(Map<String, Object> map) {
		return sqlSession.insert("reply.insertLike", map);
	}
	
	public int unlikeReply(Map<String, Object> map) {
		return sqlSession.delete("reply.deleteLike", map);
	}

}
