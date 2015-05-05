package com.xizhimojie.common.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener 
public class ContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String path = sce.getServletContext().getRealPath("/");
		System.setProperty("constantPath", path + File.separator +"WEB-INF");
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
