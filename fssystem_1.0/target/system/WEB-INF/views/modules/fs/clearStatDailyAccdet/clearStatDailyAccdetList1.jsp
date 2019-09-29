<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>卡余额信息</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
$(document).ready(function() {
		
		changeStlNeedFlag();
	});

function exportExcel() {
	var begainDailyDate = $("#begainDailyDate").val();
	var endDailyDate = $("#endDailyDate").val();
	
	if(begainDailyDate!='' && endDailyDate!='' && (getDate(begainDailyDate)-getDate(endDailyDate)>0)){
		alert("结束统计日期不能小于开始统计日期");
		return false;
	}
	
	$("#form").attr("action","${pageContext.request.contextPath}/ClearStatDailyAccdet/exportExcel?flag=1");
	$("#form").submit();
}
function query() {
	var begainDailyDate = $("#begainDailyDate").val();
	var endDailyDate = $("#endDailyDate").val();
	
	if(begainDailyDate!='' && endDailyDate!='' && (getDate(begainDailyDate)-getDate(endDailyDate)>0)){
		alert("结束统计日期不能小于开始统计日期");
		return false;
	}
	
	$("#form").attr("action","${pageContext.request.contextPath}/ClearStatDailyAccdet/getList?flag=1");
	$("#form").submit();
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
				src : '/ClearStatDailyAccdet/toShow?ClearStatDailyAccdetId=' + id
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
				src : '/ClearStatDailyAccdet/getDetailList?id=' + id
					    + '&random='
						+ Math.random()
			}
		});
	}	
	var resetQuery = function() {
		$("#begainDailyDate").val(CurentDayTime("-1",null));
		$("#endDailyDate").val(CurentDayTime(null,"0"));	
		$("#cardBin").val("");
		$("#crdproduct").select().val("");	
		$("#cardStatus").select().val("");
		$("#exelusiveCardFlag").select().val("");
		$("#stlNeedFlag").select().val("");
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
	
	function changeStlNeedFlag(){
		var exelusiveCardFlag=$("#exelusiveCardFlag").val();
		if(exelusiveCardFlag==0||exelusiveCardFlag==null||exelusiveCardFlag==""){
			
			$("#stlNeedFlagTitle").hide();
			$("#stlNeedFlagText").hide();
			$("#stlNeedFlag").val("");
			$("#stlNeedFlagTitleBlack").show();
			$("#stlNeedFlagTextBlack").show();
			
		}else if(exelusiveCardFlag==1){
			$("#stlNeedFlagTitle").show();
			$("#stlNeedFlagText").show();
			$("#stlNeedFlagTitleBlack").hide();
			$("#stlNeedFlagTextBlack").hide();
		
		}
	}
	
	function getDate(date){
		 var dates = date.split("-");
		 var dateReturn = '';
		 
		 for(var i=0; i<dates.length; i++){
		  dateReturn+=dates[i];
		 }
		 return dateReturn;
	}
</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">卡余额</div>
					<div class="shmc_tab2">
						<form id="form" action="${pageContext.request.contextPath}/ClearStatDailyAccdet/getList" method="post">
							<input type="hidden" id="curPage" name="curPage" value="${curPage}" /> 
							<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="text-align: right;">统计日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.begainDailyDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="begainDailyDate" id="begainDailyDate" />
										-
										<input type="text" class="inputext_style" value="${query.endDailyDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="endDailyDate" id="endDailyDate" />	
									</td>
									<td style="text-align: right;">卡BIN：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${query.cardBin}" name="cardBin" id="cardBin" maxlength="11"/>
									</td>
									<td style="text-align: right;">卡产品：</td>
									<td style="text-align: left;">
										<select id="crdproduct" name="crdproduct" class="inputext_style" value="${query.crdproduct}">
											<option value="">--请选择--</option>										
											<c:forEach var="model" items="${cortexCrdProductList}">
												<option value="${model.crdproduct}" <c:if test="${query.crdproduct==model.crdproduct}">selected="selected"</c:if>>
													${model.descr}
												</option>
											</c:forEach>
										</select>	
									</td>
									
										<td style="text-align: right;">卡状态：</td>
										<td style="text-align: left;">
										<select id="cardStatus" name="cardStatus" class="inputext_style" value="${query.cardStatus}">
											<option value="">--请选择--</option>										
											<option value="94" <c:if test="${query.cardStatus eq '94'}">selected="selected"</c:if>>合卡冻结</option>
											<option value="92" <c:if test="${query.cardStatus eq '92'}">selected="selected"</c:if>>异号补卡冻结</option>
											<option value="91" <c:if test="${query.cardStatus eq '91'}">selected="selected"</c:if>>消磁补卡冻结</option>
											<option value="90" <c:if test="${query.cardStatus eq '90'}">selected="selected"</c:if>>拆卡冻结</option>
											<option value="93" <c:if test="${query.cardStatus eq '93'}">selected="selected"</c:if>>未使用</option>
											<option value="97" <c:if test="${query.cardStatus eq '97'}">selected="selected"</c:if>>金额冻结</option>
											<option value="96" <c:if test="${query.cardStatus eq '96'}">selected="selected"</c:if>>黑名单</option>
											<option value="98" <c:if test="${query.cardStatus eq '98'}">selected="selected"</c:if>>其他(冻结)</option>
											<option value="95" <c:if test="${query.cardStatus eq '95'}">selected="selected"</c:if>>作废</option>
											<option value="00" <c:if test="${query.cardStatus eq '00'}">selected="selected"</c:if>>正常</option>
											<option value="01" <c:if test="${query.cardStatus eq '01'}">selected="selected"</c:if>>密码尝试超限</option>
											<option value="02" <c:if test="${query.cardStatus eq '02'}">selected="selected"</c:if>>未发行</option>
											<option value="03" <c:if test="${query.cardStatus eq '03'}">selected="selected"</c:if>>卡过期</option>
											<option value="04" <c:if test="${query.cardStatus eq '04'}">selected="selected"</c:if>>挂失</option>
											<option value="05" <c:if test="${query.cardStatus eq '05'}">selected="selected"</c:if>>失窃</option>
											<option value="06" <c:if test="${query.cardStatus eq '06'}">selected="selected"</c:if>>客户注销</option>
											<option value="07" <c:if test="${query.cardStatus eq '07'}">selected="selected"</c:if>>银行取消</option>
											<option value="08" <c:if test="${query.cardStatus eq '08'}">selected="selected"</c:if>>欺诈使用</option>
											<option value="20" <c:if test="${query.cardStatus eq '20'}">selected="selected"</c:if>>等待激活</option>
											<option value="99" <c:if test="${query.cardStatus eq '99'}">selected="selected"</c:if>>未激活</option>
										</select>	
									</td>
																										
								</tr>
								<tr>									
									<td style="text-align: right;">是否是专属卡</td>
									<td style="text-align: left;">			
										<select id="exelusiveCardFlag" name="exelusiveCardFlag" class="inputext_style" onchange="changeStlNeedFlag()" value="${query.exelusiveCardFlag}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.exelusiveCardFlag eq '0'}">selected="selected"</c:if>>否</option>
											<option value="1" <c:if test="${query.exelusiveCardFlag eq '1'}">selected="selected"</c:if>>是</option>
										</select>															
									</td>
									
									<td id="stlNeedFlagTitle" style="text-align: right;">是否需要结算</td>
									<td id="stlNeedFlagText" style="text-align: left;">			
										<select id="stlNeedFlag" name="stlNeedFlag" class="inputext_style" value="${query.stlNeedFlag}">
											<option value="">--请选择--</option>
											<option value="0" <c:if test="${query.stlNeedFlag eq '0'}">selected="selected"</c:if>>无需结算</option>
											<option value="1" <c:if test="${query.stlNeedFlag eq '1'}">selected="selected"</c:if>>需要结算</option>
										</select>															
									</td>
									<td id="stlNeedFlagTitleBlack" style="text-align: right;"></td>
									<td id="stlNeedFlagTextBlack" style="text-align: left;">			
																									
									</td>
									
									
									<td style="text-align: right;"></td>
										<td style="text-align: left;">
										</td>
																												
									<td style="text-align: center;" colspan="2">
										
											<input type="button" value="查询" class="button" onclick="query()"/>
											<input type="button" value="导出" class="button" onclick="exportExcel()" />
										
										<input type="button" value="清空" class="button" onClick='resetQuery()'/>	
									</td>									
								</tr>
							</table>
						</form>
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<th>序号</th>									
								<th>卡BIN</th>
								<th>卡产品</th>
								<th>是否是专属卡</th>
								<th>卡产品名称</th>
								<th>卡状态</th>
								<th>是否需要结算</th>
								<th>卡张数</th>
								<th>卡账户余额(元)</th>								
								<th>实名账户余额(元)</th>
								<th>积分账户余额(元)</th>
								<th>联名卡积分账户(元)</th>
								<th>账户总余额(元)</th>
								<th>统计日期</th>
							</tr>
							<c:if test="${ ClearStatDailyAccdetList != null  }">
								<c:forEach items="${ClearStatDailyAccdetList}" var="info" varStatus="index">
									<tr class="cow">
										<td>${((curPage-1) * pageSize) + index.count }</td>									
										<td>${info.cardBin}</td>
										<td>${info.crdproduct}</td>
										<td>
											<c:if test="${info.exelusiveCardFlag=='0'}">否</c:if>	
											<c:if test="${info.exelusiveCardFlag=='1'}">是</c:if>											
										</td>
										<td>
											${info.cardTypeName}
																					
										</td>
										<td>
											${info.cardStatusDesc}
																					
										</td>
										<td>
											<c:if test="${info.exelusiveCardFlag=='1'&& info.stlNeedFlag=='0'}">不需要</c:if>	
											<c:if test="${info.exelusiveCardFlag=='1'&& info.stlNeedFlag=='1'}">需要</c:if>
											<c:if test="${info.exelusiveCardFlag=='0'}">--</c:if>	
																						
										</td>
										
										<td>${info.cardCnt}</td>
										<td>
										<fmt:formatNumber value="${info.cardBal}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>
										<td>
										<fmt:formatNumber value="${info.trueBal}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>	
										<td>
										<fmt:formatNumber value="${info.instBal}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>
										<td>
										<fmt:formatNumber value="${info.aCC}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>
										<td>
										<fmt:formatNumber value="${info.totalBal}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber>
										</td>																				
										<td>${info.dailyDate}</td>																						
								</c:forEach>
								<c:if test="${fn:length(ClearStatDailyAccdetList)==0}">
									<td colspan="18" style="text-align: center"><span style="color: red">***没有相关记录***</span></td>
								</c:if>
								<tr>
									<td colspan="18" class="page">${pageBar}</td>
								</tr>
							</c:if>
							<c:if test="${ ClearStatDailyAccdetList == null  }">
								<tr>
									<td colspan="18" style="text-align: center"><span style="color: red">***请查询***</span></td>
								</tr>
							</c:if>							
							<c:if test="${ ClearStatDailyAccdetList != null  }">
								<tr>
									<td colspan="18" style="text-align: center">
										<span style="color: red">
										合计
										&nbsp;&nbsp;&nbsp;&nbsp;卡张数:${card_cnt_sum}
										&nbsp;&nbsp;&nbsp;&nbsp;卡账户余额:${card_bal_sum}
										&nbsp;&nbsp;&nbsp;&nbsp;实名账户余额:${true_bal_sum}
										&nbsp;&nbsp;&nbsp;&nbsp;积分账户余额:${inst_bal_sum}
										&nbsp;&nbsp;&nbsp;&nbsp;联名卡积分账户:${total_acc_sum}
										&nbsp;&nbsp;&nbsp;&nbsp;账户总余额:${total_bal_sum}
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
