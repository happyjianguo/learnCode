<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>商户活跃率查询</title>
		<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		
		$('#query').click(function() {
			var startStlDate = $("#startStlDate").val();
			var endStlDate = $("#endStlDate").val();

			if(startStlDate==''){
			    alert("必须输入起始时间");
			    return false;			
			}
			if(endStlDate==''){
			    alert("必须输入截止时间");
			    return false;			
			}		
			if(startStlDate!='' && endStlDate!='' && (getDate(startStlDate)-getDate(endStlDate)>0)){
			    alert("截止时间不能小于起始时间");
			    return false;
			}
			
			$("#form").attr("action","${pageContext.request.contextPath}/MerchantActiveReach/getList");
			$("#form").submit();
		});
		
		$('#exportExcel').click(function() {
			var startStlDate = $("#startStlDate").val();
			var endStlDate = $("#endStlDate").val();

			if(startStlDate==''){
			    alert("必须输入起始时间");
			    return false;			
			}
			if(endStlDate==''){
			    alert("必须输入截止时间");
			    return false;			
			}		
			if(startStlDate!='' && endStlDate!='' && (getDate(startStlDate)-getDate(endStlDate)>0)){
			    alert("截止时间不能小于起始时间");
			    return false;
			}
			$("#form").attr("action","${pageContext.request.contextPath}/MerchantActiveReach/exportExcel");
			$("#form").submit();
		});
		
		$('#provinceCode').change(function(){
			var provinceId = $('#provinceCode').val();
			if(provinceId == null || provinceId == ''){
				$("#cityName").html("<option id='city' value=''>--请选择--</option>");
				return;
			}
			$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath}/MerchantActiveReach/getCityList",
			   data: {provinceId:provinceId},
			   success: function(cityList){
				   if(cityList == null){
					   return;
				   }
				   $("#cityName").html("<option id='city' value=''>--请选择--</option>");
			   	   for(var i = 0; i < cityList.length; i++){
			   	   	  $("#cityName").append(
			   	   			"<option value=" + cityList[i].provinceCity + ">"+
			   	   			cityList[i].provinceCity + "</option>"
			   	   	  );
			   	   }
			   	   
			   }
			});
		});
	});	
	
	function resetQuery() {
		$("#endStlDate").val(CurentDayTime(null,null));
		$("#startStlDate").val(CurentDayTime("-1",null));
		$("#merNo").val("");
		$("#merName").val("");
		$("#province").attr("selected",true);
		$("#cityName").html("<option id='city' value=''>--请选择--</option>");
		$("#city").attr("selected",true);
		$("#tranAmt").val("");
		$("#consumNum").val("");
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
	
</script>
	</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">
							商户活跃率查询
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/MerchantActiveReach/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage"
									value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize"
									value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">商户号：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.merNo}" name="merNo" id="merNo" maxlength="15"/>
										</td>
										<td style="text-align: right;">商户名称：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.merName}" name="merName" id="merName" maxlength="20"/>
										</td>
										<td style="text-align: right;">交易金额：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.tranAmt}" name="tranAmt" id="tranAmt" maxlength="20"/>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">
											起始日期：
										</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style"
												value="${query.startStlDate}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
												name="startStlDate" id="startStlDate" />
										</td>
										<td style="text-align: right;">
											截止日期：
										</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style"
												value="${query.endStlDate}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
												name="endStlDate" id="endStlDate" />
										</td>
										<td style="text-align: right;">消费笔数：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.consumNum}" name="consumNum" id="consumNum" maxlength="11"/>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">商户区域(省)：</td>
										<td style="text-align: left;">
											<select id="provinceCode" name="provinceCode" class="inputext_style" value="${query.provinceName}">
												<option id="province" value="">--请选择--</option>										
												<c:forEach var="model" items="${provinceList}">
													<option value="${model.id }" <c:if test="${query.provinceName eq model.provinceCity}">selected="selected"</c:if>>
														${model.provinceCity}
													</option>
												</c:forEach>
											</select>	
										</td>
										<td style="text-align: right;">商户区域(市)：</td>
										<td style="text-align: left;">
											<select id="cityName" name="cityName" class="inputext_style" value="${query.cityName}">
												<option id="city" value="">--请选择--</option>
												<c:forEach var="model" items="${cityList}">
													<option value="${model.provinceCity }" <c:if test="${query.cityName eq model.provinceCity}">selected="selected"</c:if>>
														${model.provinceCity}
													</option>
												</c:forEach>
											</select>	
										</td>
										<td style="text-align: center;" colspan="2">
											<input type="button" value="查询" class="button" id="query" />
											<input type="button" value="导出" class="button" id="exportExcel" />
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
										商户号
									</th>
									<th>
										商户名称
									</th>
									<th>
										交易笔数
									</th>
									<th>
										交易金额
									</th>
									<th>
										商户区域
									</th>
									<th>
										费率（%）
									</th>
								</tr>
								<c:if test="${tClearMerstlBookList != null}">
									<c:forEach items="${tClearMerstlBookList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>
												${info.merNo}
											</td>
											<td>
												${info.merName}
											</td>
											<td>
												${info.consumNum}
											</td>
											<td>
												<fmt:formatNumber value="${info.tranAmt}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												${info.provinceName}-${info.cityName}
											</td>
											<td>
												${info.rate}
											</td>
									</c:forEach>
									<c:if test="${fn:length(tClearMerstlBookList)==0}">
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
								<c:if test="${ tClearMerstlBookList == null  }">
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
