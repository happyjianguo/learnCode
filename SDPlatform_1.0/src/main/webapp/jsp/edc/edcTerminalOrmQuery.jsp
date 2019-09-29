<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>旧福卡银行终端管理</title>
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
		<c:if test="${!empty param.queryMerchantId}" >
		var queryMerchantId = '<c:out value="${param.queryMerchantId}"/>';
		document.forms[0].queryMerchantId.value=queryMerchantId;
		</c:if>	
		<c:if test="${!empty param.queryTerminalId}" >
		var queryTerminalId = '<c:out value="${param.queryTerminalId}"/>';
		document.forms[0].queryTerminalId.value=queryTerminalId;
		</c:if>	
		<c:if test="${!empty param.queryBankTerminalId}" >
		var queryBankTerminalId = '<c:out value="${param.queryBankTerminalId}"/>';
		document.forms[0].queryBankTerminalId.value=queryBankTerminalId;
		</c:if>	
		<c:if test="${!empty param.queryLogonStatus}" >
		var queryLogonStatus = '<c:out value="${param.queryLogonStatus}"/>';
		document.forms[0].queryLogonStatus.value=queryLogonStatus;
		</c:if>		
	}
	function addClick()
	{
		document.forms[0].method.value="queryEdcTerminalOrmByKey";
		document.forms[0].submit();
	}
	function editClicks(id,method)
	{
		document.forms[0].method.value=method;
		document.forms[0].merchantId.value=id;
		document.forms[0]._id.value=id;
		document.forms[0].submit();		
	}
	function downloadTemplate(){
		document.forms[0].method.value="downloadTemplate";
		document.forms[0].submit();
	}
	function fileUpload(){
		var fileName = document.getElementById("formShowData").upload.value;
		 if(!fileName){
			 alert("请选择要导入的文件!");
	         return false;
		 }
	     var strRegex = "(.xlsx|.xls)$";
	     var re=new RegExp(strRegex);
	     if (!re.test(fileName.toLowerCase())){
	         alert("文件格式不合法，必须为EXCEL文档!");
	         return false;
	     }
	     document.getElementById("formShowData").submit();
	}
	function setSettle(_bankmerchantId,_bankterminalId)
	{
		document.forms[0]._bankmerchantId.value=_bankmerchantId;
		document.forms[0]._bankterminalId.value=_bankterminalId;
		document.forms[0].method.value="settle";
		document.forms[0].submit();
	}
	function setLogin(_bankmerchantId,_bankterminalId)
	{
		document.forms[0]._bankmerchantId.value=_bankmerchantId;
		document.forms[0]._bankterminalId.value=_bankterminalId;
		document.forms[0].method.value="login";
		document.forms[0].submit();
	}
	</script>
</head>
<shiro:lacksPermission name="posp:terminalorm:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:terminalorm:view">
<body onload="init();">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				旧福卡银行终端列表
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/edcTerminalOrm" method="post">
					<input type="hidden" name="method" value="queryEdcTerminalOrm" />
					<input type="hidden" name="_bankmerchantId" value="" />
					<input type="hidden" name="_bankterminalId" value="" />
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								商户编号:
							</td>
							<td class="table2_td" width="20%">
							<input name="queryMerchantId" type="text"  maxlength="15"  style="width:135px;">
							</td>
                            <td class="table2_td_title" width="10%">
								终端号:
							</td>
							<td class="table2_td" width="20%">							
							<input name="queryTerminalId" type="text"  maxlength="8"  style="width:135px;">
							</td>
							<td class="table2_td_title" width="10%">
								银行终端号:
							</td>
							<td class="table2_td" width="20%">							
							<input name="queryBankTerminalId" type="text"  maxlength="8"  style="width:135px;">
							</td>
                            <td class="table2_td_title" width="10%">
								状态:
							</td>
							<td class="table2_td" width="20%">							
								<select name="queryLogonStatus">
								 <option value="">--请选择-- </option>
								 <option value="0">签退 </option>
								 <option value="1">签到</option>
								 <option value="2">异常</option>
								</select>
							</td>
						</tr>				
						<tr>
							<td colspan="8" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="查询" width="65" height="20" border="0" onclick="return searchClick('queryEdcTerminalOrm');">
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/edcTerminalOrm.do">
						<display:column title="选择" style="width:10%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.merchantId}#${displayTable.terminalId}#${displayTable.moduleId}#end"/>">
							
						</display:column>
						<display:column title="商户编号" style="width:10%;text-align:center" property="merchantId" headerClass="sortable" sortable="true" />
						<display:column title="终端编号" style="width:12%;text-align:center" property="terminalId" headerClass="sortable" sortable="true" />
						<display:column title="银行商户号" style="width:13%;text-align:center" property="bankMerchantId" headerClass="sortable" sortable="true" />
						<display:column title="银行终端号" style="width:15%;text-align:center" property="bankTerminalId" headerClass="sortable" sortable="true" />
						<display:column title="批次号" style="width:10%;text-align:center" property="batchNo" headerClass="sortable" sortable="true" />
						<display:column title="流水号" style="width:10%;text-align:center" property="sysTrace" headerClass="sortable" sortable="true" />
						<display:column title="状态" style="width:10%;text-align:center" headerClass="sortable" sortable="true">
							<c:if test="${displayTable.logonStatus eq '0'}">签退</c:if>
							<c:if test="${displayTable.logonStatus eq '1'}">签到</c:if>
							<c:if test="${displayTable.logonStatus eq '2'}">异常</c:if>
						</display:column>
						<display:column title="操作" style="width:10%;text-align:center">
                        <shiro:hasPermission name="posp:terminalorm:edit">
						   <a href="#" onclick="javascript:editClicks('<c:out value="${displayTable.merchantId}#${displayTable.terminalId}#${displayTable.moduleId}#end"/>','queryEdcTerminalOrmByKey')">查看</a>
						   <a href="#" onclick="javascript:setSettle('${displayTable.bankMerchantId}','${displayTable.bankTerminalId}')">结算</a>
						   <a href="#" onclick="javascript:setLogin('${displayTable.bankMerchantId}','${displayTable.bankTerminalId}')">签到</a>
                        </shiro:hasPermission>
						</display:column>
					</display:table>
					<!-- 用Diaplay Tag来显示 -->		
					
					<input type="hidden" name="_id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="merchantId" />
					
					<!----------以下Table用操作选择-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								是否全选
							</td>
							<td>
                                <shiro:hasPermission name="posp:terminalorm:delete">
								<a href="javascript:deleteClick('selectItems','checkItem','deleteEdcTerminalOrm');"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="删除" width="65" height="20" border="0"></a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="posp:terminalorm:add">
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
	<shiro:hasPermission name="posp:terminalorm:upload">
	<center>			
	<form id='formShowData' name="formShowData" enctype='multipart/form-data' method='post' action="edcTerminalOrmUpload.do" >
	<input type="hidden" name="method" value="fileUpload" />
	<input type="file" name='upload' id='upload' /><input type="button" value="导入" onclick="fileUpload();" />
	<a href="javascript:;" onclick="downloadTemplate();">下载导入模板</a>
	</form>
	</center>
	</shiro:hasPermission>
</body>
</shiro:hasPermission>
</html:html>