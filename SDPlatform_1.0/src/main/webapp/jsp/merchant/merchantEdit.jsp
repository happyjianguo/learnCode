<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*" isELIgnored="false"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
	<title>�̻������趨</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<link href="<fmt:message key='StylePath' />testAjax.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />ajaxSignBankIdQuery.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />ajaxMccParamQuery.js"></script>
	<script type="text/javascript"  language="JavaScript" src="<fmt:message key='JavaScriptPath' />data.js"></script>
	<script type="text/javascript" language="JavaScript"  src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script  type="text/javascript" language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script  type="text/javascript" language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />DatePicker/WdatePicker.js"></script>
	<script language="javascript">
	
	function saveClick()
	{
		var merchantId = document.forms[0].merchantId.value.RTrim();
		var mcc = document.forms[0].mcc.value.RTrim();
		var cityCname = document.forms[0].cityCname.value.RTrim();
		//if(merchantId.substr(3, 8)!=cityCname+mcc){
		//	alert('�̻����Ϊ3λ�յ��д��� +4λ������+4λ�̻�����+4λ���к�');
		//	return false;
		//}
		document.forms[0].method.value="saveItem";
		return validateMerchantForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	function findArea()
	{
		var return_value = window.showModalDialog("merchant.do?method=showFindAreaPage");
		if(return_value!=undefined){
			document.forms[0].cityCname.value=return_value;
		}
	}
    //ɾ������ո�
  	String.prototype.RTrim   =   function(){   
  		return   this.replace(/(\s*$)/g,"");   
  	} 
  	
	</script>
</head>
<shiro:lacksPermission name="posp:merchant:edit">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:merchant:edit">
<body>

	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle"> 
				�޸��̻�����
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="merchantForm" />
				<html:errors />
				<html:form action="/merchant">

					<html:hidden property="method" value="saveItem" />
					
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						
						<tr height="25" class="table2_td_title"><td colspan="4"><b>��������</b></td></tr>
	
						<tr>
							<td class="table2_td_title">
								�̻����:
							</td>
							<td class="table2_td">
								<input type="text" value="${merchantForm.merchantId}" size="30" maxlength="15" disabled="true" />
							    <html:hidden property="merchantId" />
							</td>
							<td class="table2_td_title">
								�̻�����:
							</td>
							<td class="table2_td">
								<input type="text" name="mcc" value="${merchantForm.mcc}" id="mcc" size="30" onclick="findMccParam();" maxlength="4" onkeyup="this.value=this.value.replace(/\D/g,'');findMccParam();" onblur="closeDiv();" /><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�̻�����(��):
							</td>
							<td class="table2_td">
								<html:text property="merchantCname" size="30" maxlength="20" onfocus="this.value=this.value.RTrim()"/><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								���(��):
							</td>
							<td class="table2_td">
								<html:text property="abbrCname" size="30"  maxlength="6" onfocus="this.value=this.value.RTrim()" /><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�̻�����(Ӣ):
							</td>
							<td class="table2_td">
								<html:text property="merchantEname"  size="30"  maxlength="20" onfocus="this.value=this.value.RTrim() "/><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								���(Ӣ):
							</td>
							<td class="table2_td">
								<html:text property="abbrEname" size="30"  maxlength="8" onfocus="this.value=this.value.RTrim()"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��ַ(��):
							</td>
							<td class="table2_td">
								<html:text property="addressChn"  size="30" maxlength="15" onfocus="this.value=this.value.RTrim()"/>
							</td>
							<td class="table2_td_title">
								��ַ(Ӣ):
							</td>
							<td class="table2_td">
								<html:text property="addressEng" size="30"  maxlength="20" onfocus="this.value=this.value.RTrim()"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								����:
							</td>
							<td class="table2_td">
								<input type="text" name="cityCname" value="${merchantForm.cityCname}" id="cityCname" size="30" maxlength="4" readonly="readonly" /><input type="button" value="ѡ��" onclick="findArea();"><font color="red">*</font>
							</td>
							<td class="table2_td_title">
<%--								����(Ӣ):--%>
							</td>
							<td class="table2_td">
<%--								<html:text property="cityEname" size="30" maxlength="20" onfocus="this.value=this.value.RTrim()"/>--%>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��ϵ��:
							</td>
							<td class="table2_td">
								<html:text property="manager" size="30" maxlength="4" onfocus="this.value=this.value.RTrim()"/>
							</td>
							<td class="table2_td_title">
								��������:
							</td>
							<td class="table2_td">
								<html:text property="postCode" size="30" maxlength="6" onkeyup="this.value=this.value.replace(/\D/g,'')" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�绰����:
							</td>
							<td class="table2_td">
								<html:text property="telephone" size="30" maxlength="20" onfocus="this.value=this.value.RTrim()"/>
							</td>
							<td class="table2_td_title">
								�������:
							</td>
							<td class="table2_td">
								<html:text property="fax" size="30"  maxlength="20" onfocus="this.value=this.value.RTrim()"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�����̻����:
							</td>
							<td class="table2_td">
								<html:text property="settleMerchId" size="30" maxlength="15" disabled="true" />
								<html:hidden property="settleMerchId" />
							</td>
							<td class="table2_td_title">
							</td>
							<td class="table2_td">
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��������:
							</td>
							<td class="table2_td">
							<html:select property="zbank" style="width:195px;">
							 <html:option value="000001">ԣ������</html:option>
							 </html:select>
							</td>
							<td class="table2_td_title">
								���㷽ʽ:
							</td>
							<td class="table2_td">
							<html:select property="settleMode" style="width:195px;">
								<html:option value="0">�ֹ����� </html:option>
								<html:option value="1">��Ҫ����</html:option>
								<html:option value="2">��������</html:option>
							</html:select>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								ǩԼ����:
							</td>
							<td class="table2_td">
								<input name="signBankId" id="signBankId" onclick="findNames();" value="${merchantForm.signBankId}" size="30" onblur="closeDiv();" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'');findNames();"/><font color="red">*</font>
								<div style="position:absolute;z-index:9999;" id="popup"  style.display = "none" onmouseenter="onmouseDiv();" onmouseleave="onmouseoutDiv();" onblur="closeDiv();">
					        <table id="name_table"  bgcolor="" border="0" cellspacing="0" cellpadding="0"/>            
					            <tbody id="name_table_body"> </tbody>
					        </table>
				    	  </div>
							</td>
							<div id="aa"></div>
					  		<div id="bb"></div>
							<td class="table2_td_title">
								ǩԼ����:
							</td>
							<td class="table2_td">
								<html:text property="signDate" size="30" readonly="true" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" styleClass="Wdate" /> 
								<font color="red">*</font>
							</td>
		
						</tr>
						<tr>
							<td class="table2_td_title">
								ǩԼ��������:
							</td>
							<td class="table2_td">
								<input name="signHostId" id="signHostId" value="${merchantForm.signHostId}" size="30" readonly="true"/><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								�̻�״̬:
							</td>
							<td class="table2_td">
								<html:select property="merchantStat" style="width:195px;">
								<html:option value="Y">����</html:option>
								<html:option value="N">����</html:option>
							</html:select><font color="red">*</font>
							</td>
						</tr>
						<tr height="25" class="table2_td_title"><td colspan="4"><b>��������</b></td></tr>
						<tr>
							<td class="table2_td_title">
								����������:
							</td>
							<td class="table2_td">
								<html:text property="sndName" size="30" maxlength="20"  onfocus="this.value=this.value.RTrim()"/>
							</td>
							<td class="table2_td_title">
								�������˺�:
							</td>
							<td class="table2_td">
								<html:text property="sndAcct" size="30"  maxlength="30" onfocus="this.value=this.value.RTrim()" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�����˿�����:
							</td>
							<td class="table2_td">
								<html:text property="sndBank" size="30" maxlength="15" onfocus="this.value=this.value.RTrim()"/>
							</td>
							<td class="table2_td_title">
							</td>
							<td class="table2_td">
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�տ�������:
							</td>
							<td class="table2_td">
								<html:text property="rcvName" size="30"  maxlength="20" onfocus="this.value=this.value.RTrim()"/>
							</td>
							<td class="table2_td_title">
								�տ����˺�1:
							</td>
							<td class="table2_td">
								<html:text property="rcvAcct1"  size="30" maxlength="30" onfocus="this.value=this.value.RTrim()" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�տ����˺�2:
							</td>
							<td class="table2_td">
								<html:text property="rcvAcct2"  size="30"  maxlength="30" onfocus="this.value=this.value.RTrim()"/>
							</td>
							<td class="table2_td_title">
								�տ��˿�����:
							</td>
							<td class="table2_td">
								<html:text property="rcvBank"  size="30" maxlength="15" onfocus="this.value=this.value.RTrim()"/>
							</td>
						</tr>
						<tr height="25" class="table2_td_title"><td colspan="4"><b>�˻��޶����</b></td></tr>
						<tr>
							<td class="table2_td_title">
								�˻��޶�:
							</td>
							<td class="table2_td">
								<html:text property="refundLimit" size="30"/>
							</td>
							<td class="table2_td_title">
								�˻����:
							</td>
							<td class="table2_td">
								<html:select property="refundCheck" style="width:195px;">
								<html:option value="Y">���</html:option>
								<html:option value="N">�����</html:option>
							</html:select>
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
					<INPUT TYPE="hidden" name="mccMark" value="N">
					<INPUT TYPE="hidden" name="divflag" id="divflag" value="N">
					<!-- ά����ͼ״̬�������� -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<!-- ά����ͼ״̬�������� -->
				</html:form>

			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>



