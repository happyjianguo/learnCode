<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>终端TPDU</title>
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
		<c:if test="${!empty param._tpdu}" >
		var _tpdu = '<c:out value="${param._tpdu}"/>';
		document.forms[0]._tpdu.value=_tpdu;
		</c:if>	
		<c:if test="${!empty param._posLinkType}" >
		var _posLinkType = '<c:out value="${param._posLinkType}"/>';
		document.forms[0]._posLinkType.value=_posLinkType;
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
		document.forms[0].tpdu.value=id;
		document.forms[0].submit();
		
	}
	
	</script>
</head>
<shiro:lacksPermission name="posp:edctpdu:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:edctpdu:view">
<body onload="init();">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				终端TPDU列表
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/edcTpdu" method="post">
					<input type="hidden" name="method" value="queryEdcTpdu" />
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								终端TPDU:
							</td>
							<td class="table2_td" width="40%">
							<input name="_tpdu" type="text"  maxlength="10"  style="width:135px;">
							</td>
                               <td class="table2_td_title" width="10%">
								连接类型:
							</td>
							<td class="table2_td" width="40%">							
							<select name="_posLinkType">
							     <option></option>
								 <option value="0" >直连</option>
								 <option value="1" >间连</option>
							     <option value="2" >代理</option>
							</select>
							</td>
							
						</tr>				
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="查询" width="65" height="20" border="0" onclick="return searchClick('queryEdcTpdu');">
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/edcTpdu.do">
						<display:column title="选择" style="width:10%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.tpdu}"/>">
							
						</display:column>
						<display:column title="TPDU" style="width:10%;text-align:center" property="tpdu" headerClass="sortable" sortable="true" />
						<display:column title="连接类型" style="width:10%;text-align:center" property="ch_posLinkType" headerClass="sortable" sortable="true" />
						<display:column title="渠道号" style="width:10%;text-align:center" property="chnlno" headerClass="sortable" sortable="true" />
						<display:column title="模块号" style="width:10%;text-align:center" property="ch_moduleId" headerClass="sortable" sortable="true" />
						<display:column title="拆解包类型" style="width:20%;text-align:center" property="ch_packType" headerClass="sortable" sortable="true" />
						<display:column title="拆解包类型号" style="width:20%;text-align:center" property="ch_packTypeno" headerClass="sortable" sortable="true" />
						<display:column title="操作" style="width:20%;text-align:center">
                        <shiro:hasPermission name="posp:edctpdu:edit">
						   <a href="#" onclick="editClicks('<c:out value="${displayTable.tpdu}"/>','queryEdcTpduByKey')">编辑</a>
                        </shiro:hasPermission>
						</display:column>
						
					</display:table>
					<!-- 用Diaplay Tag来显示 -->		
					
					<input type="hidden" name="tpdu" />		
					<input type="hidden" name="selectItems" />
					
					<!----------以下Table用操作选择-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								是否全选
							</td>
							<td>
                                <shiro:hasPermission name="posp:edctpdu:delete">
								<a href="javascript:deleteClick('selectItems','checkItem','deleteEdcTpdu');"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="删除" width="65" height="20" border="0"></a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="posp:edctpdu:add">
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
</body>
</shiro:hasPermission>
</html:html>