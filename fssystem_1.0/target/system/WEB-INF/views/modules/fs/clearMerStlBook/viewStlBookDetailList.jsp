<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易明细</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">	
$(document).ready(function() {
	$('#getDetailListExcel').click(function() {
		$("#form").attr("action","${pageContext.request.contextPath}/ClearMerStlBook/getDetailListExcel");
		$("#form").submit();
	});
	$('#getDetailListPdf').click(function() {
		$("#form").attr("action","${pageContext.request.contextPath}/ClearMerStlBook/getDetailListPdf");
		$("#form").submit();
	});	
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	$('#closeBtn').click(function() {
		parent.layer.close(index);
	});
});
</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">交易明细</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/ClearMerStlBook/getDetailListPdf" method="post">
							<input type="hidden" id="id" name="id" value="${id}"/>							
						</form>
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<c:if test="${ ViewStlBookDetailList != null and fn:length(ViewStlBookDetailList)!=0 }">
								<tr>
									<td colspan="20" style="text-align: right">
										<input type="button" value="导出Excel" class="button" id="getDetailListExcel" />	
										<input type="button" value="导出PDF" class="button" id="getDetailListPdf" />	
									</td>
								</tr>
							</c:if>
							<tr>
								<th>序号</th>
								<th>商户编号</th>
								<th>商户名称</th>
								<th>终端号</th>
								<th>卡号</th>
								<th>交易类型</th>
								<th>交易日期</th>
								<th>交易时间</th>															
								<th>交易金额（元）</th>
								<th>费率(%)</th>
								<th>手续费（元）</th>
							</tr>
							<c:if test="${ ViewStlBookDetailList != null  }">
								<c:forEach items="${ViewStlBookDetailList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>
										<td>${info.merNo}</td>
										<td>${info.merName}</td>
										<td>${info.termNo}</td>
										<td>${info.cardNo}</td>										
										<td>${info.tranTypeDesc}</td>
										<td>${info.tranDate}</td>
										<td>${info.tranTime}</td>
										<td>${info.tranAmt}</td>																				
										<td>${info.timezone}</td>																				
										<td>${info.fee}</td>																				
								</c:forEach>
								<c:if test="${fn:length(ViewStlBookDetailList)==0}">
									<td colspan="20" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="20" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ ViewStlBookDetailList == null  }">
								<tr>
									<td colspan="20" style="text-align: center"><span style="color: red">***请查询***</span></td>
								</tr>
							</c:if>							
							<c:if test="${ ViewStlBookDetailList != null  }">
								<tr>
									<td colspan="20" style="text-align: center">
										<span style="color: red">
										合计
										&nbsp;&nbsp;&nbsp;&nbsp;交易金额 ：${tran_amt}										
										&nbsp;&nbsp;&nbsp;&nbsp;手续费 ：${fee}										
										</span>
									</td>
								</tr>
							</c:if>
						</table>
						<div style="text-align: center;">
						 	<input type="button" id="closeBtn" value="关闭" class="button" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- <div class="sh_footer"></div> -->
</body>
</html>
