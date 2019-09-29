<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><fmt:message key="recharge.accesspriceedit.title"/></title>
<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li><fmt:message key="recharge.accessprice.accessprice"/></li>
				<li class="active" id="bill_check"><fmt:message key="recharge.accesspriceedit.accesspriceadd"/></li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form id="form" modelAttribute="accesspriceModel" action="${ctxPath}/accprice/editdata" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<div class="col-sm-4">
							<form:input path="apid" type="hidden" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.acid"/></label>
						<div class="col-sm-3 select-list" >
							<select id="acid" name="acid" class="form-control select" disabled="disabled">
								<c:forEach var="access" items="${accesspriceModel.priceList }">
									<option value="${access.id }" ${access.id eq accesspriceModel.acid ? 'selected="selected"':''}>${access.acname }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.ispno"/></label>
						<div class="col-sm-3 select-list" >
							<select id="ispno" name="ispno" class="form-control select" disabled="disabled">
								<option value="yd" ${'yd' eq accesspriceModel.ispno ? 'selected="selected"':''}><fmt:message key="recharge.ispnoyd"/></option>
								<option value="lt" ${'lt' eq accesspriceModel.ispno ? 'selected="selected"':''}><fmt:message key="recharge.ispnolt"/></option>
								<option value="dx" ${'dx' eq accesspriceModel.ispno ? 'selected="selected"':''}><fmt:message key="recharge.ispnodx"/></option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessprice.ipstype"/></label>
						<div class="col-sm-3 select-list" >
							<select id="ipstype" name="ipstype" class="form-control select" disabled="disabled">
								<option value="hf" ${'hf' eq accesspriceModel.ipstype ? 'selected="selected"':''}><fmt:message key="recharge.ipstypehf"/></option>
								<option value="ll" ${'ll' eq accesspriceModel.ipstype ? 'selected="selected"':''}><fmt:message key="recharge.ipstypell"/></option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.province"/></label>
						<div class="col-sm-3 select-list">
							<select id="province" name="province" class="form-control select"  disabled="disabled">
								<c:forEach var="province" items="${accesspriceModel.provinceList}">
									<option value="${province.pno}" ${province.pno eq accesspriceModel.province ? 'selected="selected"':''}>${province.pname}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessprice.price"/></label>
						<div class="col-sm-3">
							<form:input path="facevalue" cssClass="form-control" readonly = "true"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessprice.offer"/></label>
						<div class="col-sm-3">
							<form:input path="price" cssClass="form-control" id="price"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.status"/></label>
						<div class="col-sm-3 select-list" >
							<select id="status" name="status" class="form-control select" >
								<option value="kt" ${'kt' eq accesspriceModel.status ? 'selected="selected"':''}><fmt:message key="recharge.operationkt"/></option>
								<option value="gb" ${'gb' eq accesspriceModel.status ? 'selected="selected"':''}><fmt:message key="recharge.operationgb"/></option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="button" id="ok" class="btn btn-primary"><fmt:message key="button.submit"/></button>
							<a href="${ctxPath}/accprice/init"><button type="button" class="btn default cancel" ><fmt:message key="button.cancel"/></button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<cx:script>
	<script type="text/javascript">
	$(document).ready( function () {
		var a = $('#form').validate({
			rules : {
				ispno : {
					notnull : true,
					required : true
				},
				ipstype : {
					notnull : true,
					required : true
				},
				province : {
					notnull : true,
					required : true
				},
				facevalue : {
					notnull : true,
					required : true,
					facevalue : true
				},
				price : {
					notnull : true,
					required : true,
					maxlength : 60,
					money:true
				},
			}
		});
		$("#endtime").on("change",function(){
			$('#form').valid();
		});
		
		$(".form-group").on("click", "#ok", function() {
			$("#acid").removeAttr("disabled"); 
			$("#ispno").removeAttr("disabled"); 
			$("#ipstype").removeAttr("disabled");
			$("#province").removeAttr("disabled");
			$("#form").submit();
		});
		
		
		convertMoney('price');
		$("#price").on("change",function(){
			convertMoney('price');
		}); 
		$("#budget").on("click",function(){
			convertMoney('price');
		}); 
	});
	</script>
	</cx:script>
</body>
</html>