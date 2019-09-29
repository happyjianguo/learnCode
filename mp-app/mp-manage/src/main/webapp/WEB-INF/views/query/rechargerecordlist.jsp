<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<title>业务查询-充值记录查询</title>
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<i class="icon-cog"></i>
				<li>业务查询</li>
				<li class="active" id="bill_check">充值记录查询</li>
			</ol>
		</div>
		<div class="mainbox">
			<form:form cssClass="form-horizontal" action="${ctxPath}/rechargerecordq/init" methodParam="post" modelAttribute="cloudrechargerecordModel">
				<div class="query">
				 	<div class="query-tit">查询条件</div>
				 	<div class="query_1">
				 		<div class="query-text">
							<span>用户名</span>
							<div class="col-sm-3">
								<form:input path="userid" cssClass="form-control"/>
							</div>
						</div>
						<div class="query-text">
							<span>机构名称</span>
							<div class="col-sm-3">
								<select id="orgid" name="orgid" class="form-control select" >
									<option value="">-请选择-</option> 
									<c:forEach items="${cloudrechargerecordModel.cloudpforgList}" var="showList">
										<option value="${showList.id}" ${cloudrechargerecordModel.orgid eq showList.id ? 'selected="selected"':''}>${showList.orgname}</option>
									</c:forEach>
								</select>
							</div>
						</div>
				 		<div class="query-btn">
							<button type="submit" class="btn btn-primary" id="queryBtn">查询</button>
							<a href="${ctxPath}/rechargerecordq/init" class="btn btn-primary">重置</a>
						</div>
				 	</div>
				</div>
			</form:form>
			<div class="tablebox">
				 <div class="table-tit">
					<div class="tit-left" >
						充值记录列表
					</div>
				</div> 
				<table id="table_id" class="tablemain">
				  <thead>
					  <tr class="tit-top">
					    <th scope="col">充值记录ID</th>
					    <th scope="col">用户名</th>
					    <th scope="col">机构ID</th>
					    <th scope="col">机构名称</th>
					    <th scope="col">平台币数量</th>	
					    <th scope="col">充值状态</th>	
					    <th scope="col">支付方式</th>	
					  </tr>
				  </thead> 
				  <tbody id="tbody">
				  	<c:forEach items="${cloudrechargerecordModel.cloudrechargerecordList}" var="cloudrecharg">
					  		<tr>
					  			<td>${cloudrecharg.orderid}</td>
					  			<td>${cloudrecharg.userid}</td>
					  			<td>${cloudrecharg.orgid}</td>
					  			<td>${cloudrecharg.orgname}</td>
					  			<td>${cloudrecharg.currentmoney}</td>
					  			<td>
					  				<c:choose>
					  					<c:when test="${cloudrecharg.status eq '0'}">失败</c:when>
					  					<c:when test="${cloudrecharg.status eq '1'}">成功</c:when>
					  					<c:when test="${cloudrecharg.status eq '000000'}">处理中</c:when>
					  				</c:choose>
					  			</td>
					  			<td>
					  				<c:choose>
					  					<c:when test="${cloudrecharg.paytype eq 'op'}">线下</c:when>
					  					<c:when test="${cloudrecharg.paytype eq 'yl'}">银联</c:when>
					  				</c:choose>
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
			});
		</script>
</body>
</html>