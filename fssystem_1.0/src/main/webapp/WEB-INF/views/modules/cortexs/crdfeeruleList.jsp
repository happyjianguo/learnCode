<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>扣款费率管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() {
				var regYear = /^([1-9]|10)$/;
				var regRate = /^([1-9]\d{0,1}|100)$/;
				
				var beginyear = $("#beginyear").val();
				var rate = $("#rate").val();
				if(Boolean(beginyear)){
					if(!regYear.exec(beginyear)){
						alert("收取年限请输入1~10之间的数字！");
						return false;
					}
				}
				if(Boolean(rate)){
					if(!regRate.exec(rate)){
						alert("收取费率请输入1~100之间的数字！");
						return false;
					}
				}
				
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/crdFeeRule/list");				
				$("#form").submit();
			});
			
			$('#btnAdd').click(function() {
				$("#form").attr("action","${pageContext.request.contextPath}/cortexs/crdFeeRule/form");				
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
		<li class="active"><a href="${pageContext.request.contextPath}/cortexs/crdFeeRule/">扣款费率管理列表</a></li>
	</ul>
	
<div class="poup_right">

	<div class="shmc_tab2">
	<form id="form" action="${pageContext.request.contextPath}/cortexs/crdFeeRule/" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align: right;">卡BIN：</td>
				<td style="text-align: left;">
					<input type="text" name="iid" id="iid" value="${crdfeerule.iid }" maxlength="9" class="inputext_style"/>
				</td>
				<td style="text-align: right;">卡产品：</td>
				<td style="text-align: left;">
					<input type="text" name="crdproduct" id="crdproduct" value="${crdfeerule.crdproduct }" maxlength="4" class="inputext_style"/>
				</td>
				<td style="text-align: right;">收取年限：</td>
				<td style="text-align: left;">
					<input type="text" name="beginyear" id="beginyear" value="${crdfeerule.beginyear }" maxlength="2" class="inputext_style"/>
				</td>
				<td style="text-align: right;">收取费率(单位：%)：</td>
				<td style="text-align: left;">
					<input type="text" name="rate" id="rate" value="${crdfeerule.rate }" maxlength="4" class="inputext_style"/>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">是否收费：</td>
				<td style="text-align: left;">
					<select id = "feeflag" name="feeflag" class="inputext_style">
						<option value="">--请选择--</option>
						<option value="0" <c:if test="${crdfeerule.feeflag == '0' }">selected="selected"</c:if>>否</option>
						<option value="1" <c:if test="${crdfeerule.feeflag == '1' }">selected="selected"</c:if>>是</option>
					</select>
				</td>
				<td style="text-align: right;">是否为实名卡：</td>
				<td style="text-align: left;">
					<select id = "truenameflag" name="truenameflag" class="inputext_style">
						<option value="">--请选择--</option>
						<option value="0" <c:if test="${crdfeerule.truenameflag == '0' }">selected="selected"</c:if>>非实名</option>
						<option value="1" <c:if test="${crdfeerule.truenameflag == '1' }">selected="selected"</c:if>>实名</option>
					</select>
				</td>
				<td style="text-align: right;" colspan="4">
					<input id="btnSubmit" class="button" type="button" value="查询"/>
					<shiro:hasPermission  name="cortexs:crdFeeRule:edit">
						<input id="btnAdd" type="button" value="添加" class="button" />
					</shiro:hasPermission>
					<input type="button" value="清空" class="button" onClick="ClearForm('form')"/>
				</td>
			</tr>
		</table>
	</form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">规则ID</th>
				<th style="text-align: center;">卡BIN</th>
				<th style="text-align: center;">卡产品</th>
				<th style="text-align: center;">卡描述</th>
				<th style="text-align: center;">收取年限</th>
				<th style="text-align: center;">收取费率(单位：%)</th>
				<th style="text-align: center;">是否收费</th>
				<th style="text-align: center;">是否为实名卡</th>
				<shiro:hasPermission  name="cortexs:crdFeeRule:edit">
				<th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<shiro:hasPermission name="cortexs:crdFeeRule:view">
			<c:forEach items="${page.list}" var="crdfeeRule">
				<tr>
					<td>
						${crdfeeRule.crdfeeruleId}
					</td>
					<td>
						${crdfeeRule.iid}
					</td>
					<td>
						${crdfeeRule.crdproduct}
					</td>
					<td>
						${crdfeeRule.descr}
					</td>
					<td>
						${crdfeeRule.beginyear}
					</td>
					<td>
						${crdfeeRule.rate}
					</td>
					<td>
						<c:if test="${crdfeeRule.feeflag == '0'}">否</c:if>
						<c:if test="${crdfeeRule.feeflag == '1'}">是</c:if>
						<c:if test="${fn:length(feeflag)==0}"></c:if>
					</td>
					<td>
						<c:if test="${crdfeeRule.truenameflag == '0'}">非实名
						</c:if>
						<c:if test="${crdfeeRule.truenameflag == '1'}">实名
						</c:if>
						<c:if test="${fn:length(truenameflag)==0}"></c:if>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/cortexs/crdFeeRule/show?crdfeeruleId=${crdfeeRule.crdfeeruleId}">修改</a>
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