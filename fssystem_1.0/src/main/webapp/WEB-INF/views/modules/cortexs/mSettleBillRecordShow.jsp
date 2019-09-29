<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户结算开票设置管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${pageContext.request.contextPath}/cortexs/mSettleBillRecord/">商户结算开票修改记录详情</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="originalDate" class="form-horizontal">
		<sys:message content="${message}"/>	
		<div class="control-group">
			商户结算信息
		</div>		
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="base">
				<div class="control-group">
					<label class="control-label">商户号：</label>
					<div class="controls">
						<input name="mcc" type="text" disabled="true" 
							value="${originalData.mrchno}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">结算周期：</label>
					<div class="controls">
						<input name="fsCycle" type="text" disabled="true"
							value="${originalData.fsCycle}"/>-
						<input name="fsCycle" type="text" disabled="true"
							value="${modifyData.fsCycle}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">结算参数：</label>
					<div class="controls">
						<input name="fsCycleParam" type="text" disabled="true"
							value="${originalData.fsCycleParam}"/>-
						<input name="fsCycleParam" type="text" disabled="true"
							value="${modifyData.fsCycleParam}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">上次结算日期：</label>
					<div class="controls">
						<input name="lastSettleDate" type="text" disabled="true" maxlength="20" class="input-medium Wdate "
							value="${originalData.lastSettleDate}"
							onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>-
						<input name="lastSettleDate" type="text" disabled="true" maxlength="20" class="input-medium Wdate "
							value="${modifyData.lastSettleDate}"
							onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">是否按照不同消费场景结算：</label>
					<div class="controls">
						<c:if test="${originalData.isConsumpCategory == ''}">
							<input name="isConsumpCategory" type="text" disabled="true"/>
						</c:if>
						<c:if test="${originalData.isConsumpCategory == '0'}">
							<input name="isConsumpCategory" type="text" disabled="true" 
							value="否"/>
						</c:if>
						<c:if test="${originalData.isConsumpCategory == '1'}">
							<input name="isConsumpCategory" type="text" disabled="true" 
							value="是"/>
						</c:if>
						-
						<c:if test="${modifyData.isConsumpCategory == ''}">
							<input name="isConsumpCategory" type="text" disabled="true"/>
						</c:if>
						<c:if test="${modifyData.isConsumpCategory == '0'}">
							<input name="isConsumpCategory" type="text" disabled="true" 
							value="否"/>
						</c:if>
						<c:if test="${modifyData.isConsumpCategory == '1'}">
							<input name="isConsumpCategory" type="text" disabled="true"
							value="是"/>
						</c:if>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">是否合并打款：</label>
					<div class="controls">
						<c:if test="${originalData.amtConsumpCategory == ''}">
							<input name="amtConsumpCategory" type="text" disabled="true"/>
						</c:if>
						<c:if test="${originalData.amtConsumpCategory == '0'}">
							<input name="amtConsumpCategory" type="text" disabled="true" 
							value="否"/>
						</c:if>
						<c:if test="${originalData.amtConsumpCategory == '1'}">
							<input name="amtConsumpCategory" type="text" disabled="true" 
							value="是"/>
						</c:if>
						-
						<c:if test="${modifyData.amtConsumpCategory == ''}">
							<input name="amtConsumpCategory" type="text" disabled="true"/>
						</c:if>
						<c:if test="${modifyData.amtConsumpCategory == '0'}">
							<input name="amtConsumpCategory" type="text" disabled="true"
							value="否"/>
						</c:if>
						<c:if test="${modifyData.amtConsumpCategory == '1'}">
							<input name="amtConsumpCategory" type="text" disabled="true"
							value="是"/>
						</c:if>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">普卡/专属卡是否合并打款：</label>
					<div class="controls">
						<c:if test="${originalData.mergeMoneyFlag == ''}">
							<input name="mergeMoneyFlag" type="text" disabled="true"/>
						</c:if>
						<c:if test="${originalData.mergeMoneyFlag == '0'}">
							<input name="mergeMoneyFlag" type="text" disabled="true"
							value="否"/>
						</c:if>
						<c:if test="${originalData.mergeMoneyFlag == '1'}">
							<input name="mergeMoneyFlag" type="text" disabled="true"
							value="是"/>
						</c:if>
						-
						<c:if test="${modifyData.mergeMoneyFlag == ''}">
							<input name="mergeMoneyFlag" type="text" disabled="true"/>
						</c:if>
						<c:if test="${modifyData.mergeMoneyFlag == '0'}">
							<input name="mergeMoneyFlag" type="text" disabled="true"
							value="否"/>
						</c:if>
						<c:if test="${modifyData.mergeMoneyFlag == '1'}">
							<input name="mergeMoneyFlag" type="text" disabled="true"
							value="是"/>
						</c:if>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">是否按照卡类别分组：</label>
					<div class="controls">
						<c:if test="${originalData.isCardTypeGroup == ''}">
							<input name="isCardTypeGroup" type="text" disabled="true"/>
						</c:if>
						<c:if test="${originalData.isCardTypeGroup == '0'}">
							<input name="isCardTypeGroup" type="text" disabled="true"
							value="否"/>
						</c:if>
						<c:if test="${originalData.isCardTypeGroup == '1'}">
							<input name="isCardTypeGroup" type="text" disabled="true"
							value="是"/>
						</c:if>
						-
						<c:if test="${modifyData.isCardTypeGroup == ''}">
							<input name="isCardTypeGroup" type="text" disabled="true"/>
						</c:if>
						<c:if test="${modifyData.isCardTypeGroup == '0'}">
							<input name="isCardTypeGroup" type="text" disabled="true"
							value="否"/>
						</c:if>
						<c:if test="${modifyData.isCardTypeGroup == '1'}">
							<input name="isCardTypeGroup" type="text" disabled="true"
							value="是"/>
						</c:if>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">是否按父商户结算：</label>
					<div class="controls">
						<c:if test="${originalData.isFmrchnoDecide == ''}">
							<input name="isFmrchnoDecide" type="text" disabled="true"/>
						</c:if>
						<c:if test="${originalData.isFmrchnoDecide == '0'}">
							<input name="isFmrchnoDecide" type="text" disabled="true"
								value="否"/>
						</c:if>
						<c:if test="${originalData.isFmrchnoDecide == '1'}">
							<input name="isFmrchnoDecide" type="text" disabled="true"
								value="是"/>
						</c:if>
						-
						<c:if test="${modifyData.isFmrchnoDecide == ''}">
							<input name="isFmrchnoDecide" type="text" disabled="true"/>
						</c:if>
						<c:if test="${modifyData.isFmrchnoDecide == '0'}">
							<input name="isFmrchnoDecide" type="text" disabled="true"
								value="否"/>
						</c:if>
						<c:if test="${modifyData.isFmrchnoDecide == '1'}">
							<input name="isFmrchnoDecide" type="text" disabled="true"
								value="是"/>
						</c:if>
					</div>
				</div>
			</div>			
		</div>
		<div class="control-group">
			商户开票信息
		</div>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="base">
				<div class="control-group">
					<label class="control-label">是否开票：</label>
					<div class="controls">
						<c:if test="${originalData.isKp == ''}">
							<input name="isKp" type="text" disabled="true"/>
						</c:if>
						<c:if test="${originalData.isKp == '0'}">
							<input name="isKp" type="text" disabled="true"
							value="否"/>
						</c:if>
						<c:if test="${originalData.isKp == '1'}">
							<input name="isKp" type="text" disabled="true"
							value="是"/>
						</c:if>
						-
						<c:if test="${modifyData.isKp == ''}">
							<input name="isKp" type="text" disabled="true"/>
						</c:if>
						<c:if test="${modifyData.isKp == '0'}">
							<input name="isKp" type="text" disabled="true"
							value="否"/>
						</c:if>
						<c:if test="${modifyData.isKp == '1'}">
							<input name="isKp" type="text" disabled="true"
							value="是"/>
						</c:if>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">开票周期：</label>
					<div class="controls">
						<input name="fsKpCycle" type="text" disabled="true"
							value="${originalData.fsKpCycle}"/>-
						<input name="fsKpCycle" type="text" disabled="true"
							value="${modifyData.fsKpCycle}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">开票周期参数：</label>
					<div class="controls">
						<input name="fsKpCycleParam" type="text" disabled="true"
							value="${originalData.fsKpCycleParam}"/>-
						<input name="fsKpCycleParam" type="text" disabled="true"
							value="${modifyData.fsKpCycleParam}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">上次开票日期：</label>
					<div class="controls">
						<input name="lastKpDate" type="text" disabled="true" maxlength="20" class="input-medium Wdate "
							value="${originalData.lastKpDate}"
							onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>-
						<input name="lastKpDate" type="text" disabled="true" maxlength="20" class="input-medium Wdate "
							value="${modifyData.lastKpDate}"
							onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
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