<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员管理-会员管理</title>
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<i class="icon-cog"></i>
				<li>会员管理</li>
				<li class="active" id="bill_check">会员管理</li>
			</ol>
		</div>
		<div class="mainbox">
			<form:form cssClass="form-horizontal" action="${ctxPath}/membermanage/init" methodParam="post" modelAttribute="registerReviewModel">
				<div class="query">
				 	<div class="query-tit">查询条件</div>
				 	<div class="query_1">
						<div class="query-text">
							<span>用户名</span>
							<div class="col-sm-3">
<%-- 								<form:input path="username" cssClass="form-control"/> --%>
								<input type="text" id="username" name="username" class="form-control">
							</div>
						</div>
						<div class="query-text">
							<span>状态</span>
							<select id="status" name="status" class="form-control select" >
								<option value="">全部</option>
								<option value="0" ${'0' eq registerReviewModel.status ? 'selected="selected"':''}>失效</option>
								<option value="1" ${'1' eq registerReviewModel.status ? 'selected="selected"':''}>正常</option>
								<option value="2" ${'2' eq registerReviewModel.status ? 'selected="selected"':''}>冻结</option>
								<option value="3" ${'3' eq registerReviewModel.status ? 'selected="selected"':''}>待审核</option>
								<option value="4" ${'4' eq registerReviewModel.status ? 'selected="selected"':''}>审核失败</option>
							</select>
						</div>
						
						<div class="query-text">
							<span>机构名称</span>
							<div class="col-sm-3">
								<select id="orgid" name="orgid" class="form-control select" >
									<option value="">-请选择-</option> 
									<c:forEach items="${registerReviewModel.cloudpforgList}" var="showList">
										<option value="${showList.id}" ${registerReviewModel.orgid eq showList.id ? 'selected="selected"':''}>${showList.orgname}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
				 		<div class="query-btn">
							<button type="submit" class="btn btn-primary" id="queryBtn">查询</button>
							<a href="${ctxPath}/membermanage/init" class="btn btn-primary">重置</a>
						</div>
				 	</div>
				</div>
			</form:form>
			<div class="tablebox">
				 <div class="table-tit">
					<div class="tit-left" >
						会员管理
					</div>
					<div class="tit-btn">
						<span><a href="${ctxPath}/membermanage/addinit" class="btn btn-primary"><i class="fa fa-plus"></i>新增</a></span>
					</div>
				</div> 
				<table id="table_id" class="tablemain">
				  <thead>
					  <tr class="tit-top">
					    <th scope="col">机构code</th>
					    <th scope="col">手机号</th>
					    <th scope="col">用户名</th>
					    <th scope="col">姓名</th>
					    <th scope="col">性别</th>
					    <th scope="col">邮箱</th>
					    <!-- <th scope="col">创建时间</th> -->
					    <th scope="col">更新时间</th>	
					    <th scope="col">状态</th>	
					    <th scope="col">审核人</th>
					    <th scope="col">审核意见</th>	
					    <th scope="col">操作</th>
					  </tr>
				  </thead> 
				  <tbody id="tbody">
				  	<c:forEach items="${registerReviewModel.fsmUserList}" var="interfacemanager">
					  		<tr>
					  			<td>${interfacemanager.orgid}</td>
					  			<td>${interfacemanager.phoneno}</td>
					  			<td>${interfacemanager.username}</td>
					  			<td>${interfacemanager.cname}</td>
					  			<td>
					  				<c:choose>
					  					<c:when test="${interfacemanager.mf eq '1'}">男</c:when>
					  					<c:when test="${interfacemanager.mf eq '2'}">女</c:when>
					  				</c:choose>
					  			</td>
					  			<td>${interfacemanager.email}</td>
					  			<%-- <td>${interfacemanager.createtime}</td> --%>
					  			<td>${interfacemanager.updatetime}</td>
					  			<td>
					  				<c:choose>
					  					<c:when test="${interfacemanager.status eq '0'}">失效</c:when>
					  					<c:when test="${interfacemanager.status eq '1'}">正常</c:when>
					  					<c:when test="${interfacemanager.status eq '2'}">冻结</c:when>
					  					<c:when test="${interfacemanager.status eq '3'}">待审核</c:when>
					  					<c:when test="${interfacemanager.status eq '4'}">审核失败</c:when>
					  				</c:choose>
					  			</td>
					  			<td>${interfacemanager.reviewper}</td>
					  			<td>${interfacemanager.reviewdes}</td>
					  			<td>
									<span><a class="tableedit" title="编辑" data-val='{"userid":"${interfacemanager.userid}"}' href="javascript:;"><i class='fa fa-edit'></i></a></span> 
									<span><a class="tabledel" title="删除" data-val='{"userid":"${interfacemanager.userid}"}' href="javascript:;"><i class='fa fa-trash-o'></i></a></span>
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
			    	submiturl("${ctxPath}/membermanage/editinfo",$(this).attr("data-val"));
			    });
			 	 //删除
			    $("#table_id").on("click",".tabledel",function(){
			    	submiturl("${ctxPath}/membermanage/delinfo",$(this).attr("data-val"));
			    });
			});
		</script>
</body>
</html>