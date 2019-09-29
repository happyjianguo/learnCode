<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易流水管理</title>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#exportExcel').click(function() {
				$("#searchForm").attr("action","${pageContext.request.contextPath}/cortexs/tlog/exportExcel");
				$("#searchForm").submit();
				$("#searchForm").attr("action","${pageContext.request.contextPath}/cortexs/tlog/list");				
			});
			$('#query').click(function() {
				var addDT_startdate = $("#beginDatelocal").val();
				var addDT_enddate =$("#endDatelocal").val();
				if(addDT_startdate==''){
				    alert("开始日期不能为空");
				    window.document.forms['searchForm'].elements['beginDatelocal'].focus() ;
				    return false;
				}
				if(addDT_enddate==''){
				    alert("结束日期不能为空");
				    window.document.forms['searchForm'].elements['endDatelocal'].focus() ;
				    return false;
				}				
				if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
				    alert("结束日期不能小于开始日期");
				    window.document.forms['searchForm'].elements['beginDatelocal'].focus() ;
				    return false;
				}			
				$("#searchForm").attr("action","${pageContext.request.contextPath}/cortexs/tlog/list");				
				$("#searchForm").submit();
			});				
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		//data转换
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
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/tlog/">交易流水列表</a></li>
		<shiro:hasPermission name="cortexs:tlog:edit"><li><a href="${pageContext.request.contextPath}/cortexs/tlog/form">交易流水添加</a></li></shiro:hasPermission>
	</ul>
<div class="poup_right">
	
		
	<div class="shmc_tab2">
	<form id="searchForm" action="${pageContext.request.contextPath}/cortexs/tlog/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">主机流水号：</td>
				<td style="text-align: left;">
					<input type="text"  id="rrn" name="rrn" value="${tlog.rrn}" maxlength="12" class="inputext_style"/>
				</td>
				<td style="text-align: right;">卡号：</td>
				<td style="text-align: left;">
					<input type="text"  id="pan" name="pan" value="${tlog.pan }" maxlength="19" class="inputext_style"/>
				</td>
				<td style="text-align: right;">商户号：</td>
				<td style="text-align: left;">
					<input type="text"  id="crdacptid" name="crdacptid" value="${tlog.crdacptid}" maxlength="15" class="inputext_style"/>
				</td>
				<td style="text-align: right;">终端号：</td>
				<td style="text-align: left;">
					<input type="text"  id="termcode" name="termcode" value="${tlog.termcode }" maxlength="16" class="inputext_style"/>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">交易日期：</td>
				<td style="text-align: left;" colspan="3">
					<input name="beginDatelocal" id="beginDatelocal" type="text" readonly="readonly" maxlength="20" class="inputext_style Wdate"
						value="${tlog.beginDatelocal}"
						onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/> -- 
					<input name="endDatelocal" id="endDatelocal" type="text" readonly="readonly" maxlength="20" class="inputext_style Wdate"
						value="${tlog.endDatelocal}"
						onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
				</td>
				<td style="text-align: right;">交易类型：</td>
				<td style="text-align: left;" >
					<select id="txnType" name="txnType" class="inputext_style" value="${tlog.txnType}">
						<option value="" >--请选择--</option>
						<c:forEach var="entity" items="${tranTypeList}">
							<option value="${entity.id }" <c:if test="${tlog.txnType eq entity.id}">selected="selected"</c:if>>${entity.tranTypeDesc }</option>
						</c:forEach>
						
					<select>
				</td>
			
				<td style="text-align: right;">交易状态：</td>
				<td style="text-align: left;" >
					<select id="txnstatus" name="txnstatus" class="inputext_style" value="${tlog.txnstatus}">
						<option value="" >--请选择--</option>
						<c:forEach var="entity" items="${fns:getDictList('TXN_STATUS')}">
							<option value="${entity.value }" <c:if test="${tlog.txnstatus eq entity.value}">selected="selected"</c:if>>${entity.label }</option>
						</c:forEach>
						
					<select>
				
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">交易来源：</td>
				<td style="text-align: left;" >
					<select id="txnsrc" name="txnsrc" class="inputext_style" value="${tlog.txnsrc}">
						<option value="" >--请选择--</option>
						<c:forEach var="entity" items="${fns:getDictList('TXN_SRC')}">
							<option value="${entity.value }" <c:if test="${tlog.txnsrc eq entity.value}">selected="selected"</c:if>>${entity.label }</option>
						</c:forEach>
					<select>
				</td>
				<td style="text-align: right;">交易金额：</td>
				<td style="text-align: left;" >
					<input type="text" id="amttxn" name="amttxn" value="${tlog.amttxn }" class="inputext_style"/>
					
				</td>
				
				<td style="text-align: right;"></td>
				<td style="text-align: left;" >
				</td>
				<td style="text-align: right;" colspan="2">
					<input type="submit" value="查询" class="button" id="query"/>
					<input type="button" value="导出" class="button" id="exportExcel" />
					<input type="button" value="清空" class="button" onClick="ClearForm('searchForm')"/>
				</td>
			</tr>
		</ul>
	</form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>卡号</th>
				<th>商户号</th>
				<th>终端号</th>
				<th>交易日期</th>
				<th>交易时间</th>
				<th>交易来源</th>
				<th>交易状态</th>
				<th>交易金额</th>
				<th>交易手续费（POS）</th>
				<th>交易类型</th>
				<th>卡产品</th>
				<th>面值</th>
				<th>订单号</th>
				<th>购卡时间</th>
				<th>购卡点</th>
				<th>购卡单位</th>					
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tlog">
			<tr>
				<td>
					${tlog.pan}
				</td>
				<td>
					${tlog.crdacptid}
				</td>
				<td>
					${tlog.termcode}
				</td>
				<td>
					<fmt:formatDate value="${tlog.datelocal}" pattern="yyyyMMdd"/>
				</td>
				<td>
					${tlog.timelocal}
				</td>
				<td>
					${fns:getDictLabel(tlog.txnsrc, 'TXN_SRC', '')}
				</td>
				<td>
					${fns:getDictLabel(tlog.txnstatus, 'TXN_STATUS', '')}
				</td>
				<td>
					<fmt:formatNumber value="${tlog.amttxn}"  pattern="#,###,###,###,###,##0.00"  type="number"/>
				</td>
				<td>
					<fmt:formatNumber value="${tlog.amttax}"  pattern="#,###,###,###,###,##0.00"  type="number"/>
				</td>
				<td>
					${tlog.txnTypeDesc}
				</td>
				<td>
					${tlog.crdproduct}
				</td>
				<td>
					${tlog.amtEachCrd}
				</td>
				<td>
					${tlog.fatherOrder}
				</td>
				<td>
					${tlog.time}
				</td>
				<td>
					${tlog.salesPoint}
				</td>
				<td>
					${tlog.payerName}
				</td>
			</tr>
		</c:forEach>
		<c:if test="${not empty tlogSum}">
			<thead>
			<tr>
				<td colspan="7" align="center">
					合计
				</td>
				<td>
					<fmt:formatNumber value="${tlogSum.amttxn}"  pattern="#,###,###,###,###,##0.00"  type="number"/>
				</td>
				<td>
					<fmt:formatNumber value="${tlogSum.amttax}"  pattern="#,###,###,###,###,##0.00"  type="number"/>
				</td>
				<td>					
				</td>
				<td>					
				</td>
			</tr>
			</thead>	
		</c:if>	
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</div>
</div>
</body>
</html>