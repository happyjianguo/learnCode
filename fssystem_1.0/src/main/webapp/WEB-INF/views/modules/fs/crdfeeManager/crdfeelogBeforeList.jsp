<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>扣款明细预收查询</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				var year = $("#year").val();
				var month = $("#month").val();
				if(!Boolean(year)){
					if(Boolean(month)){
						alert("月份存在时，年份不可为空！");
						return false;
					}
				}

				$("#form").attr("action","${pageContext.request.contextPath}/OldCrdfeelogBefore/list");				
				$("#form").submit();
			});
			
			$('#exportExcel').click(function() {
				var year = $("#year").val();
				var month = $("#month").val();
				if(!Boolean(year)){
					if(Boolean(month)){
						alert("月份存在时，年份不可为空！");
						return false;
					}
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/OldCrdfeelogBefore/exportExcel");
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
						<div class="sh_title">
							老福卡扣款明细预收查询
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/OldCrdfeelogBefore/list"
								method="post">
								<input type="hidden" id="curPage" name="curPage" value="${curPage}" />
								<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<td style="text-align: right;">卡号：</td>
									<td style="text-align: left;">
										<input type="text" name="pan" id="pan" value="${queryModel.pan }" maxlength="16" class="inputext_style"/>
									</td>
									<td style="text-align: right;">年份：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style"
											value="${queryModel.year}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyy',isShowClear:true,readOnly:true})"
											name="year" id="year" />
									</td>
									<td style="text-align: right;">月份：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style"
											value="${queryModel.month}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'MM',isShowClear:true,readOnly:true})"
											name="month" id="month" />
									</td>
									<td style="text-align: right;" colspan="4">
										<input id="btnSubmit" class="button" type="button" value="查询"/>
										<input type="button" value="导出" class="button" id="exportExcel" />
										<input type="button" value="清空" class="button" onClick="ClearForm('form')"/>
									</td>
								</tr>
								</table>
							</form>
							<table width="100%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<th style="text-align: center;">序号</th>
									<th style="text-align: center;">卡号</th>
									<th style="text-align: center;">扣款商户号</th>
									<th style="text-align: center;">扣款终端号</th>
									<th style="text-align: center;">扣款月份</th>
									<th style="text-align: center;">扣款余额（元）</th>
									<th style="text-align: center;">收取金额（元）</th>
									<th style="text-align: center;">交易日期</th>
									<th style="text-align: center;">交易时间</th>
								</tr>
								<c:if test="${crdfeelogList != null}">
									<c:forEach items="${crdfeelogList}" var="info"
										varStatus="index">
										<tr class="cow">
											<td>
												${((curPage-1) * pageSize) + index.count }
											</td>
											<td>
												${info.pan}
											</td>
											<td>
												${info.merchantnumber}
											</td>
											<td>
												${info.terminalnumber}
											</td>
											<td>
												${info.loacleDate}
											</td>
											<td>
												<fmt:formatNumber value="${info.avlbal}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												<fmt:formatNumber value="${info.fee}" pattern="#,###,###,###,###,##0.##"
													minFractionDigits="2"></fmt:formatNumber>
											</td>
											<td>
												${info.transactiondate}
											</td>
											<td>
												${info.transactiontime}
											</td>
										</tr>
									</c:forEach>
									<tr class="cow">
										<td colspan="9" align="center">
											<font color="red">总条数：${count}&emsp;&emsp;
											收取金额汇总(元)：<fmt:formatNumber value="${feeSum}" pattern="#,###,###,###,###,##0.##" minFractionDigits="2" ></fmt:formatNumber></font>
										</td>
									</tr>
									<c:if test="${fn:length(crdfeelogList)==0}">
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
								<c:if test="${crdfeelogList == null}">
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