<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>卡类型状态管理</title>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/crdStatus/list");				
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
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/crdStatus/">卡状态设置列表</a></li>
<%-- 		<shiro:hasPermission name="cortexs:tTranType:edit"><li><a href="${pageContext.request.contextPath}/cortexs/crdStatus/form">交易类型设置添加</a></li></shiro:hasPermission>
 --%>	</ul>
	
<div class="poup_right">
	
		
	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/cortexs/crdStatus/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">卡号：</td>
				<td style="text-align: left;">
					<input type="text" name="pan" id="pan"value="${CrdStatus.pan }" maxlength="200" class="inputext_style"/>
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
				<th>卡状态</th>
				<th>卡状态描述</th>
			<!-- 	<th>交易码</th>
				<th>子处理码</th>
				<th>金额符号</th>
				<th>是否需要显示</th> -->
				<shiro:hasPermission name="cortexs:tTranType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="crdStatus">
			<tr>
				<td><a href="${pageContext.request.contextPath}/cortexs/crdStatus/form?id=${crdStatus.pan}">
					${crdStatus.pan}
				</a></td>
				<td>
					${crdStatus.statCode}
				</td>
				<td>
					${crdStatus.descr}
				</td>				
				<%-- <td>
					${tTranType.txncode}
				</td>
				<td>
					${tTranType.subTxncode}
				</td>
				<td>
					${fns:getDictLabel(tTranType.amtFlag, 'AMT_FLAG', '')}
				</td>
				<td>
					${fns:getDictLabel(tTranType.showFlag, 'SHOW_FLAG', '')}
				</td> --%>
				<shiro:hasPermission name="cortexs:tTranType:edit"><td>
    				<a href="${pageContext.request.contextPath}/cortexs/crdStatus/form?id=${crdStatus.pan}">变更卡状态</a>
<%-- 					<a href="/cortexs/crdStatus/delete?id=${crdStatus.pan}" onclick="return confirmx('确认要删除该交易类型设置吗？', this.href)">删除</a>
 --%>				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
</div>
	
</body>
</html>