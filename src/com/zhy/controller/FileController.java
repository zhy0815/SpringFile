package com.zhy.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
}
