<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>厂商信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			$('#closeBtn').click(function() {
				parent.layer.close(index);
			});	
			//$("#name").focus();
			$("#inputForm").validate({
				onsubmit : true,// 是否在提交是验证
				onfocusout : false,// 是否在获取焦点时验证
				onkeyup : false,// 是否在敲击键盘时验证
				rules : {

				},			
				submitHandler: function(form){
					var param = $("#inputForm").serialize();
					url = $("#inputForm").attr("action");
					$.ajax({
						url : url,
						type : "post",
						data : param,
						dataType : "json",
						success : function(data) {						
							if (data.result == '0') {								
								layer.alert(data.resultMsg, 1, function() {								
									$("#searchForm", window.parent.document).attr("action","/pos/pFactory/list");
									$("#searchForm", window.parent.document).submit();
								});
							} else {								
								layer.alert(data.resultMsg);
							}
						}
					});
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
	<div class="sh_title">厂商信息</div>	
	<form:form id="inputForm" modelAttribute="pFactory" action="${pageContext.request.contextPath}/pos/pFactory/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">厂商名称：</label>
			<div class="controls">
				<form:input path="factoryName" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司状态：</label>
			<div class="controls">
				<form:select path="factoryStatus" class="input-xlarge required">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('FACTORY_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系经理：</label>
			<div class="controls">
				<form:input path="manager" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经理手机：</label>
			<div class="controls">
				<form:input path="managerMobile" htmlEscape="false" maxlength="15" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="managerTel" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pos:pFactory:edit">
				<input type="submit" id="submit" value="提交" class="button" />
			</shiro:hasPermission>
			<input type="button" id="closeBtn" value="关闭" class="button" />										
		</div>
	</form:form>
</body>
</html>