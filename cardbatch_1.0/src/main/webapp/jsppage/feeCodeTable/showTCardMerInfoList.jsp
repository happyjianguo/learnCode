<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html lang="true">
<head>
	<html:base />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/new_look.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style.css" />
	<script type="text/javascript" src="<%=path%>/js/eposcc.js"></script>
	<script src="<%=path%>/js/calendar.js"></script>
	<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
</head>
<script language="javascript">
	function addConfirm(){
		var url = "<%=path%>/tCardMerInfo.do?method=preAddTCardMerInfo&random=" + Math.random();
		var iWidth = 1046; //弹出窗口的宽度;
		var iHeight = 600; //弹出窗口的高度;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
		var winSize = 'height='
				+ iHeight
				+ ',width='
				+ iWidth
				+ ',top='
				+ iTop
				+ ',left='
				+ iLeft
				+ ',toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no';
		window.open(url, "", winSize);	
	}
	<%-- function addConfirm(){
		var url = "<%=path%>/tCardMerInfo.do?method=preAddTCardMerInfo&random=" + Math.random();
		var tCardMerInfoForm = document.all("tCardMerInfoForm");
		tCardMerInfoForm.action = url;
		tCardMerInfoForm.submit();
	} --%>
	function query(){
		var tCardMerInfoForm = document.all("tCardMerInfoForm");
		tCardMerInfoForm.action = "<%=path%>/tCardMerInfo.do?method=getTCardMerInfoList";
		tCardMerInfoForm.submit();
	}
	function resetClick()
	{
		document.forms[0].merNo.value="";		
		document.forms[0].cardNo.value="";
	}	
	function deleteClick(merNo,cardNo){
		var url = "<%=path%>/tCardMerInfo.do?method=preDeletetCardMerInfo&merNo="+merNo+"&cardNo="+cardNo+"&random=" + Math.random();
		var tCardMerInfoForm = document.all("tCardMerInfoForm");
		tCardMerInfoForm.action = url;
		tCardMerInfoForm.submit();
	}
</script>
<shiro:lacksPermission name="cardbatch:tcardmerinfo:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="cardbatch:tcardmerinfo:view">
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form action="/tCardMerInfo?method=getTCardMerInfoList" method="post"
		styleId="tCardMerInfoForm">
		<bean:define id="menu_level" name="menu_level" />
		<table border="0" cellpadding="0" cellspacing="0" width="100%"
			height="100%">
			<tr>
				<td align="center" valign="top" height="87%">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="28" height="10"></td>
						</tr>
						<tr>
							<td align="left" width="28" height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
							</td>
							<td height="28"
								style="background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;当前位置： 商户终端管理 &gt; 商户活动卡管理
							</td>
							<td width="7" height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td width="28" height="5" colspan="3"></td>
						</tr>
					</table>
					<table cellpadding="0" border="0" cellspacing="0" width="100%"
						 style="padding: 0px;" align="left">
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								商户号
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="merNo" maxlength="15" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
								卡号
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="cardNo" maxlength="15" size="16"/>
							</td>
							<td height="25" align="right">
								<input type="button" class="button" onclick='return query()'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)" />
							</td>
							<td height="25" align="left">						
								<input type="button" class="button" onclick='return resetClick()'
									style="background-image: url(<%=path%>/image1/border/Clear_button.gif)" />	
							</td>	
							<shiro:hasPermission name="cardbatch:tcardmerinfo:add">
								<td height="25" align="right">
									<input type="button" class="button"
										onClick='return addConfirm()'
										style="background-image: url(<%=path%>/image1/border/New_button.gif)">
								</td>
								<td height="25" align="left"></td>
							</shiro:hasPermission>
						</tr>
						<tr>
						<td colspan="13">
							<display:table name="merchantOrgList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/tCardMerInfo.do">
								<display:column title="商户号" style="text-align:center" property="merNo" headerClass="sortable" sortable="true" />
								<display:column title="卡号" style="text-align:center" property="cardNo" headerClass="sortable" sortable="true" />
								<display:column title="操作者" style="text-align:center" property="addUser" headerClass="sortable" sortable="true" />
								<display:column title="操作时间" style="text-align:center" property="timeStamp" headerClass="sortable" sortable="true" />
								<shiro:hasPermission name="cardbatch:tcardmerinfo:add">
									<display:column title="操作" style="text-align:center">
										 <a href="javascript:;" onclick="deleteClick('<c:out value="${displayTable.merNo}"/>','<c:out value="${displayTable.cardNo}"/>')">删除</a>
									</display:column>
								</shiro:hasPermission>
							</display:table>
						</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</shiro:hasPermission>
</html:html>