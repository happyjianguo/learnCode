package com.jansh.core.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.filter.GenericFilterBean;

import com.jansh.core.menu.MenuAuths;
import com.jansh.core.security.userdetails.UserDetail;

public class MenuStatusFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			throw new ServletException("MenuStatusFilter just supports HTTP requests");
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String contextPath = httpRequest.getServletPath();
		SecurityContext sc = SecurityContextHolder.getContext();
		Authentication auth = null;
		Object principal = null;
		if (sc != null) {
			auth = sc.getAuthentication();
		}
		if (auth != null) {
			principal = auth.getPrincipal();
		}
		UserDetail userDetail = null;
		if (principal instanceof UserDetail) {
			userDetail = (UserDetail) principal;
		}
		if (userDetail != null && contextPath != null) {
			List<MenuAuths> menuList = userDetail.getMenuList();
			if (isMenu(menuList, contextPath)) {
				checkUrlMenu(menuList, contextPath);
				//刷新redis中session信息
				httpRequest.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
						SecurityContextHolder.getContext());
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * 将菜单置为选中
	 * 
	 * @param menuList
	 * @param url
	 * @return
	 */
	private boolean checkUrlMenu(List<MenuAuths> menuList, String url) {
		if (menuList == null) {
			return false;
		}
		boolean flag = false;
		for (MenuAuths menu : menuList) {
			// 置为未选中
			menu.setSelected("0");
			if (url.equals(menu.getMenuurl())) {
				// 置为选中
				menu.setSelected("1");
				flag = true;
			} else {
				if (checkUrlMenu(menu.getChild(), url)) {
					// 置为选中
					menu.setSelected("1");
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * 判断是否为菜单
	 * 
	 * @param menuList
	 * @param url
	 * @return
	 */
	private boolean isMenu(List<MenuAuths> menuList, String url) {
		if (menuList == null) {
			return false;
		}
		for (MenuAuths menu : menuList) {
			if (url.equals(menu.getMenuurl())) {
				return true;
			} else {
				if (isMenu(menu.getChild(), url)) {
					return true;
				}
			}
		}
		return false;
	}
}
