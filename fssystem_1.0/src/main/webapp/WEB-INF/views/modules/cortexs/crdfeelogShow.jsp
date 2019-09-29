<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>扣款费率管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${pageContext.request.contextPath}/cortexs/crdfeelog/">新福卡扣款明细详情</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="crdfeelog" 
		class="form-horizontal">
		<sys:message content="${message}"/>
		
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="base">
				<div class="control-group">
					<label class="control-label">卡号：</label>
					<div class="controls">
						<form:input path="pan" disabled="true" htmlEscape="false"  class="inputext_style"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">扣款月份：</label>
					<div class="controls">
						<form:input path="localDate" disabled="true" htmlEscape="false" class="inputext_style"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">扣款商户号：</label>
					<div class="controls">
						<form:input path="merchantno" disabled="true" htmlEscape="false" class="inputext_style"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">扣款终端号：</label>
					<div class="controls">
						<form:input path="termcode" disabled="true" htmlEscape="false" class="inputext_style"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">原账户余额（元）：</label>
					<div class="controls">
						<input value="<fmt:formatNumber value='${crdfeelog.avlbal}' pattern='#,###,###,###,###,##0.##'
							minFractionDigits='2'></fmt:formatNumber>" disabled="true" class="inputext_style"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">交易金额（元）：</label>
					<div class="controls">
						<input value="<fmt:formatNumber value='${crdfeelog.fee}' pattern='#,###,###,###,###,##0.##'
							minFractionDigits='2'></fmt:formatNumber>" disabled="true" class="inputext_style"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">费率（%）：</label>
					<div class="controls">
						<form:input path="rate" disabled="true" htmlEscape="false"  class="inputext_style"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">交易日期：</label>
					<div class="controls">
						<form:input path="tranDate" disabled="true" htmlEscape="false"  class="inputext_style"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">交易时间：</label>
					<div class="controls">
						<form:input path="tranTime" disabled="true" htmlEscape="false"  class="inputext_style"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">未有交易年数：</label>
					<div class="controls">
						<form:input path="noTranYear" disabled="true" htmlEscape="false" class="inputext_style"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">收取规则ID：</label>
					<div class="controls">
						<form:input path="feeRule" disabled="true" htmlEscape="false" class="inputext_style"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">TLOG流水ID：</label>
					<div class="controls">
						<form:input path="tlogId" disabled="true" htmlEscape="false" class="inputext_style"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">交易状态：</label>
					<div class="controls">
						<form:select path="reserved4" class="inputext_style" disabled="true" >
							<form:option value="" label="--请选择--"/>
							<form:option value="0" label="失败"/>
							<form:option value="7" label="成功"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">交易类型：</label>
					<div class="controls">
						<form:select path="txncode" class="inputext_style" disabled="true" >
							<form:option value="" label="--请选择--"/>
							<form:option value="0" label="消费"/>
							<form:option value="20" label="退款"/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">原交易ID：</label>
					<div class="controls">
						<form:input path="reserved3" disabled="true" htmlEscape="false" class="inputext_style"/>
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