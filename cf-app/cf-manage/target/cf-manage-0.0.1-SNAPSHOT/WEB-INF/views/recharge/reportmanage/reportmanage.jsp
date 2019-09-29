<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><fmt:message key="recharge.reportmanage.title"/></title>
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
				<div class="main-content">
					<div class="list-nav">
						<ol class="breadcrumb">
							<li><fmt:message key="recharge.reportmanage.tongji"/></li>
							<li class="active" id="bill_check"><fmt:message key="recharge.reportmanage.report"/></li>
						</ol>
					</div>
						 
						<div class="mainbox">
							<div class="query">
						  <form:form id="query" method="post" cssClass="btnenable">
								<div class="query-tit"><fmt:message key="recharge.querycondition"/></div>
									<div class="query-text">
										<span><fmt:message key="recharge.customer"/></span>
										<select id="cid" name="cid" class="form-control select cid" >
											<option value=""><fmt:message key="recharge.all"/></option>
												<c:forEach var="customer" items="${reportModel.cfCustomerList}">
											<option value="${customer.id }">${customer.cname }</option>
											</c:forEach>
										</select>
									</div>
									<div class="query-text">
										<span><fmt:message key="recharge.acid"/></span>
										<select id="acid" name="acid" class="form-control select acid" >
											<option value=""><fmt:message key="recharge.all"/></option>
										</select>	
									</div>		
									<div class="query-text">
										<span><fmt:message key="recharge.ispno"/></span> 
										<select id="ispno" name="ispno" class="form-control select" >
											<option value="" selected><fmt:message key="recharge.all"/></option>
											<option value="yd"><fmt:message key="recharge.ispnoyd"/></option>
											<option value="lt"><fmt:message key="recharge.ispnolt"/></option>
											<option value="dx"><fmt:message key="recharge.ispnodx"/></option>
										</select>
									</div>
									<div class="form-group">
										<label class="control-label" style="float:left; width:100px; padding-right:10px"><fmt:message key="recharge.beginendtime"/></label>
										<div class="input-append date date1" style="float:left; width:130px;">								 
										    <input type="text" id="begintime" name = "begintime" class="date_1 form_datetime_long"  readonly="readonly" value="${reportModel.begintime}"  style="margin-top:0">     
										</div>
										
										<div class="input-append date date2" style="float:left">
											<span class="span_d"><fmt:message key="recharge.timespot"/></span>
										    <input type="text" id="endtime" name = "endtime" class="date_2 form_datetime_long"  readonly="readonly" value="${reportModel.endtime}" style="margin-top:0"/>
										</div>																							
									</div>
									<div class="query-btn">
										<button type="button" class="btn btn-primary" id="queryBtn"><fmt:message key="button.query"/></button>
										<a href="${ctxPath}/reportmanage/init" class="btn btn-primary"><fmt:message key="button.reset"/></a>
									</div>
								</form:form>
							</div>
							<div class="tablebox">
							 <div class="table-tit">
								<div class="tit-left" >
									<fmt:message key="recharge.reportmanage.report"/>
								</div>
								<div class="tit-btn">
									<span>
										<button type="submit" class="btn btn-primary" formaction="${ctxPath}/reportmanage/download" formmethod="post" form="query"><fmt:message key="recharge.currbusilog.export"/></button>
									</span>
								</div>
							</div> 
							<table id="table_id" class="tablemain">
								  <thead>
									  <tr class="tit-top">
									    <th scope="col"><fmt:message key="recharge.reportmanage.date"/></th>
									    <th scope="col"><fmt:message key="recharge.customer"/></th>
									    <th scope="col"><fmt:message key="recharge.acid"/></th>
									    <th scope="col"><fmt:message key="recharge.ispno"/></th>
									    <th scope="col"><fmt:message key="recharge.reportmanage.totalapprice"/></th>
									    <th scope="col"><fmt:message key="recharge.reportmanage.totalspprice"/></th>
									    <th scope="col"><fmt:message key="recharge.reportmanage.profit"/></th>
									  </tr>
								  </thead> 
								  <tbody id="tbody"></tbody>
							</table>
							<table id="table_id2" class="tablemain">
								  <thead>
									  <tr class="tit-top">
									    <th scope="col" ><fmt:message key="recharge.reportmanage.intotalappric"/></th>
									    <th scope="col" ><fmt:message key="recharge.reportmanage.intotalspprice"/></th>
									    <th scope="col" ><fmt:message key="recharge.reportmanage.inprofitt"/></th>
									  </tr>
									   <tr >
									    <td scope="col" id = "a1"></td>
									    <td scope="col" id = "a2"></td>
									    <td scope="col" id = "a3"></td>
									  </tr>
								  </thead>
								   
								  <tbody id="tbody"></tbody>
							</table>
							</div>
						</div>								
				</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript" >
			var table;
			$(function() {
                  table = $('#table_id').DataTable({
               	  		  "serverSide": true,//打开后台分页
 			          	  "dataSrc": "aaData", 	  						  					
	 				      "ajax": function (dataModel, callback, settings) {
	 							var obj = serializeForm($('#query'));
	  					   for(var r in obj){
	  		                	eval("dataModel."+r+"=obj."+r);
	  		                } 
	  	                	$.ax({
	  	                		"url":"${ctxPath}/reportmanage/ajax/query",
	  	                		"data" : dataModel,	  							
	  					        successfn : function(result) {		  					        		  					        					        		  					        
		  					           $("#a1").html($.parseJSON(result.data).a1);
		  					           $("#a2").html($.parseJSON(result.data).a2);
		  				    	       $("#a3").html($.parseJSON(result.data).a3);
		  				               callback($.parseJSON(result.data).view);		
		  					 }
	 	                	});
 	                  }, 
					"columns" : [
							{
								'data' : 'querytime'
							}, 
							{
								'data' : 'cname'
							},
							{
								'data' : 'acname'
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
								'data' : 'totalspprice'
							},
							{
								'data' : 'totalapprice'
							},
							{
								'data' : 'profit'
							} 
					 ]
				});
				 
				
				//接入者初始化
			    $(".query-text").on("change","#cid",function(){
			    	getacid();
			    });
				
			  //获取接入者
		    	function getacid(){
		    		$.ax({
	            		url  :"${ctxPath}/reportmanage/ajax/initClient",
	            		data : {cid:$('#cid').val()},
				        successfn : function(result) {
			        		$(".acid").find("option").not("[value='']").remove(); 
				        	$.each(result.data, function(i, item){ 
			        			$(".acid").append("<option value="+item.id+">"+item.acname+"</option>");
				        	});  	
				        }
	            	});
		    	}
		    	 $('#query').validate({
						rules : {
							endtime : {
								required : true,
								maxlength : 60,
								compareDate:begintime
							}
						}
					});
		    	 
		    	 	$("#endtime").on("change",function(){
						/* $('#begintime').valid(); */
						$('#form').validate().element('#endtime');
					}); 
				
		    	 /*条件查询*/
					$(".query-btn").on("click", "#queryBtn", function() {
						 table.ajax.reload();
					});
			}); 
		</script>
</body>
</html>
