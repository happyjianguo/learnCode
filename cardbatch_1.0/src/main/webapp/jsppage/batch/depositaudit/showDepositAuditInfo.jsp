
<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%String path = request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>����ҳ��</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	<script type="text/javascript" src="<%=path%>/js/eposcc.js"></script>
	<script type="text/javascript" src="<%=path%>/js/textselect.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
</head>
<script language="javascript">
	window.history.forward(1);
	var flag = false;
	function depositsend(id){
		$("#btSH").attr("disabled","true");
		$("#btCX").attr("disabled","true");
		window.location.href="<%=path %>/deposit.do?method=Deposit&id="+id+"&msgtype=0028";
	}
	function depositcancel(id){
		$("#btSH").attr("disabled","true");
		$("#btCX").attr("disabled","true");
		window.location.href="<%=path %>/deposit.do?method=Deposit&id="+id+"&msgtype=0051";
	}
</script>
<center>
	<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
		
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="99%" >
				<tr>
					<td align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
				    <tr>
						<td width="28" height="10"></td>
					</tr>
					<tr>
						<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
						
						</td>
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;��ǰλ�ã��������� &gt; &gt; ������ֵ���� &gt; &gt;����</td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
					</table>
						<table border="0" cellpadding="0" cellspacing="0" width="80%"  class = "box1">
							<tr>
								<td width="20%" align="right" class="box1">
									��ţ�
								</td>
								<td width="50%" align="left" class="box2">
								
									<bean:write name="depositBean" property="id"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td width="20%" align="right" class="box1">
									��Ϣ���ͣ�
								</td>
								<td width="50%" align="left" class="box2">
								
									<bean:write name="depositBean" property="txncode"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									�������ڣ�
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="depositBean" property="time"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									���ڣ�
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="depositBean" property="acct_period"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									��ˮ�ţ�
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="depositBean" property="stan"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  width="20%" align="right" class="box1">
									��ʼ���ţ�
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="depositBean" property="pan_start"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  align="right" class="box1">
							�������ţ�
								</td>
								<td align="left" class="box2">
									<bean:write name="depositBean" property="pan_end"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									������
								</td>
								<td align="left" class="box2">									
									<bean:write name="depositBean" property="pan_count"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									�����									
								</td>
								<td align="left" class="box2">									
									<bean:write name="depositBean" property="amt_each_crd"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									������֣�									
								</td>
								<td align="left" class="box2">									
									<bean:write name="depositBean" property="currbill"/>
								</td>
								<td align="left" class="box2" width="40%">
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									���׻��ʣ�									
								</td>
								<td align="left" class="box2">									
									<bean:write name="depositBean" property="rateset"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									���׽�									
								</td>
								<td align="left" class="box2">									
									<bean:write name="depositBean" property="amttxn"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									���ױ��֣�									
								</td>
								<td align="left" class="box2">									
									<bean:write name="depositBean" property="curtxn"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>	
							<tr>
								<td  align="right" class="box1">
									����Ʒ��									
								</td>
								<td align="left" class="box2">									
									<bean:write name="depositBean" property="crdproduct"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>									
							<tr>
								<td  width="20%" align="right" class="box1">
									��������
								</td>
								<td width="50%" align="left" class="box2">									
									<bean:write name="depositBean" property="father_order"/>
								</td>
								<td width="30%"></td>
							</tr>
							<tr>
								<td  align="right" class="box1">
							�Ӷ�����
								</td>
								<td align="left" class="box2">
									<bean:write name="depositBean" property="children_order"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									����״̬��
								</td>
								<td align="left" class="box2">									
									
									<logic:equal name="depositBean" property="batch_stat" value="00">�Ѵ���</logic:equal>
									<logic:equal name="depositBean" property="batch_stat" value="01">δ����</logic:equal>
									<logic:equal name="depositBean" property="batch_stat" value="02">�ѳ���</logic:equal>
									<logic:equal name="depositBean" property="batch_stat" value="03">�ѳ���</logic:equal>	
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									��ֵ���ͣ�
								</td>
								<td align="left" class="box2">									
									<logic:equal name="depositBean" property="cashin_type" value="1">��ͨ������ֵ</logic:equal>
									<logic:equal name="depositBean" property="cashin_type" value="2">���ÿ�����</logic:equal> 
									<logic:equal name="depositBean" property="cashin_type" value="3">���ּƻ���ֵ</logic:equal>
									<logic:equal name="depositBean" property="cashin_type" value="4">�����������ֳ�ֵ</logic:equal>
									<logic:equal name="depositBean" property="cashin_type" value="5">&nbsp;</logic:equal>
									<logic:equal name="depositBean" property="cashin_type" value="6">���ÿ��״γ�ֵ</logic:equal>
									<logic:equal name="depositBean" property="cashin_type" value="7">ϲ����ֵ</logic:equal>
									<logic:equal name="depositBean" property="cashin_type" value="9">���Ż��ֳ�ֵ</logic:equal>
									<logic:equal name="depositBean" property="cashin_type" value="10">�������ֳ�ֵ</logic:equal>
									<logic:equal name="depositBean" property="cashin_type" value="11">ʵ���˻���ֵ</logic:equal>
									<logic:equal name="depositBean" property="cashin_type" value="12">���ﷵ���ֳ�ֵ</logic:equal>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									֧����ʽ��
								</td>
								<td align="left" class="box2">										
									<bean:write name="depositBean" property="pay_type_desc"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							<tr>
								<td  align="right" class="box1">
									֧����ʽ��ϸ��Ϣ��									
								</td>
								<td align="left" class="box2">									
									<bean:write name="depositBean" property="pay_desc"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>
							
								
							<tr>
								<td  align="right" class="box1">
									������									
								</td>
								<td align="left" class="box2">
									<bean:write name="depositBean" property="area"/>
									
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>		
							<tr>
								<td  align="right" class="box1">
									��ע��֧Ʊ�ţ���
								</td>
								<td align="left" class="box2">
									<bean:write name="depositBean" property="summary"/>
								</td>
								<td align="left" class="box2" width="40%">
									
								</td>	
							</tr>						
							<tr>
								<td  align="right" class="box1">
									�̻��ţ�
								</td>
								<td align="left" class="box2">
									
									<bean:write name="depositBean" property="mrcht_id"/>
								</td>
							</tr>
							<tr>
								<td  align="right" class="box1">
									�Ƿ��ˣ�
								</td>
								<td align="left" class="box2">									
									<bean:write name="depositBean" property="ispaid"/>
								</td>
							</tr>
							<tr>
								<td  align="right" class="box1">
									���۵㣺
								</td>
								<td align="left" class="box2">									
									<bean:write name="depositBean" property="sales_point"/>
								</td>
							</tr>
							<tr>
								<td  align="right" class="box1">
									����Ա��
								</td>
								<td align="left" class="box2">									
									<bean:write name="depositBean" property="operator"/>
								</td>
							</tr>
							<tr>
								<td  align="right" class="box1">
									�����Ա��
								</td>
								<td align="left" class="box2">									
									<bean:write name="depositBean" property="descr_u"/>
								</td>
							</tr>
							<tr>
								<td  align="right" class="box1">
									���ʱ�䣺
								</td>
								<td align="left" class="box2">									
									<bean:write name="depositBean" property="descr_t"/>
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="80%"  class = "box1">
							<tr>
								<logic:notEqual name="depositBean" property="ispaid" value="1">
									<logic:equal name="depositBean" property="batch_stat" value="01">
										<td height="23" align="center" class="box1">									
											&nbsp;&nbsp;&nbsp;
											<input id="btSH"  class="button" type="button"  value="���" onClick="return depositsend(<bean:write name="depositBean" property="id"/>)">
										</td>
										<td height="23" align="center" class="box1">									
											&nbsp;&nbsp;&nbsp;
											<input id="btCX"  class="button" type="button"  value="����" onClick="return depositcancel(<bean:write name="depositBean" property="id"/>)">
										</td>
									</logic:equal>
								</logic:notEqual>
								<td height="23" align="center" class="box1">									
									&nbsp;&nbsp;&nbsp;
									<input class="button" type="button"  value="�ر�" onClick="javascript:window.close()">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>		
	</body>
</center>
</html:html>
