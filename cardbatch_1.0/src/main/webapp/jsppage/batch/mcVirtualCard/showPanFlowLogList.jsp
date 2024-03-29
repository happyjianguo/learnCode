<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%
	String path = request.getContextPath();
%>
<%
	String execdate = (String) request.getAttribute("execdate");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>显示列表页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/cssem.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/new_look.css" />
</head>
<script src="<%=path%>/js/sorttable.js"></script>
<script src="<%=path%>/js/eposcc.js"></script>
<script src="<%=path%>/js/calendar.js"></script>
<script language="javascript">
	function addConfirm(){
		window.location.href="<%=path%>/bmanger.do?method=preAddBManger";
	}
	function delConfirm(){
		var answer = window.confirm("您确定要删除吗？删除之后将不能恢复!");
		if(answer == true)
		{
			return true;mcVirtualCard
		}
		return false;
	}
	function query(){
		var bflowlogForm = document.all("bflowlogForm");
		bflowlogForm.action = "<%=path%>/mcVirtualCard.do?method=preShowPanFlowLogList";
		bflowlogForm.submit();
	}

	function preQueryCrdformatMap(id)
	{
		var url =  "<%=path%>/mcVirtualCard.do?method=showPanFlowLogInfo&id="+id+"&random=" + Math.random();		
		var iWidth = 1046; //弹出窗口的宽度;
		var iHeight = 600; //弹出窗口的高度;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
		var winSize = 'height='
				+ iHeight
				+ ',width='
				+ iWidth
				+ ',top='
				+ iTop
				+ ',left='
				+ iLeft
				+ ',toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no';
		window.open(url, "", winSize);	
	}	
	function addpaninfo(id)
	{
		var url =  "<%=path%>/mcVirtualCard.do?method=preAddPanInfo&random=" + Math.random();
		var iWidth = 1046; //弹出窗口的宽度;
		var iHeight = 400; //弹出窗口的高度;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
		var winSize = 'height='
				+ iHeight
				+ ',width='
				+ iWidth
				+ ',top='
				+ iTop
				+ ',left='
				+ iLeft
				+ ',toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no';
		window.open(url, "", winSize);	
	}	
</script>
<shiro:lacksPermission name="housekeep:mckaika:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="housekeep:mckaika:view">
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form action="/mcVirtualCard.do?method=getBFlowLogList"
		method="post" styleId="bflowlogForm">
		<table border="0" cellpadding="0" cellspacing="0" width="100%"
			height="99%">
			<tr>
				<td align="center" valign="top" height="87%">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="28" height="10"></td>
						</tr>
						<tr>
							<td align="left" width="28" height="28"
								style="background: url(<%=path%>/image1/Navigation_bar/left1.gif)">
							</td>
							<td height="28"
								style="background: url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;当前位置：批量管理 &gt; 生成同福卡绑定的虚拟卡管理
							</td>
							<td width="7" height="28"
								style="background: url(<%=path%>/image1/Navigation_bar/right1.gif)">
							</td>
						</tr>
					</table>
					<table cellpadding="0" border="0" cellspacing="0" width="100%"
						style="padding: 0px;" align="left">
						<tr>
							<td coslpan="4">
								<font color="red">${info}</font>
							</td>
						</tr>
						<tr  class="serch">
							<shiro:hasPermission name="housekeep:mckaika:faka">
								<td style="white-space: nowrap" align="right">
									<input class="button" type="button" value="发卡"
										onClick="addpaninfo()">
								</td>
							</shiro:hasPermission>
							<td style="white-space: nowrap" align="right">
								受理号：
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="panflagno" maxlength="20" size="20" />
							</td>
							<td style="white-space: nowrap" align="left">
								<input type="button" class="button" onClick='return query()'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)">
							</td>
						</tr>
						<tr>
							<td colspan="10">
								<display:table name="bflowlogList" partialList="true"
									size="pageBean.totalRecords" pagesize="10" style="width:100%"
									class="dpTable" cellpadding="0" cellspacing="0"
									id="displayTable" requestURI="/mcVirtualCard.do">
									<display:column title="编号" style="text-align:center"
										property="id" headerClass="sortable" sortable="true" />
									<display:column title="批处理标识" style="text-align:center"
										property="batchname" headerClass="sortable" sortable="true" />
									<display:column title="处理结果" style="text-align:center"
										headerClass="sortable" sortable="true">
										<c:if test="${displayTable.issucess!='0' }">失败</c:if>
										<c:if test="${displayTable.issucess=='0' }">成功 </c:if>
									</display:column>
									<display:column title="执行日期" style="text-align:center"
										property="execdateday" headerClass="sortable" sortable="true" />
									<display:column title="执行时间" style="text-align:center"
										property="execdatemin" headerClass="sortable" sortable="true" />
									<display:column title="受理号" style="text-align:center"
										property="panflagno" headerClass="sortable" sortable="true" />
									<display:column title="查看" style="width:5%;text-align:center">
										<a href="javascript:;"
											onclick="preQueryCrdformatMap('<c:out value="${displayTable.id}"/>')">查看</a>
									</display:column>
								</display:table>
								<input type="hidden" name="id">
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="center" valign="top" height="30">
					<table border="0" cellpadding="0" cellspacing="0" width="98%">
						<tr align="right">
							<td height="25">
								<logic:equal name="queryflag" value="0">
									<util:page uri="/mcVirtualCard.do?method=getBFlowLogList" />
								</logic:equal>
								<logic:equal name="queryflag" value="1">
									<util:page uri="/mcVirtualCard.do?method=queryBFlowLogList" />
								</logic:equal>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</shiro:hasPermission>

</html:html>
