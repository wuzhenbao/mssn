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

public class RedirectFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse rsp = (HttpServletResponse)response;
		String redirectUrl = req.getParameter("redirectUrl");
		//判断url，如果不存在系统中，则跳转到首页
		System.out.println("redirectUrl="+redirectUrl);
		if("/".equals(redirectUrl)){
			rsp.sendRedirect("index.html");
		}else{
			rsp.sendRedirect(redirectUrl);
		}
	}

	public void destroy() {

	}

}
