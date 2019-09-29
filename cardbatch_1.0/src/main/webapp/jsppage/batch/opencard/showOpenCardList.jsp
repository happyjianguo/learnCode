<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%
	String path = request.getContextPath();
	String sumAmt=request.getAttribute("sumAmt")==null?"":request.getAttribute("sumAmt").toString();
	String sumPanCount=request.getAttribute("sumPanCount")==null?"":request.getAttribute("sumPanCount").toString();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html lang="true">
<head>
	<html:base />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/new_look.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style.css" />
	<script type="text/javascript" src="<%=path%>/js/eposcc.js"></script>
	<script src="<%=path%>/js/calendar.js"></script>
	<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	
</head>
<script language="javascript">
	function query(){
		var addDT_startdate = document.getElementsByName("startdate")[0].value;
		var addDT_enddate = document.getElementsByName("enddate")[0].value;
		if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
		    alert("结束时间不能小于开始时间");
		    window.document.forms['cardopenForm'].elements['startdate'].focus() ;
		    return false;
		}
		var addDT_descrStartDate = document.getElementsByName("descrStartDate")[0].value;
		var addDT_descrEndDate = document.getElementsByName("descrEndDate")[0].value;
		if(addDT_descrStartDate!='' && addDT_descrEndDate!='' && (getDate(addDT_descrStartDate)-getDate(addDT_descrEndDate)>0)){
		    alert("结束时间不能小于开始时间");
		    window.document.forms['cardopenForm'].elements['descrStartDate'].focus() ;
		    return false;
		}
		var cardopenForm = document.all("cardopenForm");
		cardopenForm.action = "<%=path%>/opencard.do?method=getOpenCardList";
		cardopenForm.submit();	
	}
	function exportExcel(){	
		var cardopenForm = document.all("cardopenForm");
		cardopenForm.action = "<%=path%>/opencard.do?method=exportExcel";
		cardopenForm.submit();	
			
		//window.open('<%=path%>/opencard.do?method=exportExcel');	
	}
	function resetClick()
	{
		document.forms[0].father_order.value="";		
		document.forms[0].operator.value="";
		document.forms[0].startdate.value="";
		document.forms[0].enddate.value="";
		document.forms[0].descrStartDate.value="";
		document.forms[0].descrEndDate.value="";
		document.forms[0].pay_type.value="";		
		document.forms[0].batch_stat.value="";
		document.forms[0].sales_point.value="";
		document.forms[0].pan_start.value="";
		document.forms[0].pan_end.value="";
		document.forms[0].start_period.value="";
		document.forms[0].end_period.value="";
		document.forms[0].province.value="";
		document.forms[0].city_no.value="";
		document.forms[0].zone.value="";
		document.forms[0].amt_each_crd.value="";	
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

	function editClicks(id)
	{
		var url = "<%=path%>/opencard.do?method=showOpenCardInfo&id="+id+"&random=" + Math.random();
		var iWidth = 1046; //弹出窗口的宽度;
		var iHeight = 600; //弹出窗口的高度;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
		var winSize = 'height='
				+ iHeight
				+ ',width='
				+ iWidth
				+ ',top='
				+ iTop
				+ ',left='
				+ iLeft
				+ ',toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no';
		window.open(url, "", winSize);	
	}
	function checkAll(e, itemName)
	{
		var aa = document.getElementsByName(itemName);
		
		for (var i=0; i<aa.length; i++)
			aa[i].checked = e.checked;
	}
	
	function createBatchCheck(textName,itemName,method)
	{		
		document.getElementById("batchbutton").disabled=true;

	 	var aa = document.getElementsByName(itemName);
	 	
	 	var bb = document.all[textName];
	
	 	var cardopenForm = document.all("cardopenForm");
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
	 		if(confirm("确定要选中的选项吗？"))
	 		{	
	 			cardopenForm.action = "<%=path%>/opencard.do?method=createBatchCheck&msgtype=0014";
	 			cardopenForm.submit();
	 		}else{
	 			document.getElementById("batchbutton").disabled=false;
	 		}
	 	}else{
	 		alert("请确定要选中的选项");
	 		document.getElementById("batchbutton").disabled=false;
	 	}
	}
</script>
<shiro:lacksPermission name="cardbatch:openCards:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="cardbatch:openCards:view">
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form action="/opencard.do?method=getOpenCardList" method="post"
		styleId="cardopenForm">
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
								style="background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;当前位置：批量管理 &gt; 批量开卡管理
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
								订单号
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="father_order" maxlength="20" size="10" />
							</td>
							<td style="white-space: nowrap" align="right">
								售卡操作员
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="operator" maxlength="20" size="10" />
							</td>
							<td style="white-space: nowrap" align="right">
								订单时间
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="startdate" maxlength="20" size="15"
									onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});" />
								-
								<html:text property="enddate" maxlength="20" size="15"
									onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});" />
							</td>
							<td style="white-space: nowrap" align="right">
								支付方式
							</td>
							<td style="white-space: nowrap" align="left"  colspan="2">
								<html:select property="pay_type">
									<html:option value="">－请选择－</html:option>								
									<logic:present name="pay_typeList">
										<html:optionsCollection name="pay_typeList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								订单状态
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="batch_stat">	
									<html:option value="">－全部－</html:option>
									<html:option value="00">已处理</html:option>
									<html:option value="01">未处理</html:option>
									<html:option value="02">已退卡</html:option>
									<html:option value="03">已撤销</html:option>
								</html:select>
							</td>
							<td style="white-space: nowrap" align="right">
								卡产品
							</td>							
							<td height="25" align="left">
								<html:select property="crdproduct" >
									<html:option value="">－全部－</html:option>
									<logic:present name="cardProductList">
										<html:optionsCollection name="cardProductList" label="descr" value="crdproduct" />
									</logic:present>
								</html:select>
							</td>
							
							<td style="white-space: nowrap" align="right">
								开始卡号
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="pan_start" maxlength="16" size="15" />
							</td>
							<td style="white-space: nowrap" align="right">
								结束卡号
							</td>
							<td style="white-space: nowrap" align="left" colspan="2">
								<html:text property="pan_end" maxlength="16" size="15" />
							</td>
						</tr>
						<tr class="serch">
						<td style="white-space: nowrap" align="right">
								面值
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="amt_each_crd" maxlength="20" size="10" />
							</td>
							<td style="white-space: nowrap" align="right">
								地区
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="province" onchange="getCity_no()">
									<html:option value="">－请选择－</html:option>
									<logic:present name="provinList">
										<html:optionsCollection name="provinList"
											label="province_city" value="aid" />
									</logic:present>
								</html:select>
								<html:select property="city_no" onchange="getZone()">
									<html:option value="">－请选择－</html:option>
									<logic:present name="city_noList">
										<html:optionsCollection name="city_noList"
											label="province_city" value="aid" />
									</logic:present>
								</html:select>
								<html:select property="zone" style="display:none">
									<html:option value="">－请选择－</html:option>
								</html:select>
							</td>
							<td style="white-space: nowrap" align="right">
								账期
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="start_period" maxlength="20" size="15"
									onclick="WdatePicker({dateFmt:'yyyyMMdd'});" />
								-
								<html:text property="end_period" maxlength="20" size="15"
									onclick="WdatePicker({dateFmt:'yyyyMMdd'});" />
							</td>

							
							
								
							<td style="white-space: nowrap" align="right">
								审批日期
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="descrStartDate" maxlength="20" size="15"
									onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});" />
								-
								<html:text property="descrEndDate" maxlength="20" size="15"
									onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});" />
							</td>
						</tr>
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								售卡点
							</td>
							<td style="white-space: nowrap" align="left" colspan="2">
								<html:select property="sales_point">
									<html:option value="">－请选择－</html:option>								
									<logic:present name="sales_pointList">
										<html:optionsCollection name="sales_pointList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
							
							
							<td height="25" style="white-space: nowrap"align="right">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								是否全选
							</td>
							<td height="25" align="right">
								<input id=batchbutton type="button"  value="批量审核" onclick="javascript:createBatchCheck('selectItems','checkItem','createBatchCheck');"/>
							</td>
	
							<td height="25" align="right">
								<input type="button" class="button" onclick='return query()'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)" />							
							</td>	
							<td height="25" align="left">						
								<input type="button" class="button" onclick='return resetClick()'
									style="background-image: url(<%=path%>/image1/border/Clear_button.gif)" />	
							</td>								
							<td height="25" align="left">
								<a href="javascript:;" onClick='return exportExcel()'><img alt="" src="<%=path%>/images/icon-import.png"> </a>
							</td>													
						</tr>
						<tr>
						<td colspan="13">
							<display:table name="openCardList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/opencard.do">
								<display:column  title="选择" style="width:3%;text-align:center;" headerClass="sortable" sortable="true" >
								<c:if test="${displayTable.is_enablement=='1'}">
									<c:if test="${(displayTable.pay_type=='3'||displayTable.pay_type=='4'||displayTable.pay_type=='6')&&displayTable.batch_stat=='01'&&displayTable.is_enablement=='1'}">
									<%-- <c:if test="${(displayTable.pay_type=='3'||displayTable.pay_type=='4'||displayTable.pay_type=='6')&&displayTable.batch_stat=='01'}"> --%>
										<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.id}#${displayTable.father_order}#end"/>">		
									</c:if>
								</c:if>
								</display:column>
								<display:column title="编号" style="text-align:center" property="id" headerClass="sortable" sortable="true" />
								<display:column title="交易类型" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.txncode=='28'}">
										<c:if test="${displayTable.batch_stat=='00'}">开卡交易</c:if>
										<c:if test="${displayTable.batch_stat=='01'}">开卡交易</c:if>
										<c:if test="${displayTable.batch_stat=='02'}">开卡交易</c:if>
										<c:if test="${displayTable.batch_stat=='03'}">退单交易</c:if>									
									</c:if>
									<c:if test="${displayTable.txncode=='20'}">
										<c:if test="${displayTable.batch_stat=='00'}">退卡交易</c:if>
										<c:if test="${displayTable.batch_stat=='01'}">开卡交易</c:if>
										<c:if test="${displayTable.batch_stat=='02'}">开卡交易</c:if>
										<c:if test="${displayTable.batch_stat=='03'}">退单交易</c:if>									
									</c:if>								
								</display:column>
								<display:column title="订单号" style="text-align:center" property="father_order" headerClass="sortable" sortable="true" />								
								<display:column title="开始卡号" style="text-align:center" property="pan_start" headerClass="sortable" sortable="true" />
								<display:column title="结束卡号" style="text-align:center" property="pan_end" headerClass="sortable" sortable="true" />
								<display:column title="数量" style="text-align:center" property="pan_count" headerClass="sortable" sortable="true" />
								<display:column title="支付方式" style="text-align:center" property="pay_type_desc" headerClass="sortable" sortable="true" />
								<display:column title="面值" style="text-align:center" property="amt_each_crd" headerClass="sortable" sortable="true" />
								<display:column title="订单状态" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.batch_stat=='00'}">已处理</c:if>
									<c:if test="${displayTable.batch_stat=='01'}">未处理</c:if>
									<c:if test="${displayTable.batch_stat=='02'}">已退卡</c:if>
									<c:if test="${displayTable.batch_stat=='03'}">已撤销</c:if>															
								</display:column>
								<display:column title="账期" style="text-align:center" property="acct_period" headerClass="sortable" sortable="true" />
								<display:column title="售卡点" style="text-align:center" property="sales_point" headerClass="sortable" sortable="true" />
								<display:column title="售卡人员" style="text-align:center" property="operator" headerClass="sortable" sortable="true" />
								<display:column title="订单日期" style="text-align:center" property="time" headerClass="sortable" sortable="true" />
								<display:column title="审批人" style="text-align:center" property="descr_u" headerClass="sortable" sortable="true" />
								<display:column title="审批日期" style="text-align:center" property="descr_t" headerClass="sortable" sortable="true" />
								<shiro:hasPermission name="cardbatch:openCards:edit">
								<display:column title="查看" style="width:5%;text-align:center">
									<a href="javascript:;" onclick="editClicks('<c:out value="${displayTable.id}"/>')">查看</a>
								</display:column>
								</shiro:hasPermission>
							</display:table>
							<!-- 用Diaplay Tag来显示 -->		
					
							<input type="hidden" name="id" />
							<input type="hidden" name="selectItems" />
						</td>
						</tr>
						<tr>
							<td align="center" colspan="3">
								合计数量：<%=sumPanCount%>
							</td>
							<td align="center" colspan="3">
								合计金额：<%=sumAmt%>
							</td>
						</tr>						
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</shiro:hasPermission>
</html:html>
