<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>退款明细管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/sqlServer/ReMemberCardSale/list");				
				$("#form").submit();
			});
			
			$('#btnExport').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/sqlServer/ReMemberCardSale/export");				
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
		<li class="active"><a href="${pageContext.request.contextPath}/sqlServer/ReMemberCardSale/list">退款明细</a></li>
	</ul>
	
<div class="poup_right">
	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/sqlServer/ReMemberCardSale/list" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">退款商户号：</td>
				<td style="text-align: left;">
					<input type="text" name="LRUID" id="LRUID" value="${reMemberCardSale.LRUID}" maxlength="15" class="inputext_style"/>
				</td>
				<td style="text-align: right;">退款终端号：</td>
				<td style="text-align: left;">
					<input type="text" name="sSerialNum" id="sSerialNum" value="${reMemberCardSale.sSerialNum}" maxlength="8" class="inputext_style"/>
				</td>
				<td style="text-align: right;">原交易日期：</td>
				<td style="text-align: left;">
					<input type="text" name="begainTranDate" id="begainTranDate" value="${reMemberCardSale.begainTranDate}" maxlength="8" class="inputext_style"/>
					- <input type="text" name="endTranDate" id="endTranDate" value="${reMemberCardSale.endTranDate}" maxlength="8" class="inputext_style"/>
				</td>
			</tr>
			<tr>	
				<td style="text-align: right;">退款卡号：</td>
				<td style="text-align: left;">
					<input type="text" name="aCardnum" id="aCardnum" value="${reMemberCardSale.aCardnum}" maxlength="16" class="inputext_style"/>
				</td>
				<td style="text-align: right;">退款状态：</td>
				<td style="text-align: left;">
					<select id="bstate" name="bstate" class="inputext_style">
						<option value="">--请选择--</option>										
						<c:forEach var="entity" items="${fns:getDictList('REFUND_STATE')}">
							<option value="${entity.value}" <c:if test="${reMemberCardSale.bstate eq entity.value}">selected="selected"</c:if>>${entity.label}</option>
						</c:forEach>
					</select>
				</td>
				<td style="text-align: right;">创建日期：</td>
				<td style="text-align: left;">
					<input type="text" name="begainARegDate" id="begainARegDate" value="${reMemberCardSale.begainARegDate}" maxlength="8" class="inputext_style"/>
					- <input type="text" name="endARegDate" id="endARegDate" value="${reMemberCardSale.endARegDate}" maxlength="8" class="inputext_style"/>
				</td>
			</tr>
			<tr>	
				<td style="text-align: right;"></td>
				<td style="text-align: left;"></td>
				<td style="text-align: right;"></td>
				<td style="text-align: left;"></td>
				<td style="text-align: right;" colspan="2">
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
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">退款商户号</th>
				<th style="text-align: center;">退款终端号</th>
				<th style="text-align: center;">退款卡号</th>
				<th style="text-align: center;">退款金额</th>
				<th style="text-align: center;">原交易日期</th>
				<th style="text-align: center;">原批次号</th>
				<th style="text-align: center;">原流水号</th>
				<th style="text-align: center;">原参考号</th>
				<th style="text-align: center;">退款时间</th>
				<th style="text-align: center;">退款状态</th>
				<th style="text-align: center;">状态描述</th>
				<th style="text-align: center;">创建日期</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="reMemberCardSale" varStatus="index">
				<tr>
					<td>${((page.pageNo-1) * page.pageSize) + index.count }</td>
					<td>${reMemberCardSale.LRUID}</td>
					<td>${reMemberCardSale.sSerialNum}</td>
					<td>${reMemberCardSale.aCardnum}</td>
					<td>
						<fmt:formatNumber value="${reMemberCardSale.aSaleInegral}" pattern="#,###,###,###,###,##0.##"
								minFractionDigits="2"></fmt:formatNumber>
					</td>
					<td>${reMemberCardSale.oldaRegDate}</td>
					<td>
						<fmt:formatNumber value="${reMemberCardSale.lOrdNum}" pattern="###############0"></fmt:formatNumber>
					</td>
					<td>
						<fmt:formatNumber value="${reMemberCardSale.lInvNum}" pattern="###############0"></fmt:formatNumber>
					</td>
					<td>
						<fmt:formatNumber value="${reMemberCardSale.refOldid}" pattern="###############0"></fmt:formatNumber>
					</td>
					<td>${reMemberCardSale.retime}</td>
					<td>${fns:getDictLabel(reMemberCardSale.bstate, 'REFUND_STATE', '')}</td>
					<td>${reMemberCardSale.reMsg}</td>
					<td>${reMemberCardSale.aRegDate}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="18" align="center">
					<font color="red">退款总金额(元)：<fmt:formatNumber value="${refundSumAmt}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber></font>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
</div>
	
</body>
</html>