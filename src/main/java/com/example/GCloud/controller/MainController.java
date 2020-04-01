package com.example.GCloud.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.GCloud.command.LoginCommand;
import com.example.GCloud.command.RegisterCommand;
import com.example.GCloud.dao.DocumentDao;
import com.example.GCloud.dao.UserDao;
import com.example.GCloud.model.Document;
import com.example.GCloud.model.User;
import com.example.GCloud.util.Util;



@Controller
public class MainController {
	
	@RequestMapping({"/"})
	public String index(){
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginGet(HttpSession httpSession) {
		ModelAndView mv = new ModelAndView();
		DocumentDao documentDao = new DocumentDao();
		User currentUser = (User)httpSession.getAttribute("user");
		if(currentUser == null) {
			mv.setViewName("error");
			return mv;
		}else
		mv.addObject("documentList", documentDao.getUsersDocuments(currentUser.getUserId()));
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(LoginCommand loginCommand, HttpSession httpSession) {
        try {
        	DocumentDao documentDao = new DocumentDao();
        	User user = new UserDao().getUserByLoginAndPassword(loginCommand.getLoginName(), loginCommand.getPassword());
        	httpSession.setAttribute("user", user);
        	httpSession.setAttribute("username", user.getLoginName());
        	ModelAndView mv = new ModelAndView();
    		mv.setViewName("index");
    		mv.addObject("attributes", user);
    		mv.addObject("documentList", documentDao.getUsersDocuments(user.getUserId()));
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
		if(registerCommand.getLoginName().length()>=1 &&
				Util.isValidEmail(registerCommand.getEmail()) &&
				registerCommand.getPassword().length()>=1) {
			User user = new User();
			user.setEmail(registerCommand.getEmail());
			user.setLoginName(registerCommand.getLoginName());
			user.setPassword(registerCommand.getPassword());
			try {
				new UserDao().addUser(user);
				return "redirect:/?msg=reg_success";
			} catch (Exception e) {
				return "redirect:/register?msg=reg_fail";
			}	
		} else return "redirect:/register?msg=reg_fail_wrong_email";
		
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
		//httpSession.removeAttribute("user");
		httpSession.invalidate();
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
	
	@RequestMapping(value="delete_doc")
	@ResponseBody
	public String deleteDocument(@RequestParam String id) {
		DocumentDao documentDao = new DocumentDao();
		documentDao.delete(Integer.parseInt(id));
		return "Usunieto poprawnie dokument"+id;
	}
	
	@RequestMapping(value="download_doc")
	@ResponseBody
	public String downloadDocument(HttpServletResponse response,
			@RequestParam String id, HttpSession httpSession){
		
		DocumentDao documentDao = new DocumentDao();
		
		try {
//			Document document = documentDao.getDocument(Integer.parseInt(id));
			User currentUser = (User) httpSession.getAttribute("user");
			Document document = documentDao.getDocumentForUser(Integer.parseInt(id), currentUser.getUserId());
			if(document != null) {
				InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(document.getData()));
				String mimeType = URLConnection.guessContentTypeFromStream(inputStream);
				if(mimeType == null) {
					mimeType="application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength(document.getData().length);
				response.setHeader("Content-Disposition", "attachment; filename=\""+document.getFileName()+"\"");
				FileCopyUtils.copy(document.getData(), response.getOutputStream());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Download not ok";
		}
		
		
		return "Download ok";
	}
}
