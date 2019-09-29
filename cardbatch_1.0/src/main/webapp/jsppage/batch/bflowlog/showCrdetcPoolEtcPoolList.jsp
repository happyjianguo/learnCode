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
<%
	String execdate = (String) request.getAttribute("execdate");
	String countTotle=request.getAttribute("countTotle")==null?"0":(String) request.getAttribute("countTotle");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>显示列表页面</title>

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
</head>
<script src="<%=path%>/js/sorttable.js"></script>
<script src="<%=path%>/js/eposcc.js"></script>
<script src="<%=path%>/js/calendar.js"></script>
<script language="javascript">

	function Entercrdetcpool(){
		var start_etc_pan = document.getElementsByName("start_etc_pan")[0].value;
		var end_etc_pan = document.getElementsByName("end_etc_pan")[0].value;
		var answer;

		if(0!=start_etc_pan.length&&20!=start_etc_pan.length){
			alert("开始卡号必须为20位数字");	
			return false;
		}
		if(0!=end_etc_pan.length&&20!=end_etc_pan.length){
			alert("结束卡号必须为20位数字");	
			return false;
		}
		if(0!=start_etc_pan.length&&0!=end_etc_pan.length){
			if(end_etc_pan>start_etc_pan||end_etc_pan==start_etc_pan){
				answer= window.confirm("您确定要录入么？\n开始卡号："+start_etc_pan+"\n结束卡号："+end_etc_pan);
				document.getElementById("Entercrdetc").disabled=true;
			}
			if(end_etc_pan<start_etc_pan){
				alert("开始卡号不能小于结束卡号");
				return false;
			}
			
		}
		if(0==start_etc_pan.length&&0==end_etc_pan.length){
			alert("请输入开始卡号和结束卡号");
			return false;
		}

		if(0!=start_etc_pan.length&&0==end_etc_pan.length){
			alert("请输入结束卡号");
			return false;
		}
		if(0==start_etc_pan.length&&0!=end_etc_pan.length){
			alert("请输入开始卡号");
			return false;
		}
		if(answer == true)
		{
			crdetcpoolForm.action = "<%=path%>/crdetcpool.do?method=Entercrdetcpool";
			crdetcpoolForm.submit();
			return true;
		}
		return false;
	}
	function query(){
		var crdetcpoolForm = document.all("crdetcpoolForm");
		var start_etc_pan = document.getElementsByName("start_etc_pan")[0].value;
		var end_etc_pan = document.getElementsByName("end_etc_pan")[0].value;
		if(0!=start_etc_pan.length&&20!=start_etc_pan.length){
				alert("开始卡号必须为20位数字");	
				return false;
		}
		if(0!=end_etc_pan.length&&20!=end_etc_pan.length){
				alert("结束卡号必须为20位数字");	
				return false;
		}
		
		if(0!=start_etc_pan.length&&0!=end_etc_pan.length){
			if(end_etc_pan<start_etc_pan){
				alert("查询开始卡号不能小于结束卡号");
				return false;
			}
			
		}
		
		crdetcpoolForm.action = "<%=path%>/crdetcpool.do?method=preShowCrdEtcPoolList";
		crdetcpoolForm.submit();
	}


	
</script>

<shiro:lacksPermission name="housekeep:crdetcpool:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="housekeep:crdetcpool:view">

<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form action="/crdetcpool.do?method=preShowCrdEtcPoolList" method="post" styleId="crdetcpoolForm">
		<table border="0" cellpadding="0" cellspacing="0" width="100%"
			height="99%">
			<tr>
				<td align="center" valign="top" height="87%">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="28" height="10"></td>
						</tr>
						<tr>
							<td align="left" width="28" height="28"
								style="background: url(<%=path%>/image1/Navigation_bar/left1.gif)">
							</td>
							<td height="28"
								style="background: url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;当前位置：批量管理 &gt;ETC卡号管理
							</td>
							<td width="7" height="28"
								style="background: url(<%=path%>/image1/Navigation_bar/right1.gif)">
							</td>
						</tr>
					</table>
					<table cellpadding="0" border="0" cellspacing="0" width="100%"
						style="padding: 0px;" align="left">
						<tr>
							<td coslpan="4">
								<font color="red">${info}</font>
							</td>
						</tr>
						<tr>
							<td coslpan="4">
								<font color="red">${CrdEtcPoolInfo}</font>
							</td>
						</tr>
						<tr  class="serch">
							<td style="white-space: nowrap" align="right">
								<input id="Entercrdetc" type="button" value="录入"
								onClick="Entercrdetcpool()">
							</td>
							<td style="white-space: nowrap" align="right">
								开始卡号：
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="start_etc_pan" maxlength="20" size="20" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
							</td>
							<td style="white-space: nowrap" align="right">
								结束卡号：
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="end_etc_pan" maxlength="20" size="20" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
							</td>
							<td style="white-space: nowrap" align="left">
								未制卡总卡数：<%=countTotle %>
							</td>						
							<td style="white-space: nowrap" align="left">
								<input type="button" class="button" onClick='return query()'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)">
							</td>
						</tr>
						<tr>
							<td colspan="10">
								<display:table name="crdetcpoolList" partialList="true"
									size="pageBean.totalRecords" pagesize="10" style="width:100%"
									class="dpTable" cellpadding="0" cellspacing="0"
									id="displayTable" requestURI="/crdetcpool.do">
									<display:column title="卡号" style="text-align:center"
										property="etc_pan" headerClass="sortable" sortable="true" />	
									<display:column title="卡号标识" style="text-align:center"  
									headerClass="sortable" sortable="true">
										<c:if test="${displayTable.flag=='0' }">0-未制卡</c:if>
										<c:if test="${displayTable.flag=='1' }">1-已制卡 </c:if>		
									</display:column>
									<display:column title="制卡批次号" style="text-align:center"
										property="crdbatch_batch" headerClass="sortable" sortable="true" />
									<display:column title="制卡订单号" style="text-align:center"
										property="batchtask_ticket_no" headerClass="sortable" sortable="true" />
									<display:column title="增加日期 " style="text-align:center"
										property="insertdate" headerClass="sortable" sortable="true" />
									<display:column title="增加时间" style="text-align:center"
										property="inserttime" headerClass="sortable" sortable="true" />
									<display:column title="更新日期 " style="text-align:center"
										property="updatedate" headerClass="sortable" sortable="true" />
									<display:column title="更新时间" style="text-align:center"
										property="updatetime" headerClass="sortable" sortable="true" />
								</display:table>
								<input type="hidden" name="id">
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

