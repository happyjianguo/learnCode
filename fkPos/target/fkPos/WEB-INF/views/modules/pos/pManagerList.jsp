<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户经理信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() { 				
				$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/pManager/list");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要导出商户经理信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","/pos/pManager/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","/pos/pManager/list");
					}
				},{buttonsFocus:1});
				$('.jbox-body .jbox-icon').css('top','55px');
			});			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
   		var toAdd = function() {
			var laySum = $.layer({
				type : 2,
				title : false,
				zIndex : -1,
				shade : [ 0.2, '#000' ],
				closeBtn : [ 1, true ],
				area : [ '1000px', '450px' ],
				iframe : {
					src : '${ctxBak}/pos/pManager/form?random='+ Math.random()
				}
			});
		}
 		var toEdit = function(id) {
			var laySum = $.layer({
				type : 2,
				title : false,
				zIndex : -1,
				shade : [ 0.2, '#000' ],
				closeBtn : [ 1, true ],
				area : [ '1000px', '450px' ],
				iframe : {
					src : '${ctxBak}/pos/pManager/form?id='+id+'&random='+ Math.random()
				}
			});
		}    
 		var toShow = function(id) {
			var laySum = $.layer({
				type : 2,
				title : false,
				zIndex : -1,
				shade : [ 0.2, '#000' ],
				closeBtn : [ 1, true ],
				area : [ '1000px', '450px' ],
				iframe : {
					src : '${ctxBak}/pos/pManager/toShow?id='+id+'&random='+ Math.random()
				}
			});
		} 
		     
	</script>
</head>
<body>
	<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" /> 
	<div class="sh_title">商户经理信息列表</div>
	<form:form id="searchForm" modelAttribute="PManager" action="${pageContext.request.contextPath}/pos/pManager/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>经理名称：</label>
				<form:input path="managerName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="managerStatus" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('MANAGER_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>区域：</label>
				<form:select path="managerArea" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('MERCHANT_AREA')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>角色：</label>
				<form:select path="managerRole" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('MANAGER_ROLE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>联系电话：</label>
				<form:input path="managerTel" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns" style="padding-left: 513px;">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button"	value="导出" />
				<input id="btnClear"  class="btn btn-primary" type="button" value="清空" onClick="ClearForm('searchForm')"/>
			</li>
		
			<shiro:hasPermission name="pos:pManager:edit">	
				<li class="btns">
					<input type="button" value="添加" class="button" onClick='toAdd()'/>
				</li>	
			</shiro:hasPermission>		
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户经理名称</th>
				<th>状态</th>
				<th>区域</th>
				<th>角色</th>
				<th>上级</th>
				<th>联系电话</th>
				<th>邮箱</th>
				<shiro:hasPermission name="pos:pManager:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pManager">
			<tr>
				<td>
					<a href="javascript:toShow('${pManager.id}')">
						${pManager.managerName}
					</a>
				</td>
				<td>
					${fns:getDictLabel(pManager.managerStatus, 'MANAGER_STATUS', '')}
				</td>
				<td>
					${fns:getDictLabel(pManager.managerArea, 'MERCHANT_AREA', '')}
				</td>
				<td>
					${fns:getDictLabel(pManager.managerRole, 'MANAGER_ROLE', '')}
				</td>
				<td>
					${pManager.fatherId}
				</td>
				<td>
					${pManager.managerTel}
				</td>
				<td>
					${pManager.managerEmail}
				</td>
				<shiro:hasPermission name="pos:pManager:edit">
					<td>
						<a href="javascript:toEdit('${pManager.id}')">修改</a>	
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>