<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*,cn.yufu.posp.terminalmanager.domain.model.*" isELIgnored="false"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>POSǩ����Ҫ�����ù���</title>
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
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />jquery-1.4.min.js" ></script>
	<script language="javascript">

	function saveClick()
	{	
	var hotline=$("input[name='hotline']").val();
	var telSupport=$("input[name='telSupport']").val();
	var adInfo=$("textarea[name='adInfo']").val();
	var adYesNoFlag=$("select[name='adYesNoFlag']").val();
	var status=$("select[name='status']").val();
	
	if(hotline==null||hotline==""){
	    alert("�������ߵ绰����Ϊ�գ����������룡");
	    $("input[name='hotline']").focus();
	    return false;
	}else if(telSupport==null||telSupport==""){
	    alert("����֧�ֵ绰����Ϊ�գ����������룡");
	    $("input[name='telSupport']").focus();	    
	    return false;
	}else if(11!=telSupport.length){
	    alert("����֧�ֵ绰����Ϊ11λ�����������룡");
	    $("input[name='telSupport']").focus();
	    return false;  
	}else if(adInfo==null||adInfo==""){
	    alert("������ݲ���Ϊ�գ����������룡");
	    $("input[name='adInfo']").focus();	    
	    return false;
	}else{
	    var flag1 = /<s>/g;
	    var flag2 = /<\/s>/g;
	    var flag3 = /<b>/g;
	    var flag4 = /<\/b>/g;
	    var result1 = adInfo.match(flag1);
	    var result2 = adInfo.match(flag2);
	    var result3 = adInfo.match(flag3);
	    var result4 = adInfo.match(flag4);
		if((result1.length>0&&result2==null)||(result2.length>0&&result1==null)){
			alert("s��ǩ��ɶԴ���");
		return false;
		} 
		if((result3.length>0&&result4==null)||(result4.length>0&&result3==null)){
			alert("b��ǩ��ɶԴ���");
		return false;
		}
		
		if(result1.length!=result2.length){
			alert("s��ǩ��ɶԴ���");
		return false;
		}
		if(result3.length!=result4.length){
			alert("b��ǩ��ɶԴ���");
		return false;
		}
	
		document.forms[0].method.value="saveItem";
	 }
	}

	
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}

	//���������Ч��ʾ
	function test(){
	  	{
		var temp=$("textarea[name='adInfo']").val();
		temp = temp.replace(/#/g,"<br/>");
		temp = temp.replace(/<b>/g,"<b><font color=#FF0000>");
		temp = temp.replace(/<\/b>/g,"</font></b>");
		temp = temp.replace(/<s>/g,"<small>");
		temp = temp.replace(/<\/s>/g,"</small>");
		temp = temp + "<TITLE>���������Ч��ʾ</TITLE>";
		testwin= open("", "testwin","status=no,menubar=yes,toolbar=no");
		testwin.document.open();
		testwin.document.write(temp);
		testwin.document.close();
		}
	}
	</script>
</head>
<shiro:lacksPermission name="posp:posReceiptInfo:edit">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:posReceiptInfo:edit">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				�޸�POSǩ����Ҫ������
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="posReceiptInfoForm" />
				<html:errors />
				<html:form action="/posReceiptInfo">

					<html:hidden property="method" value="saveItem" />
					
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						
	
						<tr>
							<td class="table2_td_title">
								�������ߵ绰:
							</td>
							<td class="table2_td">
								<html:text property="hotline" size="20" maxlength="15" onkeyup="this.value=this.value.replace(/[^\d\-]/g,'');"/>
								<font color="red">*</font>
							    <html:hidden property="hotline"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								����֧�ֵ绰:
							</td>
							<td class="table2_td">
								<html:text property="telSupport" size="20" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'');"/>
								<font color="red">*</font>
								<html:hidden property="telSupport"/>
							<br></td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�������:
							</td>
							<td class="table2_td">
								<html:textarea property="adInfo" rows="10" cols="25" /><font color="red">*</font>&nbsp;&nbsp;<b><font color=#FF0000>#--���б�ǩ;&nbsp;&#60;b&#62;&#60;/b&#62;--�ӴּӴ��ǩ��b����Сд;&nbsp;&#60;s&#62;&#60;/s&#62;--��С��ǩ��s����Сд;</font></b>
								<BR><INPUT  type="button" value="���������Ч��ʾ" onclick="test();">   
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��ӡ����־:
							</td>
							<td class="table2_td">
								<html:select property="adYesNoFlag" style="width:85px;">
									<html:option value="0">����ӡ</html:option>
									<html:option value="1">��ӡ</html:option>
								</html:select><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								״̬��ʶ:
							</td>
							<td class="table2_td">
								<html:select property="status" style="width:85px;">
									<html:option value="1">��Ч</html:option>
								</html:select><font color="red">*</font>
							</td>
						</tr>
						<html:hidden property="createDate"/>
						<html:hidden property="updateOper"/>

						
						<tr>
							<td align="center" colspan="4" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()" />
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()" />
							</td>
						</tr>
						
					</table>

					<!-- ά����ͼ״̬�������� -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<!-- ά����ͼ״̬�������� -->
				</html:form>

			</td>
		</tr>
	</table>

	
</body>
</shiro:hasPermission>
</html:html>




