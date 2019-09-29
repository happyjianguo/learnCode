<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*,cn.yufu.posp.terminalmanager.domain.model.*"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>�ն����򿨿�BIN����</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script language="javascript">
	
	function saveClick()
	{
		var obj = document.forms[0].merchantList;
		var options = obj.options;
		document.forms[0].merchantIds.value="";
		for(var i=0,len=options.length;i<len;i++){
		    var opt = options[i];
		    if(i==len-1){
		    	document.forms[0].merchantIds.value =document.forms[0].merchantIds.value+ opt.value;
		    }else{
		    	document.forms[0].merchantIds.value =document.forms[0].merchantIds.value+ opt.value+",";
			}
		}
		if(document.forms[0].merchantIds.value==""){
			alert("����Ӷ�Ӧ�նˡ�");
			return false;		
		}
		document.forms[0].method.value="createItem";
		return validateEdcCardBinAreaForm(document.forms[0]);
	}
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	</script>
	<script type="text/javascript" language="javascript">
	var req;
	function init() {
		if(window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	function leavetext() {
		if ("" == document.forms[0].merchantId.value)
	    {
		    //alert("�̻���Ų���Ϊ�գ����������룡");
		    //form1.merchantId.focus()
		    return false;
		}
		if (document.forms[0].merchantId.value.length !=15)
		{
		    alert("�̻���ű���Ϊʮ��λ�����������룡");
		   // document.forms[0].merchantId.focus();
		    return false;
		}
		init();
		var url = "edcCardBinArea.do?method=getTerminalListByMerchantId&merchantId=" + escape(document.forms[0].merchantId.value)+"&cardBin="+escape(document.forms[0].cardBin.value);
		req.open("POST", url, true);
		req.onreadystatechange = callback;
		req.send(null);
	}
	function callback() {
		if(4 == req.readyState) {
			if(200 == req.status) {
				if(req.responseText==""||req.responseText==null){
					alert("��ȷ���̻��Ƿ���ڣ�");
					document.forms[0].merchantId.value ="";
				}else{
					document.forms[0].terminalId.options.length = 0;
					var str = req.responseText;
					var areaList = str.split("|");
					if (areaList.length > 1) {
						document.forms[0].terminalId.options.add(new Option(
								"--��ѡ��--", ""));
						for (var i = 0; i < areaList.length-1; i++) {
							document.forms[0].terminalId.options.add(new Option(
									areaList[i].RTrim(), areaList[i].RTrim()));
						}
					}					
				}			
			}
		}
	}
	function setMerchantList(){
		
		if ("" == document.forms[0].merchantId.value)
	    {
		    alert("�̻���Ų���Ϊ�գ����������룡");
		    document.forms[0].merchantId.focus()
		    return false;
		}
		//��ȡ�ն�������ѡ��ֵ
		
		var obj = document.getElementsByName("terminalId")[0]; 
		var index = obj.selectedIndex;
		
		var binvalue = obj.options[index].value;			
		if(binvalue == ""){
			alert("��ѡ���ն�");
			return false;
		}
		
		var objmer = document.forms[0].merchantList;
		
		var options = objmer.options;

		for(var i=0,len=options.length;i<len;i++){
		    var opt = options[i];
		    if(opt.value==binvalue){
		    	alert('�ն��Ѵ����б�');
		    	return false;
			}
		}
		
		var opt = document.createElement ("option");
	    opt.value = binvalue.RTrim();
	    opt.innerText = binvalue.RTrim();
	    document.forms[0].merchantList.appendChild (opt);
	}
	function removeMerchantList(){
		var obj = document.forms[0].merchantList;
		var options = obj.options;
		for(var i=options.length-1;i>=0;i--){
		    var opt = options[i];
		    if(opt.selected){
			    options.remove(i);
		    }
		}
	}
	//ɾ������ո�
	String.prototype.RTrim   =   function(){   
		return   this.replace(/(\s*$)/g,"");   
	}

	function findCardBinArea() {
		var url = "cardBinArea.do?method=queryAll&model=sel&random="+ Math.random();
		var iWidth = 1046; //�������ڵĿ��;
		var iHeight = 500; //�������ڵĸ߶�;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //��ô��ڵĴ�ֱλ��;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //��ô��ڵ�ˮƽλ��;
		var winSize = 'height='
				+ iHeight
				+ ',width='
				+ iWidth
				+ ',top='
				+ iTop
				+ ',left='
				+ iLeft
				+ ',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no';
		window.open(url, "", winSize);
	}

	function setCardBinArea(cardBin) {
		document.forms[0].cardBin.value = cardBin;
	}
</script>
</head>
<shiro:lacksPermission name="posp:edcCardBinArea:add">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:edcCardBinArea:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle"> 
				�����ն����򿨿�BIN
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="edcCardBinAreaForm" />
				<html:errors />
				<html:form action="/edcCardBinArea">
					<html:hidden property="method" value="createItem" />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								��BIN:
							</td>
							<td class="table2_td">
								<html:text property="cardBin" size="30" maxlength="9" onclick="findCardBinArea();" readonly="true"/>
								<input type="button" value="ѡ��" onclick="findCardBinArea();"><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�̻���:
							</td>
							<td class="table2_td">
								<input type="text" name="merchantId" size="30" maxlength="15" onblur="leavetext()" onkeyup="this.value=this.value.replace(/\D/g,'');"/>
								<html:hidden property="merchantIds" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�ն�:
							</td>
							<td class="table2_td">
								<html:select property="terminalId" style="width:195px;">
								</html:select>
								<input type="button" value="���" onclick="setMerchantList();">
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�ն��б�:
							</td>
							<td class="table2_td">
								<select multiple="multiple" name="merchantList" size="8" style="width: 300px;">
									
								</select><input type="button" value="�Ƴ�" onclick="removeMerchantList();"><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								״̬:
							</td>
							<td class="table2_td">
								<html:select property="status" style="width:195px;" value="">
									<html:option value="1">��Ч</html:option>
									<html:option value="0">��Ч</html:option>									
								</html:select><font color="red">*</font>
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
					
				</html:form>

			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>



