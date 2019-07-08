package com.timeline.vo;

public class UserRelationVo {
	
	//Database Column
	private int relationNo;
	private int relationType; // 0-FRIEND 1-BLOCKED 
	private int relationFrom;
	private int relationTo;
	private String relationRegDate;
	

	//Additional Field
	
	
	public UserRelationVo() {
		
	}
	
	public UserRelationVo(int relationNo, int relationType, int relationFrom, int relationTo,
			String relationRegDate) {
		super();
		this.relationNo = relationNo;
		this.relationType = relationType;
		this.relationFrom = relationFrom;
		this.relationTo = relationTo;
		this.relationRegDate = relationRegDate;
	}

	public int getRelationNo() {
		return relationNo;
	}

	public void setRelationNo(int relationNo) {
		this.relationNo = relationNo;
	}

	public int getRelationType() {
		return relationType;
	}

	public void setRelationType(int relationType) {
		this.relationType = relationType;
	}

	public int getRelationFrom() {
		return relationFrom;
	}

	public void setRelationFrom(int relationFrom) {
		this.relationFrom = relationFrom;
	}

	public int getRelationTo() {
		return relationTo;
	}

	public void setRelationTo(int relationTo) {
		this.relationTo = relationTo;
	}

	public String getRelationRegDate() {
		return relationRegDate;
	}

	public void setRelationRegdate(String relationRegDate) {
		this.relationRegDate = relationRegDate;
	}

	@Override
	public String toString() {
		return "UserRelationVo [relationNo=" + relationNo + ", relationType=" + relationType + ", relationFrom="
				+ relationFrom + ", relationTo=" + relationTo + ", relationRegDate=" + relationRegDate + "]";
	}

}
