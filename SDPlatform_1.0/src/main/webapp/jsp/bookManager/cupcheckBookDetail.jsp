<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>银联对账单明细</title>
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
				当前位置：收单系统管理平台 >> 单据管理 >> 银联对账单
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
				银联对账单明细
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="analyzeCupcheckBookForm" />
				<html:errors />
				<html:form action="/cupcheckBook">
					<input type="hidden" name="method" value="queryAll" />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								收单机构对账单:
							</td>
							<td class="table2_td" colspan="3">
								${analyzeCupcheckBookForm.bookid }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								对账单登记时间:
							</td>
							<td class="table2_td">
								${analyzeCupcheckBookForm.gentasktime }
							</td>
							<td class="table2_td_title">
								对账文件日期:
							</td>
							<td class="table2_td">
								${analyzeCupcheckBookForm.filedate }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								COMA文件名<br>(交易流水文件):
							</td>
							<td class="table2_td">
								${analyzeCupcheckBookForm.comafilename }
							</td>
							<td class="table2_td_title">
								ERRA文件名<br>(差错文件):
							</td>
							<td class="table2_td">
								${analyzeCupcheckBookForm.errafilename }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								LEF文件名<br>(品牌费文件文件):
							</td>
							<td class="table2_td">
								${analyzeCupcheckBookForm.lfefilename }
							</td>
							<td class="table2_td_title">
								文件获取时间:
							</td>
							<td class="table2_td">
								${analyzeCupcheckBookForm.genfiletime }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								状态 :
							</td>
							<td class="table2_td">
								<c:if test="${analyzeCupcheckBookForm.status eq 0}">任务初登记</c:if>
								<c:if test="${analyzeCupcheckBookForm.status eq 1}">获取文件成功</c:if>
								<c:if test="${analyzeCupcheckBookForm.status eq 2}">获取文件失败</c:if>
								<c:if test="${analyzeCupcheckBookForm.status eq 3}">正在对账</c:if>
								<c:if test="${analyzeCupcheckBookForm.status eq 4}">对账完成</c:if>
								<c:if test="${analyzeCupcheckBookForm.status eq 5}">对账失败</c:if>
								<c:if test="${analyzeCupcheckBookForm.status eq 9}">任务删除</c:if>
							</td>
							<td class="table2_td_title">
								对账日期<br>(对账时的逻辑交易日):
							</td>
							<td class="table2_td">
								${analyzeCupcheckBookForm.checkdate }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								对账开始时间:
							</td>
							<td class="table2_td">
								${analyzeCupcheckBookForm.chkstarttime }
							</td>
							<td class="table2_td_title">
								对账完成时间:
							</td>
							<td class="table2_td">
								${analyzeCupcheckBookForm.chkendtime }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								COMA文件总金额:
							</td>
							<td class="table2_td">
								${analyzeCupcheckBookForm.comatotleamt }
							</td>
							<td class="table2_td_title">
								COMA文件总笔数:
							</td>
							<td class="table2_td">
								${analyzeCupcheckBookForm.comatotalcnt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								系统侧交易总金额:
							</td>
							<td class="table2_td">
								${analyzeCupcheckBookForm.systotalamt }
							</td>
							<td class="table2_td_title">
								系统侧交易总笔数:
							</td>
							<td class="table2_td">
								${analyzeCupcheckBookForm.systotalcnt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								ERRA文件总金额:
							</td>
							<td class="table2_td">
								${analyzeCupcheckBookForm.erratotleamt }
							</td>
							<td class="table2_td_title">
								ERRA文件总笔数:
							</td>
							<td class="table2_td">
								${analyzeCupcheckBookForm.erratotalcnt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								LFE文件总金额:
							</td>
							<td class="table2_td">
								${analyzeCupcheckBookForm.lfetotleamt }
							</td>
							<td class="table2_td_title">
								LFE文件总笔数:
							</td>
							<td class="table2_td">
								${analyzeCupcheckBookForm.lfetotalcnt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								备注:
							</td>
							<td colspan="3" class="table2_td">
								${analyzeCupcheckBookForm.comments }
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