<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>添加差错流水信息</title>
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

var checkCortexMerchantXByMerNo = function() {
	$.ajax({
		url : '${pageContext.request.contextPath}/ClearCheckErr/checkCortexMerchantXByMerNo',
		type : "post",
		data : {
			merNo : $("#merNo").val()
		},
		dataType : "text",
		success : function(data) {
			if (data!=null&&data!='') {
		      	$("#merName").val(data);
			}else{
				if($("#merNo").val()!=''){
					alert("不存在该商户号"+$("#merNo").val()+",请重新输入");
					$("#termNo option").remove();
					$("#merName").val('')
					$("#merNo").focus();	
				}
			}
		}
	})
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

var setFee=function(){
	var termNoText= $("#termNo option:selected").text();
	var coretranAmt=$("#coretranAmt").val()	;

	if($("#termNo").val()!=""&&termNoText!=""&&coretranAmt!=""){
		var timeZone=termNoText.substr(termNoText.indexOf(":")+1,termNoText.length);
		$("#fee").val((coretranAmt*timeZone*0.01).toFixed(2));		
	}
};

</script>
</head>
<body onload="form.merNo.focus();">
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">添加差错流水信息</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/ClearCheckErr/addClearCheckErr" method="post">
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">商户编号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="merNo" name="merNo"  maxlength="15"
										 onkeyup="this.value=this.value.replace(/\D/g,'')" onblur="checkCortexMerchantXByMerNo();getTerminalListByMerNo();"/>								
									</td>
									<td style="text-align: left;">商户名称：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="merName" name="merName"  maxlength="20" readonly="true"/>								
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">卡号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="cardNo" name="cardNo"  maxlength="19" onkeyup="this.value=this.value.replace(/\D/g,'')" />								
									</td>
									<td style="text-align: left;">终端号（费率单位%）：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="termCode" name="termCode"  maxlength="16" 
										onkeyup="this.value=this.value.replace(/\D/g,'')" onblur="getTerminalListByMerNo();"/>																		
										<select id="termNo" name="termNo" class="inputext_style" onchange="setFee();">
											<option value="">--请选择--</option>		
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
										<input class="inputext_style" id="coretranAmt" name="coretranAmt"  maxlength="20" onblur="setFee();"/>								
									</td>
									<td style="text-align: left;">交易手续费（元）：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="fee" name="fee"  maxlength="20" readonly="true"/>								
									</td>									
								</tr>	
								<tr>									
									<td style="text-align: left;">交易类型：</td>
									<td style="text-align: left;">		
										<select id="tranType" name="tranType" class="inputext_style">
											<c:forEach var="model" items="${fsTranTypeList}"> 
												<option value="${model.paramValue}">
													${model.paramName}
												</option>
											</c:forEach>
										</select>									
									</td>
									<td style="text-align: left;">卡核心批次号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="corebatNo" name="corebatNo"  maxlength="20" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
									</td>
								</tr>															
								<tr>
									<td style="text-align: left;">卡核心交易流水号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="corejonlNo" name="corejonlNo"  maxlength="20" onkeyup="this.value=this.value.replace(/\D/g,'')"/>								
									</td>
									<td style="text-align: left;">卡核心参考号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="coreRrn" name="coreRrn"  maxlength="12" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
									</td>																										
								</tr>								
								<tr>
									<td style="text-align: left;">收单批次号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="acqbatNo" name="acqbatNo"  maxlength="20" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
									</td>
									<td style="text-align: left;">收单交易流水号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="acqjonlNo" name="acqjonlNo"  maxlength="20" onkeyup="this.value=this.value.replace(/\D/g,'')"/>								
									</td>									
								</tr>								
								<tr>
									<td style="text-align: left;">收单参考号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="acqRrn" name="acqRrn"  maxlength="12" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
									</td>
									<td style="text-align: left;">对账标识：</td>
									<td style="text-align: left;">
										<select id="chkFlag" name="chkFlag" class="inputext_style">									
											<option value="2">卡核心多账</option>
											<option value="3">卡核心少账</option>
											<option value="4">金额不符</option>
										</select>	
									</td>																		
								</tr>								
								<tr>
									<td style="text-align: left;">借贷标识：</td>
									<td style="text-align: left;">
										<select id="dcFlag" name="dcFlag" class="inputext_style">
											<option value="D">借记</option>
											<option value="C">贷记</option>
										</select>	
									</td>
									<td style="text-align: left;">中石化消费场景：</td>
									<td style="text-align: left;">
										<select id="consumeType" name="consumeType" class="inputext_style">
											<option value="">--请选择--</option>
											<option value="90">购物</option>
											<option value="91">加油</option>
										</select>	
									</td>							
								</tr>
								<tr>
									<td style="text-align: left;">备注:</td>
									<td style="text-align: left;">	
										<textarea rows="5" cols="25" id="comments" name="comments" maxlength="50"></textarea>
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