<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%
	String path = request.getContextPath();
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
		var addDT_startdate = document.getElementsByName("addDT_startdate")[0].value;
		var addDT_enddate = document.getElementsByName("addDT_enddate")[0].value;
		if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
		    alert("结束时间不能小于开始时间");
		    window.document.forms['terminalForm'].elements['addDT_startdate'].focus() ;
		    return false;
		} 
		var terminalForm = document.all("terminalForm");
		terminalForm.action = "<%=path%>/terminal.do?method=getTerminalList";
		terminalForm.submit();
	}
	function addConfirm()
	{
		var url = "<%=path%>/terminal.do?method=preAddTerminal&random=" + Math.random();
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
	function preModTerminal(id)
	{
		var url = "<%=path%>/terminal.do?method=preModTerminal&id="+id+"&random=" + Math.random();
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
	function preQueryTerminal(id)
	{
		var url = "<%=path%>/terminal.do?method=preQueryTerminal&id="+id+"&random=" + Math.random();
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
	function exportExcel(){
		var addDT_startdate = document.getElementsByName("addDT_startdate")[0].value;
		var addDT_enddate = document.getElementsByName("addDT_enddate")[0].value;
		if(addDT_startdate!='' && addDT_enddate!='' && (getDate(addDT_startdate)-getDate(addDT_enddate)>0)){
		    alert("结束时间不能小于开始时间");
		    window.document.forms['terminalForm'].elements['addDT_startdate'].focus() ;
		    return false;
		} 
		var terminalForm = document.all("terminalForm");
		terminalForm.action = "<%=path%>/terminal.do?method=exportExcel";
		terminalForm.submit();
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
</script>
<shiro:lacksPermission name="cardbatch:terminal:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="cardbatch:terminal:view">
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form action="/terminal?method=getTerminalList" method="post"
		styleId="terminalForm">
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
								&nbsp;&nbsp;当前位置： 商户终端管理 &gt; 终端管理
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
								<html:text property="mrchnoQ" maxlength="15" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
								商户名称
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="mrchNameQ" maxlength="20" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
								增加时间
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="addDT_startdate" maxlength="10" size="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
								-
								<html:text property="addDT_enddate" maxlength="10" size="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
								万科商户类型
								<html:select property="yard_mer_type" >
									<html:option value="">请选择</html:option>
									<html:option value="0">万科车场</html:option>
								</html:select> 
							</td>
							<td style="white-space: nowrap" align="right">
								终端状态
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="term_stat">
									<html:option value="">－请选择－</html:option>
									<html:option value="0">可用</html:option>
									<html:option value="1">停用</html:option>
									<html:option value="2">销户</html:option>
								</html:select>
							</td>
							<td style="white-space: nowrap" align="right">
								有无结算账号
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="accIsNull">
									<html:option value="">－请选择－</html:option>
									<html:option value="1">有</html:option>
									<html:option value="2">无</html:option>
								</html:select>
							</td>
							<td height="25" align="right">
								<input type="button" class="button" onClick='return query()'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)">
									
							</td>
						</tr>
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								终端号
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="termcode" maxlength="8" size="16" />
							</td>

							<td style="white-space: nowrap" align="right">
								位置
							</td>
							<td align="left">
								<html:text property="x_location" maxlength="20" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
								地区
							</td>
							<td style="white-space: nowrap" align="left">
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
								<html:select property="zone">
									<html:option value="">－请选择－</html:option>
								</html:select>
							</td>
							<td style="white-space: nowrap" align="right">
								裕福原有状态
							</td>
							<td align="left">
								<html:text property="state" maxlength="20" size="10" />
							</td>
							<td style="white-space: nowrap" align="right">
								费率(单位：%)
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="timeZoneQ" maxlength="15" size="10"/>
							</td>

							<shiro:hasPermission name="cardbatch:terminal:add">
								<td align="left">
									<input type="button" class="button"
										onClick='return addConfirm()'
										style="background-image: url(<%=path%>/image1/border/New_button.gif)">
									<a href="javascript:;" onClick='return exportExcel()'><img alt="" src="<%=path%>/images/icon-import.png"> </a>									
										
								</td>
							</shiro:hasPermission>
						</tr>
						<tr>
						<td colspan="14">
							<display:table name="terminalList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/terminal.do">
								<display:column title="终端ID" style="text-align:center" property="id" headerClass="sortable" sortable="true" />
								<display:column title="终端号" style="text-align:center" property="termcode" headerClass="sortable" sortable="true" />
								<display:column title="终端序列号" style="text-align:center"  property="termno" headerClass="sortable" sortable="true"/>
								<display:column title="终端所在位置" style="text-align:center"  property="x_location" headerClass="sortable" sortable="true"/>
								<display:column title="终端状态" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.term_stat=='0' }">可用 </c:if>
									<c:if test="${displayTable.term_stat=='1' }">停用 </c:if>
									<c:if test="${displayTable.term_stat=='2' }">销户 </c:if>
								</display:column>
								<display:column title="所属商户" style="text-align:center" property="mrchno" headerClass="sortable" sortable="true" />
								<display:column title="结算账号" style="text-align:center" property="settle_mrch_acc_id" headerClass="sortable" sortable="true" />
								<display:column title="商户账号ID" style="text-align:center" property="m_acc_id" headerClass="sortable" sortable="true" />
								<display:column title="费率(单位：%)" style="text-align:center" property="x_timezone" headerClass="sortable" sortable="true" />
								<display:column title="增加日期" style="text-align:center" property="add_date" headerClass="sortable" sortable="true" />
								<display:column title="查看" style="width:5%;text-align:center">
									<a href="javascript:;" onclick="preQueryTerminal('<c:out value="${displayTable.id}"/>')">
										<img border="0" src="<%=path%>/image1/border/query.png" />
									</a>
								</display:column>	
								<shiro:hasPermission name="cardbatch:terminal:edit">
									<display:column title="修改" style="width:5%;text-align:center">
										<c:if test="${displayTable.term_stat!='2' }">
										<a href="/terminal.do?method=preModTerminal&id=${displayTable.id}"/>修改</a>
										</c:if>
									</display:column>	
								</shiro:hasPermission>
							</display:table>
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
