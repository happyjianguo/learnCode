<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>ר�����ն˹���</title>
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
	
	function saveClick(){
		var keyIndex=document.forms[0].keyIndex.value;
		if(keyIndex==null||keyIndex==""){
	       alert("��Կ��������Ϊ�գ�����");
	       document.forms[0].keyIndex.focus();  
	       return false;
	    }
		
		document.forms[0].method.value = "createEdcAlipayWeChat";
		return validateEdcZskterminalOrmForm(document.forms[0]);		
	}
	
	function backClick(){
		document.forms[0].method.value = "queryEdcZskterminalOrm";
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
	       document.forms[0].flag.focus();
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
		       document.forms[0].merchanName.value='';
		       document.forms[0].flag.focus();
		       return;
		    }/* else{
		       document.forms[0].merchanName.value=req.responseText;
		    } */
		    var merchantId = document.forms[0].merchantId.value;
			var terminalId = document.forms[0].terminalId.value;
			var moduleId = document.forms[0].moduleId.value;
			if(!Boolean(merchantId) || !Boolean(terminalId) || !Boolean(moduleId)){
				return;
			} else {
				checkMerTerMod();
			}
		}
	  }
    }
    
    /*****************�ն˱����֤***********************/
    function checkTerminalId(terminalId) {
	    if(terminalId==null||terminalId==""){
	       alert("�ն˱�Ų���Ϊ�գ�����");
	       document.forms[0].flag.focus();
	    }else{
			init();
			var url="edcCheck.do?method=checkTerminalId&terminalId="+terminalId;
			req.open("POST", url, true);
			req.onreadystatechange = terminalIdCallback;
			req.send(null);
		}
    }
    
	function terminalIdCallback() {
		if(4 == req.readyState) {
			if(200 == req.status) {
			    if(req.responseText!='false'){
			       alert("�ն˱�Ų����ڣ����������룡����");
			       document.forms[0].terminalId.value='';
			       document.forms[0].flag.focus();
			       return;
			    }
			    var merchantId = document.forms[0].merchantId.value;
				var terminalId = document.forms[0].terminalId.value;
				var moduleId = document.forms[0].moduleId.value;
				if(!Boolean(merchantId) || !Boolean(terminalId) || !Boolean(moduleId)){
					return;
				} else {
					checkMerTerMod();
				}
			}
		}
    }
  
	
	/*****************��֤�̻���š��ն˺� ��ģ��ID���ߵ�Ψһ��***********************/ 			
	function checkMerTerMod() {	
		var merchantId = document.forms[0].merchantId.value;
		var terminalId = document.forms[0].terminalId.value;
		var moduleId = document.forms[0].moduleId.value;
		if(!Boolean(merchantId) || !Boolean(terminalId) || !Boolean(moduleId)){
			return;
		}
		init();
		var url="edcZskterminalOrm.do?method=checkMerTerMod&merchantId="+merchantId+"&terminalId="+terminalId+"&moduleId="+moduleId;
		req.open("POST", url, true);
		req.onreadystatechange = MerTerModCallback;
		req.send(null);
	}
	
    function MerTerModCallback() {
		if(4 == req.readyState) {
			if(200 == req.status) {
			    if(req.responseText=='false'){
			       alert("�̻���,�ն˺� ��ģ��ID��һ�������Ѵ���, ���������룡����");
			       document.forms[0].merchantId.value="";
			       document.forms[0].terminalId.value="";
			       document.forms[0].flag.focus();
			    }
			}	
	 	}
    }
    
	</script>
</head>
<shiro:lacksPermission name="posp:edczskterminalorm:add">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:edczskterminalorm:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				ר�����ն˹����б�
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="edcZskterminalOrmForm" />
				<html:errors />
				<html:form action="/edcZskterminalOrm">

					<html:hidden property="method" value="createEdcAlipayWeChat" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">

						<tr>
							<td class="table2_td_title">
								�̻����:
							</td>
							<td class="table2_td">
								<html:text property="merchantId" size="25" maxlength="15" onblur="checkMerchantId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<font color="red">*</font>
								<!-- <input name="merchanName" size="25" maxlength="15" disabled="true"/>
								<font color="red">*</font> -->
							</td>
							<td class="table2_td_title">
								���б�ʶ:
							</td>
							<td class="table2_td">
								<html:text property="bankId" size="25" maxlength="8"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 �ն˺ţ�
							</td>
							<td class="table2_td">
								<html:text property="terminalId" size="25"  maxlength="8" onblur="checkTerminalId(this.value)" onkeyup="this.value=this.value.replace(/\W/g,'')"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								ר�����̻��ţ�
							</td>
							<td class="table2_td">
								<html:text property="bankMerchantId" size="25" maxlength="15" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								ר�����ն˺ţ�
							</td>
							<td class="table2_td">
								<html:text property="bankTerminalId" size="25"  maxlength="8" />
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								ģ��ID��
							</td>
							<td class="table2_td">
								<html:text property="moduleId" size="25" value="77" readonly="true"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								ϵͳ��ˮ�ţ�
							</td>
							<td class="table2_td">
								<html:text property="sysTrace"  value="1" size="25" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								ר������ˮ�ţ�
							</td>
							<td class="table2_td">
								<html:text property="bankTrace" size="25" value="1" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								PIN�㷨��ʶ:
							</td>
							<td class="table2_td">
								<html:select property="pinFmt">
								 <option value="1">ANSI X98��ʽ���������˺ţ�</option>
								 <option value="2">ANSI X98�㷨�������˺ţ�</option>
								</html:select>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								�����㷨:
							</td>
							<td class="table2_td">
								<html:select property="encMethod">
								 	<option value="6" selected="selected">3DES</option>
								 	<option value="0">DES</option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								MAC�����־ :
							</td>
							<td class="table2_td">
								<html:select property="macFlag">
								 <option value="0">�����ն˲���</option>
								 <option value="1" selected="selected">����</option>
								</html:select>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								���κţ�
							</td>
							<td class="table2_td">
								<html:text property="batchNo" size="19" maxlength="30" value="1" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								������Կ����PIN_KEY��
							</td>
							<td class="table2_td">
								<html:text property="pik" size="25"  maxlength="32"/>
							</td>
							<td class="table2_td_title">
								������Կ����MAC_KEY��
							</td>
							<td class="table2_td">
								<html:text property="mak" size="25"  maxlength="32"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								������Կ����PIN_KEY<br>���ն�ǩ����ȡ����
							</td>
							<td class="table2_td">
								<html:text property="pikTmk" size="25"  maxlength="32"/>
							</td>
							<td class="table2_td_title">
								������Կ����MAC_KEY<br>(�ն�ǩ����ȡ)(MD5_KEY)��
							</td>
							<td class="table2_td">
								<html:text property="makTmk" size="25" value="11111111111111111111111111111111" maxlength="32"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��Կ������
							</td>
							<td class="table2_td">
								<html:text property="keyIndex" size="25" value="01" maxlength="2"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								����״̬ :
							</td>
							<td class="table2_td">
								<html:select property="settStatus">
								 <html:option value="0">��������״̬ </html:option>
								 <html:option value="1">��Ҫ����</html:option>
								 <html:option value="2">���ʽ�����</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�������POS�ն�ǩ��״̬ :
							</td>
							<td class="table2_td">
								<html:select property="logonStatus">
								 <option value="0">ǩ�� </option>
								 <option value="1" selected="selected">ǩ��</option>
								 <option value="2">�쳣</option>
								</html:select>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								��ͨ��־:
							</td>
							<td class="table2_td">
								<html:select property="flag">
								 <html:option value="1">������ͨ </html:option>
								 <html:option value="0">δ��ͨ</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4" class="table2_btn">
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