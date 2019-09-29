<%@ page language="java" pageEncoding="GB2312"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%	
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>


<title>管理控制台---标题栏</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css"
	href="<%=path%>/styles/ext-all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/styles/main.css" />
<script src="<%=path%>/js/calendar.js"></script>
<script src="<%=path%>/scripts/ext/core/Ext.js" type="text/javascript"></script>
<script src="<%=path%>/scripts/ext/adapter/ext-base.js"
	type="text/javascript"></script>
<script src="<%=path%>/scripts/ext/ext-all-debug.js"
	type="text/javascript"></script>

<script src="<%=path%>/scripts/creditMenu.js" type="text/javascript"></script>

<script language="javascript">
		var logoutFlag = "logout2";
		var usercode = top.usercode;
		var username = top.username;
		var teamno = top.teamno;
		var teamname = top.teamname;
		var roleno = top.roleno;
		var rolename = top.rolename;
		var deptno = top.deptno;
		var deptname = top.deptname;
		var currentDate = top.currentDate;
		
		function checkmenu(menuId,op){
			var menuTd = document.getElementsByTagName("td");
			for(var i=0 ; i<menuTd.length ; i++){
				menuTd[i].background = "";
			}
			op.background="<%=path %>/images/menu/menuclick.gif";
			
			var menuSpan = document.getElementsByTagName("span");
			for(var i=0 ; i<menuSpan.length ; i++){
				menuSpan[i].style.color = "#E4F6FF";
			}
			op.innerHTML = "<span class='topmenu2'>" + op.innerText + "</span>";
			window.parent.document.getElementsByName("left")[0].src="<%=path%>/checkMenu.do?menuId="+menuId+"&usercode="+usercode+"&method=checkchild";
			window.parent.document.getElementsByName("right")[0].src="<%=path%>/jsppage/common/right.jsp";
		}
		
		function logout1(mess1){
			var answer = window.confirm("您确定要退出吗？");
			if(answer == true)
			{
				logoutFlag = "logout1";
				document.getElementById("infoframe").src = "<%=path%>/exit.do?usercode="+usercode+"&username="+username+"&teamno="+teamno+"&teamname="+teamname+"&roleno="+roleno+"&rolename="+rolename+"&deptno="+deptno+"&deptname="+deptname+"&flag="+mess1;
			}
		}
		
		function logout2(mess2){
			if(logoutFlag == "logout2"){
			//	top.document.getElementById("loginframe").src = "<%=path%>/exit.do?usercode="+usercode+"&username="+username+"&teamno="+teamno+"&teamname="+teamname+"&roleno="+roleno+"&rolename="+rolename+"&deptno="+deptno+"&deptname="+deptname+"&flag="+mess2;
			}
		}
		
		function modifyPassword(){
			var inputWin;
			if(inputWin==null || inputWin.closed){
				inputWin = window.open('<%=path%>/user.do?method=preModPassword&usercode='+usercode,'管理员修改密码页面','resizeable=no,dependent,width=400,height=230,left=400,top=300');
				inputWin.focus();
			}
			else{
				inputWin.close();
	 			inputWin = window.open('<%=path%>/user.do?method=preModPassword&usercode='+usercode,'管理员修改密码页面','resizeable=no,dependent,width=400,height=230,left=400,top=300');
	    		inputWin.focus();
			}
		}
		//add 2012-11-13 luo
		function updatePwd(usercode){
			window.open('<%=path%>/user.do?method=preModPassword&usercode='+usercode,'管理员修改密码页面','resizeable=no,dependent,width=400,height=230,left=400,top=300');
			
		}
		
		function frUsercode(){
			document.setusercode.location.href ="<%=path%>/jsppage/common/setusercode.jsp?usercode="+top.usercode+"&username="+top.username+"&teamno="+top.teamno+"&teamname="+top.teamname;
			setTimeout("frUsercode();",20*60*1000);
		}
	
	function toggleLeftNav() {
	
		var leftNav = document.getElementById("leftNav");
		var leftNavHandle = document.getElementById("leftNavHandle");
		
		if(leftNav.className == "hide"){
			leftNav.className = "";
			leftNavHandle.alt='点击隐藏';
			leftNavHandle.className = "";
		} else {
			leftNav.className = "hide";
			leftNavHandle.alt='点击显示';
			leftNavHandle.className = "hide";
		}
	}
		
	function doOnload() {
 
		var contentHeight = getHeight()
			- document.getElementById('banner').offsetHeight
			- document.getElementById('mainMenuBar').offsetHeight
			- document.getElementById('bottom').offsetHeight;
		
		if (contentHeight < document.getElementById('leftMenuBar').offsetHeight) {
			contentHeight = document.getElementById('leftMenuBar').offsetHeight;
		}
		
		document.getElementById('infoframe').style.height = contentHeight + 'px';
		loadContent();
	}
	
	function loadContent(){
			getMenuData("<%=path%>/menudata.do", "mainMenuBar", "leftMenuBar");
	}
	
	function changeLanguage(el) {
		var value = el.value;
		var url = "<%=path%>/main.do?locale="+value;
		url = EMPTools.encodeURI(url);
		window.location = url;
	}
	function signOff(mess1){
		if(confirm("您确定要退出吗？")){
			document.location.href = "<%=path%>/exit.do?usercode=" + usercode
					+ "&username=" + username + "&teamno=" + teamno
					+ "&teamname=" + teamname + "&roleno=" + roleno
					+ "&rolename=" + rolename + "&deptno=" + deptno
					+ "&deptname=" + deptname + "&flag=" + mess1;

		}
	}
	function getHeight() {
		var yScroll;
		if (window.innerHeight && window.scrollMaxY) {
			yScroll = window.innerHeight + window.scrollMaxY;
		} else if (document.body.scrollHeight > document.body.offsetHeight) {
			yScroll = document.body.scrollHeight; // all but Explorer Mac 
		} else {
			yScroll = document.body.offsetHeight; // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari   
		}
		var windowHeight;
		if (self.innerHeight) {
			windowHeight = self.innerHeight; // all except Explorer       
		} else if (document.documentElement
				&& document.documentElement.clientHeight) {
			windowHeight = document.documentElement.clientHeight; // Explorer 6 Strict Mode         
		} else if (document.body) {
			windowHeight = document.body.clientHeight; // other Explorers           
		}
		if (yScroll < windowHeight) {
			pageHeight = windowHeight; // for small pages with total height less then height of the viewport       
		} else {
			pageHeight = yScroll;
		}
		return pageHeight;
	}
</script>
<style type="text/css">
#userInfo a:LINK {
	color: #000000;
}

#userInfo a:VISITED {
	color: #000000;
}

#userInfo a:hover {
	color: #FF0000;
}
</style>
</head>
<body leftmargin="0" topmargin="0" onload="doOnload();">
	<div id="main">
		<!-- Logo区域 -->
		<div id="banner">
			<div id="logo"></div>
			<div id="userInfoBox">
				<div id="userInfo">
					<form action="" method="post">
						欢迎您：<span id='emp_field_s_actorname' label='用户名' type='Base'
							class='emp_field' cssErrorClass='emp_field_error'
							cssRequiredClass='emp_field_required' colSpan='1'
							readonly='false' onlyControlElement='false' languageUse='zh_CN'
							value='' rendered='false'><span class='emp_field_flat'><bean:write
									name="usercode" /></span></span>| 用户组名称：<span id='emp_field_s_organname'
							label='用户组名称' type='Base' class='emp_field'
							cssErrorClass='emp_field_error'
							cssRequiredClass='emp_field_required' colSpan='1'
							readonly='false' onlyControlElement='false' languageUse='zh_CN'
							value='' rendered='false'><span class='emp_field_flat'><bean:write
									name="deptname" /></span></span>| 登录时间：<span id='emp_field_s_lastlogdat'
							label='登录时间' type='Base' class='emp_field'
							cssErrorClass='emp_field_error'
							cssRequiredClass='emp_field_required' colSpan='1'
							readonly='false' onlyControlElement='false' languageUse='zh_CN'
							value='' rendered='false'><span class='emp_field_flat'><bean:write
									name="lastintime" /></span></span>| <a style="text-decoration: none;"
							href="javascript:void();"
							onclick="updatePwd('<bean:write name="usercode"  />')">修改密码</a>

					</form>
				</div>
			</div>
			<div id="signOut" onclick="signOff('logout1')"></div>
		</div>
		<div id="mainMenuBar">
			<!-- 一级菜单在此生成 -->
		</div>

		<!-- 左侧菜单+主区域 -->
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td id="leftNav">
					<div id="leftNavTop">
						<div id="leftMenuBar"
							style="height: 400px; width: 100%; overflow: auto; text-align: left;">
							<!-- 左侧菜单在此生成 -->
						</div>
					</div>
				</td>
				<td id="leftNavHandleWrapper">
					<div id="leftNavHandle" onclick="toggleLeftNav()"></div>
				</td>

				<!-- 右侧的主工作区域 -->
				<td id="rightFrame"><iframe id="infoframe" name="infoframe"
						src="jsppage/common/right.jsp" frameborder="0" scrolling="auto"
						height="100%" width="100%" allowTransparency="true"> </iframe></td>

			</tr>
		</table>

		<div id="bottom">
			<div id="copyright"></div>
		</div>
	</div>
</body>
</html:html>