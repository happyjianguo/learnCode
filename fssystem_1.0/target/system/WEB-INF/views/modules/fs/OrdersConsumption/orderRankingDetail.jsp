<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消费订单金额排名</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#exportExcel').click(function() {
				
				$("#form").attr("action","${pageContext.request.contextPath}/OrderRanking/exportExcel");
				$("#form").submit();
			});
		});
	</script>
</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">
							消费订单金额排名列表
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/OrderRanking/exportExcel"
								method="post">
								<input type="hidden" id="batchId" name="batchId" value="${queryModel.batchId}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<td style="text-align: right;" colspan="8">
										<input type="button" value="导出" class="button" id="exportExcel" />
									</td>
								</tr>
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th style="text-align: center;">序号</th>
									<th style="text-align: center;">商户号</th>
									<th style="text-align: center;">公司名称</th>
									<th style="text-align: center;">手机号</th>
									<th style="text-align: center;">订单数</th>
									<th style="text-align: center;">订单总额(元)</th>
									<th style="text-align: center;">购卡张数</th>
									<th style="text-align: center;">消费的卡张数</th>
									<th style="text-align: center;">消费笔数</th>
									<th style="text-align: center;">消费总额(元)</th>
								</tr>
								<c:if test="${orderRankingList != null}">
									<c:forEach items="${orderRankingList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>
												${info.merNo}
											</td>
											<td>
												${info.companyName}
											</td>
											<td>
												${info.cellPhone}
											</td>
											<td>
												${info.orderSum}
											</td>
											<td>
												<fmt:formatNumber value="${info.amtEachCrd}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												${info.panAccount}
											</td>
											<td>
												${info.customCardSum}
											</td>
											<td>
												${info.customSum}
											</td>
											<td>
												<fmt:formatNumber value="${info.tranAmt}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${fn:length(orderRankingList)==0}">
										<td colspan="18" style="text-align: center">
											<span style="color: red">***没有相关记录***</span>
										</td>
									</c:if>
									<tr class="cow">
										<td colspan="18" align="center">
											<input id="btnCancel" class="button" type="button" value="返 回" onclick="history.go(-1)"/>
										</td>
									</tr>
								</c:if>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>