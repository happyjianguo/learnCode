<%@ page contentType="text/html;charset=GBK"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>收单系统管理平台—redirect</title>
<script type="text/javascript" src="../common/js/jquery-1.4.min.js" ></script>
    <script type="text/javascript">
        $(document).ready(function() {
        	$("#frame").attr("src","<%=request.getParameter("url")%>");	
        });
    </script>
</head>

<body>
    <iframe id="frame" src="" frameborder="0" height="100%" width="100%"></iframe>
</body>
</html>
