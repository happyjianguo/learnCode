<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>手动调账设置</title>
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
			//$("#submit").attr('disabled',true);
			
			var voidTraceNo = $("#voidTraceNo").val();
			var voidSysDate = $("#voidSysDate").val();
			if((voidTraceNo != null && voidTraceNo != "")
					&& (voidSysDate == null || voidSysDate == "")){
				layer.alert("原交易流水号、原交易日期需同时存在");
				return false;
			}
			if((voidTraceNo == null || voidTraceNo == "")
					&& (voidSysDate != null && voidSysDate != "")){
				layer.alert("原交易流水号、原交易日期需同时存在");
				return false;
			}
			
			var upFile = $('#file')[0].files[0];
			if(upFile != null && upFile != ''){
				//上传
				if(!viewImg()){
					return false;
				}
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

function getMerName(merNo){
	if(merNo == null || merNo == ''){
		return;
	} 
	if(merNo.length > 14){
		$.ajax({
			url : '${pageContext.request.contextPath}/ChargeBalance/getMerName?random='+ Math.random(),
			type : "post",
			data : {
				merNo:merNo
			},
			success : function(data) {
				if (data.result == '0') {
					$("#merName").val(data.resultMsg);
					resetTLogInfo();
				} else {
					$("#merName").val("");
					layer.alert("获取商户名称失败。");
				}
			}
		});
	} else {
		layer.alert("商户号不符合规范");
	}
}

function resetTLogInfo(){
	var voidTraceNo = $("#voidTraceNo").val();
	var voidSysDate = $("#voidSysDate").val();
	
	var merNo = $("#merNo").val();
	var terminalId = $("#terminalId").val();
	var cardNo = $("#cardNo").val();
	var flagT = false;
	if(merNo != null && merNo != ''
			&& terminalId != null && terminalId != ''){
		//商户、终端存在，判断该终端是否位于商户下
		$.ajax({
			url : '${pageContext.request.contextPath}/ChargeBalance/verifyMer?random='+ Math.random(),
			type : "post",
			async: false,
			data : {
				merNo:merNo,terminalId:terminalId
			},
			success : function(data) {
				if (data.result != '0') {
					$("#terminalId").val("");
					layer.alert("该终端不存在，或非该商户下的终端");
					flagT = true;
				}
			}
		});
	}
	if(flagT) {
		return;
	}
	
	if(voidTraceNo != null && voidTraceNo != ''
			&& voidSysDate != null && voidSysDate != ''){
		if(merNo == null || merNo == ''
			|| terminalId == null || terminalId == ''
			|| cardNo == null || cardNo == ''){
		} else {
			getTLogInfo()
		}
	}
}

function getTLogInfo(){
	var voidTraceNo = $("#voidTraceNo").val();
	var voidSysDate = $("#voidSysDate").val();
	if(voidTraceNo != null && voidTraceNo != ''
			&& voidSysDate != null && voidSysDate != ''){
		var merNo = $("#merNo").val();
		var terminalId = $("#terminalId").val();
		var cardNo = $("#cardNo").val();
		if(merNo == null || merNo == ''
			|| terminalId == null || terminalId == ''
			|| cardNo == null || cardNo == ''){
			layer.alert("请填写商户号、终端号以及卡号");
			return;
		}
		$.ajax({
			url : '${pageContext.request.contextPath}/ChargeBalance/getTLogInfo?random='+ Math.random(),
			type : "post",
			data : {
				merNo:merNo,terminalId:terminalId,cardNo:cardNo,
				voidTraceNo:voidTraceNo,voidSysDate:voidSysDate
			},
			success : function(data) {
				if (data.result == '0') {
					$("#voidTraceCode").val(data.resultMsg.txncode);
					$("#voidSysTime").val(data.resultMsg.transactiontime);
					$("#voidAmt").val(data.resultMsg.amttxn);
					$("#voidRespCode").val(data.resultMsg.txnstatus);
					verifyAmt();
				} else {
					layer.alert(data.resultMsg);
				}
			}
		});
	} 
}

function verifyAmt(){
	var voidAmt = $("#voidAmt").val();
	var chargeAmt = $("#chargeAmt").val();
	var chargeFlag = $("#chargeFlag").val();
	if(chargeFlag == '0' && voidAmt != null && voidAmt != ''
		&& chargeAmt != null && chargeAmt != ''){
		var shortSub = voidAmt - chargeAmt;
		if(shortSub < 0){
			layer.alert("调账金额不能大于原交易金额");
			$("#chargeAmt").val("");
		}
	}
}

function viewImg() {
    /* if(f == null || f ==undefined || f == ''){
        return false;
    } */
    /* if(!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f)) {
        alert("类型必须是图片(.png|jpg|bmp|gif|PNG|JPG|BMP|GIF)");
        $(obj).val('');
        return false;
    } */
    /* $.each($(obj)[0].files,function(i,file){
        data.append('file', file);
    }); */
    var flag = false;
    var formData = new FormData();
    formData.append('file', $('#file')[0].files[0]);
    $.ajax({
        type: "POST",
        url: '${pageContext.request.contextPath}/ChargeBalance/imgUpload?random='+ Math.random(),
        data: formData,
        cache: false,
        contentType: false,    //不可缺
        processData: false,    //不可缺
        dataType:"json",
        async: false,
        success: function(data) {
            if (data.result == '0') {
            	$("#picRoute").val(data.resultMsg);			//保存地址     
            	//$("#imgSrc").attr("src", data.resultMsg);	//显示图片   
            	flag = true;
			} else {
			    var idId = $("#idId").val();
			    if(idId == null || idId == ''){
					$("#picRoute").val("");	
					$("#imgSrc").attr("src", "");
			    }
				layer.alert(data.resultMsg);
			}
        }
        //error: function(XMLHttpRequest, textStatus, errorThrown) {
        //    alert("上传失败，请检查网络后重试");
        //}
    });
    return flag;   
}
</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">手动调账设置</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/ChargeBalance/save" method="post"
							enctype="multipart/form-data" >
							<input type="hidden" id="idId" name="id" value="${info.id}"/>
							<input type="hidden" id="voidTraceCode" name="voidTraceCode" value="${info.voidTraceCode}"/>
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">商户号：</td>
									<td style="text-align: left;">
										<input type="text" id="merNo" name="merNo" value="${info.merNo}" maxlength="15" onblur="getMerName(this.value);" required="required"/>
										<span class="help-inline"><font color="red">*</font> </span>
									</td>
									<td style="text-align: left;">商户名称：</td>
									<td style="text-align: left;">
										<input type="text" id="merName" name="merName" value="${info.merName}" readonly="readonly" required="required"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">终端号：</td>
									<td style="text-align: left;">
										<input type="text" id="terminalId" name="terminalId" value="${info.terminalId}" maxlength="8" onblur="resetTLogInfo();" required="required"/>
										<span class="help-inline"><font color="red">*</font> </span>
									</td>
									<td style="text-align: left;">卡号：</td>
									<td style="text-align: left;">
										<input type="text" id="cardNo" name="cardNo" value="${info.cardNo}" maxlength="16" onblur="resetTLogInfo();" required="required"/>
										<span class="help-inline"><font color="red">*</font> </span>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">原交易流水号：</td>
									<td style="text-align: left;">
										<input type="text" id="voidTraceNo" name="voidTraceNo" value="${info.voidTraceNo}" maxlength="6" onblur="getTLogInfo();"/>
									</td>
									<td style="text-align: left;">原交易日期：</td>
									<td style="text-align: left;">
										<input type="text" id="voidSysDate" name="voidSysDate" value="${info.voidSysDate}" maxlength="8" onblur="getTLogInfo();"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">调账金额：</td>
									<td style="text-align: left;" width:90%;" colspan="3" rowspan="1">
										<select id="chargeFlag" name="chargeFlag" class="input-mini" style="border: 1px solid #cacaca;">
											<option value="1" <c:if test="${info.chargeFlag == '1' }">selected="selected"</c:if>>加钱</option>
											<option value="0" <c:if test="${info.chargeFlag == '0' }">selected="selected"</c:if>>减钱</option>
										</select>
										-
										<input type="text" id="chargeAmt" name="chargeAmt" value="${info.chargeAmt}" maxlength="10"
											required="required" onkeyup="this.value=this.value.replace(/[^\d.]/g,'')" 
											onblur="verifyAmt();"/>
										<span class="help-inline"><font color="red">*</font> </span>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">备注：</td>
									<td style="text-align: left;">
										<textarea rows="2" cols="4" id="remarks" name="remarks" maxlength="60">${info.remarks}</textarea>
									</td>
									<td style="text-align: left;">上传图片：</td>
									<td style="text-align: left;" colspan="3">
										<input id="file" type="file" name="file" accept="image/*" >
										<input type="hidden" id="picRoute" name="picRoute" value="${info.picRoute}"/>
									</td>
								</tr>
								<c:if test="${info.picRoute != null}">
									<tr>
										<td style="text-align: left;width:10%;">图片预览：</td>
										<td style="text-align: left;width:90%;" colspan="3" rowspan="1">
											<div id="imgId" style="height:200px; width:100%; overflow:auto" >
												<img alt="" id="imgSrc" name="imgSrc" src="${info.picRoute}" style="overflow:scroll;border: 1px solid #cacaca;" />
										   	</div>
										</td>
									</tr>
								</c:if>
							</table>
							<div class="sh_title">原交易信息：</div>
							<div class="sh_cm">
								<table width="100%" border="0" class="tab">
									<tr>
										<td style="text-align: left;">原交易时间：</td>
										<td style="text-align: left;">
											<input type="text" id="voidSysTime" name="voidSysTime" value="${info.voidSysTime}" readonly="readonly" />
										</td>
										<td style="text-align: left;">原交易金额：</td>
										<td style="text-align: left;">
											<input type="text" id="voidAmt" name="voidAmt" value="${info.voidAmt}" readonly="readonly" />
										</td>
									</tr>
									<tr>
										<td style="text-align: left;">原交易状态：</td>
										<td style="text-align: left;">
											<input type="text" id="voidRespCode" name="voidRespCode" value="${info.voidRespCode}" readonly="readonly" />
										</td>
									</tr>
									<tr>
										<td style="text-align: center;" colspan="4">
											<input type="submit" id="submit" value="提交" class="button" />
											<input type="button" id="closeBtn" value="关闭" class="button" />										
										</td>
									</tr>
								</table>
							</div>	
						</form>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>