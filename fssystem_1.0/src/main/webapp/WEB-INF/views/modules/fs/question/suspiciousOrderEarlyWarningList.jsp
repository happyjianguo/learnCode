<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>预警订单查询</title>
		<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		/* $("#example").html("请输入1~100之间的数字");
		$("#example").css("color","red"); */
		
		$('#exportExcel').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/SuspiciousOrderEarlyWarning/exportExcel");
			$("#form").submit();
		});
		
		$('#query').click(function() {
			var beginDate = $("#beginDate").val();
			var endDate = $("#endDate").val();
			//var provisionsRateStr = $("#provisionsRate").val();
			//var provisionsRate = trim(provisionsRateStr);
			if(beginDate==''){
			    alert("必须输入开始时间");
			    return false;			
			}
			if(endDate==''){
			    alert("必须输入结算时间");
			    return false;			
			}		
			if(beginDate!='' && endDate!='' && (getDate(beginDate)-getDate(endDate)>0)){
			    alert("结束时间不能小于开始时间");
			    return false;
			}
			/* if(isNaN(provisionsRate)){
				$("#example").html("请输入1~100之间的数字");
				return false;
			} else if(parseInt(provisionsRate) > 100 || parseInt(provisionsRate) < 1){
				$("#example").html("请输入1~100之间的数字");
				return false;
			} */
			
			$("#form").attr("action","${pageContext.request.contextPath}/SuspiciousOrderEarlyWarning/getList");
			$("#form").submit();
		});		
	});	
	
	function resetQuery() {
		$("#endDate").val(CurentDayTime(null,"-1"));
		$("#beginDate").val(CurentDayTime("-1",null));
		$("#ordercode").val("");
		$("#purchaseAmt").val("");
		//$("#provisionsRate").val("");
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
</script>
	</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">
							预警订单查询
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/SuspiciousOrderEarlyWarning/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage"
									value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize"
									value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">
											预警起始日期：
										</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style"
												value="${query.beginDate}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
												name="beginDate" id="beginDate" />
										</td>
										<td style="text-align: right;">
											预警截至日期：
										</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style"
												value="${query.endDate}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
												name="endDate" id="endDate" />
										</td>
										<td style="text-align: right;">订单号：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.ordercode}" name="ordercode" id="ordercode" maxlength="11"/>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">购卡总金额：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.purchaseAmt}" name="purchaseAmt" id="purchaseAmt" maxlength="20"/>
										</td>
										<%-- <td style="text-align: right;">备付金占比：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.provisionsRate}" name="provisionsRate" id="provisionsRate" maxlength="3"/>
											<span id="example"></span>
										</td> --%>
										<td style="text-align: right;" colspan="4">
											<input type="button" value="查询" class="button" id="query" />
											<input type="button" value="导出" class="button"
												id="exportExcel" />
											<input type="button" value="清空" class="button"
												onclick='resetQuery()' />
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
										订单号
									</th>
									<th>
										购卡总金额
									</th>
									<th>
										购卡日期
									</th>
									<th>
										预警日期
									</th>
									<th>
										备付金金额
									</th>
									<!-- <th>
										备付金占比
									</th> -->
								</tr>
								<c:if test="${suspiciousOrderEarlyWarningList != null}">
									<c:forEach items="${suspiciousOrderEarlyWarningList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>
												${info.ordercode}
											</td>											
											<td>
												<fmt:formatNumber value="${info.purchaseAmt}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												${info.purchaseDate}
											</td>
											<td>
												${info.suspiciousDate}
											</td>
											<td>
												<fmt:formatNumber value="${info.provisions}"
													pattern="#,###,###,###,###,##0.##" minFractionDigits="2"></fmt:formatNumber>
											</td>
											<%-- <td>
												<fmt:formatNumber value="${info.provisionsRate}"
													pattern="#,###,###,###,###,##0.##" minFractionDigits="2"></fmt:formatNumber>
											</td> --%>
									</c:forEach>
									<c:if test="${fn:length(suspiciousOrderEarlyWarningList)==0}">
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
								<c:if test="${ suspiciousOrderEarlyWarningList == null  }">
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
