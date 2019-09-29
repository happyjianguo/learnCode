<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$('#btnSubmit').click(function() { 				
				$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/merchantBase/list");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要导出商户基本信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctxBak}/pos/merchantBase/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctxBak}/pos/merchantBase/list");
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
					src : '${pageContext.request.contextPath}/pos/merchantBase/form?random='+ Math.random()
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
					src : '${pageContext.request.contextPath}/pos/merchantBase/form?merchantId='+id+'&random='+ Math.random()
				}
			});
		}
 		
 		function toDisable(selectMers,selectMer){
		 	var aa = document.getElementsByName(selectMer);
		 	var bb = document.all[selectMers];
		
		 	bb.value = "";
		 	flag = false;
		 	for (var i=0; i<aa.length; i++){
		 		if(aa[i].checked){
		 			flag = true;
		 			
		 			if(bb.value==""){
		 				bb.value = aa[i].value;
		 			}else
		 				bb.value = bb.value + "|" + aa[i].value;
		 		}
		 	}
		 	if(flag){
		 		$.jBox.confirm("确认将此商户停用吗?","系统提示",function(v,h,f){
					if(v=="ok"){
						var laySum = $.layer({
							type : 2,
							title : false,
							zIndex : -1,
							shade : [ 0.2, '#000' ],
							closeBtn : [ 1, true ],
							area : [ '1000px', '450px' ],
							iframe : {
								src : '${pageContext.request.contextPath}/pos/merchantBase/disable?merchantIds='+bb.value+'&random='+ Math.random()
							}
						});
					}
				},{buttonsFocus:1});
				$('.jbox-body .jbox-icon').css('top','55px');
		 	}else
		 		alert("请至少选中一个商户!");
		
 		}
 		
 		function selectAll(itself, selectMer){
 			var checkBoxs = document.getElementsByName(selectMer);
 			
 			for (var i = 0; i < checkBoxs.length; i++){
 				checkBoxs[i].checked = itself.checked;
 			}
 		}
	</script>
</head>
<body>
	<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" /> 
	<div class="sh_title">商户信息列表</div>
	<form:form id="searchForm" modelAttribute="merchantBase" action="${pageContext.request.contextPath}/pos/merchantBase/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<input type="hidden" name="selectMers" />
		
		<ul class="ul-form">
			<li><label>商户编号：</label>
				<form:input path="merchantId" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>商户类型：</label>
				<form:input path="mcc" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>商户名称：</label>
				<form:input path="merchantCname" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>城市：</label>
				<form:input path="cityCname" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>商户状态：</label>
				<form:select path="merchantStat" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('MERCHANT_STAT')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>清算模式：</label>
				<form:select path="settleMode" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('MRCH_SETTLE_MODE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>合同期限：</label>
				<form:select path="contractflag" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:option value="0" label="未到期"/>
					<form:option value="1" label="已到期"/>
					<form:option value="2" label="将续约"/>
				</form:select>
			</li>
			<li class="btns" style="padding-left: 80px;" >
				<input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button"	value="导出" />
				<input id="btnClear"  class="btn btn-primary" type="button" value="清空" onClick="ClearForm('searchForm')"/>
			</li>
			<shiro:hasPermission name="pos:merchantBase:edit">	
				<li class="btns">
					<input type="button" value="停用" class="button" onClick="toDisable('selectMers','selectMer')"/>
				</li>
			</shiro:hasPermission>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="20" align="center">
					<input type="checkbox" name="selectAll" onclick="selectAll(this, 'selectMer')" />
					停用
				</th>
				<th>商户编号</th>
				<th>商户类型</th>
				<th>商户名称</th>
				<th>城市</th>
				<th>商户状态</th>
				<th>清算模式</th>
				<shiro:hasPermission name="pos:merchantBase:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="merchantBase">
			<tr>
				<c:if test="${merchantBase.merchantStat == 'Y'}">								
					<td align="center">
						<input type="checkbox" name="selectMer" value="<c:out value='${merchantBase.merchantId}'/>">
					</td>	
				</c:if>
				<c:if test="${merchantBase.merchantStat == 'N'}">								
					<td align="center"></td>	
				</c:if>
				<td>
					${merchantBase.merchantId}
				</td>
				<td>
					${merchantBase.mcc}
				</td>
				<td>
					${merchantBase.merchantCname}
				</td>
				<td>
					${merchantBase.cityCname}
				</td>
				<td>
					${fns:getDictLabel(merchantBase.merchantStat, 'MERCHANT_STAT', '')}
				</td>
				<td>
					${fns:getDictLabel(merchantBase.settleMode, 'MRCH_SETTLE_MODE', '')}
				</td>
				<shiro:hasPermission name="pos:merchantBase:edit">
					<td>
						<a href="javascript:toEdit('${merchantBase.merchantId}')">修改</a>	
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>