<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<title>业务查询-订单流水查询</title>
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<i class="icon-cog"></i>
				<li>业务查询</li>
				<li class="active" id="bill_check">订单流水查询</li>
			</ol>
		</div>
		<div class="mainbox">
			<form:form cssClass="form-horizontal" action="${ctxPath}/winningrecordq/init" methodParam="post" modelAttribute="cloudwinningrecordModel">
				<div class="query">
				 	<div class="query-tit">查询条件</div>
				 	<div class="query_1">
				 		<div class="query-text">
							<span>OPENID</span>
							<div class="col-sm-3">
								<form:input path="openid" cssClass="form-control"/>
							</div>
						</div>
						<div class="query-text">
							<span>手机号</span>
							<div class="col-sm-3">
								<form:input path="winnerphone" cssClass="form-control"/>
							</div>
						</div>
						<div class="query-text">
							<span>机构名称</span>
							<div class="col-sm-3">
								<select id="orgid" name="orgid" class="form-control select" >
									<option value="">-请选择-</option> 
									<c:forEach items="${cloudwinningrecordModel.cloudpforgList}" var="showList">
										<option value="${showList.id}" ${cloudwinningrecordModel.orgid eq showList.id ? 'selected="selected"':''}>${showList.orgname}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="query-text">
							<span>活动名称</span>
							<div class="col-sm-3">
								<select id="gameid" name="gameid" class="form-control select" >
									<option value="">-请选择-</option> 
									<c:forEach items="${cloudwinningrecordModel.gameCodeAndNameList}" var="gameList">
										<option value="${gameList.id}" ${cloudwinningrecordModel.gameid eq gameList.id ? 'selected="selected"':''}>${gameList.playname}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="query-text">
							<span>审核状态</span>
							<select id="preliminarystatus" name="preliminarystatus" class="form-control select" >
								<option value="">全部</option>
								<option value="0" ${'0' eq cloudwinningrecordModel.preliminarystatus ? 'selected="selected"':'' }>待审核</option>
								<option value="1" ${'1' eq cloudwinningrecordModel.preliminarystatus ? 'selected="selected"':'' }>审核通过</option>
								<option value="2" ${'2' eq cloudwinningrecordModel.preliminarystatus ? 'selected="selected"':'' }>审核未通过</option>
							</select>
						</div>
						
				 		<div class="query-btn">
							<button type="submit" class="btn btn-primary" id="queryBtn">查询</button>
							<a href="${ctxPath}/winningrecordq/init" class="btn btn-primary">重置</a>
						</div>
				 	</div>
				</div>
			</form:form>
			<div class="tablebox">
				 <div class="table-tit">
					<div class="tit-left" >
						订单流水列表
					</div>
				</div> 
				<table id="table_id" class="tablemain">
				  <thead>
					  <tr class="tit-top">
					    <th scope="col">中奖记录ID</th>
					    <th scope="col">用户OPENID</th>
<!-- 					    <th scope="col">机构ID</th> -->
					    <th scope="col">机构名称</th>
<!-- 					    <th scope="col">活动ID</th>	 -->
					    <th scope="col">活动名称</th>	
					    <th scope="col">中奖手机号</th>	
					    <th scope="col">发货状态</th>	
<!-- 					    <th scope="col">奖品ID</th>	 -->
					    <th scope="col">奖品名称</th>	
					    <th scope="col">奖品类型</th>	
<!-- 					    <th scope="col">创建时间</th>	 -->
					    <th scope="col">审核状态</th>	
					    <th scope="col">审核意见</th>	
					    <th scope="col">审核人</th>	
					    <th scope="col">更新时间</th>	
<!-- 					    <th scope="col">复审人</th>	 -->
					  </tr>
				  </thead> 
				  <tbody id="tbody">
				  	<c:forEach items="${cloudwinningrecordModel.cloudwinningrecordList}" var="cloudwinning">
					  		<tr>
					  			<td>${cloudwinning.id}</td>
					  			<td>${cloudwinning.openid}</td>
<%-- 					  			<td>${cloudwinning.orgid}</td> --%>
					  			<td>${cloudwinning.orgname}</td>
<%-- 					  			<td>${cloudwinning.gameid}</td> --%>
					  			<td>${cloudwinning.playname}</td>
					  			<td>${cloudwinning.winnerphone}</td>
					  			<td>
					  				<c:choose>
					  					<c:when test="${cloudwinning.sendstatus eq '0'}">未发送</c:when>
					  					<c:when test="${cloudwinning.sendstatus eq '1'}">充值成功</c:when>
					  					<c:when test="${cloudwinning.sendstatus eq '2'}">充值失败</c:when>
					  					<c:when test="${cloudwinning.sendstatus eq '3'}">无此订单</c:when>
					  					<c:when test="${cloudwinning.sendstatus eq '4'}">已受理</c:when>
					  					<c:when test="${cloudwinning.sendstatus eq '5'}">套餐不存在</c:when>
					  					<c:when test="${cloudwinning.sendstatus eq '6'}">游戏预算不足</c:when>
					  					<c:when test="${cloudwinning.sendstatus eq '7'}">已发送</c:when>
					  					<c:when test="${cloudwinning.sendstatus eq '8'}">无需发送</c:when>
					  				</c:choose>
					  			</td>
<%-- 					  			<td>${cloudwinning.prizestyle}</td> --%>
					  			<td>${cloudwinning.prizename}</td>
					  			<td>
					  			<c:choose>
					  					<c:when test="${cloudwinning.prizetype eq 'xn'}">话费流量</c:when>
					  					<c:when test="${cloudwinning.prizetype eq 'sw'}">实物</c:when>
					  				</c:choose>
					  			</td>
<%-- 					  			<td>${cloudwinning.createtime}</td> --%>
					  			<td>
					  				<c:choose>
					  					<c:when test="${cloudwinning.preliminarystatus eq '0'}">待审核</c:when>
					  					<c:when test="${cloudwinning.preliminarystatus eq '1'}">审核通过</c:when>
					  					<c:when test="${cloudwinning.preliminarystatus eq '2'}">审核未通过</c:when>
<%-- 					  					<c:when test="${cloudwinning.preliminarystatus eq '3'}">复核通过</c:when> --%>
<%-- 					  					<c:when test="${cloudwinning.preliminarystatus eq '4'}">复核未通过</c:when> --%>
					  				</c:choose>
					  			</td>
					  			<td>${cloudwinning.reviewdes}</td>
					  			<td>${cloudwinning.preliminaryper}</td>
					  			<td>${cloudwinning.updatetime}</td>
<%-- 					  			<td>${cloudwinning.reviewper}</td> --%>
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