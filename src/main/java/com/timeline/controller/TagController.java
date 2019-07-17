package com.timeline.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timeline.service.TagService;
import com.timeline.vo.UserVo;

@Controller
@RequestMapping(value = "/tag")
public class TagController {

	@Autowired
	private TagService service;

	// 태그 페이지 로드
	@RequestMapping(value = "/tagform")
	public String loginForm() {
		System.out.println("load tag page");

		return "/WEB-INF/views/tag/tag.jsp";
	}
	
	//json.stringify 가 없으므로 @RequestBody로 받을 필요 없다.
	@ResponseBody
	@RequestMapping(value="/searchFriends", method=RequestMethod.POST)
	public List<UserVo> searchFriends(String value, HttpSession session) throws UnsupportedEncodingException {
		System.out.println("search user...");
		String name = URLDecoder.decode(value, "UTF-8");
		
		UserVo me = (UserVo)(session.getAttribute("authUser"));
			
		List<UserVo> list = service.searchFriends(name, me);
		
		return list;
	}
	
	//게시글 공유 
	@ResponseBody
	@RequestMapping(value="/sharePost", method=RequestMethod.POST)
	public int sharePost(@RequestBody List<Object> multiParam) {
		System.out.println("share post...");
		System.out.println(multiParam);
		
		return service.sharePost(multiParam);
	}
	
}
