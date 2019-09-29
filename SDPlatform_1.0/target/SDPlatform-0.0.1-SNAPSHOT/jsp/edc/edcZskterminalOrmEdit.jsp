<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>专属卡终端管理</title>
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
	
	function saveClick() {
		document.forms[0].method.value="saveEdcAlipayWeChat";
		return validateEdcTerminalForm(document.forms[0]);
	}
	
	function backClick() {
		document.forms[0].method.value="queryEdcZskterminalOrm";
	}
	
	</script>
</head>
<shiro:lacksPermission name="posp:edczskterminalorm:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:edczskterminalorm:view">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				专属卡终端列表
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="edcZskterminalOrmForm" />
				<html:errors />
				<html:form action="/edcZskterminalOrm">

					<html:hidden property="method" value="saveEdcAlipayWeChat" />
					<html:hidden property="queryMerchantId" />
					<html:hidden property="queryTerminalId"/>
					<html:hidden property="queryLogonStatus" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">

						<tr>
							<td class="table2_td_title">
								商户编号:
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="merchantId" size="25" maxlength="15" onblur="checkMerchantId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								银行标识:
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="bankId" size="25" readonly="true" maxlength="15"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 终端号：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="terminalId" size="25"  maxlength="8" onblur="checkTerminalId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								专属卡商户号：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="bankMerchantId" size="25"  maxlength="8" onblur="checkTerminalId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								专属卡终端号：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="bankTerminalId" size="25"  maxlength="8" onblur="checkTerminalId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								模块ID：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="moduleId" size="25"  maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								系统流水号：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="sysTrace" size="25"  maxlength="4"/>
							</td>
							<td class="table2_td_title">
								专属卡流水号：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="bankTrace" size="25"  maxlength="4"/>
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
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								加密算法:
							</td>
							<td class="table2_td">
								<html:select property="encMethod" disabled="true">
								 <html:option value="0">DES</html:option>
								 <html:option value="6">3DES</html:option>
								 <html:option value="7">MD5</html:option>
								</html:select>
								<font color="red">*</font>
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
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								批次号：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="batchNo" size="19" maxlength="30" onclick="javascript:setday(document.all.tan1,document.all.setDate);" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								工作密钥密文PIN_KEY：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="pik" size="25"  maxlength="30"/>
							</td>
							<td class="table2_td_title">
								工作密钥密文MAC_KEY：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="mak" size="25"  maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								工作密钥密文PIN_KEY<br>（终端签到获取）：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="pikTmk" size="25"  maxlength="30"/>
							</td>
							<td class="table2_td_title">
								工作密钥密文MAC_KEY<br>（终端签到获取）：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="makTmk" size="25"  maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								密钥索引：
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="keyIndex" size="25"  maxlength="30"/>
								<font color="red">*</font>
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
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4" class="table2_btn">
<%--								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()">--%>
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