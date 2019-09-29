<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户结算账号信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() { 				
				$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/merchTerminalAccNo/list");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要导出商户结算账号信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/merchTerminalAccNo/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/merchTerminalAccNo/list");
					}
				},{buttonsFocus:1});
				$('.jbox-body .jbox-icon').css('top','55px');
			});			
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
		<li class="active"><a href="${pageContext.request.contextPath}/pos/merchTerminalAccNo/">商户结算账号信息列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="merchTerminalAccNo" action="${pageContext.request.contextPath}/pos/merchTerminalAccNo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商户号：</label>
				<form:input path="mechno" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>终端号：</label>
				<form:input path="termcode" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label></label></li><li><label></label></li><li><label></label></li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button"	value="导出" />
				<input id="btnClear"  class="btn btn-primary" type="button" value="清空" onClick="ClearForm('searchForm')"/>
			</li>			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>商户号</th>
				<th>终端号</th>
				<th>商户结算账号</th>
				<th>商户结算账号名称</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="merchTerminalAccNo" varStatus="index">
			<tr>
				<td>${((page.pageNo-1) * page.pageSize) + index.count}</td>		
				<td>${merchTerminalAccNo.mechno}</td>
				<td>${merchTerminalAccNo.termcode}</td>
				<td>${merchTerminalAccNo.accno}</td>
				<td>${merchTerminalAccNo.accName}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>