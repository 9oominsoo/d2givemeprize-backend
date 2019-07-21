package com.timeline.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timeline.dao.PostDao;
import com.timeline.dao.ReplyDao;
import com.timeline.dao.UserDao;
import com.timeline.vo.PostVo;
import com.timeline.vo.ReplyVo;
import com.timeline.vo.UserVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao pDao;
	
	@Autowired
	private ReplyDao rDao;
	
	@Autowired
	private UserDao uDao;
	
	public int writePheed(HttpServletRequest request, HttpServletResponse response, PostVo pVo) throws Exception{
		int userNo = uDao.checkAuthUser(request, response);
		
		pVo.setUserNo(userNo);
		return pDao.writePheed(pVo);
	}
	
	public List<PostVo> loadPheed(){
		return pDao.loadPheed();
	}
	
	public List<PostVo> loadMyPheed(HttpServletRequest request, HttpServletResponse response) throws Exception{
		UserVo uVo = new UserVo();
		int userNo = uDao.checkAuthUser(request, response);
		
		uVo.setUserNo(userNo);
		return pDao.loadMyPheed(uVo);
	}
	
	public Map<String,Object> detailPheed(HttpServletRequest request, HttpServletResponse response, int postNo) throws Exception{
		PostVo pVo = new PostVo();
		int userNo = uDao.checkAuthUser(request, response);
		pVo.setPostNo(postNo);
		pVo.setUserNo(userNo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		PostVo searchedPost = pDao.detailPheed(pVo);
		List<ReplyVo> searchedReply = rDao.detailReply(pVo);
		
		map.put("post", searchedPost);
		map.put("replyList", searchedReply);
		pDao.hitPheed(pVo);
		
		return map;
	}
	
	@Transactional
	public int likeTogglePheed(HttpServletRequest request, HttpServletResponse response, int postNo) throws Exception {
		int userNo = uDao.checkAuthUser(request, response);
		
		Map<String, Object> liked = new HashMap<String, Object>();
		liked.put("userNo", userNo);
		liked.put("postNo", postNo);
		
		int result;
		int exist = pDao.findLiked(liked);
		
		if(exist == 0) {
			// like function
			 result = pDao.likePheed(liked);
		}else {
			// unlike function
			result = pDao.unlikePheed(liked);
		}
		return result;
	}
	
}
