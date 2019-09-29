<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>扣款明细预收查询</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				var year = $("#year").val();
				var month = $("#month").val();
				if(!Boolean(year)){
					if(Boolean(month)){
						alert("月份存在时，年份不可为空！");
						return false;
					}
				}

				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/crdfeelogBefore/list");				
				$("#form").submit();
			});
			
			$('#exportExcel').click(function() {
				var year = $("#year").val();
				var month = $("#month").val();
				if(!Boolean(year)){
					if(Boolean(month)){
						alert("月份存在时，年份不可为空！");
						return false;
					}
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/crdfeelogBefore/exportExcel");
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
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/crdfeelogBefore/">新福卡扣款明细预收列表</a></li>
	</ul>
<div class="poup_right">
	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/cortexs/crdfeelog/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">卡号：</td>
				<td style="text-align: left;">
					<input type="text" name="pan" id="pan" value="${crdfeelog.pan }" maxlength="16" class="inputext_style"/>
				</td>
				<td style="text-align: right;">年份：</td>
				<td style="text-align: left;">
					<input type="text" class="inputext_style"
						value="${crdfeelog.year}" readonly="readonly"
						onFocus="WdatePicker({dateFmt:'yyyy',isShowClear:true,readOnly:true})"
						name="year" id="year" />
				</td>
				<td style="text-align: right;">月份：</td>
				<td style="text-align: left;">
					<input type="text" class="inputext_style"
						value="${crdfeelog.month}" readonly="readonly"
						onFocus="WdatePicker({dateFmt:'MM',isShowClear:true,readOnly:true})"
						name="month" id="month" />
				</td>
				<td style="text-align: right;">交易状态：</td>
				<td style="text-align: left;">
					<select id = "txnstatus" name="txnstatus" class="inputext_style">
						<option value="" <c:if test="${crdfeelog.txnstatus eq null }">selected="selected"</c:if>>--请选择--</option>
						<option value="7" <c:if test="${crdfeelog.txnstatus eq 7 }">selected="selected"</c:if>>成功</option>
						<option value="0" <c:if test="${crdfeelog.txnstatus eq 0 }">selected="selected"</c:if>>失败</option>
					</select>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;"></td>
				<td style="text-align: left;"></td>
				<td style="text-align: right;"></td>
				<td style="text-align: left;"></td>
				<td style="text-align: right;" colspan="4">
					<input id="btnSubmit" class="button" type="button" value="查询"/>
					<input type="button" value="导出" class="button" id="exportExcel" />
					<input type="button" value="清空" class="button" onClick="ClearForm('form')"/>
				</td>
			</tr>
		</table>
	</form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">卡号</th>
				<th style="text-align: center;">扣款月份</th>
				<th style="text-align: center;">扣款商户号</th>
				<th style="text-align: center;">扣款终端号</th>
				<th style="text-align: center;">扣款余额（元）</th>
				<th style="text-align: center;">收取金额（元）</th>
				<th style="text-align: center;">费率（%）</th>
				<th style="text-align: center;">未有交易年数</th>
				<th style="text-align: center;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="crdfeelog" varStatus="index">
				<tr>
					<td>
						${((page.pageNo-1) * page.pageSize) + index.count }
					</td>
					<td>
						${crdfeelog.pan}
					</td>
					<td>
						${crdfeelog.localDate}
					</td>
					<td>
						${crdfeelog.merchantno}
					</td>
					<td>
						${crdfeelog.termcode}
					</td>
					<td>
						<fmt:formatNumber value="${crdfeelog.avlbal}" pattern="#,###,###,###,###,##0.00"
							minFractionDigits="2"></fmt:formatNumber>
					</td>
					<td>
						<fmt:formatNumber value="${crdfeelog.fee}" pattern="#,###,###,###,###,##0.00"
							minFractionDigits="2"></fmt:formatNumber>
					</td>
					<td>
						${crdfeelog.rate}
					</td>
					<td>
						${crdfeelog.noTranYear}
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/cortexs/crdfeelogBefore/show?crdFeeLogId=${crdfeelog.crdFeeLogId}">详情</a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="10" align="center">
					<font color="red">总条数：${count}&emsp;&emsp;收取金额汇总(元)：${feeSum}</font>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
</div>
	
</body>
</html>