<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>财卡过期积分扣款明细</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/crdfeeCkjfFlog/list");				
				$("#form").submit();
			});
			
			$('#btnExport').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/crdfeeCkjfFlog/export");				
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
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/crdfeeCkjfFlog/">财卡过期积分扣款明细</a></li>
	</ul>
	
<div class="poup_right">

	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/cortexs/crdfeeCkjfFlog/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">扣款商户号：</td>
				<td style="text-align: left;">
					<input type="text" name="merchantno" id="merchantno" value="${crdfeeCkjfFlog.merchantno}" maxlength="15" class="inputext_style"/>
				</td>
				<td style="text-align: right;">扣款终端号：</td>
				<td style="text-align: left;">
					<input type="text" name="termcode" id="termcode" value="${crdfeeCkjfFlog.termcode}" maxlength="8" class="inputext_style"/>
				</td>
				<td style="text-align: right;">交易日期：</td>
				<td style="text-align: left;">
					<input type="text" name="begainTranDate" id="begainTranDate" value="${crdfeeCkjfFlog.begainTranDate}" maxlength="8" class="inputext_style"/>
					-<input type="text" name="endTranDate" id="endTranDate" value="${crdfeeCkjfFlog.endTranDate}" maxlength="8" class="inputext_style"/>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">卡号：</td>
				<td style="text-align: left;">
					<input type="text" name="pan" id="pan" value="${crdfeeCkjfFlog.pan}" maxlength="19" class="inputext_style"/>
				</td>
				<td style="text-align: right;">账户类型：</td>
				<td style="text-align: left;">
					<select id="accflag" name="accflag" class="inputext_style">
						<option value="">--请选择--</option>										
						<c:forEach var="model" items="${fns:getDictList('CRDFEE_TYPE')}">
							<option value="${model.value}" <c:if test="${crdfeeCkjfFlog.accflag eq model.value}">selected="selected"</c:if>>
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
				<th style="text-align: center;">扣款商户号</th>
				<th style="text-align: center;">扣款终端号</th>
				<th style="text-align: center;">扣款账户类型</th>
				<th style="text-align: center;">扣款前账户余额(元)</th>
				<th style="text-align: center;">扣款金额(元)</th>
				<th style="text-align: center;">扣款日期</th>
				<th style="text-align: center;">交易日期</th>
				<th style="text-align: center;">交易时间</th>
				<th style="text-align: center;">流水</th>
				<th style="text-align: center;">系统参考号</th>
				<th style="text-align: center;">交易码</th>
				<th style="text-align: center;">交易渠道</th>
				<th style="text-align: center;">返回码</th>
				<th style="text-align: center;">交易状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="crdfeeCkjfFlog" varStatus="index">
				<tr>
					<td>${((page.pageNo-1) * page.pageSize) + index.count}</td>
					<td>
						<fmt:formatNumber value="${crdfeeCkjfFlog.tlogId}" pattern="###############0"></fmt:formatNumber>
					</td>
					<td>${crdfeeCkjfFlog.pan}</td>
					<td>${crdfeeCkjfFlog.merchantno}</td>
					<td>${crdfeeCkjfFlog.termcode}</td>
					<td>${fns:getDictLabel(crdfeeCkjfFlog.accflag, 'CRDFEE_TYPE', '')}</td>
					<td>
						<fmt:formatNumber value="${crdfeeCkjfFlog.avlbal}" pattern="#,###,###,###,###,##0.##"
								minFractionDigits="2"></fmt:formatNumber>
					</td>
					<td>
						<fmt:formatNumber value="${crdfeeCkjfFlog.amttxn}" pattern="#,###,###,###,###,##0.##"
								minFractionDigits="2"></fmt:formatNumber>
					</td>
					<td>${crdfeeCkjfFlog.localDate}</td>
					<td>${crdfeeCkjfFlog.tranDate}</td>
					<td>${crdfeeCkjfFlog.tranTime}</td>
					<td>
						<fmt:formatNumber value="${crdfeeCkjfFlog.stan}" pattern="###############0"></fmt:formatNumber>
					</td>
					<td>${crdfeeCkjfFlog.rrn}</td>
					<td>
						<fmt:formatNumber value="${crdfeeCkjfFlog.txncode}" pattern="###############0"></fmt:formatNumber>
					</td>
					<td>${crdfeeCkjfFlog.txnsrc}</td>
					<td>${crdfeeCkjfFlog.rspcode}</td>
					<td>
						<fmt:formatNumber value="${crdfeeCkjfFlog.txnstatus}" pattern="###############0"></fmt:formatNumber>
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