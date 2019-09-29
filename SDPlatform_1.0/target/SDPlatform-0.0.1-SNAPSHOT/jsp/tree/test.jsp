<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>用户管理</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link href="/SDPlatform/jsp/tree/dtree.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="/SDPlatform/jsp/tree/dtree.js"></script>
<script language="javascript">
function goframe(name,id,url)
{
	//window.parent.body.location.href=url;
	window.parent.body.addTab(name,id,url);
}
</script>
</head>

<body>
	<div class="dtree">
<a href="javascript: d.openAll();">全部展开</a> | <a href="javascript: d.closeAll();">全部关闭</a>
<script type="text/javascript">
  d = new dTree('d');
<logic:iterate id="Test" name="list">
        d.add('<bean:write name="Test" property="id"/>','<bean:write name="Test" property="pid"/>','<bean:write name="Test" property="name"/>','<bean:write name="Test" property="url"/>','','','','');
</logic:iterate>
  document.write(d);
  d.closeAll();
   </script>
        </div>

</body>

</html:html>



