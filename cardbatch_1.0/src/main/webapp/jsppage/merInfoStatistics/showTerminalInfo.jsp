<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html lang="true">
<head>
	<html:base />

	<title>�̻���Ϣͳ��-�ն���Ϣ�鿴</title>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/cssem.css" />
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
	<script type="text/javascript" src="<%=path%>/js/textselect.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
</head>
<body>
	<html:form styleId="terminalInfoForm"
		action="/terminalInfo.do?method=getTerminalInfoList" method="post">
		<table border="0" cellpadding="0" cellspacing="0" style="width: 100%; height: 99%;">
			<tr>
				<td align="center" valign="top">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="width: 28px; height: 10px;"></td>
						</tr>
						<tr>
							<td align="left"
								style="width:28px; height:28px;  background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
							</td>
							<td
								style="height:28px;  background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;��ǰλ�ã� �̻���Ϣͳ�� &gt; �鿴�ն���Ϣ
							</td>
							<td
								style="width:7px; height:28px;  background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td style="width: 28px; height: 5px" colspan="3"></td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td coslpan="2"><font color="red">${info}</font></td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�ն˱��
							</td>
							<td align="left" class="box2">
								<html:text property="id" maxlength="15" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�����̻�
							</td>
							<td align="left" class="box2">
								<html:text property="merchantid" maxlength="15" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻�װ����ַ
							</td>
							<td align="left" class="box2">
								<html:text property="address" maxlength="15" disabled="true"></html:text>
							</td>
						</tr>
						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								���̼���̨��ַ
							</td>
							<td align="left" class="box2">
								<html:text property="detailaddress" maxlength="15" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POS�ͺ�
							</td>
							<td align="left" class="box2">
								<logic:present name="pos_modelList">
									<html:select property="model" disabled="true">
										<html:optionsCollection name="pos_modelList"
											label="param_name" value="param_value" />
									</html:select>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POS����
							</td>
							<td align="left" class="box2">
								<logic:present name="pos_typeList">
									<html:select property="type" disabled="true">
										<html:optionsCollection name="pos_typeList"
											label="param_name" value="param_value" />
									</html:select>
								</logic:present>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����POS�ֻ���
							</td>
							<td align="left" class="box2">
								<html:text property="mobilenumber" maxlength="15" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POS��S/N��
							</td>
							<td align="left" class="box2">
								<html:text property="snnumber" maxlength="15" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��װ����
							</td>
							<td align="left" class="box2">
								<html:text property="installdate" maxlength="15" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								ͣ������
							</td>
							<td align="left" class="box2">
								<html:text property="disabledate" maxlength="15" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��������
							</td>
							<td align="left" class="box2">
								<html:text property="updatedate" maxlength="15" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��ϵ��
							</td>
							<td align="left" class="box2">
								<html:text property="name" maxlength="15" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�ŵ�绰
							</td>
							<td align="left" class="box2">
								<html:text property="phonenumber" maxlength="15" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POS��״̬
							</td>
							<td align="left" class="box2">
								<html:select property="status" disabled="true">
									<html:option value="">����ѡ��</html:option>
									<html:option value="0">����</html:option>
									<html:option value="1">������</html:option>
									<html:option value="2">������</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POSѺ��Ԫ��
							</td>
							<td align="left" class="box2">
								<html:text property="deposite" maxlength="15" disabled="true"></html:text>
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="height: 23px;" align="center" class="box1">
								<input class="button" type="button" value="�ر�"
									onClick="javascript:window.close()" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>

</html:html>