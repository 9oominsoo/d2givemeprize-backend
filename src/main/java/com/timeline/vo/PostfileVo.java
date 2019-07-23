package com.timeline.vo;

public class PostfileVo {
	
	//Database Column
	private int postFileNo;
	private int postNo;
	private String postFilePath;
	
	//Additional Field
	
	
	public PostfileVo() {
		
	}
	
	public PostfileVo(int postFileNo, int postNo, String postFilePath) {
		super();
		this.postFileNo = postFileNo;
		this.postNo = postNo;
		this.postFilePath = postFilePath;
	}

	public int getPostFileNo() {
		return postFileNo;
	}

	public void setPostFileNo(int postFileNo) {
		this.postFileNo = postFileNo;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public String getPostFilePath() {
		return postFilePath;
	}

	public void setPostFilePath(String postFilePath) {
		this.postFilePath = postFilePath;
	}

	@Override
	public String toString() {
		return "PostfileVo [postFileNo=" + postFileNo + ", postNo=" + postNo + ", postFilePath=" + postFilePath + "]";
	}
	
	
}
