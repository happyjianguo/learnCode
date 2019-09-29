<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>终端补充信息管理</title>
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
					var disableDate = $("#disableDate").val();
					var usableDate = $("#usableDate").val();
					if(usableDate!='' && disableDate!='' && (getDate(disableDate)-getDate(usableDate)>0)){
						alert("终端启用日期不能小于终端停用日期");
						return false;
					}
					$.ajax({
						url : url,
						type : "post",
						data : param,
						dataType : "json",
						success : function(data) {						
							if (data.result == '0') {								
								layer.alert(data.resultMsg, 1, function() {								
									$("#searchForm", window.parent.document).attr("action","${ctxBak}/pos/edcTerminalX/list");
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
			var terminalArea=$.trim($("#terminalArea").val());
			$("#merchantAdvisor").html("<option value=''>--请选择--</option>");	
			$("#merchantAdvisor").select().val('');
			if(!Boolean(terminalArea)){
				return;
			}
			//获取商户顾问
			$.ajax({
				   type: "POST",
				   url: "${pageContext.request.contextPath}/pos/merchantX/getSMgrOrMAdvisor",
				   data: {roleId:roleId,merchantArea:terminalArea},
				   success: function(merchantAdvisorList){
					   if(merchantAdvisorList == null){
						   return;
					   }
				   	   for(var i = 0; i < merchantAdvisorList.length; i++){
				   		   var merchantAdvisor = merchantAdvisorList[i];
				   		   if(!Boolean(merchantAdvisor)){
				   		   } else {
				   		   		$("#merchantAdvisor").append(
					   	   			"<option value=" + merchantAdvisor.value + ">"+
					   	   			merchantAdvisor.name + "</option>"
					   	   	    );
				   		   }
				   	   	   
				   	   }
				   	   
				   }
			});
		};		
		
		var getCityList = function() {
			$("#terminalCity").html("<option value=''>--请选择--</option>");
			$("#terminalCity").select().val('');
			$("#terminalZone").html("<option value=''>--请选择--</option>");	
			$("#terminalZone").select().val('');
			var terminalArea=$.trim($("#terminalProvince").val());
			if(terminalArea!=""&&terminalArea!="undefined"){			
				$.ajax({
					url : '${pageContext.request.contextPath}/cortex/cortexArea/getCortexAreaList',
					type : "post",
					data : {
						depth : '2',
						fid: terminalArea
					},
					dataType : "json",
					success : function(data) {
						if (data.length != 0) {
							$.each(data, function(i, info) {
								$("#terminalCity").append(
									"<option title='"+info.name+"' value='"+info.value+"'>" + info.name + "</option>");
							});
						}							
					}
				})
			}
		};
		var getZoneList = function() {
			$("#terminalZone").html("<option value=''>--请选择--</option>");	
			$("#terminalZone").select().val('');
			var terminalArea=$.trim($("#terminalCity").val());
			if(terminalArea!=""&&terminalArea!="undefined"){			
				$.ajax({
					url : '${pageContext.request.contextPath}/cortex/cortexArea/getCortexAreaList',
					type : "post",
					data : {
						depth : '',
						fid: terminalArea
					},
					dataType : "json",
					success : function(data) {
						if (data.length != 0) {
							$.each(data, function(i, info) {
								$("#terminalZone").append(
									"<option title='"+info.name+"' value='"+info.value+"'>" + info.name + "</option>");
							});
						}							
					}
				})
			}
		};
		
		function getDate(date){
			 var dates = date.split("-");
			 var dateReturn = '';
			 
			 for(var i=0; i<dates.length; i++){
			  dateReturn+=dates[i];
			 }
			 return dateReturn;
		}
	</script>
</head>
<body>
	<div class="sh_title">终端补充信息</div>	
	<form:form id="inputForm" modelAttribute="edcTerminal" action="${pageContext.request.contextPath}/pos/edcTerminal/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="flagMerchantX" value="1"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">终端号：</label>
			<div class="controls">
				<form:input path="terminalId" disabled="true" htmlEscape="false" maxlength="8" class="input-xlarge required"/>
				<form:hidden path="terminalId"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">升级日期：</label>
			<div class="controls">
				<input name="upgradeDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${edcTerminal.upgradeDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">终端停用日期：</label>
			<div class="controls">
				<input name="disableDate" id="disableDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${edcTerminal.disableDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true, readOnly:true});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">终端启用日期：</label>
			<div class="controls">
				<input name="usableDate" id="usableDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${edcTerminal.usableDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true, readOnly:true});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">升级版本号：</label>
			<div class="controls">
				<form:input path="upgradeVersion" htmlEscape="false" maxlength="8" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">门店联系人：</label>
			<div class="controls">
				<form:input path="storeContacts" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">门店电话：</label>
			<div class="controls">
				<form:input path="storePhone" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区域：</label>
			<div class="controls">
				<form:select path="terminalArea" class="input-xlarge " onchange="getManagerList('');">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('MERCHANT_AREA')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商户顾问：</label>
			<div class="controls">
				<form:select path="merchantAdvisor" class="input-xlarge">
					<form:option value="" label="--请选择--"/>
					<form:options items="${managerList}" itemLabel="managerName" itemValue="id" htmlEscape="false"/>
				</form:select>						
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">省份：</label>
			<div class="controls">
				<form:select path="terminalProvince" class="input-xlarge " onchange="getCityList();">
					<form:option value="" label="--请选择--"/>
					<form:options items="${provinceList}" itemLabel="provinceCity" itemValue="id" htmlEscape="false"/>
				</form:select>						
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">城市：</label>
			<div class="controls">
				<form:select path="terminalCity" class="input-xlarge" onchange="getZoneList();">
					<form:option value="" label="--请选择--"/>
					<form:options items="${cityList}" itemLabel="provinceCity" itemValue="id" htmlEscape="false"/>
				</form:select>		
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区：</label>
			<div class="controls">
				<form:select path="terminalZone" class="input-xlarge">
					<form:option value="" label="--请选择--"/>
					<form:options items="${zoneList}" itemLabel="provinceCity" itemValue="id" htmlEscape="false"/>
				</form:select>						
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">终端装机地址：</label>
			<div class="controls">
				<form:input path="terminalPosition" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pos:edcTerminalX:edit">
				<input type="submit" id="submit" value="提交" class="button" />
			</shiro:hasPermission>
			<input type="button" id="closeBtn" value="关闭" class="button" />										
		</div>
	</form:form>
</body>
</html>