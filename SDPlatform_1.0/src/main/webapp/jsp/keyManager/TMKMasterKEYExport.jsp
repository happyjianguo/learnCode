<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>终端主密钥</title>
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
	function init(){
		<c:if test="${!empty param._merchantId}" >
		var _merchantId = '<c:out value="${param._merchantId}"/>';
		document.forms[0]._merchantId.value=_merchantId;
		</c:if>	
		<c:if test="${!empty param._terminalId}" >
		var _terminalId = '<c:out value="${param._terminalId}"/>';
		document.forms[0]._terminalId.value=_terminalId;
		</c:if>
		<c:if test="${!empty param.querySettleFlag}" >
		var querySettleFlag = '<c:out value="${param.querySettleFlag}"/>';
		document.forms[0].querySettleFlag.value=querySettleFlag;
		</c:if>
	}
	function exportExcel(){
		document.forms[0].method.value="exportExcel";
		document.forms[0].submit();
	}
	function exportTxT(){
		document.forms[0].method.value="exportTxT";
		document.forms[0].submit();
	}
	</script>
</head>
<shiro:lacksPermission name="posp:tmkmasterkey:download">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:tmkmasterkey:download">
<body onload="init();">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				终端主密钥列表
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/TMKMasterKEY" method="post">
					<input type="hidden" name="method" value="queryExport" />
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								商户编号:
							</td>
							<td class="table2_td" width="20%">
								<input name="_merchantId" type="text"  maxlength="15"  style="width:135px;">
							</td>
                               <td class="table2_td_title" width="10%">
								终端编号:
							</td>
							<td class="table2_td" width="20%">	
								<input name="_terminalId" type="text"  maxlength="8"  style="width:135px;">
							</td>
							 <td class="table2_td_title" width="10%">
								状态:
							</td>
							<td class="table2_td" width="30%">
								<html:select property="querySettleFlag" style="width:130px;">
									  <option value="1">未签到</option>
									  <option value="2">已签到</option>
							    </html:select>
							</td>
						</tr>				
						<tr>
							<td colspan="6" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="查询" width="65" height="20" border="0" onclick="return searchClick('queryExport');">
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/TMKMasterKEY.do">
						<display:column title="商户编号" style="width:20%;text-align:center" property="merchantId" headerClass="sortable" sortable="true" />
						<display:column title="终端编号" style="width:20%;text-align:center" property="terminalId" headerClass="sortable" sortable="true" />						
						<display:column title="operNo" style="width:20%;text-align:center" property="operNo" headerClass="sortable" sortable="true" />
						<display:column title="状态" style="width:20%;text-align:center" headerClass="sortable" sortable="true" >
						<c:if test="${displayTable.settleFlag eq '1'}">未签到</c:if>
						<c:if test="${displayTable.settleFlag eq '2'}">已签到</c:if>
						</display:column>
					</display:table>
					<!-- 用Diaplay Tag来显示 -->		
					
					<!-- 维护视图状态的隐藏域 -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<input type="hidden" name="search_name" value="<c:out value="${param.search_name}"/>" />
					<!-- 维护视图状态的隐藏域 -->
					</html:form>
					
			</td>
		</tr>
		<tr>
			<td class="table2_td_title" align="right">
				<input type="button" value="导出Excel文件查询结果" onclick="exportExcel();"/>
				<input type="button" value="导出TxT文件查询结果" onclick="exportTxT();"/>
			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>