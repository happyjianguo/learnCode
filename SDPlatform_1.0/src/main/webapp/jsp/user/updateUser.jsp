<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>�û�����</title>
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
		document.forms[0].method.value="saveUser";
		return validateUserForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryUser";
	}
	</script>
</head>

<body>
	<!--------------����TableΪ·��-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">
				��ǰλ�ã��յ�ϵͳ����ƽ̨ >> Ȩ�޹��� >> �û����� 
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
				�޸��û�
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="userForm" />
				<html:errors />
				<html:form action="/User">

					<html:hidden property="method" value="saveUser" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">

						<tr>
							<td class="table2_td_title">
								�û�����

							</td>
							<td class="table2_td">
								<html:text property="userId" size="19" maxlength="40" disabled="true"/>
								<font color="red">*</font>
								<html:hidden property="userId"/>
							</td>
						</tr>
<tr>
							<td class="table2_td_title">
								�û�������

							</td>
							<td class="table2_td">
								<html:text property="userName" size="19" maxlength="40" />
								<font color="red">*</font>
							</td>
						</tr>
<tr>
							<td class="table2_td_title">
								�û����룺

							</td>
							<td class="table2_td">
								<html:password property="pwdsub" size="20" maxlength="40" value="#111111111111111111#"/>
								<font color="red">*</font>
								<html:hidden property="password" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��ɫ���ƣ�

							</td>
							<td class="table2_td">

								<html:select property="groupId">
									<c:forEach items="${pageInfoResult}" var="OaGroupInfo">
										<option value="<c:out value="${OaGroupInfo.id.groupId}"/>" >
											<c:out value="${OaGroupInfo.groupDescription}" />
										</option>
									</c:forEach>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�����������޶���

							</td>
							<td class="table2_td">
								<html:text property="passwordAttemptsLimit" size="19" maxlength="1" />
								<font color="red">*</font>
							</td>
						</tr>
						
						<tr>
							<td class="table2_td_title">
								������Ч�ڣ�

							</td>
							<td class="table2_td">
								<html:text property="passwordExpiry" size="19" maxlength="20"  onclick="javascript:setday(document.all.tan1,document.all.passwordExpiry);" readonly="true"/>
<a href="javascript:setday(document.all.tan1,document.all.passwordExpiry);"> <img width="16" height="16" name="tan1" border="0" src="<fmt:message key='CommonImagePath' />tan.gif" alt=""></a>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��ʱ���볢��ʧ�ܴ�����
							</td>
							<td class="table2_td">
<html:text property="passwordAttempts" size="19" maxlength="1" />
								
							</td>
						</tr>
<tr>
							<td class="table2_td_title">
								״����

							</td>
							<td class="table2_td">
								<html:select property="status">
								<html:option value="A">A-ʹ����</html:option>
							    <html:option value="N" >N-δ����</html:option>
								<html:option value="S" >S-��ʱͣ��</html:option>
								<html:option value="T" >T-����ֹ</html:option>
								</html:select>
								
							</td>
						</tr>
	
						<tr>
							<td align="center" colspan="2" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()">
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()">


							</td>
						</tr>
					</table>

				

			</td>
		</tr>
	</table>

<!-- ά����ͼ״̬�������� -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<input type="hidden" name="search_name" value="<c:out value="${param.search_name}"/>" />
					<!-- ά����ͼ״̬�������� -->
				</html:form>

</body>

</html:html>

