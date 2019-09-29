<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>卡状态设置管理</title>
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
			
			
			//校验密码复杂度
			/* $('#comments').blur(function(){
				var reg = /^(?![a-zA-Z]+$)(?=.*[a-zA-Z]+).{8,50}$/;
				if($(this).val() ==''){
					alert('备注不能为空');
					return false;
				}else{
					return true;
				}
			}) */	
			

		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- <li><a href="${pageContext.request.contextPath}/cortexs/tTranType/">交易类型设置列表</a></li>
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/tTranType/form?id=${tTranType.id}">交易类型设置<shiro:hasPermission name="cortexs:tTranType:edit">${not empty tTranType.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cortexs:tTranType:edit">查看</shiro:lacksPermission></a></li> --%>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="crdStatus" action="${pageContext.request.contextPath}/cortexs/crdStatus/save" method="post" class="form-horizontal">
		<sys:message content="${message}"/>	
		
		<div class="control-group">
			<label class="control-label">卡号：</label>
			<div class="controls">
				<c:if test="${not empty crdStatus.pan}">
					${crdStatus.pan}
					<form:hidden path="pan"/>
				</c:if>
				<c:if test="${empty crdStatus.pan}">
					<form:input path="pan" htmlEscape="false" maxlength="5" class="inputext_style required digits"/>
				</c:if>
			</div>
		</div>			
		
		<div class="control-group">
			<label class="control-label">卡状态码：</label>
			<div class="controls">
				<c:if test="${not empty crdStatus.statCode}">
					${crdStatus.statCode}
					${CrdStatus.statCode}
					<form:hidden path="statCode"/>
				</c:if>
				<c:if test="${empty crdStatus.statCode}">
					<form:input path="statCode" htmlEscape="false" maxlength="5" class="inputext_style required digits"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</c:if>
			</div>
		</div>	
		
		<div class="control-group">
			<label class="control-label">卡状态描述：</label>
			<div class="controls">
				${crdStatus.descr}
				<form:input path="descr" type="hidden" htmlEscape="false" maxlength="200" class="inputext_style required"/>
				<!-- <span class="help-inline"><font color="red">*</font> </span> -->
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">卡状态列表：</label>
			<div class="controls">
				<form:select path="currentStatus" class="inputext_style">
					
					<form:option value="94" label="94-合卡冻结"/>
					<form:option value="92" label="92-异号补卡冻结"/>
					<form:option value="91" label="91-消磁补卡冻结"/>
					<form:option value="90" label="90-拆卡冻结"/>
					<form:option value="93" label="93-未使用"/>
					<form:option value="97" label="97-金额冻结"/>
					<form:option value="98" label="98-其他(冻结)"/>
					<form:option value="95" label="95-作废"/>
					<form:option value="00" label="00-正常"/>
					<form:option value="01" label="01-密码尝试超限"/>
					<form:option value="02" label="02-未发行"/>
					<form:option value="03" label="03-卡过期"/>
					<form:option value="04" label="04-挂失"/>
					<form:option value="05" label="05-失窃"/>
					<form:option value="06" label="06-客户注销"/>
					<form:option value="07" label="07-银行取消"/>
					<form:option value="08" label="08-欺诈使用"/>
					<form:option value="20" label="20-等待激活"/>
					<form:option value="99" label="99-未激活"/>
					<form:option value="96" label="96-黑名单"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="comments" htmlEscape="false" maxlength="15" class="inputext_style required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> 
	
		<div class="form-actions">
			<shiro:hasPermission name="cortexs:crdStatus:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>