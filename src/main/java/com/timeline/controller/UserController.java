package com.timeline.controller;

import java.util.List;
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

import com.timeline.service.UserService;
import com.timeline.vo.PostUserVo;
import com.timeline.vo.UserRelationVo;
import com.timeline.vo.UserVo;

@RestController
@CrossOrigin
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	//로그인 처리
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public UserVo logIn(@RequestBody UserVo vo, HttpServletResponse response) throws Exception {
		UserVo authUser = service.logIn(vo, response);
		
		return authUser;
	}
	
	//로그인 된 유저 정보 로드
	@RequestMapping(value="/authuserinfo", method=RequestMethod.GET)
	public PostUserVo authUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.findAuthUserInfo(request, response);
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
	public Map<String, Object> signUp(@RequestBody UserVo vo) {
		return service.signUp(vo);
	}
	
	//아이디 중복검사
	@RequestMapping(value="/checkdupid", method=RequestMethod.POST)
	public Map<String, Object> checkDupId(@RequestBody UserVo vo) {
		return service.checkDupId(vo);
	}
	
	//회원정보수정 처리
	@RequestMapping(method=RequestMethod.PUT)
	public Map<String, Object> modifyInfo(@RequestBody UserVo vo) {
		return service.modifyInfo(vo);
	}
	
	//회원탈퇴 처리
	@RequestMapping(method=RequestMethod.DELETE)
	public int signOut(@PathVariable("userno") int userNo, @RequestBody UserVo vo) {
		vo.setUserNo(userNo);
		return service.signOut(vo);
	}
	
	//유저 페이지 로드
	@RequestMapping(value="/{userno}", method=RequestMethod.GET)
	public  Map<String, Object> userPage(@PathVariable("userno") int userNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.findUserInfo(request, response, userNo);
	}
	
	//유저 관계 확인
	@RequestMapping(value="/{userno}/checkuserrelation", method=RequestMethod.GET)
	public UserRelationVo checkUserRelation(@PathVariable("userno") int userNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.checkUserRelation(request, response, userNo);
	}
	
	//팔로우 
	@RequestMapping(value="/{userno}/follow", method=RequestMethod.GET)
	public Map<String, Object> follow(@PathVariable("userno") int userNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("following " + userNo + " users...");
		
		return service.follow(request, response, userNo);
	}
	
	//언팔로우
	@RequestMapping(value="/{userno}/unfollow", method=RequestMethod.GET)
	public Map<String, Object> unfollow(@PathVariable("userno") int userNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("unfollowing " + userNo + " users...");
		
		return service.unfollow(request, response, userNo);
	}
	
	//전체 유저 출력 
	@RequestMapping(value="/alluser", method=RequestMethod.GET)
	public List<UserVo> loadUser(){
		return service.loadUser();
	}
	
	//팔로워 출력 
	@RequestMapping(value="/{userno}/followers", method=RequestMethod.GET)
	public List<PostUserVo> followers(@PathVariable("userno") int userNo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		return service.loadFollowers(request, response, userNo);
	}
	
	//팔로잉 출력
	@RequestMapping(value="/{userno}/followings", method=RequestMethod.GET)
	public List<PostUserVo> followings(@PathVariable("userno") int userNo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		return service.loadFollowings(request, response, userNo);
	}
	
	//유저 추천 
	@RequestMapping(value="/recommend", method=RequestMethod.GET)
	public List<PostUserVo> userRecommend(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return service.userRecommend(request, response);
	}
}
