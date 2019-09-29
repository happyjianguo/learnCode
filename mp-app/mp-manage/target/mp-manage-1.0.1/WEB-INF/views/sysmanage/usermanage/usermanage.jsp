<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>系统管理-用户管理</title>
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
				<li class="active" id="bill_check">用户管理</li>
			</ol>
		</div>
		 <div class="mainbox">
		 	<form:form cssClass="form-horizontal" action="${ctxPath}/imuser/init" methodParam="post" modelAttribute="userManageModel">
		 	<div class="query">
				<div class="query-tit">查询条件</div>
				<div class="query-text">
					<span>用户名</span>
					<form:input path="qusername" cssClass="form-control"/>
				</div>
				<div class="query-text">
					<span>姓名</span>
					<form:input path="qcname" cssClass="form-control"/>
				</div>
				<div class="query-text">
					<span>用户角色</span>
				    <select name="qroleid" class="form-control">
				    	<option value="">全部</option>
				    	<c:forEach var="role" items="${userManageModel.roleList}">
				    		<option value="${role.roleId}" ${userManageModel.qroleid eq role.roleId ? 'selected="selected"':''}>${role.roleName}</option>
				    	</c:forEach>
				    </select>
				</div>
				<div class="query-btn">
					<button type="submit" class="btn btn-primary" id="queryBtn">查询</button>
					<a href="${ctxPath}/imuser/init" class="btn btn-primary">重置</a>
				</div>
			</div>
			</form:form>
			<div class="tablebox">
				<div class="table-tit">
					<div class="tit-left" >
						用户管理
					</div>
					<div class="tit-btn">
<!-- 									<span><button type="button" class="btn btn-primary addImuser" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i>新增</button></span> -->
						<span><a href="${ctxPath}/imuser/addinit" class="btn btn-primary"><i class="fa fa-plus"></i>新增</a></span>
					</div>
				</div> 
				<table id="table_id" class="tablemain">
					  <thead>
						  <tr class="tit-top">
						    <th scope="col">用户名</th>
						    <th scope="col">姓名</th>
						    <th scope="col">用户角色</th>
						    <th scope="col">手机号</th>
						    <th scope="col">邮箱</th>
						    <th scope="col">创建时间</th>
						    <th scope="col">状态</th>
						    <th scope="col">操作</th>
						  </tr>
					  </thead>
					  <tbody>
					  	<c:forEach items="${userManageModel.userList}" var="user">
					  		<tr>
					  			<td data-name="username">${user.username}</td>
					  			<td data-name="cname" data-val="${user.cname}">${user.cname}</td>
					  			<td>${user.rolename}</td>
					  			<td>${user.phoneno}</td>
					  			<td>${user.email}</td>
					  			<td>${user.createtime}</td>
					  			<td>
					  				<c:choose>
					  					<c:when test="${user.status eq '0'}">失效</c:when>
					  					<c:when test="${user.status eq '1'}">正常</c:when>
					  					<c:when test="${user.status eq '2'}">冻结</c:when>
					  				</c:choose>
					  			</td>
					  			<td>
									<span><a class="tableedit" title="修改" data-val='{"userid":"${user.userid}"}' href="javascript:;"><i class='fa fa-edit'></i></a></span> 
<%-- 						    				<span><a class="tableinitpwd" title="密码重置" data-val='{"userid":"${user.userid}"}' href="javascript:;"><i class='fa fa-repeat'></i></a></span> --%>
									<span><a class="tabledel" title="删除" data-val='{"userid":"${user.userid}","username":"${user.username}"}' href="javascript:;"><i class='fa fa-trash-o'></i></a></span> 
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
			$(function($) {
				var table=$('#table_id').DataTable({"ajax":false});
				//修改
			    $("#table_id").on("click",".tableedit",function(){
			    	submiturl("${ctxPath}/imuser/editinit",$(this).attr("data-val"));
			    });
			  	//密码重置
// 			    $("#table_id").on("click",".tableinitpwd",function(){
// 			    	submiturl("${ctxPath}/imuser/initPwd",$(this).attr("data-val"));
// 			    });
			  	//删除
			    $("#table_id").on("click",".tabledel",function(){
			    	submiturl("${ctxPath}/imuser/delinit",$(this).attr("data-val"));
			    });
			});
		</script>
</body>
</html>