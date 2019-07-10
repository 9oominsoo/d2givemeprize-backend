package com.timeline.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.timeline.util.S3Util;
import com.timeline.vo.DatafileVo;

@Controller
@RequestMapping(value="/file")
public class FileController {

	@Autowired
	private S3Util s3Util;

	private String bucketName = "d2-resources";
	
	//테스트용 function
	@RequestMapping(value="/testuploaduser")
	public String testuploaduserform() {
		return "/WEB-INF/views/users/userimgupload.jsp";
	}
	
	//테스트용 function
	@RequestMapping(value="/testuploadpost")
	public String testuploadpostform() {
		return "/WEB-INF/views/users/postimgupload.jsp";
	}
	
	// 버킷 생성
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public void userInit() {
		// 버킷생성
		System.out.println("create bucket...");
		s3Util.createBucket(bucketName);

		// 버킷리스트
		System.out.println(s3Util.getBucketlist());
	}
	
	@RequestMapping(value="/userfolder", method = RequestMethod.GET)
	public void createUserFolder() {
		// 폴더생성
		System.out.println("create folder...");
		s3Util.createFolder(bucketName, "userimg_folder");
	}
	
	@RequestMapping(value="/postfolder", method = RequestMethod.GET)
	public void createPostFolder() {
		// 폴더생성
		System.out.println("create folder...");
		s3Util.createFolder(bucketName, "postimg_folder");
	}
	

	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public DatafileVo fileUpload(@RequestParam("file") MultipartFile file, Model model, @RequestParam("folderName") String folderName) {
		System.out.println("aws 파일업로드");
		System.out.println(file.getOriginalFilename());
		String fileName = file.getOriginalFilename();
		//확장자
				String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				System.out.println("exName: " + exName);
				
		//파일사이즈
				long fileSize = file.getSize();
				System.out.println("fileSize: " + fileSize);

		//저장파일명
				String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
				System.out.println("saveName: " + saveName);
				
		//파일패스
				//String filePath = s3Util.getFileURL(bucketName, saveName);
				
				//s3Util.getFileURL(bucketName, saveName);
				String filePath = bucketName + "/" + folderName;
				System.out.println("filePath: " + filePath);
				
				//s3Util.fileUpload(bucketName, file, exName, saveName);
				
				s3Util.fileUpload(filePath, file, exName, saveName);
				
				
				
				String url = s3Util.getFileURL(filePath, saveName);
				model.addAttribute("url", url);
				
		DatafileVo vo = new DatafileVo();
		vo.setFileName(fileName);
		vo.setFilePath(filePath);
		vo.setFileSize(fileSize);
		vo.setSaveName(saveName);
		System.out.println(vo.toString());
		return vo;
	}
	
}
