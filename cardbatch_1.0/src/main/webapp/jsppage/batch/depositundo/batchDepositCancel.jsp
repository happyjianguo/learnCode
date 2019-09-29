
<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
	String path = request.getContextPath();
	String pcid=request.getAttribute("id")==null?"":request.getAttribute("id").toString();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>批量充值撤销页面</title>

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
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
</head>
<script language="javascript">
	window.history.forward(1);
	//去左右空格;
	function trim(s){
	    return s.replace(/(^\s*)|(\s*$)/g, "");
	}
	function isNull(data){ 
		var datas = trim(data);
		return (datas == "" || datas == undefined || datas == null) ? true : false; 
	}
	function depositcancel(){
		var pcid=document.getElementById("pcid").value; 
		if(isNull(pcid)){
			alert("批次编号不能为空！");
			return false;
		}
		var cardnum=document.getElementById("cardnum").value; 
		if(isNull(cardnum)){
			alert("卡张数不能为空！");
			return false;
		}
		var tmoney=document.getElementById("tmoney").value;  
		if(isNull(tmoney)){
			alert("金额不能为空！");
			return false;
		}
		/* else{
			var matches = /^[0-9]+/\(.[0-9]{2})?$/;
			if(matches.test(tmoney)){
				alert("ok");
			}else{
				alert("false")
			}
		} */
		$("#btCX").attr("disabled","true");
		window.location.href="<%=path %>/depositUndo.do?method=Deposit&id="+pcid+"&cardnum="+cardnum+"&tmoney="+tmoney+"&msgtype=0069";
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
							<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置：批量管理 &gt; &gt; 批量充值撤销 &gt; &gt;操作</td>
							<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
						</tr>
					 	<tr>
							<td width="28" height="5" colspan="3"></td>
						</tr>
					</table>
					<!-- <table border="0" cellpadding="0" cellspacing="0" width="80%"  class = "box1"> -->
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								批次编号：
							</td>
							<td align="left" class="box2">
								<input type="text" name="pcid" id="pcid" size="40" value="<bean:write name="depositBean" property="id"/>"  readonly="readonly"/>
								<font color="red">*</font>
							</td>
						</tr> 
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								卡张数：
							</td>
							<td align="left" class="box2">
								<input type="text" id="cardnum" name="cardnum"  onkeyup="this.value=this.value.replace(/\D/g,'')" />
								<font color="red">*</font>&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								金额：
							</td>
							<td align="left" class="box2">
								<input type="text" id="tmoney" name="tmoney"/>
								<font color="red">*</font>&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%"  class = "box1">
						<tr>
							<td height="23" align="center" class="box1">									
								&nbsp;&nbsp;&nbsp;
								<input id="btCX"  class="button" type="button"  value="撤销" onClick="return depositcancel()">
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
	</body>
</center>
</html:html>
