<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户经理信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var managerRole = $.trim($("#managerRole").val());
			// 1 店长  0 普通员工
			if(managerRole == '1'){
				$("#fatherId").attr("disabled",true);
			}
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
									$("#searchForm", window.parent.document).attr("action","/pos/pManager/list");
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
		var getManagerList = function(roleId) {
			var managerArea=$.trim($("#managerArea").val());
			if(managerArea!=""){
				$.ajax({
					url : '${pageContext.request.contextPath}/pos/merchantBase/getManagerList',
					type : "post",
					data : {
						roleId : roleId,
						managerArea: $("#managerArea").val()
					},
					dataType : "json",
					success : function(data) {
						if(roleId=="1"){
							$("#fatherId").empty();
							$("#fatherId").append("<option value=''>--请选择--</option>");	
							$("#fatherId").select().val('');
							if (data.length != 0) {
								$.each(data, function(i, info) {
									$("#fatherId").append(
										"<option title='"+info.name+"' value='"+info.value+"'>" + info.name + "</option>");
								});
							}							
						}
					}
				})
			}

		};

		function selectChange(){
			var managerRole = $.trim($("#managerRole").val());
			// 1 店长  0 普通员工
			if(managerRole == '1'){
				$("#fatherId").select().val('');
				$("#fatherId").attr("disabled",true);
			}else{
				$("#fatherId").removeAttr("disabled");
			}
		}
		
	</script>
</head>
<body>
	<div class="sh_title">商户经理信息</div>	
	<form:form id="inputForm" modelAttribute="pManager" action="${pageContext.request.contextPath}/pos/pManager/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">商户经理名称：</label>
			<div class="controls">
				<form:input path="managerName" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="managerStatus" class="input-xlarge required">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('MANAGER_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区域：</label>
			<div class="controls">
				<form:select path="managerArea" class="input-xlarge required" onchange="getManagerList('1');">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('MERCHANT_AREA')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">角色：</label>
			<div class="controls">
				<form:select path="managerRole" class="input-xlarge required" onchange = "selectChange();">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('MANAGER_ROLE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上级：</label>
			<div class="controls">
				<form:select path="fatherId" class="input-xlarge">
					<form:option value="" label="--请选择--"/>
					<form:options items="${storeManagerList}" itemLabel="managerName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="managerTel" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱：</label>
			<div class="controls">
				<form:input path="managerEmail" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pos:pManager:edit">
				<input type="submit" id="submit" value="提交" class="button" />
			</shiro:hasPermission>
			<input type="button" id="closeBtn" value="关闭" class="button" />										
		</div>
	</form:form>
</body>
</html>