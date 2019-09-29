<%@ page language="java" pageEncoding="gbk" %>
<%@include file="/jsppage/common/checkSession.jsp"  %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%String path = request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>商户修改页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
	<script type="text/javascript" src="<%=path%>/js/textselect.js"></script>
	<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
</head>

<script language="javascript">
	
</script>
<center>
	<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0" >
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="99%" >
				<tr>
					<td align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
					    	<tr><td width="28" height="10"></td></tr>
							<tr>
								<td align="left" width="28" height="28" style="background:url(<%=path%>/image1/Navigation_bar/left1.gif) "></td>
								<td height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置： 信息查询 &gt; 账户详情 </td>
								<td width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
							</tr>
							<tr>
								<td width="28" height="5" colspan="3"></td>
							</tr>
						</table>
						帐户总金额：<font color="red"><bean:write name="sum"/></font><hr/>
					<logic:present name="acctList">
							<logic:iterate id="acctBean" name="acctList">
						<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center" style="background-color:#D2E8F3">
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">账户号：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="accno"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">机构代码：</font>
								</td>
								<td align="left" class="box3">
									<bean:write name="acctBean" property="inst_id"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">账户：</font>
								</td>
								<td align="left" class="box2">
									<logic:equal name="acctBean" property="classid" value="1">借记账户</logic:equal>
									<logic:equal name="acctBean" property="classid" value="2">记账账户</logic:equal>
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">账户类型：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="typecode_name"/>(<bean:write name="acctBean" property="typecode"/>)&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">货币代码：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="currcode"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">状态码：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="statcode_name"/>(<bean:write name="acctBean" property="statcode"/>)&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">VIP 标记：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="vipflag"/>级
								</td>
								<td align="right" class="box2">
									<font color="">锁定的金额：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="blkamt"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">可用金额：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="avlbal"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">已清算的金额：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="clrbal"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">未清算的金额：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="unclrbal"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">信用额度：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="credit_limit"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">顾客ID：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="custdet_id"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">账户的唯一性标识符：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="id"/>&nbsp;
								</td>
							</tr>
						</table>
							<hr/>	
							</logic:iterate>						
					</logic:present>
						<table border="0" cellpadding="0" cellspacing="0"  width="100%" align="center" style="background-color:#D2E8F3">
							<tr>
								<td height="23" align="center" class="box2">
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
								

