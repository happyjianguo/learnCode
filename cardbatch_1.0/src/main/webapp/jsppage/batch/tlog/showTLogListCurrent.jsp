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
	<title>��ˮ��ѯ�б�</title>
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
//		<c:if test="${!empty param.aiid}" >
//			var aiid = '<c:out value="${param.aiid}"/>';
//			document.forms[0].aiid.value=aiid;
//		</c:if>	
		<c:if test="${!empty param.crdproduct}" >
			var crdproduct = '<c:out value="${param.crdproduct}"/>';
			document.forms[0].crdproduct.value=crdproduct;
		</c:if>		
		<c:if test="${!empty param.txnsrc}" >
			var txnsrc = '<c:out value="${param.txnsrc}"/>';
			document.forms[0].txnsrc.value=txnsrc;
		</c:if>			
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
			tlogForm.action = "<%=path%>/tlog.do?method=exportExcel";
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
		if(nums > 30){
			alert("����ʱ�䲻�ܳ���30��");
			window.document.forms['tlogForm'].elements['queryDTStart'].focus() ;
			return false;
		}else{
			var tlogForm = document.all("tlogForm");
			tlogForm.action = "<%=path%>/tlog.do?method=selectqueryTLogListCurrent";
			<%-- tlogForm.action = "<%=path%>/tlog.do?method=queryTLogListCurrent"; --%>
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
		document.forms[0].rrn.value="";		
		document.forms[0].pan.value="";
		document.forms[0].crdacptid.value="";
		document.forms[0].termcode.value="";
		document.forms[0].queryTxnType.value="";
		document.forms[0].txncode.value="";
// 		document.forms[0].aiid.value="";		
		document.forms[0].queryDTStart.value=CurentDayTime(null);
		document.forms[0].queryDTEnd.value=CurentDayTime(null);
		document.forms[0].txnsrc.value="";
		document.forms[0].txnstatus.value="";
		document.forms[0].crdproduct.value="";
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
								&nbsp;&nbsp;��ǰλ�ã���־���� &gt; ʵʱ��ˮ��ѯ
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
								������ˮ��
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="rrn" maxlength="12" size="16" value="${param.rrn}"/>
							</td>
							<td style="white-space: nowrap" align="right">
								��������
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="queryTxnType" value="${param.queryTxnType}">
									<html:option value="">��ȫ����</html:option>
									<html:option value="200-0-00">200-0-00---����</html:option>
									<html:option value="200-20-00">200-20-00---����</html:option>
									<html:option value="200-20-01">200-20-01---�˻�</html:option>
									<html:option value="200-20-99">200-20-99---�˵�</html:option>
									<html:option value="200-28-04">200-28-04---PAY��ֵ</html:option>
									<html:option value="200-30-00">200-30-00---����ѯ</html:option>
									<html:option value="200-40-00">200-40-00---ת��</html:option>		
									<html:option value="200-90-00">200-90-00---����</html:option>
									<html:option value="400-0-02">400-0-02---���ѳ���</html:option>
									<html:option value="400-20-01">400-20-01---�˻�����</html:option>
									<html:option value="400-20-02">400-20-02---��������</html:option>
									<html:option value="500-0-93">500-0-93---���</html:option>
									<html:option value="500-0-94">500-0-94---�������</html:option>
									<html:option value="500-20-92">500-20-92---��ֵ����</html:option>
									<html:option value="500-20-98">500-20-98---��ʵʱ�˿�</html:option>
									<html:option value="500-20-99">500-20-99---ʵʱ�˿�</html:option>
									<html:option value="500-28-96">500-28-96---������ֵ</html:option>
									<html:option value="500-28-97">500-28-97---SALE��ֵ</html:option>
									<html:option value="500-28-98">500-28-98---��ʵʱ����</html:option>
									<html:option value="500-28-99">500-28-99---ʵʱ����</html:option>
								</html:select>
							</td>		
							<!--<td style="white-space: nowrap" align="right">
								�յ���������
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="aiid" maxlength="11" size="16" value="${param.aiid}"/>
							</td>	-->	
							<td style="white-space: nowrap" align="right">
								����Ʒ
							</td>
							<td align="left">
								<html:select property="crdproduct" value="${param.crdproduct}">
									<html:option value="">��ȫ����</html:option>
									<logic:present name="cardProductList">
										<html:optionsCollection name="cardProductList" label="descr" value="crdproduct" />
									</logic:present>
								</html:select>
							</td>													
							<td style="white-space: nowrap" align="right">
								����״̬
							</td>							
							<td style="white-space: nowrap" align="left">
								<html:select property="txnstatus" value="${param.txnstatus}">
									<html:option value="">��ȫ����</html:option>
									<html:option value="1">1---���ܽ����׷��͸�Ŀ�ķ�</html:option>
									<html:option value="2">2---���ײ�����</html:option>
									<html:option value="3">3---���״�����</html:option>
									<html:option value="4">4---������δ���ʱ������</html:option>
									<html:option value="5">5---������ɲ��ұ��ܾ�</html:option>
									<html:option value="6">6---������ɱ��ܾ����ұ�����</html:option>
									<html:option value="7">7---���׳ɹ�</html:option>
									<html:option value="8">8---���׳ɹ���������</html:option>
									<html:option value="9">9---����Ӧ</html:option>
									<html:option value="10">10---����û����Ӧ������</html:option>
									<html:option value="11">11---���������Ľ��ױ���¼</html:option>
									<html:option value="12">12---���׳ɹ�����û���յ�����ATM��ȷ��</html:option>
									<html:option value="13">13---����POS���׵ĳ���֪ͨ</html:option>
									<html:option value="14">14---������ͣ</html:option>
								</html:select>
							</td>	
							<td style="white-space: nowrap" align="right">
								
							</td>						
						</tr>
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								������Դ
							</td>							
							<td style="white-space: nowrap" align="left">
								<html:select property="txnsrc" value="${param.txnsrc}">
									<html:option value="">��ȫ����</html:option>
									<html:option value="2">2---POS</html:option>
									<html:option value="4">4---SALE</html:option>
									<html:option value="6">6---IC-POS</html:option>
									<html:option value="8">8---PAY</html:option>
									<html:option value="9">9---����̨</html:option>
								</html:select>
							</td>	
							<td style="white-space: nowrap" align="right">
								������
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="txncode" value="${param.txncode}">
									<html:option value="">��ȫ����</html:option>
									<html:option value="0">0</html:option>
									<html:option value="20">20</html:option>
									<html:option value="28">28</html:option>
									<html:option value="30">30</html:option>
									<html:option value="40">40</html:option>
									<html:option value="90">90</html:option>
								</html:select>
							</td>					
							<td style="white-space: nowrap" align="right" colspan="2">
								<input type="button" class="button" onClick='return query()'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)">
							</td>	
							<td style="white-space: nowrap" align="left" colspan="2">
								<input type="button" class="button" onClick='return resetClick()' value="���">
							</td>
							<td style="white-space: nowrap" align="right" colspan="2">
								<%-- <input type="button" class="button"
									onClick='return exportExcel()'
									style="background-image: url(<%=path%>/images/icon-import.png)"> --%>
							</td>
						</tr>
						<tr>
							<td colspan="10">
							<display:table name="tlogList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/tlog.do">
								<display:column title="ԭʼ������ˮ��" style="text-align:center" property="stanorg" headerClass="sortable" sortable="true" />
								<display:column title="������ˮ��" style="text-align:center" property="rrn" headerClass="sortable" sortable="true" />
								<display:column title="pos��ˮ��" style="text-align:center" property="termseq" headerClass="sortable" sortable="true" />
								<display:column title="�̻���" style="text-align:center" property="crdacptid" headerClass="sortable" sortable="true" />
								<display:column title="�ն˺�" style="text-align:center" property="termcode" headerClass="sortable" sortable="true" />
								<display:column title="����" style="text-align:center" property="pan" headerClass="sortable" sortable="true" />
								<display:column title="����ʱ��" style="text-align:center"  property="datelocal" headerClass="sortable" sortable="true"/>
								<display:column title="������Դ" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.txnsrc=='2' }">POS</c:if>
									<c:if test="${displayTable.txnsrc=='4' }">SALE</c:if>
									<c:if test="${displayTable.txnsrc=='6' }">IC-POS</c:if>
									<c:if test="${displayTable.txnsrc=='8' }">PAY</c:if>
									<c:if test="${displayTable.txnsrc=='9' }">����̨</c:if>
								</display:column>
								
								<display:column title="������" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.txncode=='0' }">0</c:if>
									<c:if test="${displayTable.txncode=='20' }">20</c:if>
									<c:if test="${displayTable.txncode=='28' }">28</c:if>
									<c:if test="${displayTable.txncode=='30' }">30</c:if>
									<c:if test="${displayTable.txncode=='40' }">40</c:if>
									<c:if test="${displayTable.txncode=='90' }">90</c:if>
								</display:column>

								<display:column title="��������" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.queryTxnType=='200-0-00' }">����</c:if>
									<c:if test="${displayTable.queryTxnType=='200-20-00' }">����</c:if>
									<c:if test="${displayTable.queryTxnType=='200-20-01' }">�˻�</c:if>
									<c:if test="${displayTable.queryTxnType=='200-20-99' }">�˵�</c:if>
									<c:if test="${displayTable.queryTxnType=='200-28-04' }">PAY��ֵ</c:if>
									<c:if test="${displayTable.queryTxnType=='200-30-00' }">����ѯ</c:if>
									<c:if test="${displayTable.queryTxnType=='200-40-00' }">ת��</c:if>
									<c:if test="${displayTable.queryTxnType=='200-90-00' }">����</c:if>
									<c:if test="${displayTable.queryTxnType=='400-0-02' }">���ѳ���</c:if>
									<c:if test="${displayTable.queryTxnType=='400-20-01' }">�˻�����</c:if>
									<c:if test="${displayTable.queryTxnType=='400-20-02' }">��������</c:if>
									<c:if test="${displayTable.queryTxnType=='500-0-93' }">���</c:if>
									<c:if test="${displayTable.queryTxnType=='500-0-94' }">�������</c:if>
									<c:if test="${displayTable.queryTxnType=='500-20-92' }">��ֵ����</c:if>
									<c:if test="${displayTable.queryTxnType=='500-20-98' }">��ʵʱ�˿�</c:if>
									<c:if test="${displayTable.queryTxnType=='500-20-99' }">ʵʱ�˿�</c:if>
									<c:if test="${displayTable.queryTxnType=='500-28-96' }">������ֵ</c:if>
									<c:if test="${displayTable.queryTxnType=='500-28-97' }">SALE��ֵ</c:if>
									<c:if test="${displayTable.queryTxnType=='500-28-98' }">��ʵʱ����</c:if>
									<c:if test="${displayTable.queryTxnType=='500-28-99' }">ʵʱ����</c:if>
								</display:column>
								
								<display:column title="����״̬" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.txnstatus=='7' }">�ɹ�</c:if>
									<c:if test="${displayTable.txnstatus=='8' }">������</c:if>
									<c:if test="${displayTable.txnstatus!='7'&&displayTable.txnstatus!='8'}">ʧ��</c:if>
								</display:column>
								<display:column title="���׽��" style="text-align:center" property="amttxn" headerClass="sortable" sortable="true" />
								<display:column title="Ӧ����" style="text-align:center" property="rspcode" headerClass="sortable" sortable="true" />	
								<display:column title="����Ʒ" style="text-align:center" property="crdproduct" headerClass="sortable" sortable="true" />
							</display:table>							
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