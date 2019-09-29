<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<title>活动管理-游戏活动管理</title>
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<i class="icon-cog"></i>
				<li>活动管理</li>
				<li class="active" id="bill_check">游戏活动管理</li>
			</ol>
		</div>
		<div class="mainbox">
			<form:form cssClass="form-horizontal" action="${ctxPath}/gameinit/init" methodParam="post" modelAttribute="cloudGameInitModel">
				<div class="query">
				 	<div class="query-tit">查询条件</div>
				 	<div class="query_1">
						<div class="query-text">
							<span>游戏名称</span>
							<div class="col-sm-3">
								<form:input path="playname" cssClass="form-control"/>
<!-- 								<input type="text" id="username" name="username" class="form-control"> -->
							</div>
						</div>
						
<!-- 						<div class="query-text"> -->
<!-- 							<span>游戏名称</span> -->
<!-- 							<div class="col-sm-3"> -->
<!-- 								<select id="id" name="id" class="form-control select" > -->
<!-- 									<option value="">-请选择-</option>  -->
<%-- 									<c:forEach items="${cloudGameInitModel.gameCodeAndNameList}" var="showList"> --%>
<%-- 										<option value="${showList.id}">${showList.playname}</option>  --%>
<%-- 									</c:forEach> --%>
<!-- 								</select> -->
<!-- 							</div> -->
<!-- 						</div> -->
						
						<div class="query-text">
							<span>状态</span>
							<select id="status" name="status" class="form-control select" >
								<option value="">全部</option>
								<option value="0" ${'0' eq cloudGameInitModel.status ? 'selected="selected"':''}>未上线</option>
								<option value="1" ${'1' eq cloudGameInitModel.status ? 'selected="selected"':''}>已上线</option>
								<option value="2" ${'2' eq cloudGameInitModel.status ? 'selected="selected"':''}>已下线</option>
								<option value="3" ${'3' eq cloudGameInitModel.status ? 'selected="selected"':''}>推荐</option>
							</select>
						</div>
				 		<div class="query-btn">
							<button type="submit" class="btn btn-primary" id="queryBtn">查询</button>
							<a href="${ctxPath}/gameinit/init" class="btn btn-primary">重置</a>
						</div>
				 	</div>
				</div>
			</form:form>
			<div class="tablebox">
				 <div class="table-tit">
					<div class="tit-left" >
						游戏管理
					</div>
					<div class="tit-btn">
						<span><a href="${ctxPath}/gameinit/addinit" class="btn btn-primary"><i class="fa fa-plus"></i>新增</a></span>
					</div>
				</div> 
				<table id="table_id" class="tablemain">
				  <thead>
					  <tr class="tit-top">
					    <th scope="col">游戏编号</th>
					    <th scope="col">顺序号</th>
					    <th scope="col">渠道</th>
					    <th scope="col">游戏名称</th>
					    <th scope="col">部署路径</th>
					    <th scope="col">状态</th>
					    <th scope="col">更新时间</th>	
					    <th scope="col">创建时间</th>	
					    <th scope="col">操作人</th>
					    <th scope="col">操作</th>
					  </tr>
				  </thead> 
				  <tbody id="tbody">
				  	<c:forEach items="${cloudGameInitModel.gameInitList}" var="gamelist">
					  		<tr>
					  			<td>${gamelist.id}</td>
					  			<td>${gamelist.indexnum}</td>
					  			<td>
					  				<c:choose>
					  					<c:when test="${gamelist.channel eq '0'}">微信</c:when>
					  					<c:when test="${gamelist.channel eq '1'}">APP</c:when>
					  				</c:choose>
					  			</td>
					  			<td>${gamelist.playname}</td>
					  			<td>${gamelist.deploypath}</td>
					  			<td>
					  				<c:choose>
					  					<c:when test="${gamelist.status eq '0'}">未上线</c:when>
					  					<c:when test="${gamelist.status eq '1'}">上线</c:when>
					  					<c:when test="${gamelist.status eq '2'}">下线</c:when>
					  					<c:when test="${gamelist.status eq '3'}">推荐</c:when>
					  				</c:choose>
					  			</td>
					  			<td>${gamelist.updatetime}</td>
					  			<td>${gamelist.createtime}</td>
					  			<td>${gamelist.operator}</td>
					  			<td>
									<span><a class="tableedit" title="编辑" data-val='{"id":"${gamelist.id}"}' href="javascript:;"><i class='fa fa-edit'></i></a></span> 
<%-- 									<span><a class="tabledel" title="删除" data-val='{"id":"${gamelist.id}"}' href="javascript:;"><i class='fa fa-trash-o'></i></a></span> --%>
								</td>
					  		</tr>
					  	</c:forEach>
				  </tbody>
			</table>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript" >
		
			var table;
			
			$(document).ready( function () {
				table=$('#table_id').DataTable({"ajax":false});
				//编辑
			    $(".tablemain").on("click",".tableedit",function(){
			    	submiturl("${ctxPath}/gameinit/editinfo",$(this).attr("data-val"));
			    });
			 	 //删除
// 			    $("#table_id").on("click",".tabledel",function(){
// 			    	submiturl("${ctxPath}/gameinit/delinit",$(this).attr("data-val"));
// 			    });
			});
		</script>
</body>
</html>