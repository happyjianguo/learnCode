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
	<title>流水查询列表</title>
</head>
<script src="<%=path%>/js/sorttable.js"></script>
<script src="<%=path%>/js/eposcc.js"></script>
<script src="<%=path%>/js/calendar.js"></script>
<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>

<script language="javascript">
	function CurentDayTime(flag) {
		var now = new Date();
		var year = now.getFullYear(); 	//年
		var month = now.getMonth() + 1; //月
		if(flag!=null&&flag=="-1"){
			month = now.getMonth(); //月
		}		
		var day = now.getDate(); 		//日
		var hour = now.getHours();		//时
		var minute = now.getMinutes();	//分
		var second = now.getSeconds();	//秒
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
		    alert("必须输入开始时间");
		    window.document.forms['tlogForm'].elements['queryDTStart'].focus() ;
		    return false;			
		}
		if(addDT_enddate==''){
		    alert("必须输入结算时间");
		    window.document.forms['tlogForm'].elements['queryDTEnd'].focus() ;
		    return false;			
		}		
		if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
		    alert("结束时间不能小于开始时间");
		    window.document.forms['tlogForm'].elements['queryDTStart'].focus() ;
		    return false;
		} 
		var nums = getDifDays(addDT_startdate, addDT_enddate);
		/* var nums = (getDate(addDT_enddate)-getDate(addDT_startdate))/1000000; */
		if(nums > 30){
			alert("交易时间不能超过30天");
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
			alert("商户号、终端号、卡号至少输入一项");
			return false;
		}
		if(addDT_startdate==''){
		    alert("必须输入开始时间");
		    window.document.forms['tlogForm'].elements['queryDTStart'].focus() ;
		    return false;			
		}
		if(addDT_enddate==''){
		    alert("必须输入结算时间");
		    window.document.forms['tlogForm'].elements['queryDTEnd'].focus() ;
		    return false;			
		}		
		if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
		    alert("结束时间不能小于开始时间");
		    window.document.forms['tlogForm'].elements['queryDTStart'].focus() ;
		    return false;
		} 
		var nums = getDifDays(addDT_startdate, addDT_enddate);
		if(nums > 30){
			alert("交易时间不能超过30天");
			window.document.forms['tlogForm'].elements['queryDTStart'].focus() ;
			return false;
		}else{
			var tlogForm = document.all("tlogForm");
			tlogForm.action = "<%=path%>/tlog.do?method=selectqueryTLogListCurrent";
			<%-- tlogForm.action = "<%=path%>/tlog.do?method=queryTLogListCurrent"; --%>
			tlogForm.submit();
		}
	}
	//data转换
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
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
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
								&nbsp;&nbsp;当前位置：日志管理 &gt; 实时流水查询
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
								商户号
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="crdacptid" maxlength="15" size="16" value="${param.crdacptid}"/>
							</td>
							<td style="white-space: nowrap" align="right">
								终端号
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="termcode" maxlength="16" size="16" value="${param.termcode}"/>
							</td>	
							<td style="white-space: nowrap" align="right">
								卡号
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="pan" maxlength="16" size="16" value="${param.pan}"/>
							</td>
							<td style="white-space: nowrap" align="right">
								交易时间
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
								主机流水号
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="rrn" maxlength="12" size="16" value="${param.rrn}"/>
							</td>
							<td style="white-space: nowrap" align="right">
								交易类型
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="queryTxnType" value="${param.queryTxnType}">
									<html:option value="">－全部－</html:option>
									<html:option value="200-0-00">200-0-00---消费</html:option>
									<html:option value="200-20-00">200-20-00---撤销</html:option>
									<html:option value="200-20-01">200-20-01---退货</html:option>
									<html:option value="200-20-99">200-20-99---退单</html:option>
									<html:option value="200-28-04">200-28-04---PAY充值</html:option>
									<html:option value="200-30-00">200-30-00---余额查询</html:option>
									<html:option value="200-40-00">200-40-00---转账</html:option>		
									<html:option value="200-90-00">200-90-00---改密</html:option>
									<html:option value="400-0-02">400-0-02---消费冲正</html:option>
									<html:option value="400-20-01">400-20-01---退货冲正</html:option>
									<html:option value="400-20-02">400-20-02---撤销冲正</html:option>
									<html:option value="500-0-93">500-0-93---赎回</html:option>
									<html:option value="500-0-94">500-0-94---换卡赎回</html:option>
									<html:option value="500-20-92">500-20-92---充值调账</html:option>
									<html:option value="500-20-98">500-20-98---非实时退卡</html:option>
									<html:option value="500-20-99">500-20-99---实时退卡</html:option>
									<html:option value="500-28-96">500-28-96---换卡充值</html:option>
									<html:option value="500-28-97">500-28-97---SALE充值</html:option>
									<html:option value="500-28-98">500-28-98---非实时开卡</html:option>
									<html:option value="500-28-99">500-28-99---实时开卡</html:option>
								</html:select>
							</td>		
							<!--<td style="white-space: nowrap" align="right">
								收单机构代码
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="aiid" maxlength="11" size="16" value="${param.aiid}"/>
							</td>	-->	
							<td style="white-space: nowrap" align="right">
								卡产品
							</td>
							<td align="left">
								<html:select property="crdproduct" value="${param.crdproduct}">
									<html:option value="">－全部－</html:option>
									<logic:present name="cardProductList">
										<html:optionsCollection name="cardProductList" label="descr" value="crdproduct" />
									</logic:present>
								</html:select>
							</td>													
							<td style="white-space: nowrap" align="right">
								交易状态
							</td>							
							<td style="white-space: nowrap" align="left">
								<html:select property="txnstatus" value="${param.txnstatus}">
									<html:option value="">－全部－</html:option>
									<html:option value="1">1---不能将交易发送给目的方</html:option>
									<html:option value="2">2---交易不存在</html:option>
									<html:option value="3">3---交易处理中</html:option>
									<html:option value="4">4---当交易未完成时被冲正</html:option>
									<html:option value="5">5---交易完成并且被拒绝</html:option>
									<html:option value="6">6---交易完成被拒绝并且被冲正</html:option>
									<html:option value="7">7---交易成功</html:option>
									<html:option value="8">8---交易成功并被冲正</html:option>
									<html:option value="9">9---无响应</html:option>
									<html:option value="10">10---交易没有相应被冲正</html:option>
									<html:option value="11">11---来自批量的交易被记录</html:option>
									<html:option value="12">12---交易成功但是没有收到来自ATM的确认</html:option>
									<html:option value="13">13---来自POS交易的撤销通知</html:option>
									<html:option value="14">14---交易暂停</html:option>
								</html:select>
							</td>	
							<td style="white-space: nowrap" align="right">
								
							</td>						
						</tr>
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								交易来源
							</td>							
							<td style="white-space: nowrap" align="left">
								<html:select property="txnsrc" value="${param.txnsrc}">
									<html:option value="">－全部－</html:option>
									<html:option value="2">2---POS</html:option>
									<html:option value="4">4---SALE</html:option>
									<html:option value="6">6---IC-POS</html:option>
									<html:option value="8">8---PAY</html:option>
									<html:option value="9">9---收银台</html:option>
								</html:select>
							</td>	
							<td style="white-space: nowrap" align="right">
								交易码
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="txncode" value="${param.txncode}">
									<html:option value="">－全部－</html:option>
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
								<input type="button" class="button" onClick='return resetClick()' value="清空">
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
								<display:column title="原始交易流水号" style="text-align:center" property="stanorg" headerClass="sortable" sortable="true" />
								<display:column title="主机流水号" style="text-align:center" property="rrn" headerClass="sortable" sortable="true" />
								<display:column title="pos流水号" style="text-align:center" property="termseq" headerClass="sortable" sortable="true" />
								<display:column title="商户号" style="text-align:center" property="crdacptid" headerClass="sortable" sortable="true" />
								<display:column title="终端号" style="text-align:center" property="termcode" headerClass="sortable" sortable="true" />
								<display:column title="卡号" style="text-align:center" property="pan" headerClass="sortable" sortable="true" />
								<display:column title="交易时间" style="text-align:center"  property="datelocal" headerClass="sortable" sortable="true"/>
								<display:column title="交易来源" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.txnsrc=='2' }">POS</c:if>
									<c:if test="${displayTable.txnsrc=='4' }">SALE</c:if>
									<c:if test="${displayTable.txnsrc=='6' }">IC-POS</c:if>
									<c:if test="${displayTable.txnsrc=='8' }">PAY</c:if>
									<c:if test="${displayTable.txnsrc=='9' }">收银台</c:if>
								</display:column>
								
								<display:column title="交易码" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.txncode=='0' }">0</c:if>
									<c:if test="${displayTable.txncode=='20' }">20</c:if>
									<c:if test="${displayTable.txncode=='28' }">28</c:if>
									<c:if test="${displayTable.txncode=='30' }">30</c:if>
									<c:if test="${displayTable.txncode=='40' }">40</c:if>
									<c:if test="${displayTable.txncode=='90' }">90</c:if>
								</display:column>

								<display:column title="交易类型" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.queryTxnType=='200-0-00' }">消费</c:if>
									<c:if test="${displayTable.queryTxnType=='200-20-00' }">撤销</c:if>
									<c:if test="${displayTable.queryTxnType=='200-20-01' }">退货</c:if>
									<c:if test="${displayTable.queryTxnType=='200-20-99' }">退单</c:if>
									<c:if test="${displayTable.queryTxnType=='200-28-04' }">PAY充值</c:if>
									<c:if test="${displayTable.queryTxnType=='200-30-00' }">余额查询</c:if>
									<c:if test="${displayTable.queryTxnType=='200-40-00' }">转账</c:if>
									<c:if test="${displayTable.queryTxnType=='200-90-00' }">改密</c:if>
									<c:if test="${displayTable.queryTxnType=='400-0-02' }">消费冲正</c:if>
									<c:if test="${displayTable.queryTxnType=='400-20-01' }">退货冲正</c:if>
									<c:if test="${displayTable.queryTxnType=='400-20-02' }">撤销冲正</c:if>
									<c:if test="${displayTable.queryTxnType=='500-0-93' }">赎回</c:if>
									<c:if test="${displayTable.queryTxnType=='500-0-94' }">换卡赎回</c:if>
									<c:if test="${displayTable.queryTxnType=='500-20-92' }">充值调账</c:if>
									<c:if test="${displayTable.queryTxnType=='500-20-98' }">非实时退卡</c:if>
									<c:if test="${displayTable.queryTxnType=='500-20-99' }">实时退卡</c:if>
									<c:if test="${displayTable.queryTxnType=='500-28-96' }">换卡充值</c:if>
									<c:if test="${displayTable.queryTxnType=='500-28-97' }">SALE充值</c:if>
									<c:if test="${displayTable.queryTxnType=='500-28-98' }">非实时开卡</c:if>
									<c:if test="${displayTable.queryTxnType=='500-28-99' }">实时开卡</c:if>
								</display:column>
								
								<display:column title="交易状态" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.txnstatus=='7' }">成功</c:if>
									<c:if test="${displayTable.txnstatus=='8' }">被冲正</c:if>
									<c:if test="${displayTable.txnstatus!='7'&&displayTable.txnstatus!='8'}">失败</c:if>
								</display:column>
								<display:column title="交易金额" style="text-align:center" property="amttxn" headerClass="sortable" sortable="true" />
								<display:column title="应答码" style="text-align:center" property="rspcode" headerClass="sortable" sortable="true" />	
								<display:column title="卡产品" style="text-align:center" property="crdproduct" headerClass="sortable" sortable="true" />
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