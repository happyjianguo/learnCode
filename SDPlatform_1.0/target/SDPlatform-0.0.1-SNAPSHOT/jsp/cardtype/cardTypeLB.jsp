<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>卡类管理</title>
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
		<c:if test="${!empty param._cardId}" >
		var _cardId = '<c:out value="${param._cardId}"/>';
		document.forms[0]._cardId.value=_cardId;
		</c:if>
		<c:if test="${!empty param._cardName}" >
		var _cardName = '<c:out value="${param._cardName}"/>';
		document.forms[0]._cardName.value=_cardName;
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
		document.forms[0].jgId.value=id;
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
<shiro:lacksPermission name="posp:cardtype:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:cardtype:view">
<body onload="init()">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				卡类列表
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/cardType" method="post">
					<input type="hidden" name="method" value="queryCardType" />
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								卡标识号:
							</td>
							<td class="table2_td" width="40%">
							<input type="text" name="_cardId"  maxlength="20"  style="width:135px;" />
							</td>
							<td class="table2_td_title" width="10%">
								卡名:
							</td>
							<td class="table2_td" width="40%">
							<input type="text" name="_cardName"  maxlength="20"  style="width:135px;" />
							</td>
							
						</tr>				
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="查询" width="65" height="20" border="0" onclick="return searchClick('queryCardType');">
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/cardType.do">
						<display:column title="选择" style="width:5%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.cardId}"/>_<c:out value="${displayTable.cardLen}"/>_<c:out value="${displayTable.cardNoId}"/>_<c:out value="${displayTable.cardType}"/>_<c:out value="${displayTable.cardName}"/>_<c:out value="${displayTable.bankType}"/>_<c:out value="${displayTable.bankCode}"/>_<c:out value="${displayTable.cardMode}"/>_<c:out value="${displayTable.cardIdTrack}"/>_<c:out value="${displayTable.cardIdOff}"/>_<c:out value="${displayTable.cardNoTrack}"/>_<c:out value="${displayTable.cardNoOff}"/>_<c:out value="${displayTable.expDateOff}"/>_<c:out value="${displayTable.pinMode}"/>_<c:out value="${displayTable.inputMode}"/>_<c:out value="${displayTable.chkCardValid}"/>_<c:out value="${displayTable.ifLocal}"/>_<c:out value="${displayTable.ifOffline}"/>_<c:out value="${displayTable.updateOper}"/>_<c:out value="${displayTable.updateDate}"/>_<c:out value="${displayTable.updateTime}"/>">
							
						</display:column>
						<display:column title="卡标识号" style="width:15%;text-align:center" property="cardId" headerClass="sortable" sortable="true" />
						<display:column title="卡号标识" style="width:15%;text-align:center" property="cardNoId" headerClass="sortable" sortable="true" />
						<display:column title="中文名称" style="width:15%;text-align:center" property="cardName" headerClass="sortable" sortable="true" />
						<display:column title="卡类型" style="width:15%;text-align:center" property="cardTypeName" headerClass="sortable" sortable="true" />
						<display:column title="银行类型" style="width:15%;text-align:center" property="bankTypeName" headerClass="sortable" sortable="true" />
						<display:column title="机构代码" style="width:10%;text-align:center" property="bankCode" headerClass="sortable" sortable="true" />
						<display:column title="操作" style="width:10%;text-align:center">
						<shiro:hasPermission name="posp:cardtype:edit">
							<a href="#" onclick="editClicks('<c:out value="${displayTable.cardId}"/>_<c:out value="${displayTable.cardLen}"/>_<c:out value="${displayTable.cardNoId}"/>_<c:out value="${displayTable.cardType}"/>_<c:out value="${displayTable.cardName}"/>_<c:out value="${displayTable.bankType}"/>_<c:out value="${displayTable.bankCode}"/>_<c:out value="${displayTable.cardMode}"/>_<c:out value="${displayTable.cardIdTrack}"/>_<c:out value="${displayTable.cardIdOff}"/>_<c:out value="${displayTable.cardNoTrack}"/>_<c:out value="${displayTable.cardNoOff}"/>_<c:out value="${displayTable.expDateOff}"/>_<c:out value="${displayTable.pinMode}"/>_<c:out value="${displayTable.inputMode}"/>_<c:out value="${displayTable.chkCardValid}"/>_<c:out value="${displayTable.ifLocal}"/>_<c:out value="${displayTable.ifOffline}"/>_<c:out value="${displayTable.updateOper}"/>_<c:out value="${displayTable.updateDate}"/>_<c:out value="${displayTable.updateTime}"/>','queryCardTypeByKey')">编辑</a>
						</shiro:hasPermission>
						</display:column>
						
					</display:table>
					<!-- 用Diaplay Tag来显示 -->		
					
					<input type="hidden" name="id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="jgId" />
					
					<!----------以下Table用操作选择-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								是否全选
							</td>
							<td>
								<shiro:hasPermission name="posp:cardtype:delete">
									<a href="#"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="删除" width="65" height="20" border="0" onclick="deleteClick('selectItems','checkItem','deleteCardType');"></a>
								</shiro:hasPermission>
								<shiro:hasPermission name="posp:cardtype:add">
									<a href="#" onclick="addClick()"><img border=0 name="imageField322" src="<fmt:message key='CommonImagePath' />btnAdd.gif" alt="增加" width="65" height="20" border="0"></a>
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
	<shiro:hasPermission name="posp:cardtype:upload">
	<center>						
	<form id='formShowData' name="formShowData" enctype='multipart/form-data' method='post' action="cardTypeUpload.do" >
	<input type="hidden" name="method" value="fileUpload" />
	<input type="file" name='upload' id='upload' /><input type="button" value="导入" onclick="fileUpload();" />
	<input type="button" value="下载导入模板" onclick="downloadTemplate();" />
	</form>
	</center>
	</shiro:hasPermission>
</body>
</shiro:hasPermission>
</html:html>