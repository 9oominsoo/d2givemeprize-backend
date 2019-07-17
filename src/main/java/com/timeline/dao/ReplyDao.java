package com.timeline.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
