<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><fmt:message key="recharge.accesspriceadd.title"/></title>
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
				<li class="active" id="bill_check"><fmt:message key="recharge.accesspriceadd.accesspriceadd"/></li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form id="form" modelAttribute="accesspriceModel" action="${ctxPath}/accprice/add" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.customer"/></label>
						<div class="col-sm-3 select-list" >
							<select id="cid" name="cid" class="form-control select cid" >
								<option value=""><fmt:message key="recharge.pleasechoose"/></option>
								<c:forEach var="access" items="${accesspriceModel.cfCustomerList}">
									<option value="${access.id }">${access.cname }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.acid"/></label>
						<div class="col-sm-3 select-list" >
							<select id="acid" name="acid" class="form-control select acid" >
								<option value=""><fmt:message key="recharge.pleasechoose"/></option>
								<c:forEach var="access" items="${accesspriceModel.priceList }">
									<option value="${access.id }">${access.acname }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.ispno"/></label>
						<div class="col-sm-3 select-list" >
							<select id="ispno" name="ispno" class="form-control select getfacevalue ispno" >
								<option value=""><fmt:message key="recharge.pleasechoose"/></option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessprice.ipstype"/></label>
						<div class="col-sm-3 select-list" >
							<select id="ipstype" name="ipstype" class="form-control select ipstype" >
								<option value=""><fmt:message key="recharge.pleasechoose"/></option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.province"/></label>
						<div class="col-sm-3 select-list">
							<select id="province" name="province" class="form-control select getfacevalue province"  >
							<option value=""><fmt:message key="recharge.pleasechoose"/></option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><fmt:message key="recharge.accessprice.price"/></label>
						<div class="col-sm-3 select-list" >
							<select id="facevalue" name="facevalue" class="form-control select facevalue" >
								<option value=""><fmt:message key="recharge.pleasechoose"/></option>
							</select>	
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
								<option value="kt" selected><fmt:message key="recharge.operationkt"/></option>
								<option value="gb"><fmt:message key="recharge.operationgb"/></option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary"><fmt:message key="button.submit"/></button>
							<a href="${ctxPath}/accprice/init"><button type="button" class="btn default cancel" ><fmt:message key="button.cancel"/></button></a>
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
	    	getIpsno();
			//冲值类型初始化
	    	$(".form-group").on("change",".ispno",function(){
	    		initIpsType();
	    	});
	    	//省份初始化
	    	$(".form-group").on("change",".ipstype",function(){
	    		initprovince();
	    	});
	    	//面值初始化
	    	$(".form-group").on("change",".province",function(){
	    		initFaceValue();
	    	});
	    	
	    	//获取接入者
	    	function getacid(){
	    		$.ax({
            		url  :"${ctxPath}/accprice/ajax/initClient",
            		data : {cid:$('#cid').val()},
			        successfn : function(result) {
		        		$(".acid").find("option").not("[value='']").remove(); 
			        	$.each(result.data, function(i, item){ 
		        			$(".acid").append("<option value="+item.id+">"+item.acname+"</option>");
			        	});  
			        	getIpsnoValue();
			        }
            	});
	    	}
			//获取运营商
		    function getIpsno(){
		    	$.ax({
            		url  :"${ctxPath}/accprice/ajax/initIpsno",
			        successfn : function(result) { 	
			        	$(".ispno").find("option").not("[value='']").remove(); 
			        	$.each(result.data, function(i, item){
	        			  	if(item == "yd"){
		        				$(".ispno").append("<option value="+item+"><fmt:message key="recharge.ispnoyd"/></option>");
		        			}else if(item == "lt"){
		        				$(".ispno").append("<option value="+item+"><fmt:message key="recharge.ispnolt"/></option>");
		        			}else if(item == "dx"){
		        				$(".ispno").append("<option value="+item+"><fmt:message key="recharge.ispnodx"/></option>");
		        			}
			        	});
			        	initIpsType();
			        }
            	}); 
		    }
			
		  	//获取冲值类型
		    function initIpsType(){
		    	$.ax({
            		url  :"${ctxPath}/accprice/ajax/initIpsType",
            		data : {ispno:$('#ispno').val()},
			        successfn : function(result) { 	
			        	$(".ipstype").find("option").not("[value='']").remove(); 
			        	$.each(result.data, function(i, item){
	        			  	if(item == "hf"){
		        				$(".ipstype").append("<option value="+item+"><fmt:message key="recharge.ipstypehf"/></option>");
		        			}else if(item == "ll"){
		        				$(".ipstype").append("<option value="+item+"><fmt:message key="recharge.ipstypell"/></option>");
		        			}
			        	});
			        	initprovince();
			        }
            	}); 
		    }
		  	//获取省份
		    function initprovince(){
		    	$.ax({
            		url  :"${ctxPath}/accprice/ajax/initprovince",
            		data : {ispno:$('#ispno').val(),ipstype:$('#ipstype').val()},
			        successfn : function(result) { 	
			        	$(".province").find("option").not("[value='']").remove(); 
			        	$.each(result.data, function(i, item){
			        		$(".province").append("<option value="+item.pno+">"+item.pname+"</option>");
			        	});
			        	initFaceValue();
			        }
            	}); 
		    }
		    //获取面值
		    function initFaceValue(){
		    	$.ax({
            		url  :"${ctxPath}/accprice/ajax/initFaceValue",
            		data : {ispno:$('#ispno').val(),ipstype:$('#ipstype').val(),province:$('#province').val()},
			        successfn : function(result) { 	
			        	$(".facevalue").find("option").not("[value='']").remove(); 
			        	$.each(result.data, function(i, item){
			        		$(".facevalue").append("<option value="+item+">"+item+"</option>");
			        	});
			        }
            	}); 
		    }
		    
			
			var a = $('#form').validate({
				rules : {
					cid: {
						notnull : true,
						required : true
					},
					acid: {
						notnull : true,
						required : true
					},
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
					}
				}
			});
			$("#endtime").on("change",function(){
				var validator = $( "#form" ).validate();
				validator.element( "#myselect" ); 
			});
			 
			$("#price").on("change",function(){
				convertMoney('price');
			}); 
			$("#budget").on("click",function(){
				convertMoney('price');
			});
		});
	</script>
</body>
</html>