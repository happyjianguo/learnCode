
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
	window.history.forward(1);
	var flag = false;
	
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
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置：批量管理 &gt; &gt; 批处理日志详细信息 </td>
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
								
									<bean:write name="bflowlogBean" property="id"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									批处理标识：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="bflowlogBean" property="batchflag"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									用户组号：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="bflowlogBean" property="dept_no"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									执行日期：
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="bflowlogBean" property="execdate"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  align="right" class="box1">
							处理结果：
								</td>
								<td align="left" class="box2">
									<logic:equal name="bflowlogBean" property="issucess"	value="0">成功&nbsp;</logic:equal>
									<logic:notEqual name="bflowlogBean" property="issucess"	value="0">失败&nbsp;</logic:notEqual>
									
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									信息描述：
								</td>
								<td align="left" class="box2">									
									<bean:write name="bflowlogBean" property="execinfo"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									添加时间：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="bflowlogBean" property="addtime"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<logic:notEmpty name="bflowlogBean" property="srcstartflagname"> 
							<tr>
								<td  align="right" class="box1">
									<bean:write name="bflowlogBean" property="srcstartflagname"/>：									
								</td>
								<td align="left" class="box2">
									
									<bean:write name="bflowlogBean" property="srcstartflag"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							</logic:notEmpty>	
							<logic:notEmpty name="bflowlogBean" property="srcendflagname">	
							<tr>
								<td  align="right" class="box1">
									<bean:write name="bflowlogBean" property="srcendflagname"/>：
									
								</td>
								<td align="left" class="box2">									
									<bean:write name="bflowlogBean" property="srcendflag"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							</logic:notEmpty>
                        <logic:notEmpty name="bflowlogBean" property="descstartflagname">		
							<tr>
								<td  align="right" class="box1">
									<bean:write name="bflowlogBean" property="descstartflagname"/>：
									
								</td>
								<td align="left" class="box2">
									
								<bean:write name="bflowlogBean" property="descstartflag"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							</logic:notEmpty>	
							<logic:notEmpty name="bflowlogBean" property="descendflagname">							
							<tr>
								<td  align="right" class="box1">
									<bean:write name="bflowlogBean" property="descendflagname"/>：
								</td>
								<td align="left" class="box2">
									
									<bean:write name="bflowlogBean" property="descendflag"/>
								</td>
							</tr>
							</logic:notEmpty>	
							<tr>
								<td  align="right" class="box1">
									批处理文件路径：
								</td>
								<td align="left" class="box2">									
									<bean:write name="bflowlogBean" property="batchfile"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									标志文件：									
								</td>
								<td align="left" class="box2">									
									<bean:write name="bflowlogBean" property="batchflagfile"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									备份文件的路径：									
								</td>
								<td align="left" class="box2">
									
									<bean:write name="bflowlogBean" property="backuppath"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>		
							<tr>
								<td  align="right" class="box1">
									产生日志的文件路径：
									
								</td>
								<td align="left" class="box2">									
									<bean:write name="bflowlogBean" property="logfilepath"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									所属用户组：
									
								</td>
								<td align="left" class="box2">
									
								<bean:write name="bflowlogBean" property="dept_no"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>						
							<tr>
								<td  align="right" class="box1">
									批处理描述：
								</td>
								<td align="left" class="box2">
									
									<bean:write name="bflowlogBean" property="demo"/>
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="80%"  class = "box1">
							<tr>
							
							
								<td height="23" align="center" class="box1">									
									&nbsp;&nbsp;&nbsp;
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
