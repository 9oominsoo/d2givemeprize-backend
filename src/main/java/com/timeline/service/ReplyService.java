package com.timeline.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timeline.dao.ReplyDao;
import com.timeline.dao.UserDao;
import com.timeline.vo.ReplyVo;
import com.timeline.vo.ReplytagVo;
import com.timeline.vo.UserVo;


@Service
public class ReplyService {
	
	@Autowired 
	private ReplyDao dao;
	
	@Autowired 
	private UserDao uDao;
	
	@Transactional
	public int writeReply(HttpServletRequest request, HttpServletResponse response, List<Object> multiParam) throws Exception {
		HashMap<String, Object> map = (HashMap<String, Object>) multiParam.get(1);
		
		List<Integer> tagList = (List<Integer>)(multiParam.get(0));
		
		int tagFlag = 0;
		int writerNo = uDao.checkAuthUser(request, response);
		
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
	public int reReply(HttpServletRequest request, HttpServletResponse response, List<Object> multiParam) throws Exception {
		HashMap<String, Object> map = (HashMap<String, Object>) multiParam.get(1);
		
		List<Integer> tagList = (List<Integer>)(multiParam.get(0));	
		
		int tagFlag = 0;
		int writerNo = uDao.checkAuthUser(request, response);
		int parentReplyNo = Integer.parseInt((String)map.get("replyNo"));
		int groupNo = dao.findGroupNo(parentReplyNo);
		int orderNo = dao.findOrderNo(groupNo) + 1;
		
		ReplyVo rVo = new ReplyVo();
		rVo.setReplyContent((String)map.get("replyContent"));
		rVo.setWriterNo(writerNo);
		rVo.setPostNo(Integer.parseInt((String)map.get("postNo")));
		rVo.setReplyGroupNo(groupNo);
		rVo.setReplyOrderNo(orderNo);
		
		dao.reReply(rVo);
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
	
	public List<ReplyVo> loadReReply(int replyNo){
		dao.loadReReply(replyNo);
	}
	
	@Transactional
	public int likeToggleReply(HttpServletRequest request, HttpServletResponse response, int replyNo) throws Exception {
		Map<String, Object> liked = new HashMap<String, Object>();
		int userNo = uDao.checkAuthUser(request, response);
		
		liked.put("userNo", userNo);
		liked.put("replyNo", replyNo);
		
		int result;
		int exist = dao.findLiked(liked);
		
		if(exist == 0) {
			// like function
			 result = dao.likeReply(liked);
		}else {
			// unlike function
			result = dao.unlikeReply(liked);
		}
		return result;
	}
}
