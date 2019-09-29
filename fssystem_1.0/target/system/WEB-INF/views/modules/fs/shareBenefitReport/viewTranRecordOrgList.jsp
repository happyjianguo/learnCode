<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户分润</title>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$('#exportExcel').click(function() {
				var transactiondateStart = $("#transactiondateStart").val();
				var transactiondateEnd = $("#transactiondateEnd").val();
				if(transactiondateStart!='' && transactiondateEnd!='' && (getDate(transactiondateStart)-getDate(transactiondateEnd)>0)){
				    alert("起始账期不能小于截止账期");
				    return false;
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/viewTranRecordOrg/exportExcel");				
				$("#form").submit();
			});
			
			$('#btnSubmit').click(function() {
				var transactiondateStart = $("#transactiondateStart").val();
				var transactiondateEnd = $("#transactiondateEnd").val();
				if(transactiondateStart!='' && transactiondateEnd!='' && (getDate(transactiondateStart)-getDate(transactiondateEnd)>0)){
				    alert("起始账期不能小于截止账期");
				    return false;
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/viewTranRecordOrg/list");				
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
			$("#orgBin").val("");
			$("#orgName").val("");
			$("#mrchtName").val("");
		}
		
	</script>
</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">
							商户分润信息
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/viewTranRecordOrg/list"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<td style="text-align: right;">商户号：</td>
									<td style="text-align: left;">
										<input type="text" name="merchantnumber" id="merchantnumber" value="${queryModel.merchantnumber }" maxlength="15" class="inputext_style"/>
									</td>
									<td style="text-align: right;">商户名称：</td>
									<td style="text-align: left;">
										<input type="text" name="mrchtName" id="mrchtName" value="${queryModel.mrchtName }" class="inputext_style"/>
									</td>
									<td style="text-align: right;">起止账期：</td>
									<td style="text-align: left;" colspan="2">
										<input type="text" class="inputext_style" value="${queryModel.transactiondateStart}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
											name="transactiondateStart" id="transactiondateStart" />-
										<input type="text" class="inputext_style" value="${queryModel.transactiondateEnd}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
											name="transactiondateEnd" id="transactiondateEnd" />
									</td>
								</tr>
								<tr>
									<td style="text-align: right;">分润机构：</td>
									<td style="text-align: left;">
										<input type="text" name="orgName" id="orgName" value="${queryModel.orgName }" class="inputext_style"/>
									</td>
									<td style="text-align: right;">分润卡BIN：</td>
									<td style="text-align: left;">
										<input type="text" name="orgBin" id="orgBin" value="${queryModel.orgBin }" maxlength="9" class="inputext_style"/>
									</td>
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
									<th style="text-align: center;">交易金额</th>
									<th style="text-align: center;">笔数</th>
									<th style="text-align: center;">商户手续费率(%)</th>
									<th style="text-align: center;">手续费收取金额</th>
									<th style="text-align: center;">手续费留底费率(%)</th>
									<th style="text-align: center;">手续费留底金额</th>
									<th style="text-align: center;">分润金额</th>
								</tr>
								<c:if test="${viewTranRecordOrgList != null}">
									<c:forEach items="${viewTranRecordOrgList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>${((curPage-1) * pageSize) + index.count }</td>
											<td>${info.merchantnumber}</td>
											<td>${info.mrchtName}</td>
											<td>
												<fmt:formatNumber value="${info.transactionmoney}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>${info.tranTotal}</td>
											<td>
												<fmt:formatNumber value="${info.feeOrder}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.perfee}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.feeRetention}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.amtRetention}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.amtShare}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${fn:length(viewTranRecordOrgList)!=0}">
										<tr>
											<td colspan="18" align="center">
												<font color="red">汇总：
													手续费收取总金额(元)：<fmt:formatNumber value="${sumAmt.perfee}" pattern="#,###,###,###,###,##0.##"
														minFractionDigits="2"></fmt:formatNumber>&emsp;&emsp;
													手续费留底总金额(元)：<fmt:formatNumber value="${sumAmt.amtRetention}" pattern="#,###,###,###,###,##0.##"
														minFractionDigits="2"></fmt:formatNumber>&emsp;&emsp;
													分润总金额(元)：<fmt:formatNumber value="${sumAmt.amtShare}" pattern="#,###,###,###,###,##0.##"
														minFractionDigits="2"></fmt:formatNumber>&emsp;&emsp;
												</font>
											</td>
										</tr>
									</c:if>
									<c:if test="${fn:length(viewTranRecordOrgList)==0}">
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
								<c:if test="${viewTranRecordOrgList == null}">
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