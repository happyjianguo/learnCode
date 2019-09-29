<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>已结算报表信息</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
$(document).ready(function() {
		$('#exportExcel').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/ClearMerStlFinalBook/exportExcelBak");
			$("#form").submit();
		});
		$('#query').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/ClearMerStlFinalBook/getListBak");
			$("#form").submit();
		});		
	});

	var toShow = function(id) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '1000px', '480px' ],
			iframe : {
				src : '/ClearMerStlFinalBook/toShow?ClearMerStlFinalBookId=' + id
					    + '&random='
						+ Math.random()
			}
		});
	}
	
	var resetQuery = function() {
		$("#merNo").val("");
		$("#merName").val("");
		$("#stlDate").val(CurentDayTime(null,"-1"));
		//$("#payoutDate").val(CurentDayTime(null));
		
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
					<div class="sh_title">已结算报表</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/ClearMerStlFinalBook/getListBak" method="post">
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
									<td style="text-align: right;">结算日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.stlDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="stlDate" id="stlDate" />
									</td>
									<td style="text-align: right;">打款日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.payoutDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="payoutDate" id="payoutDate" />
									</td>																														
								</tr>
								<tr>									
									<td style="text-align: right;"></td>
									<td style="text-align: left;">
									</td>
									<td style="text-align: right;"></td>
									<td style="text-align: left;">
									</td>																										
									<td style="text-align: right;" colspan="4">
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
								<th>商户编号</th>
								<th>商户名称</th>
								<th>所属帐期</th>
								<th>消费金额（元）</th>
								<th>消费笔数</th>
								<th>手续费（元）</th>																
								<th>净额（元）</th>																
								<th>实际结算金额（元）</th>								
								<th>结算日期</th>
								<th>打款日期</th>
							</tr>
							<c:if test="${ ClearMerStlFinalBookList != null  }">
								<c:forEach items="${ClearMerStlFinalBookList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>									
										<td>${info.merNo}</td>
										<td>${info.merName}</td>
										<td>${info.startStlDate}-${info.endStlDate}</td>
										<td>${info.consumAmt}</td>										
										<td>${info.consumNum}</td>
										<td>${info.fee}</td>
										<td>${info.netAmt}</td>
										<td>${info.tranAmt}</td>
										<td>${info.stlDate}</td>
										<td>${info.payoutDate}</td>																		
								</c:forEach>
								<c:if test="${fn:length(ClearMerStlFinalBookList)==0}">
									<td colspan="17" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="17" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ ClearMerStlFinalBookList == null  }">
								<tr>
									<td colspan="17" style="text-align: center"><span style="color: red">***请查询***</span></td>
								</tr>
							</c:if>							
							<c:if test="${ ClearMerStlFinalBookList != null  }">
								<tr>
									<td colspan="17" style="text-align: center">
										<span style="color: red">
										合计
										&nbsp;&nbsp;&nbsp;&nbsp;消费金额：${consum_amt}
										&nbsp;&nbsp;&nbsp;&nbsp;消费笔数:${consum_num}
										&nbsp;&nbsp;&nbsp;&nbsp;手续费：${fee}
										&nbsp;&nbsp;&nbsp;&nbsp;净额：${net_amt}
										&nbsp;&nbsp;&nbsp;&nbsp;实际结算金额：${tran_amt}	
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
