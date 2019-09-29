<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="true">
<head>
	<title>��������</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="<c:out value='${pageContext.request.contextPath}' />/<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	
	<script>
	var userRemindTime=5;//���û����õ�����ʱ����,��λ����
	var remindType="part"//���û����õ��������͡�part����һ��ʱ���� �� ��all��:���� 
	var remindTime=userRemindTime*60000;
	var timeId;
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
	function startRemind()
	{
		remindWorkItem();
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
	function remindWorkItem() 
	{
		var url = "<c:out value='${pageContext.request.contextPath}' />/sendFile.do?method=testAjax&remindTime="+userRemindTime+"&remindType="+remindType; 
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
	    	var newWorkTitle = responseXML.getElementsByTagName("desc");
	    	
	    	//���쵯����ʾ������
	    	if(newWorkCount>0){
				popTop=50;
				var winstr="<table style=\"border: 1px solid #CC9900\" width=\"241\" height=\"172\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
				winstr+="<tr><td style=\"font-size: 14px;font-weight: bold;color: #FFFFFF;background-image: url(<c:out value='${pageContext.request.contextPath}' />/<fmt:message key='CommonImagePath' />table1_head_bg.jpg)\" >";
				winstr+="<img src=\"<c:out value='${pageContext.request.contextPath}' />/<fmt:message key='CommonImagePath' />table1_head_ico.gif\" align=\"absmiddle\">��������";
				winstr+="</td>";
				winstr+="<td style=\"background-image: url(<c:out value='${pageContext.request.contextPath}' />/<fmt:message key='CommonImagePath' />table1_head_bg.jpg)\" align=\"right\">";
				winstr+="<a href=\"#\" onClick=\"javascript:parent.popTop=3010;\"><img src=\"<c:out value='${pageContext.request.contextPath}' />/<fmt:message key='CommonImagePath' />btnQuery2.gif\"  border=\"0\"></a>";
				winstr+="</td></tr>";
				winstr+="<tr><td colspan=\"2\" align=\"center\">";
				
				winstr+="<table width=\"95%\\" height=\"110\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
				winstr+="<tr><td valign=\"top\" style=\"font-size:12px; color: #6D93C8; face: Tahoma\">����:�������� "+newWorkCount+" ������δ�鿴������</td></tr>";
				for(var i=0; i<newWorkTitle.length; i++){
					if(i<4){
						winstr+="<tr><td valign=\"top\" style=\"font-size:12px; color: #6D93C8; face: Tahoma\">���� "+(i+1)+"��"+newWorkTitle[i].firstChild.nodeValue+"</td></tr>";
					}else{
						winstr+="<tr><td valign=\"top\" style=\"font-size:12px; color: #6D93C8; face: Tahoma\">��������</td></tr>";
						break;
					}
				}
				winstr+="</table>";
				
				winstr+="</td></tr></table>";
				
				winstr+="<embed src=\"<c:out value='${pageContext.request.contextPath}' />/security.wav\" hidden=\"true\" autostart=\"true\" >";
				popmsg(winstr);
				
			}
	}
	</script>
</head>

<body onload="startRemind()">

<br><br><br><br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" onClick="runNow();" value="ִ      ��">
&nbsp;&nbsp;
<input type="button" onClick="startRemind();" value="��      ʼ">
&nbsp;&nbsp;
<input type="button" onClick="stopRemind();" value="ͣ      ֹ">

</body>
</html>

