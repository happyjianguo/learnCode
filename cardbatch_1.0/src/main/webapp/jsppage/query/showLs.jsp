<%@ page language="java" pageEncoding="gbk" %>
<%@include file="/jsppage/common/checkSession.jsp"  %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
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
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
	<script type="text/javascript" src="<%=path%>/js/textselect.js"></script>
	<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
</head>

<script language="javascript">
	
</script>
<center>
	<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0" >
		<html:form styleId="lsForm" action="/queryLs.do">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="99%" >
				<tr>
					<td align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
					    	<tr><td width="28" height="10"></td></tr>
							<tr>
								<td align="left" width="28" height="28" style="background:url(<%=path%>/image1/Navigation_bar/left1.gif) "></td>
								<td height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置： 信息查询 &gt; 流水详情 </td>
								<td width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
							</tr>
							<tr>
								<td width="28" height="5" colspan="3"></td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center" style="background-color:#D2E8F3">
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">类型：</font>
								</td>
								<td align="left" class="box3">
									<bean:write name="lsForm" property="sales_type"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">卡产品：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="lsForm" property="pan"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">id：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="lsForm" property="id"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">tlog_id：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="lsForm" property="tlog_id"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">金额：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="lsForm" property="amttxn"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">金额：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="lsForm" property="amttxn"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">父订单号：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="lsForm" property="father_order"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">子订单号：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="lsForm" property="children_order"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">售卡人员：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="lsForm" property="verifier"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">订单日期：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="lsForm" property="verifytime"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">售卡点：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="lsForm" property="sales_point"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">地区：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="lsForm" property="area"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">卡状态：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="lsForm" property="stxnstat"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">订单状态：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="lsForm" property="batch_stat"/>&nbsp;
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0"  width="100%" align="center" style="background-color:#D2E8F3">
							<tr>
								<td height="23" align="center" class="box2">
									<input class="button" type="button"  value="关闭" onClick="javascript:window.close()">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</center>
</html:html>
								

