<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><fmt:message key="recharge.accessclientedit.title"/></title>
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
				<li class="active" id="bill_check"><fmt:message key="recharge.accessclientedit.accessclientedit"/></li>
			</ol>
		</div>
		<div class="mainbox">
					<div class="tablebox">
			<div class="row">
				<form:form id="editform" modelAttribute="accessclientModel" action="${ctxPath}/accessclient/editdata" methodParam="post"
					cssClass="form-horizontal">
					<!-- 主键 -->
					<form:input path="id" type="hidden"  cssClass="form-control" />
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
					<!-- 是否有密钥权限 -->
					<sec:authorize url="/getrandom/getrandom">
						<c:set var="secflag" value="true"/>
					</sec:authorize>
					<c:choose>
		  				<c:when test="${secflag}">
							<div class="form-group">
								<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.secretkey"/></label>
								<button type="button" id="getkey" class="btn btn-primary"><fmt:message key="recharge.accessclient.secretkeychange"/></button>
								<div class="col-sm-2">
									<form:input path="ackey" id="ackey" cssClass="form-control"  readonly="true" />
								</div>
							</div>
		  				</c:when>
		  				<c:otherwise>
							<form:input path="ackey" type="hidden" cssClass="form-control" />
		  				</c:otherwise>
		  			</c:choose>
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
							<%-- <form:input path="budget" cssClass="form-control"/> --%>
 							<form:input path="budget" cssClass="form-control" id="budget"/>
<!-- 							<input type="hidden" id="amount" name="amount"> -->
						</div>
					</div>	
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessclient.status"/></label>
						<div class="col-sm-3 select-list" >
							<select id="status" name="status" class="form-control select" >
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
							<button type="button" id="ok" class="btn btn-primary"><fmt:message key="button.submit"/></button>
							<a href="${ctxPath}/accessclient/init"><button type="button" class="btn default cancel" ><fmt:message key="button.cancel"/></button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		</div>
	</div>
	<cx:script>
	<script type="text/javascript">
		//新增管理员表单验证，提交
		$(document).ready( function () {
			var validator = $('#editform').validate({
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
						money:true
					},
					ackey : {
						isRightfulString : true,
						notnull : true,
						required : true,
						maxlength : 60
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
				$('#editform').validate().element('#endtime');
			}); 
			$("#begintime").on("change",function(){
				/*$('#form').valid();*/
				validator.element( "#endtime" ); 
			});
			
			$(".form-group").on("click", "#ok", function() {
				$("#cid").removeAttr("disabled"); 
				$("#editform").submit();
			});
			convertMoney('budget');
			$("#budget").on("change",function(){
				convertMoney('budget');
			}); 
			$("#budget").on("click",function(){
				convertMoney('budget');
			}); 
			$(".form-group").on("click", "#getkey", function() {
				//获取面值
		    	$.ax({
            		url  :"${ctxPath}/getrandom/getrandom",
			        successfn : function(result) { 	
			        	$("#ackey").val(result.data);
			        }
            	}); 
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
	</cx:script>
</body>
</html>