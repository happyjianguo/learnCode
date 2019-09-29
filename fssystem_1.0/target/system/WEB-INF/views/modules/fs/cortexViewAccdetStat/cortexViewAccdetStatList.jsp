<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>卡余额实时查询</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
$(document).ready(function() {
		$('#exportExcel').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/CortexViewAccdetStat/exportExcel");
			$("#form").submit();
		});
		$('#query').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/CortexViewAccdetStat/getList");
			$("#form").submit();
		});		
		changeStlNeedFlag();
	});

	var resetQuery = function() {
		$("#cardStatus").val("");		
		$("#cardBin").val("");
		//$("#crdproduct").val("");		
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
	
	function changeStlNeedFlag(){
		var exelusiveCardFlag=$("#isExclusive").val();
		if(exelusiveCardFlag==0||exelusiveCardFlag==null||exelusiveCardFlag==""){
			
			$("#stlNeedFlagTitle").hide();
			$("#stlNeedFlagText").hide();
			$("#stlFlag").val("");
			$("#stlNeedFlagTitleBlack").show();
			$("#stlNeedFlagTextBlack").show();
			
		}else if(exelusiveCardFlag==1){
			$("#stlNeedFlagTitle").show();
			$("#stlNeedFlagText").show();
			$("#stlNeedFlagTitleBlack").hide();
			$("#stlNeedFlagTextBlack").hide();
		
		}
	}
</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">卡余额</div>
					<div class="shmc_tab2">
						<form id="form" action="/CortexViewAccdetStat/getList" method="post">
							<input type="hidden" id="curPage" name="curPage" value="${curPage}" /> 
							<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="text-align: right;">卡BIN：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.cardBin}" name="cardBin" id="cardBin" maxlength="11"/>
									</td>								
									<td style="text-align: right;">卡状态：</td>
									<td style="text-align: left;">
										<select id="cardStatus" name="cardStatus" class="inputext_style" value="${query.cardStatus}">
											<option value="">--请选择--</option>																				
											<c:forEach var="model" items="${cortexCardStatusList}">
												<option value="${model.statcode}" <c:if test="${query.cardStatus==model.statcode}">selected="selected"</c:if>>
													${model.descr}
												</option>
											</c:forEach>
										</select>	
									</td>
									<td style="text-align: right;">卡产品：</td>
									<td style="text-align: left;">
										<select id="crdproduct" name="crdproduct" class="inputext_style" value="${query.crdproduct}">
											<option value="">--请选择--</option>										
											<c:forEach var="model" items="${cortexCrdProductList}">
												<option value="${model.crdproduct}" <c:if test="${query.crdproduct==model.crdproduct}">selected="selected"</c:if>>
													${model.descr}
												</option>
											</c:forEach>
										</select>	
									</td>
									
								</tr>
								<tr>									
									<td style="text-align: right;">是否是专属卡</td>
									<td style="text-align: left;">			
										<select id="isExclusive" name="isExclusive" class="inputext_style" onchange="changeStlNeedFlag()" value="${query.isExclusive}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.isExclusive eq '0'}">selected="selected"</c:if>>否</option>
											<option value="1" <c:if test="${query.isExclusive eq '1'}">selected="selected"</c:if>>是</option>
										</select>															
									</td>
									
									<td id="stlNeedFlagTitle" style="text-align: right;">是否需要结算</td>
									<td id="stlNeedFlagText" style="text-align: left;">			
										<select id="stlFlag" name="stlFlag" class="inputext_style" value="${query.stlFlag}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.stlFlag eq '0'}">selected="selected"</c:if>>无需结算</option>
											<option value="1" <c:if test="${query.stlFlag eq '1'}">selected="selected"</c:if>>需要结算</option>
										</select>															
									</td>
									<td id="stlNeedFlagTitleBlack" style="text-align: right;"></td>
										<td id="stlNeedFlagTextBlack" style="text-align: left;">			
																										
										</td>
																														
									<td style="text-align: center;" colspan="2">
										<input type="submit" value="查询" class="button" id="query"/>
										<input type="button" value="导出" class="button" id="exportExcel" />
										<input type="button" value="清空" class="button" onClick='resetQuery()'/>
									</td>									
								</tr>
							</table>
						</form>
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<th>序号</th>									
								<th>卡BIN</th>
								<th>卡状态</th>								
								<th>卡产品</th>
								<th>是否是专属卡</th>
								<th>卡产品名称</th>
								<th>是否需要结算</th>
								<th>卡张数</th>
								<th>卡账户余额(元)</th>								
								<th>实名账户余额(元)</th>
								<th>积分账户余额(元)</th>
								<th>联名卡账户余额(元)</th>
								<th>账户总余额(元)</th>
							</tr>
							<c:if test="${ CortexViewAccdetStatList != null  }">
								<c:forEach items="${CortexViewAccdetStatList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>									
										<td>${info.cardBin}</td>
										<td>${info.cardStatusName}</td>
										<td>${info.crdproduct}</td>
										<td>
											<c:if test="${info.isExclusive=='0'}">否</c:if>	
											<c:if test="${info.isExclusive=='1'}">是</c:if>											
										</td>
										<td>
											${info.cardTypeName}
																					
										</td>
										<td>
											<c:if test="${info.isExclusive=='1'&& info.stlFlag=='0'}">不需要</c:if>	
											<c:if test="${info.isExclusive=='1'&& info.stlFlag=='1'}">需要</c:if>
											<c:if test="${info.isExclusive=='0'}">--</c:if>	
																						
										</td>
										<td>${info.cardCnt}</td>
										<td>
										<fmt:formatNumber value="${info.cardBal}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>
										<td>
										<fmt:formatNumber value="${info.trueBal}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>	
										<td>
										<fmt:formatNumber value="${info.instBal}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>
										<td>
										<fmt:formatNumber value="${info.instBal9}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>
										<td>
										<fmt:formatNumber value="${info.totalBal}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>																				
								</c:forEach>
								<c:if test="${fn:length(CortexViewAccdetStatList)==0}">
									<td colspan="18" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="18" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ CortexViewAccdetStatList == null  }">
								<tr>
									<td colspan="18" style="text-align: center"><span style="color: red">***请查询***</span></td>
								</tr>
							</c:if>							
							<c:if test="${ CortexViewAccdetStatList != null  }">
								<tr>
									<td colspan="18" style="text-align: center">
										<span style="color: red">
										合计
										&nbsp;&nbsp;&nbsp;&nbsp;卡张数:${card_cnt_sum}
										&nbsp;&nbsp;&nbsp;&nbsp;卡账户余额:<fmt:formatNumber value="${card_bal_sum}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										&nbsp;&nbsp;&nbsp;&nbsp;实名账户余额:<fmt:formatNumber value="${true_bal_sum}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										&nbsp;&nbsp;&nbsp;&nbsp;积分账户余额:<fmt:formatNumber value="${inst_bal_sum}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										&nbsp;&nbsp;&nbsp;&nbsp;联名卡账户余额:<fmt:formatNumber value="${total_acc_sum}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										&nbsp;&nbsp;&nbsp;&nbsp;账户总余额:<fmt:formatNumber value="${total_bal_sum}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
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
