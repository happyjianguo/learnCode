<%@ page contentType="text/html;charset=GBK" import="cn.yufu.posp.terminalmanager.domain.model.*"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>商户转换</title>
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
		<c:if test="${!empty param.queryMerId}" >
		var queryMerId = '<c:out value="${param.queryMerId}"/>';
		document.forms[0].queryMerId.value=queryMerId;
		</c:if>	
		<c:if test="${!empty param.queryBank}" >
		var queryBank = '<c:out value="${param.queryBank}"/>';
		document.forms[0].queryBank.value=queryBank;
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
		document.forms[0].queryMerId.value="";
		document.forms[0].queryBank.value="";
	}
	</script>
	
</head>
<shiro:lacksPermission name="posp:merchantswitch:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:merchantswitch:view">
<body onload="init();">

	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				商户转换列表   
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/merchantSwitch" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%"> 
								商户编号: 
							</td>
							<td class="table2_td" width="40%">
							<input name="queryMerId" size="19" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')" />
							</td>
							<td class="table2_td_title" width="10%">  
								银行类型:  
							</td>
							<td class="table2_td" width="40%">
							<html:select property="queryBank" style="width:130px;" value="">
								  <option value="">- 请选择 -</option>
								  <c:forEach var="model" items="${bankTypeList}">
							        <option value="<c:out value="${model.bankType}"/>"><c:out value="${model.typeName}" /></option>
							      </c:forEach>
								 <!--   <html:option value="01  ">中国人民银行</html:option>
							      <html:option value="02  ">中国工商银行</html:option>
							      <html:option value="03  ">中国农业银行</html:option>
							      <html:option value="04  ">中国银行</html:option>
							      <html:option value="05  ">中国建设银行</html:option>
							      <html:option value="06  ">交通银行</html:option>
							      <html:option value="07  ">邮政储蓄</html:option>
							      <html:option value="08  ">招商银行</html:option>
							      <html:option value="09  ">九通银行</html:option>
							      <html:option value="10  ">中信银行</html:option>
							      <html:option value="11  ">民生银行</html:option>
							      <html:option value="12  ">华夏银行</html:option>
							      <html:option value="13  ">广发银行</html:option>
							      <html:option value="14  ">浦发银行</html:option>
							      <html:option value="15  ">深发银行</html:option>
							      <html:option value="16  ">光大银行</html:option>
							      <html:option value="17  ">福建兴业银行</html:option>
							      <html:option value="18  ">商业银行</html:option>
							      <html:option value="20  ">农村信用社</html:option>
							      <html:option value="21  ">中国香港银行</html:option>
							      <html:option value="22  ">中国澳门银行</html:option>
							      <html:option value="23  ">香港上海汇丰</html:option>
							      <html:option value="24  ">南洋商业银行</html:option>
							      <html:option value="25  ">集友银行</html:option>
							      <html:option value="26  ">东亚银行</html:option>
							      <html:option value="27  ">大新银行</html:option>
							      <html:option value="28  ">恒生银行</html:option>
							      <html:option value="29  ">上海银行</html:option>
							      <html:option value="30  ">美国银行亚洲有限公司</html:option>
							      <html:option value="31  ">永隆银行</html:option>
							      <html:option value="32  ">中信嘉华银行</html:option>
							      <html:option value="33  ">恒丰银行</html:option>
							      <html:option value="34  ">星展银行（香港）有限公司</html:option>
							      <html:option value="35  ">花旗银行有限公司</html:option>
							      <html:option value="36  ">永亨银行</html:option>
							      <html:option value="37  ">廖创兴银行</html:option>
							      <html:option value="38  ">北京银行</html:option>
							      <html:option value="39  ">渤海银行</html:option>
							      <html:option value="40  ">TRAVELEX</html:option>
							      <html:option value="41  ">AEON信贷财务（亚洲）有限公司</html:option>
							      <html:option value="48  ">汇鑫集团</html:option>
							      <html:option value="50  ">外国银行</html:option>
							      <html:option value="51  ">VISA</html:option>
							      <html:option value="52  ">MASTER_CARD</html:option>
							      <html:option value="53  ">AMEX</html:option>
							      <html:option value="54  ">DINERS</html:option>
							      <html:option value="55  ">JCB</html:option>-->
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/merchantSwitch.do">
						<display:column title="选择" style="width:10%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.merchantId},${displayTable.bankType}"/>">
						
						</display:column>
						<display:column title="商户编号" style="width:20%;text-align:center" property="merchantId" headerClass="sortable" sortable="true" />
						<display:column title="银行类型" style="width:18%;text-align:center" property="bankTypeName" headerClass="sortable" sortable="true" />
						<display:column title="他行商户编号" style="width:20%;text-align:center" property="othMerchantId" headerClass="sortable" sortable="true" />
						<display:column title="他行商户类型" style="width:20%;text-align:center" property="othMcc" headerClass="sortable" sortable="true"/>
						
						<display:column title="操作" style="width:10%;text-align:center">
						<shiro:hasPermission name="posp:merchantswitch:edit">
							<a href="#" onclick="editClicks('<c:out value="${displayTable.merchantId},${displayTable.bankType}"/>','findItem')">编辑</a>
						</shiro:hasPermission>
						</display:column>
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
								<shiro:hasPermission name="posp:merchantswitch:delete">
									<a href="#"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="删除" width="65" height="20" border="0" onclick="deleteClick('selectItems','checkItem','deleteItem');"></a> 
								</shiro:hasPermission>
								<shiro:hasPermission name="posp:merchantswitch:add">
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
