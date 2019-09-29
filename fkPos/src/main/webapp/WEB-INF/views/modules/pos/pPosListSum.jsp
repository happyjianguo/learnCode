<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>POS机统计信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() { 				
				$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/pPos/listSum");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要导出POS机信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","/pos/pPos/exportSum");
						$("#searchForm").submit();
						$("#searchForm").attr("action","/pos/pPos/listSum");
					}
				},{buttonsFocus:1});
				$('.jbox-body .jbox-icon').css('top','55px');
			});		
		});
	</script>
</head>
<body>
	<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" /> 
	<div class="sh_title">POS机统计信息</div>
	<form:form id="searchForm" modelAttribute="PPos" action="${pageContext.request.contextPath}/pos/pPos/listSum" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>厂商：</label>
				<form:select path="factoryId" class="input-xlarge" onchange="getPModelList();">
					<form:option value="" label="--请选择--"/>
					<form:options items="${pFactoryList}" itemLabel="factoryName" itemValue="id" htmlEscape="false"/>
				</form:select>					
			</li>
			<li><label>型号：</label>
				<form:select path="modelId" class="input-xlarge">
					<form:option value="" label="--请选择--"/>
					<form:options items="${pModelList}" itemLabel="modelId" itemValue="modelId" htmlEscape="false"/>
				</form:select>					
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button"	value="导出" />
			</li>	
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>厂商名称</th>
				<th>型号</th>
				<th>空闲数量</th>
				<th>在用数量</th>
				<th>故障数量</th>
				<th>报废数量</th>
				<th>总数量</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${listSum}" var="pPos">
			<tr>
				<td>
					${pPos.factoryName}
				</td>
				<td>
					${pPos.modelId}
				</td>
				<td>
					${pPos.idleNum}
				</td>
				<td>
					${pPos.useNum}
				</td>
				<td>
					${pPos.malfuncNum}
				</td>
				<td>
					${pPos.scrapNum}
				</td>
				<td>
					${pPos.totleNum}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>