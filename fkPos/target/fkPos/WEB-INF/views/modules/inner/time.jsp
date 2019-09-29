<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	//" no-cache:强制缓存从服务器上获取新的页面
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	//告诉代理服务器它的缓存页面何时将过期
	response.setDateHeader("Expires", -10);
%>

<!--time start -->
<div class="shindex_top">
	<div class="shit_left">
		欢迎<span class="shi_name">${userInfo.loginName}</span>登录&nbsp;商户管理系统 <span class="padleft15" id="clock"></span>
	</div>
	<div class="shit_right">
		<!-- <a href="#"><img src="<s:url value='/image/sh_icon_new.gif'/>" width="17" height="14" /></a><a href="#"><img src="<s:url value='/image/sh_icon_sz.gif'/>" width="16" height="17" /></a> -->
		<a href="javascript:;"><img alt="退出" src="${ctx}/resources/image/sh_icon_tc.gif" onclick="javascript:window.location.href='${ctx}/exit'" width="14" height="18" /></a>
	</div>
	<div class="clear"></div>
</div>
<!--time end -->

