<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>�̻���ֵ���ϸ</title>
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
				��ǰλ�ã��յ�ϵͳ����ƽ̨ >> ���ݹ��� >> �̻���ֵ�
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
				�̻���ֵ���ϸ
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
				<html:form action="/merclearBook">
					<input type="hidden" name="method" value="queryAll" />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								�̻����㵥ID:
							</td>
							<td class="table2_td">
								${analyzeMerclearBookForm.id }
							</td>
							<td class="table2_td_title">
								���ݵǼ�ʱ��:
							</td>
							<td class="table2_td">
								${analyzeMerclearBookForm.gentime }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�̻����:
							</td>
							<td class="table2_td">
								${analyzeMerclearBookForm.merno }
							</td>
							<td class="table2_td_title">
								��������<br>ϵͳ��������:
							</td>
							<td class="table2_td">
								${analyzeMerclearBookForm.trandate }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								���ױ���<br>(�������˻�����):
							</td>
							<td class="table2_td">
								${analyzeMerclearBookForm.trannum }
							</td>
							<td class="table2_td_title">
								���׽��(��λ:��)<br>(�������˻���� ):
							</td>
							<td class="table2_td">
								${analyzeMerclearBookForm.tranamt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�˻�����(��λ:��):
							</td>
							<td class="table2_td">
								${analyzeMerclearBookForm.refundnum }
							</td>
							<td class="table2_td_title">
								�˻����(��λ:��):
							</td>
							<td class="table2_td">
								${analyzeMerclearBookForm.refundamt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								����Ӷ��(��λ:��):
							</td>
							<td class="table2_td">
								${analyzeMerclearBookForm.tranfee }
							</td>
							<td class="table2_td_title">
								������(��λ:��):
							</td>
							<td class="table2_td">
								${analyzeMerclearBookForm.clearamt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�����Ӧ���㵥��:
							</td>
							<td class="table2_td">
								${analyzeMerclearBookForm.stlbookid }
							</td>
							<td class="table2_td_title">
								��������<br>ϵͳ�����:
							</td>
							<td class="table2_td">
								${analyzeMerclearBookForm.stldate }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�����ʶ:
							</td>
							<td class="table2_td">
								<c:if test="${analyzeMerclearBookForm.stlflag eq 0}">δ����</c:if>
								<c:if test="${analyzeMerclearBookForm.stlflag eq 1}">�ѽ���</c:if>
							</td>
							<td class="table2_td_title">
								������(��λ:��):
							</td>
							<td class="table2_td">
								${analyzeMerclearBookForm.stlamt }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								������Ӧ�յ�:
							</td>
							<td class="table2_td">
								${analyzeMerclearBookForm.feebookid }
							</td>
							<td class="table2_td_title">
								�����ѽ�������<br>ϵͳ�����:
							</td>
							<td class="table2_td">
								${analyzeMerclearBookForm.feestldate }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�����Ѵ�����״̬ :
							</td>
							<td class="table2_td">
								<c:if test="${analyzeMerclearBookForm.feestlflag eq 0}">δ����</c:if>
								<c:if test="${analyzeMerclearBookForm.feestlflag eq 1}">�ѽ���</c:if>
								
							</td>
							<td class="table2_td_title">
								�����ѽ�����(��λ:��):
							</td>
							<td class="table2_td">
								${analyzeMerclearBookForm.feestlamt }
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