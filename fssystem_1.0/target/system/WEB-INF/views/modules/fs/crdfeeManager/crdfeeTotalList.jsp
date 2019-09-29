<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>分润报表</title>
		<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$('#exportExcel').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/CrdfeeTotal/exportExcel");
			$("#form").submit();
		});
		
		$('#query').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/CrdfeeTotal/getList");
			$("#form").submit();
		});		
	});	
	
	function resetQuery() {
		$("#merNo").val("");
	}
	
</script>
	</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">
							扣款汇总查询
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/CrdfeeTotal/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">年/月份：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style"
												value="${query.startStlDate}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMM',isShowClear:true,readOnly:true})"
												name="startStlDate" id="startStlDate" />
										</td>
										<td style="text-align: right;">结算日期：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style"
												value="${query.stlDate}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
												name="stlDate" id="stlDate" />
										</td>
										<td style="text-align: right;" colspan="4">
											<input type="button" value="查询" class="button" id="query" />
											<input type="button" value="导出" class="button"
												id="exportExcel" />
											<input type="button" value="清空" class="button" onClick="ClearForm('form')"/>
										</td>
									</tr>
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th>序号</th>
									<th>商户号</th>
									<th>商户名称</th>
									<th>账户管理费(元)</th>
									<th>卡片数</th>
									<th>结出金额(元)</th>
									<th>结算日期</th>
								</tr>
								<c:if test="${crdfeeTotalList != null}">
									<c:forEach items="${crdfeeTotalList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>${info.merNo}</td>
											<td>${info.merName}</td>
											<td>
												<fmt:formatNumber value="${info.consumAmt}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												${info.consumNum}
											</td>
											<td>
												<fmt:formatNumber value="${info.tranAmt}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>${info.stlDate}</td>
									</c:forEach>
									<c:if test="${fn:length(crdfeeTotalList)==0}">
										<td colspan="18" style="text-align: center">
											<span style="color: red">***没有相关记录***</span>
										</td>
									</c:if>
									<tr>
										<td colspan="18" class="page">
											${pageBar}
										</td>
									</tr>
								</c:if>
								<c:if test="${crdfeeTotalList == null}">
									<tr>
										<td colspan="18" style="text-align: center">
											<span style="color: red">***请查询***</span>
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
