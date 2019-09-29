<%@ page language="java" contentType="text/html;charset=GBK"
	import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>��������</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet"
		type="text/css">
	<script type="text/javascript"
		src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script language="javascript"
		src="<fmt:message key='JavaScriptPath' />meizzDatea.js"></script>
	<script language="JavaScript"
		src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	<script type="text/javascript"
		src="<fmt:message key='JavaScriptPath' />DatePicker/WdatePicker.js"></script>
	<script language="javascript">
	
	function saveClick()
	{
		document.forms[0].method.value="saveSysParam";
		return validateSysForm(document.forms[0]);
	}
	function backClick()
	{
		document.forms[0].method.value="queryJg";
	}
	function setMbh()
	{
		var sss = document.forms[0].bankid.value;
		//document.forms[0].rcvBankId.value=sss;
		var wz=sss.indexOf("_");
		document.forms[0].hostId.value=sss.substr(wz+1);
		document.forms[0].bankId.value=sss.substr(0,wz);
	}
	function setAdmMbh()
	{
		var sss = document.forms[0].admbankid.value;
		//document.forms[0].rcvBankId.value=sss;
		var wz=sss.indexOf("_");
		document.forms[0].admHostId.value=sss.substr(wz+1);
		document.forms[0].admBankId.value=sss.substr(0,wz);	
	}
	</script>
</head>
<shiro:lacksPermission name="posp:sys:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:sys:view">
<body>
	<table width="95%" border="0" align="center" cellpadding="0"
		cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif"
					width="26" height="23" align="absmiddle">
				������Ϣ
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="sysForm" />
				<html:errors />
				<html:form action="/sys" enctype="multipart/form-data">
					<html:hidden property="method" value="saveSysParam" />
					<table align="center" width="90%" border="0" align="center"
						cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								��������:
							</td>
							<td class="table2_td">
								<html:select onchange="setMbh()" property="bankid">
									<c:forEach var="model" items="${bankInfoList}">
										<option value="<c:out value="${model.bankId}"/>_<c:out value="${model.hostId}" />">
											<c:out value="${model.bankName}" />
										</option>
									</c:forEach>
								</html:select>
								<html:hidden property="bankId" />
								<!--<html:text property="bankId" size="19" maxlength="11" />-->
								<html:text property="hostId" size="5" maxlength="2" />
							</td>
							<td class="table2_td_title">
								��Ͻ������:
							</td>
							<td class="table2_td">
								<html:select onchange="setAdmMbh()" property="admbankid">
									<c:forEach var="model" items="${bankInfoList}">
										<option value="<c:out value="${model.bankId}"/>_<c:out value="${model.hostId}" />">
											<c:out value="${model.bankName}" />
										</option>
									</c:forEach>
								</html:select>
								<html:hidden property="admBankId" />
								<!--<html:text property="admBankId" size="12" maxlength="11" />-->
								<html:text property="admHostId" size="5" maxlength="2" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��������:
							</td>
							<td class="table2_td">
								<html:select styleId="bankType" property="bankType">
									<c:forEach var="model" items="${bankTypeList}">
										<option value="<c:out value="${model.bankType}"/>">
											<c:out value="${model.typeName}" />
										</option>
									</c:forEach>
								</html:select>
							</td>
							<td class="table2_td_title">
								������ˮ��:
							</td>
							<td class="table2_td">
								<html:text property="hostLsNo" size="19" maxlength="7"
									onkeyup="this.value=this.value.replace(/\D/g,'')" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��ʷ��ˮ��������:
							</td>
							<td class="table2_td">
								<html:text property="hisLsDay" size="19" maxlength="4"
									onkeyup="this.value=this.value.replace(/\D/g,'')" />
							</td>
							<td class="table2_td_title">
								�����������:
							</td>
							<td class="table2_td">
								<html:text property="backupLdate" size="19" maxlength="12"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								���ݼ��ʱ��:
							</td>
							<td class="table2_td">
								<html:text property="backupInterval" size="19" maxlength="12" />
							</td>
							<td class="table2_td_title">
								�Ƿ��Զ�ת��ˮ:
							</td>
							<td class="table2_td">
								<html:select property="autoChgFlag">
									<html:option value="Y">�Զ�ת</html:option>
									<html:option value="N">���Զ�ת</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��ǰ��ˮ��������:
							</td>
							<td class="table2_td">
								<html:text property="curLsDay" size="19" maxlength="4"
									onkeyup="this.value=this.value.replace(/\D/g,'')" />
							</td>
							<td class="table2_td_title">
								�Ƿ�֧�ִ洢ת��:
							</td>
							<td class="table2_td">
								<html:select property="ifNoSaf">
									<html:option value="0">֧��</html:option>
									<html:option value="1">��֧��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�洢ת��ʱ����:
							</td>
							<td class="table2_td">
								<html:text property="safInterval" size="19" maxlength="10" />
							</td>
							<td class="table2_td_title">
								�洢ת����������:
							</td>
							<td class="table2_td">
								<html:text property="autoSafDay" size="19" maxlength="12"
									onkeyup="this.value=this.value.replace(/\D/g,'')" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								ת��ǿ���������:
							</td>
							<td class="table2_td">
								<html:text property="endDay" size="19" maxlength="10"
									onkeyup="this.value=this.value.replace(/\D/g,'')" />
							</td>
							<td class="table2_td_title">
								�������ÿ����˱�־:
							</td>
							<td class="table2_td">
								<html:select property="creChkFlag">
									<html:option value="0">������</html:option>
									<html:option value="1">EDC����</html:option>
									<html:option value="2">���н���</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�����߼���:
							</td>
							<td class="table2_td">
								<input name="logicDate" size="19" value="${sysForm.logicDate}" maxlength="12" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" readonly="true" />
							</td>
							<td class="table2_td_title">
								�Ƿ�֧�ִ���Ȩ:
							</td>
							<td class="table2_td">
								<html:select property="ifRepAuth">
									<html:option value="0">��Ȩ</html:option>
									<html:option value="1">��Ȩ</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								���ʴ����޶�:
							</td>
							<td class="table2_td">
								<html:text property="authAmt" size="19" maxlength="10" />
							</td>
							<td class="table2_td_title">
								���մ����޴�:
							</td>
							<td class="table2_td">
								<html:text property="dayAuthCnt" size="19" maxlength="4" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								���մ����޶�:
							</td>
							<td class="table2_td">
								<html:text property="dayAuthAmt" size="19" maxlength="10" />
							</td>
							<td class="table2_td_title">
								���߱�־:
							</td>
							<td class="table2_td">
								<html:select property="ifOffline">
									<html:option value="Y">֧��</html:option>
									<html:option value="N">��֧��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								���˱���:
							</td>
							<td class="table2_td">
								<html:text property="adjustRate" size="19" maxlength="10" />
							</td>
							<td class="table2_td_title">
								Ԥ��Ȩȷ�ϱ���:
							</td>
							<td class="table2_td">
								<html:text property="confirmRate" size="19" maxlength="10" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								ά����Ա:
							</td>
							<td class="table2_td">
								<html:text property="updateOper" size="19" maxlength="6" disabled="true" />
							</td>
							<td class="table2_td_title">
								����ʱ��:
							</td>
							<td class="table2_td">
								<html:text property="updateDate" size="19" maxlength="6" disabled="true" />
								<html:text property="updateTime" size="19" maxlength="6" disabled="true" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4" class="table2_btn">
							 	<shiro:hasPermission name="posp:sys:edit">
									<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick=returnsaveClick();>
								</shiro:hasPermission>
							</td>
						</tr>
					</table>
					<!-- ά����ͼ״̬�������� -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort"
						value="<c:out value="${param.search_sort}"/>" />
					<!-- ά����ͼ״̬�������� -->
				</html:form>
			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>



