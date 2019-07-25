package com.timeline.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.timeline.service.TagService;
import com.timeline.vo.AlarmPheedVo;
import com.timeline.vo.UserVo;

@RestController
@CrossOrigin
@RequestMapping(value = "/tag")
public class TagController {

	@Autowired
	private TagService service;
	
	@RequestMapping(value="/searchUsers", method=RequestMethod.POST)
	public List<UserVo> searchUsers(@RequestBody String value, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		List<UserVo> list = new ArrayList<UserVo>();
		String name = URLDecoder.decode(value, "UTF-8");
		name = name.split("=")[0];
		
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
		String name = URLDecoder.decode(value, "UTF-8");
		name = name.split("=")[0];
		
		try {
			list = service.searchFriends(request, response, name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@RequestMapping(value="/checkAlarm", method=RequestMethod.GET)
	public List<AlarmPheedVo> checkAlarm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.checkAlarm(request,response);
	}
	
	@RequestMapping(value="/readAlarm", method=RequestMethod.PUT)
	public Map<String,Object> readAlarm(@RequestBody AlarmPheedVo vo){
		return service.readAlarm(vo);
	}
	
}
