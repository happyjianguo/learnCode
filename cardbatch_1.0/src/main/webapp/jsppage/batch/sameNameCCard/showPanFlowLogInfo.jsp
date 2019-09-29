
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
 function	addPanInfoAgain(){
	 var panflagno= document.bflowlogForm.panflagno.value;	
	 window.location.href="<%=path%>/snCCard.do?method=addPanInfoAgain&panflagno="+panflagno;
 }
</script>
<center>
	<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
			<html:form styleId="bflowlogForm" action="/snCCard.do?method=addPanInfo" method="post" >
	
			<input type="hidden" name="panflagno" value="${panflagno}">
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
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置：批量管理 &gt; 同号换卡 </td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
					</table>
						<table border="0" cellpadding="0" cellspacing="0" width="80%"  class = "box1">
							<tr>
								<td  align="right" class="box1">
									<font color="red">受理号：</font>
									
								</td>
								<td align="left" class="box2">
									<html:text property="panflagno" disabled="true"></html:text>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									<font color="red">卡号：</font>									
								</td>
								<td align="left" class="box2">
									<html:text property="batchflag" disabled="true"></html:text>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>								
							<tr id="trvalid">
								<td  align="right" class="box1">
									<font color="red">福卡有效期：</font>
									
								</td>
								<td align="left" class="box2">
									<html:text property="srcstartflag" disabled="true"></html:text>
								</td>
								<td align="left" class="box2" width="40%">
									<font color="red"></font>
								</td>	
							</tr>	
							<tr id="trvalid">
								<td  align="right" class="box1">
									<font color="red">执行时间：</font>									
								</td>
								<td align="left" class="box2">
									<html:text property="execdate" disabled="true"></html:text>									
								</td>
								<td align="left" class="box2" width="40%">
									<font color="red"></font>
								</td>	
							</tr>	
							<tr id="trvalid">
								<td  align="right" class="box1">
									<font color="red">状态：</font>									
								</td>
								<td align="left" class="box2">
								    <c:if test="${bflowlogForm.issucess=='00'}">
								    	00-制卡成功
								    </c:if>
								    <c:if test="${bflowlogForm.issucess=='01'}">
								    	01-制卡成功
								    </c:if>
								    <c:if test="${bflowlogForm.issucess=='02'}">
								    	02-卡片信息不存在
								    </c:if>
								    <c:if test="${bflowlogForm.issucess=='03'}">
								    	03-卡片状态异常
								    </c:if>								    								    								    
								</td>
								<td align="left" class="box2" width="40%">
									<font color="red"></font>
								</td>	
							</tr>							
													
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="80%"  class = "box1">
							<tr>
							    <c:if test="${bflowlogForm.issucess!='01'}">
								    <td height="23" align="center" class="box1">									
										&nbsp;&nbsp;&nbsp;
										<input class="button" type="button"  value="手动执行" onClick="addPanInfoAgain();">
									</td>						    
							    </c:if>
								<td height="23" align="center" class="box1">									
									&nbsp;&nbsp;&nbsp;
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
