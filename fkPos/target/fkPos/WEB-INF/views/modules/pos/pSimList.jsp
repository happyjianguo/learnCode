<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>SIM卡信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() { 				
				$("#searchForm").attr("action","${ctxBak}/pos/pSim/list");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要导出SIM卡信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctxBak}/pos/pSim/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctxBak}/pos/pSim/list");
					}
				},{buttonsFocus:1});
				$('.jbox-body .jbox-icon').css('top','55px');
			});		
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
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
					src : '${ctxBak}/pos/pSim/form?random='+ Math.random()
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
					src : '${ctxBak}/pos/pSim/toUpdate?id='+id+'&random='+ Math.random()
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
					src : '${ctxBak}/pos/pSim/toAction?id='+id+'&actFlag='+flag+'&random='+ Math.random()
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
					src : '${ctxBak}/pos/pSim/toShow?id='+id+'&random='+ Math.random()
				}
			});
		}  
		     
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctxBak}/pos/pSim/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:10px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctxBak}/pos/pSim/import/template">下载模板</a>
			<h2>运营商为必填项，手机号或SIM卡串号至少填一个 ，单元格前请加'</h2>
		</form>
	</div>
	<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" /> 
	<div class="sh_title">SIM卡信息列表</div>
	<form:form id="searchForm" modelAttribute="PSim" action="${ctxBak}/pos/pSim/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>运营商：</label>
				<form:select path="simCommunication" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SIM_COMMUNICATION')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>手机号：</label>
				<form:input path="phoneNumber" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>SIM卡串号：</label>
				<form:input path="serialNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>SIM卡状态：</label>
				<form:select path="simStatus" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SIM_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>SN码：</label>
				<form:input path="posSn" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>采购日期：</label>
				<input name="purchaseDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${PSim.purchaseDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<li><label>采购者：</label>
				<form:input path="purchaseBy" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns" style="padding-left: 750px;">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button"	value="导出" />
				<input id="btnImport" class="btn btn-primary" type="button"	value="导入" />
				<input id="btnClear"  class="btn btn-primary" type="button" value="清空" onClick="ClearForm('searchForm')"/>
				<shiro:hasPermission name="pos:pSim:edit">	
					<input type="button" value="添加" class="button" onClick='toAdd()'/>
				</shiro:hasPermission>
			</li>
		
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>运营商</th>
				<th>手机号</th>
				<th>SIM卡串号</th>
				<th>SIM卡状态</th>
				<th>SN码</th>
				<th>采购日期</th>
				<th>采购者</th>
				<th>入库时间</th>
				<th>入库者</th>
				<shiro:hasPermission name="pos:pSim:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pSim">
			<tr>
				<td><a href="javascript:toShow('${pSim.id}')">
					${fns:getDictLabel(pSim.simCommunication, 'SIM_COMMUNICATION', '')}
				</a></td>
				<td>
					${pSim.phoneNumber}
				</td>
				<td>
					${pSim.serialNumber}
				</td>
				<td>
					${fns:getDictLabel(pSim.simStatus, 'SIM_STATUS', '')}
				</td>
				<td>
					${pSim.posSn}
				</td>
				<td>
					${pSim.purchaseDate}
				</td>
				<td>
					${pSim.purchaseBy}
				</td>
				<td>
					${pSim.instockDate}
				</td>
				<td>
					${pSim.instockBy}
				</td>
				<td>
				<!-- 
				<shiro:hasPermission name="pos:pSim:edit">
					<c:if test="${pSim.simStatus!='SCRAP'}">
						<a href="javascript:toEdit('${pSim.id}')">修改</a>	
					</c:if>
				</shiro:hasPermission>
				 -->
				<!-- 出库:空闲的SIM卡可以出库 -->
				<shiro:hasPermission name="pos:pSim:outStock">
					<c:if test="${pSim.simStatus=='IDLE'}">
						<a href="javascript:toAction('${pSim.id}','outStock')">出库</a>	
					</c:if>
				</shiro:hasPermission>	
				<!-- 故障:空闲和出库的SIM卡可以报修 -->							
				<shiro:hasPermission name="pos:pSim:malfunc">
					<c:if test="${pSim.simStatus=='IDLE' or pSim.simStatus=='USE'}">
						<a href="javascript:toAction('${pSim.id}','malfunc')">报修</a>	
					</c:if>
				</shiro:hasPermission>
				<!-- 故障修好:故障的SIM卡可以修好 -->							
				<shiro:hasPermission name="pos:pSim:malfunc">
					<c:if test="${pSim.simStatus=='MALFUNC'}">
						<a href="${ctxBak}/pos/pSim/toMalfuncFine?id=${pSim.id}">修好</a>						
					</c:if>
				</shiro:hasPermission>				
				<!-- 报废-->							
				<shiro:hasPermission name="pos:pSim:scrap">
					<c:if test="${pSim.simStatus!='SCRAP'}">
						<a href="javascript:toAction('${pSim.id}','scrap')">报废</a>	
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