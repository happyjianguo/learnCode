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

	<title>参数增加页面</title>
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
		if (!checkelement("sysParameterForm", "param_type", "参数类型")) {
			return false;
		}   
		if (!checkelement("sysParameterForm", "param_name", "参数名称")) {
			return false;
		}  
		if (!checkelement("sysParameterForm", "param_value", "参数值")) {
			return false;
		}    				    	    
		document.sysParameterForm.submit();   	    
	}

</script>

<body>
	<html:form styleId="sysParameterForm" action="/sysParameter?method=addSysParameter" method="post">
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
								&nbsp;&nbsp;当前位置： 商户终端信息管理 &gt; 增加参数
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
								参数类型
							</td>
							<td align="left" class="box2">
								<html:text property="param_type" maxlength="20" onblur="checkSysParameterPK();"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								参数名称
							</td>
							<td align="left" class="box2">
								<html:text property="param_name" maxlength="30" onblur="checkSysParameterPK();"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								参数值
							</td>
							<td align="left" class="box2">
								<html:text property="param_value" maxlength="30"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								参数描述
							</td>
							<td align="left" class="box2">
								<html:text property="param_notes" maxlength="50"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								父参数编号
							</td>
							<td align="left" class="box2">
								<html:text property="parent_id" maxlength="10" onblur="checkSysParameterParentId();"></html:text>
							</td>
						</tr>						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否可用
							</td>
							<td align="left" class="box2">
								<html:select property="enable">
									<html:option value="1">可用</html:option>
									<html:option value="0">不可用</html:option>
								</html:select>									
							</td>
						</tr>						
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="height: 23px;" align="center" class="box1">
								<input class="button" type="button" onclick="commit()"
									value="保存" />
								&nbsp;&nbsp;&nbsp;
								<input class="button" type="button" value="关闭"
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