<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>终端资料明细</title>
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
		document.forms[0].method.value="saveEdcTerminal";
		return validateEdcTerminalForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryEdcTerminal";
	}
	</script>
</head>
<shiro:lacksPermission name="posp:terminal:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:terminal:view">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				终端资料明细
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="edcTerminalForm" />
				<html:errors />
				<html:form action="/edcTerminal">

					<html:hidden property="method" value="saveEdcTerminal" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">

						<tr>
							<td class="table2_td_title">
								商户编号:
							</td>
							<td class="table2_td">
								${edcTerminalForm.id.merchantId }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								商户名称:
							</td>
							<td class="table2_td">
								${edcTerminalForm.id.merchanName }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 终端号：
							</td>
							<td class="table2_td">
								${edcTerminalForm.id.terminalId }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								终端状态：
							</td>
							<td class="table2_td">
								<html:select property="terminalStat" disabled="true">
								 <html:option value="Y">正常</html:option>
								 <html:option value="N">冻结</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								终端设备型号：
							</td>
							<td class="table2_td">
								<html:select property="edcType" disabled="true">
								  <html:option value=""></html:option>
								  <html:option value="STD">STD</html:option>
							      <html:option value="CMB">CMB</html:option>
							      <html:option value="CITIC">CITIC</html:option>
							      <html:option value="CITIC1">CITIC1</html:option>
							      <html:option value="HYPT7">HYPT7</html:option>
							      <html:option value="SPECS900">SPECS900</html:option>
							      <html:option value="STAR">STAR</html:option>
							      <html:option value="SHARE">SHARE</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								设备规格说明：
							</td>
							<td class="table2_td">
								${edcTerminalForm.edcDoc }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								打印机类型：
							</td>
							<td class="table2_td">
								${edcTerminalForm.printerType }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								PIN PAD类型：
							</td>
							<td class="table2_td">
								${edcTerminalForm.pinpadType }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								终端软件版本：
							</td>
							<td class="table2_td">
								${edcTerminalForm.softVer }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								参数下载标志：
							</td>
							<td class="table2_td">
								<html:select property="downloadFlag" disabled="true">
								 <html:option value="Y">需要安装</html:option>
								 <html:option value="N">无需安装</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								参数下载模式：
							</td>
							<td class="table2_td">${edcTerminalForm.downloadMode}-
								<c:if test="${edcTerminalForm.downloadMode eq 0}">无</c:if>
								<c:if test="${edcTerminalForm.downloadMode eq 4}">更新公钥</c:if>
								<c:if test="${edcTerminalForm.downloadMode eq 5}">下载IC卡参数</c:if>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								安装日期：
							</td>
							<td class="table2_td">
								${edcTerminalForm.setDate }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								安装地点：
							</td>
							<td class="table2_td">
								${edcTerminalForm.setAddr }
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



