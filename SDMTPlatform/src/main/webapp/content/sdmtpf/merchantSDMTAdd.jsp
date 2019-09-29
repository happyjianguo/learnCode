<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>添加商户信息</title>
<!-- 样式所需导入的链接 -->
<%@ include file="../inner/css.jsp"%>
<!-- 样式所需导入的链接 -->
<script type="text/javascript">
	$().ready(function(){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		$('#closeBtn').click(function() {
			parent.layer.close(index);
		});
		$("#form").validate({
			onsubmit : true,// 是否在提交是验证
			onfocusout : false,// 是否在获取焦点时验证
			onkeyup : false,// 是否在敲击键盘时验证
			rules : {
				merchantId : {
					required : true
				},
				mrchtName : {
					required : true
				},
				merchantEname : {
					required : true
				},
				abbrCname : {
					required : true
				},
				abbrEname : {
					required : true
				},
				address : {
					required : true
				},
				province : {
					required : true
				},
				cityNo : {
					required : true
				},
				zone : {
					required : true
				},
				bis : {
					required : true
				},
				manager : {
					required : true
				},
				telephone : {
					required : true
				},
				addDate : {
					required : true
				},
				accName : {
					required : true
				},
				accNo : {
					required : true,
					number : true
				},
				bankName : {
					required : true
				},
				accNickName : {
					required : true
				},
				shortNickName : {
					required : true
				},
				bankNo : {
					required : true,
					number : true
				},
				lastSettleDate : {
					required : true
				},
				agent : {
					required : true
				},
				idNo : {
					required : true
				},
				lastSettleDate : {
					required : true
				},
				legalRep : {
					required : true
				},
				lrIdNo : {
					required : true
				},
				lrIdValidity : {
					required : true
				},
				busLicNo : {
					required : true
				},
				busLicValidity : {
					required : true
				},
				taxId : {
					required : true
				},
				taxIdValidity : {
					required : true
				},
				orgValidity : {
					required : true
				},
				enterpriseNo : {
					required : true
				},
				orgId : {
					required : true
				},
				contractStartDate : {
					required : true
				},
				contractRenewalDate : {
					required : true
				},
				merchantCompanyName : {
					required : true
				},
				renewalType : {
					required : true
				},
				guaranteeLastDate : {
					required : true
				},
				guaranteeAmt : {
					required : true
				},
				marketContactPerson : {
					required : true
				},
				marketContactMobile : {
					required : true
				},
				/* financialContactPerson : {
					required : true
				},
				financialContactMobile : {
					required : true
				}, */
				merchantDeposit : {
					required : true
				},
				merchantArea : {
					required : true
				},
				storeManager : {
					required : true
				},
				merchantAdvisor : {
					required : true
				},
				contractEndDate : {
					required : true
				}
				,
				manName : {
					required : true
				},
				idType1 : {
					required : true
				},
				idNo1 : {
					required : true
				},
				idDeadline1 : {
					required : true
				},
				accXName : {
					required : true
				},
				merchant_x_type : {
					required : true
				},
				merchant_x_reg_amt : {
					required : true
				},
				merchant_x_code : {
					required : true
				},
				merchant_x_acc_type1 : {
					required : true
				}
			},
			//提交表单
			submitHandler : function(form) {
				$('#submit').attr("disabled","disabled");
				var merchantId = document
						.getElementsByName("merchantId")[0].value;
				if (merchantId != ''
						&& merchantId.length != 15) {
					alert("商户号必须是十五位");
					return false;
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
								layer.alert(data.resultMsg,1,
									function() {
										$("#form",window.parent.document).submit();
									});
							} else {
								layer.alert(data.resultMsg);
							}
							$('#submit').removeAttr("disabled");
						},
			        	error: function (XMLHttpRequest, textStatus, errorThrown) {
			        		$('#submit').removeAttr("disabled")
			        	}
					});
				}
			});
	});

	function clearNoNum(obj){ 
	    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
	    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
	    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数  
	    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
	    	if(obj.value.length >= 16){
	    		obj.value= parseFloat(obj.value.substring(0,15));
	        }else{
	        	obj.value= parseFloat(obj.value); 
	        }
	    }  else if(obj.value.indexOf(".") == 0 && obj.value !=""){
	    	obj.value="";
	    }else{
	    	if(obj.value.indexOf(".")+1 == obj.value.length){
				obj.value = obj.value.substring(0,obj.value.indexOf("."));
			}
	    	if(obj.value.indexOf(".") > 15){
	    		obj.value="";
	    	}
	    }
	}
	
	var getCityList = function() {
		$.ajax({
			url : '${ctx }/merchantSDMT/getAreaList',
			type : "post",
			data : {
				fid : $("#province").val(),
				isuse : '1'
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
										+ info.id + "---" + info.provinceCity
										+ "</option>");
					});
				}
			}
		})
	};

	var getZoneList = function() {
		$.ajax({
			url : '${ctx }/merchantSDMT/getAreaList',
			type : "post",
			data : {
				fid : $("#cityNo").val(),
				isuse : '1'
			},
			dataType : "json",
			success : function(data) {
				$("#zone option").remove();
				$("#zone").append("<option value=''>--请选择--</option>");
				if (data.length != 0) {
					$.each(data, function(i, info) {
						$("#zone").append(
								"<option title='"+info.id+"' value="+info.id+">"
										+ info.id + "---" + info.provinceCity
										+ "</option>");
					});
				}
			}
		})
	};

	var getManagerList = function() {
		findListByRoleId('1');
		findListByRoleId('0');
	};
	function findListByRoleId(roleId){
		var merchantArea=$.trim($("#merchantArea").val());
		if(merchantArea!=""){
			$.ajax({
				url : '${ctx }/merchantSDMT/findListByRoleId',
				type : "post",
				data : {
					roleId : roleId,
					managerArea: $("#merchantArea").val()
				},
				dataType : "json",
				success : function(data) {
					if(roleId=="1"){
						$("#storeManager").empty();
						$("#storeManager").append("<option value=''>--请选择--</option>");	
						$("#storeManager").val('');
						if (data.length != 0) {
							$.each(data, function(i, info) {
								$("#storeManager").append(
									"<option title='"+info.managerName+"' value='"+info.id+"'>" + info.managerName + "</option>");
							});
						}							
					}else if(roleId=="0"){
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
				}
			})
		}
	};
	var checkMerchantfId = function() {
		$.ajax({
			url : '${ctx }/merchantSDMT/checkMerchantfId',
			type : "post",
			data : {
				fmrchno : $("#fmrchno").val()
			},
			dataType : "text",
			success : function(data) {
				if (data != null && data != '' && data != '0') {
					if (data == '1') {
						alert("该商户编号在X平台未同步！");
					} else if (data == '3') {
						alert("该父商户编号不存在！");
					}
					$("#fmrchno").val('');
				}
			}
		})
	};
	var checkMerchantId = function() {
		$.ajax({
			url : '${ctx }/merchantSDMT/checkMerchantId',
			type : "post",
			data : {
				merchantId : $("#merchantId").val()
			},
			dataType : "text",
			success : function(data) {
				if (data != null && data != '' && data != '0') {
					if (data == '1') {
						alert("该商户编号在中间库已存在，请重新输入！");
					} else if (data == '2') {
						alert("该商户编号只在收单系统存在，请重新输入！");
					} else if (data == '3') {
						alert("该商户编号只在X平台系统存在，请重新输入！");
					} else if (data == '4') {
						alert("该商户编号只在老福卡系统存在，请重新输入！");
					}
					$("#merchantId").val('');
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
					<div class="sh_title">添加商户信息</div>
					<div class="sh_cm">
						<form id="form" action="${ctx }/merchantSDMT/save" method="post">
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">商户编号：</td>
									<td style="text-align: left;"><input
										class="inputext_style" id="merchantId" name="merchantId"
										maxlength="15"
										onkeyup="this.value=this.value.replace(/\D/g,'')"
										onblur="checkMerchantId();" /></td>
									<td style="text-align: left;">商户名称：</td>
									<td style="text-align: left;"><input
										class="inputext_style" id="mrchtName" name="mrchtName"
										maxlength="30" /> <!-- <input class="inputext_style" id="mrchtName" name="mrchtName"  maxlength="8" />	 -->
										<!-- 商户名称（目前8个字）扩充至30个 --></td>
								</tr>
								<tr>
									<td style="text-align: left;">商户名称(英)：</td>
									<td style="text-align: left;"><input
										class="inputext_style" id="merchantEname" name="merchantEname" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" 
										maxlength="20" /></td>
									<td style="text-align: left;">简称(中)：</td>
									<td style="text-align: left;"><input
										class="inputext_style" id="abbrCname" name="abbrCname"
										maxlength="4" /></td>
								</tr>
								<tr>
									<td style="text-align: left;">简称(英)：</td>
									<td style="text-align: left;"><input
										class="inputext_style" id="abbrEname" name="abbrEname" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"
										maxlength="8" /></td>
									<td style="text-align: left;">MCC：</td>
									<td style="text-align: left;"><select id="mccId"
										name="mccId" class="inputext_style">
											<c:forEach var="model" items="${mccList}">
												<option value="${model.id}">
													${model.id}---${model.descr}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: left;">地址：</td>
									<td style="text-align: left;"><input
										class="inputext_style" id="address" name="address"
										maxlength="30" /> <!-- <input class="inputext_style" id="address" name="address"  maxlength="10" /> -->
										<!-- 地址,扩充至30个 --></td>
									<td style="text-align: left;">省份：</td>
									<td style="text-align: left;"><select id="province"
										name="province" class="inputext_style"
										onchange="getCityList();">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${proviceList}">
												<option value="${model.id}">
													${model.id}---${model.provinceCity}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: left;">联系电话：</td>
									<td style="text-align: left;"><input
										class="inputext_style" id="telephone" name="telephone"
										maxlength="20"
										onkeyup="this.value=this.value.replace(/\D/g,'')" /></td>
									<td style="text-align: left;">城市：</td>
									<td style="text-align: left;"><select id="cityNo"
										name="cityNo" class="inputext_style" onchange="getZoneList();">
											<option value="">--请选择--</option>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: left;">联系人：</td>
									<td style="text-align: left;"><input
										class="inputext_style" id="manager" name="manager"
										maxlength="4" /></td>
									<td style="text-align: left;">区域：</td>
									<td style="text-align: left;"><select id="zone"
										name="zone" class="inputext_style">
											<option value="">--请选择--</option>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: left;">增加时间：</td>
									<td style="text-align: left;"><input type="text"
										class="inputext_style" readonly="readonly"
										onFocus="WdatePicker({dateFmt:'yyyyMMddHHmmss',isShowClear:false,readOnly:true})"
										name="addDate" id="addDate" maxlength="14"
										value="${info.addDate}" /></td>
									<td style="text-align: left;"></td>
									<td style="text-align: left;"></td>
								</tr>
								<tr>
									<td style="text-align: left;">结算账户开户名：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="accName" name="accName" maxlength="30" /> 
									<!-- <input class="inputext_style" id="accName" name="accName"  maxlength="10" /> -->
									<!-- 结算账户开户名 ,扩充至30个 --></td>
									<td style="text-align: left;">结算账号：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="accNo" name="accNo" maxlength="30" />
									<!-- <input class="inputext_style" id="accNo" name="accNo" maxlength="20" /> -->
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">是否是北京开户行:</td>
									<td style="text-align: left;">
									<select id="isBjAcct" name="isBjAcct" class="inputext_style">
											<option value="0">否</option>
											<option value="1">是</option>
									</select></td>
									<td style="text-align: left;">结算银行：</td>
									<td style="text-align: left;">
									<select id="bis" name="bis" class="inputext_style">
											<c:forEach var="model" items="${bankList}">
												<option value="${model.bankCode}">
													${model.bankCode}---${model.bankName}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: left;">开户银行名称：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="bankName" name="bankName" maxlength="30" /> 
									<!-- <input class="inputext_style" id="bankName" name="bankName"  maxlength="10" /> -->
										<!-- 开户银行名称,扩充至30个 --></td>
									<td style="text-align: left;">商户账号全称：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="accNickName" name="accNickName" maxlength="30" /></td>
									<!-- <input class="inputext_style" id="accNickName" name="accNickName" maxlength="15" /></td> -->
								</tr>
								<tr>
									<td style="text-align: left;">商户账号简称：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="shortNickName" name="shortNickName" maxlength="30" /></td>
									<td style="text-align: left;">开户银行行号：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="bankNo" name="bankNo" maxlength="16" /></td>
								</tr>
								<tr>
									<td style="text-align: left;">是否单独结算：</td>
									<td style="text-align: left;">
									<select id="individual" name="individual" class="inputext_style">
											<option value="0">否</option>
											<option value="1">是</option>
									</select></td>
									<td style="text-align: left;">上次结算日期：</td>
									<td style="text-align: left;">
									<input type="text" class="inputext_style" readonly="readonly"
										onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
										name="lastSettleDate" id="lastSettleDate" maxlength="8" value="${info.lastSettleDate}" /></td>
								</tr>
								<tr>
									<td style="text-align: left;">商户类型：</td>
									<td style="text-align: left;">
									<select id="typeYf" name="typeYf" class="inputext_style">
										<c:forEach var="model" items="${typeYfList}">
											<option value="${model.paramValue}">${model.paramName}</option>
										</c:forEach>
									</select>
									</td>
									<td style="text-align: left;">办理手续经办人姓名：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="agent" name="agent" maxlength="6" />
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">办理手续经办人证件类型：</td>
									<td style="text-align: left;">
									<select id="idType" name="idType" class="inputext_style">
											<option value="1">居民身份证</option>
											<option value="2">户口本</option>
											<option value="3">军人身份证</option>
											<option value="4">武装警察身份证</option>
											<option value="5">往来内地通行证</option>
											<option value="6">往来大陆通行证</option>
											<option value="7">护照</option>
											<option value="8">其他</option>
									</select></td>
									<td style="text-align: left;">办理手续经办人证件号码：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="idNo" name="idNo" maxlength="20" />
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">办理手续经办人证件有效期：</td>
									<td style="text-align: left;">
									<input type="text" class="inputext_style" readonly="readonly"
										onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
										name="idValidity" id="idValidity" maxlength="8" value="${info.idValidity}" /></td>
									<td style="text-align: left;">法定代表人（负责人）姓名：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="legalRep" name="legalRep" maxlength="6" /></td>
								</tr>
								<tr>
									<td style="text-align: left;">法定代表人证件类型：</td>
									<td style="text-align: left;">
									<select id="lrIdType" name="lrIdType" class="inputext_style">
											<option value="1">居民身份证</option>
											<option value="2">户口本</option>
											<option value="3">军人身份证</option>
											<option value="4">武装警察身份证</option>
											<option value="5">往来内地通行证</option>
											<option value="6">往来大陆通行证</option>
											<option value="7">护照</option>
											<option value="8">其他</option>
									</select></td>
									<td style="text-align: left;">法定代表人证件号码：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="lrIdNo" name="lrIdNo" maxlength="20" /></td>
								</tr>
								<tr>
									<td style="text-align: left;">法定代表人证件有效期：</td>
									<td style="text-align: left;">
									<input type="text" class="inputext_style" readonly="readonly"
										onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
										name="lrIdValidity" id="lrIdValidity" maxlength="8" value="${info.lrIdValidity}" /></td>
									<td style="text-align: left;">企业账号：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="enterpriseNo" name="enterpriseNo" maxlength="28" onkeyup="this.value=this.value.replace(/\D/g,'')" /></td>
								</tr>
								<tr>
									<td style="text-align: left;">控股股东或实际控制人姓名：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="manName" name="manName" maxlength="6" /></td>
									<td style="text-align: left;">控股股东或实际控制人证件种类：</td>
									<td style="text-align: left;">
									<select id="idType1" name="idType1" class="inputext_style">
											<option value="1">居民身份证</option>
											<option value="2">户口本</option>
											<option value="3">军人身份证</option>
											<option value="4">武装警察身份证</option>
											<option value="5">往来内地通行证</option>
											<option value="6">往来大陆通行证</option>
											<option value="7">护照</option>
											<option value="8">其他</option>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: left;">控股股东或实际控制人证件号码：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="idNo1" name="idNo1" maxlength="20" /></td>
									<td style="text-align: left;">控股股东或实际控制人证件有效期截止日：</td>
									<td style="text-align: left;">
									<input type="text" class="inputext_style" readonly="readonly"
										onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
										name="idDeadline1" id="idDeadline1" maxlength="8" value="${info.idDeadline1}" /></td>
								</tr>
								<tr>
									<td style="text-align: left;">营业执照号：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="busLicNo" name="busLicNo" maxlength="30" onkeyup="value=value.replace(/[^\w\/]/ig,'')" /></td>
									<td style="text-align: left;">营业执照名称：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="accXName" name="accXName" maxlength="20" /></td>
								</tr>
								<tr>
									<td style="text-align: left;">营业执照年检时间：</td>
									<td style="text-align: left;">
									<input type="text" class="inputext_style" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
										name="busLicValidity" id="busLicValidity" maxlength="8" value="${info.busLicValidity}" /></td>
									<td style="text-align: left;">税务登记证编号：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="taxId" name="taxId" maxlength="30" onkeyup="value=value.replace(/[^\w\/]/ig,'')"/>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">税务登记证年检时间：</td>
									<td style="text-align: left;"><input type="text" class="inputext_style" readonly="readonly"
										onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
										name="taxIdValidity" id="taxIdValidity" maxlength="8" value="${info.taxIdValidity}" /></td>
									<td style="text-align: left;">组织机构证编号：</td>
									<td style="text-align: left;"><input
										class="inputext_style" id="orgId" name="orgId" maxlength="30" onkeyup="value=value.replace(/[^\w\/]/ig,'')" />
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">组织机构证年检时间：</td>
									<td style="text-align: left;">
									<input type="text" class="inputext_style" readonly="readonly"
										onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
										name="orgValidity" id="orgValidity" maxlength="8" value="${info.orgValidity}" /></td>
									<td style="text-align: left;"></td>
									<td style="text-align: left;">
									</td>
								</tr>
								<tr>
									<td><div class="sh_title">添加商户补充信息</div></td><td><div class="sh_title"></div></td>
									<td><div class="sh_title"></div></td><td><div class="sh_title"></div></td>
								</tr>

								<tr>
									<td style="text-align: left;">合同签订日期：</td>
									<td style="text-align: left;">
									<input type="text" class="inputext_style" readonly="readonly"
										onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
										name="contractStartDate" id="contractStartDate" maxlength="8" value="${info.contractStartDate}" /></td>
									<td style="text-align: left;">合同续约日期：</td>
									<td style="text-align: left;">
									<input type="text" class="inputext_style" readonly="readonly"
										onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
										name="contractRenewalDate" id="contractRenewalDate" maxlength="8" value="${info.contractRenewalDate}" /></td>
								</tr>

								<tr>
									<td style="text-align: left;">商户公司名称：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="merchantCompanyName" name="merchantCompanyName" maxlength="60" /></td>
									<td style="text-align: left;">续约方式：</td>
									<td style="text-align: left;">
									<select id="renewalType" name="renewalType" class="inputext_style">
										<option value="">--请选择--</option>
											<c:forEach var="model" items="${renewalTypeList}">
												<option value="${model.value}">${model.label}</option>
											</c:forEach>
									</select></td>
								</tr>

								<tr>
									<td style="text-align: left;">保函到期日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" readonly="readonly"
										onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
										name="guaranteeLastDate" id="guaranteeLastDate" maxlength="8" value="${info.guaranteeLastDate}" /></td>
									<td style="text-align: left;">保函金额：</td>
									<td style="text-align: left;"><input
										class="inputext_style" id="guaranteeAmt" name="guaranteeAmt"  maxlength="10"
										onkeyup="this.value=this.value.replace(/\D/g,'')" /></td>
								</tr>

								<tr>
									<td style="text-align: left;">市场联系人：</td>
									<td style="text-align: left;"><input class="inputext_style" id="marketContactPerson"
										name="marketContactPerson" maxlength="200" /></td>
									<td style="text-align: left;">市场联系人电话：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="marketContactMobile" name="marketContactMobile" maxlength="14" onkeyup="this.value=this.value.replace(/\D/g,'')" />
									</td>
								</tr>

								<!-- <tr>
									<td style="text-align: left;">财务联系人：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="financialContactPerson" name="financialContactPerson" maxlength="200" />
									</td>
									<td style="text-align: left;">财务联系人电话：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="financialContactMobile" name="financialContactMobile" maxlength="14" onkeyup="this.value=this.value.replace(/\D/g,'')" />
									</td>
								</tr> -->

								<tr>
									<td style="text-align: left;">押金：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="merchantDeposit" name="merchantDeposit" maxlength="10"
										onkeyup="this.value=this.value.replace(/\D/g,'')" /></td>
									<td style="text-align: left;">区域：</td>
									<td style="text-align: left;">
									<select id="merchantArea" name="merchantArea" class="inputext_style" onchange="getManagerList()">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${merchantAreaList}">
												<option value="${model.value}">${model.label}</option>
											</c:forEach>
									</select>
									</td>
								</tr>

								<tr>
									<td style="text-align: left;">区域负责人：</td>
									<td style="text-align: left;">
									<select id="storeManager" name="storeManager" class="inputext_style">
									</select>
									</td>
									<td style="text-align: left;">商户顾问：</td>
									<td style="text-align: left;">
									<select id="merchantAdvisor" name="merchantAdvisor" class="inputext_style">
									</select></td>
								</tr>
								<tr>
									<td style="text-align: left;">合同结束日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" readonly="readonly"
										onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
										name="contractEndDate" id="contractEndDate" maxlength="8" value="${info.contractEndDate}" /></td>
									<td style="text-align: left;">父商户号：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="fmrchno" name="fmrchno" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')" onblur="checkMerchantfId();" />
									</td>
								</tr>
								
								<tr>
									<td style="text-align: left;">企业经营范围：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="merchant_x_operate" name="merchant_x_operate" maxlength="165"  />
									</td>
									<td style="text-align: left;">商户分类：</td>
									<td style="text-align: left;">
									<select id="merchant_x_type" name="merchant_x_type" class="inputext_style">
											<option value="12">单位</option>
											<option value="11">自然人</option>
									</select>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">注册资本金：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="merchant_x_reg_amt" name="merchant_x_reg_amt" value="0" maxlength="18" onchange="clearNoNum(this);" />
									</td>
									<td style="text-align: left;">注册资本金币种：</td>
									<td style="text-align: left;">
									<select id="merchant_x_code" name="merchant_x_code" class="inputext_style">
										<c:forEach var="model" items="${merchant_x_code_typeList}">
											<option value="${model.paramValue}">${model.paramName}</option>
										</c:forEach>
									</select>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">结算账户类型：</td>
									<td style="text-align: left;">
									<select id="merchant_x_acc_type1" name="merchant_x_acc_type1" class="inputext_style">
											<option value="12">银行账户</option>
											<option value="11">支付账户</option>
									</select>
									</td>
									<td style="text-align: left;"></td>
									<td style="text-align: left;"></td>
								</tr>

								<tr>
									<td style="text-align: center;" colspan="4">
									<button type="submit" id="submit">提交</button>
									<button type="closeBtn" id="closeBtn">关闭</button>
									<!-- <input type="submit" id="submit" value="提交" class="button" /> 
									<input type="button" id="closeBtn" value="关闭" class="button" /> -->	
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