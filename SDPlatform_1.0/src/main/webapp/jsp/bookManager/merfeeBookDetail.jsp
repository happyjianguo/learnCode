<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>商户手续费应收单明细</title>
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
		<c:if test="${!empty param.read}" >
		$("#back").hide();
		</c:if>
	});
	</script>
</head>

<body>
	<!--------------以下Table为路径-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">
				当前位置：收单系统管理平台 >> 单据管理 >> 商户手续费应收单
			</td>
		</tr>
		<tr>
			<td height="10">
			</td>
		</tr>
	</table>
	<!--------------Table为路径结束-------->


	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				商户手续费应收单明细
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="analyzeMerfeeBookForm" />
				<html:errors />
				<html:form action="/merclearBook">
					<input type="hidden" name="method" value="queryAll" />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								商户结算单ID:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.id }
							</td>
							<td class="table2_td_title">
								单据登记时间:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.gentime }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								商户编号:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.merno }
							</td>
							<td class="table2_td_title">
								单据生成日:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.bookdate }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								费用周期开始日:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.cyclestartdate }
							</td>
							<td class="table2_td_title">
								费用周期结束日:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.cycleenddate }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								手续费应收单状态:
							</td>
							<td class="table2_td">
								<c:if test="${analyzeMerfeeBookForm.status eq 0}">初登记(待审核)</c:if>
								<c:if test="${analyzeMerfeeBookForm.status eq 1}">已收</c:if>
								<c:if test="${analyzeMerfeeBookForm.status eq 2}">审核通过(待收)</c:if>
								<c:if test="${analyzeMerfeeBookForm.status eq 3}">审核拒绝</c:if>
								<c:if test="${analyzeMerfeeBookForm.status eq 4}">删除</c:if>
							</td>
							<td class="table2_td_title">
								应收手续费金额 (单位:分):
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.fee }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								轧差退货交易笔数:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.trannum }
							</td>
							<td class="table2_td_title">
								轧差退货交易金额(单位:分):
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.tranamt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								审核时间:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.audtime }
							</td>
							<td class="table2_td_title">
								审核操作员:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.audoper }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								审核意见:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.audremark }
							</td>
							<td class="table2_td_title">
								收款凭证号:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.collectvoucher }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								收款日期:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.collectdate }
							</td>
							<td class="table2_td_title">
								商户手续费账户开户行分支行:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.bankbranchname }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								商户手续费账户银行账户号:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.bankaccno }
							</td>
							<td class="table2_td_title">
								商户手续费账户银行账户名:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.bankaccname }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								备注:
							</td>
							<td colspan="3" class="table2_td">
								${analyzeMerclearBookForm.comments }
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
</html:html>