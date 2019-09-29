<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>财卡积分消费明细</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/crdfeeSaleCkjf/list");				
				$("#form").submit();
			});
			
			$('#btnExport').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/crdfeeSaleCkjf/export");				
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
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/crdfeeSaleCkjf/">财卡积分消费明细</a></li>
	</ul>
	
<div class="poup_right">

	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/cortexs/crdfeeSaleCkjf/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">卡号：</td>
				<td style="text-align: left;">
					<input type="text" name="pan" id="pan" value="${crdfeeSaleCkjf.pan}" maxlength="19" class="inputext_style"/>
				</td>
				<td style="text-align: right;">卡BIN：</td>
				<td style="text-align: left;">
					<input type="text" name="iid" id="iid" value="${crdfeeSaleCkjf.iid}" maxlength="11" class="inputext_style"/>
				</td>
				<td style="text-align: right;">消费交易日期：</td>
				<td style="text-align: left;">
					<input type="text" name="begainTranDate" id="begainTranDate" value="${crdfeeSaleCkjf.begainTranDate}" maxlength="8" class="inputext_style"/>
					-<input type="text" name="endTranDate" id="endTranDate" value="${crdfeeSaleCkjf.endTranDate}" maxlength="8" class="inputext_style"/>
				</td>
			</tr>
			<tr>	
				<td style="text-align: right;" colspan="8">
					<input id="btnSubmit" class="button" type="button" value="查询"/>
					<input id="btnExport" class="button" type="button" value="导出"/>
					<input type="button"  class="button" value="清空" onClick="ClearForm('form')"/>
				</td>
			</tr>
		</table>
	</form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">TLOG_ID</th>
				<th style="text-align: center;">卡号</th>
				<th style="text-align: center;">卡BIN</th>
				<th style="text-align: center;">购物返积分-消费金额(元)</th>
				<th style="text-align: center;">联名卡送积分-消费金额(元)</th>
				<th style="text-align: center;">消费交易日期</th>
				<th style="text-align: center;">财卡充值明细ID</th>
				<th style="text-align: center;">积分消费标识</th>
				<th style="text-align: center;">最近更新时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="crdfeeSaleCkjf" varStatus="index">
				<tr>
					<td>${((page.pageNo-1) * page.pageSize) + index.count}</td>
					<td>
						<fmt:formatNumber value="${crdfeeSaleCkjf.tlogId}" pattern="###############0"></fmt:formatNumber>
					</td>
					<td>${crdfeeSaleCkjf.pan}</td>
					<td>${crdfeeSaleCkjf.iid}</td>
					<td>
						<fmt:formatNumber value="${crdfeeSaleCkjf.amttxn04}" pattern="#,###,###,###,###,##0.##"
								minFractionDigits="2"></fmt:formatNumber>
					</td>
					<td>
						<fmt:formatNumber value="${crdfeeSaleCkjf.amttxn09}" pattern="#,###,###,###,###,##0.##"
								minFractionDigits="2"></fmt:formatNumber>
					</td>
					<td>${crdfeeSaleCkjf.datelocal}</td>
					<td>${crdfeeSaleCkjf.topCkjfId}</td>
					<td>${crdfeeSaleCkjf.flag}</td>
					<td>${crdfeeSaleCkjf.updattime}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
</div>
	
</body>
</html>