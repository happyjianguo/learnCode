<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>手续费信息编辑</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
$(document).ready(function() {
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	$('#closeBtn').click(function() {
		parent.layer.close(index);
		return ;
	});
	$("#form").validate({
		onsubmit : true,// 是否在提交是验证
		onfocusout : false,// 是否在获取焦点时验证
		onkeyup : false,// 是否在敲击键盘时验证
		rules : {
			expressDate : {required : true},fee:{required : true},expressNo : {required : true},officeNo : {required : true}
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
							$("#form", window.parent.document).attr("action","${pageContext.request.contextPath}/ClearFeeBook/getList");							
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
//校验是数字：一个小数点开头可为+-  
function clearNoNumOfAlert(obj){  
   
    var message="";
    if(isNaN(obj.value)){
    	layer.alert("非法字符！"); 
    	
    	return ;
    }
    
    var fee = $("#fee").val();
	var checkFee = $("#checkFee").val();
	
	var lenght=checkFee.toString().split(".")[1].length;
	
	if(lenght>2){
		layer.alert("手续费调整值保留两位小数");
		return ;
	}
	if(accAdd(fee,checkFee)<0){
		layer.alert("减少手续费值应小于手续费原始值");
		return ;
	}
    
}  
//两个数精确相加减
function accAdd(arg1, arg2) {
	var r1, r2, m, c;
	try {
		r1 = arg1.toString().split(".")[1].length;
	} catch (e) {
		layer.alert("发生异常");
		return ;
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	} catch (e) {
		layer.alert("发生异常");
		return ;
	}
	c = Math.abs(r1 - r2);
	m = Math.pow(10, Math.max(r1, r2));
	if (c > 0) {
		var cm = Math.pow(10, c);
		if (r1 > r2) {
			arg1 = Number(arg1.toString().replace(".", ""));
			arg2 = Number(arg2.toString().replace(".", "")) * cm;
		} else {
			arg1 = Number(arg1.toString().replace(".", "")) * cm;
			arg2 = Number(arg2.toString().replace(".", ""));
		}
	} else {
		arg1 = Number(arg1.toString().replace(".", ""));
		arg2 = Number(arg2.toString().replace(".", ""));
	}
	return (arg1 + arg2) / m;
}

	
</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">编辑手续费信息</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/ClearFeeBook/edit" method="post">
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
									<td style="text-align: left;">发票抬头：</td>
									<td style="text-align: left;">
										${info.headOffice}
									</td>
									<td style="text-align: left;"></td>
									<td style="text-align: left;">
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">开始帐期：</td>
									<td style="text-align: left;">
										${info.startDate}
									</td>
									<td style="text-align: left;">结束帐期：</td>
									<td style="text-align: left;">
										${info.startDate}
									</td>
								</tr>			
								<tr>
									<td style="text-align: left;">消费金额（元）：</td>
									<td style="text-align: left;">
										${info.tranAmt}
									</td>
									<td style="text-align: left;">手续费原始值（元） ：</td>
									<td style="text-align: left;">
										${info.fee}
										<input type="hidden" id="fee" name="fee" value="${info.fee}"/>
									</td>										
								</tr>
								<tr>
									<td style="text-align: left;">手续费调整值（元）：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="checkFee" name="checkFee" onblur="clearNoNumOfAlert(this)" maxlength="20" value="${info.checkFee}"/>
									</td>
									<td style="text-align: left;">实际手续费（元） ：</td>
									<td style="text-align: left;">
										${info.realFee}
									</td>										
								</tr>										
								<tr>
									<td style="text-align: left;">快递日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})" 
											name="expressDate" id="expressDate" maxlength="8" value="${info.expressDate}"/>
									</td>								
									<td style="text-align: left;">快递单号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="expressNo" name="expressNo"  maxlength="20" value="${info.expressNo}"/>								
									</td>																
								</tr>																									
								<tr>
									<td style="text-align: left;">发票号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="officeNo" name="officeNo"  maxlength="30" value="${info.officeNo}"/>								
									</td>								
									<td style="text-align: left;">备注:</td>
									<td style="text-align: left;">	
										<textarea rows="5" cols="25" id="comments" name="comments" maxlength="50">${info.comments}</textarea>
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