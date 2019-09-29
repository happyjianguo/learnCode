<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>终端补充信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function() { 				
				$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/edcTerminalX/list");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要导出终端补充信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctxBak}/pos/edcTerminalX/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctxBak}/pos/edcTerminalX/list");
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
					src : '${ctxBak}/pos/edcTerminalX/form?random='+ Math.random()
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
					src : '${ctxBak}/pos/edcTerminalX/form?terminalId='+id
					+'&merchantId='+merchantId+'&random='+ Math.random()
				}
			});
		}
 		
 		function toUpgrade(selectMers,selectMer){
 			var upgradeDate = $("#upgradeDateAjax").val();
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
		 		$.jBox.confirm("确认升级此商户终端的版本号吗?","系统提示",function(v,h,f){
					if(v=="ok"){
						if(!Boolean(upgradeDate)){
							$.jBox.prompt("请选择升级日期!","系统提示");
							return;
						}
						$.ajax({
						   type: "POST",
						   url: "${pageContext.request.contextPath}/pos/edcTerminalX/toUpgradeTerminal",
						   data: {terminalIds:bb.value,upgradeDate:upgradeDate},
						   success: function(resultMap){
							   if(resultMap.result == '0'){
								   $.jBox.prompt(resultMap.resultMsg,"系统提示");
								   window.location.href = "${pageContext.request.contextPath}/pos/edcTerminalX/list";
							   } else
							       $.jBox.prompt(resultMap.resultMsg,"系统提示");
						   }
						});	
					}
				},{buttonsFocus:1});
				$('.jbox-body .jbox-icon').css('top','55px');
		 	}else{
		 		$.jBox.prompt("请至少选中一个商户终端!","系统提示");
		 	}
		}
		
		function toReset(){
			if(confirm("确定要再次升级所有终端的版本号吗？")){
				$.jBox.confirm("确认重置所有的终端以便对其再次升级版本号吗?(已升级的不会被恢复为之前的版本号!)","系统提示",function(v,h,f){
					
		 			if(v=="ok"){
						$.ajax({
						   type: "POST",
						   url: "${pageContext.request.contextPath}/pos/edcTerminalX/toReset",
						   success: function(resultMap){
							   if(resultMap.result == '0'){
								   $.jBox.prompt(resultMap.resultMsg,"系统提示");
								   window.location.href = "${pageContext.request.contextPath}/pos/edcTerminalX/list";
							   } else
							       $.jBox.prompt(resultMap.resultMsg,"系统提示");
						   }
						});	
					}
				},{buttonsFocus:1});
				$('.jbox-body .jbox-icon').css('top','55px');
			}
		}
 		
 		var getManagerList = function() {
 			$("#merchantAdvisor").html("<option value=''>--请选择--</option>");	
			$("#merchantAdvisor").select().val('');
			var terminalArea=$.trim($("#terminalArea").val());
			if(terminalArea!=""&&terminalArea!="undefined"){
				$.ajax({
					url : '${pageContext.request.contextPath}/pos/merchantBase/getManagerList',
					type : "post",
					data : {
						roleId : '',
						managerArea: $("#terminalArea").val()
					},
					dataType : "json",
					success : function(data) {
						if (data.length != 0) {
							$.each(data, function(i, info) {
								$("#merchantAdvisor").append(
									"<option title='"+info.name+"' value='"+info.value+"'>" + info.name + "</option>");
							});
						}							
					}
				})
			}

		};		
		var getCityList = function() {
			$("#terminalCity").html("<option value=''>--请选择--</option>");
			$("#terminalCity").select().val('');
			$("#terminalZone").html("<option value=''>--请选择--</option>");	
			$("#terminalZone").select().val('');
			var terminalArea=$.trim($("#terminalProvince").val());
			if(terminalArea!=""&&terminalArea!="undefined"){			
				$.ajax({
					url : '${pageContext.request.contextPath}/cortex/cortexArea/getCortexAreaList',
					type : "post",
					data : {
						depth : '2',
						fid: terminalArea
					},
					dataType : "json",
					success : function(data) {
						if (data.length != 0) {
							$.each(data, function(i, info) {
								$("#terminalCity").append(
									"<option title='"+info.name+"' value='"+info.value+"'>" + info.name + "</option>");
							});
						}							
					}
				})
			}
		};
		var getZoneList = function() {
			$("#terminalZone").html("<option value=''>--请选择--</option>");	
			$("#terminalZone").select().val('');
			var terminalArea=$.trim($("#terminalCity").val());
			if(terminalArea!=""&&terminalArea!="undefined"){			
				$.ajax({
					url : '${pageContext.request.contextPath}/cortex/cortexArea/getCortexAreaList',
					type : "post",
					data : {
						depth : '',
						fid: terminalArea
					},
					dataType : "json",
					success : function(data) {
						if (data.length != 0) {
							$.each(data, function(i, info) {
								$("#terminalZone").append(
									"<option title='"+info.name+"' value='"+info.value+"'>" + info.name + "</option>");
							});
						}							
					}
				})
			}
		};
		
		function selectAll(itself, selectMer){
 			var checkBoxs = document.getElementsByName(selectMer);
 			
 			for (var i = 0; i < checkBoxs.length; i++){
 				checkBoxs[i].checked = itself.checked;
 			}
 		}
		
		function resetSelects(){
			$("#terminalArea").select().val('');
 			$("#merchantAdvisor").html("<option value=''>--请选择--</option>");
			$("#merchantAdvisor").select().val('');
			$("#terminalProvince").select().val('');
			$("#terminalCity").html("<option value=''>--请选择--</option>");
			$("#terminalCity").select().val('');
			$("#terminalZone").html("<option value=''>--请选择--</option>");
			$("#terminalZone").select().val('');
 		}
		
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${pageContext.request.contextPath}/pos/edcTerminalX/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:10px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${pageContext.request.contextPath}/pos/edcTerminalX/templateTerminalX">下载模板</a>
			<h2>终端号为必填项，且唯一!</h2>
		</form>
	</div>

	<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" /> 
	<div class="sh_title">终端补充信息列表</div>
	<form:form id="searchForm" modelAttribute="edcTerminal" action="${pageContext.request.contextPath}/pos/edcTerminalX/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<input type="hidden" name="selectMers" />
		
		<ul class="ul-form">
			<li><label>终端号：</label>
				<form:input path="terminalId" htmlEscape="false" maxlength="8" class="input-medium"/>
			</li>
			<li><label>升级日期：</label>
				<input name="upgradeDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${edcTerminal.upgradeDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<li><label>区域：</label>
				<form:select path="terminalArea" class="input-medium" onchange="getManagerList();">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('MERCHANT_AREA')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>商户顾问：</label>
				<form:select path="merchantAdvisor" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${managerList}" itemLabel="managerName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>门店联系人：</label>
				<form:input path="storeContacts" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>省份：</label>
				<form:select path="terminalProvince" class="input-medium" onchange="getCityList();">
					<form:option value="" label="--请选择--"/>
					<form:options items="${provinceList}" itemLabel="provinceCity" itemValue="id" htmlEscape="false"/>
				</form:select>	
			</li>
			<li><label>城市：</label>
				<form:select path="terminalCity" class="input-medium" onchange="getZoneList();">
					<form:option value="" label="--请选择--"/>
					<form:options items="${cityList}" itemLabel="provinceCity" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>区：</label>
				<form:select path="terminalZone" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${zoneList}" itemLabel="provinceCity" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>位置：</label>
				<form:input path="terminalPosition" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>升级版本号：</label>
				<form:input path="upgradeVersion" htmlEscape="false" maxlength="8" class="input-medium"/>
			</li>
			<li class="btns" style="padding-left: 180px;">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button"	value="导出" />
				<input id="btnClear"  class="btn btn-primary" type="button" value="清空" onClick="ClearForm('searchForm');resetSelects();"/>
			</li>
		
			<shiro:hasPermission name="pos:edcTerminalX:edit">	
				<li class="btns">
					<input id="btnImport" class="button" type="button"	value="导入" />	
					<input type="button" value="添加" class="button" onClick='toAdd()'/>
				</li>	
			</shiro:hasPermission>		
			<li class="clearfix"></li>
		</ul>
		<shiro:hasPermission name="pos:edcTerminalX:edit">
			<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" />
			<ul class="ul-form">
				<li><label>升级日期：</label>
					<input id="upgradeDateAjax" name="upgradeDateAjax" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
						onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false});"/>
					<font color="red">*</font>
				</li>
				<li class="btns">
					<input type="button" value="升级" class="button" onClick="toUpgrade('selectMers','selectMer')"/>
				</li>
				<li class="btns">
					<input type="button" value="重置" class="button" onClick="toReset()"/>
				</li>
			</ul>
		</shiro:hasPermission>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="20" align="center">
					<input type="checkbox" name="selectAll" onclick="selectAll(this, 'selectMer')" />
					升级
				</th>
				<th>终端号</th>
				<th>升级日期</th>
				<th>升级版本号</th>
				<th>门店联系人</th>
				<th>商户顾问</th>
				<th>区域</th>
				<th>省份</th>
				<th>城市</th>
				<th>区</th>
				<shiro:hasPermission name="pos:edcTerminalX:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="edcTerminalX">
			<tr>
				<c:if test="${edcTerminalX.historyVersion == null}">								
					<td align="center">
						<input type="checkbox" name="selectMer" value="<c:out value='${edcTerminalX.terminalId}'/>">
					</td>
				</c:if>
				<c:if test="${edcTerminalX.upgradeVersion gt edcTerminalX.historyVersion}">								
					<td align="center"></td>
				</c:if>
				<td><a href="javascript:toEdit('${edcTerminalX.terminalId}','${edcTerminalX.merchantId}')">
					${edcTerminalX.terminalId}
				</a></td>
				<td>
					${edcTerminalX.upgradeDate}
				</td>
				<td>
					${edcTerminalX.upgradeVersion}
				</td>
				<td>
					${edcTerminalX.storeContacts}
				</td>
				<td>
					${edcTerminalX.merchantAdvisor}
				</td>
				<td>
					${fns:getDictLabel(edcTerminalX.terminalArea, 'MERCHANT_AREA', '')}
				</td>
				<td>
					${edcTerminalX.terminalProvince}
				</td>
				<td>
					${edcTerminalX.terminalCity}
				</td>
				<td>
					${edcTerminalX.terminalZone}
				</td>
				<shiro:hasPermission name="pos:edcTerminalX:edit">
					<td>
						<a href="javascript:toEdit('${edcTerminalX.terminalId}','${edcTerminalX.merchantId}')">修改</a>	
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>