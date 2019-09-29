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

	<title>�ն��̻��޸�ҳ��</title>
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
	<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
	<script src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>

</head>


<script type="text/javascript">
	function trim(str){ //ɾ���������˵Ŀո�
	    return str.replace(/(^\s*)|(\s*$)/g, "");
	}	
	function commit() {
		if(trim(document.getElementsByName("merchant_id")[0].value)==""&&trim(document.getElementsByName("mrchnoSelQ")[0].value)==""){
			alert("�����̻�����Ϊ��");
			return false;
		} 
		if (!checkelement("terminalForm", "x_location", "�ն�����λ��")) {
			return false;
		}  
		if (!checkelement("terminalForm", "actdate", "��Կ��Ч����")) {
			return false;
		}   	
		if (!checkelement("terminalForm", "acttime", "��Կ��Чʱ��")) {
			return false;
		}  
		if (!checkelement("terminalForm", "pos_tel", "�ն�ʹ�õĵ绰����")) {
			return false;
		}    	
		if (!checkelement("terminalForm", "add_date", "��������")) {
			return false;
		}  
		if (!checkelement("terminalForm", "state", "ԣ��ԭ��״̬")) {
			return false;
		}  
		if (!isnumberonly3("terminalForm", "state", "ԣ��ԭ��״̬")) {
			return false;
		}
		if (!checkelement("terminalForm", "province", "ʡ��")) {
			return false;
		}  
		if (!checkelement("terminalForm", "city_no", "����")) {
			return false;
		}    	
		if (!checkelement("terminalForm", "zone", "����")) {
			return false;
		}  
		if (!checkelement("terminalForm", "settle_mrch_acc_id", "���˹��˵Ľ����˺�")) {
			return false;
		}  
		if (checkDouble("terminalForm", "x_timezone", "����")) {
			return false;
		}  
		if (!checkelement("terminalForm", "consump_category", "���ѳ���")) {
			return false;
		}
		
		
		//У���ն�״̬�Ƿ�����
		var term_stat=document.getElementsByName("term_stat")[0].value;
		if(term_stat ==null||term_stat ==""){
			alert("��ѡ���ն�״̬");
			document.forms["term_stat"].elements["term_stat"].focus();
			return false;
		}else{
			if(term_stat =="2"){
				var ret = window.confirm("��ʾ����ȷ�ϣ��ն�״̬Ϊ�������޷������޸�?");
				//�����ȷ��ʱ ���� true 
				if(ret){
				    //do something ��ȷ��
				}else{
					return false;
				}
				
			}
		}
		
		$("#btTJ").attr("disabled","true");
		document.terminalForm.submit();
	}

	/*****************************/
	 //T_WANKE_MER_BOOK������Ϣ���������̻���������
		var flagMerchant=1;
		var req;
		function init() {
			if(window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
	   function  selectWankeTer(){    

			  
			   if(flagMerchant==1){
				   var merchantNo = document.getElementsByName("mrchnoSelQ")[0].value;
			   }else{
				   var merchantNo = document.getElementsByName("merchant_id")[0].value;
			   }

			   init();
		 		var url="<%=path%>/terminal.do?method=findmerchantNo&merchantNo="+merchantNo;   
		 		req.open("POST", url, true);
				req.onreadystatechange = findmerchantNoCallback;
				req.send(null);
		 } 
	   
	   
	   function findmerchantNoCallback() {
			 if(4 == req.readyState) {
				if(200 == req.status) {
					if(req.responseText!='false'){
					  document.getElementsByName("yard_mer_type")[0].style.display = "block";
					  document.forms[0].yard_mer_type.value=req.responseText;
					}
					if(req.responseText=='false'){
					  document.getElementsByName("yard_mer_type")[0].style.display = "none";
					  document.forms[0].yard_mer_type.value="";
					
					}
				}
			  }
		    }
	   
	 //T_WANKE_MER_BOOK������Ϣ������ն˺�����
	 var flag=1;

	//����mrchnoSelQ��ȡ�����̻��������̻�
	function getMerchantBeanListH(){
		var mrchnoSelQ = document.getElementsByName("mrchnoSelQ")[0].value;
		merchant_id = document.getElementsByName("merchant_id")[0];
		document.getElementsByName("merchant_id")[0].options.length = 0;
		merchant_id.innerHTML = "";
		merchant_id.add(new Option("����ѡ��", ""));
		instManage.getMerchantBeanListByMrchNoOrName(null,null,'0',mrchnoSelQ,'yes',null, function(data) {
			for (i = 0; i < data.length; i++) {
				merchant_id.add(new Option(data[i].name, data[i].mrchno));
			}
		});
		//T_WANKE_MER_BOOK������Ϣ������ն˺�����
		if(flag==1){
			var mrchnoSelQ = document.getElementsByName("mrchnoSelQ")[0].value;
			 document.forms[0].mrchnoSelQBak.value=mrchnoSelQ;
// 			 var mrchnoSelQBak = document.getElementsByName("mrchnoSelQBak")[0].value;
// 			alert("mrchnoSelQBak="+mrchnoSelQBak);
			flag=2;
		}
		 
	}
	//����mrchnoSelJSQ��ȡ�����̻��������̻�
	function getMerchantBeanListJS(){
		var merchantId=document.getElementsByName("mrchnoSelQ")[0].value;
		var mrchnoSelJSQ = document.getElementsByName("mrchnoSelJSQ")[0].value;
		merchantJS_id = document.getElementsByName("merchantJS_id")[0];
		document.getElementsByName("merchantJS_id")[0].options.length = 0;
		merchantJS_id.innerHTML = "";
		merchantJS_id.add(new Option("����ѡ��", ""));
		instManage.getMerchantBeanListByMrchNoOrName(null, null,'1',mrchnoSelJSQ,'yes',merchantId, function(data) {
			for (i = 0; i < data.length; i++) {
				merchantJS_id.add(new Option(data[i].name, data[i].mrchno));
			}
		});
	}
	   function   checkDouble(formname, name, info)   {      
		   var nameArray = document.getElementsByName(name);  
		   for(var i = 0;i< nameArray.length; i++){
		    if(nameArray[i].value != ""){
		       if(!isUnsignedDouble(nameArray[i].value)) {
		          alert(info+"ӦΪ���֣�");
		          document.forms[formname].elements[name].focus();
		          return true;
		       }
		    }
		   }  
		    return false;    
		    } 
	   //����Ƿ�Ϊdouble      
	   function   isUnsignedDouble(strDouble)   {      
	   var   newPar=/^\d+(\.\d+)?$/;      
	   return   newPar.test(strDouble);      
	   } 

	   function loadMy() {
			getMerchantBeanListH();
			if(flagMerchant==1){
				selectWankeTer();
				flagMerchant=flagMerchant+1;
			}
			
		}
</script>


<body onload="loadMy();">
	<html:form styleId="terminalForm"
		action="/terminal?method=modTerminalInfo" method="post">
		<html:hidden property="id" />
		<html:hidden property="termcode" />
		<html:hidden property="enckey_id" />
		<html:hidden property="termno" />
		<html:hidden property="mrchno" />
		<html:hidden property="mrchnoSelQBak" />
		<table border="0" cellpadding="0" cellspacing="0"
			style="width: 100%; height: 99%;">
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
								&nbsp;&nbsp;��ǰλ�ã� �̻��ն˹��� &gt; �ն˹���
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
							<td style="width: 110px;" align="right" class="box1">
								�����̻�
							</td>
							<td align="left" class="box2">
								<html:text property="mrchnoSelQ" maxlength="15"
									onchange="getMerchantBeanListH();" ></html:text>
								<html:select property="merchant_id" onblur="selectWankeTer();" >
									<html:option value="">����ѡ��</html:option>
									<logic:present name="mrchList">
										<html:optionsCollection name="mrchList" label="name"
											value="mrchno" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								�ն˺�
							</td>
							<td align="left" class="box2">
								<html:text property="termcode" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								�ն�����λ��
							</td>
							<td align="left" class="box2">
								<html:text property="x_location" maxlength="15"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�ն�״̬
							</td>
							<td align="left" class="box2">
								<html:select property="term_stat">
									<html:option value="0">����</html:option>
									<html:option value="1">ͣ��</html:option>
									<html:option value="2">����</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
							����̻�����
							</td>
							<td align="left" class="box2">
								<html:select property="yard_mer_type"  style="width:120px;">
									<html:option value="0">��Ƴ���</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����ʱ��
							</td>
							<td align="left" class="box2">
								<html:text property="enable_date" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
							</td>
						</tr>		
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								ͣ��ʱ��
							</td>
							<td align="left" class="box2">
								<html:text property="disabled_date" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
							</td>
						</tr>							
						<!-- enckey -->
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								��Կ��Ч����
							</td>
							<td align="left" class="box2">
								<html:text property="actdate" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								��Կ��Чʱ��
							</td>
							<td align="left" class="box2">
								<html:text property="acttime" maxlength="16"
									onclick="WdatePicker({dateFmt:'HHmmss'});"></html:text>
							</td>
						</tr>

						<!-- termpos_x -->
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								�ն�ʹ�õĵ绰����
							</td>
							<td align="left" class="box2">
								<html:text property="pos_tel" maxlength="16"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								��������
							</td>
							<td align="left" class="box2">
								<html:text property="add_date" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								ԣ��ԭ��״̬
							</td>
							<td align="left" class="box2">
								<html:text property="state" maxlength="5"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								ʡ��
							</td>
							<td align="left" class="box2">
								<html:select property="province" onchange="getCity_no()">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="provinList">
										<html:optionsCollection name="provinList"
											label="province_city" value="aid" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								����
							</td>
							<td align="left" class="box2">
								<html:select property="city_no" onchange="getZone()">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="city_noList">
										<html:optionsCollection name="city_noList"
											label="province_city" value="aid" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								����
							</td>
							<td align="left" class="box2">
								<html:select property="zone">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="zoneList">
										<html:optionsCollection name="zoneList" label="province_city"
											value="aid" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�����̻�
							</td>
							<td align="left" class="box2">
								<html:text property="mrchnoSelJSQ" maxlength="15"
									onchange="getMerchantBeanListJS();"></html:text>
								<html:select property="merchantJS_id" onchange="getMcaccxId();">
									<html:option value=" ">����ѡ��</html:option>
									<logic:present name="mrchJSList">
										<html:optionsCollection name="mrchJSList" label="name"
											value="mrchno" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								���˹��˵Ľ����˺�
							</td>
							<td align="left" class="box2">
								<html:select property="settle_mrch_acc_id">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="mrchaccxList">
										<html:optionsCollection name="mrchaccxList" label="name"
											value="id" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								����(��λ��%)
							</td>
							<td align="left" class="box2">
								<html:text property="x_timezone" maxlength="16"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								���ѳ���
							</td>
							<td align="left" class="box2">
								<html:select property="consump_category">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="consump_categoryList">
										<html:optionsCollection name="consump_categoryList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="height: 23px;" align="center" class="box1">
								<input id="btTJ" class="button" type="button" onclick="commit()"
									value="����" />
								&nbsp;&nbsp;&nbsp;
								<input class="button" type="button"  value="�ر�" 
								onclick="history.go(-1);" />

							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>

</html:html>