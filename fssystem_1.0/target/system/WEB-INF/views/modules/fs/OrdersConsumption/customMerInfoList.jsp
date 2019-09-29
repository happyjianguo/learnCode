<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>消费订单下单</title>
		<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		
		$('#query').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/CustomMerInfo/list");
			$("#form").submit();
		});		
	});	
	
	var toShow = function(id) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '800px', '400px' ],
			iframe : {
				src : '${pageContext.request.contextPath}/CustomMerInfo/toShow?batchId=' + id
					    + '&random='
						+ Math.random()
			}
		});
	}
	
	function toDelete(id){
		if(confirm("确认删除消费订单下单信息吗？")){
			$.ajax({
				url : '${pageContext.request.contextPath}/CustomMerInfo/delete?batchId=' + id + '&random='+ Math.random(),
				type : "post",
				success : function(data) {
					if (data.result == '0') {
						alert(data.resultMsg);
						$("#form").attr("action","${pageContext.request.contextPath}/CustomMerInfo/list");
						$("#form").submit();
					} else {
						alert(data.resultMsg);
					}
				}
			});
		}
	}
	
</script>
	</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">
							消费订单下单列表
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/CustomMerInfo/list"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">下单日期：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${queryModel.orderDate}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
												name="orderDate" id="orderDate" />
										</td>
										<td style="text-align: right;">处理状态：</td>
										<td style="text-align: left;">
											<select id="flag" name="flag" class="inputext_style">
												<option value="">--请选择--</option>										
												<c:forEach var="model" items="${fns:getDictList('DEAL_FLAG')}">
													<option value="${model.value}" <c:if test="${queryModel.flag eq model.value}">selected="selected"</c:if>>
														${model.label}
													</option>
												</c:forEach>
											</select>
										</td>
										<td style="text-align: right;" colspan="4">
											<input type="button" value="查询" class="button" id="query" />
											<shiro:hasPermission name="fs:customMerInfo:edit">
												<input type="button" value="增加" class="button"
													onclick="toShow(' ')" />
											</shiro:hasPermission>
											<input type="button" value="清空" class="button" onClick="ClearForm('form')"/>
										</td>
									</tr>
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th>序号</th>
									<th>批次号</th>
									<th>商户号</th>
									<th>公司名称</th>
									<th>消费开始日期</th>
									<th>消费结束日期</th>
									<th>下单日期</th>
									<th>处理时间</th>
									<th>处理状态</th>
									<shiro:hasPermission name="fs:customMerInfo:edit">
										<th>操作</th>
									</shiro:hasPermission>
								</tr>
								<c:if test="${customMerInfoList != null}">
									<c:forEach items="${customMerInfoList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>${info.batchId}</td>
											<td>${info.merNo}</td>
											<td>${info.companyName}</td>
											<td>${info.tranStartDate}</td>
											<td>${info.tranEndDate}</td>
											<td>${info.orderDate}</td>
											<td>${info.dealDate}</td>
											<td>
												${fns:getDictLabel(info.flag, 'DEAL_FLAG', '')}
											</td>
											<shiro:hasPermission name="fs:customMerInfo:edit">
												<td>
													<c:if test="${info.flag == '0'}">
														<a href="javascript:toShow('${info.batchId}')">修改</a>
														<a href="javascript:toDelete('${info.batchId}')">删除</a>
													</c:if>
													<c:if test="${info.flag == '1'}">
														<a href="${pageContext.request.contextPath}/OrderRanking/toDetail?batchId=${info.batchId}">详情</a>
													</c:if>
												</td>
											</shiro:hasPermission>
									</c:forEach>
									<c:if test="${fn:length(customMerInfoList)==0}">
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
								<c:if test="${customMerInfoList == null}">
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
