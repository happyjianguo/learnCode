<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>系统管理-机构管理</title>
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li>系统管理</li>
				<li class="active" id="bill_check">机构管理</li>
			</ol>
		</div>
			<div class="mainbox">
				<div class="query">
					<form id="query">
						<div class="query-tit">查询条件</div>
						<div class="query-text">
							<span>机构名称</span>
							<input type="text" id="queryorgname" name="queryorgname" class="form-control">
						</div>
						<div class="query-text">
								<span>是否总行</span>
								<select id="queryIsparentorgid" name="queryIsparentorgid" class="form-control select">
									<option value="" selected>否</option>
									<option value="1">是</option>
								</select>
						</div>
						<div class="query-text">
								<span>所属总行</span>
								<select id="queryparentorgid" name="queryparentorgid" class="form-control select">
									<option value="" selected>请选择</option>
									<c:forEach var="orgination" items="${cloudplatformorginationModel.cloudplatformorginationList}">
										<option value="${orgination.id}">${orgination.orgname}</option>
									</c:forEach>
								</select>
						</div>
						<div class="query-text">
								<span>所属分行</span>
								<select id="branchid" name="branchid" class="form-control select branchid" >
									<option value="" selected>请选择</option>
								</select>	
						</div>
						<div class="query-text">
							<span>机构状态</span>
							<div class="col-sm-4">
							      <select id="queryorgstatus" name="queryorgstatus" class="form-control select">
							      	<option value="">请选择</option>
							      	<option value="0">未生效</option>
									<option value="1">生效</option>
							      </select>
							</div>
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
						机构管理
					</div>
					<div class="tit-btn">
						<span><a href="${ctxPath}/orgination/addinit" class="btn btn-primary"><i class="fa fa-plus"></i>新增</a></span>
					</div>
				</div>
				<table id="table_id" class="tablemain">
					<thead>
						<tr class="tit-top">
						    <th scope="col">机构id</th>
						    <th scope="col">机构名称</th>
						    <th scope="col">父机构id</th>
						    <th scope="col">父机构名称</th>
							<th scope="col">机构状态</th>
							<th scope="col">机构备注</th>
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
	                		 url : "${ctxPath}/orgination/ajax/getdata",
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
								'data' : 'id' ,'visible' : false
							},
							{
								'data' : 'orgname'
							},
							{
								'data' : 'parentorgid' ,'visible' : false
							},
							{
								'data' : 'parentorgname'
							},
							{
								'data' : function(obj) {
									var sReturn = obj.orgstatus;
									if (sReturn == "0") {
										sReturn = "未生效";
									} else if (sReturn == "1") {
										sReturn = "生效";
									} else{
										sReturn = "未知状态";
									}
									return sReturn;
								}
							
							},
							
							{
								'data' : 'note'
							},
							{
								'data' : 'createtime'
							},
							{
								'data' : 'updatetime','visible' : false
							},
 							{
								'data' : function(obj) { 
										sReturn =
											"<span><a class='tableedit' title='修改' data-val='{"+'"id":"'+obj.id+'"'+"}'><i class='fa fa-edit'></i></a></span> "+
											"<span><a class='tabledel' title='删除' data-val='{"+'"id":"'+obj.id+'","orgname":"'+obj.orgname+'"'+"}'><i class='fa fa-trash-o'></i></a></span> "
 										return sReturn;
 								}
 							}
					 ]
				});
				//分行机构初始化
			    $(".query-text").on("change","#queryparentorgid",function(){
			    	getbranchid();
			    });
				//是否为总行机构
			    $(".query-text").on("change","#queryIsparentorgid",function(){
			    	$('#queryparentorgid').val("");
					$('#branchid').val("");
			    	var isParentorgid=$("#queryIsparentorgid").val();
			    	if(isParentorgid == '1'){
				    	$('#queryparentorgid').attr("disabled",true);
						$('#branchid').attr("disabled",true);
			    	}else{
				    	$('#queryparentorgid').removeAttr("disabled");
						$('#branchid').removeAttr("disabled");
			    	}
			    });
			  	//获取分行机构
		    	function getbranchid(){
		    		$.ax({
	            		url  :"${ctxPath}/orgination/ajax/initbranchid",
	            		data : {queryparentorgid:$('#queryparentorgid').val()},
				        successfn : function(result) {	
			        		$(".branchid").find("option").not("[value='']").remove(); 
				        	$.each(result.data, function(i, item){ 
			        			$(".branchid").append("<option value="+item.id+">"+item.orgname+"</option>");
				        	});  	
				        }
	            	});
		    	}
				
				/*条件查询*/
				$(".query-btn").on("click", "#queryBtn", function() {
					table.ajax.reload();
				});
				/*重置查询条件*/
				$(".query-btn").on("click", "#resetBtn", function() {
					$('#queryorgname').val("");
					$('#queryorgstatus').val("");
					$('#queryparentorgid').val("");
					$("#branchid").empty();
					$("#branchid").append('<option value="">请选择</option>');
					$('#queryIsparentorgid').val("");
					$('#queryparentorgid').attr("disabled",false);
					$('#branchid').attr("disabled",false);
				});
				//修改
			    $("#table_id").on("click",".tableedit",function(){
			    	submiturl("${ctxPath}/orgination/editinit",$(this).attr("data-val"));
			    });
				//删除
				$("#table_id").on("click",".tabledel",function(){
			    	submiturl("${ctxPath}/orgination/delinit",$(this).attr("data-val"));
			    });
			});
		</script>
</body>
</html>