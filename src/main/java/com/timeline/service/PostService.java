package com.timeline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timeline.dao.PostDao;
import com.timeline.vo.PostVo;
import com.timeline.vo.UserVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao dao;
	
	public int writePheed(PostVo vo){
		return dao.writePheed(vo);
	}
	
	public List<PostVo> loadPheed(){
		return dao.loadPheed();
	}
	
	public List<PostVo> loadMyPheed(UserVo vo){
		return dao.loadMyPheed(vo);
	}
}
