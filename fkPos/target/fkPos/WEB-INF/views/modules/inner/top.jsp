<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	//" no-cache:强制缓存从服务器上获取新的页面
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	//告诉代理服务器它的缓存页面何时将过期
	response.setDateHeader("Expires", -10);
%>
<!--top start -->
<div class="shm_menu">
	<div class="shm_left">
		<a href="javascript:;"> <img src="${ctx}/resources/image/syfpay.png" height="44" /></a>
	</div>
	<div class="shm_right">
		<ul>
			<c:if test="${userInfo.type == 1 }">
				<li><a href="${ctx }/mercManager/mercInfo" <c:if test="${ currentModule == 1}">class="active"</c:if>>商户管理</a></li>
			</c:if>
			<c:if test="${userInfo.type == 2 }">
				<li><a href="${ctx }/agentManager/agentInfo" <c:if test="${ currentModule == 0}">class="active"</c:if>>代理商管理</a></li>
			</c:if>
			<li><a href="${ctx }/tradeManager/tradeInfo" <c:if test="${ currentModule == 3}">class="active"</c:if>>交易管理</a></li>
		</ul>
	</div>
	<div class="clear"></div>
</div>
<!--top end -->

