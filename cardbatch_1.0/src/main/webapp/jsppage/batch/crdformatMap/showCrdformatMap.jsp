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

	<title>卡BIN映射查看页面</title>
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
	function backTo(){
		window.location.href="<%=path%>/crdformatMap.do?method=getCrdformatMapList";
	}
</script>

<body>
	<html:form styleId="crdformatMapForm"
		action="/crdformatMap?method=getCrdformatMapList" method="post">
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
								style="width:28px; height:28px;  background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">

							</td>
							<td
								style="height:28px;  background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;当前位置： 批量管理 &gt; 查看卡BIN映射
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
								卡BIN
							</td>
							<td align="left" class="box2">
								<html:select property="iid" disabled="true">
									<html:option value=" ">－请选择－</html:option>
									<logic:present name="kaBinList">
										<html:optionsCollection name="kaBinList" label="iid_desc" value="iid" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<c:if test="${crdformatMapForm.func_flag=='1' }"> 
							<tr>
								<td style="width: 110px;" align="right" class="box1">
									卡BIN映射
								</td>
								<td align="left" class="box2">
									<html:select property="iid_map" disabled="true">
									<html:option value=" ">－请选择－</html:option>
									<logic:present name="kaBinListTwo">
										<html:optionsCollection name="kaBinListTwo" label="iid_desc" value="iid" />
									</logic:present>
								</html:select>
								</td>
							</tr>
						</c:if>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								对应功能
							</td>
							<td align="left" class="box2">
								<html:select property="func_flag" disabled="true">
									<html:option value="">－请选择－</html:option>
									<html:option value="0">批量发卡管理</html:option>
									<html:option value="1">生成同福卡绑定的虚拟卡管理</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								状态
							</td>
							<td align="left" class="box2">
								<html:select property="isuse">
									<html:option value="">－请选择－</html:option>
									<html:option value="1">可用</html:option>
									<html:option value="0">停用</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 150px;" align="right" class="box1">
								有效期是否必填
							</td>
							<td align="left" class="box2">
								<html:select property="needDt" disabled="true">
									<html:option value="">－请选择－</html:option>
									<html:option value="1">必填</html:option>
									<html:option value="0">非必填</html:option>
								</html:select>
								
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="height: 23px;" align="center" class="box1">
								<input class="button" type="button"  value="关闭" onClick="javascript:window.close()">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>

</html:html>