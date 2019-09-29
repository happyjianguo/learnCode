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
	</script>
</head>
<body>
	<div class="sh_title">SIM卡信息</div>	
	<form:form id="inputForm" modelAttribute="pSim" action="${pageContext.request.contextPath}/pos/pSim/save" method="post" class="form-horizontal">
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
				<form:input path="phoneNumber" htmlEscape="false" maxlength="16" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SIM卡串号：</label>
			<div class="controls">
				<form:input path="serialNumber" htmlEscape="false" maxlength="100" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SIM卡状态：</label>
			<div class="controls">
				<form:select path="simStatus" class="input-xlarge " disabled="true">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SIM_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SN码：</label>
			<div class="controls">
				<form:input path="posSn" htmlEscape="false" maxlength="50" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购日期：</label>
			<div class="controls">
				<input name="purchaseDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${pSim.purchaseDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false});" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购者：</label>
			<div class="controls">
				<form:input path="purchaseBy" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入库时间：</label>
			<div class="controls">
				<input name="instockDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${pSim.instockDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false});" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入库者：</label>
			<div class="controls">
				<form:input path="instockBy" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入库批次号：</label>
			<div class="controls">
				<form:input path="instockBatch" htmlEscape="false" maxlength="20" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出库时间：</label>
			<div class="controls">
				<input name="outstockDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${pSim.outstockDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false});" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出库者：</label>
			<div class="controls">
				<form:input path="outstockBy" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">安装时间：</label>
			<div class="controls">
				<input name="installDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${pSim.installDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false});" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">安装者：</label>
			<div class="controls">
				<form:input path="installBy" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报修时间：</label>
			<div class="controls">
				<input name="repairDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${pSim.repairDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false});" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报修者：</label>
			<div class="controls">
				<form:input path="repairBy" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报废时间：</label>
			<div class="controls">
				<input name="scrappedDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${pSim.scrappedDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false});" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报废者：</label>
			<div class="controls">
				<form:input path="scrappedBy" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>
			</div>
		</div>
		<div class="form-actions">
			<input type="button" id="closeBtn" value="关闭" class="button" />										
		</div>
	</form:form>
</body>
</html>