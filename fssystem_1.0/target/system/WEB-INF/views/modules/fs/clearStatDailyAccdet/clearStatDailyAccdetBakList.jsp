<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>卡余额信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		
		function query() {
			/* var begainDailyDate = $("#begainDailyDate").val();
			var endDailyDate = $("#endDailyDate").val();
			
			if(begainDailyDate!='' && endDailyDate!='' && (getDate(begainDailyDate)-getDate(endDailyDate)>0)){
				alert("结束统计日期不能小于开始统计日期");
				return false;
			} */
			
			$("#form").attr("action","${pageContext.request.contextPath}/StatDailyAccdetBak/getList");
			$("#form").submit();
		}	
		
		var resetQuery = function() {
			//$("#begainDailyDate").val(CurentDayTime("-1",null));
			$("#endDailyDate").val(CurentDayTime(null,"-1"));	
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
					<div class="sh_title">卡余额明细</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/StatDailyAccdetBak/getList" method="post">
							<input type="hidden" id="curPage" name="curPage" value="${curPage}" /> 
							<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="text-align: right;">统计日期：</td>
									<td style="text-align: left;">
										<%-- <input type="text" class="inputext_style" value="${queryModel.begainDailyDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="begainDailyDate" id="begainDailyDate" />
										- --%>
										<input type="text" class="inputext_style" value="${queryModel.endDailyDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="endDailyDate" id="endDailyDate" />	
									</td>
									<td style="text-align: right;" colspan="4">
										<input type="button" value="查询" class="button" onclick="query();"/>
										<input type="button" value="清空" class="button" onClick='resetQuery()'/>	
									</td>									
								</tr>
							</table>
						</form>
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<th>序号</th>
								<th>统计日期</th>									
								<th>卡张数</th>
								<th>卡账户余额(元)</th>								
							</tr>
							<c:if test="${ ClearStatDailyAccdetBakList != null  }">
								<c:forEach items="${ClearStatDailyAccdetBakList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>									
										<td>${info.dailyDate}</td>
										<td>
											${info.cardSum}
										</td>
										<td>
											<fmt:formatNumber value="${info.cardBal}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>
									</tr>	
								</c:forEach>
								<c:if test="${fn:length(ClearStatDailyAccdetBakList)==0}">
									<td colspan="18" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="18" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ ClearStatDailyAccdetBakList == null  }">
								<tr>
									<td colspan="18" style="text-align: center"><span style="color: red">***请查询***</span></td>
								</tr>
							</c:if>							
							<c:if test="${ ClearStatDailyAccdetBakList != null  }">
								<tr>
									<td colspan="18" style="text-align: center">
										<span style="color: red">
										合计
										&nbsp;&nbsp;&nbsp;&nbsp;卡张数:&nbsp;${sumAndBal.cardSum}
										&nbsp;&nbsp;&nbsp;&nbsp;卡账户余额(元):&nbsp;
										<fmt:formatNumber value="${sumAndBal.cardBal}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
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
</body>
</html>
