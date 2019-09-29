<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>支付清单</title>
		<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		
		$('#query').click(function() {
			if(!verifyCardNo()){
				return false;
			}
			
			$("#form").attr("action","${pageContext.request.contextPath}/OrderPayTlog/getList");
			$("#form").submit();
		});		
		
		$('#export').click(function() {
			if(!verifyCardNo()){
				return false;
			}
			
			$("#form").attr("action","${pageContext.request.contextPath}/OrderPayTlog/exportExcel");
			$("#form").submit();
		});	
		
	});	
	
	function verifyCardNo(){
		var begainPan = $("#begainPan").val();
		var endPan = $("#endPan").val();
		
		if((begainPan != null && begainPan != "")
				&& (endPan == null || endPan == "")){
			alertx("卡段起始、结束卡号不可单独为空", "");
			return false;
		}
		if((begainPan == null || begainPan == "")
				&& (endPan != null && endPan != "")){
			alertx("卡段起始、结束卡号不可单独为空", "");
			return false;
		}
		if((begainPan != null && begainPan != "")
				&& (endPan != null && endPan != "")){
			if(begainPan.length < 15 || endPan < 15){
				alertx("卡段起始、结束卡号位数不足", "");
				return false;
			}
			var startCardBin = begainPan.substr(0, 9); 
			var endCardBin = endPan.substr(0, 9); 
			if(!(startCardBin == endCardBin)){
				alertx("卡段起始、结束卡BIN不一致", "");
				return false;
			}
			var cardSumA = begainPan.substr(0, 15); 
			var cardSumB = endPan.substr(0, 15); 
			if((cardSumA - cardSumB) > 0){
				alertx("卡段起始卡号需大于结束卡号", "");
				return false;
			}
		}
		return true;
	}	
	
</script>
	</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">支付清单明细</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/OrderPayTlog/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">支付清算单号：</td>
										<td style="text-align: left;">
											<input type="text" name="orderId" id="orderId" value="${query.orderId}" maxlength="25" class="inputext_style"/>
										</td>
										<td style="text-align: right;">卡系统参考号：</td>
										<td style="text-align: left;">
											<input type="text" name="rrn" id="rrn" value="${query.rrn}" maxlength="12" class="inputext_style"/>
										</td>
										<td style="text-align: right;">卡段：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.begainPan}" name="begainPan" id="begainPan" maxlength="16"
												onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
											-
											<input type="text" class="inputext_style" value="${query.endPan}" name="endPan" id="endPan" maxlength="16"
												onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">是否结算：</td>
										<td style="text-align: left;">
											<select id="clearFlag" name="clearFlag" class="inputext_style" style="border: 1px solid #cacaca;" >
												<option value="">--请选择--</option>
												<option value="0" <c:if test="${query.clearFlag == '0'}">selected="selected"</c:if>>否</option>
												<option value="1" <c:if test="${query.clearFlag == '1'}">selected="selected"</c:if>>是</option>
											</select>
										</td>
										<td style="text-align: right;">交易状态：</td>
										<td style="text-align: left;">
											<select id="txtstatus" name="txtstatus" class="inputext_style" style="border: 1px solid #cacaca;" >
												<option value="">--请选择--</option>
												<option value="0" <c:if test="${query.txtstatus == '0'}">selected="selected"</c:if>>失败</option>
												<option value="7" <c:if test="${query.txtstatus == '7'}">selected="selected"</c:if>>成功</option>
											</select>
										</td>
										<td style="text-align: right;">交易日期：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.begainDate}" 
												placeholder="yyyyMMdd" maxlength="8" name="begainDate" id="begainDate" 
												onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
											-
											<input type="text" class="inputext_style" value="${query.endDate}" 
												placeholder="yyyyMMdd" maxlength="8" name="endDate" id="endDate" 
												onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
										</td>
									</tr>
									<tr>	
										<td style="text-align: right;" colspan="8">
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
									<th>TLOG_ID</th>
									<th>终端号</th>
									<th>卡号</th>
									<th>支付清算单号</th>
									<th>支付原清算单号</th>
									<th>卡系统参考号</th>
									<th>交易日期</th>
									<th>交易时间</th>
									<th>交易金额(元)</th>
									<th>交易类型</th>
									<th>交易状态</th>
									<th>充值来源 </th>
								</tr>
								<c:if test="${orderPayTlogList != null}">
									<c:forEach items="${orderPayTlogList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>${((curPage-1) * pageSize) + index.count }</td>
											<td>${info.id}</td>
											<td>${info.termcode}</td>
											<td>${info.pan}</td>
											<td>${info.orderId}</td>
											<td>${info.orgOrderId}</td>
											<td>${info.rrn}</td>
											<td>${info.transactionDate}</td>
											<td>${info.transactionTime}</td>
											<td>
												<fmt:formatNumber value="${info.amttxn}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												${fns:getTranTypeDesc(info.txncode,info.subTxncode,info.fncode)}
											</td>
											<td>
												<c:if test="${info.txtstatus eq '7'}">成功</c:if>
												<c:if test="${info.txtstatus ne '7'}">失败</c:if>
											</td>
											<td>${info.cashSource}</td>
										</tr>	
									</c:forEach>
									<c:if test="${fn:length(orderPayTlogList)==0}">
										<td colspan="18" style="text-align: center">
											<span style="color: red">***没有相关记录***</span>
										</td>
									</c:if>
									<tr>
										<td colspan="18" class="page">${pageBar}</td>
									</tr>
								</c:if>
								<c:if test="${orderPayTlogList == null}">
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
