
<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%String path = request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>详情页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	<script type="text/javascript" src="<%=path%>/js/eposcc.js"></script>
	<script type="text/javascript" src="<%=path%>/js/textselect.js"></script>
</head>
<script language="javascript">
	
</script>
<center>
	<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
		
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="99%" >
				<tr>
					<td align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
				    <tr>
						<td width="28" height="10"></td>
					</tr>
					<tr>
						<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
						
						</td>
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置：日志管理</td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
					</table>
						<table border="0" cellpadding="0" cellspacing="0" width="80%"  class = "box1">
							<tr>
								<td width="20%" align="right" class="box1">
									编号：
								</td>
								<td width="50%" align="left" class="box2">
									<bean:write name="salestlogBean" property="id"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td width="20%" align="right" class="box1">
									流水表（TLOG）流水ID：
								</td>
								<td width="50%" align="left" class="box2">
								
									<bean:write name="salestlogBean" property="tlog_id"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									流水表（TLOG）原流水ID：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="salestlogBean" property="org_tlog_id"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									卡号
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="salestlogBean" property="pan"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									交易金额：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="salestlogBean" property="amttxn"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									父订单号：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="salestlogBean" property="father_order"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  align="right" class="box1">
									子订单号：
								</td>
								<td align="left" class="box2">
									<bean:write name="salestlogBean" property="children_order"/>
								</td>
								<td width="30%"></td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									原父订单号：
								</td>
								<td align="left" class="box2">									
									<bean:write name="salestlogBean" property="org_f_order"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  align="right" class="box1">
									原子订单号：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="salestlogBean" property="org_c_order"/>
								</td>
								<td width="30%"></td>
							</tr>	
							<tr>
								<td  width="20%" align="right" class="box1">
									操作员：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="salestlogBean" property="verifier"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  align="right" class="box1">
									操作时间：
								</td>
								<td align="left" class="box2">
									<bean:write name="salestlogBean" property="verifytime"/>
								</td>
								<td width="30%"></td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									交易状态：
								</td>
								<td class=box2 height="28">
									<logic:equal name="salestlogBean" property="stxnstat" value="0">正常</logic:equal>
									<logic:equal name="salestlogBean" property="stxnstat" value="8">已撤销</logic:equal>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  align="right" class="box1">
									（非）实时：									
								</td>
								<td align="left" class="box2">									
									<logic:equal name="salestlogBean" property="sub_txncode" value="99">实时</logic:equal>
									<logic:equal name="salestlogBean" property="sub_txncode" value="98">非实时</logic:equal>
								</td>
								<td width="30%"></td>	
							</tr>
							
							
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="80%"  class = "box1">
							<tr>
								<td height="23" align="center" class="box1">									
									<input class="button" type="button"  value="关闭" onClick="history.go(-1)">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>		
	</body>
</center>
</html:html>
