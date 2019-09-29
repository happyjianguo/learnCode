<%@ page language="java" contentType="text/html; charset=gbk"	pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String h=request.getAttribute("h")==null?"":request.getAttribute("h").toString();
	String b=request.getAttribute("b")==null?"":request.getAttribute("b").toString();
	String c=request.getAttribute("c")==null?"":request.getAttribute("c").toString();
	String d=request.getAttribute("d")==null?"":request.getAttribute("d").toString();
	String e=request.getAttribute("e")==null?"":request.getAttribute("e").toString();
	String f=request.getAttribute("f")==null?"":request.getAttribute("f").toString();
	String g=request.getAttribute("g")==null?"":request.getAttribute("g").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html lang="true">
<head>
	<html:base />
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/cssem.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/new_look.css" />
	<title>��������ͳ��</title>
</head>
<script src="<%=path%>/js/sorttable.js"></script>
<script src="<%=path%>/js/eposcc.js"></script>
<script src="<%=path%>/js/calendar.js"></script>
<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>

<script language="javascript">
	function CurentDayTime(flag) {
		var now = new Date();
		var year = now.getFullYear(); 	//��
		var month = now.getMonth() + 1; //��
		if(flag!=null&&flag=="-1"){
			month = now.getMonth(); //��
		}		
		var day = now.getDate(); 		//��
		var hour = now.getHours();		//ʱ
		var minute = now.getMinutes();	//��
		var second = now.getSeconds();	//��
		var clock = year + "";
		if (month < 10)
			clock += "0";
		clock += month + "";
		if (day < 10)
			clock += "0";
		clock += day + "";
	 	if(hour<10){
	 		clock += "0";
	 	}
		clock += hour + "";
		if(minute<10){
			clock += "0";
		}
		clock += minute + "";
		if(second<10){
			clock += "0";
		}
		clock += second + "";
		return (clock);
	}
	function initMy() {
		<c:if test="${!empty param.crdacptid}" >
			var crdacptid = '<c:out value="${param.crdacptid}"/>';
			document.forms[0].crdacptid.value=crdacptid;
		</c:if>	
		<c:if test="${!empty param.termcode}" >
			var termcode = '<c:out value="${param.termcode}"/>';
			document.forms[0].termcode.value=termcode;
		</c:if>	
		<c:if test="${!empty param.pan}" >
			var pan = '<c:out value="${param.pan}"/>';
			document.forms[0].pan.value=pan;
		</c:if>	
		<c:if test="${!empty param.rrn}" >
			var rrn = '<c:out value="${param.rrn}"/>';
			document.forms[0].rrn.value=rrn;
		</c:if>	
		<c:if test="${!empty param.queryDTStart}" >
			var queryDTStart = '<c:out value="${param.queryDTStart}"/>';
			document.forms[0].queryDTStart.value=queryDTStart;
		</c:if>
		<c:if test="${empty param.queryDTStart}" >
			document.forms[0].queryDTStart.value=CurentDayTime(null);
			/* document.forms[0].queryDTStart.value=CurentDayTime("-1"); */
		</c:if>	
		<c:if test="${!empty param.queryDTEnd}" >
			var queryDTEnd = '<c:out value="${param.queryDTEnd}"/>';
			document.forms[0].queryDTEnd.value=queryDTEnd;
		</c:if>	
		<c:if test="${empty param.queryDTEnd}" >
			document.forms[0].queryDTEnd.value=CurentDayTime(null);
		</c:if>	
		<c:if test="${!empty param.txnstatus}" >
			var txnstatus = '<c:out value="${param.txnstatus}"/>';
			document.forms[0].txnstatus.value=txnstatus;
		</c:if>					
		<c:if test="${!empty param.queryTxnType}" >
			var queryTxnType = '<c:out value="${param.queryTxnType}"/>';
			document.forms[0].queryTxnType.value=queryTxnType;
		</c:if>	
		<c:if test="${!empty param.txncode}" >
			var txncode = '<c:out value="${param.txncode}"/>';
			document.forms[0].txncode.value=txncode;
		</c:if>
		<c:if test="${!empty param.crdproduct}" >
			var crdproduct = '<c:out value="${param.crdproduct}"/>';
			document.forms[0].crdproduct.value=crdproduct;
		</c:if>		
		<c:if test="${!empty param.txnsrc}" >
			var txnsrc = '<c:out value="${param.txnsrc}"/>';
			document.forms[0].txnsrc.value=txnsrc;
		</c:if>			
	}
	
	function StringToDate(s) { 
		alert("�õ��������ַ��� �� " + s); 
		alert(parseInt(s.substring(0,4),10)); 
		alert(parseInt(s.substring(5,7)-1,10)); 
		alert(parseInt(s.substring(8,10),10)); 
		alert(parseInt(s.substring(11,13),10)); 
		alert(parseInt(s.substring(14,16),10)); 
		alert(parseInt(s.substring(17,19),10)); 
		var d = new Date(); 
		d.setYear(parseInt(s.substring(0,4),10)); 
		d.setMonth(parseInt(s.substring(5,7)-1,10)); 
		d.setDate(parseInt(s.substring(8,10),10)); 
		d.setHours(parseInt(s.substring(11,13),10)); 
		d.setMinutes(parseInt(s.substring(14,16),10)); 
		d.setSeconds(parseInt(s.substring(17,19),10)); 
		return d; 
	}
	
	function getDifDays( s1,s2 ){
		var pattern = /(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})/;
		var ms = ""
		var formatedS1 = s1.replace(pattern, '$1-$2-$3 $4:$5:$6');
		var formatedS2 = s2.replace(pattern, '$1-$2-$3 $4:$5:$6');
		
		s1 = new Date(formatedS1.replace(/-/g, '/'));
        s2 = new Date(formatedS2.replace(/-/g, '/'));
        ms = Math.abs(s1.getTime() - s2.getTime());
     
        return ms / 1000 / 60 / 60/24;
	}
	
	function exportExcel(){	
		var addDT_startdate = document.forms[0].queryDTStart.value;
		var addDT_enddate = document.forms[0].queryDTEnd.value;
		
		if(addDT_startdate==''){
		    alert("�������뿪ʼʱ��");
		    window.document.forms['tlogForm'].elements['queryDTStart'].focus() ;
		    return false;			
		}
		if(addDT_enddate==''){
		    alert("�����������ʱ��");
		    window.document.forms['tlogForm'].elements['queryDTEnd'].focus() ;
		    return false;			
		}		
		if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
		    alert("����ʱ�䲻��С�ڿ�ʼʱ��");
		    window.document.forms['tlogForm'].elements['queryDTStart'].focus() ;
		    return false;
		} 
		var nums = getDifDays(addDT_startdate, addDT_enddate);
		/* var nums = (getDate(addDT_enddate)-getDate(addDT_startdate))/1000000; */
		if(nums > 30){
			alert("����ʱ�䲻�ܳ���30��");
			window.document.forms['tlogForm'].elements['queryDTStart'].focus() ;
			return false;
		}else{
			var tlogForm = document.all("tlogForm");
			tlogForm.action = "<%=path%>/tlog.do?method=exportExcelRollingPoor";
			tlogForm.submit();
		}
	}
	
	function query(){
		var addDT_startdate = document.forms[0].queryDTStart.value;
		var addDT_enddate = document.forms[0].queryDTEnd.value;
		var crdacptid = document.forms[0].crdacptid.value;
		var termcode = document.forms[0].termcode.value;
		var pan = document.forms[0].pan.value;
		if(!(pan!=''||crdacptid!=''||termcode!='')){
			alert("�̻��š��ն˺š�������������һ��");
			return false;
		}
		if(addDT_startdate==''){
		    alert("�������뿪ʼʱ��");
		    window.document.forms['tlogForm'].elements['queryDTStart'].focus() ;
		    return false;			
		}
		if(addDT_enddate==''){
		    alert("�����������ʱ��");
		    window.document.forms['tlogForm'].elements['queryDTEnd'].focus() ;
		    return false;			
		}		
		if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
		    alert("����ʱ�䲻��С�ڿ�ʼʱ��");
		    window.document.forms['tlogForm'].elements['queryDTStart'].focus() ;
		    return false;
		} 
		var nums = getDifDays(addDT_startdate, addDT_enddate);
		/* var nums = (getDate(addDT_enddate)-getDate(addDT_startdate))/1000000; */
		if(nums > 30){
			alert("����ʱ�䲻�ܳ���30��");
			window.document.forms['tlogForm'].elements['queryDTStart'].focus() ;
			return false;
		}else{
			var tlogForm = document.all("tlogForm");
			tlogForm.action = "<%=path%>/tlog.do?method=selectqueryTLogListRollingPoor";
			<%-- tlogForm.action = "<%=path%>/tlog.do?method=queryTLogListRollingPoor"; --%>
			tlogForm.submit();
		}
	}
	//dataת��
	function getDate(date){
	 var dates = date.split("-");
	 var dateReturn = '';
	 for(var i=0; i<dates.length; i++){
	  dateReturn+=dates[i];
	 }
	 return dateReturn;
	}
	function resetClick()
	{
		document.forms[0].pan.value="";
		document.forms[0].crdacptid.value="";
		document.forms[0].termcode.value="";
		document.forms[0].queryDTStart.value=CurentDayTime(null);
		document.forms[0].queryDTEnd.value=CurentDayTime(null);
	}
</script>
<shiro:lacksPermission name="cardbatch:tlogt:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="cardbatch:tlogt:view">
<body>
	<html:form action="/tlog.do?method=queryTLogList" method="post" styleId="tlogForm">
		<bean:define id="menu_level" name="menu_level" />
		<table border="0" cellpadding="0" cellspacing="0" width="100%"
			height="100%">
			<tr>
				<td align="center" valign="top" height="87%">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="28" height="10"></td>
						</tr>
						<tr>
							<td align="left" width="28" height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">

							</td>
							<td height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;��ǰλ�ã���־���� &gt; ��������ͳ��
							</td>
							<td width="7" height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td width="28" height="5" colspan="3"></td>
						</tr>
					</table>
					<table cellpadding="0" border="0" cellspacing="0" width="100%"
						 style="padding: 0px;" align="left">
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								�̻���
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="crdacptid" maxlength="15" size="16" value="${param.crdacptid}"/>
							</td>
							<td style="white-space: nowrap" align="right">
								�ն˺�
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="termcode" maxlength="16" size="16" value="${param.termcode}"/>
							</td>	
							<td style="white-space: nowrap" align="right">
								����
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="pan" maxlength="16" size="16" value="${param.pan}"/>
							</td>
							<td style="white-space: nowrap" align="right">
								����ʱ��
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="queryDTStart" maxlength="14" size="16" onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});" value="${param.queryDTStart}"/>
								-
								<html:text property="queryDTEnd" maxlength="14" size="16" onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});" value="${param.queryDTEnd}"/>
							</td>	
							<td style="white-space: nowrap" align="right">
							</td>											
						</tr>
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
							</td>
							<td style="white-space: nowrap" align="left">
							</td>
							<td style="white-space: nowrap" align="right">
							</td>
							<td style="white-space: nowrap" align="right" colspan="2">
								<input type="button" class="button" onClick='return query()'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)">
							</td>	
							<td style="white-space: nowrap" align="left" colspan="2">
								<input type="button" class="button" onClick='return resetClick()' value="���">
							</td>
							<td style="white-space: nowrap" align="right" colspan="2">
								<input type="button" class="button"
									onClick='return exportExcel()'
									style="background-image: url(<%=path%>/images/icon-import.png)">
							</td>
						</tr>
						<tr>
							<td colspan="10">
							<display:table name="tlogList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/tlog.do">
								<display:column title="����" style="text-align:center" property="pan" headerClass="sortable" sortable="true" />
								<display:column title="�̻���" style="text-align:center" property="crdacptid" headerClass="sortable" sortable="true" />
								<display:column title="�ն˺�" style="text-align:center" property="termcode" headerClass="sortable" sortable="true" />
								<display:column title="��������" style="text-align:center"  property="txncode" headerClass="sortable" sortable="true" />
								<display:column title="���׽��" style="text-align:center" property="amttxn" headerClass="sortable" sortable="true" />
								<display:column title="��������" style="text-align:center"  property="datelocal" headerClass="sortable" sortable="true"/>
								<display:column title="����ʱ��" style="text-align:center"  property="timelocal" headerClass="sortable" sortable="true"/>
							</display:table>							
							</td>
						</tr>
						<tr>
							<td style="white-space: nowrap">
								�˻��ܱ�����<%=e%>
							</td>
							<td style="white-space: nowrap">
								�˻��ܽ�<%=f%>
							</td>
							<td style="white-space: nowrap">
								�����ܱ�����<%=c%>
							</td>
							<td style="white-space: nowrap">
								�����ܽ�<%=d%>
							</td>
							<td style="white-space: nowrap">
								�����ܱ�����<%=h%>
							</td>
							<td style="white-space: nowrap">
								�����ܽ�<%=b%>
							</td>
							<td style="white-space: nowrap">
								������<%=g%>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
<script language="javascript">
initMy();
</script>
</body>
</shiro:hasPermission>

</html:html>