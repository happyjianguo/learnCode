<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%String path = request.getContextPath();%>
<%
String execdate=(String)request.getAttribute("execdate");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>显示列表页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/new_look.css" />
</head>
<script src="<%=path%>/js/sorttable.js"></script>
<script src="<%=path%>/js/eposcc.js"></script>
<script src="<%=path%>/js/calendar.js"></script>
<script language="javascript">
	function addConfirm(){
		window.location.href="<%=path %>/bmanger.do?method=preAddBManger";
	}
	function delConfirm(){
		var answer = window.confirm("您确定要删除吗？删除之后将不能恢复!");
		if(answer == true)
		{
			return true;
		}
		return false;
	}
	function query(){
		var bflowlogForm = document.all("sixbflowlogForm");
		bflowlogForm.action = "<%=path%>/sixbflowlog.do?method=querySixBFlowLogList";
		bflowlogForm.submit();
	}
	function callshellupdate(idcode){
		if(confirm("您确定要手动执行吗？")){
		    window.open('<%=path%>/sixbflowlog.do?method=execshell&amp;logid='+idcode,'手动执行shell页面','resizeable=no,dependent,width=400,height=230,left=400,top=300');
		}
	}
</script>

<shiro:lacksPermission name="housekeep:sixkaikaList:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="housekeep:sixkaikaList:view">

<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0" >
<html:form action="/sixbflowlog.do?method=getSixBFlowLogList" method="post" styleId="sixbflowlogForm">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="99%">
		<tr>
			<td align="center" valign="top"  height="87%">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				    <tr>
						<td width="28" height="10"></td>
					</tr>
					<tr>
						<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
						
						</td>
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置：批量管理 &gt; 特制批量发卡管理 </td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td> 
					</tr>
					</table>
				
			<table  cellpadding="0" cellspacing="0" width="100%" class="serch" style="padding:5px;"  align="left"  >
								<tr>
									<td width ="150" align="right" >执行日期：</td>
									<td width ="150" align="left" ><html:text property="execdate" maxlength="20" size="10" onclick="fPopCalendar(execdate,execdate);return false" readonly="true" value="<%=execdate%>"/></td>
									
									<td width ="150" height="25" align="left">
										<input type="button" class="button" onClick='return query()'  style="background-image: url(<%=path%>/image1/border/Check_button.gif)">									
									</td>
									<td height="25" align="left">
									&nbsp;									
									</td>
									
								</tr>
								
				</table>
				<div class="dlist"  style="top: 96px ;height:75%;" >
				<table  cellpadding="0" cellspacing="0" width="100%" border="0" >
					<tr  id="tl" height="35" align="center" style="top: expression(this.offsetParent.scrollTop);position: relative;border-style: none" >
						<td height="20">
							编号
						</td>
						<td>
							批处理标识
						</td>
						<td >
							处理结果
						</td>
						<td >
							执行日期
						</td>
						<td >
							执行时间
						</td>						
						<td>
							执行方式
						</td>
						<logic:match name="menu_level" value="1">
						<td>
							查看 
						</td>
						<shiro:hasPermission name="housekeep:sixkaikaList:edit">
						<td>
							手动修改
						</td>
						</shiro:hasPermission>
						

						
					</tr>
						<tbody  class="list" >
					<logic:present name="sixbflowlogList">
							<logic:iterate id="sixbflowlogBean" name="sixbflowlogList">
								<tr align="center" onMouseOver="changeClass(this,1)" onMouseOut="changeClass(this,2)">
									<td class=box2 height="28">
										<bean:write name="sixbflowlogBean" property="id"/>
									</td>
									<td class=box2 height="28">
										<bean:write name="sixbflowlogBean" property="batchname"/>&nbsp;
									</td>								 
									<td class=box2 height="28">
										<logic:equal name="sixbflowlogBean" property="issucess"	value="0">成功&nbsp;</logic:equal>
										<logic:notEqual name="sixbflowlogBean" property="issucess"	value="0">失败&nbsp;</logic:notEqual>
										
									</td>									 
									<td class=box2 height="28">
										<bean:write name="sixbflowlogBean" property="execdateday"/>&nbsp;
									</td>
									<td class=box2 height="28">
										<bean:write name="sixbflowlogBean" property="execdatemin"/>&nbsp;
									</td>
									<td class=box2 height="28">
										<logic:equal name="sixbflowlogBean" property="execflag"	value="0">自动执行&nbsp;</logic:equal>
										<logic:notEqual name="sixbflowlogBean" property="execflag"	value="0">手动执行&nbsp;</logic:notEqual>
									</td>
									<logic:match name="menu_level" value="1">
						          <td class=box2 height="28">
							             <html:link action="/sixbflowlog.do?method=showSixBFlowLogInfo"
												paramId="id" paramName="sixbflowlogBean"
												paramProperty="id">
												<img border="0" src="<%=path %>/image1/border/query.png" />
											</html:link>
						          </td>
						         <shiro:hasPermission name="housekeep:sixkaikaList:edit">
							          <td class=box2 height="28">
							          <logic:notEqual name="sixbflowlogBean" property="issucess"	value="0">							          
										 <span style="cursor:hand" onclick="callshellupdate('<bean:write name="sixbflowlogBean" property="id"/>')">手动修改</span>
									   </logic:notEqual>
									   &nbsp;
							          </td>
						          </shiro:hasPermission>					          		
								</tr>								
							</logic:iterate>						
					</logic:present>
					<logic:notPresent name="sixbflowlogList">
						<%int i=6; %>
						<logic:match name="menu_level" value="2">
						<%i++; %>
						</logic:match>						
						<tr align="center"><td class=box3 colspan="<%=i %>">暂无信息</td></tr>
					</logic:notPresent>
					</tbody>
				</table>
				</div>
			</td>
		</tr>
		<tr>
			<td align="center" valign="top" height="30">				
				<table border="0" cellpadding="0" cellspacing="0" width="98%">
 						<tr align="right">
						<td height="25">
						<logic:equal  name="queryflag"	value="0">										
							<util:page uri="/sixbflowlog.do?method=getSixBFlowLogList" />
						</logic:equal>
						<logic:equal  name="queryflag"	value="1">										
							<util:page uri="/sixbflowlog.do?method=querySixBFlowLogList" />
						</logic:equal>
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