<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>内管系统-日志管理</title>
	
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
		<div class="main-content">			
			<div class="list-nav">
				<ol class="breadcrumb">
					<li>个人中心</li>
					<li class="active" id="bill_check">日志管理</li>
				</ol>
			</div>				
			<div class="mainbox">
				<div class="query">
				<form id="queryform">
					<div class="query-tit">查询条件</div>
					<input type="hidden" id="platformid" name="platformid" class="form-control ">
				    <div class="query_2">
					    <div class="datetime datetime-t">	    							     
			            	<div class="input-append date date2 ">
								<span class="span_d">至</span>
							    <input type="text" id="date_2" class="date_2 form_datetime" name="endTime" value="${loggerManageModel.endTime}" readonly="readonly" />
							</div>
							<div class="input-append date date1">								 
								<span class="span_d">日期范围</span>
							    <input type="text" id="date_1" class="date_1 form_datetime" name="beginTime" value="${loggerManageModel.beginTime}" readonly="readonly"> 
							</div>
						</div>	
				    </div>                   
					<div class="query-btn">
	                    <button type="button" class="btn btn-primary" id="queryBtn">查询</button>
	                    <a href="${ctxPath}/log/init" class="btn btn-primary">重置</a>
	                </div>
	                </form>
				</div>
				<div class="tablebox">
					<div class="table-tit">
						<div class="tit-left" >
							日志管理
						</div>
					</div>
						<table id="table_id" class="tablemain">
							<thead>
								<tr class="tit-top">
									<th scope="col">用户名</th>
									<th scope="col">交易名</th>
									<th scope="col">时间</th>
									<th scope="col">IP地址</th>
									<th scope="col">状态</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
				</div>
			</div>			
		</div>
<cx:script>
 <script type="text/javascript">
 var table;
	$(function() {
		table = $('#table_id').DataTable({
	        "serverSide": true,//打开后台分页
	        "dataSrc": "aaData", 
			"ajax": function (dataModel, callback, settings) {
				var obj = serializeForm($('#queryform'));
				$.extend(dataModel,obj);
				$.ax({
         			"url":"${ctxPath}/log/getdata",
         			"data" : dataModel,
			        successfn : function(result) {
			        	callback(result.data);
			        }
         		});
			},
        	"columns": [
	            { 'data': 'userName' }, 
	            { 'data': 'transName' },
	            { 'data': 'transTime' },
	            { 'data': 'remoteIP' },
	            { 'data': function(obj) {
					if (obj.status === "1") {
						sReturn = "成功";
					} else {
						sReturn = "失败";
					}
					return sReturn;
				}}
        	]
		});
	});
	//查询按钮
	$(".query-btn").on("click","#queryBtn",function() {
		if($("#date_1").val()>$("#date_2").val()){
			$("#errtextmsg").text("开始时间不能大于结束时间，请重新选择！");
			$('#myModal-errmsg').modal('show');
			return false
		};
		table.ajax.reload();
	});
</script>
</cx:script>
</body>
</html>