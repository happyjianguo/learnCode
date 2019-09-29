<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>备付金信息</title>
		<meta name="decorator" content="default" />
		<script type="text/javascript">
		$(document).ready(function() {
				$('#exportExcel').click(function() {
					$("#form").attr("action","${pageContext.request.contextPath}/ClientAccBalance/exportExcel");
					$("#form").submit();
				});
				$('#query').click(function() {
					$("#form").attr("action","${pageContext.request.contextPath}/ClientAccBalance/getList");
					$("#form").submit();
				});		
			});	
	var resetQuery = function() {
		$("#dailyDate").val(CurentDayTime(null,"-1"));		
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
							备付金
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/ClientAccBalance/getList"
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
												value="${query.dailyDate}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
												name="dailyDate" id="dailyDate" />
										</td>
										<td style="text-align: right;" colspan="2">
											<input type="submit" value="查询" class="button" id="query" />
											<input type="button" value="导出" class="button"
												id="exportExcel" />
											<input type="button" value="清空" class="button"
												onClick='resetQuery()' />
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
										统计日期
									</th>
									<th>
										卡余额(元)
									</th>
									<th>
										未结算金额(元)
									</th>
									<th>
										对账差错金额(元)
									</th>
									<th>
										客户资金帐户总余额(元)
									</th>
									<th>
										备注
									</th>
								</tr>
								<c:if test="${ ClientAccBalanceList != null  }">
									<c:forEach items="${ClientAccBalanceList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>
												${info.dailyDate}
											</td>											
											<td>
												<fmt:formatNumber value="${info.cardBal}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.waitStlAmt}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.errAmt}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.custTotlAmt}"
													pattern="#,###,###,###,###,##0.##" minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												${info.comments}
											</td>
									</c:forEach>
									<c:if test="${fn:length(ClientAccBalanceList)==0}">
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
								<c:if test="${ ClientAccBalanceList == null  }">
									<tr>
										<td colspan="18" style="text-align: center">
											<span style="color: red">***请查询***</span>
										</td>
									</tr>
								</c:if>
								<c:if test="${ ClientAccBalanceList != null  }">
									<tr>
										<td colspan="18" style="text-align: center">
											<span style="color: red"> 合计
												&nbsp;&nbsp;&nbsp;&nbsp;卡余额:<fmt:formatNumber value="${sumCardBal}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber> 
												&nbsp;&nbsp;&nbsp;&nbsp;未结算金额:<fmt:formatNumber value="${sumWaitStlAmt}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber> 
												&nbsp;&nbsp;&nbsp;&nbsp;对账差错金额:<fmt:formatNumber value="${sumErrAmt}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber> 
												&nbsp;&nbsp;&nbsp;&nbsp;客户资金帐户总余额:<fmt:formatNumber value="${sumCustTotlAmt}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
											 </span>
										</td>
									</tr>
								</c:if>
							</table>

						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- <div class="sh_footer"></div> -->
	</body>
</html>
