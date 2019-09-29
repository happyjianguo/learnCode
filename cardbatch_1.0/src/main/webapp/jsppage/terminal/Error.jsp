<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Error.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  
    <!-- <h2>You are Wrong!</h2> -->
    <body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0" bgproperties="fixed">
	<table id="tab" width="100%" height="100%" border="0" align="center" valign="middle" cellpadding="0" cellspacing="0" >
		<tr>
			<td>
				<table width="350" border="0" cellspacing="0" cellpadding="0"
					align="center" valign="middle">
					<tr>
						<td colspan="3" width="350" height="21" background="<%=path%>/images/result/messges_top_01.gif">&nbsp;
							
						</td>
					</tr>
					<tr>
						<td width="23" height="107" align="right"background="<%=path%>/images/result/messges_left_01.gif">
							
						</td>
						<td align="center" width="303" align="center" height="107">
							<span class="big">结 果 信 息</span>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								
									
									
									
									
								<tr>
									<td height="25" colspan="5" align="center">
										<input name="Submit2" type="button" class="button" value="返回"
											style="cursor: hand" onclick="loadPage();">
									</td>
								</tr>
							</table>
						</td>
						<td align="left" width="23" height="107" background="<%=path%>/images/result/messges_right_01.gif">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3" background="<%=path%>/images/result/messges_bottome_01.gif" width="350" height="18">
								
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
<
</body>

    