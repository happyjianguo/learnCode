<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日终步骤信息</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
$(document).ready(function() {	
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
	function query(){
		$("#form").attr("action","${pageContext.request.contextPath}/FkStlDayEndStep/getList");
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
				src : '/FkStlDayEndStep/toShow?FkStlDayEndStepId=' + id
					    + '&random='
						+ Math.random()
			}
		});
	}
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
	
</script>
</head>
<body> 
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">日终步骤</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/FkStlDayEndStep/getList" method="post">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="text-align: right;">日终日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.dailydate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="dailydate" id="dailydate" />
									</td>
									<td style="text-align: right;"></td>
									<td style="text-align: left;"></td>
									<td style="text-align: right;"></td>
									<td style="text-align: left;"></td>
									<td style="text-align: right;"></td>																	
									<td style="text-align: left;">
										<input type="button" value="查询" class="button" onClick="query();"/>									
									</td>																														
								</tr>
							</table>
						</form>
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<th>步骤号</th>									
								<th>日终日期</th>
								<th>步骤名</th>
								<th>步骤执行状态 </th>
								<th>步骤状态 </th>
								<th>步骤执行开始时间</th>								
								<th>步骤执行结束时间</th>
							</tr>
							<c:if test="${ FkStlDayEndStepList != null  }">
								<c:forEach items="${FkStlDayEndStepList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${info.id}</td>
										<td>${info.dailydate}</td>
										<td>${info.stepname}</td>
										<td>
											<c:if test="${info.execStatus=='0'}">未执行</c:if>	
											<c:if test="${info.execStatus=='1'}">执行完成</c:if>	
											<c:if test="${info.execStatus=='2'}">执行失败</c:if>																					
											<c:if test="${info.execStatus=='3'}">正在执行</c:if>																					
											<c:if test="${info.execStatus=='4'}">结果未知</c:if>																					
										</td>										
										<td>
											<c:if test="${info.status=='0'}">无效</c:if>	
											<c:if test="${info.status=='1'}">有效</c:if>	
										</td>										
										<td>
											<fmt:formatDate value="${info.begintime}" pattern="yyyy-MM-dd HH:mm:ss"/>										
										</td>																				
										<td>
											<fmt:formatDate value="${info.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>	
										</td>																				
								</c:forEach>
								<c:if test="${fn:length(FkStlDayEndStepList)==0}">
									<td colspan="18" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="18" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ FkStlDayEndStepList == null  }">
								<tr>
									<td colspan="18" style="text-align: center"><span style="color: red">***请查询***</span></td>
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
