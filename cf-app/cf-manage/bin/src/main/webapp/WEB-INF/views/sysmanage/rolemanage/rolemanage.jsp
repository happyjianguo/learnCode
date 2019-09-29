<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>系统管理-角色管理</title>
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
			<div class="main-content">
				<div class="list-nav">
					<ol class="breadcrumb">
						<li>系统管理</li>
						<li class="active" id="bill_check">角色管理</li>
					</ol>
				</div>
				 <div class="mainbox">
				 	
					<div class="tablebox">
						<div class="table-tit">
							<div class="tit-left" >
								角色管理
							</div>
							<div class="tit-btn">
								<span><a href="${ctxPath}/role/addinit" class="btn btn-primary"><i class="fa fa-plus"></i>新增</a></span>
							</div>
						</div> 
						<table id="table_id" class="tablemain">
							<thead>
								  <tr class="tit-top">
								   
								    <th scope="col">角色名称</th>
								    <th scope="col">创建时间</th>
								    <th scope="col">操作</th>
								  </tr>
							</thead>
						 <tbody>
							  	<c:forEach items="${roleManageModel.rolelist}" var="role">
							  		<tr>
							  			<td data-name="roleName" data-val="${role.roleName}">${role.roleName}</td>
							  			<td>${role.createTime}</td>
							  			<td>
											<span><a class="tableedit" title="修改" data-val='{"roleId":"${role.roleId}","roleName":"${role.roleName}"}' href="javascript:;"><i class='fa fa-edit'></i></a></span> 
											<span><a class="tabledel" title="删除" data-val='{"roleId":"${role.roleId}","roleName":"${role.roleName}"}' href="javascript:;"><i class='fa fa-trash-o'></i></a></span> 
										</td>
							  		</tr>
							  	</c:forEach>
							  </tbody>
						</table>
					</div>
				</div>			
			</div>
	
	<cx:script>
		<script type="text/javascript" >
		$(function($) {
			var table=$('#table_id').DataTable({"ajax":false});
			//修改
		    $("#table_id").on("click",".tableedit",function(){
// 		    	var data = table.row($(this).parents('tr')).data();
		    	submiturl("${ctxPath}/role/editinit",$(this).data("val"));
		    });
		  	
		  	//删除
		    $("#table_id").on("click",".tabledel",function(){
		    	submiturl("${ctxPath}/role/delinit",$(this).attr("data-val"));
		    });
		  	
 			
		});
		
		</script>
	</cx:script>
</body>
</html>