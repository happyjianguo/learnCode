<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>������ˮ�б�</title>
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
		<c:if test="${!empty param._traceNo}" >
		var _traceNo = '<c:out value="${param._traceNo}"/>';
		document.forms[0]._traceNo.value=_traceNo;
		</c:if>	
		<c:if test="${!empty param._cardNo}" >
		var _cardNo = '<c:out value="${param._cardNo}"/>';
		document.forms[0]._cardNo.value=_cardNo;
		</c:if>	
		<c:if test="${!empty param._startDate}" >
		var _startDate = '<c:out value="${param._startDate}"/>';
		document.forms[0]._startDate.value=_startDate;
		</c:if>	
		<c:if test="${!empty param._endDate}" >
		var _endDate = '<c:out value="${param._endDate}"/>';
		document.forms[0]._endDate.value=_endDate;
		</c:if>	
		<c:if test="${!empty param._startTime}" >
		var _startTime = '<c:out value="${param._startTime}"/>';
		document.forms[0]._startTime.value=_startTime;
		</c:if>	
		<c:if test="${!empty param._endTime}" >
		var _endTime = '<c:out value="${param._endTime}"/>';
		document.forms[0]._endTime.value=_endTime;
		</c:if>	
		<c:if test="${!empty param._merchantName}" >
		var _merchantName = '<c:out value="${param._merchantName}"/>';
		document.forms[0]._merchantName.value=_merchantName;
		</c:if>	
		<c:if test="${!empty param._tranType}" >
		var _tranType = '<c:out value="${param._tranType}"/>';
		document.forms[0]._tranType.value=_tranType;
		</c:if>	
	}
	
	function addClick()
	{
		document.forms[0].method.value="showSr";
		document.forms[0].submit();
	}
	function openTrancNo(traceNo)
	{
		window.showModalDialog("queryCurTranLs.do?method=queryDetail&read=true&_traceNo="+traceNo, "_blank", 'dialogWidth=800px;dialogHeight=440px;scroll=yes;resizable=no;status=no;center=yes;');
	}
	function queryDetail(id)
	{
		document.forms[0].method.value="queryDetail";
		document.forms[0]._traceNo.value=id;
		document.forms[0].submit();
	}
	</script>
	
</head>
<shiro:lacksPermission name="posp:queryCurTranLs:viewQueryDay">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:queryCurTranLs:viewQueryDay">
<body onload="init()">

	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				������ˮ�б�
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:errors />
				<html:form action="/queryCurTranLs" method="post">
					<input type="hidden" name="method" value="queryCurTranLs" />
					<!----------����TableΪ��ѯform-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								������ˮ��:
							</td>
							<td class="table2_td" width="40%">
							<input type="text" name="_traceNo"  maxlength="20"  style="width:135px;" />
							</td>
							<td class="table2_td_title" width="10%">
								����:
							</td>
							<td class="table2_td" width="40%">
							<input type="text" name="_cardNo"  maxlength="20"  style="width:135px;" />
							</td>
						</tr>		
						<tr>
							<td class="table2_td_title" width="15%">
								��������:
							</td>
							<td class="table2_td" width="40%">
							<select type="text" name="_tranType">
								<option value = "">--��ѡ��--</option>
								<c:forEach items="${tranTypeList}" var="model">
									<option value = "${model.paramValue }">${model.id.paramName }</option>
								</c:forEach>
							</select>
							</td>
							<td class="table2_td_title" width="15%">
							</td>
							<td class="table2_td" width="40%">
							</td>
						</tr>	
						<tr>
							<td class="table2_td_title" width="15%">
								�̻����  :
							</td>
							<td class="table2_td" width="40%">
								<input type="text" name="_merchantName"  maxlength="20"  style="width:135px;" />
							</td>
							<td class="table2_td_title" width="15%">
								����ϵͳʱ��:
							</td>
							<td class="table2_td" width="40%">
							<input name="_startTime" size="19" maxlength="12" onclick="WdatePicker({dateFmt:'HHmmss'})" class="Wdate" readonly="true"/>
							��
							<input name="_endTime" size="19" maxlength="12" onclick="WdatePicker({dateFmt:'HHmmss'})" class="Wdate" readonly="true"/>
							</td>
						</tr>		
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="��ѯ" width="65" height="20" border="0" onclick="return searchClick('queryCurTranLs');">
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/queryCurTranLs.do">
						<display:column title="������ˮ��" style="width:10%;text-align:center" property="traceNo" headerClass="sortable" sortable="true" />
						<display:column title="����" style="width:10%;text-align:center" property="cardNo" headerClass="sortable" sortable="true" />
						<display:column title="�̻����" style="width:15%;text-align:center" property="merchantId" headerClass="sortable" sortable="true" />
						<display:column title="�������� " style="width:5%;text-align:center" headerClass="sortable" sortable="true" >
							<c:forEach var="model" items="${tranTypeList}">
								<c:if test="${displayTable.tranType eq model.paramValue}">
									${model.paramChinese}
								</c:if>
							</c:forEach>
						</display:column>
						<display:column title="���׽�� " style="width:5%;text-align:center" property="tranAmt" headerClass="sortable" sortable="true" />
						<display:column title="����" style="width:5%;text-align:center" headerClass="sortable" sortable="true" >
							<c:out value="${displayTable.localSysDate}"/>
						</display:column>
						<display:column title="ʱ�� " style="width:5%;text-align:center" headerClass="sortable" sortable="true" >
							<c:out value="${displayTable.localSysTime}"/>
						</display:column>
						<display:column title="����" style="width:5%;text-align:center">
 						<shiro:hasPermission name="posp:queryCurTranLs:detailQueryDay">
						<a href="javascript:;" onclick="openTrancNo('${displayTable.traceNo }')" >��ϸ</a>
						</shiro:hasPermission>
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
</shiro:hasPermission>
</html:html>
