package com.example.GCloud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.GCloud.command.LoginCommand;
import com.example.GCloud.command.RegisterCommand;
import com.example.GCloud.dao.DocumentDao;
import com.example.GCloud.dao.UserDao;
import com.example.GCloud.model.Document;
import com.example.GCloud.model.User;



@Controller
public class MainController {
	
	@RequestMapping({"/"})
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginGet(HttpSession httpSession) {
		if(httpSession.getAttribute("user") == null) {
			return "error";
		}else
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(LoginCommand loginCommand, HttpSession httpSession) {
        try {
        	User user = new UserDao().getUserByLoginAndPassword(loginCommand.getLoginName(), loginCommand.getPassword());
        	httpSession.setAttribute("user", user);
        	ModelAndView mv = new ModelAndView();
    		mv.setViewName("login");
    		mv.addObject("attributes", user);
    		return mv;
        } catch (Exception e) {
        	e.printStackTrace();
        	ModelAndView mv = new ModelAndView();
        	mv.setViewName("redirect:/?msg=bad_login");
        	return mv;
        }
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register() {
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register_post(RegisterCommand registerCommand) {
		User user = new User();
		user.setEmail(registerCommand.getEmail());
		user.setLoginName(registerCommand.getLoginName());
		user.setPassword(registerCommand.getPassword());
		new UserDao().addUser(user);
		return "redirect:/?msg=reg_success";
	}
	
	@RequestMapping(value="/doUpload", method=RequestMethod.POST)
	public String upload(@RequestParam CommonsMultipartFile[] fileUpload, HttpSession httpSession) {
		if(fileUpload != null && fileUpload.length > 0) {
			DocumentDao documentDao = new DocumentDao();
			User currentUser = (User)httpSession.getAttribute("user");
			for(CommonsMultipartFile aFile : fileUpload) {
				System.out.println("Saving file: "+aFile.getOriginalFilename());
				Document document = new Document();
				document.setFileName(aFile.getOriginalFilename());
				document.setData(aFile.getBytes());
				document.setUserId(currentUser.getUserId());
				documentDao.save(document);
			}
		}
		return "redirect:login?msg=succ";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public String logout(HttpSession httpSession) {
		httpSession.removeAttribute("user");
		return "redirect:/";
	}
	
	@RequestMapping(value="/myFiles", method=RequestMethod.GET)
	public ModelAndView myFiles(HttpSession httpSession) {
		User currentUser = (User)httpSession.getAttribute("user");
		System.out.println(currentUser);
		ModelAndView mv = new ModelAndView();
		if(currentUser == null) {
			mv.setViewName("error");
			return mv;
		}
		DocumentDao documentDao = new DocumentDao();
		mv.setViewName("myFiles");
		mv.addObject("documentList", documentDao.getUsersDocuments(currentUser.getUserId()));

		return mv;
	}
}
