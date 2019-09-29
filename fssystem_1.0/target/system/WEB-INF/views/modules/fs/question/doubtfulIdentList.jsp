<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>可疑订单预警</title>
		<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$('#query').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/DoubtfulIdent/getList");
			$("#form").submit();
		});		
	});	
	
	function resetQuery() {
		$("#suspiciousDate").val(CurentDayTime(null,"-1"));
	}
	
	function CurentDayTime(monthFlag,dateFlag) {
		var now = new Date();
		var year = now.getFullYear(); 	//年
		var month = now.getMonth() + 1; //月
		if(monthFlag!=null&&monthFlag=="-1"){
			month = now.getMonth(); //月
		}else if(monthFlag!=null&&monthFlag=="+1"){
			month = now.getMonth()+2; //月
		}		
		var day = now.getDate(); 		//日
		if(dateFlag!=null&&dateFlag=="-1"){
			day = now.getDate()-1; 
		}else if(dateFlag!=null&&dateFlag=="-7"){
			day = now.getDate()-7; 
		}else if(dateFlag!=null&&dateFlag=="+1"){
			day = now.getDate()+1; 
		}
		var clock = year + "";
		if (month < 10)
			clock += "0";
		clock += month + "";
		if (day < 10)
			clock += "0";
		clock += day + "";
	 	
		return (clock);
	}
	
</script>
	</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">
							可疑订单预警
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/DoubtfulIdent/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage"
									value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize"
									value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">
											统计日期：
										</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style"
												value="${query.suspiciousDate}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
												name="suspiciousDate" id="suspiciousDate" />
										</td>
										<td style="text-align: right;" colspan="2">
											<input type="submit" value="查询" class="button" id="query" />
											<input type="button" value="清空" class="button"
												onclick='resetQuery()' />
										</td>
									</tr>
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th>
										序号
									</th>
									<th>
										订单号
									</th>
									<th>
										购卡总金额
									</th>
									<th>
										购卡日期
									</th>
									<th>
										预警日期
									</th>
									<th>
										备付金金额
									</th>
									<!-- <th>
										备付金占比
									</th> -->
								</tr>
								<c:if test="${suspiciousOrderEarlyWarningList != null}">
									<c:forEach items="${suspiciousOrderEarlyWarningList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>
												${info.ordercode}
											</td>											
											<td>
												<fmt:formatNumber value="${info.purchaseAmt}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												${info.purchaseDate}
											</td>
											<td>
												${info.suspiciousDate}
											</td>
											<td>
												<fmt:formatNumber value="${info.provisions}"
													pattern="#,###,###,###,###,##0.##" minFractionDigits="2"></fmt:formatNumber>
											</td>
											<%-- <td>
												<fmt:formatNumber value="${info.provisionsRate}"
													pattern="#,###,###,###,###,##0.##" minFractionDigits="2"></fmt:formatNumber>
											</td> --%>
									</c:forEach>
									<c:if test="${fn:length(suspiciousOrderEarlyWarningList)==0}">
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
								<c:if test="${ suspiciousOrderEarlyWarningList == null  }">
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
