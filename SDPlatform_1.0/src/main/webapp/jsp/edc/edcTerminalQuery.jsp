<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>终端资料设定</title>
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
		<c:if test="${!empty param._softVer}" >
		var _softVer = '<c:out value="${param._softVer}"/>';
		document.forms[0]._softVer.value=_softVer;
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
	</script>
</head>
<shiro:lacksPermission name="posp:terminal:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:terminal:view">
<body onload="init();">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				终端资料列表
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/edcTerminal" method="post">
					<input type="hidden" name="method" value="queryEdcTerminal" />
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
								终端号:
							</td>
							<td class="table2_td" width="40%">							
							<input name="_terminalId" type="text"  maxlength="8"  style="width:135px;">
							</td>
						</tr>
						<tr>
							<td class="table2_td_title" width="10%">  
								终端类型: 
							</td>
							<td class="table2_td" width="40%">
							<html:select property="_softVer" style="width:135px;" value="">
								<html:option value="">- 选择业务角色 -</html:option>
								<html:option value="mul">多场景终端</html:option>
								<html:option value="common">普通终端</html:option>
							</html:select>
							</td>
							</td>
                               <td class="table2_td_title" width="10%">
							</td>
							<td class="table2_td" width="40%">							
							</td>
						</tr>				
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="查询" width="65" height="20" border="0" onclick="return searchClick('queryEdcTerminal');">
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/edcTerminal.do">
						<display:column title="选择" style="width:10%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.id.merchantId}#${displayTable.id.terminalId}#${displayTable.terminalStat}#${displayTable.edcType}#${displayTable.softVer}#${displayTable.downloadFlag}#${displayTable.downloadMode}#${displayTable.updateOper}#${displayTable.updateDate}#${displayTable.updateTime}#${displayTable.edcDoc}#${displayTable.printerType}#${displayTable.pinpadType}#${displayTable.setDate}#${displayTable.setAddr}#end"/>">
							
						</display:column>
						<display:column title="商户编号" style="width:10%;text-align:center" property="id.merchantId" headerClass="sortable" sortable="true" />
						<display:column title="终端编号" style="width:12%;text-align:center" property="id.terminalId" headerClass="sortable" sortable="true" />
						<display:column title="终端状态" style="width:13%;text-align:center" property="ch_terminalStat" headerClass="sortable" sortable="true" />
						<display:column title="终端设备型号" style="width:15%;text-align:center" property="edcType" headerClass="sortable" sortable="true" />
						<display:column title="维护柜员" style="width:15%;text-align:center" property="updateOper" headerClass="sortable" sortable="true" />
						<display:column title="日期时间" style="width:15%;text-align:center" property="ch_dateAndTime" headerClass="sortable" sortable="true" />
						<display:column title="操作" style="width:10%;text-align:center">
                        <shiro:hasPermission name="posp:terminal:edit">
						   <a href="#" onclick="javascript:editClicks('<c:out value="${displayTable.id.merchantId}#${displayTable.id.terminalId}#${displayTable.terminalStat}#${displayTable.edcType}#${displayTable.softVer}#${displayTable.downloadFlag}#${displayTable.downloadMode}#${displayTable.updateOper}#${displayTable.updateDate}#${displayTable.updateTime}#${displayTable.edcDoc}#${displayTable.printerType}#${displayTable.pinpadType}#${displayTable.setDate}#${displayTable.setAddr}#end"/>','queryEdcTerminalByKey')">编辑</a>
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
							<!--  
                                <shiro:hasPermission name="posp:terminal:delete">
								<a href="javascript:deleteClick('selectItems','checkItem','deleteEdcTerminal');"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="删除" width="65" height="20" border="0"></a>
                                </shiro:hasPermission>
                            -->
                                <shiro:hasPermission name="posp:terminal:add">
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
	<shiro:hasPermission name="posp:terminal:upload">
	<center>			
	<form id='formShowData' name="formShowData" enctype='multipart/form-data' method='post' action="edcTerminalUpload.do" >
	<input type="hidden" name="method" value="fileUpload" />
	<input type="file" name='upload' id='upload' /><input type="button" value="导入" onclick="fileUpload();" />
	<a href="javascript:;" onclick="downloadTemplate();">下载导入模板</a>
	</form>
	</center>
	</shiro:hasPermission>
</body>
</shiro:hasPermission>
</html:html>
