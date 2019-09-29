<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*,cn.yufu.posp.terminalmanager.domain.model.*" isELIgnored="false"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>�̻�������</title>
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
		return validateMerchantCardForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	</script>
</head>

<body>
	<!--------------����TableΪ·��-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">    
				��ǰλ�ã��յ�ϵͳ����ƽ̨ >> �̻����� >> �̻�������
			</td>
		</tr>
		<tr>
			<td height="10">
			</td>
		</tr>
	</table>
	<!--------------TableΪ·������-------->


	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				�޸��̻�������
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="merchantCardForm" />
				<html:errors />
				<html:form action="/merchantCard">

					<html:hidden property="method" value="saveItem" />
					
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						
	
						<tr>
							<td class="table2_td_title">
								�̻����:
							</td>
							<td class="table2_td">
								<html:text property="merchantId" size="30" maxlength="15" disabled="true"/><font color="red">*</font>
							    <html:hidden property="merchantId"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�̻�����:

							</td>
							<td class="table2_td">
								<html:text property="merchantName" size="30" disabled="true"/>
								<html:hidden property="merchantName"/>
							<br></td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��������:

							</td>
							<td class="table2_td">
							<html:select property="bankType" style="width:195px;">
							       <c:forEach var="model" items="${bankTypeList}">
							      <html:option value="${model.bankType}"><c:out value="${model.typeName}" /></html:option>
							      </c:forEach>
							      </html:select><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��������:

							</td>
							<td class="table2_td">
								<html:select property="cardType" style="width:195px;">
							      <c:forEach var="model" items="${cardTypeList}">
							      <html:option value="${model.id.cardType}"><c:out value="${model.id.typeName}" /></html:option>
							      </c:forEach>
							      </html:select><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								����״̬:

							</td>
							<td class="table2_td">
								<html:select property="cardStat" style="width:195px;">
									<html:option value="Y">����</html:option>
									<html:option value="N">����</html:option>
								</html:select><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�����ؿ���:

							</td>
							<td class="table2_td">
								<html:text property="cardDiscount" size="30" /><font color="red">*</font>
							</td>
						</tr>
						<html:hidden property="queryMerchant"/>
						<html:hidden property="queryBankType"/>
						<html:hidden property="queryCardType"/>
						<html:hidden property="cardPiece" />
						<html:hidden property="cardFeeMax"/>
						<html:hidden property="cardFeeMin"/>

						<tr>
							<td align="center" colspan="4" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()" />
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()" />


							</td>
						</tr>
					</table>

					<!-- ά����ͼ״̬�������� -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<!-- ά����ͼ״̬�������� -->
				</html:form>

			</td>
		</tr>
	</table>



</body>

</html:html>



