<%@ page contentType="text/html;charset=gb2312"	import="com.pay.util.ParamUtils"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
    String path = request.getContextPath();
    String execCode = ParamUtils.getStringAttribute(request, "execcode");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>特色系统管理平台---日终处理</title>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
</head>
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
<script type="text/javascript">
  var myexecCode =  "<%=execCode%>";
  if(myexecCode != ""){
  	  document.write(myexecCode);
  }
  window.close();
</script>
</body>
</html:html>