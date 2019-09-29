<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户双费率管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		var checkMrch = function() {
			var merchantId=$.trim($("#merchantId").val());
			var cardBin=$.trim($("#cardBin").val());
			if(merchantId!=""&&cardBin!=""){
				$.ajax({
					url : '${pageContext.request.contextPath}/cortexs/merchantDoubleRate/checkMrch',
					type : "post",
					data : {
						merchantId : merchantId,
						cardBin : cardBin
					},
					dataType : "text",
					success : function(data) {
						if(data=='false'){
							alert("该商户号该卡BIN已设置费率，不能重复设置");
							$("#cardBin").focus();
						}
					}
				})
			}
		};		

		var getMrchList = function() {
			var merchantId=$.trim($("#merchantIdLike").val());
			if(merchantId!=""){
				$.ajax({
					url : '${pageContext.request.contextPath}/cortexs/merchantDoubleRate/getMrchList',
					type : "post",
					data : {
						merchantId : merchantId
					},
					dataType : "json",
					success : function(data) {
						//$('#merchantId').attr('value',''); 		
						$("#merchantId option").remove();
						$("#merchantId").append("<option value=''>--请选择--</option>");	
						if (data.length != 0) {
							$.each(data, function(i, info) {
								$("#merchantId").append(
									"<option title='"+info.mrchno+"' value='"+info.mrchno+"'>" + info.mrchtName+"("+info.mrchno+")" + "</option>");
							});
						}
					}
				})
			}
		};		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${pageContext.request.contextPath}/cortexs/merchantDoubleRate/">商户双费率列表</a></li>
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/merchantDoubleRate/form?id=${merchantDoubleRate.id}">商户双费率<shiro:hasPermission name="cortexs:merchantDoubleRate:edit">${not empty merchantDoubleRate.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cortexs:merchantDoubleRate:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="merchantDoubleRate" action="${pageContext.request.contextPath}/cortexs/merchantDoubleRate/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">商户编号：</label>
			<div class="controls">
				<form:input path="merchantIdLike" htmlEscape="false" maxlength="15" class="input-xlarge"  onkeyup="this.value=this.value.replace(/\D/g,'')" onblur="getMrchList();"/>
				<form:select path="merchantId" class="input-xlarge required">
					<form:option value="" label="--请选择--"/>
					<form:options items="${merchantList}" itemLabel="mrchtName" itemValue="mrchno" htmlEscape="false"/>
				</form:select>	
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">卡BIN：</label>
			<div class="controls">
				<form:select path="cardBin" class="input-xlarge required" onchange="checkMrch();">
					<form:option value="" label="--请选择--"/>
					<form:options items="${cardBinList}" itemLabel="cardBin" itemValue="iid" htmlEscape="false"/>
				</form:select>	
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">费率(%)：</label>
			<div class="controls">
				<form:input path="rate" htmlEscape="false" maxlength="5" class="input-xlarge required" onkeyup="value=value.replace(/[^\d\.]/g,'')"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cortexs:merchantDoubleRate:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>