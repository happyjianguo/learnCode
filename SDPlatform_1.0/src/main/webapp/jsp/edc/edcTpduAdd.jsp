<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>�ն�TPDU</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	<script language="javascript">
	function saveClick()
	{
		document.forms[0].method.value="createEdcTpdu";
		return validateEdcTpduForm(document.forms[0]);
	}
	function backClick()
	{
		document.forms[0].method.value="queryEdcTpdu";
	}
	function updatePackTypeno(){
	    var packType=document.forms[0].packType.value;
	    if(packType=="STD"){
	       document.forms[0].ch_packTypeno.value="ֱ��";
	       document.forms[0].packTypeno.value="1";
	    }
	    if(packType=="CMB"){
	       document.forms[0].ch_packTypeno.value="���д���";
	       document.forms[0].packTypeno.value="2";
	    }
	    if(packType=="CITIC"){
	       document.forms[0].ch_packTypeno.value="���Ŵ���";
	       document.forms[0].packTypeno.value="3";
	    }
	}
	/********TPDU��֤*******/
	var req;
	function init() {
		if(window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	function checkEdcTpdu(tpdu) {
		if(tpdu==null||tpdu==""){
		   alert("TPDU����Ϊ��!!!");
		}else{
		   init();
			var url="edcCheck.do?method=checkEdcTpdu&tpdu="+tpdu;
			req.open("POST", url, true);
			req.onreadystatechange = callback;
			req.send(null);
		}
    }
	function callback() {
	 if(4 == req.readyState) {
		if(200 == req.status) {
			if(req.responseText=='false'){
			  alert("TPDU�Ѿ����ڣ����������룡����");
			  document.forms[0].tpdu.value='';
			}
		}
	  }
    }
	</script>
</head>
<shiro:lacksPermission name="posp:edctpdu:add">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:edctpdu:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				�����ն�TPDU
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="edcTpduForm" />
				<html:errors />
				<html:form action="/edcTpdu">

					<html:hidden property="method" value="saveEdcTpdu" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">

						<tr>
							<td class="table2_td_title">
								TPDU:
							</td>
							<td class="table2_td">
								<html:text property="tpdu"  size="19" maxlength="10" onblur="checkEdcTpdu(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��������:
							</td>
							<td class="table2_td">
								<html:select property="posLinkType">
								 <option value="0">ֱ��</option>
								 <option value="1">����</option>
								 <option value="2">����</option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�����ţ�
							</td>
							<td class="table2_td">
								<html:text property="chnlno" size="19" maxlength="3" value="100" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								ģ��ţ�
							</td>
							<td class="table2_td">
								<html:select property="moduleId">
								<c:forEach items="${moduleIdList}" var="model">
									<html:option value = "${model.paramValue }">${model.id.paramName }</html:option>
								</c:forEach>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�������ͣ�
							</td>
							<td class="table2_td">
								<html:select property="packType" onchange="updatePackTypeno()">
								 <option value="STD">ֱ��</option>
								 <option value="CMB">���д���</option>
								 <option value="CITIC">���Ŵ���</option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�������ͺţ�
							</td>
							<td class="table2_td">
								<html:text property="ch_packTypeno" size="19" value="ֱ��" maxlength="10" readonly="true"/>
								<font color="red">*</font>
								<html:hidden property="packTypeno" value="1"/>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()">
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()">
							</td>
						</tr>
					</table>
					<!-- ά����ͼ״̬�������� -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<input type="hidden" name="search_name" value="<c:out value="${param.search_name}"/>" />
					<!-- ά����ͼ״̬�������� -->
					</html:form>
			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>