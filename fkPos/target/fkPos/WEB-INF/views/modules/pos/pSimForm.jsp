<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>SIM卡信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var phoneOrSerial = $("#phoneOrSerial").val();
			if(!Boolean(phoneOrSerial)){
				addDisable();
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
					//移动-电话号码  必填;联通-SIM卡串号 必填
					var simCommunication=$("#simCommunication").val();
					var phoneNumber=$.trim($("#phoneNumber").val());
					var serialNumber=$.trim($("#serialNumber").val());
					var reg = /^\d{0,12}(\.\d{1,2})?$/;
					var simDeposit =$.trim($("#simDeposit").val());
					if(!reg.exec(simDeposit)){
						alert("押金请输入两位小数的数字！");
						return false;
					}
					if(simCommunication!=""){
						/* if(simCommunication=="CHINA_MOBILE"&&phoneNumber==""){
							alert("运营商为移动,手机号为必填项");
							$("#phoneNumber").focus();
							return false;
						}else if(simCommunication=="CHINA_UNICOM"&&serialNumber==""){
							alert("运营商为联通,SIM卡串号为必填项");
							$("#serialNumber").focus()
							return false;
						} */
						/*
						//校验手机号或SIM卡是否重复
						if(phoneNumber!=""||serialNumber!=""){
							$.ajax({
								url : '${pageContext.request.contextPath}/pos/pSim/checkSIM',
								type : "post",
								data : {
									simCommunication : simCommunication,
									phoneNumber	: phoneNumber,
									serialNumber : serialNumber
								},
								dataType : "text",
								success : function(data) {
									if(data=='false'){
										alert("该运营商该手机号或SIM卡串号有重复，请重新输入");
										$("#phoneNumber").val('');	
										$("#serialNumber").val('');	
										$("#phoneNumber").focus();
										return false;																
									}
								}
							})
						}
						*/
					}
					
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
								$("#phoneNumber").val('');	
								$("#serialNumber").val('');	
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
		
		function selectChange(){
			var phoneOrSerial = $("#phoneOrSerial").val();
			var phoneNumber=$.trim($("#phoneNumber").val());
			var serialNumber=$.trim($("#serialNumber").val());
			if(!Boolean(phoneOrSerial)){
				addDisable();
				resetVal();
				return;
			}else if(phoneOrSerial=="手机号" && phoneNumber==""){
				$("#serialNumber").attr("disabled",true);
				$("#phoneNumber").removeAttr("disabled");
				$("#phoneNumber").focus();
				return;
			}else if(phoneOrSerial=="SIM卡串号" && serialNumber==""){
				$("#phoneNumber").attr("disabled",true);
				$("#serialNumber").removeAttr("disabled");
				$("#serialNumber").focus()
				return;
			}
		}
		
		function addDisable(){
			$("#phoneNumber").attr("disabled",true);
			$("#serialNumber").attr("disabled",true);
		}
		
		function removeDisable(){
			$("#phoneNumber").removeAttr("disabled");
			$("#serialNumber").removeAttr("disabled");
		}
		
		function resetVal(){
			$("#phoneNumber").val("");
			$("#serialNumber").val("");
		}
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
				<form:select path="simCommunication" class="input-xlarge required">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SIM_COMMUNICATION')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号/SIM卡串号：</label>
			<div class="controls">
				<select id="phoneOrSerial" name="phoneOrSerial" class="input-xlarge required" onchange = "selectChange();">
					<option value="">--请选择--</option>
					<option value="手机号">手机号</option>
					<option value="SIM卡串号">SIM卡串号</option>
				<select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号：</label>
			<div class="controls">
				<form:input path="phoneNumber" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SIM卡串号：</label>
			<div class="controls">
				<form:input path="serialNumber" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SIM卡状态：</label>
			<div class="controls">
				<form:select path="simStatus" class="input-xlarge " disabled="true">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SIM_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<form:hidden path="simStatus"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购日期：</label>
			<div class="controls">
				<input name="purchaseDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${pSim.purchaseDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购者：</label>
			<div class="controls">
				<form:input path="purchaseBy" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SIM卡押金(元)：</label>
			<div class="controls">
				<form:input path="simDeposit" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pos:pSim:edit">
				<input type="submit" id="submit" value="提交" class="button" />
			</shiro:hasPermission>
			<input type="button" id="closeBtn" value="关闭" class="button" />										
		</div>
	</form:form>
</body>
</html>