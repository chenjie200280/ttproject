package com.ttsofts.action.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileInfoAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private File file;
	
	private String fileFileName;
	
	private boolean result = false;
	
	private String msg;

	@SuppressWarnings({"deprecation"})
	public String upload() throws Exception {
		try {
			File f = this.file;
			String path = null;
			String newFileName = null;
			if (this.fileFileName.endsWith(".apk")) {
				path = ServletActionContext.getRequest().getRealPath("/files/apps");
				newFileName = String.valueOf(new Date().getTime())+".apk";
			}else{
				result = false;
				msg = "该文件类型暂不允许上传";
				return SUCCESS;
			}
			FileInputStream inputStream = new FileInputStream(f);
			FileOutputStream outputStream = new FileOutputStream(path + "/" + newFileName);
			byte[] buf = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, length);
			}
			inputStream.close();
			outputStream.flush();
			outputStream.close();			
			result = true;
			msg = newFileName;
		} catch (Exception e) {
			result = false;
			msg = e.getMessage();
		}
		return SUCCESS;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	
	public boolean getResult() {
		return result;
	}
	
	public String getMsg() {
		return msg;
	}
}
