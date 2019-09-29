<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>扣款费率管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var regYear = /^([1-9]|10)$/;
					var regRate = /^([1-9]\d{0,1}|100)$/;
					
					var beginyear = $("#beginyear").val();
					var rate = $("#rate").val();
					if(!Boolean(beginyear)){
						alert("收取年限不得为空!");
						return false;
					}
					if(Boolean(beginyear)){
						if(!regYear.exec(beginyear)){
							alert("收取年限请输入1~10之间的数字！");
							return false;
						}
					}
					if(!Boolean(rate)){
						alert("收取费率不得为空!");
						return false;
					}
					if(Boolean(rate)){
						if(!regRate.exec(rate)){
							alert("收取费率请输入1~100之间的数字！");
							return false;
						}
					}
					
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${pageContext.request.contextPath}/cortexs/crdFeeRule/">扣款费率修改</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="crdfeerule" 
		action="${pageContext.request.contextPath}/cortexs/crdFeeRule/save"
		class="form-horizontal">
		<sys:message content="${message}"/>
		
		<input type="hidden" name="crdfeeruleId" value="${crdfeerule.crdfeeruleId}" />
		
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="base">
				<div class="control-group">
					<label class="control-label">卡BIN：</label>
					<div class="controls">
						<form:input path="iid" disabled="true" htmlEscape="false" maxlength="11" class="inputext_style required"/>
						<form:hidden path="iid"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">卡产品：</label>
					<div class="controls">
						<form:input path="crdproduct" disabled="true" htmlEscape="false" maxlength="4" class="inputext_style"/>
						<form:hidden path="crdproduct"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">卡描述：</label>
					<div class="controls">
						<form:input path="descr" disabled="true" htmlEscape="false" class="inputext_style"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">收取年限：</label>
					<div class="controls">
						<form:input path="beginyear" htmlEscape="false" maxlength="2" class="inputext_style"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">收取费率：</label>
					<div class="controls">
						<form:input path="rate" htmlEscape="false" maxlength="3" class="inputext_style"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">是否收取手续费：</label>
					<div class="controls">
						<form:select path="feeflag" class="inputext_style">
							<form:option value="1" label="是"/>
							<form:option value="0" label="否"/>
						</form:select>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">是否为实名卡：</label>
					<div class="controls">
						<form:select path="truenameflag" class="inputext_style">
							<form:option value="0" label="非实名"/>
							<form:option value="1" label="实名"/>
						</form:select>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
			</div>			
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cortexs:crdFeeRule:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>