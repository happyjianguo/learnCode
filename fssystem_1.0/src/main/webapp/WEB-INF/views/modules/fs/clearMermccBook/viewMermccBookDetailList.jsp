<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>区域消费类型明细</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">	
	$(document).ready(function() {
		
	});

	var exportDetailList=function(consumType) {
		
		$("#form").attr("action","${pageContext.request.contextPath}/ClearMermccBook/exportDetailList");
		$("#form").submit();
		
	}


</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">区域消费类型明细</div>
					<div class="shmc_tab2">
						<div class="sh_title">消费类型：${queryModel.consumTypeName}     交易时间：${queryModel.startDt}--${queryModel.endDt} </div>
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
								
								
							</tr>
							<c:if test="${ ViewClearMermccBook != null  }">
								<c:forEach items="${ViewClearMermccBook}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>
										<td>${info.merNo}</td>
										<td>${info.merName}</td>
										<td>${info.tranNum}</td>
										<td>${info.tranAmt}</td>																																							
																													
								</c:forEach>
								<c:if test="${fn:length(ViewClearMermccBook)==0}">
									<td colspan="20" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<c:if test="${fn:length(ViewClearMermccBook)!=0}">
									<tr>
										<td colspan="2">合计</td>
										<td>${size }</td>
										<td>${tran_num }</td>
										<td>${tran_amt }</td>
									</tr>
								</c:if>
							</c:if>
							
						</table>
						<c:if test="${fn:length(ViewClearMermccBook)!=0}">
						<div style="text-align: right;">
						
						<a href="javascript:exportDetailList('${queryModel.consumType}')">导出excel</a>		
						</div>
						</c:if>
						
					</div>
					<form id="form"  action="${pageContext.request.contextPath}/ClearMermccBook/exportDetailList" method="get">
						<input type="hidden" name="consumProvince" value="${queryModel.consumProvince}">
						<input type="hidden" name="consumCity" value="${queryModel.consumCity}">
						<input type="hidden" name="consumType" value="${queryModel.consumType}">
						<input type="hidden" name="startDt" value="${queryModel.startDt}">
						<input type="hidden" name="endDt" value="${queryModel.endDt}">
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- <div class="sh_footer"></div> -->
</body>
</html>
