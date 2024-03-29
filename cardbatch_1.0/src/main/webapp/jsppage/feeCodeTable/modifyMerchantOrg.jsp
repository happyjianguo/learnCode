<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html lang="true">
<head>
	<html:base />

	<title>商户机构修改页面</title>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/cssem.css" />
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
	<script type="text/javascript" src="<%=path%>/js/textselect.js"></script>
	<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
	<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>

</head>
<script type="text/javascript">
	function commit() {
		if (!checkelement("merchantOrgForm", "orgName", "商户机构名称")) {
			return false;
		}
		//大于等于0小于1
		var reg = /^([1-9]{1}[0-9]{0,1}|0|100)(.d{1,2}){0,1}%$/;
		var orgRatio = document.getElementsByName("orgRatio")[0].value;
		if(!reg.exec(orgRatio)){
			alert("分润比例请输入百分比的形式：1%~100%");
			return false;
		}
		
	    $("#btTJ").attr("disabled","true");	
		document.merchantOrgForm.submit();	
	}

   function trim(str){ //删除左右两端的空格
　　     return str.replace(/(^\s*)|(\s*$)/g, "");
　　} 

</script>

<body>
	<html:form styleId="merchantOrgForm" action="/merchantOrg?method=modMerchantOrg"
		enctype="multipart/form-data" method="post">
		
		<html:hidden property="orgId" />
		
		<table border="0" cellpadding="0" cellspacing="0"
			style="width: 100%; height: 99%;">
			<tr>
				<td align="center" valign="top">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="width: 28px; height: 10px;"></td>
						</tr>
						<tr>
							<td align="left"
								style="  width:28px; height:28px; background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">

							</td>
							<td
								style="height:28px;  background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;当前位置： 商户机构管理 &gt; 修改商户机构
							</td>
							<td
								style=" width:7px; height:28px; background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td style="width: 28px; height: 5px" colspan="3"></td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户机构名称
							</td>
							<td align="left" class="box2">
								<html:text property="orgName" maxlength="30"></html:text>
								<font color="red">*</font>&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								分润比例
							</td>
							<td align="left" class="box2">
								<html:text property="orgRatio" maxlength="5"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								每笔分润金额
							</td>
							<td align="left" class="box2">
								<html:text property="orgSingleAmt" maxlength="9"></html:text>
								<font>单位：元</font>&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户机构状态
							</td>
							<td align="left" class="box2">
								<html:select property="orgStat">
									<html:option value="0">正常</html:option>
									<html:option value="1">锁定</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="height: 23px;" align="center" class="box1">
								<input id="btTJ" class="button" type="button" onclick="commit()"
									value="保存" />
								&nbsp;&nbsp;&nbsp;
								<input class="button" type="button"  value="关闭" 
								onclick="history.go(-1);" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>


