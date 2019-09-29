<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消费订单金额排名</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				
				var transactiondateStart = $("#transactiondateStart").val();
				var transactiondateEnd = $("#transactiondateEnd").val();
				if(transactiondateStart!='' && transactiondateEnd!='' && (getDate(transactiondateStart)-getDate(transactiondateEnd)>0)){
				    alert("订单截止时间不能小于订单起始时间");
				    return false;
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/OrderRanking/getList");				
				$("#form").submit();
			});
			
			$('#exportExcel').click(function() {
				var transactiondateStart = $("#transactiondateStart").val();
				var transactiondateEnd = $("#transactiondateEnd").val();
				if(transactiondateStart!='' && transactiondateEnd!='' && (getDate(transactiondateStart)-getDate(transactiondateEnd)>0)){
				    alert("订单截止时间不能小于订单起始时间");
				    return false;
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/OrderRanking/exportExcel");
				$("#form").submit();
			});
		});
		
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
		
		function resetQuery(){
			$("#merNo").val("");
			$("#transactiondateStart").val(CurentDayTime("-1",null));
			$("#transactiondateEnd").val(CurentDayTime(null,null));
			$("#companyName").val("");
		}
		
	</script>
</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">
							消费订单金额排名列表
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/OrderRanking/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<td style="text-align: right;">商户号：</td>
									<td style="text-align: left;">
										<input type="text" name="merNo" id="merNo" value="${queryModel.merNo }" maxlength="15" class="inputext_style"/>
									</td>
									<td style="text-align: right;">公司名称：</td>
									<td style="text-align: left;">
										<input type="text" name="companyName" id="companyName" value="${queryModel.companyName }" maxlength="200" class="inputext_style"/>
									</td>
									<td style="text-align: right;">订单日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${queryModel.transactiondateStart}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
											name="transactiondateStart" id="transactiondateStart" />-
										<input type="text" class="inputext_style" value="${queryModel.transactiondateEnd}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
											name="transactiondateEnd" id="transactiondateEnd" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;" colspan="8">
										<input id="btnSubmit" class="button" type="button" value="查询"/>
										<input type="button" value="导出" class="button" id="exportExcel" />
										<input type="button" value="清空" class="button" onClick="resetQuery();"/>
									</td>
								</tr>
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th style="text-align: center;">序号</th>
									<th style="text-align: center;">商户号</th>
									<th style="text-align: center;">公司名称</th>
									<th style="text-align: center;">手机号</th>
									<th style="text-align: center;">订单数</th>
									<th style="text-align: center;">订单总额(元)</th>
									<th style="text-align: center;">购卡张数</th>
									<th style="text-align: center;">消费的卡张数</th>
									<th style="text-align: center;">消费笔数</th>
									<th style="text-align: center;">消费总额(元)</th>
								</tr>
								<c:if test="${orderRankingList != null}">
									<c:forEach items="${orderRankingList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>
												${info.merNo}
											</td>
											<td>
												${info.companyName}
											</td>
											<td>
												${info.cellPhone}
											</td>
											<td>
												${info.orderSum}
											</td>
											<td>
												<fmt:formatNumber value="${info.amtEachCrd}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.panAccount}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												${info.customCardSum}
											</td>
											<td>
												${info.customSum}
											</td>
											<td>
												<fmt:formatNumber value="${info.tranAmt}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
										</tr>
									</c:forEach>
									<%-- <tr class="cow">
										<td colspan="8" align="center">
											<font color="red">总条数：${count}&emsp;&emsp;收取金额汇总(元)：${feeSum}</font>
										</td>
									</tr> --%>
									<c:if test="${fn:length(orderRankingList)==0}">
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
								<c:if test="${orderRankingList == null}">
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