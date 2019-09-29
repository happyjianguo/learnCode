<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>编辑差错流水信息</title>
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
			merNo : {required : true, number : true},merName : {required : true},termNo : {required : true, number : true},
			cardNo : {required : true, number : true},tranDate : {required : true},tranTime : {required : true},
			corebatNo : {required : false, number : true},corejonlNo : {required : false, number : true},
			coreRrn : {required : false, number : true},acqbatNo : {required : false, number : true},
			acqjonlNo : {required : false, number : true},acqRrn : {required : false, number : true},
			coretranAmt : {required : true, number : true},acqtranAmt : {required : true, number : true},
			fee : {required : true, number : true},stlAmt : {required : true, number : true}		
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

var setFee=function(){
	var termNoText= $("#termNo option:selected").text();
	var coretranAmt=$("#coretranAmt").val()	;

	if($("#termNo").val()!=""&&termNoText!=""&&coretranAmt!=""){
		var timeZone=termNoText.substr(termNoText.indexOf(":")+1,termNoText.length);
		$("#fee").val((coretranAmt*timeZone*0.01).toFixed(2));		
	}
};

var getTerminalListByMerNo = function() {
	$.ajax({
		url : '${pageContext.request.contextPath}/ClearCheckErr/getTerminalListByMerNo',
		type : "post",
		data : {
			merNo : $("#merNo").val(),
			termCode:	$("#termCode").val()
		},
		dataType : "json",
		success : function(data) {
			$("#termNo option").remove();
			$("#termNo").append("<option value=''>--请选择--</option>");
			if (data.length != 0) {
				$.each(data, function(i, info) {
					$("#termNo").append(
							"<option title='"+info.termcode+"' value="+info.termcode+">"
									+ info.termcode + "-费率:"
									+ info.timezone + "</option>");
				});
			}
		}
	})
};
</script>
</head>
<body onload="form.cardNo.focus();">
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">编辑差错流水信息</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/ClearCheckErr/edit" method="post">
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">商户编号：</td>
									<td style="text-align: left;">
										${info.merNo}
										<input type="hidden" id="id" name="id" class="inputext_style" value="${info.id}" />
										<input type="hidden" id="merNo" name="merNo" class="inputext_style" value="${info.merNo}" />
									</td>
									<td style="text-align: left;">商户名称：</td>
									<td style="text-align: left;">
										${info.merName}								
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">卡号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="cardNo" name="cardNo"  maxlength="19" 
										onkeyup="this.value=this.value.replace(/\D/g,'')" value="${info.cardNo}"/>								
									</td>
									<td style="text-align: left;">终端号（费率单位%）：</td>
									<td style="text-align: left;">	
										<input class="inputext_style" id="termCode" name="termCode"  maxlength="16" 
										onkeyup="this.value=this.value.replace(/\D/g,'')" onblur="getTerminalListByMerNo();"/>											
										<select id="termNo" name="termNo" class="inputext_style" onchange="setFee();" value="${info.termNo}">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${termPosList}"> 
												<option value="${model.termcode}" <c:if test="${model.termcode eq info.termNo}">selected="selected"</c:if>>
													${model.termcode}-费率:${model.timezone}
												</option>
											</c:forEach>
										</select>											
																
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">交易日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})" 
											name="tranDate" id="tranDate" maxlength="8" value="${info.tranDate}"/>
									</td>
									<td style="text-align: left;">交易时间：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'HHmmss',isShowClear:false,readOnly:true})" 
											name="tranTime" id="tranTime" maxlength="6" value="${info.tranTime}"/>
									</td>
								</tr>	
								<tr>
									<td style="text-align: left;">交易金额（元）：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="coretranAmt" name="coretranAmt"  maxlength="20" value="${info.coretranAmt}" onblur="setFee();"/>								
									</td>
									<td style="text-align: left;">交易手续费（元）：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="fee" name="fee"  maxlength="20" value="${info.fee}" readonly="true"/>								
									</td>
								</tr>																
								<tr>
									<td style="text-align: left;">交易类型：</td>
									<td style="text-align: left;">
										<select id="tranType" name="tranType" class="inputext_style" value="${info.tranType}">
											<c:forEach var="model" items="${fsTranTypeList}"> 
												<option value="${model.paramValue}" <c:if test="${model.paramValue eq info.tranType}">selected="selected"</c:if>>
													${model.paramName}
												</option>
											</c:forEach>
										</select>																
									</td>
									<td style="text-align: left;">卡核心批次号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="corebatNo" name="corebatNo"  maxlength="20" 
										onkeyup="this.value=this.value.replace(/\D/g,'')" value="${info.corebatNo}"/>
									</td>
								</tr>	
					
								<tr>
									<td style="text-align: left;">卡核心交易流水号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="corejonlNo" name="corejonlNo"  maxlength="20"
										onkeyup="this.value=this.value.replace(/\D/g,'')" value="${info.corejonlNo}"/>
									</td>								
									<td style="text-align: left;">卡核心参考号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="coreRrn" name="coreRrn"  maxlength="12"
										onkeyup="this.value=this.value.replace(/\D/g,'')" value="${info.coreRrn}"/>
									</td>									
								</tr>								
								<tr>
									<td style="text-align: left;">收单批次号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="acqbatNo" name="acqbatNo"  maxlength="20" 
										onkeyup="this.value=this.value.replace(/\D/g,'')" value="${info.acqbatNo}"/>
									</td>
									<td style="text-align: left;">收单交易流水号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="acqjonlNo" name="acqjonlNo"  maxlength="20" 
										onkeyup="this.value=this.value.replace(/\D/g,'')" value="${info.acqjonlNo}"/>

									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">收单参考号</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="acqRrn" name="acqRrn"  maxlength="12"
										onkeyup="this.value=this.value.replace(/\D/g,'')" value="${info.acqRrn}"/>
									</td>
									<td style="text-align: left;">对账标识：</td>
									<td style="text-align: left;">
										<select id="chkFlag" name="chkFlag" class="inputext_style">									
											<option value="2" <c:if test="${info.chkFlag eq '2'}">selected="selected"</c:if>>卡核心多账</option>
											<option value="3" <c:if test="${info.chkFlag eq '3'}">selected="selected"</c:if>>卡核心少账</option>	
											<option value="4" <c:if test="${info.chkFlag eq '4'}">selected="selected"</c:if>>金额不符</option>																																
										</select>											
									</td>									
								</tr>
								<tr>
									<td style="text-align: left;">借贷标识：</td>
									<td style="text-align: left;">
										<select id="dcFlag" name="dcFlag" class="inputext_style">									
											<option value="D" <c:if test="${info.dcFlag eq 'D'}">selected="selected"</c:if>>借记</option>
											<option value="C" <c:if test="${info.dcFlag eq 'C'}">selected="selected"</c:if>>贷记</option>
										</select>																				
									</td>
									<td style="text-align: left;">中石化消费场景：</td>
									<td style="text-align: left;">
										<select id="consumeType" name="consumeType" class="inputext_style">	
											<option value="">--请选择--</option>																		
											<option value="0" <c:if test="${info.consumeType eq '90'}">selected="selected"</c:if>>购物</option>
											<option value="1" <c:if test="${info.consumeType eq '91'}">selected="selected"</c:if>>加油</option>										
										</select>									
									</td>									
								</tr>	
								<tr>
									<td style="text-align: left;">备注:</td>
									<td style="text-align: left;">	
										<textarea rows="5" cols="25" id="comments" name="comments" maxlength="50">${info.comments}</textarea>
									</td>	
									<td style="text-align: left;"></td>
									<td style="text-align: left;">																			
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