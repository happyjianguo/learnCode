
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
	<script type="text/javascript" src="<%=path%>/js/calendar.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	<script type="text/javascript" src="<%=path%>/js/eposcc.js"></script>
	<script type="text/javascript" src="<%=path%>/js/textselect.js"></script>
</head>
<script language="javascript">
window.history.forward(1);
var flag = false;
function commit(){
		var iid=document.getElementsByName("iid")[0].value;
		if(trim(iid) == ""){
			alert("请输入卡bin");
			return false;
		}	
		document.iidPinChkForm.submit();
	}
</script>
<center>
	<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
		<html:form styleId="iidPinChkForm" action="/iidPinChk.do?method=addIidPinChk" method="post" >
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
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置：制卡管理 &gt; 新增发卡密码 </td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
					</table>
						<table border="0" cellpadding="0" cellspacing="0" width="700"  class = "box1">							
							<tr>
								<td  align="right" class="box1">
									<font color="red">卡bin：</font>
								</td>
								<td align="left" class="box2">
									<html:text property="iid" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									<font color="red">线上密码标识：</font>
									
								</td>
								<td align="left" class="box2">
									<html:select property="ivrpinfalg" >
									<html:option value="1">1-有</html:option>
									<%-- <html:option value="0">0-无</html:option> --%>
								</html:select>
								</td>
								<td align="left" class="box2" width="40%">
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									<font color="red">线下密码标识：</font>
									
								</td>
								<td align="left" class="box2">
								<html:select property="pospinfalg" >
									<html:option value="0">0-无</html:option>
									<html:option value="1">1-有</html:option>
								</html:select>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="700"  class = "box1">
							<tr>
							    <td height="23" align="center" class="box1">									
									&nbsp;&nbsp;&nbsp;
									<input class="button" type="button" onClick="commit();" value="保存">
								</td>
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
