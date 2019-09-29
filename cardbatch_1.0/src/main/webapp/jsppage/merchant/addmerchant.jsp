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

	<title>商户增加页面</title>
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
		var year = now.getFullYear(); 	//年
		var month = now.getMonth() + 1; //月
		var day = now.getDate(); 		//日
		var hour = now.getHours();		//时
		var minute = now.getMinutes();	//分
		var second = now.getSeconds();	//秒
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

	//根据mrchnoSelQ获取mrchno的可选项
	function getMerchantBeanListH(){
		var mrchnoSelQ = document.getElementsByName("mrchnoSelQ")[0].value;
		if(trim(mrchnoSelQ)!=''){
			mrchno = document.getElementsByName("fmrchno")[0];
			document.getElementsByName("fmrchno")[0].options.length = 0;
			mrchno.innerHTML = "";
			mrchno.add(new Option("－请选择－", ""));
			instManage.getMerchantBeanListByMrchNoOrName(null, null,'1',mrchnoSelQ,'yes',null, function(data) {
				for (i = 0; i < data.length; i++) {
					mrchno.add(new Option(data[i].name, data[i].mrchno));
				}
			});			
		}
	}
	//根据mrchnoSelQ获取mrchno的可选项
	/* function getOnchangeEmail(){
		var email = document.getElementsByName("merchant_email")[0].value;
		var reEmail = /^([A-Za-z0-9])(\w)+@(\w)+(\.)(com|com\.cn|net|cn|net\.cn|org|biz|info|gov|gov\.cn|edu|edu\.cn)/;
		if (!email.match(reEmail) && email != "") {
			alert('Email格式不对!');
			document.getElementsByName("merchant_email")[0].value='';
		}
	} */
	function clearNoNum(obj){ 
	    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
	    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
	    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数  
	    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
	    	if(obj.value.length >= 16){
	        	obj.value= parseFloat(obj.value.substring(0,15));
	        }else{
	        	obj.value= parseFloat(obj.value); 
	        }
	    } 
	}
	
	function getOnchangeEmail(){
		var email = document.getElementsByName("merchant_email")[0].value;
		//1、判断email是否为空
		//2、判断email中空邮箱
		//3、对字符串按，转成数组，遍历判断是否为邮箱
		//4、遍历时保存正确的邮箱号用于回显
		//邮箱正则
		//var reEmail = /^([A-Za-z0-9])(\w)+@(\w)+(\.)(com|com\.cn|net|cn|net\.cn|org|biz|info|gov|gov\.cn|edu|edu\.cn)$/;
		//var reEmail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		var reEmail = /^([a-zA-Z0-9\u4e00-\u9fa5]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		//返回邮箱
		var retEmail = "";
		//1
		if(email == ""){
			return false;
		}else{
			//2
			var arrnow = [];
			//3、
			var arr = email.split(",");
			var n = arr.length;//邮箱个数
			if(n > 5){
				alert("邮箱不能超过5个");
				document.getElementsByName("merchant_email")[0].value=retEmail;
				return false;
			}
			for(j = 0,len=arr.length; j < len; j++) {
				if(arr[j] != ""){
					arrnow.push(arr[j]);//去掉空邮箱
				}
			}
			if(arrnow.length == 0){
				alert('Email格式不对!');
				document.getElementsByName("merchant_email")[0].value=retEmail;
				return false;
			}
			for(j = 0,lens=arrnow.length; j < lens; j++) {
   				if (!arrnow[j].match(reEmail) && arrnow[j] != "") {
					alert(arrnow[j]+':Email格式不对!');
					document.getElementsByName("merchant_email")[0].value=retEmail;
					return false;
				}else{
					//4
					if(j != 0){
						//判断邮箱是否重复
						if(retEmail.indexOf(arrnow[j]) != -1){
							alert(arrnow[j]+':Email已经输入，请核查!');
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
	/*删除后面空格*/
	String.prototype.RTrim = function() {
		return this.replace(/(\s*$)/g, "");
	}
	function commit() {
		
		if (!checkelement("merchantForm", "mrchno", "商户号")) {
			document.forms["merchantForm"].elements["acptbus"].focus();
			return false;
		}
		
		var mrchno=document.getElementsByName("mrchno")[0].value;
		if(trim(mrchno)!=''){
			if(mrchno.length!=15){
			   alert("商户号必须是15位数字");
			   document.forms["merchantForm"].elements["acptbus"].focus();
			   return false;		
			}
		} 
		
		if (!checkelement("merchantForm", "mrcht_name", "商户名称")) {
			return false;
		}
		if (!checkelement("merchantForm", "acptbus", "ISO对应MCC码")) {
			return false;
		}
		if(trim(document.getElementsByName("contact3")[0].value)==""){
			alert("注册地址联系人不能为空");
			return false;
		}
		if (!isnumberonly3("merchantForm", "telno1", "邮寄地址联系电话")) {
			return false;
		}
		
		//号码校验
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
				alert("请正确填写您的电话号码");
				document.forms["merchantForm"].elements["telno1"].focus();
				return false;
			}	
		}		
		
		if (!checkelement("merchantForm", "mrchstat", "商户状态")) {
			return false;
		}
		if (!checkelement("merchantForm", "add_date", "增加时间")) {
			return false;
		}	
		if (!checkelement("merchantForm", "province", "省份")) {
			return false;
		}
		if (!checkelement("merchantForm", "city_no", "城市")) {
			return false;
		}
		if (!checkelement("merchantForm", "zone", "区域")) {
			return false;
		}	 
		if (!checkelement("merchantForm", "type_yf", "商户类型")) {
			return false;
		}
		if (!checkelement("merchantForm", "agent", "代办手续经办人姓名")) {
			return false;
		}
		if (!checkelement("merchantForm", "id_type", "办理手续经办人证件类型")) {
			return false;
		}
		if (!checkelement("merchantForm", "id_no", "办理手续经办人证件号码")) {
			return false;
		}
		var id_type = document.getElementById("id_type").value;
		var id_no = $("#id_no").val();
		if(id_type == "1" || id_type == "3" || id_type == "4"){
		  	if(!verifyCardNum(id_no)){
		  		alert("办理手续经办人证件号码不符合规范，请重新输入");
				$("#id_no").focus();
		  		return false;
		  	}
		}
		if (!checkCode("merchantForm", "id_no", "办理手续经办人证件号码只能是数字和字母")) {
			return false;
		}
		if (!checkelement("merchantForm", "id_validity", "办理手续经办人证件有效期")) {
			return false;
		}
		
		/*if (!checkelement("merchantForm", "legal_rep", "法定代表人（负责人）姓名")) {
			return false;
		}
		if (!checkelement("merchantForm", "lr_id_type", "法定代表人证件类型")) {
			return false;
		}
		 if (!checkelement("merchantForm", "lr_id_no", "法定代表人证件号码")) {
			return false;
		}
		if (!checkCode("merchantForm", "lr_id_no", "法定代表人证件号码只能是数字和字母")) {
			return false;
		}
		if (!checkelement("merchantForm", "lr_id_validity", "法定代表人证件有效期")) {
			return false;
		} */
		var lr_id_type = document.getElementById("lr_id_type").value;
		var lr_id_no = $("#lr_id_no").val();
		if(lr_id_type == "1" || lr_id_type == "3" || lr_id_type == "4"){
		    if(lr_id_no != null && trim(lr_id_no) != ''){
		    	if(!verifyCardNum(lr_id_no)){
			  		alert("法定代表人证件号码不符合规范，请重新输入");
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
			  		alert("控股股东或实际控制人证件号码不符合规范，请重新输入");
					$("#id_no1").focus();
			  		return false;
			  	}
		    }
		}
		//只能上传图片
		var taxFile1 =$("input[name='bus_lic_pic_f']").val()  
		var extend1 = taxFile1.substring(taxFile1.lastIndexOf(".")+1); 
	    if(extend1=="" || extend1 == null){ 
	    }else{ 
			if(!(extend1=="jpg"||extend1=="jpeg"||extend1=="gif"||extend1=="png"||extend1=="bmp"||
					extend1=="JPG"||extend1=="JPEG"||extend1=="GIF"||extend1=="PNG"||extend1=="BMP")){ 
			   alert("文件格式错误, 请上传图片格式！"); 
			   return false;
			} 
	    }
	    var taxFile2 =$("input[name='tax_id_pic_f']").val()  
		var extend2 = taxFile2.substring(taxFile2.lastIndexOf(".")+1); 
	    if(extend2=="" || extend2 == null){ 
	    }else{ 
			if(!(extend2=="jpg"||extend2=="jpeg"||extend2=="gif"||extend2=="png"||extend2=="bmp"||
					extend2=="JPG"||extend2=="JPEG"||extend2=="GIF"||extend2=="PNG"||extend2=="BMP")){ 
			   alert("文件格式错误, 请上传图片格式！"); 
			   return false;
			} 
	    }
	    var taxFile3 =$("input[name='org_id_pic_f']").val()  
		var extend3 = taxFile3.substring(taxFile3.lastIndexOf(".")+1); 
	    if(extend3=="" || extend3 == null){ 
	    }else{ 
			if(!(extend3=="jpg"||extend3=="jpeg"||extend3=="gif"||extend3=="png"||extend3=="bmp"||
					extend3=="JPG"||extend3=="JPEG"||extend3=="GIF"||extend3=="PNG"||extend3=="BMP")){ 
			   alert("文件格式错误, 请上传图片格式！"); 
			   return false;
			} 
	    }
		if (!checkelement("merchantForm", "bus_lic_no", "营业执照号")) {
			return false;
		}
		if (!checkelement("merchantForm", "bus_lic_validity", "营业执照年检时间")) {
			return false;
		}
		if (!checkelement("merchantForm", "tax_id", "税务登记证编号")) {
			return false;
		}
		if (!checkelement("merchantForm", "tax_id_validity", "税务登记证年检时间")) {
			return false;
		}
		if (!checkelement("merchantForm", "org_id", "组织机构证编号")) {
			return false;
		}
		if (!checkelement("merchantForm", "org_validity", "组织机构证年检时间")) {
			return false;
		}
		if(trim(document.getElementsByName("mx_accno")[0].value)==""){
			alert("企业账号不能为空");
			return false;
		}
		if(trim(document.getElementsByName("merchant_x_reg_amt")[0].value)==""){
			alert("注册资本金不能为空");
			return false;
		}
		if (!checkelement("merchantForm", "classify", "结算级别")) {
			return false;
		}
		/* if (!checkelement("merchantForm", "fs_cycle", "结算周期")) {
			return false;
		}
		var obj = document.getElementById("fs_cycle"); //定位id
		var index = obj.selectedIndex; // 选中索引
		var fs_cycle = obj.options[index].value; // 选中值
		if(fs_cycle!=""&&fs_cycle!="2"){
			if (!checkelement("merchantForm", "fs_cycle_param", "结算周期参数")) {
				return false;
			}	
		}
		if (!checkelement("merchantForm", "fs_kp_cycle", "开票周期")) {
			return false;
		}
		var obj1 = document.getElementById("fs_kp_cycle"); //定位id
		var index1 = obj1.selectedIndex; // 选中索引
		var fs_kp_cycle = obj1.options[index1].value; // 选中值
		if(fs_kp_cycle!=""){
			if (!checkelement("merchantForm", "fs_kp_cycle_param", "开票周期参数")) {
				return false;
			}	
		}
		if (!checkelement("merchantForm", "last_kp_date", "上次开票日期")) {
			return false;
		}*/
		//校验结算周期和结算参数
		var fs_cycle=document.getElementsByName("fs_cycle")[0].value;
		var fs_cycle_param=document.getElementsByName("fs_cycle_param")[0].value; 	
		if(fs_cycle_param !=null&&fs_cycle_param !=""){
			if(fs_cycle ==null||fs_cycle ==""){
			alert("请选择结算周期");
			document.forms["merchantForm"].elements["fs_cycle"].focus();
			return false;
			}
	   }
	   if(fs_cycle !=null&&fs_cycle !=""){
			if(fs_cycle_param ==null||fs_cycle_param ==""){
			alert("请选择结算参数");
			document.forms["merchantForm"].elements["fs_cycle_param"].focus();
			return false;
			}
	   }
		//校验开票周期和开票周期参数
		var fs_kp_cycle=document.getElementsByName("fs_kp_cycle")[0].value;
		var fs_kp_cycle_param=document.getElementsByName("fs_kp_cycle_param")[0].value;
		if(fs_kp_cycle_param !=null&&fs_kp_cycle_param !=""){
			if(fs_kp_cycle ==null||fs_kp_cycle ==""){
			alert("请选择开票周期");
			document.forms["merchantForm"].elements["fs_kp_cycle"].focus();
			return false;
			}
	   }
	   if(fs_kp_cycle !=null&&fs_kp_cycle !=""){
			if(fs_kp_cycle_param ==null||fs_kp_cycle_param ==""){
			alert("请选择开票周期参数");
			document.forms["merchantForm"].elements["fs_kp_cycle_param"].focus();
			return false;
			}
	   }
	   getOnchangeEmail();
	   $("#btTJ").attr("disabled","true");
	
		document.merchantForm.submit();
		
	}
    //检查是否为空      
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
   function trim(str){ //删除左右两端的空格
      return str.replace(/(^\s*)|(\s*$)/g, "");
   }
  
   function checkFs_cycle_param(){
	   var str=document.getElementsByName("fs_cycle_param")[0].value;
	   var count = str.match(/,/g).length;
	   if(str.match(/\，/g)!= null){
			alert("结算参数不能含有中文法输入法的逗号");  
			document.getElementsByName("fs_cycle_param")[0].value="";
		  	document.forms["merchantForm"].elements["fs_cycle_param"].focus();
	      	return -1;
	   }
	   if(count>3) {
			alert("结算参数最多3个逗号,分4段");
			document.getElementsByName("fs_cycle_param")[0].value="";
		  	document.forms["merchantForm"].elements["fs_cycle_param"].focus();
	      	return -1;
	   } 	   
	  /*
	   var reg =/^[0-9,]+$/;
	   if(!reg.test(str)){
		   alert("结算参数只能是数字和逗号");
		   document.getElementsByName("fs_cycle_param")[0].value="";
		   document.forms["merchantForm"].elements["fs_cycle_param"].focus();
	       return -1;		    
	   }
	   */	
	} 
   function checkFs_kp_cycle_param(){ 
	   var str=document.getElementsByName("fs_kp_cycle_param")[0].value;	
	   var count = str.match(/,/g).length;
	   if(str.match(/\，/g)!= null){
			alert("开票周期参数不能含有中文法输入法的逗号");  
			document.getElementsByName("fs_kp_cycle_param")[0].value="";
		  	document.forms["merchantForm"].elements["fs_kp_cycle_param"].focus();
	      	return -1;
	   }
	   if(count>3) {
			alert("开票周期参数最多3个逗号,分4段");
			document.getElementsByName("fs_kp_cycle_param")[0].value="";
		  	document.forms["merchantForm"].elements["fs_kp_cycle_param"].focus();
	      	return -1;
	   } 
	  /*
	   var reg =/^[0-9,]+$/;
	   if(!reg.test(str)){
		   alert("开票参数只能是数字和逗号");
		   document.getElementsByName("fs_kp_cycle_param")[0].value="";
		   document.forms["merchantForm"].elements["fs_kp_cycle_param"].focus();
	       return -1;		    
	   }	
	   */
	}	
   function verifyCardNum(idCard) {
		// 15位和18位身份证号码的正则表达式
		var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
		// 如果通过该验证，说明身份证格式正确，但准确性还需计算
		if (regIdCard.test(idCard)) {
			if (idCard.length == 18) {
				var idCardWi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
						5, 8, 4, 2); // 将前17位加权因子保存在数组里
				var idCardY = new Array(1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2); // 这是除以11后，可能产生的11位余数、验证码，也保存成数组
				var idCardWiSum = 0; // 用来保存前17位各自乖以加权因子后的总和
				for (var i = 0; i < 17; i++) {
					idCardWiSum += idCard.substring(i, i + 1) * idCardWi[i];
				}
				var idCardMod = idCardWiSum % 11;// 计算出校验码所在数组的位置
				var idCardLast = idCard.substring(17);// 得到最后一位身份证号码
				
				// 如果等于2，则说明校验码是10，身份证号码最后一位应该是X
				if (idCardMod == 2) {
					if (idCardLast == "X" || idCardLast == "x") {
						return true;
					} else {
						return false;
					}
				} else {
					// 用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
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
								&nbsp;&nbsp;当前位置： 商户终端管理 &gt; 增加商户
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
								商户号
							</td>
							<td align="left" class="box2">
								<html:text property="mrchno" maxlength="15" onblur="checkMrchNo();" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
								<font color="red">*</font>
							</td>
						</tr> 
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户名称
							</td>
							<td align="left" class="box2">
								<html:text property="mrcht_name" maxlength="8"></html:text>
								<font color="red">*</font>&nbsp;&nbsp;&nbsp;
								万科商户类型
								<html:select property="yard_mer_type" >
									<html:option value="">请选择</html:option>
									<html:option value="0">万科车场</html:option>
								</html:select> 
								
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户摘要
							</td>
							<td align="left" class="box2">
								<html:text property="mrch_snippet" maxlength="256"></html:text>
								
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								ISO对应MCC码
							</td>
							<td align="left" class="box2">
								<logic:present name="MCCList">
									<html:select property="acptbus">
										<html:option value="">－请选择－</html:option>
										<html:optionsCollection name="MCCList" label="descr"
											value="id" />
									</html:select>
								</logic:present>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								注册地址联系人
							</td>
							<td align="left" class="box2">
								<html:text property="contact3" maxlength="6"></html:text>
								<font color="red">*</font>
							</td>
						</tr>

						<tr>
							<td style="width: 110px;" align="right" class="box1">
								邮寄地址联系电话
							</td>
							<td align="left" class="box2">
								<html:text property="telno1" maxlength="16"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户状态
							</td>
							<td align="left" class="box2">
								<html:select property="mrchstat">
									<html:option value="">－请选择－</html:option>
									<html:option value="0">可用</html:option>
									<html:option value="1">不可用</html:option>
									<html:option value="2">黑名单</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								启用时间
							</td>
							<td align="left" class="box2">
								<html:text property="enable_date" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
							</td>
						</tr>		
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								停用时间
							</td>
							<td align="left" class="box2">
								<html:text property="disabled_date" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
							</td>
						</tr>										
						<tr>
							<td colspan="2" class="box1">
								商户补充信息
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户联系地址
							</td>
							<td align="left" class="box2">
								<html:text property="address" maxlength="50"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								增加时间
							</td>
							<td align="left" class="box2">
								<html:text property="add_date" maxlength="15"
									onclick="WdatePicker({dateFmt:'yyyyMMddHHmmss'});"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								省份
							</td>
							<td align="left" class="box2">
								<html:select property="province" onchange="getCity_no()">
									<html:option value="">－请选择－</html:option>
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
								城市
							</td>
							<td align="left" class="box2">
								<html:select property="city_no" onchange="getZone()">
									<html:option value="">－请选择－</html:option>
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
								区域
							</td>
							<td align="left" class="box2">
								<html:select property="zone">
									<html:option value="">－请选择－</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否属于北京
							</td>
							<td align="left" class="box2">
								<html:select property="is_bj">
									<html:option value="1">是</html:option>
									<html:option value="0">否</html:option>
								</html:select>
							</td>
						</tr>						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户类型
							</td>
							<td align="left" class="box2">
								<html:select property="type_yf">
									<html:option value="">－请选择－</html:option>								
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
								代办手续经办人姓名
							</td>
							<td align="left" class="box2">
								<html:text property="agent" maxlength="6"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								办理手续经办人证件类型
							</td>
							<td align="left" class="box2">
								<html:select property="id_type" styleId="id_type">
									<html:option value="">－请选择－</html:option>
									<html:option value="1">居民身份证</html:option>
									<html:option value="2">户口本</html:option>
									<html:option value="3">军人身份证</html:option>
									<html:option value="4">武装警察身份证</html:option>
									<html:option value="5">往来内地通行证</html:option>
									<html:option value="6">往来大陆通行证</html:option>
									<html:option value="7">护照</html:option>
									<html:option value="8">其他</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								办理手续经办人证件号码
							</td>
							<td align="left" class="box2">
								<html:text property="id_no" styleId="id_no" maxlength="20"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								办理手续经办人证件有效期
							</td>
							<td align="left" class="box2">
								<html:text property="id_validity" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								法定代表人（负责人）姓名
							</td>
							<td align="left" class="box2">
								<html:text property="legal_rep" maxlength="6"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								法定代表人证件类型
							</td>
							<td align="left" class="box2">
								<html:select property="lr_id_type" styleId="lr_id_type">
									<html:option value="">－请选择－</html:option>
									<html:option value="1">居民身份证</html:option>
									<html:option value="2">户口本</html:option>
									<html:option value="3">军人身份证</html:option>
									<html:option value="4">武装警察身份证</html:option>
									<html:option value="5">往来内地通行证</html:option>
									<html:option value="6">往来大陆通行证</html:option>
									<html:option value="7">护照</html:option>
									<html:option value="8">其他</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								法定代表人证件号码
							</td>
							<td align="left" class="box2">
								<html:text property="lr_id_no" styleId="lr_id_no" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								法定代表人证件有效期
							</td>
							<td align="left" class="box2">
								<html:text property="lr_id_validity" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
							</td>
						</tr>						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								控股股东或实际控制人姓名
							</td>
							<td align="left" class="box2">
								<html:text property="man_name" maxlength="6"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								控股股东或实际控制人证件类型
							</td>
							<td align="left" class="box2">
								<html:select property="id_type1" styleId="id_type1">
									<html:option value="">－请选择－</html:option>
									<html:option value="1">居民身份证</html:option>
									<html:option value="2">户口本</html:option>
									<html:option value="3">军人身份证</html:option>
									<html:option value="4">武装警察身份证</html:option>
									<html:option value="5">往来内地通行证</html:option>
									<html:option value="6">往来大陆通行证</html:option>
									<html:option value="7">护照</html:option>
									<html:option value="8">其他</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								控股股东或实际控制人证件号码
							</td>
							<td align="left" class="box2">
								<html:text property="id_no1" styleId="id_no1" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								控股股东或实际控制人证件有效期
							</td>
							<td align="left" class="box2">
								<html:text property="id_deadline1" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								营业执照号
							</td>
							<td align="left" class="box2">
								<html:text property="bus_lic_no" maxlength="30" onkeyup="value=value.replace(/[^\w\/]/ig,'')" ></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								营业执照名称
							</td>
							<td align="left" class="box2">
								<html:text property="acc_x_name" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								营业执照年检时间
							</td>
							<td align="left" class="box2">
								<html:text property="bus_lic_validity" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								税务登记证编号
							</td>
							<td align="left" class="box2">
								<html:text property="tax_id" maxlength="30" onkeyup="value=value.replace(/[^\w\/]/ig,'')" ></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								税务登记证年检时间
							</td>
							<td align="left" class="box2">
								<html:text property="tax_id_validity" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								组织机构证编号
							</td>
							<td align="left" class="box2">
								<html:text property="org_id" maxlength="30" onkeyup="value=value.replace(/[^\w\/]/ig,'')" ></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								组织机构证年检时间
							</td>
							<td align="left" class="box2">
								<html:text property="org_validity" maxlength="10"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"></html:text>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								营业执照图片
							</td>
							<td align="left" class="box2">
								<html:file property="bus_lic_pic_f" accept="image/*" maxlength="16"></html:file>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								税务登记证图片
							</td>
							<td align="left" class="box2">
								<html:file property="tax_id_pic_f" accept="image/*" maxlength="16"></html:file>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								组织机构证图片
							</td>
							<td align="left" class="box2">
								<html:file property="org_id_pic_f" accept="image/*" maxlength="16"></html:file>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								企业账号
							</td>
							<td align="left" class="box2">
								<html:text property="mx_accno" maxlength="28" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
								<font color="red">*</font>
							</td>
						</tr>

						<tr>
							<td style="width: 110px;" align="right" class="box1">
								结算级别
							</td>
							<td align="left" class="box2">
								<html:select property="classify">
									<html:option value="">－请选择－</html:option>
									<html:option value="0">结算商户</html:option>
									<html:option value="1">交易商户</html:option>
									<html:option value="2">正常商户</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								父商户
							</td>
							<td align="left" class="box2">
								<html:text property="mrchnoSelQ" maxlength="15"	onchange="getMerchantBeanListH();"></html:text>
								<html:select property="fmrchno">
									<html:option value=" ">－请选择－</html:option>
									<logic:present name="mrchList">
										<html:optionsCollection name="mrchList" label="name"
											value="mrchno" />
									</logic:present>
								</html:select>
							</td>
						</tr>	
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户机构名称
							</td>
							<td align="left" class="box2">
								<html:select property="merchant_org">
									<html:option value="">－请选择－</html:option>
									<logic:present name="merchantOrgList">
										<html:optionsCollection name="merchantOrgList"
											label="orgName" value="orgId" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								分润机构名称
							</td>
							<td align="left" class="box2">
								<html:select property="merchant_fenrunorg">
									<html:option value="">－请选择－</html:option>
									<logic:present name="merchantFenrunOrgList">
										<html:optionsCollection name="merchantFenrunOrgList"
											label="orgName" value="orgId" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								邮箱
							</td>
							<!-- <td align="left" class="box2"> -->
							<td align="left" class="box2">
							<!-- <td style="width: 110px;" align="left" class="box1"> -->
								<html:text property="merchant_email"  style="width:500px;" onchange="getOnchangeEmail();" maxlength="2000"></html:text>
								<font color="red">多个邮箱请使用英文逗号（,）隔开</font>
							</td>
						</tr>					
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								结算员
							</td>
							<td align="left" class="box2">
								<html:text property="settlement_person" maxlength="20"></html:text>
							</td>
						</tr>					
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否发送对账明细
							</td>
							<td align="left" class="box2">
								<html:select property="is_send_billdetail">
									<html:option value="1">是</html:option>
									<html:option value="0">否</html:option>
								</html:select>
							</td>
						</tr>
						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								企业经营范围
							</td>
							<td align="left" class="box2">
								<html:text property="merchant_x_operate" style="width:500px;" maxlength="165"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								商户分类
							</td>
							<td align="left" class="box2">
								<html:select property="merchant_x_type">
									<html:option value="12">单位</html:option>
									<html:option value="11">自然人</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								注册资本金
							</td>
							<td align="left" class="box2">
								<html:text property="merchant_x_reg_amt" value="0" maxlength="18" onchange="clearNoNum(this);" ></html:text>
								<font color="red">*</font>
								万元  —— 币种
								<%-- <html:select property="merchant_x_code">
									<html:option value="RMB">人民币-RMB</html:option>
									<html:option value="USD">美元-USD</html:option>
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
								节假日结算日期是否合并
							</td>
							<td align="left" class="box2">
								<html:select property="combine_flag">
									<html:option value="1">合并</html:option>
									<html:option value="0">不合并</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>	
									
						<tr>
							<td colspan="2" class="box1">
								商户结算信息
							</td>
						</tr>						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								结算周期
							</td>
							<td align="left" class="box2">
								<html:select property="fs_cycle" disabled="true">
									<html:option value="">－请选择－</html:option>
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
								结算参数
							</td>
							<td align="left" class="box2">
								<html:text property="fs_cycle_param" maxlength="28" disabled="true" onblur="checkFs_cycle_param();" onkeyup="this.value=this.value.replace(/[^\d|,|，]/g,'')"></html:text>
								<html:hidden property="fs_cycle_param" />
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								上次结算日期
							</td>
							<td align="left" class="box2">
								<html:text property="last_settle_date" maxlength="16"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,readOnly:true});" disabled="true"></html:text>
								<html:hidden property="last_settle_date" />
							</td>
						</tr>	
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否按照不同消费场景结算
							</td>
							<td align="left" class="box2">
								<html:select property="is_consump_category" disabled="true">
									<html:option value="0">否</html:option>
									<html:option value="1">是</html:option>
								</html:select>
								<html:hidden property="is_consump_category" />
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否合并打款
							</td>
							<td align="left" class="box2">
								<html:select property="amt_consump_category" disabled="true">
									<html:option value="0">否</html:option>
									<html:option value="1">是</html:option>
								</html:select>
								<html:hidden property="amt_consump_category" />
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								普卡/专属卡是否合并打款
							</td>
							<td align="left" class="box2">
								<html:select property="merge_money_flag" disabled="true">
									<html:option value="1">是</html:option>
									<html:option value="0">否</html:option>
								</html:select>
								<html:hidden property="merge_money_flag" />
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否按照卡类别分组
							</td>
							<td align="left" class="box2">
								<html:select property="is_card_type_group" disabled="true">
									<html:option value="0">否</html:option>
									<html:option value="1">是</html:option>
								</html:select>
								<html:hidden property="is_card_type_group" />
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否按父商户结算
							</td>
							<td align="left" class="box2">
								<html:select property="is_fmrchno_decide" disabled="true">
									<html:option value="0">否</html:option>
									<html:option value="1">是</html:option>
								</html:select>
								<html:hidden property="is_fmrchno_decide" />
							</td>
						</tr>												
						<tr>
							<td colspan="2" class="box1">
								商户开票信息
							</td>
						</tr>		
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								是否开票
							</td>
							<td align="left" class="box2">
								<html:select property="is_kp" disabled="true">
									<html:option value="1">是</html:option>
									<html:option value="0">否</html:option>
								</html:select>
								<html:hidden property="is_kp" />
							</td>
						</tr>										
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								开票周期
							</td>
							<td align="left" class="box2">
								<html:select property="fs_kp_cycle" disabled="true">
									<html:option value="">－请选择－</html:option>
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
								开票周期参数
							</td>
							<td align="left" class="box2">
								<html:text property="fs_kp_cycle_param" maxlength="28" disabled="true" onblur="checkFs_kp_cycle_param();" onkeyup="this.value=this.value.replace(/[^\d|,|，]/g,'')"></html:text>
								<html:hidden property="fs_kp_cycle_param" />
							</td>
						</tr>	
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								上次开票日期
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
									value="保存" />
								&nbsp;&nbsp;&nbsp;
								<input class="button" type="button"  value="关闭" onClick="javascript:window.close()">

							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>


