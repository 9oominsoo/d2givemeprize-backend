package com.timeline.vo;

public class PosttagVo {
	
	private int postTagNo;
	private int postNo;
	private int userNo;
	
	public PosttagVo() {
	}
	
	public PosttagVo(int postTagNo, int postNo, int userNo) {
		super();
		this.postTagNo = postTagNo;
		this.postNo = postNo;
		this.userNo = userNo;
	}

	public int getPostTagNo() {
		return postTagNo;
	}

	public void setPostTagNo(int postTagNo) {
		this.postTagNo = postTagNo;
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
		return "PosttagVo [postTagNo=" + postTagNo + ", postNo=" + postNo + ", userNo=" + userNo + "]";
	}
		
}
