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
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/new_look.css" />
	<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<script language="javascript">
	function query(){
		var userForm = document.all("acctForm");
		userForm.action = "<%=path%>/queryAcct.do?method=getAcctList";
		userForm.submit();
	}
	function queryOne(id)
	{
		var url = "<%=path%>/queryAcct.do?method=getAcctById&id="+id+"&random=" + Math.random();
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

	function queryCustById(id)
	{
		var url = "<%=path%>/queryCust.do?method=getCustById&id="+id+"&random=" + Math.random();
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
</script>

<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
<html:form action="/queryAcct?method=getAcctList" method="post" styleId="acctForm">
<html:hidden property="id"/>
	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" >
		<tr>
			<td align="center" valign="top" >
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
					</td>
					<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置： 信息查询 &gt; 账户查询 </td>
					<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
				</tr>
				</table>
				<table cellpadding="0" cellspacing="0" width="100%" align="left"  >
					<tr class="serch">
						<td align="right">账户号：</td>
						<td align="left" ><html:text property="accno" maxlength="20" size="16"/></td>
						<td align="right">客户ID：</td>
						<td align="left" ><html:text property="custdet_id" maxlength="20" size="16"/></td>
						<td align="right">账户类型：</td>
						<td align="left">
						<logic:present name="acctTypeList">
							<html:select property="typecode" style="width:100px;" >
								<html:option value="">－请选择－</html:option>
								<html:optionsCollection name="acctTypeList" label="descr" value="typecode" />
							</html:select>
						</logic:present>
						</td>
						<td align="right">状态：</td>
						<td align="left">
						<logic:present name="acctStatusList">
							<html:select property="statcode" style="width:100px;" >
								<html:option value="">－请选择－</html:option>
								<html:optionsCollection name="acctStatusList" label="descr" value="statcode" />
							</html:select>
						</logic:present>
						</td>
						<td height="25" align="left">
							<input type="button" class="button" onClick='return query()'  style="background-image: url(<%=path%>/image1/border/Check_button.gif)">									
						</td>
					</tr>
					<tr>
						<td colspan="9">
							<!-- 用Diaplay Tag来显示 -->
							<display:table name="acctList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/queryAcct.do">
								<display:column title="账户号" style="text-align:center" property="accno" headerClass="sortable" sortable="true" />
								<display:column title="账户类型" style="text-align:center" property="typecode_name" headerClass="sortable" sortable="true" />
								<display:column title="状态" style="text-align:center" property="statcode_name" headerClass="sortable" sortable="true" />
								<display:column title="可用金额" style="text-align:center" property="avlbal" headerClass="sortable" sortable="true" />
								<display:column title="VIP 标记" style="text-align:center" headerClass="sortable" sortable="true" >
									<bean:write name="displayTable" property="vipflag"/>级
								</display:column>
								<display:column title="客户ID" style="text-align:center" sortable="true">
									<a href="javascript:;" onclick="queryCustById('<bean:write name="displayTable" property="custdet_id"/>')"  ><bean:write name="displayTable" property="custdet_id"/></a>
								</display:column>
								<display:column title="查看" style="text-align:center" sortable="false">
									<a href="javascript:;" onclick="queryOne('<bean:write name="displayTable" property="id"/>')" ><img border="0" src="<%=path %>/image1/border/query.png" /></a>
								</display:column>
							</display:table>
							<!-- 用Diaplay Tag来显示 -->	
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
