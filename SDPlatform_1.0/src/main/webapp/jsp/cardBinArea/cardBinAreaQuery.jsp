<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>区域卡卡BIN</title>
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
		<c:if test="${!empty param.queryCardBin}" >
		var queryCardBin = '<c:out value="${param.queryCardBin}"/>';
		document.forms[0].queryCardBin.value=queryCardBin;
		</c:if>	
		<c:if test="${!empty param.queryAreaCode}" >
		var queryAreaCode = '<c:out value="${param.queryAreaCode}"/>';
		document.forms[0].queryAreaCode.value=queryAreaCode;
		</c:if>	
		<c:if test="${!empty param.queryStatus}" >
		var queryStatus = '<c:out value="${param.queryStatus}"/>';
		document.forms[0].queryStatus.value=queryStatus;
		</c:if>	
	}
	
	function addClick()
	{
		document.forms[0].method.value="findItem";
		document.forms[0].submit();
	}
	function editClicks(params,method)
	{
		document.forms[0].method.value=method;
		document.forms[0].cardBin.value=params;
		document.forms[0].submit();
		
	}
	function resetClick1(){
		document.forms[0].queryCardBin.value="";
		document.forms[0].queryAreaCode.value="";		
		document.forms[0].queryStatus.value="";
	}
	function findArea()
	{
		var return_value = window.showModalDialog("merchant.do?method=showFindAreaPage");
		if(return_value!=undefined){
			document.forms[0].queryAreaCode.value=return_value.RTrim();
		}
	}	
	//删除后面空格
	String.prototype.RTrim   =   function(){   
		return   this.replace(/(\s*$)/g,"");   
	}
	
	</script>
	
</head>
<shiro:lacksPermission name="posp:cardBinArea:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:cardBinArea:view">

<body onload="init()">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				区域卡卡BIN列表    
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
			<html:javascript formName="cardBinAreaForm" />
				<html:errors />
				<html:form action="/cardBinArea" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">  
								卡bin:  
							</td>
							<td class="table2_td" width="20%">
							<html:text property="queryCardBin" size="19" maxlength="9" />
							</td>
							<td class="table2_td_title"  width="10%">
								地区:
							</td>
							<td class="table2_td" width="30%">
								<input type="text" name="queryAreaCode" id="queryAreaCode" size="30" maxlength="4" readonly="readonly" />
								<input type="button" value="选择" onclick="findArea();">
							</td>
							<td class="table2_td_title" width="10%">  
								状态： 
							</td>
							<td class="table2_td" >
								<html:select property="queryStatus" style="width:130px;" value="">
									  <option value="">- 请选择 -</option>
									  <option value="1">有效</option>
									  <option value="0">无效</option>
							    </html:select>
							</td>						
						</tr>				
						<tr>
							<td colspan="6" class="table2_btn">
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/cardBinArea.do">
						<display:column title="选择" style="width:5%;text-align:center">							
							<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.cardBin}"/>" />						
						</display:column>
						<display:column title="卡bin" style="width:10%;text-align:center" property="cardBin" headerClass="sortable" sortable="true" />
						<display:column title="地区名称" style="width:20%;text-align:center" property="areaCodeName" headerClass="sortable" sortable="true" />
						<display:column title="状态" style="width:13%;text-align:center" headerClass="sortable" sortable="true" >
							<c:if test="${displayTable.status eq '0'}">无效</c:if>
							<c:if test="${displayTable.status eq '1'}">有效</c:if>
						</display:column>						
						<display:column title="操作" style="width:10%;text-align:center">
							<shiro:hasPermission name="posp:cardBinArea:edit">						
								<a href="#" onclick="editClicks('<c:out value="${displayTable.cardBin }"/>','findItem')">编辑</a>
							</shiro:hasPermission>
						</display:column>
					</display:table>
					<!-- 用Diaplay Tag来显示 -->
					<input type="hidden" name="id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="cardBin" />
					<!----------以下Table用操作选择-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								是否全选
							</td>
							<td>
								<shiro:hasPermission name="posp:cardBinArea:delete">
									<a href="#"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="删除" width="65" height="20" border="0" onclick="deleteClick('selectItems','checkItem','deleteItem');"></a> 
								</shiro:hasPermission>
								<shiro:hasPermission name="posp:cardBinArea:add">
									<a href="#" onclick="addClick()">
										<img border=0 name="imageField322" src="<fmt:message key='CommonImagePath' />btnAdd.gif" alt="增加" width="65" height="20" border="0"></a>
								</shiro:hasPermission>
							</td>
							<td width="240" align="right">
								&nbsp;
							</td>
						</tr>
					</table>
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