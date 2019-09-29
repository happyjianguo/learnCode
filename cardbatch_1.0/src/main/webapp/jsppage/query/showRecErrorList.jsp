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
		var userForm = document.all("recErrorForm");
		userForm.action = "<%=path%>/queryRecError.do?method=getRecErrorList";
		userForm.submit();
	}
	function queryOne(id){
		//		window.showModalDialog("<%=path%>/queryRecError.do?method=getAcctById&Id="+id, "_blank", 'dialogWidth=800px;dialogHeight=440px;scroll=yes;resizable=no;status=no;center=yes;');
		var userForm = document.all("recErrorForm");
		document.forms[0].id.value=id;
		userForm.action = "<%=path%>/queryRecError.do?method=getAcctById";
		userForm.submit();
	}
	function queryCustById(id){
		//		window.showModalDialog("<%=path%>/queryCust.do?method=getCustById&Id="+id, "_blank", 'dialogWidth=800px;dialogHeight=440px;scroll=yes;resizable=no;status=no;center=yes;');
		var userForm = document.all("recErrorForm");
		document.forms[0].id.value=id;
		userForm.action = "<%=path%>/queryCust.do?method=getCustById&id="+id;
		userForm.submit();
	}
</script>
<shiro:lacksPermission name="cardbatch:recError:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="cardbatch:recError:view">
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
<html:form action="/queryRecError?method=getRecErrorList" method="post" styleId="recErrorForm">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" >
		<tr>
			<td align="center" valign="top" >
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
					</td>
					<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置： 信息查询 &gt; 对账查询 </td>
					<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
				</tr>
				</table>
				<table cellpadding="0" cellspacing="0" width="100%" align="left"  >
					<tr class="serch">
						<td align="right">父订单：</td>
						<td align="left" ><html:text property="father_order" maxlength="20" size="16"/></td>
						<td align="right">子订单：</td>
						<td align="left" ><html:text property="children_order" maxlength="20" size="16"/></td>
						<td align="right">对账日期：</td>
						<td align="left" ><html:text property="acct_period" maxlength="20" size="16" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyyMMdd'});"/></td>
						<td align="right">错误代码：</td>
						<td align="left">
							<html:select property="errcode" style="width:100px;" >
								<html:option value="">－请选择－</html:option>
								<html:option value="0">数据对比不同</html:option>
								<html:option value="1">平台有|销售平台无</html:option>
								<html:option value="2">平台无|销售平台有</html:option>
								<html:option value="3">其他错误</html:option>
							</html:select>
						</td>
						<td height="25" align="left">
							<input type="button" class="button" onClick='return query()'  style="background-image: url(<%=path%>/image1/border/Check_button.gif)">									
						</td>
					</tr>
					<tr>
						<td colspan="9">
							<!-- 用Diaplay Tag来显示 -->
							<display:table name="recErrorList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/queryRecError.do">
								<display:column title="表名" style="text-align:center" property="table_name" headerClass="sortable" sortable="true" />
								<display:column title="父订单" style="text-align:center" property="father_order" headerClass="sortable" sortable="true" />
								<display:column title="子订单" style="text-align:center" property="children_order" headerClass="sortable" sortable="true" />
								<display:column title="对账日期" style="text-align:center" property="acct_period" headerClass="sortable" sortable="true" />
								<display:column title="错误代码" style="text-align:center" headerClass="sortable" sortable="true" >
									<logic:equal name="displayTable" property="errcode" value="0">数据对比不同</logic:equal>
									<logic:equal name="displayTable" property="errcode" value="1">平台有|销售平台无</logic:equal>
									<logic:equal name="displayTable" property="errcode" value="2">平台无|销售平台有</logic:equal>
									<logic:equal name="displayTable" property="errcode" value="3">其他错误</logic:equal>
								</display:column>
								<display:column title="错误描述" style="text-align:center" >
									<span title="<bean:write name="displayTable" property="errdesc"/>" onclick="javascript:alert('<bean:write name="displayTable" property="errdesc"/>');">详细</span>
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
