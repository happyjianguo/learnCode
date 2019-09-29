<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>未结算信息</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
	$(document).ready(function() {
			
	});
	function exportExcel() {
		
		$("#form").attr("action","${pageContext.request.contextPath}/ClearUnMerStlBook/exportExcel");
		$("#form").submit();
	}
	function query() {
		
		$("#form").attr("action","${pageContext.request.contextPath}/ClearUnMerStlBook/getList");
		$("#form").submit();
	};	
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
				src : '/ClearUnMerStlBook/toShow?ClearUnMerStlBookId=' + id
					    + '&random='
						+ Math.random()
			}
		});
	}
	
	var resetQuery = function() {
		$("#merNo").val("");
		$("#merName").val("");
		$("#endunstlDate").val(CurentDayTime(null,"-1"));		
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
					<div class="sh_title">未结算</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/ClearUnMerStlBook/getList" method="post">
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
									<td style="text-align: right;">是否需要结算</td>
									<td style="text-align: left;">			
										<select id="stlNeedFlag" name="stlNeedFlag" class="inputext_style" value="${query.stlNeedFlag}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.stlNeedFlag eq '0'}">selected="selected"</c:if>>无需结算</option>
											<option value="1" <c:if test="${query.stlNeedFlag eq '1'}">selected="selected"</c:if>>需要结算</option>
										</select>															
									</td>
									<td style="text-align: right;">未结算截止日期：</td>																	
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.endunstlDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="endunstlDate" id="endunstlDate" />																			
									</td>
									<td style="text-align: right;"></td>																	
									<td style="text-align: left;">																			
									</td>																														
								</tr>
								<tr>									
									<td style="text-align: right;"></td>
									<td style="text-align: left;">
									</td>
									<td style="text-align: right;"></td>																	
									<td style="text-align: left;">
									</td>
									<td style="text-align: right;"></td>
									<td style="text-align: left;">								
									</td>																										
									<td style="text-align: right;" colspan="2">
										<input type="submit" value="查询" class="button" onClick='query()'/>
										<input type="button" value="导出" class="button" onClick='exportExcel()' />
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
								<th>是否需要结算</th>
								<th>截止结算日期</th>
								<th>未结算日期</th>
								<th>累计未结算笔数</th>								
								<th>累计未结算金额（元）</th>
							</tr>
							<c:if test="${ ClearUnMerStlBookList != null  }">
								<c:forEach items="${ClearUnMerStlBookList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>									
										<td>${info.merNo}</td>
										<td>${info.merName}</td>
										
										<td>
											<c:if test="${info.stlNeedFlag=='0'}">不需要</c:if>	
											<c:if test="${info.stlNeedFlag=='1'}">需要</c:if>											
										</td>						
										
										<td>${info.laststlDate}</td>
										<td>${info.startunstlDate}-${info.endunstlDate}</td>
										<td>${info.tranNum}</td>
										<td>${info.tranAmt}</td>																				
								</c:forEach>
								<c:if test="${fn:length(ClearUnMerStlBookList)==0}">
									<td colspan="18" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="18" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ ClearUnMerStlBookList == null  }">
								<tr>
									<td colspan="18" style="text-align: center"><span style="color: red">***请查询***</span></td>
								</tr>
							</c:if>							
							<c:if test="${ ClearUnMerStlBookList != null  }">
								<tr>
									<td colspan="18" style="text-align: center">
										<span style="color: red">
										合计
										&nbsp;&nbsp;&nbsp;&nbsp;累计未结算笔数:${tran_num}
										&nbsp;&nbsp;&nbsp;&nbsp;累计未结算金额：${tran_amt}
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
