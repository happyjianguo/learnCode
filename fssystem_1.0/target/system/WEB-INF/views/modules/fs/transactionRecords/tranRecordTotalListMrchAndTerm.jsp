<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易统计</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#exportExcel').click(function() {
				if(!verifyInput()){
					return false;
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/tranRecordTotal/exportExcel");
				$("#form").submit();
			});
			
			$('#btnSubmit').click(function() {
				if(!verifyInput()){
					return false;
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/tranRecordTotal/list");				
				$("#form").submit();
			});
			
		});
		
		function verifyInput(){
			var transactiondateStart = $("#transactiondateStart").val();
			var transactiondateEnd = $("#transactiondateEnd").val();
			if(transactiondateStart!='' && transactiondateEnd!='' && (getDate(transactiondateStart)-getDate(transactiondateEnd)>0)){
			    alert("交易截止日期不能小于交易起始日期");
			    return false;
			}
			
			var begainCardNo = $("#begainCardNo").val();
			var endCardNo = $("#endCardNo").val();
			if(begainCardNo != null && trim(begainCardNo) != ''){
				if(endCardNo != null && trim(endCardNo) != ''){
					begainCardNo = trim(begainCardNo);
					endCardNo = trim(endCardNo);
					if(begainCardNo.length < 15 || endCardNo.length < 15){
						alert("开始卡号或结束卡号不足15位，请检查后重新输入！");
						return false;
					}
					var begainCardBin = begainCardNo.substring(0, 9);
					var endCardBin = endCardNo.substring(0, 9);
					if(begainCardBin == endCardBin){
						var begainCard = begainCardNo.substring(12, 15);
						var endCard = endCardNo.substring(12, 15);
						var cardNum = endCard - begainCard;
						if(cardNum > 100){
							alert("开始卡号与结束卡号之间的卡张数最多只能为100张！");
							return false;
						}
					}else{
						alert("开始卡号与结束卡号的卡BIN不一致，请您重新输入！");
						return false;
					}
				}else{
					alert("开始卡号与结束卡号请同时输入！");
					return false;
				}
			}else if(endCardNo != null && trim(endCardNo) != ''){
				if(begainCardNo == null || trim(begainCardNo) == ''){
					alert("开始卡号与结束卡号请同时输入！");
					return false;
				}
			}
			return true;
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
		
		//删除左右两端的空格
		function trim(str){ 
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		
		function resetQuery(){
			$("#tdLable").html("商户号：");
			$("#tdText").html("<input type='text' name='merchantnumber' id='merchantnumber' value=''" 
					+ "maxlength='15' class='inputext_style'/>");
			$("#transactiondateStart").val("");
			$("#transactiondateEnd").val("");
			$("#begainCardNo").val("");
			$("#endCardNo").val("");
			$("#queryType").select().val("0");
			$("#consumetype").select().val("");
			$("#datasourceid").select().val("");
			$("#isBanlance").select().val("");
			$("#tdLable1").html("");
			$("#tdText1").html("");
		}
		
		function selectTotalType(queryType){
			if(queryType == '0'){	//商户号
				$("#tdLable").html("商户号：");
				$("#tdText").html("<input type='text' name='merchantnumber' id='merchantnumber' value=''" 
						+ "maxlength='15' class='inputext_style'/>");
				$("#tdLable1").html("");
				$("#tdText1").html("");
			}else if(queryType == '1'){	//终端号
				$("#tdLable").html("终端号：");
				$("#tdText").html("<input type='text' name='terminalnumber' id='terminalnumber' value=''" 
						+ "maxlength='8' class='inputext_style'/>");
				$("#tdLable1").html("");
				$("#tdText1").html("");
			}else if(queryType == '2'){	//商户终端
				$("#tdLable").html("商户号：");
				$("#tdText").html("<input type='text' name='merchantnumber' id='merchantnumber' value=''" 
						+ "maxlength='15' class='inputext_style'/>");
				$("#tdLable1").html("终端号：");
				$("#tdText1").html("<input type='text' name='terminalnumber' id='terminalnumber' value=''" 
						+ "maxlength='8' class='inputext_style'/>");
			}else if(queryType == '3'){	//卡号
				$("#tdLable").html("卡号：");
				$("#tdText").html("<input type='text' name='cardnumber' id='cardnumber' value=''" 
						+ "maxlength='16' class='inputext_style'/>");
				$("#tdLable1").html("");
				$("#tdText1").html("");
			}else if(queryType == '4'){	//卡类型
				$("#tdLable1").html("");
				$("#tdText1").html("");
				$("#tdLable").html("卡类型：");
				$("#tdText").html("<select id='cardtype' name='cardtype' class='inputext_style'>"
						+ "<option value=''>--请选择--</option></select>");
				getCardType();
			}
		}
		
		//获取卡类型的AJAX请求
		function getCardType(){
			$("#cardtype").html("<option value=''>--请选择--</option>");
			var cardtype = $("#cardtype").val();
			if(cardtype != undefined){	
				$.ajax({
					url : '${pageContext.request.contextPath}/tranRecordTotal/getCardType',
					type : "post",
					data : {
						cardtype:cardtype
					},
					dataType : "json",
					success : function(data) {
						if (data.length != 0) {
							$.each(data, function(i, info) {
								$("#cardtype").append(
										"<option value='" + info.cardnumber + "'>" + info.cardkindname + "</option>");
							});
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
						<div class="sh_title">交易统计列表</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/tranRecordTotal/list"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">开始卡号：</td>
										<td style="text-align: left;">
											<input type="text" name="begainCardNo" id="begainCardNo" value="${queryModel.begainCardNo}" maxlength="15" class="inputext_style"/>
										</td>
										<td style="text-align: right;">结束卡号：</td>
										<td style="text-align: left;">
											<input type="text" name="endCardNo" id="endCardNo" value="${queryModel.endCardNo}" maxlength="15" class="inputext_style"/>
										</td>
										<td style="text-align: right;">交易日期：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${queryModel.transactiondateStart}"
												placeholder="yyyyMMdd" maxlength="8"
												name="transactiondateStart" id="transactiondateStart" />-
											<input type="text" class="inputext_style" value="${queryModel.transactiondateEnd}"
												placeholder="yyyyMMdd" maxlength="8"
												name="transactiondateEnd" id="transactiondateEnd" />
										</td>
									</tr>
									<tr>
										<td style="text-align: right;">中石化消费类型：</td>
										<td style="text-align: left;">
											<select name="consumetype" id="consumetype" class="inputext_style">
												<option value="" >--请选择--</option>
												<c:forEach var="entity" items="${fns:getDictList('CONSUMET_TYPE')}">
													<option value="${entity.value}" <c:if test="${queryModel.consumetype eq entity.value}">selected="selected"</c:if>>${entity.label}</option>
												</c:forEach>
											</select>
										</td>
										<td style="text-align: right;">数据来源：</td>
										<td style="text-align: left;">
											<select name="datasourceid" id="datasourceid" class="inputext_style">
												<option value="" >--请选择--</option>
												<c:forEach var="entity" items="${fns:getDictList('DATA_SOURCE')}">
													<option value="${entity.value}" <c:if test="${queryModel.datasourceid eq entity.value}">selected="selected"</c:if>>${entity.label}</option>
												</c:forEach>
											</select>
										</td>
										<td style="text-align: right;">是否参与结算：</td>
										<td style="text-align: left;">
											<select name="isBanlance" id="isBanlance" class="inputext_style">
												<option value="" >--请选择--</option>
												<c:forEach var="entity" items="${fns:getDictList('IS_BALANCE')}">
													<option value="${entity.value}" <c:if test="${queryModel.isBanlance eq entity.value}">selected="selected"</c:if>>${entity.label}</option>
												</c:forEach>
											</select>
										</td>
										<td style="text-align: right;"></td>
										<td style="text-align: left;"></td>
									</tr>
									<tr>
										<td style="text-align: right;">统计类型：</td>
										<td style="text-align: left;">
											<select name="queryType" id="queryType" class="inputext_style" onchange="selectTotalType(this.value);">
												<!-- <option value="" >--请选择--</option> -->
												<c:forEach var="entity" items="${fns:getDictList('TRAN_TOTAL_TYPE')}">
													<option value="${entity.value}" <c:if test="${queryModel.queryType eq entity.value}">selected="selected"</c:if>>${entity.label}</option>
												</c:forEach>
											</select>
										</td>
										<td id="tdLable" style="text-align: right;">商户号：</td>
										<td id="tdText" style="text-align: left;">
											<input type="text" name="merchantnumber" id="merchantnumber" value="${queryModel.merchantnumber}" maxlength="15" class="inputext_style"/>
										</td>
										<td id="tdLable1" style="text-align: right;">终端号：</td>
										<td id="tdText1" style="text-align: left;">
											<input type="text" name="terminalnumber" id="terminalnumber" value="${queryModel.terminalnumber}" maxlength="8" class="inputext_style"/>
										</td>
									</tr>
									<tr>
										<td style="text-align: right;" colspan="8">
											<input id="btnSubmit" class="button" type="button" value="查询"/>
											<input type="button" value="导出" class="button" id="exportExcel" />
											<input type="button" value="清空" class="button" onClick="resetQuery();"/>
										</td>
									</tr>
										
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th style="text-align: center;">序号</th>
									<th style="text-align: center;">商户号</th>
									<th style="text-align: center;">商户名称</th>
									<th style="text-align: center;">终端号</th>
									<th style="text-align: center;">终端位置</th>
									<th style="text-align: center;">交易金额</th>
									<th style="text-align: center;">交易笔数</th>
									<th style="text-align: center;">交易手续费</th>
								</tr>
								<c:if test="${tranRecordTotaList != null}">
									<c:forEach items="${tranRecordTotaList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>${info.merchantnumber}</td>
											<td>${info.merchantName}</td>
											<td>${info.terminalnumber}</td>
											<td>${info.terminallocation}</td>
											<td>
												<fmt:formatNumber value="${info.transactionmoney}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.transactionCount}" pattern="#,###,###,###,###,###"
													minFractionDigits="0"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.perfee}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${fn:length(tranRecordTotaList)==0}">
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
								<c:if test="${tranRecordTotaList == null}">
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