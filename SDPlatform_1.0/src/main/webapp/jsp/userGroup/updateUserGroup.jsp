<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>��ɫ����</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	<script language="javascript">
	
	function saveClick()
	{
		document.forms[0].method.value="saveGroup";
		return validateGroupForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryUserGroup";
	}
	//�ƹ�ʱtr�ı���ɫ 
	function rowOver(target) 
	{ 
	    target.bgColor='#e4e4e4'; 
	} 
	//�Ƴ�ʱtr�ı���ɫ 
	function rowOut(target) 
	{ 
	    target.bgColor='#ffffff'; 
	} 
	</script>
</head>

<body>
	<!--------------����TableΪ·��-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">
				��ǰλ�ã��յ�ϵͳ����ƽ̨ >> Ȩ�޹��� >> ��ɫ����
			</td>
		</tr>
		<tr>
			<td height="10">
			</td>
		</tr>
	</table>
	<!--------------TableΪ·������-------->
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				�޸Ľ�ɫ
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="groupForm" />
				<html:errors />
				<html:form action="/UserGroup">

					<html:hidden property="method" value="saveGroup" />
			
					
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">


						<tr>
							<td class="table2_td_title">
								��ɫ��ʶ��

							</td>
							<td class="table2_td">
								${groupForm.groupId }
								<html:hidden property="groupId" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��ɫ������

							</td>
							<td class="table2_td">
								<html:text property="groupDescription" size="19" maxlength="40" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�������ߣ�

							</td>
							<td class="table2_td">
								${groupForm.updateUser }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								���������ڣ�
							</td>
							<td class="table2_td">
								${groupForm.updateTimestamp }
							</td>
						</tr>

						<tr>
							<td align="center" colspan="2" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()">
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()">


							</td>
						</tr>
<tr><td colspan="2">
<div>
<table width="100%" class="table2_td_title">
<tr align="center"><td>������</td><td colspan="7">������</td></tr>
<c:out value="${allmenu}" escapeXml="false" />
</table>
</div>
</td>
</tr>
					</table>

					<!-- ά����ͼ״̬�������� -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<input type="hidden" name="search_name" value="<c:out value="${param.search_name}"/>" />
					<!-- ά����ͼ״̬�������� -->
				</html:form>

			</td>
		</tr>
	</table>



</body>

</html:html>



