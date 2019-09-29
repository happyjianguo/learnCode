<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户日汇总</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
$(document).ready(function() {
		$('#exportExcel').click(function() {
			
			$("#form").attr("action","${pageContext.request.contextPath}/ClearMerClearBook/exportExcelOfPaiMing");
			$("#form").submit();
		});
		$('#query').click(function() {
			var addDT_startdate = $("#startDt").val();
			var addDT_enddate =$("#endDt").val();
			if(addDT_startdate==null||addDT_startdate==""){
				$("#startDt").focus();
				return false;
			}
			if(addDT_enddate==null||addDT_enddate==""){
				$("#endDt").focus();				
				return false;
			}
			if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
			    alert("结束日期不能小于开始日期");
			    window.document.forms['form'].elements['startDt'].focus() ;
			    return false;
			} 			
			$("#form").attr("action","${pageContext.request.contextPath}/ClearMerClearBook/getListOfPaiMing");
			$("#form").submit();
		});		
	});

	function CurentDayTime(flag) {
		var now = new Date();
		var year = now.getFullYear(); 	//年
		var month = now.getMonth() + 1; //月
		if(flag!=null&&flag=="-1"){
			month = now.getMonth(); //月
		}else if(flag!=null&&flag=="+1"){
			month = now.getMonth()+2; //月
		}		
		var day = now.getDate(); 		//日
		var clock = year + "";
		if (month < 10)
			clock += "0";
		clock += month + "";
		if (day < 10)
			clock += "0";
		clock += day + "";
	 	
		return (clock);
	}
	//data转换
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
					<div class="sh_title">商户消费排名</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/ClearMerClearBook/getListOfPaiMing" method="post">
							<input type="hidden" id="curPage" name="curPage" value="${curPage}" /> 
							<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
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
									<td style="text-align: right;" colspan="2">
										<input type="submit" value="查询" class="button" id="query"/>
										<input type="button" value="导出" class="button" id="exportExcel" />
									</td>									
								</tr>
							</table>
						</form>
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<th>序号</th>									
								<th>商户编号</th>
								<th>商户名称</th>
								<th>消费金额（元）</th>
							</tr>
							<c:if test="${ ClearMerClearBookListOfPaiMing != null  }">
								<c:forEach items="${ClearMerClearBookListOfPaiMing}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>									
										<td>${info.merNo}</td>
										<td>${info.merName}</td>
										<td>${info.tranAmt}</td>																			
								</c:forEach>
								<c:if test="${fn:length(ClearMerClearBookListOfPaiMing)==0}">
									<td colspan="20" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="20" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ ClearMerClearBookListOfPaiMing == null  }">
								<tr>
									<td colspan="20" style="text-align: center"><span style="color: red">***请查询***</span></td>
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
