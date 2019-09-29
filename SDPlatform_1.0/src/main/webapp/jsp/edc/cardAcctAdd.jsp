<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>卡账号管理</title>
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
		document.forms[0].method.value="createCardAcct";
		return validateCardAcctForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryCardAcct";
	}
	
	function check(str,name)
	{
	  var reg =/^\d+(\.{0,1}\d+){0,1}$/; 
	  if(str!=null&&str!=""&&!reg.test(str)){
	     alert("数据格式不正确，请重新输入！！！");
		 if(name=="depositAmt"){
		   document.forms[0].depositAmt.value='';
		 }if(name=="cardAmt"){
		   document.forms[0].cardAmt.value='';
		 }if(name=="authAmt"){
		   document.forms[0].authAmt.value='';
		 }if(name=="authTotal"){
		   document.forms[0].authTotal.value='';
		 }if(name=="balance"){
		   document.forms[0].balance.value='';
		  }  
	     }
     }
	
	/********卡号验证*******/
	var req;
	function init() {
		if(window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	function checkCardNo(cardNo) {
	    if(cardNo==null||cardNo==""){
	        alert("卡号不能为空");
	    }else{
			init();
			var url="edcCheck.do?method=checkCardNo&cardNo="+cardNo;
			req.open("POST", url, true);
			req.onreadystatechange = callback;
			req.send(null);
		}
    }
	function callback() {
	 if(4 == req.readyState) {
		if(200 == req.status) {
			if(req.responseText=='false'){
			  alert("卡号已经存在，请重新输入！！！");
			  document.forms[0].cardNo.value='';
			}
		}
	  }
    }
	</script>
</head>

<body>
	<!--------------以下Table为路径-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">
				当前位置：收单系统管理平台 >> 终端机管理 >> 卡账号管理
			</td>
		</tr>
		<tr>
			<td height="10">
			</td>
		</tr>
	</table>
	<!--------------Table为路径结束-------->


	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				新增卡账号
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="cardAcctForm" />
				<html:errors />
				<html:form action="/cardAcct">

					<html:hidden property="method" value="saveCardAcct" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">

						<tr>
							<td class="table2_td_title">
								卡号:
							</td>
							<td class="table2_td">
								<html:text property="cardNo" size="19" maxlength="19" onblur="checkCardNo(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								有效期:
							</td>
							<td class="table2_td">
								<html:text property="expire" size="19" maxlength="4" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								状态:
							</td>
							<td class="table2_td">
								<html:select property="cardStat">
								 <option value="0" >正常</option>
								 <option value="1" >冻结</option>
								 <option value="2" >止付</option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								Master卡号:
							</td>
							<td class="table2_td">
								<html:text property="masterCardNo" size="19" maxlength="19" onkeyup="this.value=this.value.replace(/\D/g,'')" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								MAster卡状态:
							</td>
							<td class="table2_td">
								<html:text property="masterStat" size="19" maxlength="1" onkeyup="this.value=this.value.replace(/\D/g,'')" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								附属标志:
							</td>
							<td class="table2_td">
								<html:text property="attachFlag" size="19" maxlength="1" onkeyup="this.value=this.value.replace(/\D/g,'')" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								卡帐号:
							</td>
							<td class="table2_td">
								<html:text property="acctNo" size="19" maxlength="22" onkeyup="this.value=this.value.replace(/\D/g,'')" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								存款次数:
							</td>
							<td class="table2_td">
								<html:text property="depositCnt" size="19" maxlength="5" onkeyup="this.value=this.value.replace(/\D/g,'')" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								存款金额:
							</td>
							<td class="table2_td">
								<html:text property="depositAmt" size="19" maxlength="10" onblur="check(this.value,'depositAmt')" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								信用金额:
							</td>
							<td class="table2_td">
								<html:text property="cardAmt" size="19" maxlength="10" onblur="check(this.value,'cardAmt')" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								当日已授权次数:
							</td>
							<td class="table2_td">
								<html:text property="authCnt" size="19" maxlength="5" onkeyup="this.value=this.value.replace(/\D/g,'')" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								当日已授权金额:
							</td>
							<td class="table2_td">
								<html:text property="authAmt" size="19" maxlength="10" onblur="check(this.value,'authAmt')" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								已授权金额:
							</td>
							<td class="table2_td">
								<html:text property="authTotal" size="19" maxlength="10" onblur="check(this.value,'authTotal')" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								帐户余额:
							</td>
							<td class="table2_td">
								<html:text property="balance" size="19" maxlength="10" onblur="check(this.value,'balance')" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								持卡人:
							</td>
							<td class="table2_td">
								<html:text property="owner" size="19" maxlength="20" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								身份证:
							</td>
							<td class="table2_td">
								<html:text property="personId" size="19" maxlength="18"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								联系地址:
							</td>
							<td class="table2_td">
								<html:text property="address" size="19" maxlength="30" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								工作单位:
							</td>
							<td class="table2_td">
								<html:text property="job" size="19" maxlength="30" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								联系电话:
							</td>
							<td class="table2_td">
								<html:text property="telephone" size="19" maxlength="15" />
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
					<!-- 维护视图状态的隐藏域 -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<input type="hidden" name="search_name" value="<c:out value="${param.search_name}"/>" />
					<!-- 维护视图状态的隐藏域 -->
					</html:form>
			</td>
		</tr>
	</table>
</body>

</html:html>



