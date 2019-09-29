<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*,cn.yufu.posp.terminalmanager.domain.model.*"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>�̻����ײ���</title>
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
		var nopasswdMaxamt = document.forms[0].nopasswdMaxamt.value.trim();
		var waveFlag = document.forms[0].waveFlag.value.trim();
		var swipeFlag = document.forms[0].swipeFlag.value.trim();
		var scanFlag = document.forms[0].scanFlag.value.trim();
		if(0 == waveFlag && 0 == swipeFlag && 0 == scanFlag){
			alert("�ӿ�ˢ��ɨ�����֧��һ��");
			return false;
		}
		if(nopasswdMaxamt!=null&&nopasswdMaxamt!=""){ 
			if(nopasswdMaxamt.indexOf(".") != -1&&nopasswdMaxamt.split(".")[1].length>2){
				alert("���������޶��ʽ����");
				document.forms[0].nopasswdMaxamt.value='';
				return false;
			}
			if(nopasswdMaxamt.indexOf(".") == -1&&nopasswdMaxamt.length>13){
  				alert("���������޶��ʽ����");
  				document.forms[0].nopasswdMaxamt.value='';
  				return false;
  			}
			if(nopasswdMaxamt>5000){
  				alert("���������޶�5000Ԫ");
  				document.forms[0].nopasswdMaxamt.value='';
  				return false;
  			}
		}
		document.forms[0].method.value="createItem";
		return validateTblMerchantTranParamForm(document.forms[0]);
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
	var flag="0";	//���Ʊ�ʶ
	function checkMerchantId(merchantId) {
	    if(merchantId==null||merchantId==""){
	       alert("�̻���Ų���Ϊ�գ�����");
	       return false;
	    }else{
			init();
			var url="tblMerchantTranParam.do?method=checkMerchantId&merchantId="+merchantId;
			req.open("POST", url, false);
			req.onreadystatechange = merchantIdCallback;
			req.send(null);
			if("1"==flag){
				init();
				var url="edcCheck.do?method=checkMerchantId&merchantId="+merchantId;
				req.open("POST", url, true);
				req.onreadystatechange = checkMerchantNameCallback;
				req.send(null);
			}

		}
    }
    
	function merchantIdCallback() {
	 if(4 == req.readyState) {
		if(200 == req.status) {
		    if(req.responseText=='2'){
		       alert("���̻�������̻����в����ڣ����������룡����");
		       document.forms[0].merchantId.value='';
		       document.forms[0].merchantName.value='';
		       flag="0";
		    }else if(req.responseText=='3'){
			       alert("���̻�������̻����ײ��������Ѵ��ڣ����������룡����");
			       document.forms[0].merchantId.value='';
			       document.forms[0].merchantName.value='';
			       flag="0";
			}else{
				flag="1";
			}
	  	 }
    }
  } 
	

	/***************�̻�����������֤*****************/
	   function checkMerchantNameCallback() {
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
	 
	
</script>
</head>
<shiro:lacksPermission name="posp:tblMerchantTranParam:add">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:tblMerchantTranParam:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle"> 
				�����̻����ײ���
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="tblMerchantTranParamForm" />
				<html:errors />
				<html:form action="/tblMerchantTranParam">
	
					<html:hidden property="method" value="createItem" />
				
					<%-- <html:hidden property="waveFlag"/>
					<html:hidden property="swipeFlag"/> --%>
					<html:hidden property="tranBitmap"/>
					
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								�̻����:
							</td>
							<td class="table2_td">
								<html:text property="merchantId" size="15" maxlength="15" onblur="checkMerchantId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�̻���������:
							</td>
							<td class="table2_td">
								<html:text property="merchantName" size="15" maxlength="15"  readonly="true"/>
								<font color="red">*</font>
							</td>
						</tr>

						<tr>
							<td class="table2_td_title">
								���������޶�:
							</td>
							<td class="table2_td">
								<html:text property="nopasswdMaxamt" size="15" maxlength="7" onkeyup="this.value=this.value.replace(/[^\d\.]/g,'');"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								֧�ֻӿ�����:
							</td>
							<td class="table2_td">
								<html:select property="waveFlag">
								<html:option value="1">1-֧��</html:option>
								<html:option value="0">0-��֧��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								֧��ˢ������:
							</td>
							<td class="table2_td">
								<html:select property="swipeFlag">
								<html:option value="1">1-֧��</html:option>
								<html:option value="0">0-��֧��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								ɨ���ʶ:
							</td>
							<td class="table2_td">
								<html:select property="scanFlag">
								<html:option value="1">1-֧��</html:option>
								<html:option value="0">0-��֧��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��¼��Ч��ʶ:
							</td>
							<td class="table2_td">
								<html:select property="flag">
								<html:option value="1">1-��Ч</html:option>
								<html:option value="0">0-��Ч</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="6" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()">
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()">
							</td>
						</tr>
					</table>
					
				</html:form>

			</td>
		</tr>
	</table>



</body>
</shiro:hasPermission>
</html:html>


