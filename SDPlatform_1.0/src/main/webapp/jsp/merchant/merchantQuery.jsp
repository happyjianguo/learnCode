<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>�̻������趨</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" language="JavaScript"  src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script type="text/javascript" language="JavaScript"  src="<fmt:message key='JavaScriptPath' />listPage.js"></script>
	<script type="text/javascript"  language="JavaScript" src="<fmt:message key='JavaScriptPath' />date.js"></script>
	<script type="text/javascript" language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script type="text/javascript" language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	<script language="javascript">
	function init()
	{
		<c:if test="${!empty param.queryMerid}" >
		var queryMerid = '<c:out value="${param.queryMerid}"/>';
		document.forms[0].queryMerid.value=queryMerid;
		</c:if>	
		<c:if test="${!empty param.queryMerstat}" >
		var queryMerstate = '<c:out value="${param.queryMerstat}"/>';
		document.forms[0].queryMerstat.value=queryMerstate;
		</c:if>	
	}
	function addClick()
	{
		document.forms[0].method.value="findItem";
		document.forms[0].submit();
	}
	function editClicks(id,method)
	{
		//alert(id+","+method);
		document.forms[0].method.value=method;
		document.forms[0].merchantId.value=id;
		document.forms[0].submit();
		
	}
	function resetClick1(){
		document.forms[0].queryMerid.value="";
		document.forms[0].queryMerstat.value="";
	}
	function downloadTemplate(){
		document.forms[0].method.value="downloadTemplate";
		document.forms[0].submit();
	}
	function fileUpload(){
		var fileName = document.getElementById("formShowData").upload.value;
		 if(!fileName){
			 alert("��ѡ��Ҫ������ļ�!");
	         return false;
		 }
	     var strRegex = "(.xlsx|.xls)$";
	     var re=new RegExp(strRegex);
	     if (!re.test(fileName.toLowerCase())){
	         alert("�ļ���ʽ���Ϸ�������ΪEXCEL�ĵ�!");
	         return false;
	     }
	     document.getElementById("formShowData").submit();
	}
	</script>
	
</head>
<shiro:lacksPermission name="posp:merchant:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:merchant:view">
<body onload="init();">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				�̻������б� 
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/merchant" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------����TableΪ��ѯform-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%"> 
								�̻����: 
							</td>
							<td class="table2_td" width="40%">
							<input name="queryMerid" size="19" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'');"/>
							</td>
							<td class="table2_td_title" width="10%"> 
								�̻�״̬: 
							</td>
							<td class="table2_td" width="40%">
							<html:select property="queryMerstat" style="width:130px;" value="">
								<html:option value="">- ״̬ -</html:option>
								<html:option value="Y">����</html:option>
								<html:option value="N">����</html:option>
							</html:select>
							</td>						
						</tr>				
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="��ѯ" width="65" height="20" border="0" onclick="return searchClick('queryAll');">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" onclick="resetClick1()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="���" width="65" height="20" border="0"></a>
							</td>
						</tr>
					</table>
					<!----------TableΪ��ѯform����-------->

					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table3_border">
						<tr>
							<td class="table3_title">
								��ѯ���
							</td>
						</tr>
					</table>

					<!-- ��Diaplay Tag����ʾ -->
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/merchant.do">
						<display:column title="ѡ��" style="width:5%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.merchantId}"/>">
						
						</display:column>
						<display:column title="�̻����" style="width:13%;text-align:center" property="merchantId" headerClass="sortable" sortable="true" />
						<display:column title="�̻����" style="width:12%;text-align:center" property="abbrCname" headerClass="sortable" sortable="true" />
						<display:column title="�̻�����" style="width:12%;text-align:center" property="mccName" headerClass="sortable" sortable="true" />
						<display:column title="״̬" style="width:6%;text-align:center" headerClass="sortable" sortable="true" >
						<c:if test='${displayTable.merchantStat eq "Y"}'>����</c:if>
						<c:if test='${displayTable.merchantStat eq "N"}'>����</c:if>
						</display:column>
						<display:column title="�к�" style="width:8%;text-align:center" property="signBankId" headerClass="sortable" sortable="true" />
						<display:column title="������" style="width:6%;text-align:center" property="signHostId" headerClass="sortable" sortable="true" />
						<display:column title="������" style="width:10%;text-align:center" property="updateOper" headerClass="sortable" sortable="true" />
						<display:column title="��������ʱ��" style="width:15%;text-align:center" headerClass="sortable" sortable="true" >
						<c:out value="${displayTable.updateDate } ${displayTable.updateTime }"></c:out>
						</display:column>
						<display:column title="����" style="width:5%;text-align:center">
						<shiro:hasPermission name="posp:merchant:edit">
							<a href="#" onclick="editClicks('<c:out value="${displayTable.merchantId}"/>','findItem')">�༭</a>
						</shiro:hasPermission>	
						</display:column>
						
					</display:table>
					<!-- ��Diaplay Tag����ʾ -->		
					
					<input type="hidden" name="id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="merchantId" />
					
					<!----------����Table�ò���ѡ��-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								�Ƿ�ȫѡ
							</td>
							<td>
							<!--  
								<shiro:hasPermission name="posp:merchant:delete">
									<a href="#"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="ɾ��" width="65" height="20" border="0" onclick="deleteClick('selectItems','checkItem','deleteItem');"></a> 
								</shiro:hasPermission>
							-->
								<shiro:hasPermission name="posp:merchant:add">
									<a href="#" onclick="addClick()">
										<img border=0 name="imageField322" src="<fmt:message key='CommonImagePath' />btnAdd.gif" alt="����" width="65" height="20" border="0"></a>
								</shiro:hasPermission>
							</td>
							<td width="240" align="right">
								&nbsp;
							</td>
						</tr>
					</table>
					<!----------TTable�ò���ѡ�����-------->
					
			</td>
		</tr>
	</table>
	<%@include file="../common/getDisplayParams1.jsp"%>
	</html:form>
	<shiro:hasPermission name="posp:merchant:upload">
	<center>			
	<form id='formShowData' name="formShowData" enctype='multipart/form-data' method='post' action="merchantUpload.do" >
	<input type="hidden" name="method" value="fileUpload" />
	<input type="file" name='upload' id='upload' /><input type="button" value="����" onclick="fileUpload();" />
	<a href="javascript:;" onclick="downloadTemplate();">���ص���ģ��</a>
	</form>
	</center>
	</shiro:hasPermission>
</body>
</shiro:hasPermission>
</html:html>
