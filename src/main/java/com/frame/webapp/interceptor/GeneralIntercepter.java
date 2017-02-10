package com.frame.webapp.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.frame.core.components.AjaxResult;
import com.frame.core.utils.HttpContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.frame.core.components.ThreadBinder;
import com.frame.core.components.UserAuthoritySubject;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.RedirectView;

public class GeneralIntercepter implements HandlerInterceptor {
	private final static Logger LOGGER=LoggerFactory.getLogger(GeneralIntercepter.class);
	public static final String REQUEST_URI_REQUEST_KEY=GeneralIntercepter.class.getName()+".requestURI";
	public static final String REQUEST_URI_BEFORE_LOGIN_THREAD_KEY=GeneralIntercepter.class.getName()+".REQUESTURI_BEFOTRLOGIN";
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI=request.getServletPath();
		if (requestURI.startsWith("/resources/")) return true;
		boolean flag=false;
		if (!UserAuthoritySubject.isUserVerify()&& !requestURI.contains("login")){//未登录 进入
			if (HttpContextUtil.isAjaxRequest()){
				response.setHeader("SESSION_STATUS", "TIME_OUT");
			}else if(request.getHeader("accept").matches(".*html.*")){
				String urlToSave=requestURI;
				if (request.getQueryString()!=null) urlToSave+="?"+request.getQueryString();
				request.getSession().setAttribute(REQUEST_URI_BEFORE_LOGIN_THREAD_KEY, urlToSave);
				response.sendRedirect(request.getContextPath()+"/login");
			}
		}else{
			flag=true;
			if (request.getHeader("accept").matches(".*html.*")&&request.getAttribute(REQUEST_URI_REQUEST_KEY)==null) {
				request.setAttribute(REQUEST_URI_REQUEST_KEY,requestURI);
				LOGGER.info("Intercepter-> set requestURI : "+requestURI+" in session");
			}
		}
		return flag;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		/*if (modelAndView!=null) {
			String viewName = modelAndView.getViewName();
			if (viewName.startsWith("redirect:")){
				viewName=viewName.substring(9);
				UserAuthoritySubject.getSession().setAttribute(REQUEST_URI_SESSION_KEY,viewName);
			}
		}*/
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
			throws Exception {
		if (e!=null){
			LOGGER.error("拦截器捕获到异常。",e);
//			request.getSession().removeAttribute(REQUEST_URI_SESSION_KEY);
			if (HttpContextUtil.isAjaxRequest()) {
				AjaxResult res = new AjaxResult();
				res.setCode("-1");
				res.setMessage("发生异常：" + e.getMessage());
				res.setData(e);
				response.setCharacterEncoding("UTF-8");
				response.setStatus(500);
				response.setHeader("AJAX_ERROR","1");
				response.setHeader("Content-Type","application/json;charset=UTF-8");
				response.getWriter().print(res);
				response.getWriter().flush();
				response.getWriter().close();
			}else{
				throw e;
			}
		}

	}

}
