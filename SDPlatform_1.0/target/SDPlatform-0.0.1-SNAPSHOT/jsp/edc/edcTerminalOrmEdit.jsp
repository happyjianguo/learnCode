<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>�ɸ��������ն˹���</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	<script language="javascript">
	
	function saveClick()
	{
		document.forms[0].method.value="saveEdcTerminalOrm";
		return validateEdcTerminalForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryEdcTerminalOrm";
	}
	</script>
</head>
<shiro:lacksPermission name="posp:terminalorm:edit">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:terminalorm:edit">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				�ɸ��������ն�
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="edcTerminalOrmForm" />
				<html:errors />
				<html:form action="/edcTerminalOrm">

					<html:hidden property="method" value="saveEdcTerminalOrm" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">

						<tr>
							<td class="table2_td_title">
								�̻����:
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="merchantId" size="25" maxlength="15" onblur="checkMerchantId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<html:hidden property="merchantId"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								���б�ʶ:
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="bankId" size="25" readonly="true" maxlength="15"/>
								<html:hidden property="bankId"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 �ն˺ţ�
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="terminalId" size="25"  maxlength="8" onblur="checkTerminalId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<html:hidden property="terminalId"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								�����̻��ţ�
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="bankMerchantId" size="25"  maxlength="8" onblur="checkTerminalId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<html:hidden property="bankMerchantId"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�����ն˺ţ�
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="bankTerminalId" size="25"  maxlength="8" onblur="checkTerminalId(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<html:hidden property="bankTerminalId"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								ģ��ID��
							</td>
							<td class="table2_td">
								<html:hidden property="moduleId"/>
								<html:text disabled="true" property="moduleId" size="25"  maxlength="30"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								ϵͳ��ˮ�ţ�
							</td>
							<td class="table2_td">
								<html:hidden property="sysTrace"/>
								<html:text disabled="true" property="sysTrace" size="25"  maxlength="4"/>
							</td>
							<td class="table2_td_title">
								������ˮ�ţ�
							</td>
							<td class="table2_td">
								<html:text property="bankTrace" size="25"  maxlength="4"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								PIN�㷨��ʶ:
							</td>
							<td class="table2_td">
								<html:select property="pinFmt" disabled="true">
								 <html:option value="1">ANSI X98��ʽ���������˺ţ�</html:option>
								 <html:option value="2">ANSI X98�㷨�������˺ţ�</html:option>
								</html:select>
								<html:hidden property="pinFmt"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								���κţ�
							</td>
							<td class="table2_td">
								<html:text property="batchNo" size="19" maxlength="30" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								MAC�����־ :
							</td>
							<td class="table2_td">
								<html:select property="macFlag" disabled="true">
								 <html:option value="0">�����ն˲���</html:option>
								 <html:option value="1">����</html:option>
								</html:select>
								<html:hidden property="macFlag"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								�����㷨:
							</td>
							<td class="table2_td">
								<html:select property="encMethod" disabled="true">
								 <html:option value="0">DES</html:option>
								 <html:option value="6">3DES</html:option>
								</html:select>
								<html:hidden property="encMethod"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								������Կ����PIN_KEY��
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="pik" size="25"  maxlength="30"/>
								<html:hidden property="pik"/>
							</td>
							<td class="table2_td_title">
								������Կ����MAC_KEY��
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="mak" size="25"  maxlength="30"/>
								<html:hidden property="mak"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								������Կ����PIN_KEY<br>���ն�ǩ����ȡ����
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="pikTmk" size="25"  maxlength="30"/>
								<html:hidden property="pikTmk"/>
							</td>
							<td class="table2_td_title">
								������Կ����MAC_KEY<br>���ն�ǩ����ȡ����
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="makTmk" size="25"  maxlength="30"/>
								<html:hidden property="makTmk"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��Կ������
							</td>
							<td class="table2_td">
								<html:text disabled="true" property="keyIndex" size="25"  maxlength="30"/>
								<html:hidden property="keyIndex"/>
							</td>
							<td class="table2_td_title">
								����״̬ :
							</td>
							<td class="table2_td">
								<html:select property="settStatus" disabled="true">
								 <html:option value="0">��������״̬ </html:option>
								 <html:option value="1">��Ҫ����</html:option>
								 <html:option value="2">���ʽ�����</html:option>
								</html:select>
								<html:hidden property="settStatus"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�������POS�ն�ǩ��״̬ :
							</td>
							<td class="table2_td">
								<html:select property="logonStatus" disabled="true">
								 <html:option value="0">ǩ�� </html:option>
								 <html:option value="1">ǩ��</html:option>
								 <html:option value="2">�쳣</html:option>
								</html:select>
								<html:hidden property="logonStatus"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								��ͨ��־:
							</td>
							<td class="table2_td">
								<html:select property="flag" disabled="true">
								 <html:option value="1">������ͨ </html:option>
								 <html:option value="0">δ��ͨ</html:option>
								</html:select>
								<html:hidden property="flag"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()">
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()">
							</td>
						</tr>
					</table>
					<!-- ά����ͼ״̬�������� -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<input type="hidden" name="search_name" value="<c:out value="${param.search_name}"/>" />
					<!-- ά����ͼ״̬�������� -->
					</html:form>
			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>