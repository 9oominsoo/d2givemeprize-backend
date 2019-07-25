package com.timeline.vo;

public class AlarmPheedVo {

	// Database Column
	private int alarmPheedNo;
	private int userFrom; 
	private int userTo;
	private int pheedType; // 0-POST 1-REPLY
	private int postNo;
	private int replyNo;
	private int checked;
	private String alarmRegDate;
	private String userFromName;
	private String userToName;
	
	// Additional Field
	private String pheedMessage;
	private String postAlias;
	
	public AlarmPheedVo() {
		
	}
	
	public AlarmPheedVo(int alarmPheedNo, int userFrom, int userTo, int pheedType, int postNo) {
		super();
		this.alarmPheedNo = alarmPheedNo;
		this.userFrom = userFrom;
		this.userTo = userTo;
		this.pheedType = pheedType;
		this.postNo = postNo;
	}
	
	public AlarmPheedVo(int alarmPheedNo, int userFrom, int userTo, int pheedType, int postNo, int replyNo, int checked,
			String alarmRegDate, String userFromName, String userToName, String pheedMessage, String postAlias) {
		super();
		this.alarmPheedNo = alarmPheedNo;
		this.userFrom = userFrom;
		this.userTo = userTo;
		this.pheedType = pheedType;
		this.postNo = postNo;
		this.replyNo = replyNo;
		this.checked = checked;
		this.alarmRegDate = alarmRegDate;
		this.userFromName = userFromName;
		this.userToName = userToName;
		this.pheedMessage = pheedMessage;
		this.postAlias = postAlias;
	}

	public int getAlarmPheedNo() {
		return alarmPheedNo;
	}

	public void setAlarmPheedNo(int alarmPheedNo) {
		this.alarmPheedNo = alarmPheedNo;
	}

	public int getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(int userFrom) {
		this.userFrom = userFrom;
	}

	public int getUserTo() {
		return userTo;
	}

	public void setUserTo(int userTo) {
		this.userTo = userTo;
	}

	public int getPheedType() {
		return pheedType;
	}

	public void setPheedType(int pheedType) {
		this.pheedType = pheedType;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getAlarmRegDate() {
		return alarmRegDate;
	}

	public void setAlarmRegDate(String alarmRegDate) {
		this.alarmRegDate = alarmRegDate;
	}

	public String getUserFromName() {
		return userFromName;
	}

	public void setUserFromName(String userFromName) {
		this.userFromName = userFromName;
	}

	public String getUserToName() {
		return userToName;
	}

	public void setUserToName(String userToName) {
		this.userToName = userToName;
	}

	public String getPheedMessage() {
		return pheedMessage;
	}

	public void setPheedMessage(String pheedMessage) {
		this.pheedMessage = pheedMessage;
	}

	public String getPostAlias() {
		return postAlias;
	}

	public void setPostAlias(String postAlias) {
		this.postAlias = postAlias;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "AlarmPheedVo [alarmPheedNo=" + alarmPheedNo + ", userFrom=" + userFrom + ", userTo=" + userTo
				+ ", pheedType=" + pheedType + ", postNo=" + postNo + ", replyNo=" + replyNo + ", checked=" + checked
				+ ", alarmRegDate=" + alarmRegDate + ", userFromName=" + userFromName + ", userToName=" + userToName
				+ ", pheedMessage=" + pheedMessage + ", postAlias=" + postAlias + "]";
	}

}
