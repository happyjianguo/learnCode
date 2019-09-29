<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>POS机操作记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				var beginDate = $("#beginCreateDate").val();
				var endDate = $("#endCreateDate").val();	
				if(beginDate!='' && endDate!='' && (getDate(beginDate)-getDate(endDate)>0)){
					alert("创建结束时间不能小于创建开始时间");
					return false;
				}
				$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/pPosActLog/list");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要导出POS机操作记录吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","/pos/pPosActLog/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","/pos/pPosActLog/list");
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
					src : '/pos/pPosActLog/form?random='+ Math.random()
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
					src : '/pos/pPosActLog/form?id='+id+'&random='+ Math.random()
				}
			});
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
	<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" /> 
	<div class="sh_title">POS机操作记录列表</div>
	<form:form id="searchForm" modelAttribute="PPosActLog" action="${pageContext.request.contextPath}/pos/pPosActLog/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>SN码：</label>
				<form:input path="posSn" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>终端号：</label>
				<form:input path="terminalId" htmlEscape="false" maxlength="8" class="input-medium"/>
			</li>
			<li><label>操作类型：</label>
				<form:select path="posActType" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('POS_ACT_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>操作原因：</label>
				<form:input path="posActMsg" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>创建者：</label>
				<form:input path="createBy.name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" id="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${PPosActLog.beginCreateDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/> - 
				<input name="endCreateDate" id="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${PPosActLog.endCreateDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<li class="btns" style="padding-left: 140px;">
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
				<th>SN码</th>
				<th>终端号</th>
				<th>操作类型</th>
				<th>操作原因</th>
				<th>创建者</th>
				<th>创建时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pPosActLog">
			<tr>
				<td><a href="javascript:toEdit('${pPosActLog.id}')">
					${pPosActLog.posSn}
				</a></td>
				<td>
					${pPosActLog.terminalId}
				</td>
				<td>
					${fns:getDictLabel(pPosActLog.posActType, 'POS_ACT_TYPE', '')}
				</td>
				<td>
					${pPosActLog.posActMsg}
				</td>
				<td>
					${pPosActLog.createBy.name}
				</td>
				<td>
					<fmt:formatDate value="${pPosActLog.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>