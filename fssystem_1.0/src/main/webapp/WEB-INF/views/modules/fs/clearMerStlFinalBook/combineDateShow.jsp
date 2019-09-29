<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节假日结算合并信息设置</title>
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
			
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			if(null == endDate || endDate == '' 
					|| null == startDate || startDate == ''){
				layer.alert("结束日期、起始日期均不能为空");
				return;
			}
			
			if((startDate - endDate) > 0){
				layer.alert("结束日期不能小于起始日期");
			    return;
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
					<div class="sh_title">节假日结算合并信息设置</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/CombineDate/save" method="post">
							<input type="hidden" name="id" value="${info.id}"/>
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">开始日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${info.startDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="startDate" id="startDate" />
									</td>
								</tr>	
								<tr>
									<td style="text-align: left;">结束日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${info.endDate}" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})" name="endDate" id="endDate" />	
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