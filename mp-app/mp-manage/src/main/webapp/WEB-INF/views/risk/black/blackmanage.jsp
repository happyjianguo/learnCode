<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>风险管理-黑名单管理</title>
</head>
<body>
			<div class="main-content">
				<div class="list-nav">
					<ol class="breadcrumb">
						<li>风险管理</li>
						<li class="active" id="bill_check">黑名单管理</li>
					</ol>
				</div>
				 <div class="mainbox">
				 	<div class="query">
						<form:form id="query" modelAttribute="blacklistModel">
							<div class="query-tit">查询条件</div>
							<div class="query-text">
								<span>黑名单值</span>
								<input type="text" id="blackvalue" name="blackvalue" class="form-control">
							</div>
							<div class="query-text">
								<span>游戏名称</span>
								<select id="actionid" name="actionid" class="form-control select" >
									<option value="" selected>全部</option>
									<c:forEach items="${blacklistModel.game}" var="gamelist">
										<option value="${gamelist.id}">${gamelist.playname}</option> 
									</c:forEach>
								</select>
							</div>
							<div class="query-text">
								<span>黑名单类型</span>
								<select id="blacktype" name="blacktype" class="form-control select" >
									<option value="" selected>全部</option>
									<option value="1">手机号码</option>
									<option value="2">IP</option>
									<option value="3">OPENID</option>
								</select>
							</div>
							<div class="query-text">
								<span>状态</span>
								<select id="status" name="status" class="form-control select" >
									<option value="" selected>全部</option>
									<option value="0">过期</option>
									<option value="1">生效</option>
								</select>
							</div>
							<div class="query-btn">
								<button type="button" class="btn btn-primary" id="queryBtn">查询</button>
								<button type="button" class="btn btn-primary reset" id="resetBtn">重置</button>
							</div>
						</form:form>
					</div>
					<div class="tablebox">
						<div class="table-tit">
							<div class="tit-left" >
								黑名单管理
							</div>
							<div class="tit-btn">
								<span><a href="${ctxPath}/blackmanage/addinit" class="btn btn-primary"><i class="fa fa-plus"></i>新增</a></span>
							</div>
						</div> 
						<table id="table_id" class="tablemain">
							<thead>
								  <tr class="tit-top">
								    <th scope="col">黑名单ID</th>
								    <th scope="col">游戏编号</th>
								    <th scope="col">游戏名称</th>
								    <th scope="col">黑名单值</th>
								    <th scope="col">黑名单类型</th>
								    <th scope="col">状态</th>
								    <th scope="col">过期时间</th>
								    <th scope="col">说明</th>
								    <th scope="col">操作</th>
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
				"ajax": function (data, callback, settings) {
                	$.ax({
                		 url : "${ctxPath}/blackmanage/ajax/getdata",
                		data : function() {
							return serializeForm($('#query'));
						},
				        successfn : function(result) {
				        	callback(result);
				        }
                	});
                  },
				"columns" : [
						{
							'data' : 'blackid' ,'visible' : false
						},
						{
							'data' : 'actionid'
						},
						{
							'data' : 'playname'
						},
						{
							'data' : 'blackvalue'
						},
						{
							'data' : function(obj){
								var sReturn = obj.blacktype;
								if(sReturn == '1'){
									sReturn = "手机号码"; 
								}else if(sReturn == '2'){
									sReturn = "IP";
								}else if(sReturn == '3'){
									sReturn = "OPENID";
								}else{
									sReturn = "未知类型";
								}
								return sReturn;
							}
						},
						{
							'data' : function(obj){
								var sReturn = obj.status;
								if(sReturn == '0'){
									sReturn = "过期";
								}else if(sReturn == '1'){
									sReturn = "生效";
								}
								return sReturn;
							}
						},
						{
							'data' : 'overtime'
						},
						{
							'data' : 'remark'
						},
						{
							'data' : function(obj) { 
								var statusStr = obj.status;
								if(statusStr == '0'){
									sReturn = 
							    	"<span><a class='tabledel' title='删除' data-val='{"+'"blackid":"'+obj.blackid+'","blackvalue":"'+obj.blackvalue+'"'+"}'><i class='fa fa-trash-o'></i></a></span>";
								}else{
									sReturn = 
									"<span><a a class='tableedit' title='修改' data-val='{"+'"blackid":"'+obj.blackid+'"'+"}'><i class='fa fa-edit'></i></a></span>"+
							    	"<span><a class='tabledel' title='删除' data-val='{"+'"blackid":"'+obj.blackid+'","blackvalue":"'+obj.blackvalue+'"'+"}'><i class='fa fa-trash-o'></i></a></span>";	
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
			/*重置查询条件*/
			$(".query-btn").on("click", "#resetBtn", function() {
				$('#blackvalue').val("");
				$('#blacktype').val("");
				$('#actionid').val("");
				$('#status').val("");
			});
		 	 //修改
		    $(".tablemain").on("click",".tableedit",function(){
		    	submiturl("${ctxPath}/blackmanage/editinit",$(this).attr("data-val"));
		    });
		  	//删除
		    $(".tablemain").on("click",".tabledel",function(){
		    	submiturl("${ctxPath}/blackmanage/delinit",$(this).attr("data-val"));
		    });
		});
		</script>
</body>
</html>