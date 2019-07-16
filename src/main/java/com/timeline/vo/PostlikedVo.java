package com.timeline.vo;

public class PostlikedVo {
	
	//Database Column
	private int postLikedNo;
	private int postNo;
	private int userNo;
	
	//Additional Field
	
	public PostlikedVo() {
		
	}
	
	public PostlikedVo(int postLikedNo, int postNo, int userNo) {
		super();
		this.postLikedNo = postLikedNo;
		this.postNo = postNo;
		this.userNo = userNo;
	}

	public int getPostLikedNo() {
		return postLikedNo;
	}

	public void setPostLikedNo(int postLikedNo) {
		this.postLikedNo = postLikedNo;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return "PostlikedVo [postLikedNo=" + postLikedNo + ", postNo=" + postNo + ", userNo=" + userNo + "]";
	}
	
	
}
