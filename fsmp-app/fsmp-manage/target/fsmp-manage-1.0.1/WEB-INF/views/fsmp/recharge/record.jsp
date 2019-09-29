<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>内管系统-用户充值记录</title>

<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<%-- <form:form action="${ctxPath}/wxmanage/goauth" method="post"
				id="eform">
			<button type="submit" class="btn btn-primary btn-lg btn-block"
					value="tijiao">添加公众号</button>
		</form:form> --%>
		<div class="mainbox">
			<div class="tablebox">
				<div class="table-tit">
					<div class="tit-left">订单分析</div>
				</div>
				<div class="query">
					<form:form id="queryform" method="post">
							<div class="query_1">
							<div class="">
								
								<div class="query-text">
									<span class="span_d">活动名称</span>
									 <select name="gpid" id="gpid" class="form-control select qid" >
										<option value="" selected="selected" >请选择</option>
										<c:forEach  var="gpitem" items="${orderManageModel.gpList}">
											<option value="${gpitem.gameid}">${gpitem.gamename}</option>
										</c:forEach>
									</select>
								</div>		
								<div class="query-text">
									<span class="span_d">奖品类型</span> 
									<select name="prizetype" id="prizetype" class="form-control select qid" >
										<option value="" >请选择</option>
										<option value="xn">虚拟</option>
										<option value="sw">实物</option>
									</select>
								</div>
								<div class="query-text" id="vrdiv">
									<span class="span_d">套餐类型</span>
									 <select name="vrid" id="vrid" class="form-control select qid" >
										<option value="" selected="selected" >请选择</option>
										<c:forEach  var="vritem" items="${orderManageModel.vrList}">
											<option value="${vritem.prizestyle}" gameid = "${vritem.gameid}">${vritem.prizename}</option>
										</c:forEach>
									</select>
								</div>
								<div class="query-text" id="senddiv">
									<span class="span_d">订单状态</span> 
									<select name="status" id="status" class="form-control select qid" >
										<option value="" >请选择</option>
										<option value="0">充值失败</option>
										<option value="1">充值成功</option>
										<option value="4">受理中</option>
									</select>
									<select name="sendstatus" id="sendstatus" class="form-control select qid" style="display: none;">
										<option value="" >请选择</option>
										<option value="2">充值异常</option>
										<option value="5">套餐不存在</option>
										<option value="6">游戏预算不足</option>
										<option value="9">平台币不足</option>
									</select>
								</div>
							</div>						
						</div>
						<div class="query_2" style="float: left;">
							
								<div class="input-append date date2 " style="margin-top: -8px;">
									<span class="span_d">至</span> <input type="text" id="date_2"
										class="date_2 form_datetime" name="endTime"
										value="${orderManageModel.endTime}" readonly="readonly" />
								</div>
								<div class="input-append date date1"  style="margin-top: -8px; margin-left:36px">
									<span class="span_d">日期范围</span> <input type="text" id="date_1"
										class="date_1 form_datetime" name="beginTime"
										value="${orderManageModel.beginTime}" readonly="readonly">
								</div>
							
								<div class="query-text">
									<span class="span_d">中奖手机号</span> <input type="text" id="phone"
										name="phone" class="form-control"
										value="${orderManageModel.phone}">
								</div>
						</div>
						<div class="query-btn">
							<button type="button" class="btn btn-primary" id="queryBtn">查询</button>
							<button type="button" class="btn btn-primary" id="recharge">批量补充</button>
							<button type="button" class="btn btn-primary" id="down">下载报表</button>
							<a href="${ctxPath}/order/init" class="btn btn-primary">重置</a>
						</div>
					</form:form>
				</div>
				<table id="table_id" class="tablemain">
					<thead>
						<tr class="tit-top">
							<th scope="col">活动名称 </th>
							<th scope="col">用户id</th>
							<th scope="col">订单记录id</th>
							<th scope="col">中奖手机号</th>
							<th scope="col">奖品类型</th>
							<th scope="col">奖品名称</th>
							<th scope="col">订单状态</th>
							<th scope="col">创建时间</th>
							<th scope="col">操作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				<div class="record-num" style="text-align: right;">
					账户余额：<span id="balance">${currentMoney}</span> 已消费余额：<span id="costbalance">0</span>
				</div>
			</div>
		</div>
		<div class="modal fade" id="recharge-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">提示信息</h4>
					</div>
					<div class="modal-body">
						<div class="modal-prompt deleteAlert">
							<i class="fa fa-check"></i><span id="recharge-textmsg"></span>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default deleteCancle"
							data-dismiss="modal">关闭</button>
						<button type="button" id="chargebut" class="btn btn-primary  ">确认</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript">
			var table;
			$(function() {
				var queryCount = "${orderManageModel.queryCount}";//查询补充订单个数
				var chargeCount = "${orderManageModel.chargeCount}";//补充个数
				var validateCount = "${orderManageModel.validateCount}";
				var rechargeFlag = "${orderManageModel.rechargeFlag}";
				if(rechargeFlag == 'true'){
					$("#textmsg").text("查询订单数为："+queryCount+" 需要补充订单数为："+chargeCount+" 补充成功数为："+validateCount);
					$('#myModal-confirm').modal('show');
				}
				
				
				table = $('#table_id').DataTable({
					"serverSide" : true,//打开后台分页
					"dataSrc" : "aaData",
					"ajax" : function(dataModel, callback, settings) {
						var obj = serializeForm($('#queryform'));
						$.extend(dataModel, obj);
						$.ax({
							"url" : "${ctxPath}/order/record",
							"data" : dataModel,
							successfn : function(result) {
								callback(result);
							}
						});
					},
					"columns" : [ {
						'data' : 'gamename'
					}, {
						'data' : 'openid'
					}, {
						'data' : 'id',
						'visible' : false
					}, { 
						'data' : 'winnerphone'
					},{
						'data' : function(obj){
							if(obj.prizetype == "xn"){
								return "虚拟奖品";
							}else{
								return "实物奖品";
							}
						}
					},{
						'data' : function(obj){
							if(obj.prizetype == "xn"){
								return obj.prizestylename;
							}else{
								return  obj.prizename;
							}
						}
					},  {
						'data' : function(obj) {
							var sReturn = "";
							if (obj.sendstatus === "0") {
								sReturn = "受理中";
							} else if (obj.sendstatus === "1") {
								sReturn = "充值成功";
							} else if (obj.sendstatus === "2") {
								sReturn = "充值异常";
							} else if (obj.sendstatus === "3") {
								sReturn = "受理中";
							} else if (obj.sendstatus === "4") {
								sReturn = "受理中";
							} else if (obj.sendstatus === "5") {
								sReturn = "套餐不存在";
							} else if (obj.sendstatus === "6") {
								sReturn = "游戏预算不足";
							} else if (obj.sendstatus === "7") {
								sReturn = "受理中";
							} else if (obj.sendstatus === "8") {
								sReturn = "无需发送";
							} else if (obj.sendstatus === "9") {
								sReturn = "平台币不足";
							}
							$("#costbalance").text(obj.sumPrice);
							return sReturn;
						}
					}, { 
						'data' : 'createtime'
					},{
						'data' : function(obj){
							if(obj.sendstatus === "6" || obj.sendstatus === "9"|| obj.sendstatus === "2"){
								return "<span><a class='charge' title='补充' href='javascript:;'><i class='fa fa-edit'></i></a>";
							}else{
								return "";
							}
						}
					}]
				});
				
				$("#table_id").on("click", ".charge", function() {
					var data = table.row($(this).parents('tr')).data();
					
					$("#recharge-textmsg").text("确定补充该订单吗？");
					$('#recharge-confirm').modal('show');
					$("#recharge-confirm").off("click", "#chargebut");
					$("#recharge-confirm").on("click", "#chargebut",function(){
						$('#recharge-confirm').modal('hide');
						$.ax({
							"url" : "${ctxPath}/order/charge",
							"data" : {"id":data.id},
							successfn : function(result) {
								if(result.result == 1){
									table.ajax.reload();
									$("#textmsg").text(result.msg);
									$('#myModal-confirm').modal('show');
								}else{
									$("#errtextmsg").text(result.msg);
						        	$('#myModal-errmsg').modal('show');
								}
							}
						});
					});
				});
				//查询按钮
				$(".query-btn").on("click", "#queryBtn", function() {
					if ($("#date_1").val() > $("#date_2").val()) {
						$("#errtextmsg").text("开始时间不能大于结束时间，请重新选择！");
						$('#myModal-errmsg').modal('show');
						return false
					};
					table.ajax.reload();
				});
				$(".query-btn").on("click", "#down", function() {
					if ($("#date_1").val() > $("#date_2").val()) {
						$("#errtextmsg").text("开始时间不能大于结束时间，请重新选择！");
						$('#myModal-errmsg').modal('show');
						return false
					};
					$("#queryform").attr("action","${ctxPath}/order/download");
					$("#queryform").submit();
				});
				
				$(".query-btn").on("click","#recharge",function(){
					if ($("#date_1").val() > $("#date_2").val()) {
						$("#errtextmsg").text("开始时间不能大于结束时间，请重新选择！");
						$('#myModal-errmsg').modal('show');
						return false
					};
					$("#recharge-textmsg").text("确定批量补充吗？");
					$('#recharge-confirm').modal('show');
					$("#recharge-confirm").off("click", "#chargebut");
					$("#recharge-confirm").on("click", "#chargebut",function(){
						$("#queryform").attr("action","${ctxPath}/order/init");
						$("#queryform").submit();
					});
				});
				$('#prizetype').change(function(){ 
					var type =  $(this).children('option:selected').val();
					$("#sendstatus").children('option').prop("selected",false);
					$("#status").children("option").prop("selected",false);
					$("#vrid").children("option[value!='']").show();
					$("#senddiv").show();
					$("#vrdiv").show();
					if(type == 'sw'){
						$("#status").children("option[value='1']").prop("selected",true);
						$("#vrid").children("option[value!='']").hide();
						$("#vrid").children("option[value='']").prop("selected",true);
						$("#senddiv").hide();
						$("#vrdiv").hide();
					}
				});
				
				$("#vrid").change(function(){
					var type =  $('#prizetype').children('option:selected').val();
					if(type == 'sw'){
						$("#vrid").children("option[value!='']").prop("selected",false);
						$("#vrid").children("option[value='']").prop("selected",true);
					}
				});
				
				$('#status').change(function(){ 
					var status =  $(this).children('option:selected').val();
					var type =  $('#prizetype').children('option:selected').val();
					if(type == 'sw'){
						$(this).children("option").prop("selected",false);
						$(this).children("option[value='1']").prop("selected",true);
						$("#sendstatus").hide();
					}else{
						if(status == '0'){
							$("#sendstatus").show();
						}else{
							$("#sendstatus").hide();
						}
					}
				});
				
				$("#gpid").change(function(){
					var gameid =  $(this).children('option:selected').val() 
					if(gameid !=""){
						$("#vrid").children("option").hide();
						$("#vrid").children("option").prop("selected",false);
						$("#vrid").children("option[gameid='"+gameid+"']").show();
					}else{
						$("#vrid").children("option").show();
					}
					$("#vrid").children("option[value='']").show();
					
				});
			});
		</script>
</body>
</html>