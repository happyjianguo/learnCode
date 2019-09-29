<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*,cn.yufu.posp.terminalmanager.domain.model.*"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>商户交易参数</title>
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
			alert("挥卡刷卡扫码必须支持一种");
			return false;
		}
		if(nopasswdMaxamt!=null&&nopasswdMaxamt!=""){ 
			if(nopasswdMaxamt.indexOf(".") != -1&&nopasswdMaxamt.split(".")[1].length>2){
				alert("免密授信限额格式错误");
				document.forms[0].nopasswdMaxamt.value='';
				return false;
			}
			if(nopasswdMaxamt.indexOf(".") == -1&&nopasswdMaxamt.length>13){
  				alert("免密授信限额格式错误");
  				document.forms[0].nopasswdMaxamt.value='';
  				return false;
  			}
			if(nopasswdMaxamt>5000){
  				alert("免密授信限额5000元");
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
	/***************商户编号验证*****************/
	var flag="0";	//控制标识
	function checkMerchantId(merchantId) {
	    if(merchantId==null||merchantId==""){
	       alert("商户编号不能为空！！！");
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
		       alert("此商户编号在商户表中不存在，请重新输入！！！");
		       document.forms[0].merchantId.value='';
		       document.forms[0].merchantName.value='';
		       flag="0";
		    }else if(req.responseText=='3'){
			       alert("此商户编号在商户交易参数表中已存在，请重新输入！！！");
			       document.forms[0].merchantId.value='';
			       document.forms[0].merchantName.value='';
			       flag="0";
			}else{
				flag="1";
			}
	  	 }
    }
  } 
	

	/***************商户中文名称验证*****************/
	   function checkMerchantNameCallback() {
		 if(4 == req.readyState) {
			if(200 == req.status) {
			    if(req.responseText=='false'){
			       alert("商户编号不存在，请重新输入！！！");
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
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:tblMerchantTranParam:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle"> 
				新增商户交易参数
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
								商户编号:
							</td>
							<td class="table2_td">
								<html:text property="merchantId" size="15" maxlength="15" onblur="checkMerchantId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								商户中文名称:
							</td>
							<td class="table2_td">
								<html:text property="merchantName" size="15" maxlength="15"  readonly="true"/>
								<font color="red">*</font>
							</td>
						</tr>

						<tr>
							<td class="table2_td_title">
								免密授信限额:
							</td>
							<td class="table2_td">
								<html:text property="nopasswdMaxamt" size="15" maxlength="7" onkeyup="this.value=this.value.replace(/[^\d\.]/g,'');"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								支持挥卡免密:
							</td>
							<td class="table2_td">
								<html:select property="waveFlag">
								<html:option value="1">1-支持</html:option>
								<html:option value="0">0-不支持</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								支持刷卡免密:
							</td>
							<td class="table2_td">
								<html:select property="swipeFlag">
								<html:option value="1">1-支持</html:option>
								<html:option value="0">0-不支持</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								扫码标识:
							</td>
							<td class="table2_td">
								<html:select property="scanFlag">
								<html:option value="1">1-支持</html:option>
								<html:option value="0">0-不支持</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								记录有效标识:
							</td>
							<td class="table2_td">
								<html:select property="flag">
								<html:option value="1">1-有效</html:option>
								<html:option value="0">0-无效</html:option>
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


