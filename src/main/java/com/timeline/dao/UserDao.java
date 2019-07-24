package com.timeline.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.timeline.vo.PostVo;
import com.timeline.vo.UserRelationVo;
import com.timeline.vo.UserVo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Repository
public class UserDao {
	
	@Autowired 
	private SqlSession sqlSession;
	
	static final long EXPIRATIONTIME = 7200000;
	static final String SECRET = "fabwisolfabwisolfabwisolfabwisolfabwisolfabwisol";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	public int insertUserBatch(List<UserVo> list) {
		if(list == null || list.size() == 0) {
			System.out.println("no list");
		}
		
		return sqlSession.insert("user.insertUserBatch", list);
	}
	
	public UserVo logIn(UserVo vo, HttpServletResponse response) throws Exception {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		UserVo user = sqlSession.selectOne("user.selectLoginUser", vo);

		String jwt = Jwts.builder().setSubject("jwtauthtoken")
				.claim("userNo", user.getUserNo())
				.claim("userId", user.getUserId())
				.claim("userName", user.getUserName())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS256, SECRET.getBytes("UTF-8")).compact();
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);
		return user;
	}
	
	public int checkAuthUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String jwtstr = request.getHeader("Authorization");
			jwtstr = jwtstr.replace("Bearer ", "");
			
			Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET.getBytes("UTF-8")).parseClaimsJws(jwtstr);
			String tokenSubject = (String) claims.getBody().getSubject();
			String userId = (String) claims.getBody().get("userId");
			String userName = (String) claims.getBody().get("userName");
			int userNo = (int) claims.getBody().get("userNo");

			String jwt = Jwts.builder().setSubject(tokenSubject)
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
					.claim("userNo", userNo)
					.claim("userId", userId)
					.claim("userName", userName)
					.signWith(SignatureAlgorithm.HS256, SECRET.getBytes("UTF-8")).compact();
			response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);
			
			return userNo;

		} catch (Exception e) {

			response.sendError(401, "InvalidToken");
			throw new Exception(e);
		}

	}
	
	public int signUp(UserVo vo) {
		return sqlSession.insert("user.insertUser", vo);
	}
	
	public UserVo checkDupId(UserVo vo) {
		return sqlSession.selectOne("user.selectUserId", vo);
	}
	
	public int modifyInfo(UserVo vo) {
		return sqlSession.update("user.updateUser", vo);
	}
	
	public int signOut(UserVo vo) {
		return sqlSession.delete("user.deleteUser", vo);
	}
	
	public UserVo findUser(int userNo) {
		return sqlSession.selectOne("user.selectUser", userNo);
	}
	
	public List<PostVo> findUserPost(int userNo){
		return sqlSession.selectList("user.selectUserPost", userNo);
	}
	
	public UserRelationVo checkUserRelation(UserRelationVo vo) {
		return sqlSession.selectOne("user.selectUserRelation", vo);
	}
	
	public int follow(UserRelationVo vo) {
		return sqlSession.insert("user.insertUserRelation", vo);
	}
	
	public int unfollow(UserRelationVo vo) {
		return sqlSession.delete("user.deleteUserRelation", vo);
	}
	
	public List<UserVo> loadUser(){
		return sqlSession.selectList("user.selectAllUser");
	}
	
	public List<UserVo> loadFollowers(int userNo){
		return sqlSession.selectList("user.selectFollowers", userNo);
	}
	
	public List<UserVo> loadFollowings(int userNo){
		return sqlSession.selectList("user.selectFollowings", userNo);
	}
}
