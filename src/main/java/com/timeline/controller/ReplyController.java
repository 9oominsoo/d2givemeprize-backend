package com.timeline.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.timeline.service.ReplyService;
import com.timeline.vo.AlarmPheedVo;
import com.timeline.vo.ReplyVo;

@RestController
@CrossOrigin
@RequestMapping(value="/reply")
public class ReplyController {
	
	@Autowired
	private ReplyService service;

	// 댓글 작성&공유
	@RequestMapping(method=RequestMethod.POST)
	public List<AlarmPheedVo> writeReply(@RequestBody List<Object> multiParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.writeReply(request, response, multiParam);
	}
	
	// 대댓 작성
	@RequestMapping(value="/{replyno}", method=RequestMethod.POST)
	public List<AlarmPheedVo> reReply(@PathVariable("replyno") int replyNo, @RequestBody List<Object> multiParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.reReply(request, response, multiParam, replyNo);
	}
	
	// 대댓글 불러오기
	@RequestMapping(value="/{replyno}", method=RequestMethod.GET)
	public List<ReplyVo> loadReReply(@PathVariable("replyno") int replyNo){
		return service.loadReReply(replyNo);
	}
	
	// 댓글 좋아요 
	/*
	@RequestMapping(value="/{replyno}", method=RequestMethod.POST)
	public int likeTogglePheed(@PathVariable("replyno") int replyNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.likeToggleReply(request, response, replyNo);
	}
	*/
	
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
