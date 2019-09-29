<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%String path = request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html lang="true">
<head>
	<html:base />

	<title>�̻�����ҳ��</title>
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

	function CurentDay() {
		var now = new Date();
		var year = now.getFullYear(); 	//��
		var month = now.getMonth() + 1; //��
		var day = now.getDate(); 		//��
		var hour = now.getHours();		//ʱ
		var minute = now.getMinutes();	//��
		var second = now.getSeconds();	//��
		var clock = year + "";
		if (month < 10)
			clock += "0";
		clock += month + "";
		if (day < 10)
			clock += "0";
		clock += day + "";
	 	if(hour<10){
	 		clock += "0";
	 	}
		clock += hour + "";
		if(minute<10){
			clock += "0";
		}
		clock += minute + "";
		if(second<10){
			clock += "0";
		}
		clock += second + "";
		return (clock);
	}
	function loadMy() {
		document.getElementsByName("add_date")[0].value = CurentDay();
		document.getElementsByName("id_validity")[0].value = "2000-07-01";
		document.getElementsByName("lr_id_validity")[0].value = "2000-07-01";
		document.getElementsByName("bus_lic_validity")[0].value = "2000-07-01";
		document.getElementsByName("tax_id_validity")[0].value = "2000-07-01";
		document.getElementsByName("org_validity")[0].value = "2000-07-01";
		document.getElementsByName("last_settle_date")[0].value = "2000-07-01";
		document.getElementsByName("id_deadline1")[0].value = "2000-07-01";
		getCity_no();
	}

	//����mrchnoSelQ��ȡmrchno�Ŀ�ѡ��
	function getMerchantBeanListH(){
		var mrchnoSelQ = document.getElementsByName("mrchnoSelQ")[0].value;
		if(trim(mrchnoSelQ)!=''){
			mrchno = document.getElementsByName("fmrchno")[0];
			document.getElementsByName("fmrchno")[0].options.length = 0;
			mrchno.innerHTML = "";
			mrchno.add(new Option("����ѡ��", ""));
			instManage.getMerchantBeanListByMrchNoOrName(null, null,'1',mrchnoSelQ,'yes',null, function(data) {
				for (i = 0; i < data.length; i++) {
					mrchno.add(new Option(data[i].name, data[i].mrchno));
				}
			});			
		}
	}
	//����mrchnoSelQ��ȡmrchno�Ŀ�ѡ��
	/* function getOnchangeEmail(){
		var email = document.getElementsByName("merchant_email")[0].value;
		var reEmail = /^([A-Za-z0-9])(\w)+@(\w)+(\.)(com|com\.cn|net|cn|net\.cn|org|biz|info|gov|gov\.cn|edu|edu\.cn)/;
		if (!email.match(reEmail) && email != "") {
			alert('Email��ʽ����!');
			document.getElementsByName("merchant_email")[0].value='';
		}
	} */
	function clearNoNum(obj){ 
	    obj.value = obj.value.replace(/[^\d.]/g,"");  //��������֡��͡�.��������ַ�  
	    obj.value = obj.value.replace(/\.{2,}/g,"."); //ֻ������һ��. ��������  
	    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
	    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//ֻ����������С��  
	    if(obj.value.indexOf(".")< 0 && obj.value !=""){//�����Ѿ����ˣ��˴����Ƶ������û��С���㣬��λ����Ϊ������ 01��02�Ľ�� 
	    	if(obj.value.length >= 16){
	        	obj.value= parseFloat(obj.value.substring(0,15));
	        }else{
	        	obj.value= parseFloat(obj.value); 
	        }
	    } 
	}
	
	function getOnchangeEmail(){
		var email = document.getElementsByName("merchant_email")[0].value;
		//1���ж�email�Ƿ�Ϊ��
		//2���ж�email�п�����
		//3�����ַ�������ת�����飬�����ж��Ƿ�Ϊ����
		//4������ʱ������ȷ����������ڻ���
		//��������
		//var reEmail = /^([A-Za-z0-9])(\w)+@(\w)+(\.)(com|com\.cn|net|cn|net\.cn|org|biz|info|gov|gov\.cn|edu|edu\.cn)$/;
		//var reEmail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		var reEmail = /^([a-zA-Z0-9\u4e00-\u9fa5]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		//��������
		var retEmail = "";
		//1
		if(email == ""){
			return false;
		}else{
			//2
			var arrnow = [];
			//3��
			var arr = email.split(",");
			var n = arr.length;//�������
			if(n > 5){
				alert("���䲻�ܳ���5��");
				document.getElementsByName("merchant_email")[0].value=retEmail;
				return false;
			}
			for(j = 0,len=arr.length; j < len; j++) {
				if(arr[j] != ""){
					arrnow.push(arr[j]);//ȥ��������
				}
			}
			if(arrnow.length == 0){
				alert('Email��ʽ����!');
				document.getElementsByName("merchant_email")[0].value=retEmail;
				return false;
			}
			for(j = 0,lens=arrnow.length; j < lens; j++) {
   				if (!arrnow[j].match(reEmail) && arrnow[j] != "") {
					alert(arrnow[j]+':Email��ʽ����!');
					document.getElementsByName("merchant_email")[0].value=retEmail;
					return false;
				}else{
					//4
					if(j != 0){
						//�ж������Ƿ��ظ�
						if(retEmail.indexOf(arrnow[j]) != -1){
							alert(arrnow[j]+':Email�Ѿ����룬��˲�!');
							document.getElementsByName("merchant_email")[0].value=retEmail;
							return false;
						}
						retEmail = retEmail + "," + arrnow[j];
					}else{
						retEmail = arrnow[j]
					}
				}
			}
			document.getElementsByName("merchant_email")[0].value=retEmail;
		}
	}
	
	function checkMobileOrPhone(phone){
		
		 if ((!phone1.test(phone.RTrim()))&&(!phone2.test(phone.RTrim()))){
			 return false;
		 }else{
			 return true;
		 } 
	}
	/*ɾ������ո�*/
	String.prototype.RTrim = function() {
		return this.replace(/(\s*$)/g, "");
	}
	function commit() {
		
		if (!checkelement("merchantForm", "mrchno", "�̻���")) {
			document.forms["merchantForm"].elements["acptbus"].focus();
			return false;
		}
		
		var mrchno=document.getElementsByName("mrchno")[0].value;
		if(trim(mrchno)!=''){
			if(mrchno.length!=15){
			   alert("�̻��ű�����15λ����");
			   document.forms["merchantForm"].elements["acptbus"].focus();
			   return false;		
			}
		} 
		
		if (!checkelement("merchantForm", "mrcht_name", "�̻�����")) {
			return false;
		}
		if (!checkelement("merchantForm", "acptbus", "ISO��ӦMCC��")) {
			return false;
		}
		if(trim(document.getElementsByName("contact3")[0].value)==""){
			alert("ע���ַ��ϵ�˲���Ϊ��");
			return false;
		}
		if (!isnumberonly3("merchantForm", "telno1", "�ʼĵ�ַ��ϵ�绰")) {
			return false;
		}
		
		//����У��
		var telephone = document.getElementsByName("telno1")[0].value;
		
		var phone1 = /^1\d{10}$/;
		var phone2 = /^0\d{2,3}-?\d{7,8}$/;
		var phone=telephone.replace(/(\s*$)/g, "");
		var flag;
		
		if (!phone1.test(phone)&&!phone2.test(phone)){
			flag= false;
		}else{
			flag= true;
		} 
		if(telephone!=null&&telephone!=""){
			if(flag == false){
				alert("����ȷ��д���ĵ绰����");
				document.forms["merchantForm"].elements["telno1"].focus();
				return false;
			}	
		}		
		
		if (!checkelement("merchantForm", "mrchstat", "�̻�״̬")) {
			return false;
		}
		if (!checkelement("merchantForm", "add_date", "����ʱ��")) {
			return false;
		}	
		if (!checkelement("merchantForm", "province", "ʡ��")) {
			return false;
		}
		if (!checkelement("merchantForm", "city_no", "����")) {
			return false;
		}
		if (!checkelement("merchantForm", "zone", "����")) {
			return false;
		}	 
		if (!checkelement("merchantForm", "type_yf", "�̻�����")) {
			return false;
		}
		if (!checkelement("merchantForm", "agent", "������������������")) {
			return false;
		}
		if (!checkelement("merchantForm", "id_type", "��������������֤������")) {
			return false;
		}
		if (!checkelement("merchantForm", "id_no", "��������������֤������")) {
			return false;
		}
		var id_type = document.getElementById("id_type").value;
		var id_no = $("#id_no").val();
		if(id_type == "1" || id_type == "3" || id_type == "4"){
		  	if(!verifyCardNum(id_no)){
		  		alert("��������������֤�����벻���Ϲ淶������������");
				$("#id_no").focus();
		  		return false;
		  	}
		}
		if (!checkCode("merchantForm", "id_no", "��������������֤������ֻ�������ֺ���ĸ")) {
			return false;
		}
		if (!checkelement("merchantForm", "id_validity", "��������������֤����Ч��")) {
			return false;
		}
		
		/*if (!checkelement("merchantForm", "legal_rep", "���������ˣ������ˣ�����")) {
			return false;
		}
		if (!checkelement("merchantForm", "lr_id_type", "����������֤������")) {
			return false;
		}
		 if (!checkelement("merchantForm", "lr_id_no", "����������֤������")) {
			return false;
		}
		if (!checkCode("merchantForm", "lr_id_no", "����������֤������ֻ�������ֺ���ĸ")) {
			return false;
		}
		if (!checkelement("merchantForm", "lr_id_validity", "����������֤����Ч��")) {
			return false;
		} */
		var lr_id_type = document.getElementById("lr_id_type").value;
		var lr_id_no = $("#lr_id_no").val();
		if(lr_id_type == "1" || lr_id_type == "3" || lr_id_type == "4"){
		    if(lr_id_no != null && trim(lr_id_no) != ''){
		    	if(!verifyCardNum(lr_id_no)){
			  		alert("����������֤�����벻���Ϲ淶������������");
					$("#lr_id_no").focus();
			  		return false;
			  	}
		    }
		}
		var id_type1 = document.getElementById("id_type1").value;
		var id_no1 = $("#id_no1").val();
		if(id_type1 == "1" || id_type1 == "3" || id_type1 == "4"){
		    if(id_no1 != null && trim(id_no1) != ''){
		    	if(!verifyCardNum(id_no1)){
			  		alert("�عɹɶ���ʵ�ʿ�����֤�����벻���Ϲ淶������������");
					$("#id_no1").focus();
			  		return false;
			  	}
		    }
		}
		//ֻ���ϴ�ͼƬ
		var taxFile1 =$("input[name='bus_lic_pic_f']").val()  
		var extend1 = taxFile1.substring(taxFile1.lastIndexOf(".")+1); 
	    if(extend1=="" || extend1 == null){ 
	    }else{ 
			if(!(extend1=="jpg"||extend1=="jpeg"||extend1=="gif"||extend1=="png"||extend1=="bmp"||
					extend1=="JPG"||extend1=="JPEG"||extend1=="GIF"||extend1=="PNG"||extend1=="BMP")){ 
			   alert("�ļ���ʽ����, ���ϴ�ͼƬ��ʽ��"); 
			   return false;
			} 
	    }
	    var taxFile2 =$("input[name='tax_id_pic_f']").val()  
		var extend2 = taxFile2.substring(taxFile2.lastIndexOf(".")+1); 
	    if(extend2=="" || extend2 == null){ 
	    }else{ 
			if(!(extend2=="jpg"||extend2=="jpeg"||extend2=="gif"||extend2=="png"||extend2=="bmp"||
					extend2=="JPG"||extend2=="JPEG"||extend2=="GIF"||extend2=="PNG"||extend2=="BMP")){ 
			   alert("�ļ���ʽ����, ���ϴ�ͼƬ��ʽ��"); 
			   return false;
			} 
	    }
	    var taxFile3 =$("input[name='org_id_pic_f']").val()  
		var extend3 = taxFile3.substring(taxFile3.lastIndexOf(".")+1); 
	    if(extend3=="" || extend3 == null){ 
	    }else{ 
			if(!(extend3=="jpg"||extend3=="jpeg"||extend3=="gif"||extend3=="png"||extend3=="bmp"||
					extend3=="JPG"||extend3=="JPEG"||extend3=="GIF"||extend3=="PNG"||extend3=="BMP")){ 
			   alert("�ļ���ʽ����, ���ϴ�ͼƬ��ʽ��"); 
			   return false;
			} 
	    }
		if (!checkelement("merchantForm", "bus_lic_no", "Ӫҵִ�պ�")) {
			return false;
		}
		if (!checkelement("merchantForm", "bus_lic_validity", "Ӫҵִ�����ʱ��")) {
			return false;
		}
		if (!checkelement("merchantForm", "tax_id", "˰��Ǽ�֤���")) {
			return false;
		}
		if (!checkelement("merchantForm", "tax_id_validity", "˰��Ǽ�֤���ʱ��")) {
			return false;
		}
		if (!checkelement("merchantForm", "org_id", "��֯����֤���")) {
			return false;
		}
		if (!checkelement("merchantForm", "org_validity", "��֯����֤���ʱ��")) {
			return false;
		}
		if(trim(document.getElementsByName("mx_accno")[0].value)==""){
			alert("��ҵ�˺Ų���Ϊ��");
			return false;
		}
		if(trim(document.getElementsByName("merchant_x_reg_amt")[0].value)==""){
			alert("ע���ʱ�����Ϊ��");
			return false;
		}
		if (!checkelement("merchantForm", "classify", "���㼶��")) {
			return false;
		}
		/* if (!checkelement("merchantForm", "fs_cycle", "��������")) {
			return false;
		}
		var obj = document.getElementById("fs_cycle"); //��λid
		var index = obj.selectedIndex; // ѡ������
		var fs_cycle = obj.options[index].value; // ѡ��ֵ
		if(fs_cycle!=""&&fs_cycle!="2"){
			if (!checkelement("merchantForm", "fs_cycle_param", "�������ڲ���")) {
				return false;
			}	
		}
		if (!checkelement("merchantForm", "fs_kp_cycle", "��Ʊ����")) {
			return false;
		}
		var obj1 = document.getElementById("fs_kp_cycle"); //��λid
		var index1 = obj1.selectedIndex; // ѡ������
		var fs_kp_cycle = obj1.options[index1].value; // ѡ��ֵ
		if(fs_kp_cycle!=""){
			if (!checkelement("merchantForm", "fs_kp_cycle_param", "��Ʊ���ڲ���")) {
				return false;
			}	
		}
		if (!checkelement("merchantForm", "last_kp_date", "�ϴο�Ʊ����")) {
			return false;
		}*/
		//У��������ںͽ������
		var fs_cycle=document.getElementsByName("fs_cycle")[0].value;
		var fs_cycle_param=document.getElementsByName("fs_cycle_param")[0].value; 	
		if(fs_cycle_param !=null&&fs_cycle_param !=""){
			if(fs_cycle ==null||fs_cycle ==""){
			alert("��ѡ���������");
			document.forms["merchantForm"].elements["fs_cycle"].focus();
			return false;
			}
	   }
	   if(fs_cycle !=null&&fs_cycle !=""){
			if(fs_cycle_param ==null||fs_cycle_param ==""){
			alert("��ѡ��������");
			document.forms["merchantForm"].elements["fs_cycle_param"].focus();
			return false;
			}
	   }
		//У�鿪Ʊ���ںͿ�Ʊ���ڲ���
		var fs_kp_cycle=document.getElementsByName("fs_kp_cycle")[0].value;
		var fs_kp_cycle_param=document.getElementsByName("fs_kp_cycle_param")[0].value;
		if(fs_kp_cycle_param !=null&&fs_kp_cycle_param !=""){
			if(fs_kp_cycle ==null||fs_kp_cycle ==""){
			alert("��ѡ��Ʊ����");
			document.forms["merchantForm"].elements["fs_kp_cycle"].focus();
			return false;
			}
	   }
	   if(fs_kp_cycle !=null&&fs_kp_cycle !=""){
			if(fs_kp_cycle_param ==null||fs_kp_cycle_param ==""){
			alert("��ѡ��Ʊ���ڲ���");
			document.forms["merchantForm"].elements["fs_kp_cycle_param"].focus();
			return false;
			}
	   }
	   getOnchangeEmail();
	   $("#btTJ").attr("disabled","true");
	
		document.merchantForm.submit();
		
	}
    //����Ƿ�Ϊ��      
   function checkNull(name, info)   {      
	  var nameArray = document.getElementsByName(name);  
	  for(var i = 0;i< nameArray.length; i++){
	   if(nameArray[i].value == ""){   
	         alert(info);  
	         return true;   
	   }
	  } 
	  return false;     
  	 } 
   function trim(str){ //ɾ���������˵Ŀո�
      return str.replace(/(^\s*)|(\s*$)/g, "");
   }
  
   function checkFs_cycle_param(){
	   var str=document.getElementsByName("fs_cycle_param")[0].value;
	   var count = str.match(/,/g).length;
	   if(str.match(/\��/g)!= null){
			alert("����������ܺ������ķ����뷨�Ķ���");  
			document.getElementsByName("fs_cycle_param")[0].value="";
		  	document.forms["merchantForm"].elements["fs_cycle_param"].focus();
	      	return -1;
	   }
	   if(count>3) {
			alert("����������3������,��4��");
			document.getElementsByName("fs_cycle_param")[0].value="";
		  	document.forms["merchantForm"].elements["fs_cycle_param"].focus();
	      	return -1;
	   } 	   
	  /*
	   var reg =/^[0-9,]+$/;
	   if(!reg.test(str)){
		   alert("�������ֻ�������ֺͶ���");
		   document.getElementsByName("fs_cycle_param")[0].value="";
		   document.forms["merchantForm"].elements["fs_cycle_param"].focus();
	       return -1;		    
	   }
	   */	
	} 
   function checkFs_kp_cycle_param(){ 
	   var str=document.getElementsByName("fs_kp_cycle_param")[0].value;	
	   var count = str.match(/,/g).length;
	   if(str.match(/\��/g)!= null){
			alert("��Ʊ���ڲ������ܺ������ķ����뷨�Ķ���");  
			document.getElementsByName("fs_kp_cycle_param")[0].value="";
		  	document.forms["merchantForm"].elements["fs_kp_cycle_param"].focus();
	      	return -1;
	   }
	   if(count>3) {
			alert("��Ʊ���ڲ������3������,��4��");
			document.getElementsByName("fs_kp_cycle_param")[0].value="";
		  	document.forms["merchantForm"].elements["fs_kp_cycle_param"].focus();
	      	return -1;
	   } 
	  /*
	   var reg =/^[0-9,]+$/;
	   if(!reg.test(str)){
		   alert("��Ʊ����ֻ�������ֺͶ���");
		   document.getElementsByName("fs_kp_cycle_param")[0].value="";
		   document.forms["merchantForm"].elements["fs_kp_cycle_param"].focus();
	       return -1;		    
	   }	
	   */
	}	
   function verifyCardNum(idCard) {
		// 15λ��18λ���֤�����������ʽ
		var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
		// ���ͨ������֤��˵�����֤��ʽ��ȷ����׼ȷ�Ի������
		if (regIdCard.test(idCard)) {
			if (idCard.length == 18) {
				var idCardWi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
						5, 8, 4, 2); // ��ǰ17λ��Ȩ���ӱ�����������
				var idCardY = new Array(1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2); // ���ǳ���11�󣬿��ܲ�����11λ��������֤�룬Ҳ���������
				var idCardWiSum = 0; // ��������ǰ17λ���Թ��Լ�Ȩ���Ӻ���ܺ�
				for (var i = 0; i < 17; i++) {
					idCardWiSum += idCard.substring(i, i + 1) * idCardWi[i];
				}
				var idCardMod = idCardWiSum % 11;// �����У�������������λ��
				var idCardLast = idCard.substring(17);// �õ����һλ���֤����
				
				// �������2����˵��У������10�����֤�������һλӦ����X
				if (idCardMod == 2) {
					if (idCardLast == "X" || idCardLast == "x") {
						return true;
					} else {
						return false;
					}
				} else {
					// �ü��������֤�������һλ���֤����ƥ�䣬���һ�£�˵��ͨ������������Ч�����֤����
					if (idCardLast == idCardY[idCardMod]) {
						return true;
					} else {
						return false;
					}
				}
			}
		} else {
			return false;
		}
	}
</script>

<body onload="loadMy();">
	<html:form styleId="merchantForm"
		action="/merchant?method=addmerchantBeanInfo"
		enctype="multipart/form-data" method="post">
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
								style="  width:28px; height:28px; background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">

							</td>
							<td
								style="height:28px;  background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;��ǰλ�ã� �̻��ն˹��� &gt; �����̻�
							</td>
							<td
								style=" width:7px; height:28px; background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
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
								�̻���
							</td>
							<td align="left" class="box2">
								<html:text property="mrchno" maxlength="15" onblur="checkMrchNo();" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
								<font color="red">*</font>
							</td>
						</tr> 
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻�����
							</td>
							<td align="left" class="box2">
								<html:text property="mrcht_name" maxlength="8"></html:text>
								<font color="red">*</font>&nbsp;&nbsp;&nbsp;
								����̻�����
								<html:select property="yard_mer_type" >
									<html:option value="">��ѡ��</html:option>
									<html:option value="0">��Ƴ���</html:option>
								</html:select> 
								
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻�ժҪ
							</td>
							<td align="left" class="box2">
								<html:text property="mrch_snippet" maxlength="256"></html:text>
								
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								ISO��ӦMCC��
							</td>
							<td align="left" class="box2">
								<logic:present name="MCCList">
									<html:select property="acptbus">
										<html:option value="">����ѡ��</html:option>
										<html:optionsCollection name="MCCList" label="descr"
											value="id" />
									</html:select>
								</logic:present>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								ע���ַ��ϵ��
							</td>
							<td align="left" class="box2">
								<html:text property="contact3" maxlength="6"></html:text>
								<font color="red">*</font>
							</td>
						</tr>

						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�ʼĵ�ַ��ϵ�绰
							</td>
							<td align="left" class="box2">
								<html:text property="telno1" maxlength="16"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻�״̬
							</td>
							<td align="left" class="box2">
								<html:select property="mrchstat">
									<html:option value="">����ѡ��</html:option>
									<html:option value="0">����</html:option>
									<html:option value="1">������</html:option>
									<html:option value="2">������</html:option>
								</html:select>
								<font color="red">*</font>
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
						<tr>
							<td colspan="2" class="box1">
								�̻�������Ϣ
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻���ϵ��ַ
							</td>
							<td align="left" class="box2">
								<html:text property="address" maxlength="50"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����ʱ��
							</td>
							<td align="left" class="box2">
								<html:text property="add_date" maxlength="15"
									onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
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
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
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
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����
							</td>
							<td align="left" class="box2">
								<html:select property="zone">
									<html:option value="">����ѡ��</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�Ƿ����ڱ���
							</td>
							<td align="left" class="box2">
								<html:select property="is_bj">
									<html:option value="1">��</html:option>
									<html:option value="0">��</html:option>
								</html:select>
							</td>
						</tr>						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻�����
							</td>
							<td align="left" class="box2">
								<html:select property="type_yf">
									<html:option value="">����ѡ��</html:option>								
									<logic:present name="cardbatch_mer_typeList">
										<html:optionsCollection name="cardbatch_mer_typeList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								������������������
							</td>
							<td align="left" class="box2">
								<html:text property="agent" maxlength="6"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��������������֤������
							</td>
							<td align="left" class="box2">
								<html:select property="id_type" styleId="id_type">
									<html:option value="">����ѡ��</html:option>
									<html:option value="1">�������֤</html:option>
									<html:option value="2">���ڱ�</html:option>
									<html:option value="3">�������֤</html:option>
									<html:option value="4">��װ�������֤</html:option>
									<html:option value="5">�����ڵ�ͨ��֤</html:option>
									<html:option value="6">������½ͨ��֤</html:option>
									<html:option value="7">����</html:option>
									<html:option value="8">����</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��������������֤������
							</td>
							<td align="left" class="box2">
								<html:text property="id_no" styleId="id_no" maxlength="20"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��������������֤����Ч��
							</td>
							<td align="left" class="box2">
								<html:text property="id_validity" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								���������ˣ������ˣ�����
							</td>
							<td align="left" class="box2">
								<html:text property="legal_rep" maxlength="6"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����������֤������
							</td>
							<td align="left" class="box2">
								<html:select property="lr_id_type" styleId="lr_id_type">
									<html:option value="">����ѡ��</html:option>
									<html:option value="1">�������֤</html:option>
									<html:option value="2">���ڱ�</html:option>
									<html:option value="3">�������֤</html:option>
									<html:option value="4">��װ�������֤</html:option>
									<html:option value="5">�����ڵ�ͨ��֤</html:option>
									<html:option value="6">������½ͨ��֤</html:option>
									<html:option value="7">����</html:option>
									<html:option value="8">����</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����������֤������
							</td>
							<td align="left" class="box2">
								<html:text property="lr_id_no" styleId="lr_id_no" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����������֤����Ч��
							</td>
							<td align="left" class="box2">
								<html:text property="lr_id_validity" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
							</td>
						</tr>						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�عɹɶ���ʵ�ʿ���������
							</td>
							<td align="left" class="box2">
								<html:text property="man_name" maxlength="6"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�عɹɶ���ʵ�ʿ�����֤������
							</td>
							<td align="left" class="box2">
								<html:select property="id_type1" styleId="id_type1">
									<html:option value="">����ѡ��</html:option>
									<html:option value="1">�������֤</html:option>
									<html:option value="2">���ڱ�</html:option>
									<html:option value="3">�������֤</html:option>
									<html:option value="4">��װ�������֤</html:option>
									<html:option value="5">�����ڵ�ͨ��֤</html:option>
									<html:option value="6">������½ͨ��֤</html:option>
									<html:option value="7">����</html:option>
									<html:option value="8">����</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�عɹɶ���ʵ�ʿ�����֤������
							</td>
							<td align="left" class="box2">
								<html:text property="id_no1" styleId="id_no1" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�عɹɶ���ʵ�ʿ�����֤����Ч��
							</td>
							<td align="left" class="box2">
								<html:text property="id_deadline1" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								Ӫҵִ�պ�
							</td>
							<td align="left" class="box2">
								<html:text property="bus_lic_no" maxlength="30" onkeyup="value=value.replace(/[^\w\/]/ig,'')" ></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								Ӫҵִ������
							</td>
							<td align="left" class="box2">
								<html:text property="acc_x_name" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								Ӫҵִ�����ʱ��
							</td>
							<td align="left" class="box2">
								<html:text property="bus_lic_validity" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								˰��Ǽ�֤���
							</td>
							<td align="left" class="box2">
								<html:text property="tax_id" maxlength="30" onkeyup="value=value.replace(/[^\w\/]/ig,'')" ></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								˰��Ǽ�֤���ʱ��
							</td>
							<td align="left" class="box2">
								<html:text property="tax_id_validity" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��֯����֤���
							</td>
							<td align="left" class="box2">
								<html:text property="org_id" maxlength="30" onkeyup="value=value.replace(/[^\w\/]/ig,'')" ></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��֯����֤���ʱ��
							</td>
							<td align="left" class="box2">
								<html:text property="org_validity" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								Ӫҵִ��ͼƬ
							</td>
							<td align="left" class="box2">
								<html:file property="bus_lic_pic_f" accept="image/*" maxlength="16"></html:file>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								˰��Ǽ�֤ͼƬ
							</td>
							<td align="left" class="box2">
								<html:file property="tax_id_pic_f" accept="image/*" maxlength="16"></html:file>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��֯����֤ͼƬ
							</td>
							<td align="left" class="box2">
								<html:file property="org_id_pic_f" accept="image/*" maxlength="16"></html:file>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��ҵ�˺�
							</td>
							<td align="left" class="box2">
								<html:text property="mx_accno" maxlength="28" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
								<font color="red">*</font>
							</td>
						</tr>

						<tr>
							<td style="width: 110px;" align="right" class="box1">
								���㼶��
							</td>
							<td align="left" class="box2">
								<html:select property="classify">
									<html:option value="">����ѡ��</html:option>
									<html:option value="0">�����̻�</html:option>
									<html:option value="1">�����̻�</html:option>
									<html:option value="2">�����̻�</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								���̻�
							</td>
							<td align="left" class="box2">
								<html:text property="mrchnoSelQ" maxlength="15"	onchange="getMerchantBeanListH();"></html:text>
								<html:select property="fmrchno">
									<html:option value=" ">����ѡ��</html:option>
									<logic:present name="mrchList">
										<html:optionsCollection name="mrchList" label="name"
											value="mrchno" />
									</logic:present>
								</html:select>
							</td>
						</tr>	
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻���������
							</td>
							<td align="left" class="box2">
								<html:select property="merchant_org">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="merchantOrgList">
										<html:optionsCollection name="merchantOrgList"
											label="orgName" value="orgId" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�����������
							</td>
							<td align="left" class="box2">
								<html:select property="merchant_fenrunorg">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="merchantFenrunOrgList">
										<html:optionsCollection name="merchantFenrunOrgList"
											label="orgName" value="orgId" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����
							</td>
							<!-- <td align="left" class="box2"> -->
							<td align="left" class="box2">
							<!-- <td style="width: 110px;" align="left" class="box1"> -->
								<html:text property="merchant_email"  style="width:500px;" onchange="getOnchangeEmail();" maxlength="2000"></html:text>
								<font color="red">���������ʹ��Ӣ�Ķ��ţ�,������</font>
							</td>
						</tr>					
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����Ա
							</td>
							<td align="left" class="box2">
								<html:text property="settlement_person" maxlength="20"></html:text>
							</td>
						</tr>					
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�Ƿ��Ͷ�����ϸ
							</td>
							<td align="left" class="box2">
								<html:select property="is_send_billdetail">
									<html:option value="1">��</html:option>
									<html:option value="0">��</html:option>
								</html:select>
							</td>
						</tr>
						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��ҵ��Ӫ��Χ
							</td>
							<td align="left" class="box2">
								<html:text property="merchant_x_operate" style="width:500px;" maxlength="165"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻�����
							</td>
							<td align="left" class="box2">
								<html:select property="merchant_x_type">
									<html:option value="12">��λ</html:option>
									<html:option value="11">��Ȼ��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								ע���ʱ���
							</td>
							<td align="left" class="box2">
								<html:text property="merchant_x_reg_amt" value="0" maxlength="18" onchange="clearNoNum(this);" ></html:text>
								<font color="red">*</font>
								��Ԫ  ���� ����
								<%-- <html:select property="merchant_x_code">
									<html:option value="RMB">�����-RMB</html:option>
									<html:option value="USD">��Ԫ-USD</html:option>
								</html:select> --%>
								<html:select property="merchant_x_code">
									<logic:present name="merchant_x_code_typeList">
										<html:optionsCollection name="merchant_x_code_typeList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�ڼ��ս��������Ƿ�ϲ�
							</td>
							<td align="left" class="box2">
								<html:select property="combine_flag">
									<html:option value="1">�ϲ�</html:option>
									<html:option value="0">���ϲ�</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>	
									
						<tr>
							<td colspan="2" class="box1">
								�̻�������Ϣ
							</td>
						</tr>						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��������
							</td>
							<td align="left" class="box2">
								<html:select property="fs_cycle" disabled="true">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="fs_cycleList">
										<html:optionsCollection name="fs_cycleList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
								<html:hidden property="fs_cycle" />
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�������
							</td>
							<td align="left" class="box2">
								<html:text property="fs_cycle_param" maxlength="28" disabled="true" onblur="checkFs_cycle_param();" onkeyup="this.value=this.value.replace(/[^\d|,|��]/g,'')"></html:text>
								<html:hidden property="fs_cycle_param" />
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�ϴν�������
							</td>
							<td align="left" class="box2">
								<html:text property="last_settle_date" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,readOnly:true});" disabled="true"></html:text>
								<html:hidden property="last_settle_date" />
							</td>
						</tr>	
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�Ƿ��ղ�ͬ���ѳ�������
							</td>
							<td align="left" class="box2">
								<html:select property="is_consump_category" disabled="true">
									<html:option value="0">��</html:option>
									<html:option value="1">��</html:option>
								</html:select>
								<html:hidden property="is_consump_category" />
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�Ƿ�ϲ����
							</td>
							<td align="left" class="box2">
								<html:select property="amt_consump_category" disabled="true">
									<html:option value="0">��</html:option>
									<html:option value="1">��</html:option>
								</html:select>
								<html:hidden property="amt_consump_category" />
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�տ�/ר�����Ƿ�ϲ����
							</td>
							<td align="left" class="box2">
								<html:select property="merge_money_flag" disabled="true">
									<html:option value="1">��</html:option>
									<html:option value="0">��</html:option>
								</html:select>
								<html:hidden property="merge_money_flag" />
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�Ƿ��տ�������
							</td>
							<td align="left" class="box2">
								<html:select property="is_card_type_group" disabled="true">
									<html:option value="0">��</html:option>
									<html:option value="1">��</html:option>
								</html:select>
								<html:hidden property="is_card_type_group" />
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�Ƿ񰴸��̻�����
							</td>
							<td align="left" class="box2">
								<html:select property="is_fmrchno_decide" disabled="true">
									<html:option value="0">��</html:option>
									<html:option value="1">��</html:option>
								</html:select>
								<html:hidden property="is_fmrchno_decide" />
							</td>
						</tr>												
						<tr>
							<td colspan="2" class="box1">
								�̻���Ʊ��Ϣ
							</td>
						</tr>		
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�Ƿ�Ʊ
							</td>
							<td align="left" class="box2">
								<html:select property="is_kp" disabled="true">
									<html:option value="1">��</html:option>
									<html:option value="0">��</html:option>
								</html:select>
								<html:hidden property="is_kp" />
							</td>
						</tr>										
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��Ʊ����
							</td>
							<td align="left" class="box2">
								<html:select property="fs_kp_cycle" disabled="true">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="fs_kp_cycleList">
										<html:optionsCollection name="fs_kp_cycleList"
											label="param_name" value="param_value" />
									</logic:present>
								</html:select>
								<html:hidden property="fs_kp_cycle" />
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��Ʊ���ڲ���
							</td>
							<td align="left" class="box2">
								<html:text property="fs_kp_cycle_param" maxlength="28" disabled="true" onblur="checkFs_kp_cycle_param();" onkeyup="this.value=this.value.replace(/[^\d|,|��]/g,'')"></html:text>
								<html:hidden property="fs_kp_cycle_param" />
							</td>
						</tr>	
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�ϴο�Ʊ����
							</td>
							<td align="left" class="box2">
								<html:text property="last_kp_date" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,readOnly:true});" disabled="true"></html:text>
								<html:hidden property="last_kp_date" />
							</td>
						</tr>																
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="height: 23px;" align="center" class="box1">
								<input id="btTJ" class="button" type="button" onclick="commit()"
									value="����" />
								&nbsp;&nbsp;&nbsp;
								<input class="button" type="button"  value="�ر�" onClick="javascript:window.close()">

							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>


