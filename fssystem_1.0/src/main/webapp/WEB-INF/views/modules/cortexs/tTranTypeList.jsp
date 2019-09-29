<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易类型设置管理</title>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/tTranType/list");				
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
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/tTranType/">交易类型设置列表</a></li>
		<shiro:hasPermission name="cortexs:tTranType:edit"><li><a href="${pageContext.request.contextPath}/cortexs/tTranType/form">交易类型设置添加</a></li></shiro:hasPermission>
	</ul>
	
<div class="poup_right">
	
		
	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/cortexs/tTranType/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">交易类型描述：</td>
				<td style="text-align: left;">
					<input type="text" name="tranTypeDesc" id="tranTypeDesc"value="${TTranType.tranTypeDesc }" maxlength="200" class="inputext_style"/>
				</td>
				<td style="text-align: right;">金额符号：</td>
				<td style="text-align: left;">
					<select id="amtFlag" name="amtFlag" class="inputext_style"  value="${TTranType.amtFlag}">
						<option value="" >--请选择--</option>
						<c:forEach var="entity" items="${fns:getDictList('AMT_FLAG')}">
							<option value="${entity.value }" <c:if test="${TTranType.amtFlag eq entity.value}">selected="selected"</c:if>>${entity.label }</option>
						</c:forEach>
						
					</select>
				</td>
				<td style="text-align: right;">是否显示：</td>
				<td style="text-align: left;">
					<select name="showFlag" id="showFlag" class="inputext_style" value="${TTranType.showFlag}">
						<option value="" >--请选择--</option>
						<c:forEach var="entity" items="${fns:getDictList('SHOW_FLAG')}">
							<option value="${entity.value }" >${entity.label }</option>
						</c:forEach>
						
					</select>
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
				<th>交易类型ID</th>
				<th>交易类型描述</th>
				<th>功能码</th>
				<th>交易码</th>
				<th>子处理码</th>
				<th>金额符号</th>
				<th>是否需要显示</th>
				<shiro:hasPermission name="cortexs:tTranType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tTranType">
			<tr>
				<td><a href="/cortexs/tTranType/form?id=${tTranType.id}">
					${tTranType.id}
				</a></td>
				<td>
					${tTranType.tranTypeDesc}
				</td>
				<td>
					${tTranType.fncode}
				</td>				
				<td>
					${tTranType.txncode}
				</td>
				<td>
					${tTranType.subTxncode}
				</td>
				<td>
					${fns:getDictLabel(tTranType.amtFlag, 'AMT_FLAG', '')}
				</td>
				<td>
					${fns:getDictLabel(tTranType.showFlag, 'SHOW_FLAG', '')}
				</td>
				<shiro:hasPermission name="cortexs:tTranType:edit"><td>
    				<a href="${pageContext.request.contextPath}/cortexs/tTranType/form?id=${tTranType.id}">修改</a>
					<a href="${pageContext.request.contextPath}/cortexs/tTranType/delete?id=${tTranType.id}" onclick="return confirmx('确认要删除该交易类型设置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
</div>
	
</body>
</html>