<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>��־��ѯ</title>
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
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />DatePicker/WdatePicker.js" ></script>
	
	<script language="javascript">
	function init()
	{
		//<c:if test="${!empty param.search_sort}" >
		//var search_sort = '<c:out value="${param.search_sort}"/>';
		//document.forms[0].search_sort.value=search_sort;
		//</c:if>
		
		<c:if test="${!empty param.logType}" >
		var logType = '<c:out value="${param.logType}"/>';
		document.forms[0].logType.value=logType;
		</c:if>	
		<c:if test="${!empty param.logDate}" >
		var logDatee = '<c:out value="${param.logDate}"/>';
		document.forms[0].logDate.value=logDatee;
		</c:if>	
	}
	
	function editClicks(id,method)
	{
		//alert(id+","+method);
		document.forms[0].method.value=method;
		document.forms[0].logId.value=id;
		document.forms[0].submit();
		
	}
	
	function resetClick1(){
		document.forms[0].logType.value="";
		document.forms[0].logDate.value="";
	}
	</script>
	
</head>

<body onload="init();">

	<!--------------����TableΪ·��-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">  
				��ǰλ�ã��յ�ϵͳ����ƽ̨ >> ��־���� >> ��־��ѯ
			</td>
		</tr>
		<tr>
			<td height="10">
			</td>
		</tr>
	</table>
	<!--------------TableΪ·������-------->
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				������־�б� 
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/log" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------����TableΪ��ѯform-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%"> 
								��־����: 
							</td>
							<td class="table2_td" width="40%">
								<select name="logType">
									<option value="">--��ѡ��--</option>
									<option value="1">������Ϣ����</option>
									<option value="2">����</option>
								</select>
							</td>
							<td class="table2_td_title" width="10%"> 
								ʱ��: 
							</td>
							<td class="table2_td" width="40%">
								<input name="logDate" size="19" maxlength="12" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" readonly="true"/>
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/log.do">
						<display:column title="��־���" style="width:15%;text-align:center" property="logId" headerClass="sortable" sortable="true" />
						<display:column title="��־����" style="width:6%;text-align:center" headerClass="sortable" sortable="true" >
						<c:if test='${displayTable.type eq "1"}'>������Ϣ����</c:if>
						<c:if test='${displayTable.type eq "2"}'>����</c:if>
						</display:column>
						<display:column title="������" style="width:12%;text-align:center" property="createOper" headerClass="sortable" sortable="true" />
						<display:column title="��������" style="width:12%;text-align:center" property="createDate" headerClass="sortable" sortable="true" />
						<display:column title="����ʱ��" style="width:12%;text-align:center" property="createTime" headerClass="sortable" sortable="true" />
						<display:column title="����" style="width:8%;text-align:center">
							<a href="#" onclick="editClicks('<c:out value="${displayTable.logId}"/>','view')">��ϸ</a>
						</display:column>
						
					</display:table>
					<!-- ��Diaplay Tag����ʾ -->		
					
					<input type="hidden" name="id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="logId" />
					
					<!----------����Table�ò���ѡ��------
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								�Ƿ�ȫѡ
							</td>
							<td>
								<c:if test="${currentUserData.groupMap.x010201x03=='yes'}">
									<a href="#"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="ɾ��" width="65" height="20" border="0" onclick="deleteClick('selectItems','checkItem','deleteItem');"></a> 
								</c:if>
								<c:if test="${currentUserData.groupMap.x010201x01=='yes'}">
									<a href="#" onclick="addClick()">
										<img border=0 name="imageField322" src="<fmt:message key='CommonImagePath' />btnAdd.gif" alt="����" width="65" height="20" border="0"></a>
								</c:if>
							</td>
							<td width="240" align="right">
								&nbsp;
							</td>
						</tr>
					</table>-->
					<!----------TTable�ò���ѡ�����-------->
					
			</td>
		</tr>
	</table>
	<%@include file="../common/getDisplayParams1.jsp"%>
	</html:form>
	<br>
</body>
</html:html>
