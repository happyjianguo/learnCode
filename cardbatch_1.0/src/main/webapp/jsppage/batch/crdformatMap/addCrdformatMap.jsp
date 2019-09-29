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

	<title>��BINӳ������ҳ��</title>
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
		var iid   =   document.crdformatMapForm.iid.value;
			
// 		var obj1 = document.getElementById("func_flag"); 
// 		var index1 = obj1.selectedIndex;
// 		var func_flag = obj1.options[index1].value;	
		var func_flag=document.getElementsByName("func_flag")[0].value;
		
		if(func_flag=='1'){
// 			var obj = document.getElementById("iid_map"); 
// 			var index = obj.selectedIndex;
// 			var iid_map = obj.options[index].value;
			var iid_map=document.getElementsByName("iid_map")[0].value;

			if(iid_map==null||iid_map==''){
	 			alert("��ѡ��BINӳ�䣡");
				document.crdformatMapForm.iid_map.focus();
				return false;
			}
		}     	    
		document.crdformatMapForm.submit();   	    
	}
	function backTo(){
		window.location.href="<%=path%>/crdformatMap.do?method=getCrdformatMapList";
	}
</script>

<body>
	<html:form styleId="crdformatMapForm"
		action="/crdformatMap?method=addCrdformatMapInfo" method="post">
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
								style="width:28px; height:28px;  background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">

							</td>
							<td
								style="height:28px;  background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;��ǰλ�ã� �������� &gt; ���ӿ�BINӳ��
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
								��BIN
							</td>
							<td align="left" class="box2">
								<select name="iid" id="iid" onchange="checkCrdformatMapPK();">
									<option value=" ">����ѡ��</option>
									<logic:present name="kaBinList">
										<c:forEach items="${kaBinList}" var="item">
											<option value="${item.iid }">${item.iid_desc }</option>
										</c:forEach>
										
									</logic:present>
								</select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��BINӳ��
							</td>
							<td align="left" class="box2">
								<select name="iid_map" id="iid_map">
									<option value=" ">����ѡ��</option>
									<logic:present name="kaBinListTwo">
										<c:forEach items="${kaBinList }" var ="item">
										<option value="${item.iid }">${item.iid_desc }</option>
										</c:forEach>
										
									</logic:present>
								</select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								��Ӧ����
							</td>
							<td align="left" class="box2">
								<select id="func_flag" name="func_flag" onchange="checkCrdformatMapPK();">
									<option value="0">������������</option>
									<option value="1">����ͬ�����󶨵����⿨����</option>
								</select>
							</td>
						</tr>
						<tr>
							<td style="width: 110px;" align="right" class="box1">
								״̬
							</td>
							<td align="left" class="box2">
								<select name="isuse" id="isuse">
									<option value="1">����</option>
									<option value="0">ͣ��</option>
								</select>
							</td>
						</tr>

					</table>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="height: 23px;" align="center" class="box1">
								<input class="button" type="button" onclick="commit()"
									value="����" />
								&nbsp;&nbsp;&nbsp;
								<input class="button" type="button"  value="�ر�" onClick="javascript:window.close()">

							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>

</html:html>