<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*,cn.yufu.posp.terminalmanager.domain.model.*"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>商户转换</title>
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
		return validateMerchantSwitchForm(document.forms[0]);
	}
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	</script>
	<script type="text/javascript" language="javascript">

var req;
		
function init() {
	if(window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
}
		
function leavetext() {
	
	if ("" == document.merchantSwitchForm.merchantId.value)
    {
	   // alert("商户编号不能为空，请重新输入！");
	    //form1.merchantId.focus()
	    return false;
	}
	
	if (document.merchantSwitchForm.merchantId.value.length !=15)
	{
	    alert("商户编号必须为十五位，请重新输入！");
	    //form1.merchantId.focus()
	    return false;
	}
	
	
	init();
	var url = "merchantSwitch.do?method=findMerchantName&merchantId=" + escape(document.merchantSwitchForm.merchantId.value);
	req.open("POST", url, true);
	req.onreadystatechange = callback;
	req.send(null);
	
}
function callback() {
	if(4 == req.readyState) {

		if(200 == req.status) {
			//alert(req.responseText);
			//eval(req.responseText);
			document.merchantSwitchForm.merchantName.value = req.responseText;
			
		}
	}
	
}
//验证是否为数字
function checknumber(String) 
{ 
	var Letters = "1234567890"; 
	var i; 
	var c; 
	for( i = 0; i < String.length; i ++ ) 
	{ 
	c = String.charAt( i ); 
	if (Letters.indexOf( c ) ==-1) 
	{ 
	return true; 
	} 
	} 
	return false; 
} 
</script>
</head>
<shiro:lacksPermission name="posp:merchantswitch:add">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:merchantswitch:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">  
				新增商户转换
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="merchantSwitchForm" />
				<html:errors />
				<html:form action="/merchantSwitch">

					<html:hidden property="method" value="createItem" />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						
						<tr>
							<td class="table2_td_title">
								商户编号:
							</td>
							<td class="table2_td">
								<html:text property="merchantId" size="30" maxlength="15" onblur="leavetext()" onkeyup="this.value=this.value.replace(/\D/g,'')"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								商户名称:

							</td>
							<td class="table2_td">
								<html:text property="merchantName" size="30" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								银行类型:

							</td>
							<td class="table2_td">
								<html:select property="bankType" style="width:195px;">
								  <c:forEach var="model" items="${bankTypeList}">
							      <option value="<c:out value="${model.bankType}"/>"><c:out value="${model.typeName}" /></option>
							      </c:forEach>
							      </html:select><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title"> 
								他行商户编号: 
 
							</td>
							<td class="table2_td">
								<html:text property="othMerchantId"  size="30" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title"> 
								他行商户类型: 
 
							</td>
							<td class="table2_td">
								<html:text property="othMcc"  size="30" maxlength="4" onkeyup="this.value=this.value.replace(/\D/g,'')"/><font color="red">*</font>
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
					
				</html:form>

			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>



