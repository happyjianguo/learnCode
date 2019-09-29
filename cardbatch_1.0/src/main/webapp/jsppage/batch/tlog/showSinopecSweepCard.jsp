<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%String path = request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />
	<title>日志管理-中石化扫码刷卡统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/new_look.css" />
</head>
<script src="<%=path%>/js/sorttable.js"></script>
<script src="<%=path%>/js/eposcc.js"></script>
<script language="javascript">
	function downloadTemplate(){
		/* var merchantInfoForm = document.all("merchantInfoForm"); */
		sinopecSweepCardUploadForm.action = "<%=path%>/querySinopecSweepCard.do?method=downloadTemplate";
		sinopecSweepCardUploadForm.submit();
	}
	function clearfileUpload(){
		var fileName = document.all("formShowData").upload.value;
		 if(!fileName){
			 alert("请选择要导入的文件!");
	         return false;
		 }else{
			 document.all("formShowData").upload.value = "";
		 }
	}
	
	function fileUpload(){
		var fileName = document.all("formShowData").upload.value;
		 if(!fileName){
			 alert("请选择要导入的文件!");
	         return false;
		 }
	     var strRegex = "(.xlsx|.xls)$";
	     var re = new RegExp(strRegex);
	     if (!re.test(fileName.toLowerCase())){
	         alert("文件格式不合法，必须为EXCEL文档!");
	         return false;
	     }
		 var formShowData = document.all("formShowData");
		 formShowData.action = "<%=path%>/querySinopecSweepCard.do?method=fileUpload";
		 formShowData.submit();
	}
</script>
<shiro:lacksPermission name="posp:sinopecSweepCard:upload">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:sinopecSweepCard:upload">
<body>
	<html:form action="/querySinopecSweepCard.do?method=toQuery" method="post" styleId="sinopecSweepCardUploadForm">
		<bean:define id="menu_level" name="menu_level" />
		<table border="0" cellpadding="0" cellspacing="0" width="100%" height="60%">
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
								style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;当前位置：日志管理 &gt; 中石化扫码刷卡统计
							</td>
							<td width="7" height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td width="28" height="5" colspan="3"></td>
						</tr>
					</table>
					<table cellpadding="0" border="0" cellspacing="0" width="100%" style="padding: 0px;" align="left">
						<tr>
							<td coslpan="9"><font color="red"><h2>${info}<h2></font></td>
						</tr>
						
						<tr>
						<td colspan="3">
							<display:table name="sinopecSweepCardUploadList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/querySinopecSweepCard.do">
								<display:column title="交易类型" style="text-align:center" property="tradingType" headerClass="sortable" sortable="true" />
								<display:column title="交易笔数" style="text-align:center" property="tradingNumber" headerClass="sortable" sortable="true" />
								<display:column title="交易金额" style="text-align:center" property="tradingAmt" headerClass="sortable" sortable="true"/>
							</display:table>
						</td>
						</tr>
					</table>
			</td>
			</tr>
		</table>
	</html:form>
	<table cellpadding="0" border="0" cellspacing="0" width="100%" class="serch" style="padding: 1px;" align="left">
		<tr>
			<td>
				<shiro:hasPermission name="posp:sinopecSweepCard:upload">	
					<center>			
						<form id='formShowData' name="formShowData" enctype='multipart/form-data' method='post' action="/querySinopecSweepCard.do" >
							<input type="hidden" name="method" value="fileUpload" />
							<input type="file" name='upload' id='upload' /><input type="button" value="查询" onclick="fileUpload();" />
							<input type="button" value="清空" onclick="clearfileUpload();" />
							<input type="button" value="下载查询模板" onclick="downloadTemplate();" />
						</form>
					</center>
				</shiro:hasPermission>
			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>
