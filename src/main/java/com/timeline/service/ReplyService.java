package com.timeline.service;

import java.util.ArrayList;
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
import com.timeline.vo.AlarmPheedVo;
import com.timeline.vo.ReplyVo;
import com.timeline.vo.ReplytagVo;


@Service
public class ReplyService {
	
	@Autowired 
	private ReplyDao dao;
	
	@Autowired 
	private UserDao uDao;
	
	@Transactional
	public List<AlarmPheedVo> writeReply(HttpServletRequest request, HttpServletResponse response, List<Object> multiParam) throws Exception {
		List<Integer> tagList = (List<Integer>)(multiParam.get(0));
		HashMap<String, Object> map = (HashMap<String, Object>) multiParam.get(1);
		
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
				break;
			}
		}
		
		List<AlarmPheedVo> alarmList = new ArrayList<AlarmPheedVo>();
		AlarmPheedVo aVo = new AlarmPheedVo();
		
		aVo.setUserFrom(writerNo);
		aVo.setUserFromName((uDao.findUser(aVo.getUserFrom())).getUserName());
		aVo.setPostNo(rVo.getPostNo());
		aVo.setReplyNo(replyNo);
		aVo.setPheedType(1);
		aVo.setPheedMessage("님이 댓글에 회원님을 언급하였습니다.");
		if(tagList.size() > 0) {
			for(int i=0; i<tagList.size(); i++) {
				aVo.setUserTo(Integer.parseInt(""+tagList.get(i)));
				aVo.setUserToName((uDao.findUser(aVo.getUserTo())).getUserName());
				
				tagFlag = dao.storeAlarmPheed(aVo);
				if(tagFlag == 0) {
					break;
				}
				alarmList.add(aVo);
			}
		}
		
		return alarmList;
	}
	
	@Transactional
	public List<AlarmPheedVo> reReply(HttpServletRequest request, HttpServletResponse response, List<Object> multiParam, int replyNo) throws Exception {
		HashMap<String, Object> map = (HashMap<String, Object>) multiParam.get(1);
		
		List<Integer> tagList = (List<Integer>)(multiParam.get(0));	
		
		int tagFlag = 0;
		int writerNo = uDao.checkAuthUser(request, response);
		int parentReplyNo = replyNo;
		int groupNo = dao.findGroupNo(parentReplyNo);
		int orderNo = dao.findOrderNo(groupNo) + 1;
		
		ReplyVo rVo = new ReplyVo();
		rVo.setReplyContent((String)map.get("replyContent"));
		rVo.setWriterNo(writerNo);
		rVo.setPostNo(Integer.parseInt((String)map.get("postNo")));
		rVo.setReplyGroupNo(groupNo);
		rVo.setReplyOrderNo(orderNo);
		
		dao.reReply(rVo);
		int rereplyNo = rVo.getReplyNo();
		
		for(int i=0; i<tagList.size(); i++) {
			
			ReplytagVo tVo = new ReplytagVo();
			tVo.setReplyNo(rereplyNo);
			
			tVo.setUserNo(Integer.parseInt(""+tagList.get(i)));
			tagFlag = dao.shareReply(tVo);
			if(tagFlag == 0) {
				System.out.println("error");
				break;
			}
		}
		
		List<AlarmPheedVo> alarmList = new ArrayList<AlarmPheedVo>();
		AlarmPheedVo aVo = new AlarmPheedVo();
		
		aVo.setUserFrom(writerNo);
		aVo.setUserFromName((uDao.findUser(aVo.getUserFrom())).getUserName());
		aVo.setPostNo(rVo.getPostNo());
		aVo.setReplyNo(replyNo);
		aVo.setPheedType(1);
		aVo.setPheedMessage("님이 댓글에 회원님을 언급하였습니다.");
		if(tagList.size() > 0) {
			for(int i=0; i<tagList.size(); i++) {
				aVo.setUserTo(Integer.parseInt(""+tagList.get(i)));
				aVo.setUserToName((uDao.findUser(aVo.getUserTo())).getUserName());
				
				tagFlag = dao.storeAlarmPheed(aVo);
				if(tagFlag == 0) {
					break;
				}
				alarmList.add(aVo);
			}
		}
		
		return alarmList;
	}
	
	public List<ReplyVo> loadReReply(int replyNo){
		int replyGroupNo = dao.findGroupNo(replyNo);
		return dao.loadReReply(replyGroupNo);
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
