<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><fmt:message key="recharge.currbusilog.title"/></title>
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
							<li class="active" id="bill_check"><fmt:message key="recharge.currbusilog.currbusilog"/></li>
						</ol>
					</div>
						 
						<div class="mainbox">
							<div class="query">
						  <form:form id="query" method="post" action="${ctxPath}/currbusilog/download" cssClass="btnenable">
								<div class="query-tit"><fmt:message key="recharge.querycondition"/></div>
								<div class="query-text">
									<span><fmt:message key="recharge.customer"/></span>
									<select id="cid" name="cid" class="form-control select cid" >
									  <option value="" selected><fmt:message key="recharge.all"/></option>
											<c:forEach var="customer" items="${currbusilogModel.cfCustomerList}">
										<option value="${customer.id }">${customer.cname }</option>
										</c:forEach>
									</select>
								</div>
								<div class="query-text">
									<span><fmt:message key="recharge.acid"/></span>
									<select id="acid" name="acid" class="form-control select acid" >
									   <option value="" selected><fmt:message key="recharge.all"/></option>
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
								<div class="query-text">
									<span><fmt:message key="recharge.province"/></span> 
									<select id="province" name="province" class="form-control select"  >
										<option value="" selected><fmt:message key="recharge.all"/></option>
										<c:forEach var="province" items="${currbusilogModel.provinceList}">
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
										<span><fmt:message key="recharge.price"/> </span> 
										<input type="text" id="facevalue" name="facevalue" class="form-control">
									</div>	
									<div class="query-text">
										<span><fmt:message key="recharge.status"/></span> 
										<select id="status" name="status" class="form-control select" >
											<option value="" selected><fmt:message key="recharge.all"/></option>
											<option value="0"><fmt:message key="recharge.currbusilog.statuslr"/></option>
											<option value="1"><fmt:message key="recharge.currbusilog.statuscg"/></option>
											<option value="2"><fmt:message key="recharge.currbusilog.statussb"/></option>
											<option value="3"><fmt:message key="recharge.currbusilog.statuswz"/></option>
											<option value="4"><fmt:message key="recharge.currbusilog.statusysl"/></option>
											<%-- <option value="270005"><fmt:message key="recharge.currbusilog.statuswjrz"/></option>
											<option value="270006"><fmt:message key="recharge.currbusilog.statuswgysbj"/></option>
											<option value="270007"><fmt:message key="recharge.currbusilog.statuswjrzjgys"/></option>
											<option value="270008"><fmt:message key="recharge.currbusilog.statusqf"/></option>
											<option value="270009"><fmt:message key="recharge.currbusilog.statusjrzbj"/></option>
											<option value="270010"><fmt:message key="recharge.currbusilog.statusjrz"/></option>
											<option value="270011"><fmt:message key="recharge.currbusilog.statusgysbj"/></option>					 --%>													
										</select>
									</div>
									<div class="query-text">
										<span><fmt:message key="recharge.currbusilog.accessno"/> </span> 
										<input type="text" id="systransno" name="systransno" class="form-control">
									</div>
									<div class="query-text">
										<span><fmt:message key="recharge.currbusilog.appriceno"/></span> 
										<input type="text" id="orderid" name="orderid" class="form-control">
									</div>
									<div class="query-text">
									<span><fmt:message key="recharge.phone"/> </span> 
									<input type="text" id="phone" name="phone" class="form-control">
								</div> 								
								<!-- <th scope="col">流水ID</th> -->																			
								<div class="query_2">
									<div class="form-group">
										<label class="control-label" style="float:left; width:100px; padding-right:10px"><fmt:message key="recharge.beginendtime"/></label>
										<div class="input-append date date1" style="float:left; width:130px;">								 
			<!-- 								<span class="span_d"></span> -->
											<input type="hidden" id="defbegin" value="${currbusilogModel.begintime}">
										    <input type="text" id="begintime" name = "begintime" class="date_1 form_datetime_long" readonly="readonly" value="${currbusilogModel.begintime}"  style="margin-top:0">     
										</div>
										<div class="input-append date date2" style="float:left">
											<span class="span_d"><fmt:message key="recharge.timespot"/></span>
											<input type="hidden" id="defend" value="${currbusilogModel.endtime}">
										    <input type="text" id="endtime" name = "endtime" class="date_2 form_datetime_long" readonly="readonly"  value="${currbusilogModel.endtime}" style="margin-top:0"/>
										</div>																							
									</div>		
									<div class="query-btn">
										<button type="button" class="btn btn-primary" id="queryBtn"><fmt:message key="button.query"/></button>
										<button type="button" class="btn btn-primary" id="valueReset"><fmt:message key="button.reset"/></button>
									</div>							
								</div>
							</form:form>							
						</div>													
						<div class="tablebox">
							 <div class="table-tit">
								<div class="tit-left" >
									<fmt:message key="recharge.currbusilog.currbusilog"/>
								</div>
								<div class="tit-btn">
									<span>
										<button type="submit" class="btn btn-primary"  form="query"><fmt:message key="recharge.currbusilog.export"/></button>
									</span>
								</div>
							</div> 
							<table id="table_id" class="tablemain">
								  <thead>
									  <tr class="tit-top">
									    <th scope="col"><fmt:message key="recharge.currbusilog.id"/></th>
									    <th scope="col"><fmt:message key="recharge.customer"/></th>
									    <th scope="col"><fmt:message key="recharge.acid"/></th>
									    <th scope="col"><fmt:message key="recharge.phone"/></th>
									    <th scope="col"><fmt:message key="recharge.ispno"/></th>
									    <th scope="col"><fmt:message key="recharge.ipstype"/></th>
									    <th scope="col"><fmt:message key="recharge.province"/></th>
									    <th scope="col"><fmt:message key="recharge.price"/></th>
									    <th scope="col"><fmt:message key="recharge.currbusilog.appriceno"/></th>
									    <th scope="col"><fmt:message key="recharge.currbusilog.platno"/></th>
									    <th scope="col"><fmt:message key="recharge.currbusilog.accessno"/></th>
									    <th scope="col"><fmt:message key="recharge.currbusilog.responsecode"/></th>
									    <th scope="col"><fmt:message key="recharge.supplierprice"/></th>
									    <th scope="col"><fmt:message key="recharge.currbusilog.apid"/></th>
									    <th scope="col"><fmt:message key="recharge.accessprice"/></th>
									    <th scope="col"><fmt:message key="recharge.currbusilog.spid"/></th>
									    <th scope="col"><fmt:message key="recharge.createtime"/></th>
									    <th scope="col"><fmt:message key="recharge.updatetime"/></th>
									    <th scope="col"><fmt:message key="recharge.status"/></th>
									    <th scope="col"><fmt:message key="recharge.operation"/></th>
									  </tr>
								  </thead> 
								  <tbody id="tbody"></tbody>
							</table>
						</div>
						</div>								
				</div>
	
	<!--  -->
	<!-- Modal-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel"><fmt:message key="recharge.currbusilog.mingxi"/></h4>
				</div>
				<div class="modal-body">
					<form:form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.currbusilog.id"/>:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelid"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.customer"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelcustomer"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.acid"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelaccess"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.phone"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelphone"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.ispno"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelispno"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.ipstype"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelipstype"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.province"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelidprovince"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.price"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelprice"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.currbusilog.appriceno"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelappriceno">
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.currbusilog.platno"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelplatno">
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.currbusilog.accessno"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelaccessno"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.currbusilog.responsecode"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelresponsecode"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.supplierprice"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelsupplierprice">
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.currbusilog.apid"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelapid"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.accessprice"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelaccessprice"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.currbusilog.spid"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelspid"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.createtime"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelcreatetime"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.updatetime"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelupdatetime">
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><fmt:message key="recharge.status"/></label>
							<div class="col-sm-6">
								<p class="form-control-static" id="myModalLabelstatus"></p>
							</div>
						</div>
					</form:form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="button.submit"/></button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal end-->
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
	                		"url":"${ctxPath}/currbusilog/ajax/query",
	                		"data" : dataModel,
					        successfn : function(result) {
					        	callback($.parseJSON(result.data));
					        }
	                	});
	                  },
					"columns" : [
							{
								'data' : 'bizid' 
								/* ,'visible' : false */
							},
							{
								'data' : 'cname'
							},
							{
								'data' : 'acname'
							},
							{
								'data' : 'phone'
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
								'data' : 'pname'
							},
							{
								'data' : 'facevalue'
							},
							
							{
								'data' : 'orderid','visible' : false 
							},
							{
								'data' : 'cporderno','visible' : false 
							},
							{
								'data' : 'systransno','visible' : false 
							},
							
							{
								'data' : 'responsecode','visible' : false 
								/* 'data' : function(obj) {
									var rescodeReturn = obj.responsecode;
									if (rescodeReturn == "") {
										rescodeReturn = "";
									} else if (rescodeReturn == "10100") {
										rescodeReturn = "<fmt:message key="recharge.currbusilog.responsecodechggws"/>";
									} else if (rescodeReturn == "10000") {
										rescodeReturn = "<fmt:message key="recharge.currbusilog.responsecodejsqqccws"/>";
									} else if (rescodeReturn == "20407") {
										rescodeReturn = "<fmt:message key="recharge.currbusilog.responsecodeclzws"/>";
									} else if (rescodeReturn == "1") {
										rescodeReturn = "<fmt:message key="recharge.currbusilog.responsecodechggof"/>";
									} else if (rescodeReturn == "0") {
										rescodeReturn = "<fmt:message key="recharge.currbusilog.responsecodecgcczof"/>";
									} else if (rescodeReturn == "9") {
										rescodeReturn = "<fmt:message key="recharge.currbusilog.responsecodeczsb"/>";
									} 
									return rescodeReturn;
								},'visible' : false  */
							},
							{
								'data' : 'spprice','visible' : false 
							},
							{
								'data' : 'spno','visible' : false 
							},
							{
								'data' : 'apprice','visible' : false 
							},
							{
								'data' : 'apno','visible' : false 
							},
							{
								'data' : 'createtime','visible' : false 
							},
							{
								'data' : 'updatetime'
							},
							
							{ 
								'data' : function(obj) {
									var sReturn = obj.status;
									if (sReturn == "0") {
										sReturn = "<fmt:message key="recharge.currbusilog.statuslr"/>";
									} else if (sReturn == "1") {
										sReturn = "<fmt:message key="recharge.currbusilog.statuscg"/>";
									} else if (sReturn == "2") {
										sReturn = "<fmt:message key="recharge.currbusilog.statussb"/>";
									}else if (sReturn == "3") {
										sReturn = "<fmt:message key="recharge.currbusilog.statuswz"/>";
									}else if (sReturn == "4") {
										sReturn = "<fmt:message key="recharge.currbusilog.statusysl"/>";
									}
									/* else if (sReturn == "270005"){
										sReturn = "<fmt:message key="recharge.currbusilog.statuswjrz"/>";
									}else if (sReturn == "270006"){
										sReturn = "<fmt:message key="recharge.currbusilog.statuswgysbj"/>";
									}else if (sReturn == "270007"){
										sReturn = "<fmt:message key="recharge.currbusilog.statuswjrzjgys"/>";
									}else if (sReturn == "270008"){
										sReturn = "<fmt:message key="recharge.currbusilog.statusqf"/>";
									}else if (sReturn == "270009"){
										sReturn = "<fmt:message key="recharge.currbusilog.statusjrzbj"/>";
									}else if (sReturn == "270010"){
										sReturn = "<fmt:message key="recharge.currbusilog.statusjrz"/>";
									}else if (sReturn == "270011"){
										sReturn = "<fmt:message key="recharge.currbusilog.statusgysbj"/>";
									}								 */
									return sReturn;
								}
							},
							{
								'data' : function(obj) { 									
										sReturn = "<span><a class='busilogdetail' title='<fmt:message key="recharge.currbusilog.dianjickxq"/>' href='javascript:;'><i class='fa fa-search' aria-hidden='true'></i></a></span>";
 									return sReturn;
 								}
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
	            		url  :"${ctxPath}/accessclient/ajax/initClient",
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
							
							phone : {
								
								mobile : true
							},
							begintime:{
								required : true,
								maxlength : 60,
								compareEnd  : endtime
							},
							endtime : {
								required : true,
								maxlength : 60,
								compareDate:begintime
							}
						},submitHandler :function(form) {
							if(queryFlag){
								queryFlag = false;
								table.ajax.reload();
							}else{
								form.submit();
							}
							
						}
					}
		      );
		      
			      /*点击查看详情*/
					$(".tablemain").on("click", ".busilogdetail", function() {
						var data = table.row($(this).parents('tr')).data();
						$('#myModalLabelid').text(data.bizid);
						$('#myModalLabelcustomer').text(data.cname);
						$('#myModalLabelaccess').text(data.acname);
						$('#myModalLabelphone').text(data.phone);
						if (data.ispno == "lt") {
							$('#myModalLabelispno').text('<fmt:message key="recharge.ispnolt"/>');
						} else if (data.ispno == "yd") {
							$('#myModalLabelispno').text('<fmt:message key="recharge.ispnoyd"/>');
						} else if(data.ispno == "dx"){
							$('#myModalLabelispno').text('<fmt:message key="recharge.ispnodx"/>');
						}
						$('#myModalLabelipstype').text(data.ipstype);
						if (data.ipstype == "ll") {
							$('#myModalLabelipstype').text('<fmt:message key="recharge.ipstypell"/>');
						} else if (data.ipstype == "hf") {
							$('#myModalLabelipstype').text('<fmt:message key="recharge.ipstypehf"/>');
						} 
						$('#myModalLabelidprovince').text(data.pname);
						$('#myModalLabelprice').text(data.facevalue);
						$('#myModalLabelappriceno').text(data.orderid);
						$('#myModalLabelplatno').text(data.cporderno);
						$('#myModalLabelaccessno').text(data.systransno);
						$('#myModalLabelresponsecode').text(data.responsecode);
						$('#myModalLabelsupplierprice').text(data.spprice);
						$('#myModalLabelapid').text(data.spno);
						$('#myModalLabelaccessprice').text(data.apprice);
						$('#myModalLabelspid').text(data.apno);
						$('#myModalLabelcreatetime').text(data.createtime);
						$('#myModalLabelupdatetime').text(data.updatetime);
						if (data.status == "0") {
							$('#myModalLabelstatus').text('<fmt:message key="recharge.currbusilog.statuslr"/>');
						} else if (data.status == "1") {
							$('#myModalLabelstatus').text( '<fmt:message key="recharge.currbusilog.statuscg"/>');
						} else if (data.status == "2") {
							$('#myModalLabelstatus').text('<fmt:message key="recharge.currbusilog.statussb"/>');
						}else if (data.status == "3") {
							$('#myModalLabelstatus').text('<fmt:message key="recharge.currbusilog.statuswz"/>');
						}else if (data.status == "4") {
							$('#myModalLabelstatus').text('<fmt:message key="recharge.currbusilog.statusysl"/>');
						}
						$("#myModal").modal('show');
					});
		    	 	/* mody by wangyufeng 20161659 获取选中的日期后，获取失去焦点以完成validate校验 */
		    	 	$("#begintime").on("change",function(){ 
		    	 		$(this).focus();
						$(this).blur();
					});
		    	 	$("#endtime").on("change",function(){ 
		    	 		$(this).focus();
						$(this).blur();
					});
		    	 	var queryFlag = false ;
		    	 /*条件查询*/
					$(".query-btn").on("click", "#queryBtn", function() {
						queryFlag = true;
						$('#query').submit();
					});
		    	 /* 内容重置 */
		    	    $("#valueReset").click(function(){
		    		    $("#query input[type=text][id!=begintime][id!=endtime]").val("");
		    		    $("#begintime").val($("#defbegin").val());
		    		    $("#endtime").val($("#defend").val());
		    		    $('#query').validate().resetForm();
		    		    $("#query option").prop("selected",false);		    			    		 
		    		    $("#acid option").hide();		
		    		    $("#acid").find("option[value='']").prop("selected",true);	
		    	 });
			});
		</script>
</body>
</html>
