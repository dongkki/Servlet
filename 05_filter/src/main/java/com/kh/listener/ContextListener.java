package com.kh.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String path = sce.getServletContext().getContextPath();
		System.out.println("ContextListener : " + path + "�� ����Ǿ����ϴ�.");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		String path = sce.getServletContext().getContextPath();
		System.out.println("ContextListener : " + path + "�� �Ҹ�Ǿ����ϴ�.");
	}

}
