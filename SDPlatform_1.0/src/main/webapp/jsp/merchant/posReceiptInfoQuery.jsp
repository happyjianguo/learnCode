<%@ page contentType="text/html;charset=GBK" import="cn.yufu.posp.terminalmanager.domain.model.*"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>POS签购单要素设置管理</title>
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

	function editClicks(params,method)
	{
		document.forms[0].method.value=method;
		//用editClicks()函数来调用的参数status
		document.forms[0].status.value=params;
		document.forms[0].submit();
	}
	</script>
	
</head>
<shiro:lacksPermission name="posp:posReceiptInfo:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:posReceiptInfo:view">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				POS签购单要素设置列表  
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/posReceiptInfo" method="post">
					<input type="hidden" name="method" value="queryAll" />
				<!-- 用Diaplay Tag来显示 -->
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/posReceiptInfo.do">
						<display:column title="服务热线电话" style="width:15%;text-align:center" property="hotline" headerClass="sortable" sortable="true" />
						<display:column title="技术支持电话" style="width:11%;text-align:center" property="telSupport" headerClass="sortable" sortable="true" />
						<display:column title="广告内容" style="width:18%;text-align:center" property="adInfo" headerClass="sortable" sortable="true" />
						<display:column title="打印广告标志" style="width:10%;text-align:center" property="adYesNoFlag" headerClass="sortable" sortable="true" />
						<display:column title="状态标识" style="width:10%;text-align:center" property="status" headerClass="sortable" sortable="true" />
						<display:column title="创建日期时间" style="width:14%;text-align:center" property="createDate" headerClass="sortable" sortable="true" />
						<display:column title="更新柜员" style="width:10%;text-align:center" property="updateOper" headerClass="sortable" sortable="true" />
						<display:column title="更新日期时间" style="width:12%;text-align:center" property="updateDate" headerClass="sortable" sortable="true" />

						<display:column title="操作" style="width:10%;text-align:center">
						<shiro:hasPermission name="posp:posReceiptInfo:edit">
							<a href="#" onclick="editClicks('<c:out value="${displayTable.status}"/>','findItem')">编辑</a>
						</shiro:hasPermission>
						</display:column>
					</display:table>
					<!-- 用Diaplay Tag来显示 -->		
					<!-- 用editClicks()函数来调用的参数 -->	
					<input type="hidden" name="status" />

			</td>
		</tr>
	</table>
	<%@include file="../common/getDisplayParams1.jsp"%>
	</html:form>
	<br>

</body>
</shiro:hasPermission>
</html:html>
