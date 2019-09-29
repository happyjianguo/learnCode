<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>卡账户余额信息</title>
		<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		
		$('#query').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/ViewRealNameAccnoAvlBal/getList");
			$("#form").submit();
		});		
		
		$('#export').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/ViewRealNameAccnoAvlBal/exportExcel");
			$("#form").submit();
		});	
		
	});	
	
</script>
	</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">卡账户余额信息</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/ViewRealNameAccnoAvlBal/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">卡BIN：</td>
										<td style="text-align: left;">
											<input type="text" name="cardbin" id="cardbin" value="${query.cardbin}" maxlength="9" class="inputext_style"/>
										</td>
										<td style="text-align: right;">卡状态：</td>
										<td style="text-align: left;">
											<select id="statcode" name="statcode" class="input-mini" style="border: 1px solid #cacaca;" >
												<option value="">--请选择--</option>
												<option value="00" <c:if test="${query.statcode == '00'}">selected="selected"</c:if>>激活</option>
											</select>
										</td>
										<td style="text-align: right;">账户类型：</td>
										<td style="text-align: left;">
											<select id="accnoType" name="accnoType" class="inputext_style">
												<option value="">--请选择--</option>
												<option value="1" <c:if test="${query.accnoType == '1'}">selected="selected"</c:if>>卡账户</option>
												<option value="2" <c:if test="${query.accnoType == '2'}">selected="selected"</c:if>>实名账户</option>
												<option value="4" <c:if test="${query.accnoType == '4'}">selected="selected"</c:if>>购物返积分账户</option>
												<option value="9" <c:if test="${query.accnoType == '9'}">selected="selected"</c:if>>联名卡送积分账户</option>
												<option value="12" <c:if test="${query.accnoType == '12'}">selected="selected"</c:if>>实名、卡账户</option>
												<option value="49" <c:if test="${query.accnoType == '49'}">selected="selected"</c:if>>购物、联名卡账户</option>
											</select>
										</td>
										<td style="text-align: right;">余额限制：</td>
										<td style="text-align: left;">
											<select id="symbol" name="symbol" class="input-mini" style="border: 1px solid #cacaca;" >
												<option value="0" <c:if test="${query.symbol == '0'}">selected="selected"</c:if>>大于</option>
												<option value="1" <c:if test="${query.symbol == '1'}">selected="selected"</c:if>>大于等于</option>
												<option value="2" <c:if test="${query.symbol == '2'}">selected="selected"</c:if>>等于</option>
												<option value="3" <c:if test="${query.symbol == '3'}">selected="selected"</c:if>>小于等于</option>
												<option value="4" <c:if test="${query.symbol == '4'}">selected="selected"</c:if>>小于</option>
											</select>
											-
											<input type="text" name="avlThreshold" id="avlThreshold" value="${query.avlThreshold}" maxlength="8" class="inputext_style" 
												onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;"></td>
										<td style="text-align: left;"></td>
										<td style="text-align: right;"></td>
										<td style="text-align: left;"></td>
										<td style="text-align: right;"></td>
										<td style="text-align: left;"></td>
										<td style="text-align: right;" colspan="4">
											<input type="button" value="查询" class="button" id="query" />
											<input id="export" type="button" value="导出" class="button" />
											<input type="button" value="清空" class="button" onClick="ClearForm('form')"/>
										</td>
									</tr>
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th>序号</th>
									<th>卡BIN</th>
									<th>卡号</th>
									<th>手机号</th>
									<th>卡账户余额(元)</th>
									<th>实名账户余额(元)</th>
									<th>购物返积分账户余额(元)</th>
									<th>联名卡送积分账户余额(元)</th>
									<th>账户总余额(元)</th>
								</tr>
								<c:if test="${viewRealNameAccnoAvlBalList != null}">
									<c:forEach items="${viewRealNameAccnoAvlBalList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>${((curPage-1) * pageSize) + index.count }</td>
											<td>${info.cardbin}</td>
											<td>${info.pan}</td>
											<td>${info.phone}</td>
											<td>
												<fmt:formatNumber value="${info.av01}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.av02}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.av04}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.av09}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.totalBal}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
										</tr>	
									</c:forEach>
									<c:if test="${fn:length(viewRealNameAccnoAvlBalList)==0}">
										<td colspan="18" style="text-align: center">
											<span style="color: red">***没有相关记录***</span>
										</td>
									</c:if>
									<tr>
										<td colspan="18" class="page">${pageBar}</td>
									</tr>
								</c:if>
								<c:if test="${viewRealNameAccnoAvlBalList == null}">
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
