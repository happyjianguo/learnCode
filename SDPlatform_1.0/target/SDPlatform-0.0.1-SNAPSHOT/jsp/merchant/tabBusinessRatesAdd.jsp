<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*,cn.yufu.posp.terminalmanager.domain.model.*"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>ҵ�񳡾�����</title>
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
		var rate = document.forms[0].rate.value.trim();
		var businessNameDetail = document.forms[0].businessNameDetail.value.trim();
		var reg = /(^[0-9]{1}\.[0-9]{1,2}$)|(^[1-9]{1}[0-9]\.[0-9]{1,2}$)|(^[0-9]{1}$)|(^[1-9]{1}[0-9]$)/;
		if(!reg.test(rate)){
			alert("ҵ����ʸ�ʽ���󣬱�׼��ʽΪС����ǰ�󲻳�����λ���ֻ���λ����");
			return false;
		}
		
		if(businessNameDetail.length<=0||businessNameDetail.length>100){
			alert("ҵ������������ϸ����������д�������ڵ���100���ַ�");
			return false;
		}
		document.forms[0].method.value="createItem";
		return validateTabBusinessRatesForm(document.forms[0]);
	}
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	

	function checkNumberXSD(n,maxLengthAfter,maxLengthBefor){
			if(n!=null&&n!=""){
				if(n.indexOf(".") != -1&&n.toString().split(".")[1].length>maxLengthAfter){
					alert("С������ܳ���"+maxLengthAfter+"λ���֣����������롣"); 
					return false;
				}
				if(n.indexOf(".") != -1&&n.toString().split(".")[0].length>maxLengthBefor){
					alert("С����ǰ���ܳ���"+maxLengthBefor+"λ���֣����������롣"); 
					return false;
				}
				if(n.indexOf(".") == -1&&n.toString().length>maxLengthBefor){
					alert("�������ܳ���"+maxLengthBefor+"λ���֣����������롣"); 
					return false;
				}			
			}
		}
</script>
</head>
<shiro:lacksPermission name="posp:tabBusinessRates:add">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:tabBusinessRates:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle"> 
				����ҵ�񳡾�����
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="tabBusinessRatesForm" />
				<html:errors />
				<html:form action="/tabBusinessRates">

					<html:hidden property="method" value="createItem" />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								ҵ����������:
							</td>
							<td class="table2_td">
								<html:text property="businessname" size="30" maxlength="6"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								ҵ�����(%):
							</td>
							<td class="table2_td">
								<html:text property="rate" size="30" maxlength="5" onkeyup="this.value=this.value.replace(/[^\d\.]/g,'');checkNumberXSD(this.value,'2','2');"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								ҵ������������ϸ����:
							</td>
							<td class="table2_td">
								<html:textarea property="businessNameDetail" rows="10" cols="25" /><font color="red">*</font>   
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



