package com.wuzhenbao.it.core.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.log4j.Logger;

import com.wuzhenbao.it.core.hdfs.HdfsUtil;

public class MyDownloadServlet extends HttpServlet implements Servlet {
	private String savePath;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4272857630829318925L;
	private static Logger log = Logger.getLogger(MyDownloadServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	//字符编码
	private final String ENCODING="GB2312";
	//内容类型
	private final String CONTENT_TYPE="text/html;charset=gb2312";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置request对象的字符编码
		request.setCharacterEncoding(ENCODING);
		// 从request中取出要下载文件的名字
		String filePath = request.getParameter("filePath");
		if (filePath == null || filePath.trim().equals("")) {
			// 设置response对象的ContentType
			response.setContentType(CONTENT_TYPE);
			// 输出错误信息
			PrintWriter out = response.getWriter();
			out.println("<font color=red>输入的文件名无效！</font>");
			out.close();
		} else {
		try {	
		    FileSystem fs = HdfsUtil.getFS();  
	        InputStream in = fs.open(new Path(fs.getUri()+filePath));  
			// 根据文件的类型设置response对象的ContentType
			String contentType = "application/octet-stream";
			response.setContentType(contentType);
			// 设置response的头信息
			response.setHeader("Content-disposition", "attachment;filename=\"" + filePath + "\"");
			OutputStream os = null;
			try {
				// 定义输出字节流
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				// 定义response的输出流
				os = new BufferedOutputStream(response.getOutputStream());
				// 定义buffer
				byte[] buffer = new byte[4 * 1024]; // 4k Buffer
				int read = 0;
				// 从文件中读入数据并写到输出字节流中
				while ((read = in.read(buffer)) != -1) {
					baos.write(buffer, 0, read);
				}
				// 将输出字节流写到response的输出流中
				os.write(baos.toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// 关闭输出字节流和response输出流
				os.close();
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
