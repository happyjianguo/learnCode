<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>清除缓存</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<sys:message content="${message}"/>
	<form:form id="searchForm" action="${pageContext.request.contextPath}/sys/cleanCache/user" method="post" class="breadcrumb form-search">
		清除用户缓存  <input id="btnSubmit" class="btn btn-primary" type="submit" value="清除"/>
	</form:form>
</body>
</html>