<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>MCC管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() { 				
				$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/mccParam/list");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要导出MCC吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","/pos/mccParam/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","/pos/mccParam/list");
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
					src : '/pos/mccParam/form?random='+ Math.random()
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
					src : '/pos/mccParam/form?mcc='+id+'&random='+ Math.random()
				}
			});
		}         
	</script>
</head>
<body>
	<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" /> 
	<div class="sh_title">MCC列表</div>
	<form:form id="searchForm" modelAttribute="mccParam" action="${pageContext.request.contextPath}/pos/mccParam/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>mcc：</label>
				<form:input path="mcc" htmlEscape="false" maxlength="4" class="input-medium"/>
			</li>
			<li><label>MCC名称：</label>
				<form:input path="mccName" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label style="width:90px;">MCC英文名：</label>
				<form:input path="mccEname" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:input path="mccType" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li class="btns" style="padding-left: 858px;">
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
				<th>mcc</th>
				<th>MCC名称</th>
				<th>MCC英文名</th>
				<th>类型</th>
				<shiro:hasPermission name="pos:mccParam:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mccParam">
			<tr>
				<td><a href="javascript:toEdit('${mccParam.mcc}')">
					${mccParam.mcc}
				</a></td>
				<td>
					${mccParam.mccName}
				</td>
				<td>
					${mccParam.mccEname}
				</td>
				<td>
					${mccParam.mccType}
				</td>
				<shiro:hasPermission name="pos:mccParam:edit">
					<td>
						<a href="javascript:toEdit('${mccParam.mcc}')">修改</a>	
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>