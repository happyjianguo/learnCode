<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>添加终端信息</title>
<!-- 样式所需导入的链接 -->
<%@ include file="../inner/css.jsp"%>
<!-- 样式所需导入的链接 -->
<script type="text/javascript">
$().ready(function() {
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	$('#closeBtn').click(function() {
		parent.layer.close(index);
		//$("#form", window.parent.document).submit();
	});
	$("#form").validate({
		onsubmit : true,// 是否在提交是验证
		onfocusout : false,// 是否在获取焦点时验证
		onkeyup : false,// 是否在敲击键盘时验证
		rules : {
			merchantId : {required : true},termCode : {required : true},softVer : {required : true},state : {required : true},termAddress : {required : true},
			province : {required : true},cityNo : {required : true},zone : {required : true},termTel : {required : true},xTimezone : {required : true,number : true},
			upgradeDate : {required : true},upgradeVersion : {required : true},
			storeContacts : {required : true},storePhone : {required : true},
			merchantAdvisor : {required : true},terminalArea : {required : true},terminalPosition : {required : true}
		},
		//提交表单
		submitHandler : function(form) {
			var termCode=document.getElementsByName("termCode")[0].value;			
			if(termCode!=''&&termCode.length!=8){
				alert("终端号必须是八位");
				return false;
			}else{
				if("APP" == $("#edcType").val()){
					if(!checkRate(termCode)){
						alert("终端设备型号为APP类型，终端号必须是由大写首字母+数字组成");
						return false;
					}
				}else{
					if(isNaN(termCode)){
						alert("终端设备型号为非APP类型，终端号必须是由数字组成");
						return false;
					}
				}
			}			
			var param = $("#form").serialize();
			url = $("#form").attr("action");
			$.ajax({
				url : url,
				type : "post",
				data : param,
				dataType : "json",
				success : function(data) {
					if (data.result == '0') {
						layer.alert(data.resultMsg, 1);
					} else {
						layer.alert(data.resultMsg);
					}
				}
			});
		}
	});
});
function checkRate(nubmer){  
   //var re =  /^[0-9a-zA-Z]*$/g;  //判断字符串是否为数字和字母组合     //判断正整数 /^[1-9]+[0-9]*]*$/    
   var re =  /^[A-Z]+[0-9]*$/g;  //判断字符串是否为数字和字母组合     //判断正整数 /^[1-9]+[0-9]*]*$/    
   if (!re.test(nubmer)){  
      return false;  
   }else{  
  	return true;  
   }  
} 
var getCityList = function() {
	$.ajax({
		url : '${ctx }/terminalSDMT/getAreaList',
		type : "post",
		data : {
			fid : $("#province").val(),
			isuse: '1'
		},
		dataType : "json",
		success : function(data) {
			$("#cityNo option").remove();
			$("#cityNo").append("<option value=''>--请选择--</option>");
			$("#zone option").remove();
			$("#zone").append("<option value=''>--请选择--</option>");			
			if (data.length != 0) {
				$.each(data, function(i, info) {
					$("#cityNo").append(
							"<option title='"+info.id+"' value="+info.id+">"
									+ info.id + "---"
									+ info.provinceCity + "</option>");
				});
			}
		}
	})
};

var getZoneList = function() {
	$.ajax({
		url : '${ctx }/terminalSDMT/getAreaList',
		type : "post",
		data : {
			fid : $("#cityNo").val(),
			isuse: '1'
		},
		dataType : "json",
		success : function(data) {
			$("#zone option").remove();
			$("#zone").append("<option value=''>--请选择--</option>");
			if (data.length != 0) {
				$.each(data, function(i, info) {
					$("#zone").append(
							"<option title='"+info.id+"' value="+info.id+">"
									+ info.id + "---"
									+ info.provinceCity + "</option>");
				});
			}
		}
	})
};

var getMerchantName = function() {
	$.ajax({
		url : '${ctx }/terminalSDMT/getMerchantName',
		type : "post",
		data : {
			merchantId : $("#merchantId").val()
		},
		dataType : "json",
		success : function(data) {
			if (data!=null&&data!=''&&data!='merchantIdIsNull') {
				$("#merchantName").val(data.mrchtName);
			}else if(data==''){
		      	alert("商户编号不存在，请重新输入！！！");
		      	$("#merchantId").val('');
		      	$("#merchantName").val('');			      	
			}else if(data=='merchantIdIsNull'){
				alert("商户编号在福卡前置、X平台、老福卡系统中没有同时存在，请重新输入！！！");
		      	$("#merchantId").val('');
		      	$("#merchantName").val('');	
			}else if(data==null){
		      	alert("商户编号不存在，请重新输入！！！");
		      	$("#merchantId").val('');
		      	$("#merchantName").val('');	
			}
		}
	})
};
var getAdvList = function() {
	findListByRoleId("0",$("#terminalArea").val());
};
function findListByRoleId(roleId,merchantArea){
	if(merchantArea!=""){
		$.ajax({
			url : '${ctx }/merchantSDMT/findListByRoleId',
			type : "post",
			data : {
				roleId : roleId,
				managerArea: merchantArea
			},
			dataType : "json",
			success : function(data) {
				$("#merchantAdvisor").empty();
				$("#merchantAdvisor").append("<option value=''>--请选择--</option>");	
				$("#merchantAdvisor").val('');
				if (data.length != 0) {
					$.each(data, function(i, info) {
						$("#merchantAdvisor").append(
							"<option title='"+info.managerName+"' value='"+info.id+"'>" + info.managerName + "</option>");
					});
				}
			}
		})
	}
};

var checkTerminalKey = function() {
	$.ajax({
		url : '${ctx }/terminalSDMT/checkTerminalKey',
		type : "post",
		data : {
			merchantId : $("#merchantId").val(),
			termCode:	$("#termCode").val()
		},
		dataType : "text",
		success : function(data) {
			if (data!=null&&data!='') {
				if(data=='1'){
			      	alert("与中间库终端号重复，请重新输入！");
			      	$("#termCode").val('');
				}else if(data=='2'){
			      	alert("与老福卡系统终端号重复，请重新输入！");
			      	$("#termCode").val('');
				}else if(data=='3'){
			      	alert("与X平台终端号重复，请重新输入！");
			      	$("#termCode").val('');
				}
			}
		}
	})
};
</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">添加终端信息</div>
					<div class="sh_cm">
						<form id="form" action="${ctx }/terminalSDMT/save" method="post">
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">商户编号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="merchantId" name="merchantId"  maxlength="15"
										 onkeyup="this.value=this.value.replace(/\D/g,'');" onblur="getMerchantName();"/>
										 							
									</td>
									<td style="text-align: left;">商户名称：</td>
									<td style="text-align: left;">	
										<input class="inputext_style" id="merchantName" name="merchantName" readonly="readonly"/>										 							
									</td>									
								</tr>
								<tr>									
									<td style="text-align: left;">终端编号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="termCode" name="termCode"  maxlength="8" 
										onblur="checkTerminalKey();"/>								
										<!-- 
										<input class="inputext_style" id="termCode" name="termCode"  maxlength="8" 
										onkeyup="this.value=this.value.replace(/\D/g,'')" onblur="checkTerminalKey();"/> 
										-->								
									</td>
									<td style="text-align: left;">终端设备型号：</td>
									<td style="text-align: left;">
								        <select class="inputext_style" id="edcType" name="edcType">
											 <option value="STD">STD</option>
											 <option value="APP">APP</option>
								             <option value="CMB">CMB</option>
								             <option value="CITIC">CITIC</option>
								             <option value="CITIC1">CITIC1</option>
								             <option value="HYPT7">HYPT7</option>
								             <option value="SPECS900">SPECS900</option>
								             <option value="STAR">STAR</option>
								             <option value="SHARE">SHARE</option>
								        </select>																
									</td>									
								</tr>
								<tr>
									<td style="text-align: left;">终端类型：</td>
									<td style="text-align: left;">common</td>
									<input type="hidden" id="softVer" name="softVer"  maxlength="10" value="common"/>
										<!-- <input class="inputext_style" id="softVer" name="softVer"  maxlength="10" />								
									</td> -->								
									<td style="text-align: left;">参数下载标志：</td>
									<td style="text-align: left;">
								        <select class="inputext_style" id="downloadFlag" name="downloadFlag">
											 <option value="0">需要安装</option>
								             <option value="1">无需安装</option>
								        </select>																	
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">终端所在位置：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="termAddress" name="termAddress"  maxlength="30" />								
										<!-- <input class="inputext_style" id="termAddress" name="termAddress"  maxlength="10" /> -->
										<!-- 终端所在位置原始长度10，现在30 -->								
									</td>
									<td style="text-align: left;">省份：</td>
									<td style="text-align: left;">
										<select id="province" name="province" class="inputext_style" onchange="getCityList();">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${proviceList}">
												<option value="${model.id}">
													${model.id}---${model.provinceCity}
												</option>
											</c:forEach>
										</select>																	
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">密钥生效日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})" 
											name="actDate" id="actDate" maxlength="8" value="${info.actDate}"/>																		
									</td>								
									<td style="text-align: left;">城市：</td>
									<td style="text-align: left;">
										<select id="cityNo" name="cityNo" class="inputext_style" onchange="getZoneList();">
											<option value="">--请选择--</option>		
										</select>								
									</td>									
								</tr>								
								<tr>
									<td style="text-align: left;">密钥生效时间：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'HHmmss',isShowClear:false,readOnly:true})" 
											name="actTime" id="actTime" maxlength="8" value="${info.actTime}"/>																		
									</td>
									<td style="text-align: left;">区：</td>
									<td style="text-align: left;">
										<select id="zone" name="zone" class="inputext_style">
											<option value="">--请选择--</option>		
										</select>							
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">增加时间：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})" 
											name="addDate" id="addDate" maxlength="8" value="${info.addDate}"/>
									</td>
									<td style="text-align: left;">终端使用的电话号码</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="termTel" name="termTel"  maxlength="20" onkeyup="this.value=this.value.replace(/\D/g,'')"/>																	
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">裕福原有状态：</td>
									<td style="text-align: left;">				
										<input class="inputext_style" id="state" name="state"  maxlength="5" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
									</td>
									<td style="text-align: left;">费率(单位：%)：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="xTimezone" name="xTimezone"  maxlength="10" value="${info.xTimezone}"/>								
									</td>									
								</tr>
								<tr>
									<td style="text-align: left;">消费场景：</td>
									<td style="text-align: left;">
										<select id="consumpCategory" name="consumpCategory" class="inputext_style" value="${info.consumpCategory}">
											<c:forEach var="model" items="${consumpList}">
												<option value="${model.paramValue}" <c:if test="${model.paramValue eq info.consumpCategory}">selected="selected"</c:if>>
													${model.paramValue}---${model.paramName}
												</option>
											</c:forEach>
										</select>						
									</td>
									<td style="text-align: left;"></td>
									<td style="text-align: left;"></td>									
								</tr>								
								<tr>
									<td><div class="sh_title">添加终端补充信息</div></td><td><div class="sh_title"></div></td>
									<td><div class="sh_title"></div></td><td><div class="sh_title"></div></td>
								</tr>
								<tr>
									<td style="text-align: left;">升级日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" readonly="readonly"
										onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
										name="upgradeDate" id="upgradeDate" maxlength="8" value="${info.upgradeDate}" /></td>
									<td style="text-align: left;">门店电话：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="storePhone" name="storePhone" maxlength="16" onkeyup="this.value=this.value.replace(/\D/g,'')" />
									</td>
									<!-- 升级版本号： -->
									<input type="hidden" value="0" id="upgradeVersion" name="upgradeVersion"/>
									<!-- 
									<td style="text-align: left;">升级版本号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="upgradeVersion" name="upgradeVersion" maxlength="8" value="0" readonly="readonly"/>
									</td> 
									-->
								</tr>
								<tr>
									<td style="text-align: left;">区域：</td>
									<td style="text-align: left;">
										<select id="terminalArea" name="terminalArea" class="inputext_style" onchange="getAdvList();">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${merchantAreaList}">
												<option value="${model.value}">${model.label}</option>
											</c:forEach>
										</select>
										<!-- <input type="hidden" value="" id="terminalArea" name="terminalArea"/> -->
									</td>
									<td style="text-align: left;">门店联系人：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="storeContacts" name="storeContacts" maxlength="30" />
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">商户顾问：</td>
									<td style="text-align: left;">
										<select id="merchantAdvisor" name="merchantAdvisor" class="inputext_style" >
										</select>
									</td>
									<td style="text-align: left;">终端装机地址:</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="terminalPosition" name="terminalPosition" maxlength="30" />
									</td>
								</tr>
								<tr>
									<td style="text-align: center;" colspan="4">
										<input type="submit" id="submit" value="提交" class="button" />
										<input type="button" id="closeBtn" value="关闭" class="button" />										
									</td>
								</tr>
							</table>
						</form>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>