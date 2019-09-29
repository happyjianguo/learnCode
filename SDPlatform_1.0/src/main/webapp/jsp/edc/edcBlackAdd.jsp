<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>�ն˺�����</title>
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
	<script language="javascript">
	
	function saveClick()
	{
		document.forms[0].method.value="createItem";
		return validateEdcBlackForm(document.forms[0]);		
	}
	
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	
	/*****************************************/
	
	var req;
	function init() {
		if(window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	/***************�̻������֤*****************/
	function checkMerchantId(merchantId) {
	    if(merchantId==null||merchantId==""){
	       alert("�̻���Ų���Ϊ�գ�����");
	    }else{
			init();
			var url="edcCheck.do?method=checkMerchantId&merchantId="+merchantId;
			req.open("POST", url, true);
			req.onreadystatechange = merchantIdCallback;
			req.send(null);
		}
    }
    
	function merchantIdCallback() {
	 if(4 == req.readyState) {
		if(200 == req.status) {
		    if(req.responseText=='false'){
		       alert("�̻���Ų����ڣ����������룡����");
		       document.forms[0].merchantId.value='';
		       document.forms[0].merchantName.value='';
		    }else{
		       document.forms[0].merchantName.value=req.responseText;
		    }
		}
	  }
    }
    
 /*****************�ն˱����֤***********************/
    
    function checkTerminalId() {
       var merchantId=document.forms[0].merchantId.value ;
       var terminalId=document.forms[0].terminalId.value ;
	    if(merchantId==null||merchantId==""){
	       alert("�̻���Ų���Ϊ�գ�����");
	    }else if(terminalId==null||terminalId==""){
	       alert("�ն˱�Ų���Ϊ�գ�����");
	    }else{
			init();
			var url="edcCheck.do?method=checkMerchantTerminalId&merchantId="+merchantId+"&terminalId="+terminalId;
			req.open("POST", url, true);
			req.onreadystatechange = terminalIdCallback;
			req.send(null);
		}
    }
    
	function terminalIdCallback() {
	 if(4 == req.readyState) {
		if(200 == req.status) {
		    if(req.responseText=='false'){
		       alert("�ն˱�Ų����ڣ����������룡����");
		       document.forms[0].terminalId.value='';
		    }
		}
	  }
    }
    
    
	</script>
</head>
<shiro:lacksPermission name="posp:edcblack:add">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:edcblack:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				�����ն˺�����
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="edcBlackForm" />
				<html:errors />
				<html:form action="/edcBlack">

					<html:hidden property="method" value="createItem" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">

						<tr>
							<td class="table2_td_title">
								�̻����:
							</td>
							<td class="table2_td">
								<html:text styleId="merchantId" property="merchantId" size="25" maxlength="15" onblur="checkMerchantId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�̻�����:
							</td>
							<td class="table2_td">
								<html:text styleId="merchantName" property="merchantName" size="25" readonly="true" maxlength="15"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 �ն˺ţ�
							</td>
							<td class="table2_td">
								<html:text styleId="terminalId" property="terminalId" size="25"  maxlength="8" onblur="checkTerminalId(this.value)" onkeyup="this.value=this.value.replace(/\W/g,'')"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�ն�״̬��
							</td>
							<td class="table2_td">
								<html:select property="status">
								 <option value="Y">������</option>
								 <option value="N">������</option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								ԭ��:
							</td>
							<td class="table2_td">
								<html:textarea property="reason" rows="3" /><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��ע:
							</td>
							<td class="table2_td">
								<html:textarea property="remark" rows="3"  />
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