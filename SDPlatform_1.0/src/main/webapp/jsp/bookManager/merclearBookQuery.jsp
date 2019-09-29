<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>�̻���ֵ��б�</title>
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
    <script type="text/javascript" src="<fmt:message key='JavaScriptPath' />jquery-1.4.min.js" ></script>
    <script type="text/javascript" src="<fmt:message key='JavaScriptPath' />DatePicker/WdatePicker.js" ></script>
	
	<script language="javascript">
	function init()
	{
		<c:if test="${!empty param._merno}" >
		var _merno = '<c:out value="${param._merno}"/>';
		document.forms[0]._merno.value=_merno;
		</c:if>	
		<c:if test="${!empty param._trandate}" >
		var _trandate = '<c:out value="${param._trandate}"/>';
		document.forms[0]._trandate.value=_trandate;
		</c:if>
	}

	function openDetail(id)
	{
		window.showModalDialog("merclearBook.do?method=queryDetail&read=true&id="+id, "_blank", 'dialogWidth=800px;dialogHeight=440px;scroll=yes;resizable=no;status=no;center=yes;');
	}
	function queryDetail(id)
	{
		document.forms[0].method.value="queryDetail";
		document.forms[0]._traceNo.value=id;
		document.forms[0].submit();
	}
	function queryAll(){
		document.forms[0].method.value="queryAll";
		return true;
	}
	//ɾ������ո�
  	String.prototype.RTrim   =   function(){   
  		return   this.replace(/(\s*$)/g,"");   
  	} 
	</script>
</head>

<body onload="init()">

	<!--------------����TableΪ·��-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">
				��ǰλ�ã��յ�ϵͳ����ƽ̨ >> ���ݹ��� >> �̻���ֵ�
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
				�̻���ֵ��б�
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:errors />
				<html:form action="/merclearBook" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------����TableΪ��ѯform-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								�̻����  :
							</td>
							<td class="table2_td" width="40%">
								<input type="text" name="_merno"  maxlength="20"  style="width:135px;" onkeyup="this.value=this.value.replace(/\D/g,'');" />
							</td>
							<td class="table2_td_title" width="10%">
								��������:
							</td>
							<td class="table2_td" width="40%">
							<input name="_trandate" size="19" maxlength="12" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="��ѯ" width="65" height="20" border="0" onclick="return queryAll();">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" onclick="resetClick()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="���" width="65" height="20" border="0"></a>
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/merclearBook.do">
						<display:column title="�̻����" style="width:10%;text-align:center" property="merno" headerClass="sortable" sortable="true" />
						<display:column title="���ױ��� " style="width:6%;text-align:center" property="trannum" headerClass="sortable" sortable="true" />
						<display:column title="���׽��(��)" style="width:6%;text-align:center" property="tranamt" headerClass="sortable" sortable="true" />
						<display:column title="�˻�����(��)" style="width:6%;text-align:center" property="refundnum" headerClass="sortable" sortable="true" />
						<display:column title="�˻����(��)" style="width:6%;text-align:center" property="refundamt" headerClass="sortable" sortable="true" />
						<display:column title="����Ӷ��(��)" style="width:6%;text-align:center" property="tranfee" headerClass="sortable" sortable="true" />
						<display:column title="������(��)" style="width:6%;text-align:center" property="clearamt" headerClass="sortable" sortable="true" />
						<display:column title="��������" style="width:6%;text-align:center" property="trandate" headerClass="sortable" sortable="true" />
						<display:column title="����" style="width:5%;text-align:center">
						<c:if test="${currentUserData.groupMap.x010901x00=='yes'}"> 
							<a href="javascript:;" onclick="openDetail('${displayTable.id}')" >��ϸ</a>
						</c:if>
						</display:column>
					</display:table>
					<!-- ��Diaplay Tag����ʾ -->		
			</td>
		</tr>
	</table>
	<%@include file="../common/getDisplayParams1.jsp"%>
	</html:form>
	<br>

</body>
</html:html>
