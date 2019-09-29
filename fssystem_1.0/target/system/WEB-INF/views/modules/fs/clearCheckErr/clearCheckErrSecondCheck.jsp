<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>第二次审核差错流水信息</title>
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
							$("#form", window.parent.document).attr("action","${pageContext.request.contextPath}/ClearCheckErr/getList");							
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
<body onload="form.comments2.focus();">
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">第二次审核差错流水信息</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/ClearCheckErr/secondCheck" method="post">
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
									<td style="text-align: left;">卡号：</td>
									<td style="text-align: left;">
										${info.cardNo}							
									</td>
									<td style="text-align: left;">终端号：</td>
									<td style="text-align: left;">
										${info.termNo}							
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">交易日期：</td>
									<td style="text-align: left;">
										${info.tranDate}
									</td>
									<td style="text-align: left;">交易时间：</td>
									<td style="text-align: left;">
										${info.tranTime}
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">交易类型：</td>
									<td style="text-align: left;">
										${info.tranTypeDesc}																													
									</td>
									<td style="text-align: left;">卡核心批次号：</td>
									<td style="text-align: left;">
										${info.corebatNo}
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">卡核心交易流水号：</td>
									<td style="text-align: left;">
										${info.corejonlNo}
									</td>								
									<td style="text-align: left;">卡核心参考号：</td>
									<td style="text-align: left;">
										${info.coreRrn}
									</td>									
								</tr>								
								<tr>
									<td style="text-align: left;">卡核心交易金额（元）：</td>
									<td style="text-align: left;">
										${info.coretranAmt}								
									</td>
									<td style="text-align: left;">收单批次号：</td>
									<td style="text-align: left;">
										${info.acqbatNo}
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">收单交易流水号：</td>
									<td style="text-align: left;">
										${info.acqjonlNo}
									</td>
									<td style="text-align: left;">收单参考号</td>
									<td style="text-align: left;">
										${info.acqRrn}
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">收单交易金额（元）：</td>
									<td style="text-align: left;">
										${info.acqtranAmt}								
									</td>
									<td style="text-align: left;">交易手续费（元）：</td>
									<td style="text-align: left;">
										${info.fee}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">对账标识：</td>
									<td style="text-align: left;">
										<select id="chkFlag" name="chkFlag" class="inputext_style" disabled="true">									
											<option value="2" <c:if test="${info.chkFlag eq '2'}">selected="selected"</c:if>>卡核心多账</option>
											<option value="3" <c:if test="${info.chkFlag eq '3'}">selected="selected"</c:if>>卡核心少账</option>	
											<option value="4" <c:if test="${info.chkFlag eq '4'}">selected="selected"</c:if>>金额不符</option>																																
										</select>											
									</td>
									<td style="text-align: left;">借贷标识：</td>
									<td style="text-align: left;">
										<select id="chkFlag" name="chkFlag" class="inputext_style" disabled="true">									
											<option value="D" <c:if test="${info.chkFlag eq 'D'}">selected="selected"</c:if>>借记</option>
											<option value="C" <c:if test="${info.chkFlag eq 'C'}">selected="selected"</c:if>>贷记</option>
										</select>																				
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">结算标识：</td>
									<td style="text-align: left;">
										<select id="stlFlag" name="stlFlag" class="inputext_style" disabled="true">									
											<option value="0" <c:if test="${info.stlFlag eq '0'}">selected="selected"</c:if>>初登记</option>
											<option value="1" <c:if test="${info.stlFlag eq '1'}">selected="selected"</c:if>>需要结算</option>	
											<option value="2" <c:if test="${info.stlFlag eq '2'}">selected="selected"</c:if>>无需结算</option>																																
										</select>											
									</td>
									<td style="text-align: left;">调帐标识：</td>
									<td style="text-align: left;">
										<select id="adjustFlag" name="adjustFlag" class="inputext_style" disabled="true">									
											<option value="0" <c:if test="${info.adjustFlag eq '0'}">selected="selected"</c:if>>初登记</option>
											<option value="1" <c:if test="${info.adjustFlag eq '1'}">selected="selected"</c:if>>需要调账</option>	
											<option value="2" <c:if test="${info.adjustFlag eq '2'}">selected="selected"</c:if>>无需调账</option>	
										</select>																				
									</td>
								</tr>																
								<tr>
									<td style="text-align: left;">来源标识</td>									
									<td style="text-align: left;">
										<select id="genType" name="genType" class="inputext_style" disabled="true">									
											<option value="0" <c:if test="${info.genType eq '0'}">selected="selected"</c:if>>系统录入</option>
											<option value="1" <c:if test="${info.genType eq '1'}">selected="selected"</c:if>>手工录入</option>										
										</select>									
									</td>
									<td style="text-align: left;">中石化消费场景：</td>
									<td style="text-align: left;">
										<select id="consumeType" name="consumeType" class="inputext_style" disabled="true">
											<option value="" <c:if test="${info.consumeType eq ''}">selected="selected"</c:if>></option>																			
											<option value="0" <c:if test="${info.consumeType eq '90'}">selected="selected"</c:if>>购物</option>
											<option value="1" <c:if test="${info.consumeType eq '91'}">selected="selected"</c:if>>加油</option>										
										</select>									
									</td>									
								</tr>	
								<tr>
									<td style="text-align: left;">一审人员:</td>
									<td style="text-align: left;">
										${info.operor1}
									</td>
									<td style="text-align: left;">一审时间：</td>
									<td style="text-align: left;">
										${info.operDt1}
									</td>
								</tr>							
								<tr>
									<td style="text-align: left;">备注:</td>
									<td style="text-align: left;">
										<textarea rows="5" cols="25" readonly="true">${info.comments}</textarea>										
									</td>
									<td style="text-align: left;">一审备注:</td>
									<td style="text-align: left;">
										<textarea rows="5" cols="25" readonly="true">${info.comments1}</textarea>										
									</td>
								</tr>
								<tr>								
									<td style="text-align: left;">二审备注:</td>
									<td style="text-align: left;" colspan="3">	
										<textarea rows="5" cols="25" id="comments2" name="comments2" maxlength="50"></textarea>
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