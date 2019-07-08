package com.timeline.vo;

public class DatafileVo {
	
	//Database Column
	private int fileNo;
	private String RegDate;
	private String filePath;
	private String fileName;
	private String saveName;
	private String fileSize;
	

	//Additional Field
		
	
	public DatafileVo() {
		
	}
	
	public DatafileVo(int fileNo, String regDate, String filePath, String fileName, String saveName, String fileSize) {
		super();
		this.fileNo = fileNo;
		RegDate = regDate;
		this.filePath = filePath;
		this.fileName = fileName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public String getRegDate() {
		return RegDate;
	}

	public void setRegDate(String regDate) {
		RegDate = regDate;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "DatafileVo [fileNo=" + fileNo + ", RegDate=" + RegDate + ", filePath=" + filePath + ", fileName="
				+ fileName + ", saveName=" + saveName + ", fileSize=" + fileSize + "]";
	}
	
}
