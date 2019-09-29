<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>通知路由</title>
<fmt:setBundle basename="MyResource"/>
<link href="<c:out value="${pageContext.request.contextPath}" />/<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">

</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="50">&nbsp; </td>
  </tr>
</table>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
  <tr>
    <td class="table1_head_title"><img src="<c:out value="${pageContext.request.contextPath}" />/<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">错误提示</td>
  </tr>
  <tr>
    <td class="table1_head_line"> </td>
  </tr>
  <tr>
    <td><table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
        <tr>
          <td height="50" align="center"><img src="<c:out value="${pageContext.request.contextPath}" />/<fmt:message key='CommonImagePath' />icoPrompt.gif" width="37" height="33" align="absmiddle"><font color="#FF0000">对不起，操作失败。请检查交易类型与卡类型是否已存在！</font></td>
        </tr>
      </table></td>
  </tr>
</table>
</body>
</html>

