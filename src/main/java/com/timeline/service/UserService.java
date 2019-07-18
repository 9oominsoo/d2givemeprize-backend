package com.timeline.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timeline.dao.UserDao;
import com.timeline.vo.PostVo;
import com.timeline.vo.UserRelationVo;
import com.timeline.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	public UserVo logIn(UserVo vo) {
		return dao.logIn(vo);
	}
	
	public int signUp(UserVo vo) {
		return dao.signUp(vo);
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
	
	public Map<String, Object> findUserInfo(int userNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		UserVo vo = dao.findUser(userNo);
		List<PostVo> postList = dao.findUserPost(userNo);
		
		map.put("selectedUser", vo);
		map.put("postList", postList);
		
		return map;
	}
	
	public UserRelationVo checkUserRelation(UserVo authUser, int userNo) {
		UserRelationVo vo = new UserRelationVo();
		
		vo.setRelationTo(userNo);
		vo.setRelationFrom(authUser.getUserNo());
		
		UserRelationVo result = new UserRelationVo();
		
		result = dao.checkUserRelation(vo); 
		
		return result;
	}
	
	public int follow(UserVo authUser, int userNo) {
		UserRelationVo vo = new UserRelationVo();
		
		vo.setRelationTo(userNo);
		vo.setRelationFrom(authUser.getUserNo());
		
		return dao.follow(vo);
	}
	
	public int unfollow(UserVo authUser, int userNo) {
		UserRelationVo vo = new UserRelationVo();
		
		vo.setRelationTo(userNo);
		vo.setRelationFrom(authUser.getUserNo());
		
		return dao.unfollow(vo);
	}
}
