<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><fmt:message key="recharge.audit.title"/></title>
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
				<li class="active" id="bill_check"><fmt:message key="recharge.audit.audit"/></li>
			</ol>
		</div>
			<div class="mainbox">
				<div class="tablebox">
					<div class="table-tit">
						<div class="tit-left" >
							<fmt:message key="recharge.audit.audit"/>
						</div>
					</div>
					<table id="table_id" class="tablemain">
						  <thead>
							  <tr class="tit-top">
							    <th scope="col"><fmt:message key="recharge.audit.id"/></th>
								<th scope="col"><fmt:message key="recharge.audit.name"/></th>
								<th scope="col"><fmt:message key="recharge.audit.operation"/></th>
								<th scope="col"><fmt:message key="recharge.audit.primary"/></th>
								<th scope="col"><fmt:message key="recharge.audit.createtime"/></th>
								<th scope="col"><fmt:message key="recharge.audit.audittime"/></th>
								<th scope="col"><fmt:message key="recharge.audit.auditpeople"/></th>
								<th scope="col"><fmt:message key="recharge.audit.editpeople"/></th>
								<th scope="col"><fmt:message key="recharge.status"/></th>
								<th scope="col"><fmt:message key="recharge.operation"/></th>
							  </tr>
						  </thead>
					</table>
				</div>
			</div>
		</div>			
	<cx:script>
		<script type="text/javascript" >
			var table;
			$(function() {
				table = $('#table_id').DataTable({
					
					"ajax": function (data, callback, settings) {
						
	                	$.ax({
	                		"url":"${ctxPath}/audit/ajax/auditdata",
	                		"data" : function() {
								return serializeForm($('#query'));
							},
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
								'data' : 'auname' 
							},
							{
								'data' : function(obj) {
									var sReturn = obj.autype;
									if (sReturn == "create") {
										sReturn = "<fmt:message key="button.add"/>";
									} else if (sReturn == "update") {
										sReturn = "<fmt:message key="button.edit"/>";
									} else if(sReturn == "delete"){
										sReturn = "<fmt:message key="button.del"/>";
									}
									
									return sReturn;
								}
							},
							{
								'data' : 'detailkey' ,'visible' : false
							},
							{
								'data' : 'createtime' 
							},
							{
								'data' : 'updatetime' 
							},
							
							{
								'data' : 'auditer' ,'visible' : false
							},
							{
								'data' : 'draftman' 
							},
							{
								'data' : function(obj) {
									var sReturn = obj.status;
									if (sReturn == "1") {
										sReturn = "<fmt:message key="recharge.audit.statusds"/>";
									} else if (sReturn == "44") {
										sReturn = "<fmt:message key="recharge.audit.statussb"/>";
									} else if(sReturn == "66"){
										sReturn = "<fmt:message key="recharge.audit.statustg"/>";
									}
									
									return sReturn;
								}
							},
							{
								'data' : function(obj) { 									
									sReturn = "<span><a class='tableedit' title='<fmt:message key="recharge.audit"/>' data-val='{"+'"id":"'+obj.id+'","detailkey":"'+obj.detailkey+'"'+"}'"
 											+"><i class='fa fa-edit'></i></a></span> ";
 									return sReturn;
 								}
 							}
					 ]
				});
				
				/*条件查询*/
				/* $(".query-btn").on("click", "#queryBtn", function() {
					 table.ajax.reload();
				}); */
			    $(".tablemain").on("click",".tableedit",function(){
			    	submiturl("${ctxPath}/audit/audit",$(this).attr("data-val"));
			    });
			});
		</script>
	</cx:script>
</body>
</html>
