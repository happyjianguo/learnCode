<%@ page contentType="text/html;charset=GBK" import="cn.yufu.posp.terminalmanager.domain.model.*"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>商户受理卡类</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />data.js"></script>
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />listPage.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	
	<script language="javascript">
	function init()
	{
		<c:if test="${!empty param.qMerId}" >
		var qMerId = '<c:out value="${param.qMerId}"/>';
		document.forms[0].qMerId.value=qMerId;
		</c:if>	
		<c:if test="${!empty param.qMerBank}" >
		var qMerBank = '<c:out value="${param.qMerBank}"/>';
		document.forms[0].qMerBank.value=qMerBank;
		</c:if>	
	}
	
	function addClick()
	{
		document.forms[0].method.value="findItem";
		document.forms[0].submit();
	}
	function editClicks(params,method)
	{
		//alert(params+","+method);
		document.forms[0].method.value=method;
		document.forms[0].merchantId.value=params;
		document.forms[0].submit();
		
	}
	function resetClick1(){
		document.forms[0].qMerId.value="";
		document.forms[0].qMerBank.value="";
	}
	</script>
	
</head>

<body onload="init();">

	<!--------------以下Table为路径-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">   
				当前位置：收单系统管理平台 >> 商户管理 >> 商户受理卡类
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
				商户受理卡类列表  
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/merchantCard" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%"> 
								商户编号: 
							</td>
							<td class="table2_td" width="40%">
							<input name="qMerId" size="19" maxlength="15"  onkeyup="this.value=this.value.replace(/\D/g,'')" />
							</td>
							<td class="table2_td_title" width="10%">  
								银行类型:  
							</td>
							<td class="table2_td" width="40%">
							<html:select property="qMerBank" style="width:130px;" value="">
								  <option value="">- 请选择 -</option>
								  <c:forEach var="model" items="${bankTypeList}">
							      <option value="<c:out value="${model.bankType}"/>"><c:out value="${model.typeName}" /></option>
							      </c:forEach>
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/merchantCard.do">
						<display:column title="选择" style="width:5%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.merchantId},${displayTable.bankType},${displayTable.cardType}"/>">
						
						</display:column>
						<display:column title="商户编号" style="width:15%;text-align:center" property="merchantId" headerClass="sortable" sortable="true" />
						<display:column title="银行类型" style="width:18%;text-align:center" property="bankTypeName" headerClass="sortable" sortable="true" />
						<display:column title="受理卡类" style="width:10%;text-align:center" property="cardTypeName" headerClass="sortable" sortable="true" />
						<display:column title="受理卡状态" style="width:8%;text-align:center">
						<c:if test='${displayTable.cardStat eq "Y"}'>正常</c:if>
						<c:if test='${displayTable.cardStat eq "N"}'>冻结</c:if>
						</display:column>
						<display:column title="回扣率" style="width:12%;text-align:center" property="cardDiscount" headerClass="sortable" sortable="true" />
						<display:column title="更新柜员" style="width:10%;text-align:center" property="updateOper" headerClass="sortable" sortable="true" />
						<display:column title="更新日期时间" style="width:14%;text-align:center" headerClass="sortable" sortable="true" >
						<c:out value="${displayTable.updateDate } ${displayTable.updateTime }"></c:out>
						</display:column>
						<display:column title="操作" style="width:8%;text-align:center">
						<c:if test="${currentUserData.groupMap.x010202x02=='yes'}">
							<a href="#" onclick="editClicks('<c:out value="${displayTable.merchantId},${displayTable.bankType},${displayTable.cardType}"/>','findItem')">编辑</a>
						</c:if>	
						</display:column>
						<html:hidden property="queryMerchant"/>
						<html:hidden property="queryBankType"/>
						<html:hidden property="queryCardType"/>
					</display:table>
					<!-- 用Diaplay Tag来显示 -->		
					
					<input type="hidden" name="id" />		
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
								<c:if test="${currentUserData.groupMap.x010202x03=='yes'}">
									<a href="#"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="删除" width="65" height="20" border="0" onclick="deleteClick('selectItems','checkItem','deleteItem');"></a> 
								</c:if>
								<c:if test="${currentUserData.groupMap.x010202x01=='yes'}">
									<a href="#" onclick="addClick()">
										<img border=0 name="imageField322" src="<fmt:message key='CommonImagePath' />btnAdd.gif" alt="增加" width="65" height="20" border="0"></a>
								</c:if>
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
</html:html>
