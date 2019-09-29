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
		var url =  "<%=path%>/crdformatMap.do?method=preAddCrdformatMap&random=" + Math.random();
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
		var crdformatMapForm = document.all("crdformatMapForm");
		crdformatMapForm.action = "<%=path%>/crdformatMap.do?method=queryCrdformatMapList";
		crdformatMapForm.submit();
	}

	function preQueryCrdformatMap(id,funcFlag)
	{
		var url =  "<%=path%>/crdformatMap.do?method=preQueryCrdformatMap&id="+id+"&funcFlag="+funcFlag+"&random=" + Math.random();		
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
	
	function preModCrdformatMap(id,funcFlag)
	{
		var url =  "<%=path%>/crdformatMap.do?method=preModCrdformatMap&id="+id+"&funcFlag="+funcFlag+"&random=" + Math.random();
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
	function deleteClick(id){
		if(confirm("ȷ��Ҫɾ�����Ϊ"+id+"�Ŀ�BINӳ�䣿")){
			var crdformatMapForm = document.all("crdformatMapForm");
			crdformatMapForm.action = "<%=path%>/crdformatMap.do?method=delCrdformatMapInfo&id="+id;
			crdformatMapForm.submit();		
		}
	}
</script>

<body>
	<html:form action="/crdformatMap?method=queryCrdformatMapList" method="post"
		styleId="crdformatMapForm">
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
								&nbsp;&nbsp;��ǰλ�ã��������� &gt; ��BINӳ�����
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
								<html:text property="queryIid" maxlength="15" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
								��Ӧ����
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="queryFuncFlag">
									<html:option value="">����ѡ��</html:option>
									<html:option value="0">������������</html:option>
									<html:option value="1">����ͬ�����󶨵����⿨����</html:option>
								</html:select>
							</td>
							<td style="white-space: nowrap" align="right">
								״̬
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="queryIsuse" >
									<html:option value="">����ѡ��</html:option>
									<html:option value="1">����</html:option>
									<html:option value="0">ͣ��</html:option>
								</html:select>
							</td>
							<td style="white-space: nowrap" align="right">
								��Ч���Ƿ����
							</td>							
							<td style="white-space: nowrap" align="left">
								<html:select property="queryNeedDt">
									<html:option value="">����ѡ��</html:option>
									<html:option value="1">����</html:option>
									<html:option value="0">�Ǳ���</html:option>
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
							<display:table name="crdformatMapList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/crdformatMap.do">
								<display:column title="��BIN" style="text-align:center" property="iid" headerClass="sortable" sortable="true" />
								<display:column title="��BIN����" style="text-align:center" property="iid_desc" headerClass="sortable" sortable="true" />
								<display:column title="ӳ�俨BIN" style="text-align:center"  property="iid_map" headerClass="sortable" sortable="true"/>
								<display:column title="ӳ�俨BIN����" style="text-align:center"  property="iid_map_desc" headerClass="sortable" sortable="true"/>
								<display:column title="��Ӧ����" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.func_flag=='0' }">������������ </c:if>
									<c:if test="${displayTable.func_flag=='1' }">����ͬ�����󶨵����⿨���� </c:if>
								</display:column>
								<display:column title="��Ч���Ƿ����" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.needDt=='1' }">���� </c:if>
									<c:if test="${displayTable.needDt=='0' }">�Ǳ��� </c:if>
								</display:column>								
								<display:column title="״̬" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.isuse=='1' }">���� </c:if>
									<c:if test="${displayTable.isuse=='0' }">ͣ�� </c:if>
								</display:column>
								<display:column title="�鿴" style="width:5%;text-align:center">
									<a href="javascript:;" onclick="preQueryCrdformatMap('<c:out value="${displayTable.iid}"/>','<c:out value="${displayTable.func_flag}"/>')">�鿴</a>
								</display:column>								
								<display:column title="�޸�" style="width:5%;text-align:center">
									<a href="/crdformatMap.do?method=preModCrdformatMap&id=${displayTable.iid}&funcFlag=${displayTable.func_flag}">�޸�</a>
								</display:column>	
								<display:column title="ɾ��" style="width:5%;text-align:center">
									<a href="javascript:;" onclick="deleteClick('<c:out value="${displayTable.iidAndFuncFlag}"/>')">ɾ��</a>
								</display:column>														
							</display:table>
							<input type="hidden" name="id">
						</td>
						</tr>
					</table>
			</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
