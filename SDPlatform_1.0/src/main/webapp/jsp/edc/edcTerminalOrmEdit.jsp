<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>旧福卡银行终端管理</title>
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
		document.forms[0].method.value="saveEdcTerminalOrm";
		return validateEdcTerminalForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryEdcTerminalOrm";
	}
	</script>
</head>
<shiro:lacksPermission name="posp:terminalorm:edit">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:terminalorm:edit">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				旧福卡银行终端
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="edcTerminalOrmForm" />
				<html:errors />
				<html:form action="/edcTerminalOrm">

					<html:hidden property="method" value="saveEdcTerminalOrm" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">

						<tr>
							<td class="table2_td_title">
								商户编号:
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="merchantId" size="25" maxlength="15" onblur="checkMerchantId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<html:hidden property="merchantId"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								银行标识:
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="bankId" size="25" readonly="true" maxlength="15"/>
								<html:hidden property="bankId"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 终端号：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="terminalId" size="25"  maxlength="8" onblur="checkTerminalId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<html:hidden property="terminalId"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								银行商户号：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="bankMerchantId" size="25"  maxlength="8" onblur="checkTerminalId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<html:hidden property="bankMerchantId"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								银行终端号：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="bankTerminalId" size="25"  maxlength="8" onblur="checkTerminalId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<html:hidden property="bankTerminalId"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								模块ID：
							</td>
							<td class="table2_td">
								<html:hidden property="moduleId"/>
								<html:text disabled="true" property="moduleId" size="25"  maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								系统流水号：
							</td>
							<td class="table2_td">
								<html:hidden property="sysTrace"/>
								<html:text disabled="true" property="sysTrace" size="25"  maxlength="4"/>
							</td>
							<td class="table2_td_title">
								银行流水号：
							</td>
							<td class="table2_td">
								<html:text property="bankTrace" size="25"  maxlength="4"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								PIN算法标识:
							</td>
							<td class="table2_td">
								<html:select property="pinFmt" disabled="true">
								 <html:option value="1">ANSI X98格式（不带主账号）</html:option>
								 <html:option value="2">ANSI X98算法（带主账号）</html:option>
								</html:select>
								<html:hidden property="pinFmt"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								批次号：
							</td>
							<td class="table2_td">
								<html:text property="batchNo" size="19" maxlength="30" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								MAC运算标志 :
							</td>
							<td class="table2_td">
								<html:select property="macFlag" disabled="true">
								 <html:option value="0">特例终端不做</html:option>
								 <html:option value="1">正常</html:option>
								</html:select>
								<html:hidden property="macFlag"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								加密算法:
							</td>
							<td class="table2_td">
								<html:select property="encMethod" disabled="true">
								 <html:option value="0">DES</html:option>
								 <html:option value="6">3DES</html:option>
								</html:select>
								<html:hidden property="encMethod"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								工作密钥密文PIN_KEY：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="pik" size="25"  maxlength="30"/>
								<html:hidden property="pik"/>
							</td>
							<td class="table2_td_title">
								工作密钥密文MAC_KEY：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="mak" size="25"  maxlength="30"/>
								<html:hidden property="mak"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								工作密钥密文PIN_KEY<br>（终端签到获取）：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="pikTmk" size="25"  maxlength="30"/>
								<html:hidden property="pikTmk"/>
							</td>
							<td class="table2_td_title">
								工作密钥密文MAC_KEY<br>（终端签到获取）：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="makTmk" size="25"  maxlength="30"/>
								<html:hidden property="makTmk"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								密钥索引：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="keyIndex" size="25"  maxlength="30"/>
								<html:hidden property="keyIndex"/>
							</td>
							<td class="table2_td_title">
								结帐状态 :
							</td>
							<td class="table2_td">
								<html:select property="settStatus" disabled="true">
								 <html:option value="0">正常交易状态 </html:option>
								 <html:option value="1">需要结帐</html:option>
								 <html:option value="2">结帐进行中</html:option>
								</html:select>
								<html:hidden property="settStatus"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								受理机构POS终端签到状态 :
							</td>
							<td class="table2_td">
								<html:select property="logonStatus" disabled="true">
								 <html:option value="0">签退 </html:option>
								 <html:option value="1">签到</html:option>
								 <html:option value="2">异常</html:option>
								</html:select>
								<html:hidden property="logonStatus"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								开通标志:
							</td>
							<td class="table2_td">
								<html:select property="flag" disabled="true">
								 <html:option value="1">正常开通 </html:option>
								 <html:option value="0">未开通</html:option>
								</html:select>
								<html:hidden property="flag"/>
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