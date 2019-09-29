<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>分润报表</title>
		<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$('#exportExcel').click(function() {
			var startStlDate = $("#startStlDate").val();
			var endStlDate = $("#endStlDate").val();
			if(startStlDate!='' && endStlDate!='' && (getDate(startStlDate)-getDate(endStlDate)>0)){
			    alert("截止时间不能小于起始时间");
			    return false;
			}
			
			$("#form").attr("action","${pageContext.request.contextPath}/ShareBenefitReport/exportExcel");
			$("#form").submit();
		});
		
		$('#query').click(function() {
			var startStlDate = $("#startStlDate").val();
			var endStlDate = $("#endStlDate").val();
			if(startStlDate!='' && endStlDate!='' && (getDate(startStlDate)-getDate(endStlDate)>0)){
			    alert("截止时间不能小于起始时间");
			    return false;
			}
			
			$("#form").attr("action","${pageContext.request.contextPath}/ShareBenefitReport/getList");
			$("#form").submit();
		});		
	});	
	
	function resetQuery() {
		$("#merNo").val("");
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
</script>
	</head>
	<body>
		<div class="sh_main">
			<div class="shm_contab">
				<div class="poup_right">
					<div class="shmcr_cont01">
						<div class="sh_title">
							分润商户明细查询
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/ShareBenefitReport/getList"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td style="text-align: right;">分润商户号：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.merNo}" name="merNo" id="merNo" maxlength="15"/>
										</td>
										<td style="text-align: right;">分润机构：</td>
										<td style="text-align: left;">
											<select id="merchantOrg" name="merchantOrg" class="inputext_style">
												<option value="">--请选择--</option>										
												<c:forEach var="model" items="${merchantOrgList}">
													<option value="${model.orgId}" <c:if test="${query.merchantOrg eq model.orgId}">selected="selected"</c:if>>
														${model.orgName}
													</option>
												</c:forEach>
											</select>
										</td>
										<td style="text-align: right;">帐期：</td>
										<td style="text-align: left;">
											<input type="text" class="inputext_style" value="${query.startStlDate}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="startStlDate" id="startStlDate" />
											-
											<input type="text" class="inputext_style" value="${query.endStlDate}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="endStlDate" id="endStlDate" />
										</td>
									</tr>
									<tr>	
										<td style="text-align: right;" colspan="8">
											<input type="button" value="查询" class="button" id="query" />
											<input type="button" value="导出" class="button"
												id="exportExcel" />
											<input type="button" value="清空" class="button" onClick="ClearForm('form')"/>
										</td>
									</tr>
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th>
										序号
									</th>
									<th>
										商户号
									</th>
									<th>
										商户名称
									</th>
									<th>
										分润机构
									</th>
									<th>
										账期
									</th>
									<th>
										消费金额(元)
									</th>
									<th>
										笔数
									</th>
									<th>
										手续费(元)
									</th>
									<th>
										分润金额(元)
									</th>
								</tr>
								<c:if test="${shareBenefitReportList != null}">
									<c:forEach items="${shareBenefitReportList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>
												${info.merNo}
											</td>
											<td>
												${info.merName}
											</td>
											<td>
												${info.merchantOrg}
											</td>
											<td>
												${info.startStlDate}-${info.endStlDate}
											</td>											
											<td>
												<fmt:formatNumber value="${info.consumAmt}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												${info.consumNum}
											</td>
											<td>
												<fmt:formatNumber value="${info.fee}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.merchantAmt}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
									</c:forEach>
									<c:if test="${fn:length(shareBenefitReportList)!=0}">
										<tr>
											<td colspan="18" align="center">
												<font color="red">汇总：
													消费金额(元)：<fmt:formatNumber value="${sumConsumAmt}" pattern="#,###,###,###,###,##0.##"
														minFractionDigits="2"></fmt:formatNumber>&emsp;&emsp;
													笔数：${sumConsumNum}&emsp;&emsp;
													手续费(元)：<fmt:formatNumber value="${sumFee}" pattern="#,###,###,###,###,##0.##"
														minFractionDigits="2"></fmt:formatNumber>&emsp;&emsp;
													分润金额(元)：<fmt:formatNumber value="${sumMerchantAmt}" pattern="#,###,###,###,###,##0.##"
														minFractionDigits="2"></fmt:formatNumber>&emsp;&emsp;
												</font>
											</td>
										</tr>
									</c:if>
									<c:if test="${fn:length(shareBenefitReportList)==0}">
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
								<c:if test="${shareBenefitReportList == null}">
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
