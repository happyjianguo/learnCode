<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>卡类型分润明细</title>
		<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$('#exportExcel').click(function() {
			var begainTransactiondate = $("#begainTransactiondate").val();
			var endTransactiondate = $("#endTransactiondate").val();
			if(begainTransactiondate!='' && endTransactiondate!='' && (getDate(begainTransactiondate)-getDate(endTransactiondate)>0)){
			    alert("截止时间不能小于起始时间");
			    return false;
			}
			
			$("#form").attr("action","${pageContext.request.contextPath}/TransactionSplitting/exportExcel");
			$("#form").submit();
		});
		
		$('#query').click(function() {
			var begainTransactiondate = $("#begainTransactiondate").val();
			var endTransactiondate = $("#endTransactiondate").val();
			if(begainTransactiondate!='' && endTransactiondate!='' && (getDate(begainTransactiondate)-getDate(endTransactiondate)>0)){
			    alert("截止时间不能小于起始时间");
			    return false;
			}
			
			$("#form").attr("action","${pageContext.request.contextPath}/TransactionSplitting/getList");
			$("#form").submit();
		});		
	});	
	
	function getDate(date){
		 var dates = date.split("-");
		 var dateReturn = '';
		 
		 for(var i=0; i<dates.length; i++){
		  dateReturn+=dates[i];
		 }
		 return dateReturn;
	}
	
	//删除左右两端的空格
	function trim(str){ 
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
</script>
	</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">
							卡类型分润明细查询
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/TransactionSplitting/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">卡BIN：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${queryModel.cardBin}" name="cardBin" id="cardBin" maxlength="9"/>
										</td>
										<td style="text-align: right;">卡描述：</td>
										<td style="text-align: left;">
											<select id="cardBin2" name="cardBin2" class="inputext_style">
												<option value="">--请选择--</option>										
												<c:forEach var="model" items="${crdfeeruleList}">
													<option value="${model.iid}" <c:if test="${queryModel.cardBin2 eq model.iid}">selected="selected"</c:if>>
														${model.descr}
													</option>
												</c:forEach>
											</select>
										</td>
										<td style="text-align: right;">起始日期：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${queryModel.begainTransactiondate}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="begainTransactiondate" id="begainTransactiondate" />
										</td>
										<td style="text-align: right;">截止日期：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${queryModel.endTransactiondate}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="endTransactiondate" id="endTransactiondate" />
										</td>
									</tr>
									<tr>	
										<td style="text-align: right;" colspan="8">
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
									<th>
										序号
									</th>
									<th>
										商户号
									</th>
									<th>
										商户名称
									</th>
									<th>
										卡BIN
									</th>
									<th>
										账期
									</th>
									<th>
										消费金额(元)
									</th>
									<th>
										笔数
									</th>
									<th>
										手续费金额(元)
									</th>
									<th>
										分润比例
									</th>
									<th>
										分润金额(元)
									</th>
								</tr>
								<c:if test="${transactionSplittingList != null}">
									<c:forEach items="${transactionSplittingList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>
												${info.merchantnumber}
											</td>
											<td>
												${info.merchantName}
											</td>
											<td>
												${info.cardBin}
											</td>
											<td>
												${info.minPaymentDay}-${info.maxPaymentDay}
											</td>
											<td>
												<fmt:formatNumber value="${info.transactionmoney}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												${info.comments}
											</td>
											<td>
												<fmt:formatNumber value="${info.perfee}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber type="percent" 
            										maxIntegerDigits="3" value="${info.feeOrderCard}" />
											</td>
											<td>
												<fmt:formatNumber value="${info.splitAmt}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
									</c:forEach>
									<c:if test="${fn:length(transactionSplittingList)!=0}">
										<tr>
											<td colspan="18" align="center">
												<font color="red">汇总：
													消费金额(元)：<fmt:formatNumber value="${sumTransactionmoney}" pattern="#,###,###,###,###,##0.##"
														minFractionDigits="2"></fmt:formatNumber>&emsp;&emsp;
													笔数：${sumComments}&emsp;&emsp;
													手续费(元)：<fmt:formatNumber value="${sumPerfee}" pattern="#,###,###,###,###,##0.##"
														minFractionDigits="2"></fmt:formatNumber>&emsp;&emsp;
													分润金额(元)：<fmt:formatNumber value="${sumSplitAmt}" pattern="#,###,###,###,###,##0.##"
														minFractionDigits="2"></fmt:formatNumber>&emsp;&emsp;
												</font>
											</td>
										</tr>
									</c:if>
									<c:if test="${fn:length(transactionSplittingList)==0}">
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
								<c:if test="${transactionSplittingList == null}">
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
