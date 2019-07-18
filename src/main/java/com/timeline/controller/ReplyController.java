package com.timeline.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timeline.service.ReplyService;
import com.timeline.vo.ReplyVo;
import com.timeline.vo.UserVo;

@Controller
@RequestMapping(value="/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService service;

	// 댓글 페이지 로드
	@RequestMapping(value = "/replyform")
	public String loginForm() {
		System.out.println("load reply page");

		return "/WEB-INF/views/tag/tag.jsp";
	}
	
	// 댓글 작성&공유
	@ResponseBody
	@RequestMapping(value="/writeReply", method=RequestMethod.POST)
	public int writeReply(@RequestBody List<Object> multiParam, HttpSession session) {
		System.out.println("write reply...");
		System.out.println(multiParam);
		
		UserVo me = (UserVo)(session.getAttribute("authUser"));
		
		return service.writeReply(multiParam, me);
	}
	
	// 대댓 작성
	
	// 댓글 좋아요 
	@ResponseBody
	@RequestMapping(value="/likeReply", method=RequestMethod.POST)
	public int likeTogglePheed(HttpSession session, @RequestBody ReplyVo vo) {
		System.out.println("like/unlike pheed No " + vo.getPostNo() + "...");
		
		UserVo me = (UserVo)(session.getAttribute("authUser"));
		
		return service.likeToggleReply(me, vo);
	}
	
}
