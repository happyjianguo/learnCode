<%@ page language="java" pageEncoding="gbk" import="com.pay.util.ParamUtils"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
    String path = request.getContextPath();
    String result = ParamUtils.getParameter(request,"result","exchange");
    String info = ParamUtils.getParameter(request,"info","exchange");
    String flushdo = ParamUtils.getParameter(request,"flushdo","exchange");

    if (result == null || "".equals(result)) {
        result = ParamUtils.getStringAttribute(request,"result","exchange");
    }
    if (info == null || "".equals(info)) {
        info = ParamUtils.getStringAttribute(request,"info","exchange");
    }
    if (flushdo == null || "".equals(flushdo)) {
        flushdo = ParamUtils.getStringAttribute(request,"flushdo","exchange");
    }
%>
<bean:define id="de_path" value="<%=path%>"></bean:define>
<bean:define id="de_info" value="<%=info%>"></bean:define>
<bean:define id="de_flushdo" value="<%=flushdo%>"></bean:define>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html lang="true">
<head>
	<html:base />

	<title>结果页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="content-type" content="text/html; charset=GB2312">
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
<style type="text/css">
* {
    padding:0px;
    margin:0px;
}
html, body {
    width:99.9%;
    height:99.9%;
    border:solid 0px;
}
 
</style>
</head>
<script language="javascript">
     function loadPage(){
     	var path = '<bean:write name="de_path"/>';
     	var flushdo = '<bean:write name="de_flushdo"/>';
     	flushdo = path + flushdo;		
      if (flushdo == path + "closewindow"){
			window.close();
	  }else if(flushdo == (path + "javascript:history.go(-1)")){
		  window.location.href=flushdo;
	  }else{
		  window.location.href=flushdo;
      }
     }
</script>
<body bgcolor="#EFF7F9" >
	<div style="height:100%">
	<table style="width:100%; height:100%"  border="0" align="center"  cellpadding="0" cellspacing="0">
		<tr>
			<td style="width:100%; height:100%" valign="middle">
				<table width="350" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td colspan="3" background="<%=path%>/images/result/messges_top_01.gif" width="350"
								height="21">
							
						</td>
					</tr>
					<tr>
						<td align="right" background="<%=path%>/images/result/messges_left_01.gif"  width="23"
								height="107">
							
						</td>
						<td align="center" width="303" align="center">
							<span class="big">结 果 信 息</span>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="10%" height="35" align="right">
										<%
										    if ("0".equals(result)) {
										%>
										<img src="<%=path%>/images/result/messges_02.gif" width="30"
											height="31" align="right">
										<%
										    } else {
										%>
										<img src="<%=path%>/images/result/messges_03.gif" width="30"
											height="31" align="right">
										<%
										    }
										%>
									</td>
									<%
									    if ("0".equals(result)) {
									%>
									<td width="80%" height="40" align="center">
										<font size="2" color="green"><bean:write name="de_info"/></font>
									</td>
									<%
									    } else {
									%>
									<td width="80%" height="40" align="center">
										<font size="2" color="red"><bean:write name="de_info"/></font>
									</td>
									<%
									    }
									%>
									<td width="10%" height="40" align="center">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td height="25" colspan="3" align="center">
										<input name="Submit2" type="button" class="button" value="返回"
											style="cursor: hand" onclick="loadPage();">
									</td>
								</tr>
							</table>
						</td>
						<td align="left" background="<%=path%>/images/result/messges_right_01.gif" width="23" height="107">
							
						</td>
					</tr>
					<tr>
						<td colspan="3" background = "<%=path%>/images/result/messges_bottome_01.gif" width="350" height="18" >
							
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</div>
</body>
<script type="text/javascript">
	document.all("Submit2").focus();
</script>
</html:html>