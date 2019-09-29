<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/com/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<!-- 借记卡明细 -->
<meta charset="utf-8">
<meta name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no" />
<title><fmt:message key="wechat.ebank" /></title>
<%@ include file="/WEB-INF/views/com/cssjs.jsp"%>
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/carddetail.css'/>" />
<script type="text/javascript" src="<c:url value='/resources/js/carddetail.js'/>" ></script>
</head>
<body>
	<div class="cardmain">
	<form:form action="${ctxPath}/acctdetail/query" modelAttribute="acctDetailModel" method="post" id="eform">
			<div class="card_box">
				<div class="card_tit"><fmt:message key="cardnumber" /></div>
				<%-- <input type="hidden" name="cardno" value="${acctDetailModel.cardno }"> --%>
				<input type="hidden" name="openid" value="${acctDetailModel.openid }">
				
				<div class="card_num">
					<%-- <div class="current">
						<ul class="card-selecter" style="margin: 0;">
							<c:if test="${not empty acctDetailModel.cardno }">
								<li value="${acctDetailModel.cardno }">${acctDetailModel.cardno }</li>
							</c:if>
							<c:if test="${empty acctDetailModel.cardno }">
								<li><fmt:message key="selectcard" /></li>
							</c:if>
						</ul>
					</div>
					 <ul style="display: none;" class="select">
						<c:forEach var="list" items="${acctDetailModel.cardnos}">
							 <li value="${list.card}">${list.card}</li>
						</c:forEach>
					</ul> --%>
			        <select name="cardno" class="cardno"> 
					      <option value="" selected>请选择卡号</option>
					       <c:forEach var="cardno" items="${acctDetailModel.cardnos}" varStatus="i" >
					             <option value="${cardno.key}" <c:if test="${acctDetailModel.cardno==cardno.key}">selected</c:if>>${cardno.value}</option>
					       </c:forEach>
					</select> 
					<i></i>
				</div>
				<option></option>
			</div>
			<div class="find_time">
				<span>
					<div class="card_tit"><fmt:message key="startdate" /></div>
					<div class="find_left">
						<input  name="startDate" type="date" value="${acctDetailModel.startDate}">
					</div>
				</span> <span>
					<div class="card_tit"><fmt:message key="enddate" /></div>
					<div class="find_right">
						<input  name="endDate" type="date" value="${acctDetailModel.endDate}">
					</div>
				</span>
			</div>
			<div class="find_but">
				<input type="button" class="but" value="<fmt:message key="query" />" id="search"/>
			</div>
		</form:form>
		<div class="recordall">
			<c:if test="${not empty acctDetailModel.acctDetailList }">
				<div class="card_tit"><fmt:message key="transinfo" /></div>
			</c:if>
			<c:forEach var="list" items="${acctDetailModel.acctDetailList}">
				<div class="record_box">
					<ul>
						<li><span><fmt:message key="transdate" /></span>${list.transDate}</li>
						<li><span><fmt:message key="transtype" /></span><c:if test="${list.transtype eq '1'}">工资入职</c:if><c:if test="${list.transtype eq '2'}">汇款</c:if><c:if test="${list.transtype eq '3'}">ATM取款</c:if></li>
						<li><span><fmt:message key="transamt" /></span>${list.amount}</li>
					</ul>
				</div>
			</c:forEach>
		</div>
		<!--弹窗  -->
		<div id="end">
			<h3 id="info"></h3>
			<input type="button" id="res" class="res select1 closebtn" attr-id="end"/>
		</div>
		<!--弹窗  end-->
	</div>
</body>
</html>