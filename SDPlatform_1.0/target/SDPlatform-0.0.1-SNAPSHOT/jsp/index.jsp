<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
	<title>收单系统管理平台</title>
	<meat http-equiv=Content-Type content="text/html; charset=GBK">
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
	<script language="JavaScript" type="text/JavaScript">
	function MM_reloadPage(init) { //reloads the window if Nav4 resized
		if (init == true)
			with (navigator) {
				if ((appName == "Netscape") && (parseInt(appVersion) == 4)) {
					document.MM_pgW = innerWidth;
					document.MM_pgH = innerHeight;
					onresize = MM_reloadPage;
				}
			}
		else if (innerWidth != document.MM_pgW
				|| innerHeight != document.MM_pgH)
			location.reload();
	}
	MM_reloadPage(true);
	//-->
	</script>
</head>
<frameset id=main rows="50,2,*" cols="*" frameborder="NO" border="0" framespacing="0">
	<!--页头-->
	<frame src="<c:url value="/jsp/header.jsp"/>" name="header" frameborder="no" scrolling="NO" noresize marginwidth="0" marginheight="0" id="header">
	<frame name=f6 marginWidth=0 marginHeight=0 src="<c:url value="/jsp/f6.htm"/>" frameborder=no noResize scrolling=no>
	<!--页控制-->
	<frameset id=fmain style="corsor: auto" border=0 name=fmain frameSpacing=0 frameBorder=no cols="150, 2, *">
		<!--页左-->
		<frame src="<c:url value="/User.do?method=getMenu"/>" name=navigation scrolling="auto" noResize marginWidth=0 marginHeight=0 id="Navigation Area">
		<frame name=f5 marginWidth=0 marginHeight=0 src="<c:url value="/jsp/f5.htm"/>" frameBorder=no noResize scrolling=no>
		<!--页控制-->
		<frameset rows="*,25" frameborder="NO" border="0" framespacing="0">
			<frame id=body name=body marginWidth=0 marginHeight=0 src="<c:url value="/jsp/welcome.jsp"/>" noResize scrolling="NO">
			<frame src="<c:url value="/jsp/footer.htm"/>" name="footer" scrolling="NO" noresize id="footer">
		</frameset>
	</frameset>
</frameset>
<noframes></noframes>
</html>