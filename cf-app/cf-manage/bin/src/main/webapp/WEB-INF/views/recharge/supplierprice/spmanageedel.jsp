<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><fmt:message key="recharge.spmanagedel.title"/></title>
<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li><fmt:message key="recharge.spmanage.supplierprice"/></li>
				<li class="active" id="bill_check"><fmt:message key="recharge.spmanagedel.supplierpricedel"/></li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form modelAttribute="supplierpriceModel" action="${ctxPath}/supplierprice/deldata" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<div class="col-sm-4">
							<form:input path="id" type="hidden" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.spmanage.spmanage.name"/></label>
						<div class="col-sm-3">
							<form:input path="pname" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.spmanage.spmanage.cpid"/></label>
						<div class="col-sm-3">
							<form:input path="cpordernos" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group" >
						<label class="col-sm-4 control-label"><fmt:message key="recharge.ispno"/></label>
						<div class="col-sm-3 select-list" >
							<select id="ispno" name="ispno" class="form-control select" disabled="disabled">
								<option value="yd" ${'yd' eq supplierpriceModel.ispno ? 'selected="selected"':''}><fmt:message key="recharge.ispnoyd"/></option>
								<option value="lt" ${'lt' eq supplierpriceModel.ispno ? 'selected="selected"':''}><fmt:message key="recharge.ispnolt"/></option>
								<option value="dx" ${'dx' eq supplierpriceModel.ispno ? 'selected="selected"':''}><fmt:message key="recharge.ispnodx"/></option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.ipstype"/></label>
						<div class="col-sm-3 select-list" >
							<select id="ipstype" name="ipstype" class="form-control select" disabled="disabled">
								<option value="hf" ${'hf' eq supplierpriceModel.ipstype ? 'selected="selected"':''}><fmt:message key="recharge.ipstypehf"/></option>
								<option value="ll" ${'ll' eq supplierpriceModel.ipstype ? 'selected="selected"':''}><fmt:message key="recharge.ipstypell"/></option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.province"/></label>
						<div class="col-sm-3 select-list">
							<select id="pno" name="pno" class="form-control select"  disabled="disabled">
								<c:forEach var="province" items="${supplierpriceModel.provinceList}">
									<option value="${province.pno}" ${province.pno eq supplierpriceModel.pno ? 'selected="selected"':''}>${province.pname}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.price"/></label>
						<div class="col-sm-3">
							<form:input path="size" cssClass="form-control" readonly = "true"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.supplier"/></label>
						<div class="col-sm-3">
							<select id="sid" name="sid" class="form-control select"  disabled="disabled">
								<c:forEach var="supplier" items="${supplierpriceModel.supplierList}" >
									<option value="${supplier.id}" ${supplier.id eq supplierpriceModel.sid ? 'selected="selected"':''}>${supplier.sname}</option>
								</c:forEach>
								
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.supplierprice"/></label>
						<div class="col-sm-3">
							<form:input path="price" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.beginendtime"/></label>
						<div class="col-sm-6">
							<div class="input-append date date1" style="float:left; width:130px;">								 
<!-- 								<span class="span_d"></span> -->
							    <input type="text" id="begintime" disabled="disabled" name = "begintime" class="date_1 form_datetime_long" value="${supplierpriceModel.begintime}" readonly="readonly"  style="margin-top:0">     
							</div>						
							<div class="input-append date date2" style="float:left">
								<span class="span_d"><fmt:message key="recharge.timespot"/></span>
							    <input type="text" id="endtime" disabled="disabled" name = "endtime" class="date_2 form_datetime_long" value="${supplierpriceModel.endtime}" readonly="readonly"  style="margin-top:0"/>
							</div>							
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.status"/></label>
						<div class="col-sm-3 select-list" >
							<select id="status" name="status" class="form-control select" disabled="disabled">
								<option value="kt" ${'kt' eq supplierpriceModel.status ? 'selected="selected"':''}><fmt:message key="recharge.operationkt"/></option>
								<option value="gb" ${'gb' eq supplierpriceModel.status ? 'selected="selected"':''}><fmt:message key="recharge.operationgb"/></option>
							</select>
						</div>
					</div>					
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary"><fmt:message key="button.submit"/></button>
							<a href="${ctxPath}/supplierprice/init"><button type="button" class="btn default cancel" ><fmt:message key="button.cancel"/></button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>