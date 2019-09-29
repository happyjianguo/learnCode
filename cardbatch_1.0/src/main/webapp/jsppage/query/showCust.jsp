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
		<html:form styleId="custForm" action="/queryCust.do">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="99%" >
				<tr>
					<td align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
					    	<tr><td width="28" height="10"></td></tr>
							<tr>
								<td align="left" width="28" height="28" style="background:url(<%=path%>/image1/Navigation_bar/left1.gif) "></td>
								<td height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置： 信息查询 &gt; 卡详情 </td>
								<td width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
							</tr>
							<tr>
								<td width="28" height="5" colspan="3"></td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center" style="background-color:#D2E8F3">
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">机构代码：</font>
								</td>
								<td align="left" class="box3">
									<bean:write name="custForm" property="inst_id"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">顾客编号：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="custcode"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">称谓：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="title"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">名称：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="firstname"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">姓氏：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="lastname"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">顾客类型：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="typeid"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">地址行0：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="addrl0"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">地址行1：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="addrl1"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">地址行2：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="addrl2"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">地址行3：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="addrl3"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">所在城市：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="home_city"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">联系电话：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="home_tel"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">工作地址行1：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="work_addr1"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">工作地址行2：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="work_addr2"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">工作地址行3：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="work_addr3"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">：</font>
								</td>
								<td align="left" class="box2">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">工作城市：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="work_city"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">工作电话：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="work_tel"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">邮件寄送方式：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="stmt_code"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">工作地址邮编：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="work_postcode"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">家庭地址邮编：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="postcode"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">邮箱：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="po_box"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">婚姻状况：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="married"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">性别：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="sex"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">身份证号：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="id_number"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">数据建立的时间：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="date_accepted"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">出生日期：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="date_birth"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">电子邮箱：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="email"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">移动电话：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="mob_tel"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">传真：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="fax"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">唯一标识：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="id"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">备忘信息：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="memo"/>&nbsp;
								</td>
							</tr>
						</table>
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
		</html:form>
	</body>
</center>
</html:html>
								

