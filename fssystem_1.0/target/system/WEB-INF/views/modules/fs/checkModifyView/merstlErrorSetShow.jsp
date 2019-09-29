<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>卡余额调整</title>
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
					<div class="sh_title">卡余额调整设置</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/MerstlErrorSet/save" method="post">
							<input type="hidden" name="id" value="${info.id}"/>
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">卡张数调整：</td>
									<td style="text-align: left;">
										<select id = "numFlag" name="numFlag" class="input-mini">
											<option value="0" <c:if test="${info.numFlag == '0' }">selected="selected"</c:if>>上调</option>
											<option value="1" <c:if test="${info.numFlag == '1' }">selected="selected"</c:if>>下调</option>
										</select>
										-
										<input type="text" id="errorCardNum" name="errorCardNum" value="${info.errorCardNum}" maxlength="10"
											required="required" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" />
									</td>
								</tr>	
								<tr>
									<td style="text-align: left;">卡余额调整：</td>
									<td style="text-align: left;">
										<select id = "amtFlag" name="amtFlag" class="input-mini">
											<option value="0" <c:if test="${info.amtFlag == '0' }">selected="selected"</c:if>>上调</option>
											<option value="1" <c:if test="${info.amtFlag == '1' }">selected="selected"</c:if>>下调</option>
										</select>
										-
										<input type="text" id="errorCardAmt" name="errorCardAmt" value="${info.errorCardAmt}" maxlength="10" 
											required="required" onkeyup="this.value=this.value.replace(/[^\d.]/g,'')"/>
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