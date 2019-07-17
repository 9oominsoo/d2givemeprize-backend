package com.timeline.vo;

public class ReplytagVo {

	// Database Column
	private int replyTagNo;
	private int userNo;
	private int replyNo;

	// Additional Field

	
	public ReplytagVo() {

	}
	
	public ReplytagVo(int replyTagNo, int userNo, int replyNo) {
		super();
		this.replyTagNo = replyTagNo;
		this.userNo = userNo;
		this.replyNo = replyNo;
	}

	public int getReplyTagNo() {
		return replyTagNo;
	}

	public void setReplyTagNo(int replyTagNo) {
		this.replyTagNo = replyTagNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	@Override
	public String toString() {
		return "ReplytagVo [replyTagNo=" + replyTagNo + ", userNo=" + userNo + ", replyNo=" + replyNo + "]";
	}

}
