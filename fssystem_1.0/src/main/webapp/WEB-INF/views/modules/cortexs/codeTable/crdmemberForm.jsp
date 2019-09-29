<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目信息码表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").attr("disabled", true); 
			$("#inputForm").validate({
				submitHandler: function(form){
					
					var memberName = $("#memberName").val();
					if(!Boolean(trim(memberName))){
						alert("项目名称不可为空!");
						return false;
					}
					
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
			
			$("#btnCancel").click(function(){
				window.location.href = "${pageContext.request.contextPath}/cortexs/Crdmember/list";
			});
		});
		
		function checkCardBin(){
			var cardBin = $("#cardBin").val();
			if(!Boolean(trim(cardBin))){
				alert("卡BIN不可为空!");
				$("#crdproduct").val('');
				$("#descr").val('');
				$("#btnSubmit").attr("disabled", true); 
				return false;
			}
			$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath}/cortexs/crdFeeRule/checkIID",
			   data: {iid:cardBin},
			   success: function(dataSource){
					if(dataSource.result == '0'){
						alert(dataSource.desc);
						$("#crdproduct").val('');
						$("#descr").val('');
						$("#btnSubmit").attr("disabled", true); 
					}else{
						$("#crdproduct").val(dataSource.desc.crdproduct);
						$("#descr").val(dataSource.desc.descr);
						$("#btnSubmit").removeAttr("disabled");
					}					   	   
			   }
			});
		}
		//删除左右两端的空格
		function trim(str){ 
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="">项目信息</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="crdmember" 
		action="${pageContext.request.contextPath}/cortexs/Crdmember/save"
		class="form-horizontal">
		<sys:message content="${message}"/>
		
		<input type="hidden" name="memberId" value="${crdmember.memberId}" />
		
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="base">
				<div class="control-group">
					<label class="control-label">项目名称：</label>
					<div class="controls">
						<form:input path="memberName" htmlEscape="false" maxlength="100" class="inputext_style required"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">卡BIN：</label>
					<div class="controls">
						<input name="cardBin" id="cardBin" htmlEscape="false" value="${crdmember.cardBin}" maxlength="9" onblur="checkCardBin();" class="inputext_style"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">卡产品：</label>
					<div class="controls">
						<form:input path="crdproduct" disabled="true" htmlEscape="false" class="inputext_style"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">卡描述：</label>
					<div class="controls">
						<form:input path="descr" disabled="true" htmlEscape="false" class="inputext_style"/>
					</div>
				</div>
			</div>			
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cortexs:crdmember:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"/>
		</div>
	</form:form>
</body>
</html>