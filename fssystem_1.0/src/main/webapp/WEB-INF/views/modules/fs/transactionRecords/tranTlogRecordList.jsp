<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>非实时交易流水</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#exportExcel').click(function() {
				if(!verifyCardNo()){
					return false;
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/tranTlogRecord/exportExcel");
				$("#form").submit();
			});
			
			$('#query').click(function() {
				if(!verifyCardNo()){
					return false;
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/tranTlogRecord/list");				
				$("#form").submit();
			});
			
		});
		
		//删除左右两端的空格
		function trim(str){ 
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		
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
						<div class="sh_title">非实时交易流水</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/tranTlogRecord/list"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">商户号：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${queryModel.merchantnumber}" name="merchantnumber" id="merchantnumber" maxlength="15"/>
										</td>
										<td style="text-align: right;">终端号：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${queryModel.terminalnumber}" name="terminalnumber" id="terminalnumber" maxlength="8"/>
										</td>
										<td style="text-align: right;">卡段：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${queryModel.begainPan}" name="begainPan" id="begainPan" maxlength="16"
												onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
											-
											<input type="text" class="inputext_style" value="${queryModel.endPan}" name="endPan" id="endPan" maxlength="16"
												onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">卡类型：</td>
										<td style="text-align: left;">
											<select id="cardtype" name="cardtype" class="inputext_style">
												<option value="">--请选择--</option>										
												<c:forEach var="model" items="${cardTypeList}">
													<option value="${model.cardnumber}" <c:if test="${queryModel.cardtype eq model.cardnumber}">selected="selected"</c:if>>
														${model.cardkindname}
													</option>
												</c:forEach>
											</select>		
										</td>
										<td style="text-align: right;">中石化消费类型：</td>
										<td style="text-align: left;">
											<select name="consumetype" id="consumetype" class="inputext_style">
												<option value="" >--请选择--</option>
												<c:forEach var="entity" items="${fns:getDictList('CONSUMET_TYPE')}">
													<option value="${entity.value}" <c:if test="${queryModel.consumetype eq entity.value}">selected="selected"</c:if>>${entity.label}</option>
												</c:forEach>
											</select>
										</td>
										<td style="text-align: right;">交易日期：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${queryModel.transactiondateStart}" 
												placeholder="yyyyMMdd" maxlength="8"
												name="transactiondateStart" id="transactiondateStart" />-
											<input type="text" class="inputext_style" value="${queryModel.transactiondateEnd}" 
												placeholder="yyyyMMdd"  maxlength="8"
												name="transactiondateEnd" id="transactiondateEnd" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;" colspan="8">
											<input type="button" value="查询" class="button" id="query" />
											<input type="button" value="导出" class="button" id="exportExcel" />
											<input type="button" value="清空" class="button" onClick="ClearForm('form')" />
										</td>
									</tr>
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th>序号</th>
									<th>商户号</th>
									<th>商户名称</th>
									<th>卡号</th>
									<th>终端号</th>
									<th>终端位置</th>
									<th>交易金额</th>
									<th>交易日期</th>
									<th>交易时间</th>
									<th>流水号</th>
									<th>批次号</th>
									<th>参考号</th>
									<th>手续费</th>
									<th>交易类型</th>
								</tr>
								<c:if test="${tranTlogRecordList != null}">
									<c:forEach items="${tranTlogRecordList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>${info.merchantnumber}</td>
											<td>${info.merchantName}</td>
											<td>${info.cardnumber}</td>
											<td>${info.terminalnumber}</td>
											<td>${info.terminallocation}</td>											
											<td>
												<fmt:formatNumber value="${info.transactionmoney}" pattern="#,###,###,###,###,##0.00"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>${info.transactiondate}</td>
											<td>${info.transactiontime}</td>
											<td>${info.serialnumber}</td>
											<td>${info.lotno}</td>
											<td>${info.referencenumber}</td>
											<td>
												<fmt:formatNumber value="${info.perfee}" pattern="#,###,###,###,###,##0.00"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>${fns:getTranTypeCortexDesc(info.transactiontype)}</td>
										</tr>	
									</c:forEach>
									<tr class="cow">
										<td colspan="18" align="center">
											<font color="red">总交易金额：<fmt:formatNumber value="${sumAmt}" pattern="#,###,###,###,###,##0.00"
													minFractionDigits="2"></fmt:formatNumber>&nbsp;元&emsp;&emsp;</font>
										</td>
									</tr>
									<c:if test="${fn:length(tranTlogRecordList)==0}">
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
								<c:if test="${tranTlogRecordList == null}">
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