<%@ page contentType="text/html;charset=GBK" import="cn.yufu.posp.terminalmanager.domain.model.*"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>�̻�ת��</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />listPage.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	
	<script language="javascript">
	function init()
	{
		<c:if test="${!empty param.queryMerId}" >
		var queryMerId = '<c:out value="${param.queryMerId}"/>';
		document.forms[0].queryMerId.value=queryMerId;
		</c:if>	
		<c:if test="${!empty param.queryBank}" >
		var queryBank = '<c:out value="${param.queryBank}"/>';
		document.forms[0].queryBank.value=queryBank;
		</c:if>	
	}
	
	function addClick()
	{
		document.forms[0].method.value="findItem";
		document.forms[0].submit();
	}
	function editClicks(params,method)
	{
		//alert(params+","+method);
		document.forms[0].method.value=method;
		document.forms[0].merchantId.value=params;
		document.forms[0].submit();
		
	}
	function resetClick1(){
		document.forms[0].queryMerId.value="";
		document.forms[0].queryBank.value="";
	}
	</script>
	
</head>
<shiro:lacksPermission name="posp:merchantswitch:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:merchantswitch:view">
<body onload="init();">

	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				�̻�ת���б�   
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/merchantSwitch" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------����TableΪ��ѯform-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%"> 
								�̻����: 
							</td>
							<td class="table2_td" width="40%">
							<input name="queryMerId" size="19" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')" />
							</td>
							<td class="table2_td_title" width="10%">  
								��������:  
							</td>
							<td class="table2_td" width="40%">
							<html:select property="queryBank" style="width:130px;" value="">
								  <option value="">- ��ѡ�� -</option>
								  <c:forEach var="model" items="${bankTypeList}">
							        <option value="<c:out value="${model.bankType}"/>"><c:out value="${model.typeName}" /></option>
							      </c:forEach>
								 <!--   <html:option value="01  ">�й���������</html:option>
							      <html:option value="02  ">�й���������</html:option>
							      <html:option value="03  ">�й�ũҵ����</html:option>
							      <html:option value="04  ">�й�����</html:option>
							      <html:option value="05  ">�й���������</html:option>
							      <html:option value="06  ">��ͨ����</html:option>
							      <html:option value="07  ">��������</html:option>
							      <html:option value="08  ">��������</html:option>
							      <html:option value="09  ">��ͨ����</html:option>
							      <html:option value="10  ">��������</html:option>
							      <html:option value="11  ">��������</html:option>
							      <html:option value="12  ">��������</html:option>
							      <html:option value="13  ">�㷢����</html:option>
							      <html:option value="14  ">�ַ�����</html:option>
							      <html:option value="15  ">�����</html:option>
							      <html:option value="16  ">�������</html:option>
							      <html:option value="17  ">������ҵ����</html:option>
							      <html:option value="18  ">��ҵ����</html:option>
							      <html:option value="20  ">ũ��������</html:option>
							      <html:option value="21  ">�й��������</html:option>
							      <html:option value="22  ">�й���������</html:option>
							      <html:option value="23  ">����Ϻ����</html:option>
							      <html:option value="24  ">������ҵ����</html:option>
							      <html:option value="25  ">��������</html:option>
							      <html:option value="26  ">��������</html:option>
							      <html:option value="27  ">��������</html:option>
							      <html:option value="28  ">��������</html:option>
							      <html:option value="29  ">�Ϻ�����</html:option>
							      <html:option value="30  ">���������������޹�˾</html:option>
							      <html:option value="31  ">��¡����</html:option>
							      <html:option value="32  ">���żλ�����</html:option>
							      <html:option value="33  ">�������</html:option>
							      <html:option value="34  ">��չ���У���ۣ����޹�˾</html:option>
							      <html:option value="35  ">�����������޹�˾</html:option>
							      <html:option value="36  ">��������</html:option>
							      <html:option value="37  ">�δ�������</html:option>
							      <html:option value="38  ">��������</html:option>
							      <html:option value="39  ">��������</html:option>
							      <html:option value="40  ">TRAVELEX</html:option>
							      <html:option value="41  ">AEON�Ŵ��������ޣ����޹�˾</html:option>
							      <html:option value="48  ">���μ���</html:option>
							      <html:option value="50  ">�������</html:option>
							      <html:option value="51  ">VISA</html:option>
							      <html:option value="52  ">MASTER_CARD</html:option>
							      <html:option value="53  ">AMEX</html:option>
							      <html:option value="54  ">DINERS</html:option>
							      <html:option value="55  ">JCB</html:option>-->
							      </html:select>
							</td>						
						</tr>				
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="��ѯ" width="65" height="20" border="0" onclick="return searchClick('queryAll');">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" onclick="resetClick1()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="���" width="65" height="20" border="0"></a>
							</td>
						</tr>
					</table>
					<!----------TableΪ��ѯform����-------->


					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table3_border">
						<tr>
							<td class="table3_title">
								��ѯ���
							</td>
						</tr>
					</table>

					<!-- ��Diaplay Tag����ʾ -->
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/merchantSwitch.do">
						<display:column title="ѡ��" style="width:10%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.merchantId},${displayTable.bankType}"/>">
						
						</display:column>
						<display:column title="�̻����" style="width:20%;text-align:center" property="merchantId" headerClass="sortable" sortable="true" />
						<display:column title="��������" style="width:18%;text-align:center" property="bankTypeName" headerClass="sortable" sortable="true" />
						<display:column title="�����̻����" style="width:20%;text-align:center" property="othMerchantId" headerClass="sortable" sortable="true" />
						<display:column title="�����̻�����" style="width:20%;text-align:center" property="othMcc" headerClass="sortable" sortable="true"/>
						
						<display:column title="����" style="width:10%;text-align:center">
						<shiro:hasPermission name="posp:merchantswitch:edit">
							<a href="#" onclick="editClicks('<c:out value="${displayTable.merchantId},${displayTable.bankType}"/>','findItem')">�༭</a>
						</shiro:hasPermission>
						</display:column>
					</display:table>
					<!-- ��Diaplay Tag����ʾ -->		
					
					<input type="hidden" name="id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="merchantId" />
					
					<!----------����Table�ò���ѡ��-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								�Ƿ�ȫѡ
							</td>
							<td>
								<shiro:hasPermission name="posp:merchantswitch:delete">
									<a href="#"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="ɾ��" width="65" height="20" border="0" onclick="deleteClick('selectItems','checkItem','deleteItem');"></a> 
								</shiro:hasPermission>
								<shiro:hasPermission name="posp:merchantswitch:add">
									<a href="#" onclick="addClick()">
										<img border=0 name="imageField322" src="<fmt:message key='CommonImagePath' />btnAdd.gif" alt="����" width="65" height="20" border="0"></a>
								</shiro:hasPermission>
							</td>
							<td width="240" align="right">
								&nbsp;
							</td>
						</tr>
					</table>
					<!----------TTable�ò���ѡ�����-------->
					
			</td>
		</tr>
	</table>
	<%@include file="../common/getDisplayParams1.jsp"%>
	</html:form>
	<br>
</body>
</shiro:hasPermission>
</html:html>
