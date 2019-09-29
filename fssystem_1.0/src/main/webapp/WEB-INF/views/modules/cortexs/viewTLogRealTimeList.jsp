<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>实名认证信息管理</title>
		<meta name="decorator" content="default" />
	<script type="text/javascript">
		$(document).ready(function() {
			
			$('#exportExcel').click(function() {
				var begainTrueName = $("#begainTrueName").val();
				var endTrueName = $("#endTrueName").val();
				if(begainTrueName!='' && endTrueName!='' && (getDate(begainTrueName)-getDate(endTrueName)>0)){
				    alert("截止时间不能小于起始时间");
				    return false;
				}
				
				if(!verifyCardNo()){
					return false;
				}
				$("#form").attr("action","${pageContext.request.contextPath}/viewTLogRealTime/exportExcel");
				$("#form").submit();
			});
			
			$('#query').click(function() {
				var begainTrueName = $("#begainTrueName").val();
				var endTrueName = $("#endTrueName").val();
				if(begainTrueName!='' && endTrueName!='' && (getDate(begainTrueName)-getDate(endTrueName)>0)){
				    alert("截止时间不能小于开始时间");
				    return false;
				}
				if(!verifyCardNo()){
					return false;
				}
				$("#form").attr("action","${pageContext.request.contextPath}/viewTLogRealTime/list");
				$("#form").submit();
			});		
		});	
	
		//删除左右两端的空格
		function trim(str){ 
			return str.replace(/(^\s*)|(\s*$)/g, "");
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
				return true;
			}
			var merchantnumber = $("#merchantnumber").val();
			var terminalnumber = $("#terminalnumber").val();
			if((merchantnumber == null || merchantnumber == '')
				&& (terminalnumber == null || terminalnumber == '')){
				alertx("商户号、终端号、卡段必填一项", "");
				return false;
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
						<div class="sh_title">实时交易流水列表</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/viewTLogRealTime/list"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">商户号：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.merchantnumber}" name="merchantnumber" id="merchantnumber" maxlength="15"/>
										</td>
										<td style="text-align: right;">终端号：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.terminalnumber}" name="terminalnumber" id="terminalnumber" maxlength="8"/>
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
										<td style="text-align: right;">卡类型：</td>
										<td style="text-align: left;">
											<select id="kindId" name="kindId" class="inputext_style">
												<option value="">--请选择--</option>										
												<c:forEach var="model" items="${cardTypeList}">
													<option value="${model.oldKindId}" <c:if test="${query.kindId eq model.oldKindId}">selected="selected"</c:if>>
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
													<option value="${entity.value}" <c:if test="${query.consumetype eq entity.value}">selected="selected"</c:if>>${entity.label}</option>
												</c:forEach>
											</select>
										</td>
										<td style="text-align: right;">交易日期：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.begainTrueName}" 
												placeholder="yyyyMMdd" maxlength="8" name="begainTrueName" id="begainTrueName" 
												onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>-
											<input type="text" class="inputext_style" value="${query.endTrueName}" 
												placeholder="yyyyMMdd"  maxlength="8" name="endTrueName" id="endTrueName" 
												onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/>
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
									<th>支付清算号</th>
									<th>原支付清算号</th>
									<th>交易金额</th>
									<th>交易日期</th>
									<th>交易时间</th>
									<th>流水号</th>
									<th>批次号</th>
									<th>参考号</th>
									<th>手续费</th>
									<th>交易类型</th>
								</tr>
								<c:if test="${viewTLogRealTimeList != null}">
									<c:forEach items="${viewTLogRealTimeList}" var="info"
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
											<td>${info.orderId}</td>	
											<td>${info.orgOrderId}</td>										
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
												<fmt:formatNumber value="${info.amttxn}" pattern="#,###,###,###,###,##0.00"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>${info.tranTypeDesc}</td>
										</tr>	
									</c:forEach>
									<tr class="cow">
										<td colspan="18" align="center">
											<font color="red">总交易金额：<fmt:formatNumber value="${sumAmt}" pattern="#,###,###,###,###,##0.00"
													minFractionDigits="2"></fmt:formatNumber>&nbsp;元&emsp;&emsp;</font>
										</td>
									</tr>
									<c:if test="${fn:length(viewTLogRealTimeList)==0}">
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
								<c:if test="${viewTLogRealTimeList == null}">
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
