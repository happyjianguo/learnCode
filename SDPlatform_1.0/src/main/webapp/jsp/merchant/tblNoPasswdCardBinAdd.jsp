<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*,cn.yufu.posp.terminalmanager.domain.model.*"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>��Ȩ���ܿ�BIN��Ϣ</title>
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
		var firstCardBin = document.forms[0].firstCardBin.value.trim();
		if(firstCardBin!=null&&firstCardBin!=""){
			if(firstCardBin.length<9){
				alert("��ʾ:����Ϣλ������9λ");
				return false;
			}
			
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
		return validateTblNoPasswdCardBinForm(document.forms[0]);
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
	function checkFirstCardBin(firstCardBin) {
	    if(firstCardBin==null||firstCardBin==""){
	       alert("����Ϣ����Ϊ�գ�����");
	    }else{
			init();
			var url="tblNoPasswdCardBin.do?method=checkFirstCardBin&firstCardBin="+firstCardBin;
			req.open("POST", url, true);
			req.onreadystatechange = firstCardBinCallback;
			req.send(null);
		}
    }
    
	function firstCardBinCallback() {
	 if(4 == req.readyState) {
		if(200 == req.status) {
		    if(req.responseText=='false'){
		       alert("����Ϣ�Ѱ��������������룡����");
		       document.forms[0].firstCardBin.value='';
		       
		    }
		}
	  }
    }
    
	
	
</script>
</head>
<shiro:lacksPermission name="posp:tblNoPasswdCardBin:add">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:tblNoPasswdCardBin:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle"> 
				������Ȩ���ܿ�BIN��Ϣ
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="tblNoPasswdCardBinForm" />
				<html:errors />
				<html:form action="/tblNoPasswdCardBin">

					<html:hidden property="method" value="createItem" />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								����Ϣ:
							</td>
							<td class="table2_td">
								<html:text property="firstCardBin" size="11" maxlength="11" onblur="checkFirstCardBin(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								���������޶�:
							</td>
							<td class="table2_td">
								<html:text property="nopasswdMaxamt" size="11" maxlength="7" onkeyup="this.value=this.value.replace(/[^\d\.]/g,'');"/><font color="red">*</font>
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




