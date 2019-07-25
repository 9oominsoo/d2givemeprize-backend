package com.timeline.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timeline.dao.TagDao;
import com.timeline.dao.UserDao;
import com.timeline.vo.AlarmPheedVo;
import com.timeline.vo.UserVo;


@Service
public class TagService {
	
	@Autowired 
	private TagDao dao;
	
	@Autowired
	private UserDao uDao;
	
	public List<UserVo> searchUsers(String name) {
		List<UserVo> result = new ArrayList<UserVo>();
		List<UserVo> list = uDao.loadUser();
		
		for(UserVo vo : list) {
			String userName = vo.getUserName();
			if(userName.indexOf(name) != -1) {
				result.add(vo);
			}else {
				continue;
			}
		}
		
		return result;
	}
	
	public List<UserVo> searchFriends(HttpServletRequest request, HttpServletResponse response, String name) throws Exception{
		int authUserNo = uDao.checkAuthUser(request, response);
		List<UserVo> result = new ArrayList<UserVo>();
		List<UserVo> list = dao.searchFriends(authUserNo);
		
		for(UserVo vo : list) {
			String userName = vo.getUserName();
			if(userName.indexOf(name) != -1) {
				result.add(vo);
			}else {
				continue;
			}
		}
		
		for(UserVo vo: result)
			System.out.println(vo.toString());
		
		return result;
	}
	
	public List<AlarmPheedVo> checkAlarm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int authUserNo = uDao.checkAuthUser(request, response);
		
		return dao.checkAlarm(authUserNo);
	}
	
	public Map<String, Object> readAlarm(AlarmPheedVo vo){
		Map<String, Object> result = new HashMap<String, Object>();
		int status = dao.readAlarm(vo);
		
		if(status == 1) {
			// 중복되는 아이디가 있는 경우
			result.put("status", "success");
		}else {
			// 중복되는 아이디가 없는 경우
			result.put("status", "failed");
		}
		
		return result;
	}
}
