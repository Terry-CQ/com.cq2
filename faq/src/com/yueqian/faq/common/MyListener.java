package com.yueqian.faq.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//@WebListener
public class MyListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		DBUtils.closeDataSource();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("应用程序初始化完毕...");
	}

}
