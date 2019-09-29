<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><fmt:message key="recharge.bill.title"/></title>
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li><fmt:message key="recharge.bill.Statistics"/></li>
				<li class="active" id="bill_check"><fmt:message key="recharge.bill.bill"/></li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="query">
			<form:form id="query" cssClass="btnenable">
					<div class="query-tit"><fmt:message key="recharge.querycondition"/></div>
					<div class="form-group">
						<div class="query-text">
							<span><fmt:message key="recharge.bill.status"/></span> 
							<div class="col-sm-2 select-list" >
								<select id="status" name="status" class="form-control select" >
									<option value="" selected><fmt:message key="recharge.all"/></option>
									<option value="240000"><fmt:message key="recharge.bill.statusfail"/></option>
									<option value="240001"><fmt:message key="recharge.bill.statusbalance"/></option>
									<option value="240002"><fmt:message key="recharge.bill.statusxiaoyu"/></option>
									<option value="240003"><fmt:message key="recharge.bill.statusdayu"/></option>
								</select>
							</div>
						</div>
						<label class="control-label" style="float:left; width:100px; padding:5px 10px 0 0"><fmt:message key="recharge.beginendtime"/></label>
							<div class="input-append date date1" style="float:left; width:130px;">								 
								<input type="text" id="starttime" name = "starttime" class="date_1 form_datetime_long"  readonly="readonly" value="${ofBillModel.starttime}"  style="margin-top:0">     
							</div>
							<div class="input-append date date2" style="float:left">
								<span class="span_d"><fmt:message key="recharge.timespot"/></span>
								<input type="text" id="endtime" name = "endtime" class="date_2 form_datetime_long"  readonly="readonly" value="${ofBillModel.endtime}" style="margin-top:0"/>
							</div>																							
					</div>				
					<div class="query-btn">
						<button type="button" class="btn btn-primary" id="queryBtn"><fmt:message key="button.query"/></button>
						<a href="${ctxPath}/bill/init" class="btn btn-primary"><fmt:message key="button.reset"/></a>
					</div>
					</form:form>
				</div>
			
			<div class="tablebox">
				<div class="table-tit">
					<div class="tit-left" >
						<fmt:message key="recharge.bill.ofbill"/>
					</div>
					<div class="tit-btn">
						<span>
							<button type="submit" class="btn btn-primary" formaction="${ctxPath}/bill/ofdownloads" formmethod="post" form="query"><fmt:message key="recharge.bill.export"/></button>
						</span>
						<span><a href="${ctxPath}/bill/wsbill" class="btn btn-primary"><i class="fa fa-plus"></i><fmt:message key="recharge.bill.wsbill"/></a></span>
					</div>
				</div> 
				<table id="table_id" class="tablemain">
					  <thead>
						  <tr class="tit-top">
						  	<th scope="col"><fmt:message key="recharge.bill.cpbizid"/></th>
						    <th scope="col"><fmt:message key="recharge.bill.spno"/></th>
						    <th scope="col"><fmt:message key="recharge.bill.commoditynumber"/></th>
						    <th scope="col"><fmt:message key="recharge.bill.commodityshuliang"/></th>
						    <th scope="col"><fmt:message key="recharge.bill.rechargeaccount"/></th>
					        <th scope="col"><fmt:message key="recharge.bill.platstatus"/></th>
						    <th scope="col"><fmt:message key="recharge.bill.ofstatus"/></th> 
						    <th scope="col"><fmt:message key="recharge.acid"/></th>
						    <th scope="col"><fmt:message key="recharge.bill.ordermoney"/></th>
						    <th scope="col"><fmt:message key="recharge.bill.ordertime"/></th>
						    <th scope="col"><fmt:message key="recharge.bill.orderstatus"/></th>
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
	                		"url":"${ctxPath}/bill/ajax/ofbillquery",
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
								'data' : 'cpno' 
							},
							{
								'data' : 'spno'
							},
							{
								'data' : 'num'
							},
							{
								'data' : 'size'
							},
							{
								'data' : 'phone'
							},
							{
								'data' : function(obj) {
									var psReturn = obj.platstatus;
									if (psReturn == "270000") {
										psReturn = "录入";
									} else if (psReturn == "270001") {
										psReturn = "成功";
									} else if (psReturn == "270002") {
										psReturn = "失败";
									}else if (psReturn == "270003") {
										psReturn = "未知，通讯超时";
									}else if (psReturn == "270004") {
										psReturn = "已受理";
									}else if (psReturn == "270005") {
										psReturn = "无接入者报价";
									}else if (psReturn == "270006") {
										psReturn = "无供应商报价";
									}else if (psReturn == "270007") {
										psReturn = "无接入者及供应商报价";
									}else if (psReturn == "270008") {
										psReturn = "欠费";
									}else if (psReturn == "270009") {
										psReturn = " 接入者报价状态为关闭";
									}else if (psReturn == "270010") {
										psReturn = "接入者状态为关闭";
									}else if (psReturn == "270011") {
										psReturn = "供应商报价状态为关闭";
									}								
									return psReturn;
								}
							},
							{
								'data' : function(obj) {
									var fsReturn = obj.ofstatus;
									if (fsReturn == "1") {
										fsReturn = "充值成功";
									} else if (fsReturn == "0") {
										fsReturn = "处理中";
									} else if (fsReturn == "9") {
										fsReturn = "充值失败";
									}
									return fsReturn;
								}
							}, 
							{
								'data' : 'acname'
							},
							{
								'data' : 'oprice'
							},
							{
								'data' : 'otime'
							},
							{ 
								'data' : function(obj) {
									var sReturn = obj.status;
									if (sReturn == "240000") {
										sReturn = "<fmt:message key="recharge.bill.statusfail"/>";
									} else if (sReturn == "240001") {
										sReturn = "<fmt:message key="recharge.bill.statusbalance"/>";
									} else if (sReturn == "240002") {
										sReturn = "<fmt:message key="recharge.bill.statusxiaoyu"/>";
									}else if (sReturn == "240003") {
										sReturn = "<fmt:message key="recharge.bill.statusdayu"/>";
									}
									return sReturn;
								}
							}
					 ]
				});
				
				/*条件查询*/
				$(".query-btn").on("click", "#queryBtn", function() {
					 table.ajax.reload();
				});
				
			});
		</script>
	</cx:script>
</body>
</html>