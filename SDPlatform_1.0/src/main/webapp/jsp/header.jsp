<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link href="../common/style/style.css" rel="stylesheet" type="text/css">
<script type="text/javaScript">
function dologout(){
if(window.confirm("ȷ���˳�ϵͳ��"))
 window.parent.location = "<%=basePath %>UserLogout.do?method=userLogout";

}
</script>
</head>

<body style="overflow-y:hidden" >
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="head_logo">
    <div style="float:right; padding-top:0px;padding: 5px 10px 10px 10px;">
	<a>${currentUserData.userName},���ã�</a>
	<a href="javascript:;" onclick="dologout();" title="�뿪ϵͳ,���ص�¼����">�˳�ϵͳ</a>
    </div>
    </td>
  </tr>
</table>
</body>
</html>
