<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>计息明细查询</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				var begainTransactiondate = $("#begainTransactiondate").val();
				var endTransactiondate = $("#endTransactiondate").val();
				if(begainTransactiondate!='' && endTransactiondate!='' && (getDate(begainTransactiondate)-getDate(endTransactiondate)>0)){
				    alert("截止统计日期不能小于起始统计日期");
				    return false;
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/WankeInterestAccrual/getList");				
				$("#form").submit();
			});
			
			$('#exportExcel').click(function() {
				var begainTransactiondate = $("#begainTransactiondate").val();
				var endTransactiondate = $("#endTransactiondate").val();
				if(begainTransactiondate!='' && endTransactiondate!='' && (getDate(begainTransactiondate)-getDate(endTransactiondate)>0)){
				    alert("截止统计日期不能小于起始统计日期");
				    return false;
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/WankeInterestAccrual/exportExcel");
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
	</script>
</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">
							计息明细查询
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/WankeInterestAccrual/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<td style="text-align: right;">起始统计日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style"
											value="${queryModel.begainTransactiondate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
											name="begainTransactiondate" id="begainTransactiondate" />
									</td>
									<td style="text-align: right;">截止统计日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style"
											value="${queryModel.endTransactiondate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
											name="endTransactiondate" id="endTransactiondate" />
									</td>
									<td style="text-align: right;" colspan="4">
										<input id="btnSubmit" class="button" type="button" value="查询"/>
										<input type="button" value="导出" class="button" id="exportExcel" />
										<input type="button" value="清空" class="button" onClick="ClearForm('form')"/>
									</td>
								</tr>
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th style="text-align: center;">序号</th>
									<th style="text-align: center;">统计日期</th>
									<th style="text-align: center;">账户总余额（元）</th>
									<th style="text-align: center;">上交备付金（元）</th>
									<th style="text-align: center;">当日充值金额（元）</th>
									<th style="text-align: center;">定期存款金额（元）</th>
									<th style="text-align: center;">净额（元）</th>
									<th style="text-align: center;">总计息（元）</th>
									<th style="text-align: center;">详情</th>
								</tr>
								<c:if test="${wankeInterestAccrualList != null}">
									<c:forEach items="${wankeInterestAccrualList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>
												${info.transactiondate}
											</td>
											<td>
												<fmt:formatNumber value="${info.cardaccount}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.stockFund}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.rechargeAmt}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.fixedDeposit}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.netAmount}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.grossInterest}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<a href="${pageContext.request.contextPath}/WankeInterestAccrual/show?id=${info.id}">详情</a>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${fn:length(wankeInterestAccrualList)==0}">
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
								<c:if test="${wankeInterestAccrualList == null}">
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