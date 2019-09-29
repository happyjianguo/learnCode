<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户结算开票设置管理</title>
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
		<li><a href="${pageContext.request.contextPath}/cortexs/mSettleBill/">商户结算开票设置列表</a></li>
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/mSettleBill/form?id=${mSettleBill.id}">商户结算开票设置<shiro:hasPermission name="cortexs:mSettleBill:edit">${not empty mSettleBill.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cortexs:mSettleBill:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="mSettleBill" action="${pageContext.request.contextPath}/cortexs/mSettleBill/save" method="post" class="form-horizontal">
		<sys:message content="${message}"/>	
		<div class="control-group">
			商户结算信息
		</div>		
		<div class="control-group">
			<label class="control-label">商户号：</label>
			<div class="controls">
					${mSettleBill.mrchno}
			<form:hidden path="mrchno"/>
			</div>
		</div>	

		<div class="control-group">
			<label class="control-label">结算周期：</label>
			<div class="controls">
				
				<form:select path="fsCycle" class="inputext_style">
				<form:option value="" label="--请选择--"/>
				<form:options items="${fs_cycleList}" itemLabel="paramName" itemValue="paramValue" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结算参数：</label>
			<div class="controls">
				<form:input path="fsCycleParam" htmlEscape="false" maxlength="2" class="inputext_style required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上次结算日期：</label>
			<div class="controls">
			<form:input path="lastSettleDate" type="text" readonly="readonly" maxlength="20" class="inputext_style Wdate"
			value="${mSettleBill.lastSettleDate}"
			onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"
			/>
			<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否按照不同消费场景结算：</label>
			<div class="controls">
			<form:select path="isConsumpCategory" class="inputext_style">
					<form:option value="0" label="否"/>
					<form:option value="1" label="是"/>
			</form:select>
			<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否合并打款：</label>
			<div class="controls">
				<form:select path="amtConsumpCategory" class="inputext_style">
					<form:option value="1" label="是"/>
					<form:option value="0" label="否"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">普卡/专属卡是否合并打款：</label>
			<div class="controls">
				<form:select path="mergeMoneyFlag" class="inputext_style">
					<form:option value="1" label="是"/>
					<form:option value="0" label="否"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否按照卡类别分组：</label>
			<div class="controls">
				<form:select path="isCardTypeGroup" class="inputext_style">
					<form:option value="0" label="否"/>
					<form:option value="1" label="是"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否按父商户结算：</label>
			<div class="controls">
				<form:select path="isFmrchnoDecide" class="inputext_style">
					<form:option value="0" label="否"/>
					<form:option value="1" label="是"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			商户开票信息
		</div>	
		<div class="control-group">
			<label class="control-label">是否开票：</label>
			<div class="controls">
				<form:select path="isKp" class="inputext_style">
					<form:option value="1" label="是"/>
					<form:option value="0" label="否"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开票周期：</label>
			<div class="controls">
				<form:select path="fsKpCycle" class="inputext_style">
				<form:option value="" label="--请选择--"/>
				<form:options items="${fs_kp_cycleList}" itemLabel="paramName" itemValue="paramValue" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开票周期参数：</label>
			<div class="controls">
			<form:input path="fsKpCycleParam" htmlEscape="false" maxlength="2" class="inputext_style required"/>
			<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上次开票日期：</label>
			<div class="controls">
					<form:input path="lastKpDate" type="text" readonly="readonly" maxlength="20" class="inputext_style Wdate"
					value="${mSettleBill.lastKpDate}"
						onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"
					/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cortexs:mSettleBill:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>