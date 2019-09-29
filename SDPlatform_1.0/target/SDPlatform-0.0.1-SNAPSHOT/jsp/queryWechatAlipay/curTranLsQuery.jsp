<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>支付宝微信流水查询列表</title>
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
	function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式    
	    var  aDate,  oDate1,  oDate2,  iDays;    
	    aDate  =  sDate1.split("-");    
	    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);    //转换为12-18-2006格式    
	    aDate  =  sDate2.split("-");    
	    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);    
	    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24);    //把相差的毫秒数转换为天数   
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
			alert('请输入查询的商户编号和日期');
			return false;
		}else{
			var endd = endDate.substring(0,4)+"-"+endDate.substring(4,6)+"-"+endDate.substring(6,8);
			var startt = startDate.substring(0,4)+"-"+startDate.substring(4,6)+"-"+startDate.substring(6,8);
			var num = DateDiff(endd,startt);
			if(num > 90){
				alert("请查询90天以内的数据");
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
			alert('请输入查询的商户编号和日期');
			return false;
		}else{
			var endd = endDate.substring(0,4)+"-"+endDate.substring(4,6)+"-"+endDate.substring(6,8);
			var startt = startDate.substring(0,4)+"-"+startDate.substring(4,6)+"-"+startDate.substring(6,8);
			var num = DateDiff(endd,startt);
			if(num > 90){
				alert("请查询90天以内的数据");
				return false;
			}
		}
		document.forms[0].method.value="queryAll";
		return true;
	}
	//删除后面空格
  	String.prototype.RTrim   =   function(){   
  		return   this.replace(/(\s*$)/g,"");   
  	} 
	</script>
	
</head>

<shiro:lacksPermission name="posp:queryWechatAlipay:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:queryWechatAlipay:view">
<body onload="init()">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				流水列表
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
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								商户编号:
							</td>
							<td class="table2_td" width="15%">
								<input type="text" name="_merchantId"  maxlength="15"  style="width:135px;" onkeyup="this.value=this.value.replace(/\D/g,'');" />
							</td>
							<td class="table2_td_title" width="10%">
								系统终端号:
							</td>
							<td class="table2_td" width="15%">
								<input type="text" name="_terminalId"  maxlength="8"  style="width:135px;" />
							</td>
							<td class="table2_td_title">
								参考号:
							</td>
							<td class="table2_td">
								<input type="text" name="_tranRrn"  maxlength="12"  style="width:135px;" onkeyup="this.value=this.value.replace(/\D/g,'');" />
							</td>
							<td class="table2_td_title">
								聚合条码:
							</td>
							<td class="table2_td">
								<input type="text" name="_scanCode"  maxlength="32"  style="width:135px;" onkeyup="this.value=this.value.replace(/\D/g,'');" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								交易系统时间:
							</td>
							<td class="table2_td" width="20%">
								<input name="_startDate" size="19" maxlength="12" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" readonly="true"/>
							</td>
							<td class="table2_td_title">
								至
							</td>
							<td class="table2_td" width="20%">
								<input name="_endDate" size="19" maxlength="12" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" readonly="true"/>
							</td>
							<td class="table2_td_title">
								状态:
							</td>
							<td class="table2_td">
								<select name="_queryType">
									<option value = "">请选择</option>
									<option value = "200">成功</option>
									<option value = "520">失败</option>
								</select>
							</td>
							<td class="table2_td_title">
								交易类型:
							</td>
							<td class="table2_td">
								<select name="_tranType">
									<option value = "">请选择</option>
									<option value = "1">消费</option>
									<option value = "9">退货</option>
									<option value = "41">冲正</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="8" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="查询" width="65" height="20" border="0" onclick="return queryAll();">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" onclick="resetClick()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="清空" width="65" height="20" border="0"></a>
							</td>
						</tr>
					</table>
					<!----------Table为查询form结束-------->
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table3_border">
						<tr>
							<td class="table3_title">
								查询结果
							</td>
						</tr>
					</table>
					<!-- 用Diaplay Tag来显示 -->
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/queryWechatAlipay.do">
						<display:column title="商户号" style="width:10%;text-align:center" property="merchantId" headerClass="sortable" sortable="true" />
						<display:column title="终端号" style="width:6%;text-align:center" property="terminalId" headerClass="sortable" sortable="true" />
						<display:column title="参考号" style="width:6%;text-align:center" property="tranRrn" headerClass="sortable" sortable="true" />
						<%-- <display:column title="交易类型" style="width:6%;text-align:center" property="tranType" headerClass="sortable" sortable="true" /> --%>
						<display:column title="交易类型" style="width:6%;text-align:center" headerClass="sortable" sortable="true" >
							<c:if test="${displayTable.tranType eq 1}">
								消费
							</c:if>
							<c:if test="${displayTable.tranType eq 9}">
							 	退货
							</c:if>
							<c:if test="${displayTable.tranType eq 41}">
								冲正
							</c:if>
						</display:column>
						<display:column title="业务订单号" style="width:12%;text-align:center" property="sysOrderId" headerClass="sortable" sortable="true" />
						<display:column title="原业务订单号 " style="width:12%;text-align:center" property="sysVoidOrderId" headerClass="sortable"  sortable="true" />
						<display:column title="订单明细 " style="width:5%;text-align:center" property="sysOrderDtl" headerClass="sortable" sortable="true" />
						<display:column title="交易金额" style="width:5%;text-align:center" property="tranAmt" headerClass="sortable" sortable="true" />
						<display:column title="原交易金额 " style="width:5%;text-align:center" property="tranVoidAmt" headerClass="sortable" sortable="true" />
						<display:column title="时间戳" style="width:5%;text-align:center" headerClass="sortable" sortable="true" >
							<c:out value="${displayTable.sysTimeStamp}"/>
						</display:column>
						<display:column title="交易结果 " style="width:15%;text-align:center" property="acqRespMsg" headerClass="sortable" sortable="true" />
						<%-- <display:column title="交易状态 " style="width:5%;text-align:center" property="acqRespCode" headerClass="sortable" sortable="true" /> --%>
					</display:table>
					
					<!-- 用Diaplay Tag来显示 -->		
			</td>
		</tr>
		<tr>
			<td class="table2_td_title" align="right">
				<shiro:hasPermission name="posp:queryWechatAlipay:export">
				<input type="button" value="流水导出" onclick="exportCurTranLs();"/>
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