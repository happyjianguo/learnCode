<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>手动执行脚本</title>	   
    <link rel="stylesheet" type="text/css" media="screen"		href="<%=path%>/css/style.css" /> 
    <script type="text/javascript" src="<%=path%>/js/dwr.js"></script>   
   
    <script>
	
	function submitstatsT(panflagno,batchflag){	
		
		ShellServiceOfHousekeep.readstatsOfNC(panflagno,batchflag);				
	}
	
    function loadPage(){
    	var path = '<%=path%>';
    	var flushdo = '<bean:write name="flushdo"/>';
    	flushdo = path + flushdo;	    	
     if (flushdo == path + "closewindow"){
			window.opener.query();         	
			window.close();
	  }else if(flushdo == (path + "javascript:history.go(-1)")){
		  window.location.href="javascript:history.go(-1)";
	  }else{
		  window.location.href=flushdo;
      }
    }
</script>
   
  </head>
  
  <body>	
	<table id="tab" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" >
		<tr>
			<td>
				<table width="350" border="0" cellspacing="0" cellpadding="0"
					align="center" >
					<tr>
						<td colspan="3" width="350" height="21" background="<%=path%>/images/result/messges_top_01.gif">&nbsp;
							
						</td>
					</tr>
					<tr>
						<td width="23" height="107" align="right"background="<%=path%>/images/result/messges_left_01.gif">
							
						</td>
						<td align="center" width="303" align="center" height="107">
							<span class="big">结 果 信 息</span>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="10%" height="35" align="right">
										
										<img src="<%=path%>/images/result/messges_02.gif" width="30"
											height="31" align="right">
										
									</td>
									<td width="80%" height="40" align="center">
										<font color="green" id="msginfo">脚本正在执行，请耐心等待!<br/>受理号：<bean:write name="bFlowLogBean" property="panflagno" /></font>
									</td>
									
									<td width="10%" height="40" align="center">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td height="25" colspan="5" align="center">
										<input name="Submit2" type="button" class="button" value="关闭"
											style="cursor: hand" onclick="loadPage();">
									</td>
								</tr>
							</table>
						</td>
						<td align="left" width="23" height="107" background="<%=path%>/images/result/messges_right_01.gif">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3" background="<%=path%>/images/result/messges_bottome_01.gif" width="350" height="18">
							
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table> 
	<script>
	submitstatsT('<bean:write name="bFlowLogBean" property="panflagno" />','<bean:write name="bFlowLogBean" property="batchflag"/>');
	</script>
  </body>
</html>
