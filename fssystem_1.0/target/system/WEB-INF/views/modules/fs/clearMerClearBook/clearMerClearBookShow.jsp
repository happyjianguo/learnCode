<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户日汇总信息</title>
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
					<div class="sh_title">查看商户日汇总信息</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/ClearMerClearBook/getList" method="post">
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">商户编号：</td>
									<td style="text-align: left;">
										${info.merNo}
										<input type="hidden" id="srcId" name="srcId" class="inputext_style" value="${info.srcId}" />	
									</td>
									<td style="text-align: left;">商户名称：</td>
									<td style="text-align: left;">
										${info.merName}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">父商户编号：</td>
									<td style="text-align: left;">
										${info.fmrchNo}
									</td>
									<td style="text-align: left;">父商户名称：</td>
									<td style="text-align: left;">
										${info.fmrchName}
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">交易日期：</td>
									<td style="text-align: left;">
										${info.tranDate}
									</td>
									<td style="text-align: left;">消费场景：</td>
									<td style="text-align: left;">
										${info.sceneName}
									</td>
								</tr>			
								<tr>
									<td style="text-align: left;">卡类型名称：</td>
									<td style="text-align: left;">
										${info.cardTypeName}
									</td>
									<td style="text-align: left;">结算标识：</td>
									<td style="text-align: left;">
										<select id="stlFlag" name="stlFlag" class="inputext_style" disabled="true">									
											<option value="0" <c:if test="${info.stlFlag eq '0'}">selected="selected"</c:if>>初登记</option>
											<option value="1" <c:if test="${info.stlFlag eq '1'}">selected="selected"</c:if>>无需结算</option>	
											<option value="2" <c:if test="${info.stlFlag eq '2'}">selected="selected"</c:if>>已结算</option>																																
										</select>											
									</td>
								</tr>									
								<tr>
									<td style="text-align: left;">消费总笔数：</td>
									<td style="text-align: left;">
										${info.tranNum}
									</td>
									<td style="text-align: left;">消费总金额（元）：</td>
									<td style="text-align: left;">
										${info.tranAmt}
									</td>
								</tr>									
								<tr>
									<td style="text-align: left;">退货总笔数：</td>
									<td style="text-align: left;">
										${info.refNum}
									</td>
									<td style="text-align: left;">退货总金额（元）：</td>
									<td style="text-align: left;">
										${info.refAmt}
									</td>
								</tr>																	
								<tr>
									<td style="text-align: left;">净额（元） ：</td>
									<td style="text-align: left;">
										${info.netAmt}
									</td>								
									<td style="text-align: left;">结算金额（元） ：</td>
									<td style="text-align: left;">
										${info.stlAmt}
									</td>									
								</tr>								
								<tr>
									<td style="text-align: left;">交易手续费（元）：</td>
									<td style="text-align: left;">
										${info.fee}								
									</td>
									<td style="text-align: left;">手续费是否单独结算:</td>
									<td style="text-align: left;">
										<c:if test="${info.feeType=='0'}">否</c:if>										
										<c:if test="${info.feeType=='1'}">是</c:if>										
									</td>	
								</tr>								
								<tr>
									<td style="text-align: left;">结算日期 ：</td>
									<td style="text-align: left;">
										${info.stlDate}
									</td>								
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