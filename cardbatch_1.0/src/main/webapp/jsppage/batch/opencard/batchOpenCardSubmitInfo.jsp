<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
String path = request.getContextPath();
String batch_stat_query=request.getAttribute("batch_stat_query")==null?"":request.getAttribute("batch_stat_query").toString();
String pay_type_query=request.getAttribute("pay_type_query")==null?"":request.getAttribute("pay_type_query").toString();
String sales_point_query=request.getAttribute("sales_point_query")==null?"":request.getAttribute("sales_point_query").toString();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>手动执行脚本</title>	   
    <link rel="stylesheet" type="text/css" media="screen"		href="<%=path%>/css/style.css" /> 
    <script type="text/javascript" src="<%=path%>/js/dwr.js"></script>   
   
    <script>
	function submitstatsT(panflagno,batchflag){	
		
		ShellService.readstats(panflagno,batchflag);				
	}
    function loadPage(){
    	var batch_stat_query = '<%=batch_stat_query%>';
    	var pay_type_query = '<%=pay_type_query%>';
    	var sales_point_query = '<%=sales_point_query%>';
    	var path = '<%=path%>';
    	var flushdo = '<bean:write name="flushdo"/>';
    	flushdo = path + flushdo;	 
     if (flushdo == path + "closewindow"){
    	 window.location.href="<%=path%>/opencard.do?method=getOpenCardList&batch_stat_query="+batch_stat_query+"&pay_type_query="+pay_type_query+"&sales_point_query="+sales_point_query;
	  }
    }
</script>
   
  </head>
  
  <body>	
	<table id="tab" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" >
		<tr>
			<td>
				<table width="350" border="0" cellspacing="0" cellpadding="0"		align="center" >
					<tr>
						<td colspan="3" width="350" height="21" background="<%=path%>/images/result/messges_top_01.gif">&nbsp;
							
						</td>
					</tr>
					<tr>
						<td width="23" height="107" align="right"background="<%=path%>/images/result/messges_left_01.gif">
							
						</td>
						<td align="center" width="303" align="center" height="107">
							<span class="big">结 果 信 息</span>
							<table width="800" border="0" align="center" cellpadding="0"	cellspacing="0" >
								<tr>
									<td width="10%" height="35" align="right">
										<img src="<%=path%>/images/result/messges_02.gif" width="30"
											height="31" align="right">										
									</td>
									<td width="100%" height="100" align="center">
											<c:forEach items="${info}" var="array">
												${array}<br/>
											</c:forEach>
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
	
	</script>
  </body>
</html>
