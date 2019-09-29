<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>系统管理-公告管理</title>
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li>系统管理</li>
				<li class="active" id="bill_check">公告管理</li>
			</ol>
		</div>
			<div class="mainbox">
				<div class="query">
					<form id="query">
						<div class="query-tit">查询条件</div>
						<div class="query-text">
							<span>公告名</span>
							<input type="text" id="noticenames" name="noticename" class="form-control">
						</div>
						<div class="query-btn">
							<button type="button" class="btn btn-primary" id="queryBtn">查询</button>
							<button type="button" class="btn btn-primary reset" id="resetBtn">重置</button>
						</div>
					</form>
				</div>
				<div class="tablebox">
					<div class="table-tit">
						<div class="tit-left" >
							公告管理
						</div>
						<div class="tit-btn">
							<span><a href="${ctxPath}/notice/addinit" class="btn btn-primary"><i class="fa fa-plus"></i>新增</a></span>
						</div>
					</div>
					<table id="table_id" class="tablemain">
						<thead>
							<tr class="tit-top">
							    <th scope="col">公告id</th>
							    <th scope="col">公告名</th>
								<th scope="col">公告值</th>
								<th scope="col">状态</th>
								<th scope="col">开始时间</th>
								<th scope="col">结束时间</th>
								<th scope="col">创建时间</th>
								<th scope="col">更新时间</th>
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
	                		 url : "${ctxPath}/notice/ajax/getdata",
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
								'data' : 'noticeid' ,'visible' : false
							},
							{
								'data' : 'noticename'
							},
							{
								'data' : 'noticevalue'
							},
							{
								'data' : function(obj) {
									var sReturn = obj.status;
									if (sReturn == "0") {
										sReturn = "未发布";
									} else if (sReturn == "1") {
										sReturn = "已发布";
									} else if (sReturn == "2"){
										sReturn = "过期";
									}
									return sReturn;
								}
							
							},
							
							{
								'data' : 'begintime'
							},
							{
								'data' : 'endtime'
							},
							{
								'data' : 'createtime','visible' : false
							},
							{
								'data' : 'updatetime','visible' : false
							},
 							{
								'data' : function(obj) { 
									var statusStr = obj.status;
									var sReturn = "";
									if(statusStr == "1"){
										sReturn =
											"<span><a class='tableedit' title='修改' data-val='{"+'"noticeid":"'+obj.noticeid+'"'+"}'><i class='fa fa-edit'></i></a></span> "+
											"<span><a class='tabledel' title='删除' data-val='{"+'"noticeid":"'+obj.noticeid+'","pname":"'+obj.noticename+'"'+"}'><i class='fa fa-trash-o'></i></a></span> "
									}else if(statusStr == "0"){
										sReturn =
											"<span><a class='tablefabu' title='发布' data-val='{"+'"noticeid":"'+obj.noticeid+'","pname":"'+obj.noticename+'"'+"}'><i class='fa fa-trash-o'></i></a></span> " +
								    		"<span><a class='tableedit' title='修改' data-val='{"+'"noticeid":"'+obj.noticeid+'"'+"}'><i class='fa fa-edit'></i></a></span> "+
											"<span><a class='tabledel' title='删除' data-val='{"+'"noticeid":"'+obj.noticeid+'","pname":"'+obj.noticename+'"'+"}'><i class='fa fa-trash-o'></i></a></span> "
									}else if(statusStr == "2") {
										sReturn = "<span><a class='tabledel' title='删除' data-val='{"+'"noticeid":"'+obj.noticeid+'","pname":"'+obj.noticename+'"'+"}'><i class='fa fa-trash-o'></i></a></span> "
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
					$('#noticenames').val("");
				});
				//修改
			    $("#table_id").on("click",".tableedit",function(){
			    	submiturl("${ctxPath}/notice/editinit",$(this).attr("data-val"));
			    });
				//删除
				$("#table_id").on("click",".tabledel",function(){
			    	submiturl("${ctxPath}/notice/delinit",$(this).attr("data-val"));
			    });
				//发布
				$("#table_id").on("click",".tablefabu",function(){
			    	submiturl("${ctxPath}/notice/fabuinit",$(this).attr("data-val"));
			    });
			});
		</script>
</body>
</html>