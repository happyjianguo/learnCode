<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*" isELIgnored="false"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
	<title>ѡ��</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<link href="<fmt:message key='StylePath' />testAjax.css" rel="stylesheet" type="text/css">
	<script type="text/javascript"  language="JavaScript" src="<fmt:message key='JavaScriptPath' />data.js"></script>
	<script type="text/javascript" language="JavaScript"  src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script  type="text/javascript" language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script  type="text/javascript" language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	<script language="javascript">
	function setNames(the_names) { 
		nameTableBody = document.getElementById("name_table_body");
		var ind = nameTableBody.childNodes.length;
        for (var i = ind - 1; i >= 0 ; i--) {
             nameTableBody.removeChild(nameTableBody.childNodes[i]);
        }
        var size = the_names.length;
        var row, cell, txtNode;
        for (var i = 0; i < size; i++) {
            var nextNode = the_names[i].firstChild.data;
            row = document.createElement("tr");
            cell = document.createElement("td");
            
            cell.onmouseout = function() {this.className='mouseOver';};
            cell.onmouseover = function() {this.className='mouseOut';};
            cell.setAttribute("bgcolor", "#FFFAFA");
            cell.setAttribute("border", "0");
            cell.onmousedown = function() { 
            	var allValue = this.firstChild.nodeValue ;
    			var intPos = allValue.indexOf("-");
            	window.returnValue = this.firstChild.nodeValue.substr(0,intPos);
            	window.close();
            	};                             
            txtNode = document.createTextNode(nextNode);
            cell.appendChild(txtNode);
            row.appendChild(cell);
            nameTableBody.appendChild(row);
        }
    }
    function termReqBat(){
    	window.returnValue = document.forms[0].termReq.value;
    	window.close();
	}
	</script>
</head>

<body>
<form>
	<!--------------����TableΪ·��-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">    
				��ǰλ�ã��յ�ϵͳ����ƽ̨ >> �̻����� >> ��������Ҫ������
			</td>
		</tr>
		<tr>
			<td height="10">
			</td>
		</tr>
	</table>
	<!--------------TableΪ·������-------->
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
		<thead>
			<tr class="table2_td_title"><td colspan="2"><b>��д������Ϣ��</b></td></tr>
		</thead>
		<tr>
			<td class="table2_td_title" width="50%">
				��������Ҫ��:
			</td>
			<td class="table2_td" width="50%">
				<select id="termReq" name="termReq" >
						<option value="0">��</option>
						<option value="4">���¹�Կ</option>
						<option value="5">����IC������</option>
					</select>
			</td>
			
		</tr>				
		<tr>
			<td colspan="4" class="table2_btn">
	 			<input type="button" name="submit" value="������������Ҫ�� " class="button" onclick="termReqBat();">
			</td>
		</tr>
	</table>
</form>
</body>
</html:html>