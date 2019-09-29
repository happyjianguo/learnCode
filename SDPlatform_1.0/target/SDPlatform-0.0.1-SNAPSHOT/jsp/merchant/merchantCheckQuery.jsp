<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>商户信息审核</title>
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
		<c:if test="${!empty param.queryMerid}" >
		var queryMerid = '<c:out value="${param.queryMerid}"/>';
		document.forms[0].queryMerid.value=queryMerid;
		</c:if>	
		<c:if test="${!empty param.queryMerstat}" >
		var queryMerstate = '<c:out value="${param.queryMerstat}"/>';
		document.forms[0].queryMerstat.value=queryMerstate;
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
<shiro:lacksPermission name="posp:merchantCheck:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:merchantCheck:view">
<body onload="init();">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				商户信息审核列表 
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/merchantCheck" method="post">
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
								商户状态: 
							</td>
							<td class="table2_td" width="40%">
							<html:select property="queryMerstat" style="width:130px;" value="">
								<html:option value="">- 状态 -</html:option>
								<html:option value="Y">正常</html:option>
								<html:option value="N">冻结</html:option>
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/merchantCheck.do">
						<display:column title="商户编号" style="width:15%;text-align:center" property="merchantId" headerClass="sortable" sortable="true" />
						<display:column title="商户简称" style="width:12%;text-align:center" property="abbrCname" headerClass="sortable" sortable="true" />
						<display:column title="商户类型" style="width:12%;text-align:center" property="mccName" headerClass="sortable" sortable="true" />
						<display:column title="签约行行号" style="width:12%;text-align:center" property="signBankId" headerClass="sortable" sortable="true" />
						<display:column title="签约行主机号" style="width:8%;text-align:center" property="signHostId" headerClass="sortable" sortable="true" />
						<display:column title="提交日期时间" style="width:12%;text-align:center" headerClass="sortable" sortable="true" >
						<c:out value="${displayTable.updateDate } ${displayTable.updateTime }"></c:out>
						</display:column>
						<display:column title="操作" style="width:8%;text-align:center">
						<shiro:hasPermission name="posp:merchantInfo:edit">
							<a href="#" onclick="editClicks('<c:out value="${displayTable.merchantId}"/>','findItem')">审核</a>
						</shiro:hasPermission>
						</display:column>
						
					</display:table>
					<!-- 用Diaplay Tag来显示 -->		
					
					<input type="hidden" name="id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="merchantId" />
					
			</td>
		</tr>
	</table>
	<%@include file="../common/getDisplayParams1.jsp"%>
	</html:form>
	<br>
</body>
</shiro:hasPermission>
</html:html>
