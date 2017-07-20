package com.zhy.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zhy.model.User;

@Controller
public class FileController {
	@RequestMapping(value="/{formName}")
	 public String loginForm(@PathVariable String formName){
		// 动态跳转页面
		return formName;
	}
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(HttpServletRequest request,
						@RequestParam("description") String description,
						@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		System.out.println(description);
		// 如果文件不为空，写入上传路径
		if(!file.isEmpty()){
			// 上传文件路径
			String path=request.getServletContext().getRealPath("/images/");
			System.out.println(path);
			// 上传文件名
			String fileName=file.getOriginalFilename();
			File filePath=new File(path, fileName);
			if(!filePath.getParentFile().exists()){
				filePath.getParentFile().mkdirs();
			}
			// 判断路径是否存在，如果不存在就创建一个
			file.transferTo(new File(path+File.separator+fileName));
			System.out.println(path+File.separator+fileName);
			return "success";
		}else {
			return "error";
		}
	}
	@RequestMapping(value="/register")
	public String register(HttpServletRequest request,
							@ModelAttribute User user,
							Model model) throws IllegalStateException, IOException {
		System.out.println(user.getUsername());
		// 如果文件不为空，写入上传路径
		if(!user.getImage().isEmpty()){
			//上传文件路径
			String path=request.getServletContext().getRealPath("/image/");
			//上传文件名
			String filename=user.getImage().getOriginalFilename();
			File filePath=new File(path, filename);
			if(!filePath.getParentFile().exists()){
				filePath.getParentFile().mkdirs();
			}
			user.getImage().transferTo(new File(path+File.separator+filename));
			model.addAttribute("user", user);
			return "userInfor";
		}else {
			return "error";
		}
	}
	@RequestMapping(value="/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request,
			@RequestParam("filename") String filename,Model model) throws IOException {
		System.out.println("文件名："+filename);
		// 下载文件路径
				String path = request.getServletContext().getRealPath(
		                "/image/");
				File file = new File(path+File.separator+ filename);
		        HttpHeaders headers = new HttpHeaders();  
		        // 下载显示的文件名，解决中文名称乱码问题  
		        String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
		        // 通知浏览器以attachment（下载方式）打开图片
		        headers.setContentDispositionFormData("attachment", downloadFielName); 
		        // application/octet-stream ： 二进制流数据（最常见的文件下载）。
		        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		        // 201 HttpStatus.CREATED
		        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
		                headers, HttpStatus.CREATED);  
	}
}
