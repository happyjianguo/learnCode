<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%String path = request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>��ʾ�б�ҳ��</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/new_look.css" />
</head>
<script src="<%=path%>/js/sorttable.js"></script>
<script src="<%=path%>/js/eposcc.js"></script>
<script language="javascript">
	function addConfirm(id)
	{
		var url =  "<%=path%>/iidPinChk.do?method=preAddIidPinChk&random=" + Math.random();
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
	
	function query(){
		var iidPinChkForm = document.all("iidPinChkForm");
		iidPinChkForm.action = "<%=path%>/iidPinChk.do?method=getIidPinChkList";
		iidPinChkForm.submit();
	}
		
	function deleteClick(iid){
		if(confirm("ȷ��Ҫɾ�����Ϊ"+iid+"�ķ�������ӳ�䣿")){
			var iidPinChkForm = document.all("iidPinChkForm");
			iidPinChkForm.action = "<%=path%>/iidPinChk.do?method=delIidPinChk&iid="+iid;
			iidPinChkForm.submit();		
		}
	}
</script>

<body>
	<html:form action="/iidPinChk?method=getIidPinChkList" method="post"
		styleId="iidPinChkForm">
		<bean:define id="menu_level" name="menu_level" />
		<table border="0" cellpadding="0" cellspacing="0" width="100%"
			height="100%">
			<tr>
				<td align="center" valign="top" height="87%">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="28" height="10"></td>
						</tr>
						<tr>
							<td align="left" width="28" height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">

							</td>
							<td height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;��ǰλ�ã��ƿ����� &gt; �����������
							</td>
							<td width="7" height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td width="28" height="5" colspan="3"></td>
						</tr>
					</table>
					<table cellpadding="0" border="0" cellspacing="0" width="100%" style="padding: 0px;" align="left">
						<tr>
							<td coslpan="7"><font color="red">${info}</font></td>
						</tr>
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								��BIN
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="queryiid" maxlength="11" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
								���������ʶ
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="ivrpinfalg">
									<html:option value="">����ѡ��</html:option>
									<html:option value="1">1-��</html:option>
									<%-- <html:option value="0">0-��</html:option> --%>
								</html:select>
							</td>
							<td style="white-space: nowrap" align="right">
								���������ʶ
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="pospinfalg" >
									<html:option value="">����ѡ��</html:option>
									<html:option value="1">1-��</html:option>
									<html:option value="0">0-��</html:option>
								</html:select>
							</td>
							<td style="white-space: nowrap" align="left">
								<input type="button" class="button" onClick='query();'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)">
								<input type="button" class="button"
									onClick='return addConfirm()'
									style="background-image: url(<%=path%>/image1/border/New_button.gif)">
							</td>
						</tr>
						<tr>
						<td colspan="10">
							<display:table name="iidPinChkList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/iidPinChk.do">
								<display:column title="��BIN" style="text-align:center" property="iid" headerClass="sortable" sortable="true" />
								<display:column title="���������ʶ" style="text-align:center"  headerClass="sortable" >
									<c:if test="${displayTable.ivrpinfalg=='1' }">1-�� </c:if>
									<c:if test="${displayTable.ivrpinfalg=='0' }">0-�� </c:if>
								</display:column>
								<display:column title="���������ʶ" style="text-align:center"  headerClass="sortable" >
									<c:if test="${displayTable.pospinfalg=='1' }">1-�� </c:if>
									<c:if test="${displayTable.pospinfalg=='0' }">0-�� </c:if>
								</display:column>								
								<display:column title="���ʱ��" style="text-align:center"  property="adddatetime" headerClass="sortable" sortable="true"/>
								<display:column title="����Ա" style="text-align:center"  property="oper" headerClass="sortable"/>
								<%-- <display:column title="����" style="width:5%;text-align:center">
									<a href="javascript:;" onclick="deleteClick('<c:out value="${displayTable.iid}"/>')">ɾ��</a>
								</display:column>	 --%>													
							</display:table>
							<!-- <input type="hidden" name="id"> -->
						</td>
						</tr>
					</table>
			</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
