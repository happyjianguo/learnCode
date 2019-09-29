<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
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
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />listPage.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	
	<script language="javascript">
	function init()
	{
		<c:if test="${!empty param._enable}" >
		var _enable = '<c:out value="${param._enable}"/>';
			document.forms[0]._enable.value=_enable;
		</c:if>	
	}
	function addClick()
	{
		document.forms[0].method.value="showSr";
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
		document.forms[0]._paramName.value="";
		document.forms[0]._paramType.value="";
		document.forms[0]._enable.value="";
	}
	
	</script>
</head>
<shiro:lacksPermission name="posp:sysparam:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:sysparam:view">
<body onload="init()">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				�������б�
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/sysParameter" method="post">
					<input type="hidden" name="method" value="querySysParameter" />
					<!----------����TableΪ��ѯform-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								��������:
							</td>
							<td class="table2_td" width="40%">
							<input name="_paramName" type="text" value="${param._paramName}" maxlength="19"  style="width:135px;">
							</td>
                               <td class="table2_td_title" width="10%">
								���ñ�־:
							</td>
							<td class="table2_td" width="40%">
								<select name="_enable">
									<option value="">--��ѡ��--</option>
									 <option value="0" >������</option>
									 <option value="1" >����</option>
								</select>
							</td>
						</tr>				
						<tr>
							<td class="table2_td_title" width="10%">
								��������:
							</td>
							<td class="table2_td" width="40%">
								<input name="_paramType" type="text" value="${param._paramType}" maxlength="19"  style="width:135px;">
							</td>
                              <td class="table2_td_title" width="10%">
							</td>
							<td class="table2_td" width="40%">
							</td>
						</tr>				
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="��ѯ" width="65" height="20" border="0" onclick="return searchClick('querySysParameter');">
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/sysParameter.do">
						<display:column title="ѡ��" style="width:10%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.id.paramType}#${displayTable.id.paramName}#end"/>">
							
						</display:column>
						<display:column title="��������" style="width:12%;text-align:center" property="id.paramType" headerClass="sortable" sortable="true" />
						<display:column title="��������" style="width:12%;text-align:center" property="id.paramName" headerClass="sortable" sortable="true" />
						<display:column title="������������" style="width:20%;text-align:center" property="paramChinese" headerClass="sortable" sortable="true" />
						<display:column title="����ֵ" style="width:12%;text-align:center" property="paramValue" headerClass="sortable" sortable="true" />
						<display:column title="����˵��" style="width:12%;text-align:center" property="paramNotes" headerClass="sortable" sortable="true" />
						<display:column title="���ñ�־" style="width:10%;text-align:center" property="ch_enable" headerClass="sortable" sortable="true" />
						<display:column title="����" style="width:10%;text-align:center">
                        <shiro:hasPermission name="posp:sysparam:edit">
						   <a href="#" onclick="javascript:editClicks('<c:out value="${displayTable.id.paramType}#${displayTable.id.paramName}#end"/>','querySysParameterByKey')">�༭</a>
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
                                <shiro:hasPermission name="posp:sysparam:delete">
								<a href="javascript:deleteClick('selectItems','checkItem','deleteSysParameter');"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="ɾ��" width="65" height="20" border="0"></a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="posp:sysparam:add">
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