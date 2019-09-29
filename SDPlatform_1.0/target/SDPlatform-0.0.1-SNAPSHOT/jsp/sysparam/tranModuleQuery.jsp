<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>交易模块信息</title>
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
		<c:if test="${!empty param._moduleId}" >
		var _moduleId = '<c:out value="${param._moduleId}"/>';
		document.forms[0]._moduleId.value=_moduleId;
		</c:if>	
		<c:if test="${!empty param._flag}" >
		var _flag = '<c:out value="${param._flag}"/>';
		document.forms[0]._flag.value=_flag;
		</c:if>	
	}
	function addClick()
	{
		document.forms[0].method.value="findItem";
		document.forms[0].submit();
	}
	function editClicks(id,method)
	{
		document.forms[0].method.value=method;
		document.forms[0]._id.value=id;
		document.forms[0].submit();
		
	}
	function reset()
	{
		document.forms[0]._moduleId.value="";
		document.forms[0]._tranType.value="";
		document.forms[0]._tranName.value="";
		document.forms[0]._flag.value="";
	}
	
	</script>
</head>
<shiro:lacksPermission name="posp:tranmodule:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:tranmodule:view">
<body onload="init();">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				渠道模块信息列表
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/tranModule" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="15%">
								渠道模块号:
							</td>
							<td class="table2_td" width="40%">
								<input name="_moduleId" type="text" value="${param._moduleId}" maxlength="19"  style="width:135px;">
							</td>
                               <td class="table2_td_title" width="15%">
								开通状态:
							</td>
							<td class="table2_td" width="40%">							
							<select name="_flag" value="${param._flag}" >
							     <option></option>
								 <option value="0" >未开通</option>
								 <option value="1" >正常开通</option>
							</select>
							</td>
						</tr>				
						<tr>
							<td class="table2_td_title" width="15%">
								交易类型代码:
							</td>
							<td class="table2_td" width="40%">
								<input name="_tranType" type="text" value="${param._tranType}" maxlength="19"  style="width:135px;">
							</td>
                            <td class="table2_td_title" width="15%">
                              	交易名称:
							</td>
							<td class="table2_td" width="40%">
								<input name="_tranName" type="text" value="${param._tranName}" maxlength="19"  style="width:135px;">
							</td>
						</tr>				
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="查询" width="65" height="20" border="0" onclick="return searchClick('queryAll');">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:reset()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="清空" width="65" height="20" border="0"></a>
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/tranModule.do">
						<display:column title="选择" style="width:5%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.moduleId}#${displayTable.tranType}#${displayTable.voidTranType}#${displayTable.voidOldTranType}#end"/>">
							
						</display:column>
						<display:column title="渠道模块号" style="width:10%;text-align:center" property="moduleId" headerClass="sortable" sortable="true" />
						<display:column title="交易类型代码" style="width:10%;text-align:center" property="tranType" headerClass="sortable" sortable="true" />
						<display:column title="交易名称" style="width:15%;text-align:center" property="tranName" headerClass="sortable" sortable="true" />
						<display:column title="结算标志" style="width:10%;text-align:center" headerClass="sortable" sortable="true" >
						<c:if test="${displayTable.settFlag eq '0'}">不参加</c:if>
						<c:if test="${displayTable.settFlag eq '1'}">参加(借记)</c:if>
						<c:if test="${displayTable.settFlag eq '2'}">参加(贷记)</c:if>
						</display:column>
						<display:column title="记录流水" style="width:10%;text-align:center" headerClass="sortable" sortable="true" >
						<c:if test="${displayTable.writeLsFlag eq '0'}">不记</c:if>
						<c:if test="${displayTable.writeLsFlag eq '1'}">记录</c:if>
						</display:column>
						<display:column title="MAC校验" style="width:10%;text-align:center" headerClass="sortable" sortable="true" >
						<c:if test="${displayTable.checkMacFlag eq '0'}">不校验</c:if>
						<c:if test="${displayTable.checkMacFlag eq '1'}">校验</c:if>
						</display:column>
						<display:column title="开通状态" style="width:10%;text-align:center" headerClass="sortable" sortable="true" >
						<c:if test="${displayTable.flag eq '0'}">未开通</c:if>
						<c:if test="${displayTable.flag eq '1'}">正常开通</c:if>
						</display:column>
						<display:column title="操作" style="width:5%;text-align:center">
                        <shiro:hasPermission name="posp:tranmodule:edit">
						   <a href="#" onclick="javascript:editClicks('<c:out value="${displayTable.moduleId}#${displayTable.tranType}#${displayTable.voidTranType}#${displayTable.voidOldTranType}#end"/>','findItem')">编辑</a>
                        </shiro:hasPermission>
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
                                <shiro:hasPermission name="posp:tranmodule:delete">
									<a href="javascript:deleteClick('selectItems','checkItem','deleteItem');"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="删除" width="65" height="20" border="0"></a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="posp:tranmodule:add">
								<a href="javascript:addClick()">
									<img border=0 name="imageField322" src="<fmt:message key='CommonImagePath' />btnAdd.gif" alt="增加" width="65" height="20" border="0" ></a>
                                </shiro:hasPermission>
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
</shiro:hasPermission>
</html:html>