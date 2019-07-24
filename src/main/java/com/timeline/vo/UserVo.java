package com.timeline.vo;

public class UserVo {
	
	//Database Column
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String userRepImg;
	
	//Additional Field
	private int followers;
	private int followings;
	
	public UserVo() {
		
	}
	
	public UserVo(String userId, String userPwd, String userName) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
	}

	public UserVo(int userNo, String userId, String userPwd, String userName, String userRepImg) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userRepImg = userRepImg;
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

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
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

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public int getFollowings() {
		return followings;
	}

	public void setFollowings(int followings) {
		this.followings = followings;
	}

	@Override
	public String toString() {
		return "UserVo [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", userRepImg=" + userRepImg + ", followers=" + followers + ", followings=" + followings + "]";
	}

	

}
