package com.timeline.controller;

import java.util.List;

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

import com.timeline.service.ReplyService;
import com.timeline.vo.UserVo;

@RestController
@CrossOrigin
@RequestMapping(value="/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService service;

	// 댓글 작성&공유
	@RequestMapping(method=RequestMethod.POST)
	public int writeReply(@RequestBody List<Object> multiParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("write reply...");
		System.out.println(multiParam);
		
		return service.writeReply(request, response, multiParam);
	}
	
	// 대댓 작성
	@RequestMapping(value="/reReply", method=RequestMethod.POST)
	public int reReply(@RequestBody List<Object> multiParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("write reply on reply...");
		System.out.println(multiParam);
		
		return service.reReply(request, response, multiParam);
	}
	
	// 댓글 좋아요 
	@RequestMapping(value="/{replyno}", method=RequestMethod.POST)
	public int likeTogglePheed(@PathVariable("replyno") int replyNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("like/unlike pheed No " + replyNo + "...");
		
		return service.likeToggleReply(request, response, replyNo);
	}
	
	// 댓글 삭제 
	@RequestMapping(value="/{replyno}", method=RequestMethod.DELETE)
	public int deleteReply() {
		return 1;
	}
	
	// 댓글 수정
	@RequestMapping(value="/{replyNo}", method=RequestMethod.PUT)
	public int modifyReply() {
		return 1;
	}
	
}
