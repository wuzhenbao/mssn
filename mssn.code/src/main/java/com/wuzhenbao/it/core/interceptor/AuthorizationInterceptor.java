package com.wuzhenbao.it.core.interceptor;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;


public class AuthorizationInterceptor implements WebRequestInterceptor{

	private static final long serialVersionUID = 2575538469018873724L;

	public void preHandle(WebRequest request) throws Exception {
		
	}

	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		
	}

	public void afterCompletion(WebRequest request, Exception ex)
			throws Exception {
		
	}

	/*@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		Map<String,Object> session = invocation.getInvocationContext().getSession();
		// String encoding = request.getCharacterEncoding();
		// System.err.println(encoding);
		Object usero = request.getAttribute("catalog.catalogNameCn");
		System.out.println(usero);
		String userName = request.getParameter("catalog.catalogNameCn");
		System.out.println(userName);
		Object o =  session.get(Constants.USERNAME_KEY);
		if(null != o){
			UserVO user = (UserVO)o;
			if (StringUtils.isNotBlank(user.getUserNameCn())) {
				// 合法用户
				return invocation.invoke();
			}
		}
		System.out.println("拦截器：用户未登录---");
		//String requestType = request.getHeader(Constants.HEADER);
		//如果为空，表示不是ajax提交
		//if(StringUtils.isBlank(requestType)){
			return "login";
		//}else{
			//Map<String,String> errors = new HashMap<String,String>();
			//errors.put("message", "用户未登录");
			//errors.put("errFlag", Constants.AJAX_ERROR_LOGIN_USER_INVAILD);
			//String msg = JSONUtil.serialize(errors);
			//HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
			//PrintWriter out = response.getWriter();
			//out.print(msg);
			//return null;
		}*/
		
	
}
