package com.mi.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限过滤器，未登录状态将统一跳转到登录界面，登录成功后回到原页面
 * 
 * @author tangl 2016-2-16 16:59:09
 */
public class AuthFilter implements Filter {
	private String permitUrls[] = null; // 不拦截的url
	private String gotoUrl = null; // 拦截跳转的url

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (!isPermitUrl(request)) {
			if (filterCurrUrl(request)) {
				System.out.println("--->请登录");
				res.sendRedirect(req.getContextPath() + gotoUrl);
				return;
			}
		}
		System.out.println("--->允许访问");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		String permitUrls = config.getInitParameter("permitUrls");
		String gotoUrl = config.getInitParameter("gotoUrl");
		this.gotoUrl = gotoUrl;
		if (permitUrls != null && permitUrls.length() > 0) {
			this.permitUrls = permitUrls.split(",");
		}
	}

	@Override
	public void destroy() {
		permitUrls = null;
		gotoUrl = null;
	}

	/*
	 * 判断是否要拦截
	 */
	private boolean filterCurrUrl(ServletRequest request) {
		boolean filter = false;
		HttpServletRequest res = (HttpServletRequest) request;
		String user = (String) res.getSession().getAttribute("user");
		if (null == user)
			filter = true;
		return filter;

	}

	/*
	 * 判断是否为排除的url
	 */
	private boolean isPermitUrl(ServletRequest request) {
		boolean isPermit = false;
		String currentUrl = currentUrl(request);
		if (permitUrls != null && permitUrls.length > 0) {
			for (int i = 0; i < permitUrls.length; i++) {
				if (permitUrls[i].equals(currentUrl)) {
					isPermit = true;
					break;
				}
			}
		}
		return isPermit;
	}

	// 请求地址
	private String currentUrl(ServletRequest request) {
		HttpServletRequest res = (HttpServletRequest) request;
		String task = request.getParameter("task");
		String path = res.getContextPath();
		String uri = res.getRequestURI();
		if (task != null) {// uri格式 xx/ser
			uri = uri.substring(path.length(), uri.length()) + "?" + "task=" + task;
		} else {
			uri = uri.substring(path.length(), uri.length());
		}
		System.out.println("当前请求地址:" + uri);
		return uri;
	}

}
