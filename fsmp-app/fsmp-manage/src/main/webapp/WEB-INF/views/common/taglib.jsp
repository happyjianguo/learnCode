<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- <%@ taglib prefix="cx" uri="http://www.jansh.com.cn/tags/cx"%> --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<c:set var="csrfheader" value="${_csrf.headerName}"/>
<c:set var="csrftoken" value="${_csrf.token}"/>