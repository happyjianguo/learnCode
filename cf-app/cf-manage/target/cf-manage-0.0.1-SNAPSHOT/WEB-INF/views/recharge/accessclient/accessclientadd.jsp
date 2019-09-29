<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><fmt:message key="recharge.accessclientadd.title"/></title>
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
				<li class="active" id="bill_check"><fmt:message key="recharge.accessclientadd.accessclientadd"/></li>
			</ol>
		</div>
		<div class="mainbox">
					<div class="tablebox">
			<div class="row">
				<form:form id="form" modelAttribute="accessclientModel" action="${ctxPath}/accessclient/adddata" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.customer"/></label>
						<div class="col-sm-3 select-list" >
							<select id="cid" name="cid" class="form-control select" >
								<c:forEach var="access" items="${accessclientModel.cfCustomerList }">
									<option value="${access.id }">${access.cname }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.acid"/></label>
						<div class="col-sm-3">
							<form:input id="acname" path="acname" cssClass="form-control" />
							<!-- <input type="email" class="form-control" id="inputEmail3" placeholder="Email"> -->
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.contactsname"/></label>
						<div class="col-sm-3">
							<form:input path="mname" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.contactsphone"/></label>
						<div class="col-sm-3">
							<form:input path="phone" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.contactsemail"/></label>
						<div class="col-sm-3">
							<form:input path="email" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.callbackurl"/></label>
						<div class="col-sm-3">
							<form:input path="callbackurl" cssClass="form-control" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.beginendtime"/></label>
						<div class="col-sm-6">
							<div class="input-append date date1" style="float:left; width:130px;">								 
<!-- 								<span class="span_d"></span> -->
							    <input type="text" id="begintime" name = "begintime" class="date_1 form_datetime_nl" value="${accessclientModel.begintime}" readonly="readonly"  style="margin-top:0">     
							</div>						
							<div class="input-append date date2" style="float:left">
								<span class="span_d"><fmt:message key="recharge.accessclient.timespot"/></span>
							    <input type="text" id="endtime" name = "endtime" class="date_2 form_datetime_nl" value="${accessclientModel.endtime}" readonly="readonly"  style="margin-top:0"/>
							</div>							
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.budget"/></label>
						<div class="col-sm-3">
							<%-- <form:input path="budget" cssClass="form-control" /> --%>
							<form:input path="budget" cssClass="form-control" id="budget"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.status"/></label>
						<div class="col-sm-3 select-list" >
							<select id="status" name="status" class="form-control select" >
								<option value="kt" selected><fmt:message key="recharge.operationkt"/></option>
								<option value="gb"><fmt:message key="recharge.operationgb"/></option>
							</select>
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
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<script type="text/javascript">
		$(document).ready( function () {
			var validator = $('#form').validate({
				rules : {
					acname : {
						isRightfulString : true,
						notnull : true,
						required : true,
						maxlength : 60
					},
					cid : {						
						notnull : true,
						required : true,
						maxlength : 60
					},
					mname : {
						isRightfulString : true,
						notnull : true,
						required : true,
						maxlength : 60
					},
					begintime : {
						notnull : true,
						required : true,
						maxlength : 60
					},
					endtime : {
						notnull : true,
						required : true,
						maxlength : 60,
						compareDate:begintime
					},
					status : {
						notnull : true,
						required : true,
						maxlength : 60
					},
					budget : {
						notnull : true,
						required : true,
						money:true,					
					},
					callbackurl:{
						notnull : true,
						required : true,
						url : true,
						maxlength : 800
					},
					
					cumulative : {
						notnull : true,
						required : true,
						maxlength : 60,
						num:true
					},
					phone : {
						notnull : true,
						required : true,
						mobile : true
					},
					email : {
						notnull : true,
						required : true,
						email : true,
						maxlength:40
					}
				}
			});
			$("#endtime").on("change",function(){
				/* $('#begintime').valid(); */
				$('#form').validate().element('#endtime');
			}); 
			$("#begintime").on("change",function(){
				/*$('#form').valid();*/
				validator.element( "#endtime" ); 
			});
			//convertMoney('budget');
			$("#budget").on("change",function(){
				convertMoney('budget');
			}); 
			$("#budget").on("click",function(){
				convertMoney('budget');
			}); 
			//日期选择datepicker(时间范围  now ~ After a long time)
			$(".form_datetime_nl").datepicker({
				format: "yyyy-mm-dd",
			    todayBtn: "linked",
			    language: "zh-CN",
			    autoclose: true,
			   	startDate: "${accessclientModel.begintime}",
			   	todayHighlight: true
			});	
		});
	</script>
</body>
</html>