<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>口令信息码表</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/Crdopen/list");				
				$("#form").submit();
			});
			
			$('#btnAdd').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/Crdopen/form");				
				$("#form").submit();
			});	
		});
		
		function toDelete(openId){
			$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath}/cortexs/Crdopen/delete",
			   data: {openId:openId},
			   success: function(dataSource){
					if(dataSource.result == '0'){
						alert(dataSource.desc);
						$("#form").attr("action","${pageContext.request.contextPath}/cortexs/Crdopen/list");				
						$("#form").submit();
					}else{
						alert(dataSource.desc);
					}					   	   
			   }
			});
		}
		
		/* function getOrder(){
			var operator = $("#operator").val();
			$("#indentNumber").html("<option value=''>--请选择--</option>");
			if(!Boolean(trim(operator))){
				return false;
			}
			$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath}/cortexs/Crdopen/getOrder",
			   data: {operator:operator},
			   success: function(orderList){
				   if(orderList == null){
					   $("#indentNumber").html("<option value=''>--请选择--</option>");
					   return;
				   }
			   	   for(var i = 0; i < orderList.length; i++){
			   		   var indentNumber = orderList[i];
			   		   if(!Boolean(indentNumber)){
			   		   } else {
			   				$("#indentNumber").append(
				   	   			"<option value=" + indentNumber + ">"+
				   	   			indentNumber + "</option>"
				   	   	    );
			   		   }
			   		   
			   	   }				   	   
			   }
			});
		} */
		
		//删除左右两端的空格
		function trim(str){ 
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/Crdopen/">口令信息码表</a></li>
	</ul>
	
<div class="poup_right">

	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/cortexs/Crdopen/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">操作员：</td>
				<td style="text-align: left;">
					<select id="operator" name="operator" class="inputext_style">
						<option value="">--请选择--</option>										
						<c:forEach var="model" items="${operatorList}">
							<option value="${model}" <c:if test="${crdopen.operator eq model}">selected="selected"</c:if>>
								${fn:split(model, ",")[1]}
							</option>
						</c:forEach>
					</select>	
				</td>
				<%-- <td style="text-align: right;">订单号：</td>
				<td style="text-align: left;">
					<select id="indentNumber" name="indentNumber" class="inputext_style" >
						<option value="">--请选择--</option>
						<c:forEach var="model" items="${orderList}">
							<option value="${model}" <c:if test="${crdopen.indentNumber eq model}">selected="selected"</c:if>>
								${model}
							</option>
						</c:forEach>										
					</select>	
				</td> --%>
				<td style="text-align: right;" colspan="4">
					<input id="btnSubmit" class="button" type="button" value="查询"/>
					<shiro:hasPermission  name="cortexs:crdopen:edit">
						<input id="btnAdd" type="button" value="添加" class="button" />
					</shiro:hasPermission>
					<input type="button" value="清空" class="button" onClick="ClearForm('form')"/>
				</td>
			</tr>
		</table>
	</form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">操作员</th>
				<%-- <th style="text-align: center;">订单号</th> --%>
				<th style="text-align: center;">录入时间</th>
				<%-- <th style="text-align: center;">修改时间</th> --%>
				<shiro:hasPermission  name="cortexs:crdopen:edit">
				<th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<shiro:hasPermission name="cortexs:crdopen:view">
			<c:forEach items="${page.list}" var="crdopen" varStatus="index">
				<tr>
					<td>
						${((page.pageNo-1) * page.pageSize) + index.count }
					</td>
					<td>
						${fn:split(crdopen.operator, ",")[1]}
					</td>
					<%-- <td>
						${crdopen.indentNumber}
					</td> --%>
					<td>
						${crdopen.createTime}
					</td>
					<%-- <td>
						${crdopen.updateTime}
					</td> --%>
					<td>
						<%-- <a href="${pageContext.request.contextPath}/cortexs/Crdopen/show?openId=${crdopen.openId}">修改</a> --%>
						<a href="javascript:toDelete('${crdopen.openId}');">删除</a>
					</td>
				</tr>
			</c:forEach>
		</shiro:hasPermission>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
</div>
	
</body>
</html>