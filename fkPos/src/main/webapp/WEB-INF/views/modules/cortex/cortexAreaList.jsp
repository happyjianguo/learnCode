<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>省市区信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() { 				
				$("#searchForm").attr("action","${pageContext.request.contextPath}/cortex/cortexArea/list");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要导出省市区信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${pageContext.request.contextPath}/cortex/cortexArea/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${pageContext.request.contextPath}/cortex/cortexArea/list");
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
					src : '${pageContext.request.contextPath}/cortex/cortexArea/form?random='+ Math.random()
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
					src : '${pageContext.request.contextPath}/cortex/cortexArea/form?id='+id+'&random='+ Math.random()
				}
			});
		}         
	</script>
</head>
<body>
	<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" /> 
	<div class="sh_title">省市区信息列表</div>
	<form:form id="searchForm" modelAttribute="cortexArea" action="${pageContext.request.contextPath}/cortex/cortexArea/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>ID：</label>
				<form:input path="id" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>名称：</label>
				<form:input path="provinceCity" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>英文名：</label>
				<form:input path="enprovinceCity" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>上一级ID：</label>
				<form:input path="fid" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>级别：</label>
				<form:input path="depth" htmlEscape="false" maxlength="3" class="input-medium"/>
			</li>
			<li><label>是否有效：</label>
				<form:input path="isuse" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li class="btns" style="padding-left: 332px;">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button"	value="导出" />
				<input id="btnClear"  class="btn btn-primary" type="button" value="清空" onClick="ClearForm('searchForm')"/>
			</li>	
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>名称</th>
				<th>英文名</th>
				<th>上一级ID</th>
				<th>层级关系</th>
				<th>级别</th>
				<th>排序</th>
				<th>是否有效</th>
				<shiro:hasPermission name="cortex:cortexArea:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cortexArea">
			<tr>
				<td><a href="javascript:toEdit('${cortexArea.id}')">
					${cortexArea.id}
				</a></td>
				<td>
					${cortexArea.provinceCity}
				</td>
				<td>
					${cortexArea.enprovinceCity}
				</td>
				<td>
					${cortexArea.fid}
				</td>
				<td>
					${cortexArea.parentpath}
				</td>
				<td>
					${cortexArea.depth}
				</td>
				<td>
					${cortexArea.orderid}
				</td>
				<td>
					${cortexArea.isuse}
				</td>
				<shiro:hasPermission name="cortex:cortexArea:edit">
					<td>
						<a href="javascript:toEdit('${cortexArea.id}')">修改</a>	
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>