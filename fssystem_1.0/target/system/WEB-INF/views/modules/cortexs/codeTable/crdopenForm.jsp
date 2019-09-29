<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>口令信息码表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					
					var operator = $("#operator").val();
					if(!Boolean(operator)){
						alert("操作员不可为空!");
						return false;
					}
					
					/* var indentNumber = $("#indentNumber").val();
					if(!Boolean(indentNumber)){
						alert("订单号不可为空!");
						return false;
					} */
					
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
		
		//删除左右两端的空格
		function trim(str){ 
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		
		/* function getOrder(){
			var operator = $("#operator").val();
			$("#indentNumber").html("<option value=''>--请选择--</option>");
			if(!Boolean(trim(operator))){
				return false;
			}
			
			$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath}/cortexs/Crdopen/getOrder",
			   data: {operator:operator},
			   success: function(orderList){
				   if(orderList == null){
					   $("#indentNumber").html("<option value=''>--请选择--</option>");
					   return;
				   }
			   	   for(var i = 0; i < orderList.length; i++){
			   		   var indentNumber = orderList[i];
			   		   if(!Boolean(indentNumber)){
			   		   } else {
			   				$("#indentNumber").append(
				   	   			"<option value=" + indentNumber + ">"+
				   	   			indentNumber + "</option>"
				   	   	    );
			   		   }
			   	   }				   	   
			   }
			});
		} */
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="">口令信息</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="crdopen" 
		action="${pageContext.request.contextPath}/cortexs/Crdopen/save"
		class="form-horizontal">
		<sys:message content="${message}"/>
		
		<input type="hidden" name="openId" value="${crdopen.openId}" />
		
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="base">
				<div class="control-group">
					<label class="control-label">操作员：</label>
					<div class="controls">
						<select id="operator" name="operator" class="inputext_style">
							<option value="">--请选择--</option>										
							<c:forEach var="model" items="${operatorList}">
								<option value="${model}" <c:if test="${crdopen.operator eq model}">selected="selected"</c:if>>
									${fn:split(model, ",")[1]}
								</option>
							</c:forEach>
						</select>	
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<%-- <div class="control-group">
					<label class="control-label">订单号：</label>
					<div class="controls">
						<select id="indentNumber" name="indentNumber" class="inputext_style" >
							<option value="">--请选择--</option>										
							<c:forEach var="model" items="${orderList}">
								<option value="${model}" <c:if test="${crdopen.indentNumber eq model}">selected="selected"</c:if>>
									${model}
								</option>
							</c:forEach>
						</select>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div> --%>
			</div>			
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cortexs:crdopen:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>