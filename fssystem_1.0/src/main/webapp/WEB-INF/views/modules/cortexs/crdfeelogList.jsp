<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>扣款明细查询</title>
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

				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/crdfeelog/list");				
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
				
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/crdfeelog/exportExcel");
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
		
		//退款
		function toRefund(){
		 	var aa = document.getElementsByName("selectMer");
		 	var bb = document.getElementById("refundIDs");
		 	bb.value = "";
		 	flag = false;
		 	for (var i = 0; i < aa.length; i++){
		 		if(aa[i].checked){
		 			flag = true;
		 			if(bb.value == ""){
		 				bb.value = aa[i].value;
		 			}else {
		 				bb.value = bb.value + "|" + aa[i].value;
		 			}	
		 		}
		 	}
		 	if(flag){
		 		window.location.href="${pageContext.request.contextPath}/cortexs/crdfeelog/toRefund?refundIDs=" + bb.value
				+ "&random=" + Math.random();
		 		/* $.jBox.confirm("确认进行退款吗?","系统提示",function(v,h,f){
		 			if(v=="ok"){
		 				
					}
				},{buttonsFocus:1});
				$('.jbox-body .jbox-icon').css('top','55px'); */
		 	}else
		 		alert("请至少选中一条数据!");
		
 		}
 		
 		function selectAll(selectAll){
 			if(selectAll.checked){
 				$("input:checkbox[name='selectMer']").prop("checked", "checked");
 			} else {
 				$("input:checkbox[name='selectMer']").prop("checked", "");
 			}
 		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/crdfeelog/">新福卡扣款明细列表</a></li>
	</ul>
<div class="poup_right">
	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/cortexs/crdfeelog/" method="post">
		<input type="hidden" id="refundIDs" name="refundIDs" value="" />
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
						<option value="">--请选择--</option>
						<option value="7" <c:if test="${crdfeelog.txnstatus eq 7 }">selected="selected"</c:if>>成功</option>
						<option value="0" <c:if test="${crdfeelog.txnstatus eq 0 }">selected="selected"</c:if>>失败</option>
					</select>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">退款状态：</td>
				<td style="text-align: left;">
					<select id="reserved1" name="reserved1" class="inputext_style">
						<option value="">--请选择--</option>										
						<c:forEach var="entity" items="${fns:getDictList('REFUND_STATE_X')}">
							<option value="${entity.value}" <c:if test="${crdfeelog.reserved1 eq entity.value}">selected="selected"</c:if>>${entity.label}</option>
						</c:forEach>
					</select>
				</td>
				<td style="text-align: right;">交易类型：</td>
				<td style="text-align: left;">
					<select id = "txncode" name="txncode" class="inputext_style">
						<option value="">--请选择--</option>
						<option value="0" <c:if test="${crdfeelog.txncode eq 0 }">selected="selected"</c:if>>消费</option>
						<option value="20" <c:if test="${crdfeelog.txncode eq 20 }">selected="selected"</c:if>>退款</option>
					</select>
				</td>
				<td style="text-align: right;" colspan="4">
					<input id="btnSubmit" class="button" type="button" value="查询"/>
					<input type="button" value="导出" class="button" id="exportExcel" />
					<input type="button" value="清空" class="button" onClick="ClearForm('form')"/>
					<input type="button" value="退款" class="button" onClick="toRefund();"/>
				</td>
			</tr>
		</table>
	</form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">
					<input type="checkbox" id="selectAll" name="selectAll" onclick="selectAll(this);" />全选
				</th>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">卡号</th>
				<th style="text-align: center;">扣款月份</th>
				<th style="text-align: center;">扣款商户号</th>
				<th style="text-align: center;">扣款终端号</th>
				<th style="text-align: center;">原账户余额（元）</th>
				<th style="text-align: center;">交易金额（元）</th>
				<th style="text-align: center;">费率（%）</th>
				<th style="text-align: center;">未有交易年数</th>
				<th style="text-align: center;">交易类型</th>
				<th style="text-align: center;">退款状态</th>
				<th style="text-align: center;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="crdfeelog" varStatus="index">
				<tr>
					<c:choose>
						<c:when test="${crdfeelog.reserved1 ne '1' and crdfeelog.txncode == 0}">
							<td align="center">
								<input type="checkbox" name="selectMer" value="<c:out value='${crdfeelog.crdFeeLogId}'/>">
							</td>
						</c:when>
						<c:otherwise><td align="center"></td></c:otherwise>
					</c:choose>
					<td>${((page.pageNo-1) * page.pageSize) + index.count }</td>
					<td>${crdfeelog.pan}</td>
					<td>${crdfeelog.localDate}</td>
					<td>${crdfeelog.merchantno}</td>
					<td>${crdfeelog.termcode}</td>
					<td>
						<fmt:formatNumber value="${crdfeelog.avlbal}" pattern="#,###,###,###,###,##0.00"
							minFractionDigits="2"></fmt:formatNumber>
					</td>
					<td>
						<fmt:formatNumber value="${crdfeelog.fee}" pattern="#,###,###,###,###,##0.00"
							minFractionDigits="2"></fmt:formatNumber>
					</td>
					<td>${crdfeelog.rate}</td>
					<td>${crdfeelog.noTranYear}</td>
					<td>
						<c:if test="${crdfeelog.txncode eq 0}">消费</c:if>
						<c:if test="${crdfeelog.txncode eq 20}">退款</c:if>
					</td>
					<td>
						${fns:getDictLabel(crdfeelog.reserved1, 'REFUND_STATE_X', '')}
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/cortexs/crdfeelog/show?crdFeeLogId=${crdfeelog.crdFeeLogId}">详情</a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="16" align="center">
					<font color="red">
						总条数：<fmt:formatNumber value="${count}" pattern="#,###,###,###,###,###"></fmt:formatNumber>&emsp;&emsp;
						消费金额汇总(元)：<fmt:formatNumber value="${consumeAmt}" pattern="#,###,###,###,###,##0.00" minFractionDigits="2"></fmt:formatNumber>&emsp;&emsp;
						扣款金额汇总(元)：<fmt:formatNumber value="${consumeSum}" pattern="#,###,###,###,###,##0.00" minFractionDigits="2"></fmt:formatNumber>&emsp;&emsp;
						退款金额汇总(元)：<fmt:formatNumber value="${refundSum}" pattern="#,###,###,###,###,##0.00" minFractionDigits="2"></fmt:formatNumber>
					</font>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
	
</div>
	
</body>
</html>