<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户双费率管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/merchantDoubleRate/list");				
				$("#form").submit();
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
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/merchantDoubleRate/">商户双费率列表</a></li>
		<shiro:hasPermission name="cortexs:merchantDoubleRate:edit"><li><a href="${pageContext.request.contextPath}/cortexs/merchantDoubleRate/form">商户双费率添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="merchantDoubleRate" action="${pageContext.request.contextPath}/cortexs/merchantDoubleRate/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商户编号：</label>
				<form:input path="merchantId" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>卡BIN：</label>
				<form:select path="cardBin" class="input-xlarge" >
					<form:option value="" label="--请选择--"/>
					<form:options items="${cardBinList}" itemLabel="cardBin" itemValue="iid" htmlEscape="false"/>
				</form:select>					
				
			</li>
			<li><label>费率：</label>
				<form:input path="rate" htmlEscape="false" maxlength="64" class="input-medium" onkeyup="value=value.replace(/[^\d\.]/g,'')"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnClear"  class="btn btn-primary" type="button" value="清空" onClick="ClearForm('searchForm')"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户编号</th>
				<th>商户名称</th>
				<th>卡BIN</th>
				<th>费率(%)</th>
				<th>创建者</th>
				<th>创建时间</th>
				<shiro:hasPermission name="cortexs:merchantDoubleRate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="merchantDoubleRate">
			<tr>
				<td>
					${merchantDoubleRate.merchantId}
				</td>
				<td>
					${merchantDoubleRate.mrchtName}
				</td>
				<td>
					${merchantDoubleRate.cardBin}
				</td>
				<td>
					${merchantDoubleRate.rate}
				</td>
				<td>
					${fns:getUserById(merchantDoubleRate.createBy.id).name}
				</td>
				<td>
					<fmt:formatDate value="${merchantDoubleRate.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="cortexs:merchantDoubleRate:edit"><td>
    				<a href="${pageContext.request.contextPath}/cortexs/merchantDoubleRate/form?id=${merchantDoubleRate.id}">修改</a>
					<a href="${pageContext.request.contextPath}/cortexs/merchantDoubleRate/delete?id=${merchantDoubleRate.id}" onclick="return confirmx('确认要删除该商户双费率吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>