<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>账户余额变更设置管理</title>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/cAccount/list");				
				$("#form").submit();
			});
			$("#form").validate({
				onsubmit : true,// 是否在提交是验证
				onfocusout : false,// 是否在获取焦点时验证
				onkeyup : false,// 是否在敲击键盘时验证
				rules : {
					pan : {required : true, number : true,
						minlength:16,  //设置最小长度  
          			  	maxlength:16,}
				},
				messages: {
					pan: {required: "请填写卡号.",number:"请输入16位数字的卡号",minlength:"请输入16位数字的卡号"}

				}
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#form").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/cAccount/">账户余额变更设置列表</a></li>
	</ul>
	
<div class="poup_right">
	
		
	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/cortexs/cAccount/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">卡号：</td>
				<td style="text-align: left;">
					<input type="text" name="pan" id="pan"value="${CAccount.pan }"  maxlength="16" class="inputext_style"/>
				</td>
				
				<td>
					<input id="btnSubmit" class="button" type="submit" value="查询"/>
				</td>
			</tr>
		</table>
	</form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>卡号</th>
				<th>账户号</th>
				<th>账户类型</th>
				<th>币种</th>
				<th>当前账户余额</th>
				<shiro:hasPermission name="cortexs:cAccount:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cAccount">
			<tr>
				<td><a href="${pageContext.request.contextPath}/cortexs/cAccount/form?id=${cAccount.pan}">
					${cAccount.pan}
				</a></td>
				<td>
					${cAccount.accno}
				</td>				
				<td>
					${cAccount.typecode}
				</td>
				<td>
					${cAccount.currcode}
				</td>
				<td>
					<fmt:formatNumber value="${cAccount.avlbal}"  pattern="#,###,###,###,###,##0.00"  type="number"/>
				</td>
				<shiro:hasPermission name="cortexs:cAccount:edit"><td>
    				<a href="${pageContext.request.contextPath}/cortexs/cAccount/form?id=${cAccount.pan}">调账</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
</div>
	
</body>
</html>