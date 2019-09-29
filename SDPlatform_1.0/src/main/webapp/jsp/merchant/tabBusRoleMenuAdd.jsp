<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<title>业务角色菜单</title>
<fmt:setBundle basename="MyResource"/>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />jquery-1.4.min.js"></script>

<script language="javascript">
	function saveClick() {
		var menuList = '';
		var varInt='';
		var varTotal='${tabBusinessRatesList[fn:length(tabBusinessRatesList)-1].businessid}';

		for (var int = 1; int <= varTotal; int++) {
			if(int>=100){
				varInt='';
			}
			if(9<int<100){
				varInt='0';
			}
			if(int<10){
				varInt='00';
			}
			if ($("#tabBusinessRatesMap" + varInt+int).is(':checked')) {
				if(int>=2){
					if(menuList==''){
						menuList =varInt + int;
					}else{
						menuList =menuList + ',' + varInt + int;
					}
				}else{	
					menuList =menuList + varInt + int;
				}
			} else {
				menuList += '';
			}
			
		}
		if(menuList.split(",").length>8){
			alert("角色业务菜单列表中不超过8个业务场景费率");
			return false;
		}
		document.forms[0].menuList.value = menuList;
		document.forms[0].method.value = "createItem";
		return validateTabBusRoleMenuForm(document.forms[0]);
	}
	function backClick() {
		document.forms[0].method.value = "queryAll";
	}
	
	
	/*****************************************/
	//删除后面空格
	String.prototype.RTrim   =   function(){   
		return   this.replace(/(\s*$)/g,"");   
	}
	
	var req;
	function init() {
		if(window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	 /*****************验证业务角色中文名称的唯一性***********************/ 	
	function findBusRoleNameKey(){
 		var busRoleName=document.forms[0].busRoleName.value;
 	  	init();
  		var url="tabBusRoleMenu.do?method=findBusRoleNameKey&busRoleName="+busRoleName;
  		req.open("POST", url, true);
  		req.onreadystatechange = findBusRoleNameKeyCallback;
  		req.send(null);
	  	
	}
	
    function findBusRoleNameKeyCallback() {
	 if(4 == req.readyState) {
		if(200 == req.status) {
		    if(req.responseText=='false'){
		       alert("业务角色中文名称已存在, 请重新输入！！！");
		       document.forms[0].busRoleName.value="";
		    }
		}	
     }
    } 
		
</script>
</head>
<shiro:lacksPermission name="posp:tabBusRoleMenu:add">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:tabBusRoleMenu:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title"><img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle"> 新增业务角色菜单</td>
		</tr>
		<tr>
			<td class="table1_head_line"></td>
		</tr>
		<tr>
			<td align="center"><html:javascript formName="tabBusRoleMenuForm" /> <html:errors /> 
			<html:form action="/tabBusRoleMenu">
					<html:hidden property="method" value="createItem" />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								业务角色中文名称:
							</td>
							<td class="table2_td">
								<html:text property="busRoleName" size="40" maxlength="15"	onblur="findBusRoleNameKey();"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">角色业务菜单列表:</td>
							<td class="table2_td"><html:hidden property="menuList" /> 
								<c:forEach items="${tabBusinessRatesList}" var="tabBusinessRatesMap" varStatus="index">
									${tabBusinessRatesMap.businessid }、<input type="checkbox" name="tabBusinessRatesMap${tabBusinessRatesMap.businessid }" id="tabBusinessRatesMap${tabBusinessRatesMap.businessid }">${tabBusinessRatesMap.businessname } <br>
								</c:forEach> 
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()"> 
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()">
							</td>
						</tr>
					</table>
				</html:form></td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>