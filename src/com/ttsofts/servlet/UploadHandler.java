package com.ttsofts.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSONObject;

public class UploadHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();  
		JSONObject jsonObject = new JSONObject();  
		@SuppressWarnings("deprecation")
		String disFilePath = request.getRealPath("uploadimage")+"/";
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			if (!ServletFileUpload.isMultipartContent(request)) {
				return;
			}
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					continue;
				} else {
					String filename = item.getName();
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					filename = new Date().getTime() + filename.substring(filename.lastIndexOf("."));
				    item.write(new File(disFilePath+filename));
					jsonObject.put("result", true);  
					jsonObject.put("msg", filename);	
					item.delete();
				}
			}
		} catch (Exception e) {
			jsonObject.put("result", false);  
			jsonObject.put("msg", "文件上传失败");	
		}
		pw.write(jsonObject.toJSONString()); 
		pw.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
