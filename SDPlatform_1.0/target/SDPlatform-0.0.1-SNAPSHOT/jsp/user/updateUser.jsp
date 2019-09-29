<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>用户管理</title>
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
	<!--------------以下Table为路径-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">
				当前位置：收单系统管理平台 >> 权限管理 >> 用户管理 
			</td>
		</tr>
		<tr>
			<td height="10">
			</td>
		</tr>
	</table>
	<!--------------Table为路径结束-------->


	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				修改用户
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
								用户名：

							</td>
							<td class="table2_td">
								<html:text property="userId" size="19" maxlength="40" disabled="true"/>
								<font color="red">*</font>
								<html:hidden property="userId"/>
							</td>
						</tr>
<tr>
							<td class="table2_td_title">
								用户姓名：

							</td>
							<td class="table2_td">
								<html:text property="userName" size="19" maxlength="40" />
								<font color="red">*</font>
							</td>
						</tr>
<tr>
							<td class="table2_td_title">
								用户密码：

							</td>
							<td class="table2_td">
								<html:password property="pwdsub" size="20" maxlength="40" value="#111111111111111111#"/>
								<font color="red">*</font>
								<html:hidden property="password" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								角色名称：

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
								密码错误次数限定：

							</td>
							<td class="table2_td">
								<html:text property="passwordAttemptsLimit" size="19" maxlength="1" />
								<font color="red">*</font>
							</td>
						</tr>
						
						<tr>
							<td class="table2_td_title">
								密码有效期：

							</td>
							<td class="table2_td">
								<html:text property="passwordExpiry" size="19" maxlength="20"  onclick="javascript:setday(document.all.tan1,document.all.passwordExpiry);" readonly="true"/>
<a href="javascript:setday(document.all.tan1,document.all.passwordExpiry);"> <img width="16" height="16" name="tan1" border="0" src="<fmt:message key='CommonImagePath' />tan.gif" alt=""></a>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								现时密码尝试失败次数：
							</td>
							<td class="table2_td">
<html:text property="passwordAttempts" size="19" maxlength="1" />
								
							</td>
						</tr>
<tr>
							<td class="table2_td_title">
								状况：

							</td>
							<td class="table2_td">
								<html:select property="status">
								<html:option value="A">A-使用中</html:option>
							    <html:option value="N" >N-未起用</html:option>
								<html:option value="S" >S-暂时停用</html:option>
								<html:option value="T" >T-已终止</html:option>
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

<!-- 维护视图状态的隐藏域 -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<input type="hidden" name="search_name" value="<c:out value="${param.search_name}"/>" />
					<!-- 维护视图状态的隐藏域 -->
				</html:form>

</body>

</html:html>

