<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	//" no-cache:强制缓存从服务器上获取新的页面
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	//告诉代理服务器它的缓存页面何时将过期
	response.setDateHeader("Expires", -10);
%>

<!--foot start -->
<div class="shmc_left">
	<ul>
		<c:if test="${ currentModule == 0}">
			<li <c:if test="${ sub_currentModule == 01}">class="listbg"</c:if>><a href="${ctx }/agentManager/agentInfo" class="shlb">代理商信息</a></li>
			<li <c:if test="${ sub_currentModule == 02}">class="listbg"</c:if>><a href="${ctx }/agentManager/updatePassword" class="xgmm">修改密码</a></li>
		</c:if>
		<c:if test="${ currentModule == 1}">
			<li <c:if test="${ sub_currentModule == 11}">class="listbg"</c:if>><a href="${ctx }/mercManager/mercInfo" class="shlb">商户信息</a></li>
			<li <c:if test="${ sub_currentModule == 12}">class="listbg"</c:if>><a href="${ctx }/mercManager/updatePassword" class="xgmm">修改密码</a></li>
		</c:if>
		<c:if test="${ currentModule == 3}">
			<c:if test="${userInfo.type == 1 }">
				<li <c:if test="${ sub_currentModule == 31}">class="listbg"</c:if>><a href="${ctx }/tradeManager/tradeInfo" class="xflb">交易流水</a></li>
				<li <c:if test="${ sub_currentModule == 41}">class="listbg"</c:if>><a href="${ctx }/tradeManager/tradeReport" class="xflb">交易统计</a></li>
			</c:if>
			<c:if test="${userInfo.type == 2 }">
				<li <c:if test="${ sub_currentModule == 41}">class="listbg"</c:if>><a href="${ctx }/tradeManager/tradeReport" class="xflb">交易统计</a></li>
			</c:if>
		</c:if>
		<c:if test="${ currentModule == 4}">
			<li <c:if test="${ sub_currentModule == 41}">class="listbg"</c:if>><a href="/settleInfo!getSettleInfo." class="rjsbb">日结算报表</a></li>
		</c:if>
	</ul>
</div>
<div class="clear"></div>
<!--foot end -->

