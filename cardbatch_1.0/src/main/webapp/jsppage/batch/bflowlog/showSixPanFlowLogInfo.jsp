
<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%String path = request.getContextPath();
String panflagno=request.getAttribute("panflagno").toString();
String errMsg=request.getAttribute("errMsg").toString();
%>

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
	window.history.forward(1);
	var flag = false;
 function	addSixPanInfoAgain(){
	 window.location.href="<%=path%>/sixbflowlog.do?method=addSixPanInfoAgain&panflagno=<%=panflagno%>";
 }	
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
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置：批量管理 &gt; 特制批量发卡管理&gt; 发卡日志详细信息 </td>
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
								
									<bean:write name="sixbflowlogBean" property="id"/>
								</td>
								<td width="10%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									批处理标识：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="sixbflowlogBean" property="batchflag"/>
								</td>
								<td></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									受理号：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="sixbflowlogBean" property="panflagno"/>
								</td>
								<td></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									处理名称：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="sixbflowlogBean" property="batchname"/>
								</td>
								<td></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									执行日期：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="sixbflowlogBean" property="execdate"/>
								</td>
								<td ></td>
							</tr>
							<tr>
								<td  align="right" class="box1">
							处理结果：
								</td>
								<td align="left" class="box2">
									<logic:equal name="sixbflowlogBean" property="issucess"	value="0">成功&nbsp;</logic:equal>
									<logic:notEqual name="sixbflowlogBean" property="issucess"	value="0">失败&nbsp;<%=errMsg%></logic:notEqual>
									
								</td>
								<td align="left" class="box2" >
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									发卡文件信息：
								</td>
								<td align="left" class="box2">	
								<logic:present name="lstexecinfo">
							       <logic:iterate id="execinfon" name="lstexecinfo">
							                   <bean:write name="execinfon" /><br>
							      </logic:iterate>
							</logic:present>								
									
								</td>
								<td align="left" class="box2" >
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									添加时间：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="sixbflowlogBean" property="addtime"/>
								</td>
								<td align="left" class="box2">
									
								</td>	
							</tr>	
							<logic:notEmpty name="sixbflowlogBean" property="srcstartflagname"> 
							<tr>
								<td  align="right" class="box1">
									<bean:write name="sixbflowlogBean" property="srcstartflagname"/>：									
								</td>
								<td align="left" class="box2">
									
									<bean:write name="sixbflowlogBean" property="srcstartflag"/>
								</td>
								<td align="left" class="box2">
									
								</td>	
							</tr>
							</logic:notEmpty>	
							<logic:notEmpty name="sixbflowlogBean" property="srcendflagname">	
							<tr>
								<td  align="right" class="box1">
									<bean:write name="sixbflowlogBean" property="srcendflagname"/>：
									
								</td>
								<td align="left" class="box2">									
									<bean:write name="sixbflowlogBean" property="srcendflag"/>
								</td>
								<td align="left" class="box2">
									
								</td>	
							</tr>	
							</logic:notEmpty>
                        <logic:notEmpty name="sixbflowlogBean" property="descstartflagname">		
							<tr>
								<td  align="right" class="box1">
									<bean:write name="sixbflowlogBean" property="descstartflagname"/>：
									
								</td>
								<td align="left" class="box2">
									
								<bean:write name="sixbflowlogBean" property="descstartflag"/>
								</td>
								<td align="left" class="box2">
									
								</td>	
							</tr>	
							</logic:notEmpty>	
							<logic:notEmpty name="sixbflowlogBean" property="descendflagname">							
							<tr>
								<td  align="right" class="box1">
									<bean:write name="sixbflowlogBean" property="descendflagname"/>：
								</td>
								<td align="left" class="box2">
									
									<bean:write name="sixbflowlogBean" property="descendflag"/>
								</td>
							</tr>
							</logic:notEmpty>	
							<!-- 
							<tr>
								<td  align="right" class="box1">
									批处理文件路径：
								</td>
								<td align="left" class="box2">									
									<bean:write name="sixbflowlogBean" property="batchfile"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							 
							<tr>
								<td  align="right" class="box1">
									标志文件：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="sixbflowlogBean" property="batchflagfile"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									备份文件的路径：									
								</td>
								<td align="left" class="box2">
									
									<bean:write name="sixbflowlogBean" property="backuppath"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>		-->
							<tr>
								<td  align="right" class="box1">
									产生日志的文件路径：
									
								</td>
								<td align="left" class="box2">									
									<bean:write name="sixbflowlogBean" property="logfilepath"/>
								</td>
								<td align="left" class="box2">
									
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									所属用户组：
									
								</td>
								<td align="left" class="box2">
									
								<bean:write name="sixbflowlogBean" property="dept_no"/>
								</td>
								<td align="left" class="box2">
									
								</td>	
							</tr>						
							<tr>
								<td  align="right" class="box1">
									批处理描述：
								</td>
								<td align="left" class="box2">
									
									<bean:write name="sixbflowlogBean" property="demo"/>
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="80%"  class = "box1">
							<tr>
							<logic:notEqual name="sixbflowlogBean" property="issucess" value="0">
							    <td height="23" align="center" class="box1">									
									&nbsp;&nbsp;&nbsp;
									<input class="button" type="button"  value="手动执行" onClick="addSixPanInfoAgain();">
								</td>
						    </logic:notEqual>							
								<td height="23" align="center" class="box1">									
									&nbsp;&nbsp;&nbsp;
								<input class="button" type="button"  value="关闭" onClick="javascript:window.close()">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>		
	</body>
</center>
</html:html>
