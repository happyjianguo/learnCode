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

	<title>�̻���Ϣͳ��-�ն���Ϣ�б�</title>

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
<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
	function addConfirm(){
		var url = "<%=path%>/terminalInfo.do?method=preAddTerminalInfo&random=" + Math.random();
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
		var addDT_startdate = document.getElementsByName("qinstalldate_startdate")[0].value;
		var addDT_enddate = document.getElementsByName("qinstalldate_enddate")[0].value;
		if(addDT_startdate!='' && addDT_enddate!='' && (addDT_startdate-addDT_enddate)>0){
		    alert("��װ���ڿ�ʼʱ�䲻�ܴ��ڽ���ʱ�䡣");
		    window.document.forms['merchantForm'].elements['qinstalldate_startdate'].focus() ;
		    return false;
		}
		
		var terminalInfoForm = document.all("terminalInfoForm");
		terminalInfoForm.action = "<%=path%>/terminalInfo.do?method=getTerminalInfoList";
		terminalInfoForm.submit();
	}	
	
	function preModTerminal(id)
	{
		var url = "<%=path%>/terminal.do?method=preModTerminal&id="+id+"&random=" + Math.random();
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
	function preQueryTerminal(id)
	{
		var url = "<%=path%>/terminal.do?method=preQueryTerminal&id="+id+"&random=" + Math.random();
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
			
	function preModTerminal(id)
	{
		var url = "<%=path%>/terminalInfo.do?method=preModTerminalInfo&id="+id+"&random=" + Math.random();
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
	function preQueryTerminal(id)
	{
		var url = "<%=path%>/terminalInfo.do?method=preQueryTerminalInfo&id="+id+"&random=" + Math.random();
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
	
	function exportExcelOfTerminalInfo(){
		var addDT_startdate = document.getElementsByName("qinstalldate_startdate")[0].value;
		var addDT_enddate = document.getElementsByName("qinstalldate_enddate")[0].value;
		if(addDT_startdate!='' && addDT_enddate!='' && (addDT_startdate-addDT_enddate)>0){
		    alert("��װ���ڿ�ʼʱ�䲻�ܴ��ڽ���ʱ�䡣");
		    window.document.forms['merchantForm'].elements['qinstalldate_startdate'].focus() ;
		    return false;
		} 
		var terminalInfoForm = document.all("terminalInfoForm");
		terminalInfoForm.action = "<%=path%>/terminalInfo.do?method=exportExcelOfTerminalInfo";
		terminalInfoForm.submit();
	}
	function downloadTemplate(){
		var terminalInfoForm = document.all("terminalInfoForm");
		terminalInfoForm.action = "<%=path%>/terminalInfoUpload.do?method=downloadTemplate";
		terminalInfoForm.submit();
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
		 formShowData.action = "<%=path%>/terminalInfoUpload.do?method=fileUpload";
		 formShowData.submit();
	}
	
</script>
<shiro:lacksPermission name="cardbatch:terminalInfo:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="cardbatch:terminalInfo:view">

<body>
	<html:form action="/terminalInfo.do?method=getTerminalInfoList" method="post" styleId="terminalInfoForm">
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
								&nbsp;&nbsp;��ǰλ�ã��̻���Ϣͳ�� &gt; �ն���Ϣ����
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
								�ն˱��
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="qid" maxlength="15" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
								�����̻����
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="qmerchantid" maxlength="15" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
								�����̻�����
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="qmerchantname" maxlength="15" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
							</td>
							<td style="white-space: nowrap" align="left">
							</td>
						</tr>
						
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								POS�ͺ�
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="qmodel">
									<html:option value="">����ѡ��</html:option>								
									<logic:present name="pos_modelList">
										<html:optionsCollection name="pos_modelList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
							<td style="white-space: nowrap" align="right">
								POS����
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="qtype">
									<html:option value="">����ѡ��</html:option>								
									<logic:present name="pos_typeList">
										<html:optionsCollection name="pos_typeList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
							<td style="white-space: nowrap" align="right">
								����POS�ֻ���
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="qmobilenumber" maxlength="15" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
								POS��S/N��
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="qsnnumber" maxlength="15" size="16" />
							</td>
						</tr>
						
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								��װ����
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="qinstalldate_startdate" maxlength="10" size="10"
									onclick="WdatePicker({dateFmt:'yyyyMMdd'});" />
								-
								<html:text property="qinstalldate_enddate" maxlength="10" size="10"
									onclick="WdatePicker({dateFmt:'yyyyMMdd'});" />
							</td>
							<td style="white-space: nowrap" align="right">
								POS��״̬
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
								<input type="button" class="button" onClick='return query()'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)">
							</td>
							
								<td height="25" align="left">
									<a href="javascript:;" onClick='return exportExcelOfTerminalInfo()'>
										<img alt="" src="<%=path%>/images/icon-import.png">
									</a>
									<shiro:hasPermission name="cardbatch:terminalInfo:add">	
										<input type="button" class="button"
											onClick='return addConfirm()'
											style="background-image: url(<%=path%>/image1/border/New_button.gif)">
									</shiro:hasPermission>
								</td>
							
							<td style="white-space: nowrap" align="right">
							</td>
							<td style="white-space: nowrap" align="left">
							</td>
						</tr>
						
						<tr>
						<td colspan="9">
							<display:table name="terminalInfoList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/terminalInfo.do">
								<display:column title="�ն˱��" style="text-align:center" property="id" headerClass="sortable" sortable="true" />
								<display:column title="�����̻�" style="text-align:center" property="merchantid" headerClass="sortable" sortable="true" />
								
								<display:column title="POS�ͺ�" style="text-align:center" headerClass="sortable" sortable="true">
									<logic:present name="pos_modelList">
										<html:select property="" value="${displayTable.model }" disabled="true">
											<html:optionsCollection name="pos_modelList"
												label="param_name" value="param_value" />
										</html:select>
									</logic:present>
								</display:column>
								<display:column title="POS����" style="text-align:center" headerClass="sortable" sortable="true">
									<logic:present name="pos_typeList">
										<html:select property="" value="${displayTable.type }" disabled="true">
											<html:optionsCollection name="pos_typeList"
												label="param_name" value="param_value" />
										</html:select>
									</logic:present>
								</display:column>
								
								<display:column title="����POS�ֻ���" style="text-align:center" property="mobilenumber" headerClass="sortable" sortable="true" />
								<display:column title="POS��S/N��" style="text-align:center" property="snnumber" headerClass="sortable" sortable="true" />
								<display:column title="��װ����" style="text-align:center" property="installdate" headerClass="sortable" sortable="true" />
								<display:column title="��ϵ��" style="text-align:center" property="name" headerClass="sortable" sortable="true" />
								<display:column title="�ŵ�绰" style="text-align:center" property="phonenumber" headerClass="sortable" sortable="true" />
																
								<display:column title="POS��״̬" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.status=='0' }">���� </c:if>
									<c:if test="${displayTable.status=='1' }">������ </c:if>
									<c:if test="${displayTable.status=='2' }">������</c:if>
								</display:column>
								
								<display:column title="POSѺ��Ԫ��" style="text-align:center" property="deposite" headerClass="sortable" sortable="true" />
								
								<display:column title="�鿴" style="text-align:center">
									<a href="javascript:;" onclick="preQueryTerminal('<c:out value="${displayTable.id}"/>')">
									<img border="0" src="<%=path%>/image1/border/query.png" /></a>
								</display:column>
								<shiro:hasPermission name="cardbatch:terminalInfo:edit">
									<display:column title="�޸�" style="text-align:center">
									<a href="/terminalInfo.do?method=preModTerminalInfo&id=${displayTable.id }">
										�޸�</a>
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
	<shiro:hasPermission name="cardbatch:terminalInfo:add">	
		<center>			
			<form id='formShowData' name="formShowData" enctype='multipart/form-data' method='post' action="/terminalInfoUpload.do" >
				<input type="hidden" name="method" value="fileUpload" />
				<input type="file" name='upload' id='upload' /><input type="button" value="����" onclick="fileUpload();" />
				<a href="javascript:void(0);" onclick="downloadTemplate();">���ص���ģ��</a>
			</form>
		</center>
	</shiro:hasPermission>
</body>
</shiro:hasPermission>
</html:html>
