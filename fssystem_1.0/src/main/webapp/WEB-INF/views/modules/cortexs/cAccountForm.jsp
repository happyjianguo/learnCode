<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>账户余额变更管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				//自定义评语内容验证方法  
	              jQuery.validator.addMethod("AmtLimit",function(value, element){  
	            	  var avlbal=$('#avlbal').val();
	            	  var amtFlag=$('#amtFlag').val();
	            	  var returnVal = false;
	            	  if(amtFlag==1&&(parseFloat(avlbal)+parseFloat(value))>5000){
	            		  return returnVal;
	            	  }
	            	  if(amtFlag==-1&&(parseFloat(avlbal)-parseFloat(value))<0){
	            		  return returnVal;
	            	  }
	                 return true;  
	            },"提示：最终账户金额范围0-5000，请重新设置调账金额。"); 
				
	            //自定义评语内容验证方法  
	              jQuery.validator.addMethod("AmtLimitInput",function(value, element){  
	            	  if(value!=null&&value!=""){
	   
	          			if(value.indexOf(".") != -1&&value.split(".")[1].length>2){
	          				return false;
	          			}else{
	          				return true;
	          			}			
	          		}else{
	          			return true;
	          		}
	            }, "请正确填写调账金额格式：最多只能保留2位小数");
				
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
				},
				rules : {
					transferAmt : {number : true,
									AmtLimit : true,
									AmtLimitInput : true
									}
				},
				messages: {
					transferAmt: {number:"提示：调账金额，请输入数字"}
				}
				
			});
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/cAccount/form?id=${cAccount.pan}">账户余额变更修改</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cAccount" action="${pageContext.request.contextPath}/cortexs/cAccount/save" method="post" class="form-horizontal">
		<sys:message content="${message}"/>			
		<div class="control-group">
			<label class="control-label">卡号：</label>
			<div class="controls">
					${cAccount.pan}
					<form:hidden path="pan"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">账户号：</label>
			<div class="controls">
				${cAccount.accno}
				<form:hidden path="accno"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">账户类型：</label>
			<div class="controls">
					${cAccount.typecode}
					<form:hidden path="typecode"/>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">币种：</label>
			<div class="controls">
					${cAccount.currcode}
					<form:hidden path="currcode"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">当前账户余额：</label>
			<div class="controls">
					${cAccount.avlbal}
					<form:hidden path="avlbal"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">金额符号：</label>
			<div class="controls">
				
				<form:select path="amtFlag" class="inputext_style">
					<form:options items="${fns:getDictList('AMT_FLAG')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
					
				
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">调账金额：</label>
			<div class="controls">
				<form:input path="transferAmt" htmlEscape="false" maxlength="20" class="inputext_style required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="comments" htmlEscape="false" rows="2" maxlength="15" class="inputext_style required"/>
			    <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cortexs:cAccount:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>