package com.prowo.system;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class SystemInitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.clearProperty("pointsPath");

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Configuration config = null;
        try {
			config = new PropertiesConfiguration("properties/init.properties");
		} catch (ConfigurationException e) {

		}
		//设置配置文件根路径
		System.setProperty("pointsPath", config.getString("pointsPath"));
	}

}
