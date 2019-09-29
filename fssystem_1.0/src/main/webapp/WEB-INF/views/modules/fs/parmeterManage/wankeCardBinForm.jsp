<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>万科卡BIN设置</title>
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
					<div class="sh_title">万科卡BIN设置</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/wankeCardBin/save" method="post">
							<input type="hidden" name="flag" value="${flag}"/>
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">卡BIN-ID：</td>
									<td style="text-align: left;">
										<input type="text" name="cardId" id="cardId" readonly="readonly" value="${info.cardId}" 
											maxlength="10" required/>
									</td>
								</tr>	
								<tr>
									<td style="text-align: left;">万科卡BIN：</td>
									<td style="text-align: left;">
										<input type="text" name="cardBin" id="cardBin" value="${info.cardBin}" 
										maxlength="9" required onkeyup="value=value.replace(/[^\d]/g,'')"/>
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