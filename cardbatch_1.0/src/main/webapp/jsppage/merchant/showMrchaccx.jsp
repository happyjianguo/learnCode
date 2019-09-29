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
</head>
<script language="javascript">
	function addConfirm()
	{
		var url = "<%=path%>/mrchaccx.do?method=preAddMrchaccxCheck&random=" + Math.random();
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
		var mrch_acc_xForm = document.all("mrch_acc_xForm");
		mrch_acc_xForm.action = "<%=path%>/mrchaccx.do?method=getMrchaccxList";
		mrch_acc_xForm.submit();
	}
	function exportExcelOfMrchAcc(){
		var mrch_acc_xForm = document.all("mrch_acc_xForm");
		mrch_acc_xForm.action = "<%=path%>/mrchaccx.do?method=exportExcelOfMrchAcc";
		mrch_acc_xForm.submit();
	}
	function preModMrchaccx(id)
	{
		var url = "<%=path%>/mrchaccx.do?method=preModMrchaccx&id="+id+"&random=" + Math.random();
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
	function preQueryMrchaccx(id)
	{
		var url = "<%=path%>/mrchaccx.do?method=preQueryMrchaccx&id="+id+"&random=" + Math.random();
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
</script>
<shiro:lacksPermission name="cardbatch:mrchAcc:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="cardbatch:mrchAcc:view">
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form action="/mrchaccx.do?method=getMrchaccxList" method="post"
		styleId="mrch_acc_xForm">
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
								&nbsp;&nbsp;当前位置： 商户终端信息管理 &gt; 商户账户信息管理
							</td>
							<td width="7" height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td width="28" height="5" colspan="3"></td>
						</tr>
					</table>
					<table cellpadding="0" cellspacing="0" width="100%" 
						style="padding: 5px;" align="left">
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								商户号
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="mrchnoQ" maxlength="15" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
								商户名称
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="mrchNameQ" maxlength="20" size="10" />
							</td>
							<td style="white-space: nowrap" align="right">
								结算帐号
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="accno" maxlength="20" size="10" />
							</td>
							<td style="white-space: nowrap" align="right">
								结算银行
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="bank_name" maxlength="20" size="10" />
							</td>
							<td height="25" align="left">
								<input type="button" class="button" onClick='return query()'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)">
							</td>
							<td height="25" align="left">
								<a href="javascript:;" onClick='return exportExcelOfMrchAcc()'><img alt="" src="<%=path%>/images/icon-import.png"> </a>																		
							</td>
							<shiro:hasPermission name="cardbatch:mrchAcc:add">
								<td align="center">
									<input type="button" class="button"
										onClick='return addConfirm()'
										style="background-image: url(<%=path%>/image1/border/New_button.gif)">
								</td>
							</shiro:hasPermission>
						</tr>
						<tr>
						<td colspan="13">
							<display:table name="mrchaccxList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/mrchaccx.do">
								<display:column title="id" style="text-align:center" property="id" headerClass="sortable" sortable="true" />
								<display:column title="开户名" style="text-align:center" property="acc_name" headerClass="sortable" sortable="true" />
								<display:column title="结算账号" style="text-align:center" property="accno" headerClass="sortable" sortable="true" />
								<display:column title="所属商户" style="text-align:center"  property="mrchno" headerClass="sortable" sortable="true"/>
								<display:column title="商户账号名称" style="text-align:center"  property="acc_nick_name" headerClass="sortable" sortable="true"/>
								<display:column title="商户账号简称" style="text-align:center" property="short_nick_name" headerClass="sortable" sortable="true" />
								<display:column title="联行号" style="text-align:center" property="bank_no" headerClass="sortable" sortable="true" />
								<display:column title="开户银行名称" style="text-align:center" property="bank_name" headerClass="sortable" sortable="true" />
								<display:column title="增加日期" style="text-align:center" property="acc_add_date" headerClass="sortable" sortable="true" />
								<display:column title="单独结算" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.individual=='0' }">否 </c:if>
									<c:if test="${displayTable.individual=='1' }">是 </c:if>
								</display:column>
								<display:column title="上次结算日期" style="text-align:center" property="last_settle_date" headerClass="sortable" sortable="true" />
								<display:column title="查看" style="width:5%;text-align:center">
									<a href="javascript:;" onclick="preQueryMrchaccx('<c:out value="${displayTable.id}"/>')">
										<img border="0" src="<%=path%>/image1/border/query.png" />
									</a>
								</display:column>	
								<shiro:hasPermission name="cardbatch:mrchAcc:edit">
									<display:column title="修改" style="width:5%;text-align:center">
										<a href="/mrchaccx.do?method=preModMrchaccx&id=${displayTable.id}">修改</a>
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
