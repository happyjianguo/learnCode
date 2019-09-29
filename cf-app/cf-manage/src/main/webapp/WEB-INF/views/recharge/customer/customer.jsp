<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><fmt:message key="recharge.customer.title"/></title>
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
				<div class="main-content">
					<div class="list-nav">
						<ol class="breadcrumb">
							<li><fmt:message key="recharge.customer.recharge"/></li>
							<li class="active" id="bill_check"><fmt:message key="recharge.customer.customer"/></li>
						</ol>
					</div>
						<div class="mainbox">
						<div class="tablebox">
                         <form:form cssClass="form-horizontal" action="${ctxPath}/customer/init" methodParam="post" modelAttribute="customerModel">					
			             <div class="query">			          				      
					      <div class="query-tit"><fmt:message key="recharge.customer.querycondition"/></div>
					       <div class="query_1">
						     <div class="query-text">
							   <span><fmt:message key="recharge.customer"/></span> 
							   <select id="qid" name="qid" class="form-control select" >
								<option value="" ><fmt:message key="recharge.all"/></option>	
									<c:forEach var="cus" items="${customerModel.customerlistselect}">
									<option value="${cus.id}" ${customerModel.qid eq cus.id ? 'selected="selected"':''}>${cus.cname}</option>
								</c:forEach>							
							</select>							
						     </div>					
						     <div class="query-text">
							<span><fmt:message key="recharge.customer.rechargename"/></span> 
							<form:input path="qmname" cssClass="form-control"/>									
						    </div>
						    <div class="query-btn">
						      <button type="submit" class="btn btn-primary" id="queryBtn"><fmt:message key="button.query"/></button>				
							<a href="${ctxPath}/customer/init" class="btn btn-primary"><fmt:message key="button.reset"/></a>
						    </div>
					   </div>
				      </div>
				     </form:form>						
							<div class="table-tit">
								<div class="tit-left" >
									<fmt:message key="recharge.customer.customer"/>
								</div>
								<div class="tit-btn">
<!-- 									<span><button type="button" class="btn btn-primary addImuser" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i>新增</button></span> -->
									<span><a href="${ctxPath}/customer/addinit" class="btn btn-primary"><i class="fa fa-plus"></i><fmt:message key="button.add"/></a></span>
								</div>
							</div> 
							<table id="table_id" class="tablemain">
								  <thead>
									  <tr class="tit-top">
									   <!--  <th scope="col">供应商ID</th> -->
									    <th scope="col"><fmt:message key="recharge.customer"/></th>
									    <th scope="col"><fmt:message key="recharge.customer.rechargename"/></th>
									    <th scope="col"><fmt:message key="recharge.customer.rechargephone"/></th>
									    <th scope="col"><fmt:message key="recharge.customer.rechargeemail"/></th>
									    <th scope="col"><fmt:message key="recharge.operation"/></th>
									  </tr>
								  </thead>
								  <tbody>
								  	<c:forEach items="${customerModel.customerlist}" var="customer">
								  		<tr>
								  			<%-- <td data-name="cname">${customer.id}</td> --%>
								  			<td data-name="cname" data-val="${customer.cname}">${customer.cname}</td>
								  			<td>${customer.mname}</td>
								  			<td>${customer.phone}</td>
								  			<td>${customer.email}</td>
								  			<td>
												<span><a class="tableedit" title="<fmt:message key="button.edit"/>"  data-val='{"id":"${customer.id}"}' href="javascript:;"><i class='fa fa-edit'></i></a></span> 
												<span><a class="tabledel"  title="<fmt:message key="button.del"/>"  data-val='{"id":"${customer.id}","cname":"${customer.cname}"}'href="javascript:;"><i class='fa fa-trash-o'></i></a></span> 
											
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
			    $(".tablemain").on("click",".tableedit",function(){
			    	submiturl("${ctxPath}/customer/editinit",$(this).attr("data-val"));
			    });
			  	//删除
			    $(".tablemain").on("click",".tabledel",function(){
			    	submiturl("${ctxPath}/customer/delinit",$(this).attr("data-val"));
			    });
			});
		</script>
</body>
</html>
