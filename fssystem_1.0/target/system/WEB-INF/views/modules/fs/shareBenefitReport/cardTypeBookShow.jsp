<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>卡类型分润设置</title>
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
			var regYear = /^[01](\.\d{1,2})?$/;
			var feeOrder = $("#feeOrder").val();
			if(Boolean(feeOrder)){
				if(!regYear.exec(feeOrder)){
					alert("分润比例请输入0.01~1.00之间的数字！");
					return false;
				}
			}
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
					<div class="sh_title">卡类型分润设置</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/CardShareBenefit/save" method="post">
							<input type="hidden" name="flag" value="${flag}"/>
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">卡BIN：</td>
									<td style="text-align: left;">
										<input type="text" name="cardTypeId" value="${info.cardTypeId}" maxlength="9"/>
									</td>
								</tr>	
								<tr>
									<td style="text-align: left;">分润比例：</td>
									<td style="text-align: left;">
										<input type="text" name="feeOrder" id="feeOrder" value="${info.feeOrder}" maxlength="4"/>
									</td>
								</tr>	
								<tr>
									<td style="text-align: center;" colspan="4">
										<input type="submit" id="submit" value="提交" class="button" />
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