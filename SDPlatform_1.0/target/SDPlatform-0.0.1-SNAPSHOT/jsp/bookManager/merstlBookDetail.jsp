<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>商户结算打款单明细</title>
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
				当前位置：收单系统管理平台 >> 单据管理 >> 商户结算打款单
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
				商户结算打款单明细
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="analyzeMerclearBookForm" />
				<html:errors />
				<html:form action="/merstlBook">
					<input type="hidden" name="method" value="queryAll" />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								商户结算单ID:
							</td>
							<td class="table2_td">
								${analyzeMerstlBookForm.id }
							</td>
							<td class="table2_td_title">
								单据登记时间:
							</td>
							<td class="table2_td">
								${analyzeMerstlBookForm.gentime }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								商户编号:
							</td>
							<td class="table2_td">
								${analyzeMerstlBookForm.merno }
							</td>
							<td class="table2_td_title">
								结算日期-逻辑日期:
							</td>
							<td class="table2_td">
								${analyzeMerstlBookForm.stldate }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								结算开始日:
							</td>
							<td class="table2_td">
								${analyzeMerstlBookForm.stlstartdate }
							</td>
							<td class="table2_td_title">
								结算结束日:
							</td>
							<td class="table2_td">
								${analyzeMerstlBookForm.stlenddate }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								结算单状态 :
							</td>
							<td class="table2_td">
								<c:if test="${analyzeMerstlBookForm.status eq 0}">处登记(已结算,待打款或待归集)</c:if>
								<c:if test="${analyzeMerstlBookForm.status eq 1}">打款成功</c:if>
								<c:if test="${analyzeMerstlBookForm.status eq 2}">打款失败</c:if>
								<c:if test="${analyzeMerstlBookForm.status eq 3}">已归集</c:if>
								<c:if test="${displayTable.status eq 4}">单据作废</c:if>
							</td>
							<td class="table2_td_title">
								结算金额(清分金额-退货金额):
							</td>
							<td class="table2_td">
								${analyzeMerstlBookForm.stlamt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								付款手续费:
							</td>
							<td class="table2_td">
								${analyzeMerstlBookForm.payoutfee }
							</td>
							<td class="table2_td_title">
								付款金额:
							</td>
							<td class="table2_td">
								${analyzeMerstlBookForm.payoutamt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								付款流水号(资金平台回写):
							</td>
							<td class="table2_td">
								${analyzeMerstlBookForm.payoutjnlno }
							</td>
							<td class="table2_td_title">
								结算商户开户行分支行:
							</td>
							<td class="table2_td">
								${analyzeMerstlBookForm.bankbranchname }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								结算商户结算银行账户号:
							</td>
							<td class="table2_td">
								${analyzeMerstlBookForm.bankaccno }
							</td>
							<td class="table2_td_title">
								结算商户结算银行账户名:
							</td>
							<td class="table2_td">
								${analyzeMerstlBookForm.bankaccname }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								结算商户结算银行账户预留手机号:
							</td>
							<td class="table2_td">
								${analyzeMerstlBookForm.bankaccphone }
							</td>
							<td class="table2_td_title">
								结算笔数(包括退货):
							</td>
							<td class="table2_td">
								${analyzeMerstlBookForm.stlnum }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								备注:
							</td>
							<td colspan="3" class="table2_td">
								${analyzeMerstlBookForm.comments }
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