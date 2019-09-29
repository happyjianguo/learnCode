<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>日志查询</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" language="JavaScript"  src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script type="text/javascript" language="JavaScript"  src="<fmt:message key='JavaScriptPath' />listPage.js"></script>
	<script type="text/javascript"  language="JavaScript" src="<fmt:message key='JavaScriptPath' />date.js"></script>
	<script type="text/javascript" language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script type="text/javascript" language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />DatePicker/WdatePicker.js" ></script>
	
	<script language="javascript">
	function init()
	{
		//<c:if test="${!empty param.search_sort}" >
		//var search_sort = '<c:out value="${param.search_sort}"/>';
		//document.forms[0].search_sort.value=search_sort;
		//</c:if>
		
		<c:if test="${!empty param.logType}" >
		var logType = '<c:out value="${param.logType}"/>';
		document.forms[0].logType.value=logType;
		</c:if>	
		<c:if test="${!empty param.logDate}" >
		var logDatee = '<c:out value="${param.logDate}"/>';
		document.forms[0].logDate.value=logDatee;
		</c:if>	
	}
	
	function editClicks(id,method)
	{
		//alert(id+","+method);
		document.forms[0].method.value=method;
		document.forms[0].logId.value=id;
		document.forms[0].submit();
		
	}
	
	function resetClick1(){
		document.forms[0].logType.value="";
		document.forms[0].logDate.value="";
	}
	</script>
	
</head>

<body onload="init();">

	<!--------------以下Table为路径-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">  
				当前位置：收单系统管理平台 >> 日志管理 >> 日志查询
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
				操作日志列表 
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/log" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%"> 
								日志类型: 
							</td>
							<td class="table2_td" width="40%">
								<select name="logType">
									<option value="">--请选择--</option>
									<option value="1">敏感信息操作</option>
									<option value="2">其它</option>
								</select>
							</td>
							<td class="table2_td_title" width="10%"> 
								时间: 
							</td>
							<td class="table2_td" width="40%">
								<input name="logDate" size="19" maxlength="12" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" readonly="true"/>
							</td>						
						</tr>				
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="查询" width="65" height="20" border="0" onclick="return searchClick('queryAll');">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" onclick="resetClick1()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="清空" width="65" height="20" border="0"></a>
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/log.do">
						<display:column title="日志编号" style="width:15%;text-align:center" property="logId" headerClass="sortable" sortable="true" />
						<display:column title="日志类型" style="width:6%;text-align:center" headerClass="sortable" sortable="true" >
						<c:if test='${displayTable.type eq "1"}'>敏感信息操作</c:if>
						<c:if test='${displayTable.type eq "2"}'>其它</c:if>
						</display:column>
						<display:column title="操作人" style="width:12%;text-align:center" property="createOper" headerClass="sortable" sortable="true" />
						<display:column title="操作日期" style="width:12%;text-align:center" property="createDate" headerClass="sortable" sortable="true" />
						<display:column title="操作时间" style="width:12%;text-align:center" property="createTime" headerClass="sortable" sortable="true" />
						<display:column title="操作" style="width:8%;text-align:center">
							<a href="#" onclick="editClicks('<c:out value="${displayTable.logId}"/>','view')">详细</a>
						</display:column>
						
					</display:table>
					<!-- 用Diaplay Tag来显示 -->		
					
					<input type="hidden" name="id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="logId" />
					
					<!----------以下Table用操作选择------
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								是否全选
							</td>
							<td>
								<c:if test="${currentUserData.groupMap.x010201x03=='yes'}">
									<a href="#"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="删除" width="65" height="20" border="0" onclick="deleteClick('selectItems','checkItem','deleteItem');"></a> 
								</c:if>
								<c:if test="${currentUserData.groupMap.x010201x01=='yes'}">
									<a href="#" onclick="addClick()">
										<img border=0 name="imageField322" src="<fmt:message key='CommonImagePath' />btnAdd.gif" alt="增加" width="65" height="20" border="0"></a>
								</c:if>
							</td>
							<td width="240" align="right">
								&nbsp;
							</td>
						</tr>
					</table>-->
					<!----------TTable用操作选择结束-------->
					
			</td>
		</tr>
	</table>
	<%@include file="../common/getDisplayParams1.jsp"%>
	</html:form>
	<br>
</body>
</html:html>
