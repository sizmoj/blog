package com.xizhimojie.common.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class QiniuUploadUtils {
	
	private static Logger log = LoggerFactory.getLogger(QiniuUploadUtils.class);	
	private static UploadManager uploadManager = new UploadManager();
	private static Auth auth = Auth.create(Constants.ACCESS_KEY, Constants.SECRET_KEY);
	
	public  static void upLoadFile(byte[] file, String key) {
		try {
			
	        Response res = uploadManager.put(file, key, auth.uploadToken("xizhimojie"));
	        // log.info(res);
	        // log.info(res.bodyString());
	        // Ret ret = res.jsonToObject(Ret.class);
	        if(res.isOK()){
	        	log.info(key+"上传成功！");
	        }else {
	        	log.error(key+"上传失败！");
	        }
	    } catch (QiniuException e) {
	        Response r = e.response;
	        // 请求失败时简单状态信息
	        log.error(r.toString());
	        try {
	            // 响应的文本信息
	            log.error(r.bodyString());
	        } catch (QiniuException e1) {
	            //ignore
	        }
	    }
	}	
}
