<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><fmt:message key="recharge.batchrecharge.title"/></title>
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li><fmt:message key="recharge.batchrecharge.recharge"/></li>
				<li class="active" id="bill_check"><fmt:message key="recharge.batchrecharge.batchrecharge"/></li>
			</ol>
		</div>
		<div class="mainbox">
			<form:form id="query" modelAttribute="batchinfoModel"  methodParam="post" cssClass="form-horizontal">
				<form:input path="batchid" type="hidden" readonly="true" />
			</form:form>
			
			
			<div class="tablebox">
				<div class="table-tit">
					<div class="tit-left" >
						<fmt:message key="recharge.batchrecharge.batchrechargeinfo"/>
					</div>
					<div class="tit-btn">
							<span><button type="button" class="btn btn-primary " id = "uploadok"><i class="fa fa-plus"></i><fmt:message key="recharge.batchrecharge.recharge"/></button></span>
							<%-- <span><button type="button" class="btn default cancel" id = "uploadRollBack"><i class="fa fa-trash-o"></i><fmt:message key="button.cancel"/></button></span> --%>
					</div>
				</div> 
				<table id="table_id" class="tablemain">
					  <thead>
						  <tr class="tit-top">
						  	<th scope="col"><fmt:message key="recharge.batchcurrbusilog.id"/></th>
						    <th scope="col"><fmt:message key="recharge.ispno"/></th>
						    <th scope="col"><fmt:message key="recharge.ipstype"/></th>
						    <th scope="col"><fmt:message key="recharge.price"/></th>
						    <th scope="col"><fmt:message key="recharge.phone"/></th>
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
			/*条件查询*/
			$(".query-btn").on("click", "#queryBtn", function() {
				 table.ajax.reload();
			});
			table = $('#table_id').DataTable({
				"ajax": function (data, callback, settings) {
                	$.ax({
                		"url":"${ctxPath}/batchrecharge/ajax/batchrecharge",
                		"data" : {batchid:"${batchinfoModel.batchid}",status:'20'},
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
			
				$(".tit-btn").on("click", "#uploadok", function() {
					$('#query').attr('action','${ctxPath}/batchrecharge/uploadOk' );
					$('#uploadRollBack').html('');
					$("#query").submit();
				});
				$(".tit-btn").on("click", "#uploadRollBack", function() {
					$('#query').attr('action','${ctxPath}/batchrecharge/uploadRollBack' );
					$("#query").submit();
				});
				
				
			});
		</script>
</body>
</html>