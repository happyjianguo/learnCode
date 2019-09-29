<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>

<%
	String path = request.getContextPath();
	String txtype = "";
	if(request.getAttribute("txtype")!=null){
		txtype = request.getAttribute("txtype").toString();
	}
	//System.out.println(txtype);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html lang="true">
<head>
	<html:base />
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
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
	<title>��־�����б���ʾ</title>
</head>
<script src="<%=path%>/js/sorttable.js"></script>
<script src="<%=path%>/js/eposcc.js"></script>
<script src="<%=path%>/js/calendar.js"></script>
<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
	
	function exportExcel(){
		var salestlogForm = document.all("salestlogForm");
		salestlogForm.action = "<%=path%>/salestlog.do?method=exportExcel&txtype="+"<%=txtype%>";
		salestlogForm.submit();
	}
	
	function query(){
		var salestlogForm = document.all("salestlogForm");
		salestlogForm.action = "<%=path%>/salestlog.do?method=preQuerySalestlogList&txtype="+"<%=txtype%>";
		salestlogForm.submit();
	}

</script>
<shiro:lacksPermission name="cardbatch:salesTLog:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="cardbatch:salesTLog:view">
<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<html:form action="/salestlog.do?method=getSalestlogList" method="post"
		styleId="salestlogForm">
		<html:hidden property="txtype" />
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
								<c:if test="${txtype=='289796' }">
									&nbsp;&nbsp;��ǰλ�ã���־���� &gt; ������ֵ��־
								</c:if>
								<c:if test="${txtype=='289998' }">
									&nbsp;&nbsp;��ǰλ�ã���־���� &gt; ����������־
								</c:if>
								<c:if test="${txtype=='009493' }">
									&nbsp;&nbsp;��ǰλ�ã���־���� &gt; ���������־
								</c:if>
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
						class="serch" style="padding: 1px;" align="left">
						<tr>
							<td width="85" align="right">
								��������
							</td>
							<td align="left">
								<html:text property="father_order" maxlength="20" size="16" />
							</td>

							<td width="90" align="right">
								�ۿ�����Ա
							</td>
							<td align="left">
								<html:text property="verifier" maxlength="20" size="16" />
							</td>

							<td width="85" align="left">
								����ʱ��
							</td>
							<td align="left">
								<html:text property="starttime" maxlength="20" size="16" onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});"/>
								-
								<html:text property="endtime" maxlength="20" size="16" onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});"/>
							</td>
							<td align="left"></td>
						</tr>
						<tr>
							<td width="85" align="right">
								����״̬
							</td>
							<td align="left">
								<html:select property="stxnstat">
									<html:option value="">
										ȫ��
									</html:option>
									<html:option value="0">
										����
									</html:option>
									<html:option value="8">
										����
									</html:option>
								</html:select>
							</td>
							<td width="70" align="right">
								����
							</td>
							<td align="left">
								<html:text property="pan" maxlength="16" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
								����Ʒ
							</td>
							<td  align="left">
								<html:select property="crdproduct" >
									<html:option value="">��ȫ����</html:option>
									<logic:present name="cardProductList">
										<html:optionsCollection name="cardProductList" label="descr" value="crdproduct" />
									</logic:present>
								</html:select>
								
								<input type="button" class="button" onclick='return query()'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)" />
								<input type="button" class="button"
									onClick='return exportExcel()'
									style="background-image: url(<%=path%>/images/icon-import.png)">	
							</td>
						</tr>

					</table>
					<div class="dlist" style="top: 112px; height: 70%">
						<table cellpadding="0" cellspacing="0" width="100%" border="0">
							<tr id="tl" height="35" align="center"
								style="top: expression(this.offsetParent.scrollTop); position: relative; border-style: none">
								<td height="20">
									���
								</td>
								<td>
									��������
								</td>
								<td>
									����
								</td>
								<td>
									���
								</td>
								<td>
									״̬
								</td>
								<td>
									��������
								</td>
								<td>
									�Ӷ�����
								</td>
								<td>
									����Ա
								</td>
								<td>
									��������
								</td>
								<shiro:hasPermission name="cardbatch:salesTLog:edit">
									<td>
										����
									</td>
								</shiro:hasPermission>


							</tr>
							<tbody class="list">
								<logic:present name="salestlogList">
									<logic:iterate id="salestlogBean" name="salestlogList">
										<tr align="center" onMouseOver="changeClass(this,1)"
											onMouseOut="changeClass(this,2)">
											<td class=box2 height="28">
												<bean:write name="salestlogBean" property="id" />
											</td>
											<td class=box2 height="28">
												<logic:equal name="salestlogBean" property="txncode"
													value="28">
													<logic:equal name="salestlogBean" property="sub_txncode"
														value="99">ʵʱ����</logic:equal>
													<logic:equal name="salestlogBean" property="sub_txncode"
														value="98">��ʵʱ����</logic:equal>
													<logic:equal name="salestlogBean" property="sub_txncode"
														value="97">������ֵ</logic:equal>
													<logic:equal name="salestlogBean" property="sub_txncode"
														value="96">������ֵ</logic:equal>
												</logic:equal>
												<logic:equal name="salestlogBean" property="txncode"
													value="20">
													<logic:equal name="salestlogBean" property="sub_txncode"
														value="99">ʵʱ�˿�</logic:equal>
													<logic:equal name="salestlogBean" property="sub_txncode"
														value="98">��ʵʱ�˿�</logic:equal>
													<logic:equal name="salestlogBean" property="sub_txncode"
														value="92">��ֵ����</logic:equal>
												</logic:equal>
												<logic:equal name="salestlogBean" property="txncode"
													value="00">
													<logic:equal name="salestlogBean" property="sub_txncode"
														value="94">�������</logic:equal>
													<logic:equal name="salestlogBean" property="sub_txncode"
														value="93">��������</logic:equal>
												</logic:equal>
											</td>
											<td class=box2 height="28">
												<bean:write name="salestlogBean" property="pan" />
												&nbsp;
											</td>
											<td class=box2 height="28">
												<bean:write name="salestlogBean" property="amttxn" />
												&nbsp;
											</td>
											<td class=box2 height="28">
												&nbsp;
												<logic:equal name="salestlogBean" property="stxnstat"
													value="0">����</logic:equal>
												<logic:equal name="salestlogBean" property="stxnstat"
													value="8">�ѳ���</logic:equal>
											</td>
											<td class=box2 height="28">
												<bean:write name="salestlogBean" property="father_order" />
												&nbsp;
											</td>
											<td class=box2 height="28">
												<bean:write name="salestlogBean" property="children_order" />
												&nbsp;
											</td>
											<td class=box2 height="28">
												<bean:write name="salestlogBean" property="verifier" />
												&nbsp;
											</td>
											<td class=box2 height="28">
												<bean:write name="salestlogBean" property="verifytime" />
												&nbsp;
											</td>
											<shiro:hasPermission name="cardbatch:salesTLog:edit">
												<td class=box2 height="28">
													<html:link action="/salestlog.do?method=showSalestlogInfo"
														paramId="id" paramName="salestlogBean" paramProperty="id">
														<img border="0" src="<%=path%>/image1/border/query.png" />
													</html:link>
												</td>
											</shiro:hasPermission>

										</tr>
									</logic:iterate>
								</logic:present>
								<logic:notPresent name="salestlogList">
									<%
										int i = 9;
									%>
										<%
											i++;
										%>
									<tr align="center">
										<td class=box3 colspan="<%=i%>">
											������Ϣ
										</td>
									</tr>
								</logic:notPresent>
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td height="30">
					<table border="0" cellpadding="0" cellspacing="0" width="98%">
						<tr align="right">
							<td height="25">
								<logic:equal name="queryflag" value="0">
									<util:page uri="/salestlog.do?method=getSalestlogList" />
								</logic:equal>
								<logic:equal name="queryflag" value="1">
									<util:page uri="/salestlog.do?method=querySalestlogList" />
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