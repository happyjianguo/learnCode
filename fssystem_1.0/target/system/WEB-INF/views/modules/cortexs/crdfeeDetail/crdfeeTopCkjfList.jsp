<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>财卡积分充值明细</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/crdfeeTopCkjf/list");				
				$("#form").submit();
			});
			
			$('#btnExport').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/crdfeeTopCkjf/export");				
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
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/crdfeeTopCkjf/">财卡积分充值明细</a></li>
	</ul>
	
<div class="poup_right">

	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/cortexs/crdfeeTopCkjf/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">卡号：</td>
				<td style="text-align: left;">
					<input type="text" name="pan" id="pan" value="${crdfeeTopCkjf.pan}" maxlength="19" class="inputext_style"/>
				</td>
				<td style="text-align: right;">卡BIN：</td>
				<td style="text-align: left;">
					<input type="text" name="iid" id="iid" value="${crdfeeTopCkjf.iid}" maxlength="11" class="inputext_style"/>
				</td>
				<td style="text-align: right;">充值交易日期：</td>
				<td style="text-align: left;">
					<input type="text" name="begainTranDate" id="begainTranDate" value="${crdfeeTopCkjf.begainTranDate}" maxlength="8" class="inputext_style"/>
					-<input type="text" name="endTranDate" id="endTranDate" value="${crdfeeTopCkjf.endTranDate}" maxlength="8" class="inputext_style"/>
				</td>
			</tr>
			<tr>	
				<td style="text-align: right;">账户类型：</td>
				<td style="text-align: left;">
					<select id="acctype" name="acctype" class="inputext_style">
						<option value="">--请选择--</option>										
						<c:forEach var="model" items="${fns:getDictList('CRDFEE_TYPE')}">
							<option value="${model.value}" <c:if test="${crdfeeTopCkjf.acctype eq model.value}">selected="selected"</c:if>>
								${model.label}
							</option>
						</c:forEach>
					</select>
				</td>
				<td style="text-align: right;" colspan="4">
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
				<th style="text-align: center;">账户类型</th>
				<th style="text-align: center;">充值金额</th>
				<th style="text-align: center;">充值交易日期</th>
				<th style="text-align: center;">有效期(月数)</th>
				<th style="text-align: center;">已消费总金额</th>
				<th style="text-align: center;">剩余总金额</th>
				<th style="text-align: center;">过期日期</th>
				<th style="text-align: center;">过期积分</th>
				<th style="text-align: center;">是否存在剩余积分</th>
				<th style="text-align: center;">最近更新时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="crdfeeTopCkjf" varStatus="index">
				<tr>
					<td>${((page.pageNo-1) * page.pageSize) + index.count}</td>
					<td>
						<fmt:formatNumber value="${crdfeeTopCkjf.tlogId}" pattern="###############0"></fmt:formatNumber>
					</td>
					<td>${crdfeeTopCkjf.pan}</td>
					<td>${crdfeeTopCkjf.iid}</td>
					<td>${fns:getDictLabel(crdfeeTopCkjf.acctype, 'CRDFEE_TYPE', '')}</td>
					<td>
						<fmt:formatNumber value="${crdfeeTopCkjf.amttxn}" pattern="#,###,###,###,###,##0.##"
								minFractionDigits="2"></fmt:formatNumber>
					</td>
					<td>${crdfeeTopCkjf.datelocal}</td>
					<td>${crdfeeTopCkjf.usemonth}</td>
					<td>
						<fmt:formatNumber value="${crdfeeTopCkjf.alreadyamt}" pattern="#,###,###,###,###,##0.##"
								minFractionDigits="2"></fmt:formatNumber>
					</td>
					<td>
						<fmt:formatNumber value="${crdfeeTopCkjf.lastamt}" pattern="#,###,###,###,###,##0.##"
								minFractionDigits="2"></fmt:formatNumber>
					</td>
					<td>${crdfeeTopCkjf.expdate}</td>
					<td>
						<fmt:formatNumber value="${crdfeeTopCkjf.expireamt}" pattern="#,###,###,###,###,##0.##"
								minFractionDigits="2"></fmt:formatNumber>
					</td>
					<td>
						<c:if test="${crdfeeTopCkjf.flag eq '0'}">存在</c:if>
						<c:if test="${crdfeeTopCkjf.flag ne '0'}">不存在</c:if>
					</td>
					<td>${crdfeeTopCkjf.updattime}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
</div>
	
</body>
</html>