<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>终端主密钥</title>
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
		<c:if test="${!empty param.querySettleFlag}" >
		var querySettleFlag = '<c:out value="${param.querySettleFlag}"/>';
		document.forms[0].querySettleFlag.value=querySettleFlag;
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
	function showClicks(id,method)
	{
		document.forms[0].method.value=method;
		document.forms[0]._id.value=id;
		document.forms[0].showFlag.value="changeshow";
		document.forms[0].submit();
		
	}
	function createBatchClick(textName,itemName,method){
	 	var aa = document.getElementsByName(itemName);
	 	var bb = document.all[textName];
	 	bb.value = "";
	 	flag = false;
	 	for (var i=0; i<aa.length; i++)
	 	{
	 		if(aa[i].checked)
	 		{
	 			flag = true;
	 			if(bb.value=="")
	 			{
	 				bb.value = aa[i].value;
	 			}
	 			else
	 				bb.value = bb.value + "|" + aa[i].value;
	 		}
	 	}
	 	if(flag)
	 	{
	 		if(confirm("确定要操作选中的选项吗？"))
	 		{
	 			document.forms[0].method.value=method;
	 			document.forms[0].submit();
	 		}
	 	}
	 	else
	 		alert("请选择要操作的选项！");
	}
	</script>
</head>
<shiro:lacksPermission name="posp:tmkmasterkey:changeview">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:tmkmasterkey:changeview">
<body onload="init();">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				终端主密钥列表
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/TMKMasterKEY" method="post">
					<input type="hidden" name="method" value="queryChangeAll" />
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
								终端编号:
							</td>
							<td class="table2_td" width="20%">	
								<input name="_terminalId" type="text"  maxlength="8"  style="width:135px;">
							</td>
							<%-- 
							<td class="table2_td_title" width="10%">
								<!-- 状态: -->
							</td>
							<td class="table2_td" width="30%">
								<html:select property="querySettleFlag" style="width:130px;">
									<html:option value="0">新终端</html:option>
									<html:option value="1">生成终端主密钥</html:option>
									<html:option value="2">已签到</html:option>
								</html:select>
							</td> --%>
						</tr>				
						<tr>
							<td colspan="6" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="查询" width="65" height="20" border="0" onclick="return searchClick('queryChangeAll');">
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/TMKMasterKEY.do">
						<%-- <display:column title="选择" style="width:10%;text-align:center">
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.merchantId}#${displayTable.terminalId}#end"/>">
						</display:column> --%>
						<display:column title="商户编号" style="width:20%;text-align:center" property="merchantId" headerClass="sortable" sortable="true" />
						<display:column title="终端编号" style="width:20%;text-align:center" property="terminalId" headerClass="sortable" sortable="true" />						
						<display:column title="operNo" style="width:20%;text-align:center" property="operNo" headerClass="sortable" sortable="true" />
						<display:column title="状态" style="width:20%;text-align:center" headerClass="sortable" sortable="true" >
						<c:if test="${displayTable.settleFlag eq '0'}">新终端</c:if>
						<c:if test="${displayTable.settleFlag eq '1'}">生成终端主密钥</c:if>
						<c:if test="${displayTable.settleFlag eq '2'}">已签到</c:if>
						</display:column>
						<display:column title="操作" style="width:20%;text-align:center">
						<shiro:hasPermission name="posp:tmkmasterkey:changeedit">                        
                         <c:if test="${displayTable.settleFlag!='0'}">
							   <a href="#" onclick="javascript:showClicks('<c:out value="${displayTable.merchantId}#${displayTable.terminalId}#end"/>','findChangeItem')">查看</a>
	                        </c:if>    
	                        <c:if test="${displayTable.settleFlag=='0'}">
							   <a href="#" onclick="javascript:editClicks('<c:out value="${displayTable.merchantId}#${displayTable.terminalId}#end"/>','findChangeItem')">生成</a>
	                        </c:if> 
	                        <c:if test="${displayTable.settleFlag!='0'}">
							   <a href="#" onclick="javascript:editClicks('<c:out value="${displayTable.merchantId}#${displayTable.terminalId}#end"/>','findChangeItem')">修改</a>
	                        </c:if> 
                         </shiro:hasPermission> 
						</display:column>
						
					</display:table>
					<!-- 用Diaplay Tag来显示 -->		
					
					<input type="hidden" name="_id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="showFlag" />
					<!----------以下Table用操作选择-------->
					<%-- <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								是否全选	
							</td>
							<td>
                                <shiro:hasPermission name="posp:tmkmasterkey:changebatch">
								<input type="button" value="批量生成主密钥" onclick="javascript:createBatchClick('selectItems','checkItem','createBatch');"/>
								</shiro:hasPermission>
							</td>
							<td width="240" align="right">
								&nbsp;
							</td>
						</tr>
					</table> --%>
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