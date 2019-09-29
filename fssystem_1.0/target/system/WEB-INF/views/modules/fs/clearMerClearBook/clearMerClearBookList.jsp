<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户日汇总</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
$(document).ready(function() {
		$('#exportExcel').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/ClearMerClearBook/exportExcel");
			$("#form").submit();
		});
		$('#query').click(function() {
			var addDT_startdate = $("#startDt").val();
			var addDT_enddate =$("#endDt").val();
			if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
			    alert("结束日期不能小于开始日期");
			    window.document.forms['form'].elements['startDt'].focus() ;
			    return false;
			} 			
			$("#form").attr("action","${pageContext.request.contextPath}/ClearMerClearBook/getList");
			$("#form").submit();
		});		
	});
	//data转换
	function getDate(date){
	 var dates = date.split("-");
	 var dateReturn = '';
	 
	 for(var i=0; i<dates.length; i++){
	  dateReturn+=dates[i];
	 }
	 return dateReturn;
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
				src : '/ClearMerClearBook/toShow?ClearMerClearBookId=' + id
					    + '&random='
						+ Math.random()
			}
		});
	}
	var resetQuery = function() {
		$("#merNo").val("");
		$("#merName").val("");
		$("#startDt").val(CurentDayTime(null,"-1"));
		$("#endDt").val(CurentDayTime(null,"-1"));
		//$("#stlFlag").val("");
		$("#fmrchNo").val("");
		$("#fmrchName").val("");
		
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
					<div class="sh_title">商户日汇总</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/ClearMerClearBook/getList" method="post">
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
								</tr>
								<tr>									
									<td style="text-align: right;">父商户编号：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.fmrchNo}" name="fmrchNo" id="fmrchNo" maxlength="15"/>
									</td>
									<td style="text-align: right;">父商户名称：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.fmrchName}" name="fmrchName" id="fmrchName" maxlength="15"/>
									</td>
									<td style="text-align: right;">结算标识：</td>
									<td style="text-align: left;">
										<select id="stlFlag" name="stlFlag" class="inputext_style" value="${query.stlFlag}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.stlFlag eq '0'}">selected="selected"</c:if>>初登记</option>
											<option value="1" <c:if test="${query.stlFlag eq '1'}">selected="selected"</c:if>>无需结算</option>
											<option value="2" <c:if test="${query.stlFlag eq '2'}">selected="selected"</c:if>>已结算</option>
										</select>									
									</td>																										
									<td style="text-align: right;" colspan="2">
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
								<th>父商户编号</th>
								<th>父商户名称</th>
								<th>交易日期</th>
								<th>消费场景</th>
								<th>卡产品名称</th>
								<th>消费总笔数</th>
								<th>消费总金额（元）</th>
								<th>退货总笔数</th>
								<th>退货总金额（元）</th>
								<th>净额（元）</th>																
								<th>结算金额（元）</th>
								<th>手续费（元）</th>																
								<th>结算标识</th>								
								<th>结算日期</th>
								<th>操作</th>
							</tr>
							<c:if test="${ ClearMerClearBookList != null  }">
								<c:forEach items="${ClearMerClearBookList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>									
										<td>${info.merNo}</td>
										<td>${info.merName}</td>
										<td>${info.fmrchNo}</td>
										<td>${info.fmrchName}</td>
										<td>${info.tranDate}</td>
										<td>${info.sceneName}</td>
										<td>${info.cardTypeName}</td>										
										<td>${info.tranNum}</td>
										<td>${info.tranAmt}</td>
										<td>${info.refNum}</td>
										<td>${info.refAmt}</td>
										<td>${info.netAmt}</td>
										<td>${info.stlAmt}</td>
										<td>${info.fee}</td>
										<td>
											<c:if test="${info.stlFlag=='0'}">初登记</c:if>	
											<c:if test="${info.stlFlag=='1'}">无需结算</c:if>	
											<c:if test="${info.stlFlag=='2'}">已结算</c:if>																					
										</td>
										<td>${info.stlDate}</td>						
										<td>
											<a href="javascript:toShow('${info.srcId}')">查看</a>																																									
										</td>																				
								</c:forEach>
								<c:if test="${fn:length(ClearMerClearBookList)==0}">
									<td colspan="20" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="20" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ ClearMerClearBookList == null  }">
								<tr>
									<td colspan="20" style="text-align: center"><span style="color: red">***请查询***</span></td>
								</tr>
							</c:if>							
							<c:if test="${ ClearMerClearBookList != null  }">
								<tr>
									<td colspan="20" style="text-align: center">
										<span style="color: red">
										合计
										&nbsp;&nbsp;&nbsp;&nbsp;消费总笔数:${tranNumSum}
										&nbsp;&nbsp;&nbsp;&nbsp;消费总金额：${tranAmtSum}
										&nbsp;&nbsp;&nbsp;&nbsp;退货总笔数：${refNumSum}
										&nbsp;&nbsp;&nbsp;&nbsp;退货总金额：${refAmtSum}
										&nbsp;&nbsp;&nbsp;&nbsp;净额：${netAmtSum}
										&nbsp;&nbsp;&nbsp;&nbsp;结算金额 ：${stlAmtSum}
										&nbsp;&nbsp;&nbsp;&nbsp;手续费：${feeSum}										
										
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
