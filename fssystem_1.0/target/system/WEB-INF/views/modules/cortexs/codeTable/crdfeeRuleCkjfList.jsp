<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>积分卡BIN管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/crdfeeRuleCkjf/list");				
				$("#form").submit();
			});
			
			$('#btnExport').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/crdfeeRuleCkjf/export");				
				$("#form").submit();
			});	
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#form").submit();
        	return false;
        }
		
		function toUpdate(id){
			if(confirm("确认要修改该积分卡BIN信息吗？")){
				window.location.href="${pageContext.request.contextPath}/cortexs/crdfeeRuleCkjf/form?id=" + id
						+ "&random=" + Math.random();
			}
		}
		
		function toDelete(id){
			if(confirm("确认删除该积分卡BIN信息吗？")){
				window.location.href="${pageContext.request.contextPath}/cortexs/crdfeeRuleCkjf/delete?id=" + id
						+ "&random=" + Math.random();
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/crdfeeRuleCkjf/">积分卡BIN列表</a></li>
		<shiro:hasPermission  name="cortexs:crdfeeRuleCkjf:edit">
		<li><a href="${pageContext.request.contextPath}/cortexs/crdfeeRuleCkjf/form">积分卡BIN添加</a></li>
		</shiro:hasPermission>
	</ul>
	
<div class="poup_right">

	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/cortexs/crdfeeRuleCkjf/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">积分卡BIN：</td>
				<td style="text-align: left;">
					<input type="text" name="iid" id="iid" value="${crdfeeRuleCkjf.iid}" maxlength="11" class="inputext_style"/>
				</td>
				<td style="text-align: right;" colspan="4">
					<input id="btnSubmit" class="button" type="button" value="查询"/>
					<shiro:hasPermission  name="cortexs:crdfeeRuleCkjf:edit">
						<input id="btnExport" type="button" value="导出" class="button" />
					</shiro:hasPermission>
					<input type="button" value="清空" class="button" onClick="ClearForm('form')"/>
				</td>
			</tr>
		</table>
	</form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">积分卡BIN</th>
				<th style="text-align: center;">有效期(月数)</th>
				<th style="text-align: center;">购物返积分</th>
				<th style="text-align: center;">联名卡送积分</th>
				<th style="text-align: center;">积分卡BIN是否生效</th>
				<th style="text-align: center;">最后操作者</th>
				<shiro:hasPermission  name="cortexs:crdfeeRuleCkjf:edit">
				<th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<shiro:hasPermission name="cortexs:crdfeeRuleCkjf:view">
			<c:forEach items="${page.list}" var="crdfeeRuleCkjf" varStatus="index">
				<tr>
					<td>${((page.pageNo-1) * page.pageSize) + index.count}</td>
					<td>${crdfeeRuleCkjf.iid}</td>
					<td>${crdfeeRuleCkjf.usemonth}</td>
					<td>${fns:getDictLabel(crdfeeRuleCkjf.acctype4, 'IS_EFFECTIVE', '')}</td>
					<td>${fns:getDictLabel(crdfeeRuleCkjf.acctype9, 'IS_EFFECTIVE', '')}</td>
					<td>${fns:getDictLabel(crdfeeRuleCkjf.flag, 'IS_EFFECTIVE', '')}</td>
					<td>${crdfeeRuleCkjf.oper}</td>
					<td>
						<a href="javascript:toUpdate('${crdfeeRuleCkjf.id}');">修改</a>
						<a href="javascript:toDelete('${crdfeeRuleCkjf.id}');">删除</a>
					</td>
				</tr>
			</c:forEach>
		</shiro:hasPermission>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
</div>
	
</body>
</html>