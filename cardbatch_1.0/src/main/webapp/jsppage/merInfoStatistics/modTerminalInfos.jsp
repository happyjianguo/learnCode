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

	<title>�̻���Ϣͳ��-�����޸��̻���Ϣ��Ӧ�ն���Ϣ</title>
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
	<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>

</head>
<script type="text/javascript">
	
	function loadMy(){
		
	}
	
	function commit() {
   		var checkstr="";
        $("input:checkbox[name=crdproductCB]:checked").each(function(i){
        	//����ѡ�ĸ����ն���Ϣ��id��,�ָ���ַ���
        	if(i != 0){
            	checkstr+=",";    
          	}    
         	checkstr += $(this).val();    
        }); 	
        if(checkstr==""){
			alert("������ѡ��һ���ն���Ϣ���ٵ�����°�ť��");
			return false;
		} 
        document.forms["merchantInfoForm"].elements["qid"].value=checkstr;	
        
        //�̻�װ����ַ
        var qprovince=document.getElementsByName("qprovince")[0].value;
        //���̼���̨��ַ
        var qcity=document.getElementsByName("qcity")[0].value;
        //POS�ͺ�
		var obj = document.getElementsByName("qarea")[0];  //��λname
		var index = obj.selectedIndex;  //ѡ������
		var qarea = obj.options[index].value;  //ѡ��ֵ
        
        //POS����
		var obj1 = document.getElementsByName("qtype")[0];  //��λname
		var index1 = obj1.selectedIndex;  //ѡ������
		var qtype = obj1.options[index1].value;  //ѡ��ֵ
        
        //����POS�ֻ���
        var area=document.getElementsByName("area")[0].value;
        if (trim(area) != '') {
        	var isInteger = RegExp(/^[0-9]+$/);
        	if ( !isInteger.test(trim(area)) ) {
        		alert ("����POS�ֻ��ű���Ϊ���֡�");
        		document.forms["merchantInfoForm"].elements["area"].focus();
        	    document.forms["merchantInfoForm"].elements["area"].value = "";
                return false;
        	}
		}
        //POS��S/N��
        var fullname=document.getElementsByName("fullname")[0].value;
        //��װ����
        var type=document.getElementsByName("type")[0].value;
        //ͣ������
        var qname=document.getElementsByName("qname")[0].value;
        //��������
        var qfullname=document.getElementsByName("qfullname")[0].value;
        //��ϵ��
        var status=document.getElementsByName("status")[0].value;
        //�ŵ�绰
       var province=document.getElementsByName("province")[0].value;
        if (trim(province) != '') {
        	var isInteger = RegExp(/^[0-9]+$/);
        	if ( !isInteger.test(trim(province)) ) {
        		alert ("�ŵ�绰����Ϊ���֡�");
        		document.forms["merchantInfoForm"].elements["province"].focus();
        	    document.forms["merchantInfoForm"].elements["province"].value = "";
                return false;
        	}
		}
        //POS��״̬
		var obj2 = document.getElementsByName("qstatus")[0];  //��λname
		var index2 = obj2.selectedIndex;  //ѡ������
		var qstatus = obj2.options[index2].value;  //ѡ��ֵ
        
        //POSѺ��Ԫ��
        var city=document.getElementsByName("city")[0].value;
        if (trim(city) != '') {
        	var isInteger = RegExp(/^[0-9]+$/);
        	if ( !isInteger.test(trim(city)) ) {
        		alert ("POSѺ��Ԫ������Ϊ���֡�");
        		document.forms["merchantInfoForm"].elements["city"].focus();
        	    document.forms["merchantInfoForm"].elements["city"].value = "";
                return false;
        	}
		}
				
		if(trim(qprovince) == '' && trim(qcity) == '' && trim(qarea) == ''
		&& trim(qtype) == '' && trim(area) == '' && trim(fullname) == ''
		&& trim(type) == '' && trim(qname) == '' && trim(qfullname) == ''
		&& trim(status) == '' && trim(province) == '' && trim(qstatus) == ''
		&& trim(city) == ''){
			alert("����������һ����Ҫ�����޸ĵ��ն���Ϣ�");
			document.forms["merchantInfoForm"].elements["qprovince"].focus();				
			return false;
		}
		$("#btTJ").attr("disabled","true");
		document.merchantInfoForm.submit();
	}
	
	//ɾ���������˵Ŀո�
	function trim(str){
	    return str.replace(/(^\s*)|(\s*$)/g, "");
	} 

    var flag=true;
    function selectAll(){
 	   if(flag==true){
 		   $("input:checkbox[name=crdproductCB]").attr("checked","true");//ȫѡ
		   flag=false; 
	   }else{
		   $("input:checkbox[name=crdproductCB]").removeAttr("checked");//ȡ��ȫѡ
		   flag=true; 
	   }  
    } 	 
</script>

<body onload="loadMy();">
	<html:form styleId="merchantInfoForm"
		action="/merchantInfo?method=modTerminalInfos"
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
								&nbsp;&nbsp;��ǰλ�ã� �̻���Ϣͳ�ƹ��� &gt; �����޸��̻���Ϣ��Ӧ�ն���Ϣ
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
								�̻����
							</td>
							<td align="left" class="box2">
								<html:text property="id" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻�����
							</td>
							<td align="left" class="box2">
								<html:text property="name" readonly="true"></html:text>
							</td>
						</tr>					
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								ѡ���ն�
							</td>
							<td align="left" class="box2">
								<html:hidden property="qid"/>
								<input type="checkbox" name="chk_all" onclick="selectAll();" />ȫѡ/ȡ��ȫѡ
								<br/>								
								<c:forEach var="list" items="${terminalInfoList }" varStatus="index">
									<input type="checkbox" name="crdproductCB" value="${list.id }" />
									<span style="width: 350px;">
										�նˣ�${list.id}
									</span>
									<c:if test="${index.count%2==0}">
										<br />
									</c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td colspan="2" class="box1" align="center">
								Ҫ�����޸ĵ��ն���Ϣ
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻�װ����ַ
							</td>
							<td align="left" class="box2">
								<html:text property="qprovince" maxlength="66"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								���̼���̨��ַ
							</td>
							<td align="left" class="box2">
								<html:text property="qcity" maxlength="66"></html:text>
							</td>
						</tr>						
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POS�ͺ�
							</td>
							<td align="left" class="box2">
								<html:select property="qarea">
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
								<html:select property="qtype">
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
								<html:text property="area" maxlength="16" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
							</td>
						</tr>				
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POS��S/N��
							</td>
							<td align="left" class="box2">
								<html:text property="fullname" maxlength="16"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��װ����
							</td>
							<td align="left" class="box2">
								<html:text property="type" maxlength="8"
									onclick="WdatePicker({dateFmt:'yyyyMMdd'});"></html:text>								
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								ͣ������
							</td>
							<td align="left" class="box2">
								<html:text property="qname" maxlength="8"
									onclick="WdatePicker({dateFmt:'yyyyMMdd'});"></html:text>
							</td>
						</tr>		
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��������
							</td>
							<td align="left" class="box2">
								<html:text property="qfullname" maxlength="8"
									onclick="WdatePicker({dateFmt:'yyyyMMdd'});"></html:text>
							</td>
						</tr>	
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��ϵ��
							</td>
							<td align="left" class="box2">
								<html:text property="status" maxlength="16"></html:text>
							</td>
						</tr>					
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�ŵ�绰
							</td>
							<td align="left" class="box2">
								<html:text property="province" maxlength="16" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								POS��״̬
							</td>
							<td align="left" class="box2">
								<html:select property="qstatus">
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
								<html:text property="city" maxlength="50" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
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


