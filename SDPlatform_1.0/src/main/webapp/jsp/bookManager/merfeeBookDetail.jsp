<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>�̻�������Ӧ�յ���ϸ</title>
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
	<!--------------����TableΪ·��-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">
				��ǰλ�ã��յ�ϵͳ����ƽ̨ >> ���ݹ��� >> �̻�������Ӧ�յ�
			</td>
		</tr>
		<tr>
			<td height="10">
			</td>
		</tr>
	</table>
	<!--------------TableΪ·������-------->


	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				�̻�������Ӧ�յ���ϸ
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
								�̻����㵥ID:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.id }
							</td>
							<td class="table2_td_title">
								���ݵǼ�ʱ��:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.gentime }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�̻����:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.merno }
							</td>
							<td class="table2_td_title">
								����������:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.bookdate }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�������ڿ�ʼ��:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.cyclestartdate }
							</td>
							<td class="table2_td_title">
								�������ڽ�����:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.cycleenddate }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								������Ӧ�յ�״̬:
							</td>
							<td class="table2_td">
								<c:if test="${analyzeMerfeeBookForm.status eq 0}">���Ǽ�(�����)</c:if>
								<c:if test="${analyzeMerfeeBookForm.status eq 1}">����</c:if>
								<c:if test="${analyzeMerfeeBookForm.status eq 2}">���ͨ��(����)</c:if>
								<c:if test="${analyzeMerfeeBookForm.status eq 3}">��˾ܾ�</c:if>
								<c:if test="${analyzeMerfeeBookForm.status eq 4}">ɾ��</c:if>
							</td>
							<td class="table2_td_title">
								Ӧ�������ѽ�� (��λ:��):
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.fee }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�����˻����ױ���:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.trannum }
							</td>
							<td class="table2_td_title">
								�����˻����׽��(��λ:��):
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.tranamt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								���ʱ��:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.audtime }
							</td>
							<td class="table2_td_title">
								��˲���Ա:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.audoper }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								������:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.audremark }
							</td>
							<td class="table2_td_title">
								�տ�ƾ֤��:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.collectvoucher }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�տ�����:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.collectdate }
							</td>
							<td class="table2_td_title">
								�̻��������˻������з�֧��:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.bankbranchname }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�̻��������˻������˻���:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.bankaccno }
							</td>
							<td class="table2_td_title">
								�̻��������˻������˻���:
							</td>
							<td class="table2_td">
								${analyzeMerfeeBookForm.bankaccname }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��ע:
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
					<!-- ά����ͼ״̬�������� -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<!-- ά����ͼ״̬�������� -->
				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>