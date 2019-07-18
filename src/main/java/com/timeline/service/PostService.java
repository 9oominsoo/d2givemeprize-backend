package com.timeline.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.timeline.dao.PostDao;
import com.timeline.dao.ReplyDao;
import com.timeline.vo.PostVo;
import com.timeline.vo.ReplyVo;
import com.timeline.vo.UserVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao pDao;
	
	@Autowired
	private ReplyDao rDao;
	
	public int writePheed(PostVo pVo, UserVo uVo){
		pVo.setUserNo(uVo.getUserNo());
		return pDao.writePheed(pVo);
	}
	
	public List<PostVo> loadPheed(){
		return pDao.loadPheed();
	}
	
	public List<PostVo> loadMyPheed(UserVo vo){
		return pDao.loadMyPheed(vo);
	}
	
	public Map<String,Object> detailPheed(UserVo uVo, PostVo pVo) {
		pVo.setUserNo(uVo.getUserNo());
		
		Map<String, Object> map = new HashMap<String, Object>();
		PostVo searchedPost = pDao.detailPheed(pVo);
		List<ReplyVo> searchedReply = rDao.detailReply(pVo);
		
		map.put("post", searchedPost);
		map.put("replyList", searchedReply);
		pDao.hitPheed(pVo);
		
		return map;
	}
	
	@Transactional
	public int likeTogglePheed(UserVo uVo, PostVo pVo) {
		Map<String, Object> liked = new HashMap<String, Object>();
		liked.put("userNo", uVo.getUserNo());
		liked.put("postNo", pVo.getPostNo());
		
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
