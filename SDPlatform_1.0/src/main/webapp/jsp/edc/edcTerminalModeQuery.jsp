<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>��������Ҫ��</title>
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
	function init(){
		<c:if test="${!empty param._merchantId}" >
		var _merchantId = '<c:out value="${param._merchantId}"/>';
		document.forms[0]._merchantId.value=_merchantId;
		</c:if>	
		<c:if test="${!empty param._terminalId}" >
		var _terminalId = '<c:out value="${param._terminalId}"/>';
		document.forms[0]._terminalId.value=_terminalId;
		</c:if>	
		<c:if test="${!empty param._downloadMode}" >
		var _downloadMode = '<c:out value="${param._downloadMode}"/>';
		document.forms[0]._downloadMode.value=_downloadMode;
		</c:if>	
	}
	function addClick()
	{
		document.forms[0].method.value="showSr";
		document.forms[0].submit();
	}
	function detailClicks(id,method)
	{
		window.showModalDialog("edcTerminal.do?method="+method+"&_id="+id, "_blank", 'dialogWidth=800px;dialogHeight=440px;scroll=yes;resizable=no;status=no;center=yes;');
	}
	function showTermReq(textName,itemName,method){
		var aa = document.getElementsByName(itemName);
	 	var bb = document.all[textName];
	 	bb.value = "";
	 	flag = false;
	 	for (var i=0; i<aa.length; i++)
	 	{
	 		if(aa[i].checked)
	 		{
	 			flag = true;
	 			if(bb.value=="")
	 			{
	 				bb.value = aa[i].value;
	 			}
	 			else
	 				bb.value = bb.value + "|" + aa[i].value;
	 		}
	 	}
	 	if(flag)
	 	{
			var return_value = window.showModalDialog("edcTerminal.do?method=queryEdcTerminalModeList&random="+Math.random(),window,"dialogWidth=400px;dialogHeight=150px");
			if(return_value!=undefined){
	 			document.forms[0].method.value=method;
	 			document.forms[0].mode.value=return_value;
	 			document.forms[0].submit();
			}
	 	}
	 	else
	 		alert("\u8bf7\u9009\u62e9\u8981\u8bbe\u7f6e\u7684\u9009\u9879\uff01");
 		
	}
	</script>
</head>

<body onload="init();">

	<!--------------����TableΪ·��-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">
				��ǰλ�ã��յ�ϵͳ����ƽ̨ >> �ն˻����� >> ��������Ҫ��
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
				��������Ҫ���б�
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/edcTerminal" method="post">
					<input type="hidden" name="method" value="queryEdcTerminalMode" />
					<!----------����TableΪ��ѯform-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								�̻����:
							</td>
							<td class="table2_td" width="20%">
								<input name="_merchantId" type="text"  maxlength="15"  style="width:135px;">
							</td>
                            <td class="table2_td_title" width="10%">
								�ն˺�:
							</td>
							<td class="table2_td" width="20%">							
								<input name="_terminalId" type="text"  maxlength="8"  style="width:135px;">
							</td>
                            <td class="table2_td_title" width="10%">
								��������Ҫ��:
							</td>
							<td class="table2_td" width="20%">							
								<select id="_downloadMode" name="_downloadMode" >
									<option value="">--��ѡ��--</option>
									<option value="0">��</option>
									<option value="4">���¹�Կ</option>
									<option value="5">����IC������</option>
								</select>
							</td>
						</tr>				
						<tr>
							<td colspan="6" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="��ѯ" width="65" height="20" border="0" onclick="return searchClick('queryEdcTerminalMode');">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:resetClick()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="���" width="65" height="20" border="0"></a>
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/edcTerminal.do">
						<display:column title="ѡ��" style="width:10%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.id.merchantId}#${displayTable.id.terminalId}#${displayTable.terminalStat}#${displayTable.edcType}#${displayTable.softVer}#${displayTable.downloadFlag}#${displayTable.downloadMode}#${displayTable.updateOper}#${displayTable.updateDate}#${displayTable.updateTime}#${displayTable.edcDoc}#${displayTable.printerType}#${displayTable.pinpadType}#${displayTable.setDate}#${displayTable.setAddr}#end"/>">
							
						</display:column>
						<display:column title="�̻����" style="width:10%;text-align:center" property="id.merchantId" headerClass="sortable" sortable="true" />
						<display:column title="�ն˱��" style="width:12%;text-align:center" property="id.terminalId" headerClass="sortable" sortable="true" />
						<display:column title="�ն�״̬" style="width:13%;text-align:center" property="ch_terminalStat" headerClass="sortable" sortable="true" />
						<display:column title="��������Ҫ��" style="width:15%;text-align:center" headerClass="sortable" sortable="true" >
							<c:if test="${displayTable.downloadMode eq 0}">��</c:if>
							<c:if test="${displayTable.downloadMode eq 4}">���¹�Կ</c:if>
							<c:if test="${displayTable.downloadMode eq 5}">����IC������</c:if>
						</display:column>
						<display:column title="ά����Ա" style="width:15%;text-align:center" property="updateOper" headerClass="sortable" sortable="true" />
						<display:column title="����ʱ��" style="width:15%;text-align:center" property="ch_dateAndTime" headerClass="sortable" sortable="true" />
						<display:column title="����" style="width:10%;text-align:center">
                        <c:if test="${currentUserData.groupMap.x010307x00=='yes'}">
						   <a href="#" onclick="javascript:detailClicks('<c:out value="${displayTable.id.merchantId}|${displayTable.id.terminalId}|${displayTable.terminalStat}|${displayTable.edcType}|${displayTable.softVer}|${displayTable.downloadFlag}|${displayTable.downloadMode}|${displayTable.updateOper}|${displayTable.updateDate}|${displayTable.updateTime}|${displayTable.edcDoc}|${displayTable.printerType}|${displayTable.pinpadType}|${displayTable.setDate}|${displayTable.setAddr}|end"/>','queryDetailByKey')">�鿴</a>
                        </c:if> 
						</display:column>
						
					</display:table>
					<!-- ��Diaplay Tag����ʾ -->		
					
					<input type="hidden" name="_id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="mode" />
					
					<!----------����Table�ò���ѡ��-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								�Ƿ�ȫѡ
							</td>
							<td>
                                <c:if test="${currentUserData.groupMap.x010307x02=='yes'}">
									<input type="button" name="setEdcTerminalMode" value="������������Ҫ��" onclick="showTermReq('selectItems','checkItem','setEdcTerminalMode');"/>
                                </c:if>
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
</html:html>
