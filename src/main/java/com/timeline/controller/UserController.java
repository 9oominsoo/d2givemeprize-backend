package com.timeline.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.timeline.service.UserService;
import com.timeline.vo.UserRelationVo;
import com.timeline.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	//로그인 페이지 로드
	@RequestMapping(value="/loginform")
	public String loginForm() {
		System.out.println("load log-in page");
		
		return "/WEB-INF/views/users/login.jsp";
	}
	
	//로그인 성공 페이지 로드
	@RequestMapping(value="/loginsuccess")
	public String loginSuccessForm() {
		System.out.println("load log-in success page");
		
		return "/WEB-INF/views/users/loginsuccess.jsp";
	}
	
	//로그인 처리
	@ResponseBody 
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public UserVo logIn(@RequestBody UserVo vo, HttpSession session) {
		System.out.println("log in...");
		System.out.println("User Info: " + vo.toString());
		
		UserVo authUser = service.logIn(vo);
		if(authUser != null)
			session.setAttribute("authUser", authUser);
		
		return authUser;
	}
	
	//로그아웃 처리
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public void logOut(HttpSession session) {
		System.out.println("log out...");
		
		session.removeAttribute("authUser");
		session.invalidate();
	}
	
	//회원가입 페이지 로드 
	@RequestMapping(value="/signupform")
	public String signupForm() {
		System.out.println("load sign-in page");
		
		return "/WEB-INF/views/users/signin.jsp";
	}
	
	//회원가입 처리
	@ResponseBody 
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public int signUp(@RequestBody UserVo vo) {
		System.out.println("sign in...");
		System.out.println("User Info: " + vo.toString());
		
		return service.signUp(vo);
	}
	
	//회원정보수정 처리
	@RequestMapping(value="/modifyUser", method=RequestMethod.POST)
	public int userModify() {
		return 0;
	}
	
	//회원탈퇴 처리
	@RequestMapping(value="/signOut", method=RequestMethod.POST)
	public int signOut() {
		return 0;
	}
	
	//아이디 중복검사
	@ResponseBody
	@RequestMapping(value="/checkdupid", method=RequestMethod.POST)
	public int checkDupId(@RequestBody UserVo vo) {
		System.out.println("check duplicate Id...");
		
		return service.checkDupId(vo);
	}
	
	//유저 페이지 로드
	@ResponseBody
	@RequestMapping(value="/{userno}")
	public  Map<String, Object> userPage(@PathVariable("userno") int userNo, HttpSession session) {
		System.out.println("load user page");
		
		return service.findUserInfo((UserVo)(session.getAttribute("authUser")), userNo);
	}
	
	//유저 관계 확인
	@ResponseBody
	@RequestMapping(value="/{userno}/checkuserrelation", method=RequestMethod.GET)
	public UserRelationVo checkUserRelation(HttpSession session, @PathVariable("userno") int userNo) {
		System.out.println("check user relationship...");
		
		return service.checkUserRelation((UserVo)(session.getAttribute("authUser")), userNo);
	}
	
	//팔로우 
	@ResponseBody
	@RequestMapping(value="/{userno}/follow", method=RequestMethod.GET)
	public int follow(HttpSession session, @PathVariable("userno") int userNo) {
		System.out.println("following " + userNo + " users...");
		
		return service.follow((UserVo)(session.getAttribute("authUser")), userNo);
	}
	
	//언팔로우
	@ResponseBody
	@RequestMapping(value="/{userno}/unfollow", method=RequestMethod.GET)
	public int unfollow(HttpSession session, @PathVariable("userno") int userNo) {
		System.out.println("unfollowing " + userNo + " users...");
		
		return service.unfollow((UserVo)(session.getAttribute("authUser")), userNo);
	}
	
}
