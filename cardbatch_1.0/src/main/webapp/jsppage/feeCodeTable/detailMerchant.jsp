<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html lang="true">
<head>
	<html:base />

	<title>商户机构明细页面</title>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/cssem.css" />
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
	<script type="text/javascript" src="<%=path%>/js/textselect.js"></script>
	<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
	<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>

</head>

<body>
	<table border="0" cellpadding="0" cellspacing="0"
		style="width: 100%; height: 99%;">
		<tr>
			<td align="center" valign="top">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td style="width:28px; height:10px;"></td>
					</tr>
					<tr>
						<td align="left"
							style="  width:28px; height:28px; background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
						</td>
						<td
							style="height:28px;  background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
							&nbsp;&nbsp;当前位置： 商户机构管理 &gt; 商户明细
						</td>
						<td
							style=" width:7px; height:28px; background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
						</td>
					</tr>
					<tr>
						<td style="width: 28px; height: 5px" colspan="3"></td>
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<c:forEach var="model" items="${merchantList}">
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户号
							</td>
							<td align="left" class="box2">
								<input type="text" value="${model.mrchno}" disabled="disabled"/>
							</td>
							<td style="width: 110px;" align="right" class="box1">
								商户名称
							</td>
							<td align="left" class="box2">
								<input type="text" value="${model.mrcht_name}" disabled="disabled"/>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td align="center" class="box2" colspan="4">
							<font color="red">总计：<c:out value="${counts}"></c:out> 个商户</font>
						</td>
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td style="height: 23px;" align="center" class="box1">
							<input class="button" type="button"  value="关闭" 
							onclick="history.go(-1);" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html:html>


