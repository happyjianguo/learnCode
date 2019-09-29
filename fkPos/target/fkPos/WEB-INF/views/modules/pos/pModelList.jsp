<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>型号信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() { 	
				$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/pModel/list");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要导出型号信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","/pos/pModel/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","/pos/pModel/list");
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
					src : '/pos/pModel/form?random='+ Math.random()
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
					src : '/pos/pModel/form?modelId='+id+'&random='+ Math.random()
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
					src : '/pos/pModel/toShow?modelId='+id+'&random='+ Math.random()
				}
			});
		} 
		      
	</script>
</head>
<body>
	<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" /> 
	<div class="sh_title">型号信息列表</div>
	<form:form id="searchForm" modelAttribute="PModel" action="${pageContext.request.contextPath}/pos/pModel/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>型号：</label>
				<form:input path="modelId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="modelStatus" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('MANAGER_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>			
			<li><label>厂商：</label>
				<form:select path="factoryId" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${pFactoryList}" itemLabel="factoryName" itemValue="id" htmlEscape="false"/>
				</form:select>					
			</li>
			<li class="btns" style="padding-left: 32px;">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button"	value="导出" />
				<input id="btnClear"  class="btn btn-primary" type="button" value="清空" onClick="ClearForm('searchForm')"/>
			</li>
		
			<shiro:hasPermission name="pos:pModel:edit">	
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
				<th>型号</th>
				<th>状态</th>				
				<th>厂商名称</th>
				<th>创建者</th>
				<th>创建时间</th>
				<shiro:hasPermission name="pos:pModel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pModel">
			<tr>
				<td><a href="javascript:toShow('${pModel.modelId}')">
					${pModel.modelId}
				</a></td>
				<td>
					${fns:getDictLabel(pModel.modelStatus, 'MANAGER_STATUS', '')}					
				</td>				
				<td>
					${pModel.factoryName}
				</td>
				<td>
					${pModel.createBy.name}
				</td>
				<td>
					<fmt:formatDate value="${pModel.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="pos:pModel:edit">
					<td>
						<a href="javascript:toEdit('${pModel.modelId}')">修改</a>	
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>