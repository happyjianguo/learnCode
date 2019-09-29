<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><fmt:message key="recharge.accessclientdel.title"/></title>
<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li><fmt:message key="recharge.accessclient.accessclient"/></li>
				<li class="active" id="bill_check"><fmt:message key="recharge.accessclientdel.accessclientadel"/></li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form modelAttribute="accessclientModel" action="${ctxPath}/accessclient/deldata" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<div class="col-sm-4">
							<form:input path="id" type="hidden" cssClass="form-control" />
						</div>
					</div>
				 	<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.customer"/></label>
						<div class="col-sm-3 select-list" >
							<select id="cid" name="cid" class="form-control select" disabled="disabled">
								<c:forEach var="access" items="${accessclientModel.cfCustomerList }">
									<option value="${access.id }" ${access.id eq accessclientModel.cid ? 'selected="selected"':''} >${access.cname }</option>
								</c:forEach>
							</select>
						</div>
					</div> 
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.acid"/></label>
						<div class="col-sm-3">
							<form:input path="acname" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.contactsname"/></label>
						<div class="col-sm-3">
							<form:input path="mname" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.contactsphone"/></label>
						<div class="col-sm-3">
							<form:input path="phone" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.contactsemail"/></label>
						<div class="col-sm-3">
							<form:input path="email" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.secretkey"/></label>
						<div class="col-sm-3">
							<form:input path="ackey" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.callbackurl"/></label>
						<div class="col-sm-3">
							<form:input path="callbackurl" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.beginendtime"/></label>
						<div class="col-sm-6">
							<div class="input-append date date1" style="float:left; width:130px;">								 
							    <input type="text" id="begintime" name = "begintime"  disabled="disabled" class="date_1 form_datetime_long" value="${accessclientModel.begintime}"   style="margin-top:0">     
							</div>						
							<div class="input-append date date2" style="float:left">
								<span class="span_d"><fmt:message key="recharge.accessclient.timespot"/></span>
							    <input type="text" id="endtime" name = "endtime" disabled="disabled" class="date_2 form_datetime_long" value="${accessclientModel.endtime}"  style="margin-top:0"/>
							</div>							
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.budget"/></label>
						<div class="col-sm-3">
							<form:input path="budget" cssClass="form-control" readonly="true" />
						</div>
					</div>	
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.status"/></label>
						<div class="col-sm-3 select-list" >
							<select id="status" name="status" class="form-control select"  disabled="disabled">
								<option value="kt" ${'kt' eq accessclientModel.status ? 'selected="selected"':''}><fmt:message key="recharge.operationkt"/></option>
								<option value="gb" ${'gb' eq accessclientModel.status ? 'selected="selected"':''}><fmt:message key="recharge.operationgb"/></option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.cumulative"/></label>
						<div class="col-sm-3">
							<form:input path="cumulative" cssClass="form-control" readonly="true"/>
						</div>
					</div>					
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary"><fmt:message key="button.submit"/></button>
							<a href="${ctxPath}/accessclient/init"><button type="button" class="btn default cancel" ><fmt:message key="button.cancel"/></button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>