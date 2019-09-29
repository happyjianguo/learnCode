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
	
	    var  pwd1=document.forms[0].password.value;
	    var pwd2=document.forms[0].pwdsub.value;
	    if(pwd1==null||pwd1==""){
	       alert("用户密码不能为空！！！");
	       return false;
	    }else if(pwd2==null||pwd2==""){
	       alert("密码确认不能为空！！！");
	       return false;
	    }else if(pwd1!=pwd2){
	       alert("密码不一致，请重新输入！！！");
	       document.forms[0].password.value='';
	       document.forms[0].pwdsub.value='';
	       return false;	       
	    }else{
		   document.forms[0].method.value="saveUserPwd";
		   return validateUserForm(document.forms[0]);
		}
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
				当前位置：收单系统管理平台 >> 权限管理 >> 密码修改  
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
				用户密码修改
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

					<html:hidden property="method" value="saveUserPwd" />
					
					
					
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
                        <tr>
							<td class="table2_td_title">
								用户密码：
							</td>
							<td class="table2_td">
								<html:password property="password" size="19" maxlength="20" />
								<font color="red">*</font>
							</td>
						</tr>
                           <tr>
							<td class="table2_td_title">
								密码确认：
							</td>
							<td class="table2_td">
								<html:password property="pwdsub" size="19" maxlength="20" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2" class="table2_btn">
								<c:if test="${currentUserData.groupMap.x010803x02=='yes'}">
									<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()">
								</c:if>
							</td>
						</tr>
					</table>

					<!-- 维护视图状态的隐藏域 -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<input type="hidden" name="search_name" value="<c:out value="${param.search_name}"/>" />
					<!-- 维护视图状态的隐藏域 -->
				</html:form>

			</td>
		</tr>
	</table>



</body>

</html:html>



