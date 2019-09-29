<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收单地址管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() { 				
				$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/areaCodeInfo/list");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要导出收单地址吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/areaCodeInfo/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/areaCodeInfo/list");
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
					src : '/pos/areaCodeInfo/form?random='+ Math.random()
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
					src : '/pos/areaCodeInfo/form?areaCode='+id+'&random='+ Math.random()
				}
			});
		}         
	</script>
</head>
<body>
	<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" /> 
	<div class="sh_title">收单地址列表</div>
	<form:form id="searchForm" modelAttribute="areaCodeInfo" action="${pageContext.request.contextPath}/pos/areaCodeInfo/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>地区编码：</label>
				<form:input path="areaCode" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li><label>中文名称：</label>
				<form:input path="areaName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>父地区编码：</label>
				<form:input path="parentAreaCode" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li class="btns">
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
				<th>地区编码</th>
				<th>中文名称</th>
				<th>父地区编码</th>
				<shiro:hasPermission name="pos:areaCodeInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="areaCodeInfo">
			<tr>
				<td><a href="javascript:toEdit('${areaCodeInfo.areaCode}')">
					${areaCodeInfo.areaCode}
				</a></td>
				<td>
					${areaCodeInfo.areaName}
				</td>
				<td>
					${areaCodeInfo.parentAreaCode}
				</td>
				<shiro:hasPermission name="pos:areaCodeInfo:edit">
					<td>
						<a href="javascript:toEdit('${areaCodeInfo.areaCode}')">修改</a>	
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>