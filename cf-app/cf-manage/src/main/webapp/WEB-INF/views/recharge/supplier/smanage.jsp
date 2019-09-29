<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><fmt:message key="recharge.supplier.title"/></title>
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li><fmt:message key="recharge.supplier.recharge"/></li>
				<li class="active" id="bill_check"><fmt:message key="recharge.supplier.supplier"/></li>
			</ol>
		</div>
			<div class="mainbox">
				<div class="tablebox">
				<div class="table-tit">
					<div class="tit-left" >
						<fmt:message key="recharge.supplier.supplier"/>
					</div>
					<div class="tit-btn">
<!-- 									<span><button type="button" class="btn btn-primary addImuser" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i>新增</button></span> -->
						<span><a href="${ctxPath}/supplier/addinit" class="btn btn-primary"><i class="fa fa-plus"></i><fmt:message key="button.add"/></a></span>
					</div>
				</div> 
				<table id="table_id" class="tablemain">
					  <thead>
						  <tr class="tit-top">
						   <!--  <th scope="col">供应商ID</th> -->
						    <th scope="col"><fmt:message key="recharge.supplier.suppliername"/></th>
						    <th scope="col"><fmt:message key="recharge.supplier.rechargename"/></th>
						    <th scope="col"><fmt:message key="recharge.supplier.rechargephone"/></th>
						    <th scope="col"><fmt:message key="recharge.supplier.rechargeemail"/></th>
						    <th scope="col"><fmt:message key="recharge.operation"/></th>
						  </tr>
					  </thead>
					  <tbody>
					  	<c:forEach items="${supplierModel.supplierList}" var="entity">
					  		<tr>
					  			<%-- <td data-name="username">${entity.id}</td> --%>
					  			<td data-name="cname" data-val="${entity.sname}">${entity.sname}</td>
					  			<td>${entity.mname}</td>
					  			<td>${entity.phone}</td>
					  			<td>${entity.email}</td>
					  			<td>
									<span><a class="tableedit" title="<fmt:message key="button.edit"/>"  data-val='{"id":"${entity.id}"}' href="javascript:;"><i class='fa fa-edit'></i></a></span> 
									<span><a class="tabledel"  title="<fmt:message key="button.del"/>"  data-val='{"id":"${entity.id}","sname":"${entity.sname}"}'href="javascript:;"><i class='fa fa-trash-o'></i></a></span> 
								
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
				//修改
			    $(".tablemain").on("click",".tableedit",function(){
			    	submiturl("${ctxPath}/supplier/editinit",$(this).attr("data-val"));
			    });
			  	//删除
			    $(".tablemain").on("click",".tabledel",function(){
			    	submiturl("${ctxPath}/supplier/delinit",$(this).attr("data-val"));
			    });
			});
		</script>
</body>
</html>