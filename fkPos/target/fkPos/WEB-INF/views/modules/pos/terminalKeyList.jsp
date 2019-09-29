<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>终端主密钥管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() { 	
				$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/terminalKey/list");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要导出终端主密钥信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/terminalKey/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/terminalKey/list");
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
	<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" /> 
	<div class="sh_title">终端主密钥列表</div>
	<form:form id="searchForm" modelAttribute="terminalKey" action="${pageContext.request.contextPath}/pos/terminalKey/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<ul class="ul-form">
			<li><label>商户号：</label>
				<form:input path="merchantId" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>终端号：</label>
				<form:input path="terminalId" htmlEscape="false" maxlength="8" class="input-medium"/>
			</li>		
			<li><label>POS-SN：</label>
				<form:input path="posSn" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns" style="padding-left: 32px;">
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
				<th>商户号</th>
				<th>商户名称</th>
				<th>终端号</th>	
				<th>商户结算账号</th>
				<th>店铺及款台位置</th>				
				<th>密文</th>
				<th>POS-SN</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="terminalKey">
			<tr>
				<td>${terminalKey.merchantId}</td>
				<td>${terminalKey.merchantCname}</td>
				<td>${terminalKey.terminalId}</td>
				<td>${terminalKey.accno}</td>
				<td>${terminalKey.setAddr}</td>
				<td>${terminalKey.tmkMasterKey}</td>
				<td>${terminalKey.posSn}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>