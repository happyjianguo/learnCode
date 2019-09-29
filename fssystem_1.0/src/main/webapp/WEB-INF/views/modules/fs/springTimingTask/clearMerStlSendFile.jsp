<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>结算初表线下</title>
		<meta name="decorator" content="default" />
		<script type="text/javascript">
			$(document).ready(function() {
				$('#sendFile').click(function() {
					$("#form").attr("action","${pageContext.request.contextPath}/time/clearMerStl/sendFile");
					$("#form").submit();
				});		
				$('#sendFileExcel').click(function() {
					$("#form").attr("action","${pageContext.request.contextPath}/time/clearMerStl/sendFileExcel");
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
							发送对账明细单
						</div>
						<div class="shmc_tab2">
							<form id="form" action="${pageContext.request.contextPath}/time/clearMerStl/sendFile"
								method="post">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>	
										<td style="text-align: center;">
											<input type="text" class="inputext_style" value="${now}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
												name="now" id="now" />
											<font color="blue" size="3">
												是否给当前时间下, 已结算的线下商户发送对账明细单?
											</font>
										</td>
									</tr>
									<tr>	
										<td style="text-align: center;">
											<input type="button" value="发送PDF" class="button" id="sendFile" />
											<input type="button" value="发送EXCEL" class="button" id="sendFileExcel" />
										</td>
									</tr>
								</table>
							</form>
						<div class="control-group">
							<label class="control-label">
								<font color="red" size="3">返回结果：</font>
							</label>
							<div class="controls">
								<font color="blue" size="2">${result}</font>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
