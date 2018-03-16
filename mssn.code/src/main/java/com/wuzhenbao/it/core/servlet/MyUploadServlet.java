package com.wuzhenbao.it.core.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.wuzhenbao.it.core.hdfs.HdfsUtil;
import com.wuzhenbao.it.core.util.CommonUtil;

public class MyUploadServlet extends HttpServlet implements Servlet {
	private String savePath;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4272857630829318925L;
	private static Logger log = Logger.getLogger(MyUploadServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//文件存储的路径，每天一个文件夹
		String dataStr = CommonUtil.formatStrFromDate(new Date(),null);
		String path = savePath+dataStr+"/";
		try{
			//解析页面上传的文件
			if(ServletFileUpload.isMultipartContent(req)){
				FileItemFactory dfif = new DiskFileItemFactory();
				ServletFileUpload sfu =  new ServletFileUpload(dfif);
				sfu.setSizeMax(1073741824);
				List<FileItem> items =sfu.parseRequest(req);
				String dest = null;
				for(FileItem item : items){
					//如果不是文件域，则是上传的目标目录，简单不处理
					if(item.isFormField()){
						if(item.getString().indexOf("destDir") > -1) {
							log.info("===========页面选择上传的目标目录："+item.getString());
							dest = item.getString().split(":")[1];
						}
					}else{
						//上传前的文件名称
						/*String oldName = item.getName();
						//文件类型
						String fileType = oldName.substring(oldName.lastIndexOf(".")+1);
						//根据日期拼接的新的文件名称
						String newName = oldName.substring(0,oldName.lastIndexOf("."))+dataStr+"."+fileType;
						
						//把文件保存到指定的目录下面
						String filePath = path+newName;
						File file = new File(filePath);
						if(!file.getParentFile().exists()) {
							file.getParentFile().mkdirs();
						}
						//item.write(file);*/
						HdfsUtil.uploadToHDFS(item, dest);
					}
				}
			}
		}catch(FileUploadException e){
			log.info("MyUploadServlet解析上传文件报错="+e);
		}catch( Exception e1){
			log.info("MyUploadServlet处理上传文件报错="+e1);
		}
		//将文件信息保存到数据库
		resp.setContentType("text/html;charset=utf8");
		PrintWriter pw = resp.getWriter();
		pw.print("success");
		pw.flush();
		pw.close();
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.savePath = config.getInitParameter("savePath");
	}
}
