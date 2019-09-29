<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%String path = request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>显示列表页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/new_look.css" />
</head>
<script src="<%=path%>/js/sorttable.js"></script>
<script src="<%=path%>/js/eposcc.js"></script>
<script language="javascript">
	function addConfirm(){
		var bankForm = document.all("bankForm");
		bankForm.action = "<%=path%>/bank.do?method=preAddTBranchInfo";
		bankForm.submit();
	}
	
	function toGetTBankInfoList(){
		var bankForm = document.all("bankForm");
		bankForm.action = "<%=path%>/bank.do?method=getTBankInfoList";
		bankForm.submit();
	}
</script>

<body>
	<html:form action="/bank?method=getTBankInfoList" method="post" styleId="bankForm">
		<input type="hidden" name="id"  value="${param.id}">
		<input type="hidden" name="bank_code" value="">
		<bean:define id="menu_level" name="menu_level" />
		<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
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
								style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;当前位置：商户终端信息管理 &gt; 银行支行管理
							</td>
							<td width="7" height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td width="28" height="5" colspan="3"></td>
						</tr>
					</table>
					<table cellpadding="0" border="0" cellspacing="0" width="100%" style="padding: 0px;" align="left">
						<tr>
							<td coslpan="6"><font color="red">${info}</font></td>
						</tr>
						<tr class="serch">
							<td style="white-space: nowrap" align="center"  coslpan="6">
									<input type="button" class="button"
										onClick='return addConfirm()'
										style="background-image: url(<%=path%>/image1/border/New_button.gif)">
									<input class="button" type="button" value="返回"
									onclick="return toGetTBankInfoList()" />	
							</td>
						</tr>
						<tr>
						<td colspan="6">
							<display:table name="TBranchInfoList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/bank.do">
								<display:column title="银行编号" style="text-align:center"  property="bank_code" headerClass="sortable" sortable="true"/>
								<display:column title="银行支行编号" style="text-align:center" property="branch_code" headerClass="sortable" sortable="true" />
								<display:column title="银行支行名称" style="text-align:center" property="branch_name" headerClass="sortable" sortable="true" />
								<display:column title="查看" style="text-align:center" href="/bank.do?method=preQueryTBranchInfo" paramId="bankCodeAndBranchCode" paramProperty="bankCodeAndBranchCode">
									<img border="0" src="<%=path%>/image1/border/query.png" />
								</display:column>
								<display:column title="修改" style="text-align:center" href="/bank.do?method=preModTBranchInfo" paramId="bankCodeAndBranchCode" paramProperty="bankCodeAndBranchCode">
									修改
								</display:column>
								<display:column title="删除" style="text-align:center" href="/bank.do?method=delTBranchInfo" paramId="id" paramProperty="bankCodeAndBranchCode">
									删除
								</display:column>
							</display:table>	
						</td>
						</tr>
					</table>
			</td>
			</tr>
		</table>
	</html:form>
</body>
<script language="javascript">
	<c:if test="${!empty param.id}" >
		document.forms[0].id.value='<c:out value="${param.id}"/>';
	</c:if>
</script>
</html:html>
