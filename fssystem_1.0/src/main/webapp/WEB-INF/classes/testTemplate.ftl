<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>MyFreeMarkerTestHtml.html</title> 
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
  </head>  
  <body>  
	<table width="100%" border="1" cellspacing="0" cellpadding="0">
		<tr>
			<th>商户编号</th>
			<th>商户名称</th>
			<th>交易类型</th>
			<th>交易日期</th>
			<th>交易时间</th>															
			<th>交易金额（元）</th>
		</tr> 
		<#list ViewStlBookDetailList as info> 
			<tr>
				<td>${info.merNo}</td>
				<td>${info.merName}</td>
				<td>${info.tranType}</td>
				<td>${info.tranDate}</td>
				<td>${info.tranTime}</td>
				<td>${info.tranAmt}</td>	
			</tr>		  
		</#list>      
	</table> 
  </body>  
</html>  