<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*,cn.yufu.posp.terminalmanager.domain.model.*"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>业务场景费率</title>
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
			alert("业务费率格式错误，标准格式为小数点前后不超过两位数字或两位整数");
			return false;
		}
		
		if(businessNameDetail.length<=0||businessNameDetail.length>100){
			alert("业务中文名称详细描述必须填写并且少于等于100个字符");
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
					alert("小数点后不能超过"+maxLengthAfter+"位数字，请重新输入。"); 
					return false;
				}
				if(n.indexOf(".") != -1&&n.toString().split(".")[0].length>maxLengthBefor){
					alert("小数点前不能超过"+maxLengthBefor+"位数字，请重新输入。"); 
					return false;
				}
				if(n.indexOf(".") == -1&&n.toString().length>maxLengthBefor){
					alert("整数不能超过"+maxLengthBefor+"位数字，请重新输入。"); 
					return false;
				}			
			}
		}
</script>
</head>
<shiro:lacksPermission name="posp:tabBusinessRates:add">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:tabBusinessRates:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle"> 
				新增业务场景费率
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
								业务中文名称:
							</td>
							<td class="table2_td">
								<html:text property="businessname" size="30" maxlength="6"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								业务费率(%):
							</td>
							<td class="table2_td">
								<html:text property="rate" size="30" maxlength="5" onkeyup="this.value=this.value.replace(/[^\d\.]/g,'');checkNumberXSD(this.value,'2','2');"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								业务中文名称详细描述:
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



