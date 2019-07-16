package com.timeline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.timeline.service.TagService;

@Controller
@RequestMapping(value = "/tag")
public class TagController {

	@Autowired
	private TagService service;

	// 로그인 페이지 로드
	@RequestMapping(value = "/tagform")
	public String loginForm() {
		System.out.println("load tag page");

		return "/WEB-INF/views/tag/tag.jsp";
	}
}
