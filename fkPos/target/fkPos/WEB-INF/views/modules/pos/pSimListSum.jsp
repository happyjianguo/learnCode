<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>SIM卡统计信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() { 				
				$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/pSim/listSum");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要导出SIM卡信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","/pos/pSim/exportSum");
						$("#searchForm").submit();
						$("#searchForm").attr("action","/pos/pSim/listSum");
					}
				},{buttonsFocus:1});
				$('.jbox-body .jbox-icon').css('top','55px');
			});		
		});
	</script>
</head>
<body>
	<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" /> 
	<div class="sh_title">SIM卡统计信息</div>
	<form:form id="searchForm" modelAttribute="PSim" action="${pageContext.request.contextPath}/pos/pSim/listSum" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>运营商：</label>
				<form:select path="simCommunication" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SIM_COMMUNICATION')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button"	value="导出" />
			</li>	
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>运营商</th>
				<th>空闲数量</th>
				<th>在用数量</th>
				<th>故障数量</th>
				<th>报废数量</th>
				<th>总数量</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${listSum}" var="PSim">
			<tr>
				<td>
					<!-- ${fns:getDictLabel(PSim.simCommunication, 'SIM_COMMUNICATION', '')}  -->
					<c:if test="${PSim.simCommunication=='CHINA_MOBILE'}">移动</c:if>
					<c:if test="${PSim.simCommunication=='CHINA_UNICOM'}">联通</c:if>
					<c:if test="${PSim.simCommunication=='CHINA_TELECOM'}">电信</c:if>
					<c:if test="${PSim.simCommunication=='合计:'}">合计:</c:if>
				</td>
				<td>
					${PSim.idleNum}
				</td>
				<td>
					${PSim.useNum}
				</td>
				<td>
					${PSim.malfuncNum}
				</td>
				<td>
					${PSim.scrapNum}
				</td>
				<td>
					${PSim.totleNum}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>