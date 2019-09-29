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

	<title>�����޸��̻���Ӧ�ն���Ϣҳ��</title>
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
	function loadMy(){
		document.getElementsByName("address1")[0].value = "";
		document.getElementsByName("address2")[0].value = "";			
	}
	function commit() {
   		var checkstr="";
   		
        $("input:checkbox[name=crdproductCB]:checked").each(function(i){
        if(i!=0)    
         {    //����ѡ�ĸ����¼��id�γ��ַ��������ԡ������ָ�
          checkstr+=",";    
          }    
         checkstr += $(this).val();    
        }); 	
		if(checkstr==""){
			alert("������ѡ��һ���նˣ��ٵ����ť");
			return -1;
		} 
		
		//����
		var x_timezone=document.getElementsByName("address1")[0].value;
		//���ѳ���
		var obj = document.getElementsByName("address2")[0]; //��λid
		var index = obj.selectedIndex; // ѡ������
		var consump_category = obj.options[index].value; // ѡ��ֵ
		//�ն�״̬
		var obj1 = document.getElementsByName("mrchstat")[0]; //��λid
		var index1 = obj1.selectedIndex; // ѡ������
		var term_stat = obj1.options[index1].value; // ѡ��ֵ		
		//����ʱ��
		var disabled_date=document.getElementsByName("disabled_date")[0].value;		
		//ͣ��ʱ��
		var enable_date=document.getElementsByName("enable_date")[0].value;		
		
		if(trim(x_timezone)==''&&trim(consump_category)==''&&trim(term_stat)==''&&trim(disabled_date)==''&&trim(enable_date)==''){
			alert("�ն�״̬������ʱ�䡢ͣ��ʱ�䡢���ʺ����ѳ���������������һ�");
			document.forms["merchantForm"].elements["mrchstat"].focus();				
			return false;
		}
		
		if (trim(document.getElementsByName("address1")[0].value)!=''&&checkDouble("merchantForm", "address1", "�̻���Ӧ�ն˵ķ���")) {
			return false;
		}
	
		document.forms["merchantForm"].elements["address"].value=checkstr;			
		$("#btTJ").attr("disabled","true");
		
		document.merchantForm.submit();
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

	function trim(str){ //ɾ���������˵Ŀո�
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
	<html:form styleId="merchantForm"
		action="/merchant?method=modMrchTerminal"
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
								&nbsp;&nbsp;��ǰλ�ã� �̻��ն˹��� &gt; �����޸��̻���Ӧ�ն���Ϣ
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
								�̻���
							</td>
							<td align="left" class="box2">
								<html:text property="mrchno" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								�̻�����
							</td>
							<td align="left" class="box2">
								<html:text property="mrcht_name" readonly="true"></html:text>
							</td>
						</tr>					
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								ѡ���ն�
							</td>
							<td align="left" class="box2">
								<html:hidden property="address"/>
								<input type="checkbox" name="chk_all" onclick="selectAll();" />ȫѡ/ȡ��ȫѡ
								<br/>								
								<c:forEach var="list" items="${terminalList}"
									varStatus="index">
									<input type="checkbox" name="crdproductCB"
										value="${list.termcode}" />
									<span style="width: 350px;">
										�նˣ�${list.termcode}(���ʣ�${list.timezone}%,���ѳ���:${list.location})
									</span>
									<c:if test="${index.count%2==0}">
										<br>
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
								�ն�״̬
							</td>
							<td align="left" class="box2">
								<html:select property="mrchstat">
									<html:option value="">����ѡ��</html:option>
									<html:option value="0">����</html:option>
									<html:option value="1">ͣ��</html:option>
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
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����(��λ��%)
							</td>
							<td align="left" class="box2">
								<html:text property="address1" maxlength="16"></html:text>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								���ѳ���
							</td>
							<td align="left" class="box2">
								<html:select property="address2">
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


