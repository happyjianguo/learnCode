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
		var userForm = document.all("lsForm");
		userForm.action = "<%=path%>/queryLs.do?method=getLsList";
		userForm.submit();
	}

	function queryOne(id)
	{
		var url = "<%=path%>/queryLs.do?method=getLsById&id="+id+"&random=" + Math.random();
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
<shiro:lacksPermission name="cardbatch:ls:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="cardbatch:ls:view">
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
<html:form action="/queryLs?method=getLsList" method="post" styleId="lsForm">
<html:hidden property="id"/>
	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" >
		<tr>
			<td align="center" valign="top" >
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
					</td>
					<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置： 信息查询 &gt; 流水查询 </td>
					<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
				</tr>
				</table>
				<table cellpadding="0" cellspacing="0" width="100%" align="left"  >
					<tr class="serch">
						<td align="right">卡号：</td>
						<td align="left" ><html:text property="pan" maxlength="16" size="16"/></td>
						<td align="right">类型：</td>
						<td align="left">
							<html:select property="sales_type" style="width:100px;" >
								<html:option value="">－请选择－</html:option>
								<html:option value="1">－开卡－</html:option>
								<html:option value="2">－充值－</html:option>
								<html:option value="3">－赎回－</html:option>
							</html:select>
						</td>
						<%-- <td align="right">状态：</td>
						<td align="left">
							<html:select property="stxnstat" style="width:100px;" >
								<html:option value="">－请选择－</html:option>
							</html:select>
						</td> --%>
						<td align="right">售卡人员：</td>
						<td align="left" ><html:text property="verifier" maxlength="20" size="16"/></td>
						
					</tr>
					<tr class="serch">
						
						<td align="right">订单号：</td>
						<td align="left"><html:text property="father_order" maxlength="20" size="16"/></td>
						<td align="right">订单日期：</td>
						<td align="left"><html:text property="verifytime" size="16" readonly="true" onclick="WdatePicker({dateFmt:'yyyyMMdd'});"/></td>
						<td align="left"></td>
						<td height="25" align="left">
							<input type="button" class="button" onClick='return query()'  style="background-image: url(<%=path%>/image1/border/Check_button.gif)">									
						</td>
					</tr>
					<tr>
						<td colspan="7">
							<!-- 用Diaplay Tag来显示 -->
							<display:table name="lsList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/queryLs.do">
								<display:column title="类型" style="text-align:center" headerClass="sortable" sortable="true" >
									<logic:equal name="displayTable" property="sales_type" value="1">开卡</logic:equal>
									<logic:equal name="displayTable" property="sales_type" value="2">充值</logic:equal>
									<logic:equal name="displayTable" property="sales_type" value="3">赎回</logic:equal>
								</display:column>
								<display:column title="卡号" style="text-align:center" property="pan" headerClass="sortable" sortable="true" />
								<display:column title="金额" style="text-align:center" property="amttxn" headerClass="sortable" sortable="true" />
								<display:column title="订单号" style="text-align:center" property="father_order" headerClass="sortable" sortable="true" />
								<display:column title="售卡人员" style="text-align:center" property="verifier" headerClass="sortable" sortable="true" />
								<display:column title="订单日期" style="text-align:center" property="verifytime" headerClass="sortable" sortable="true" />
								<display:column title="查看" style="text-align:center" sortable="false" >
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
</shiro:hasPermission>

</html:html>
