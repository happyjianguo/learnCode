<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*,org.apache.commons.lang3.StringUtils,org.apache.commons.lang3.ObjectUtils" %>

<html>
<head>
	<title>订单消费明细及汇总</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
	function query() {
		$("#form").attr("action","${pageContext.request.contextPath}/OrdersConsumption/getList");
		if($("#ordercode").val()==''){
			alert("订单号不能为空");
			return false;
		}else{
			$("#form").submit();
		}
	}
	var resetQuery = function() {
		$("#ordercode").val("");
	}

	/* var toShow = function(id) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '1000px', '480px' ],
			iframe : {
				src : '/ClearMerStlBook/toShow?ClearMerStlBookId=' + id
					    + '&random='
						+ Math.random()
			}
		});
	} */
	/* var toShowDetail = function(id) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '1000px', '480px' ],
			iframe : {
				src : '/ClearMerStlBook/getDetailList?id=' + id
					    + '&random='
						+ Math.random()
			}
		});
	} */	
	
	/* function CurentDayTime(flag) {
		var now = new Date();
		var year = now.getFullYear(); 	//年
		var month = now.getMonth() + 1; //月
		
		var day = now.getDate(); 		//日
		if(flag!=null&&flag=="-1"){
			day = now.getDate()-1; 
		}else if(flag!=null&&flag=="+1"){
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
	} */
	
</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">订单消费明细及汇总</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/OrdersConsumption/getList" method="post">
							<input type="hidden" id="curPage" name="curPage" value="${curPage}" /> 
							<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="text-align: right;">订单号：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.ordercode}" name="ordercode" id="ordercode" maxlength="15"/>
										<span class="help-inline"><font color="red">*</font> </span>
									</td>
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
										<input type="button" value="查询" class="button" onclick="query()"/>
										<input type="button" value="清空" class="button" onClick='resetQuery()'/>
									</td>									
								</tr>
							</table>
						</form>
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<!-- <th>序号</th> -->									
								<th>序号</th>									
								<th>订单号</th>
								<th>卡号</th>
								<th>商户号</th>
								<th>商户名称</th>
								<th>消费金额</th>								
								<th>消费日期</th>
								<th>消费时间</th>
							</tr>
							<c:if test="${ OrdersConsumptionList != null  }">
								<c:forEach items="${OrdersConsumptionList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>									
										
										<td>${info.ordercode}</td>
										<td>${info.cardnumber}</td>
										<td>${info.merchantnumber}</td>
										<td>${info.merchantname}</td>
										<td>${info.transactionmoney}</td>										
										<td>${info.transactiondate}</td>
										<td>${info.transactiontime}</td>
										
										<%-- <td>
											<a href="javascript:toShowDetail('${info.id}')">明细</a>																																									
										</td> --%>																				
								</c:forEach>
								<c:if test="${fn:length(OrdersConsumptionList)==0}">
									<td colspan="18" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="18" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ OrdersConsumptionList == null  }">
								<tr>
									<td colspan="18" style="text-align: center"><span style="color: red">***请查询***</span></td>
								</tr>
							</c:if>							
							<c:if test="${ OrdersConsumptionList != null  }">
								<tr>
									<td colspan="18" style="text-align: center">
										<span style="color: red">
										合计
										&nbsp;&nbsp;&nbsp;&nbsp;订单号:${ordercodeno}
										&nbsp;&nbsp;&nbsp;&nbsp;购卡时间：${ordertime}
										&nbsp;&nbsp;&nbsp;&nbsp;购卡金额：${ordertotalmoney}
										&nbsp;&nbsp;&nbsp;&nbsp;消费总金额：${xiaofeimoney}
										&nbsp;&nbsp;&nbsp;&nbsp;备付金剩余金额：${cover}								
										
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
