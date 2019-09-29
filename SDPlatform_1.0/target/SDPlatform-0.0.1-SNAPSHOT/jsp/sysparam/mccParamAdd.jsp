<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>MCC参数管理</title>
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
		return validateMccForm(document.forms[0]);
	}
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	</script>

</head>
<shiro:lacksPermission name="posp:mcc:add">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:mcc:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">  
				新增MCC
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="mccForm" />
				<html:errors />
				<html:form action="/mccParam">

					<html:hidden property="method" value="createItem" />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						
						<tr>
							<td class="table2_td_title">
								MCC编号:
							</td>
							<td class="table2_td">
								<html:text property="mcc" size="30" maxlength="4"  onkeyup="this.value=this.value.replace(/(^\s*)|(\s*$)/g ,'');"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								MCC名称:

							</td>
							<td class="table2_td">
								<html:text property="mccName" size="30" maxlength="30" /><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								MCC英文名称:

							</td>
							<td class="table2_td">
								<html:text property="mccEname"  size="30"  maxlength="30" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title"> 
								类型编码: 
 
							</td>
							<td class="table2_td">
								<html:select property="mccType"  style="width:195px;" value="">
								<html:option value=" ">- 请选择 -</html:option>
								<html:option value="0">宾馆类</html:option>   
								<html:option value="1">餐饮、娱乐和珠宝工艺类</html:option>   
								<html:option value="2">房地产、汽车销售与批发类</html:option>   
								<html:option value="3">航空售票、加油、超市类</html:option>   
								<html:option value="4">公益类商户</html:option>   
								<html:option value="5">一般商户类</html:option>   
								<html:option value="6">新兴行业商户</html:option>   
								<html:option value="7">县乡优惠</html:option>
								</html:select><font color="red">*</font>
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