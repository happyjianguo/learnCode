<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流水明细</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				var transactiondateStart = $("#transactiondateStart").val();
				var transactiondateEnd = $("#transactiondateEnd").val();
				if(transactiondateStart!='' && transactiondateEnd!='' && (getDate(transactiondateStart)-getDate(transactiondateEnd)>0)){
				    alert("交易截止日期不能小于交易起始日期");
				    return false;
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/TransactionRecordsHisT/list");				
				$("#form").submit();
			});
			
		});
		
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
			$("#merchantnumber").val("");
			$("#transactiondateStart").val(CurentDayTime("-1",null));
			$("#transactiondateEnd").val(CurentDayTime(null,null));
			$("#terminalnumber").val("");
			$("#cardnumber").val("");
			$("#referencenumber").val("");
			$("#transactiontype").select().val("");
		}
		
	</script>
</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">
							流水明细
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/TransactionRecordsHisT/list"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<td style="text-align: right;">商户号：</td>
									<td style="text-align: left;">
										<input type="text" name="merchantnumber" id="merchantnumber" value="${queryModel.merchantnumber }" maxlength="15" class="inputext_style"/>
									</td>
									<td style="text-align: right;">终端号：</td>
									<td style="text-align: left;">
										<input type="text" name="terminalnumber" id="terminalnumber" value="${queryModel.terminalnumber }" maxlength="9" class="inputext_style"/>
									</td>
									<td style="text-align: right;">交易日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${queryModel.transactiondateStart}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
											name="transactiondateStart" id="transactiondateStart" />-
										<input type="text" class="inputext_style" value="${queryModel.transactiondateEnd}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
											name="transactiondateEnd" id="transactiondateEnd" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">卡号：</td>
									<td style="text-align: left;">
										<input type="text" name="cardnumber" id="cardnumber" value="${queryModel.cardnumber }" maxlength="16" class="inputext_style"/>
									</td>
									<td style="text-align: right;">参考号：</td>
									<td style="text-align: left;">
										<input type="text" name="referencenumber" id="referencenumber" value="${queryModel.referencenumber }" maxlength="15" class="inputext_style"/>
									</td>
									<td style="text-align: right;">交易类型：</td>
									<td style="text-align: left;" >
										<select id="transactiontype" name="transactiontype" class="inputext_style" value="${queryModel.transactiontype }">
											<option value="" >--请选择--</option>
											<c:forEach var="entity" items="${fns:getDictList('TRAN_TYPE')}">
												<option value="${entity.value }" <c:if test="${queryModel.transactiontype eq entity.value}">selected="selected"</c:if>>${entity.label }</option>
											</c:forEach>
										<select>
									</td>
									<td style="text-align: center;">
										<input id="btnSubmit" class="button" type="button" value="查询"/>
										<%-- <input type="button" value="导出" class="button" id="exportExcel" /> --%>
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
									<th style="text-align: center;">卡号</th>
									<th style="text-align: center;">交易金额</th>
									<th style="text-align: center;">终端位置</th>
									<th style="text-align: center;">交易类型</th>
									<th style="text-align: center;">交易状态</th>
									<th style="text-align: center;">交易日期</th>
									<th style="text-align: center;">交易时间</th>
									<th style="text-align: center;">流水号</th>
									<th style="text-align: center;">批次号</th>
									<th style="text-align: center;">参考号</th>
								</tr>
								<c:if test="${transactionRecordsHisList != null}">
									<c:forEach items="${transactionRecordsHisList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>
												${info.merchantnumber}
											</td>
											<td>
												${info.merchantName}
											</td>
											<td>
												${info.terminalnumber}
											</td>
											<td>
												${info.cardnumber}
											</td>
											<td>
												<fmt:formatNumber value="${info.transactionmoney}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												${info.terminallocation}
											</td>
											<td>
												${fns:getDictLabel(info.transactiontype, 'TRAN_TYPE', '')}
											</td>
											<td>
												${fns:getDictLabel(info.transactionstatus, 'TXN_STATUS', '')}
											</td>
											<td>
												${info.transactiondate}
											</td>
											<td>
												${info.transactiontime}
											</td>
											<td>
												${info.serialnumber}
											</td>
											<td>
												${info.lotno}
											</td>
											<td>
												${info.referencenumber}
											</td>
										</tr>
									</c:forEach>
									<c:if test="${fn:length(transactionRecordsHisList)==0}">
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
								<c:if test="${transactionRecordsHisList == null}">
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