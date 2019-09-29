<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>手续费信息</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
$(document).ready(function() {
		$('#exportExcel').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/ClearFeeBook/exportExcel");
			$("#form").submit();
		});
		$('#query').click(function() {
			var addDT_startdate = $("#startDate").val();
			var addDT_enddate =$("#endDate").val();
			if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
			    alert("结束日期不能小于开始日期");
			    window.document.forms['form'].elements['startDate'].focus() ;
			    return false;
			}			
			$("#form").attr("action","${pageContext.request.contextPath}/ClearFeeBook/getList");
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
	var toEdit = function(id) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '1000px', '480px' ],
			iframe : {
				src : '/ClearFeeBook/toEdit?ClearFeeBookId=' + id
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
				src : '/ClearFeeBook/getDetailList?id=' + id
					    + '&random='
						+ Math.random()
			}
		});
	}	
	var resetQuery = function() {
		$("#merNo").val("");
		$("#merName").val("");
		$("#stlDate").val(CurentDayTime(null,"-1"));
		//$("#startDate").val(CurentDayTime("-1",null));
		//$("#endDate").val(CurentDayTime("+1",null));
		
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
					<div class="sh_title">手续费</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/ClearFeeBook/getList" method="post">
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
									<td style="text-align: right;">开始帐期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.startDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="startDate" id="startDate" />
									</td>
									<td style="text-align: right;">结束帐期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.endDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="endDate" id="endDate" />
									</td>																														
								</tr>
								<tr>									
									<td style="text-align: right;">结算日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.stlDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="stlDate" id="stlDate" />
									</td>
									<td style="text-align: right;">快递日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.expressDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="expressDate" id="expressDate" />
									</td>									
									<td style="text-align: right;">是否已开票：</td>
									<td style="text-align: left;">
										<select id="status" name="status" class="inputext_style" value="${query.status}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.status eq '0'}">selected="selected"</c:if>>未开票</option>
											<option value="1" <c:if test="${query.status eq '1'}">selected="selected"</c:if>>已开票</option>
										</select>									
									</td>	
									<td style="text-align: right;">是否要开票：</td>
									<td style="text-align: left;">
										<select id="isKp" name="isKp" class="inputext_style" value="${query.isKp}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.isKp eq '0'}">selected="selected"</c:if>>不需要</option>
											<option value="1" <c:if test="${query.isKp eq '1'}">selected="selected"</c:if>>需要</option>
										</select>									
									</td>	
								</tr>
								<tr>																
									<td style="text-align: right;" colspan="8">
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
								<th>发票抬头</th>								
								<th>消费金额（元）</th>
								<th>开始帐期</th>
								<th>结算帐期</th>
								<th>手续费原始值（元）</th>	
								<th>实际手续费（元）</th>																	
								<th>是否要开票</th>																
								<th>快递日期</th>																
								<th>快递单号</th>
								<th>发票号</th>																
								<th>编辑</th>																
								<th>明细</th>
							</tr>
							<c:if test="${ ClearFeeBookList != null  }">
								<c:forEach items="${ClearFeeBookList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>									
										<td>${info.merNo}</td>
										<td>${info.merName}</td>
										<td>${info.headOffice}</td>
										<td>${info.tranAmt}</td>										
										<td>${info.startDate}</td>
										<td>${info.endDate}</td>
										<td>${info.fee}</td>
										<td>${info.realFee}</td>
										<td>
											<c:if test="${info.isKp=='0'}">不需要</c:if>	
											<c:if test="${info.isKp=='1'}">需要</c:if>											
										</td>											
										<td>${info.expressDate}</td>
										<td>${info.expressNo}</td>
										<td>${info.officeNo}</td>									
										<td>
											<a href="javascript:toEdit('${info.id}')">编辑</a>											
										</td>										
										<td>
											<a href="javascript:toShowDetail('${info.id}')">明细</a>											
										</td>																		
								</c:forEach>
								<c:if test="${fn:length(ClearFeeBookList)==0}">
									<td colspan="17" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="17" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ ClearFeeBookList == null  }">
								<tr>
									<td colspan="17" style="text-align: center"><span style="color: red">***请查询***</span></td>
								</tr>
							</c:if>							
							<c:if test="${ ClearFeeBookList != null  }">
								<tr>
									<td colspan="17" style="text-align: center">
										<span style="color: red">
										合计
										&nbsp;&nbsp;&nbsp;&nbsp;消费金额:${tran_amt}
										&nbsp;&nbsp;&nbsp;&nbsp;手续费：${fee}
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
