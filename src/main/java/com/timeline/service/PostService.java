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

import com.timeline.dao.PostDao;
import com.timeline.dao.ReplyDao;
import com.timeline.dao.UserDao;
import com.timeline.vo.AlarmPheedVo;
import com.timeline.vo.PostVo;
import com.timeline.vo.PostfileVo;
import com.timeline.vo.PosttagVo;
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
	public List<AlarmPheedVo> writePheed(HttpServletRequest request, HttpServletResponse response, List<Object> multiParam) throws Exception{
		int userNo = uDao.checkAuthUser(request, response);
		int postFlag = 0;
		List<String> imgList = (List<String>)(multiParam.get(0));
		List<Integer> tagList = (List<Integer>)(multiParam.get(1));
		HashMap<String, Object> map = (HashMap<String, Object>) multiParam.get(2);
		String postContent = (String)map.get("postContent");
		
		// 글 작성
		PostVo pVo = new PostVo();
		pVo.setPostContent(postContent);
		pVo.setUserNo(userNo);
		pVo.setPostImgCount(imgList.size());
		if(imgList.size() > 0)
			pVo.setPostRepImg(imgList.get(0));
		else 
			pVo.setPostRepImg("");

		pDao.writePheed(pVo);
		int postNo = pVo.getPostNo();

		if (imgList.size() > 0) {
			for (int i = 0; i < imgList.size(); i++) {

				PostfileVo vo = new PostfileVo();
				vo.setPostNo(postNo);

				vo.setPostFilePath((String) imgList.get(i));
				postFlag = pDao.storeImg(vo);

				if (postFlag == 0) {
					break;
				}
			}
		}
		
		for(int i=0; i<tagList.size(); i++) {
			
			PosttagVo vo = new PosttagVo();
			vo.setPostNo(postNo);
			
			vo.setUserNo(Integer.parseInt(""+tagList.get(i)));
			postFlag = pDao.sharePost(vo);
			if(postFlag == 0) {
				break;
			}
		}
		
		List<AlarmPheedVo> alarmList = new ArrayList<AlarmPheedVo>();
		AlarmPheedVo aVo = new AlarmPheedVo();
		aVo.setUserFrom(userNo);
		aVo.setUserFromName((uDao.findUser(aVo.getUserFrom())).getUserName());
		aVo.setPostNo(postNo);
		aVo.setPheedType(0);
		aVo.setPheedMessage("님이 게시글을 공유하였습니다.");
		if(tagList.size() > 0) {
			for(int i=0; i<tagList.size(); i++) {
				
				aVo.setUserTo(Integer.parseInt(""+tagList.get(i)));
				aVo.setUserToName((uDao.findUser(aVo.getUserTo())).getUserName());
				
				postFlag = pDao.storeAlarmPheed(aVo);
				if(postFlag == 0) {
					break;
				}
				alarmList.add(aVo);
				
			}
		}
		
		return alarmList;
	}
	
	public List<PostVo> loadPheed(){
		return pDao.loadPheed();
	}
	
	public Map<String,Object> loadMyPheed(HttpServletRequest request, HttpServletResponse response, int pageNumber) throws Exception{
		int authUserNo = uDao.checkAuthUser(request, response);
		int listSize = 10 ;
		int startPheedNo = 1+listSize*(pageNumber-1); //시작 피드 번호 
		int endPheedNo = listSize*pageNumber; //마지막 피드 번호 
		int countPheed = pDao.countPheed(authUserNo); //전체 피드 개수 
		int maxPage = (int)Math.ceil((double)countPheed/listSize); //최대 페이지 번호 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("authUserNo", authUserNo);
		map.put("startPheedNo", startPheedNo);
		map.put("endPheedNo", endPheedNo);
		List<PostVo> list = pDao.pagingPheed(map);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list);
		resultMap.put("maxPage", maxPage);
		return resultMap;
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
	
	public List<String> loadPheedImg(int postNo){
		List<PostfileVo> list = pDao.loadPheedImg(postNo);
		List<String> resultList = new ArrayList<String>();
		
		for(PostfileVo vo : list) {
			resultList.add(vo.getPostFilePath());
		}
		
		return resultList;
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
