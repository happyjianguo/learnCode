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
				<li class="active" id="bill_check">模板管理</li>
			</ol>
		</div>
		<div class="mainbox">
			<form:form cssClass="form-horizontal" action="${ctxPath}/gametemplate/init" methodParam="post" modelAttribute="cloudGameInitModel">
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
						
				 		<div class="query-btn">
							<button type="submit" class="btn btn-primary" id="queryBtn">查询</button>
							<a href="${ctxPath}/gametemplate/init" class="btn btn-primary">重置</a>
						</div>
				 	</div>
				</div>
			</form:form>
			<div class="tablebox">
				 <div class="table-tit">
					<div class="tit-left" >
						模板管理
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
<!-- 					    <th scope="col">操作人</th> -->
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
					  					<c:when test="${gamelist.channel eq '1'}">手机APP</c:when>
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
<%-- 					  			<td>${gamelist.operator}</td> --%>
					  			<td>
									<span><a class="tableedit" title="创建模板" data-val='{"gameid":"${gamelist.id}"}' href="javascript:;"><i class='fa fa-edit'></i></a></span> 
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
				$("#table_id").on("click",".tableedit",function(){
					/* var obj = JSON.parse($(this).attr("data-val")); */
					/* alert(obj.gameid); */
			    	/* submiturl("${ctxPath}/gametemplate/tempinit&gameid="+obj.gameid); */
 			    	submiturl("${ctxPath}/gametemplate/tempinit",$(this).attr("data-val"));
			    });
				
				table=$('#table_id').DataTable({"ajax":false});
				//编辑
// 			    $(".tablemain").on("click",".tableedit",function(){
// 			    	submiturl("${ctxPath}/gametemplate/editinfo",$(this).attr("data-val"));
// 			    });
			});
		</script>
</body>
</html>