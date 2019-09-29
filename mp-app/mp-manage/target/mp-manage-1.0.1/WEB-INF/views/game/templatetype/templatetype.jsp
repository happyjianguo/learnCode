<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<title>活动管理-模板管理</title>
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<i class="icon-cog"></i>
				<li>活动管理</li>
				<li class="active" id="bill_check">游戏模板分类管理</li>
			</ol>
		</div>
		<div class="mainbox">
			<%-- action="${ctxPath}/gametmptype/init" --%>
			<form:form cssClass="form-horizontal" id="queryform" methodParam="post" modelAttribute="cloudGameTemplateTypeModel">
				<div class="query">
				 	<div class="query-tit">查询条件</div>
				 	<div class="query_1">
						<div class="query-text">
							<span>模版分类名称</span>
							<div class="col-sm-3">
								<form:input path="tmptypename" cssClass="form-control"/>
 								<!-- <input type="text" id="username" name="username" class="form-control"> -->
							</div>
						</div>
						<div class="query-text">
						<span>游戏名称</span>
							<div class="col-sm-3 select-list">
								<select id="gameid" name="gameid" class="form-control select actionid" >
									<option value="">请选择</option>
									<c:forEach items="${cloudGameTemplateTypeModel.game }" var="gamelist">
										<option value="${gamelist.id }">${gamelist.playname}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
				 		<div class="query-btn">
							<button type="button" class="btn btn-primary" id="queryBtn">查询</button>
<!-- 							<button type="submit" class="btn btn-primary" id="queryBtn">查询</button> -->
							<a href="${ctxPath}/gametmptype/init" class="btn btn-primary">重置</a>
						</div>
				 	</div>
				</div>
			</form:form>
			<div class="tablebox">
				 <div class="table-tit">
					<div class="tit-left" >
						游戏模板分类管理
					</div>
					<div class="tit-btn">
						<span><a href="${ctxPath}/gametmptype/addinit" class="btn btn-primary"><i class="fa fa-plus"></i>新增</a></span>
					</div>
				</div> 
				<table id="table_id" class="tablemain">
				  <thead>
					  <tr class="tit-top">
					    <th scope="col">游戏编号</th>
					    <th scope="col">游戏名称</th>
					    <th scope="col">游戏模版分类id</th>
					    <th scope="col">游戏模版分类名称</th>
					    <th scope="col">操作</th>
					  </tr>
				  </thead> 
				  <tbody id="tbody">
				  		<%-- 
				  		<c:forEach items="${cloudGameTemplateTypeModel.gameTemplateTypelist}" var="gameTemplateTypelist">
					  		<tr>
					  			<td>${gameTemplateTypelist.gameid}</td>
					  			<td>${gameTemplateTypelist.playname}</td>
					  			<td>${gameTemplateTypelist.tmptypeid}</td>
					  			<td>${gameTemplateTypelist.tmptypename}</td>
					  			<td>
									<span><a class="tableedit" title="修改" data-val='{"tmptypeid":"${gameTemplateTypelist.tmptypeid}"}' href="javascript:;"><i class='fa fa-edit'></i></a></span> 
									<span><a class="tabledel" title="删除" data-val='{"tmptypeid":"${gameTemplateTypelist.tmptypeid}"}' href="javascript:;"><i class='fa fa-trash-o'></i></a></span>
								</td>
					  		</tr>
					  	</c:forEach> 
					  	--%>
				  </tbody>
			</table>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript" >
		
			var table;
			
			$(document).ready( function () {
				table = $('#table_id').DataTable({
					"ajax": function (data, callback, settings) {
	                	$.ax({
	                		"url":"${ctxPath}/gametmptype/ajax/query",
	                		data : function() {
								return serializeForm($('#queryform'));
							},
					        successfn : function(result) {
					        	callback(result);
					        }
	                	});
	                  },
					"columns" : [
							{'data' : 'gameid'},
							{'data' : 'playname'},
							{'data' : 'tmptypeid'},
							{'data' : 'tmptypename'},
							{
								'data' : function(obj) { 
									sReturn = 
										"<span><a class='tableedit' title='修改' data-val='{"+'"tmptypeid":"'+obj.tmptypeid+'"'+"}'><i class='fa fa-edit'></i></a></span>"+
										"<span><a class='tabledel' title='删除' data-val='{"+'"tmptypeid":"'+obj.tmptypeid+'"'+"}'><i class='fa fa-trash-o'></i></a></span>";
									return sReturn;
								}
							}
						]
				});
				/*条件查询*/
				$(".query-btn").on("click", "#queryBtn", function() {
					 table.ajax.reload();
				});
				//编辑
				$("#table_id").on("click",".tableedit",function(){
			    	submiturl("${ctxPath}/gametmptype/editinit",$(this).attr("data-val"));
			    });
				//删除
				$("#table_id").on("click",".tabledel",function(){
			    	submiturl("${ctxPath}/gametmptype/delinit",$(this).attr("data-val"));
			    });
				
				/* table=$('#table_id').DataTable({"ajax":false}); */
			});
		</script>
</body>
</html>