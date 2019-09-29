<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>手续费信息</title>
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
					<div class="sh_title">查看手续费信息</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/ClearFeeBook/getList" method="post">
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">商户编号：</td>
									<td style="text-align: left;">
										${info.merNo}
										<input type="hidden" id="id" name="id" class="inputext_style" value="${info.id}" />	
									</td>
									<td style="text-align: left;">商户名称：</td>
									<td style="text-align: left;">
										${info.merName}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">发票抬头：</td>
									<td style="text-align: left;">
										${info.headOffice}
									</td>
									<td style="text-align: left;">消费场景：</td>
									<td style="text-align: left;">
										${info.sceneName}
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">开始帐期：</td>
									<td style="text-align: left;">
										${info.startDate}
									</td>
									<td style="text-align: left;">结束帐期：</td>
									<td style="text-align: left;">
										${info.startDate}
									</td>
								</tr>			
								<tr>
									<td style="text-align: left;">消费金额（元）：</td>
									<td style="text-align: left;">
										${info.tranAmt}
									</td>
									<td style="text-align: left;">手续费是否单独结算：</td>
									<td style="text-align: left;">
										<select id="feeType" name="feeType" class="inputext_style" disabled="true">									
											<option value="0" <c:if test="${info.feeType eq '0'}">selected="selected"</c:if>>否</option>
											<option value="1" <c:if test="${info.feeType eq '1'}">selected="selected"</c:if>>是</option>	
										</select>																	
									</td>
								</tr>										
								<tr>
									<td style="text-align: left;">手续费费率 （%）：</td>
									<td style="text-align: left;">
										${info.feeRate}
									</td>									
									<td style="text-align: left;">手续费（元） ：</td>
									<td style="text-align: left;">
										${info.fee}
									</td>								
								</tr>																									
								<tr>
									<td style="text-align: left;">生成日期</td>
									<td style="text-align: left;">${info.genDate}</td>									
									<td style="text-align: left;">备注:</td>
									<td style="text-align: left;">
										<textarea rows="5" cols="25" readonly="true">${info.comments} </textarea>	
									</td>
								</tr>	
								<tr>
									<td style="text-align: center;" colspan="4">
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