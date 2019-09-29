<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>POS机信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() { 				
				$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/pPos/list");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要导出POS机信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctxBak}/pos/pPos/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctxBak}/pos/pPos/list");
					}
				},{buttonsFocus:1});
				$('.jbox-body .jbox-icon').css('top','55px');
			});	
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			$("#btnImportSim").click(function(){
				$.jBox($("#importBoxSim").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
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
					src : '/pos/pPos/form?random='+ Math.random()
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
					src : '/pos/pPos/toUpdate?posSn='+id+'&random='+ Math.random()
				}
			});
		}   
		//出库、报修、报废
 		var toAction = function(id,flag) {
			var laySum = $.layer({
				type : 2,
				title : false,
				zIndex : -1,
				shade : [ 0.2, '#000' ],
				closeBtn : [ 1, true ],
				area : [ '1000px', '450px' ],
				iframe : {
					src : '/pos/pPos/toAction?posSn='+id+'&actFlag='+flag+'&random='+ Math.random()
				}
			});
		}
		
 		var toShow = function(id) {
			var laySum = $.layer({
				type : 2,
				title : false,
				zIndex : -1,
				shade : [ 0.2, '#000' ],
				closeBtn : [ 1, true ],
				area : [ '1000px', '450px' ],
				iframe : {
					src : '/pos/pPos/toShow?posSn='+id+'&random='+ Math.random()
				}
			});
		}   
		   
		var getPModelList = function() {
			$.ajax({
				url : '${pageContext.request.contextPath}/pos/pPos/getPModelList',
				type : "post",
				data : {
					factoryId : $("#factoryId").val()
				},
				dataType : "json",
				success : function(data) {
					//$('#modelId').attr('value',''); 		
					$("#modelId").empty();
					$("#modelId").append("<option value=''>--请选择--</option>");	
					$("#modelId").select().val('');
					if (data.length != 0) {
						$.each(data, function(i, info) {
							$("#modelId").append(
								"<option title='"+info.modelId+"' value='"+info.modelId+"'>" + info.modelId + "</option>");
						});
					}
				}
			})
		};
		   
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${pageContext.request.contextPath}/pos/pPos/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:10px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${pageContext.request.contextPath}/pos/pPos/import/template">下载模板</a>
			<h2>SN、厂商、型号、设备类型、是否支持扫码、布放类型为必填项，单元格前请加'</h2>
		</form>
	</div>
	
	<div id="importBoxSim" class="hide">
		<form id="importSimForm" action="${pageContext.request.contextPath}/pos/pPos/importSim" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:10px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSimSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${pageContext.request.contextPath}/pos/pPos/importSim/templateSim">下载模板</a>
			<h2>运营商、手机号或SIM卡串号、SN码为必填项。</h2>
		</form>
	</div>
	
	<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" /> 
	<div class="sh_title">POS机信息列表</div>
	<form:form id="searchForm" modelAttribute="PPos" action="${pageContext.request.contextPath}/pos/pPos/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>SN码：</label>
				<form:input path="posSn" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>厂商：</label>
				<form:select path="factoryId" class="input-medium" onchange="getPModelList();">
					<form:option value="" label="--请选择--"/>
					<form:options items="${pFactoryList}" itemLabel="factoryName" itemValue="id" htmlEscape="false"/>
				</form:select>					
			</li>
			<li><label>型号：</label>
				<form:select path="modelId" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${pModelList}" itemLabel="modelId" itemValue="modelId" htmlEscape="false"/>
				</form:select>					
			</li>
			<li><label>设备类型：</label>
				<form:select path="posType" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('POS_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>终端号：</label>
				<form:input path="terminalId" htmlEscape="false" maxlength="8" class="input-medium"/>
			</li>
			<li><label>设备状态：</label>
				<form:select path="posStatus" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('POS_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否可扫描：</label>
				<form:select path="posScanFlag" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('POS_SCAN_FLAG')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>布放类型：</label>
				<form:select path="layOutFlag" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('LAY_OUT_FLAG')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>采购日期：</label>
				<input name="purchaseDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${PPos.purchaseDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<li class="btns" style="padding-left: 352px;">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button"	value="导出" />
				<input id="btnImport" class="btn btn-primary" type="button"	value="导入" />	
				<input id="btnImportSim" class="btn btn-primary" type="button"	value="关联SIM卡" />			
				<input id="btnClear"  class="btn btn-primary" type="button" value="清空" onClick="ClearForm('searchForm')"/>
			</li>
		
			<shiro:hasPermission name="pos:pPos:edit">	
				<li class="btns">
					<input type="button" value="添加" class="button" onClick='toAdd()'/>
				</li>	
			</shiro:hasPermission>		
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>SN码</th>
				<th>厂商名称</th>
				<th>型号</th>
				<th>设备类型</th>
				<th>设备状态</th>
				<th>是否支持扫描</th>
				<th>布放类型</th>
				<th>终端号</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pPos">
			<tr>
				<td><a href="javascript:toShow('${pPos.posSn}')">
					${pPos.posSn}
				</a></td>
				<td>
					${pPos.factoryName}
				</td>
				<td>
					${pPos.modelId}
				</td>
				<td>
					${fns:getDictLabel(pPos.posType, 'POS_TYPE', '')}
				</td>
				<td>
					${fns:getDictLabel(pPos.posStatus, 'POS_STATUS', '')}
				</td>
				<td>
					${fns:getDictLabel(pPos.posScanFlag, 'POS_SCAN_FLAG', '')}
				</td>
				<td>
					${fns:getDictLabel(pPos.layOutFlag, 'LAY_OUT_FLAG', '')}
				</td>
				<td>
					${pPos.terminalId}
				</td>
				<td>
				<shiro:hasPermission name="pos:pPos:edit">
					<c:if test="${pPos.posStatus!='SCRAP'}">
						<a href="javascript:toEdit('${pPos.posSn}')">修改</a>	
					</c:if>
				</shiro:hasPermission>
				<!-- 出库:空闲的POS可以出库 -->
				<shiro:hasPermission name="pos:pPos:outStock ">
					<c:if test="${pPos.posStatus=='IDLE'}">
						<a href="javascript:toAction('${pPos.posSn}','outStock')">出库</a>	
					</c:if>
				</shiro:hasPermission>	
				<!-- 故障:空闲和出库的POS可以报修 -->							
				<shiro:hasPermission name="pos:pPos:malfunc">
					<c:if test="${pPos.posStatus=='IDLE' or pPos.posStatus=='USE'}">
						<a href="javascript:toAction('${pPos.posSn}','malfunc')">报修</a>	
					</c:if>
				</shiro:hasPermission>
				<!-- 故障修好:故障的POS可以修好 -->							
				<shiro:hasPermission name="pos:pPos:malfunc">
					<c:if test="${pPos.posStatus=='MALFUNC'}">
						<a href="${pageContext.request.contextPath}/pos/pPos/toMalfuncFine?posSn=${pPos.posSn}">修好</a>						
					</c:if>
				</shiro:hasPermission>				
				<!-- 报废:没有报废的可以报废 -->							
				<shiro:hasPermission name="pos:pPos:scrap">
					<c:if test="${pPos.posStatus!='SCRAP'}">
						<a href="javascript:toAction('${pPos.posSn}','scrap')">报废</a>	
					</c:if>
				</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>