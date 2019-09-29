<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>计息明细</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wankeInterestAccrual" 
		class="form-horizontal">
		<sys:message content="${message}"/>
		
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="base">
				<div class="control-group">
					<label class="control-label">统计日期：</label>
					<div class="controls">
						<form:input path="transactiondate" disabled="true" htmlEscape="false"  class="inputext_style required"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">账户总余额：</label>
					<div class="controls">
						<form:input path="cardaccount" disabled="true" htmlEscape="false" class="inputext_style"/>
						<font>单位：元</font>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">上交备付金：</label>
					<div class="controls">
						<form:input path="stockFund" disabled="true" htmlEscape="false" class="inputext_style"/>
						<font>单位：元</font>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">当日充值金额：</label>
					<div class="controls">
						<form:input path="rechargeAmt" disabled="true" htmlEscape="false" class="inputext_style"/>
						<font>单位：元</font>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">定期存款金额：</label>
					<div class="controls">
						<form:input path="fixedDeposit" disabled="true" htmlEscape="false" class="inputext_style"/>
						<font>单位：元</font>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">净额：</label>
					<div class="controls">
						<form:input path="netAmount" disabled="true" htmlEscape="false" class="inputext_style"/>
						<font>单位：元</font>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">留底金额：</label>
					<div class="controls">
						<form:input path="littleAmount" disabled="true" htmlEscape="false" class="inputext_style"/>
						<font>单位：元</font>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">活期计息基数：</label>
					<div class="controls">
						<form:input path="currentInterestBase" disabled="true" htmlEscape="false" class="inputext_style"/>
						<font>单位：元</font>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">协定存款计息基数：</label>
					<div class="controls">
						<form:input path="agreementDepositBase" disabled="true" htmlEscape="false" class="inputext_style"/>
						<font>单位：元</font>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">留底和活期的利息金额：</label>
					<div class="controls">
						<form:input path="agreementCurrentInterest" disabled="true" htmlEscape="false" class="inputext_style"/>
						<font>单位：元</font>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">协定存款利息金额：</label>
					<div class="controls">
						<form:input path="agreementDepositInterest" disabled="true" htmlEscape="false" class="inputext_style"/>
						<font>单位：元</font>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">总计息：</label>
					<div class="controls">
						<form:input path="grossInterest" disabled="true" htmlEscape="false" class="inputext_style"/>
						<font>单位：元</font>
					</div>
				</div>
			</div>			
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>