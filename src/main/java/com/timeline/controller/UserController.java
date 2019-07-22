package com.timeline.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.timeline.service.UserService;
import com.timeline.vo.UserRelationVo;
import com.timeline.vo.UserVo;

@RestController
@CrossOrigin
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public ModelAndView loginTestPage() {
		ModelAndView mav = new ModelAndView("/WEB-INF/views/users/login.jsp");
		
		return mav;
	}
	
	//로그인 처리
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public UserVo logIn(@RequestBody UserVo vo, HttpServletResponse response) throws Exception {
		System.out.println("log in...");
		System.out.println("User Info: " + vo.toString());
		
		UserVo authUser = service.logIn(vo, response);
		
		return authUser;
	}
	
	/*
	//로그아웃 처리
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public void logOut(HttpSession session) {
		System.out.println("log out...");
		
		session.removeAttribute("authUser");
		session.invalidate();
	}
	*/
	
	//회원가입 처리
	@RequestMapping(method=RequestMethod.POST)
	public int signUp(@RequestBody UserVo vo) {
		System.out.println("sign in...");
		System.out.println("User Info: " + vo.toString());
		
		return service.signUp(vo);
	}
	
	//회원정보수정 처리
	@RequestMapping(method=RequestMethod.PUT)
	public int userModify() {
		return 0;
	}
	
	//회원탈퇴 처리
	@RequestMapping(method=RequestMethod.DELETE)
	public int signOut() {
		return 0;
	}
	
	//아이디 중복검사
	@RequestMapping(value="/checkdupid", method=RequestMethod.POST)
	public int checkDupId(@RequestBody UserVo vo) {
		System.out.println("check duplicate Id...");
		
		return service.checkDupId(vo);
	}
	
	//유저 페이지 로드
	@RequestMapping(value="/{userno}", method=RequestMethod.GET)
	public  Map<String, Object> userPage(@PathVariable("userno") int userNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("load user page");
		
		return service.findUserInfo(request, response, userNo);
	}
	
	//유저 관계 확인
	@RequestMapping(value="/{userno}/checkuserrelation", method=RequestMethod.GET)
	public UserRelationVo checkUserRelation(@PathVariable("userno") int userNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("check user relationship...");
		
		return service.checkUserRelation(request, response, userNo);
	}
	
	//팔로우 
	@RequestMapping(value="/{userno}", method=RequestMethod.POST)
	public int follow(@PathVariable("userno") int userNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("following " + userNo + " users...");
		
		return service.follow(request, response, userNo);
	}
	
	//언팔로우
	@RequestMapping(value="/{userno}", method=RequestMethod.DELETE)
	public int unfollow(@PathVariable("userno") int userNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("unfollowing " + userNo + " users...");
		
		return service.unfollow(request, response, userNo);
	}
	
}
