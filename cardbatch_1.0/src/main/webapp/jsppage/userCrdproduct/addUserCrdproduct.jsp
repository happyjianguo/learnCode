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

	<title>�û�����Ʒӳ������ҳ��</title>
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
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
</head>
<script type="text/javascript">	
   	function commit() {  
		if (!checkelement("userCrdproductForm", "user_code", "�û�")) {
			return false;
		}   	   	 
   		var checkstr="";
        $("input:checkbox[name=crdproductCB]:checked'").each(function(i){
        if(i!=0)    
         {    //����ѡ�ĸ����¼��id�γ��ַ��������ԡ������ָ�
          checkstr+=",";    
          }    
         checkstr += $(this).val();    
        }); 
		if(checkstr==""){
			alert("������ѡ��һ��ٵ����ť");
			return -1;
		} 			
		document.forms["userCrdproductForm"].elements["crdproduct"].value=checkstr;
		document.userCrdproductForm.submit();   	    
	}
    var flag=true;
    function selectAll(){
 	   if(flag==true){
 		   $("input:checkbox[name=crdproductCB]'").attr("checked",'true');//ȫѡ
		   flag=false; 
	   }else{
		   $("input:checkbox[name=crdproductCB]'").removeAttr("checked");//ȡ��ȫѡ
		   flag=true; 
	   }  
    } 
</script>

<body>
	<html:form styleId="userCrdproductForm" action="/userCrdproduct?method=addUserCrdproduct" method="post">
		<table border="0" cellpadding="0" cellspacing="0" style="width: 100%; height: 99%;">
			<tr>
				<td align="center" valign="top">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="width: 28px; height: 10px;"></td>
						</tr>
						<tr>
							<td align="left"
								style="width:28px; height:28px;  background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
							</td>
							<td
								style="height:28px;  background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;��ǰλ�ã� �̻��ն���Ϣ���� &gt; �����û�����Ʒӳ��
							</td>
							<td
								style="width:7px; height:28px;  background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
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
								�û�
							</td>
							<td align="left" class="box2">
								<html:select property="user_code">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="userList">
										<html:optionsCollection name="userList"
											label="username" value="usercode" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								����Ʒ
							</td>
							<td align="left" class="box2">
								<html:hidden property="crdproduct"/>
								<input type="checkbox" name="chk_all" onclick="selectAll();" />ȫѡ/ȡ��ȫѡ
								<br/>								
								<c:forEach var="list" items="${cardProductList}"
									varStatus="index">
									<input type="checkbox" name="crdproductCB"
										value="${list.crdproduct}" />
									<span style="width: 200px;">${list.descr}</span>
									<c:if test="${index.count%4==0}">
										<br>
									</c:if>
								</c:forEach>
							</td>
						</tr>					
					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="height: 23px;" align="center" class="box1">
								<input class="button" type="button" onclick="commit()"
									value="����" />
								&nbsp;&nbsp;&nbsp;
								<input class="button" type="button" value="�ر�"
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