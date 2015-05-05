
package com.xizhimojie.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertiesLoader {
	
	/**
	 * 重写了获取配置文件路径方法， 摆脱了以前怕获取不到路径各种重复放置的问题
	 * WEB容器启动通过Listener设置了系统变量 constantPath 启动获取 constantPath 
	 * @param fullFile
	 * @return
	 */
	public static Properties loadPropertyFile(String fileName) {
		if (null == fileName || fileName.equals(""))
			throw new IllegalArgumentException(
					"Properties file path can not be null : " + fileName);
		String webRootPath = System.getProperty("constantPath");
		if(webRootPath == null)
			throw new IllegalArgumentException(
					"System Property constantPath path can not be null : " + webRootPath);
		return getProperties(webRootPath + File.separator + fileName);
			
	}
	
	/**
	 * 
	 * @param fullFile 不能为空
	 * @param path 不能为空
	 * @return
	 */
	public static Properties loadPropertyFile(String fullFile,  String path) {
		
		if (null == fullFile || fullFile.equals(""))
			throw new IllegalArgumentException(
					"Properties fullFile path can not be null : " + fullFile);
		if(StringUtils.isBlank(path))
			throw new IllegalArgumentException(
					"Properties path can not be null : " + fullFile);
		String profilepath = path + File.separator + fullFile;
		return getProperties(profilepath);
	}
	
	private static Properties getProperties(String fileName) {
		InputStream inputStream = null;
		Properties p = null;
		try {
			inputStream = new FileInputStream(new File(fileName));
			p = new Properties();
			p.load(inputStream);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Properties file not found: "
					+ fileName);
		} catch (IOException e) {
			throw new IllegalArgumentException(
					"Properties file can not be loading: " + fileName);
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p;
	}
	
}
