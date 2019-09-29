<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>SIM卡信息管理</title>
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
									$("#searchForm", window.parent.document).attr("action","/pos/pSim/list");
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
		var checkPosSim = function() {
			var posSn=$.trim($("#posSn").val());
			if(posSn!=""){
				$.ajax({
					url : '${pageContext.request.contextPath}/pos/pSim/checkPosSim',
					type : "post",
					data : {
						posSn : posSn
					},
					dataType : "text",
					success : function(data) {
						if(data=='0'){
							alert("该SN码对应的POS机不存在，确认后请重新输入");
							$("#posSn").val('');							
						}else if(data!='0'&&data!='1'){
							alert(data);
						}
					}
				})
			}
		};
		
	</script>
</head>
<body>
	<div class="sh_title">SIM卡出库</div>	
	<form:form id="inputForm" modelAttribute="pSim" action="${pageContext.request.contextPath}/pos/pSim/outStock" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">运营商：</label>
			<div class="controls">
				<form:select path="simCommunication" class="input-xlarge required" disabled="true">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SIM_COMMUNICATION')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号：</label>
			<div class="controls">
				<form:input path="phoneNumber" htmlEscape="false" maxlength="16" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SIM卡串号：</label>
			<div class="controls">
				<form:input path="serialNumber" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SN码：</label>
			<div class="controls">
				<form:input path="posSn" htmlEscape="false" maxlength="50" class="input-xlarge required" onblur="checkPosSim();"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pos:pSim:outStock">
				<input type="submit" id="submit" value="提交" class="button" />
			</shiro:hasPermission>
			<input type="button" id="closeBtn" value="关闭" class="button" />										
		</div>
	</form:form>
</body>
</html>