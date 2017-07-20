package com.zhy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhy.model.Admin;
import com.zhy.model.Book;

@Controller
public class InterceptorController {
	@RequestMapping(value="/login")
	public ModelAndView login(String loginname,String password,
							ModelAndView mv,HttpSession session) {
		if(loginname!=null&&loginname.equals("zhy")&&password!=null&&password.equals("zhy")){
			Admin admin=new Admin();
			admin.setLoginname(loginname);
			admin.setPassword(password);
			admin.setUsername("管理员");
			session.setAttribute("admin", admin);
			mv.setViewName("redirect:main");
		}else {
			mv.addObject("message", "登录失败！");
			mv.setViewName("loginForm");
		}
		return mv;
	}
	@RequestMapping(value="/main")
	public String main(Model model) {
		List<Book> bookList=new ArrayList<>();
		bookList.add(new Book("疯狂Java讲义（附光盘）","李刚 编著",74.2,"java.jpg"));
		bookList.add(new Book("轻量级Java EE企业应用实战","李刚 编著",59.2,"ee.jpg"));
		bookList.add(new Book("疯狂Android讲义（附光盘）","李刚 编著",60.6,"android.jpg"));
		bookList.add(new Book("疯狂Ajax讲义（附光盘）","李刚 编著",66.6,"ajax.jpg"));
		model.addAttribute("bookList", bookList);
		return "main";
	}
}
