package com.timeline.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.timeline.service.TagService;
import com.timeline.vo.UserVo;

@RestController
@CrossOrigin
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
	
	@RequestMapping(value="/searchUsers", method=RequestMethod.POST)
	public List<UserVo> searchUsers(@RequestBody String value, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		List<UserVo> list = new ArrayList<UserVo>();
		System.out.println("value: " + value);
		String name = URLDecoder.decode(value, "UTF-8");
		name = name.split("=")[0];
		System.out.println("name: "+name);
		
		try {
			list = service.searchUsers(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//json.stringify 가 없으므로 @RequestBody로 받을 필요 없다.
	@RequestMapping(value="/searchFriends", method=RequestMethod.POST)
	public List<UserVo> searchFriends(@RequestBody String value, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		List<UserVo> list = new ArrayList<UserVo>();
		System.out.println("value: " + value);
		String name = URLDecoder.decode(value, "UTF-8");
		name = name.split("=")[0];
		System.out.println("name: "+name);
		
		try {
			list = service.searchFriends(request, response, name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//게시글 공유  
	//@RequestMapping(value="/sharePost", method=RequestMethod.POST)
	/*
	@RequestMapping(method=RequestMethod.POST)
	public int sharePost(@RequestBody List<Object> multiParam) {
		System.out.println("share post...");
		System.out.println(multiParam);
		
		return service.sharePost(multiParam);
	}
	*/
}
