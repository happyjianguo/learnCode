<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户消费汇总</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#exportExcel').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/TransactionrecordsDaily/exportExcel");
				$("#form").submit();
			});
			
			$('#btnSubmit').click(function() {
				var startDt = $("#startDt").val();
				var endDt = $("#endDt").val();
				if(startDt!='' && endDt!='' && (getDate(startDt)-getDate(endDt)>0)){
				    alert("交易截止日期不能小于交易起始日期");
				    return false;
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/TransactionrecordsDaily/list");				
				$("#form").submit();
			});
			
		});
		
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
		
		function resetQuery(){
			$("#startDt").val(CurentDayTime("-1",null));
			$("#endDt").val(CurentDayTime(null,null));
		}
		
	</script>
</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">
							商户消费汇总
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/TransactionRecords/list"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="text-align: right;">交易日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${queryModel.startDt}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
											name="startDt" id="startDt" />-
										<input type="text" class="inputext_style" value="${queryModel.endDt}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
											name="endDt" id="endDt" />
									</td>
									<td style="text-align: right;" colspan="4">
										<input type="button" value="查询" class="button" id="btnSubmit"/>
										<input type="button" value="导出" class="button" id="exportExcel" />
										<input type="button" value="清空" class="button" onClick="resetQuery();"/>
									</td>
								</tr>
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th style="text-align: center;">序号</th>
									<th style="text-align: center;">商户号</th>
									<th style="text-align: center;">商户名称</th>
									<th style="text-align: center;">交易金额(元)</th>
									<th style="text-align: center;">交易笔数</th>
								</tr>
								<c:if test="${transactionRecordsList != null}">
									<c:forEach items="${transactionRecordsList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>
												${info.merchantnumber}
											</td>
											<td>
												${info.merchantName}
											</td>
											<td>
												<fmt:formatNumber value="${info.transactionmoneySum}"  pattern="#,###,###,###,###,##0.00"
													minFractionDigits="2"/>
											</td>
											<td>
												${info.tranNum}
											</td>
										</tr>
									</c:forEach>
									<tr class="cow">
										<td colspan="5" align="center">
											<font color="red">总笔数：${numAndAmt.tranNum}&emsp;&emsp;
											总交易金额(元)：<fmt:formatNumber value="${numAndAmt.transactionmoneySum}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber></font>
										</td>
									</tr>
									<c:if test="${fn:length(transactionRecordsList)==0}">
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
								<c:if test="${transactionRecordsList == null}">
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