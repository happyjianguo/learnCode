<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>流水统计</title>
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
		<c:if test="${!empty param._merchantId}" >
		var _merchantId = '<c:out value="${param._merchantId}"/>';
		document.forms[0]._merchantId.value=_merchantId;
		</c:if>	
		<c:if test="${!empty param._startDate}" >
		var _startDate = '<c:out value="${param._startDate}"/>';
		document.forms[0]._startDate.value=_startDate;
		</c:if>	
		<c:if test="${!empty param._endDate}" >
		var _endDate = '<c:out value="${param._endDate}"/>';
		document.forms[0]._endDate.value=_endDate;
		</c:if>	
	}
	
	function addClick()
	{
		document.forms[0].method.value="showSr";
		document.forms[0].submit();
	}
	function queryDetail(id)
	{
		document.forms[0].method.value="queryDetail";
		document.forms[0]._traceNo.value=id;
		document.forms[0].submit();
	}
	function exportCurTranLs()
	{
		document.forms[0].method.value="exportCurTranLsReport";
		document.forms[0].submit();
	}
	</script>
	
</head>
<shiro:lacksPermission name="posp:queryCurTranLsReport:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:queryCurTranLsReport:view">
<body onload="init()">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				流水统计查询
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:errors />
				<html:form action="/queryCurTranLsReport" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								商户编号  :
							</td>
							<td class="table2_td" width="40%">
								<input type="text" name="_merchantId"  maxlength="20"  style="width:135px;" />
							</td>
							</td>
							<td class="table2_td_title" width="10%">
								交易系统日期:
							</td>
							<td class="table2_td" width="40%">
							<input name="_startDate" size="19" maxlength="12" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" readonly="true"/>
							至
							<input name="_endDate" size="19" maxlength="12" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" readonly="true"/>
							</td>
						</tr>		
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="查询" width="65" height="20" border="0" onclick="return searchClick('queryAll');">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" onclick="resetClick()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="清空" width="65" height="20" border="0"></a>
							</td>
						</tr>
					</table>
					<!----------Table为查询form结束-------->
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table3_border">
						<tr>
							<td class="table3_title">
								查询结果
							</td>
						</tr>
					</table>
					<!-- 用Diaplay Tag来显示 -->
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/queryCurTranLsReport.do">
						<display:column title="商户编号" style="width:10%;text-align:center" property="merchantId" headerClass="sortable" sortable="true" />
						<display:column title="商户名称" style="width:10%;text-align:center" property="merchantName" headerClass="sortable" sortable="true" />
						<display:column title="总笔数" style="width:5%;text-align:center" property="sumls" headerClass="sortable" sortable="true" />
						<display:column title="总金额" style="width:5%;text-align:center" property="amtTotal" headerClass="sortable" sortable="true" />
						<display:column title="消费笔数 " style="width:5%;text-align:center" property="purchase" headerClass="sortable" sortable="true" />
						<display:column title="消费金额" style="width:5%;text-align:center" property="purchaseTotal" headerClass="sortable" sortable="true" />
						<display:column title="退货笔数 " style="width:5%;text-align:center" property="refund" headerClass="sortable" sortable="true" />
						<display:column title="退货金额" style="width:5%;text-align:center" property="refundTotal" headerClass="sortable" sortable="true" />
					</display:table>
					
					<!-- 用Diaplay Tag来显示 -->		
			</td>
		</tr>
		<tr>
			<td class="table2_td_title" align="right">
				<shiro:hasPermission name="posp:queryCurTranLsReport:export">
				<input type="button" value="流水导出" onclick="exportCurTranLs();"/>
				</shiro:hasPermission>
			</td>
		</tr>
	</table>
	<%@include file="../common/getDisplayParams1.jsp"%>
	</html:form>
	<br>

</body>
</shiro:hasPermission>
</html:html>
