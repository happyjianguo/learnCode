<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>卡账号管理</title>
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
		<c:if test="${!empty param._cardNo}" >
		var _cardNo = '<c:out value="${param._cardNo}"/>';
		document.forms[0]._cardNo.value=_cardNo;
		</c:if>	
		<c:if test="${!empty param._cardStat}" >
		var _cardStat = '<c:out value="${param._cardStat}"/>';
		document.forms[0]._cardStat.value=_cardStat;
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
		document.forms[0].cardNo.value=id;
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
				当前位置：收单系统管理平台 >> 终端机管理 >> 卡账号管理
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
				卡账号列表
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/cardAcct" method="post">
					<input type="hidden" name="method" value="queryCardAcct" />
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								卡号:
							</td>
							<td class="table2_td" width="40%">
							<input name="_cardNo" type="text"  maxlength="19"  style="width:135px;">
							</td>
                               <td class="table2_td_title" width="10%">
								卡状态:
							</td>
							<td class="table2_td" width="40%">							
							<select name="_cardStat">
							     <option></option>
								 <option value="0" >正常</option>
								 <option value="1" >冻结</option>
							     <option value="2" >止付</option>
							</select>
							</td>
							
						</tr>				
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="查询" width="65" height="20" border="0" onclick="return searchClick('queryCardAcct');">
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/cardAcct.do">
						<display:column title="选择" style="width:10%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.cardNo}"/>">
							
						</display:column>
						<display:column title="卡号" style="width:15%;text-align:center" property="cardNo" headerClass="sortable" sortable="true" />
						<display:column title="卡状态" style="width:10%;text-align:center" property="ch_cardStat" headerClass="sortable" sortable="true" />
						<display:column title="Master卡号" style="width:18%;text-align:center" property="masterCardNo" headerClass="sortable" sortable="true" />
						<display:column title="卡帐号" style="width:20%;text-align:center" property="acctNo" headerClass="sortable" sortable="true" />
						<display:column title="存款金额" style="width:10%;text-align:center" property="ch_depositAmt" headerClass="sortable" sortable="true" />
						<display:column title="信用金额" style="width:10%;text-align:center" property="ch_cardAmt" headerClass="sortable" sortable="true" />
						<display:column title="操作" style="width:13%;text-align:center">
                        <c:if test="${currentUserData.groupMap.ababaczac=='yes'}">
						   <a href="#" onclick="javascript:editClicks('<c:out value="${displayTable.cardNo}"/>','queryCardAcctByKey')">编辑</a>
                        </c:if> 
						</display:column>
						
					</display:table>
					<!-- 用Diaplay Tag来显示 -->		
					
					<input type="hidden" name="cardNo" />		
					<input type="hidden" name="selectItems" />
					
					<!----------以下Table用操作选择-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								是否全选
							</td>
							<td>
                                <c:if test="${currentUserData.groupMap.ababaczad=='yes'}">
								<a href="javascript:deleteClick('selectItems','checkItem','deleteCardAcct');"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="删除" width="65" height="20" border="0"></a>
                                </c:if> <c:if test="${currentUserData.groupMap.ababaczab=='yes'}">
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
