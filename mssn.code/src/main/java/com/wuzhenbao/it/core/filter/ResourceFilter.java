package com.wuzhenbao.it.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResourceFilter implements Filter {
	private static final Log log = LogFactory.getLog(ResourceFilter.class);
	public void destroy() {
		log.info("系统关闭=====");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse rsp = (HttpServletResponse)response;
		String uri = req.getRequestURL().toString();
		//判断url，如果不存在系统中，则跳转到首页
		System.out.println(uri);
		if("/".equals(uri)){
			rsp.sendRedirect("index.html");
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		log.info("欢迎使用mssn系统！");
	}

}
