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

	<title>��ʾ�б�ҳ��</title>

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
		var answer = window.confirm("��ȷ��Ҫɾ����ɾ��֮�󽫲��ָܻ�!");
		if(answer == true)
		{
			return true;
		}
		return false;
	}
	function query(){
		var tlogForm = document.all("tlogForm");
		tlogForm.action = "<%=path%>/tlog.do?method=prequeryRedeembalList";
		tlogForm.submit();
	}
	function callshellupdate(idcode){
		if(confirm("��ȷ��Ҫ�ֶ�ִ����")){
		    window.open('<%=path%>/bflowlog.do?method=execshell&amp;logid='+idcode,'�ֶ�ִ��shellҳ��','resizeable=no,dependent,width=400,height=230,left=400,top=300');
		}
	}
</script>

<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0" >
<html:form action="/tlog.do?method=getRedeembalTlogList" method="post" styleId="tlogForm">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="99%">
		<tr>
			<td align="center" valign="top"  >
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				    <tr>
						<td width="28" height="10"></td>
					</tr>
					<tr>
						<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
						
						</td>
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;��ǰλ�ã��������� &gt; ������ع��� </td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td> 
					</tr>
					</table>
				
			<table  cellpadding="0" border="0" cellspacing="0" width="100%" class="serch" style="padding:1px;"  align="left"  >
						<tr>
								<td  width="85" align="right" >������</td>
								<td  align="left" ><html:text property="order_no" maxlength="20" size="16"/></td>
								
								<td   width="90" align="right" >�ۿ�����Ա</td>
								<td  align="left" ><html:text property="sale_point" maxlength="20" size="16"/></td>
															
								<td width="85" align="left" >����ʱ��</td>
								<td  align="left">
										<html:text property="starttime" maxlength="20" size="16"/>-<html:text property="endtime" maxlength="20" size="16"/>						
								</td>									
								<td align="left"></td>
							</tr>
							<tr>
								<td  width="85" align="right" >����״̬</td>
								<td  align="left" ><html:select property="state_code" >
								    <html:option value="">ȫ��</html:option>
								    <html:option value="00">δ����</html:option>
								    <html:option value="01">�Ѵ���</html:option>
								    <html:option value="02">����</html:option>									    
								</html:select></td>
								<td  width="85" align="right" >�������</td>
								<td  align="left" >
									<html:select property="rb_type">
										<html:option value="">��ȫ����</html:option>
										<html:option value="1">ʵ�����</html:option>
										<html:option value="2">��������</html:option>
										<html:option value="3">�ϲ����</html:option>
										<html:option value="4">��Ϣ���</html:option>
										<html:option value="5">�������������</html:option>
										<html:option value="6">�ƽ����</html:option>
										<html:option value="7">�������</html:option>
										<html:option value="8">������Ϣ���</html:option>
										<html:option value="9">���ﷵ�������</html:option>
										<html:option value="10">�������ͻ������</html:option>
									</html:select>
								</td>
								
								<td  idth="70"  align="right" >����</td>
								<td align="left" ><html:text property="pan" maxlength="16" size="16"/></td>									
								
								<td align="left"></td>
							</tr>
							<tr>
								<td height="25" align="left" colspan="9">
								&nbsp;									
								</td>
								
								<td  height="25" align="left">
									<input type="button" class="button" onClick='return query()'  style="background-image: url(<%=path%>/image1/border/Check_button.gif)">									
								</td>
								
								
							</tr>
								
				</table>
				<div class="dlist"  style="top: 150px ;height:75%;" >
				<table  cellpadding="0" cellspacing="0" width="100%" border="0" >
					<tr  id="tl" height="35" align="center" style="top: expression(this.offsetParent.scrollTop);position: relative;border-style: none" >
						<td height="20">
							���
						</td>						
						<td >
							����
						</td>						
						<td >
							��������
						</td>						
						<td>
							��ؽ��
						</td>						
						<td>
							����״̬
						</td>
						
						<td>
							�������
						</td>
						<td>
							��������
						</td>
						<td>
							֧������
						</td>
						<td>
							��������
						</td>
						<td>
							������Ա
						</td>
						<td>
							������Ա
						</td>						
						<logic:match name="menu_level" value="1">
						<td>
							����
						</td>
						</logic:match>
						
						
					</tr>
						<tbody  class="list" >
					<logic:present name="tlogList">
							<logic:iterate id="tlogBean" name="tlogList">
								<tr align="center" onMouseOver="changeClass(this,1)" onMouseOut="changeClass(this,2)">
									<td class=box2 height="28">
										<bean:write name="tlogBean" property="id"/>
									</td>
									<td class=box2 height="28">
										<bean:write name="tlogBean" property="pan"/>&nbsp;
									</td>								 
									<td class=box2 height="28">
										<bean:write name="tlogBean" property="txtime"/>&nbsp;
										
									</td>									 
									<td class=box2 height="28">
										<bean:write name="tlogBean" property="amt"/>&nbsp;
									</td>
									<td class=box2 height="28">
										&nbsp;
										<logic:equal  name="tlogBean" property="state_code"	value="00">δ����</logic:equal>
										<logic:equal  name="tlogBean" property="state_code"	value="01">�Ѵ���</logic:equal>
										<logic:equal  name="tlogBean" property="state_code"	value="02">�ѳ���</logic:equal>
									</td>
									<td class=box2 height="28">
										<bean:write name="tlogBean" property="rb_type"/>&nbsp;
									</td>
									<td class=box2 height="28">
										<bean:write name="tlogBean" property="bank_name"/>&nbsp;
									</td>
									<td class=box2 height="28">
										<bean:write name="tlogBean" property="branch_name"/>&nbsp;
									</td>
									<td class=box2 height="28">
										<bean:write name="tlogBean" property="card_acceptor_name"/>&nbsp;
									</td>
									<td class=box2 height="28">										
										<bean:write name="tlogBean" property="card_acceptor_name"/>&nbsp;
									</td>
									<td class=box2 height="28">
										<bean:write name="tlogBean" property="operator"/>&nbsp;
									</td>
									<td class=box2 height="28">
										
									</td>
									
									<logic:match name="menu_level" value="1">
						           <td class=box2 height="28">
							             <html:link action="/tlog.do?method=showRedeembalInfo"
												paramId="id" paramName="tlogBean"
												paramProperty="id">
												<img border="0" src="<%=path %>/image1/border/query.png" />
											</html:link>
						          </td>
						          </logic:match>
						          					          		
								</tr>								
							</logic:iterate>						
					</logic:present>
					<logic:notPresent name="tlogList">
						<%int i=12; %>
						<logic:match name="menu_level" value="2">
						<%i++; %>
						</logic:match>						
						<tr align="center"><td class=box3 colspan="<%=i %>">������Ϣ</td></tr>
					</logic:notPresent>
					</tbody>
				</table>
				</div>
			</td>
		</tr>
		<tr>
			<td align="center" valign="top" height="10">				
				<table border="0" cellpadding="0" cellspacing="0" width="98%">
 						<tr align="right">
						<td >
						<logic:equal  name="queryflag"	value="0">										
							<util:page uri="/tlog.do?method=getRedeembalList" />
						</logic:equal>
						<logic:equal  name="queryflag"	value="1">										
							<util:page uri="/tlog.do?method=queryRedeembalList" />
						</logic:equal>
						</td>
 					    </tr>
				</table>
			</td>
		</tr>
	</table>
</html:form>
</body>

</html:html>
