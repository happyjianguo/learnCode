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

	<title>�̻��޸�ҳ��</title>
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
								<td height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;��ǰλ�ã� ��Ϣ��ѯ &gt; �˻����� </td>
								<td width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
							</tr>
							<tr>
								<td width="28" height="5" colspan="3"></td>
							</tr>
						</table>
						�ʻ��ܽ�<font color="red"><bean:write name="sum"/></font><hr/>
					<logic:present name="acctList">
							<logic:iterate id="acctBean" name="acctList">
						<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center" style="background-color:#D2E8F3">
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">�˻��ţ�</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="accno"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">�������룺</font>
								</td>
								<td align="left" class="box3">
									<bean:write name="acctBean" property="inst_id"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">�˻���</font>
								</td>
								<td align="left" class="box2">
									<logic:equal name="acctBean" property="classid" value="1">����˻�</logic:equal>
									<logic:equal name="acctBean" property="classid" value="2">�����˻�</logic:equal>
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">�˻����ͣ�</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="typecode_name"/>(<bean:write name="acctBean" property="typecode"/>)&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">���Ҵ��룺</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="currcode"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">״̬�룺</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="statcode_name"/>(<bean:write name="acctBean" property="statcode"/>)&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">VIP ��ǣ�</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="vipflag"/>��
								</td>
								<td align="right" class="box2">
									<font color="">�����Ľ�</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="blkamt"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">���ý�</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="avlbal"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">������Ľ�</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="clrbal"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">δ����Ľ�</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="unclrbal"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">���ö�ȣ�</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="credit_limit"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">�˿�ID��</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="acctBean" property="custdet_id"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">�˻���Ψһ�Ա�ʶ����</font>
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
									<input class="button" type="button"  value="�ر�" onClick="javascript:window.close()">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
	</body>
</center>
</html:html>
								

