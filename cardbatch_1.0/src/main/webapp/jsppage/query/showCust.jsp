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
		<html:form styleId="custForm" action="/queryCust.do">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="99%" >
				<tr>
					<td align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
					    	<tr><td width="28" height="10"></td></tr>
							<tr>
								<td align="left" width="28" height="28" style="background:url(<%=path%>/image1/Navigation_bar/left1.gif) "></td>
								<td height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;��ǰλ�ã� ��Ϣ��ѯ &gt; ������ </td>
								<td width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
							</tr>
							<tr>
								<td width="28" height="5" colspan="3"></td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center" style="background-color:#D2E8F3">
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">�������룺</font>
								</td>
								<td align="left" class="box3">
									<bean:write name="custForm" property="inst_id"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">�˿ͱ�ţ�</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="custcode"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">��ν��</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="title"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">���ƣ�</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="firstname"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">���ϣ�</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="lastname"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">�˿����ͣ�</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="typeid"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">��ַ��0��</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="addrl0"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">��ַ��1��</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="addrl1"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">��ַ��2��</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="addrl2"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">��ַ��3��</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="addrl3"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">���ڳ��У�</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="home_city"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">��ϵ�绰��</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="home_tel"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">������ַ��1��</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="work_addr1"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">������ַ��2��</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="work_addr2"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">������ַ��3��</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="work_addr3"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">��</font>
								</td>
								<td align="left" class="box2">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">�������У�</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="work_city"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">�����绰��</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="work_tel"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">�ʼ����ͷ�ʽ��</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="stmt_code"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">������ַ�ʱࣺ</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="work_postcode"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">��ͥ��ַ�ʱࣺ</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="postcode"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">���䣺</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="po_box"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">����״����</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="married"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">�Ա�</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="sex"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">���֤�ţ�</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="id_number"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="#FF0000">���ݽ�����ʱ�䣺</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="date_accepted"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">�������ڣ�</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="date_birth"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">�������䣺</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="email"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="#FF0000">�ƶ��绰��</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="mob_tel"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">���棺</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="fax"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td align="right" class="box2">
									<font color="">Ψһ��ʶ��</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="id"/>&nbsp;
								</td>
								<td align="right" class="box2">
									<font color="">������Ϣ��</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="custForm" property="memo"/>&nbsp;
								</td>
							</tr>
						</table>
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
		</html:form>
	</body>
</center>
</html:html>
								

