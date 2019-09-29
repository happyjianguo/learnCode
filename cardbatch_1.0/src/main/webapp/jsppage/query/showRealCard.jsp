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
		<html:form styleId="cardForm" action="/queryCard.do">
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
									<bean:write name="cardForm" property="branch_id"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">卡产品：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="crdproduct_id"/>(<bean:write name="cardForm" property="crdproduct_name"/>)&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">卡分类：</font>
								</td>
								<td align="left" class="box2">
									<logic:equal name="cardForm" property="classid" value="1">借记卡</logic:equal>
									<logic:equal name="cardForm" property="classid" value="2">贷记卡</logic:equal>
									<logic:equal name="cardForm" property="classid" value="3">记账卡</logic:equal>
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">客户ID：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="custdet_id"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">默认账户ID：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="accdet_id"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">卡号：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="pan"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">隐藏卡号：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="pan_display"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">卡片序列号：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="seqno"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">卡片有效期：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="expdate"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">卡片激活日期：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="effdate"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">卡片状态码：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="statcode"/>(<bean:write name="cardForm" property="statcode_name"/>)&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">制卡批次：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="batch"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">语言代码：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="lang"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">持卡人称谓：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="title"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">持卡人名称：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="firstname"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">持卡人姓氏：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="lastname"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">是否执行翻新：</font>
								</td>
								<td align="left" class="box2">
									<logic:equal name="cardForm" property="renew" value="0">不执行</logic:equal>
									<logic:equal name="cardForm" property="renew" value="1">执行</logic:equal>
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">公司卡标记：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="corp"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">持卡人生日：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="date_birth"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">卡面凸印上的姓名：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="embossname"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">附属卡的数量：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="additionalno"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">制卡日期：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="date_created"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">卡片的唯一性标识符：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="id"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">周期长度（天）：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="cyclen"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">周期开始时间：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="cycbegin"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">每个周期的授权金额：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="amtauth"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">本周期剩余金额：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="amtrem"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">锁定的金额（授权而未清算）：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="blkamt"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">可用金额：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="avlbal"/>&nbsp;
								</td>
								<td align="right" class="box2">
									&nbsp;
								</td>
								<td align="left" class="box2">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">姓名：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="cust_name"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">证件号：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="id_num"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">电话：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="cell_phone"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">邮件：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="cardForm" property="mail_box"/>&nbsp;
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
								

