package com.timeline.vo;

public class PostVo {
	
	//Database Column
	private int postNo;
	private int userNo;
	private String postTitle;
	private String postContent;
	private String postRegDate;
	private int postHit;
	private String postRepImg;
	private int postImgCount;
	
	//Additional Field
	private String userName;
	private int liked;
	private int likedByAuth;
	private int rnum;
	
	
	public PostVo() {
		
	}
	
	public PostVo(int postNo, int userNo, String postTitle, String postContent, String postRegDate, int postHit,
			String postRepImg, int postImgCount) {
		super();
		this.postNo = postNo;
		this.userNo = userNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postRegDate = postRegDate;
		this.postHit = postHit;
		this.postRepImg = postRepImg;
		this.postImgCount = postImgCount;
	}

	public PostVo(int postNo, int userNo, String postTitle, String postContent, String postRegDate, int postHit,
			String postRepImg, int postImgCount, String userName, int liked, int likedByAuth, int rnum) {
		super();
		this.postNo = postNo;
		this.userNo = userNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postRegDate = postRegDate;
		this.postHit = postHit;
		this.postRepImg = postRepImg;
		this.postImgCount = postImgCount;
		this.userName = userName;
		this.liked = liked;
		this.likedByAuth = likedByAuth;
		this.rnum = rnum;
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

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostRegDate() {
		return postRegDate;
	}

	public void setPostRegDate(String postRegDate) {
		this.postRegDate = postRegDate;
	}

	public int getPostHit() {
		return postHit;
	}

	public void setPostHit(int postHit) {
		this.postHit = postHit;
	}

	public String getPostRepImg() {
		return postRepImg;
	}

	public void setPostRepImg(String postRepImg) {
		this.postRepImg = postRepImg;
	}
	
	public int getPostImgCount() {
		return postImgCount;
	}

	public void setPostImgCount(int postImgCount) {
		this.postImgCount = postImgCount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getLiked() {
		return liked;
	}

	public void setLiked(int liked) {
		this.liked = liked;
	}

	public int getLikedByAuth() {
		return likedByAuth;
	}

	public void setLikedByAuth(int likedByAuth) {
		this.likedByAuth = likedByAuth;
	}
	
	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	@Override
	public String toString() {
		return "PostVo [postNo=" + postNo + ", userNo=" + userNo + ", postTitle=" + postTitle + ", postContent="
				+ postContent + ", postRegDate=" + postRegDate + ", postHit=" + postHit + ", postRepImg=" + postRepImg
				+ ", postImgCount=" + postImgCount + ", userName=" + userName + ", liked=" + liked + ", likedByAuth="
				+ likedByAuth + ", rnum=" + rnum + "]";
	}

}
