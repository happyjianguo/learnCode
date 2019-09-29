<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易账户信息</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">

function exportExcel() {
	$("#form").attr("action","${pageContext.request.contextPath}/ClearTransactionAccount/exportExcel");
	$("#form").submit();
}
function query() {
	$("#form").attr("action","${pageContext.request.contextPath}/ClearTransactionAccount/getList");
	$("#form").submit();
}

	var toShow = function(id) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '1000px', '480px' ],
			iframe : {
				src : '/ClearTransactionAccount/toShow?ClearTransactionAccountId=' + id
					    + '&random='
						+ Math.random()
			}
		});
	}
	var toShowDetail = function(id) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '1000px', '480px' ],
			iframe : {
				src : '/ClearTransactionAccount/getDetailList?id=' + id
					    + '&random='
						+ Math.random()
			}
		});
	}	
	var resetQuery = function() {
		
		$("#merNo").val("");
		$("#merName").val("");
		$("#startDt").val("");	
		$("#endDt").val("");	
		$("#iid").val("");		
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
					<div class="sh_title">账户交易信息</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/ClearTransactionAccount/getList" method="post">
							<input type="hidden" id="curPage" name="curPage" value="${curPage}" /> 
							<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="text-align: right;">商户编号：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.merNo}" name="merNo" id="merNo" maxlength="15"/>
									</td>
									<td style="text-align: right;">商户名称：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.merName}" name="merName" id="merName" maxlength="15"/>
									</td>
									
									<td style="text-align: right;">卡BIN：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.iid}" name="iid" id="cardBin" maxlength="11"/>
									</td>
																
								</tr>
								<tr>									
									<td style="text-align: right;">交易时间：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.startDt}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="startDt" id="startDt" />
									</td>
									<td style="text-align: right;">至：</td>																	
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.endDt}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="endDt" id="endDt" />																			
									</td>	
									<td style="text-align: right;"></td>
										<td style="text-align: left;">
									</td>
									
									<td style="text-align: right;"></td>
										<td style="text-align: left;">
									</td>
									<td style="text-align: right;"></td>
										<td style="text-align: left;">
									</td>
																												
									<td style="text-align: center;" colspan="2">
										
											<input type="submit" value="查询" class="button" onclick="query()"/>
											<input type="button" value="导出" class="button" onclick="exportExcel()" />
										
										<input type="button" value="清空" class="button" onClick='resetQuery()'/>	
									</td>									
								</tr>
							</table>
						</form>
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<th>序号</th>		
								<th>商户编号</th>	
								<th>商户名称</th>	
								<th>卡产品</th>						
								<th>卡BIN</th>
								<th>交易总金额</th>
								<th>交易总笔数</th>
								<th>卡账户余额</th>
								<th>实名账户余额(元)</th>
								<th>积分账户余额(元)</th>
								<th>联名卡积分账户(元)</th>
								<th>统计日期</th>
							</tr>
							<c:if test="${ ClearTransactionAccountList != null  }">
								<c:forEach items="${ClearTransactionAccountList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>	
										<td>${info.merNo}</td>
										<td>${info.merName}</td>	
										<td>${info.crdproduct}</td>							
										<td>${info.iid}</td>
										<td>
										<fmt:formatNumber value="${info.tranAmt}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>
										<td>${info.tranNum}</td>	
										<td>
										<fmt:formatNumber value="${info.cardaccountmoney}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>
										<td>
										<fmt:formatNumber value="${info.trueaccountmoney}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>	
										<td>
										<fmt:formatNumber value="${info.integrationaccountmoney}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>
										<td>
										<fmt:formatNumber value="${info.coBrandedAccountmoney}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>																				
										<td>${info.transactiondate}</td>																						
								</c:forEach>
								<c:if test="${fn:length(ClearTransactionAccountList)==0}">
									<td colspan="18" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="18" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ ClearTransactionAccountList == null  }">
								<tr>
									<td colspan="18" style="text-align: center"><span style="color: red">***请查询***</span></td>
								</tr>
							</c:if>							
							<!-- <c:if test="${ ClearTransactionAccountList != null  }">-->
								<!-- <tr>-->
									<!-- <td colspan="18" style="text-align: center">-->
									<!-- 	<span style="color: red">-->
										<!--合计-->
										<!--  &nbsp;&nbsp;&nbsp;&nbsp;卡张数:${card_cnt_sum}-->
										<!-- &nbsp;&nbsp;&nbsp;&nbsp;卡账户余额:${card_bal_sum}-->
										<!-- &nbsp;&nbsp;&nbsp;&nbsp;实名账户余额:${true_bal_sum}-->
										<!-- &nbsp;&nbsp;&nbsp;&nbsp;积分账户余额:${inst_bal_sum}-->
										<!-- &nbsp;&nbsp;&nbsp;&nbsp;账户总余额:${total_bal_sum}-->
										<!-- </span>-->
									<!-- </td>-->
								<!-- </tr>-->
							<!-- </c:if>-->
						</table>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- <div class="sh_footer"></div> -->
</body>
</html>
