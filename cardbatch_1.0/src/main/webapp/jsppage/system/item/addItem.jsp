	
<%@ page language="java" pageEncoding="gbk"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%String path = request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>Ȩ������ҳ��</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
</head>
<script language="javascript">
	function conmmit(){
		var itemno = document.itemForm.itemno.value;
		var itemname = document.itemForm.itemname.value;
		if(trim(itemno) == ""){
			alert("������Ȩ�ޱ�ţ�");
			document.itemForm.itemno.focus();
			return false;
		}
		if(!isnumberonly("itemForm","itemno","Ȩ�ޱ��")){
			return false;
		}
		if(itemno.length != 4){
			alert("Ȩ�ޱ�ű���Ϊ��λ���֣����������룡");
			document.itemForm.itemno.focus();
			return false;
		}
		if(trim(itemname) == ""){
			alert("������Ȩ�����ƣ�");
			document.itemForm.itemname.focus();
			return false;
		}
		document.itemForm.submit();
	}
</script>
<center>
	<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
		<html:form styleId="itemForm" action="/item?method=addItem" method="post">
			<table border="0" cellpadding="0" cellspacing="0" width="862" height="474" background="<%=path%>/images/background/welcome2.gif">
				<tr>
					<td align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="80%">
							<tr>
								<td height="20"></td>
							</tr>
							<tr>
								<td height="30" align="center">
									<font color="blue" class="big">�� �� Ȩ ��</font>
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="80%">
							<tr>
								<td width="80" align="right" class="box1">
									<font color="red">Ȩ�ޱ��:</font>
								</td>
								<td align="left" class="box3">
									<html:text property="itemno" maxlength="4"></html:text>
									<font color="red">ע��:[����Ϊ��λ����]</font>
								</td>
							</tr>
							<tr>
								<td width="80" align="right" class="box1">
									<font color="red">Ȩ������:</font>
								</td>
								<td align="left" class="box2">
									<html:text property="itemname" maxlength="30"></html:text>
								</td>
							</tr>
							<tr>
								<td width="80" align="right" class="box1">
									Ȩ������:
								</td>
								<td align="left" class="box2">
									<html:textarea rows="2" cols="60" property="itemdescrip"></html:textarea>
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="80%">
							<tr>
								<td height="23" align="center" class="box1">
									<input class="button" type="button" onclick="conmmit();" value="����">
									&nbsp;&nbsp;&nbsp;
									<input class="button" type="button"  value="�ر�" onClick="history.go(-1)">
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
