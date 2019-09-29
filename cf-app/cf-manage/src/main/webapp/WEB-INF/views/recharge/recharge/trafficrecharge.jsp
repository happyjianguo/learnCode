<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					<div class="table-tit">
						<div class="tit-left"><fmt:message key="recharge.llrecharge"/></div>
						<div class="tit-btn">
							<span><a href="${ctxPath}/recharge/init"
								class="btn btn-primary"><i class="fa fa-plus"></i><fmt:message key="recharge.hrrecharge"/></a></span>
						</div>
					</div>
					<div class="row">
						<form:form id="form" modelAttribute="rechargeModel"
							action="${ctxPath}/recharge/lladd" methodParam="post"
							cssClass="form-horizontal">

							<div class="form-group">
								<label class="col-sm-4 control-label"><fmt:message key="recharge.customer"/></label>
								<div class="col-sm-3 select-list">
									<select id="cid" name="cid" class="form-control select cid">
										<option value=""><fmt:message key="recharge.pleasechoose"/></option>
										<c:forEach var="access"
											items="${rechargeModel.cfCustomerList}">
											<option value="${access.id }">${access.cname }</option>
										</c:forEach>
									</select>
								</div>
							</div>


							<div class="form-group">
								<label class="col-sm-4 control-label"><fmt:message key="recharge.acid"/></label>
								<div class="col-sm-3 select-list">
									<select id="acid" name="acid" class="form-control select acid">
										<option value=""><fmt:message key="recharge.pleasechoose"/></option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label"><fmt:message key="recharge.ispno"/></label>
								<div class="col-sm-3 select-list">
									<select id="ispno" name="ispno"
										class="form-control select getfacevalue ispno">
										<option value=""><fmt:message key="recharge.pleasechoose"/></option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label"><fmt:message key="recharge.province"/></label>
								<div class="col-sm-3 select-list">
									<select id="province" name="province"
										class="form-control select getfacevalue province">
										<option value=""><fmt:message key="recharge.pleasechoose"/></option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label"><fmt:message key="recharge.price"/></label>
								<div class="col-sm-3 select-list">
									<select id="facevalue" name="facevalue"
										class="form-control select facevalue">
										<option value=""><fmt:message key="recharge.pleasechoose"/></option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label"><fmt:message key="recharge.phone"/></label>
								<div class="col-sm-3">
									<form:input path="phone" cssClass="form-control" />
								</div>
							</div>
							<%-- 							<div class="form-group">
								<label class="col-sm-4 control-label">确认手机号</label>
								<div class="col-sm-3">
									<form:input path="phone" cssClass="form-control" />
								</div>
							</div>			 --%>
							<div class="form-group">
								<div class="col-sm-offset-4 col-sm-8">
									<button type="submit" class="btn btn-primary"><fmt:message key="button.submit"/></button>
									<a href="${ctxPath}/recharge/llrecharge"><button type="button"
											class="btn default cancel"><fmt:message key="button.cancel"/></button></a>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
			<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
				<script type="text/javascript">
				$(document).ready( function () {
				  	//接入者初始化
				    $(".form-group").on("change",".cid",function(){
				    	getacid();
				    });
				  	//运营商初始化
			    	$(".form-group").on("change",".acid",function(){
			    		//getfaceValue();
			    		getIpsnoValue();
			    	});
			    	//省份初始化
			    	$(".form-group").on("change",".ispno",function(){
			    		getprovince();
			    	});
			    	//面值初始化
			    	$(".form-group").on("change",".province",function(){
			    		getfaceValue();
			    	});
			    	
			    	//获取接入者
			    	function getacid(){
			    		$.ax({
		            		url  :"${ctxPath}/recharge/ajax/initClient",
		            		data : {cid:$('#cid').val()},
					        successfn : function(result) {
				        		$(".acid").find("option").not("[value='']").remove(); 
					        	$.each(result.data, function(i, item){ 
//	 			        		    if(i==0){
//	 			        		  		$(".acid").append("<option value="+item.id+" selected = 'selected'>"+item.acname+"</option>");
				        		  		
				        		  		//getfaceValue(); 
//	 			        		    }else{
				        				$(".acid").append("<option value="+item.id+">"+item.acname+"</option>");
//	 			        		    }
					        	});  
					        	getIpsnoValue();
					        }
		            	});
			    	}
			    	//获取运营商
				    function getIpsnoValue(){
				    	$.ax({
		            		url  :"${ctxPath}/recharge/ajax/llinitIpsnoValue",
		            		data : {acid:$('#acid').val()},
					        successfn : function(result) { 	
					        	$(".ispno").find("option").not("[value='']").remove(); 
					        	$.each(result.data, function(i, item){
//	 				        		if(i==1){
//	 				        			if(item.ispno == "yd"){
//	 				        				$(".ispno").append("<option value="+item.ispno+" selected = 'selected'>移动</option>");
//	 				        			}else if(item.ispno == "lt"){
//	 				        				$(".ispno").append("<option value="+item.ispno+" selected = 'selected'>联通</option>");
//	 				        			}else if(item.ispno == "dx"){
//	 				        				$(".ispno").append("<option value="+item.ispno+" selected = 'selected'>电信</option>");
//	 				        			}
//	 			        		  	}else{
				        			  	if(item.ispno == "yd"){
					        				$(".ispno").append("<option value="+item.ispno+"><fmt:message key="recharge.ispnoyd"/></option>");
					        			}else if(item.ispno == "lt"){
					        				$(".ispno").append("<option value="+item.ispno+"><fmt:message key="recharge.ispnolt"/></option>");
					        			}else if(item.ispno == "dx"){
					        				$(".ispno").append("<option value="+item.ispno+"><fmt:message key="recharge.ispnodx"/></option>");
					        			}
//	 			        		  	}
					        	});
					        	getprovince();
					        }
		            	}); 
				    }
				    //获取省份
				    function getprovince(){
				    	$.ax({
		            		url  :"${ctxPath}/recharge/ajax/llinitprovinceValue",
		            		data : {acid:$('#acid').val(),ispno:$('#ispno').val()},
					        successfn : function(result) {
					        	$(".province").find("option").not("[value='']").remove();
//	 				        	$(".province").empty()
					        	$.each(result.data, function(i, item){ 
//	 				        		if(i==1){
//	 				        			$(".province").append("<option value="+item.province+" selected = 'selected'>"+item.pname+"</option>");
//	 			        		  	}else{
				        		  		$(".province").append("<option value="+item.province+">"+item.pname+"</option>");
//	 			        		  	}
					        	});
					        	getfaceValue();
					        }
		            	}); 
				    }
			    	//获取面值
				    function getfaceValue(){
			    		if($('#ispno').val() == "" ||$('#province').val() == "" || $('#acid').val() == ""){
			    			$(".facevalue").find("option").not("[value='']").remove(); 
			    		}else{
			    			$.ax({
			            		url  :"${ctxPath}/recharge/ajax/llinitFaceValue",
			            		data : {ispno:$('#ispno').val(),province:$('#province').val(),acid:$('#acid').val()},
						        successfn : function(result) {
						        	$(".facevalue").find("option").not("[value='']").remove(); 
						        	$.each(result.data, function(i, item){ 
//	 					        		  if(i==1){
//	 					        			  $(".facevalue").append("<option value="+item.facevalue+" selected = 'selected'>"+item.facevalue+"</option>");
//	 					        		  }else{
						        			  $(".facevalue").append("<option value="+item.facevalue+">"+item.facevalue+"</option>");
//	 					        		  }
						        	});  
						        }
			            	}); 
			    		}
	 				 }
			    	
			    	

					 $('#form').validate({
							rules : {
								cid : {
									notnull : true,
									required : true
								},
								acid : {
									notnull : true,
									required : true
								},
								ispno : {
									notnull : true,
									required : true
								},
								province : {
									notnull : true,
									required : true
								},
								facevalue : {
									notnull : true,
									required : true
								},
								phone : {
									notnull : true,
									required : true,
									mobile : true
								}
							}
					
					 });
				});
				
				</script>
</body>
</html>
