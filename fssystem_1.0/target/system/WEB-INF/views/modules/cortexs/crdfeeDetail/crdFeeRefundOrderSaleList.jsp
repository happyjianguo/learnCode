<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>新福卡退款明细管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/CrdFeeRefundOrder/list");				
				$("#form").submit();
			});
			
			$('#btnExport').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/CrdFeeRefundOrder/export");				
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
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/CrdFeeRefundOrder/">退款明细</a></li>
	</ul>
	
<div class="poup_right">
	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/cortexs/CrdFeeRefundOrder/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">退款商户号：</td>
				<td style="text-align: left;">
					<input type="text" name="merchantno" id="merchantno" value="${crdFeeRefundOrder.merchantno}" maxlength="15" class="inputext_style"/>
				</td>
				<td style="text-align: right;">退款终端号：</td>
				<td style="text-align: left;">
					<input type="text" name="termcode" id="termcode" value="${crdFeeRefundOrder.termcode}" maxlength="8" class="inputext_style"/>
				</td>
				<td style="text-align: right;">原交易日期：</td>
				<td style="text-align: left;">
					<input type="text" name="begainTranDate" id="begainTranDate" value="${crdFeeRefundOrder.begainTranDate}" maxlength="8" class="inputext_style"
						onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
					- <input type="text" name="endTranDate" id="endTranDate" value="${crdFeeRefundOrder.endTranDate}" maxlength="8" class="inputext_style"
						onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
				</td>
			</tr>
			<tr>	
				<td style="text-align: right;">退款卡号：</td>
				<td style="text-align: left;">
					<input type="text" name="pan" id="pan" value="${crdFeeRefundOrder.pan}" maxlength="16" class="inputext_style"/>
				</td>
				<td style="text-align: right;">退款状态：</td>
				<td style="text-align: left;">
					<select id="refundflag" name="refundflag" class="inputext_style">
						<option value="">--请选择--</option>										
						<c:forEach var="entity" items="${fns:getDictList('REFUND_STATE')}">
							<option value="${entity.value}" <c:if test="${crdFeeRefundOrder.refundflag eq entity.value}">selected="selected"</c:if>>${entity.label}</option>
						</c:forEach>
					</select>
				</td>
				<td style="text-align: right;">退款日期：</td>
				<td style="text-align: left;">
					<input type="text" name="begainAdddate" id="begainAdddate" value="${crdFeeRefundOrder.begainAdddate}" maxlength="8" class="inputext_style"
						onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
					- <input type="text" name="endAdddate" id="endAdddate" value="${crdFeeRefundOrder.endAdddate}" maxlength="8" class="inputext_style"
						onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
				</td>
			</tr>
			<tr>	
				<td style="text-align: right;" colspan="8">
					<input id="btnSubmit" class="button" type="button" value="查询"/>
					<input id="btnExport" type="button" value="导出" class="button" />
					<input type="button" value="清空" class="button" onClick="ClearForm('form')"/>
				</td>
			</tr>
		</table>
	</form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">扣款明细ID</th>
				<th style="text-align: center;">退款商户号</th>
				<th style="text-align: center;">退款终端号</th>
				<th style="text-align: center;">退款卡号</th>
				<th style="text-align: center;">退款金额</th>
				<th style="text-align: center;">原交易日期</th>
				<th style="text-align: center;">原流水号</th>
				<th style="text-align: center;">原参考号</th>
				<th style="text-align: center;">退款状态</th>
				<th style="text-align: center;">退款日期</th>
				<th style="text-align: center;">退款时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="crdFeeRefundOrder" varStatus="index">
				<tr>
					<td>${crdFeeRefundOrder.crdfeelogId}</td>
					<td>${crdFeeRefundOrder.merchantno}</td>
					<td>${crdFeeRefundOrder.termcode}</td>
					<td>${crdFeeRefundOrder.pan}</td>
					<td>
						<fmt:formatNumber value="${crdFeeRefundOrder.fee}" pattern="#,###,###,###,###,##0.##"
								minFractionDigits="2"></fmt:formatNumber>
					</td>
					<td>${crdFeeRefundOrder.tranDate}</td>
					<td>
						<fmt:formatNumber value="${crdFeeRefundOrder.stan}" pattern="###############0"></fmt:formatNumber>
					</td>
					<td>${crdFeeRefundOrder.rrn}</td>
					<td>${fns:getDictLabel(crdFeeRefundOrder.refundflag, 'REFUND_STATE', '')}</td>
					<td>${crdFeeRefundOrder.adddate}</td>
					<td>${crdFeeRefundOrder.addtime}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="16" align="center">
					<font color="red">
						退款金额汇总(元)：<fmt:formatNumber value="${refundSum}" pattern="#,###,###,###,###,##0.00" minFractionDigits="2"></fmt:formatNumber>
					</font>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
</div>
	
</body>
</html>