<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>账户余额变更历史记录管理</title>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/cAccountLog/list");				
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
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/cAccountLog/">账户余额变更历史记录列表</a></li>
	</ul>
	
<div class="poup_right">
	
		
	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/cortexs/cAccountLog/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">卡号：</td>
				<td style="text-align: left;">
					<input type="text" name="pan" id="pan"value="${CAccountLog.pan }"  maxlength="16" class="inputext_style"/>
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
				<th>交易类型</th>
				<th>卡号</th>
				<th>账户号</th>
				<th>调账金额符号</th>
				<th>调账金额</th>
				<th>调账前余额</th>
				<th>当前账户余额</th>
				<th>备注</th>
				<th>日期</th>
				<th>时间</th>
				<th>操作者</th>
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cAccountLog">
			<tr>
				<td>
					${cAccountLog.tranType}
				</td>
				<td>
					${cAccountLog.pan}
				</td>
				<td>
					${cAccountLog.accno}
				</td>
				<td>
					<c:if test="${cAccountLog.amtFlag eq '1'}">
					正值
					</c:if>
					<c:if test="${cAccountLog.amtFlag eq '-1'}">
					负值
					</c:if>	
				</td>	
				<td>
					${cAccountLog.transferAmt}
				</td>
				<td>
					${cAccountLog.beforeAvlbal}
				</td>
				<td>
					${cAccountLog.avlbal}
				</td>
				<td>
					${cAccountLog.comments}
				</td>
				<td>
					${cAccountLog.updateLogDate}
				</td>
				<td>
					${cAccountLog.updateLogTime}
				</td>			
				<td>
					${cAccountLog.updateOper}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
</div>
	
</body>
</html>