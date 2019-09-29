<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>405 - 该页无法显示</title>
<style type="text/css">
HTML {
	PADDING-RIGHT: 0px;
	PADDING-LEFT: 0px;
	FONT-SIZE: 14px;
	BACKGROUND: #ffffff;
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-TOP: 0px;
	FONT-FAMILY: Tahoma, Verdana, "??";
	TEXT-ALIGN: center
}

BODY {
	PADDING-RIGHT: 0px;
	PADDING-LEFT: 0px;
	FONT-SIZE: 12px;
	BACKGROUND: #ffffff;
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-TOP: 0px;
	FONT-FAMILY: Tahoma, Verdana, "??";
	TEXT-ALIGN: center
}

A:link {
	COLOR: #000000;
	text-decoration: none;
	cursor: pointer;
}

A:visited {
	COLOR: #000000;
	text-decoration: none;
	cursor: pointer;
}

A:hover {
	COLOR: #000000;
	text-decoration: none;
	cursor: pointer;
}

#container {
	MARGIN-LEFT: auto;
	WIDTH: 100%;
	MARGIN-RIGHT: auto;
	BACKGROUND-COLOR: #ffffff;
	TEXT-ALIGN: center
}

#maincolumn {
	FONT-SIZE: 14px;
	MARGIN-LEFT: auto;
	WIDTH: 100%;
	MARGIN-RIGHT: auto;
	HEIGHT: 80px;
	BACKGROUND-COLOR: #ffffff;
	TEXT-ALIGN: center
}
</style>
</head>

<body>
	<DIV id=container>
		<IMG src="${ctx}/common/images/error.gif">
	</DIV>
	<DIV id=maincolumn>
		<DIV>
			<li><font color="red">非法请求！</font></li>
		</DIV>
		<DIV>
			<A href="" class="style1"></A> - <A href=""></A>
		</DIV>
	</DIV>
	<DIV>
		Copyright © 2014 <A href="http://www.fuka.cc" target="">中石化裕福支付</A> All Rights Reserved.
	</DIV>
</body>
</html>