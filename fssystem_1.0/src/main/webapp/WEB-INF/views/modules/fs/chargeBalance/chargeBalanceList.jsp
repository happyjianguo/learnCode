<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>手工调账</title>
		<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		
		$('#query').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/ChargeBalance/getList");
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
			area : [ '800px', '500px' ],
			iframe : {
				src : '${pageContext.request.contextPath}/ChargeBalance/toShow?id=' + id
					    + '&random=' + Math.random()
			}
		});
	}
	
	var toDetail = function(id) {
		var laySum = $.layer({
			type : 2,
			title : false,
			zIndex : -1,
			shade : [ 0.2, '#000' ],
			closeBtn : [ 1, true ],
			area : [ '900px', '500px' ],
			iframe : {
				src : '${pageContext.request.contextPath}/ChargeBalance/toDetail?id=' + id
					    + '&random=' + Math.random()
			}
		});
	}
	
	function toDelete(id){
		$.jBox.confirm("确认删除该手工调账信息吗?","系统提示",function(v,h,f){
 			if(v=="ok"){
 				$.ajax({
 					url : '${pageContext.request.contextPath}/ChargeBalance/delete?random='+ Math.random(),
 					type : "post",
 					data : {
 						id:id
 					},
 					success : function(data) {
 						if (data.result == '0') {
 							layer.alert(data.resultMsg, 1, function() {
 								$("#form").attr("action","${pageContext.request.contextPath}/ChargeBalance/getList");
 								$("#form").submit();
 							});
 						} else {
 							alertx("删除手工调账信息失败。", "");
 						}
 					}
 				});
			}
		},{buttonsFocus:1});
		$('.jbox-body .jbox-icon').css('top','55px');
	}
	
	function toCancel(id){
		$.jBox.confirm("确认作废该手工调账信息吗?","系统提示",function(v,h,f){
 			if(v=="ok"){
 				$.ajax({
 					url : '${pageContext.request.contextPath}/ChargeBalance/cancel?random='+ Math.random(),
 					type : "post",
 					data : {
 						id:id
 					},
 					success : function(data) {
 						if (data.result == '0') {
 							layer.alert(data.resultMsg, 1, function() {
 								$("#form").attr("action","${pageContext.request.contextPath}/ChargeBalance/getList");
 								$("#form").submit();
 							});
 						} else {
 							alertx("作废手工调账信息失败。", "");
 						}
 					}
 				});
			}
		},{buttonsFocus:1});
		$('.jbox-body .jbox-icon').css('top','55px');
	}
	
	function toRegulAcc(id){
		$.jBox.confirm("确认对该调账信息进行调账操作吗?","系统提示",function(v,h,f){
 			if(v=="ok"){
 				loading("正在提交，请稍等...");
 				$.ajax({
 					url : '${pageContext.request.contextPath}/ChargeBalance/toRegulAcc?random='+ Math.random(),
 					type : "post",
 					//async: false,
 					data : {
 						id:id
 					},
 					success : function(data) {
 						if (data.result == '0') {
 							closeTip();
 							layer.alert(data.resultMsg, 1, function() {
 								$("#form").attr("action","${pageContext.request.contextPath}/ChargeBalance/getList");
 								$("#form").submit();
 							});
 						} else {
 							closeTip();
 							layer.alert(data.resultMsg, 0, function() {
 								$("#form").attr("action","${pageContext.request.contextPath}/ChargeBalance/getList");
 								$("#form").submit();
 							});
 						}
 					}
 				});
			}
		},{buttonsFocus:1});
		$('.jbox-body .jbox-icon').css('top','55px');
	}
	
</script>
	</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">手工调账明细</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/ChargeBalance/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">商户号：</td>
										<td style="text-align: left;">
											<input type="text" name="merNo" id="merNo" value="${query.merNo }" maxlength="15" class="inputext_style"/>
										</td>
										<td style="text-align: right;">终端号：</td>
										<td style="text-align: left;">
											<input type="text" name="terminalId" id="terminalId" value="${query.terminalId }" maxlength="8" class="inputext_style"/>
										</td>
										<td style="text-align: right;">调账时间：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.startChargeDate}" maxlength="8"
												name="startChargeDate" id="startChargeDate" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
											-
											<input type="text" class="inputext_style" value="${query.endChargeDate}" maxlength="8"
												name="endChargeDate" id="endChargeDate" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
										</td>
									</tr>	
									<tr>	
										<td style="text-align: right;">卡号：</td>
										<td style="text-align: left;">
											<input type="text" name="cardNo" id="cardNo" value="${query.cardNo }" maxlength="16" class="inputext_style"/>
										</td>
										<td style="text-align: right;">调账状态：</td>
										<td style="text-align: left;">
											<select id = "respCode" name="respCode" class="inputext_style">
												<option value="">--请选择--</option>
												<option value="0" <c:if test="${query.respCode == '0' }">selected="selected"</c:if>>成功</option>
												<option value="1" <c:if test="${query.respCode == '1' }">selected="selected"</c:if>>失败</option>
												<option value="2" <c:if test="${query.respCode == '2' }">selected="selected"</c:if>>作废</option>
											</select>
										</td>
										<td style="text-align: right;" colspan="4">
											<input type="button" value="查询" class="button" id="query" />
											<shiro:hasPermission name="fs:chargeBalance:edit">
												<input id="btnAdd" type="button" value="添加" class="button" 
													onclick="toShow(' ');"/>
											</shiro:hasPermission>
											<input type="button" value="清空" class="button" onClick="ClearForm('form')"/>
										</td>
									</tr>
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th>序号</th>
									<th>商户号</th>
									<th>商户名称</th>
									<th>终端号</th>
									<th>卡号</th>
									<th>调账标识</th>
									<th>调账金额(元)</th>
									<th>调账时间</th>
									<th>调账状态</th>
									<th>原交易流水号</th>
									<th>原交易日期</th>
									<shiro:hasPermission name="fs:chargeBalance:edit">
										<th>操作</th>
									</shiro:hasPermission>
								</tr>
								<c:if test="${chargeBalanceList != null}">
									<c:forEach items="${chargeBalanceList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>${((curPage-1) * pageSize) + index.count }</td>
											<td>${info.merNo}</td>
											<td>${info.merName}</td>
											<td>${info.terminalId}</td>
											<td>${info.cardNo}</td>
											<td>
												<c:if test="${info.chargeFlag eq '0'}">减钱</c:if>
												<c:if test="${info.chargeFlag eq '1'}">加钱</c:if>
											</td>
											<td>
												<fmt:formatNumber value="${info.chargeAmt}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<c:if test="${info.respCode ne null}">${info.createDate}</c:if>
											</td>
											<td>
												<c:if test="${info.respCode eq '0'}">成功</c:if>
												<c:if test="${info.respCode eq '1'}">失败</c:if>
												<c:if test="${info.respCode eq '2'}">作废</c:if>
											</td>
											<td>${info.voidTraceNo}</td>
											<td>${info.voidSysDate}</td>
											<shiro:hasPermission name="fs:chargeBalance:edit">
												<td>
													<c:if test="${info.respCode eq null}">
														<a href="javascript:toShow('${info.id}')">修改</a>
														<a href="javascript:toDelete('${info.id}')">删除</a>
														<shiro:hasPermission name="fs:chargeBalance:tiaozhang">
															<a href="javascript:toRegulAcc('${info.id}')">调账</a>
														</shiro:hasPermission>
													</c:if>
													<shiro:hasPermission name="fs:chargeBalance:tiaozhang">
														<c:if test="${info.respCode eq '1'}">
															<a href="javascript:toDetail('${info.id}')">详情</a>
															<a href="javascript:toRegulAcc('${info.id}')">重新调账</a>
															<a href="javascript:toCancel('${info.id}')">作废</a>
														</c:if>	
													</shiro:hasPermission>
													<c:if test="${info.respCode eq '0' or info.respCode eq '2'}">
														<a href="javascript:toDetail('${info.id}')">详情</a>
													</c:if>
												</td>
											</shiro:hasPermission>
										</tr>	
									</c:forEach>
									<c:if test="${fn:length(chargeBalanceList)==0}">
										<td colspan="18" style="text-align: center">
											<span style="color: red">***没有相关记录***</span>
										</td>
									</c:if>
									<tr>
										<td colspan="18" class="page">${pageBar}</td>
									</tr>
								</c:if>
								<c:if test="${chargeBalanceList == null}">
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
