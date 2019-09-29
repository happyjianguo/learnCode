<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<title>充值管理-人工充值</title>
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<i class="icon-cog"></i>
				<li>充值管理</li>
				<li class="active" id="bill_check">人工充值</li>
			</ol>
		</div>
		<div class="mainbox">
			<form:form cssClass="form-horizontal" action="${ctxPath}/accountsurplusrev/init" methodParam="post" modelAttribute="cloudaccountsurplusrevModel">
				<div class="query">
				 	<div class="query-tit">查询条件</div>
				 	<div class="query_1">
						<div class="query-text">
							<span>机构名称</span>
							<div class="col-sm-3">
								<select id="orgid" name="orgid" class="form-control select" >
									<option value="">-请选择-</option> 
									   <c:forEach items="${cloudaccountsurplusrevModel.cloudpforgList}" var="showList">  
									   		<option value="${showList.id}" ${cloudaccountsurplusrevModel.orgid eq showList.id ? 'selected="selected"':''}>${showList.orgname}</option>
									   </c:forEach>
								</select>
							</div>
						</div>
						<div class="query-text">
							<span>状态</span>
							<select id="rstatus" name="rstatus" class="form-control select" >
								<option value="">全部</option>
								<option value="0" ${'0' eq cloudaccountsurplusrevModel.rstatus ? 'selected="selected"':''}>审核成功</option>
								<option value="1" ${'1' eq cloudaccountsurplusrevModel.rstatus ? 'selected="selected"':''}>审核失败</option>
								<option value="2" ${'2' eq cloudaccountsurplusrevModel.rstatus ? 'selected="selected"':''}>待审核</option>
							</select>
						</div>
				 		<div class="query-btn">
							<button type="submit" class="btn btn-primary" id="queryBtn">查询</button>
							<a href="${ctxPath}/accountsurplusrev/init" class="btn btn-primary">重置</a>
						</div>
				 	</div>
				</div>
			</form:form>
			<div class="tablebox">
				 <div class="table-tit">
					<div class="tit-left" >
						人工充值
					</div>
					<div class="tit-btn">
						<span><a href="${ctxPath}/accountsurplusrev/addinit" class="btn btn-primary"><i class="fa fa-plus"></i>充值</a></span>
					</div>
				</div> 
				<table id="table_id" class="tablemain">
				  <thead>
					  <tr class="tit-top">
					    <th scope="col">机构ID</th>
					    <th scope="col">机构名称</th>
					    <th scope="col">平台币</th>
					    <th scope="col">操作人</th>
					    <th scope="col">审核状态</th>
					    <th scope="col">审核人</th>
					    <th scope="col">审核时间</th>	
					    <th scope="col">创建时间</th>	
					    <th scope="col">审核意见</th>	
					  </tr>
				  </thead> 
				  <tbody id="tbody">
				  	<c:forEach items="${cloudaccountsurplusrevModel.accountsurpList}" var="accountsurp">
					  		<tr>
					  			<td>${accountsurp.orgid}</td>
					  			<td>${accountsurp.orgname}</td>
					  			<td>${accountsurp.amount}</td>
					  			<td>${accountsurp.operator}</td>
					  			<td>
					  				<c:choose>
					  					<c:when test="${accountsurp.rstatus eq '0'}">审核成功</c:when>
					  					<c:when test="${accountsurp.rstatus eq '1'}">审核失败</c:when>
					  					<c:when test="${accountsurp.rstatus eq '2'}">待审核</c:when>
					  				</c:choose>
					  			</td>
					  			<td>${accountsurp.auditor}</td>
					  			<td>${accountsurp.updatetime}</td>
					  			<td>${accountsurp.createtime}</td>
					  			<td>${accountsurp.audresult}</td>
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
				//审核
			    $(".tablemain").on("click",".tableedit",function(){
			    	submiturl("${ctxPath}/registerreview/reviewinit",$(this).attr("data-val"));
			    });
			    /*重置查询条件*/
				$(".query-btn").on("click", "#resetBtn", function() {
					$('#orgid').val("");
					$('#rstatus').val("");
					/* $('#orgid').append("<option value=''>请选择</option>"); */
				});
			});
		</script>
</body>
</html>