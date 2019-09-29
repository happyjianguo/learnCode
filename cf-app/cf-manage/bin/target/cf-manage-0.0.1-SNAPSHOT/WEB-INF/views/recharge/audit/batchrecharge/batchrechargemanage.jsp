<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><fmt:message key="recharge.audit.batchrechge.title"/></title>
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
				<div class="main-content">
					<div class="list-nav">
						<ol class="breadcrumb">
							<li><fmt:message key="recharge.audit.audit"/></li>
							<li class="active" id="bill_check"><fmt:message key="recharge.audit.batchrechge"/></li>
						</ol>
					</div>
						<div class="mainbox">
							<div class="table-tit">
								<div class="tit-left" >
									<fmt:message key="recharge.audit.batchrechge"/>
								</div>
								<div class="tit-btn">
<!-- 								<span><button type="button" class="btn btn-primary addImuser" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i>新增</button></span> -->
 									<%-- <span><a href="${ctxPath}/batchrecharge/reviewinit" class="btn btn-primary"><i class="fa fa-plus"></i><fmt:message key="recharge.audit.batchrechge.pc"/></a></span>  --%>
								
								</div>											
							</div>
							<div class="mainbox">
									<div class="row">
										<form:form id="form" modelAttribute="batchinfoModel" action="${ctxPath}/audit/yes" methodParam="post" cssClass="form-horizontal">
											<form:input path="batchid" type="hidden" readonly="true" />
											<input id = "auditid" name="auditid" type="hidden" value="${auditid}">
											<div class="form-group">
												<label class="col-sm-4 control-label"><fmt:message key="recharge.audit.batchrechge.pcname"/></label>
												<div class="col-sm-3">
													<form:input path="batchname" cssClass="form-control" readonly = "true"/>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-4 control-label"><fmt:message key="recharge.customer"/></label>
												<div class="col-sm-3 select-list" >
													<input id = "cname" name="cname" type="text" value="${cname}" class="form-control" readonly = "true">
												</div>
											</div>	
											<div class="form-group">
											<label class="col-sm-4 control-label"><fmt:message key="recharge.acid"/></label>
												<div class="col-sm-3 select-list" >
													<input id = "acname" name="acname" type="text" value="${acname}" class="form-control" readonly = "true">
												</div>
											</div>
											
											<%-- <c:choose>
											   <c:when test="${ auditstatus eq '1'}"> 
											   		<div class="form-group">
														<label class="col-sm-4 control-label">意见</label>
														<div class="col-sm-3">
															<input id = "opinion" name="opinion" type="text" value="${opinion}" class="form-control">
														</div>
													</div>
													<div class="form-group">
														<div class="col-sm-offset-4 col-sm-8">
															<button type="button" id="ok" class="btn btn-primary ok">同意</button>
															<button type="button" id="no" class="btn btn-primary no">不同意</button>
															<a href="${ctxPath}/audit/init"><button type="button" class="btn default cancel" >取消</button></a>
														</div>
													</div>
											   </c:when>   
											   <c:otherwise>
											   		<div class="form-group">
														<label class="col-sm-4 control-label">意见</label>
														<div class="col-sm-3">
															<input id = "opinion" name="opinion" type="text" value="${opinion}" disabled="disabled" class="form-control">
														</div>
													</div>
											   </c:otherwise>  
											</c:choose> --%>
											<c:choose>
							<c:when test="${SPRING_SECURITY_CONTEXT.authentication.principal.userid eq draftman}">
								<c:choose>
								   <c:when test="${auditstatus eq '1'}"> 
								   </c:when>
								   <c:otherwise>
								   		<div class="form-group">
											<label class="col-sm-4 control-label"><fmt:message key="recharge.audit.advice"/></label>
											<div class="col-sm-3">
												<input id = "opinion" name="opinion" type="text" value="${opinion}" disabled="disabled" class="form-control">
											</div>
										</div>
								   </c:otherwise> 
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
								   	<c:when test="${auditstatus eq '1'}"> 
								   		<sec:authorize url="/audit/init">
								   		<div class="form-group">
											<label class="col-sm-4 control-label"><fmt:message key="recharge.audit.advice"/></label>
											<div class="col-sm-3">
												<input id = "opinion" name="opinion" type="text" value="${opinion}" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-4 col-sm-8">
												<button type="button" id="ok" class="btn btn-primary ok"><fmt:message key="recharge.audit.adviceok"/></button>
												<button type="button" id="no" class="btn btn-primary no"><fmt:message key="recharge.audit.adviceno"/></button>
												<a href="${ctxPath}/audit/init"><button type="button" class="btn default cancel" ><fmt:message key="button.cancel"/></button></a>
											</div>
										</div>
										</sec:authorize>
								   </c:when>
								   <c:otherwise>
								   		<div class="form-group">
											<label class="col-sm-4 control-label"><fmt:message key="recharge.audit.advice"/></label>
											<div class="col-sm-3">
												<input id = "opinion" name="opinion" type="text" value="${opinion}" disabled="disabled" class="form-control">
											</div>
										</div>
								   </c:otherwise>  
								</c:choose>
							</c:otherwise>
						</c:choose>
										</form:form>
										<div class="tablebox">
											<div class="table-tit">
												<div class="tit-left" >
													<fmt:message key="recharge.audit.batchrechge.batchrechargeinfo"/>
												</div>
											</div> 
											<table id="table_id" class="tablemain">
												  <thead>
													  <tr class="tit-top">
													  	<th scope="col"><fmt:message key="recharge.audit.batchrechge.id"/></th>
													    <th scope="col"><fmt:message key="recharge.ispno"/></th>
													    <th scope="col"><fmt:message key="recharge.accessprice.ipstype"/></th>
													    <th scope="col"><fmt:message key="recharge.accessprice.price"/></th>
													    <th scope="col"><fmt:message key="recharge.phone"/></th>
													  </tr>
												  </thead>
												  <tbody id="tbody"></tbody>
											</table>
										</div>
									</div>
								</div> 	
						</div>
					</div>			
	
	<cx:script>
		<script type="text/javascript">
			$(document).ready( function () {
				//接入者初始化
			    $(".form-group").on("change",".cid",function(){
			    	getacid();
			    });
				//获取接入者
		    	function getacid(){
		    		$.ax({
		        		url  :"${ctxPath}/audit/ajax/initClient",
		        		data : {cid:$('#cid').val()},
				        successfn : function(result) {
				        	
			        		$(".acid").find("option").not("[value='']").remove(); 
				        	$.each(result.data, function(i, item){
			        			$(".acid").append("<option value="+item.id+">"+item.acname+"</option>");
				        	});  
				        }
		        	});
		    	}
				
		    	table = $('#table_id').DataTable({
					"ajax": function (data, callback, settings) {
	                	$.ax({
	                		"url":"${ctxPath}/audit/ajax/batchrecharge",
	                		"data" : {batchid:"${batchinfoModel.batchid}"},
					        successfn : function(result) {
					        	callback(result);
					        }
	                	});
	                  },
					"columns" : [
							{
								'data' : 'id' ,'visible' : false
							},
							{
								'data' : function(obj) {
									var sReturn = obj.ispno;
									if (sReturn == "lt") {
										sReturn = "<fmt:message key="recharge.ispnolt"/>";
									} else if (sReturn == "yd") {
										sReturn = "<fmt:message key="recharge.ispnoyd"/>";
									} else if(sReturn == "dx"){
										sReturn = "<fmt:message key="recharge.ispnodx"/>";
									}
									
									return sReturn;
								}
							},
							{
								'data' : function(obj) {
									var sReturn = obj.isptype;
									if (sReturn == "ll") {
										sReturn = "<fmt:message key="recharge.ipstypell"/>";
									} else if (sReturn == "hf") {
										sReturn = "<fmt:message key="recharge.ipstypehf"/>";
									} 
									return sReturn;
								}
							},
							{
								'data' : 'facevalue'
							},
							{
								'data' : 'phone'
							}
					 	]
					});
				
		    	$('#form').validate({
					rules : {
						batchid : {
						/* 	checkcustomername : true, */
							notnull : true,
							required : true
						},
						batchname : {
							notnull : true,
							required : true
						},
						cid : {
							notnull : true,
							required : true
						},
						apno : {
							notnull : true,
							required : true
						},
						myfile : {
							notnull : true,
							required : true
						},
						opinion : {
							maxlength : 60
						}	
					}
				});
		    	
		    	$(".form-group").on("click", "#ok", function() {
					$("#form").attr("action","${ctxPath}/audit/yes");
					$("#form").submit();
				});
				$(".form-group").on("click", "#no", function() {
					$("#form").attr("action","${ctxPath}/audit/no");
					$("#form").submit();
				});
			});
		</script>
	</cx:script>
</body>
</html>