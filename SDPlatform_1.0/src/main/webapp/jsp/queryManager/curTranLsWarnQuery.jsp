<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>交易预警</title>
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
		<c:if test="${!empty param._merno}" >
		var _merno = '<c:out value="${param._merno}"/>';
		document.forms[0]._merno.value=_merno;
		</c:if>	
		<c:if test="${!empty param._rulecode}" >
		var _rulecode = '<c:out value="${param._rulecode}"/>';
		document.forms[0]._rulecode.value=_rulecode;
		</c:if>	
	}
	
	function openTrancNo(traceNo)
	{
		window.showModalDialog("queryCurTranLs.do?method=queryDetail&read=true&_traceNo="+traceNo, "_blank", 'dialogWidth=800px;dialogHeight=440px;scroll=yes;resizable=no;status=no;center=yes;');
	}
	function openConfigId(configId)
	{
		window.showModalDialog("ruleManager.do?method=queryRuleOne&read=true&_configId="+configId, "_blank", 'dialogWidth=800px;dialogHeight=500px;scroll=yes;resizable=no;status=no;center=yes;');
	}
	function queryDetail(id)
	{
		document.forms[0].method.value="queryDetail";
		document.forms[0]._traceNo.value=id;
		document.forms[0].submit();
	}
	function exportCurTranLs()
	{
		document.forms[0].method.value="exportCurTranLsWarn";
		document.forms[0].submit();
	}
	function query()
	{
		document.forms[0].method.value="queryAll";
		document.forms[0].submit();
	}
	window.setInterval(query,10000);
	</script>
	
</head>
<shiro:lacksPermission name="posp:queryCurTranLsWarn:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:queryCurTranLsWarn:view">
<body onload="init()">

	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				预警列表
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:errors />
				<html:form action="/queryCurTranLsWarn" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								交易流水号:
							</td>
							<td class="table2_td" width="40%">
							<input type="text" name="_traceNo"  maxlength="20"  style="width:135px;" />
							</td>
							<td class="table2_td_title" width="10%">
								卡号:
							</td>
							<td class="table2_td" width="40%">
							<input type="text" name="_cardNo"  maxlength="20"  style="width:135px;" />
							</td>
						</tr>		
						<tr>
							<td class="table2_td_title" width="10%">
								商户号:
							</td>
							<td class="table2_td" width="40%">
							<input type="text" name="_merno"  maxlength="20"  style="width:135px;" />
							</td>
							<td class="table2_td_title" width="10%">
								规则ID:
							</td>
							<td class="table2_td" width="40%">
							<input type="text" name="_rulecode"  maxlength="20"  style="width:135px;" />
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/queryCurTranLsWarn.do">
						<display:column title="POS批次号" style="width:5%;text-align:center" property="batno" headerClass="sortable" sortable="true" />
						<display:column title="卡号" style="width:10%;text-align:center" property="cardno" headerClass="sortable" sortable="true" />
						<display:column title="交易流水号" style="width:10%;text-align:center" headerClass="sortable" sortable="true" >
							<a href="javascript:void(0);" onclick="openTrancNo('${displayTable.tranceno }')" ><font color="blue">${displayTable.tranceno }</font></a>
						</display:column>
						<display:column title="商户号" style="width:10%;text-align:center" property="merno" headerClass="sortable" sortable="true" />
						<display:column title="交易金额" style="width:5%;text-align:center" property="tranamt" headerClass="sortable" sortable="true" />
						<display:column title="规则ID" style="width:10%;text-align:center" headerClass="sortable" sortable="true" >
							<a href="javascript:void(0);" onclick="openConfigId('${displayTable.rulecode }')" ><font color="blue">${displayTable.rulecode }</font></a>
						</display:column>
						<display:column title="处理动作 " style="width:5%;text-align:center" headerClass="sortable" sortable="true" >
						<c:if test="${displayTable.actionstatus eq '1'}">提醒</c:if>
						<c:if test="${displayTable.actionstatus eq '2'}">移交</c:if>
						<c:if test="${displayTable.actionstatus eq '3'}">托收</c:if>
						<c:if test="${displayTable.actionstatus eq '4'}">拒绝</c:if>
						<c:if test="${displayTable.actionstatus eq '5'}">管控</c:if>
						</display:column>
						<display:column title="登记时间" style="width:10%;text-align:center" property="cteatetime" headerClass="sortable" sortable="true" />
					</display:table>
					
					<!-- 用Diaplay Tag来显示 -->		
			</td>
		</tr>
		<tr>
			<td class="table2_td_title" align="right">
				<shiro:hasPermission name="posp:queryCurTranLsWarn:export">
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
