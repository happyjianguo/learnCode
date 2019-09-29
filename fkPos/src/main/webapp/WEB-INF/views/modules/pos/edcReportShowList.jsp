<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报表展示信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$('#btnSubmit').click(function () {
				var beginContractStartDate = $("#beginContractStartDate").val();
				var endContractStartDate = $("#endContractStartDate").val();
				var beginContractRenewalDate = $("#beginContractRenewalDate").val();
				var endContractRenewalDate = $("#endContractRenewalDate").val();
				var beginCreateDate = $("#beginCreateDate").val();
				var endCreateDate = $("#endCreateDate").val();
				var beginSetDate = $("#beginSetDate").val();
				var endSetDate = $("#endSetDate").val();
				
				if(beginContractStartDate!='' && endContractStartDate!='' && (getDate(beginContractStartDate)-getDate(endContractStartDate)>0)){
					alert("结束签订日期不能小于开始签订日期");
					return false;
				}
				if(beginContractRenewalDate!='' && endContractRenewalDate!='' && (getDate(beginContractRenewalDate)-getDate(endContractRenewalDate)>0)){
					alert("结束续约日期不能小于开始签订日期");
					return false;
				}
				if(beginCreateDate!='' && endCreateDate!='' && (getDate(beginCreateDate)-getDate(endCreateDate)>0)){
					alert("结束创建日期不能小于开始创建日期");
					return false;
				}
				if(beginSetDate!='' && endSetDate!='' && (getDate(beginSetDate)-getDate(endSetDate)>0)){
					alert("结束录入日期不能小于开始录入日期");
					return false;
				}
				
				$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/edcReportShow/list");
				$("#searchForm").submit();
			});			
			$("#btnExport").click(function(){
				$.jBox.confirm("确认要生成相应的报表吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/edcReportShow/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${pageContext.request.contextPath}/pos/edcReportShow/list");
					}
				},{buttonsFocus:1});
				$('.jbox-body .jbox-icon').css('top','55px');
			});
			
		});
		
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
		
		/* function avable(){
			var merchantId = $.trim($("#merchantId").val());
			var terminalId = $.trim($("#terminalId").val());
			if(!Boolean(terminalId) && !Boolean(merchantId)){
				$.jBox.prompt("商户号和终端号至少选填一个","系统提示");
				resetInput();
				addDisable();
				return;
			}
			removeDisable();
		} */
			
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function resetInput(){
			$("#merchantId").val("");
			$("#terminalId").val("");
			$("#merchantCname").val("");
			$("#timezone").val("");
			$("#terminalStat").val("");
			$("#beginContractStartDate").val("");
			$("#endContractStartDate").val("");
			$("#beginContractRenewalDate").val("");
			$("#endContractRenewalDate").val("");
			$("#beginCreateDate").val("");
			$("#endCreateDate").val("");
			$("#beginSetDate").val("");
			$("#endSetDate").val("");
			$("#contractflag").select().val("");
			$("#layOutFlag").select().val('');
			$("#aplipayWeChat").select().val('');
			$("#merchantStat").select().val('');
			$("#terminalProvince").select().val('');
			$("#terminalCity").html("<option value=''>--请选择--</option>");
			$("#terminalCity").select().val('');
			$("#terminalZone").html("<option value=''>--请选择--</option>");	
			$("#terminalZone").select().val('');
			//addDisable();
		}
		
		/* function addDisable(){
			$("#merchantCname").attr("disabled",true);
			$("#timezone").attr("disabled",true);
			$("#beginContractStartDate").attr("disabled",true);
			$("#endContractStartDate").attr("disabled",true);
			$("#beginContractRenewalDate").attr("disabled",true);
			$("#endContractRenewalDate").attr("disabled",true);
			$("#contractflag").attr("disabled",true);
		} */
		
		/* function removeDisable(){
			$("#merchantCname").removeAttr("disabled");
			$("#timezone").removeAttr("disabled");
			$("#beginContractStartDate").removeAttr("disabled");
			$("#endContractStartDate").removeAttr("disabled");
			$("#beginContractRenewalDate").removeAttr("disabled");
			$("#endContractRenewalDate").removeAttr("disabled");
			$("#contractflag").removeAttr("disabled");
		} */
   	
 		var toDetail = function(id,merchantId) {
			var laySum = $.layer({
				type : 2,
				title : false,
				zIndex : -1,
				shade : [ 0.2, '#000' ],
				closeBtn : [ 1, true ],
				area : [ '1000px', '450px' ],
				iframe : {
					src : '${pageContext.request.contextPath}/pos/edcReportShow/form?terminalId='+id
					+'&merchantId='+merchantId+'&random='+ Math.random()
				}
			});
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
	<div class="sh_title">报表展示信息列表</div>
	<form:form id="searchForm" modelAttribute="reportShow" action="${pageContext.request.contextPath}/pos/edcReportShow/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<ul class="ul-form">
			<li><label>商户号：</label>
				<form:input path="merchantId" htmlEscape="false" maxlength="15" class="input-medium"/>
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
			<li><label>商户名称：</label>
				<form:input path="merchantCname" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>商户状态：</label>
				<form:select path="merchantStat" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('MERCHANT_STAT')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li style="padding-left: 10px;">
				<label style="width:100px;">商户创建时间：</label>
				<input name="beginCreateDate" type="text" class="input-medium Wdate"
					id="beginCreateDate" value="${reportShow.beginCreateDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/> - 
				<input name="endCreateDate" type="text" class="input-medium Wdate"
					id="endCreateDate" value="${reportShow.endCreateDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
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
			<li style="padding-left: 10px;">
				<label style="width:100px;">终端录入时间：</label>
				<input name="beginSetDate" type="text" class="input-medium Wdate"
					id="beginSetDate" value="${reportShow.beginSetDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/> - 
				<input name="endSetDate" type="text" class="input-medium Wdate"
					id="endSetDate" value="${reportShow.endSetDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<li><label>合同期限：</label>
				<form:select path="contractflag" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:option value="0" label="未到期"/>
					<form:option value="1" label="已到期"/>
					<form:option value="2" label="将续约"/>
				</form:select>
			</li>
			<li><label style="width:100px;">终端所属类型：</label>
				<form:select path="layOutFlag" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:option value="APP" label="APP"/>
					<form:options items="${fns:getDictList('LAY_OUT_FLAG')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li style="padding-left: 10px;"><label>续约日期：</label>
				<input name="beginContractRenewalDate" type="text"  maxlength="20" class="input-medium Wdate"
					id="beginContractRenewalDate" value="${reportShow.beginContractRenewalDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/> - 
				<input name="endContractRenewalDate" type="text"  maxlength="20" class="input-medium Wdate"
					id="endContractRenewalDate" value="${reportShow.endContractRenewalDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<li><label>费率：</label>
				<form:input path="timezone" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label style="width:120px;">支付宝/微信商户：</label>
				<form:select path="aplipayWeChat" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:option value="0" label="不支持"/>
					<form:option value="1" label="支持"/>
				</form:select>
			</li>
			<li style="padding-left: 30px;"><label>签订日期：</label>
				<input name="beginContractStartDate" type="text"  maxlength="20" class="input-medium Wdate"
					id="beginContractStartDate" value="${reportShow.beginContractStartDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/> - 
				<input name="endContractStartDate" type="text"  maxlength="20" class="input-medium Wdate"
					id="endContractStartDate" value="${reportShow.endContractStartDate}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<li style="padding-left: 850px;">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button"	value="导出" />
				<input id="btnClear"  class="btn btn-primary" type="button" value="清空" onClick="resetInput();"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户名称</th>
				<th>商户编号</th>
				<th>终端号</th>
				<th>商户类型</th>
				<th>合同签订日期</th>
				<th>合同续约日期</th>
				<th>续约方式</th>
				<th>创建日期</th>
				<th>费率</th>
				<shiro:hasPermission name="pos:merchantX:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:if test="${page.list != null  }">
				<c:forEach items="${page.list}" var="reportShow">
					<tr>
						<td>
							${reportShow.merchantCname}
						</td>
						<td>
							${reportShow.merchantId}
						</td>
						<td>
							${reportShow.terminalId}
						</td>
						<td>
							${reportShow.merchantType}-${reportShow.mcc}
						</td>
						<td>
							${reportShow.contractStartDate}
						</td>
						<td>
							${reportShow.contractRenewalDate}
						</td>
						<td>
							${fns:getDictLabel(reportShow.renewalType, 'RENEWAL_TYPE', '')}
						</td>
						<td>
							${reportShow.createTime}
						</td>
						<td>
							${reportShow.timezone}
						</td>
						<shiro:hasPermission name="pos:merchantX:view">
							<td>
								<a href="javascript:toDetail('${reportShow.terminalId}','${reportShow.merchantId}')">查看</a>	
							</td>
						</shiro:hasPermission>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${page.list == null  }">
				<tr>
					<td colspan="9" style="text-align: center">
						<span style="color: red">***请查询***</span>
					</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>