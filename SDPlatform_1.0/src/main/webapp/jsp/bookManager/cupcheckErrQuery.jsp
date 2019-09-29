<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>�������˲����ˮ</title>
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
    <script type="text/javascript" src="<fmt:message key='JavaScriptPath' />jquery-1.4.min.js" ></script>
    <script type="text/javascript" src="<fmt:message key='JavaScriptPath' />DatePicker/WdatePicker.js" ></script>
	
	<script language="javascript">
	function init()
	{
		<c:if test="${!empty param._checkDate}" >
		var _checkDate = '<c:out value="${param._checkDate}"/>';
		document.forms[0]._checkDate.value=_checkDate;
		</c:if>	
		<c:if test="${!empty param._checkFlag}" >
		var _checkFlag = '<c:out value="${param._checkFlag}"/>';
		document.forms[0]._checkFlag.value=_checkFlag;
		</c:if>
		<c:if test="${!empty param._merchantId}" >
		var _merchantId = '<c:out value="${param._merchantId}"/>';
		document.forms[0]._merchantId.value=_merchantId;
		</c:if>
		<c:if test="${!empty param._localSysDate}" >
		var _localSysDate = '<c:out value="${param._localSysDate}"/>';
		document.forms[0]._localSysDate.value=_localSysDate;
		</c:if>
		<c:if test="${!empty param._terminalId}" >
		var _terminalId = '<c:out value="${param._terminalId}"/>';
		document.forms[0]._terminalId.value=_terminalId;
		</c:if>
		<c:if test="${!empty param._cardNo}" >
		var _cardNo = '<c:out value="${param._cardNo}"/>';
		document.forms[0]._cardNo.value=_cardNo;
		</c:if>
		<c:if test="${!empty param._tranType}" >
		var _tranType = '<c:out value="${param._tranType}"/>';
		document.forms[0]._tranType.value=_tranType;
		</c:if>
		<c:if test="${!empty param._traceNo}" >
		var _traceNo = '<c:out value="${param._traceNo}"/>';
		document.forms[0]._traceNo.value=_traceNo;
		</c:if>
	}

	function openDetail(id)
	{
		window.showModalDialog("cupcheckErr.do?method=queryDetail&read=true&bookid="+id, "_blank", 'dialogWidth=800px;dialogHeight=440px;scroll=yes;resizable=no;status=no;center=yes;');
	}
	function queryDetail(id)
	{
		document.forms[0].method.value="queryDetail";
		document.forms[0]._traceNo.value=id;
		document.forms[0].submit();
	}
	function queryAll(){
		document.forms[0].method.value="queryAll";
		return true;
	}
	//ɾ������ո�
  	String.prototype.RTrim   =   function(){   
  		return   this.replace(/(\s*$)/g,"");   
  	} 
	</script>
</head>

<body onload="init()">

	<!--------------����TableΪ·��-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">
				��ǰλ�ã��յ�ϵͳ����ƽ̨ >> ���ݹ��� >> �������˲����ˮ
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
				�������˲����ˮ�б�
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:errors />
				<html:form action="/cupcheckErr" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------����TableΪ��ѯform-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								��������:
							</td>
							<td class="table2_td" width="20%">
								<input type="text" name="_checkDate"  maxlength="20" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" readonly="true"/>
							</td>
							<td class="table2_td_title" width="10%">
								���˱�ʶ :
							</td>
							<td class="table2_td" width="20%">
								<select name="_checkFlag">
									<option value="">--��ѡ��--</option>
									<option value="0">δ����</option>
									<option value="1">�������</option>
									<option value="2">ϵͳ����</option>
									<option value="3">ϵͳ����</option>
									<option value="4">�������</option>
								</select>
							</td>
							<td class="table2_td_title" width="10%">
								�̻����  :
							</td>
							<td class="table2_td" width="20%">
								<input type="text" name="_merchantId"  maxlength="20"  style="width:135px;" onkeyup="this.value=this.value.replace(/\D/g,'');" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title" width="10%">
								����ϵͳ����:
							</td>
							<td class="table2_td" width="20%">
								<input name="_localSysDate" maxlength="20" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" readonly="true"/>
							</td>
							<td class="table2_td_title" width="10%">
								�ն˺�:
							</td>
							<td class="table2_td" width="20%">
								<input type="text" name="_terminalId"  maxlength="20"  style="width:135px;" />
							</td>
							<td class="table2_td_title" width="10%">
								����:
							</td>
							<td class="table2_td" width="20%">
							<input type="text" name="_cardNo"  maxlength="20"  style="width:135px;" />
							</td>
						</tr>		
						<tr>
							<td class="table2_td_title" width="10%">
								��������:
							</td>
							<td class="table2_td" width="20%">
							<select name="_tranType">
								<option value = "">--��ѡ��--</option>
								<c:forEach items="${tranTypeList}" var="model">
									<option value = "${model.paramValue }">${model.id.paramName }</option>
								</c:forEach>
							</select>
							</td>
							<td class="table2_td_title" width="10%">
								������ˮ��:
							</td>
							<td class="table2_td" width="20%">
							<input type="text" name="_traceNo"  maxlength="20"  style="width:135px;" />
							</td>
							<td class="table2_td_title" width="10%">
							</td>
							<td class="table2_td" width="20%">
							</td>
						</tr>
						<tr>
							<td colspan="6" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="��ѯ" width="65" height="20" border="0" onclick="return queryAll();">
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/cupcheckErr.do">
						<display:column title="���˱�ʶ " style="width:6%;text-align:center" headerClass="sortable" sortable="true" >
							<c:if test="${displayTable.checkFlag eq 0}">δ����</c:if>
							<c:if test="${displayTable.checkFlag eq 1}">�������</c:if>
							<c:if test="${displayTable.checkFlag eq 2}">ϵͳ����</c:if>
							<c:if test="${displayTable.checkFlag eq 3}">ϵͳ����</c:if>
							<c:if test="${displayTable.checkFlag eq 4}">�������</c:if>
						</display:column>
						<display:column title="�������� " style="width:6%;text-align:center" property="checkDate" headerClass="sortable" sortable="true" />
						<display:column title="�̻����" style="width:6%;text-align:center" property="merchantId" headerClass="sortable" sortable="true" />
						<display:column title="�ն˺�" style="width:6%;text-align:center" property="terminalId" headerClass="sortable" sortable="true" />
						<display:column title="����" style="width:6%;text-align:center" property="cardNo" headerClass="sortable" sortable="true" />
						<display:column title="���κ�" style="width:5%;text-align:center" property="batchNo" headerClass="sortable" sortable="true" />
						<display:column title="��ˮ��" style="width:6%;text-align:center" property="traceNo" headerClass="sortable" sortable="true" />
						<display:column title="��� " style="width:5%;text-align:center" property="tranAmt" headerClass="sortable" sortable="true" />
						<display:column title="����" style="width:5%;text-align:center" property="localSysDate" headerClass="sortable" sortable="true" />
						<display:column title="����" style="width:5%;text-align:center">
						<c:if test="${currentUserData.groupMap.x010905x00=='yes'}"> 
							<a href="javascript:;" onclick="openDetail('${displayTable.bookid}')" >��ϸ</a>
						</c:if>
						</display:column>
					</display:table>
					<!-- ��Diaplay Tag����ʾ -->		
			</td>
		</tr>
	</table>
	<%@include file="../common/getDisplayParams1.jsp"%>
	</html:form>
	<br>

</body>
</html:html>
