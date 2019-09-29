
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
	//window.history.forward(1);
	function commit(){
		if (!checkelement("makeCardForm", "batch", "批次号")) {
			document.makeCardForm.batch.focus();
			return false;
		}
		document.makeCardForm.submit();
	}
</script>
<center>
	<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
		<html:form styleId="makeCardForm" action="/makeCard.do?method=addPanInfo" method="post" >
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
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置：批量管理 &gt; 钱包卡制卡 </td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
					</table>
						<table border="0" cellpadding="0" cellspacing="0" width="700"  class = "box1">							
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								批次号
							</td>
							<td align="left" class="box2">
								<logic:present name="makeCardList">
									<select name="batch" id="batch">
										<option value="">－请选择－</option>
										<c:forEach items="${makeCardList}" var="item">
											<option value="${item.batch }">${item.batch }</option>
										</c:forEach>
									</select>
								</logic:present>
							</td>
						</tr>	
							
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="700"  class = "box1">
							<tr>
								<logic:present name="makeCardList">
								    <td height="23" align="center" class="box1">									
										&nbsp;&nbsp;&nbsp;
										<input class="button" type="button" onClick="commit();" value="保存">
									</td>
							    </logic:present>							
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
