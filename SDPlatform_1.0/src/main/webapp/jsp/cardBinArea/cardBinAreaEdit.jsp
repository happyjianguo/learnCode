<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>���򿨿�BIN</title>
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
		document.forms[0].method.value="saveItem";
		return validateCardBinAreaForm(document.forms[0]);
	}
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	function findArea()
	{
		var return_value = window.showModalDialog("merchant.do?method=showFindAreaPage");
		if(return_value!=undefined){
			document.forms[0].areaCode.value=return_value.RTrim();
		}
	}	
	//ɾ������ո�
  	String.prototype.RTrim   =   function(){   
  		return   this.replace(/(\s*$)/g,"");   
  	} 	
	</script>

</head>
<shiro:lacksPermission name="posp:cardBinArea:edit">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:cardBinArea:edit">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">  
				�޸����򿨿�BIN
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="cardBinAreaForm" />
				<html:errors />
				<html:form action="/cardBinArea" method="post">
					<html:hidden property="method" value="saveItem" />
					<html:hidden property="queryCardBin" />
					<html:hidden property="queryStatus" />
					<html:hidden property="queryAreaCode" />
					<html:hidden property="cardBin" value="${cardBinAreaForm.cardBin}"/>
					
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								��BIN:
							</td>
							<td class="table2_td">
								<html:text property="cardBin" size="30" maxlength="9" onkeyup="this.value=this.value.replace(/\D/g,'')" disabled="true"/><font color="red">*</font>
							</td>
						</tr>
						<tr>							
							<td class="table2_td_title">
								����:
							</td>
							<td class="table2_td">
								<input type="text" name="areaCode" value="${cardBinAreaForm.areaCode}" id="areaCode" size="30" maxlength="4" readonly="readonly" />
								<input type="button" value="ѡ��" onclick="findArea();">
								<font color="red">*</font>
							</td>
						</tr>
						<tr>							
							<td class="table2_td_title">
								״̬:
							</td>
							<td class="table2_td">
								<html:select property="status" style="width:195px;" value="">
								  <html:option value="1">��Ч</html:option>
  								  <html:option value="0">��Ч</html:option>								  
						        </html:select>
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
				</html:form>
			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>