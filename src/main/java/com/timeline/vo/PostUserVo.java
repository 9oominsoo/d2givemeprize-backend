package com.timeline.vo;

public class PostUserVo {
	private int userNo;
	private String userId;
	private String userName;
	private String userRepImg;
	
	//authRelation = 0 -> self
	//authRelation = 1 -> both Following 
	//authRelation = 2 -> auth is following 
	//authRelation = 3 -> following auth 
	private int authRelation;
	
	public PostUserVo() {
		
	}
	
	public PostUserVo(int userNo, String userId, String userName, String userRepImg, int authRelation) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.userRepImg = userRepImg;
		this.authRelation = authRelation;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRepImg() {
		return userRepImg;
	}

	public void setUserRepImg(String userRepImg) {
		this.userRepImg = userRepImg;
	}

	public int getAuthRelation() {
		return authRelation;
	}

	public void setAuthRelation(int authRelation) {
		this.authRelation = authRelation;
	}

	@Override
	public String toString() {
		return "PostUserVo [userNo=" + userNo + ", userId=" + userId + ", userName=" + userName + ", userRepImg="
				+ userRepImg + ", authRelation=" + authRelation + "]";
	}
	
}
