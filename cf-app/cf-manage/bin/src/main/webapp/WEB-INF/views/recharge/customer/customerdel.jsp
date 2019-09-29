<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><fmt:message key="recharge.customerdel.title"/></title>
<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li><fmt:message key="recharge.customer.customer"/></li>
				<li class="active" id="bill_check"><fmt:message key="recharge.customerdel.customerdel"/></li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form modelAttribute="customerModel" action="${ctxPath}/customer/deldata" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<div class="col-sm-4">
							<form:input path="id" type="hidden" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.customer"/></label>
						<div class="col-sm-3">
							<form:input path="cname" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.customer.rechargename"/></label>
						<div class="col-sm-3">
							<form:input path="mname" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.customer.rechargephone"/></label>
						<div class="col-sm-3">
							<form:input path="phone" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.customer.rechargeemail"/></label>
						<div class="col-sm-3">
							<form:input path="email" cssClass="form-control" readonly="true" />
						</div>
					</div>					
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary"><fmt:message key="button.submit"/></button>
							<a href="${ctxPath}/customer/init"><button type="button" class="btn default cancel" ><fmt:message key="button.cancel"/></button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>