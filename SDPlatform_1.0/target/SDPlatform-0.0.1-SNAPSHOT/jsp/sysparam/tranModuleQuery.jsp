<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>����ģ����Ϣ</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />listPage.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	
	<script language="javascript">
	function init()
	{
		<c:if test="${!empty param._moduleId}" >
		var _moduleId = '<c:out value="${param._moduleId}"/>';
		document.forms[0]._moduleId.value=_moduleId;
		</c:if>	
		<c:if test="${!empty param._flag}" >
		var _flag = '<c:out value="${param._flag}"/>';
		document.forms[0]._flag.value=_flag;
		</c:if>	
	}
	function addClick()
	{
		document.forms[0].method.value="findItem";
		document.forms[0].submit();
	}
	function editClicks(id,method)
	{
		document.forms[0].method.value=method;
		document.forms[0]._id.value=id;
		document.forms[0].submit();
		
	}
	function reset()
	{
		document.forms[0]._moduleId.value="";
		document.forms[0]._tranType.value="";
		document.forms[0]._tranName.value="";
		document.forms[0]._flag.value="";
	}
	
	</script>
</head>
<shiro:lacksPermission name="posp:tranmodule:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:tranmodule:view">
<body onload="init();">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				����ģ����Ϣ�б�
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/tranModule" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------����TableΪ��ѯform-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="15%">
								����ģ���:
							</td>
							<td class="table2_td" width="40%">
								<input name="_moduleId" type="text" value="${param._moduleId}" maxlength="19"  style="width:135px;">
							</td>
                               <td class="table2_td_title" width="15%">
								��ͨ״̬:
							</td>
							<td class="table2_td" width="40%">							
							<select name="_flag" value="${param._flag}" >
							     <option></option>
								 <option value="0" >δ��ͨ</option>
								 <option value="1" >������ͨ</option>
							</select>
							</td>
						</tr>				
						<tr>
							<td class="table2_td_title" width="15%">
								�������ʹ���:
							</td>
							<td class="table2_td" width="40%">
								<input name="_tranType" type="text" value="${param._tranType}" maxlength="19"  style="width:135px;">
							</td>
                            <td class="table2_td_title" width="15%">
                              	��������:
							</td>
							<td class="table2_td" width="40%">
								<input name="_tranName" type="text" value="${param._tranName}" maxlength="19"  style="width:135px;">
							</td>
						</tr>				
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="��ѯ" width="65" height="20" border="0" onclick="return searchClick('queryAll');">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:reset()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="���" width="65" height="20" border="0"></a>
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/tranModule.do">
						<display:column title="ѡ��" style="width:5%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.moduleId}#${displayTable.tranType}#${displayTable.voidTranType}#${displayTable.voidOldTranType}#end"/>">
							
						</display:column>
						<display:column title="����ģ���" style="width:10%;text-align:center" property="moduleId" headerClass="sortable" sortable="true" />
						<display:column title="�������ʹ���" style="width:10%;text-align:center" property="tranType" headerClass="sortable" sortable="true" />
						<display:column title="��������" style="width:15%;text-align:center" property="tranName" headerClass="sortable" sortable="true" />
						<display:column title="�����־" style="width:10%;text-align:center" headerClass="sortable" sortable="true" >
						<c:if test="${displayTable.settFlag eq '0'}">���μ�</c:if>
						<c:if test="${displayTable.settFlag eq '1'}">�μ�(���)</c:if>
						<c:if test="${displayTable.settFlag eq '2'}">�μ�(����)</c:if>
						</display:column>
						<display:column title="��¼��ˮ" style="width:10%;text-align:center" headerClass="sortable" sortable="true" >
						<c:if test="${displayTable.writeLsFlag eq '0'}">����</c:if>
						<c:if test="${displayTable.writeLsFlag eq '1'}">��¼</c:if>
						</display:column>
						<display:column title="MACУ��" style="width:10%;text-align:center" headerClass="sortable" sortable="true" >
						<c:if test="${displayTable.checkMacFlag eq '0'}">��У��</c:if>
						<c:if test="${displayTable.checkMacFlag eq '1'}">У��</c:if>
						</display:column>
						<display:column title="��ͨ״̬" style="width:10%;text-align:center" headerClass="sortable" sortable="true" >
						<c:if test="${displayTable.flag eq '0'}">δ��ͨ</c:if>
						<c:if test="${displayTable.flag eq '1'}">������ͨ</c:if>
						</display:column>
						<display:column title="����" style="width:5%;text-align:center">
                        <shiro:hasPermission name="posp:tranmodule:edit">
						   <a href="#" onclick="javascript:editClicks('<c:out value="${displayTable.moduleId}#${displayTable.tranType}#${displayTable.voidTranType}#${displayTable.voidOldTranType}#end"/>','findItem')">�༭</a>
                        </shiro:hasPermission>
						</display:column>
						
					</display:table>
					<!-- ��Diaplay Tag����ʾ -->		
					
					<input type="hidden" name="_id" />		
					<input type="hidden" name="selectItems" />
					
					<!----------����Table�ò���ѡ��-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								�Ƿ�ȫѡ
							</td>
							<td>
                                <shiro:hasPermission name="posp:tranmodule:delete">
									<a href="javascript:deleteClick('selectItems','checkItem','deleteItem');"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="ɾ��" width="65" height="20" border="0"></a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="posp:tranmodule:add">
								<a href="javascript:addClick()">
									<img border=0 name="imageField322" src="<fmt:message key='CommonImagePath' />btnAdd.gif" alt="����" width="65" height="20" border="0" ></a>
                                </shiro:hasPermission>
							</td>
							<td width="240" align="right">
								&nbsp;
							</td>
						</tr>
					</table>
					<!----------TTable�ò���ѡ�����-------->
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