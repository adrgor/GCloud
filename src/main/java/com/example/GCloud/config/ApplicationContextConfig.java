package com.example.GCloud.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.example.GCloud.dao.DocumentDao;

public class ApplicationContextConfig {
//	@Autowired
//	@Bean(name = "fileUploadDao")
//	public DocumentDao getUserDao(SessionFactory sessionFactory) {
//	    return new DocumentDao(sessionFactory);
//	}
//	
//	@Bean(name = "multipartResolver")
//	public CommonsMultipartResolver getCommonsMultipartResolver() {
//	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//	    multipartResolver.setMaxUploadSize(20971520);   // 20MB
//	    multipartResolver.setMaxInMemorySize(1048576);  // 1MB
//	    return multipartResolver;
//	}
//	
//	
}
