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
		var makeCardForm = document.all("makeCardForm");
		makeCardForm.action = "<%=path%>/makeCard.do?method=queryMakeCardList";
		makeCardForm.submit();
	}	
	function addpaninfo()
	{
		var url =  "<%=path%>/makeCard.do?method=showMakeCardInfo&random=" + Math.random();
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
<shiro:lacksPermission name="housekeep:makeCardList:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="housekeep:makeCardList:view">
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form action="/makeCard.do?method=getMakeCardList" method="post"
		styleId="makeCardForm">
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
								&nbsp;&nbsp;��ǰλ�ã��������� &gt; Ǯ�����ƿ�����
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
								״̬
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property ="queryStat">
									<html:option value="">����ѡ��</html:option>
									<html:option value="00">δ�ƿ�</html:option>
									<html:option value="01">���ƿ�</html:option>
									<html:option value="02">�ƿ�ʧ��</html:option>
								</html:select>
							</td>
							<td style="white-space: nowrap" align="right">
								���ţ�
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="queryPan" maxlength="19" size="20" />
							</td>
							 <shiro:hasPermission name="housekeep:makeCardList:edit">
								<td style="white-space: nowrap" align="left">
									<input type="button" class="button" onClick="addpaninfo();" value="�ƿ�"/>
								</td>
							</shiro:hasPermission>
						</tr>
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								������
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="queryCustOrder" maxlength="40" size="20" />
							</td>
							<td style="white-space: nowrap" align="right">
								���κţ�
							</td>
							<td style="white-space: nowrap" align="le
								<html:text property="queryBatch" maxlength="40" size="20" />
							</td>
							<td style="white-space: nowrap" align="left">
								<input type="button" class="button" onClick="query();" style="background-image: url(<%=path%>/image1/border/Check_button.gif)">
							</td>
						</tr>						
						<tr>
							<td colspan="10">
								<display:table name="makeCardList" partialList="true"
									size="pageBean.totalRecords" pagesize="10" style="width:100%"
									class="dpTable" cellpadding="0" cellspacing="0"
									id="displayTable" requestURI="/makeCard.do">
									<display:column title="����ʱ��" style="text-align:center"
										property="txtime" headerClass="sortable" sortable="true" />
									<display:column title="��ˮ��" style="text-align:center"
										property="stan" headerClass="sortable" sortable="true" />										
									<display:column title="������" style="text-align:center"
										property="cust_order" headerClass="sortable" sortable="true" />										
									<display:column title="����" style="text-align:center"
										property="pan" headerClass="sortable" sortable="true" />	
									<display:column title="����" style="text-align:center"
										property="name" headerClass="sortable" sortable="true" />
									<display:column title="���κ�" style="text-align:center"
										property="batch" headerClass="sortable" sortable="true" />
									<display:column title="ԭ���κ�" style="text-align:center"
										property="org_batch" headerClass="sortable" sortable="true" />
									<display:column title="�ƿ��ļ�·��" style="text-align:center"
										property="reserved1" headerClass="sortable" sortable="true" />										
									<display:column title="��ע" style="text-align:center"
										property="reserved3" headerClass="sortable" sortable="true" />
									<display:column title="����Ч��" style="text-align:center"
										property="crd_expdate" headerClass="sortable" sortable="true" />													
									<display:column title="״̬" style="text-align:center"
										headerClass="sortable" sortable="true">
										<c:if test="${displayTable.stat=='00' }">δ�ƿ�</c:if>
										<c:if test="${displayTable.stat=='01' }">���ƿ� </c:if>
										<c:if test="${displayTable.stat=='02' }">�ƿ�ʧ�� </c:if>
									</display:column>
								</display:table>
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
									<util:page uri="/makeCard.do?method=getMakeCardList" />
								</logic:equal>
								<logic:equal name="queryflag" value="1">
									<util:page uri="/makeCard.do?method=queryMakeCardList" />
								</logic:equal>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</shiro:hasPermission>
</html:html>
