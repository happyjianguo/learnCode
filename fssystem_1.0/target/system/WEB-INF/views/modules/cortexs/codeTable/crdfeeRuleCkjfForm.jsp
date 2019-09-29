<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>积分卡BIN管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
				}
			});
		});
		
		function checkIID(){
			var id = $("#id").val();
			if(!Boolean(id)){
				var iid = $("#iid").val();
				if(!Boolean(trim(iid))){
					return;
				}
				
				$.ajax({
				   type: "POST",
				   url: "${pageContext.request.contextPath}/cortexs/crdFeeRule/checkIID",
				   data: {iid:iid},
				   success: function(dataSource){
						if(dataSource.result == '0'){
							alert(dataSource.desc);
							$("#btnSubmit").attr("disabled", true); 
						}else{
							$("#spanIID").text(dataSource.desc.descr);
							$("#btnSubmit").removeAttr("disabled");
						}					   	   
				   }
				});
			}
		}
		
		//删除左右两端的空格
		function trim(str){ 
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${pageContext.request.contextPath}/cortexs/crdfeeRuleCkjf/">积分卡BIN列表</a></li>
		<li class="active"><a href="">${not empty crdfeeRuleCkjf.id?'积分卡BIN修改':'积分卡BIN添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="crdfeeRuleCkjf" 
		action="${pageContext.request.contextPath}/cortexs/crdfeeRuleCkjf/save"
		class="form-horizontal">
		<sys:message content="${message}"/>
		
		<form:hidden path="id"/>
		
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="base">
				<div class="control-group">
					<label class="control-label">积分卡BIN：</label>
					<div class="controls">
						<c:choose>
							<c:when test="${crdfeeRuleCkjf.id ne null}">
								<input id="iid" name="iid" readonly="readonly" htmlEscape="false" maxlength="11" class="inputext_style required"
									value="${crdfeeRuleCkjf.iid}" />
							</c:when>
							<c:otherwise>
								<input id="iid" name="iid" htmlEscape="false" maxlength="11" class="inputext_style required"
									value="${crdfeeRuleCkjf.iid}" onblur="checkIID();"/>
							</c:otherwise>
						</c:choose>
						<span class="help-inline"><font color="red" id="spanIID">*</font></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">有效期(月数)：</label>
					<div class="controls">
						<form:input path="usemonth" htmlEscape="false" maxlength="3" class="inputext_style required digits"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">购物返积分(04)：</label>
					<div class="controls">
						<form:select path="acctype4" class="input-medium ">
							<form:options items="${fns:getDictList('IS_EFFECTIVE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">联名卡送积分(09)：</label>
					<div class="controls">
						<form:select path="acctype9" class="input-medium ">
							<form:options items="${fns:getDictList('IS_EFFECTIVE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">积分卡BIN是否生效：</label>
					<div class="controls">
						<form:select path="flag" class="input-medium ">
							<form:options items="${fns:getDictList('IS_EFFECTIVE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
			</div>			
		</div>
		<div class="form-actions">
			<shiro:hasPermission  name="cortexs:crdfeeRuleCkjf:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>