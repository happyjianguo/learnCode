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
	function query(){
		var makeCardForm = document.all("makeCardForm");
		makeCardForm.action = "<%=path%>/makeCard.do?method=queryMakeCardList";
		makeCardForm.submit();
	}	
	function addpaninfo()
	{
		var url =  "<%=path%>/makeCard.do?method=showMakeCardInfo&random=" + Math.random();
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
<shiro:lacksPermission name="housekeep:makeCardList:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="housekeep:makeCardList:view">
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form action="/makeCard.do?method=getMakeCardList" method="post"
		styleId="makeCardForm">
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
								&nbsp;&nbsp;当前位置：批量管理 &gt; 钱包卡制卡管理
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
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								状态
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property ="queryStat">
									<html:option value="">－请选择－</html:option>
									<html:option value="00">未制卡</html:option>
									<html:option value="01">已制卡</html:option>
									<html:option value="02">制卡失败</html:option>
								</html:select>
							</td>
							<td style="white-space: nowrap" align="right">
								卡号：
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="queryPan" maxlength="19" size="20" />
							</td>
							 <shiro:hasPermission name="housekeep:makeCardList:edit">
								<td style="white-space: nowrap" align="left">
									<input type="button" class="button" onClick="addpaninfo();" value="制卡"/>
								</td>
							</shiro:hasPermission>
						</tr>
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								订单号
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="queryCustOrder" maxlength="40" size="20" />
							</td>
							<td style="white-space: nowrap" align="right">
								批次号：
							</td>
							<td style="white-space: nowrap" align="le
								<html:text property="queryBatch" maxlength="40" size="20" />
							</td>
							<td style="white-space: nowrap" align="left">
								<input type="button" class="button" onClick="query();" style="background-image: url(<%=path%>/image1/border/Check_button.gif)">
							</td>
						</tr>						
						<tr>
							<td colspan="10">
								<display:table name="makeCardList" partialList="true"
									size="pageBean.totalRecords" pagesize="10" style="width:100%"
									class="dpTable" cellpadding="0" cellspacing="0"
									id="displayTable" requestURI="/makeCard.do">
									<display:column title="订单时间" style="text-align:center"
										property="txtime" headerClass="sortable" sortable="true" />
									<display:column title="流水号" style="text-align:center"
										property="stan" headerClass="sortable" sortable="true" />										
									<display:column title="订单号" style="text-align:center"
										property="cust_order" headerClass="sortable" sortable="true" />										
									<display:column title="卡号" style="text-align:center"
										property="pan" headerClass="sortable" sortable="true" />	
									<display:column title="姓名" style="text-align:center"
										property="name" headerClass="sortable" sortable="true" />
									<display:column title="批次号" style="text-align:center"
										property="batch" headerClass="sortable" sortable="true" />
									<display:column title="原批次号" style="text-align:center"
										property="org_batch" headerClass="sortable" sortable="true" />
									<display:column title="制卡文件路径" style="text-align:center"
										property="reserved1" headerClass="sortable" sortable="true" />										
									<display:column title="备注" style="text-align:center"
										property="reserved3" headerClass="sortable" sortable="true" />
									<display:column title="卡有效期" style="text-align:center"
										property="crd_expdate" headerClass="sortable" sortable="true" />													
									<display:column title="状态" style="text-align:center"
										headerClass="sortable" sortable="true">
										<c:if test="${displayTable.stat=='00' }">未制卡</c:if>
										<c:if test="${displayTable.stat=='01' }">已制卡 </c:if>
										<c:if test="${displayTable.stat=='02' }">制卡失败 </c:if>
									</display:column>
								</display:table>
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
									<util:page uri="/makeCard.do?method=getMakeCardList" />
								</logic:equal>
								<logic:equal name="queryflag" value="1">
									<util:page uri="/makeCard.do?method=queryMakeCardList" />
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
