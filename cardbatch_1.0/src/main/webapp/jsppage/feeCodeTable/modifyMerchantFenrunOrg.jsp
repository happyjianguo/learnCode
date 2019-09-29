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

	<title>��������޸�ҳ��</title>
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
	function commit() {
		if (!checkelement("merchantFenrunOrgForm", "orgName", "�����������")) {
			return false;
		}
		/* if (!checkelement("merchantFenrunOrgForm", "orgBin", "��BIN")) {
			return false;
		} */
		if (!checkelement("merchantFenrunOrgForm", "orgPurposeAmt", "����������׷���")) {
			return false;
		}
		var reg = /^(100|100\.00|[1-9]?\d(\.\d\d?)?)$/;
		//var reg = /^([1-9]{1}[0-9]{0,1}|0|100)(.d{1,2}){0,1}$/;
		var orgRatio = document.getElementsByName("orgPurposeAmt")[0].value;
		if(!reg.exec(orgRatio)){
			alert("����������׷���������ٷֱȵ���ʽ��0.00~100.00%");
			return false;
		}
		
	    $("#btTJ").attr("disabled","true");	
		document.merchantFenrunOrgForm.submit();	
	}
	//����mrchnoSelJSQ��ȡ�����̻��������̻�
	function getMerchantBeanListJS(){
		var mrchnoSelJSQ = document.getElementsByName("iidSelJSQ")[0].value;
		//����̻������ǰ��ո��̻�����Ļ�ֻ��ѡ���̻�
		if(mrchnoSelJSQ!=null&&mrchnoSelJSQ!=''){
			merchantJS_id = document.getElementsByName("iidJS_id")[0];
			document.getElementsByName("iidJS_id")[0].options.length = 0;
			merchantJS_id.innerHTML = "";
			merchantJS_id.add(new Option("����ѡ��", ""));
			instManage.getCrdroutingByIid(mrchnoSelJSQ, function(data) {
				for (i = 0; i < data.length; i++) {
					merchantJS_id.add(new Option(data[i].iid+"����"+data[i].descr, data[i].iid));
				}
			});
		}else{
			merchantJS_id = document.getElementsByName("iidJS_id")[0];
			document.getElementsByName("iidJS_id")[0].options.length = 0;
			merchantJS_id.innerHTML = "";
			merchantJS_id.add(new Option("����ѡ��", ""));
		}
	}
	var varorgBin = "";
	function getiidJS_id(){
		var mrchnoSelJSQ = document.getElementsByName("iidJS_id")[0].value;
		if(mrchnoSelJSQ!=null&&mrchnoSelJSQ!=''){
			varorgBin = document.getElementsByName("orgBin")[0].value;
			if(varorgBin!=null&&varorgBin!=''){
				var arrOrgBin = varorgBin.split(",");// ��ÿ������(,)�����зֽ⡣
				var arrnum = arrOrgBin.length;
				if(arrnum > 9){
					alert("��BIN�������ܳ���10��");
				}else{
					for(var i=0;i<arrOrgBin.length;i++){
						if(mrchnoSelJSQ == arrOrgBin[i]){
							alert("��BIN�Ѵ��ڣ�����������");
							document.getElementsByName("iidSelJSQ")[0].value = "";
							document.getElementsByName("iidJS_id")[0].value = "";
							return false;
						}
					}
					varorgBin = varorgBin+","+mrchnoSelJSQ
				}
			}else{
				varorgBin = mrchnoSelJSQ
			}
			document.getElementsByName("iidSelJSQ")[0].value = "";
			document.getElementsByName("iidJS_id")[0].value = "";
			document.getElementsByName("orgBin")[0].value = varorgBin;
		}
	}
	function closeCardBin(){
		document.getElementsByName("orgBin")[0].value = "";
		varorgBin = "";
	}
</script>

<body>
	<html:form styleId="merchantFenrunOrgForm" action="/merchantFenrunOrg?method=modMerchantOrg"
		enctype="multipart/form-data" method="post">
		
		<html:hidden property="orgId" />
		
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
								&nbsp;&nbsp;��ǰλ�ã� ����������� &gt; �޸ķ������
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
							<td style="width: 110px;" align="right" class="box1">
								�����������
							</td>
							<td align="left" class="box2">
								<html:text property="orgName" maxlength="30"></html:text>
								<font color="red">*</font>&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								���뿨BIN
							</td>
							<td align="left" class="box2">
								<html:text property="iidSelJSQ" maxlength="11" onblur="getMerchantBeanListJS();" onkeyup="this.value=this.value.replace(/\D/g,'')" ></html:text>
								<html:select property="iidJS_id" onchange="getiidJS_id();" style="width: 220px;">
									<html:option value=" ">����ѡ��</html:option>
									<logic:present name="mrchJSList">
										<html:optionsCollection name="mrchJSList" label="descr" value="iid" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 120px;" align="right" class="box1">
								��BIN
							</td>
							<td align="left" class="box2">
								<html:text property="orgBin" style="width: 700px;" maxlength="250" readonly="true"></html:text>
								<input class="button" type="button"  value="�����BIN" onClick="closeCardBin();">
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����������׷���
							</td>
							<td align="left" class="box2">
								<html:text property="orgPurposeAmt" maxlength="6"></html:text>
								<font>%</font>&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�������״̬
							</td>
							<td align="left" class="box2">
								<html:select property="orgStat">
									<html:option value="0">����</html:option>
									<html:option value="1">����</html:option>
								</html:select>
								<font color="red">*</font>
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


