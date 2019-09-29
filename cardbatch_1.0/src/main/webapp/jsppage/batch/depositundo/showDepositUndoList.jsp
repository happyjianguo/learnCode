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
	//去左右空格;
	function trim(s){
	    return s.replace(/(^\s*)|(\s*$)/g, "");
	}
	function isNull(data){ 
		var datas = trim(data);
		return (datas == "" || datas == undefined || datas == null) ? false : true; 
	}
	function query(){
		var father_order = document.getElementsByName("reserved1")[0].value;
		if(isNull(father_order)){
			var depositUndoForm = document.all("depositUndoForm");
			depositUndoForm.action = "<%=path%>/depositUndo.do?method=getDepositUndoList";
			depositUndoForm.submit();
		}else{
			alert("批次号不能为空");
		}
	}
	function resetClick()
	{
		document.forms[0].reserved1.value="";		
	}	
	function editClicks(id)
	{
		var url = "<%=path%>/depositUndo.do?method=showDepositUndoInfo&id="+id+"&random=" + Math.random();
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
	function batchDepositUndo(){
		var father_order = document.getElementsByName("reserved1")[0].value;
		if(isNull(father_order)){
			batchDepositUndos(father_order);
		}else{
			alert("批次号不能为空");
		}
	}
	function batchDepositUndos(id)
	{
		var url = "<%=path%>/depositUndo.do?method=batchDepositUndo&id="+id+"&random=" + Math.random();
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
	
</script>
<shiro:lacksPermission name="cardbatch:depositUndo:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="cardbatch:depositUndo:view">
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form action="/depositUndo.do?method=getDepositUndoList" method="post" styleId="depositUndoForm">
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
								&nbsp;&nbsp;当前位置：批量管理 &gt; 批量充值撤销</td>
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
								批次编号：
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="reserved1" maxlength="50" size="40" />
								<span class="help-inline"><font color="red">*</font> </span>
							</td>
							<td style="white-space: nowrap" align="right">
							</td>
							<td height="25" align="right">
								<input type="button"  value="批量充值撤销" onclick='return batchDepositUndo()' />
							</td>	
							<td height="25" align="right">
								<input type="button" class="button" onclick='return query()'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)" />							
							</td>	
							<td height="25" align="left">						
								<input type="button" class="button" onclick='return resetClick()'
									style="background-image: url(<%=path%>/image1/border/Clear_button.gif)" />	
							</td>			
						</tr>
						<tr>
						<td colspan="13">
							<display:table name="openCardList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/deposit.do">
								<display:column title="编号" style="text-align:center" property="id" headerClass="sortable" sortable="true" />
								<display:column title="交易类型" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.txncode=='28'}">
										<c:if test="${displayTable.batch_stat=='00'}">充值交易</c:if>
										<c:if test="${displayTable.batch_stat=='01'}">充值交易</c:if>
										<c:if test="${displayTable.batch_stat=='02'}">充值交易</c:if>
										<c:if test="${displayTable.batch_stat=='03'}">充值交易</c:if>									
									</c:if>
									<c:if test="${displayTable.txncode=='20'}">
										<c:if test="${displayTable.batch_stat=='00'}">充值冲正</c:if>
										<c:if test="${displayTable.batch_stat=='01'}">充值冲正</c:if>
										<c:if test="${displayTable.batch_stat=='02'}">充值冲正</c:if>
										<c:if test="${displayTable.batch_stat=='03'}">充值冲正</c:if>									
									</c:if>								
								</display:column>
								<display:column title="订单号" style="text-align:center" property="father_order" headerClass="sortable" sortable="true" />								
								<display:column title="开始卡号" style="text-align:center" property="pan_start" headerClass="sortable" sortable="true" />
								<display:column title="结束卡号" style="text-align:center" property="pan_end" headerClass="sortable" sortable="true" />
								<display:column title="数量" style="text-align:center" property="pan_count" headerClass="sortable" sortable="true" />
								<display:column title="充值金额" style="text-align:center" property="amt_each_crd" headerClass="sortable" sortable="true" />
								<display:column title="支付方式" style="text-align:center" property="pay_type_desc" headerClass="sortable" sortable="true" />
								<display:column title="订单状态" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.batch_stat=='00'}">已处理</c:if>
									<c:if test="${displayTable.batch_stat=='01'}">未处理</c:if>
									<c:if test="${displayTable.batch_stat=='02'}">已冲正</c:if>
									<c:if test="${displayTable.batch_stat=='03'}">已撤销</c:if>															
								</display:column>
								<display:column title="充值类型" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.cashin_type=='1'}">普通福卡充值 </c:if>	       
									<c:if test="${displayTable.cashin_type=='2'}">信用卡还款 </c:if>		  
									<c:if test="${displayTable.cashin_type=='3'}">积分计划充值 </c:if>		
									<c:if test="${displayTable.cashin_type=='4'}">购卡返还积分充值 </c:if>   
									<c:if test="${displayTable.cashin_type=='5'}">&nbsp; </c:if>			
									<c:if test="${displayTable.cashin_type=='6'}">商旅卡首次充值 </c:if>	
									<c:if test="${displayTable.cashin_type=='7'}">喜卡充值 </c:if>			
									<c:if test="${displayTable.cashin_type=='9'}">中信积分充值 </c:if>		
									<c:if test="${displayTable.cashin_type=='10'}">福卡积分充值 </c:if>		
									<c:if test="${displayTable.cashin_type=='11'}">实名账户充值 </c:if>		
									<c:if test="${displayTable.cashin_type=='12'}">购物返积分充值 </c:if>																
								</display:column>								
								<display:column title="账期" style="text-align:center" property="acct_period" headerClass="sortable" sortable="true" />
								<display:column title="销售点" style="text-align:center" property="sales_point" headerClass="sortable" sortable="true" />
								<display:column title="销售人员" style="text-align:center" property="operator" headerClass="sortable" sortable="true" />
								<display:column title="订单日期" style="text-align:center" property="time" headerClass="sortable" sortable="true" />
								<display:column title="审批人" style="text-align:center" property="descr_u" headerClass="sortable" sortable="true" />
								<display:column title="审批日期" style="text-align:center" property="descr_t" headerClass="sortable" sortable="true" />
								<shiro:hasPermission name="cardbatch:depositUndo:edit">
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

