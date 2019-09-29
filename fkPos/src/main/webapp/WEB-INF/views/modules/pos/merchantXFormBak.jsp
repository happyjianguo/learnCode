<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户补充信息管理</title>
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
					
					var contractStartDate = $("#contractStartDate").val();
					var contractRenewalDate = $("#contractRenewalDate").val();
					var contractEndDate = $("#contractEndDate").val();
					var disableDate = $("#disableDate").val();
					var usableDate = $("#usableDate").val();
					if(contractStartDate==''){
						alert("必须输入合同签订日期");
						return false;			
					}		
					if(contractStartDate!='' && contractRenewalDate!='' && (getDate(contractStartDate)-getDate(contractRenewalDate)>0)){
						alert("合同续约日期不能小于合同签订日期");
						return false;
					}
					if(contractStartDate!='' && contractEndDate!='' && (getDate(contractStartDate)-getDate(contractEndDate)>0)){
						alert("合同终止日期不能小于合同签订日期");
						return false;
					}
					if(usableDate!='' && contractStartDate!='' && (getDate(contractStartDate)-getDate(usableDate)>0)){
						alert("商户启用日期不能小于合同签订日期");
						return false;
					}
					if(usableDate!='' && disableDate!='' && (getDate(disableDate)-getDate(usableDate)>0)){
						alert("商户启用日期不能小于商户停用日期");
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
									$("#searchForm", window.parent.document).attr("action","/pos/merchantX/list");
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
	<div class="sh_title">商户补充信息</div>	
	<form:form id="inputForm" modelAttribute="merchantBase" action="${pageContext.request.contextPath}/pos/merchantBase/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="flagMerchantX" value="1"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">商户编号：</label>
			<div class="controls">
				<form:input path="merchantId" htmlEscape="false" maxlength="15" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同签订日期：</label>
			<div class="controls">
				<input name="contractStartDate" id="contractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${merchantBase.contractStartDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同续约日期：</label>
			<div class="controls">
				<input name="contractRenewalDate" id="contractRenewalDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${merchantBase.contractRenewalDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同终止日期：</label>
			<div class="controls">
				<input name="contractEndDate" id="contractEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${merchantBase.contractEndDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商户停用日期：</label>
			<div class="controls">
				<input name="disableDate" id="disableDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${merchantBase.disableDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true, readOnly:true});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商户启用日期：</label>
			<div class="controls">
				<input name="usableDate" id="usableDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${merchantBase.usableDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true, readOnly:true});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商户公司名称：</label>
			<div class="controls">
				<form:input path="merchantCompanyName" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">续约方式：</label>
			<div class="controls">
				<form:select path="renewalType" class="input-xlarge ">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('RENEWAL_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保函到期日期：</label>
			<div class="controls">
				<input name="guaranteeLastDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="${merchantBase.guaranteeLastDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保函金额：</label>
			<div class="controls">
				<input name="guaranteeAmt" type="text" class="input-xlarge  number"
							value="<fmt:formatNumber value='${merchantBase.guaranteeAmt}' pattern='##.##'
													minFractionDigits='2'></fmt:formatNumber>"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">市场联系人：</label>
			<div class="controls">
				<form:input path="marketContactPerson" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">市场联系人电话：</label>
			<div class="controls">
				<form:input path="marketContactMobile" htmlEscape="false" maxlength="14" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">押金：</label>
			<div class="controls">
				<form:input path="merchantDeposit" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<shiro:hasPermission name="pos:merchantX:allot">
			<div class="control-group">
				<label class="control-label">区域：</label>
				<div class="controls">
					<form:select path="merchantArea" class="input-xlarge ">
						<form:option value="${merchantBase.merchantArea }" label="${merchantArea }"/>
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">门店负责人：</label>
				<div class="controls">
					<form:select path="storeManager" class="input-xlarge">
						<form:option value="${merchantBase.storeManager }" label="${storeManager }"/>
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
		</shiro:hasPermission>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pos:merchantX:edit">
				<input type="submit" id="submit" value="提交" class="button" />
			</shiro:hasPermission>
			<input type="button" id="closeBtn" value="关闭" class="button" />										
		</div>
	</form:form>
</body>
</html>