<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>商户身份信息</title>
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
	<script language="javascript">
	function init()
	{
		//<c:if test="${!empty param.search_sort}" >
		//var search_sort = '<c:out value="${param.search_sort}"/>';
		//document.forms[0].search_sort.value=search_sort;
		//</c:if>
		
		<c:if test="${!empty param.queryMerid}" >
		var queryMerid = '<c:out value="${param.queryMerid}"/>';
		document.forms[0].queryMerid.value=queryMerid;
		</c:if>	
		<c:if test="${!empty param.queryMerstat}" >
		var queryMerstat = '<c:out value="${param.queryMerstat}"/>';
		document.forms[0].queryMerstat.value=queryMerstat;
		</c:if>	
	}
	
	function addClick()
	{
		document.forms[0].method.value="findItem";
		document.forms[0].submit();
	}
	function editClicks(id,method)
	{
		//alert(id+","+method);
		document.forms[0].method.value=method;
		document.forms[0].merchantId.value=id;
		document.forms[0].submit();
		
	}
	
	function resetClick1(){
		document.forms[0].queryMerid.value="";
		document.forms[0].queryMerstat.value="";
	}
	</script>
	
</head>
<shiro:lacksPermission name="posp:merchantIdentity:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:merchantIdentity:view">
<body onload="init();">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				商户身份列表 
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/merchantIdentity" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%"> 
								商户编号: 
							</td>
							<td class="table2_td" width="40%">
							<input name="queryMerid" size="19" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'');"/>
							</td>
							<td class="table2_td_title" width="10%"> 
								商户等级: 
							</td>
							<td class="table2_td" width="40%">
							<html:select property="queryMerstat" style="width:130px;" value="">
								<html:option value="">- 等级 -</html:option>
								<html:option value="A">A</html:option>
								<html:option value="B">B</html:option>
								<html:option value="C">C</html:option>
								<html:option value="D">D</html:option>
							</html:select>
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/merchantIdentity.do">
						<display:column title="商户编号" style="width:15%;text-align:center" property="merchantId" headerClass="sortable" sortable="true" />
						<display:column title="商户等级" style="width:12%;text-align:center" property="classType" headerClass="sortable" sortable="true" />
						<display:column title="证件到期" style="width:6%;text-align:center" headerClass="sortable" sortable="true" >
						<c:if test='${displayTable.warn eq "Y"}'>到期</c:if>
						<c:if test='${displayTable.warn eq "N"}'>正常</c:if>
						</display:column>
						<display:column title="法人" style="width:6%;text-align:center" property="legalName" headerClass="sortable" sortable="true" />
						<display:column title="经办人" style="width:12%;text-align:center" property="attnName" headerClass="sortable" sortable="true" />
						<display:column title="更新者" style="width:10%;text-align:center" property="updateOper" headerClass="sortable" sortable="true" />
						<display:column title="更新日期时间" style="width:12%;text-align:center" headerClass="sortable" sortable="true" >
						<c:out value="${displayTable.updateDate } ${displayTable.updateTime }"></c:out>
						</display:column>
						<display:column title="操作" style="width:8%;text-align:center">
						<shiro:hasPermission name="posp:merchantIdentity:edit">
							<a href="#" onclick="editClicks('<c:out value="${displayTable.merchantId}"/>','findItem')">编辑</a>
						</shiro:hasPermission>
						</display:column>
						
					</display:table>
					<!-- 用Diaplay Tag来显示 -->		
					
					<input type="hidden" name="id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="merchantId" />
					
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
</shiro:hasPermission>
</html:html>

