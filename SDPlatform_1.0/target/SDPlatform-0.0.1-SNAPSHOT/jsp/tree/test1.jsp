<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <%
    String root = request.getContextPath();
    %>
    <head>
        <title>TREE</title>

        <link href="/kfgj/jsp/tree/dtree.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="/kfgj/jsp/tree/dtree.js"></script>
        <style type="text/css">
<!--
body {
    background-color: #FFFFFF;
}
-->
</style>

    </head>

    <body>
        <div class="dtree">

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

</html>
