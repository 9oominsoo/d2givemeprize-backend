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
		
		if(status == 0)
			result.put("status", "failed");
		else 
			result.put("status", "success");
		
		return result;
	}
	
	public int checkDupId(UserVo vo) {
		UserVo temp = dao.checkDupId(vo);
		
		if(temp != null) {
			// 중복되는 아이디가 있는 경우
			return 0;
		}else {
			// 중복되는 아이디가 없는 경우
			return 1;
		}
	}
	
	public int signOut(UserVo vo) {
		return dao.signOut(vo);
	}
	
	public Map<String, Object> findUserInfo(HttpServletRequest request, HttpServletResponse response, int userNo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// find userInfo
		UserVo uVo = dao.findUser(userNo);
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
	
	public int follow(HttpServletRequest request, HttpServletResponse response, int userNo) throws Exception {
		UserRelationVo vo = new UserRelationVo();
		int authUserNo = dao.checkAuthUser(request, response);
		
		vo.setRelationTo(userNo);
		vo.setRelationFrom(authUserNo);
		
		return dao.follow(vo);
	}
	
	public int unfollow(HttpServletRequest request, HttpServletResponse response, int userNo) throws Exception {
		UserRelationVo vo = new UserRelationVo();
		int authUserNo = dao.checkAuthUser(request, response);
		
		vo.setRelationTo(userNo);
		vo.setRelationFrom(authUserNo);
		
		return dao.unfollow(vo);
	}
	
	public List<UserVo> loadUser(){
		return dao.loadUser();
	}
}
