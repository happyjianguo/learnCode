loadJS = function(url){var script = "<script type='text/javascript' src='" + url + "'></script>";document.write(script);}
loadJS("/js/checkform.js");
loadJS("/js/dwr.js");

//�û��������֤
function checkMerchantForm(){
	if (!checkelement("merchantForm","mc_agrno","Э����")) {
		return false;
	}
	if (!checkelement("merchantForm","mc_name","�û�����")) {
		return false;
	}
	if (!checkelement("merchantForm","mc_malocnam","��Ӫ��������")) {
		return false;
	}

	
//�ж���ҵ���벻Ϊ�� ����Ϊ�� 	
	if (trim(window.document.forms["merchantForm"].elements["mc_type"].value) != "" && window.document.forms["merchantForm"].elements["mc_type"].value != null) {	
		if (!isLenNumOnly("merchantForm","mc_type","��ҵ����",4)) {
			return false;
		}
	}
	var objisacct=document.all("tc_isacct");
	if(objisacct[0].checked==true){//��������˻�  ����֤�˻�	  
	
		if (!checkelement("merchantForm","mc_cactno","���˺�")) {
			return false;
		}
		if (!checkelement("merchantForm","mc_cactnam","���˺Ż���")) {
			return false;
		}
		if (!checkelement("merchantForm","mc_opnbnk","�˺ſ���������")) {
			return false;
		}
	}
	
	if (!checkelement("merchantForm","mc_scale","�û���ģ")) {
		return false;
	}
	if (!checkelement("merchantForm","mc_chadate","�շ�����")) {
		return false;
	}
	/*��ӵ�ʱ������������� ȥ�� 
	 * if (document.getElementById("mc_id4cons").value != '') {
		if (!isLenNumOnly("merchantForm","mc_id4cons","�������û����",15)) {
			return false;
		}
	}*/
	if (document.getElementById("mc_id4mob").value != '') {
		if (!isLenNumOnly("merchantForm","mc_id4mob","�ֻ�֧�����û����",15)) {
			return false;
		}
	}
	return true;
	/*
	if (!isCheckMerchant()) {alert("+++++");
		return false;
	} else {alert("--------");
		return true;
	}*/
	
}
//�û��������֤
function checkSignMerchantForm(){
//	if (!checkelement("merchantForm","mc_agrno","Э����")) {
//		return false;
//	}
//	if (!checkelement("merchantForm","mc_name","�û�����")) {
//		return false;
//	}
//	if (!checkelement("merchantForm","mc_malocnam","��Ӫ��������")) {
//		return false;
//	}
//	if (!checkelement("merchantForm","mckind_id","ƽ̨�û����")) {
//		return false;
//	}
	
	
//�ж���ҵ���벻Ϊ�� ����Ϊ�� 	
	if (trim(window.document.forms["merchantForm"].elements["mc_type"].value) != "" && window.document.forms["merchantForm"].elements["mc_type"].value != null) {	
		if (!isLenNumOnly("merchantForm","mc_type","��ҵ����",4)) {
			return false;
		}
	}	
//	if (!checkelement("merchantForm","mc_scale","�û���ģ")) {
//		return false;
//	}
//	if (!checkelement("merchantForm","mc_chadate","�շ�����")) {
//		return false;
//	}
	
//	if (document.getElementById("mc_id4mob").value != '') {
//		if (!isLenNumOnly("merchantForm","mc_id4mob","�ֻ�֧�����û����",15)) {
//			return false;
//		}
//	}
	return true;
	
	
}
//�ն˹������֤
function checkTerminalForm() {
	var term_tel = document.terminalForm.term_tel.value;
	var term_psam = document.terminalForm.term_psam.value;
	var tacctFlag = false;
	var isnewnumber = RegExp(/^[0-9|\*]+$/);	
	if (!isnewnumber.test(term_tel)) {
		alert("�󶨵绰����Ϊ���ֻ���*��");
		document.terminalForm.term_tel.focus();
		return false;
	}
	/*
	if(bingTel.length != 8){
		alert("�󶨵绰����Ϊ8λ���֣�");
		document.terminalForm.bingTel.focus();
		return false;
	}
	*/
	if(!isInteger(term_psam)){
		alert("PSAM���ű���Ϊ���֣�");
		document.terminalForm.term_psam.focus();
		return false;
	}
	if(term_psam.length != 16){
		alert("PSAM���ű���Ϊ16λ���֣�");
		document.terminalForm.term_psam.focus();
		return false;
	}
	//if (!checkelement("terminalForm","term_addr","��̨����¥��")) {
	//	return false;
	//}
	//if (!checkelement("terminalForm","term_connam","��̨����")) {
	//	return false;
	//}
	if (document.getElementById("term_id4cons").value != '') {
		if (!isLenNumOnly("terminalForm","term_id4cons","�������ն˱��",8)) {
			return false;
		}
	}
	if (document.getElementById("term_id4mob").value != '') {
		if (!isLenNumOnly("terminalForm","term_id4mob","�ֻ�֧�����ն˱��",8)) {
			return false;
		}
	}
	if (!checkelement("terminalForm","term_insdat","��װ����")) {
		return false;
	}
	/*
	if(document.terminalForm.term_insdat.value <= getNowDate() ){
		alert("��װ���ڱ�����ڵ��գ�");
		return false;
	}
	*/
	if(!checkelement("terminalForm","prov_id","����ʡ��") || !checkelement("terminalForm","area_code","��������")){
		return false;
	}
	var nums = document.all("tmenu_id");
//	if (nums != null && nums !='') {
	if (typeof(nums)!= undefined && nums !='') {
		if (!isNumber(document.all("term_bcust_amt"),"�û�����ѽ��")) {
			return false;
		}
		var len = nums.length;
		var test=/^[0-9]+$/;
	 	if (test.test(len) == false) {
	 		if (!isNumber(document.all("tc_ensglamt"),"�����޶�")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tc_endailyamt"),"���ۼ��޶�")) {
	 			return false;
	 		}
//	 		if (!isNumber(document.all("tc_trans_fee"),"���������ѵ�����ȡ���")) {
//	 			return false;
//	 		}
	 		if (!isNumber(document.all("tc_trans_feefix"),"���������ѱ�����ȡ���")) {
	 			return false;
	 		}
	 		
//	 		if (!isNumber(document.all("tc_trans_feemin"),"������������С��ȡ���")) {
//	 			return false;
//	 		}
//	 		if (!isNumber(document.all("tc_trans_feemax"),"���������������ȡ���")) {
//	 			return false;
//	 		}
//	 		if(document.all("tc_trans_feemax").value > 0){
//	 			if(document.all("tc_trans_fee").value - document.all("tc_trans_feemax").value > 0){
//	 				alert("���������ѵ�����ȡ��� ���ܴ��� ���������������ȡ���");
//	 				return false;
//	 			}
//	 		}
	 		if(document.all("tc_endailyamt").value > 0){
	 			if(document.all("tc_ensglamt").value - document.all("tc_endailyamt").value > 0){
	 				alert("�����޶� ���ܴ��� ���ۼ��޶�");
	 				return false;
	 			}
	 		}
	 		/*
	 		if (!isNumber(document.all("tc_cust_fee"),"�ͻ������ѵ�����ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tc_cust_feefix"),"�ͻ������ѱ�����ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tc_cust_feemin"),"�ͻ���������С��ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tc_cust_feemax"),"�ͻ������������ȡ���")) {
	 			return false;
	 		}
	 		if(document.all("tc_cust_feemax").value > 0){
	 			if(document.all("tc_cust_fee").value - document.all("tc_cust_feemax").value > 0){
	 				alert("�ͻ������ѵ�����ȡ��� ���ܴ��� �ͻ������������ȡ���");
	 				return false;
	 			}
	 		}
	 		*/
//	 		if (!isNumber(document.all("tc_term_fee"),"�ն������ѵ�����ȡ���")) {
//	 			return false;
//	 		}
	 		if (!isNumber(document.all("tc_term_feefix"),"�ն������ѱ�����ȡ���")) {
	 			return false;
	 		}
	 		//	 		if (!isNumber(document.all("tc_term_feemin"),"�ն���������С��ȡ���")) {
//	 			return false;
//	 		}
//	 		if (!isNumber(document.all("tc_term_feemax"),"�ն������������ȡ���")) {
//	 			return false;
//	 		}
//	 		if(document.all("tc_term_feemax").value > 0){
//	 			if(document.all("tc_term_fee").value - document.all("tc_term_feemax").value > 0){
//	 				alert("�ն������ѵ�����ȡ��� ���ܴ��� �ն������������ȡ���");
//	 				return false;
//	 			}
//	 		}
	 		if (!isNumber(document.all("tc_reck_feefix"),"���������ѱ�����ȡ���")) {
	 			return false;
	 		}
//	 		if(document.all("tc_reck_feefix").value < document.all("tmp_reck_feefix").value){
//	 			alert("�ն��������ۿ۱�������С��Ĭ�ϵ��ۿ۱���");
//	 			document.all("tc_reck_feefix").focus;
//	 			document.all("tc_reck_feefix").select;
//	 			return false;
//	 		}
//	 		if(document.all("tc_trans_feefix").value < document.all("tmp_trans_feefix").value){
//	 			alert("�����������ۿ۱�������С��Ĭ�ϵ��ۿ۱���");
//	 			document.all("tc_trans_feefix").focus;
//	 			document.all("tc_trans_feefix").select;
//	 			return false;
//	 		}
//	 		if(document.all("tc_term_feefix").value < document.all("tmp_term_feefix").value){
//	 			alert("�ۿ۱�������С��Ĭ�ϵ��ۿ۱���");
//	 			document.all("tc_term_feefix").focus;
//	 			document.all("tc_term_feefix").select;
//	 			return false;
//	 		}
	 		/*
	 		if (!isNumber(document.all("tc_reck_fee"),"���������ѵ�����ȡ���")) {
	 			return false;
	 		}
	 		
	 		if (!isNumber(document.all("tc_reck_feemin"),"������������С��ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tc_reck_feemax"),"���������������ȡ���")) {
	 			return false;
	 		}
	 		if(document.all("tc_reck_feemax").value > 0){
	 			if(document.all("tc_reck_fee").value - document.all("tc_reck_feemax").value > 0){
	 				alert("���������ѵ�����ȡ��� ���ܴ��� ���������������ȡ���");
	 				return false;
	 			}
	 		}
	 		*/
	 	
	 		if (document.all("tc_term_open").value == '1') tacctFlag = true;
	 		
	 	} else {
			for(var i=0; i<len; i++) {
				if (!isNumber(document.all("tc_ensglamt")[i],"�����޶�")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tc_endailyamt")[i],"���ۼ��޶�")) {
		 			return false;
		 		}
//				if (!isNumber(document.all("tc_trans_fee")[i],"���������ѵ�����ȡ���")) {
//		 			return false;
//		 		}
				if (!isNumber(document.all("tc_trans_feefix")[i],"���������ѱ�����ȡ���")) {
		 			return false;
		 		}
//				if(document.all("tc_trans_feefix")[i].value < document.all("tmp_trans_feefix")[i].value){
//		 			alert("�����������ۿ۱�������С��Ĭ�ϵ��ۿ۱���");
//		 			return false;
//		 		}
//				if (!isNumber(document.all("tc_trans_feemin")[i],"������������С��ȡ���")) {
//		 			return false;
//		 		}
//				if (!isNumber(document.all("tc_trans_feemax")[i],"���������������ȡ���")) {
//		 			return false;
//		 		}
//		 		if(document.all("tc_trans_feemax")[i].value > 0){
//		 			if(document.all("tc_trans_fee")[i].value -  document.all("tc_trans_feemax")[i].value > 0){
//		 				alert("���������ѵ�����ȡ��� ���ܴ��� ���������������ȡ���");
//		 				return false;
//		 			}
//	 			}
	 			if(document.all("tc_endailyamt")[i].value > 0){
		 			if(document.all("tc_ensglamt")[i].value -  document.all("tc_endailyamt")[i].value > 0){
		 				alert("�����޶� ���ܴ��� ���ۼ��޶�");
		 				return false;
		 			}
	 			}
	 			/*
				if (!isNumber(document.all("tc_cust_fee")[i],"�ͻ������ѵ�����ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tc_cust_feefix")[i],"�ͻ������ѱ�����ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tc_cust_feemin")[i],"�ͻ���������С��ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tc_cust_feemax")[i],"�ͻ������������ȡ���")) {
		 			return false;
		 		}
		 		if(document.all("tc_cust_feemax")[i].value > 0){
		 			if(document.all("tc_cust_fee")[i].value -  document.all("tc_cust_feemax")[i].value > 0){
		 				alert("�ͻ������ѵ�����ȡ��� ���ܴ��� �ͻ������������ȡ���");
		 				return false;
		 			}
	 			}
	 			*/
//				if (!isNumber(document.all("tc_term_fee")[i],"�ն������ѵ�����ȡ���")) {
//		 			return false;
//		 		}
				if (!isNumber(document.all("tc_term_feefix")[i],"�ն������ѱ�����ȡ���")) {
		 			return false;
		 		}
//				if(document.all("tc_term_feefix")[i].value < document.all("tmp_term_feefix")[i].value){
//		 			alert("�ն��������ۿ۱�������С��Ĭ�ϵ��ۿ۱���");
//		 			document.all("tc_term_feefix")[i].focus;
//		 			document.all("tc_term_feefix")[i].select;
//		 			return false;
//		 		}
				if (!isNumber(document.all("tc_reck_feefix")[i],"���������ѱ�����ȡ���")) {
		 			return false;
		 		}
//				if(document.all("tc_reck_feefix")[i].value < document.all("tmp_reck_feefix")[i].value){
//		 			alert("�����������ۿ۱�������С��Ĭ�ϵ��ۿ۱���");
//		 			document.all("tc_reck_feefix")[i].focus;
//		 			document.all("tc_reck_feefix")[i].select;
//		 			return false;
//		 		}
//				if (!isNumber(document.all("tc_term_feemin")[i],"�ն���������С��ȡ���")) {
//		 			return false;
//		 		}
//				if (!isNumber(document.all("tc_term_feemax")[i],"�ն������������ȡ���")) {
//		 			return false;
//		 		}
//		 		if(document.all("tc_term_feemax")[i].value > 0){
//		 			if(document.all("tc_term_fee")[i].value -  document.all("tc_term_feemax")[i].value > 0){
//		 				alert("�ն������ѵ�����ȡ��� ���ܴ��� �ն������������ȡ���");
//		 				return false;
//		 			}
//	 			}
		 		/*
				if (!isNumber(document.all("tc_reck_fee")[i],"���������ѵ�����ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tc_reck_feefix")[i],"���������ѱ�����ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tc_reck_feemin")[i],"������������С��ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tc_reck_feemax")[i],"���������������ȡ���")) {
		 			return false;
		 		}
		 		if(document.all("tc_reck_feemax")[i].value > 0){
		 			if(document.all("tc_reck_fee")[i].value -  document.all("tc_reck_feemax")[i].value > 0){
		 				alert("���������ѵ�����ȡ��� ���ܴ��� ���������������ȡ���");
		 				return false;
		 			}
	 			}
	 			*/
				
		 		if (document.all("tc_term_open")[i].value == '1') tacctFlag = true;
		 		
			}
	 	}
	 	
	} else {
		var isExcust = document.all("tc_isexcust");
		if (isExcust != null && isExcust != '' && isExcust[1].checked == true) {
			alert("���û����޲˵���������Ի���");
			isExcust[0].checked = true;
			return false;
		}
	}
	/**�����ն�ʹ�÷���ȡ�˻�����֤ qhg 2010-02-22
	 * 
	 * if (tacctFlag == true && !checkelement("terminalForm","term_acct","�ն�ʹ�÷���ȡ�˻�")) {
		return false;
	}*/
	return true;
}
//ԤǩԼ�ն˹������֤
function checkSignTerminalForm() {
	
	var term_tel = document.terminalForm.term_tel.value;
	var term_psam = document.terminalForm.term_psam.value;
	var tacctFlag = false;
	var isnewnumber = RegExp(/^[0-9|\*]+$/);	
	if (term_tel!=''&&!isnewnumber.test(term_tel)) {
		alert("�󶨵绰����Ϊ���ֻ���*��");
		document.terminalForm.term_tel.focus();
		return false;
	}	
	if(!isInteger(term_psam)){
		alert("PSAM���ű���Ϊ���֣�");
		document.terminalForm.term_psam.focus();
		return false;
	}
	if(term_psam.length != 16){
		alert("PSAM���ű���Ϊ16λ���֣�");
		document.terminalForm.term_psam.focus();
		return false;
	}	
	if (document.getElementById("term_id4cons").value != '') {
		if (!isLenNumOnly("terminalForm","term_id4cons","�������ն˱��",8)) {
			return false;
		}
	}
	if (document.getElementById("term_id4mob").value != '') {
		if (!isLenNumOnly("terminalForm","term_id4mob","�ֻ�֧�����ն˱��",8)) {
			return false;
		}
	}	
	if(!checkelement("terminalForm","prov_id","����ʡ��") || !checkelement("terminalForm","area_code","��������")){
		return false;
	}
	
	var nums = document.all("tmenu_id");
/*if(nums==null||nums==""){
		
	}else{*/
	if (nums != null && nums !='') {
		if (!isNumber(document.all("term_bcust_amt"),"�û�����ѽ��")) {
			return false;
		}
		
		var len = nums.length;
		var test=/^[0-9]+$/;
	 	if (test.test(len) == false) {
	 		if (!isNumber(document.all("tc_ensglamt"),"�����޶�")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tc_endailyamt"),"���ۼ��޶�")) {
	 			return false;
	 		}
//	 		if (!isNumber(document.all("tc_trans_fee"),"���������ѵ�����ȡ���")) {
//	 			return false;
//	 		}
	 		if (!isNumber(document.all("tc_trans_feefix"),"���������ѱ�����ȡ���")) {
	 			return false;
	 		}
//	 		if (!isNumber(document.all("tc_trans_feemin"),"������������С��ȡ���")) {
//	 			return false;
//	 		}
//	 		if (!isNumber(document.all("tc_trans_feemax"),"���������������ȡ���")) {
//	 			return false;
//	 		}
//	 		if(document.all("tc_trans_feemax").value > 0){
//	 			if(document.all("tc_trans_fee").value - document.all("tc_trans_feemax").value > 0){
//	 				alert("���������ѵ�����ȡ��� ���ܴ��� ���������������ȡ���");
//	 				return false;
//	 			}
//	 		}
	 		if(document.all("tc_endailyamt").value > 0){
	 			if(document.all("tc_ensglamt").value - document.all("tc_endailyamt").value > 0){
	 				alert("�����޶� ���ܴ��� ���ۼ��޶�");
	 				return false;
	 			}
	 		}
	 		
//	 		if (!isNumber(document.all("tc_term_fee"),"�ն������ѵ�����ȡ���")) {
//	 			return false;
//	 		}
	 		if (!isNumber(document.all("tc_term_feefix"),"�ն������ѱ�����ȡ���")) {
	 			return false;
	 		}
//	 		if (!isNumber(document.all("tc_term_feemin"),"�ն���������С��ȡ���")) {
//	 			return false;
//	 		}
//	 		if (!isNumber(document.all("tc_term_feemax"),"�ն������������ȡ���")) {
//	 			return false;
//	 		}
//	 		if(document.all("tc_term_feemax").value > 0){
//	 			if(document.all("tc_term_fee").value - document.all("tc_term_feemax").value > 0){
//	 				alert("�ն������ѵ�����ȡ��� ���ܴ��� �ն������������ȡ���");
//	 				return false;
//	 			}
//	 		}	 		
	 		if (document.all("tc_term_open").value == '1') tacctFlag = true;
	 	} else {
			for(var i=0; i<len; i++) {
				if (!isNumber(document.all("tc_ensglamt")[i],"�����޶�")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tc_endailyamt")[i],"���ۼ��޶�")) {
		 			return false;
		 		}
//				if (!isNumber(document.all("tc_trans_fee")[i],"���������ѵ�����ȡ���")) {
//		 			return false;
//		 		}
				if (!isNumber(document.all("tc_trans_feefix")[i],"���������ѱ�����ȡ���")) {
		 			return false;
		 		}
//				if (!isNumber(document.all("tc_trans_feemin")[i],"������������С��ȡ���")) {
//		 			return false;
//		 		}
//				if (!isNumber(document.all("tc_trans_feemax")[i],"���������������ȡ���")) {
//		 			return false;
//		 		}
//		 		if(document.all("tc_trans_feemax")[i].value > 0){
//		 			if(document.all("tc_trans_fee")[i].value -  document.all("tc_trans_feemax")[i].value > 0){
//		 				alert("���������ѵ�����ȡ��� ���ܴ��� ���������������ȡ���");
//		 				return false;
//		 			}
//	 			}
	 			if(document.all("tc_endailyamt")[i].value > 0){
		 			if(document.all("tc_ensglamt")[i].value -  document.all("tc_endailyamt")[i].value > 0){
		 				alert("�����޶� ���ܴ��� ���ۼ��޶�");
		 				return false;
		 			}
	 			}
	 			
//				if (!isNumber(document.all("tc_term_fee")[i],"�ն������ѵ�����ȡ���")) {
//		 			return false;
//		 		}
				if (!isNumber(document.all("tc_term_feefix")[i],"�ն������ѱ�����ȡ���")) {
		 			return false;
		 		}
//				if (!isNumber(document.all("tc_term_feemin")[i],"�ն���������С��ȡ���")) {
//		 			return false;
//		 		}
//				if (!isNumber(document.all("tc_term_feemax")[i],"�ն������������ȡ���")) {
//		 			return false;
//		 		}
//		 		if(document.all("tc_term_feemax")[i].value > 0){
//		 			if(document.all("tc_term_fee")[i].value -  document.all("tc_term_feemax")[i].value > 0){
//		 				alert("�ն������ѵ�����ȡ��� ���ܴ��� �ն������������ȡ���");
//		 				return false;
//		 			}
//	 			}
		 		if (document.all("tc_term_open")[i].value == '1') tacctFlag = true;
			}
	 	}
	
	} else {
		
		var isExcust = document.all("tc_isexcust");
		
		if (isExcust != null && isExcust != '' && isExcust[0].checked == true) {
			alert("���û����޲˵���������Ի���");
			isExcust[1].checked = true;
			return false;
		}
		
	}
	/**�����ն�ʹ�÷���ȡ�˻�����֤ qhg 2010-02-22
	 * 
	 * if (tacctFlag == true && !checkelement("terminalForm","term_acct","�ն�ʹ�÷���ȡ�˻�")) {
		return false;
	}*/
	return true;
}
//�ն����������֤
function checkTermKindForm() {
	var tkindname = document.termKindForm.tkind_name.value;
	var nums = document.all("tmenu_id");
	if (nums != null && nums !='') {
		var len = nums.length;
		var test=/^[0-9]+$/;
	 	if (test.test(len) == false) {
	 		if (!isNumber(document.all("tkc_ensglamt"),"�����޶�")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tkc_endailyamt"),"���ۼ��޶�")) {
	 			return false;
	 		}
//	 		if (!isNumber(document.all("tkc_trans_fee"),"���������ѵ�����ȡ���")) {
//	 			return false;
//	 		}
	 		if (!isNumber(document.all("tkc_trans_feefix"),"���������ѱ�����ȡ���")) {
	 			return false;
	 		}
//	 		if (!isNumber(document.all("tkc_trans_feemin"),"������������С��ȡ���")) {
//	 			return false;
//	 		}
//	 		if (!isNumber(document.all("tkc_trans_feemax"),"���������������ȡ���")) {
//	 			return false;
//	 		}
//	 		if(document.all("tkc_trans_feemax").value > 0){
//	 			if(document.all("tkc_trans_fee").value - document.all("tkc_trans_feemax").value > 0){
//	 				alert("���������ѵ�����ȡ��� ���ܴ��� ���������������ȡ���");
//	 				return false;
//	 			}
//	 		}
	 		/*
	 		if (!isNumber(document.all("tkc_cust_fee"),"�ͻ������ѵ�����ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tkc_cust_feefix"),"�ͻ������ѱ�����ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tkc_cust_feemin"),"�ͻ���������С��ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tkc_cust_feemax"),"�ͻ������������ȡ���")) {
	 			return false;
	 		}
	 		if(document.all("tkc_cust_feemax").value > 0){
	 			if(document.all("tkc_cust_fee").value - document.all("tkc_cust_feemax").value > 0){
	 				alert("�ͻ������ѵ�����ȡ��� ���ܴ��� �ͻ������������ȡ���");
	 				return false;
	 			}
	 		}
	 		*/
//	 		if (!isNumber(document.all("tkc_term_fee"),"�ն������ѵ�����ȡ���")) {
//	 			return false;
//	 		}
	 		if (!isNumber(document.all("tkc_term_feefix"),"�ն������ѱ�����ȡ���")) {
	 			return false;
	 		}
//	 		if (!isNumber(document.all("tkc_term_feemin"),"�ն���������С��ȡ���")) {
//	 			return false;
//	 		}
//	 		if (!isNumber(document.all("tkc_term_feemax"),"�ն������������ȡ���")) {
//	 			return false;
//	 		}
//	 		if(document.all("tkc_term_feemax").value > 0){
//	 			if(document.all("tkc_term_fee").value - document.all("tkc_term_feemax").value > 0){
//	 				alert("�ն������ѵ�����ȡ��� ���ܴ��� �ն������������ȡ���");
//	 				return false;
//	 			}
//	 		}
	 		/*
	 		if (!isNumber(document.all("tkc_reck_fee"),"���������ѵ�����ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tkc_reck_feefix"),"���������ѱ�����ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tkc_reck_feemin"),"������������С��ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tkc_reck_feemax"),"���������������ȡ���")) {
	 			return false;
	 		}
	 		if(document.all("tkc_reck_feemax").value > 0){
	 			if(document.all("tkc_reck_fee").value - document.all("tkc_reck_feemax").value > 0){
	 				alert("���������ѵ�����ȡ��� ���ܴ��� ���������������ȡ���");
	 				return false;
	 			}
	 		}
	 		*/
	 	} else {
			for(var i=0; i<len; i++) {
				if (!isNumber(document.all("tkc_ensglamt")[i],"�����޶�")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tkc_endailyamt")[i],"���ۼ��޶�")) {
		 			return false;
		 		}
//				if (!isNumber(document.all("tkc_trans_fee")[i],"���������ѵ�����ȡ���")) {
//		 			return false;
//		 		}
				if (!isNumber(document.all("tkc_trans_feefix")[i],"���������ѱ�����ȡ���")) {
		 			return false;
		 		}
//				if (!isNumber(document.all("tkc_trans_feemin")[i],"������������С��ȡ���")) {
//		 			return false;
//		 		}
//				if (!isNumber(document.all("tkc_trans_feemax")[i],"���������������ȡ���")) {
//		 			return false;
//		 		}
		 		
//		 		if(document.all("tkc_trans_feemax")[i].value > 0){
//		 			if(document.all("tkc_trans_fee")[i].value -  document.all("tkc_trans_feemax")[i].value > 0){
//		 				alert("���������ѵ�����ȡ��� ���ܴ��� ���������������ȡ���");
//		 				return false;
//		 			}
//	 			}
		 		/*
				if (!isNumber(document.all("tkc_cust_fee")[i],"�ͻ������ѵ�����ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tkc_cust_feefix")[i],"�ͻ������ѱ�����ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tkc_cust_feemin")[i],"�ͻ���������С��ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tkc_cust_feemax")[i],"�ͻ������������ȡ���")) {
		 			return false;
		 		}
		 		if(document.all("tkc_cust_feemax")[i].value > 0){
		 			if(document.all("tkc_cust_fee")[i].value -  document.all("tkc_cust_feemax")[i].value > 0){
		 				alert("�ͻ������ѵ�����ȡ��� ���ܴ��� �ͻ������������ȡ���");
		 				return false;
		 			}
	 			}
	 			*/
//				if (!isNumber(document.all("tkc_term_fee")[i],"�ն������ѵ�����ȡ���")) {
//		 			return false;
//		 		}
				if (!isNumber(document.all("tkc_term_feefix")[i],"�ն������ѱ�����ȡ���")) {
		 			return false;
		 		}
//				if (!isNumber(document.all("tkc_term_feemin")[i],"�ն���������С��ȡ���")) {
//		 			return false;
//		 		}
//				if (!isNumber(document.all("tkc_term_feemax")[i],"�ն������������ȡ���")) {
//		 			return false;
//		 		}
//		 		if(document.all("tkc_term_feemax")[i].value > 0){
//		 			if(document.all("tkc_term_fee")[i].value -  document.all("tkc_term_feemax")[i].value > 0){
//		 				alert("�ն������ѵ�����ȡ��� ���ܴ��� �ն������������ȡ���");
//		 				return false;
//		 			}
//	 			}
		 		/*
				if (!isNumber(document.all("tkc_reck_fee")[i],"���������ѵ�����ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tkc_reck_feefix")[i],"���������ѱ�����ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tkc_reck_feemin")[i],"������������С��ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tkc_reck_feemax")[i],"���������������ȡ���")) {
		 			return false;
		 		}
		 		if(document.all("tkc_reck_feemax")[i].value > 0){
		 			if(document.all("tkc_reck_fee")[i].value -  document.all("tkc_reck_feemax")[i].value > 0){
		 				alert("���������ѵ�����ȡ��� ���ܴ��� ���������������ȡ���");
		 				return false;
		 			}
	 			}
	 			*/
			}
	 	}
	}
	if (!checkelement("termKindForm","tkind_name","�û��������")) {
		return false;
	}
	if (!isNumber(document.all("tkind_bcust_amt"),"�û�����ѽ��")) {
		return false;
	}
	return true;
}

//���������ѹ������֤
function checkReckFeeForm() {
	var nums = document.all("conf_id");
	if (nums != null && nums !='') {
		var len = nums.length;
		var test=/^[0-9]+$/;
	 	if (test.test(len) == false) {
	 		if (!isNumber(document.all("reck_fee"),"������ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feefix"),"������ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feemin"),"��С��ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feemax"),"�����ȡ���")) {
	 			return false;
	 		}
	 		if(document.all("reck_feemax").value > 0){
	 			if(document.all("reck_fee").value - document.all("reck_feemax").value > 0){
	 				alert("������ȡ��� ���ܴ��� �����ȡ���");
	 				return false;
	 			}
	 		}
	 	} else {
			for(var i=0; i<len; i++) {
				if (!isNumber(document.all("reck_fee")[i],"������ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feefix")[i],"������ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feemin")[i],"��С��ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feemax")[i],"�����ȡ���")) {
		 			return false;
		 		}
		 		if(document.all("reck_feemax")[i].value > 0){
		 			if(document.all("reck_fee")[i].value -  document.all("reck_feemax")[i].value > 0){
		 				alert("������ȡ��� ���ܴ��� �����ȡ���");
		 				return false;
		 			}
	 			}
			}
	 	}
	}
	return true;
}
//�����������ñ���֤

function checkTransFeeForm() {
	var nums = document.all("conf_id");
	if (nums != null && nums !='') {
		var len = nums.length;
		var test=/^[0-9]+$/;
	 	if (test.test(len) == false) {
	 		if (!isNumber(document.all("reck_fee"),"������ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feefix"),"������ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feemin"),"��С��ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feemax"),"�����ȡ���")) {
	 			return false;
	 		}
	 		if(document.all("reck_feemax").value > 0){
	 			if(document.all("reck_fee").value - document.all("reck_feemax").value > 0){
	 				alert("������ȡ��� ���ܴ��� �����ȡ���");
	 				return false;
	 			}
	 		}
	 	} else {
			for(var i=0; i<len; i++) {
				if (!isNumber(document.all("reck_fee")[i],"������ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feefix")[i],"������ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feemin")[i],"��С��ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feemax")[i],"�����ȡ���")) {
		 			return false;
		 		}
		 		if(document.all("reck_feemax")[i].value > 0){
		 			if(document.all("reck_fee")[i].value -  document.all("reck_feemax")[i].value > 0){
		 				alert("������ȡ��� ���ܴ��� �����ȡ���");
		 				return false;
		 			}
	 			}
			}
	 	}
	}
	return true;
}

//�ն�ʹ�÷��ñ���֤

function checkTermFeeForm() {
	var nums = document.all("conf_id");
	if (nums != null && nums !='') {
		var len = nums.length;
		var test=/^[0-9]+$/;
	 	if (test.test(len) == false) {
	 		if (!isNumber(document.all("reck_fee"),"������ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feefix"),"������ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feemin"),"��С��ȡ���")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feemax"),"�����ȡ���")) {
	 			return false;
	 		}
	 		if(document.all("reck_feemax").value > 0){
	 			if(document.all("reck_fee").value - document.all("reck_feemax").value > 0){
	 				alert("������ȡ��� ���ܴ��� �����ȡ���");
	 				return false;
	 			}
	 		}
	 	} else {
			for(var i=0; i<len; i++) {
				if (!isNumber(document.all("reck_fee")[i],"������ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feefix")[i],"������ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feemin")[i],"��С��ȡ���")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feemax")[i],"�����ȡ���")) {
		 			return false;
		 		}
		 		if(document.all("reck_feemax")[i].value > 0){
		 			if(document.all("reck_fee")[i].value -  document.all("reck_feemax")[i].value > 0){
		 				alert("������ȡ��� ���ܴ��� �����ȡ���");
		 				return false;
		 			}
	 			}
			}
	 	}
	}
	return true;
}
//�˻��������֤
function checkFeeAcctForm() {
	var fa_trans_acct = document.all("fa_trans_acct").value;
	var fa_reck_acct = document.all("fa_reck_acct").value;
	var fa_bcust_acct = document.all("fa_bcust_acct").value;
	if (fa_trans_acct != '' && !isnumberonly("feeAcctForm","fa_trans_acct","������������ȡ�˻�")) {
		return false;
	}
	if (fa_reck_acct != '' && !isnumberonly("feeAcctForm","fa_reck_acct","������������ȡ�˻�")) {
		return false;
	}
	if (fa_bcust_acct != '' && !isnumberonly("feeAcctForm","fa_bcust_acct","�û��������ȡ�˻�")) {
		return false;
	}
	return true;
}

//����������֤
function checkProfitForm() {
	var profit_trans_feefix = document.all("profit_trans_feefix").value;
	var profit_bcust_feefix = document.all("profit_bcust_feefix").value;
	var profit_reck_feefix = document.all("profit_reck_feefix").value;
	if (!checkelement("profitForm","profit_id","���󷽱��")) {
		return false;
	}
	if (!checkelement("profitForm","profit_name","��������")) {
		return false;
	}
	if (!isnumberonly("profitForm","profit_acctno","���������˻�")) {
		return false;
	}
	if (!isNumber(document.all("profit_trans_fee"),"���������ѵ�����ȡ���")) {
		return false;
	}
	if (!isNumber(document.all("profit_trans_feefix"),"���������ѱ�����ȡ���")) {
		return false;
	}
	if (!isNumber(document.all("profit_trans_feemin"),"������������С��ȡ���")) {
		return false;
	}
	if (!isNumber(document.all("profit_trans_feemax"),"���������������ȡ���")) {
		return false;
	}
	if(document.all("profit_trans_feemax").value > 0){
		if(document.all("profit_trans_fee").value - document.all("profit_trans_feemax").value > 0){
			alert("���������ѵ�����ȡ��� ���ܴ��� ���������������ȡ���");
			return false;
		}
	}
	if (!isNumber(document.all("profit_bcust_fee"),"�û�����ѵ�����ȡ���")) {
		return false;
	}
	if (!isNumber(document.all("profit_bcust_feefix"),"�û�����ѱ�����ȡ���")) {
		return false;
	}
	if (!isNumber(document.all("profit_bcust_feemin"),"�û��������С��ȡ���")) {
		return false;
	}
	if (!isNumber(document.all("profit_bcust_feemax"),"�û�����������ȡ���")) {
		return false;
	}
	if(document.all("profit_bcust_feemax").value > 0){
		if(document.all("profit_bcust_fee").value - document.all("profit_bcust_feemax").value > 0){
			alert("�û�����ѵ�����ȡ��� ���ܴ��� �û�����������ȡ���");
			return false;
		}
	}
	if (!isNumber(document.all("profit_reck_fee"),"���������ѵ�����ȡ���")) {
		return false;
	}
	if (!isNumber(document.all("profit_reck_feefix"),"���������ѱ�����ȡ���")) {
		return false;
	}
	if (!isNumber(document.all("profit_reck_feemin"),"������������С��ȡ���")) {
		return false;
	}
	if (!isNumber(document.all("profit_reck_feemax"),"���������������ȡ���")) {
		return false;
	}
	if(document.all("profit_reck_feemax").value > 0){
		if(document.all("profit_reck_fee").value - document.all("profit_reck_feemax").value > 0){
			alert("���������ѵ�����ȡ��� ���ܴ��ڽ��������������ȡ���");
			return false;
		}
	}
	if (parseFloat(profit_trans_feefix) + parseFloat(trans_feefix_sum) > 100) {
		alert("�����������ܷ�������ѳ���100%��");
		document.all("profit_trans_feefix").focus();
		return false;
	}
	if (parseFloat(profit_bcust_feefix) + parseFloat(bcust_feefix_sum) > 100) {
		alert("�û�������ܷ�������ѳ���100%��");
		document.all("profit_bcust_feefix").focus();
		return false;
	}
	if (parseFloat(profit_reck_feefix) + parseFloat(reck_feefix_sum) > 100) {
		alert("�����������ܷ�������ѳ���100%��");
		document.all("profit_reck_feefix").focus();
		return false;
	}
	return true;
}
//����������֤
function checkProfitMcForm() {
	
	
	if (!checkelement("profitAcctForm","profit_id","���󷽱��")) {
		return false;
	}
	if (!checkelement("profitAcctForm","profit_name","��������")) {
		return false;
	}
	if (!isnumberonly("profitAcctForm","profit_acctno","���������˻�")) {
		return false;
	}
	
	return true;
}
//�Ƿ���Ի��ն���֤
function checkTermIsExcust(flag, cfflag){
	var nums = document.all("tmenu_id");
	if(nums==null||nums=='') return false;
	var len = nums.length;
	var test=/^[0-9]+$/;
	if (!flag && cfflag) {
		if (!confirm("�����Ը��ն˽��и��Ի��޸ģ��޸ĺ��ն�����еļƷѹ����뱾�û��޹أ�ȷ����")) {
			document.all("tc_isexcust")[1].checked = true;
			return false;
		}
	} else if (flag && cfflag) {
		if (!confirm("����ȡ�����ն˵ĸ��Ի���ȡ�����ն�����еļƷѹ����������ڱ��û���ȷ����")) {
			document.all("tc_isexcust")[0].checked = true;
			return false;
		}
	}	
	document.all("term_bcust_type").disabled = flag;

	//document.all("term_bcust_acct").disabled = flag;
//	document.all("term_bcust_amt").disabled = flag;
	if (test.test(len) == false) {
	
		document.all("tc_isactive").disabled = flag;
	
		document.all("tc_ennatcredit").disabled = flag;
	
		document.all("tc_enothdebit").disabled = flag;
	
		document.all("tc_enothcredit").disabled = flag;

		document.all("tc_ensgl").disabled = flag;
	
		document.all("tc_ensglamt").disabled = flag;
	
		document.all("tc_endaily").disabled = flag;
	
		document.all("tc_endailyamt").disabled = flag;
		
		document.all("tc_trans_open").disabled = flag;
		
		//document.all("tc_trans_acct").disabled = flag;
//		document.all("tc_trans_fee").disabled = flag;
		document.all("tc_trans_feefix").disabled = flag;
		
//		document.all("tc_trans_feemin").disabled = flag;
//		document.all("tc_trans_feemax").disabled = flag;
		/*
		document.all("tc_cust_open").disabled = flag;
		document.all("tc_cust_acct").disabled = flag;
		document.all("tc_cust_fee").disabled = flag;
		document.all("tc_cust_feefix").disabled = flag;
		document.all("tc_cust_feemin").disabled = flag;
		document.all("tc_cust_feemax").disabled = flag;
		*/
//		document.all("tc_reck_open")[i].disabled =flag;
		
		document.all("tc_term_open").disabled = flag;
		
		//document.all("tc_term_acct").disabled = flag;
//		document.all("tc_term_fee").disabled = flag;
		document.all("tc_term_feefix").disabled = flag;
		document.all("tc_reck_open").disabled = flag;
		document.all("tc_reck_feefix").disabled = flag;
		
//		document.all("tc_term_feemin").disabled = flag;
//		document.all("tc_term_feemax").disabled = flag;
		//document.all("tc_reck_open").disabled = flag;
		//document.all("tc_reck_open").disabled=true;//Ĭ�ϲ����޸� ���������� ��ͨ����
		/*
		document.all("tc_reck_acct").disabled = flag;
		document.all("tc_reck_fee").disabled = flag;
		document.all("tc_reck_feefix").disabled = flag;
		document.all("tc_reck_feemin").disabled = flag;
		document.all("tc_reck_feemax").disabled = flag;
		*/
	} else {
		for(var i=0; i<len; i++) {
			document.all("tc_isactive")[i].disabled = flag;
			document.all("tc_ennatcredit")[i].disabled = flag;
			document.all("tc_enothdebit")[i].disabled = flag;
			document.all("tc_enothcredit")[i].disabled = flag;
	 		document.all("tc_ensgl")[i].disabled = flag;
	 		document.all("tc_ensglamt")[i].disabled = flag;
	 		document.all("tc_endaily")[i].disabled = flag;
	 		document.all("tc_endailyamt")[i].disabled = flag;
			document.all("tc_trans_open")[i].disabled = flag;
			//document.all("tc_trans_acct")[i].disabled = flag;
//			document.all("tc_trans_fee")[i].disabled = flag;
			document.all("tc_trans_feefix")[i].disabled = flag;
//			document.all("tc_trans_feemin")[i].disabled = flag;
//			document.all("tc_trans_feemax")[i].disabled = flag;
			/*
			document.all("tc_cust_open")[i].disabled = flag;
			document.all("tc_cust_acct")[i].disabled = flag;
			document.all("tc_cust_fee")[i].disabled = flag;
			document.all("tc_cust_feefix")[i].disabled = flag;
			document.all("tc_cust_feemin")[i].disabled = flag;
			document.all("tc_cust_feemax")[i].disabled = flag;
			*/
			document.all("tc_term_open")[i].disabled = flag;
			//document.all("tc_term_acct")[i].disabled = flag;
//			document.all("tc_term_fee")[i].disabled = flag;
			document.all("tc_term_feefix")[i].disabled = flag;
			document.all("tc_reck_open")[i].disabled = flag;
			document.all("tc_reck_feefix")[i].disabled = flag;
//			document.all("tc_term_feemin")[i].disabled = flag;
//			document.all("tc_term_feemax")[i].disabled = flag;
			
			//document.all("tc_reck_open")[i].disabled = true;//Ĭ�ϲ����޸� ���������� ��ͨ����
			/*
			document.all("tc_reck_acct")[i].disabled = flag;
			document.all("tc_reck_fee")[i].disabled = flag;
			document.all("tc_reck_feefix")[i].disabled = flag;
			document.all("tc_reck_feemin")[i].disabled = flag;
			document.all("tc_reck_feemax")[i].disabled = flag;
			*/
		}
	
	}
}

//��ʾ�˻���֤�ȴ���Ϣ
function dispInfo() {
	if (noteFlag == "true") {
		//document.all("message3").innerHTML = str;
		document.all("message1").innerHTML = str + point;
		document.all("message2").innerHTML = "";
		point = point + "��";
		i++;
		if(i > 4) {
			i = 0;
			point = "";
		}
		setTimeout("dispInfo()",1000);
	}
}
//��ʾ�ϰ帶���˻���֤�ȴ���Ϣ
function dispfkInfo() {
	if (fknoteFlag == "true") {
		//document.all("message3").innerHTML = str;
		document.all("message5").innerHTML = str + point;
		document.all("message6").innerHTML = "";
		point = point + "��";
		i++;
		if(i > 4) {
			i = 0;
			point = "";
		}
		setTimeout("dispfkInfo()",1000);
	}
}
//��ʼ���˻���Ϣ
function init_flag(){
	if(click == "true"){
		var answer = window.confirm("��ȷ��Ҫ���������ʺ���?");
		if(answer == true)
		{
			click = "false";
			document.getElementById("merCheckButton").style.display="";
			document.all("message1").innerHTML = "";
		}
		return false;
		click = "true";
	}
}

//��ʼ���˻���Ϣ
function init_fk_flag(){
	if(fkclick == "true"){
		var answer = window.confirm("��ȷ��Ҫ���������ʺ���?");
		if(answer == true)
		{
			fkclick = "false";
			document.getElementById("merCheckfkButton").style.display="";
			document.all("message5").innerHTML = "";
		}
		return false;
		fkclick = "true";
	}
}
//�ı䱳����ɫ
//function changeClass(obj, val) {
//	obj.className = 'box' + val;
//}
function changeClass(obj, val) {
	var o = obj.childNodes;
	if(val != 2){
		for(var i=0;i<o.length;i++){
			if(o[i].tagName != 'TD') continue;
			o[i].style.backgroundColor="#f8fecc";
		}
	}
	else{
		var p_trs = obj.parentNode.childNodes;
		var x = 0;
		for(var i=0;i<p_trs.length;i++){
			if(p_trs[i].tagName != 'TR') continue;
			x++;
				var tr_tds = p_trs[i].childNodes;
				for(var j=0;j<tr_tds.length;j++){
					if(tr_tds[j].tagName != 'TD') continue;
					if(x%2==0)
						tr_tds[j].style.backgroundColor="#f7fbfc";
					else
						tr_tds[j].style.backgroundColor="";
				}
		}
	}
	obj.className = 'box' + val;
}

//�ı�ͳ������
function changeStatType() {
	var stat_type = document.getElementById("stat_type").value;
	if (stat_type == '1') {
		document.getElementById("bg1").style.display = "";
		document.getElementById("bg2").style.display = "";
		document.getElementById("ed1").style.display = "";
		document.getElementById("ed2").style.display = "";
		document.getElementById("st1").style.display = "none";
		document.getElementById("st2").style.display = "none";
	} else {
		document.getElementById("bg1").style.display = "none";
		document.getElementById("bg2").style.display = "none";
		document.getElementById("ed1").style.display = "none";
		document.getElementById("ed2").style.display = "none";
		document.getElementById("st1").style.display = "";
		document.getElementById("st2").style.display = "";
	}
}

//�û�ҵ�񱨱��ѯ
function queryMcTrans() {
	var stat_type = document.reportForm.stat_type.value;
	if (stat_type == '2') {
		var selYear = document.reportForm.selYear.value;
		var selMonth = document.reportForm.selMonth.value;
		document.reportForm.stat_date.value = selYear + "" + selMonth;
		var stat_date = document.reportForm.stat_date.value;
		var date = new Date().Format("yyyyMM");
		/*
		if (stat_date.replace(/-/g,"").substring(0,6) >= date) {
			alert("ֻ�ܲ�ѯ����֮ǰ���ݣ�");
			return false;
		}
		*/
		document.reportForm.action = "/bfbposp/mcTransReport.do?method=queryAgent";
	} else {
		var start_date = document.reportForm.start_date.value;
		var end_date = document.reportForm.end_date.value;
		if (!checkelement("reportForm","start_date","��ʼ����")) {
			return false;
		}
		if (!checkelement("reportForm","end_date","��������")) {
			return false;
		}
		if (start_date > end_date) {
			alert("��ʼ���ڲ��ܴ��ڽ������ڣ�");
			return false;
		}
		if (daysBetween(start_date,end_date) > 90) {
			alert("��ѯʱ���Ȳ��ܳ��������£�");
			return false;
		}
		document.reportForm.action = "/bfbposp/mcTransReport.do?method=queryAgentDaily";
	}
	document.reportForm.submit();
}

//�ͻ��������ѯ
function queryMcTermNum() {
	var stat_type = document.reportForm.stat_type.value;
	if (stat_type == '2') {
		var selYear = document.reportForm.selYear.value;
		var selMonth = document.reportForm.selMonth.value;
		document.reportForm.stat_date.value = selYear + "" + selMonth;
		var stat_date = document.reportForm.stat_date.value;
		var date = new Date().Format("yyyyMM");
		/*
		if (stat_date.replace(/-/g,"").substring(0,6) >= date) {
			alert("ֻ�ܲ�ѯ����֮ǰ���ݣ�");
			return false;
		}
		*/
		document.reportForm.action = "/bfbposp/mcTermNumReport.do?method=queryAgent";
	} else {
		var start_date = document.reportForm.start_date.value;
		var end_date = document.reportForm.end_date.value;
		if (!checkelement("reportForm","start_date","��ʼ����")) {
			return false;
		}
		if (!checkelement("reportForm","end_date","��������")) {
			return false;
		}
		if (start_date > end_date) {
			alert("��ʼ���ڲ��ܴ��ڽ������ڣ�");
			return false;
		}
		if (daysBetween(start_date,end_date) > 90) {
			alert("��ѯʱ���Ȳ��ܳ��������£�");
			return false;
		}
		document.reportForm.action = "/bfbposp/mcTermNumReport.do?method=queryAgentDaily";
	}
	document.reportForm.submit();
}
//�ͻ��������ѯ
function queryMcTermNumdev() {
	var stat_type = document.reportForm.stat_type.value;
	if (stat_type == '2') {
		var selYear = document.reportForm.selYear.value;
		var selMonth = document.reportForm.selMonth.value;
		document.reportForm.stat_date.value = selYear + "" + selMonth;
		var stat_date = document.reportForm.stat_date.value;
		var date = new Date().Format("yyyyMM");
		/*
		if (stat_date.replace(/-/g,"").substring(0,6) >= date) {
			alert("ֻ�ܲ�ѯ����֮ǰ���ݣ�");
			return false;
		}
		*/
		document.reportForm.action = "/bfbposp/mcTermNumReport.do?method=queryAgentdev";
	} else {
		var start_date = document.reportForm.start_date.value;
		var end_date = document.reportForm.end_date.value;
		if (!checkelement("reportForm","start_date","��ʼ����")) {
			return false;
		}
		if (!checkelement("reportForm","end_date","��������")) {
			return false;
		}
		if (start_date > end_date) {
			alert("��ʼ���ڲ��ܴ��ڽ������ڣ�");
			return false;
		}
		if (daysBetween(start_date,end_date) > 90) {
			alert("��ѯʱ���Ȳ��ܳ��������£�");
			return false;
		}
		document.reportForm.action = "/bfbposp/mcTermNumReport.do?method=queryAgentDailydev";
	}
	document.reportForm.submit();
}
//ҵ����ܱ����ѯ
function queryTransTot() {
	var stat_type = document.reportForm.stat_type.value;
	if (stat_type == '2') {
		var selYear = document.reportForm.selYear.value;
		var selMonth = document.reportForm.selMonth.value;
		document.reportForm.stat_date.value = selYear + "" + selMonth;
		var stat_date = document.reportForm.stat_date.value;
		var date = new Date().Format("yyyyMM");
		/*
		if (stat_date.replace(/-/g,"").substring(0,6) >= date) {
			alert("ֻ�ܲ�ѯ����֮ǰ���ݣ�");
			return false;
		}
		*/
		document.reportForm.action = "/bfbposp/transTotReport.do?method=queryAgent";
	} else {
		var start_date = document.reportForm.start_date.value;
		var end_date = document.reportForm.end_date.value;
		if (!checkelement("reportForm","start_date","��ʼ����")) {
			return false;
		}
		if (!checkelement("reportForm","end_date","��������")) {
			return false;
		}
		if (start_date > end_date) {
			alert("��ʼ���ڲ��ܴ��ڽ������ڣ�");
			return false;
		}
		if (daysBetween(start_date,end_date) > 90) {
			alert("��ѯʱ���Ȳ��ܳ��������£�");
			return false;
		}
		document.reportForm.action = "/bfbposp/transTotReport.do?method=queryAgentDaily";
	}
	document.reportForm.submit();
}
//ҵ����ܱ����ѯ
function queryTransTotdev() {
	var stat_type = document.reportForm.stat_type.value;
	if (stat_type == '2') {
		var selYear = document.reportForm.selYear.value;
		var selMonth = document.reportForm.selMonth.value;
		document.reportForm.stat_date.value = selYear + "" + selMonth;
		var stat_date = document.reportForm.stat_date.value;
		var date = new Date().Format("yyyyMM");
		/*
		if (stat_date.replace(/-/g,"").substring(0,6) >= date) {
			alert("ֻ�ܲ�ѯ����֮ǰ���ݣ�");
			return false;
		}
		*/
		document.reportForm.action = "/bfbposp/transTotReport.do?method=queryAgentdev";
	} else {
		var start_date = document.reportForm.start_date.value;
		var end_date = document.reportForm.end_date.value;
		if (!checkelement("reportForm","start_date","��ʼ����")) {
			return false;
		}
		if (!checkelement("reportForm","end_date","��������")) {
			return false;
		}
		if (start_date > end_date) {
			alert("��ʼ���ڲ��ܴ��ڽ������ڣ�");
			return false;
		}
		if (daysBetween(start_date,end_date) > 90) {
			alert("��ѯʱ���Ȳ��ܳ��������£�");
			return false;
		}
		document.reportForm.action = "/bfbposp/transTotReport.do?method=queryAgentDailydev";
	}
	document.reportForm.submit();
}
//��ز������ñ���֤
function checkMoniIniForm() {
	if(!checkelement("moniIniForm","mi_no","������")){
		return false;
	}
	if(!checkelement("moniIniForm","tmenu_id","��������")){
		return false;
	}
	if (!isNumber(document.all("mi_amount"),"���׶��׼")) {
		return false;
	}
	return true;
}

//��ʾ�·�ѡ��������
function dispSelMon() {
	var months = new Array("һ��","����","����","����","����","����","����","����","����","ʮ��","ʮһ��","ʮ����");
	var str = "";
	var monval = "";
	str += "<table border='0' width='100%'>";
	str += "<TR>";
	str += "<td valign='middle' align='left'>";
	str += "&nbsp;<SELECT id='selYear' name='selYear'>";
	for(i=2009;i<2026;i++)
		str += "<OPTION value='"+i+"'>"+i+" ��</OPTION>";
	str += "</SELECT>";
	str += "&nbsp;<select id='selMonth' name='selMonth' >";
	for (i=0; i<12; i++) {
		if (i < 9) {
			monval = "0" + (i + 1);
		} else {
			monval = i + 1;
		}
		str += "<option value='"+monval+"'>"+months[i]+"</option>";
	}
	str += "</SELECT>";
	str += "</td>";
	str += "</TR>";
	str += "</TABLE>";
	document.getElementById("st2").innerHTML=str;
}

//��ʼ���������·�
function initMon() {
	if (stat_date.length == 6) {
		var selYear = stat_date.substring(0,4);
		var selMonth = stat_date.substring(4,6);
		var lenY = document.getElementById("selYear").options.length;
		var lenM = document.getElementById("selMonth").options.length;
		for (var i=0; i<lenY; i++) {
			var selYearVal = document.getElementById("selYear").options[i].value;
			if (selYearVal == selYear) {
				document.getElementById("selYear").options[i].selected = true;
				break;
			}
		}
		for (var i=0; i<lenM; i++) {
			var selMonthVal = document.getElementById("selMonth").options[i].value;
			if (selMonthVal == selMonth) {
				document.getElementById("selMonth").options[i].selected = true;
				break;
			}
		}
	}
}

//������һ�б�ͷ,�������ݾ�ɾ��
function removeRow() {
	var iRows = tb.rows.length;
	for (var i=0;i<iRows-1;i++) {
		tb.deleteRow(1);
	}
}

//5���Զ�ˢ��һ��,5��ȡ��һ������
//function showMoni(moniFlag) {	
//	getTransMoni(moniFlag);
//	timer = window.setInterval("getTransMoni("+moniFlag+")",5000);
//}
function showMoni(moniFlag,roleno,dept_no_node) {
	var roleno_1='00';
	roleno_1=roleno;
	getTransMoni(moniFlag,roleno_1,dept_no_node);
	var funmoni="getTransMoni("+moniFlag+",'"+roleno_1+"',"+dept_no_node+")";	
	timer = window.setInterval(funmoni,5000);
}
//�ն���Կ�������֤
function checkTermKeyForm() {
	var masterkey = document.all("masterkey").value;
	var masterkey_conf = document.all("masterkey_conf").value;
	if (document.all("mc_id") != null && !checkelement("termKeyForm","mc_id","�û����")) {
		return false;
	}
	if (document.all("term_id4dcc") != null && !isLenNumOnly("termKeyForm","term_id4dcc","DCC�ն˺�",19)) {
		return false;
	}
	if (document.all("term_id4cons") != null && !isLenNumOnly("termKeyForm","term_id4cons","POSV�ն˺�",8)) {
		return false;
	}
	if (termKeyForm.term_audstate == null || termKeyForm.term_audstate.value != '2') {
		if(!checkLenElement("termKeyForm","masterkey","�ն���Կ",16) || !checkLenElement("termKeyForm","masterkey_conf","�ն���Կ",16)){
			return false;
		}
		if (masterkey != masterkey_conf) {
			alert("������Կ���벻һ�£����������룡");
			document.all("masterkey").value = "";
			document.all("masterkey_conf").value = "";
			document.all("masterkey").focus();
			return false;
		}
		document.all("term_masterkey").value = masterkey;
	}
	if (document.all("mc_id_disp") != null)
		document.all("mc_id").value = trim(document.all("mc_id_disp").innerHTML);
	return true;
}
//�ն˳��̹������֤
function checkTermCompForm() {	
	if (document.all("tc_compno") != null && !checkelement("termCompForm","tc_compno","����Ʒ�Ʊ��")) {
		return false;
	}	
	if (document.all("tc_compname") != null && !checkelement("termCompForm","tc_compname","����Ʒ��ȫ��")) {
		return false;
	}	
	if (document.all("tc_compshortname") != null && !checkelement("termCompForm","tc_compshortname","����Ʒ����д")) {
		return false;
	}	
	if (document.all("tc_acct") != null && !checkelement("termCompForm","tc_acct","�����˻�")) {
		return false;
	}
	return true;
}
//�ն����۹������֤
function checkTermSaleForm() {	                             
	if (document.all("ts_termtype") != null && !checkelement("termSaleForm","ts_termtype","��������")) {
		return false;
	}
	if (document.all("tc_serialno") != null && !checkelement("termSaleForm","tc_serialno","����Ʒ��")) {
		return false;
	}
	if (document.all("ts_compdesc") != null && !checkelement("termSaleForm","ts_compdesc","������������")) {
		return false;
	}
	if (document.all("ts_termbaseamt") != null && !isnumbermor("termSaleForm","ts_termbaseamt","���߻������")) {
		return false;
	}	
	if (document.all("ts_termfix") != null && !isnumbermor("termSaleForm","ts_termfix","����ʵ���ۿ���")) {
		return false;
	}	
	return true;
}
//�ն����͹������֤
function checkTermTypeForm() {
	
	if (document.all("tt_termtypename") != null && !checkelement("termTypeForm","tt_termtypename","������������")) {
		return false;
	}	
	return true;
}
function checkProfitAcctForm(){	
	if (document.all("profit_acct") != null && !checkelement("profitAcctForm","profit_acct","�����˻�")) {
		return false;
	}
	if (document.all("profit_type") != null && !checkelement("profitAcctForm","profit_type","��������")) {
		return false;
	}
	var itemNo = document.getElementsByName("profit_trans_open");
	var feeobj = document.getElementsByName("profit_trans_feefix");
	var profit_name=document.getElementsByName("profit_name"); 
	var profit_code=document.getElementsByName("profit_code");
	 for(k = 0; k < itemNo.length;k++){		 
		 if(itemNo[k].checked==true){
			var input=feeobj[k].value;
		    var pos1 = input.indexOf(".");
		    var pos2 = input.lastIndexOf(".");
		    var charset = "1234567890.";
		    //var value_profitcode=profit_code[k].value;
		    
		    if(profit_name[k].value==null||profit_name[k].value==''){
		    	alert("�������Ʋ���Ϊ��");
		    	profit_name[k].focus();
		    	profit_name[k].select();
		        return false;
		    }
		   /*
		    if(value_profitcode==null||value_profitcode==''){
		    	alert("�������з��󷽱�Ų���Ϊ��");
		    	profit_code[k].focus();
		    	profit_code[k].select();
		        return false;
		    }
		    */
		    if(input==null||input==''){
		    	alert("�����������Ϊ��");
		    	feeobj[k].focus();
		        feeobj[k].select();
		        return false;
		    }
		    if (input!=null&&input!=''&&((pos1 != pos2)||(!checkChar(charset, input, true)))) {
		        alert ("�������ֻ�ɰ������ֺ�һ����.����");
		        feeobj[k].focus();
		        feeobj[k].select();
		        return false;
		    }    
		 }
	 }
	 var num=0;	 
	 var m=0;
	 m=0;
	 for(i = 0; i < itemNo.length;i++){	
		if(itemNo[i].checked==true){
			  var input =feeobj[i].value; 
			   num=num+parseFloat(input);
			   m++;
		}
	}
	if(m<=0){
		alert("����ѡһ������");
		return false;
	}
	if(num!=100){
			alert ("����������ܺͱ���Ϊ100");
			return false;
	}
	return true;
	
}
function addprofitmc(){	
	var tableobj=document.all("profitaccttable");
	var nrow=tableobj.rows.length;
	
	var otr=tableobj.insertRow();	
	setTdAttribute(otr,0,'&nbsp;');	
	setTdAttribute(otr,1,'<input type="text" name="profit_name" onclick="mainshowaccdiv(this,'+nrow+')" onkeyup="mainshowaccdiv(this,'+nrow+')" value=""> <input type="hidden" name="profit_id" value=""><input type="hidden" name="profit_acctno" value="">');	
	setTdAttribute(otr,2,'<input type="checkbox" name="profit_trans_open" onclick="checkedtransopen('+nrow+')" value="">');	
	setTdAttribute(otr,3,'<input type="text" id="profit_trans_feefix" name="profit_trans_feefix" size="12" value="" maxlength="7">%');
	//setTdAttribute(otr,4,'<input type="text" id="profit_code" name="profit_code" size="12" value="" maxlength="10">');
	
	
}
function setTdAttribute(objtr,n,tdhtml){
	objtd=objtr.insertCell(n);
	objtd.setAttribute("className","box2");
	objtd.innerHTML=tdhtml;
	objtd.setAttribute("align","center");
	objtd.height="28pt";
}


/*-----���div ��̬��ʾ����  ��������  ��ʾǰ10������  �������� ��̬��ʾ��������  ���û�� ����ʾ  ------ */
var preObjectProfitName=null;
var preObjectProfitid=null;
var prenrow=0;
with (document) {
	write('<div id="divAcctName" style="overflow:auto; overflow-x:hidden;POSITION:absolute;VISIBILITY:hidden;border:1px solid #B8DBED ridge;z-index:100;width=180px;height:200px;padding:1px;background-color:White;">');
	write('<span onclick="hiddenAcctDiv()" style="algin:right">�ر�</span>')
	write("</div>");
	write('<div id="divAcctContent" style="OVERFLOW:hidden;display:none;">');
	write("</div>");
	
}
//������ obj �ı��� nrow ��ǰ�����ڵĵڼ��� ��0��ʼ����
function mainshowaccdiv(obj,nrow){
	
	setLocationdiv(obj);
	var divAcctName=document.getElementById("divAcctName");
	showProfitMcListByAcctName(obj,divAcctName);
	prenrow=nrow;
	preObjectProfitName=obj;
}
function hiddenAcctDiv(){	
	divAcctName.style.visibility = "hidden";
}
function visibleAcctDiv(){	
	divAcctName.style.visibility = "visible";
}
//��ʾλ��
function setLocationdiv(popCtrl){
	 var point = funGetXY(popCtrl);	 
	 with (divAcctName.style) {		
		left = point.x;
		top  = point.y+popCtrl.offsetHeight;
		//Ҫ�ж������λ��û��div�ĸ߶ȵ�ʱ�� Ҫ��ʾ���Ϸ�
		width = divAcctName.offsetWidth;		
		height = divAcctName.offsetHeight;
		visibility = 'visible';
	 }
}
//�õ���ǰ�ռ��λ��
function funGetXY(aTag){
	  var oTmp = aTag;
	  var pt = new funPoint(0,0);
	  do {
	  	pt.x += oTmp.offsetLeft;
	  	pt.y += oTmp.offsetTop;
	  	oTmp = oTmp.offsetParent;
	  } while(oTmp.tagName!="BODY");
	  return pt;
	}
function funPoint(iX, iY){
	this.x = iX;
	this.y = iY;
}
//���ݷ������Ƶõ������б� �����붯̬div�� 
function showProfitMcListByAcctName(objprofit_name,divobj,nrow){
	var profit_name=objprofit_name.value;
	var divAcctContent=document.all("divAcctContent");
	divAcctContent.innerHTML="";
	profitMcManage.showProfitMcListByAcctName(profit_name,function(data){
		var str='';
		str='<span onclick="hiddenAcctDiv()" style="float:right;cursor:pointer">�ر�</span>';
		str+='<ul class="tc_ul">';		
		for(i=0;i<data.length;i++){			
			str+='<li onclick="nameselect(\''+data[i].profit_id+'\')">'+data[i].profit_name+'</li>';
			divAcctContent.innerHTML+=data[i].profit_id+'|'+data[i].profit_name+'|'+data[i].profit_acctno+',';
		}			
		str+='</ul>';		
		divobj.innerHTML=str;
		
	});
	
}
function nameselect(profit_id){		
	var str=divAcctContent.innerHTML;
	var arrAcctBeanList=new Array();
	var tableobj=document.all("profitaccttable");
	var nrow=tableobj.rows;
	var profit_trans_opens = document.getElementsByName("profit_trans_open");
	var profit_ids=document.getElementsByName("profit_id");
	var profit_names=document.getElementsByName("profit_name");
	var profit_acctnos=document.getElementsByName("profit_acctno");	
	arrAcctBeanList=str.split(',');	
	for(i=0;i<arrAcctBeanList.length;i++){
		arrAcctBean=new Array();
		arrAcctBean=arrAcctBeanList[i].split('|');		
		if(profit_id==arrAcctBean[0]){			
			//��ÿ�����ص��ı���ֵ
			for(n=0;n<nrow.length;n++){
				if(n==prenrow){						
					profit_ids[n-1].value=arrAcctBean[0];				
					profit_names[n-1].value=arrAcctBean[1];					
					profit_acctnos[n-1].value=arrAcctBean[2];
					profit_trans_opens[n-1].checked=true;				
					nrow.item(n).cells.item(0).innerText=arrAcctBean[0];
				}
			}
		}
	}	
	hiddenAcctDiv();	
}
function checkedtransopen(nrow){
	var tableobj=document.all("profitaccttable");
	var trnrow=tableobj.rows;	
	var profit_trans_opens = document.getElementsByName("profit_trans_open");
	var profit_ids=document.getElementsByName("profit_id");
	var profit_names=document.getElementsByName("profit_name");
	var profit_acctnos=document.getElementsByName("profit_acctno");	
	var profit_trans_feefix=document.getElementsByName("profit_trans_feefix");
	for(n=0;n<trnrow.length;n++){		
		if(n==nrow){			
			if(profit_trans_opens[n-1].checked==false){
			profit_ids[n-1].value="";				
			profit_names[n-1].value="";					
			profit_acctnos[n-1].value="";
			profit_trans_opens[n-1].checked=false;
			profit_trans_feefix[n-1].value="";
			trnrow.item(n).cells.item(0).innerText=" ";			
			}
		}
	}
}
/*-----���div ��̬��ʾ����  ��������  ��ʾǰ10������  �������� ��̬��ʾ��������  ���û�� ����ʾ  ------ */

//��ҳ������ת
function pagegotosubmit(urltxt){	
	pagenum=document.getElementById("txtPage").value;
	if(pagenum!=""&&pagenum!=''&&pagenum!='undefine'){
	  document.location=urltxt+"&currentPage="+pagenum;
	}
}

