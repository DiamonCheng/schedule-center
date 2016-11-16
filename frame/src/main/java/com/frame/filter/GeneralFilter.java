package com.frame.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.frame.core.components.ThreadBinder;
/**
 * 通用过滤器
 * 一般用于注入ctx对象
 * @author Defferson.Cheng
 *
 */
public class GeneralFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setAttribute("ctx", request.getServletContext().getContextPath());
		ThreadBinder.set(ThreadBinder.REQUEST, request);
		ThreadBinder.set(ThreadBinder.RESPONSE, response);
		ThreadBinder.set(ThreadBinder.SESSION, ((HttpServletRequest)request).getSession());
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
