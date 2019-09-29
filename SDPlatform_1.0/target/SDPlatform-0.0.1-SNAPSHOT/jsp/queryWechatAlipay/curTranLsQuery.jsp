<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>֧����΢����ˮ��ѯ�б�</title>
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
		<c:if test="${!empty param._startDate}" >
		var _startDate = '<c:out value="${param._startDate}"/>';
		document.forms[0]._startDate.value=_startDate;
		</c:if>	
		<c:choose>
		   <c:when test="${!empty param._endDate}">  
		    var _endDate = '<c:out value="${param._endDate}"/>';
			document.forms[0]._endDate.value=_endDate;      
		   </c:when>
		   <c:otherwise> 
		    var _endDate = '<c:out value="${param._startDate}"/>';
			document.forms[0]._endDate.value=_endDate;
		   </c:otherwise>
		</c:choose>
		<c:if test="${!empty param._merchantId}" >
		var _merchantId = '<c:out value="${param._merchantId}"/>';
		document.forms[0]._merchantId.value=_merchantId;
		</c:if>	
		<c:if test="${!empty param._terminalId}" >
		var _terminalId = '<c:out value="${param._terminalId}"/>';
		document.forms[0]._terminalId.value=_terminalId;
		</c:if>	
		
		<c:if test="${!empty param._tranType}" >
		var _tranType = '<c:out value="${param._tranType}"/>';
		document.forms[0]._tranType.value=_tranType;
		</c:if>	
		<c:if test="${!empty param._queryType}" >
		var _queryType = '<c:out value="${param._queryType}"/>';
		document.forms[0]._queryType.value=_queryType;
		</c:if>	
		<c:if test="${!empty param._scanCode}" >
		var _scanCode = '<c:out value="${param._scanCode}"/>';
		document.forms[0]._scanCode.value=_scanCode;
		</c:if>	
		<c:if test="${!empty param._tranRrn}" >
		var _tranRrn = '<c:out value="${param._tranRrn}"/>';
		document.forms[0]._tranRrn.value=_tranRrn;
		</c:if>	
	}
	function  DateDiff(sDate1,  sDate2){    //sDate1��sDate2��2006-12-18��ʽ    
	    var  aDate,  oDate1,  oDate2,  iDays;    
	    aDate  =  sDate1.split("-");    
	    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);    //ת��Ϊ12-18-2006��ʽ    
	    aDate  =  sDate2.split("-");    
	    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);    
	    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24);    //�����ĺ�����ת��Ϊ����   
	    return  iDays + 1;   
	}
	function addClick()
	{
		document.forms[0].method.value="showSr";
		document.forms[0].submit();
	}
	function queryDetail(id)
	{
		document.forms[0].method.value="queryDetail";
		document.forms[0]._traceNo.value=id;
		document.forms[0].submit();
	}
	function exportCurTranLs()
	{
		var merchantId = document.forms[0]._merchantId.value.RTrim();
		var startDate = document.forms[0]._startDate.value.RTrim();
		var endDate = document.forms[0]._endDate.value.RTrim();
		if(merchantId==""||startDate==""||endDate==""){
			alert('�������ѯ���̻���ź�����');
			return false;
		}else{
			var endd = endDate.substring(0,4)+"-"+endDate.substring(4,6)+"-"+endDate.substring(6,8);
			var startt = startDate.substring(0,4)+"-"+startDate.substring(4,6)+"-"+startDate.substring(6,8);
			var num = DateDiff(endd,startt);
			if(num > 90){
				alert("���ѯ90�����ڵ�����");
				return false;
			}
		}
		document.forms[0].method.value="exportCurTranLs";
		document.forms[0].submit();
	}
	function queryAll(){
		var merchantId = document.forms[0]._merchantId.value.RTrim();
		var startDate = document.forms[0]._startDate.value.RTrim();
		var endDate = document.forms[0]._endDate.value.RTrim();
		if(merchantId==""||startDate==""||endDate==""){
			alert('�������ѯ���̻���ź�����');
			return false;
		}else{
			var endd = endDate.substring(0,4)+"-"+endDate.substring(4,6)+"-"+endDate.substring(6,8);
			var startt = startDate.substring(0,4)+"-"+startDate.substring(4,6)+"-"+startDate.substring(6,8);
			var num = DateDiff(endd,startt);
			if(num > 90){
				alert("���ѯ90�����ڵ�����");
				return false;
			}
		}
		document.forms[0].method.value="queryAll";
		return true;
	}
	//ɾ������ո�
  	String.prototype.RTrim   =   function(){   
  		return   this.replace(/(\s*$)/g,"");   
  	} 
	</script>
	
</head>

<shiro:lacksPermission name="posp:queryWechatAlipay:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:queryWechatAlipay:view">
<body onload="init()">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				��ˮ�б�
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:errors />
				<html:form action="/queryWechatAlipay" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------����TableΪ��ѯform-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								�̻����:
							</td>
							<td class="table2_td" width="15%">
								<input type="text" name="_merchantId"  maxlength="15"  style="width:135px;" onkeyup="this.value=this.value.replace(/\D/g,'');" />
							</td>
							<td class="table2_td_title" width="10%">
								ϵͳ�ն˺�:
							</td>
							<td class="table2_td" width="15%">
								<input type="text" name="_terminalId"  maxlength="8"  style="width:135px;" />
							</td>
							<td class="table2_td_title">
								�ο���:
							</td>
							<td class="table2_td">
								<input type="text" name="_tranRrn"  maxlength="12"  style="width:135px;" onkeyup="this.value=this.value.replace(/\D/g,'');" />
							</td>
							<td class="table2_td_title">
								�ۺ�����:
							</td>
							<td class="table2_td">
								<input type="text" name="_scanCode"  maxlength="32"  style="width:135px;" onkeyup="this.value=this.value.replace(/\D/g,'');" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								����ϵͳʱ��:
							</td>
							<td class="table2_td" width="20%">
								<input name="_startDate" size="19" maxlength="12" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" readonly="true"/>
							</td>
							<td class="table2_td_title">
								��
							</td>
							<td class="table2_td" width="20%">
								<input name="_endDate" size="19" maxlength="12" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" readonly="true"/>
							</td>
							<td class="table2_td_title">
								״̬:
							</td>
							<td class="table2_td">
								<select name="_queryType">
									<option value = "">��ѡ��</option>
									<option value = "200">�ɹ�</option>
									<option value = "520">ʧ��</option>
								</select>
							</td>
							<td class="table2_td_title">
								��������:
							</td>
							<td class="table2_td">
								<select name="_tranType">
									<option value = "">��ѡ��</option>
									<option value = "1">����</option>
									<option value = "9">�˻�</option>
									<option value = "41">����</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="8" class="table2_btn">
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/queryWechatAlipay.do">
						<display:column title="�̻���" style="width:10%;text-align:center" property="merchantId" headerClass="sortable" sortable="true" />
						<display:column title="�ն˺�" style="width:6%;text-align:center" property="terminalId" headerClass="sortable" sortable="true" />
						<display:column title="�ο���" style="width:6%;text-align:center" property="tranRrn" headerClass="sortable" sortable="true" />
						<%-- <display:column title="��������" style="width:6%;text-align:center" property="tranType" headerClass="sortable" sortable="true" /> --%>
						<display:column title="��������" style="width:6%;text-align:center" headerClass="sortable" sortable="true" >
							<c:if test="${displayTable.tranType eq 1}">
								����
							</c:if>
							<c:if test="${displayTable.tranType eq 9}">
							 	�˻�
							</c:if>
							<c:if test="${displayTable.tranType eq 41}">
								����
							</c:if>
						</display:column>
						<display:column title="ҵ�񶩵���" style="width:12%;text-align:center" property="sysOrderId" headerClass="sortable" sortable="true" />
						<display:column title="ԭҵ�񶩵��� " style="width:12%;text-align:center" property="sysVoidOrderId" headerClass="sortable"  sortable="true" />
						<display:column title="������ϸ " style="width:5%;text-align:center" property="sysOrderDtl" headerClass="sortable" sortable="true" />
						<display:column title="���׽��" style="width:5%;text-align:center" property="tranAmt" headerClass="sortable" sortable="true" />
						<display:column title="ԭ���׽�� " style="width:5%;text-align:center" property="tranVoidAmt" headerClass="sortable" sortable="true" />
						<display:column title="ʱ���" style="width:5%;text-align:center" headerClass="sortable" sortable="true" >
							<c:out value="${displayTable.sysTimeStamp}"/>
						</display:column>
						<display:column title="���׽�� " style="width:15%;text-align:center" property="acqRespMsg" headerClass="sortable" sortable="true" />
						<%-- <display:column title="����״̬ " style="width:5%;text-align:center" property="acqRespCode" headerClass="sortable" sortable="true" /> --%>
					</display:table>
					
					<!-- ��Diaplay Tag����ʾ -->		
			</td>
		</tr>
		<tr>
			<td class="table2_td_title" align="right">
				<shiro:hasPermission name="posp:queryWechatAlipay:export">
				<input type="button" value="��ˮ����" onclick="exportCurTranLs();"/>
				</shiro:hasPermission>
			</td>
		</tr>
	</table>
	<%@include file="../common/getDisplayParams1.jsp"%>
	</html:form>
	<br>
</body>
</shiro:hasPermission>
</html:html>