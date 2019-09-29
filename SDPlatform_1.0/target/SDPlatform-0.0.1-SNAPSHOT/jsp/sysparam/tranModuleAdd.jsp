<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>渠道模块信息</title>
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
		document.forms[0].method.value="createItem";
		return validateTranModuleInfForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	
	</script>
</head>
<shiro:lacksPermission name="posp:tranmodule:add">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:tranmodule:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				新增渠道模块信息
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="tranModuleInfForm" />
				<html:errors />
				<html:form action="/tranModule">

					<html:hidden property="method" value="saveItem" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">

						<tr>
							<td class="table2_td_title">
								渠道模块号:
							</td>
							<td class="table2_td">
								<html:text property="moduleId" size="19" onkeyup="this.value=this.value.replace(/\D/g,'')" />
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								交易类型代码:
							</td>
							<td class="table2_td">
								<html:text property="tranType" size="19" onkeyup="this.value=this.value.replace(/\D/g,'')" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								原交易类型:
							</td>
							<td class="table2_td">
								<html:text property="voidTranType" size="19" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')"  />
								<font color="red">*</font><br>撤销类或冲正类
							</td>
							<td class="table2_td_title">
								原原交易类型:
							</td>
							<td class="table2_td">
								<html:text property="voidOldTranType" size="19" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')" />
								<font color="red">*</font><br>撤销类冲正
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								报文中获取MessgeId:
							</td>
							<td class="table2_td">
								<html:text property="msgId" size="19" maxlength="4"  />
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								报文25域获取:
							</td>
							<td class="table2_td">
								<html:text property="serviceCode" size="19" maxlength="2" />
								<br>服务点条件码
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								网络管理码/交易类型编码:
							</td>
							<td class="table2_td">
								<html:text property="typeCode" size="19" maxlength="3"  />
								<font color="red">*</font><br>管理类交易 取8583报文60.3域<br>否则 取8583报文60.1 
							</td>
							<td class="table2_td_title">
								8583报文3域 交易处理码:
							</td>
							<td class="table2_td">
								<html:text property="procCode" size="19" maxlength="6" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								交易名称:
							</td>
							<td class="table2_td">
								<html:text property="tranName" size="19" maxlength="32" />
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								请求报文位图:
							</td>
							<td class="table2_td">
								<html:text property="reqBitmap" size="19" maxlength="32" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								响应报文位图:
							</td>
							<td class="table2_td">
								<html:text property="respBitmap" size="19" maxlength="32" />
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								原交易类型处理码:
							</td>
							<td class="table2_td">
								<html:text property="revOrgProcCode" size="19" maxlength="6" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								参加结算标志 :
							</td>
							<td class="table2_td">
								<html:select property="settFlag">
								 <html:option value="0" >不参加</html:option>
								 <html:option value="1" >参加（借记）</html:option>
								 <html:option value="2" >参加（贷记）</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								是否记录流水 :
							</td>
							<td class="table2_td">
								<html:select property="writeLsFlag">
								 <html:option value="0" >不记录</html:option>
								 <html:option value="1" >记录</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								MAC校验标志:
							</td>
							<td class="table2_td">
								<html:select property="checkMacFlag">
								 <html:option value="0" >不校验</html:option>
								 <html:option value="1" >校验</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								开通标志:
							</td>
							<td class="table2_td">
								<html:select property="flag">
								 <html:option value="0" >未开通</html:option>
								 <html:option value="1" >正常开通</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()">
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()">
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
</shiro:hasPermission>
</html:html>