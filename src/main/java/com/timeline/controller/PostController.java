package com.timeline.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.timeline.service.PostService;
import com.timeline.vo.PostVo;
import com.timeline.vo.UserVo;

@RestController
@CrossOrigin
@RequestMapping(value="/post")
public class PostController {
	
	@Autowired
	private PostService service;
	
	//게시글 작성
	@RequestMapping(method=RequestMethod.POST)
	public int writePheed(@RequestBody PostVo vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("write pheed...");
		System.out.println("Pheed Info: " + vo.toString());
		
		return service.writePheed(request, response, vo);
	}
	
	//모든 게시글 로드
	@RequestMapping(value="/allPheed", method=RequestMethod.GET)
	public List<PostVo> loadPheed(){
		System.out.println("load pheed...");
		
		return service.loadPheed();
	}
	
	//유저별 게시글 로드(following 중인 친구 게시글 로드)
	@RequestMapping(method=RequestMethod.GET)
	public List<PostVo> loadMyPheed(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("load my pheed...");
		
		return service.loadMyPheed(request, response);
	}
	
	//게시글 상세보기
	@RequestMapping(value="/{postno}", method=RequestMethod.GET)
	public Map<String, Object> detailPheed(@PathVariable("postno") int postNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("load distinct pheed No " + postNo + "...");
		
		return service.detailPheed(request, response, postNo);
	}
		
	//게시글 좋아요
	@RequestMapping(value="/{postno}", method=RequestMethod.PUT)
	public int likeTogglePheed(@PathVariable("postno") int postNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("like/unlike pheed No " + postNo + "...");
		
		return service.likeTogglePheed(request, response, postNo);
	}
	
}
