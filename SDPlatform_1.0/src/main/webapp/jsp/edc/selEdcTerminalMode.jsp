<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*" isELIgnored="false"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
	<title>选择</title>
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
	<!--------------以下Table为路径-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">    
				当前位置：收单系统管理平台 >> 商户管理 >> 联机处理要求设置
			</td>
		</tr>
		<tr>
			<td height="10">
			</td>
		</tr>
	</table>
	<!--------------Table为路径结束-------->
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
		<thead>
			<tr class="table2_td_title"><td colspan="2"><b>填写如下信息：</b></td></tr>
		</thead>
		<tr>
			<td class="table2_td_title" width="50%">
				联机处理要求:
			</td>
			<td class="table2_td" width="50%">
				<select id="termReq" name="termReq" >
						<option value="0">无</option>
						<option value="4">更新公钥</option>
						<option value="5">下载IC卡参数</option>
					</select>
			</td>
			
		</tr>				
		<tr>
			<td colspan="4" class="table2_btn">
	 			<input type="button" name="submit" value="设置联机处理要求 " class="button" onclick="termReqBat();">
			</td>
		</tr>
	</table>
</form>
</body>
</html:html>