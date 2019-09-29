<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><fmt:message key="recharge.accessclient.title"/></title>
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li><fmt:message key="recharge.accessclient.QuotationManagement"/></li>
				<li class="active" id="bill_check"><fmt:message key="recharge.accessclient.accessclient"/></li>
			</ol>
		</div>
			<div class="mainbox">
			<div class="tablebox">
		<form:form  cssClass="form-horizontal" action="${ctxPath}/accessclient/init" methodParam="post" modelAttribute="accessclientModel">					
			<div class="query">			          				      
			   <div class="query-tit"><fmt:message key="recharge.querycondition"/></div>
			      <div class="query_1">
				     <div class="query-text">
					   <span><fmt:message key="recharge.customer"/></span>
					   <select id="qcid" name="qcid" class="form-control select qcid" >
						 <option value="" ><fmt:message key="recharge.all"/></option>	
							<c:forEach var="cus" items="${accessclientModel.customerListselect}">								
							<option value="${cus.id}" ${accessclientModel.qcid eq cus.id ? 'selected="selected"':''}>${cus.cname}</option>
						</c:forEach> 								
					</select>							
				     </div>	
				     <div class="query-text">
					   <span><fmt:message key="recharge.acid"/></span>
					   <select id="qid" name="qid" class="form-control select qid" >
						<option value="" data-cid=""><fmt:message key="recharge.all"/></option>						
						<c:forEach var="ac" items="${accessclientModel.accessclientListselect}">								
							<option value="${ac.id}" ${accessclientModel.qid eq ac.id ? 'selected="selected"':''} data-qcid="${ac.cid}">${ac.acname}</option>
						</c:forEach>
					</select>							
				     </div>						     							     				
				<div class="query-text">
					<span><fmt:message key="recharge.status"/></span> 
				    <select id="qstatus" name="qstatus" class="form-control select" >
						<option value="" ><fmt:message key="recharge.all"/></option>	
						<option value="kt" ${accessclientModel.qstatus eq 'kt' ? 'selected="selected"':''}><fmt:message key="recharge.operationkt"/></option>
						<option value="gb" ${accessclientModel.qstatus eq 'gb' ? 'selected="selected"':''}><fmt:message key="recharge.operationgb"/></option>																											
					</select>											
				</div>
				<div class="query-btn">
				<button type="submit" class="btn btn-primary" id="queryBtn"><fmt:message key="button.query"/></button>				
					<a href="${ctxPath}/accessclient/init" class="btn btn-primary"><fmt:message key="button.reset"/></a>
				</div>
			</div>
				</div>
				</form:form>					
				<div class="table-tit">
					<div class="tit-left" >
						<fmt:message key="recharge.accessclient.accessclient"/>
					</div>
					<div class="tit-btn">
<!-- 									<span><button type="button" class="btn btn-primary addImuser" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i>新增</button></span> -->
						<span><a href="${ctxPath}/accessclient/addinit" class="btn btn-primary"><i class="fa fa-plus"></i><fmt:message key="button.add"/></a></span>
					</div>
				</div> 
				<table id="table_id" class="tablemain">
					  <thead>
						  <tr class="tit-top">
						  	<th scope="col"><fmt:message key="recharge.customer"/></th>
						    <th scope="col"><fmt:message key="recharge.acid"/></th>
							<th scope="col"><fmt:message key="recharge.accessclient.contactsname"/></th>
							<th scope="col"><fmt:message key="recharge.accessclient.contactsphone"/></th>
							<th scope="col"><fmt:message key="recharge.accessclient.contactsemail"/></th>
							<th scope="col"><fmt:message key="recharge.accessclient.callbackurl"/></th>
							<th scope="col"><fmt:message key="recharge.accessclient.begintime"/></th>
							<th scope="col"><fmt:message key="recharge.accessclient.endtime"/></th>
							<th scope="col"><fmt:message key="recharge.accessclient.budget"/></th>
							<th scope="col"><fmt:message key="recharge.accessclient.status"/></th>
							<th scope="col"><fmt:message key="recharge.accessclient.cumulative"/></th>
						    <th scope="col"><fmt:message key="recharge.operation"/></th>
						  </tr>
					  </thead>
					  
					  <tbody>
					  	<c:forEach items="${accessclientModel.accessclientList}" var="accessclient">
					  		<tr>	
					  			<td>${accessclient.cname}</td>			  		
					  			<td>${accessclient.acname}</td>
					  			<td>${accessclient.mname}</td>
					  			<td>${accessclient.phone}</td>
					  			<td>${accessclient.email}</td>
					  			<td>${accessclient.callbackurl}</td>
					  			<td>${accessclient.begintime}</td>
					  			<td>${accessclient.endtime}</td>
					  			<td><fmt:formatNumber value="${accessclient.budget}" pattern="###,##0.00"/></td>
					  			<td>
					  			<c:choose>
					  				<c:when test="${accessclient.status == 'kt'}">
					  					<fmt:message key="recharge.operationkt"/>
					  				</c:when>
					  				<c:otherwise>
					  					<fmt:message key="recharge.operationgb"/>
					  				</c:otherwise>
					  			</c:choose>
					  			</td>
					  			<td>${accessclient.cumulative}</td>
					  			
					  			<td>
					  			
									<span><a class="tableedit" title="<fmt:message key="button.edit"/>"  data-val='{"id":"${accessclient.id}"}' href="javascript:;"><i class='fa fa-edit'></i></a></span> 
									<span><a class="tabledel"  title="<fmt:message key="button.del"/>"  data-val='{"id":"${accessclient.id}","acname":"${accessclient.acname}"}'href="javascript:;"><i class='fa fa-trash-o'></i></a></span> 
								
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
			    	submiturl("${ctxPath}/accessclient/editinit",$(this).attr("data-val"));
			    });
			  	//删除
			    $(".tablemain").on("click",".tabledel",function(){
			    	submiturl("${ctxPath}/accessclient/delinit",$(this).attr("data-val"));
			    });
				//接入者初始化
			    $(".query-text").on("change","#qcid",function(){
			    	getacid($(this).val());
			    	$("#qid").find("option[value='']").prop("selected",true);
			    });
			    function getacid(value){
			    	$("#qid").find("option").not("[value='']").each(function(){
			    		if(value != $(this).data("qcid")){
			    			$(this).hide();
			    		}else{
			    			$(this).show();
			    		}
			    	});
			    }
			    getacid($("#qcid").val());
			});
			
		</script>
</body>
</html>
