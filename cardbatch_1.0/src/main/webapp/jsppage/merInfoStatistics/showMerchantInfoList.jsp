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

	<title>�̻���Ϣͳ��-�̻���Ϣ�б�</title>

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
	function addConfirm(){
		var url = "<%=path%>/merchantInfo.do?method=preAddMerchantInfo&random=" + Math.random();
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

	function query(){
		var merchantInfoForm = document.all("merchantInfoForm");
		merchantInfoForm.action = "<%=path%>/merchantInfo.do?method=getMerchantInfoList";
		merchantInfoForm.submit();
	}	
	
	function exportExcelOfMerchantInfo(){
		var merchantInfoForm = document.all("merchantInfoForm");
		merchantInfoForm.action = "<%=path%>/merchantInfo.do?method=exportExcelOfMerchantInfo";
		merchantInfoForm.submit();
	}
	
	function preQueryMerchant(id)
	{
		var url = "<%=path%>/merchantInfo.do?method=preQueryMerchantInfo&id="+id+"&random=" + Math.random();
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
	
	function preModMerchant(id)
	{
		var url = "<%=path%>/merchantInfo.do?method=preModMerchantInfo&id="+id+"&random=" + Math.random();
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
	
	function preModTerminalInfos(id){
		var url = "<%=path%>/merchantInfo.do?method=preModTerminalInfos&id="+id+"&random=" + Math.random();
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
	
	function downloadTemplate(){
		var merchantInfoForm = document.all("merchantInfoForm");
		merchantInfoForm.action = "<%=path%>/merchantInfo.do?method=downloadTemplate";
		merchantInfoForm.submit();
	}
	
	function fileUpload(){
		var fileName = document.all("formShowData").upload.value;
		 if(!fileName){
			 alert("��ѡ��Ҫ������ļ�!");
	         return false;
		 }
	     var strRegex = "(.xlsx|.xls)$";
	     var re = new RegExp(strRegex);
	     if (!re.test(fileName.toLowerCase())){
	         alert("�ļ���ʽ���Ϸ�������ΪEXCEL�ĵ�!");
	         return false;
	     }
		 var formShowData = document.all("formShowData");
		 formShowData.action = "<%=path%>/merchantInfoUpload.do?method=fileUpload";
		 formShowData.submit();
	}
</script>
<shiro:lacksPermission name="cardbatch:merchantInfo:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="cardbatch:merchantInfo:view">
<body>
	<html:form action="/merchantInfo.do?method=getMerchantInfoList" method="post" styleId="merchantInfoForm">
		<bean:define id="menu_level" name="menu_level" />
		<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
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
								&nbsp;&nbsp;��ǰλ�ã��̻���Ϣͳ�� &gt; �̻���Ϣ����
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
							<td coslpan="9"><font color="red">${info}</font></td>
						</tr>
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								�̻����
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="qid" maxlength="15" size="16" onkeyup="this.value=this.value.replace(/\D/g,'')" />
							</td>
							<td style="white-space: nowrap" align="right">
								�̻�����
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="qname" maxlength="15" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
								�̻���˾����
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="qfullname" maxlength="15" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
							</td>
							<td style="white-space: nowrap" align="left">
							</td>
						</tr>
						
						<tr class="serch">	
							<td style="white-space: nowrap" align="right">
								�̻�����
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="qtype">
									<html:option value="">����ѡ��</html:option>								
									<logic:present name="cardbatch_mer_typeList">
										<html:optionsCollection name="cardbatch_mer_typeList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>	
							</td>
							<td style="white-space: nowrap" align="right">
								�̻�״̬
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="qstatus">
									<html:option value="">����ѡ��</html:option>
									<html:option value="0">����</html:option>
									<html:option value="1">������</html:option>
									<html:option value="2">������</html:option>
								</html:select>
							</td>
							<td style="white-space: nowrap" align="right">
								����
							</td>
							<td style="white-space: nowrap" align="left">
								<logic:present name="provinList">
									<html:select property="qprovince" onchange="getCity()">
										<html:option value="">����ѡ��</html:option>
										<html:optionsCollection name="provinList"
											label="province_city" value="aid" />
									</html:select>
								</logic:present>
								<html:select property="qcity" onchange="getArea()">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="city_noList">
										<html:optionsCollection name="city_noList"
											label="province_city" value="aid" />
									</logic:present>
								</html:select>
								<html:select property="qarea">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="areaList">
										<html:optionsCollection name="areaList"
											label="province_city" value="aid" />
									</logic:present>
								</html:select>
							</td>
						
							<td style="white-space: nowrap" align="right">
								<input type="button" class="button" onClick='return query()'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)">
							</td>
							
								<td height="25" align="left">
									<a href="javascript:;" onClick='return exportExcelOfMerchantInfo()'>
										<img alt="" src="<%=path%>/images/icon-import.png">
									</a>
									<shiro:hasPermission name="cardbatch:merchantInfo:add">	
										<input type="button" class="button"
											onClick='return addConfirm()'
											style="background-image: url(<%=path%>/image1/border/New_button.gif)">
									</shiro:hasPermission>
								</td>
						
						</tr>
						<tr>
						<td colspan="9">
							<display:table name="merchantInfoList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/merchantInfo.do">
								<display:column title="�̻����" style="text-align:center" property="id" headerClass="sortable" sortable="true" />
								<display:column title="�̻�����" style="text-align:center" property="name" headerClass="sortable" sortable="true" />
								<display:column title="�̻���˾����" style="text-align:center" property="fullname" headerClass="sortable" sortable="true"/>
								<display:column title="�̻�����" style="text-align:center" headerClass="sortable" sortable="true">
									<logic:present name="cardbatch_mer_typeList">
										<html:select property="" value="${displayTable.type }" disabled="true">
											<html:optionsCollection name="cardbatch_mer_typeList"
												label="param_name" value="param_value" />
										</html:select>
									</logic:present>
								</display:column>
								<display:column title="�̻�״̬" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.status=='0' }">���� </c:if>
									<c:if test="${displayTable.status=='1' }">������ </c:if>
									<c:if test="${displayTable.status=='2' }">������</c:if>
								</display:column>
								
								<display:column title="�鿴" style="text-align:center">
								<a onclick="preQueryMerchant('<c:out value="${displayTable.id}"/>');" >
									<img border="0" src="<%=path%>/image1/border/query.png" /></a>
								</display:column>
								<shiro:hasPermission name="cardbatch:merchantInfo:edit">
									<display:column title="�޸�" style="text-align:center">
										<a href="/merchantInfo.do?method=preModMerchantInfo&id=${displayTable.id}">
										�޸�</a>
									</display:column>	
									<display:column title="�����޸��ն���Ϣ" style="text-align:center">
										<a href="/merchantInfo.do?method=preModTerminalInfos&id=${displayTable.id}">�����޸��ն���Ϣ</a>
									</display:column>
								</shiro:hasPermission>						
							</display:table>
							<input type="hidden" name="id">
						</td>
						</tr>
					</table>
			</td>
			</tr>
		</table>
	</html:form>
	<shiro:hasPermission name="cardbatch:merchantInfo:add">	
		<center>			
			<form id='formShowData' name="formShowData" enctype='multipart/form-data' method='post' action="/merchantInfoUpload.do" >
				<input type="hidden" name="method" value="fileUpload" />
				<input type="file" name='upload' id='upload' /><input type="button" value="����" onclick="fileUpload();" />
				<a href="javascript:;" onclick="downloadTemplate();">���ص���ģ��</a>
			</form>
		</center>
	</shiro:hasPermission>
</body>
</shiro:hasPermission>
</html:html>
