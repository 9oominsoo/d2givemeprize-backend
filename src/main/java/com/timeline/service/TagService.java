package com.timeline.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timeline.dao.TagDao;
import com.timeline.vo.PosttagVo;
import com.timeline.vo.UserVo;


@Service
public class TagService {
	
	@Autowired 
	private TagDao dao;
	
	public List<UserVo> searchFriends(String name, UserVo me){
		List<UserVo> result = new ArrayList<UserVo>();
		List<UserVo> list = dao.searchFriends(me);
		
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
	

	//register new schedule
	public int sharePost(List<Object> multiParam) {
		HashMap<String, Object> map = (HashMap<String, Object>) multiParam.get(1);
		
		List<Integer> tagList = (List<Integer>)(multiParam.get(0));
		
		int tagFlag = 0;
		int postNo = Integer.parseInt((String)map.get("postNo"));
		
		for(int i=0; i<tagList.size(); i++) {
			
			PosttagVo vo = new PosttagVo();
			vo.setPostNo(postNo);
			
			vo.setUserNo(Integer.parseInt(""+tagList.get(i)));
			tagFlag = dao.sharePost(vo);
			if(tagFlag == 0) {
				System.out.println("error");
				break;
			}
		}
		
		return tagFlag;
	}
}
