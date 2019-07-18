package com.timeline.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timeline.dao.ReplyDao;
import com.timeline.vo.PostVo;
import com.timeline.vo.ReplyVo;
import com.timeline.vo.ReplytagVo;
import com.timeline.vo.UserVo;


@Service
public class ReplyService {
	
	@Autowired 
	private ReplyDao dao;
	
	@Transactional
	public int writeReply(List<Object> multiParam, UserVo vo) {
		HashMap<String, Object> map = (HashMap<String, Object>) multiParam.get(1);
		
		List<Integer> tagList = (List<Integer>)(multiParam.get(0));
		
		int tagFlag = 0;
		int writerNo = vo.getUserNo();
		
		ReplyVo rVo = new ReplyVo();
		rVo.setReplyContent((String)map.get("replyContent"));
		rVo.setWriterNo(writerNo);
		rVo.setPostNo(Integer.parseInt((String)map.get("postNo")));
		
		dao.writeReply(rVo);
		int replyNo = rVo.getReplyNo();
		
		for(int i=0; i<tagList.size(); i++) {
			
			ReplytagVo tVo = new ReplytagVo();
			tVo.setReplyNo(replyNo);
			
			tVo.setUserNo(Integer.parseInt(""+tagList.get(i)));
			tagFlag = dao.shareReply(tVo);
			if(tagFlag == 0) {
				System.out.println("error");
				break;
			}
		}
		
		return tagFlag;
	}
	
	@Transactional
	public int likeToggleReply(UserVo uVo, ReplyVo rVo) {
		Map<String, Object> liked = new HashMap<String, Object>();
		liked.put("userNo", uVo.getUserNo());
		liked.put("replyNo", rVo.getReplyNo());
		
		int result;
		int exist = dao.findLiked(liked);
		
		if(exist == 0) {
			// like function
			 result = dao.likePheed(liked);
		}else {
			// unlike function
			result = dao.unlikePheed(liked);
		}
		return result;
	}
}
