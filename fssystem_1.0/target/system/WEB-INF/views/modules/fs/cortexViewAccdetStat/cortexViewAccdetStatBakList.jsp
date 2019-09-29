<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>卡余额实时查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#query').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/CortexViewAccdetStat/getBakList");
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
					<div class="sh_title">卡余额汇总</div>
					<div class="shmc_tab2">
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<th>序号</th>									
								<th>卡张数总数</th>
								<th>卡账户总余额(元)</th>								
							</tr>
							<c:if test="${falg != '0'}">
								<tr class="cow">
									<td>1</td>									
									<td>
										${card_cnt_sum}
									</td>
									<td>
										<fmt:formatNumber value="${card_bal_sum}"
													pattern="#,###,###,###,###,##0.##" minFractionDigits="2"></fmt:formatNumber>
									</td>
								</tr>
							</c:if>
							<c:if test="${falg == '0'}">
								<td colspan="18" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
							</c:if>
							<tr>
								<td colspan="18" class="page">${pageBar}</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
