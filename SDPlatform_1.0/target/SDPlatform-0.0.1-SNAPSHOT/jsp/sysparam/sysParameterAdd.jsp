<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>����������</title>
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
		document.forms[0].method.value="createSysParameter";
		return validateSysParameterForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="querySysParameter";
	}
	
	
	
	/*****************************/
	var req;
	function init() {
		if(window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	function checkSysParameter() {
	    var paramType=document.forms[0].paramType.value;
	    var paramName=document.forms[0].paramName.value;
	    if(paramType==null||paramType==""){
	       alert("�������Ͳ���Ϊ��!!!");
	    }else if(paramName==null||paramName==""){
	      alert("�������Ʋ���Ϊ��!!!");
	    }else{
	        init();
			var url="edcCheck.do?method=checkSysParameter&paramType="+paramType+"&paramName="+paramName;
			req.open("POST", url, true);
			req.onreadystatechange = callback;
			req.send(null);
	    }
    }
	function callback() {
	 if(4 == req.readyState) {
		if(200 == req.status) {
			if(req.responseText=='false'){
			  alert("�������ͺͲ�������һ���Ѿ����ڣ����������룡����");
			  document.forms[0].paramType.value='';
			  document.forms[0].paramName.value='';
			}
		}
	  }
    }
	</script>
</head>
<shiro:lacksPermission name="posp:sysparam:add">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:sysparam:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				����������
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="sysParameterForm" />
				<html:errors />
				<html:form action="/sysParameter">

					<html:hidden property="method" value="saveSysParameter" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">

						<tr>
							<td class="table2_td_title">
								��������:
							</td>
							<td class="table2_td">
								<html:text styleId="paramType" property="id.paramType"  size="19" maxlength="20"  />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��������:
							</td>
							<td class="table2_td">
								<html:text styleId="paramName" property="id.paramName" onblur="checkSysParameter()" size="19" maxlength="20" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								������������:
							</td>
							<td class="table2_td">
								<html:text property="paramChinese" size="19" maxlength="100" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								����ֵ:
							</td>
							<td class="table2_td">
								<html:text property="paramValue" size="19" maxlength="10" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								����˵��:
							</td>
							<td class="table2_td">
								<html:text property="paramNotes" size="19" maxlength="100" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								���ñ�־:
							</td>
							<td class="table2_td">
								<html:select property="enable">
								 <option value="0" >������</option>
								 <option value="1" >����</option>
								</html:select>
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