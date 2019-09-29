package com.jansh.core.web.servlet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

import com.jansh.core.web.servlet.interceptor.SecurityRequestInterceptor;

public class SecurityRequestDataValueProcessor implements RequestDataValueProcessor {

	private Pattern DISABLE_CSRF_TOKEN_PATTERN = Pattern.compile("(?i)^(GET|HEAD|TRACE|OPTIONS)$");

	private String DISABLE_CSRF_TOKEN_ATTR = "DISABLE_CSRF_TOKEN_ATTR";

	public String processAction(HttpServletRequest request, String action) {
		return action;
	}

	public String processAction(HttpServletRequest request, String action, String method) {
		if (method != null && DISABLE_CSRF_TOKEN_PATTERN.matcher(method).matches()) {
			request.setAttribute(DISABLE_CSRF_TOKEN_ATTR, Boolean.TRUE);
		} else {
			request.removeAttribute(DISABLE_CSRF_TOKEN_ATTR);
		}
		return action;
	}

	public String processFormFieldValue(HttpServletRequest request, String name, String value, String type) {
		return value;
	}

	public Map<String, String> getExtraHiddenFields(HttpServletRequest request) {
		Map<String, String> hiddenFields = new HashMap<String, String>(2);
		String rrtoken = (String) request.getSession(false)
				.getAttribute(SecurityRequestInterceptor.REPEAT_REQUEST_TOKEN);
		if (rrtoken != null) {
			hiddenFields.put(SecurityRequestInterceptor.REPEAT_REQUEST_TOKEN, rrtoken);
		}

		if (Boolean.TRUE.equals(request.getAttribute(DISABLE_CSRF_TOKEN_ATTR))) {
			request.removeAttribute(DISABLE_CSRF_TOKEN_ATTR);
		}else{
			CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
			if (token != null) {
				hiddenFields.put(token.getParameterName(), token.getToken());
			}
		}
		if(hiddenFields.isEmpty()){
			return Collections.emptyMap();
		}
		
		return hiddenFields;
	}

	public String processUrl(HttpServletRequest request, String url) {
		return url;
	}

}
