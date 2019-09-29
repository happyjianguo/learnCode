<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>银联对账和对统计明细</title>
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
				当前位置：收单系统管理平台 >> 单据管理 >> 银联对账和对统计
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
				银联对账和对统计明细
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="analyzeCupcheckStaticsForm" />
				<html:errors />
				<html:form action="/cupcheckOk">
					<input type="hidden" name="method" value="queryAll" />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								银联对账统计单:
							</td>
							<td class="table2_td" colspan="3">
								${analyzeCupcheckStaticsForm.bookid }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								对账文件日期:
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.filedate }
							</td>
							<td class="table2_td_title">
								对账日期:
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.checkDate }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								系统侧当日勾兑总笔数:
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.sysCurCheckTotalNum }
							</td>
							<td class="table2_td_title">
								系统侧当日勾兑总金额:
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.sysCurCheckTotalAmt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								银联侧当日勾兑总笔数:
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.cupCurCheckTotalNum }
							</td>
							<td class="table2_td_title">
								银联侧当日勾兑总金额:
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.cupCurCheckTotalAmt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								当日勾平总笔数:
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.curCheckOkNum }
							</td>
							<td class="table2_td_title">
								当日勾平总金额:
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.curCheckOkAmt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								当日差错总笔数<br>(包括单边掉单和金额不等):
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.curCheckErrNum }
							</td>
							<td class="table2_td_title">
								当日差错总金额<br>(包括单边掉单和金额不等):
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.curCheckErrAmt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								当日长款总笔数<br>(包括单边掉单和金额不等):
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.curCheckMoerNum }
							</td>
							<td class="table2_td_title">
								当日长款总金额<br>(包括单边掉单和金额不等):
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.curCheckMoreAmt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								当日短款总笔数<br>(包括单边掉单和金额不等):
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.curCheckLessNum }
							</td>
							<td class="table2_td_title">
								当日短款总金额<br>(包括单边掉单和金额不等):
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.curCheckLessAmt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								当日未对总笔数:
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.sysCurCheckNotyetChkNum }
							</td>
							<td class="table2_td_title">
								当日未对总金额:
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.sysCurCheckNotyetChkAmt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								系统当日补核总笔数:
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.curCheckAddchkNum }
							</td>
							<td class="table2_td_title">
								系统当日补核总笔数:
							</td>
							<td class="table2_td">
								${analyzeCupcheckStaticsForm.curCheckAddchkAmt }
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