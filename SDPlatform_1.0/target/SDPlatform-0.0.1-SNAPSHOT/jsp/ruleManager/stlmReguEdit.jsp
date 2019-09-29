<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>清算规则</title>
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
    <script type="text/javascript" src="<fmt:message key='JavaScriptPath' />DatePicker/WdatePicker.js" ></script>
	<script language="javascript">
	
	function saveClick()
	{
		document.forms[0].method.value="saveItem";
		return validateTblStlmReguForm(document.forms[0]);
	}
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	</script>

</head>
<shiro:lacksPermission name="posp:stlmRegu:edit">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:stlmRegu:edit">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">  
				修改清算规则</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="tblStlmReguForm" />
				<html:errors />
				<html:form action="/stlmRegu" method="post">

					<html:hidden property="method" value="createItem" />
					<html:hidden property="crtDatetime"  />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								 规则编号 :
							</td>
							<td class="table2_td">
								<html:text property="ruleNo" size="30" maxlength="7" disabled="true" />
								<html:hidden property="ruleNo" /><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								 优先级(越大优先级越高):
							</td>
							<td class="table2_td">
								<html:text property="ruleLevel" size="30" maxlength="7" onkeyup="this.value=this.value.replace(/\D/g,'')" /><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 生效开始日期 :
							</td>
							<td class="table2_td">							
								<html:text property="startDate" size="30" maxlength="8" onclick="WdatePicker({dateFmt:'yyyyMMdd'})"/><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								 生效结束日期 :
							</td>
							<td class="table2_td">
								<html:text property="endDate" size="30" maxlength="8" onclick="WdatePicker({dateFmt:'yyyyMMdd'})"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 交易渠道 :
							</td>
							<td class="table2_td">
							<html:select property="channelNo" style="width:130px;">
								<html:option value="">-请选择-</html:option>
								<html:option value="00">所有渠道</html:option>
								<html:option value="01">POS交易</html:option>
								<html:option value="02">ATM交易</html:option>
								<html:option value="03">网上收单</html:option>
							</html:select><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								 卡种 :
							</td>
							<td class="table2_td">
							<html:select property="cardType" style="width:130px;">
								<html:option value="">-请选择-</html:option>
								<c:forEach var="model" items="${cardTypeList}">
								<html:option value="${model.id.cardType}">${model.id.typeName}</html:option>
								</c:forEach>
							</html:select><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 交易类型 :
							</td>
							<td class="table2_td">
							<html:select property="transCode" style="width:130px;">
								<html:option value="0">所有类型</html:option>
								<c:forEach items="${tranTypeList}" var="model">
									<html:option value = "${model.paramValue }">${model.id.paramName }</html:option>
								</c:forEach>
							</html:select><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								 商户类型:
							</td>
							<td class="table2_td">
								<html:text property="mcc" size="30" maxlength="4" />ALL、xxxx<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 商户编号:
							</td>
							<td class="table2_td">
								<html:text property="mchtNo" size="30" maxlength="15" />ALL、xxxx<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								 商户手续费结算方式 :
							</td>
							<td class="table2_td">
							<html:select property="mchtFlg" style="width:130px;">
								<html:option value="">-请选择-</html:option>
								<html:option value="1">1档</html:option>
								<html:option value="2">2档</html:option>
								<html:option value="3">3档</html:option>
								<html:option value="4">4档</html:option>
								<html:option value="5">5档</html:option>
							</html:select><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 档次标志 :
							</td>
							<td class="table2_td">
							<html:select property="g1CrDscFlg" style="width:130px;">
								<html:option value="">-请选择-</html:option>
								<html:option value="0">每笔交易金额</html:option>
								<html:option value="1">商户签约扣率</html:option>
							</html:select><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								 档次扣率 :
							</td>
							<td class="table2_td">
								<html:text property="g1CrDscRate1" size="30" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 档次单比回佣最大金额 :
							</td>
							<td class="table2_td">
								<html:text property="g1CrDscAmt1" size="30" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')"/><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								 档次单比回佣最小金额 :
							</td>
							<td class="table2_td">
								<html:text property="g1CrDscAmt2" size="30" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 档次结算周期内累计最大金额 :
							</td>
							<td class="table2_td">
								<html:text property="g1CrDscSum1" size="30" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')" /><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								 档次结算周期内累计最小金额 :
							</td>
							<td class="table2_td">
								<html:text property="g1CrDscSum2" size="30" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								档次在结算周期内的固定收费金额 :
							</td>
							<td class="table2_td">
								<html:text property="g1CrDscFee" size="30" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')"/><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								  备注 :
							</td>
							<td class="table2_td">
								<html:text property="remark" size="30" maxlength="60" />
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
					
				</html:form>

			</td>
		</tr>
	</table>



</body>
</shiro:hasPermission>
</html:html>




