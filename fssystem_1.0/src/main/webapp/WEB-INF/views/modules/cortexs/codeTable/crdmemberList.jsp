<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目信息码表</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/Crdmember/list");				
				$("#form").submit();
			});
			
			$('#btnAdd').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/Crdmember/form");				
				$("#form").submit();
			});	
		});
		
		function toDelete(memberId){
			$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath}/cortexs/Crdmember/delete",
			   data: {memberId:memberId},
			   success: function(dataSource){
					if(dataSource.result == '0'){
						alert(dataSource.desc);
						$("#form").attr("action","${pageContext.request.contextPath}/cortexs/Crdmember/list");				
						$("#form").submit();
					}else{
						alert(dataSource.desc);
					}					   	   
			   }
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/Crdmember/">项目信息码表</a></li>
	</ul>
	
<div class="poup_right">

	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/cortexs/Crdmember/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">项目名称：</td>
				<td style="text-align: left;">
					<input type="text" name="memberName" id="memberName" value="${crdmember.memberName }" maxlength="100" class="inputext_style"/>
				</td>
				<td style="text-align: right;">卡BIN：</td>
				<td style="text-align: left;">
					<input type="text" name="cardBin" id="cardBin" value="${crdmember.cardBin }" maxlength="9" class="inputext_style"/>
				</td>
				<td style="text-align: right;" colspan="4">
					<input id="btnSubmit" class="button" type="button" value="查询"/>
					<shiro:hasPermission  name="cortexs:crdmember:edit">
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
				<th style="text-align: center;">项目名称</th>
				<th style="text-align: center;">卡BIN</th>
				<th style="text-align: center;">卡产品</th>
				<th style="text-align: center;">卡描述</th>
				<th style="text-align: center;">录入时间</th>
				<th style="text-align: center;">修改时间</th>
				<shiro:hasPermission  name="cortexs:crdmember:edit">
				<th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<shiro:hasPermission name="cortexs:crdmember:view">
			<c:forEach items="${page.list}" var="crdmember" varStatus="index">
				<tr>
					<td>
						${((page.pageNo-1) * page.pageSize) + index.count }
					</td>
					<td>
						${crdmember.memberName}
					</td>
					<td>
						${crdmember.cardBin}
					</td>
					<td>
						${crdmember.crdproduct}
					</td>
					<td>
						${crdmember.descr}
					</td>
					<td>
						${crdmember.createTime}
					</td>
					<td>
						${crdmember.updateTime}
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/cortexs/Crdmember/show?memberId=${crdmember.memberId}">修改</a>
						<a href="javascript:toDelete('${crdmember.memberId}');">删除</a>
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