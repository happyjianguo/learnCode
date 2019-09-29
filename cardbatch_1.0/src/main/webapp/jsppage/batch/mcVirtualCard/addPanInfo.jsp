
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
		var flagnum   =   document.bflowlogForm.execinfo.value;
// 		var obj = document.getElementById("batchflag"); 
// 		var index = obj.selectedIndex;
// 		var binvalue = obj.options[index].value;
		var binvalue=document.getElementsByName("batchflag")[0].value;

		if(trim(binvalue) == ""){
			alert("请选择福卡卡bin");
			return false;
		}
		if(!checkelement("bflowlogForm", "srcstartflag", "万事达卡卡bin")){
			return false;
		}	
		if(isnumberonly('bflowlogForm','execinfo','发卡数')==false)return false;
		if(flagnum >1000){
           alert("发卡数不能超过1000");			
			return false;
		}

		var needFlag=document.bflowlogForm.datasource.value;
		if(needFlag=='1'){
			if(!checkelement("bflowlogForm", "yeardate", "福卡专属卡有效期")){
				return false;
			}
		}	

		document.bflowlogForm.submit();
	}
function checkNeedDt() {
	var obj = document.getElementById("batchflag"); 
	var index = obj.selectedIndex;
	var binvalue = obj.options[index].value;
	var needDt=binvalue.substr(binvalue.indexOf("#")+1,binvalue.length);
	
	var trvalid=document.getElementById("trvalid"); 
	var yeardate=document.getElementById("yeardate");
		
	if(needDt=="1"){
		trvalid.style.display="block";
		//yeardate.value="";
		document.bflowlogForm.datasource.value="1";		
	}else{
		trvalid.style.display="none";
		//yeardate.value="";
		document.bflowlogForm.datasource.value="0";	
	}
	
}
</script>
<center>
	<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
		<html:form styleId="bflowlogForm" action="/mcVirtualCard.do?method=addPanInfo" method="post" >
			<html:hidden property="datasource" value="0"/>
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
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置：批量管理 &gt; 生成同福卡绑定的虚拟卡管理&gt; 发卡 </td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
					</table>
						<table border="0" cellpadding="0" cellspacing="0" width="700"  class = "box1">							
							<tr>
								<td  align="right" class="box1">
									<font color="red">福卡卡bin：</font>
									
								</td>
								<td align="left" class="box2">
									<select id="batchflag" name="batchflag" onchange="getIidMapStrByIid();checkNeedDt();">
										<option value="">－请选择－</option>
										<c:forEach items="${kabinList}" var="item">
										<option value="${item.batchflag }">${item.batchname }</option>
									</c:forEach>
										
									</select>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									<font color="red">万事达卡卡bin：</font>									
								</td>
								<td align="left" class="box2">
									<html:text property="srcstartflag" maxlength="10" readonly="true"></html:text>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									<font color="red">发卡张数：</font>
									
								</td>
								<td align="left" class="box2">
									<html:text property="execinfo" maxlength="10" ></html:text>
								</td>
								<td align="left" class="box2" width="40%">
									<font color="red">最多只能输入1000：</font>
									<html:hidden property="flagnum" value="0"/>
								</td>	
							</tr>							
							<tr id="trvalid">
								<td  align="right" class="box1">
									<font color="red">福卡有效期：</font>									
								</td>
								<td align="left" class="box2">
									<html:text property="yeardate" maxlength="10" onclick="WdatePicker({dateFmt:'yyyyMMdd'});"></html:text>
								</td>
								<td align="left" class="box2" width="40%">
									<font color="red"></font>
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									<font color="red">虚拟卡有效期：</font>									
								</td>
								<td align="left" class="box2">
									<html:text property="addtime" maxlength="10" onclick="WdatePicker({dateFmt:'yyyyMMdd'});"></html:text>
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
