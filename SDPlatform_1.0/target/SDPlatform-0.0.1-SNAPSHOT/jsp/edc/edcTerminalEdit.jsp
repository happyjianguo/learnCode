<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>�ն������趨</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />DatePicker/WdatePicker.js" ></script>
	<script language="javascript">
	
	function saveClick()
	{
		var terminalId=document.forms[0].terminalId.value;
		var edcType=document.forms[0].edcType.value;
		var str = /^[0-9]{8}$/;
		var softVer=document.forms[0].softVer.value;
		var busRoleId=document.forms[0].busRoleId.value;
		if((edcType.indexOf("STD")!=0)&&(str.test(terminalId))){
			alert("�ն˺�8λȫΪ���ֵ��ն��豸�ͺ�ѡ��STD������");
			return false;
		}

		if((terminalId.indexOf("A")==0)&&(edcType.indexOf("APP")!=0)){
			alert("�ն˺�A��ͷ���ն��豸�ͺű���ѡ��APP������");
			return false;
		}
		
		
		if(softVer=="mul"&&(busRoleId==""||busRoleId==null)){
  			alert("��ѡ��ҵ���ɫ������");
			return false;
  		}
		
		document.forms[0].method.value="saveEdcTerminal";
		return validateEdcTerminalForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryEdcTerminal";
	}
	
	/*****************************************/
	//ɾ������ո�
	String.prototype.RTrim   =   function(){   
		return   this.replace(/(\s*$)/g,"");   
	}
	
	var req;
	function init() {
		if(window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	function findBusRoleList()
	{
		var softVer=document.forms[0].softVer.value; 
	  	if(softVer=="mul"){
	  		document.getElementsByName("busRoleId")[0].style.display = "block";
	  		init();
 			var url="edcTerminal.do?method=findBusRoleList";
 			req.open("POST", url, true);
 			req.onreadystatechange = findBusRoleListCallback;
 			req.send(null);
	  	}else{
	  		document.getElementsByName("busRoleId")[0].style.display = "none";
	  		document.getElementById("busRoleIdButton").style.display = "none";
	  	}
	}
	
	function findBusRoleListCallback() {
		if(4 == req.readyState) {
			if(200 == req.status) {
				if(req.responseText=='false'){
					alert("��ȷ��ҵ���ɫ�Ƿ���ڣ�");
				}else{
					document.forms[0].busRoleId.options.length = 0;
					var str = req.responseText;
					var areaList = str.split("|");
					if (areaList.length > 1) {
						document.forms[0].busRoleId.options.add(new Option("--��ѡ��ҵ���ɫ--", ""));
						for (var i = 0; i < areaList.length-1;i=i+2) {
							document.forms[0].busRoleId.options.add(new Option(areaList[i].RTrim()+"--"+areaList[i+1].RTrim(),areaList[i+1].RTrim()));
						}
					}
					
				}			
			}
		}
	}
	</script>
</head>
<shiro:lacksPermission name="posp:terminal:edit">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:terminal:edit">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				�޸��ն�����
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="edcTerminalForm" />
				<html:errors />
				<html:form action="/edcTerminal">

					<html:hidden property="method" value="saveEdcTerminal" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">

						<tr>
							<td class="table2_td_title">
								�̻����:
							</td>
							<td class="table2_td">
								<html:text property="id.merchantId" size="25" disabled="true" maxlength="15"/>
								<font color="red">*</font>
								<html:hidden property="id.merchantId"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�̻�����:
							</td>
							<td class="table2_td">
								<html:text property="id.merchanName" size="25" disabled="true" maxlength="15"/>
								<font color="red">*</font>
								<html:hidden property="id.merchanName" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 �ն˺ţ�
							</td>
							<td class="table2_td">
								<html:text styleId="terminalId"  property="id.terminalId" size="25" disabled="true"  maxlength="8"/>
								<font color="red">*</font>
								<html:hidden property="id.terminalId" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�ն�״̬��
							</td>
							<td class="table2_td">
								<html:select property="terminalStat">
								 <html:option value="Y">����</html:option>
								 <html:option value="N">����</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�ն��豸�ͺţ�
							</td>
							<td class="table2_td">
								<html:select property="edcType">
								  <html:option value=""></html:option>
								  <html:option value="STD">STD</html:option>
							      <html:option value="APP">APP</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�豸���˵����
							</td>
							<td class="table2_td">
								<html:text property="edcDoc" size="25"  maxlength="30"/>
								
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��ӡ�����ͣ�
							</td>
							<td class="table2_td">
								<html:text property="printerType" size="25"  maxlength="4"/>
								
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								PIN PAD���ͣ�
							</td>
							<td class="table2_td">
								<html:text property="pinpadType" size="25"  maxlength="4"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�ն����ͣ�
							</td>
							<td class="table2_td">
								<html:select property="softVer" onchange="findBusRoleList()"> 
								  <c:if test="${edcTerminalForm.softVer eq 'mul'}">
								  	<html:option value="mul">�ೡ���ն�</html:option>
								  	<html:option value="common">��ͨ�ն�</html:option> 
							      </c:if>  
								  <c:if test="${!(edcTerminalForm.softVer eq 'mul')}">
								  	<html:option value="mul">�ೡ���ն�</html:option>
							      	<html:option value="${(edcTerminalForm.softVer)}">��ͨ�ն�</html:option> 
							      </c:if>  
							      
								</html:select>
								<font color="red">*</font>
								<c:choose>
								 	<c:when test="${edcTerminalForm.softVer eq 'mul'}">
								 	<input id=busRoleIdButton type="button" value="����"  style="display: block;" onclick="findBusRoleList();">
									<html:select property="busRoleId" style="width:220px;display: block;">
									<html:option value="${edcTerminalForm.busRoleId}">${edcTerminalForm.busRoleName}--${edcTerminalForm.busRoleId}</html:option>
									</html:select>
									</c:when>
								   <c:otherwise>
								   <input id=busRoleIdButton type="button" value="����" style="display: none;" onclick="findBusRoleList();">
									<html:select property="busRoleId" style="width:220px;display: none;">

									</html:select>
								  </c:otherwise>   
								</c:choose>	
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�������ر�־��
							</td>
							<td class="table2_td">
								<html:select property="downloadFlag">
								 <html:option value="Y">��Ҫ��װ</html:option>
								 <html:option value="N">���谲װ</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��������ģʽ��
							</td>
							<td class="table2_td">${edcTerminalForm.downloadMode}-
								<c:if test="${edcTerminalForm.downloadMode eq 0}">��</c:if>
								<c:if test="${edcTerminalForm.downloadMode eq 4}">���¹�Կ</c:if>
								<c:if test="${edcTerminalForm.downloadMode eq 5}">����IC������</c:if>
								<html:hidden property="downloadMode" value="${edcTerminalForm.downloadMode}"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��װ���ڣ�
							</td>
							<td class="table2_td">
								<html:text property="setDate" size="25" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" styleClass="Wdate" />
								<font color="red">*</font>							
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��װ�ص㣺
							</td>
							<td class="table2_td">
								<html:text property="setAddr" size="25"  maxlength="30"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()">
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()">
							</td>
						</tr>
					</table>
					<!-- ά����ͼ״̬�������� -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<input type="hidden" name="search_name" value="<c:out value="${param.search_name}"/>" />
					<!-- ά����ͼ״̬�������� -->
					</html:form>
			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>



