<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>未结算信息</title>
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
					<div class="sh_title">查看未结算信息</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/ClearUnMerStlBook/getList" method="post">
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
									<td style="text-align: left;">生成日期：</td>
									<td style="text-align: left;">
										${info.genDt}
									</td>
									<td style="text-align: left;">帐期：</td>
									<td style="text-align: left;">
										${info.startunstlDate}-${info.endunstlDate}
									</td>
								</tr>												
								<tr>
									<td style="text-align: left;">消费金额（元）：</td>
									<td style="text-align: left;">
										${info.tranAmt}
									</td>
									<td style="text-align: left;">消费笔数：</td>
									<td style="text-align: left;">
										${info.tranNum}
									</td>
								</tr>	
								<tr>
									<td style="text-align: left;"></td>
									<td style="text-align: left;"></td>									
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