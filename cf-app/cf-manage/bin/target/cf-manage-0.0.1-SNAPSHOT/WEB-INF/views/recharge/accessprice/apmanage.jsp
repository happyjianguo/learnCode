<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><fmt:message key="recharge.accessprice.title"/></title>
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li><fmt:message key="recharge.accessprice.QuotationManagement"/></li>
				<li class="active" id="bill_check"><fmt:message key="recharge.accessprice.accessprice"/></li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="query">
			 <form id="query">
					<div class="query-tit"><fmt:message key="recharge.accessprice.querycondition"/></div>
					
					<div class="query-text">
						<span><fmt:message key="recharge.ispno"/></span> 
						<select id="ispno" name="ispno" class="form-control select" >
							<option value="" selected><fmt:message key="recharge.all"/></option>
							<option value="yd"><fmt:message key="recharge.ispnoyd"/></option>
							<option value="lt"><fmt:message key="recharge.ispnolt"/></option>
							<option value="dx"><fmt:message key="recharge.ispnodx"/></option>
						</select>
					</div>
					<div class="query-text">
						<span><fmt:message key="recharge.province"/></span> 
						<select id="province" name="province" class="form-control select"  >
							<option value="" selected><fmt:message key="recharge.all"/></option>
							<c:forEach var="province" items="${accesspriceModel.provinceList}">
								<option value="${province.pno}">${province.pname}</option>
							</c:forEach>
						</select>
					</div>		
					<div class="query-text">
						<span><fmt:message key="recharge.ipstype"/></span> 
						<select id="ipstype" name="ipstype" class="form-control select" >
							<option value="" selected><fmt:message key="recharge.all"/></option>
							<option value="hf"><fmt:message key="recharge.ipstypehf"/></option>
							<option value="ll"><fmt:message key="recharge.ipstypell"/></option>
						</select>
					</div>
					<div class="query-text">
						<span><fmt:message key="recharge.acid"/></span> 
						<select id="acid" name="acid" class="form-control select">
							<option value="" selected><fmt:message key="recharge.all"/></option>
							<c:forEach var="access" items="${accesspriceModel.priceList}">
								<option value="${access.id}">${access.acname}</option>
							</c:forEach>
						</select>
					</div>
					<div class="query-btn">
						<button type="button" class="btn btn-primary" id="queryBtn"><fmt:message key="button.query"/></button>
							<a href="${ctxPath}/accprice/init" class="btn btn-primary"><fmt:message key="button.reset"/></a>
					</div>
					</form> 
				</div>
			
			<div class="tablebox">
				<div class="table-tit">
					<div class="tit-left" >
						<fmt:message key="recharge.accessprice.accessprice"/>
					</div>
					<div class="tit-btn">
<!-- 									<span><button type="button" class="btn btn-primary addImuser" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i>新增</button></span> -->
						<span><a href="${ctxPath}/accprice/addinit" class="btn btn-primary"><i class="fa fa-plus"></i><fmt:message key="button.add"/></a></span>
					</div>
				</div> 
				<table id="table_id" class="tablemain">
					  <thead>
						  <tr class="tit-top">
						  	<th scope="col"><fmt:message key="recharge.accessprice.pricenember"/></th>
						    <th scope="col"><fmt:message key="recharge.acid"/></th>
						    <th scope="col"><fmt:message key="recharge.ispno"/></th>
						    <th scope="col"><fmt:message key="recharge.province"/></th>
						    <th scope="col"><fmt:message key="recharge.ipstype"/></th>
						    <th scope="col"><fmt:message key="recharge.accessprice.price"/></th>
						    <th scope="col"><fmt:message key="recharge.accessprice.offer"/></th>
						    <th scope="col"><fmt:message key="recharge.status"/></th>
						    <th scope="col"><fmt:message key="recharge.operation"/></th>
						  </tr>
					  </thead>
					  <tbody id="tbody"></tbody>
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
	                		"url":"${ctxPath}/accprice/ajax/accpriceprice",
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
								'data' : 'apid' ,'visible' : false
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
								'data' : 'pname'
							},
							{
								'data' : function(obj) {
									var sReturn = obj.ipstype;
									if (sReturn == "ll") {
										sReturn = "<fmt:message key="recharge.ipstypell"/>";
									} else if (sReturn == "hf") {
										sReturn = "<fmt:message key="recharge.ipstypehf"/>";
									} 
									return sReturn;
								}
							},
							
							
							{
								'data' : function(obj) {
									var sReturn = obj.ipstype;
									if (sReturn == "ll") {
										sReturn = obj.facevalue;
									} else if (sReturn == "hf") {
										sReturn = obj.facevalue+"(元)";
									} 
									return sReturn;
								}
							},
							{
								'data' : function(obj) {
									return changePrice2money(obj.price);
								}
							},
							{
								'data' : function(obj) {
									var sReturn = obj.status;
									if (sReturn == "kt") {
										sReturn = "<fmt:message key="recharge.operationkt"/>";
									} else if (sReturn == "gb") {
										sReturn = "<fmt:message key="recharge.operationgb"/>";
									} else if (sReturn == "de") {
										sReturn = "<fmt:message key="recharge.operationty"/>";
									} 
									return sReturn;
								}
							},
 							{
								'data' : function(obj) { 									
										sReturn = "<span><a class='tableedit' title='<fmt:message key="button.edit"/>' data-val='{"+'"apid":"'+obj.apid+'"'+"}'><i class='fa fa-edit'></i></a></span> "
 												+ "<span><a class='tabledel' title='<fmt:message key="button.del"/>' data-val='{"+'"apid":"'+obj.apid+'"'+"}'><i class='fa fa-trash-o'></i></a></span> ";
 									return sReturn;
 								}
 							}
					 ]
				});
				
				/*条件查询*/
				$(".query-btn").on("click", "#queryBtn", function() {
					 table.ajax.reload();
				});
				//修改
			    $(".tablemain").on("click",".tableedit",function(){
			    	submiturl("${ctxPath}/accprice/editinit",$(this).attr("data-val"));
			    });
			  	//删除
			    $(".tablemain").on("click",".tabledel",function(){
			    	submiturl("${ctxPath}/accprice/delinit",$(this).attr("data-val"));
			    });
			});
		</script>
	</cx:script>
</body>
</html>