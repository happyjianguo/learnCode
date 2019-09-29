<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>卡类型设置</title>
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
					<div class="sh_title">卡类型信息设置</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/Cardkindsof/save" method="post">
							<input type="hidden" name="cardnumber" value="${info.cardnumber}"/>
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">卡类型：</td>
									<td style="text-align: left;">
										<input type="text" id="cardkindname" name="cardkindname" value="${info.cardkindname}" maxlength="30" />
									</td>
								</tr>	
								<tr>
									<td style="text-align: left;">是否专属卡：</td>
									<td style="text-align: left;">
										<select id = "isExclusive" name="isExclusive" class="inputext_style">
											<option value="0" <c:if test="${info.isExclusive == '0' }">selected="selected"</c:if>>否</option>
											<option value="1" <c:if test="${info.isExclusive == '1' }">selected="selected"</c:if>>是</option>
										</select>
									</td>
								</tr>	
								<tr>
									<td style="text-align: left;">是否参与结算：</td>
									<td style="text-align: left;">
										<select id="stlFlag" name="stlFlag" class="inputext_style">
											<option value="0" <c:if test="${info.stlFlag == '0' }">selected="selected"</c:if>>不参与</option>
											<option value="1" <c:if test="${info.stlFlag == '1' }">selected="selected"</c:if>>参与</option>
										</select>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">备注：</td>
									<td style="text-align: left;">
										<input type="text" id="remark" name="remark" value="${info.remark}" maxlength="40" />
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