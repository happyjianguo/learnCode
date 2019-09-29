<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
	String path = request.getContextPath();
	List<String> deptList  = (List<String>)request.getAttribute("deptList");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>管理员增加页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
	<script type="text/javascript" src="<%=path%>/js/textselect.js"></script>
	<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dwr.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
	
</head>
<script language="javascript">
$(function(){
	var deptList = "<%=deptList%>";
	var splitDeptList = deptList.substr(1,deptList.length-2).split(",");
	var deptNumber = splitDeptList.length;
	for(var i=0;i<deptNumber;i++){
		var dept = splitDeptList[i];
		var dept_no = dept.split("|")[0];
		var dept_name = dept.split("|")[1];
		//alert(dept_no+":"+dept_name);
		document.getElementById("deptno").add(new Option(dept_name,dept_no));
	}
});
	window.history.forward(1);
	function commit(){
		var usercode = document.userForm.usercode.value;
		var username = document.userForm.username.value;
		var passwd = document.userForm.passwd.value;
		var validdays = document.userForm.validdays.value;
		var roleno = document.userForm.roleno.value;
		var deptno = document.userForm.deptno.value;
		var fax = document.userForm.fax.value;
		var phone = document.userForm.phone.value;
		var mac = document.userForm.mac.value;
		if(trim(usercode) == ""){
			alert("请输入管理员编号！");
			document.userForm.usercode.focus();
			return false;
		}
		if(!checkGAndCode(trim(usercode))){
			alert("管理员编号只能输入数字或字母！");
			document.userForm.usercode.focus();
			document.userForm.usercode.select();
			return false;
		}
		if(trim(username) == ""){
			alert("请输入管理员名称！");
			document.userForm.username.focus();
			return false;
		}
		if(trim(deptno) == ""){
			alert("请选择管理员所属用户组！");
			document.userForm.deptno.focus();
			return false;
		}
		if(trim(roleno) == ""){
			alert("请选择管理员所属角色！");
			document.userForm.roleno.focus();
			return false;
		}
		if(fax != '' && !isnumberonly("userForm","fax","传真格式")){
			return false;
		}
		if(phone != '' && !isnumberonly("userForm","phone","电话格式")){
			return false;
		}
		<%//判断validdays必须为数字%>
		if(trim(validdays) == ""){
			alert("密码有效期不能为空！请重新输入！");
			document.userForm.validdays.focus();
			return false;
		}
		if(validdays == "永不过期"){
			document.userForm.validdays.value = "-1";
		}
		else if(!isnumberonly("userForm","validdays","密码有效期")){
			return false;
		}
		
		
		document.userForm.passwd.value = MD5(passwd);<%//对管理员密码进行MD5加密%>
		var isMac = $("#isMac").val();
		if(isMac=="0"){
			var macInput = $("#mac").val();
			if(null==macInput || macInput==""){
				alert("您未绑定MAC地址");
				return false;
			}
		}
		document.userForm.submit();
	}
	
	function showMacInput(){
		var macInput = $("#macInput");
		var isMac = $("#isMac").val();
		if(isMac=="0")
			macInput.show();
		else{
			$("#mac").val("");
			macInput.hide();
		}
	}
	//校验数字和英文字母
	function checkGAndCode(str){
		var reg = /^[0-9,a-z,A-Z]+$/;
		if(!reg.test(str)){
			return false;
		}
		return true;
	}
   function trim(str){ //删除左右两端的空格
　　     return str.replace(/(^\s*)|(\s*$)/g, "");
　　 }
   function checkUserCode(){
		var usercode = document.getElementsByName("usercode")[0].value;
		if (usercode == "") {
			alert("请输入管理员编号！");
			return -1;
		}
		roleManage.checkUserCode(usercode, function(data) {
			if(data !='0'){
				alert("管理员编号已经存在！请重新输入");
				document.getElementsByName("usercode")[0].focus();
				return -1;
			}else{
				return 0;
			}
		});
	}
</script>
<center>
	<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0" >
		<html:form styleId="userForm" action="/user?method=addUser" method="post" focus="usercode">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="99%" >
				<tr>
					<td align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
				    <tr>
						<td width="28" height="10"></td>
					</tr>
					<tr>
						<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
						
						</td>
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置：系统管理管理 &gt; 管理员管理 &gt; 增加管理员 </td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
					</table>
						<table border="0" cellpadding="0" cellspacing="0" width="80%">
							<tr>
								<td width="110" align="right" class="box1">
									<font color="#FF0000">管理员编号：</font>
								</td>
								<td align="left" class="box3">
									<html:text property="usercode" maxlength="20" onchange="checkUserCode();"></html:text>
								</td>
							</tr>
							<tr>
								<td width="110" align="right" class="box1">
									<font color="#FF0000">管理员名称：</font>
								</td>
								<td align="left" class="box2">
									<html:text property="username" maxlength="20"></html:text>
								</td>
							</tr>
							<html:hidden property="passwd" value="111111" />
							<html:hidden property="passwd2" value="111111" />
							<tr>
								<td width="110" align="right" class="box1">
									<font color="#FF0000">所属用户组：</font>
								</td>
								<td align="left" class="box2">
									<select id="deptno" onchange="checkAdminDept()" name="deptno">
										<option value="">—请选择—</option>
									</select>
									<%--
									<logic:present name="deptList">
										<logic:equal value="00" name="roleno" > 
											<html:select property="deptno" onchange="checkAdminDept()">
												<html:option value="">－请选择－</html:option>
												<html:optionsCollection name="deptList" label="deptname" value="deptno"/>
											</html:select>
										<</logic:equal> 
										<logic:notEqual value="00" name="roleno">
											<html:select property="deptno" onchange="checkAdminDept()">
												<html:option value="">－请选择－</html:option>
												<html:optionsCollection name="deptList" label="deptname" value="deptno"/>
											</html:select>
										</logic:notEqual>
										
									</logic:present>
									 --%>
								</td>
							</tr>
							<tr>
								<td width="110" align="right" class="box1">
									<font color="#FF0000">所属角色：</font>
								</td>
								<td align="left" class="box2">
									<select name="roleno" id="roleno">
										<option value="">－请选择－</option>
									</select>
								</td>
							</tr>
							<tr>
								<td width="110" align="right" class="box1">
									通讯地址：
								</td>
								<td align="left" class="box2">
									<html:textarea rows="2" cols="60" property="address"  ></html:textarea>
								</td>
							</tr>
							<tr>
								<td width="110" align="right" class="box1">
									邮政编码：
								</td>
								<td align="left" class="box2">
									<html:text property="postalcode" maxlength="6"></html:text>
								</td>
							</tr>
							<tr>
								<td width="110" align="right" class="box1">
									EMAIL地址：
								</td>
								<td align="left" class="box2">
									<html:text property="mail" maxlength="20"></html:text>
								</td>
							</tr>
							<tr>
								<td width="110" align="right" class="box1">
									传真：
								</td>
								<td align="left" class="box2">
									<html:text property="fax" maxlength="16"></html:text>
								</td>
							</tr>
							<tr>
								<td width="110" align="right" class="box1" >
									联系电话：
								</td>
								<td align="left" class="box2">
									<html:text property="phone" maxlength="16"></html:text>
								</td>
							</tr>
							<tr>
								<td width="110" align="right" class="box1">
									<font color="#FF0000">密码有效期：</font>
								</td>
								<td align="left" class="box2">
									<input type="text" name="validdays" size="10"
										onfocus="javascript:showSelect(this,'select1')"
										value='<logic:equal value="-1"  property="validdays" name="userForm">永不过期</logic:equal><logic:notEqual value="-1"  property="validdays" name="userForm"><bean:write property="validdays" name="userForm"/></logic:notEqual>'>
									<select name="select1" id="select1" style="display: none"
										disabled>
										<option value="7">
											7
										</option>
										<option value="30">
											30
										</option>
										<option value="60">
											60
										</option>
										<option value="120">
											120
										</option>
										<option value="-1">
											永不过期
										</option>
									</select>
									<font color="red">[注意:密码有效期以天为单位!]</font>
								</td>
							</tr>
							<tr>
								<td width="110" align="right" class="box1">
									状态：
								</td>
								<td align="left" class="box2">
									<html:select property="isactive" >
										<html:option value="0">无效</html:option>
										<html:option value="1">有效</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="110" align="right" class="box1">是否绑定Mac地址：</td>
								<td  align="left" class="box2">
									<select id="isMac" onchange="showMacInput()">
										<option value="0">绑定</option>
										<option value="1" selected="selected">不绑定</option>
									</select>
								</td>
							</tr>
							<tr id="macInput" style="display:none">
								<td width="110" align="right" class="box1">请输入Mac地址：</td>
								<td  align="left" class="box2">
									<input type="text"  name="mac" id="mac">
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="80%">
							<tr>
								<td height="23" align="center" class="box1">
									<input class="button" type="button" onClick="commit();" value="保存">
									&nbsp;&nbsp;&nbsp;
									<input class="button" type="button"  value="关闭" onClick="history.go(-1)">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</center>
</html:html>