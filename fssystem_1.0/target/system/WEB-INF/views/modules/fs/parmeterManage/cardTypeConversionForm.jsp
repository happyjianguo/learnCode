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
					<div class="sh_title">卡类型设置</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/cardTypeConversion/save" method="post">
							<input type="hidden" name="flag" value="${flag}"/>
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">卡类型ID：</td>
									<td style="text-align: left;">
										<c:choose>
											<c:when test="${flag eq '1'}">
												<input type="text" name="cardtype" id="cardtype" readonly="readonly" value="${info.cardtype}" 
													maxlength="3" required onkeyup="value=value.replace(/[^\d]/g,'')"/>
											</c:when>
											<c:otherwise>
												<input type="text" name="cardtype" id="cardtype" value="${info.cardtype}" 
												maxlength="3" required onkeyup="value=value.replace(/[^\d]/g,'')"/>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>	
								<tr>
									<td style="text-align: left;">卡类型名称：</td>
									<td style="text-align: left;">
										<input type="text" name="cardtypename" id="cardtypename" value="${info.cardtypename}" 
											maxlength="30" required/>
									</td>
								</tr>	
								<tr>
									<td style="text-align: left;">卡类型编码ID：</td>
									<td style="text-align: left;">
										<input type="text" name="cardtypeId" id="cardtypeId" value="${info.cardtypeId}" 
										maxlength="3" required onkeyup="value=value.replace(/[^\d]/g,'')"/>
									</td>
								</tr>	
								<tr>
									<td style="text-align: left;">卡类型编码名称：</td>
									<td style="text-align: left;">
										<input type="text" name="cardtypeIdName" id="cardtypeIdName" value="${info.cardtypeIdName}" 
											maxlength="30" required/>
									</td>
								</tr>	
								<tr>
									<td style="text-align: center;" colspan="6">
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