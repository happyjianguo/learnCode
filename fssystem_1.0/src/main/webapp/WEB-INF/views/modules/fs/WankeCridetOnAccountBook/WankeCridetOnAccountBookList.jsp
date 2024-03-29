<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>授信挂账明细单信息</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">

function exportExcel() {
	$("#form").attr("action","${pageContext.request.contextPath}/WankeCridetOnAccountBook/exportExcel");
	$("#form").submit();
}
function query() {
	$("#form").attr("action","${pageContext.request.contextPath}/WankeCridetOnAccountBook/getList");
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
				src : '/WankeCridetOnAccountBook/toShow?WankeCridetOnAccountBookId=' + id
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
				src : '/WankeCridetOnAccountBook/getDetailList?id=' + id
					    + '&random='
						+ Math.random()
			}
		});
	}	
	var resetQuery = function() {
		
		$("#merNo").val("");
		$("#merName").val("");
		$("#pan").val("");
		$("#debtAmt").val("");
// 		$("#startDt").val("");	
// 		$("#endDt").val("");	
	
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
					<div class="sh_title">授信挂账明细单信息</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/WankeCridetOnAccountBook/getList" method="post">
							<input type="hidden" id="curPage" name="curPage" value="${curPage}" /> 
							<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="text-align: right;">商户编号：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.merNo}" name="merNo" id="merNo" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')"/>					
									</td>
									<td style="text-align: right;">商户名称：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.merName}" name="merName" id="merName" maxlength="15"/>
									</td>
									<td style="text-align: right;">卡号：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.pan}" name="pan" id="pan" maxlength="16" onkeyup="this.value=this.value.replace(/\D/g,'')"/>					
									</td>
									<td style="text-align: right;">剩余欠款：</td>
									<td style="text-align: left;">			
										<select id="debtAmt" name="debtAmt" class="inputext_style" value="${query.debtAmt}">
											<option value="">--全部--</option>
											<option value="0" <c:if test="${query.debtAmt eq '0'}">selected="selected"</c:if>>已还清</option>
											<option value="1" <c:if test="${query.debtAmt eq '1'}">selected="selected"</c:if>>未还清</option>
										</select>															
									</td>
															
								</tr>
								<tr>									
									
																												
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
								<th>终端号</th>	
								<th>卡号</th>	
								<th>持卡人类型</th>	
								<th>挂账日期</th>	
								<th>挂账时间</th>	
								<th>挂账金额</th>	
								<th>还款金额</th>	
								<th>还款日期</th>
								<th>还款时间</th>
								<th>剩余欠款</th>	
								<th>操作</th>			
							</tr>
							<c:if test="${ WankeCridetOnAccountBookList != null  }">
								<c:forEach items="${WankeCridetOnAccountBookList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>	
										<td>${info.merNo}</td>
										<td>${info.merName}</td>
										<td>${info.terNo}</td>
										<td>${info.pan}</td>
										<td>
											<c:if test="${info.cardType=='1'}">1-业主</c:if>	
											<c:if test="${info.cardType=='2'}">2-员工</c:if>											
										</td>
										<td>${info.credetDate}</td>
										<td>${info.credetTime}</td>
										<td>
										<fmt:formatNumber value="${info.creditAmt}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>
										<td>
										<fmt:formatNumber value="${info.repayAmt}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>
										<td>${info.repayDate}</td>
										<td>${info.repayTime}</td>
										<td>
										<fmt:formatNumber value="${info.debtAmt}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>
										<td>
											<a href="javascript:toShow('${info.id}')">查看</a>																																									
										</td>																						
								</c:forEach>
								<c:if test="${fn:length(WankeCridetOnAccountBookList)==0}">
									<td colspan="18" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="18" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ WankeCridetOnAccountBookList == null  }">
								<tr>
									<td colspan="18" style="text-align: center"><span style="color: red">***请查询***</span></td>
								</tr>
							</c:if>							
							<!-- <c:if test="${ WankeCridetOnAccountBookList != null  }">-->
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
