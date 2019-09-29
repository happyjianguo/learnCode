<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>POS机信息管理</title>
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
									$("#searchForm", window.parent.document).attr("action","/pos/pPos/list");
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
		var getPModelList = function() {
			$.ajax({
				url : '${pageContext.request.contextPath}/pos/pPos/getPModelList',
				type : "post",
				data : {
					factoryId : $("#factoryId").val()
				},
				dataType : "json",
				success : function(data) {
					//$('#modelId').attr('value',''); 		
					$("#modelId").empty();
					$("#modelId").append("<option value=''>--请选择--</option>");	
					$("#modelId").select().val('');
					if (data.length != 0) {
						$.each(data, function(i, info) {
							$("#modelId").append(
								"<option title='"+info.modelId+"' value='"+info.modelId+"'>" + info.modelId + "</option>");
						});
					}
				}
			})
		};
		
	</script>
</head>
<body>
	<div class="sh_title">POS机信息</div>	
	<form:form id="inputForm" modelAttribute="pPos" action="${pageContext.request.contextPath}/pos/pPos/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">SN码：</label>
			<div class="controls">
				<form:input path="posSn" htmlEscape="false" maxlength="50" class="input-xlarge required" disabled="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">厂商：</label>
			<div class="controls">
				<form:select path="factoryId" class="input-xlarge required" disabled="true">
					<form:option value="" label="--请选择--"/>
					<form:options items="${pFactoryList}" itemLabel="factoryName" itemValue="id" htmlEscape="false"/>
				</form:select>							
				<span class="help-inline"><font color="red">*</font> </span>				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">型号：</label>
			<div class="controls">
				<form:select path="modelId" class="input-xlarge required" disabled="true">
					<form:option value="" label="--请选择--"/>
					<form:options items="${pModelList}" itemLabel="modelId" itemValue="modelId" htmlEscape="false"/>					
				</form:select>	
				<span class="help-inline"><font color="red">*</font> </span>				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备类型：</label>
			<div class="controls">
				<form:select path="posType" class="input-xlarge required" disabled="true">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('POS_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备状态：</label>
			<div class="controls">
				<form:select path="posStatus" class="input-xlarge required" disabled="true">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('POS_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否支持扫描：</label>
			<div class="controls">
				<form:select path="posScanFlag" class="input-xlarge " disabled="true">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('POS_SCAN_FLAG')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">布放类型：</label>
			<div class="controls">
				<form:select path="layOutFlag" class="input-xlarge " disabled="true">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('LAY_OUT_FLAG')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">终端号：</label>
			<div class="controls">
				<form:input path="terminalId" htmlEscape="false" maxlength="8" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购日期：</label>
			<div class="controls">
				<input name="purchaseDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${pPos.purchaseDate}"
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
			<label class="control-label">保修时间：</label>
			<div class="controls">
				<form:input path="warrantyTimeLimit" htmlEscape="false" maxlength="8" class="input-xlarge " disabled="true"/>（单位：年）
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入库时间：</label>
			<div class="controls">
				<input name="instockDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${pPos.instockDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});" disabled="true"/>
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
					value="${pPos.outstockDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出库者：</label>
			<div class="controls">
				<form:input path="outstockBy" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<!-- 
		<div class="control-group">
			<label class="control-label">安装时间：</label>
			<div class="controls">
				<input name="installDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${pPos.installDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false});" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">安装者：</label>
			<div class="controls">
				<form:input path="installBy" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		 -->
		<div class="control-group">
			<label class="control-label">报修时间：</label>
			<div class="controls">
				<input name="repairDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${pPos.repairDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报修者：</label>
			<div class="controls">
				<form:input path="repairBy" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报修原因：</label>
			<div class="controls">
				<form:textarea path="repairRemarks" htmlEscape="false" rows="4" maxlength="80" class="input-xxlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报废时间：</label>
			<div class="controls">
				<input name="scrappedDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${pPos.scrappedDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报废者：</label>
			<div class="controls">
				<form:input path="scrappedBy" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报废原因：</label>
			<div class="controls">
				<form:textarea path="scrappedRemarks" htmlEscape="false" rows="4" maxlength="80" class="input-xxlarge " disabled="true"/>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="80" class="input-xxlarge " disabled="true"/>
			</div>
		</div>
		<div class="form-actions">
			<input type="button" id="closeBtn" value="关闭" class="button" />										
		</div>
	</form:form>
</body>
</html>