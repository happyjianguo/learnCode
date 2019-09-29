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
			
			$("#form").attr("action","${pageContext.request.contextPath}/MerchantLazyReach/getList");
			$("#form").submit();
		});

	});	
	
	function resetQuery() {
		$("#endStlDate").val(CurentDayTime(null,null));
		$("#startStlDate").val(CurentDayTime("-1",null));
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
							无交易商户查询
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/MerchantLazyReach/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage"
									value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize"
									value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
										<td style="text-align: right;" colspan="2">
											<input type="submit" value="查询" class="button" id="query" />
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
										商户状态
									</th>
								</tr>
								<c:if test="${lazyMerchantList != null}">
									<c:forEach items="${lazyMerchantList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>
												${info.mrchno}
											</td>
											<td>
												${info.mrchtName}
											</td>
											<td>
												${info.mrchstat}
											</td>
									</c:forEach>
									<c:if test="${fn:length(lazyMerchantList)==0}">
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
								<c:if test="${ lazyMerchantList == null  }">
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
