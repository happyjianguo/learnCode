<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><fmt:message key="recharge.spmanageadd.title"/></title>
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
				<li class="active" id="bill_check"><fmt:message key="recharge.spmanageadd.supplierpriceadd"/></li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="tablebox">
				<div class="row">
					<form:form id="form" modelAttribute="supplierpriceModel" action="${ctxPath}/supplierprice/add" methodParam="post"
						cssClass="form-horizontal">
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.spmanage.spmanage.name"/></label>
							<div class="col-sm-3">
								<form:input path="pname" cssClass="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.spmanage.spmanage.cpid"/></label>
							<div class="col-sm-3">
								<form:input path="cpordernos" cssClass="form-control" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.ispno"/></label>
							<div class="col-sm-3 select-list" >
								<select id="ispno" name="ispno" class="form-control select" >
									<option value="yd" selected><fmt:message key="recharge.ispnoyd"/></option>
									<option value="lt"><fmt:message key="recharge.ispnolt"/></option>
									<option value="dx"><fmt:message key="recharge.ispnodx"/></option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.ipstype"/></label>
							<div class="col-sm-3 select-list" >
								<select id="ipstype" name="ipstype" class="form-control select" >
									<option value="hf" selected><fmt:message key="recharge.ipstypehf"/></option>
									<option value="ll"><fmt:message key="recharge.ipstypell"/></option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.province"/></label>
							<div class="col-sm-3 select-list">
								<select id="pno" name="pno" class="form-control select"  >
									<c:forEach var="province" items="${supplierpriceModel.provinceList}">
										<option value="${province.pno}">${province.pname}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						<%-- <div class="form-group">
							<label class="col-sm-4 control-label">面额</label>
							<div class="col-sm-3">
								<form:input path="size" cssClass="form-control" />
							</div>
						</div> --%>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.price"/></label>
							<div class="col-sm-2" style="padding-right:5px; width:12%">
								<form:input path="size" cssClass="form-control" />
							</div>
							<!-- 单位 -->
							<div class="col-sm-2"  style="padding-left:0; width:13%">
								<select id="company" name="company" class="form-control select"  >
									<option value=''><fmt:message key="recharge.company"/></option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.supplier"/></label>
							<div class="col-sm-3">
								<select id="sid" name="sid" class="form-control select"  >
									<c:forEach var="supplier" items="${supplierpriceModel.supplierList}">
										<option value="${supplier.id}">${supplier.sname}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.supplierprice"/></label>
							<div class="col-sm-3">
								<form:input path="price" cssClass="form-control" id="price"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.beginendtime"/></label>
							<div class="col-sm-6">
								<div class="input-append date date1" style="float:left; width:130px;">								 
								    <input type="text" id="begintime" name = "begintime" class="date_1 form_datetime_nl" value="${supplierpriceModel.begintime}" readonly="readonly"  style="margin-top:0">     
								</div>						
								<div class="input-append date date2" style="float:left">
									<span class="span_d"><fmt:message key="recharge.timespot"/></span>
								    <input type="text" id="endtime" name = "endtime" class="date_2 form_datetime_nl" value="${supplierpriceModel.endtime}" readonly="readonly"  style="margin-top:0"/>
								</div>							
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.status"/></label>
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
								<a href="${ctxPath}/supplierprice/init"><button type="button" class="btn default cancel" ><fmt:message key="button.cancel"/></button></a>
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
					pname : {
						notnull : true,
						required : true,
						isRightfulString : true,
						maxlength : 60
					},
					cpordernos : {
						stringCheck : true,
						notnull : true,
						required : true,
						maxlength : 60
					},
					size : {
						notnull : true,
						required : true,
						facevalue : true,
						maxlength : 16
					},
					price : {
						notnull : true,
						required : true,
						maxlength : 60,
						money:true
					},
					begintime : {
						notnull : true,
						required : true
					},
					endtime : {
						notnull : true,
						required : true,
						compareDate:begintime
					}
				}
			});
			$("#endtime").on("change",function(){
				/*$('#form').valid();*/
				validator.element( "#endtime" ); 
			});
			$("#begintime").on("change",function(){
				/*$('#form').valid();*/
				validator.element( "#endtime" ); 
			});
			
			//convertMoney('price');
			$("#price").on("change",function(){
				convertMoney('price');
			}); 
			$("#budget").on("click",function(){
				convertMoney('price');
			}); 
			
			$("#ipstype").on("change",function(){
				
			});
			/* $("#size").on("click",function(){
				$("#company").find("option").remove(); 
				if($("#ipstype").val()=="ll"){
					$("#company").append("<option value='M'>M</option>");
					$("#company").append("<option value='G'>G</option>");
				}else if($("#ipstype").val()=="hf"){
					$("#company").append("<option value=''>元</option>");
				}
			});
			
			$("#size").on("click",function(){
				$("#company").find("option").remove(); 
				if($("#ipstype").val()=="ll"){
					$("#company").append("<option value='M'>M</option>");
					$("#company").append("<option value='G'>G</option>");
				}else if($("#ipstype").val()=="hf"){
					$("#company").append("<option value=''>元</option>");
				}
			}); */
			$("#ipstype").on("change",function(){
				$("#company").find("option").remove(); 
				if($("#ipstype").val()=="ll"){
					$("#company").append("<option value='M'>M</option>");
					$("#company").append("<option value='G'>G</option>");
				}else if($("#ipstype").val()=="hf"){
					$("#company").append("<option value=''>元</option>");
				}
			});
		//日期选择datepicker(时间范围  now ~ After a long time)
			$(".form_datetime_nl").datepicker({
				format: "yyyy-mm-dd",
			    todayBtn: "linked",
			    language: "zh-CN",
			    autoclose: true,
			   	startDate: "${supplierpriceModel.begintime}",
			   	todayHighlight: true
			});	
		});
	</script>
</body>
</html>