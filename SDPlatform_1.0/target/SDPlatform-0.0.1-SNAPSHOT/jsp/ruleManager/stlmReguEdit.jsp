<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>�������</title>
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
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:stlmRegu:edit">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">  
				�޸��������</td>
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
								 ������ :
							</td>
							<td class="table2_td">
								<html:text property="ruleNo" size="30" maxlength="7" disabled="true" />
								<html:hidden property="ruleNo" /><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								 ���ȼ�(Խ�����ȼ�Խ��):
							</td>
							<td class="table2_td">
								<html:text property="ruleLevel" size="30" maxlength="7" onkeyup="this.value=this.value.replace(/\D/g,'')" /><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 ��Ч��ʼ���� :
							</td>
							<td class="table2_td">							
								<html:text property="startDate" size="30" maxlength="8" onclick="WdatePicker({dateFmt:'yyyyMMdd'})"/><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								 ��Ч�������� :
							</td>
							<td class="table2_td">
								<html:text property="endDate" size="30" maxlength="8" onclick="WdatePicker({dateFmt:'yyyyMMdd'})"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 �������� :
							</td>
							<td class="table2_td">
							<html:select property="channelNo" style="width:130px;">
								<html:option value="">-��ѡ��-</html:option>
								<html:option value="00">��������</html:option>
								<html:option value="01">POS����</html:option>
								<html:option value="02">ATM����</html:option>
								<html:option value="03">�����յ�</html:option>
							</html:select><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								 ���� :
							</td>
							<td class="table2_td">
							<html:select property="cardType" style="width:130px;">
								<html:option value="">-��ѡ��-</html:option>
								<c:forEach var="model" items="${cardTypeList}">
								<html:option value="${model.id.cardType}">${model.id.typeName}</html:option>
								</c:forEach>
							</html:select><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 �������� :
							</td>
							<td class="table2_td">
							<html:select property="transCode" style="width:130px;">
								<html:option value="0">��������</html:option>
								<c:forEach items="${tranTypeList}" var="model">
									<html:option value = "${model.paramValue }">${model.id.paramName }</html:option>
								</c:forEach>
							</html:select><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								 �̻�����:
							</td>
							<td class="table2_td">
								<html:text property="mcc" size="30" maxlength="4" />ALL��xxxx<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 �̻����:
							</td>
							<td class="table2_td">
								<html:text property="mchtNo" size="30" maxlength="15" />ALL��xxxx<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								 �̻������ѽ��㷽ʽ :
							</td>
							<td class="table2_td">
							<html:select property="mchtFlg" style="width:130px;">
								<html:option value="">-��ѡ��-</html:option>
								<html:option value="1">1��</html:option>
								<html:option value="2">2��</html:option>
								<html:option value="3">3��</html:option>
								<html:option value="4">4��</html:option>
								<html:option value="5">5��</html:option>
							</html:select><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 ���α�־ :
							</td>
							<td class="table2_td">
							<html:select property="g1CrDscFlg" style="width:130px;">
								<html:option value="">-��ѡ��-</html:option>
								<html:option value="0">ÿ�ʽ��׽��</html:option>
								<html:option value="1">�̻�ǩԼ����</html:option>
							</html:select><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								 ���ο��� :
							</td>
							<td class="table2_td">
								<html:text property="g1CrDscRate1" size="30" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 ���ε��Ȼ�Ӷ����� :
							</td>
							<td class="table2_td">
								<html:text property="g1CrDscAmt1" size="30" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')"/><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								 ���ε��Ȼ�Ӷ��С��� :
							</td>
							<td class="table2_td">
								<html:text property="g1CrDscAmt2" size="30" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 ���ν����������ۼ������ :
							</td>
							<td class="table2_td">
								<html:text property="g1CrDscSum1" size="30" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')" /><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								 ���ν����������ۼ���С��� :
							</td>
							<td class="table2_td">
								<html:text property="g1CrDscSum2" size="30" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�����ڽ��������ڵĹ̶��շѽ�� :
							</td>
							<td class="table2_td">
								<html:text property="g1CrDscFee" size="30" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')"/><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								  ��ע :
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




