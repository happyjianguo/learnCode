
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

	<title>����ҳ��</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/new_look.css" />
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	<script type="text/javascript" src="<%=path%>/js/eposcc.js"></script>
	<script type="text/javascript" src="<%=path%>/js/textselect.js"></script>
	<script language="javascript">
	window.history.forward(1);
	var flag = false;
	function commit(){
		
		var batchflag   =   document.bmangerForm.batchflag.value;
		var batchname   =   document.bmangerForm.batchname.value;
		var batchfile   =   document.bmangerForm.batchfile.value;
		var logfilepath =   document.bmangerForm.logfilepath.value;

		
		
		if(trim(batchflag) == ""){
			alert("�������������ʶ��");
			document.bmangerForm.batchflag.focus();
			return false;
		}
		if(!checkCode(batchflag)){
		    alert("�������ʶֻ�������ַ������֣�");
			document.bmangerForm.batchflag.focus();
			return false;
		}
		if(trim(batchname) == ""){
			alert("���������ƣ�");
			document.bmangerForm.batchname.focus();
			return false;
		}
		
		if(trim(logfilepath) == ""){
			alert("������־���ļ�·����");
			document.bmangerForm.logfilepath.focus();
			return false;
		}		
		
		document.bmangerForm.submit();
	}
	function resetFlag() {
		flag = false;
	}
</script>
</head>

<center>
	<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
		<html:form styleId="bmangerForm" action="/bmanger.do?method=addBManger" method="post" focus="batchflag">
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
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;��ǰλ�ã� ��������� &gt;������ά�� &gt;���������� </td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
					</table>
						<table border="0" cellpadding="0" cellspacing="0" width="70%" align="center" style="background-color:#D2E8F3">
							<tr>
								<td align="right"  class="box1" id="batchflagtd">	<font color="red">�������ʶ��</font></td>
								<td  align="left" class="box3"><html:text property="batchflag"  maxlength="30"></html:text></td>	
								<td align="left" class="box2" width="40%">
									
								</td>						
							</tr>
							<tr>
								<td  align="right" class="box1">
									<font color="red">���������ƣ�</font>
								</td>
								<td align="left" class="box2">
									<html:text property="batchname" maxlength="30"></html:text>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									<font color="red">�������ļ�·����</font>
								</td>
								<td align="left" class="box2">
									<html:text property="batchfile" maxlength="300" ></html:text>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									��־�ļ���									
								</td>
								<td align="left" class="box2">
									<html:text property="batchflagfile" maxlength="300" ></html:text>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									�����ļ���·����									
								</td>
								<td align="left" class="box2">
									<html:text property="backuppath" maxlength="500" ></html:text>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>		
							<tr>
								<td  align="right" class="box1">
									<font color="red">������־���ļ�·����</font>
									
								</td>
								<td align="left" class="box2">
									<html:text property="logfilepath" maxlength="500" ></html:text>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									<font color="red">�����û��飺</font>
									
								</td>
								<td align="left" class="box2">
									<select id="dept_no" name="dept_no">
										<option value="">����ѡ��</option>
										<c:forEach items="${deptnoList}" var="item">
											<option value="${item.deptno" }">${item.deptname }</option>
										</c:forEach>
									</select>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>						
							<tr>
								<td  align="right" class="box1">
									������������
								</td>
								<td align="left" class="box2">
									<html:textarea rows="4" cols="60" property="demo" ></html:textarea>
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="70%" align="center" style="background-color:#D2E8F3">
							<tr>
								<td height="23" align="center" class="box1">
									<input class="button" type="button" onClick="commit();" value="����">
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
