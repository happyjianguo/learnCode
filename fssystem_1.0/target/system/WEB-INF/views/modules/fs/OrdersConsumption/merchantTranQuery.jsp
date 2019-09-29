<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户交易明细</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				var transactiondateStart = $("#transactiondateStart").val();
				var transactiondateEnd = $("#transactiondateEnd").val();
				if(transactiondateStart!='' && transactiondateEnd!='' && (getDate(transactiondateStart)-getDate(transactiondateEnd)>0)){
				    alert("交易截止时间不能小于交易起始时间");
				    return false;
				}else if(transactiondateStart!='' && transactiondateEnd!='' && (getMonthNumber(transactiondateStart,transactiondateEnd)>6)){
					alert("交易起始时间到截止时间最大六个月时间间隔");
				    return false;
				}
				
				var panStart = $("#panStart").val();
				var panEnd = $("#panEnd").val();
				if(!Boolean(trim(panStart))){
					if(!Boolean(trim(panEnd))){
					}else{
						alert("结束卡号存在，起始卡号不能为空");
					    return false;
					}
				}
				
				if(!Boolean(trim(panEnd))){
					if(!Boolean(trim(panStart))){
					}else{
						alert("起始卡号存在，结束卡号不能为空");
					    return false;
					}
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/MerchantTranRecord/getList");				
				$("#form").submit();
			});
			
			$('#exportExcel').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/MerchantTranRecord/exportExcel");
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
			$("#orderCode").val("");
			$("#transactiondateStart").val(CurentDayTime("-1",null));
			$("#transactiondateEnd").val(CurentDayTime(null,null));
			$("#merchantnumber").val("");
			$("#cardBin").val("");
			$("#panStart").val("");
			$("#panEnd").val("");
		}
		
		function getMonthNumber(date1, date2) {
			//date2 > date1
			var year1 = date1.substr(0, 4);
			var year2 = date2.substr(0, 4);
			var month1 = date1.substr(4, 2);
			var month2 = date2.substr(4, 2);
			var len = (year2 - year1) * 12 + (month2 - month1);
			var day1 = date1.substr(6, 2);
			var day2 = date2.substr(6, 2);
			var day = day2 - day1;
			if (day > 0) {
				len += 1;
			}else if (day < 0){
				len -= 1;
			}
			return len;
		}
	</script>
</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">
							商户交易明细查询
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/MerchantTranRecord/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<td style="text-align: right;">订单号：</td>
									<td style="text-align: left;">
										<input type="text" name="orderCode" id="orderCode" value="${queryModel.orderCode }" maxlength="15" class="inputext_style"/>
									</td>
									<td style="text-align: right;">交易时间：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${queryModel.transactiondateStart}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
											name="transactiondateStart" id="transactiondateStart" />-
										<input type="text" class="inputext_style" value="${queryModel.transactiondateEnd}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
											name="transactiondateEnd" id="transactiondateEnd" />
									</td>
									<td style="text-align: right;">起始卡号：</td>
									<td style="text-align: left;">
										<input type="text" name="panStart" id="panStart" value="${queryModel.panStart }" maxlength="15" class="inputext_style"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">商户号：</td>
									<td style="text-align: left;">
										<input type="text" name="merchantnumber" id="merchantnumber" value="${queryModel.merchantnumber }" maxlength="15" class="inputext_style"/>
									</td>
									<td style="text-align: right;">卡BIN：</td>
									<td style="text-align: left;">
										<input type="text" name="cardBin" id="cardBin" value="${queryModel.cardBin }" maxlength="9" class="inputext_style"/>
									</td>
									<td style="text-align: right;">结束卡号：</td>
									<td style="text-align: left;">
										<input type="text" name="panEnd" id="panEnd" value="${queryModel.panEnd }" maxlength="15" class="inputext_style"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: right;" colspan="6">
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
									<th style="text-align: center;">订单号</th>
									<th style="text-align: center;">卡BIN</th>
									<th style="text-align: center;">起始卡号</th>
									<th style="text-align: center;">结束卡号</th>
									<th style="text-align: center;">商户号</th>
									<th style="text-align: center;">交易金额（元）</th>
									<th style="text-align: center;">交易日期</th>
								</tr>
								<c:if test="${merchantTranList != null}">
									<c:forEach items="${merchantTranList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>
												${info.orderCode}
											</td>
											<td>
												${fn:substring(info.panStart, 0, 9)}
											</td>
											<td>
												${info.panStart}
											</td>
											<td>
												${info.panEnd}
											</td>
											<td>
												${info.merchantnumber}
											</td>
											<td>
												<fmt:formatNumber value="${info.transactionmoney}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												${info.transactiondate}
											</td>
										</tr>
									</c:forEach>
									<%-- <tr class="cow">
										<td colspan="8" align="center">
											<font color="red">总条数：${count}&emsp;&emsp;收取金额汇总(元)：${feeSum}</font>
										</td>
									</tr> --%>
									<c:if test="${fn:length(merchantTranList)==0}">
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
								<c:if test="${merchantTranList == null}">
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