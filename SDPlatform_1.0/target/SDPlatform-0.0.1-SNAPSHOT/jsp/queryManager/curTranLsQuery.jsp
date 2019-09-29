<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>流水查询列表</title>
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
		<c:if test="${!empty param._traceNo}" >
		var _traceNo = '<c:out value="${param._traceNo}"/>';
		document.forms[0]._traceNo.value=_traceNo;
		</c:if>	
		<c:if test="${!empty param._batchNo}" >
		var _batchNo = '<c:out value="${param._batchNo}"/>';
		document.forms[0]._batchNo.value=_batchNo;
		</c:if>	
		<c:if test="${!empty param._cardNo}" >
		var _cardNo = '<c:out value="${param._cardNo}"/>';
		document.forms[0]._cardNo.value=_cardNo;
		</c:if>	
		<c:if test="${!empty param._startDate}" >
		var _startDate = '<c:out value="${param._startDate}"/>';
		document.forms[0]._startDate.value=_startDate;
		</c:if>	
		/* <c:if test="${!empty param._endDate}" >
		var _endDate = '<c:out value="${param._endDate}"/>';
		document.forms[0]._endDate.value=_endDate;
		</c:if>	 */
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
		<c:if test="${!empty param._startTime}" >
		var _startTime = '<c:out value="${param._startTime}"/>';
		document.forms[0]._startTime.value=_startTime;
		</c:if>	
		<c:if test="${!empty param._endTime}" >
		var _endTime = '<c:out value="${param._endTime}"/>';
		document.forms[0]._endTime.value=_endTime;
		</c:if>	
		<c:if test="${!empty param._merchantId}" >
		var _merchantId = '<c:out value="${param._merchantId}"/>';
		document.forms[0]._merchantId.value=_merchantId;
		</c:if>	
		<c:if test="${!empty param._tranType}" >
		var _tranType = '<c:out value="${param._tranType}"/>';
		document.forms[0]._tranType.value=_tranType;
		</c:if>	
		<c:if test="${!empty param._terminalId}" >
		var _terminalId = '<c:out value="${param._terminalId}"/>';
		document.forms[0]._terminalId.value=_terminalId;
		</c:if>	
		<c:if test="${!empty param._queryType}" >
		var _queryType = '<c:out value="${param._queryType}"/>';
		document.forms[0]._queryType.value=_queryType;
		</c:if>	
		<c:if test="${!empty param._bankBatchNo}" >
		var _bankBatchNo = '<c:out value="${param._bankBatchNo}"/>';
		document.forms[0]._bankBatchNo.value=_bankBatchNo;
		</c:if>	
		<c:if test="${!empty param._bankTraceNo}" >
		var _bankTraceNo = '<c:out value="${param._bankTraceNo}"/>';
		document.forms[0]._bankTraceNo.value=_bankTraceNo;
		</c:if>	
		<c:if test="${!empty param._bankRan}" >
		var _bankRan = '<c:out value="${param._bankRan}"/>';
		document.forms[0]._bankRan.value=_bankRan;
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
	function openTrancNo(merchantId,terminalId,batchNo,traceNo,localSysDate,localSysTime)
	{
		window.showModalDialog("queryCurTranLs.do?method=queryDetail&read=true&_merchantId="+merchantId+"&_terminalId="+terminalId+"&_batchNo="+batchNo+"&_traceNo="+traceNo+"&localSysDate="+localSysDate+"&localSysTime="+localSysTime, "_blank", 'dialogWidth=800px;dialogHeight=440px;scroll=yes;resizable=no;status=no;center=yes;');
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

<shiro:lacksPermission name="posp:queryCurTranLs:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:queryCurTranLs:view">
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
				<html:form action="/queryCurTranLs" method="post">
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
							<td class="table2_td_title" width="10%">
								系统批次号:
							</td>
							<td class="table2_td" width="15%">
								<input type="text" name="_batchNo"  maxlength="6"  style="width:135px;" />
							</td>
							<td class="table2_td_title" width="10%">
								系统流水号:
							</td>
							<td class="table2_td" width="15%">
								<input type="text" name="_traceNo"  maxlength="6"  style="width:135px;" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								银行批次号:
							</td>
							<td class="table2_td">
								<input type="text" name="_bankBatchNo"  maxlength="6"  style="width:135px;" />
							</td>
							<td class="table2_td_title">
								银行参考号:
							</td>
							<td class="table2_td">
								<input type="text" name="_bankRan"  maxlength="12"  style="width:135px;" />
							</td>
							<!-- <td class="table2_td_title">
								银行流水号:
							</td>
							<td class="table2_td">
								<input type="text" name="_bankTraceNo"  maxlength="6"  style="width:135px;" />
							</td>
							<td class="table2_td_title">
							</td>
							<td class="table2_td">
							</td> -->
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
						</tr>	
<%--						<tr>--%>
<%--							--%>
<%--							<td class="table2_td_title" width="10%">--%>
<%--								交易系统时间:--%>
<%--							</td>--%>
<%--							<td class="table2_td" width="40%">--%>
<%--							<input name="_startTime" size="19" maxlength="12" onclick="WdatePicker({dateFmt:'HHmmss'})" class="Wdate" readonly="true"/>--%>
<%--							至--%>
<%--							<input name="_endTime" size="19" maxlength="12" onclick="WdatePicker({dateFmt:'HHmmss'})" class="Wdate" readonly="true"/>--%>
<%--							</td>--%>
<%--						</tr>		--%>
						<tr>
							<!-- <td class="table2_td_title" >
								交易日期:
							</td>
							<td class="table2_td" width="20%">
							<input name="_startDate" size="19" maxlength="12" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" readonly="true"/>
							</td> -->
							<td class="table2_td_title">
								银行流水号:
							</td>
							<td class="table2_td">
								<input type="text" name="_bankTraceNo"  maxlength="6"  style="width:135px;" />
							</td>
							<td class="table2_td_title">
								状态:
							</td>
							<td class="table2_td">
								<select name="_queryType">
									<option value = "0">成功</option>
									<option value = "1">失败</option>
								</select>
							</td>
							<td class="table2_td_title">
								卡号:
							</td>
							<td class="table2_td">
								<input type="text" name="_cardNo"  maxlength="20"  style="width:135px;" />
							</td>
							<td class="table2_td_title">
								交易类型:
							</td>
							<td class="table2_td">
								<select name="_tranType">
									<option value = "">--请选择--</option>
									<c:forEach items="${tranTypeList}" var="model">
										<option value = "${model.paramValue }">${model.id.paramName }</option>
									</c:forEach>
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/queryCurTranLs.do">
						<display:column title="商户编号" style="width:10%;text-align:center" property="merchantId" headerClass="sortable" sortable="true" />
						<display:column title="终端号" style="width:6%;text-align:center" property="terminalId" headerClass="sortable" sortable="true" />
						<display:column title="批次号" style="width:6%;text-align:center" property="batchNo" headerClass="sortable" sortable="true" />
						<display:column title="流水号" style="width:6%;text-align:center" property="traceNo" headerClass="sortable" sortable="true" />
						<display:column title="卡号" style="width:12%;text-align:center" property="cardNo" headerClass="sortable" sortable="true" />
						<display:column title="类型 " style="width:5%;text-align:center" headerClass="sortable" sortable="true">
							<c:forEach var="model" items="${tranTypeList}">
								<c:if test="${displayTable.tranType eq model.paramValue}">
									${model.paramChinese}
								</c:if>
							</c:forEach>
						</display:column>
						<display:column title="金额 " style="width:5%;text-align:center" property="tranAmt" headerClass="sortable" sortable="true" />
						<display:column title="日期" style="width:5%;text-align:center" headerClass="sortable" sortable="true" >
							<c:out value="${displayTable.localSysDate}"/>
						</display:column>
						<display:column title="时间 " style="width:5%;text-align:center" headerClass="sortable" sortable="true" >
							<c:out value="${displayTable.localSysTime}"/>
						</display:column>
						<display:column title="操作" style="width:5%;text-align:center">
						<shiro:hasPermission name="posp:queryCurTranLs:detailQueryDay">
						<a href="javascript:;" onclick="openTrancNo('${displayTable.merchantId}','${displayTable.terminalId}','${displayTable.batchNo}','${displayTable.traceNo }','${displayTable.localSysDate }','${displayTable.localSysTime }')" >详细</a>
						</shiro:hasPermission>
						</display:column>
					</display:table>
					
					<!-- 用Diaplay Tag来显示 -->		
			</td>
		</tr>
		<tr>
			<td class="table2_td_title" align="right">
				<shiro:hasPermission name="posp:queryCurTranLs:export">
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