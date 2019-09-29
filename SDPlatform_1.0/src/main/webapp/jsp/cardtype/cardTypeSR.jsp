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
	<script language="javascript">
	
	function saveClick()
	{
		document.forms[0].method.value="createCardType";
		//alert(validateCardTypeForm(document.forms[0]));
		
		return validateCardTypeForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryCardType";
	}
	</script>
</head>
<shiro:lacksPermission name="posp:cardtype:add">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:cardtype:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				��������
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="cardTypeForm" />
				<html:errors />
				<html:form action="/cardType"   enctype="multipart/form-data">

					<html:hidden property="method" value="createCardType" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">


						<tr>
							<td class="table2_td_title">
								����ʶ��:

							</td>
							<td class="table2_td">
								<html:text property="cardId" size="19" maxlength="19" />
								
							</td>
						
							<td class="table2_td_title">
								���ų���:

							</td>
							<td class="table2_td">
								<html:text property="cardLen" size="19" maxlength="5" />
								
							</td>
						</tr><tr>
							<td class="table2_td_title">
								���ű�ʶ:

							</td>
							<td class="table2_td">
								<html:text property="cardNoId" size="19" maxlength="19" />
								
							</td>
						
							<td class="table2_td_title">
								��������:

							</td>
							<td class="table2_td">
								<html:text property="cardName" size="19" maxlength="20" />
								
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								������:
				</td>
							<td class="table2_td">
							<html:select styleId="cardType" property="cardType">


<c:forEach var="model" items="${cardTypeList}">

<option value="<c:out value="${model.id.cardType}"/>"><c:out value="${model.id.typeName}" /></option>
</c:forEach>

</html:select>
								<!--<html:text property="cardType" size="19" maxlength="2" />-->
								
							</td>
						
							<td class="table2_td_title">
								��������:

							</td>
							<td class="table2_td">
							<html:select property="bankType">
								<c:forEach var="model" items="${bankTypeList}">
							     <option value="<c:out value="${model.bankType}"/>"><c:out value="${model.typeName}" />
							     </option>
							   </c:forEach>
								</html:select>
								<!--<html:text property="bankType" size="19" maxlength="4" />-->
								
							</td>
						</tr><tr>
							<td class="table2_td_title">
								��������:

							</td>
							<td class="table2_td">
								<html:text property="bankCode" size="19" maxlength="11" />
								
							</td>
						
							<td class="table2_td_title">
								�Է�������:

							</td>
							<td class="table2_td">
								<html:select property="cardMode"><html:option value="">��</html:option><html:option value="01">Master������ͨ��</html:option><html:option value="02">Master���˽�</html:option><html:option value="03">MasterԱ����ͨ��</html:option><html:option value="04">MasterԱ����</html:option><html:option value="05">Master��λ��ͨ��</html:option><html:option value="06">Master��λ��</html:option><html:option value="07">Masterר�ÿ�����ͨ����</html:option><html:option value="11">Visa������ͨ��</html:option><html:option value="12">Visa���˽�</html:option><html:option value="13">VisaԱ����ͨ��</html:option><html:option value="14">VisaԱ����</html:option><html:option value="15">Visa��λ��ͨ��</html:option><html:option value="16">Visa��λ��</html:option></html:select>
							</td>
						</tr>
<tr>
							<td class="table2_td_title">
								��ݼ���:

							</td>
							<td class="table2_td">
								<html:select property="pinMode"><html:option value="0">������</html:option><html:option value="1">ֻ������</html:option><html:option value="2">ֻ�����֤</html:option><html:option value="3">ֻ�����֤������</html:option><html:option value="4">ͬʱ�����֤������</html:option></html:select>
							</td>
						
							<td class="table2_td_title">
								�ֹ���ʽ:

							</td>
							<td class="table2_td">
								<html:select property="inputMode"><html:option value="0">ˢ��</html:option><html:option value="1">�ֹ�</html:option></html:select>
							</td>
						</tr><tr>
							<td class="table2_td_title">
								����ʶ�ŵ�:

							</td>
							<td class="table2_td">
								<html:select property="cardIdTrack"><html:option value="2">2��</html:option><html:option value="3">3��</html:option></html:select>
							</td>
						
							<td class="table2_td_title">
								����ʶ��ʼλ��:

							</td>
							<td class="table2_td">
								<html:text property="cardIdOff" size="19" maxlength="2" />
								
							</td>
						</tr><tr>
							<td class="table2_td_title">
								���Ŵŵ�:

							</td>
							<td class="table2_td">
								<html:select property="cardNoTrack"><html:option value="2">2��</html:option><html:option value="3">3��</html:option></html:select>
							</td>
						
							<td class="table2_td_title">
								������ʼλ��:

							</td>
							<td class="table2_td">
								<html:text property="cardNoOff" size="19" maxlength="2" />
								
							</td>
						</tr><tr>
							<td class="table2_td_title">
								��Ч����ʼλ��:

							</td>
							<td class="table2_td">
								<html:text property="expDateOff" size="19" maxlength="5" />
								
							</td>
						
							<td class="table2_td_title">
								���ż������:

							</td>
							<td class="table2_td">
							<html:select property="chkCardValid"><html:option value="0">��׼</html:option><html:option value="1">�Ǳ�׼</html:option></html:select>
							</td>
						</tr><tr>
							<td class="table2_td_title">
								�Ƿ񱾵ؿ�:

							</td>
							<td class="table2_td">
								<html:select property="ifLocal"><html:option value="Y">��</html:option><html:option value="N">��</html:option></html:select>
							</td>
						
							<td class="table2_td_title">
								���߱�־:

							</td>
							<td class="table2_td">
								<html:select property="ifOffline"><html:option value="Y">֧��</html:option><html:option value="N">��֧��</html:option></html:select>
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

					<!-- ά����ͼ״̬�������� -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<!-- ά����ͼ״̬�������� -->
				</html:form>

			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>