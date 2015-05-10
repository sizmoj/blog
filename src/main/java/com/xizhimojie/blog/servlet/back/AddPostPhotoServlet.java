package com.xizhimojie.blog.servlet.back;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.xizhimojie.common.web.Constants;
import com.xizhimojie.common.web.QiniuUploadUtils;

@WebServlet(urlPatterns={"/back/AddPostPhotoServlet"})
public class AddPostPhotoServlet extends HttpServlet{
	private static final long serialVersionUID = -3639995055865580141L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory); 
		sfu.setHeaderEncoding("UTF-8"); 
		PrintWriter writer = response.getWriter();
		try{
			// 处理表单请求
			List<FileItem> itemList = sfu.parseRequest(request);
			for (FileItem fileItem : itemList) {
				// 对应表单中的控件的name
				byte[] file = fileItem.get();
				String filename = fileItem.getName();
				QiniuUploadUtils.upLoadFile(file, filename);
				writer.print("{");
				writer.print("msg:" + "\"" + Constants.QINIU_URL + "/" + filename +"\"");
				writer.print("}");
				return;
			}
			writer.print("{");
			writer.print("msg:" + "文件不能为空");
			writer.print("}");
		}catch(FileSizeLimitExceededException e){
			e.printStackTrace();
		}catch(FileUploadException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			writer.close();
		}
		return;
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doPost(request, response);
	}

}
