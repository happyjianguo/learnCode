<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*,cn.yufu.posp.terminalmanager.domain.model.*" isELIgnored="false"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>商户用户管理</title>
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
		document.forms[0].method.value="saveItem";
		return validateMerchantUserForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	</script>
		<script type="text/javascript" language="javascript">

var req;
		
function init() {
	if(window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
}
		
function leavetext() {
	
	if ("" == document.merchantUserForm.merchantIds.value)
    {
	    //alert("商户编号不能为空，请重新输入！");
	    //form1.merchantId.focus()
	    return false;
	}
	
	
	if (document.merchantUserForm.merchantIds.value.length !=15)
	{
	    alert("商户编号必须为十五位，请重新输入！");
	   // document.merchantUserForm.merchantId.focus();
	    return false;
	}
	
	
	init();
	var url = "merchantBlack.do?method=findMerchantName&merchantId=" + escape(document.merchantUserForm.merchantIds.value);
	req.open("POST", url, true);
	req.onreadystatechange = callback;
	req.send(null);
	
}
function callback() {
	if(4 == req.readyState) {

		if(200 == req.status) {
			if(req.responseText==""||req.responseText==null){
				alert("请确认商户是否存在！");
				document.merchantUserForm.merchantIds.value ="";
			}
			document.merchantUserForm.merchantName.value = req.responseText;
			
		}
	}
	
}

</script>
</head>

<body>
	<!--------------以下Table为路径-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">    
				当前位置：收单系统管理平台 >> 商户管理 >> 商户用户管理
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
				修改商户用户
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="merchantUserForm" />
				<html:errors />
				<html:form action="/merchantUser">

					<html:hidden property="method" value="saveItem" />
					
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								登录用户名:
							</td>
							<td class="table2_td">
								<html:text property="loginId" size="30" maxlength="15" readonly="true"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								显示用户名:
							</td>
							<td class="table2_td">
								<html:text property="loginName" size="30" /><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								用户密码：

							</td>
							<td class="table2_td">
								<html:password property="pwdsub" size="20" maxlength="40" value="#111111111111111111#"/>
								<font color="red">*</font>
								<html:hidden property="loginPassword" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								商户编号:
							</td>
							<td class="table2_td">
								<html:text property="merchantIds" size="30" maxlength="15" onblur="leavetext()" onkeyup="this.value=this.value.replace(/\D/g,'');"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								商户名称:
							</td>
							<td class="table2_td">
								<input type="text" id="merchantName" name="merchantName" size="30" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								状态:
							</td>
							<td class="table2_td">
								<html:select property="status" style="width:195px;" value="">
									<html:option value="0">正常</html:option>
								</html:select><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()" />
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()" />


							</td>
						</tr>
					</table>

					<!-- 维护视图状态的隐藏域 -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<!-- 维护视图状态的隐藏域 -->
				</html:form>

			</td>
		</tr>
	</table>



</body>

</html:html>



