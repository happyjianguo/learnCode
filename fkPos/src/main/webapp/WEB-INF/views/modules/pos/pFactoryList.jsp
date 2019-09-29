<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>厂商信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() { 				
				$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/pFactory/list");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要导出厂商信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","/pos/pFactory/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","/pos/pFactory/list");
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
					src : '/pos/pFactory/form?random='+ Math.random()
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
					src : '/pos/pFactory/form?id='+id+'&random='+ Math.random()
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
					src : '/pos/pFactory/toShow?id='+id+'&random='+ Math.random()
				}
			});
		}   		
	</script>
</head>
<body>
	<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" /> 
	<div class="sh_title">厂商信息列表</div>
	<form:form id="searchForm" modelAttribute="PFactory" action="${pageContext.request.contextPath}/pos/pFactory/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>厂商名称：</label>
				<form:input path="factoryName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>公司状态：</label>
				<form:select path="factoryStatus" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('FACTORY_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>联系经理：</label>
				<form:input path="manager" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>经理手机：</label>
				<form:input path="managerMobile" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li class="btns" style="padding-left: 770px;">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button"	value="导出" />
				<input id="btnClear"  class="btn btn-primary" type="button" value="清空" onClick="ClearForm('searchForm')"/>
			</li>
		
			<shiro:hasPermission name="pos:pFactory:edit">	
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
				<th>厂商名称</th>
				<th>公司状态</th>
				<th>联系经理</th>
				<th>经理手机</th>
				<th>联系电话</th>
				<shiro:hasPermission name="pos:pFactory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pFactory">
			<tr>
				<td>
					<a href="javascript:toShow('${pFactory.id}')">
						${pFactory.factoryName}
					</a>
				</td>
				<td>
					${fns:getDictLabel(pFactory.factoryStatus, 'FACTORY_STATUS', '')}
				</td>
				<td>
					${pFactory.manager}
				</td>
				<td>
					${pFactory.managerMobile}
				</td>
				<td>
					${pFactory.managerTel}
				</td>
				<shiro:hasPermission name="pos:pFactory:edit">
					<td>
						<a href="javascript:toEdit('${pFactory.id}')">修改</a>	
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>