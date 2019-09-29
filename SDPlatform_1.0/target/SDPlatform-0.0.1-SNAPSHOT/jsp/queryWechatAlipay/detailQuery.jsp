<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>流水明细</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDatea.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
    <script type="text/javascript" src="<fmt:message key='JavaScriptPath' />jquery-1.4.min.js" ></script>
	<script language="javascript">
	
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	$(document).ready(function() {
		$("#tranType").val('<c:out value="${curTranLsForm.tranType}"/>');
		$("#ccyCode").val('<c:out value="${curTranLsForm.ccyCode}"/>');
		$("#cardType").val('<c:out value="${curTranLsForm.cardType}"/>');
		$("#bankType").val('<c:out value="${curTranLsForm.bankType}"/>');
		<c:if test="${!empty param.read}" >
		$("#back").hide();
		</c:if>
	});
	</script>
</head>
<shiro:lacksPermission name="posp:queryCurTranLs:detailQueryDay">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:queryCurTranLs:detailQueryDay">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				流水明细
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="curTranLsForm" />
				<html:errors />
				<html:form action="/queryCurTranLs">
					<input type="hidden" name="method" value="queryAll" />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								商户:
							</td>
							<td class="table2_td">
								${curTranLsForm.merchantId }<br>${curTranLsForm.merchantName }
							</td>
							<td class="table2_td_title">
								终端号:
							</td>
							<td class="table2_td">
								${curTranLsForm.terminalId }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								交易流水号  :

							</td>
							<td class="table2_td">
								${curTranLsForm.traceNo }
							</td>
							<td class="table2_td_title">
								卡号   :
							</td>
							<td class="table2_td">
								${curTranLsForm.cardNo }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								交易类型    :
							</td>
							<td class="table2_td">
								<c:forEach var="model" items="${tranTypeList}">
									<c:if test="${curTranLsForm.voidOldTranType eq model.paramValue}">
										${model.paramChinese}
									</c:if>
								</c:forEach>
								<c:forEach var="model" items="${tranTypeList}">
									<c:if test="${curTranLsForm.voidTranType eq model.paramValue}">
										${model.paramChinese}
									</c:if>
								</c:forEach>
								<c:forEach var="model" items="${tranTypeList}">
									<c:if test="${curTranLsForm.tranType eq model.paramValue}">
										${model.paramChinese}
									</c:if>
								</c:forEach>
							</td>
							<td class="table2_td_title">
								交易金额:
							</td>
							<td class="table2_td">
								<font color="red">${curTranLsForm.tranAmt}</font>
							</td>
						</tr>
<%--						<tr>--%>
<%--							<td class="table2_td_title">--%>
<%--								原交易类型    :--%>
<%--							</td>--%>
<%--							<td class="table2_td">--%>
<%--								<c:forEach var="model" items="${tranTypeList}">--%>
<%--									<c:if test="${curTranLsForm.voidTranType eq model.paramValue}">--%>
<%--										${model.paramChinese}--%>
<%--									</c:if>--%>
<%--								</c:forEach>--%>
<%--							</td>--%>
<%--							<td class="table2_td_title">--%>
<%--								原原交易类型:--%>
<%--							</td>--%>
<%--							<td class="table2_td">--%>
<%--								<c:forEach var="model" items="${tranTypeList}">--%>
<%--									<c:if test="${curTranLsForm.voidOldTranType eq model.paramValue}">--%>
<%--										${model.paramChinese}--%>
<%--									</c:if>--%>
<%--								</c:forEach>--%>
<%--							</td>--%>
<%--						</tr>--%>
						<tr>
							<td class="table2_td_title">
								交易系统时间 :
							</td>
							<td class="table2_td">
								${curTranLsForm.localSysDate} - ${curTranLsForm.localSysTime}
							</td>
						    <td class="table2_td_title">
								有效期:
							</td>
							<td class="table2_td">
								${curTranLsForm.expDate}
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								商户类型  :
							</td>
							<td class="table2_td">
								${curTranLsForm.mcc }-${curTranLsForm.mccName }
							</td>
							<td class="table2_td_title">
								系统参考号:
							</td>
							<td class="table2_td">
								${curTranLsForm.tranRrn }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								Pos批次号 :
							</td>
							<td class="table2_td">
								${curTranLsForm.batchNo }
							</td>
							<td class="table2_td_title">
								银行类型:
							</td>
							<td class="table2_td">
								<select id="bankType" name="bankType" readonly="true" disabled="true">
								<c:forEach var="model" items="${bankTypeList}">
									<option value="<c:out value="${model.bankType}"/>"><c:out value="${model.typeName}" /></option>
								</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								银行批次号  :
							</td>
							<td class="table2_td">
								${curTranLsForm.bankBatchNo }
							</td>
							<td class="table2_td_title">
								银行流水号:
							</td>
							<td class="table2_td">
								${curTranLsForm.trace1 }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								银行系统参考号  :
							</td>
							<td class="table2_td">
								${curTranLsForm.trace2 }
							</td>
							<td class="table2_td_title">
								处理模块号:
							</td>
							<td class="table2_td">
								${curTranLsForm.moduleId}-
								<c:forEach var="model" items="${moduleIdList}">
									<c:if test="${curTranLsForm.moduleId eq model.paramValue}">
										${model.paramChinese}
									</c:if>
								</c:forEach>
							</td>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								卡类型:
							</td>
							<td class="table2_td">
							<select id="cardType" readonly="true" disabled="true" >
								<c:forEach var="model" items="${cardTypeList}">
								<option value="<c:out value="${model.id.cardType}"/>"><c:out value="${model.id.typeName}" /></option>
								</c:forEach>
								</select>
							</td>
							<td class="table2_td_title">
								币种:
							</td>
							<td class="table2_td">
								<select id="ccyCode" name="ccyCode" disabled="disabled">
									<option value="156">人民币</option>
									<option value="344">港币</option>
									<option value="392">日元</option>
									<option value="840">美元</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								收单行号:
							</td>
							<td class="table2_td">
								${curTranLsForm.acqBankId }
							</td>
							<td class="table2_td_title">
								发卡行号:
							</td>
							<td class="table2_td">
								${curTranLsForm.issBankId }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								接收方行号:
							</td>
							<td class="table2_td">
								${curTranLsForm.rcvBankId }
							</td>
							<td class="table2_td_title">
								授权号 :
							</td>
							<td class="table2_td">
								${curTranLsForm.authNo }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								交易返回码:
							</td>
							<td class="table2_td">
								${curTranLsForm.respCode }-
								<c:forEach var="model" items="${respCodeList}">
									<c:if test="${curTranLsForm.respCode eq model.respCode}">
										${model.rcText}
									</c:if>
								</c:forEach>
							</td>
							<td class="table2_td_title">
								响应行行号   :
							</td>
							<td class="table2_td">
								${curTranLsForm.respBankId }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								证件类型:
							</td>
							<td class="table2_td">
								${curTranLsForm.idType }
							</td>
							<td class="table2_td_title">
								证件号 :
							</td>
							<td class="table2_td">
								${curTranLsForm.personId }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								操作员号:
							</td>
							<td class="table2_td">
								${curTranLsForm.operNo }
r							</td>
							<td class="table2_td_title">
								交易标志:
							</td>
							<td class="table2_td">
								<html:select property="tranFlag" disabled="true">
									<html:option value="0">正常</html:option>
									<html:option value="1">已撤消</html:option>
									<html:option value="2">已确认</html:option>
									<html:option value="3">已调整</html:option>
									<html:option value="4">已退货</html:option>
									<html:option value="8">初登记</html:option>
								</html:select>
							</td>
						</tr>
						<tr id=back>
							<td align="center" colspan="4" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()">
							</td>
						</tr>
					</table>

					<!-- 维护视图状态的隐藏域 -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<!-- 维护视图状态的隐藏域 -->
				</html:form>

			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>