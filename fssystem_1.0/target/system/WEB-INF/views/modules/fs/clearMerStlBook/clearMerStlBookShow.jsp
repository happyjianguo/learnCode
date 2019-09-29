<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>结算初表信息</title>
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
					<div class="sh_title">查看结算初表信息</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/ClearMerStlBook/getList" method="post">
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
									<td style="text-align: left;">父商户编号：</td>
									<td style="text-align: left;">
										${info.fmrchNo}
									</td>
									<td style="text-align: left;">父商户名称：</td>
									<td style="text-align: left;">
										${info.fmrName}
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">生成日期：</td>
									<td style="text-align: left;">
										${info.genDt}
									</td>
									<td style="text-align: left;">帐期：</td>
									<td style="text-align: left;">
										${info.startStlDate}-${info.endStlDate}
									</td>
								</tr>			
								<tr>
									<td style="text-align: left;">消费场景：</td>
									<td style="text-align: left;">
										${info.sceneName}
									</td>
									<td style="text-align: left;">结算标识：</td>
									<td style="text-align: left;">
										<select id="finalStlFlag" name="finalStlFlag" class="inputext_style" disabled="true">									
											<option value="0" <c:if test="${info.finalStlFlag eq '0'}">selected="selected"</c:if>>未结算</option>
											<option value="1" <c:if test="${info.finalStlFlag eq '1'}">selected="selected"</c:if>>已结算</option>	
										</select>											
									</td>
								</tr>									
								<tr>
									<td style="text-align: left;">消费金额（元）：</td>
									<td style="text-align: left;">
										${info.consumAmt}
									</td>
									<td style="text-align: left;">消费笔数：</td>
									<td style="text-align: left;">
										${info.consumNum}
									</td>
								</tr>	
								<tr>
									<td style="text-align: left;">手续费费率（%） ：</td>
									<td style="text-align: left;">
										${info.feeOrder}
									</td>									
									<td style="text-align: left;">手续费（元） ：</td>
									<td style="text-align: left;">
										${info.fee}
									</td>								
								</tr>																
								<tr>
									<td style="text-align: left;">净额（元） ：</td>
									<td style="text-align: left;">
										${info.netAmt}
									</td>
									<td style="text-align: left;">实际结算金额（元）：</td>
									<td style="text-align: left;">
										${info.tranAmt}
									</td>									
								</tr>
								<tr>
									<td style="text-align: left;">手续费是否单独结算：</td>
									<td style="text-align: left;">
										<select id="feestlType" name="feestlType" class="inputext_style" disabled="true">									
											<option value="0" <c:if test="${info.feestlType eq '0'}">selected="selected"</c:if>>否</option>
											<option value="1" <c:if test="${info.feestlType eq '1'}">selected="selected"</c:if>>是</option>	
										</select>																	
									</td>
									<td style="text-align: left;">状态：</td>
									<td style="text-align: left;">
										<select id="status" name="status" class="inputext_style" disabled="true">									
											<option value="0" <c:if test="${info.status eq '0'}">selected="selected"</c:if>>初登记</option>
											<option value="1" <c:if test="${info.status eq '1'}">selected="selected"</c:if>>已记账</option>
											<option value="2" <c:if test="${info.status eq '2'}">selected="selected"</c:if>>已结算</option>
											<option value="3" <c:if test="${info.status eq '3'}">selected="selected"</c:if>>删除</option>												
										</select>										
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">结算账户名:</td>
									<td style="text-align: left;">
										${info.accName}									
									</td>								
									<td style="text-align: left;">结算银行名称 ：</td>
									<td style="text-align: left;">
										${info.bankName}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">开户行:</td>
									<td style="text-align: left;">
										${info.branchName}									
									</td>								
									<td style="text-align: left;">联行号 ：</td>
									<td style="text-align: left;">
										${info.unionNo}
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">是否同城:</td>
									<td style="text-align: left;">
										<select id="beijingFlag" name="beijingFlag" class="inputext_style" disabled="true">									
											<option value="0" <c:if test="${info.beijingFlag eq '0'}">selected="selected"</c:if>>否</option>
											<option value="1" <c:if test="${info.beijingFlag eq '1'}">selected="selected"</c:if>>是</option>	
										</select>										
									</td>								
									<td style="text-align: left;">结算单是否按照消费场景分组 ：</td>
									<td style="text-align: left;">
										<select id="isConsumpCategory" name="isConsumpCategory" class="inputext_style" disabled="true">									
											<option value="0" <c:if test="${info.isConsumpCategory eq '0'}">selected="selected"</c:if>>否</option>
											<option value="1" <c:if test="${info.isConsumpCategory eq '1'}">selected="selected"</c:if>>是</option>	
										</select>											
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">是否合并打款:</td>
									<td style="text-align: left;">
										<select id="amtConsumpCategory" name="amtConsumpCategory" class="inputext_style" disabled="true">									
											<option value="0" <c:if test="${info.amtConsumpCategory eq '0'}">selected="selected"</c:if>>否</option>
											<option value="1" <c:if test="${info.amtConsumpCategory eq '1'}">selected="selected"</c:if>>是</option>	
										</select>										
									</td>								
									<td style="text-align: left;">结算ID ：</td>
									<td style="text-align: left;">
										${info.finalStlId}
									</td>
								</tr>									
								<tr>
									<td style="text-align: left;">结算日期</td>
									<td style="text-align: left;">${info.stlDate}</td>									
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