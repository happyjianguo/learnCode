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

	<title>商户信息统计-商户信息增加页面</title>
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
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
</head>
<script type="text/javascript">	
   	function commit() {
		if (!isLenNumOnly("merchantInfoForm", "id", "商户编号", 15)) {
			return false;
		}   
		if (!checkelement("merchantInfoForm", "name", "商户名称")) {
			return false;
		}  	
		if (!checkelement("merchantInfoForm", "fullname", "商户公司名称")) {
			return false;
		}  	
		if (!checkelement("merchantInfoForm", "type", "商户类型")) {
			return false;
		}  	
		if (!checkelement("merchantInfoForm", "status", "商户状态")) {
			return false;
		}  	
		if (!checkelement("merchantInfoForm", "province", "省")) {
			return false;
		}  	
		if (!checkelement("merchantInfoForm", "city", "市")) {
			return false;
		}  	
		if (!checkelement("merchantInfoForm", "area", "区")) {
			return false;
		}  	
		$("#btTJ").attr("disabled","true");
		document.merchantInfoForm.submit();   	    
	}

</script>

<body>
	<html:form styleId="merchantInfoForm" action="/merchantInfo?method=addMerchantInfo" method="post">
		<table border="0" cellpadding="0" cellspacing="0" style="width: 100%; height: 99%;">
			<tr>
				<td align="center" valign="top">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="width: 28px; height: 10px;"></td>
						</tr>
						<tr>
							<td align="left"
								style="width:28px; height:28px;  background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
							</td>
							<td
								style="height:28px;  background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;当前位置： 商户信息统计 &gt; 增加商户信息
							</td>
							<td
								style="width:7px; height:28px;  background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td style="width: 28px; height: 5px" colspan="3"></td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td coslpan="2"><font color="red">${info}</font></td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户编号
							</td>
							<td align="left" class="box2">
								<html:text property="id" maxlength="15" onblur="checkMerchantInfoPK();" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户名称
							</td>
							<td align="left" class="box2">
								<html:text property="name" maxlength="66"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户公司名称
							</td>
							<td align="left" class="box2">
								<html:text property="fullname" maxlength="66"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户类型
							</td>
							<td align="left" class="box2">
								<html:select property="type">
									<html:option value="">－请选择－</html:option>								
									<logic:present name="cardbatch_mer_typeList">
										<html:optionsCollection name="cardbatch_mer_typeList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户状态
							</td>
							<td align="left" class="box2">
								<html:select property="status">
									<html:option value="">－请选择－</html:option>
									<html:option value="0">可用</html:option>
									<html:option value="1">不可用</html:option>
									<html:option value="2">黑名单</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								地区
							</td>
							<td align="left" class="box2">
								<logic:present name="provinList">
									<html:select property="province" onchange="getCityAdd()">
										<html:option value="">－请选择－</html:option>
										<html:optionsCollection name="provinList"
											label="province_city" value="aid" />
									</html:select>
								</logic:present>
								<logic:present name="city_noList">
									<html:select property="city" onchange="getAreaAdd()">
										<html:option value="">－请选择－</html:option>
										<html:optionsCollection name="city_noList"
											label="province_city" value="aid" />
									</html:select>
								</logic:present>
								<html:select property="area">
									<html:option value="">－请选择－</html:option>
								</html:select>
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="height: 23px;" align="center" class="box1">
								<input id="btTJ" class="button" type="button" onclick="commit()"
									value="保存" />
								&nbsp;&nbsp;&nbsp;
								<input class="button" type="button" onClick="javascript:window.close()" 
									value="关闭" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>

</html:html>