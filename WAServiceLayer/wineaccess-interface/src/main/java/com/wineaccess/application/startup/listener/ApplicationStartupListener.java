package com.wineaccess.application.startup.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.wineaccess.service.startup.StartupService;

public class ApplicationStartupListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		try {
			new StartupService().start();
		} catch (Exception e) {
			throw new RuntimeException("Error in starting server...");
		}
	}
}
