package com.xizhimojie.common.web;

import java.util.Properties;

import com.xizhimojie.common.utils.PropertiesLoader;

public class Constants {
	public static String website = null;
	public static String sitename = null;
	public static String year = null;
	public static String duoshuo = null;
	static {
		Properties properties = PropertiesLoader.loadPropertyFile("db_server.properties");
		website = properties.getProperty("website");
		sitename = properties.getProperty("sitename");
		year = properties.getProperty("year");
		duoshuo = properties.getProperty("duoshuo");
	}
}
