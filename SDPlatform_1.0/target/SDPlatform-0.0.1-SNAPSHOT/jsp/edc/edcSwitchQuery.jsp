<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>终端转换</title>
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
		<c:if test="${!empty param._bankType}" >
		var _bankType = '<c:out value="${param._bankType}"/>';
		document.forms[0]._bankType.value=_bankType;
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
		document.forms[0]._id.value=id;
		document.forms[0].submit();
		
	}
	
	</script>
</head>

<body onload="init();">

	<!--------------以下Table为路径-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">
				当前位置：收单系统管理平台 >> 终端机管理 >> 终端转换
			</td>
		</tr>
		<tr>
			<td height="10">
			</td>
		</tr>
	</table>
	<!--------------Table为路径结束-------->


	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				终端转换列表
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/edcSwitch" method="post">
					<input type="hidden" name="method" value="queryEdcSwitch" />
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								商户编号:
							</td>
							<td class="table2_td" width="40%">
							<input name="_merchantId" type="text"  maxlength="15"  style="width:135px;">
							</td>
                               <td class="table2_td_title" width="10%">
								银行类型:
							</td>
							<td class="table2_td" width="40%">	
							<select name="_bankType">
							   <option value=""></option>
							   <c:forEach var="model" items="${bankTypeList}">
							     <option value="<c:out value="${model.bankType}"/>"><c:out value="${model.typeName}" /></option>
							   </c:forEach>
							</select>	
							</td>
						</tr>				
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="查询" width="65" height="20" border="0" onclick="return searchClick('queryEdcSwitch');">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:resetClick()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="清空" width="65" height="20" border="0"></a>
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/edcSwitch.do">
						<display:column title="选择" style="width:10%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.id.merchantId}#${displayTable.id.bankType}#${displayTable.id.terminalId}#${displayTable.othTerminalId}#end"/>">
							
						</display:column>
						<display:column title="商户编号" style="width:20%;text-align:center" property="id.merchantId" headerClass="sortable" sortable="true" />
						<display:column title="终端编号" style="width:20%;text-align:center" property="id.terminalId" headerClass="sortable" sortable="true" />						
						<display:column title="银行类型" style="width:20%;text-align:center" property="id.bankTypeName" headerClass="sortable" sortable="true" />
						<display:column title="他行终端号" style="width:20%;text-align:center" property="othTerminalId" headerClass="sortable" sortable="true" />
						<display:column title="操作" style="width:20%;text-align:center">
                        <c:if test="${currentUserData.groupMap.x010303x02=='yes'}">
						   <a href="#" onclick="javascript:editClicks('<c:out value="${displayTable.id.merchantId}#${displayTable.id.bankType}#${displayTable.id.terminalId}#${displayTable.othTerminalId}#end"/>','queryEdcSwitchByKey')">编辑</a>
                        </c:if> 
						</display:column>
						
					</display:table>
					<!-- 用Diaplay Tag来显示 -->		
					
					<input type="hidden" name="_id" />		
					<input type="hidden" name="selectItems" />
					
					<!----------以下Table用操作选择-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								是否全选
							</td>
							<td>
                                <c:if test="${currentUserData.groupMap.x010303x03=='yes'}">
								<a href="javascript:deleteClick('selectItems','checkItem','deleteEdcSwitch');"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="删除" width="65" height="20" border="0"></a>
                                </c:if> 
                                <c:if test="${currentUserData.groupMap.x010303x01=='yes'}">
								<a href="javascript:addClick()">
								<img border=0 name="imageField322" src="<fmt:message key='CommonImagePath' />btnAdd.gif" alt="增加" width="65" height="20" border="0" ></a>
                                </c:if>
							</td>
							<td width="240" align="right">
								&nbsp;
							</td>
						</tr>
					</table>
					<!----------TTable用操作选择结束-------->
					<!-- 维护视图状态的隐藏域 -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<input type="hidden" name="search_name" value="<c:out value="${param.search_name}"/>" />
					<!-- 维护视图状态的隐藏域 -->
					</html:form>
					
			</td>
		</tr>
	</table>
</body>
</html:html>
