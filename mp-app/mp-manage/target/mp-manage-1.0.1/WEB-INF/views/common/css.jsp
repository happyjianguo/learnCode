<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String cssv = "201601011189";
	request.setAttribute("cssv",cssv);
%>
<!-- css -->
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/bootstrap.min.css?v=${cssv}'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/font-awesome.min.css?v=${cssv}'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/simple-line-icons.css?v=${cssv}'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/dataTables.bootstrap.min.css?v=${cssv}'/>">
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/bootstrap-select.min.css?v=${cssv}'/>">
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/common.css?v=${cssv}'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/bootstrap-datepicker3.min.css?v=${cssv}'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/bootstrap-datepicker3.standalone.min.css?v=${cssv}'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/fileinput.min.css?v=${cssv}'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/zTreeStyle.css?v=${cssv}'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css?v=${cssv}'/>" />