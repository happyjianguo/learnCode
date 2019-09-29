<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><fmt:message key="recharge.batchcurrbusilog.title"/></title>
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li><fmt:message key="recharge.batchcurrbusilog.recharge"/></li>
				<li class="active" id="bill_check"><fmt:message key="recharge.batchcurrbusilog.batchcurrbusilog"/></li>
			</ol>
		</div>
				 
						<div class="mainbox">
							<div class="query">
						  <form:form id="query" method="post" >
								<div class="query-tit"><fmt:message key="recharge.querycondition"/></div>
								<div class="query-text">
									<span><fmt:message key="recharge.customer"/></span>
									<div class="col-sm-2 select-list" >
										<select id="cid" name="cid" class="form-control select cid" >
											<option value=""><fmt:message key="recharge.all"/></option>
												<c:forEach var="customer" items="${batchcurrbusilogModel.customerlist}">
												<option value="${customer.id }">${customer.cname }</option>
												</c:forEach>
										</select>
									</div>
								</div>
								<div class="query-text">
									<span><fmt:message key="recharge.acid"/></span>
									<div class="col-sm-2 select-list" >
										<select id="apno" name="apno" class="form-control select apno" >
											<option value=""><fmt:message key="recharge.all"/></option>
										</select>	
									</div>
								</div>
								<div class="query-text">
									<span><fmt:message key="recharge.batchcurrbusilog.pcname"/></span> 
									<div class="col-sm-2 select-list" >
										<select id="batchid" name="batchid" class="form-control select batchid" >
											<option value=""><fmt:message key="recharge.all"/></option>
										</select>	
									</div>
								</div>
								
								<div class="query-text">
									<span><fmt:message key="recharge.batchcurrbusilog.handlestatus"/></span> 
									<div class="col-sm-2 select-list" >
										<select id="status" name="status" class="form-control select status" >
											<option value="" selected><fmt:message key="recharge.all"/></option>
										</select>	
									</div>
								</div>
								<div class="query-btn">
									<button type="button" class="btn btn-primary" id="queryBtn"><fmt:message key="button.query"/></button>
									<button type="button" class="btn btn-primary" id="valueReset"><fmt:message key="button.reset"/></button>
								</div>													
							</form:form>							
						</div>													
						<div class="tablebox">	 
							 <div class="table-tit">
								<div class="tit-left" >
									<fmt:message key="recharge.batchcurrbusilog.batchcurrbusilog"/>
								</div>
							</div> 
							<table id="table_id" class="tablemain">
								  <thead>
									  <tr class="tit-top">
									  	<th scope="col"><fmt:message key="recharge.batchcurrbusilog.bizid"/></th>
									    <th scope="col"><fmt:message key="recharge.batchcurrbusilog.id"/></th>
									    <th scope="col"><fmt:message key="recharge.customer"/></th>
									    <th scope="col"><fmt:message key="recharge.acid"/></th>
									    <th scope="col"><fmt:message key="recharge.batchcurrbusilog.pcno"/></th>
									    <th scope="col"><fmt:message key="recharge.batchcurrbusilog.pcname"/></th>
									    <th scope="col"><fmt:message key="recharge.phone"/></th>
									    <th scope="col"><fmt:message key="recharge.ispno"/></th>
									    <th scope="col"><fmt:message key="recharge.ipstype"/></th>
									    <th scope="col"><fmt:message key="recharge.price"/></th>
									    <th scope="col"><fmt:message key="recharge.batchcurrbusilog.acid"/></th>
									    <th scope="col"><fmt:message key="recharge.sendstatus"/></th> 
									  </tr>
								  </thead>
								  <tbody id="tbody"></tbody>
							</table>
						</div>
					</div>								
				</div>
<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript" >
		$(document).ready( function () {
		  	//接入者初始化
		    $(".query-text").on("change","#cid",function(){
		    	getapno();
		    });
		  	//批次编号初始化
	    	$(".query-text").on("change","#apno",function(){
	    		//getfaceValue();
	    		getbatchid();
	    	});
	    	//处理状态初始化
	    	$(".query-text").on("change","#batchid",function(){
	    		getstatus();
	    	});
	    	$("#valueReset").click(function(){
	    		$("#query option").prop("selected",false);
	    	});
	    	
	    	//获取接入者
	    	function getapno(){
	    		$.ax({
            		url  :"${ctxPath}/batchcurrbusilog/ajax/initClient",
            		data : {cid:$('#cid').val()},
			        successfn : function(result) {
		        		$(".apno").find("option").not("[value='']").remove(); 
			        	$.each(result.data, function(i, item){ 
		        			$(".apno").append("<option value="+item.id+">"+item.acname+"</option>");
			        	});  
			        	getbatchid();
			        }
            	});
	    	}
	    	//获取批次编号
		    function getbatchid(){
		    	$.ax({
            		url  :"${ctxPath}/batchcurrbusilog/ajax/initbatchid",
            		data : {apno:$('#apno').val()},
			        successfn : function(result) { 	
			        	$(".batchid").find("option").not("[value='']").remove(); 
			        	$.each(result.data, function(i, item){
			        		$(".batchid").append("<option value="+item.batchid+">"+item.batchname+"</option>");
			        	});
			        	getstatus();
			        }
            	}); 
		    }
		    //获取状态
		    function getstatus(){
		    	$.ax({
            		url  :"${ctxPath}/batchcurrbusilog/ajax/initstatus",
            		data : {batchid:$('#batchid').val()},
			        successfn : function(result) {
			        	$(".status").find("option").not("[value='']").remove();
			        	$.each(result.data, function(i, item){ 
		        			if(item.status == "21"){
		        				$(".status").append("<option value="+item.status+">待审批</option>");
		        			}else if(item.status == "22"){
		        				$(".status").append("<option value="+item.status+">审批通过</option>");
		        			}else if(item.status == "23"){
		        				$(".status").append("<option value="+item.status+">审批失败</option>");
		        			}
			        	});
			        }
            	}); 
		    }
		    
		    var table;
			table = $('#table_id').DataTable({
			        "serverSide": true,//打开后台分页
			        "dataSrc": "aaData", 
					"ajax": function (dataModel, callback, settings) {
						var obj = serializeForm($('#query'));
						for(var r in obj){
		                	eval("dataModel."+r+"=obj."+r);
		                } 
						$.ax({
	                		"url":"${ctxPath}/batchcurrbusilog/ajax/query",
	                		"data" : dataModel,
					        successfn : function(result) {
					        	callback($.parseJSON(result.data));
					        }
	                	});
	                  },
					"columns" : [
							{
								'data' : 'bizid'
							},
							{
								'data' : 'id' ,'visible' : false
							},
							{
								'data' : 'cname'
							},
							{
								'data' : 'acname'
							},
							{
								'data' : 'batchid'
							},
							{
								'data' : 'batchname'
							},
							{
								'data' : 'phone'
							},
							
							{
								'data' :  function(obj) {
									var sReturn = obj.ispno;
									if (sReturn == "lt") {
										sReturn = "联通";
									} else if (sReturn == "yd") {
										sReturn = "移动";
									} else if(sReturn == "dx"){
										sReturn = "电信";
									}
									
									return sReturn;
								}
							},
							{
								'data' : function(obj) {
									var sReturn = obj.isptype;
									if (sReturn == "ll") {
										sReturn = "流量";
									} else if (sReturn == "hf") {
										sReturn = "话费";
									} 
									return sReturn;
								} 
							},
							
							
							{
								'data' : 'facevalue'
							},
							{
								'data' : 'apno','visible' : false
							},
							{
								'data' : function(obj) {
									var sReturn = obj.status;
									if (sReturn == "33") {
										sReturn = "发送成功";
									}else if (sReturn == "21") {
										sReturn = "待审批";
									} else if (sReturn == "22") {
										sReturn = "审批通过";
									}else if (sReturn == "23") {
										sReturn = "审批失败";
									}
									return sReturn;
								}
							}
					 ]
				});
				
				 /** 输入校验  */
				 /*
		    	 $('#query').validate({
						rules : {
							
							phone : {
								
								mobile : true
							}
						}
					});
				 */
		    	 /*条件查询*/
					$(".query-btn").on("click", "#queryBtn", function() {
						 table.ajax.reload();
					});
			    	 /* 内容重置 */
			    	$("#valueReset").click(function(){			    		 
			    		 $('#query').validate().resetForm();
			    		 $("#query option").prop("selected",false);		    			    		 
			    		 $("#apno option").hide();		
			    		 $("#apno").find("option[value='']").prop("selected",true);	
			    		 $("#batchid option").hide();		
			    		 $("#batchid").find("option[value='']").prop("selected",true);	
			    		 $("#status option").hide();		
			    		 $("#status").find("option[value='']").prop("selected",true);	
			    	 });
			});
			 
		</script>
</body>
</html>