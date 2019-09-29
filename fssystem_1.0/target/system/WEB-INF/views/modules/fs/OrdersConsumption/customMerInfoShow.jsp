<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消费订单下单</title>
	<meta name="decorator" content="default"/>

<script type="text/javascript">
	$(document).ready(function() {
		disableOrNot();
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
				var merNo = $("#merNo").val();
				var companyName = $("#companyName").val();
				if(!Boolean(trim(merNo))){
					if(!Boolean(trim(companyName))){
						alert("商户号和公司名称必填一个");
						return false;
					}
				}
				var tranStartDate = $("#tranStartDate").val();
				if(!Boolean(trim(tranStartDate))){
					alert("消费开始日期不能为空");
					return false;
				}
				var tranEndDate = $("#tranEndDate").val();
				if(!Boolean(trim(tranEndDate))){
					alert("消费结束日期不能为空");
					return false;
				}
				
				if((getDate(tranStartDate)-getDate(tranEndDate)>0)){
				    alert("消费结束日期不能小于消费开始日期");
				    return false;
				}
				
				var orderDate = $("#orderDate").val();
				if(!Boolean(trim(orderDate))){
					alert("下单日期不能为空");
					return false;
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

	function disableOrNot(){
		var merNo = $("#merNo").val();
		var companyName = $("#companyName").val();
		if(!Boolean(trim(merNo))){
			if(!Boolean(trim(companyName))){
				$("#merNo").removeAttr("disabled");
				$("#companyName").removeAttr("disabled");
			}else{
				$("#merNo").attr("disabled",true);
				$("#companyName").removeAttr("disabled");
			}
		}else{
			if(!Boolean(trim(companyName))){
				$("#merNo").removeAttr("disabled");
				$("#companyName").attr("disabled",true);
			}
		}
	}
	
	//删除左右两端的空格
	function trim(str){ 
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
	
	function getDate(date){
		 var dates = date.split("-");
		 var dateReturn = '';
		 
		 for(var i=0; i<dates.length; i++){
		  dateReturn+=dates[i];
		 }
		 return dateReturn;
	}
</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">消费订单下单</div>
					<div class="sh_cm">
						<form id="form" action="${pageContext.request.contextPath}/CustomMerInfo/save" method="post">
							
							<input type="hidden" name="batchId" value="${info.batchId}"/>
							
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">商户号：</td>
									<td style="text-align: left;">
										<input type="text" name="merNo" id="merNo" value="${info.merNo}" disabled="disabled" maxlength="15" onblur="disableOrNot();"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">公司名称：</td>
									<td style="text-align: left;">
										<input type="text" name="companyName" id="companyName" value="${info.companyName}" disabled="disabled" maxlength="200" onblur="disableOrNot();"/>
									</td>
								</tr>	
								<tr>
									<td style="text-align: left;">消费开始日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${info.tranStartDate}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
												name="tranStartDate" id="tranStartDate" />
												<span><font color="red">*</font> </span>
									</td>
								</tr>	
								<tr>
									<td style="text-align: left;">消费结束日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${info.tranEndDate}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
												name="tranEndDate" id="tranEndDate" />
												<span><font color="red">*</font> </span>
									</td>
								</tr>	
								<tr>
									<td style="text-align: left;">下单日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" value="${info.orderDate}" readonly="readonly"
												onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true,readOnly:true})"
												name="orderDate" id="orderDate" />
										<span><font color="red">*</font> </span>
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