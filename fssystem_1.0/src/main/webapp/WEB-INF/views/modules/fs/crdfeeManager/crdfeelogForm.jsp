<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>老福卡退款</title>
	<meta name="decorator" content="default"/>

	<script type="text/javascript">
		$(document).ready(function() {
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			$('#closeBtn').click(function() {
				parent.layer.close(index);
			});
			$("#form").validate({
				onsubmit : true,// 是否在提交是验证
				onfocusout : false,// 是否在获取焦点时验证
				onkeyup : false,// 是否在敲击键盘时验证
				rules : {
				},
				//提交表单
				submitHandler : function(form) {
					$("#submit").attr("disabled", true); 
					var param = $("#form").serialize();
					url = $("#form").attr("action");
					$.ajax({
						url : url,
						type : "post",
						data : param,
						dataType : "json",
						success : function(data) {
							if (data.result == '0') {
								layer.alert(data.resultMsg, 1, function() {
									$("#form", window.parent.document).submit();
								});
							} else {
								layer.alert(data.resultMsg);
								$("#submit").removeAttr("disabled");
							}
						}
					});
				}
			});
		});
	</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">老福卡退款详情</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/OldCrdfeelog/refund" method="post">
							<input type="hidden" name="refundIDs" value="${refundIDs}"/>
							<table width="100%" border="0" class="tab">
								<c:forEach var="info" items="${crdfeelogList}">
									<tr>
										<td style="text-align: left;">商户号：</td>
										<td style="text-align: left;">
											<input type="text" name="merchantnumber" id="merchantnumber" readonly="readonly" value="${info.merchantnumber}" 
												class="input-medium " />
										</td>
										<td style="text-align: left;">终端号：</td>
										<td style="text-align: left;">
											<input type="text" name="terminalnumber" id="terminalnumber" readonly="readonly" value="${info.terminalnumber}" 
												class="input-medium "/>
										</td>
										<td style="text-align: left;">卡号：</td>
										<td style="text-align: left;">
											<input type="text" name="pan" id="pan" readonly="readonly" value="${info.pan}" 
												class="input-medium "/>
										</td>
										<td style="text-align: left;">金额：</td>
										<td style="text-align: left;">
											<input type="text" name="fee" id="fee" readonly="readonly" 
												value="<fmt:formatNumber value='${info.fee}' pattern='#,###,###,###,###,##0.##' minFractionDigits='2' ></fmt:formatNumber>" 
												class="input-medium " />
										</td>
									</tr>	
								</c:forEach>
								<tr>
									<td style="text-align: center;" colspan="8">
										<input type="submit" id="submit" value="退款" class="button" />
										<input type="button" id="closeBtn" value="关闭" class="button" />										
									</td>
								</tr>															
							</table>
						</form>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>