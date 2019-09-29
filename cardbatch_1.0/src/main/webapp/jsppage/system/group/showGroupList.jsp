
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

	<title>��ʾ����Ա���б�ҳ��</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
</head>
<script src="<%=path%>/js/sorttable.js"></script>
<script language="javascript">
	function addConfirm(){
		window.location.href="<%=path %>/jsppage/group/addGroup.jsp";
	}
	function delConfirm(){
		var answer = window.confirm("��ȷ��Ҫɾ����ɾ��֮�󽫲��ָܻ�!");
		if(answer == true)
		{
			return true;
		}
		return false;
	}
</script>

<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<table border="0" cellpadding="0" cellspacing="0" width="862" height="474" background="<%=path%>/images/background/welcome2.gif">
		<tr>
			<td align="center" valign="top">
				<table border="0" cellpadding="0" cellspacing="0" width="90%">
				    <tr>
						<td height="20"></td>
					</tr>
					<tr>
						<td height="23" align="center">
							<font color="blue" class="big">�� �� �� �� ��</font>
						</td>
					</tr>
					<tr>
						<td height="25" align="right">
							<input type="button" class="button" onClick='return addConfirm()' style="cursor:hand" value="����">
						</td>
					</tr>
				</table>
				<div style="position:absolute; overflow:auto; overflow-x:hidden; width:90%; height:362px; border:1px none #000000; left: 50px;">
				<table border="0" cellpadding="0" cellspacing="0" width="100%" class="sortable">
					<tr align="center" style="position: relative; top: expression(this.offsetParent.scrollTop);">
						<td height="25" class=box1>
							����Ա����
						</td>
						<td height="25" class=box1>
							����Ա������
						</td>
						<td height="25" class=box1>
							����Ա������
						</td>
						<td height="25" class=box1>
							�������Ա
						</td>
						<td height="25" class=box1>
							�����ɫ
						</td>
						<td height="25" class=box1>
							�鿴Ȩ��
						</td>
						<td height="25" class=box1>
							�޸�
						</td>
						<td height="25" class=box1>
							ɾ��
						</td>
					</tr>
					<logic:present name="groupList">
						<logic:iterate id="groupBean" name="groupList">
							<tr align="center">
								<td class=box2 height="28">
									<bean:write name="groupBean" property="teamno"/>
								</td>
								<td class=box2 height="28">
									<bean:write name="groupBean" property="teamname"/>
								</td>
								<td class=box2 height="28">
									<bean:write name="groupBean" property="teamdescribe"/>&nbsp;
								</td>
								<td class=box2 height="28" style="cursor: hand">
									<html:link action="/userGroup.do?method=preGrantUser" paramId="teamno" paramName="groupBean" paramProperty="teamno">����</html:link>
								</td>
								<td class=box2 height="28" style="cursor: hand">
									<html:link action="/userGroup.do?method=preGrantRole" paramId="teamno" paramName="groupBean" paramProperty="teamno">����</html:link>
								</td>
								<td class=box2 height="28">
									<html:link action="/userGroup.do?method=queryItems" paramId="teamno" paramName="groupBean" paramProperty="teamno"><img border="0" src="<%=path %>/images/button/query.gif"/></html:link>
								</td>
								<td class=box2 height="28">
									<html:link action="/userGroup.do?method=preModGroup" paramId="teamno" paramName="groupBean" paramProperty="teamno"><img border="0" src="<%=path %>/images/button/mod.gif"/></html:link>
								</td>
								<td class=box2 height="28" onclick="return delConfirm();">
									<html:link action="/userGroup.do?method=deleteGroup" paramId="teamno" paramName="groupBean" paramProperty="teamno"><img border="0" src="<%=path %>/images/button/del.gif"/></html:link>
								</td>
							</tr>
						</logic:iterate>
					</logic:present>
					<logic:notPresent name="groupList">
						<tr align="center"><td  class=box3 colspan="6">���޹���Ա����Ϣ</td></tr>
					</logic:notPresent>
				</table>
				</div>
			</td>
		</tr>
	</table>
</body>
</html:html>
