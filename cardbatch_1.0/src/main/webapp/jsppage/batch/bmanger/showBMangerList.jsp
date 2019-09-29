
<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%String path = request.getContextPath();%>

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
</script>

<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0" >
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
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置：批处理管理 &gt; 批处理维护 </td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
					</table>
				<table border="0" cellpadding="0" cellspacing="0"  width="100%" class="serch" style="padding:7px;" >
				    
					
					<logic:match name="menu_level" value="1">
					<tr>
						<td height="25" align="left">
										<input type="button" class="button" onClick='return addConfirm()'     style="background-image: url(<%=path%>/image1/border/New_button.gif)">
										
									</td>
					</tr>
					</logic:match>
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
							批处理名称
						</td>
						<td >
							添加时间
						</td>						
						<td>
							用户组号
						</td>
						<logic:match name="menu_level" value="1">
						<td>
							查看 
						</td>
						</logic:match>
						<logic:match name="menu_level" value="2">
						<td>
							修改
						</td>
						</logic:match>
						<logic:match name="menu_level" value="3">
						<td>
							删除
						</td>
						</logic:match>
					</tr>
						<tbody  class="list" >
					<logic:present name="bmangerList">
							<logic:iterate id="bmangerBean" name="bmangerList">
								<tr align="center" onMouseOver="changeClass(this,1)" onMouseOut="changeClass(this,2)">
									<td class=box2 height="28">
										<bean:write name="bmangerBean" property="id"/>
									</td>
									<td class=box2 height="28">
										<bean:write name="bmangerBean" property="batchflag"/>&nbsp;
									</td>								 
									<td class=box2 height="28">
										<bean:write name="bmangerBean" property="batchname"/>&nbsp;
									</td>									 
									<td class=box2 height="28">
										<bean:write name="bmangerBean" property="addtime"/>&nbsp;
									</td>
									<td class=box2 height="28">
										<bean:write name="bmangerBean" property="dept_no"/>&nbsp;
									</td>
									<logic:match name="menu_level" value="1">
						          <td class=box2 height="28">
							             <html:link action="/bmanger.do?method=showBMangerInfo"
												paramId="id" paramName="bmangerBean"
												paramProperty="id">
												<img border="0" src="<%=path %>/image1/border/query.png" />
											</html:link>
						          </td>
						          </logic:match>
						          <logic:match name="menu_level" value="2">
						          <td class=box2 height="28">
							          <html:link action="/bmanger?method=preModBMangerInfo"
													paramId="id" paramName="bmangerBean"
													paramProperty="id">
													<img border="0" src="<%=path %>/image1/border/mod.png" />
												</html:link>
						          </td>
						          </logic:match>
						          <logic:match name="menu_level" value="3">
						             <td onclick="return delConfirm();" class=box2 height="28">
						             <a href="/housekeep/bmanger.do?method=delBMangerInfo&id=<bean:write name='bmangerBean' property='id'/>&batchflag=<bean:write name='bmangerBean' property='batchflag'/>">							             
													<img border="0" src="<%=path %>/image1/border/del.png" />
									 </a>

						            </td>
						          </logic:match>			
								</tr>
								
							</logic:iterate>
						
					</logic:present>
					<logic:notPresent name="bmangerList">
						<%int i=6; %>
						<logic:match name="menu_level" value="2">
						<%i++; %>
						</logic:match>
						<logic:match name="menu_level" value="3">
						<%i++; %>
						</logic:match>
						<tr align="center"><td  class=box3 colspan="<%=i %>">暂无信息</td></tr>
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
							<util:page uri="/bmanger.do?method=getBMangerList" />
						</td>
 					    </tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html:html>
