package com.xizhimojie.common.web;

import java.util.Properties;

import com.xizhimojie.common.utils.PropertiesLoader;

public class Constants {
	public static String website = null;
	public static String sitename = null;
	public static String year = null;
	public static String duoshuo = null;
	public static String ACCESS_KEY = null;
	public static String SECRET_KEY = null;
	public static String QINIU_URL = null;
	static {
		Properties properties = PropertiesLoader.loadPropertyFile("db_server.properties");
		website = properties.getProperty("website");
		sitename = properties.getProperty("sitename");
		year = properties.getProperty("year");
		duoshuo = properties.getProperty("duoshuo");
		ACCESS_KEY = properties.getProperty("ACCESS_KEY");
		SECRET_KEY = properties.getProperty("SECRET_KEY");
		QINIU_URL= properties.getProperty("QINIU_URL");
	}
}
