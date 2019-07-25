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

import com.timeline.dao.UserDao;
import com.timeline.vo.PostUserVo;
import com.timeline.vo.PostVo;
import com.timeline.vo.UserRelationVo;
import com.timeline.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	@Transactional
	public int insertUserBatch(List<UserVo> list) {
		List<UserVo> dividelist = new ArrayList<UserVo>();
		int insertCnt = 0;
		
		for(int i=0, totalSize = list.size(); i < totalSize; i++) {
		    UserVo vo = list.get(i);
		    dividelist.add(vo);
		     
		    if(insertCnt == 999 || totalSize==(i+1)) {
		        dao.insertUserBatch(dividelist);
		        dividelist = new ArrayList<>();
		        insertCnt = 0;
		    } else {
		        insertCnt++;
		    }
		}
		
		return dao.insertUserBatch(list);
	}
	
	public UserVo logIn(UserVo vo, HttpServletResponse response) throws Exception {
		return dao.logIn(vo, response);
	}
	
	public Map<String, Object> signUp(UserVo vo) {
		int status = dao.signUp(vo);
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(status == 1)
			result.put("status", "success");
		else 
			result.put("status", "failed");
		
		return result;
	}
	
	public Map<String, Object> checkDupId(UserVo vo) {
		UserVo temp = dao.checkDupId(vo);
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(temp != null) {
			// 중복되는 아이디가 있는 경우
			result.put("status", "failed");
		}else {
			// 중복되는 아이디가 없는 경우
			result.put("status", "success");
		}
		
		return result;
	}
	
	public Map<String, Object> modifyInfo(UserVo vo) {
		int status = dao.modifyInfo(vo);
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(status == 1)
			result.put("status", "success");
		else 
			result.put("status", "failed");
		
		return result;
	}
	
	public int signOut(UserVo vo) {
		return dao.signOut(vo);
	}
	
	public PostUserVo findAuthUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int authUserNo = dao.checkAuthUser(request, response);
		
		return dao.findUser(authUserNo);
	}
	
	public Map<String, Object> findUserInfo(HttpServletRequest request, HttpServletResponse response, int userNo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// find userInfo
		PostUserVo uVo = dao.findUser(userNo);
		map.put("selectedUser", uVo);
		
		// find userPosts
		List<PostVo> postList = dao.findUserPost(userNo);
		
		map.put("postList", postList);
		
		// find relation
		int authUserNo = dao.checkAuthUser(request, response);
		UserRelationVo rVo = new UserRelationVo();
		rVo.setRelationTo(userNo);
		rVo.setRelationFrom(authUserNo);
		
		UserRelationVo relation = dao.checkUserRelation(rVo);
		map.put("relation", relation);
		
		return map;
	}
	
	public UserRelationVo checkUserRelation(HttpServletRequest request, HttpServletResponse response, int userNo) throws Exception {
		UserRelationVo vo = new UserRelationVo();
		int authUserNo = dao.checkAuthUser(request, response);
		
		vo.setRelationTo(userNo);
		vo.setRelationFrom(authUserNo);
		
		UserRelationVo result = new UserRelationVo();
		
		result = dao.checkUserRelation(vo); 
		
		return result;
	}
	
	public Map<String, Object> follow(HttpServletRequest request, HttpServletResponse response, int userNo) throws Exception {
		UserRelationVo vo = new UserRelationVo();
		Map<String, Object> result = new HashMap<String, Object>();
		int authUserNo = dao.checkAuthUser(request, response);
		
		vo.setRelationTo(userNo);
		vo.setRelationFrom(authUserNo);
		
		int status = dao.follow(vo);
		
		if(status == 1) {
			result.put("status", "success");
		}else {
			result.put("status", "failed");
		}
		
		return result;
	}
	
	public Map<String, Object> unfollow(HttpServletRequest request, HttpServletResponse response, int userNo) throws Exception {
		UserRelationVo vo = new UserRelationVo();
		Map<String, Object> result = new HashMap<String, Object>();
		int authUserNo = dao.checkAuthUser(request, response);
		
		vo.setRelationTo(userNo);
		vo.setRelationFrom(authUserNo);
		
		int status = dao.unfollow(vo);

		if(status == 1) {
			result.put("status", "success");
		}else {
			result.put("status", "failed");
		}
		
		return result;
	}
	
	public List<UserVo> loadUser(){
		return dao.loadUser();
	}
	
	public List<PostUserVo> loadFollowers(HttpServletRequest request, HttpServletResponse response, int userNo) throws Exception{
		int authUserNo = dao.checkAuthUser(request, response);
		List<PostUserVo> list = dao.loadFollowers(userNo);
		
		for(int i=0; i<list.size(); i++) {
			UserRelationVo rVo = new UserRelationVo();
			rVo.setRelationFrom(authUserNo);
			rVo.setRelationTo(list.get(i).getUserNo());
			
			if(dao.checkUserRelation(rVo) != null)
				list.get(i).setFollowed(true);
			else 
				list.get(i).setFollowed(false);
		}
		
		return list;
	}
	
	public List<PostUserVo> loadFollowings(HttpServletRequest request, HttpServletResponse response, int userNo) throws Exception{
		int authUserNo = dao.checkAuthUser(request, response);
		List<PostUserVo> list = dao.loadFollowings(userNo);
		
		for(int i=0; i<list.size(); i++) {
			UserRelationVo rVo = new UserRelationVo();
			rVo.setRelationFrom(authUserNo);
			rVo.setRelationTo(list.get(i).getUserNo());
			
			if(dao.checkUserRelation(rVo) != null)
				list.get(i).setFollowed(true);
			else 
				list.get(i).setFollowed(false);
		}
		
		return list;
	}
	
}
