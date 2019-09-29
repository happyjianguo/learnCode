<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*,cn.yufu.posp.terminalmanager.domain.model.*" isELIgnored="false"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>授权免密卡BIN信息</title>
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
		var nopasswdMaxamt = document.forms[0].nopasswdMaxamt.value.trim();
		if(nopasswdMaxamt!=null&&nopasswdMaxamt!=""){ 
			if(nopasswdMaxamt.indexOf(".") != -1&&nopasswdMaxamt.split(".")[1].length>2){
				alert("免密授信限额格式错误");
				return false;
			}
			if(nopasswdMaxamt.indexOf(".") == -1&&nopasswdMaxamt.length>13){
  				alert("免密授信限额格式错误");
  				return false;
  			}
			if(nopasswdMaxamt>5000){
  				alert("免密授信限额5000元");
  				return false;
  			}
		}


		document.forms[0].method.value="saveItem";
		return validateTblNoPasswdCardBinForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	
</script>
</head>
<shiro:lacksPermission name="posp:tblNoPasswdCardBin:edit">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:tblNoPasswdCardBin:edit">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				修改授权免密卡BIN信息
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="tblNoPasswdCardBinForm" />
				<html:errors />
				<html:form action="/tblNoPasswdCardBin">

					<html:hidden property="method" value="saveItem" />
					<html:hidden property="firstCardBin"/>
					<html:hidden property="lastCardBin"/>
					<html:hidden property="dataLength"/>
					<html:hidden property="cardBinInfo"/>
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								卡信息:
							</td>
							<td class="table2_td">
								<html:text property="cardBinInfo" size="11" maxlength="11" disabled="true"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								免密授信限额(元):
							</td>
							<td class="table2_td">
								<html:text property="nopasswdMaxamt" size="11" maxlength="7" onkeyup="this.value=this.value.replace(/[^\d\.]/g,'');"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								记录有效标识:
							</td>
							<td class="table2_td">
								<html:select property="flag">
								<html:option value="1">1-有效</html:option>
								<html:option value="0">0-无效</html:option>
								</html:select>
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
</shiro:hasPermission>
</html:html>



