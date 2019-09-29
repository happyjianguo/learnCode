<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>扣款费率管理</title>
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
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${pageContext.request.contextPath}/cortexs/crdfeelog/">新福卡扣款明细列表</a></li>
		<li class="active"><a href="">新福卡退款详情</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="crdfeerule" 
		action="${pageContext.request.contextPath}/cortexs/crdfeelog/refund"
		class="form-horizontal">
		<sys:message content="${message}"/>
		<form:hidden path="refundIDs"/>
		
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="base">
				<c:forEach var="info" items="${crdfeelogList}">
					<div class="control-group">
					<label class="control-label">退款商户号：</label>
					<div class="controls">
						<input name="merchantno" disabled="true" type="text" value="${info.merchantno}" class="input-medium"/>
					   	退款终端号：
						<input name="termcode" disabled="true" type="text" value="${info.termcode}" class="input-medium"/>
					   	退款卡号：
						<input name="pan" disabled="true" type="text" value="${info.pan}" class="input-medium"/>
					   	退款金额：
						<input type="text" name="fee" id="fee" disabled="true" 
							value="<fmt:formatNumber value='${info.fee}' pattern='#,###,###,###,###,##0.##' minFractionDigits='2' ></fmt:formatNumber>" 
							class="input-medium " />
					</div>
				</div>
				</c:forEach>
			</div>			
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="退 款"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>