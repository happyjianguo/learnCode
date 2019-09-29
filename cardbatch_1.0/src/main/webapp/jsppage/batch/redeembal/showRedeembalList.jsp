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
		var addDT_startdate = document.getElementsByName("starttime")[0].value;
		var addDT_enddate = document.getElementsByName("endtime")[0].value;
		if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
		    alert("结束时间不能小于开始时间");
		    window.document.forms['redeembalForm'].elements['startdate'].focus() ;
		    return false;
		} 
		var addDT_descrStartDate = document.getElementsByName("descrStartDate")[0].value;
		var addDT_descrEndDate = document.getElementsByName("descrEndDate")[0].value;
		if(addDT_descrStartDate!='' && addDT_descrEndDate!='' && (getDate(addDT_descrStartDate)-getDate(addDT_descrEndDate)>0)){
		    alert("结束时间不能小于开始时间");
		    window.document.forms['redeembalForm'].elements['descrStartDate'].focus() ;
		    return false;
		}
		
		var redeembalForm = document.all("redeembalForm");
		redeembalForm.action = "<%=path%>/redeembal.do?method=getRedeembalList";
		redeembalForm.submit();
	}
	function exportExcel(){		
		var redeembalForm = document.all("redeembalForm");
		redeembalForm.action = "<%=path%>/redeembal.do?method=exportExcel";
		redeembalForm.submit();		
		//window.open('<%=path%>/redeembal.do?method=exportExcel');		
	}
	function resetClick()
	{
		document.forms[0].father_order.value="";		
		document.forms[0].operator.value="";
		document.forms[0].starttime.value="";
		document.forms[0].endtime.value="";
		document.forms[0].descrStartDate.value="";
		document.forms[0].descrEndDate.value="";
		document.forms[0].rb_type.value="";		
		document.forms[0].batch_stat.value="";
		document.forms[0].sales_point.value="";
		document.forms[0].pan.value="";
		document.forms[0].start_period.value="";
		document.forms[0].end_period.value="";
		document.forms[0].province.value="";
		document.forms[0].city_no.value="";
		document.forms[0].zone.value="";
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
		var url = "<%=path%>/redeembal.do?method=showRedeembalInfo&id="+id+"&random=" + Math.random();
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
<shiro:lacksPermission name="cardbatch:redeembal:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="cardbatch:redeembal:view">
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form action="/redeembal.do?method=getRedeembalList" method="post"
		styleId="redeembalForm">
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
								&nbsp;&nbsp;当前位置：批量管理 &gt; 批量赎回管理
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
								操作员
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="operator" maxlength="20" size="10" />
							</td>
							<td style="white-space: nowrap" align="right">
								订单时间
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="starttime" maxlength="20" size="15"
									onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});" />
								-
								<html:text property="endtime" maxlength="20" size="15"
									onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});" />
							</td>
							<td style="white-space: nowrap" align="right">
								赎回类型
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="rb_type">
									<html:option value="">－全部－</html:option>
									<html:option value="1">实名赎回</html:option>
									<html:option value="2">卡余额赎回</html:option>
									<html:option value="3">合并赎回</html:option>
									<html:option value="4">利息赎回</html:option>
									<html:option value="5">购卡返积分赎回</html:option>
									<html:option value="6">黄金赎回</html:option>
									<html:option value="7">基金赎回</html:option>
									<html:option value="8">基金利息赎回</html:option>
									<html:option value="9">购物返积分赎回</html:option>
									<html:option value="10">联名卡送积分赎回</html:option>
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
								卡号
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="pan" maxlength="16" size="16" />
							</td>

						</tr>
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								地区
							</td>
							<td style="white-space: nowrap" align="left" >
								<logic:present name="provinList">
									<html:select property="province" onchange="getCity_no()">
										<html:option value="">－请选择－</html:option>
										<html:optionsCollection name="provinList"
											label="province_city" value="aid" />
									</html:select>
								</logic:present>
								<logic:present name="city_noList">
									<html:select property="city_no" onchange="getZone()">
										<html:option value="">－请选择－</html:option>
										<html:optionsCollection name="city_noList"
											label="province_city" value="aid" />
									</html:select>
								</logic:present>
								<html:select property="zone" style="display:none">
									<html:option value="">－请选择－</html:option>
								</html:select>
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

							<td height="25" align="left">							
							</td>							
						</tr>													
						<tr>
						<td colspan="8">
							<display:table name="openCardList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/redeembal.do">
								<display:column title="编号" style="text-align:center" property="id" headerClass="sortable" sortable="true" />
								<display:column title="交易类型" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.txncode=='00'||displayTable.txncode=='0'}">
										<c:if test="${displayTable.batch_stat=='00'}">赎回交易</c:if>
										<c:if test="${displayTable.batch_stat=='01'}">赎回通知</c:if>
										<c:if test="${displayTable.batch_stat=='03'}">赎回撤销</c:if>									
									</c:if>								
								</display:column>
								<display:column title="订单号" style="text-align:center" property="father_order" headerClass="sortable" sortable="true" />																
								<display:column title="赎回卡号" style="text-align:center" property="pan" headerClass="sortable" sortable="true" />								
								<display:column title="订单申请时间" style="text-align:center" property="txtime" headerClass="sortable" sortable="true" />
								<display:column title="订单状态" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.batch_stat=='00'}">已处理</c:if>
									<c:if test="${displayTable.batch_stat=='01'}">未处理</c:if>
									<c:if test="${displayTable.batch_stat=='02'}">已退卡</c:if>
									<c:if test="${displayTable.batch_stat=='03'}">已撤销</c:if>															
								</display:column>								
								<display:column title="赎回金额" style="text-align:center" property="amt" headerClass="sortable" sortable="true" />
								<display:column title="卡账户" style="text-align:center" property="act1" headerClass="sortable" sortable="true" />
								<display:column title="实名账户" style="text-align:center" property="act2" headerClass="sortable" sortable="true" />
								<display:column title="利息账户" style="text-align:center" property="act3" headerClass="sortable" sortable="true" />
								<display:column title="购卡返积分账户" style="text-align:center" property="act4" headerClass="sortable" sortable="true" />
								<display:column title="基金账户" style="text-align:center" property="act5" headerClass="sortable" sortable="true" />
								<display:column title="基金利息账户" style="text-align:center" property="act6" headerClass="sortable" sortable="true" />
								<display:column title="黄金账户" style="text-align:center" property="act7" headerClass="sortable" sortable="true" />
								<display:column title="积分账户" style="text-align:center" property="act8" headerClass="sortable" sortable="true" />
								<display:column title="联名卡送积分" style="text-align:center" property="act9" headerClass="sortable" sortable="true" />
								<display:column title="赎回类型" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.rb_type=='1'}">实名赎回</c:if>
									<c:if test="${displayTable.rb_type=='2'}">卡余额赎回</c:if>
									<c:if test="${displayTable.rb_type=='3'}">合并赎回</c:if>
									<c:if test="${displayTable.rb_type=='4'}">利息赎回</c:if>
									<c:if test="${displayTable.rb_type=='5'}">购卡返积分</c:if>
									<c:if test="${displayTable.rb_type=='6'}">黄金赎回</c:if>	
									<c:if test="${displayTable.rb_type=='7'}">基金赎回</c:if>										
									<c:if test="${displayTable.rb_type=='8'}">基金利息赎回</c:if>
									<c:if test="${displayTable.rb_type=='9'}">购物返积分赎回</c:if>
									<c:if test="${displayTable.rb_type=='10'}">联名卡送积分赎回</c:if>									
								</display:column>
								<display:column title="账期" style="text-align:center" property="acct_period" headerClass="sortable" sortable="true" />
								<display:column title="银行名称" style="text-align:center" property="bank_name" headerClass="sortable" sortable="true" />
								<display:column title="支行名称" style="text-align:center" property="branch_name" headerClass="sortable" sortable="true" />
								<display:column title="银行开户名" style="text-align:center" property="card_acceptor_name" headerClass="sortable" sortable="true" />
								<display:column title="银行卡号" style="text-align:center" property="bank_pan" headerClass="sortable" sortable="true" />
								<display:column title="操作员" style="text-align:center" property="operator" headerClass="sortable" sortable="true" />
								<display:column title="审批人" style="text-align:center" property="descr_u" headerClass="sortable" sortable="true" />
								<display:column title="审批日期" style="text-align:center" property="descr_t" headerClass="sortable" sortable="true" />
								<shiro:hasPermission name="cardbatch:redeembal:edit">
								<display:column title="查看" style="width:5%;text-align:center">
									<a href="javascript:;" onclick="editClicks('<c:out value="${displayTable.id}"/>')">查看</a>
								</display:column>
								</shiro:hasPermission>
							</display:table>
						</td>
						</tr>
						<tr>
							<td align="center" colspan="16">
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
