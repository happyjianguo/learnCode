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
	var req;
	function init() {
		if(window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	/***************商户编号验证*****************/
	function findArea() {
		var AreaCode = document.getElementById("AreaCode").value;
		var AreaName = document.getElementById("AreaName").value;
		var bool=false;
	    if(AreaName!=null&&AreaName!=""){
	    	bool = true;
		}
	    if(AreaCode!=null&&AreaCode!=""){
	    	bool = true;
	    }
	    if(!bool){
	    	alert("请输入查询条件！");
		}else{
			init();
			var url="merchant.do?method=findArea&AreaCode="+AreaCode+"&AreaName="+AreaName;
			req.open("POST", url, true);
			req.onreadystatechange = Callback;
			req.send(null);
		}
    }
    
	function Callback() {
	 if(4 == req.readyState) {
		if(200 == req.status) {
			var params = req.responseXML.getElementsByTagName("param");
            setNames(params);
		}
	  }
    }
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
    function resetClick(){
		document.getElementById("AreaCode").value = "";
		document.getElementById("AreaName").value = "";
    }
	</script>
</head>

<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle"> 
				选择
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%"> 
								名称: 
							</td>
							<td class="table2_td" width="40%">
								<input id="AreaName" name="AreaName" size="19" maxlength="15" />
							</td>
							<td class="table2_td_title" width="10%"> 
								编号: 
							</td>
							<td class="table2_td" width="40%">
								<input id="AreaCode" name="AreaCode" size="19" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'');"/>
							</td>						
						</tr>				
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="查询" width="65" height="20" border="0" onclick="findArea();">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" onclick="resetClick()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="选择" width="65" height="20" border="0"></a>
							</td>
						</tr>
					</table>
					<!----------Table为查询form结束-------->

					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table3_border">
						<tr>
							<td class="table3_title">
								查询结果
							</td>
						</tr>
					</table>
					<table id="name_table"  bgcolor="" border="0" cellspacing="0" cellpadding="0"/>            
						<tbody id="name_table_body"> </tbody>
					</table>
			</td>
		</tr>
	</table>
</body>
</html:html>



