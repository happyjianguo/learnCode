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
		bankForm.action = "<%=path%>/bank.do?method=preAddTBankInfo";
		bankForm.submit();
	}

	function query(){
		var bankForm = document.all("bankForm");
		bankForm.action = "<%=path%>/bank.do?method=getTBankInfoList";
		bankForm.submit();
	}
	
	function toAddBranch(id,method)
	{
		document.forms[0].method.value=method;
		document.forms[0].id.value=id;
		document.forms[0].submit();
	}	
	
	function toShowBranchList(id,method)
	{
		document.forms[0].method.value=method;
		document.forms[0].id.value=id;
		document.forms[0].submit();
	}	
	function deleteClick(id){
		if(confirm("确定要删除编号为"+id+"的银行信息？")){
			var bankForm = document.all("bankForm");
			bankForm.action = "<%=path%>/bank.do?method=delTBankInfo&id="+id;
			bankForm.submit();			
		}
	}	
	
</script>

<body>
	<html:form action="/bank?method=getTBankInfoList" method="post" styleId="bankForm">
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
								&nbsp;&nbsp;当前位置：商户终端信息管理 &gt; 银行管理
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
							<td coslpan="9"><font color="red">${info}</font></td>
						</tr>
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								银行编号
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="queryBankCode" maxlength="15" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
								银行名称
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="queryBankName" maxlength="15" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
								英文简写
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="queryLogo" maxlength="15" size="16" />
							</td>
							<td style="white-space: nowrap" align="left">
								<input type="button" class="button" onClick='return query()'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)">
								<input type="button" class="button"
									onClick='return addConfirm()'
									style="background-image: url(<%=path%>/image1/border/New_button.gif)">
							</td>
						</tr>
						<tr>
						<td colspan="9">
							<display:table name="TBankInfoList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/bank.do">
								<display:column title="银行编号" style="text-align:center" property="bank_code" headerClass="sortable" sortable="true" />
								<display:column title="银行名称" style="text-align:center" property="bank_name" headerClass="sortable" sortable="true" />
								<display:column title="银行简称" style="text-align:center"  property="bank_short_name" headerClass="sortable" sortable="true"/>
								<display:column title="英文简写" style="text-align:center"  property="logo" headerClass="sortable" sortable="true"/>
								<display:column title="查看" style="text-align:center" href="/bank.do?method=preQueryTBankInfo" paramId="id" paramProperty="bank_code">
									<img border="0" src="<%=path%>/image1/border/query.png" />
								</display:column>
								<display:column title="修改" style="text-align:center" href="/bank.do?method=preModTBankInfo" paramId="id" paramProperty="bank_code">
									修改
								</display:column>
								<display:column title="删除" style="width:5%;text-align:center">
									<a href="javascript:;" onclick="deleteClick('<c:out value="${displayTable.bank_code}"/>')">删除</a>
								</display:column>								
								<display:column title="添加支行" style="text-align:center" href="/bank.do?method=preAddTBranchInfo" paramId="id" paramProperty="bank_code">
									添加支行
								</display:column>	
								<display:column title="查看支行列表" style="text-align:center" href="/bank.do?method=getTBranchInfoList" paramId="id" paramProperty="bank_code">
									查看支行列表
								</display:column>								
							</display:table>
							<input type="hidden" name="id">
						</td>
						</tr>
					</table>
			</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
