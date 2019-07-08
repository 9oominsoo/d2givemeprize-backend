package com.timeline.vo;

public class UserfileVo {
	
	//Database Column
	private int userFileNo;
	private int userNo;
	private int fileNo;
	
	//Additional Field
	
	
	public UserfileVo() {
		
	}

	public UserfileVo(int userFileNo, int userNo, int fileNo) {
		super();
		this.userFileNo = userFileNo;
		this.userNo = userNo;
		this.fileNo = fileNo;
	}

	public int getUserFileNo() {
		return userFileNo;
	}

	public void setUserFileNo(int userFileNo) {
		this.userFileNo = userFileNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	@Override
	public String toString() {
		return "UserfileVo [userFileNo=" + userFileNo + ", userNo=" + userNo + ", fileNo=" + fileNo + "]";
	}
	
}
