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

	<title>��ʾ�б�ҳ��</title>

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
	function addConfirm(){
		var sysParameterForm = document.all("sysParameterForm");
		sysParameterForm.action = "<%=path%>/sysParameters.do?method=preAddSysParameter";
		sysParameterForm.submit();
	}
	
	function query(){
		var sysParameterForm = document.all("sysParameterForm");
		sysParameterForm.action = "<%=path%>/sysParameters.do?method=getSysParameterList";
		sysParameterForm.submit();
	}		
	function deleteClick(id){
		if(confirm("ȷ��Ҫɾ�����Ϊ"+id+"�Ĳ�����Ϣ��")){
			document.getElementById("id").val=id;
			
			var sendUrl="<%=path%>/sysParameters.do?method=sysParameterHasChild&id="+id;
			startAjax(sendUrl);
		}
	}
	
	var xmlHttp;    
	function createXMLHttpRequest() {    
	    if (window.ActiveXObject) {    
	        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");    
	    }    
	    else if (window.XMLHttpRequest) {    
	        xmlHttp = new XMLHttpRequest();    
	    }    
	}    
	var okFunc = function(){  
		
	    if(xmlHttp.readyState == 4) {  
	    	
	        if(xmlHttp.status == 200) { 
	        	
	           if(xmlHttp.responseText==0){
	        	   var id=document.getElementById("id").val;
	        	    var sysParameterForm = document.all("sysParameterForm");
		   			sysParameterForm.action = "<%=path%>/sysParameters.do?method=delSysParameter&id="+id;
		   			sysParameterForm.submit();	
		   		
	           }else if(xmlHttp.responseText>0){
	        	   alert("�˲��������Ӳ��������Ƚ��Ӳ���ɾ���ٽ��в�����");
	           }else{
	        	   alert("ɾ��ʧ�ܣ�");
	           }      
	        }    
	    }    
	}    
	var startAjax = function(sendUrl){    
	     
	    createXMLHttpRequest();    
	    if( !xmlHttp){    
	        return alert("ɾ��ʧ�ܣ�");    
	    } 
	    
	    xmlHttp.open("POST", sendUrl, true); 
	    
	    xmlHttp.onreadystatechange = okFunc; 
	    
	    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");    
	    xmlHttp.send(null);    
	}
</script>

<body>
	<html:form action="/sysParameters?method=getSysParameterList" method="post" styleId="sysParameterForm">
		<input type="hidden" id="id" name="id" value=""/> 
		<bean:define id="menu_level" name="menu_level" />
		<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
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
								&nbsp;&nbsp;��ǰλ�ã��������� &gt; ���۵㿪������
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
							<td coslpan="10"><font color="red">${info}</font></td>
						</tr>
						<tr class="serch">
							<%-- <td style="white-space: nowrap" align="right">
								��������
							</td>
							<td style="white-space: nowrap" align="left">
								<html:select property="queryParamType" onchange="query();">
									<html:option value="">����ѡ��</html:option>
									<logic:present name="sysParameterTypeList">
										<html:optionsCollection name="sysParameterTypeList"
											label="param_type" value="param_type" />
									</logic:present>
								</html:select>						
							</td> --%>
							<td style="white-space: nowrap" align="right">
								��������
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="queryParamName" maxlength="30" size="16" />
							</td>
							<td style="white-space: nowrap" align="right"></td>
							<td style="white-space: nowrap" align="left"></td>
							<td style="white-space: nowrap" align="right"></td>
							<td style="white-space: nowrap" align="left"></td>
							<td style="white-space: nowrap" align="right"></td>
							<td style="white-space: nowrap" align="left"></td>
							<%-- <td style="white-space: nowrap" align="right">
								���������
							</td>
							<td style="white-space: nowrap" align="left">
								<html:text property="queryParentId" maxlength="15" size="16" />
							</td>								
							<td style="white-space: nowrap" align="right">
								�Ƿ����
							</td>							
							<td style="white-space: nowrap" align="left">
								<html:select property="queryEnable">
									<html:option value="">����ѡ��</html:option>
									<html:option value="1">����</html:option>
									<html:option value="0">������</html:option>
								</html:select>							
							</td> --%>
							<td style="white-space: nowrap" align="left">
								<input type="button" class="button" onClick='return query()'
									style="background-image: url(<%=path%>/image1/border/Check_button.gif)">
							</td>
							<td>			
							<shiro:hasPermission name="cardbatch:sysParameter:add">
								<input type="button" class="button"
										onClick='return addConfirm()'
										style="background-image: url(<%=path%>/image1/border/New_button.gif)">
							</shiro:hasPermission>
													
							</td>
						</tr>
						<tr>
						<td colspan="10">
							<display:table name="SysParameterList" partialList="true" size="pageBean.totalRecords" pagesize="10" style="width:100%" class="dpTable" cellpadding="0" cellspacing="0" id="displayTable" requestURI="/sysParameters.do">
								<display:column title="�������" style="text-align:center" property="param_id" headerClass="sortable" sortable="true" />
								<display:column title="��������" style="text-align:center" property="param_type" headerClass="sortable" sortable="true" />
								<display:column title="��������" style="text-align:center"  property="param_name" headerClass="sortable" sortable="true"/>
								<display:column title="����ֵ" style="text-align:center"  property="param_value" headerClass="sortable" sortable="true"/>								
								<display:column title="��������" style="text-align:center"  property="param_notes" headerClass="sortable" sortable="true"/>																
								<display:column title="���������" style="text-align:center"  property="parent_id" headerClass="sortable" sortable="true"/>
								<display:column title="�Ƿ����" style="text-align:center">
									<c:if test="${displayTable.enable=='0' }">������</c:if>
									<c:if test="${displayTable.enable=='1' }">����</c:if>										
								</display:column>														
								<display:column title="�Ƿ񿪿�" style="text-align:center">
									<c:if test="${displayTable.is_enablement=='0' }">��</c:if>
									<c:if test="${displayTable.is_enablement=='1' }">��</c:if>										
								</display:column>														
								<display:column title="�鿴" style="text-align:center" href="/sysParameters.do?method=preQuerySysParameter" paramId="id" paramProperty="param_id">
									<img border="0" src="<%=path%>/image1/border/query.png" />
								</display:column>
								<%-- <display:column title="�޸�" style="text-align:center" href="/sysParameters.do?method=preModSysParameter" paramId="id" paramProperty="param_id">
									�޸�
								</display:column> --%>
								<display:column title="ɾ��" style="width:5%;text-align:center">
									<a href="javascript:;" onclick="deleteClick('<c:out value="${displayTable.param_id}"/>')">ɾ��</a>
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
