<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<title>业务查询-账户余额查询</title>
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<i class="icon-cog"></i>
				<li>业务查询</li>
				<li class="active" id="bill_check">账户余额查询</li>
			</ol>
		</div>
		<div class="mainbox">
			<form:form cssClass="form-horizontal" action="${ctxPath}/accountbalance/init" methodParam="post" modelAttribute="accountSurplusModel">
				<div class="query">
				 	<div class="query-tit">查询条件</div>
				 	<div class="query_1">
						<div class="query-text">
							<span>机构名称</span>
							<div class="col-sm-3">
								<select id="orgid" name="orgid" class="form-control select" >
									<option value="">-请选择-</option> 
									<c:forEach items="${accountSurplusModel.cloudpforgList}" var="showList">
										<option value="${showList.id}" ${accountSurplusModel.orgid eq showList.id ? 'selected="selected"':''}>${showList.orgname}</option>
									</c:forEach>
								</select>
							</div>
						</div>
				 		<div class="query-btn">
							<button type="submit" class="btn btn-primary" id="queryBtn">查询</button>
							<a href="${ctxPath}/accountbalance/init" class="btn btn-primary">重置</a>
						</div>
				 	</div>
				</div>
			</form:form>
			<div class="tablebox">
				 <div class="table-tit">
					<div class="tit-left" >
						账户余额列表
					</div>
				</div> 
				<table id="table_id" class="tablemain">
				  <thead>
					  <tr class="tit-top">
					    <th scope="col">机构ID</th>
					    <th scope="col">机构名称</th>
					    <th scope="col">平台币余额</th>
					    <th scope="col">累计充值数量</th>
					    <th scope="col">创建时间</th>	
					    <th scope="col">更新时间</th>	
					  </tr>
				  </thead> 
				  <tbody id="tbody">
				  	<c:forEach items="${accountSurplusModel.accountBalanceList}" var="accountsurp">
					  		<tr>
					  			<td>${accountsurp.orgid}</td>
					  			<td>${accountsurp.orgname}</td>
					  			<td>${accountsurp.currentmoney}</td>
					  			<td>${accountsurp.totalmoney}</td>
					  			<td>${accountsurp.createtime}</td>
					  			<td>${accountsurp.updatetime}</td>
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
			});
		</script>
</body>
</html>