<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%String path = request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>显示列表页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/new_look.css" />
</head>
<script src="<%=path%>/js/sorttable.js"></script>
<script src="<%=path%>/js/eposcc.js"></script>
<script language="javascript">
	function addConfirm(id)
	{
		var url =  "<%=path%>/crdformatMap.do?method=preAddCrdformatMap&random=" + Math.random();
		var iWidth = 1046; //弹出窗口的宽度;
		var iHeight = 400; //弹出窗口的高度;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
		var winSize = 'height='
				+ iHeight
				+ ',width='
				+ iWidth
				+ ',top='
				+ iTop
				+ ',left='
				+ iLeft
				+ ',toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no';
		window.open(url, "", winSize);	
	}
	
	function query(){
		var crdformatMapForm = document.all("crdformatMapForm");
		crdformatMapForm.action = "<%=path%>/crdformatMap.do?method=queryCrdformatMapList";
		crdformatMapForm.submit();
	}

	function preQueryCrdformatMap(id,funcFlag)
	{
		var url =  "<%=path%>/crdformatMap.do?method=preQueryCrdformatMap&id="+id+"&funcFlag="+funcFlag+"&random=" + Math.random();		
		var iWidth = 1046; //弹出窗口的宽度;
		var iHeight = 400; //弹出窗口的高度;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
		var winSize = 'height='
				+ iHeight
				+ ',width='
				+ iWidth
				+ ',top='
				+ iTop
				+ ',left='
				+ iLeft
				+ ',toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no';
		window.open(url, "", winSize);	
	}
	
	function preModCrdformatMap(id,funcFlag)
	{
		var url =  "<%=path%>/crdformatMap.do?method=preModCrdformatMap&id="+id+"&funcFlag="+funcFlag+"&random=" + Math.random();
		var iWidth = 1046; //弹出窗口的宽度;
		var iHeight = 400; //弹出窗口的高度;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
		var winSize = 'height='
				+ iHeight
				+ ',width='
				+ iWidth
				+ ',top='
				+ iTop
				+ ',left='
				+ iLeft
				+ ',toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no';
		window.open(url, "", winSize);	
	}	
	function deleteClick(id){
		if(confirm("确定要删除编号为"+id+"的卡BIN映射？")){
			var crdformatMapForm = document.all("crdformatMapForm");
			crdformatMapForm.action = "<%=path%>/crdformatMap.do?method=delCrdformatMapInfo&id="+id;
			crdformatMapForm.submit();		
		}
	}
</script>

<body>
	<html:form action="/crdformatMap?method=queryCrdformatMapList" method="post"
		styleId="crdformatMapForm">
		<bean:define id="menu_level" name="menu_level" />
		<table border="0" cellpadding="0" cellspacing="0" width="100%"
			height="100%">
			<tr>
				<td align="center" valign="top" height="87%">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="28" height="10"></td>
						</tr>
						<tr>
							<td align="left" width="28" height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">

							</td>
							<td height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;">
								&nbsp;&nbsp;当前位置：批量管理 &gt; 卡BIN映射管理
							</td>
							<td width="7" height="28"
								style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) ">
							</td>
						</tr>
						<tr>
							<td width="28" height="5" colspan="3"></td>
						</tr>
					</table>
					<table cellpadding="0" border="0" cellspacing="0" width="100%" style="padding: 0px;" align="left">
						<tr>
							<td coslpan="7"><font color="red">${info}</font></td>
						</tr>
						<tr class="serch">
							<td style="white-space: nowrap" align="right">
								卡BIN
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="queryIid" maxlength="15" size="16" />
							</td>
							<td style="white-space: nowrap" align="right">
								对应功能
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="queryFuncFlag">
									<html:option value="">－请选择－</html:option>
									<html:option value="0">批量发卡管理</html:option>
									<html:option value="1">生成同福卡绑定的虚拟卡管理</html:option>
								</html:select>
							</td>
							<td style="white-space: nowrap" align="right">
								状态
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="queryIsuse" >
									<html:option value="">－请选择－</html:option>
									<html:option value="1">可用</html:option>
									<html:option value="0">停用</html:option>
								</html:select>
							</td>
							<td style="white-space: nowrap" align="right">
								有效期是否必填
							</td>							
							<td style="white-space: nowrap" align="left">
								<html:select property="queryNeedDt">
									<html:option value="">－请选择－</html:option>
									<html:option value="1">必填</html:option>
									<html:option value="0">非必填</html:option>
								</html:select>
							</td>							
							<td style="white-space: nowrap" align="left">
								<input type="button" class="button" onClick='query();'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)">
								<input type="button" class="button"
									onClick='return addConfirm()'
									style="background-image: url(<%=path%>/image1/border/New_button.gif)">
							</td>
						</tr>
						<tr>
						<td colspan="10">
							<display:table name="crdformatMapList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/crdformatMap.do">
								<display:column title="卡BIN" style="text-align:center" property="iid" headerClass="sortable" sortable="true" />
								<display:column title="卡BIN描述" style="text-align:center" property="iid_desc" headerClass="sortable" sortable="true" />
								<display:column title="映射卡BIN" style="text-align:center"  property="iid_map" headerClass="sortable" sortable="true"/>
								<display:column title="映射卡BIN描述" style="text-align:center"  property="iid_map_desc" headerClass="sortable" sortable="true"/>
								<display:column title="对应功能" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.func_flag=='0' }">批量发卡管理 </c:if>
									<c:if test="${displayTable.func_flag=='1' }">生成同福卡绑定的虚拟卡管理 </c:if>
								</display:column>
								<display:column title="有效期是否必填" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.needDt=='1' }">必填 </c:if>
									<c:if test="${displayTable.needDt=='0' }">非必填 </c:if>
								</display:column>								
								<display:column title="状态" style="text-align:center"  headerClass="sortable" sortable="true">
									<c:if test="${displayTable.isuse=='1' }">可用 </c:if>
									<c:if test="${displayTable.isuse=='0' }">停用 </c:if>
								</display:column>
								<display:column title="查看" style="width:5%;text-align:center">
									<a href="javascript:;" onclick="preQueryCrdformatMap('<c:out value="${displayTable.iid}"/>','<c:out value="${displayTable.func_flag}"/>')">查看</a>
								</display:column>								
								<display:column title="修改" style="width:5%;text-align:center">
									<a href="/crdformatMap.do?method=preModCrdformatMap&id=${displayTable.iid}&funcFlag=${displayTable.func_flag}">修改</a>
								</display:column>	
								<display:column title="删除" style="width:5%;text-align:center">
									<a href="javascript:;" onclick="deleteClick('<c:out value="${displayTable.iidAndFuncFlag}"/>')">删除</a>
								</display:column>														
							</display:table>
							<input type="hidden" name="id">
						</td>
						</tr>
					</table>
			</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
