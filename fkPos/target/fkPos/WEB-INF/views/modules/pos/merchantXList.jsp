<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户补充信息管理</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function () { 
				var beginContractStartDate = $("#beginContractStartDate").val();
				var endContractStartDate = $("#endContractStartDate").val();
				var beginContractRenewalDate = $("#beginContractRenewalDate").val();
				var endContractRenewalDate = $("#endContractRenewalDate").val();
				var beginAllotDate = $("#beginAllotDate").val();
				var endAllotDate = $("#endAllotDate").val();
				
				if(beginContractStartDate!='' && endContractStartDate!='' && (getDate(beginContractStartDate)-getDate(endContractStartDate)>0)){
					alert("结束签订日期不能小于开始签订日期");
					return false;
				}
				if(beginContractRenewalDate!='' && endContractRenewalDate!='' && (getDate(beginContractRenewalDate)-getDate(endContractRenewalDate)>0)){
					alert("结束续约日期不能小于开始续约日期");
					return false;
				}
				if(beginAllotDate!='' && endAllotDate!='' && (getDate(beginAllotDate)-getDate(endAllotDate)>0)){
					alert("结束分配日期不能小于开始分配日期");
					return false;
				}
				
				$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/merchantX/list");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要导出商户补充信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","/pos/merchantX/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","/pos/merchantX/list");
					}
				},{buttonsFocus:1});
				$('.jbox-body .jbox-icon').css('top','55px');
			});
			
		});
		
		var managerSelect = function(roleId,merchantAreaQuery,storeManagerQuery){
			$("#"+storeManagerQuery).html("<option value=''>--请选择--</option>");
			$("#"+storeManagerQuery).select().val('');
			if(merchantAreaQuery == 'merchantArea'){
				$("#merchantAdvisor").html("<option value=''>--请选择--</option>");
				$("#merchantAdvisor").select().val('');
			}
			var merchantArea = $("#"+merchantAreaQuery).val();
			if(!Boolean(merchantArea)){
				return;
			}
			//查询时调用
			if(!Boolean(roleId)){
				//获取门店负责人
				$.ajax({
					   type: "POST",
					   url: "${pageContext.request.contextPath}/pos/merchantX/getSMgrOrMAdvisor",
					   data: {roleId:'1',merchantArea:merchantArea},
					   success: function(storeManagerList){
						   if(storeManagerList == null){
							   return;
						   }
						   
					   	   for(var i = 0; i < storeManagerList.length; i++){
					   		   var storeManager = storeManagerList[i];
					   		   if(!Boolean(storeManager)){
					   			   
					   		   } else {
					   		   		$("#"+storeManagerQuery).append(
						   	   			"<option value=" + storeManager.value + ">"+
						   	   			storeManager.name + "</option>"
						   	   	    );
					   		   }
					   		   
					   	   }
					   }
				});
				//获取商户顾问
				$.ajax({
				   type: "POST",
				   url: "${pageContext.request.contextPath}/pos/merchantX/getSMgrOrMAdvisor",
				   data: {roleId:'', merchantArea:merchantArea},
				   success: function(merchantAdvisorList){
					   if(merchantAdvisorList == null){
						   return;
					   }
				   	   for(var i = 0; i < merchantAdvisorList.length; i++){
				   		   var merchantAdvisor = merchantAdvisorList[i];
				   		   
				   		   if(!Boolean(merchantAdvisor)){
				   			   
				   		   } else {
				   		   		$("#merchantAdvisor").append(
					   	   			"<option value=" + merchantAdvisor.value + ">"+
					   	   			merchantAdvisor.name + "</option>"
					   	   	    );
				   		   }
				   	   	   
				   	   }
				   	   
				   }
				});
			}else {
				//分配时调用
				//获取门店负责人
				$.ajax({
					   type: "POST",
					   url: "${pageContext.request.contextPath}/pos/merchantX/getSMgrOrMAdvisor",
					   data: {roleId:roleId,merchantArea:merchantArea},
					   success: function(storeManagerList){
						   if(storeManagerList == null){
							   return;
						   }
						   
					   	   for(var i = 0; i < storeManagerList.length; i++){
					   		   var storeManager = storeManagerList[i];
					   		   if(!Boolean(storeManager)){
					   			   
					   		   } else {
					   		   		$("#"+storeManagerQuery).append(
						   	   			"<option value=" + storeManager.value + ">"+
						   	   			storeManager.name + "</option>"
						   	   	    );
					   		   }
					   		   
					   	   }
					   }
				});
			}
			
		};
			
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
					src : '${pageContext.request.contextPath}/pos/merchantX/form?random='+ Math.random()
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
					src : '${pageContext.request.contextPath}/pos/merchantX/form?merchantId='+id+'&random='+ Math.random()
				}
			});
		}
 		
 		function toAllot(selectMers,selectMer){
 			var merchantArea = $('#merchantAreaAllot').val();
 			var storeManager = $("#storeManagerAllot").val();
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
		 		$.jBox.confirm("确认将此商户分配给门店负责人吗?","系统提示",function(v,h,f){
					if(v=="ok"){
						if(!Boolean(merchantArea)){
							$.jBox.prompt("请选择分配区域","系统提示");
							return;
						}
						if(!Boolean(storeManager)){
							$.jBox.prompt("请选择负责人","系统提示");
							return;
						}
						$.ajax({
						   type: "POST",
						   url: "${pageContext.request.contextPath}/pos/merchantX/allotMerToMgr",
						   data: {merchantIds:bb.value,merchantArea:merchantArea,storeManager:storeManager},
						   success: function(resultMap){
							   if(resultMap.result == '0'){
								   $.jBox.prompt(resultMap.resultMsg,"系统提示");
								   window.location.href = "${pageContext.request.contextPath}/pos/merchantX/list";
							   } else
								   $.jBox.prompt(resultMap.resultMsg,"系统提示");
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
 		
 		function resetSelects(){
			$("#merchantArea").select().val('');
 			$("#storeManager").html("<option value=''>--请选择--</option>");
			$("#storeManager").select().val('');
 			$("#merchantAdvisor").html("<option value=''>--请选择--</option>");
			$("#merchantAdvisor").select().val('');
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
	<div class="sh_title">商户补充信息列表</div>
	<form:form id="searchForm" modelAttribute="merchantBase" action="${pageContext.request.contextPath}/pos/merchantX/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<input type="hidden" name="selectMers" />
		
		<ul class="ul-form">
			<li><label>商户编号：</label>
				<form:input path="merchantId" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li style="padding-left: 30px;"><label>签订日期：</label>
				<input name="beginContractStartDate" id="beginContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${merchantBase.beginContractStartDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/> - 
				<input name="endContractStartDate" id="endContractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${merchantBase.endContractStartDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<li style="padding-left: 30px;"><label>市场联系人：</label>
				<form:input path="marketContactPerson" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>财务联系人：</label>
				<form:input path="financialContactPerson" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li style="padding-left: 30px;"><label>续约日期：</label>
				<input name="beginContractRenewalDate" id="beginContractRenewalDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${merchantBase.beginContractRenewalDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/> - 
				<input name="endContractRenewalDate" id="endContractRenewalDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${merchantBase.endContractRenewalDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<li style="padding-left: 30px;"><label>公司名称：</label>
				<form:input path="merchantCompanyName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>续约方式：</label>
				<form:select path="renewalType" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('RENEWAL_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li li style="padding-left: 30px;"><label>分配日期：</label>
				<input name="beginAllotDate" id="beginAllotDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${merchantBase.beginAllotDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/> - 
				<input name="endAllotDate" id="endAllotDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${merchantBase.endAllotDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<li style="padding-left: 30px;"><label>分配者：</label>
				<form:input path="sharer" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>区域：</label>
				<form:select path="merchantArea" class="input-medium" onchange="managerSelect('','merchantArea','storeManager');">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('MERCHANT_AREA')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>门店负责人：</label>
				<form:select path="storeManager" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${storeManagerList}" itemLabel="name" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>商户顾问：</label>
				<form:select path="merchantAdvisor" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${merchantAdvisorList}" itemLabel="name" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li style="padding-left: 50px;">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button"	value="导出" />
				<input id="btnClear"  class="btn btn-primary" type="button" value="清空" onClick="ClearForm('searchForm');resetSelects();"/>
			</li>
		
			<shiro:hasPermission name="pos:merchantX:edit">	
				<li class="btns">
					<input type="button" value="添加" class="button" onClick='toAdd()'/>
				</li>
			</shiro:hasPermission>	
			<li class="clearfix"></li>
		</ul>
		<c:if test="${allotAuth != 1}">
			<shiro:hasPermission name="pos:merchantX:allot">	
				<hr style="height: 1px; border:none; border-top:1px dashed #CCCCCC;" />
				<ul class="ul-form">
					<li><label>分配区域：</label>
						<form:select path="merchantAreaAllot" class="input-medium" onchange="managerSelect('1','merchantAreaAllot','storeManagerAllot');">
							<form:option value="" label="--请选择--"/>
							<form:options items="${fns:getDictList('MERCHANT_AREA')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</li>
					<li><label>门店负责人：</label>
						<form:select path="storeManagerAllot" class="input-medium">
							<form:option value="" label="--请选择--"/>
							<form:options items="${storeManagerList}" itemLabel="name" itemValue="value" htmlEscape="false"/>
						</form:select>
					</li>
					<li class="btns" style="padding-left: 20px;">
						<input type="button" value="分配" class="button" onClick="toAllot('selectMers','selectMer')"/>
					</li>
				</ul>
			</shiro:hasPermission>
		</c:if>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="20" align="center">
					<input type="checkbox" name="selectAll" onclick="selectAll(this, 'selectMer')" />
					分配
				</th>
				<th>商户编号</th>
				<th>合同签订日期</th>
				<th>合同续约日期</th>
				<th>商户公司名称</th>
				<th>续约方式</th>
				<th>市场联系人</th>
				<th>区域</th>
				<th>门店负责人</th>
				<th>商户顾问</th>
				<shiro:hasPermission name="pos:merchantX:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="merchantX">
			<tr id="dataList" >
				<c:if test="${merchantX.storeManager == null}">								
					<td align="center">
						<input type="checkbox" name="selectMer" value="<c:out value='${merchantX.merchantId}'/>">
					</td>	
				</c:if>
				<c:if test="${merchantX.storeManager != null}">								
					<td align="center"></td>	
				</c:if>
				<td><a href="javascript:toEdit('${merchantX.merchantId}')">
					${merchantX.merchantId}
				</a></td>
				<td>
					${merchantX.contractStartDate}
				</td>
				<td>
					${merchantX.contractRenewalDate}
				</td>
				<td>
					${merchantX.merchantCompanyName}
				</td>
				<td>
					${fns:getDictLabel(merchantX.renewalType, 'RENEWAL_TYPE', '')}
				</td>
				<td>
					${merchantX.marketContactPerson}
				</td>
				<td>
					${fns:getDictLabel(merchantX.merchantArea, 'MERCHANT_AREA', '')}
				</td>
				<td>
					${merchantX.storeManager}
				</td>
				<td>
					${merchantX.merchantAdvisor}
				</td>
				<shiro:hasPermission name="pos:merchantX:edit">
					<td>
						<a href="javascript:toEdit('${merchantX.merchantId}')">修改</a>	
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>