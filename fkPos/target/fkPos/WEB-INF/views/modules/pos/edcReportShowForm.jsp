<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户详细信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			$('#closeBtn').click(function() {
				parent.layer.close(index);
			});
		});
	</script>
</head>
<body>
	<div class="sh_title">商户综合信息</div>	
	<form:form id="inputForm" modelAttribute="reportShow" action="${pageContext.request.contextPath}/pos/merchantBase/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<div id="messageBox" class="alert alert-info hide">
			<button data-dismiss="alert" class="close">×</button>${message}</div>
		<ul id="myTab" class="nav nav-tabs">
			<li class="active"><a href="#base" data-toggle="tab">详细信息</a></li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="base">
				<div class="control-group">
					<label class="control-label">商户类别：</label>
					<div class="controls">
						<input name="mcc" type="text" disabled="true" class="input-xlarge"
							value="${reportShow.merchantType}-${reportShow.mcc}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">商户公司名称：</label>
					<div class="controls">
						<input name="merchantCompanyName" type="text" disabled="true" class="input-xlarge"
							value="${reportShow.merchantCompanyName}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">商户名称：</label>
					<div class="controls">
						<input name="merchantCname" type="text" disabled="true" class="input-xlarge"
							value="${reportShow.merchantCname}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">商户编号：</label>
					<div class="controls">
						<input name="merchantId" type="text" disabled="true" class="input-xlarge"
							value="${reportShow.merchantId}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">终端号：</label>
					<div class="controls">
						<input name="terminalId" type="text" disabled="true" class="input-xlarge"
							value="${reportShow.terminalId}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">省份：</label>
					<div class="controls">
						<input name="terminalProvince" type="text" disabled="true" class="input-xlarge"
							value="${reportShow.terminalProvince}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">城市：</label>
					<div class="controls">
						<input name="terminalCity" type="text" disabled="true" class="input-xlarge"
							value="${reportShow.terminalCity}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">区：</label>
					<div class="controls">
						<input name="terminalZone" type="text" disabled="true" class="input-xlarge"
							value="${reportShow.terminalZone}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">SN码：</label>
					<div class="controls">
						<input name="posSn" type="text" disabled="true" class="input-xlarge"
							value="${reportShow.posSn}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">POS机状态：</label>
					<div class="controls">
						<form:select path="posStatus" class="input-xlarge required" disabled="true">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('POS_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">POS型号：</label>
					<div class="controls">
						<input name="modelId" type="text" disabled="true" class="input-xlarge"
							value="${reportShow.modelId}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">POS类别：</label>
					<div class="controls">
						<form:select path="posType" class="input-xlarge " disabled="true">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('POS_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">SIM卡手机号码：</label>
					<div class="controls">
						<input name="phoneNumber" type="text" disabled="true" class="input-xlarge"
						value="${reportShow.phoneNumber}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">SIM卡串号：</label>
					<div class="controls">
						<input name="serialNumber" type="text" disabled="true" class="input-xlarge"
						value="${reportShow.serialNumber}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">SIM卡押金(元)：</label>
					<div class="controls">
						<input name="simDeposit" type="text" disabled="true" class="input-xlarge"
						value="${reportShow.simDeposit}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">店铺及款台位置：</label>
					<div class="controls">
						<input name="setAddr" type="text" disabled="true" class="input-xlarge"
							value="${reportShow.setAddr}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">终端装机地址：</label>
					<div class="controls">
						<input name="terminalPosition" type="text" disabled="true" class="input-xlarge"
							value="${reportShow.terminalPosition}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">区域：</label>
					<div class="controls">
						<form:select path="merchantArea" class="input-xlarge " disabled="true">
							<form:option value="" label="--请选择--"/>
							<form:options items="${fns:getDictList('MERCHANT_AREA')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">门店负责人：</label>
					<div class="controls">
						<form:select path="storeManager" class="input-xlarge"  disabled="true">
							<form:option value="" label="--请选择--"/>
							<form:options items="${storeManagerList}" itemLabel="managerName" itemValue="id" htmlEscape="false"/>
						</form:select>						
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">市场联系人：</label>
					<div class="controls">
						<input name="marketContactPerson" type="text" disabled="true" class="input-xlarge"
							value="${reportShow.marketContactPerson}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">财务联系人：</label>
					<div class="controls">
						<input name="financialContactPerson" type="text" disabled="true" class="input-xlarge"
							value="${reportShow.financialContactPerson}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">联系人：</label>
					<div class="controls">
						<input name="storeContacts" type="text" disabled="true" class="input-xlarge"
							value="${reportShow.storeContacts}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">门店电话：</label>
					<div class="controls">
						<input name="storePhone" type="text" disabled="true" class="input-xlarge"
							value="${reportShow.storePhone}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">押金（元）：</label>
					<div class="controls">
						<input name="merchantDeposit" type="text" disabled="true" class="input-xlarge  number"
							value="${reportShow.merchantDeposit}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">续约方式：</label>
					<div class="controls">
						<form:select path="renewalType" class="input-xlarge " disabled="true">
							<form:option value="" label="--请选择--"/>
							<form:options items="${fns:getDictList('RENEWAL_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">费率：</label>
					<div class="controls">
						<input name="timezone" type="text" disabled="true" class="input-xlarge"
							value="${reportShow.timezone}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">保函金额：</label>
					<div class="controls">
						<input name="guaranteeAmt" type="text" disabled="true" class="input-xlarge  number"
							value="<fmt:formatNumber value='${reportShow.guaranteeAmt}' pattern='##.##'
													minFractionDigits='2'></fmt:formatNumber>"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">保函到期日期：</label>
					<div class="controls">
						<input name="guaranteeLastDate" type="text" disabled="true" maxlength="20" class="input-medium Wdate "
							value="${reportShow.guaranteeLastDate}"
							onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">合同签约时间：</label>
					<div class="controls">
						<input name="contractStartDate" type="text" disabled="true" maxlength="20" class="input-medium Wdate "
							value="${reportShow.contractStartDate}"
							onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">合同续约时间：</label>
					<div class="controls">
						<input name="contractRenewalDate" type="text" disabled="true" class="input-medium Wdate "
							value="${reportShow.contractRenewalDate}"
							onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">合同终止时间：</label>
					<div class="controls">
						<input name="contractEndDate" type="text" disabled="true" class="input-medium Wdate "
							value="${reportShow.contractEndDate}"
							onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">终端录入日期：</label>
					<div class="controls">
						<input name="setDate" type="text" disabled="true" class="input-medium Wdate "
							value="${reportShow.setDate}"
							onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">终端停用日期：</label>
					<div class="controls">
						<input name="disableDate" type="text" disabled="true" class="input-medium Wdate "
							value="${reportShow.disableDate}"
							onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">升级日期：</label>
					<div class="controls">
						<input name="upgradeDate" type="text" disabled="true" class="input-medium Wdate "
							value="${reportShow.upgradeDate}"
							onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">创建时间：</label>
					<div class="controls">
						<input name="createTime" type="text" disabled="true" class="input-medium Wdate "
							value="${reportShow.createTime}"
							onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
					</div>
				</div>
			</div>			
		</div>
		<div class="form-actions">
			<input type="button" id="closeBtn" value="关闭" class="button" />										
		</div>
	</form:form>
</body>
</html>