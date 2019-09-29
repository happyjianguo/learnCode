<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户结算开票设置管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				var startStlDate = $("#beginCreateTime").val();
				var endStlDate = $("#endCreateTime").val();

				if(startStlDate!='' && endStlDate!='' && (getDate(startStlDate)-getDate(endStlDate)>0)){
				    alert("截止时间不能小于起始时间");
				    return false;
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/mSettleBillRecord/list");				
				$("#form").submit();
			});		
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#form").submit();
        	return false;
        }
		
		function getDate(date){
			 var dates = date.split("-");
			 var dateReturn = '';
			 
			 for(var i=0; i<dates.length; i++){
			  dateReturn+=dates[i];
			 }
			 return dateReturn;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/mSettleBillRecord/">商户结算开票修改记录列表</a></li>
	</ul>
	
<div class="poup_right">
	
		
	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/cortexs/mSettleBillRecord/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">商户编号：</td>
				<td style="text-align: left;">
					<input type="text" name="mrchno" id="mrchno"value="${mSettleBillRecord.mrchno }" maxlength="15" class="inputext_style"/>
				</td>
				<td style="text-align: right;">修改起始日期：</td>
				<td style="text-align: left;">
					<input type="text" class="inputext_style"
						value="${mSettleBillRecord.beginCreateTime}" readonly="readonly"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,readOnly:true})"
						name="beginCreateTime" id="beginCreateTime" />
				</td>
				<td style="text-align: right;">修改截止日期：</td>
				<td style="text-align: left;">
					<input type="text" class="inputext_style"
						value="${mSettleBillRecord.endCreateTime}" readonly="readonly"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,readOnly:true})"
						name="endCreateTime" id="endCreateTime" />
				</td>
				<td>
					<input id="btnSubmit" class="button" type="button" value="查询"/>
				</td>
			</tr>
		</table>
	</form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">商户编号</th>
				<th style="text-align: center;">修改模块</th>
				<th style="text-align: center;">修改人</th>
				<th style="text-align: center;">修改时间</th>
				<th style="text-align: center;">IP地址</th>
				<th style="text-align: center;">MAC</th>
				<shiro:hasPermission  name="cortexs:record:view">
				<th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<shiro:hasPermission name="cortexs:record:view">
			<c:forEach items="${page.list}" var="mSettleBillRecord">
				<tr>
					<td>
						${mSettleBillRecord.mrchno}
					</td>
					<td>
						${mSettleBillRecord.modifyModule}
					</td>
					<td>
						${mSettleBillRecord.createBy.name}
					</td>
					<td>
						${mSettleBillRecord.createTime}
					</td>
					<td>
						${mSettleBillRecord.remoteAddr}
					</td>
					<td>
						${mSettleBillRecord.remoteMac}
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/cortexs/mSettleBillRecord/show?id=${mSettleBillRecord.createTime}">详情</a>
					</td>
				</tr>
			</c:forEach>
		</shiro:hasPermission>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
</div>
	
</body>
</html>