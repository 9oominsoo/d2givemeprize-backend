package com.timeline.vo;

public class PostfileVo {
	
	//Database Column
	private int postFileNo;
	private int postNo;
	private int fileNo;
	
	//Additional Field
	
	
	public PostfileVo() {
		
	}
	
	public PostfileVo(int postFileNo, int postNo, int fileNo) {
		super();
		this.postFileNo = postFileNo;
		this.postNo = postNo;
		this.fileNo = fileNo;
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

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	@Override
	public String toString() {
		return "PostfileVo [postFileNo=" + postFileNo + ", postNo=" + postNo + ", fileNo=" + fileNo + "]";
	}
	
	
}
