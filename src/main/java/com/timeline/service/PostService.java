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
import com.timeline.vo.PostfileVo;
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
	
	@Transactional
	public int writePheed(HttpServletRequest request, HttpServletResponse response, List<Object> multiParam) throws Exception{
		int userNo = uDao.checkAuthUser(request, response);
		int postFlag = 0;
		HashMap<String, Object> map = (HashMap<String, Object>) multiParam.get(1);
		List<String> imgList = (List<String>)(multiParam.get(0));
		String postContent = (String)map.get("postContent");
		
		PostVo pVo = new PostVo();
		pVo.setPostContent(postContent);
		pVo.setUserNo(userNo);
		if(imgList.size() > 0)
			pVo.setPostRepImg(imgList.get(0));

		pDao.writePheed(pVo);
		int postNo = pVo.getPostNo();
		
		for(int i=0; i<imgList.size(); i++) {
			
			PostfileVo vo = new PostfileVo();
			vo.setPostNo(postNo);
			
			vo.setPostFilePath((String)imgList.get(i));
			postFlag = pDao.storeImg(vo);
			
			if(postFlag == 0) {
				System.out.println("error");
				break;
			}
		}
		
		return postFlag;
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
	
	public List<PostfileVo> loadPheedImg(int postNo){
		return pDao.loadPheedImg(postNo);
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
