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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html lang="true">
<head>
	<html:base />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/new_look.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style.css" />
	<script type="text/javascript" src="<%=path%>/js/eposcc.js"></script>
	<script src="<%=path%>/js/calendar.js"></script>
	<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	
</head>
<script language="javascript">
	function addConfirm(){
		var url = "<%=path%>/merchantFenrunOrg.do?method=preAddMerchantOrg&random=" + Math.random();
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
	
	function query(){
		/* var merchantOrgForm = document.all("merchantOrgForm"); */
		var merchantFenrunOrgForm = document.all("merchantFenrunOrgForm");
		merchantFenrunOrgForm.action = "<%=path%>/merchantFenrunOrg.do?method=getMerchantOrgList";
		merchantFenrunOrgForm.submit();
	}
	
	//data转换
	function getDate(date){
	 var dates = date.split("-");
	 var dateReturn = '';
	 
	 for(var i=0; i<dates.length; i++){
	  dateReturn+=dates[i];
	 }
	 return dateReturn;
	}
</script>
<shiro:lacksPermission name="cardbatch:mrchfenrunorg:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="cardbatch:mrchfenrunorg:view">
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form action="/merchantFenrunOrg?method=getMerchantOrgList" method="post"
		styleId="merchantFenrunOrgForm">
		<bean:define id="menu_level" name="menu_level" />
		<table border="0" cellpadding="0" cellspacing="0" width="100%"
			height="100%">
			<tr>
				<td align="center" valign="top" height="87%">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="28" height="10"></td>
						</tr>
						<tr>
							<td align="left" width="28" height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">

							</td>
							<td height="28"
								style="background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;当前位置：分润机构管理 &gt; 分润机构管理
							</td>
							<td width="7" height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td width="28" height="5" colspan="3"></td>
						</tr>
					</table>
					<table cellpadding="0" border="0" cellspacing="0" width="100%"
						 style="padding: 0px;" align="left">
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								分润机构名称
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="orgName" />
							</td>
							<td height="25" align="right">
								<input type="button" class="button" onclick='return query()'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)" />
							</td>
							<shiro:hasPermission name="cardbatch:mrchfenrunorg:edit">
								<td align="center">
									<input type="button" class="button"
										onClick='return addConfirm()'
										style="background-image: url(<%=path%>/image1/border/New_button.gif)">
								</td>
							</shiro:hasPermission>
						</tr>
						<tr>
						<td colspan="13">
							<display:table name="merchantOrgList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/merchantFenrunOrg.do">
								<display:column title="分润机构名称" style="text-align:center" property="orgName" headerClass="sortable" sortable="true" />
								<display:column title="卡BIN" style="text-align:center" property="orgBin" headerClass="sortable" sortable="true" />
								<display:column title="分润机构留底费率" style="text-align:center" property="orgPurposeAmt" headerClass="sortable" sortable="true" />
								<display:column title="分润机构状态" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.orgStat=='0' }">正常</c:if>
									<c:if test="${displayTable.orgStat=='1' }">锁定</c:if>
								</display:column>
								<display:column title="创建时间" style="text-align:center" property="createDate" headerClass="sortable" sortable="true" />
								<shiro:hasPermission name="cardbatch:mrchfenrunorg:edit">
									<display:column title="修改" style="text-align:center">
										 <a href="<%=path%>/merchantFenrunOrg.do?method=preModMerchantOrg&orgId=${displayTable.orgId}">修改</a>
										 <%-- <a href="<%=path%>/merchant.do?method=detailMerchant&merchant_org=${displayTable.orgId}">明细</a> --%>
										 <a href="<%=path%>/merchant.do?method=detailFenrunMerchant&merchant_fenrunorg=${displayTable.orgId}">明细</a>
									</display:column>									
								</shiro:hasPermission>
							</display:table>
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
