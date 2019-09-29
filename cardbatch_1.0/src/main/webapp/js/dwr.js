loadJS = function(url) {
	var script = "<scr" + "ipt type='text/javascript' src='" + url + "'></scr"
			+ "ipt>";
	document.write(script);
}
loadJS("/dwr/util.js");
loadJS("/dwr/engine.js");
// 获取全部用户信息的远程调用
loadJS("/dwr/interface/roleManage.js");
loadJS("/dwr/interface/deptManage.js");
loadJS("/dwr/interface/ShellService.js");
loadJS("/dwr/interface/instManage.js");
loadJS("/dwr/interface/terminalManage.js");
loadJS("/dwr/interface/ShellServiceOfHousekeep.js");
loadJS("/dwr/interface/tBankInfoManage.js");
loadJS("/dwr/interface/tBranchInfoManage.js");
loadJS("/dwr/interface/sysParameterManage.js");
loadJS("/dwr/interface/CrdformatMapService.js");
loadJS("/dwr/interface/merchantInfoManage.js");
loadJS("/dwr/interface/terminalInfoManage.js");


// 获取所有角色信息
function getAllRoles() {
	roleName = document.getElementsByName("roleno")[0];
	roleName.add(new Option("－请选择－", ""));
	roleManage.getRoleList( function(data) {
		for (i = 0; i < data.length; i++) {
			roleName.add(new Option(data[i].rolename, data[i].roleno));
		}
	});
}

// 超级管理员用户组验证，如果选择超级管理员用户组，角色只能是超级管理员
function checkAdminDept() {
	
	var deptno_val = document.getElementsByName("deptno")[0].value;
	roleno = document.getElementsByName("roleno")[0];
	document.getElementsByName("roleno")[0].options.length = 0;
	roleno.innerHTML = "";
	roleno.add(new Option("－请选择－", ""));
	
	// if(deptno_val == "000000000"){
	// roleno.add(new Option("超级管理员","00"));
	//		
	// }else{	
	
	roleManage.getRoleListjs("", deptno_val, function(data) {		
		for (i = 0; i < data.length; i++) {			
			roleno.add(new Option(data[i].rolename, data[i].roleno));
		}
	});
	// }

}
// 超级管理员角色验证，如果选择超级管理员角色，用户组只能是超级管理员
function checkAdminRole() {
	var roleno_val = document.getElementsByName("roleno")[0].value;
	deptno = document.getElementsByName("deptno")[0];
	document.getElementsByName("deptno")[0].options.length = 0;
	if (roleno_val == "00") {

		deptno.add(new Option("超级管理员用户组", "000000000"));
	} else {
		deptManage.getDeptList("", function(data) {
			for (i = 0; i < data.length; i++) {
				deptno.add(new Option(data[i].deptname, data[i].deptno));
			}
		});
	}
}

// 获取所有用户组信息
function getAllDepts() {
	deptName = document.getElementsByName("dept_no")[0];
	deptName.add(new Option("－请选择－", ""));
	deptManage.getDeptList("", function(data) {
		for (i = 0; i < data.length; i++) {
			deptName.add(new Option(data[i].deptname, data[i].deptno));
		}
	});
}
// 获取所有用户组等级
function getLevel() {
	dept_level = document.getElementsByName("dept_level")[0];
	dept_level.add(new Option("－请选择－", ""));
	deptManage.getLevel( function(data) {
		for (i = 0; i < data.length; i++) {
			dept_level.add(new Option(data[i].dept_level, data[i].dept_level));
		}
	});
}
// 验证新增用户组 上级用户组是否存在
function getDeptByLevel() {
	flag = false;
	var dept_level = document.getElementsByName("dept_level")[0].value;
	var dept_upperno = document.getElementsByName("dept_upperno")[0].value;
	var dept_no = document.getElementsByName("deptno")[0].value;
	deptManage
			.getDeptByLevel(
					dept_level,
					dept_upperno,
					dept_no,
					function(data) {

						if (data != null && data.deptname != null) {
							if (data.result == "0") {
								document.getElementsByName("upperNameRight")[0].innerHTML = "验证成功！上级用户组名称："
										+ data.deptname
										+ "/"
										+ "上级用户组级别："
										+ data.dept_level;
								document.getElementsByName("upperNameError")[0].innerHTML = "";
								flag = true;
								return true;
							} else if (data.result == "1") {
								document.getElementsByName("upperNameRight")[0].innerHTML = "验证成功！该用户组为一级用户组，无上级用户组！";
								document.getElementsByName("upperNameError")[0].innerHTML = "";
								flag = true;
								return true;
							} else if (data.result == "-1") {
								document.getElementsByName("upperNameError")[0].innerHTML = "验证失败！您输入的级别需要比下级用户组高！";
								document.getElementsByName("upperNameRight")[0].innerHTML = "";
								document.deptForm.dept_level.value = "";
								flag = false;
								return false;
							} else if (data.result == "-2") {
								document.getElementsByName("upperNameError")[0].innerHTML = "验证失败！一级用户组已经存在！";
								document.getElementsByName("upperNameRight")[0].innerHTML = "";
								document.deptForm.dept_upperno.value = "";
								flag = false;
								return false;
							} else {
								document.getElementsByName("upperNameError")[0].innerHTML = "验证失败！您输入的用户组号不存在，或者用户组号不是上级！";
								document.getElementsByName("upperNameRight")[0].innerHTML = "";
								document.deptForm.dept_upperno.value = "";
								flag = false;
								return false;
							}
						} else {
							document.getElementsByName("upperNameError")[0].innerHTML = "验证失败！您输入的用户组号不存在，或者用户组号不是上级！";
							document.getElementsByName("upperNameRight")[0].innerHTML = "";
							document.deptForm.dept_upperno.value = "";
							flag = false;
							return false;
						}

					});
}

// 按终端级别查询用户组信息
function getDeptInfoByLevel() {
	var dept_level = document.getElementsByName("dept_level")[0].value;
	dept_upperno = document.getElementsByName("dept_upperno")[0];
	document.getElementsByName("dept_upperno")[0].options.length = 0;
	if (dept_level == "") {
		dept_upperno.add(new Option("－请选择－", ""));
	} else {
		deptManage.getDeptInfoByLevel(dept_level, function(data) {
			for (i = 0; i < data.length; i++) {
				dept_upperno.add(new Option(data[i].deptname,
						data[i].dept_upperno));
			}
		});
	}

}

function jstranspay(str) {
	bfbepos.transpay(str, function(data) {
		alert(data);
	});
}
function jstransquery(str) {
	bfbepos.transquery(str, function(data) {
		document.getElementsByName("logindata")[0].value=data;
	});
}        
function jstranslogin(str) {	
	bfbepos.translogin(str, function(data) {			
	 document.getElementsByName("logindata")[0].value=data;		
	});
}

//商户新增时根据机构号获取brncode
function getBrncode() {
	var inst_id_val = document.getElementsByName("inst_id")[0].value;
	brncode = document.getElementsByName("brncode")[0];
	document.getElementsByName("brncode")[0].options.length = 0;
	brncode.innerHTML = "";
	brncode.add(new Option("－请选择－", ""));
	instManage.getBrncodeJs(inst_id_val, function(data) {		
		for (i = 0; i < data.length; i++) {			
			brncode.add(new Option(data[i].descr, data[i].brncode));
		}
	});
	/**
	//根据机构号，获取对应的署名方
	contrsign = document.getElementsByName("contrsign")[0];
	document.all("contrsign").options.length = 0;
	contrsign.innerHTML = "";
	contrsign.add(new Option("－请选择－", ""));
	instManage.getOfficialJs(inst_id_val, function(data) {		
		for (i = 0; i < data.length; i++) {			
			contrsign.add(new Option(data[i].descr, data[i].official));
		}
	});
	
	official = document.getElementsByName("official")[0];
	document.all("official").options.length = 0;
	official.innerHTML = "";
	official.add(new Option("－请选择－", ""));
	instManage.getOfficialJs(inst_id_val, function(data) {		
		for (i = 0; i < data.length; i++) {			
			official.add(new Option(data[i].descr, data[i].official));
		}
	});
	*/
}
//根据currcode获取taxcode的可选项
function getTaxcode(){
	var currcode_val = document.getElementsByName("currcode")[0].value;
	taxcode = document.getElementsByName("taxcode")[0];
	document.getElementsByName("taxcode")[0].options.length = 0;
	taxcode.innerHTML = "";
	taxcode.add(new Option("－请选择－", ""));
	instManage.getTaxcodeJs(currcode_val, function(data) {
		if(data.length <= 0){
			alert("该ISO码无对应的税收编号！请重新选择");
		}else{
			for (i = 0; i < data.length; i++) {
				taxcode.add(new Option(data[i].descr, data[i].taxcode));
			}
		}
	});
}

//根据province(省份)获取city_no(城市)的可选项,以及与其关联的zone可选项
function getCity_no(){
	
	var province_val = document.getElementsByName("province")[0].value;
	city_no = document.getElementsByName("city_no")[0];
	document.getElementsByName("city_no")[0].options.length = 0;
	city_no.innerHTML = "";
	city_no.add(new Option("－请选择－", ""));
	instManage.getCityByFid(province_val, function(data) {
		for (i = 0; i < data.length; i++) {
			city_no.add(new Option(data[i].province_city, data[i].aid));
		}
	});
	getZone();
}

//根据city_no(城市)获取(区县)的可选项
function getZone(){
	var city_no_val = document.getElementsByName("city_no")[0].value;
	zone = document.getElementsByName("zone")[0];
	document.getElementsByName("zone")[0].options.length = 0;
	zone.innerHTML = "";
	zone.add(new Option("－请选择－", ""));
	instManage.getCityByFid(city_no_val, function(data) {
		for (i = 0; i < data.length; i++) {
			zone.add(new Option(data[i].province_city, data[i].aid));
		}
	});
}

//根据city_no(城市)获取(区县)的可选项
function getOfficial(){
	var official_val = document.getElementsByName("official")[0].value;
	official = document.getElementsByName("official")[0];
	document.getElementsByName("official")[0].options.length = 0;
	official.innerHTML = "";
	official.add(new Option("－请选择－", ""));
	instManage.getOfficialJs(official_val, function(data) {
		for (i = 0; i < data.length; i++) {
			official.add(new Option(data[i].descr, data[i].official));
		}
	});
}

function getMcaccxId(){
	var merchant_id = document.getElementsByName("merchantJS_id")[0].value;
	mcaccxid = document.getElementsByName("settle_mrch_acc_id")[0];
	document.getElementsByName("settle_mrch_acc_id")[0].options.length = 0;
	mcaccxid.innerHTML = "";
	mcaccxid.add(new Option("－请选择－", ""));
	terminalManage.getMrchAccxBeanList(merchant_id, function(data) {
		if(data.length <= 0){
			alert("该商户无对应的账户信息！请重新选择");
		}else{
			for (var i = 0; i < data.length; i++) {
				mcaccxid.add(new Option(data[i].name, data[i].id));
			}
		}
	});
}

function checkTermcode(){
	var termcode = document.getElementsByName("termcode")[0].value;
	if (termcode == "") {
		alert("请输入终端编号！");
		return -1;
	}
	terminalManage.checkTermCode(termcode, function(data) {
		if(data > 0){
			alert("终端号已经存在！请重新输入");
			document.getElementsByName("termcode")[0].focus();
			return -1;
		}else{
			return 0;
		}
	});
}


function checkAccNo(){
	var accno = document.getElementsByName("accno")[0].value;
	if (accno == "") {
		alert("请输入结算帐号");
		return -1;
	}
	instManage.checkAccNo(accno, function(data) {
		if(data > 0){
			alert("此结算账号已存在，请仔细核对");
			//document.getElementsByName("accno")[0].focus();
			return -1;
		}else{
			return 0;
		}
	});
}

function checkMrchNo(){
	var mrchno = document.getElementsByName("mrchno")[0].value;
	if (mrchno == "") {
		alert("请输入商户号！");
		return -1;
	}
	instManage.checkMrchNo(mrchno, function(data) {
		if(data > 0){
			alert("商户号已经存在！请重新输入");
//			document.getElementsByName("mrchno")[0].focus();
			document.getElementsByName("mrchno")[0].value="";
			return -1;
		}else{
			return 0;
		}
	});
}

function checkTermno(){
	var termno = document.getElementsByName("termno")[0].value;
	var merchant_id = document.getElementsByName("merchant_id")[0].value;
	if (merchant_id == "") {
		alert("请先选择终端所属商户！");
		return -1;
	}
	if (termno == "") {
		return -1;
	}
	terminalManage.checkTermno(merchant_id, termno, function(data) {
		if(data > 0){
			alert("该商户下的【终端序号】已经存在！请重新选择");
			document.getElementsByName("termno")[0].focus();
			return -1;
		}else{
			return 0;
		}
	});
}
function checkTBankInfoPK(){
	var bank_code = document.getElementsByName("bank_code")[0].value;
	if (bank_code == "") {
		alert("请输入银行编号！");
		return -1;
	}
	tBankInfoManage.checkTBankInfoPK(bank_code, function(data) {
		if(data ==1){
			alert("银行编号"+bank_code+"已经存在！请重新输入");
			document.getElementsByName("bank_code")[0].focus();
			return -1;
		}else{
			return 0;
		}
	});
}

function checkTBranchInfoPK(){
	var branch_code = document.getElementsByName("branch_code")[0].value;
	if (branch_code == "") {
		alert("请输入银行支行编号！");
		return -1;
	}
	tBranchInfoManage.checkTBranchInfoPK(branch_code, function(data) {
		if(data==1){
			alert("银行支行编号"+branch_code+"已经存在！请重新输入");
			document.getElementsByName("branch_code")[0].focus();
			return -1;
		}else{
			return 0;
		}
	});
}

function checkSysParameterPK(){
	var param_type = document.getElementsByName("param_type")[0].value;
	var param_name = document.getElementsByName("param_name")[0].value;
	if(param_name!=""&&param_type!=""){
		sysParameterManage.checkSysParameter(param_type,param_name,null, function(data) {
			if(data==1){
				alert("参数类型"+param_type+"已经存在参数名为"+param_name+"的参数！请重新输入");
				return -1;
			}else{
				return 0;
			}
		});		
	}
}

function checkSysParameterParentId(){
	var parent_id = document.getElementsByName("parent_id")[0].value;
	if (parent_id !="") {
		sysParameterManage.checkSysParameter(null,null,parent_id, function(data) {
			if(data==0){
				alert("父参数编号"+parent_id+"不存在！请重新输入");
				document.getElementsByName("parent_id")[0].focus();
				return -1;
			}else{
				return 0;
			}
		});
	}
}
function getIidMapStrByIid() {
	var str=document.getElementsByName("batchflag")[0].value;
	if(str!=null&&str!=""){
		CrdformatMapService.getIidMapStrByIid(str, function(data) {
			
		document.getElementsByName("srcstartflag")[0].value=data;
	});
	}
}   

function checkCrdformatMapPK() {
	var iid=document.getElementsByName("iid")[0].value;
	var func_flag=document.getElementsByName("func_flag")[0].value;

	if(iid!=null&&iid!=""&&func_flag!=null&&func_flag!=""){
		CrdformatMapService.checkCrdformatMapPK(iid,func_flag, function(data) {
		if(data=="1"){
			alert("主键重复!!!在该功能下已存在卡BIN("+iid+")对应的映射，请重新选择卡BIN或对应功能。");
		}
	});
	}
}   

function checkMerchantInfoPK(){
	var id = document.getElementsByName("id")[0].value;
	if (id == "") {
		alert("请输入商户编号！");
		return -1;
	}
	merchantInfoManage.checkMerchantInfoPK(id, function(data) {
		if(data ==1){
			alert("商户编号"+id+"已经存在！");
			return -1;
		}else{
			return 0;
		}
	});
}

//省查市
function getCity(){
	var province_val = document.getElementsByName("qprovince")[0].value;
	city_no = document.getElementsByName("qcity")[0];
	document.getElementsByName("qcity")[0].options.length = 0;
	city_no.innerHTML = "";
	city_no.add(new Option("－请选择－", ""));
	merchantInfoManage.getCityByFid(province_val, function(data) {
		for (i = 0; i < data.length; i++) {
			city_no.add(new Option(data[i].province_city, data[i].aid));
		}
	});
	getArea();
}

//市查区
function getArea(){
	var city_no_val = document.getElementsByName("qcity")[0].value;
	zone = document.getElementsByName("qarea")[0];
	document.getElementsByName("qarea")[0].options.length = 0;
	zone.innerHTML = "";
	zone.add(new Option("－请选择－", ""));
	merchantInfoManage.getCityByFid(city_no_val, function(data) {
		for (i = 0; i < data.length; i++) {
			zone.add(new Option(data[i].province_city, data[i].aid));
		}
	});
}

//省查市 新增页面
function getCityAdd(){
	var province_val = document.getElementsByName("province")[0].value;
	city_no = document.getElementsByName("city")[0];
	document.getElementsByName("city")[0].options.length = 0;
	city_no.innerHTML = "";
	city_no.add(new Option("－请选择－", ""));
	merchantInfoManage.getCityByFid(province_val, function(data) {
		for (i = 0; i < data.length; i++) {
			city_no.add(new Option(data[i].province_city, data[i].aid));
		}
	});
	getAreaAdd();
}

//市查区 新增页面
function getAreaAdd(){
	var city_no_val = document.getElementsByName("city")[0].value;
	zone = document.getElementsByName("area")[0];
	document.getElementsByName("area")[0].options.length = 0;
	zone.innerHTML = "";
	zone.add(new Option("－请选择－", ""));
	merchantInfoManage.getCityByFid(city_no_val, function(data) {
		for (i = 0; i < data.length; i++) {
			zone.add(new Option(data[i].province_city, data[i].aid));
		}
	});
}

function checkTerminalInfoPK(){
	var id = document.getElementsByName("id")[0].value;
	if (id == "") {
		alert("请输入终端编号！");
		return -1;
	}
	terminalInfoManage.checkTerminalInfoPK(id, function(data) {
		if(data ==1){
			alert("终端编号"+id+"已经存在！");
			return -1;
		}else{
			return 0;
		}
	});
}