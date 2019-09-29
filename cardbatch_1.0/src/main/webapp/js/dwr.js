loadJS = function(url) {
	var script = "<scr" + "ipt type='text/javascript' src='" + url + "'></scr"
			+ "ipt>";
	document.write(script);
}
loadJS("/dwr/util.js");
loadJS("/dwr/engine.js");
// ��ȡȫ���û���Ϣ��Զ�̵���
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


// ��ȡ���н�ɫ��Ϣ
function getAllRoles() {
	roleName = document.getElementsByName("roleno")[0];
	roleName.add(new Option("����ѡ��", ""));
	roleManage.getRoleList( function(data) {
		for (i = 0; i < data.length; i++) {
			roleName.add(new Option(data[i].rolename, data[i].roleno));
		}
	});
}

// ��������Ա�û�����֤�����ѡ�񳬼�����Ա�û��飬��ɫֻ���ǳ�������Ա
function checkAdminDept() {
	
	var deptno_val = document.getElementsByName("deptno")[0].value;
	roleno = document.getElementsByName("roleno")[0];
	document.getElementsByName("roleno")[0].options.length = 0;
	roleno.innerHTML = "";
	roleno.add(new Option("����ѡ��", ""));
	
	// if(deptno_val == "000000000"){
	// roleno.add(new Option("��������Ա","00"));
	//		
	// }else{	
	
	roleManage.getRoleListjs("", deptno_val, function(data) {		
		for (i = 0; i < data.length; i++) {			
			roleno.add(new Option(data[i].rolename, data[i].roleno));
		}
	});
	// }

}
// ��������Ա��ɫ��֤�����ѡ�񳬼�����Ա��ɫ���û���ֻ���ǳ�������Ա
function checkAdminRole() {
	var roleno_val = document.getElementsByName("roleno")[0].value;
	deptno = document.getElementsByName("deptno")[0];
	document.getElementsByName("deptno")[0].options.length = 0;
	if (roleno_val == "00") {

		deptno.add(new Option("��������Ա�û���", "000000000"));
	} else {
		deptManage.getDeptList("", function(data) {
			for (i = 0; i < data.length; i++) {
				deptno.add(new Option(data[i].deptname, data[i].deptno));
			}
		});
	}
}

// ��ȡ�����û�����Ϣ
function getAllDepts() {
	deptName = document.getElementsByName("dept_no")[0];
	deptName.add(new Option("����ѡ��", ""));
	deptManage.getDeptList("", function(data) {
		for (i = 0; i < data.length; i++) {
			deptName.add(new Option(data[i].deptname, data[i].deptno));
		}
	});
}
// ��ȡ�����û���ȼ�
function getLevel() {
	dept_level = document.getElementsByName("dept_level")[0];
	dept_level.add(new Option("����ѡ��", ""));
	deptManage.getLevel( function(data) {
		for (i = 0; i < data.length; i++) {
			dept_level.add(new Option(data[i].dept_level, data[i].dept_level));
		}
	});
}
// ��֤�����û��� �ϼ��û����Ƿ����
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
								document.getElementsByName("upperNameRight")[0].innerHTML = "��֤�ɹ����ϼ��û������ƣ�"
										+ data.deptname
										+ "/"
										+ "�ϼ��û��鼶��"
										+ data.dept_level;
								document.getElementsByName("upperNameError")[0].innerHTML = "";
								flag = true;
								return true;
							} else if (data.result == "1") {
								document.getElementsByName("upperNameRight")[0].innerHTML = "��֤�ɹ������û���Ϊһ���û��飬���ϼ��û��飡";
								document.getElementsByName("upperNameError")[0].innerHTML = "";
								flag = true;
								return true;
							} else if (data.result == "-1") {
								document.getElementsByName("upperNameError")[0].innerHTML = "��֤ʧ�ܣ�������ļ�����Ҫ���¼��û���ߣ�";
								document.getElementsByName("upperNameRight")[0].innerHTML = "";
								document.deptForm.dept_level.value = "";
								flag = false;
								return false;
							} else if (data.result == "-2") {
								document.getElementsByName("upperNameError")[0].innerHTML = "��֤ʧ�ܣ�һ���û����Ѿ����ڣ�";
								document.getElementsByName("upperNameRight")[0].innerHTML = "";
								document.deptForm.dept_upperno.value = "";
								flag = false;
								return false;
							} else {
								document.getElementsByName("upperNameError")[0].innerHTML = "��֤ʧ�ܣ���������û���Ų����ڣ������û���Ų����ϼ���";
								document.getElementsByName("upperNameRight")[0].innerHTML = "";
								document.deptForm.dept_upperno.value = "";
								flag = false;
								return false;
							}
						} else {
							document.getElementsByName("upperNameError")[0].innerHTML = "��֤ʧ�ܣ���������û���Ų����ڣ������û���Ų����ϼ���";
							document.getElementsByName("upperNameRight")[0].innerHTML = "";
							document.deptForm.dept_upperno.value = "";
							flag = false;
							return false;
						}

					});
}

// ���ն˼����ѯ�û�����Ϣ
function getDeptInfoByLevel() {
	var dept_level = document.getElementsByName("dept_level")[0].value;
	dept_upperno = document.getElementsByName("dept_upperno")[0];
	document.getElementsByName("dept_upperno")[0].options.length = 0;
	if (dept_level == "") {
		dept_upperno.add(new Option("����ѡ��", ""));
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

//�̻�����ʱ���ݻ����Ż�ȡbrncode
function getBrncode() {
	var inst_id_val = document.getElementsByName("inst_id")[0].value;
	brncode = document.getElementsByName("brncode")[0];
	document.getElementsByName("brncode")[0].options.length = 0;
	brncode.innerHTML = "";
	brncode.add(new Option("����ѡ��", ""));
	instManage.getBrncodeJs(inst_id_val, function(data) {		
		for (i = 0; i < data.length; i++) {			
			brncode.add(new Option(data[i].descr, data[i].brncode));
		}
	});
	/**
	//���ݻ����ţ���ȡ��Ӧ��������
	contrsign = document.getElementsByName("contrsign")[0];
	document.all("contrsign").options.length = 0;
	contrsign.innerHTML = "";
	contrsign.add(new Option("����ѡ��", ""));
	instManage.getOfficialJs(inst_id_val, function(data) {		
		for (i = 0; i < data.length; i++) {			
			contrsign.add(new Option(data[i].descr, data[i].official));
		}
	});
	
	official = document.getElementsByName("official")[0];
	document.all("official").options.length = 0;
	official.innerHTML = "";
	official.add(new Option("����ѡ��", ""));
	instManage.getOfficialJs(inst_id_val, function(data) {		
		for (i = 0; i < data.length; i++) {			
			official.add(new Option(data[i].descr, data[i].official));
		}
	});
	*/
}
//����currcode��ȡtaxcode�Ŀ�ѡ��
function getTaxcode(){
	var currcode_val = document.getElementsByName("currcode")[0].value;
	taxcode = document.getElementsByName("taxcode")[0];
	document.getElementsByName("taxcode")[0].options.length = 0;
	taxcode.innerHTML = "";
	taxcode.add(new Option("����ѡ��", ""));
	instManage.getTaxcodeJs(currcode_val, function(data) {
		if(data.length <= 0){
			alert("��ISO���޶�Ӧ��˰�ձ�ţ�������ѡ��");
		}else{
			for (i = 0; i < data.length; i++) {
				taxcode.add(new Option(data[i].descr, data[i].taxcode));
			}
		}
	});
}

//����province(ʡ��)��ȡcity_no(����)�Ŀ�ѡ��,�Լ����������zone��ѡ��
function getCity_no(){
	
	var province_val = document.getElementsByName("province")[0].value;
	city_no = document.getElementsByName("city_no")[0];
	document.getElementsByName("city_no")[0].options.length = 0;
	city_no.innerHTML = "";
	city_no.add(new Option("����ѡ��", ""));
	instManage.getCityByFid(province_val, function(data) {
		for (i = 0; i < data.length; i++) {
			city_no.add(new Option(data[i].province_city, data[i].aid));
		}
	});
	getZone();
}

//����city_no(����)��ȡ(����)�Ŀ�ѡ��
function getZone(){
	var city_no_val = document.getElementsByName("city_no")[0].value;
	zone = document.getElementsByName("zone")[0];
	document.getElementsByName("zone")[0].options.length = 0;
	zone.innerHTML = "";
	zone.add(new Option("����ѡ��", ""));
	instManage.getCityByFid(city_no_val, function(data) {
		for (i = 0; i < data.length; i++) {
			zone.add(new Option(data[i].province_city, data[i].aid));
		}
	});
}

//����city_no(����)��ȡ(����)�Ŀ�ѡ��
function getOfficial(){
	var official_val = document.getElementsByName("official")[0].value;
	official = document.getElementsByName("official")[0];
	document.getElementsByName("official")[0].options.length = 0;
	official.innerHTML = "";
	official.add(new Option("����ѡ��", ""));
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
	mcaccxid.add(new Option("����ѡ��", ""));
	terminalManage.getMrchAccxBeanList(merchant_id, function(data) {
		if(data.length <= 0){
			alert("���̻��޶�Ӧ���˻���Ϣ��������ѡ��");
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
		alert("�������ն˱�ţ�");
		return -1;
	}
	terminalManage.checkTermCode(termcode, function(data) {
		if(data > 0){
			alert("�ն˺��Ѿ����ڣ�����������");
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
		alert("����������ʺ�");
		return -1;
	}
	instManage.checkAccNo(accno, function(data) {
		if(data > 0){
			alert("�˽����˺��Ѵ��ڣ�����ϸ�˶�");
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
		alert("�������̻��ţ�");
		return -1;
	}
	instManage.checkMrchNo(mrchno, function(data) {
		if(data > 0){
			alert("�̻����Ѿ����ڣ�����������");
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
		alert("����ѡ���ն������̻���");
		return -1;
	}
	if (termno == "") {
		return -1;
	}
	terminalManage.checkTermno(merchant_id, termno, function(data) {
		if(data > 0){
			alert("���̻��µġ��ն���š��Ѿ����ڣ�������ѡ��");
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
		alert("���������б�ţ�");
		return -1;
	}
	tBankInfoManage.checkTBankInfoPK(bank_code, function(data) {
		if(data ==1){
			alert("���б��"+bank_code+"�Ѿ����ڣ�����������");
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
		alert("����������֧�б�ţ�");
		return -1;
	}
	tBranchInfoManage.checkTBranchInfoPK(branch_code, function(data) {
		if(data==1){
			alert("����֧�б��"+branch_code+"�Ѿ����ڣ�����������");
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
				alert("��������"+param_type+"�Ѿ����ڲ�����Ϊ"+param_name+"�Ĳ���������������");
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
				alert("���������"+parent_id+"�����ڣ�����������");
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
			alert("�����ظ�!!!�ڸù������Ѵ��ڿ�BIN("+iid+")��Ӧ��ӳ�䣬������ѡ��BIN���Ӧ���ܡ�");
		}
	});
	}
}   

function checkMerchantInfoPK(){
	var id = document.getElementsByName("id")[0].value;
	if (id == "") {
		alert("�������̻���ţ�");
		return -1;
	}
	merchantInfoManage.checkMerchantInfoPK(id, function(data) {
		if(data ==1){
			alert("�̻����"+id+"�Ѿ����ڣ�");
			return -1;
		}else{
			return 0;
		}
	});
}

//ʡ����
function getCity(){
	var province_val = document.getElementsByName("qprovince")[0].value;
	city_no = document.getElementsByName("qcity")[0];
	document.getElementsByName("qcity")[0].options.length = 0;
	city_no.innerHTML = "";
	city_no.add(new Option("����ѡ��", ""));
	merchantInfoManage.getCityByFid(province_val, function(data) {
		for (i = 0; i < data.length; i++) {
			city_no.add(new Option(data[i].province_city, data[i].aid));
		}
	});
	getArea();
}

//�в���
function getArea(){
	var city_no_val = document.getElementsByName("qcity")[0].value;
	zone = document.getElementsByName("qarea")[0];
	document.getElementsByName("qarea")[0].options.length = 0;
	zone.innerHTML = "";
	zone.add(new Option("����ѡ��", ""));
	merchantInfoManage.getCityByFid(city_no_val, function(data) {
		for (i = 0; i < data.length; i++) {
			zone.add(new Option(data[i].province_city, data[i].aid));
		}
	});
}

//ʡ���� ����ҳ��
function getCityAdd(){
	var province_val = document.getElementsByName("province")[0].value;
	city_no = document.getElementsByName("city")[0];
	document.getElementsByName("city")[0].options.length = 0;
	city_no.innerHTML = "";
	city_no.add(new Option("����ѡ��", ""));
	merchantInfoManage.getCityByFid(province_val, function(data) {
		for (i = 0; i < data.length; i++) {
			city_no.add(new Option(data[i].province_city, data[i].aid));
		}
	});
	getAreaAdd();
}

//�в��� ����ҳ��
function getAreaAdd(){
	var city_no_val = document.getElementsByName("city")[0].value;
	zone = document.getElementsByName("area")[0];
	document.getElementsByName("area")[0].options.length = 0;
	zone.innerHTML = "";
	zone.add(new Option("����ѡ��", ""));
	merchantInfoManage.getCityByFid(city_no_val, function(data) {
		for (i = 0; i < data.length; i++) {
			zone.add(new Option(data[i].province_city, data[i].aid));
		}
	});
}

function checkTerminalInfoPK(){
	var id = document.getElementsByName("id")[0].value;
	if (id == "") {
		alert("�������ն˱�ţ�");
		return -1;
	}
	terminalInfoManage.checkTerminalInfoPK(id, function(data) {
		if(data ==1){
			alert("�ն˱��"+id+"�Ѿ����ڣ�");
			return -1;
		}else{
			return 0;
		}
	});
}