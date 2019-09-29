<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="true">
<head>
	<title>����</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="<c:out value='${pageContext.request.contextPath}' />/<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	
	<script>
	var remindTime=60000;//��������ʱ����,��λ����
	var timeId;//���嶨ʱ����id
	var oPopup = window.createPopup();
	var popTop=50;
	
	function popmsg(winstr){
		oPopup.document.body.innerHTML = winstr;
		popshow();
	}
	function popshow(){
		if(popTop>3220){
			clearTimeout(mytime);
			oPopup.hide();
			return;
		}else if(popTop>3020&&popTop<3220){
			oPopup.show(screen.width-250,screen.height,241,3220-popTop);
		}else if(popTop>3000&&popTop<3020){
			oPopup.show(screen.width-250,screen.height+(popTop-3220),241,172);
		}else if(popTop<180){
			oPopup.show(screen.width-250,screen.height,241,popTop);
		}else if(popTop<220){
			oPopup.show(screen.width-250,screen.height-popTop,241,172);
		}
		popTop+=10;
		var mytime=setTimeout("popshow();",50);
	}
	
	function close(){
		oPopup.hide();
	}
	function startRemind(url)
	{
		timeId=window.setInterval("remindWorkItem()",remindTime);
		
	}
	function stopRemind(){
		window.clearInterval(timeId);
	}
	
	function runNow(){
		remindWorkItem();
	}
	/////////////////////////////ajax////////////
	//�������
	function remindWorkItem(url) 
	{
		var url = "<c:out value='${pageContext.request.contextPath}' />/sendFile.do?method=testAjax&testId="+timeId; 
		createXMLHttpRequest();
		xmlHttp.onreadystatechange = handleAddStateChange;
		xmlHttp.open("GET", url, true);
		xmlHttp.send(null);
	}
	//����XMLHttpRequest����
	function createXMLHttpRequest() 
	{
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}	
	//�ص�����    
	function handleAddStateChange() {
		if(xmlHttp.readyState == 4) {
			if(xmlHttp.status == 200) {
				popAndUpdateWorkItem();
			}else {
				alert("Error while adding employee.");
			}
		}
	}
	//����ҳ��
	function popAndUpdateWorkItem()
    {
	    	//������������
	    	var responseXML = xmlHttp.responseXML;
			var newWorkCount = responseXML.getElementsByTagName("count").item(0).firstChild.nodeValue;
	    	//���쵯����ʾ������
	    	if(newWorkCount>0){
				popTop=50;
				var winstr="<table style=\"border: 1 solid #6D93C8\" width=\"241\" height=\"172\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
				winstr+="<tr><td height=\"30\" align=\"right\"><a href=\"#\" onClick=\"javascript:parent.popTop=3010;\"><img src=\"<c:out value='${pageContext.request.contextPath}' />/<fmt:message key='CommonImagePath' />btnQuery2.gif\"  border=\"0\"></a></td></tr><tr><td align=\"center\"><table width=\"90%\" height=\"110\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
				winstr+="<tr><td valign=\"top\" style=\"font-size:12px; color: #6D93C8; face: Tahoma\"><br>����<br>  �������� "+newWorkCount+" ����������Ҫ����</td></tr></table></td></tr></table>";
				popmsg(winstr);
			}
	}
	</script>
</head>

<body>
<br><br><br><br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" onclick="runNow();" value="ִ      ��">
&nbsp;&nbsp;
<input type="button" onclick="startRemind();" value="��      ʼ">
&nbsp;&nbsp;
<input type="button" onclick="stopRemind();" value="ͣ      ֹ">
</body>
</html>

