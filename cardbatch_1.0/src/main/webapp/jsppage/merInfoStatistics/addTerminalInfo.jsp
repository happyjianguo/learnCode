<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html lang="true">
<head>
	<html:base />

	<title>�̻���Ϣͳ��-�̻���Ϣ����ҳ��</title>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen"
		href="<%=path%>/css/cssem.css" />
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
	<script type="text/javascript" src="<%=path%>/js/textselect.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
	<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<script type="text/javascript">	
	function CurentDay() {
		var now = new Date();
		var year = now.getFullYear(); //��
		var month = now.getMonth() + 1; //��
		var day = now.getDate(); //��
		var clock = year + "";
		if (month < 10)
			clock += "0";
		clock += month + "";
		if (day < 10)
			clock += "0";
		clock += day + "";
		return (clock);
	}
	
	function loadMy() {
		document.getElementsByName("installdate")[0].value = CurentDay();
		document.getElementsByName("disabledate")[0].value = CurentDay();
		document.getElementsByName("updatedate")[0].value = CurentDay();
		
		getMerchantInfoListByIdOrName();
	}
   	
	function is1A7Num(formname,element,msg,len)
	{
	    var input = document.forms[formname].elements[element].value;
	    
	    if (input.length != len) {
	        alert (msg+"����Ϊ" + len + "λ�����ұ����Դ�д��ĸA�����ֿ�ͷ��");
	        return false;
	    }
    	
	    var charset1 = "A1234567890"  //��λ
    	var charset2to8 = "1234567890";  //����λ
		
    	var char1 = input.substr(0,1);
    	var char2to8 = input.substr(1);
    	
    	if (!checkChar(charset1, char1, true) || !checkChar(charset2to8, char2to8, true)) {
	        alert (msg+"����Ϊ" + len + "λ�����ұ����Դ�д��ĸA�����ֿ�ͷ��");
	        document.forms[formname].elements[element].focus();
	        document.forms[formname].elements[element].value = "";
	        return false;
		}
	    	
	    return true;
	}
	
	//����ַ������Ƿ��й涨�ַ�����/����ַ�
	function checkChar(charset, val, should_in)
	{
	    var num = val.length;
	    for (i=0; i < num; i++) {
	       var char = val.charAt(i);
	       if ((charset.indexOf(char) > -1) && (!should_in))
	          return false;
	       else if ((charset.indexOf(char) == -1) && (should_in))
	          return false;
	    }
	    return true;
	}
	
   	function commit() {
		if (!is1A7Num("terminalInfoForm", "id", "�ն˱��", 8)) {
			return false;
		}   
		if (!checkelement("terminalInfoForm", "merchantid", "�����̻����")) {
			return false;
		}
		
		if (!checkelement("terminalInfoForm", "address", "�̻�װ����ַ")) {
			return false;
		}  
		if (!checkelement("terminalInfoForm", "detailaddress", "���̼���̨��ַ")) {
			return false;
		}  
		if (!checkelement("terminalInfoForm", "model", "POS�ͺ�")) {
			return false;
		}  
		if (!checkelement("terminalInfoForm", "type", "POS����")) {
			return false;
		}  		
		var input = document.forms["terminalInfoForm"].elements["mobilenumber"].value;
		if (input != "") {
			if (!isnumberonly3("terminalInfoForm", "mobilenumber", "����POS�ֻ���")) {
				return false;
			}
		}
		if (!checkelement("terminalInfoForm", "snnumber", "POS��S/N��")) {
			return false;
		}  
		if (!checkelement("terminalInfoForm", "installdate", "��װ����")) {
			return false;
		}  
		if (!checkelement("terminalInfoForm", "disabledate", "ͣ������")) {
			return false;
		}  
		if (!checkelement("terminalInfoForm", "updatedate", "��������")) {
			return false;
		}  
		if (!checkelement("terminalInfoForm", "name", "��ϵ��")) {
			return false;
		}  
		if (!isnumberonly3("terminalInfoForm", "phonenumber", "�ŵ�绰")) {
			return false;
		}  
		if (!checkelement("terminalInfoForm", "status", "POS��״̬")) {
			return false;
		}  
		if (!isnumberonly3("terminalInfoForm", "deposite", "POSѺ��Ԫ��")) {
			return false;
		}  
		
		$("#btTJ").attr("disabled","true");
		document.terminalInfoForm.submit();   	    
	}
	
	//����aqmerchantid��ȡmerchantid�Ŀ�ѡ��
	function getMerchantInfoListByIdOrName(){
		var qmerchantidname = document.getElementsByName("qmerchantidname")[0].value;
		if(trim(qmerchantidname)!=''){
			merchantid = document.getElementsByName("merchantid")[0];
			document.getElementsByName("merchantid")[0].options.length = 0;
			merchantid.innerHTML = "";
			merchantid.add(new Option("����ѡ��", ""));
			
			merchantInfoManage.getMerchantInfoListByIdOrName(trim(qmerchantidname), function(data) {
				for (i = 0; i < data.length; i++) {
					merchantid.add(new Option(data[i].name, data[i].id));
				}
			});			
		}

	}
   	
	//ɾ���������˵Ŀո�
    function trim(str){
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
	
</script>

<body  onload="loadMy();">
	<html:form styleId="terminalInfoForm" action="/terminalInfo?method=addTerminalInfo" method="post">
		<table border="0" cellpadding="0" cellspacing="0" style="width: 100%; height: 99%;">
			<tr>
				<td align="center" valign="top">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="width: 28px; height: 10px;"></td>
						</tr>
						<tr>
							<td align="left"
								style="width:28px; height:28px;  background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
							</td>
							<td
								style="height:28px;  background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;��ǰλ�ã� �̻���Ϣͳ�� &gt; �����ն���Ϣ
							</td>
							<td
								style="width:7px; height:28px;  background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td style="width: 28px; height: 5px" colspan="3"></td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td coslpan="2"><font color="red">${info}</font></td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�ն˱��
							</td>
							<td align="left" class="box2">
								<html:text property="id" maxlength="8" onblur="checkTerminalInfoPK();" onkeyup="this.value=this.value.replace(/\W/g,'').toUpperCase()"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�����̻�
							</td>
							<td align="left" class="box2">
								<html:text property="qmerchantidname" maxlength="15" onchange="getMerchantInfoListByIdOrName();"></html:text>
								<html:select property="merchantid">
									<html:option value=" ">����ѡ��</html:option>
									<logic:present name="mrchList">
										<html:optionsCollection name="mrchList" label="name" value="mrchno" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻�װ����ַ
							</td>
							<td align="left" class="box2">
								<html:text property="address" maxlength="66"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								���̼���̨��ַ
							</td>
							<td align="left" class="box2">
								<html:text property="detailaddress" maxlength="66"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POS�ͺ�
							</td>
							<td align="left" class="box2">
								<html:select property="model">
									<html:option value="">����ѡ��</html:option>								
									<logic:present name="pos_modelList">
										<html:optionsCollection name="pos_modelList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POS����
							</td>
							<td align="left" class="box2">
								<html:select property="type">
									<html:option value="">����ѡ��</html:option>								
									<logic:present name="pos_typeList">
										<html:optionsCollection name="pos_typeList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����POS�ֻ���
							</td>
							<td align="left" class="box2">
								<html:text property="mobilenumber" maxlength="16" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POS��S/N��
							</td>
							<td align="left" class="box2">
								<html:text property="snnumber" maxlength="16"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��װ����
							</td>
							<td align="left" class="box2">
								<html:text property="installdate" onclick="WdatePicker({dateFmt:'yyyyMMdd'});" maxlength="8"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								ͣ������
							</td>
							<td align="left" class="box2">
								<html:text property="disabledate" onclick="WdatePicker({dateFmt:'yyyyMMdd'});" maxlength="8"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��������
							</td>
							<td align="left" class="box2">
								<html:text property="updatedate" onclick="WdatePicker({dateFmt:'yyyyMMdd'});" maxlength="8"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��ϵ��
							</td>
							<td align="left" class="box2">
								<html:text property="name" maxlength="16"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�ŵ�绰
							</td>
							<td align="left" class="box2">
								<html:text property="phonenumber" maxlength="16" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POS��״̬
							</td>
							<td align="left" class="box2">
								<html:select property="status">
									<html:option value="">����ѡ��</html:option>
									<html:option value="0">����</html:option>
									<html:option value="1">������</html:option>
									<html:option value="2">������</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POSѺ��Ԫ��
							</td>
							<td align="left" class="box2">
								<html:text property="deposite" maxlength="50" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="height: 23px;" align="center" class="box1">
								<input id="btTJ" class="button" type="button" onclick="commit()"
									value="����" />
								&nbsp;&nbsp;&nbsp;
								<input class="button" type="button" onClick="javascript:window.close()" 
									value="�ر�" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>

</html:html>