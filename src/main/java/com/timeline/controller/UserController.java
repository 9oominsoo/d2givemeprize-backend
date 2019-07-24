package com.timeline.controller;

import java.util.ArrayList;
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
	
	@RequestMapping(value="/signupform", method=RequestMethod.GET)
	public ModelAndView signupTestPage() {
		ModelAndView mav = new ModelAndView("/WEB-INF/views/users/signin.jsp");
		
		return mav;
	}
	
	//batch 태스트 
	@RequestMapping(value="/batch")
	public int insertUserBatch() {
		
		List<UserVo> list = new ArrayList<UserVo>();
		
		list.add(new UserVo("kdw@test.com", "1234", "강동원"));
		list.add(new UserVo("jdg@test.com", "1234", "장동건"));
		list.add(new UserVo("wb@test.com", "1234", "원빈"));
		list.add(new UserVo("hb@test.com", "1234", "현빈"));
		list.add(new UserVo("gs@test.com", "1234", "고수"));
		list.add(new UserVo("jis@test.com", "1234", "조인성"));
		list.add(new UserVo("jws@test.com", "1234", "정우성"));
		list.add(new UserVo("sjs@test.com", "1234", "소지섭"));
		list.add(new UserVo("kwb@test.com", "1234", "김우빈"));
		list.add(new UserVo("skj@test.com", "1234", "서강준"));
		list.add(new UserVo("ajh@test.com", "1234", "안재현"));
		list.add(new UserVo("phj@test.com", "1234", "박해진"));

		int flag = service.insertUserBatch(list);
		
		return flag;
	}
	
	//로그인 처리
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public UserVo logIn(@RequestBody UserVo vo, HttpServletResponse response) throws Exception {
		System.out.println("log in...");
		System.out.println("User Info: " + vo.toString());
		
		UserVo authUser = service.logIn(vo, response);
		
		return authUser;
	}
	
	//로그인 된 유저 정보 로드
	@RequestMapping(value="/authuserinfo", method=RequestMethod.GET)
	public UserVo authUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		System.out.println("sign in...");
		System.out.println("User Info: " + vo.toString());
		
		return service.signUp(vo);
	}
	
	//아이디 중복검사
	@RequestMapping(value="/checkdupid", method=RequestMethod.POST)
	public int checkDupId(@RequestBody UserVo vo) {
		System.out.println("check duplicate Id...");
		
		return service.checkDupId(vo);
	}
	
	//회원정보수정 처리
	@RequestMapping(method=RequestMethod.PUT)
	public int modifyInfo(@RequestBody UserVo vo) {
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
	@RequestMapping(value="/{userno}/follow", method=RequestMethod.GET)
	public int follow(@PathVariable("userno") int userNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("following " + userNo + " users...");
		
		return service.follow(request, response, userNo);
	}
	
	//언팔로우
	@RequestMapping(value="/{userno}/unfollow", method=RequestMethod.GET)
	public int unfollow(@PathVariable("userno") int userNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("unfollowing " + userNo + " users...");
		
		return service.unfollow(request, response, userNo);
	}
	
	//팔로워 출력 
	@RequestMapping(value="/{userno}/followers", method=RequestMethod.GET)
	public Map<String, Object> followers(@PathVariable("userno") int userNo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		return service.loadFollowers(request, response, userNo);
	}
	
	//팔로잉 출력
	@RequestMapping(value="/{userno}/followings", method=RequestMethod.GET)
	public Map<String, Object> followings(@PathVariable("userno") int userNo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		return service.loadFollowings(request, response, userNo);
	}
	
	//유저 추천 
	//전체 유저 출력 
	@RequestMapping(value="/allUser", method=RequestMethod.GET)
	public List<UserVo> loadUser(){
		return service.loadUser();
	}
}
