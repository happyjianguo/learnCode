<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>卡类型来源设置</title>
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
					<div class="sh_title">卡类型来源信息设置</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/CardKindComesource/save" method="post">
							<input type="hidden" name="id" value="${info.id}"/>
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">卡类型：</td>
									<td style="text-align: left;">
										<select id="cardnumber" name="cardnumber" class="inputext_style">
											<option value="">--请选择--</option>										
											<c:forEach var="model" items="${cardTypeList}">
												<option value="${model.cardnumber}" <c:if test="${info.cardnumber eq model.cardnumber}">selected="selected"</c:if>>
													${model.cardkindname}
												</option>
											</c:forEach>
										</select>
									</td>
								</tr>	
								<tr>
									<td style="text-align: left;">数据库来源：</td>
									<td style="text-align: left;">
										<select id = "dataBaseType" name="dataBaseType" class="inputext_style">
											<option value="">--请选择--</option>
											<option value="1" <c:if test="${info.dataBaseType == '1' }">selected="selected"</c:if>>旧库1</option>
											<option value="2" <c:if test="${info.dataBaseType == '2' }">selected="selected"</c:if>>旧库2</option>
											<option value="3" <c:if test="${info.dataBaseType == '3' }">selected="selected"</c:if>>新卡库</option>
											<option value="4" <c:if test="${info.dataBaseType == '4' }">selected="selected"</c:if>>旧库3</option>
										</select>
									</td>
								</tr>	
								<tr>
									<td style="text-align: left;">卡产品：</td>
									<td style="text-align: left;">
										<input type="text" id="oldKindid" name="oldKindid" value="${info.oldKindid}" maxlength="10" />
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