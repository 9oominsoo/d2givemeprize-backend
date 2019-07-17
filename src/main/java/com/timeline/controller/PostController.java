package com.timeline.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timeline.service.PostService;
import com.timeline.vo.PostVo;
import com.timeline.vo.UserVo;

@Controller
@RequestMapping(value="/post")
public class PostController {
	
	@Autowired
	private PostService service;
	
	//타임라인 페이지 로드
	@RequestMapping(value="/timelineform")
	public String timelineForm() {
		System.out.println("load timeline page");
		
		return "/WEB-INF/views/posts/timeline.jsp";
	}
	
	//게시글 작성
	@ResponseBody
	@RequestMapping(value="/writePheed", method=RequestMethod.POST)
	public int writePheed(@RequestBody PostVo vo, HttpSession session) {
		System.out.println("write pheed...");
		System.out.println("Pheed Info: " + vo.toString());
		
		UserVo me = (UserVo)(session.getAttribute("authUser"));

		return service.writePheed(vo, me);
	}
	
	//모든 게시글 로드
	@ResponseBody
	@RequestMapping(value="/loadPheed", method=RequestMethod.POST)
	public List<PostVo> loadPheed(){
		System.out.println("load pheed...");
		
		return service.loadPheed();
	}
	
	//유저별 게시글 로드(나의 게시글 + 친구 게시글 로드)
	@ResponseBody
	@RequestMapping(value="/loadMyPheed", method=RequestMethod.GET)
	public List<PostVo> loadMyPheed(HttpSession session){
		System.out.println("load my pheed...");
		
		UserVo me = (UserVo)(session.getAttribute("authUser"));
		
		return service.loadMyPheed(me);
	}
	
	//게시글 상세보기
	@ResponseBody
	@RequestMapping(value="/detailPheed", method=RequestMethod.POST)
	public PostVo detailPheed(HttpSession session, @RequestBody PostVo vo) {
		System.out.println("load distinct pheed No " + vo.getPostNo() + "...");
		
		UserVo me = (UserVo)(session.getAttribute("authUser"));
		
		return service.detailPheed(me, vo);
	}
		
	//게시글 좋아요
	@ResponseBody
	@RequestMapping(value="/likePheed", method=RequestMethod.POST)
	public int likeTogglePheed(HttpSession session, @RequestBody PostVo vo) {
		System.out.println("like/unlike pheed No " + vo.getPostNo() + "...");
		
		UserVo me = (UserVo)(session.getAttribute("authUser"));
		
		return service.likeTogglePheed(me, vo);
	}
	
}
