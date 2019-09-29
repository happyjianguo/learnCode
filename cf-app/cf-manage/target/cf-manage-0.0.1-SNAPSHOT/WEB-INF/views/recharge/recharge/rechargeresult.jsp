<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><fmt:message key="recharge.recharge.title"/></title>
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li><fmt:message key="recharge.recharge.recharge"/></li>
				<li class="active" id="bill_check"><fmt:message key="recharge.recharge.dbrecharge"/></li>
			</ol>
		</div>
			<div class="mainbox">
				<div class="mainbox">
						<div class="row">
							<form:form id="form" modelAttribute="rechargeModel" action="${ctxPath}/recharge/init" methodParam="post"
								cssClass="form-horizontal">
								<!-- 主键 -->
								<div class="form-group">
									<label class="col-sm-4 control-label"><fmt:message key="recharge.customer"/></label>
									<div class="col-sm-3 select-list" >
										<form:input path="cname" cssClass="form-control" readonly="true"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><fmt:message key="recharge.acid"/></label>
									<div class="col-sm-3 select-list" >
										<form:input path="acname" cssClass="form-control" readonly="true"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><fmt:message key="recharge.ispno"/></label>
									<div class="col-sm-3 select-list" >
										<select id="ispno" name="ispno" disabled="disabled" class="form-control select getfacevalue ispno" >
											<option value="lt" ${'lt' eq rechargeModel.ispno ? 'selected="selected"':''}><fmt:message key="recharge.ispnolt"/></option>
								            <option value="yd" ${'yd' eq rechargeModel.ispno ? 'selected="selected"':''}><fmt:message key="recharge.ispnoyd"/></option>
								            <option value="dx" ${'yd' eq rechargeModel.ispno ? 'selected="selected"':''}><fmt:message key="recharge.ispnodx"/></option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><fmt:message key="recharge.province"/></label>
									<div class="col-sm-3 select-list">
										<form:input path="pname" cssClass="form-control" readonly="true"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><fmt:message key="recharge.ipstype"/></label>
									<div class="col-sm-3 select-list">
										<select id="isptype" name="isptype"  disabled="disabled" class="form-control select getfacevalue ispno" >
											<option value="ll" ${'ll' eq rechargeModel.isptype ? 'selected="selected"':''}><fmt:message key="recharge.ipstypell"/></option>
								            <option value="hf" ${'hf' eq rechargeModel.isptype ? 'selected="selected"':''}><fmt:message key="recharge.ipstypehf"/></option>								          
										</select>
									</div>
								</div>								
								<div class="form-group">
									<label class="col-sm-4 control-label"><fmt:message key="recharge.price"/></label>
									<div class="col-sm-3 select-list" >
									    <form:input path="facevalue" cssClass="form-control" readonly="true"/>									
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><fmt:message key="recharge.phone"/></label>
									<div class="col-sm-3">
										<form:input path="phone" cssClass="form-control" readonly="true" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><fmt:message key="recharge.status"/></label>
									<div class="col-sm-3">
										<select id="ispno" name="ispno" disabled="disabled" class="form-control select getfacevalue ispno" >
											<option value="30" ${'30' eq rechargeModel.status ? 'selected="selected"':''}><fmt:message key="recharge.currbusilog.statuscg"/></option>								          
										</select>
									</div>
								</div>
								<%-- <div class="form-group">
									<label class="col-sm-4 control-label">确认手机号</label>
									<div class="col-sm-3">
										<form:input path="phone" cssClass="form-control" />
									</div>
								</div>	 --%>										
								<div class="form-group">
									<div class="col-sm-offset-4 col-sm-8">									
										<a href="${ctxPath}/recharge/init"><button type="button" class="btn default cancel" ><fmt:message key="button.back"/></button></a>
									</div>
								</div>
							</form:form>
						</div>
					</div> 
			</div>
		</div>		
</body>
</html>