<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易类型设置管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${pageContext.request.contextPath}/cortexs/tTranType/">交易类型设置列表</a></li>
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/tTranType/form?id=${tTranType.id}">交易类型设置<shiro:hasPermission name="cortexs:tTranType:edit">${not empty tTranType.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cortexs:tTranType:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tTranType" action="${pageContext.request.contextPath}/cortexs/tTranType/save" method="post" class="form-horizontal">
		<sys:message content="${message}"/>			
		<div class="control-group">
			<label class="control-label">交易类型ID：</label>
			<div class="controls">
				<c:if test="${not empty tTranType.id}">
					${tTranType.id}
					<form:hidden path="id"/>
				</c:if>
				<c:if test="${empty tTranType.id}">
					<form:input path="id" htmlEscape="false" maxlength="20" class="inputext_style required digits"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</c:if>				
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">交易类型描述：</label>
			<div class="controls">
				<form:input path="tranTypeDesc" htmlEscape="false" maxlength="200" class="inputext_style required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">功能码：</label>
			<div class="controls">
				<c:if test="${not empty tTranType.fncode}">
					${tTranType.fncode}
					<form:hidden path="fncode"/>
				</c:if>
				<c:if test="${empty tTranType.fncode}">
					<form:input path="fncode" htmlEscape="false" maxlength="5" class="inputext_style required digits"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</c:if>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">交易码：</label>
			<div class="controls">
				<c:if test="${not empty tTranType.txncode}">
					${tTranType.txncode}
					<form:hidden path="txncode"/>
				</c:if>
				<c:if test="${empty tTranType.txncode}">
					<form:input path="txncode" htmlEscape="false" maxlength="5" class="inputext_style required digits"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</c:if>	
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子处理码：</label>
			<div class="controls">
				<c:if test="${not empty tTranType.subTxncode}">
					${tTranType.subTxncode}
					<form:hidden path="subTxncode"/>
				</c:if>
				<c:if test="${empty tTranType.subTxncode}">
					<form:input path="subTxncode" htmlEscape="false" maxlength="2" class="inputext_style required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">金额符号：</label>
			<div class="controls">
				
				<form:select path="amtFlag" class="inputext_style">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('AMT_FLAG')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
					
				
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否需要显示：</label>
			<div class="controls">
				<form:select path="showFlag" class="inputext_style">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SHOW_FLAG')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="comments" htmlEscape="false" maxlength="200" class="inputext_style "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cortexs:tTranType:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>