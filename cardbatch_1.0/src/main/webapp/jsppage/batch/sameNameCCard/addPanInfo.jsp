
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
	<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
	
</head>
<script language="javascript">
	window.history.forward(1);
	var flag = false;
function commit(){
		if(!checkelement("bflowlogForm", "yeardate", "福卡有效期")){
			return false;
		}else{
			var addtime   =   document.bflowlogForm.addtime.value;
			var yeardate   =   document.bflowlogForm.yeardate.value;			
			if(addtime==yeardate){
				alert("福卡有效期不能和该卡初始时间相同");
				return false;
			}			
		}
		document.bflowlogForm.submit();
	}
function judeDt(){
	if(!checkelement("bflowlogForm", "yeardate", "福卡有效期")){
		document.bflowlogForm.yeardate.focus();		
		return false;
	}else{
		var addtime   =   document.bflowlogForm.addtime.value;
		var yeardate   =   document.bflowlogForm.yeardate.value;
		if(yeardate.substr(0,6)-addtime.substr(0,6)<=0){
			alert("福卡有效期必须在该卡原有效期("+addtime+")月份之后");
			document.bflowlogForm.yeardate.value='';
			document.bflowlogForm.yeardate.focus();
			return false;
		}
			
	}
}
</script>
<center>
	<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
		<html:form styleId="bflowlogForm" action="/snCCard.do?method=addPanInfo" method="post" >
			<html:hidden property="addtime" value="${bflowlogForm.addtime}"/>		
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
						<table border="0" cellpadding="0" cellspacing="0" width="700"  class = "box1">							
							<tr>
								<td  align="right" class="box1">
									<font color="red">受理号：</font>
									
								</td>
								<td align="left" class="box2">
									<html:text property="panflagno" readonly="true"></html:text>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									<font color="red">卡号</font>									
								</td>
								<td align="left" class="box2">
									<html:text property="batchflag" readonly="true"></html:text>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr><%--
							<tr id="trvalid">
								<td  align="right" class="box1">
									<font color="red">初始时间：</font>
									
								</td>
								<td align="left" class="box2">
									<html:text property="addtime" maxlength="10" onclick="WdatePicker({dateFmt:'yyyyMMdd'});" readonly="true"></html:text>
								</td>
								<td align="left" class="box2" width="40%">
									<font color="red"></font>
								</td>	
							</tr>								
							--%><tr id="trvalid">
								<td  align="right" class="box1">
									<font color="red">福卡有效期：</font>
									
								</td>
								<td align="left" class="box2">
									<html:text property="yeardate" maxlength="10" onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true});" onchange="judeDt();"></html:text>
								</td>
								<td align="left" class="box2" width="40%">
									<font color="red"></font>
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
