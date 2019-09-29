<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>卡余额信息</title>
		<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		
		$('#query').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/StatDailyAccde/getList");
			$("#form").submit();
		});		
		
		$('#export').click(function() {
			$("#form").attr("action","${pageContext.request.contextPath}/StatDailyAccde/exportExcel");
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
						<div class="sh_title">卡余额信息</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/StatDailyAccde/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">统计日期：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.startDate}" name="startDate" id="startDate" maxlength="8"/>
											-
											<input type="text" class="inputext_style" value="${query.endDate}" name="endDate" id="endDate" maxlength="8"/>
										</td>
										<td style="text-align: right;">卡BIN：</td>
										<td style="text-align: left;">
											<input type="text" name="cardBin" id="cardBin" value="${query.cardBin}" maxlength="9" class="inputext_style"/>
										</td>
										<%-- <td style="text-align: right;">余额限制：</td>
										<td style="text-align: left;">
											<select id="symbol" name="symbol" class="input-mini" style="border: 1px solid #cacaca;" >
												<option value="0" <c:if test="${query.symbol == '0'}">selected="selected"</c:if>>大于</option>
												<option value="1" <c:if test="${query.symbol == '1'}">selected="selected"</c:if>>大于等于</option>
												<option value="2" <c:if test="${query.symbol == '2'}">selected="selected"</c:if>>等于</option>
												<option value="3" <c:if test="${query.symbol == '3'}">selected="selected"</c:if>>小于等于</option>
												<option value="4" <c:if test="${query.symbol == '4'}">selected="selected"</c:if>>小于</option>
											</select>
										</td>
									</tr>
									<tr> 
										<td style="text-align: right;"></td>
										<td style="text-align: left;"></td>
										<td style="text-align: right;"></td>
										<td style="text-align: left;"></td>
										<td style="text-align: right;"></td>
										<td style="text-align: left;"></td> --%>
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
									<th>统计日期</th>									
									<th>卡BIN</th>
									<th>卡产品</th>
									<th>卡张数</th>
									<th>卡账户余额(元)</th>								
									<th>实名账户余额(元)</th>
									<th>积分账户余额(元)</th>
									<th>联名卡积分账户(元)</th>
									<th>账户总余额(元)</th>
								</tr>
								<c:if test="${statDailyAccdeList != null}">
									<c:forEach items="${statDailyAccdeList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>${((curPage-1) * pageSize) + index.count }</td>
											<td>${info.dailyDate}</td>
											<td>${info.cardBin}</td>
											<td>${info.crdproduct}</td>
											<td>
												<fmt:formatNumber value="${info.cardCnt}" pattern="#,###,###,###,###,###"
													minFractionDigits="0"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.acc01}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.acc02}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.acc04}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.acc09}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.totalBal}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
										</tr>	
									</c:forEach>
									<c:if test="${statDailyAccdeList != null}">
										<tr>
											<td colspan="18" style="text-align: center">
												<span style="color: red">
												合计
												&nbsp;&nbsp;&nbsp;&nbsp;卡张数:<fmt:formatNumber value="${sumAvlBal.cardCnt}" pattern="#,###,###,###,###,###" minFractionDigits="0"></fmt:formatNumber>
												&nbsp;&nbsp;&nbsp;&nbsp;卡账户余额:<fmt:formatNumber value="${sumAvlBal.acc01}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
												&nbsp;&nbsp;&nbsp;&nbsp;实名账户余额:<fmt:formatNumber value="${sumAvlBal.acc02}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
												&nbsp;&nbsp;&nbsp;&nbsp;积分账户余额:<fmt:formatNumber value="${sumAvlBal.acc04}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
												&nbsp;&nbsp;&nbsp;&nbsp;联名卡积分账户:<fmt:formatNumber value="${sumAvlBal.acc09}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
												&nbsp;&nbsp;&nbsp;&nbsp;账户总余额:<fmt:formatNumber value="${sumAvlBal.totalBal}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
												</span>
											</td>
										</tr>
									</c:if>
									<c:if test="${fn:length(statDailyAccdeList)==0}">
										<td colspan="18" style="text-align: center">
											<span style="color: red">***没有相关记录***</span>
										</td>
									</c:if>
									<tr>
										<td colspan="18" class="page">${pageBar}</td>
									</tr>
								</c:if>
								<c:if test="${statDailyAccdeList == null}">
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
