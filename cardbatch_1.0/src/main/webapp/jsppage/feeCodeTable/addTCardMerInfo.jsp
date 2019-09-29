<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%String path = request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html lang="true">
<head>
	<html:base />
	<title>�̻���������ҳ��</title>
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
	<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
	<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<script type="text/javascript">
	function isInteger(obj) {
		return obj%1 === 0
	}
	function commit() {
		if (!checkelement("tCardMerInfoForm", "merNo", "�̻���")) {
			return false;
		}
		if (!checkelement("tCardMerInfoForm", "startcardNo", "��ʼ����")) {
			return false;
		}
		if (!checkelement("tCardMerInfoForm", "endcardNo", "��������")) {
			return false;
		}
		var merNo = document.getElementsByName("merNo")[0].value;
		var startcardNo = document.getElementsByName("startcardNo")[0].value;
		var endcardNo = document.getElementsByName("endcardNo")[0].value;
		var num = endcardNo-startcardNo;
		if(!(isInteger(startcardNo) && isInteger(endcardNo) && isInteger(merNo))){
			alert("��ʼ���Ż�������Ż��̻��Ų������֣�����������");
			return false;
		}
		if(num < 0){
			alert("��������С�ڿ�ʼ���ţ�����������");
			return false;
		}
		if(num >1000){
			alert("����1000�ſ�������������");
			return false;
		}
	    $("#btTJ").attr("disabled","true");
		document.tCardMerInfoForm.submit();
	}
</script>
<body>
	<html:form styleId="tCardMerInfoForm"
		action="/tCardMerInfo?method=addTCardMerInfo"
		enctype="multipart/form-data" method="post">
		<table border="0" cellpadding="0" cellspacing="0"
			style="width: 100%; height: 99%;">
			<tr>
				<td align="center" valign="top">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="width: 28px; height: 10px;"></td>
						</tr>
						<tr>
							<td align="left"
								style="  width:28px; height:28px; background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
							</td>
							<td
								style="height:28px;  background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;��ǰλ�ã� �̻�������� &gt; �����̻����
							</td>
							<td
								style=" width:7px; height:28px; background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td style="width: 28px; height: 5px" colspan="3"></td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻���
							</td>
							<td align="left" class="box2">
								<html:text property="merNo" maxlength="15"></html:text>
								<font color="red">*</font>&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��ʼ����
							</td>
							<td align="left" class="box2">
								<html:text property="startcardNo" maxlength="15"></html:text>
								<font color="red">*</font>&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��������
							</td>
							<td align="left" class="box2">
								<html:text property="endcardNo" maxlength="15"></html:text>
								<font color="red">*</font>&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="height: 23px;" align="center" class="box1">
								<input id="btTJ" class="button" type="button" onclick="commit()"
									value="����" />
								&nbsp;&nbsp;&nbsp;
								<input class="button" type="button"  value="�ر�" onClick="javascript:window.close()">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>


