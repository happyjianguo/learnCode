<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
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
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />listPage.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	
	<script language="javascript">
	function init()
	{
		<c:if test="${!empty param._cardId}" >
		var _cardId = '<c:out value="${param._cardId}"/>';
		document.forms[0]._cardId.value=_cardId;
		</c:if>
		<c:if test="${!empty param._cardName}" >
		var _cardName = '<c:out value="${param._cardName}"/>';
		document.forms[0]._cardName.value=_cardName;
		</c:if>
	}
	
	function addClick()
	{
		document.forms[0].method.value="showSr";
		document.forms[0].submit();
	}
	function editClicks(id,method)
	{
		document.forms[0].method.value=method;
		document.forms[0].jgId.value=id;
		document.forms[0].submit();
		
	}
	function downloadTemplate(){
		document.forms[0].method.value="downloadTemplate";
		document.forms[0].submit();
	}
	function fileUpload(){
		var fileName = document.getElementById("formShowData").upload.value;
		 if(!fileName){
			 alert("��ѡ��Ҫ������ļ�!");
	         return false;
		 }
	     var strRegex = "(.xlsx|.xls)$";
	     var re=new RegExp(strRegex);
	     if (!re.test(fileName.toLowerCase())){
	         alert("�ļ���ʽ���Ϸ�������ΪEXCEL�ĵ�!");
	         return false;
	     }
	     document.getElementById("formShowData").submit();
	}
	</script>
	
</head>
<shiro:lacksPermission name="posp:cardtype:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:cardtype:view">
<body onload="init()">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				�����б�
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/cardType" method="post">
					<input type="hidden" name="method" value="queryCardType" />
					<!----------����TableΪ��ѯform-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								����ʶ��:
							</td>
							<td class="table2_td" width="40%">
							<input type="text" name="_cardId"  maxlength="20"  style="width:135px;" />
							</td>
							<td class="table2_td_title" width="10%">
								����:
							</td>
							<td class="table2_td" width="40%">
							<input type="text" name="_cardName"  maxlength="20"  style="width:135px;" />
							</td>
							
						</tr>				
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="��ѯ" width="65" height="20" border="0" onclick="return searchClick('queryCardType');">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" onclick="resetClick()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="���" width="65" height="20" border="0"></a>
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/cardType.do">
						<display:column title="ѡ��" style="width:5%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.cardId}"/>_<c:out value="${displayTable.cardLen}"/>_<c:out value="${displayTable.cardNoId}"/>_<c:out value="${displayTable.cardType}"/>_<c:out value="${displayTable.cardName}"/>_<c:out value="${displayTable.bankType}"/>_<c:out value="${displayTable.bankCode}"/>_<c:out value="${displayTable.cardMode}"/>_<c:out value="${displayTable.cardIdTrack}"/>_<c:out value="${displayTable.cardIdOff}"/>_<c:out value="${displayTable.cardNoTrack}"/>_<c:out value="${displayTable.cardNoOff}"/>_<c:out value="${displayTable.expDateOff}"/>_<c:out value="${displayTable.pinMode}"/>_<c:out value="${displayTable.inputMode}"/>_<c:out value="${displayTable.chkCardValid}"/>_<c:out value="${displayTable.ifLocal}"/>_<c:out value="${displayTable.ifOffline}"/>_<c:out value="${displayTable.updateOper}"/>_<c:out value="${displayTable.updateDate}"/>_<c:out value="${displayTable.updateTime}"/>">
							
						</display:column>
						<display:column title="����ʶ��" style="width:15%;text-align:center" property="cardId" headerClass="sortable" sortable="true" />
						<display:column title="���ű�ʶ" style="width:15%;text-align:center" property="cardNoId" headerClass="sortable" sortable="true" />
						<display:column title="��������" style="width:15%;text-align:center" property="cardName" headerClass="sortable" sortable="true" />
						<display:column title="������" style="width:15%;text-align:center" property="cardTypeName" headerClass="sortable" sortable="true" />
						<display:column title="��������" style="width:15%;text-align:center" property="bankTypeName" headerClass="sortable" sortable="true" />
						<display:column title="��������" style="width:10%;text-align:center" property="bankCode" headerClass="sortable" sortable="true" />
						<display:column title="����" style="width:10%;text-align:center">
						<shiro:hasPermission name="posp:cardtype:edit">
							<a href="#" onclick="editClicks('<c:out value="${displayTable.cardId}"/>_<c:out value="${displayTable.cardLen}"/>_<c:out value="${displayTable.cardNoId}"/>_<c:out value="${displayTable.cardType}"/>_<c:out value="${displayTable.cardName}"/>_<c:out value="${displayTable.bankType}"/>_<c:out value="${displayTable.bankCode}"/>_<c:out value="${displayTable.cardMode}"/>_<c:out value="${displayTable.cardIdTrack}"/>_<c:out value="${displayTable.cardIdOff}"/>_<c:out value="${displayTable.cardNoTrack}"/>_<c:out value="${displayTable.cardNoOff}"/>_<c:out value="${displayTable.expDateOff}"/>_<c:out value="${displayTable.pinMode}"/>_<c:out value="${displayTable.inputMode}"/>_<c:out value="${displayTable.chkCardValid}"/>_<c:out value="${displayTable.ifLocal}"/>_<c:out value="${displayTable.ifOffline}"/>_<c:out value="${displayTable.updateOper}"/>_<c:out value="${displayTable.updateDate}"/>_<c:out value="${displayTable.updateTime}"/>','queryCardTypeByKey')">�༭</a>
						</shiro:hasPermission>
						</display:column>
						
					</display:table>
					<!-- ��Diaplay Tag����ʾ -->		
					
					<input type="hidden" name="id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="jgId" />
					
					<!----------����Table�ò���ѡ��-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								�Ƿ�ȫѡ
							</td>
							<td>
								<shiro:hasPermission name="posp:cardtype:delete">
									<a href="#"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="ɾ��" width="65" height="20" border="0" onclick="deleteClick('selectItems','checkItem','deleteCardType');"></a>
								</shiro:hasPermission>
								<shiro:hasPermission name="posp:cardtype:add">
									<a href="#" onclick="addClick()"><img border=0 name="imageField322" src="<fmt:message key='CommonImagePath' />btnAdd.gif" alt="����" width="65" height="20" border="0"></a>
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
	<shiro:hasPermission name="posp:cardtype:upload">
	<center>						
	<form id='formShowData' name="formShowData" enctype='multipart/form-data' method='post' action="cardTypeUpload.do" >
	<input type="hidden" name="method" value="fileUpload" />
	<input type="file" name='upload' id='upload' /><input type="button" value="����" onclick="fileUpload();" />
	<input type="button" value="���ص���ģ��" onclick="downloadTemplate();" />
	</form>
	</center>
	</shiro:hasPermission>
</body>
</shiro:hasPermission>
</html:html>