<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%
	String path = request.getContextPath();
%>
<%
	String execdate = (String) request.getAttribute("execdate");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>��ʾ�б�ҳ��</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/cssem.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/new_look.css" />
</head>
<script src="<%=path%>/js/sorttable.js"></script>
<script src="<%=path%>/js/eposcc.js"></script>
<script src="<%=path%>/js/calendar.js"></script>
<script language="javascript">
	function query(){
		var bflowlogForm = document.all("bflowlogForm");
		bflowlogForm.action = "<%=path%>/snCCard.do?method=queryBFlowLogList";
		bflowlogForm.submit();
	}
	function preQueryCrdformatMap(id,panflagno)
	{
		var url =  "<%=path%>/snCCard.do?method=showBFlowLogInfo&pan="+id+"&panflagno=" + panflagno+ "&random=" + Math.random();		
		var iWidth = 1046; //�������ڵĿ��;
		var iHeight = 600; //�������ڵĸ߶�;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //��ô��ڵĴ�ֱλ��;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //��ô��ڵ�ˮƽλ��;
		var winSize = 'height='
				+ iHeight
				+ ',width='
				+ iWidth
				+ ',top='
				+ iTop
				+ ',left='
				+ iLeft
				+ ',toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no';
		window.open(url, "", winSize);	
	}		
	function addpaninfo(id,panflagno)
	{
		var url =  "<%=path%>/snCCard.do?method=preAddPanInfo&pan=" + id+ "&panflagno=" + panflagno+ "&random=" + Math.random();
		var iWidth = 1046; //�������ڵĿ��;
		var iHeight = 400; //�������ڵĸ߶�;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //��ô��ڵĴ�ֱλ��;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //��ô��ڵ�ˮƽλ��;
		var winSize = 'height='
				+ iHeight
				+ ',width='
				+ iWidth
				+ ',top='
				+ iTop
				+ ',left='
				+ iLeft
				+ ',toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no';
		window.open(url, "", winSize);
	}
</script>

<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form action="/snCCard.do?method=getBFlowLogList" method="post"
		styleId="bflowlogForm">
		<table border="0" cellpadding="0" cellspacing="0" width="100%"
			height="99%">
			<tr>
				<td align="center" valign="top" height="87%">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="28" height="10"></td>
						</tr>
						<tr>
							<td align="left" width="28" height="28"
								style="background: url(<%=path%>/image1/Navigation_bar/left1.gif)">
							</td>
							<td height="28"
								style="background: url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;��ǰλ�ã��������� &gt; ͬ�Ż�������
							</td>
							<td width="7" height="28"
								style="background: url(<%=path%>/image1/Navigation_bar/right1.gif)">
							</td>
						</tr>
					</table>
					<table cellpadding="0" border="0" cellspacing="0" width="100%"
						style="padding: 0px;" align="left">
						<tr>
							<td coslpan="4">
								<font color="red">${info}</font>
							</td>
						</tr>
						
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
							</td>
							<td style="white-space: nowrap" align="right">
								״̬
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="issucess">
									<html:option value="">����ѡ��</html:option>
									<html:option value="00">�Ѿ�����</html:option>
									<html:option value="01">�ƿ��ɹ�</html:option>
									<html:option value="02">��Ƭ��Ϣ������</html:option>
									<html:option value="03">��Ƭ״̬�쳣</html:option>
								</html:select>
							</td>
							<td style="white-space: nowrap" align="right">
								���ţ�
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="batchflag" maxlength="20" size="20" />
							</td>
							<td style="white-space: nowrap" align="left">
								<input type="button" class="button" onClick="query();" style="background-image: url(<%=path%>/image1/border/Check_button.gif)">
							</td>
						</tr>
						<tr>
							<td colspan="10">
								<display:table name="bflowlogList" partialList="true"
									size="pageBean.totalRecords" pagesize="10" style="width:100%"
									class="dpTable" cellpadding="0" cellspacing="0"
									id="displayTable" requestURI="/snCCard.do">
									<display:column title="�������ʶ" style="text-align:center">
										ͬ�Ż���
									</display:column>
									<display:column title="״̬" style="text-align:center"
										headerClass="sortable" sortable="true">
										<c:if test="${displayTable.issucess=='00' }">�Ѿ�����</c:if>
										<c:if test="${displayTable.issucess=='01' }">�ƿ��ɹ� </c:if>
										<c:if test="${displayTable.issucess=='02' }">��Ƭ��Ϣ������ </c:if>
										<c:if test="${displayTable.issucess=='03' }">��Ƭ״̬�쳣 </c:if>

									</display:column>
									<display:column title="ִ������" style="text-align:center"
										property="execdate" headerClass="sortable" sortable="true" />
									<display:column title="�����" style="text-align:center"
										property="panflagno" headerClass="sortable" sortable="true" />
									<display:column title="����" style="text-align:center"
										property="batchflag" headerClass="sortable" sortable="true" />
									<display:column title="�鿴" style="width:5%;text-align:center">
										<c:if test="${displayTable.issucess=='01'}">
											<a href="javascript:;"
												onclick="preQueryCrdformatMap('<c:out value="${displayTable.batchflag}"/>','<c:out value="${displayTable.panflagno}"/>')">�鿴</a>
										</c:if>
										<c:if test="${displayTable.issucess!='01'}">
											<a href="javascript:;"
												onclick="addpaninfo('<c:out value="${displayTable.batchflag}"/>','<c:out value="${displayTable.panflagno}"/>')">����</a>
										</c:if>
									</display:column>
								</display:table>
								<input type="hidden" name="id">
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="center" valign="top" height="30">
					<table border="0" cellpadding="0" cellspacing="0" width="98%">
						<tr align="right">
							<td height="25">
								<logic:equal name="queryflag" value="0">
									<util:page uri="/snCCard.do?method=getBFlowLogList" />
								</logic:equal>
								<logic:equal name="queryflag" value="1">
									<util:page uri="/snCCard.do?method=queryBFlowLogList" />
								</logic:equal>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>

</html:html>
