<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>系统参数管理</title>
</head>
<body>
		<div class="main-content">
			<div class="list-nav">
				<ol class="breadcrumb">
					<li>系统管理</li>
					<li class="active" id="bill_check">参数管理</li>
				</ol>
			</div>
			<div class="mainbox">
				<div class="query">
					<form id="queryform">
					<div class="query-tit">查询条件</div>
					<div class="query-text">
						<span>参数名称</span><input type="text"
							class="form-control searchText" id="sysBaseId" name="sysBaseId">
					</div>
					<div class="query-btn">
						<button type="button" class="btn btn-primary" id="queryBtn">查询</button>
						<a href="${ctxPath}/base/init" class="btn btn-primary">重置</a>
					</div>
					</form>
				</div>
				
				<div class="tablebox">
					<div class="table-tit">
						<div class="tit-left">参数管理</div>
					</div>
					<table class="tablemain">
						<thead>
							<tr class="tit-top">
								<th scope="col">参数名称</th>
								<th scope="col">参数值</th>
								<th scope="col">参数描述</th>
								<th scope="col">操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<script type="text/javascript">
		$(function() {
			var table = $(".tablemain").DataTable({
					"ajax": function (data, callback, settings) {
	                	$.ax({
	                		"url":"${ctxPath}/base/pubssysbaselist",
	                		"data" : function() {
	                			return serializeForm($("#queryform"));
					        },
					        successfn : function(result) {
					        	callback(result);
					        }
	                	});
	                  },
					"columns" : [
							{
								'data' : 'sysBaseId'
							},
							{
								'data' : 'sysBaseValue'
							},
							{
								'data' : 'sysBaseMemo'
							},
							{
								'data' : function(obj) { 
									return "<span><a class='modifySysBase' title='修改' href='javascript:;'><i class='fa fa-edit'></i></a></span>";
								}
										
							} ]
				});

			/*单个修改*/
			$(".tablemain").on("click", ".modifySysBase", function() {
				var data = table.row($(this).parents('tr')).data();
				var formdata = {};
				formdata.sysBaseId=data.sysBaseId;
				formdata.sysBaseValue=data.sysBaseValue;
				formdata.sysBaseMemo=data.sysBaseMemo;
				submiturl("${ctxPath}/base/updateinit", formdata);
			});
			//条件查询
			$("#queryBtn").on("click", function() {
				table.ajax.reload();
			});
		});
	</script>
</body>
</html>