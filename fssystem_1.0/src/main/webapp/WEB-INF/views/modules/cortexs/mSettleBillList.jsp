<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户结算开票设置管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/mSettleBill/list");				
				$("#form").submit();
			});		
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#form").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/mSettleBill/">商户结算开票设置列表</a></li>
	</ul>
	
<div class="poup_right">
	
		
	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/cortexs/mSettleBill/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">商户号：</td>
				<td style="text-align: left;">
					<input type="text" name="mrchno" id="mrchno"value="${MSettleBill.mrchno }" maxlength="15" class="inputext_style"/>
				</td>
				<td style="text-align: right;">商户名称：</td>
				<td style="text-align: left;">
					<input type="text" name="mrchtName" id="mrchtName" value="${MSettleBill.mrchtName }" maxlength="20" class="inputext_style"/>
				</td>
				<td>
					<input id="btnSubmit" class="button" type="submit" value="查询"/>
				</td>
			</tr>
		</table>
	</form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户号</th>
				<th>商户名称</th>
				<shiro:hasPermission  name="cortexs:mSettleBill:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mSettleBill">
			<tr>
				<td>
					${mSettleBill.mrchno}
				</td>
				<td>
					${mSettleBill.mrchtName}
				</td>
				<td>
				<shiro:hasPermission name="cortexs:mSettleBill:edit">
					<a href="${pageContext.request.contextPath}/cortexs/mSettleBill/show?id=${mSettleBill.mrchno}">详情</a>
    				<a href="${pageContext.request.contextPath}/cortexs/mSettleBill/form?id=${mSettleBill.mrchno}">修改</a>
				</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
</div>
	
</body>
</html>