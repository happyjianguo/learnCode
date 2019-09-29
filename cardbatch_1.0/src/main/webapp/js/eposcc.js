loadJS = function(url){var script = "<script type='text/javascript' src='" + url + "'></script>";document.write(script);}
loadJS("/js/checkform.js");
loadJS("/js/dwr.js");

//用户管理表单验证
function checkMerchantForm(){
	if (!checkelement("merchantForm","mc_agrno","协议编号")) {
		return false;
	}
	if (!checkelement("merchantForm","mc_name","用户名称")) {
		return false;
	}
	if (!checkelement("merchantForm","mc_malocnam","经营场所名称")) {
		return false;
	}

	
//判断行业代码不为空 或者为空 	
	if (trim(window.document.forms["merchantForm"].elements["mc_type"].value) != "" && window.document.forms["merchantForm"].elements["mc_type"].value != null) {	
		if (!isLenNumOnly("merchantForm","mc_type","行业代码",4)) {
			return false;
		}
	}
	var objisacct=document.all("tc_isacct");
	if(objisacct[0].checked==true){//如果不绑定账户  则不验证账户	  
	
		if (!checkelement("merchantForm","mc_cactno","绑定账号")) {
			return false;
		}
		if (!checkelement("merchantForm","mc_cactnam","绑定账号户名")) {
			return false;
		}
		if (!checkelement("merchantForm","mc_opnbnk","账号开户行名称")) {
			return false;
		}
	}
	
	if (!checkelement("merchantForm","mc_scale","用户规模")) {
		return false;
	}
	if (!checkelement("merchantForm","mc_chadate","收费日期")) {
		return false;
	}
	/*添加的时候不添加消费类编号 去掉 
	 * if (document.getElementById("mc_id4cons").value != '') {
		if (!isLenNumOnly("merchantForm","mc_id4cons","消费类用户编号",15)) {
			return false;
		}
	}*/
	if (document.getElementById("mc_id4mob").value != '') {
		if (!isLenNumOnly("merchantForm","mc_id4mob","手机支付类用户编号",15)) {
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
//用户管理表单验证
function checkSignMerchantForm(){
//	if (!checkelement("merchantForm","mc_agrno","协议编号")) {
//		return false;
//	}
//	if (!checkelement("merchantForm","mc_name","用户名称")) {
//		return false;
//	}
//	if (!checkelement("merchantForm","mc_malocnam","经营场所名称")) {
//		return false;
//	}
//	if (!checkelement("merchantForm","mckind_id","平台用户类别")) {
//		return false;
//	}
	
	
//判断行业代码不为空 或者为空 	
	if (trim(window.document.forms["merchantForm"].elements["mc_type"].value) != "" && window.document.forms["merchantForm"].elements["mc_type"].value != null) {	
		if (!isLenNumOnly("merchantForm","mc_type","行业代码",4)) {
			return false;
		}
	}	
//	if (!checkelement("merchantForm","mc_scale","用户规模")) {
//		return false;
//	}
//	if (!checkelement("merchantForm","mc_chadate","收费日期")) {
//		return false;
//	}
	
//	if (document.getElementById("mc_id4mob").value != '') {
//		if (!isLenNumOnly("merchantForm","mc_id4mob","手机支付类用户编号",15)) {
//			return false;
//		}
//	}
	return true;
	
	
}
//终端管理表单验证
function checkTerminalForm() {
	var term_tel = document.terminalForm.term_tel.value;
	var term_psam = document.terminalForm.term_psam.value;
	var tacctFlag = false;
	var isnewnumber = RegExp(/^[0-9|\*]+$/);	
	if (!isnewnumber.test(term_tel)) {
		alert("绑定电话必须为数字或者*！");
		document.terminalForm.term_tel.focus();
		return false;
	}
	/*
	if(bingTel.length != 8){
		alert("绑定电话必须为8位数字！");
		document.terminalForm.bingTel.focus();
		return false;
	}
	*/
	if(!isInteger(term_psam)){
		alert("PSAM卡号必须为数字！");
		document.terminalForm.term_psam.focus();
		return false;
	}
	if(term_psam.length != 16){
		alert("PSAM卡号必须为16位数字！");
		document.terminalForm.term_psam.focus();
		return false;
	}
	//if (!checkelement("terminalForm","term_addr","柜台所在楼层")) {
	//	return false;
	//}
	//if (!checkelement("terminalForm","term_connam","柜台名称")) {
	//	return false;
	//}
	if (document.getElementById("term_id4cons").value != '') {
		if (!isLenNumOnly("terminalForm","term_id4cons","消费类终端编号",8)) {
			return false;
		}
	}
	if (document.getElementById("term_id4mob").value != '') {
		if (!isLenNumOnly("terminalForm","term_id4mob","手机支付类终端编号",8)) {
			return false;
		}
	}
	if (!checkelement("terminalForm","term_insdat","安装日期")) {
		return false;
	}
	/*
	if(document.terminalForm.term_insdat.value <= getNowDate() ){
		alert("安装日期必须大于当日！");
		return false;
	}
	*/
	if(!checkelement("terminalForm","prov_id","所属省份") || !checkelement("terminalForm","area_code","所属城市")){
		return false;
	}
	var nums = document.all("tmenu_id");
//	if (nums != null && nums !='') {
	if (typeof(nums)!= undefined && nums !='') {
		if (!isNumber(document.all("term_bcust_amt"),"用户服务费金额")) {
			return false;
		}
		var len = nums.length;
		var test=/^[0-9]+$/;
	 	if (test.test(len) == false) {
	 		if (!isNumber(document.all("tc_ensglamt"),"单笔限额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tc_endailyamt"),"日累计限额")) {
	 			return false;
	 		}
//	 		if (!isNumber(document.all("tc_trans_fee"),"交易手续费单笔收取金额")) {
//	 			return false;
//	 		}
	 		if (!isNumber(document.all("tc_trans_feefix"),"交易手续费比例收取金额")) {
	 			return false;
	 		}
	 		
//	 		if (!isNumber(document.all("tc_trans_feemin"),"交易手续费最小收取金额")) {
//	 			return false;
//	 		}
//	 		if (!isNumber(document.all("tc_trans_feemax"),"交易手续费最大收取金额")) {
//	 			return false;
//	 		}
//	 		if(document.all("tc_trans_feemax").value > 0){
//	 			if(document.all("tc_trans_fee").value - document.all("tc_trans_feemax").value > 0){
//	 				alert("交易手续费单笔收取金额 不能大于 交易手续费最大收取金额");
//	 				return false;
//	 			}
//	 		}
	 		if(document.all("tc_endailyamt").value > 0){
	 			if(document.all("tc_ensglamt").value - document.all("tc_endailyamt").value > 0){
	 				alert("单笔限额 不能大于 日累计限额");
	 				return false;
	 			}
	 		}
	 		/*
	 		if (!isNumber(document.all("tc_cust_fee"),"客户手续费单笔收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tc_cust_feefix"),"客户手续费比例收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tc_cust_feemin"),"客户手续费最小收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tc_cust_feemax"),"客户手续费最大收取金额")) {
	 			return false;
	 		}
	 		if(document.all("tc_cust_feemax").value > 0){
	 			if(document.all("tc_cust_fee").value - document.all("tc_cust_feemax").value > 0){
	 				alert("客户手续费单笔收取金额 不能大于 客户手续费最大收取金额");
	 				return false;
	 			}
	 		}
	 		*/
//	 		if (!isNumber(document.all("tc_term_fee"),"终端手续费单笔收取金额")) {
//	 			return false;
//	 		}
	 		if (!isNumber(document.all("tc_term_feefix"),"终端手续费比例收取金额")) {
	 			return false;
	 		}
	 		//	 		if (!isNumber(document.all("tc_term_feemin"),"终端手续费最小收取金额")) {
//	 			return false;
//	 		}
//	 		if (!isNumber(document.all("tc_term_feemax"),"终端手续费最大收取金额")) {
//	 			return false;
//	 		}
//	 		if(document.all("tc_term_feemax").value > 0){
//	 			if(document.all("tc_term_fee").value - document.all("tc_term_feemax").value > 0){
//	 				alert("终端手续费单笔收取金额 不能大于 终端手续费最大收取金额");
//	 				return false;
//	 			}
//	 		}
	 		if (!isNumber(document.all("tc_reck_feefix"),"结算手续费比例收取金额")) {
	 			return false;
	 		}
//	 		if(document.all("tc_reck_feefix").value < document.all("tmp_reck_feefix").value){
//	 			alert("终端手续费折扣比例不得小于默认的折扣比例");
//	 			document.all("tc_reck_feefix").focus;
//	 			document.all("tc_reck_feefix").select;
//	 			return false;
//	 		}
//	 		if(document.all("tc_trans_feefix").value < document.all("tmp_trans_feefix").value){
//	 			alert("交易手续费折扣比例不得小于默认的折扣比例");
//	 			document.all("tc_trans_feefix").focus;
//	 			document.all("tc_trans_feefix").select;
//	 			return false;
//	 		}
//	 		if(document.all("tc_term_feefix").value < document.all("tmp_term_feefix").value){
//	 			alert("折扣比例不得小于默认的折扣比例");
//	 			document.all("tc_term_feefix").focus;
//	 			document.all("tc_term_feefix").select;
//	 			return false;
//	 		}
	 		/*
	 		if (!isNumber(document.all("tc_reck_fee"),"结算手续费单笔收取金额")) {
	 			return false;
	 		}
	 		
	 		if (!isNumber(document.all("tc_reck_feemin"),"结算手续费最小收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tc_reck_feemax"),"结算手续费最大收取金额")) {
	 			return false;
	 		}
	 		if(document.all("tc_reck_feemax").value > 0){
	 			if(document.all("tc_reck_fee").value - document.all("tc_reck_feemax").value > 0){
	 				alert("结算手续费单笔收取金额 不能大于 结算手续费最大收取金额");
	 				return false;
	 			}
	 		}
	 		*/
	 	
	 		if (document.all("tc_term_open").value == '1') tacctFlag = true;
	 		
	 	} else {
			for(var i=0; i<len; i++) {
				if (!isNumber(document.all("tc_ensglamt")[i],"单笔限额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tc_endailyamt")[i],"日累计限额")) {
		 			return false;
		 		}
//				if (!isNumber(document.all("tc_trans_fee")[i],"交易手续费单笔收取金额")) {
//		 			return false;
//		 		}
				if (!isNumber(document.all("tc_trans_feefix")[i],"交易手续费比例收取金额")) {
		 			return false;
		 		}
//				if(document.all("tc_trans_feefix")[i].value < document.all("tmp_trans_feefix")[i].value){
//		 			alert("交易手续费折扣比例不得小于默认的折扣比例");
//		 			return false;
//		 		}
//				if (!isNumber(document.all("tc_trans_feemin")[i],"交易手续费最小收取金额")) {
//		 			return false;
//		 		}
//				if (!isNumber(document.all("tc_trans_feemax")[i],"交易手续费最大收取金额")) {
//		 			return false;
//		 		}
//		 		if(document.all("tc_trans_feemax")[i].value > 0){
//		 			if(document.all("tc_trans_fee")[i].value -  document.all("tc_trans_feemax")[i].value > 0){
//		 				alert("交易手续费单笔收取金额 不能大于 交易手续费最大收取金额");
//		 				return false;
//		 			}
//	 			}
	 			if(document.all("tc_endailyamt")[i].value > 0){
		 			if(document.all("tc_ensglamt")[i].value -  document.all("tc_endailyamt")[i].value > 0){
		 				alert("单笔限额 不能大于 日累计限额");
		 				return false;
		 			}
	 			}
	 			/*
				if (!isNumber(document.all("tc_cust_fee")[i],"客户手续费单笔收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tc_cust_feefix")[i],"客户手续费比例收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tc_cust_feemin")[i],"客户手续费最小收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tc_cust_feemax")[i],"客户手续费最大收取金额")) {
		 			return false;
		 		}
		 		if(document.all("tc_cust_feemax")[i].value > 0){
		 			if(document.all("tc_cust_fee")[i].value -  document.all("tc_cust_feemax")[i].value > 0){
		 				alert("客户手续费单笔收取金额 不能大于 客户手续费最大收取金额");
		 				return false;
		 			}
	 			}
	 			*/
//				if (!isNumber(document.all("tc_term_fee")[i],"终端手续费单笔收取金额")) {
//		 			return false;
//		 		}
				if (!isNumber(document.all("tc_term_feefix")[i],"终端手续费比例收取金额")) {
		 			return false;
		 		}
//				if(document.all("tc_term_feefix")[i].value < document.all("tmp_term_feefix")[i].value){
//		 			alert("终端手续费折扣比例不得小于默认的折扣比例");
//		 			document.all("tc_term_feefix")[i].focus;
//		 			document.all("tc_term_feefix")[i].select;
//		 			return false;
//		 		}
				if (!isNumber(document.all("tc_reck_feefix")[i],"结算手续费比例收取金额")) {
		 			return false;
		 		}
//				if(document.all("tc_reck_feefix")[i].value < document.all("tmp_reck_feefix")[i].value){
//		 			alert("结算手续费折扣比例不得小于默认的折扣比例");
//		 			document.all("tc_reck_feefix")[i].focus;
//		 			document.all("tc_reck_feefix")[i].select;
//		 			return false;
//		 		}
//				if (!isNumber(document.all("tc_term_feemin")[i],"终端手续费最小收取金额")) {
//		 			return false;
//		 		}
//				if (!isNumber(document.all("tc_term_feemax")[i],"终端手续费最大收取金额")) {
//		 			return false;
//		 		}
//		 		if(document.all("tc_term_feemax")[i].value > 0){
//		 			if(document.all("tc_term_fee")[i].value -  document.all("tc_term_feemax")[i].value > 0){
//		 				alert("终端手续费单笔收取金额 不能大于 终端手续费最大收取金额");
//		 				return false;
//		 			}
//	 			}
		 		/*
				if (!isNumber(document.all("tc_reck_fee")[i],"结算手续费单笔收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tc_reck_feefix")[i],"结算手续费比例收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tc_reck_feemin")[i],"结算手续费最小收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tc_reck_feemax")[i],"结算手续费最大收取金额")) {
		 			return false;
		 		}
		 		if(document.all("tc_reck_feemax")[i].value > 0){
		 			if(document.all("tc_reck_fee")[i].value -  document.all("tc_reck_feemax")[i].value > 0){
		 				alert("结算手续费单笔收取金额 不能大于 结算手续费最大收取金额");
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
			alert("该用户尚无菜单，请勿个性化！");
			isExcust[0].checked = true;
			return false;
		}
	}
	/**屏蔽终端使用费收取账户的验证 qhg 2010-02-22
	 * 
	 * if (tacctFlag == true && !checkelement("terminalForm","term_acct","终端使用费收取账户")) {
		return false;
	}*/
	return true;
}
//预签约终端管理表单验证
function checkSignTerminalForm() {
	
	var term_tel = document.terminalForm.term_tel.value;
	var term_psam = document.terminalForm.term_psam.value;
	var tacctFlag = false;
	var isnewnumber = RegExp(/^[0-9|\*]+$/);	
	if (term_tel!=''&&!isnewnumber.test(term_tel)) {
		alert("绑定电话必须为数字或者*！");
		document.terminalForm.term_tel.focus();
		return false;
	}	
	if(!isInteger(term_psam)){
		alert("PSAM卡号必须为数字！");
		document.terminalForm.term_psam.focus();
		return false;
	}
	if(term_psam.length != 16){
		alert("PSAM卡号必须为16位数字！");
		document.terminalForm.term_psam.focus();
		return false;
	}	
	if (document.getElementById("term_id4cons").value != '') {
		if (!isLenNumOnly("terminalForm","term_id4cons","消费类终端编号",8)) {
			return false;
		}
	}
	if (document.getElementById("term_id4mob").value != '') {
		if (!isLenNumOnly("terminalForm","term_id4mob","手机支付类终端编号",8)) {
			return false;
		}
	}	
	if(!checkelement("terminalForm","prov_id","所属省份") || !checkelement("terminalForm","area_code","所属城市")){
		return false;
	}
	
	var nums = document.all("tmenu_id");
/*if(nums==null||nums==""){
		
	}else{*/
	if (nums != null && nums !='') {
		if (!isNumber(document.all("term_bcust_amt"),"用户服务费金额")) {
			return false;
		}
		
		var len = nums.length;
		var test=/^[0-9]+$/;
	 	if (test.test(len) == false) {
	 		if (!isNumber(document.all("tc_ensglamt"),"单笔限额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tc_endailyamt"),"日累计限额")) {
	 			return false;
	 		}
//	 		if (!isNumber(document.all("tc_trans_fee"),"交易手续费单笔收取金额")) {
//	 			return false;
//	 		}
	 		if (!isNumber(document.all("tc_trans_feefix"),"交易手续费比例收取金额")) {
	 			return false;
	 		}
//	 		if (!isNumber(document.all("tc_trans_feemin"),"交易手续费最小收取金额")) {
//	 			return false;
//	 		}
//	 		if (!isNumber(document.all("tc_trans_feemax"),"交易手续费最大收取金额")) {
//	 			return false;
//	 		}
//	 		if(document.all("tc_trans_feemax").value > 0){
//	 			if(document.all("tc_trans_fee").value - document.all("tc_trans_feemax").value > 0){
//	 				alert("交易手续费单笔收取金额 不能大于 交易手续费最大收取金额");
//	 				return false;
//	 			}
//	 		}
	 		if(document.all("tc_endailyamt").value > 0){
	 			if(document.all("tc_ensglamt").value - document.all("tc_endailyamt").value > 0){
	 				alert("单笔限额 不能大于 日累计限额");
	 				return false;
	 			}
	 		}
	 		
//	 		if (!isNumber(document.all("tc_term_fee"),"终端手续费单笔收取金额")) {
//	 			return false;
//	 		}
	 		if (!isNumber(document.all("tc_term_feefix"),"终端手续费比例收取金额")) {
	 			return false;
	 		}
//	 		if (!isNumber(document.all("tc_term_feemin"),"终端手续费最小收取金额")) {
//	 			return false;
//	 		}
//	 		if (!isNumber(document.all("tc_term_feemax"),"终端手续费最大收取金额")) {
//	 			return false;
//	 		}
//	 		if(document.all("tc_term_feemax").value > 0){
//	 			if(document.all("tc_term_fee").value - document.all("tc_term_feemax").value > 0){
//	 				alert("终端手续费单笔收取金额 不能大于 终端手续费最大收取金额");
//	 				return false;
//	 			}
//	 		}	 		
	 		if (document.all("tc_term_open").value == '1') tacctFlag = true;
	 	} else {
			for(var i=0; i<len; i++) {
				if (!isNumber(document.all("tc_ensglamt")[i],"单笔限额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tc_endailyamt")[i],"日累计限额")) {
		 			return false;
		 		}
//				if (!isNumber(document.all("tc_trans_fee")[i],"交易手续费单笔收取金额")) {
//		 			return false;
//		 		}
				if (!isNumber(document.all("tc_trans_feefix")[i],"交易手续费比例收取金额")) {
		 			return false;
		 		}
//				if (!isNumber(document.all("tc_trans_feemin")[i],"交易手续费最小收取金额")) {
//		 			return false;
//		 		}
//				if (!isNumber(document.all("tc_trans_feemax")[i],"交易手续费最大收取金额")) {
//		 			return false;
//		 		}
//		 		if(document.all("tc_trans_feemax")[i].value > 0){
//		 			if(document.all("tc_trans_fee")[i].value -  document.all("tc_trans_feemax")[i].value > 0){
//		 				alert("交易手续费单笔收取金额 不能大于 交易手续费最大收取金额");
//		 				return false;
//		 			}
//	 			}
	 			if(document.all("tc_endailyamt")[i].value > 0){
		 			if(document.all("tc_ensglamt")[i].value -  document.all("tc_endailyamt")[i].value > 0){
		 				alert("单笔限额 不能大于 日累计限额");
		 				return false;
		 			}
	 			}
	 			
//				if (!isNumber(document.all("tc_term_fee")[i],"终端手续费单笔收取金额")) {
//		 			return false;
//		 		}
				if (!isNumber(document.all("tc_term_feefix")[i],"终端手续费比例收取金额")) {
		 			return false;
		 		}
//				if (!isNumber(document.all("tc_term_feemin")[i],"终端手续费最小收取金额")) {
//		 			return false;
//		 		}
//				if (!isNumber(document.all("tc_term_feemax")[i],"终端手续费最大收取金额")) {
//		 			return false;
//		 		}
//		 		if(document.all("tc_term_feemax")[i].value > 0){
//		 			if(document.all("tc_term_fee")[i].value -  document.all("tc_term_feemax")[i].value > 0){
//		 				alert("终端手续费单笔收取金额 不能大于 终端手续费最大收取金额");
//		 				return false;
//		 			}
//	 			}
		 		if (document.all("tc_term_open")[i].value == '1') tacctFlag = true;
			}
	 	}
	
	} else {
		
		var isExcust = document.all("tc_isexcust");
		
		if (isExcust != null && isExcust != '' && isExcust[0].checked == true) {
			alert("该用户尚无菜单，请勿个性化！");
			isExcust[1].checked = true;
			return false;
		}
		
	}
	/**屏蔽终端使用费收取账户的验证 qhg 2010-02-22
	 * 
	 * if (tacctFlag == true && !checkelement("terminalForm","term_acct","终端使用费收取账户")) {
		return false;
	}*/
	return true;
}
//终端类别管理表单验证
function checkTermKindForm() {
	var tkindname = document.termKindForm.tkind_name.value;
	var nums = document.all("tmenu_id");
	if (nums != null && nums !='') {
		var len = nums.length;
		var test=/^[0-9]+$/;
	 	if (test.test(len) == false) {
	 		if (!isNumber(document.all("tkc_ensglamt"),"单笔限额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tkc_endailyamt"),"日累计限额")) {
	 			return false;
	 		}
//	 		if (!isNumber(document.all("tkc_trans_fee"),"交易手续费单笔收取金额")) {
//	 			return false;
//	 		}
	 		if (!isNumber(document.all("tkc_trans_feefix"),"交易手续费比例收取金额")) {
	 			return false;
	 		}
//	 		if (!isNumber(document.all("tkc_trans_feemin"),"交易手续费最小收取金额")) {
//	 			return false;
//	 		}
//	 		if (!isNumber(document.all("tkc_trans_feemax"),"交易手续费最大收取金额")) {
//	 			return false;
//	 		}
//	 		if(document.all("tkc_trans_feemax").value > 0){
//	 			if(document.all("tkc_trans_fee").value - document.all("tkc_trans_feemax").value > 0){
//	 				alert("交易手续费单笔收取金额 不能大于 交易手续费最大收取金额");
//	 				return false;
//	 			}
//	 		}
	 		/*
	 		if (!isNumber(document.all("tkc_cust_fee"),"客户手续费单笔收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tkc_cust_feefix"),"客户手续费比例收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tkc_cust_feemin"),"客户手续费最小收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tkc_cust_feemax"),"客户手续费最大收取金额")) {
	 			return false;
	 		}
	 		if(document.all("tkc_cust_feemax").value > 0){
	 			if(document.all("tkc_cust_fee").value - document.all("tkc_cust_feemax").value > 0){
	 				alert("客户手续费单笔收取金额 不能大于 客户手续费最大收取金额");
	 				return false;
	 			}
	 		}
	 		*/
//	 		if (!isNumber(document.all("tkc_term_fee"),"终端手续费单笔收取金额")) {
//	 			return false;
//	 		}
	 		if (!isNumber(document.all("tkc_term_feefix"),"终端手续费比例收取金额")) {
	 			return false;
	 		}
//	 		if (!isNumber(document.all("tkc_term_feemin"),"终端手续费最小收取金额")) {
//	 			return false;
//	 		}
//	 		if (!isNumber(document.all("tkc_term_feemax"),"终端手续费最大收取金额")) {
//	 			return false;
//	 		}
//	 		if(document.all("tkc_term_feemax").value > 0){
//	 			if(document.all("tkc_term_fee").value - document.all("tkc_term_feemax").value > 0){
//	 				alert("终端手续费单笔收取金额 不能大于 终端手续费最大收取金额");
//	 				return false;
//	 			}
//	 		}
	 		/*
	 		if (!isNumber(document.all("tkc_reck_fee"),"结算手续费单笔收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tkc_reck_feefix"),"结算手续费比例收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tkc_reck_feemin"),"结算手续费最小收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("tkc_reck_feemax"),"结算手续费最大收取金额")) {
	 			return false;
	 		}
	 		if(document.all("tkc_reck_feemax").value > 0){
	 			if(document.all("tkc_reck_fee").value - document.all("tkc_reck_feemax").value > 0){
	 				alert("结算手续费单笔收取金额 不能大于 结算手续费最大收取金额");
	 				return false;
	 			}
	 		}
	 		*/
	 	} else {
			for(var i=0; i<len; i++) {
				if (!isNumber(document.all("tkc_ensglamt")[i],"单笔限额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tkc_endailyamt")[i],"日累计限额")) {
		 			return false;
		 		}
//				if (!isNumber(document.all("tkc_trans_fee")[i],"交易手续费单笔收取金额")) {
//		 			return false;
//		 		}
				if (!isNumber(document.all("tkc_trans_feefix")[i],"交易手续费比例收取金额")) {
		 			return false;
		 		}
//				if (!isNumber(document.all("tkc_trans_feemin")[i],"交易手续费最小收取金额")) {
//		 			return false;
//		 		}
//				if (!isNumber(document.all("tkc_trans_feemax")[i],"交易手续费最大收取金额")) {
//		 			return false;
//		 		}
		 		
//		 		if(document.all("tkc_trans_feemax")[i].value > 0){
//		 			if(document.all("tkc_trans_fee")[i].value -  document.all("tkc_trans_feemax")[i].value > 0){
//		 				alert("交易手续费单笔收取金额 不能大于 交易手续费最大收取金额");
//		 				return false;
//		 			}
//	 			}
		 		/*
				if (!isNumber(document.all("tkc_cust_fee")[i],"客户手续费单笔收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tkc_cust_feefix")[i],"客户手续费比例收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tkc_cust_feemin")[i],"客户手续费最小收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tkc_cust_feemax")[i],"客户手续费最大收取金额")) {
		 			return false;
		 		}
		 		if(document.all("tkc_cust_feemax")[i].value > 0){
		 			if(document.all("tkc_cust_fee")[i].value -  document.all("tkc_cust_feemax")[i].value > 0){
		 				alert("客户手续费单笔收取金额 不能大于 客户手续费最大收取金额");
		 				return false;
		 			}
	 			}
	 			*/
//				if (!isNumber(document.all("tkc_term_fee")[i],"终端手续费单笔收取金额")) {
//		 			return false;
//		 		}
				if (!isNumber(document.all("tkc_term_feefix")[i],"终端手续费比例收取金额")) {
		 			return false;
		 		}
//				if (!isNumber(document.all("tkc_term_feemin")[i],"终端手续费最小收取金额")) {
//		 			return false;
//		 		}
//				if (!isNumber(document.all("tkc_term_feemax")[i],"终端手续费最大收取金额")) {
//		 			return false;
//		 		}
//		 		if(document.all("tkc_term_feemax")[i].value > 0){
//		 			if(document.all("tkc_term_fee")[i].value -  document.all("tkc_term_feemax")[i].value > 0){
//		 				alert("终端手续费单笔收取金额 不能大于 终端手续费最大收取金额");
//		 				return false;
//		 			}
//	 			}
		 		/*
				if (!isNumber(document.all("tkc_reck_fee")[i],"结算手续费单笔收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tkc_reck_feefix")[i],"结算手续费比例收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tkc_reck_feemin")[i],"结算手续费最小收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("tkc_reck_feemax")[i],"结算手续费最大收取金额")) {
		 			return false;
		 		}
		 		if(document.all("tkc_reck_feemax")[i].value > 0){
		 			if(document.all("tkc_reck_fee")[i].value -  document.all("tkc_reck_feemax")[i].value > 0){
		 				alert("结算手续费单笔收取金额 不能大于 结算手续费最大收取金额");
		 				return false;
		 			}
	 			}
	 			*/
			}
	 	}
	}
	if (!checkelement("termKindForm","tkind_name","用户类别名称")) {
		return false;
	}
	if (!isNumber(document.all("tkind_bcust_amt"),"用户服务费金额")) {
		return false;
	}
	return true;
}

//结算手续费管理表单验证
function checkReckFeeForm() {
	var nums = document.all("conf_id");
	if (nums != null && nums !='') {
		var len = nums.length;
		var test=/^[0-9]+$/;
	 	if (test.test(len) == false) {
	 		if (!isNumber(document.all("reck_fee"),"单笔收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feefix"),"比例收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feemin"),"最小收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feemax"),"最大收取金额")) {
	 			return false;
	 		}
	 		if(document.all("reck_feemax").value > 0){
	 			if(document.all("reck_fee").value - document.all("reck_feemax").value > 0){
	 				alert("单笔收取金额 不能大于 最大收取金额");
	 				return false;
	 			}
	 		}
	 	} else {
			for(var i=0; i<len; i++) {
				if (!isNumber(document.all("reck_fee")[i],"单笔收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feefix")[i],"比例收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feemin")[i],"最小收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feemax")[i],"最大收取金额")) {
		 			return false;
		 		}
		 		if(document.all("reck_feemax")[i].value > 0){
		 			if(document.all("reck_fee")[i].value -  document.all("reck_feemax")[i].value > 0){
		 				alert("单笔收取金额 不能大于 最大收取金额");
		 				return false;
		 			}
	 			}
			}
	 	}
	}
	return true;
}
//交易手续费用表单验证

function checkTransFeeForm() {
	var nums = document.all("conf_id");
	if (nums != null && nums !='') {
		var len = nums.length;
		var test=/^[0-9]+$/;
	 	if (test.test(len) == false) {
	 		if (!isNumber(document.all("reck_fee"),"单笔收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feefix"),"比例收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feemin"),"最小收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feemax"),"最大收取金额")) {
	 			return false;
	 		}
	 		if(document.all("reck_feemax").value > 0){
	 			if(document.all("reck_fee").value - document.all("reck_feemax").value > 0){
	 				alert("单笔收取金额 不能大于 最大收取金额");
	 				return false;
	 			}
	 		}
	 	} else {
			for(var i=0; i<len; i++) {
				if (!isNumber(document.all("reck_fee")[i],"单笔收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feefix")[i],"比例收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feemin")[i],"最小收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feemax")[i],"最大收取金额")) {
		 			return false;
		 		}
		 		if(document.all("reck_feemax")[i].value > 0){
		 			if(document.all("reck_fee")[i].value -  document.all("reck_feemax")[i].value > 0){
		 				alert("单笔收取金额 不能大于 最大收取金额");
		 				return false;
		 			}
	 			}
			}
	 	}
	}
	return true;
}

//终端使用费用表单验证

function checkTermFeeForm() {
	var nums = document.all("conf_id");
	if (nums != null && nums !='') {
		var len = nums.length;
		var test=/^[0-9]+$/;
	 	if (test.test(len) == false) {
	 		if (!isNumber(document.all("reck_fee"),"单笔收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feefix"),"比例收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feemin"),"最小收取金额")) {
	 			return false;
	 		}
	 		if (!isNumber(document.all("reck_feemax"),"最大收取金额")) {
	 			return false;
	 		}
	 		if(document.all("reck_feemax").value > 0){
	 			if(document.all("reck_fee").value - document.all("reck_feemax").value > 0){
	 				alert("单笔收取金额 不能大于 最大收取金额");
	 				return false;
	 			}
	 		}
	 	} else {
			for(var i=0; i<len; i++) {
				if (!isNumber(document.all("reck_fee")[i],"单笔收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feefix")[i],"比例收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feemin")[i],"最小收取金额")) {
		 			return false;
		 		}
				if (!isNumber(document.all("reck_feemax")[i],"最大收取金额")) {
		 			return false;
		 		}
		 		if(document.all("reck_feemax")[i].value > 0){
		 			if(document.all("reck_fee")[i].value -  document.all("reck_feemax")[i].value > 0){
		 				alert("单笔收取金额 不能大于 最大收取金额");
		 				return false;
		 			}
	 			}
			}
	 	}
	}
	return true;
}
//账户管理表单验证
function checkFeeAcctForm() {
	var fa_trans_acct = document.all("fa_trans_acct").value;
	var fa_reck_acct = document.all("fa_reck_acct").value;
	var fa_bcust_acct = document.all("fa_bcust_acct").value;
	if (fa_trans_acct != '' && !isnumberonly("feeAcctForm","fa_trans_acct","交易手续费收取账户")) {
		return false;
	}
	if (fa_reck_acct != '' && !isnumberonly("feeAcctForm","fa_reck_acct","结算手续费收取账户")) {
		return false;
	}
	if (fa_bcust_acct != '' && !isnumberonly("feeAcctForm","fa_bcust_acct","用户服务费收取账户")) {
		return false;
	}
	return true;
}

//分润管理表单验证
function checkProfitForm() {
	var profit_trans_feefix = document.all("profit_trans_feefix").value;
	var profit_bcust_feefix = document.all("profit_bcust_feefix").value;
	var profit_reck_feefix = document.all("profit_reck_feefix").value;
	if (!checkelement("profitForm","profit_id","分润方编号")) {
		return false;
	}
	if (!checkelement("profitForm","profit_name","分润方名称")) {
		return false;
	}
	if (!isnumberonly("profitForm","profit_acctno","分润方收入账户")) {
		return false;
	}
	if (!isNumber(document.all("profit_trans_fee"),"交易手续费单笔收取金额")) {
		return false;
	}
	if (!isNumber(document.all("profit_trans_feefix"),"交易手续费比例收取金额")) {
		return false;
	}
	if (!isNumber(document.all("profit_trans_feemin"),"交易手续费最小收取金额")) {
		return false;
	}
	if (!isNumber(document.all("profit_trans_feemax"),"交易手续费最大收取金额")) {
		return false;
	}
	if(document.all("profit_trans_feemax").value > 0){
		if(document.all("profit_trans_fee").value - document.all("profit_trans_feemax").value > 0){
			alert("交易手续费单笔收取金额 不能大于 交易手续费最大收取金额");
			return false;
		}
	}
	if (!isNumber(document.all("profit_bcust_fee"),"用户服务费单笔收取金额")) {
		return false;
	}
	if (!isNumber(document.all("profit_bcust_feefix"),"用户服务费比例收取金额")) {
		return false;
	}
	if (!isNumber(document.all("profit_bcust_feemin"),"用户服务费最小收取金额")) {
		return false;
	}
	if (!isNumber(document.all("profit_bcust_feemax"),"用户服务费最大收取金额")) {
		return false;
	}
	if(document.all("profit_bcust_feemax").value > 0){
		if(document.all("profit_bcust_fee").value - document.all("profit_bcust_feemax").value > 0){
			alert("用户服务费单笔收取金额 不能大于 用户服务费最大收取金额");
			return false;
		}
	}
	if (!isNumber(document.all("profit_reck_fee"),"结算手续费单笔收取金额")) {
		return false;
	}
	if (!isNumber(document.all("profit_reck_feefix"),"结算手续费比例收取金额")) {
		return false;
	}
	if (!isNumber(document.all("profit_reck_feemin"),"结算手续费最小收取金额")) {
		return false;
	}
	if (!isNumber(document.all("profit_reck_feemax"),"结算手续费最大收取金额")) {
		return false;
	}
	if(document.all("profit_reck_feemax").value > 0){
		if(document.all("profit_reck_fee").value - document.all("profit_reck_feemax").value > 0){
			alert("结算手续费单笔收取金额 不能大于结算手续费最大收取金额");
			return false;
		}
	}
	if (parseFloat(profit_trans_feefix) + parseFloat(trans_feefix_sum) > 100) {
		alert("交易手续费总分润比例已超出100%！");
		document.all("profit_trans_feefix").focus();
		return false;
	}
	if (parseFloat(profit_bcust_feefix) + parseFloat(bcust_feefix_sum) > 100) {
		alert("用户服务费总分润比例已超出100%！");
		document.all("profit_bcust_feefix").focus();
		return false;
	}
	if (parseFloat(profit_reck_feefix) + parseFloat(reck_feefix_sum) > 100) {
		alert("结算手续费总分润比例已超出100%！");
		document.all("profit_reck_feefix").focus();
		return false;
	}
	return true;
}
//分润管理表单验证
function checkProfitMcForm() {
	
	
	if (!checkelement("profitAcctForm","profit_id","分润方编号")) {
		return false;
	}
	if (!checkelement("profitAcctForm","profit_name","分润方名称")) {
		return false;
	}
	if (!isnumberonly("profitAcctForm","profit_acctno","分润方收入账户")) {
		return false;
	}
	
	return true;
}
//是否个性化终端验证
function checkTermIsExcust(flag, cfflag){
	var nums = document.all("tmenu_id");
	if(nums==null||nums=='') return false;
	var len = nums.length;
	var test=/^[0-9]+$/;
	if (!flag && cfflag) {
		if (!confirm("您正对该终端进行个性化修改，修改后，终端类别中的计费管理将与本用户无关，确认吗？")) {
			document.all("tc_isexcust")[1].checked = true;
			return false;
		}
	} else if (flag && cfflag) {
		if (!confirm("您正取消该终端的个性化，取消后，终端类别中的计费管理将会作用于本用户，确认吗？")) {
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
		//document.all("tc_reck_open").disabled=true;//默认不能修改 结算手续费 开通控制
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
			
			//document.all("tc_reck_open")[i].disabled = true;//默认不能修改 结算手续费 开通控制
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

//显示账户验证等待信息
function dispInfo() {
	if (noteFlag == "true") {
		//document.all("message3").innerHTML = str;
		document.all("message1").innerHTML = str + point;
		document.all("message2").innerHTML = "";
		point = point + "。";
		i++;
		if(i > 4) {
			i = 0;
			point = "";
		}
		setTimeout("dispInfo()",1000);
	}
}
//显示老板付款账户验证等待信息
function dispfkInfo() {
	if (fknoteFlag == "true") {
		//document.all("message3").innerHTML = str;
		document.all("message5").innerHTML = str + point;
		document.all("message6").innerHTML = "";
		point = point + "。";
		i++;
		if(i > 4) {
			i = 0;
			point = "";
		}
		setTimeout("dispfkInfo()",1000);
	}
}
//初始化账户信息
function init_flag(){
	if(click == "true"){
		var answer = window.confirm("您确定要重新输入帐号吗?");
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

//初始化账户信息
function init_fk_flag(){
	if(fkclick == "true"){
		var answer = window.confirm("您确定要重新输入帐号吗?");
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
//改变背景颜色
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

//改变统计周期
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

//用户业务报表查询
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
			alert("只能查询本月之前数据！");
			return false;
		}
		*/
		document.reportForm.action = "/bfbposp/mcTransReport.do?method=queryAgent";
	} else {
		var start_date = document.reportForm.start_date.value;
		var end_date = document.reportForm.end_date.value;
		if (!checkelement("reportForm","start_date","开始日期")) {
			return false;
		}
		if (!checkelement("reportForm","end_date","结束日期")) {
			return false;
		}
		if (start_date > end_date) {
			alert("起始日期不能大于结束日期！");
			return false;
		}
		if (daysBetween(start_date,end_date) > 90) {
			alert("查询时间跨度不能超过三个月！");
			return false;
		}
		document.reportForm.action = "/bfbposp/mcTransReport.do?method=queryAgentDaily";
	}
	document.reportForm.submit();
}

//客户数报表查询
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
			alert("只能查询本月之前数据！");
			return false;
		}
		*/
		document.reportForm.action = "/bfbposp/mcTermNumReport.do?method=queryAgent";
	} else {
		var start_date = document.reportForm.start_date.value;
		var end_date = document.reportForm.end_date.value;
		if (!checkelement("reportForm","start_date","开始日期")) {
			return false;
		}
		if (!checkelement("reportForm","end_date","结束日期")) {
			return false;
		}
		if (start_date > end_date) {
			alert("起始日期不能大于结束日期！");
			return false;
		}
		if (daysBetween(start_date,end_date) > 90) {
			alert("查询时间跨度不能超过三个月！");
			return false;
		}
		document.reportForm.action = "/bfbposp/mcTermNumReport.do?method=queryAgentDaily";
	}
	document.reportForm.submit();
}
//客户数报表查询
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
			alert("只能查询本月之前数据！");
			return false;
		}
		*/
		document.reportForm.action = "/bfbposp/mcTermNumReport.do?method=queryAgentdev";
	} else {
		var start_date = document.reportForm.start_date.value;
		var end_date = document.reportForm.end_date.value;
		if (!checkelement("reportForm","start_date","开始日期")) {
			return false;
		}
		if (!checkelement("reportForm","end_date","结束日期")) {
			return false;
		}
		if (start_date > end_date) {
			alert("起始日期不能大于结束日期！");
			return false;
		}
		if (daysBetween(start_date,end_date) > 90) {
			alert("查询时间跨度不能超过三个月！");
			return false;
		}
		document.reportForm.action = "/bfbposp/mcTermNumReport.do?method=queryAgentDailydev";
	}
	document.reportForm.submit();
}
//业务汇总报表查询
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
			alert("只能查询本月之前数据！");
			return false;
		}
		*/
		document.reportForm.action = "/bfbposp/transTotReport.do?method=queryAgent";
	} else {
		var start_date = document.reportForm.start_date.value;
		var end_date = document.reportForm.end_date.value;
		if (!checkelement("reportForm","start_date","开始日期")) {
			return false;
		}
		if (!checkelement("reportForm","end_date","结束日期")) {
			return false;
		}
		if (start_date > end_date) {
			alert("起始日期不能大于结束日期！");
			return false;
		}
		if (daysBetween(start_date,end_date) > 90) {
			alert("查询时间跨度不能超过三个月！");
			return false;
		}
		document.reportForm.action = "/bfbposp/transTotReport.do?method=queryAgentDaily";
	}
	document.reportForm.submit();
}
//业务汇总报表查询
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
			alert("只能查询本月之前数据！");
			return false;
		}
		*/
		document.reportForm.action = "/bfbposp/transTotReport.do?method=queryAgentdev";
	} else {
		var start_date = document.reportForm.start_date.value;
		var end_date = document.reportForm.end_date.value;
		if (!checkelement("reportForm","start_date","开始日期")) {
			return false;
		}
		if (!checkelement("reportForm","end_date","结束日期")) {
			return false;
		}
		if (start_date > end_date) {
			alert("起始日期不能大于结束日期！");
			return false;
		}
		if (daysBetween(start_date,end_date) > 90) {
			alert("查询时间跨度不能超过三个月！");
			return false;
		}
		document.reportForm.action = "/bfbposp/transTotReport.do?method=queryAgentDailydev";
	}
	document.reportForm.submit();
}
//监控参数设置表单认证
function checkMoniIniForm() {
	if(!checkelement("moniIniForm","mi_no","配置项")){
		return false;
	}
	if(!checkelement("moniIniForm","tmenu_id","交易类型")){
		return false;
	}
	if (!isNumber(document.all("mi_amount"),"交易额标准")) {
		return false;
	}
	return true;
}

//显示月份选择下拉框
function dispSelMon() {
	var months = new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
	var str = "";
	var monval = "";
	str += "<table border='0' width='100%'>";
	str += "<TR>";
	str += "<td valign='middle' align='left'>";
	str += "&nbsp;<SELECT id='selYear' name='selYear'>";
	for(i=2009;i<2026;i++)
		str += "<OPTION value='"+i+"'>"+i+" 年</OPTION>";
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

//初始化下拉框月份
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

//保留第一行表头,其余数据均删除
function removeRow() {
	var iRows = tb.rows.length;
	for (var i=0;i<iRows-1;i++) {
		tb.deleteRow(1);
	}
}

//5秒自动刷新一次,5秒取得一次数据
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
//终端密钥管理表单验证
function checkTermKeyForm() {
	var masterkey = document.all("masterkey").value;
	var masterkey_conf = document.all("masterkey_conf").value;
	if (document.all("mc_id") != null && !checkelement("termKeyForm","mc_id","用户编号")) {
		return false;
	}
	if (document.all("term_id4dcc") != null && !isLenNumOnly("termKeyForm","term_id4dcc","DCC终端号",19)) {
		return false;
	}
	if (document.all("term_id4cons") != null && !isLenNumOnly("termKeyForm","term_id4cons","POSV终端号",8)) {
		return false;
	}
	if (termKeyForm.term_audstate == null || termKeyForm.term_audstate.value != '2') {
		if(!checkLenElement("termKeyForm","masterkey","终端密钥",16) || !checkLenElement("termKeyForm","masterkey_conf","终端密钥",16)){
			return false;
		}
		if (masterkey != masterkey_conf) {
			alert("两次密钥输入不一致，请重新输入！");
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
//终端厂商管理表单验证
function checkTermCompForm() {	
	if (document.all("tc_compno") != null && !checkelement("termCompForm","tc_compno","机具品牌编号")) {
		return false;
	}	
	if (document.all("tc_compname") != null && !checkelement("termCompForm","tc_compname","机具品牌全称")) {
		return false;
	}	
	if (document.all("tc_compshortname") != null && !checkelement("termCompForm","tc_compshortname","机具品牌缩写")) {
		return false;
	}	
	if (document.all("tc_acct") != null && !checkelement("termCompForm","tc_acct","收入账户")) {
		return false;
	}
	return true;
}
//终端销售管理表单验证
function checkTermSaleForm() {	                             
	if (document.all("ts_termtype") != null && !checkelement("termSaleForm","ts_termtype","机具类型")) {
		return false;
	}
	if (document.all("tc_serialno") != null && !checkelement("termSaleForm","tc_serialno","机具品牌")) {
		return false;
	}
	if (document.all("ts_compdesc") != null && !checkelement("termSaleForm","ts_compdesc","机具类型描述")) {
		return false;
	}
	if (document.all("ts_termbaseamt") != null && !isnumbermor("termSaleForm","ts_termbaseamt","机具基础金额")) {
		return false;
	}	
	if (document.all("ts_termfix") != null && !isnumbermor("termSaleForm","ts_termfix","机具实际折扣率")) {
		return false;
	}	
	return true;
}
//终端类型管理表单验证
function checkTermTypeForm() {
	
	if (document.all("tt_termtypename") != null && !checkelement("termTypeForm","tt_termtypename","机具类型名称")) {
		return false;
	}	
	return true;
}
function checkProfitAcctForm(){	
	if (document.all("profit_acct") != null && !checkelement("profitAcctForm","profit_acct","分润账户")) {
		return false;
	}
	if (document.all("profit_type") != null && !checkelement("profitAcctForm","profit_type","分润类型")) {
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
		    	alert("分润方名称不能为空");
		    	profit_name[k].focus();
		    	profit_name[k].select();
		        return false;
		    }
		   /*
		    if(value_profitcode==null||value_profitcode==''){
		    	alert("上送银行分润方编号不能为空");
		    	profit_code[k].focus();
		    	profit_code[k].select();
		        return false;
		    }
		    */
		    if(input==null||input==''){
		    	alert("分润比例不能为空");
		    	feeobj[k].focus();
		        feeobj[k].select();
		        return false;
		    }
		    if (input!=null&&input!=''&&((pos1 != pos2)||(!checkChar(charset, input, true)))) {
		        alert ("分润比例只可包含数字和一个“.”！");
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
		alert("至少选一个分润方");
		return false;
	}
	if(num!=100){
			alert ("分润比例的总和必须为100");
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


/*-----添加div 动态显示分润发  焦点落入  显示前10个分润方  输入文字 动态显示分润方姓名  如果没有 则提示  ------ */
var preObjectProfitName=null;
var preObjectProfitid=null;
var prenrow=0;
with (document) {
	write('<div id="divAcctName" style="overflow:auto; overflow-x:hidden;POSITION:absolute;VISIBILITY:hidden;border:1px solid #B8DBED ridge;z-index:100;width=180px;height:200px;padding:1px;background-color:White;">');
	write('<span onclick="hiddenAcctDiv()" style="algin:right">关闭</span>')
	write("</div>");
	write('<div id="divAcctContent" style="OVERFLOW:hidden;display:none;">');
	write("</div>");
	
}
//主函数 obj 文本框 nrow 当前行所在的第几行 从0开始计数
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
//显示位置
function setLocationdiv(popCtrl){
	 var point = funGetXY(popCtrl);	 
	 with (divAcctName.style) {		
		left = point.x;
		top  = point.y+popCtrl.offsetHeight;
		//要判断下面的位置没有div的高度的时候 要显示在上方
		width = divAcctName.offsetWidth;		
		height = divAcctName.offsetHeight;
		visibility = 'visible';
	 }
}
//得到当前空间的位置
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
//根据分润方名称得到分润列表 并填入动态div中 
function showProfitMcListByAcctName(objprofit_name,divobj,nrow){
	var profit_name=objprofit_name.value;
	var divAcctContent=document.all("divAcctContent");
	divAcctContent.innerHTML="";
	profitMcManage.showProfitMcListByAcctName(profit_name,function(data){
		var str='';
		str='<span onclick="hiddenAcctDiv()" style="float:right;cursor:pointer">关闭</span>';
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
			//给每行隐藏的文本框赋值
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
/*-----添加div 动态显示分润发  焦点落入  显示前10个分润方  输入文字 动态显示分润方姓名  如果没有 则提示  ------ */

//分页函数跳转
function pagegotosubmit(urltxt){	
	pagenum=document.getElementById("txtPage").value;
	if(pagenum!=""&&pagenum!=''&&pagenum!='undefine'){
	  document.location=urltxt+"&currentPage="+pagenum;
	}
}

