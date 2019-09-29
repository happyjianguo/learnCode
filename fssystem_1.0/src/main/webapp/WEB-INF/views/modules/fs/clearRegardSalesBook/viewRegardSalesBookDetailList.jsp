<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>购卡区域消费信息明细</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">	
	$(document).ready(function() {
		
	});

	var exportDetailList=function(salesCity) {
		
		$("#form").attr("action","${pageContext.request.contextPath}/ClearRegardSalesBook/exportDetailList");
		$("#form").submit();
	}


</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">购卡区域消费信息明细</div>
					<div class="shmc_tab2">
						<div class="sh_title">购卡区域：${queryModel.salesCityName}      交易时间：${queryModel.startDt}--${queryModel.endDt} </div>
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<c:if test="${ ViewStlBookDetailList != null and fn:length(ViewStlBookDetailList)!=0 }">
								<tr>
									<td colspan="20" style="text-align: right">
										<input type="button" value="导出PDF" class="button" id="getDetailListPdf" />	
									</td>
								</tr>
							</c:if>
							<tr>
								<th>序号</th>
								<th>商户号</th>
								<th>商户名称</th>
								<th>交易笔数</th>
								<th>交易金额</th>
								<th>商户所在地</th>
								
							</tr>
							<c:if test="${ ViewClearRegardSalesBook != null  }">
								<c:forEach items="${ViewClearRegardSalesBook}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>
										<td>${info.merNo}</td>
										<td>${info.merName}</td>
										<td>${info.tranNum}</td>
										<td>${info.tranAmt}</td>																																							
										<td>${info.consumCityName}</td>																				
								</c:forEach>
								<c:if test="${fn:length(ViewClearRegardSalesBook)==0}">
									<td colspan="20" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<c:if test="${fn:length(ViewClearRegardSalesBook)!=0}">
								<tr>
									<td colspan="2">全国合计</td>
									<td>${size }</td>
									<td>${tran_num }</td>
									<td>${tran_amt }</td>
									<td>  </td>
								</tr>
								</c:if>	
							</c:if>
							
						</table>
						<c:if test="${fn:length(ViewClearRegardSalesBook)!=0}">
						<div style="text-align: right;">
						
						<a href="javascript:exportDetailList('${queryModel.salesCity}')">导出excel</a>		
						</div>
						</c:if>
						
					</div>
					<form id="form"  action="${pageContext.request.contextPath}/ClearRegardSalesBook/exportDetailList" method="get">
						<input type="hidden" name="crdproduct" value="${queryModel.crdproduct}">
						<input type="hidden" name="salesProvince" value="${queryModel.salesProvince}">
						<input type="hidden" name="salesCity" value="${queryModel.salesCity}">
						<input type="hidden" name="startDt" value="${queryModel.startDt}">
						<input type="hidden" name="endDt" value="${queryModel.endDt}">
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="sh_footer"></div>
</body>
</html>
