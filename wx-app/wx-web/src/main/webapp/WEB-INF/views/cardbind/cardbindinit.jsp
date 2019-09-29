<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/com/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title><fmt:message key="wechat.ebank" /></title>
	<%@ include file="/WEB-INF/views/com/cssjs.jsp"%>
    <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/cardbind.css'/>" />
	<script type="text/javascript" src="<c:url value='/resources/js/cardbind.js'/>" ></script>
</head>
<body>
	<div class="centainer">
	<div class="top">
		<img src="${ctxPath}/resources/img/logo.jpg" />
	</div>
	<div class="tit">
		<ul id=tags>
			<li class=selectTag><a onClick="selectTag('table0',this)" href="javascript:void(0)" id="card"><fmt:message key="debitcard" /></a></li>
			<li><a onClick="selectTag('table1',this)" href="javascript:void(0)" id="credit"><fmt:message key="creditcard" /></a></li>
		</ul>
	</div>
	<input type="hidden" id="ctx" value="${ctxPath}">
	<!--借记卡-->
	<div class="table" id="table0" style="display: block;">
		<form:form action="${ctxPath}/cardbind/result" modelAttribute="cardBindModel" method="post" id="eform">
			<div style="display: none;" class="errormsg">
				<c:if test="${not empty error_captcha}">
						<div class="alert alert-danger" role="alert">${error_captcha}</div>
				</c:if>
				<c:if test="${empty error_captcha}">
					<form:errors path="*" >
						<div class="alert alert-danger" role="alert">
						<form:errors path="*" ></form:errors></div>
					</form:errors>
				</c:if>
			</div>
			<input type="hidden" name="openid" value="${cardBindModel.openid}">
			<input type="hidden" name="cardType" value="1" />
			<!-- -->
			<div class="main">
				<div class="tabbox">
					<ul>
						<c:choose>
						       <c:when test="${empty cardBindModel.username}">
						              <li><input type="text" id="username" placeholder="<fmt:message key='userName'/>" class="tabinput nameInput" name="username" value="${cardBindModel.username}"/></li>
						       </c:when>
						       <c:when test="${not empty cardBindModel.username}">
						       		  <input type="hidden" id="username"  name="username" value="${cardBindModel.username}"  />
						       		  <li><input type="text"  placeholder="<fmt:message key='userName'/>" class="tabinput nameInput tabinput-bg"  value="${cardBindModel.username}" disabled="disabled" /></li>
						       </c:when>
						</c:choose>
						
						<c:choose>
						       <c:when test="${empty cardBindModel.idno}">
						              <li><input type="text" id="idno" placeholder="<fmt:message key='idno.lastfour'/>" class="tabinput cardInput" name="idno" value="${cardBindModel.idno }"/></li>
						       </c:when>
						       <c:when test="${not empty cardBindModel.idno}">
						       		  <input type="hidden" id="idno" placeholder="<fmt:message key='idno.lastfour'/>" class="tabinput cardInput tabinput-bg" name="idno" value="${cardBindModel.idno }"/>
						       		  <li><input type="text" placeholder="<fmt:message key='idno.lastfour'/>" class="tabinput cardInput tabinput-bg" value="${cardBindModel.idno }" disabled="disabled"/></li>
						       </c:when>
						</c:choose>
						      
						<li><input type="text" id="cardno" placeholder="<fmt:message key='cardNum'/>" class="tabinput mcInput" name="cardno" value="${cardBindModel.cardno }"/></li>
						
						<c:choose>
						       <c:when test="${empty cardBindModel.mobile}">
						              <li><input type="text" id="mobile" placeholder="<fmt:message key='mobile.lastfour'/>" class="tabinput phoneInput" name="mobile" value="${cardBindModel.mobile }"/></li>
						       </c:when>
						       <c:when test="${not empty cardBindModel.mobile}">
						       		  <input type="hidden" id="mobile"  name="mobile" value="${cardBindModel.mobile }" />
						       		  <li><input type="text" placeholder="<fmt:message key='mobile.lastfour'/>" class="tabinput phoneInput tabinput-bg" value="${cardBindModel.mobile }"  disabled="disabled"/></li>
						       </c:when>
						</c:choose>
						
						<li><input type="text" id="msgcode" placeholder="<fmt:message key="msgcode"/>" class="tabinput codeInput" name="msgcode" value="${cardBindModel.msgcode }"/>
							<input type="button" class="checkbut" value="<fmt:message key="btnget"/>" name="msgbtn">
						</li>
					</ul>
				</div>		
			</div>
			<!-- -->
			<div class="box_bottom">
				<div class="checkbox">
					<div>
						<i><input type="checkbox" id="checkbox-1-1" class="regular-checkbox" checked="checked" id="noticestatus" name="noticestatus" value="1"/><label for="checkbox-1-1"></label></i>
						<span><i><fmt:message key="accountnotice"/></i></span>
					</div>	
					<div>
						<i><input type="checkbox" id="checkbox-1-2" class="regular-checkbox" checked="checked"/><label for="checkbox-1-2" ></label></i>
						<span>我同意<a href="${ctxPath}/resources/date/terms.html">《坚石诚信电子银行个人客户服务协议》</a></span>
					</div>	
				</div>
				<div class="butmian">
					<input type="button" class="but" value="<fmt:message key="submit" />"/>
				</div>
			</div>
		</form:form>
	</div>
	<!--借记卡  end-->
	<!--信用卡-->
	<div class="table" id="table1">
		<form:form action="${ctxPath}/creditbind/result" modelAttribute="creditBindModel" method="post" id="eform">
			<div style="display: none;" class="errormsg">
				<c:if test="${not empty error_captcha}">
					<div class="alert alert-danger" role="alert">${error_captcha}</div>
				</c:if>
				<c:if test="${empty error_captcha}">
					<form:errors path="*" >
						<div class="alert alert-danger" role="alert"><form:errors path="*" ></form:errors></div>
					</form:errors>
				</c:if>
			</div>
			<input type="hidden" name="openid" value="${creditBindModel.openid}">
			<input type="hidden" name="cardType" value="2">
			<!-- -->
			<div class="main">			
				<div class="tabbox">
					<ul>
						<c:choose>
						       <c:when test="${empty creditBindModel.creusername}">
						              <li><input type="text" id="creusername" placeholder="<fmt:message key='userName'/>" class="tabinput nameInput" name="creusername" value="${creditBindModel.creusername }"/></li>
						       </c:when>
						       <c:when test="${not empty creditBindModel.creusername}">
						       		  <input type="hidden" id="creusername" name="creusername" value="${creditBindModel.creusername }"/>
						       		  <li><input type="text"  placeholder="<fmt:message key='userName'/>" class="tabinput nameInput tabinput-bg" value="${creditBindModel.creusername }"  disabled="disabled"/></li>
						       </c:when>
						</c:choose>
						
						<c:choose>
						       <c:when test="${empty creditBindModel.creidno }">
						              <li><input type="text" id="creidno" placeholder="<fmt:message key='idno.lastfour'/>" class="tabinput cardInput" name="creidno" value="${creditBindModel.creidno }"/></li>
						       </c:when>
						       <c:when test="${not empty creditBindModel.creidno }">
						       		 <input type="hidden" id="creidno"  name="creidno" value="${creditBindModel.creidno }"/>
						       		 <li><input type="text" placeholder="<fmt:message key='idno.lastfour'/>" class="tabinput cardInput tabinput-bg"  value="${creditBindModel.creidno }"  disabled="disabled"/></li>
						       </c:when>
						</c:choose>
						
						<li><input type="text" id="crecardno" placeholder="<fmt:message key='cardNum'/>" class="tabinput mcInput" name="crecardno" value="${creditBindModel.crecardno }"/></li>
						
						<c:choose>
						       <c:when test="${empty creditBindModel.cremobile}">
						             <li><input type="text" id="cremobile" placeholder="<fmt:message key='mobile.lastfour'/>" class="tabinput phoneInput" name="cremobile" value="${creditBindModel.cremobile }"/></li>
						       </c:when>
						       <c:when test="${not empty creditBindModel.cremobile}">
						       		 <input type="hidden" id="cremobile" name="cremobile" value="${creditBindModel.cremobile }" />
						       		 <li><input type="text" placeholder="<fmt:message key='mobile.lastfour'/>" class="tabinput phoneInput tabinput-bg" value="${creditBindModel.cremobile }"  disabled="disabled"/></li>
						       </c:when>
						</c:choose>
						
						
						<li><input type="text" id="cremsgcode" placeholder="<fmt:message key="msgcode"/>" class="tabinput codeInput" name="cremsgcode" value="${creditBindModel.cremsgcode }"/>
							<input type="button" class="checkbut" value="<fmt:message key="btnget"/>" name="msgbtn">
						</li>
					</ul>
				</div>
			</div>
			
			<!-- -->
			<div class="box_bottom">
				<div class="checkbox">	
					<div>
						<input type="hidden" name="crenoticestatus" value="1"/>
						<i><input type="checkbox" id="checkbox-1-3" class="regular-checkbox" checked="checked"/><label for="checkbox-1-3" ></label></i>
						<span>我同意<a href="${ctxPath}/resources/date/terms.html">《坚石诚信电子银行个人客户服务协议》</a></span>
					</div>	
				</div>
				<div class="butmian">
					<input type="button" class="but" value="<fmt:message key="submit" />"/>
				</div>
			</div>
		</form:form>
	</div>
	<!--信用卡  end-->
	<!--tall-->
	<div class="footer">
		<span><img src="${ctxPath}/resources/img/f_l.png"/></span>
		<span>客户热线：4006-501-051</span>
		<span><img src="${ctxPath}/resources/img/f_r.png"/></span>
	</div>
	<!--弹窗  -->
	<div id="end">
		<h3 id="info"></h3>
		<input type="button" id="res" class="res select closebtn" attr-id="end"/>
	</div>
	<!--弹窗  end-->
</div>
</body>
</html>