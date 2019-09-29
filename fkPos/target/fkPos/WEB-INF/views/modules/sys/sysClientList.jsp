<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户端管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/sysClient/">客户端列表</a></li>
		<shiro:hasPermission name="sys:sysClient:edit"><li><a href="${ctx}/sys/sysClient/form">客户端添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysClient" action="${ctx}/sys/sysClient/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>客户端ID：</label>
				<form:input path="clientid" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>客户端名称：</label>
				<form:input path="clientname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>客户端ID</th>
				<th>客户端名称</th>
				<th>客户端密串</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="sys:sysClient:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysClient">
			<tr>
				<td><a href="${ctx}/sys/sysClient/form?id=${sysClient.id}">
					${sysClient.clientid}
				</a></td>
				<td>
					${sysClient.clientname}
				</td>
				<td>
					${sysClient.clientsecret}
				</td>
				<td>
					<fmt:formatDate value="${sysClient.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sysClient.remarks}
				</td>
				<shiro:hasPermission name="sys:sysClient:edit"><td>
    				<a href="${ctx}/sys/sysClient/form?id=${sysClient.id}">修改</a>
					<a href="${ctx}/sys/sysClient/delete?id=${sysClient.id}" onclick="return confirmx('确认要删除该客户端吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>