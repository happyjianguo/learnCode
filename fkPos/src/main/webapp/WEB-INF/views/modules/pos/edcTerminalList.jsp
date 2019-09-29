<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>终端信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() { 				
				$("#searchForm").attr("action","${ctxBak}/pos/edcTerminal/list");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要导出终端信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctxBak}/pos/edcTerminal/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctxBak}/pos/edcTerminal/list");
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
					src : '${ctxBak}/pos/edcTerminal/form?random='+ Math.random()
				}
			});
		}
 		var toEdit = function(id,merchantId) {
			var laySum = $.layer({
				type : 2,
				title : false,
				zIndex : -1,
				shade : [ 0.2, '#000' ],
				closeBtn : [ 1, true ],
				area : [ '1000px', '450px' ],
				iframe : {
					src : '${ctxBak}/pos/edcTerminal/form?terminalId='+id
							+'&merchantId='+merchantId+'&random='+ Math.random()
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
					src : '${ctxBak}/pos/pPos/toShow?posSn='+id+'&random='+ Math.random()
				}
			});
		}   
		
 		function resetSelects(){
			$("#terminalStat").select().val('');
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
		 		$.jBox.confirm("确认将此终端停用吗?","系统提示",function(v,h,f){
					if(v=="ok"){
						var laySum = $.layer({
							type : 2,
							title : false,
							zIndex : -1,
							shade : [ 0.2, '#000' ],
							closeBtn : [ 1, true ],
							area : [ '1000px', '450px' ],
							iframe : {
								src : '${ctxBak}/pos/edcTerminal/disable?terminalIds='+bb.value+'&random='+ Math.random()
							}
						});
					}
				},{buttonsFocus:1});
				$('.jbox-body .jbox-icon').css('top','55px');
		 	}else
		 		alert("请至少选中一个终端!");
		
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
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctxBak}/pos/edcTerminal/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:10px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctxBak}/pos/edcTerminal/import/template">下载模板</a>
			<h2>注：终端号、SN码为必填项，已挂终端的POS继续进行本操作会视为转挂，单元格前请加'</h2>
		</form>
	</div>
	<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" /> 
	<div class="sh_title">终端信息列表</div>
	<form:form id="searchForm" modelAttribute="edcTerminal" action="${ctxBak}/pos/edcTerminal/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<input type="hidden" name="selectMers" />
		
		<ul class="ul-form">
			<li><label>商户号：</label>
				<form:input path="merchantId" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>终端号：</label>
				<form:input path="terminalId" htmlEscape="false" maxlength="8" class="input-medium"/>
			</li>
			<li><label>终端状态：</label>
				<form:select path="terminalStat" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('TERMINAL_STAT')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>安装日期：</label>
				<form:input path="setDate" htmlEscape="false" maxlength="8" class="input-medium Wdate" 
					readonly="true" onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<li><label>安装地点：</label>
				<form:input path="setAddr" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li class="btns" style="padding-left: 490px;">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button"	value="导出" />
				<input id="btnImport" class="btn btn-primary" type="button"	value="关联POS" />
				<input id="btnClear"  class="btn btn-primary" type="button" value="清空" onClick="ClearForm('searchForm');resetSelects();"/>
			</li>
			<shiro:hasPermission name="pos:edcTerminal:edit">	
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
				<th>商户号</th>
				<th>终端号</th>
				<th>终端状态</th>
				<th>终端类型</th>
				<th>安装日期</th>
				<th>SN码</th>
				<th>厂商名称</th>
				<th>型号</th>
				<th>设备类型</th>
				<th>设备状态</th>
				<th>是否支持扫描</th>
				<th>布放类型</th>
				<shiro:hasPermission name="pos:edcTerminal:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="edcTerminal">
			<tr>
				<c:if test="${edcTerminal.terminalStat == 'Y'}">								
					<td align="center">
						<input type="checkbox" name="selectMer" value="<c:out value='${edcTerminal.terminalId}'/>">
					</td>	
				</c:if>
				<c:if test="${edcTerminal.terminalStat == 'N'}">								
					<td align="center"></td>	
				</c:if>
				<td>
					${edcTerminal.merchantId}
				</td>
				<td>
					${edcTerminal.terminalId}
				</td>
				<td>
					${fns:getDictLabel(edcTerminal.terminalStat, 'TERMINAL_STAT', '')}
				</td>
				<td>
					${edcTerminal.edcType}
				</td>
				<td>
					${edcTerminal.setDate}
				</td>
				
				<td><a href="javascript:toShow('${edcTerminal.posSn}')">
					${edcTerminal.posSn}
				</a></td>
				<td>
					${edcTerminal.factoryName}
				</td>
				<td>
					${edcTerminal.modelId}
				</td>
				<td>
					${fns:getDictLabel(edcTerminal.posType, 'POS_TYPE', '')}
				</td>
				<td>
					${fns:getDictLabel(edcTerminal.posStatus, 'POS_STATUS', '')}
				</td>
				<td>
					${fns:getDictLabel(edcTerminal.posScanFlag, 'POS_SCAN_FLAG', '')}
				</td>
				<td>
					${fns:getDictLabel(edcTerminal.layOutFlag, 'LAY_OUT_FLAG', '')}
				</td>				
				
				<shiro:hasPermission name="pos:edcTerminal:edit">
					<td>
						<a href="javascript:toEdit('${edcTerminal.terminalId}','${edcTerminal.merchantId}')">修改</a>	
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>