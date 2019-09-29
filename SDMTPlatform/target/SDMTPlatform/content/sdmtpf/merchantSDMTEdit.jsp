<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>编辑商户信息</title>
<!-- 样式所需导入的链接 -->
<%@ include file="../inner/css.jsp"%>
<!-- 样式所需导入的链接 -->
<script type="text/javascript">
$().ready(function() {
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	$('#closeBtn').click(function() {
		parent.layer.close(index);
	});
	$("#form").validate({
		onsubmit : true,// 是否在提交是验证
		onfocusout : false,// 是否在获取焦点时验证
		onkeyup : false,// 是否在敲击键盘时验证
		/* rules : {
			merchantId : {required : true},mrchtName : {required : true},merchantEname : {required : true},abbrCname : {required : true},
			abbrEname : {required : true},address : {required : true},province : {required : true},cityNo : {required : true},
			zone : {required : true},manager : {required : true},telephone : {required : true},addDate : {required : true},
			accName : {required : true},accNo : {required : true, number : true},bankName : {required : true},accNickName : {required : true},
			shortNickName : {required : true},bankNo : {required : true, number : true},lastSettleDate : {required : true},agent : {required : true},
			idNo : {required : true},lastSettleDate : {required : true},legalRep : {required : true},lrIdNo : {required : true},
			lrIdValidity : {required : true},busLicNo : {required : true},busLicValidity : {required : true},taxId : {required : true},
			taxIdValidity : {required : true},orgValidity : {required : true},enterpriseNo : {required : true},orgId : {required : true}
		}, */
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
			bis : {
				required : true
			},
			zone : {
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
			var param = $("#form").serialize();
			url = $("#form").attr("action");
			$.ajax({
				url : url,
				type : "post",
				data : param,
				dataType : "json",
				success : function(data) {
					if (data.result == '0') {
						layer.alert(data.resultMsg, 1, function() {
							$("#form", window.parent.document).submit();
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
var getManagerList = function() {
	//$("#storeManager").val('');
	//$("#merchantAdvisor").val('');
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
					$.each(data, function(i, inf) {
						$("#storeManager").append(
							"<option title='"+inf.managerName+"' value='"+inf.id+"'>" + inf.managerName + "</option>");
					});						
				}else if(roleId=="0"){
					$("#merchantAdvisor").empty();
					$("#merchantAdvisor").append("<option value=''>--请选择--</option>");	
					$("#merchantAdvisor").val('');
					if (data.length != 0) {
						$.each(data, function(i, inf) {
							$("#merchantAdvisor").append(
								"<option title='"+inf.managerName+"' value='"+inf.id+"'>" + inf.managerName + "</option>");
						});
					}
					
				}
			}
		})
	}
};
var getZoneList = function() {
	$.ajax({
		url : '${ctx }/merchantSDMT/getAreaList',
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


</script>
</head>
<body>
	<div class="sh_main">
		<div class="shm_contab">
			<div class="poup_right">
				<div class="shmcr_cont01">
					<div class="sh_title">编辑商户信息</div>
					<div class="sh_cm">
						<form id="form" action="${ctx }/merchantSDMT/edit" method="post">
							<table width="100%" border="0" class="tab">
								<tr>
									<td style="text-align: left;">商户编号：</td>
									<td style="text-align: left;">
									${info.merchantId}
									<input type="hidden" id="merchantId" name="merchantId" class="inputext_style" value="${info.merchantId}" />								
									</td>
									<td style="text-align: left;">商户名称：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="mrchtName" name="mrchtName"  maxlength="30" value="${info.mrchtName}"/>								
										<%-- <input class="inputext_style" id="mrchtName" name="mrchtName"  maxlength="8" value="${info.mrchtName}"/> --%>
										<!-- 商户名称（目前8个字）扩充至30个 -->									
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">商户名称(英)：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="merchantEname" name="merchantEname"  onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"  maxlength="20" value="${info.merchantEname}"/>								
									</td>
									<td style="text-align: left;">简称(中)：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="abbrCname" name="abbrCname"  maxlength="4" value="${info.abbrCname}"/>								
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">简称(英)：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="abbrEname" name="abbrEname"  onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"  maxlength="8" value="${info.abbrEname}"/>								
									</td>
									<td style="text-align: left;">MCC：</td>
									<td style="text-align: left;">				
										<select id="mccId" name="mccId" class="inputext_style" value="${info.mccId}">
											<c:forEach var="model" items="${mccList}">
												<option value="${model.id}" <c:if test="${model.id eq info.mccId}">selected="selected"</c:if>>
													${model.id}---${model.descr}
												</option>
											</c:forEach>
										</select>												
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">地址：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="address" name="address"  maxlength="30" value="${info.address}"/>								
										<%-- <input class="inputext_style" id="address" name="address"  maxlength="10" value="${info.address}"/> --%>
										<!-- 地址,扩充至30个 -->								
									</td>
									<td style="text-align: left;">省份：</td>
									<td style="text-align: left;">
										<select id="province" name="province" class="inputext_style" onchange="getCityList();" value="${info.province}">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${proviceList}">
												<option value="${model.id}" <c:if test="${model.id eq info.province}">selected="selected"</c:if>>
													${model.id}---${model.provinceCity}
												</option>
											</c:forEach>
										</select>																	
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">联系电话：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="telephone" name="telephone"  maxlength="20" value="${info.telephone}"/>								
									</td>
									<td style="text-align: left;">城市：</td>
									<td style="text-align: left;">
										<select id="cityNo" name="cityNo" class="inputext_style" onchange="getZoneList();" value="${info.cityNo}">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${cityList}"> 
												<option value="${model.id}" <c:if test="${model.id eq info.cityNo}">selected="selected"</c:if>>
													${model.id}---${model.provinceCity}
												</option>
											</c:forEach>
										</select>																		
									</td>	
								</tr>								
								<tr>
									<td style="text-align: left;">联系人：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="manager" name="manager"  maxlength="4" value="${info.manager}"/>								
									</td>
									<td style="text-align: left;">区域：</td>
									<td style="text-align: left;">
										<select id="zone" name="zone" class="inputext_style" value="${info.zone}">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${zoneList}">
												<option value="${model.id}" <c:if test="${model.id eq info.zone}">selected="selected"</c:if>>
													${model.id}---${model.provinceCity}
												</option>
											</c:forEach>
										</select>																
									</td>								
								</tr>								
								<tr>
									<td style="text-align: left;">增加时间：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMddHHmmss',isShowClear:false,readOnly:true})" 
											name="addDate" id="addDate" maxlength="14" value="${info.addDate}"/>
									</td>
									<td style="text-align: left;"></td>
									<td style="text-align: left;">
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">结算账户开户名：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="accName" name="accName"  maxlength="30" value="${info.accName}"/>								
										<%-- <input class="inputext_style" id="accName" name="accName"  maxlength="10" value="${info.accName}"/> --%>
										<!-- 结算账户开户名 ,扩充至30个 -->								
									</td>
									<td style="text-align: left;">结算账号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="accNo" name="accNo"  maxlength="30" value="${info.accNo}"/>								
										<%-- <input class="inputext_style" id="accNo" name="accNo"  maxlength="20" value="${info.accNo}"/> --%>								
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">是否是北京开户行:</td>
									<td style="text-align: left;">
										<select id="isBjAcct" name="isBjAcct" class="inputext_style" value="${info.isBjAcct}">
											<option value="0" <c:if test="${info.isBjAcct eq '0'}">selected="selected"</c:if>>否</option>
											<option value="1" <c:if test="${info.isBjAcct eq '1'}">selected="selected"</c:if>>是</option>
										</select>																	
									</td>
									<td style="text-align: left;">结算银行：</td>
									<td style="text-align: left;">
										<select id="bis" name="bis" class="inputext_style" value="${info.bis}">
											<option value="">--请选择--</option>
											<c:forEach var="model" items="${bankList}">
												<option value="${model.bankCode}"  <c:if test="${model.bankCode eq info.bis}">selected="selected"</c:if>>
													${model.bankCode}---${model.bankName}
												</option>
											</c:forEach>
										</select>																		
									</td>
								</tr>								
								
								<tr>
									<td style="text-align: left;">开户银行名称：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="bankName" name="bankName"  maxlength="30" value="${info.bankName}"/>								
										<%-- <input class="inputext_style" id="bankName" name="bankName"  maxlength="10" value="${info.bankName}"/> --%>
										<!-- 开户银行名称,扩充至30个 -->									
									</td>
									<td style="text-align: left;">商户账号全称：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="accNickName" name="accNickName"  maxlength="30" value="${info.accNickName}"/>								
										<%-- <input class="inputext_style" id="accNickName" name="accNickName"  maxlength="15" value="${info.accNickName}"/> --%>								
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">商户账号简称：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="shortNickName" name="shortNickName"  maxlength="30" value="${info.shortNickName}"/>								
									</td>
									<td style="text-align: left;">开户银行行号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="bankNo" name="bankNo"  maxlength="16" value="${info.bankNo}"/>								
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">是否单独结算：</td>
									<td style="text-align: left;">
										<select id="individual" name="individual" class="inputext_style">
											<option value="0" <c:if test="${info.individual eq '0'}">selected="selected"</c:if>>否</option>
											<option value="1" <c:if test="${info.individual eq '1'}">selected="selected"</c:if>>是</option>
										</select>																	
									</td>
									<td style="text-align: left;">上次结算日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})" 
											name="lastSettleDate" id="lastSettleDate" maxlength="8" value="${info.lastSettleDate}"/>																	
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">商户类型：</td>
									<td style="text-align: left;">
									<select id="typeYf" name="typeYf" class="inputext_style" value="${info.typeYf}">
										<c:forEach var="model" items="${typeYfList}">
											<option value="${model.paramValue}" <c:if test="${model.paramValue eq info.typeYf}">selected="selected"</c:if> >${model.paramName}</option>
										</c:forEach>
									</select>
										<%--  <select id="typeYf" name="typeYf" class="inputext_style">
									         <option value="1" <c:if test="${info.typeYf eq '1'}">selected="selected"</c:if>>商场类</option>
									         <option value="2" <c:if test="${info.typeYf eq '2'}">selected="selected"</c:if>>超市类</option>
									         <option value="3" <c:if test="${info.typeYf eq '3'}">selected="selected"</c:if>>餐饮类</option>
									         <option value="4" <c:if test="${info.typeYf eq '4'}">selected="selected"</c:if>>食品类</option>
									         <option value="5" <c:if test="${info.typeYf eq '5'}">selected="selected"</c:if>>娱乐休闲</option>
									         <option value="6" <c:if test="${info.typeYf eq '6'}">selected="selected"</c:if>>美容美发</option>
									         <option value="7" <c:if test="${info.typeYf eq '7'}">selected="selected"</c:if>>健康体检</option>
									         <option value="8" <c:if test="${info.typeYf eq '8'}">selected="selected"</c:if>>体育健身</option>
									         <option value="9" <c:if test="${info.typeYf eq '9'}">selected="selected"</c:if>>汽车保养</option>
									         <option value="10" <c:if test="${info.typeYf eq '10'}">selected="selected"</c:if>>珠宝钟表</option>
									         <option value="11" <c:if test="${info.typeYf eq '11'}">selected="selected"</c:if>>票务类</option>
									         <option value="12" <c:if test="${info.typeYf eq '12'}">selected="selected"</c:if>>旅游酒店</option>
									         <option value="13" <c:if test="${info.typeYf eq '13'}">selected="selected"</c:if>>摄影服务</option>
									         <option value="14" <c:if test="${info.typeYf eq '14'}">selected="selected"</c:if>>电器类</option>
									         <option value="15" <c:if test="${info.typeYf eq '15'}">selected="selected"</c:if>>加油站类</option>
									         <option value="16" <c:if test="${info.typeYf eq '16'}">selected="selected"</c:if>>其它类</option>
										</select>	 --%> 																
									</td>
									<td style="text-align: left;">办理手续经办人姓名：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="agent" name="agent"  maxlength="6" value="${info.agent}"/>								
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">办理手续经办人证件类型：</td>
									<td style="text-align: left;">
										<select id="idType" name="idType" class="inputext_style">
									         <option value="1" <c:if test="${info.idType eq '1'}">selected="selected"</c:if>>居民身份证</option>
									         <option value="2" <c:if test="${info.idType eq '2'}">selected="selected"</c:if>>户口本</option>
									         <option value="3" <c:if test="${info.idType eq '3'}">selected="selected"</c:if>>军人身份证</option>
									         <option value="4" <c:if test="${info.idType eq '4'}">selected="selected"</c:if>>武装警察身份证</option>
									         <option value="5" <c:if test="${info.idType eq '5'}">selected="selected"</c:if>>往来内地通行证</option>
									         <option value="6" <c:if test="${info.idType eq '6'}">selected="selected"</c:if>>往来大陆通行证</option>
									         <option value="7" <c:if test="${info.idType eq '7'}">selected="selected"</c:if>>护照</option>
									         <option value="8" <c:if test="${info.idType eq '8'}">selected="selected"</c:if>>其他</option>
										</select>																	
									</td>
									<td style="text-align: left;">办理手续经办人证件号码：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="idNo" name="idNo"  maxlength="20" value="${info.idNo}"/>								
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">办理手续经办人证件有效期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})" 
											name="idValidity" id="idValidity" maxlength="8" value="${info.idValidity}"/>	
									</td>
									<td style="text-align: left;">法定代表人（负责人）姓名：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="legalRep" name="legalRep"  maxlength="6" value="${info.legalRep}"/>								
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">法定代表人证件类型：</td>
									<td style="text-align: left;">
										<select id="lrIdType" name="lrIdType" class="inputext_style">
									         <option value="1" <c:if test="${info.lrIdType eq '1'}">selected="selected"</c:if>>居民身份证</option>
									         <option value="2" <c:if test="${info.lrIdType eq '2'}">selected="selected"</c:if>>户口本</option>
									         <option value="3" <c:if test="${info.lrIdType eq '3'}">selected="selected"</c:if>>军人身份证</option>
									         <option value="4" <c:if test="${info.lrIdType eq '4'}">selected="selected"</c:if>>武装警察身份证</option>
									         <option value="5" <c:if test="${info.lrIdType eq '5'}">selected="selected"</c:if>>往来内地通行证</option>
									         <option value="6" <c:if test="${info.lrIdType eq '6'}">selected="selected"</c:if>>往来大陆通行证</option>
									         <option value="7" <c:if test="${info.lrIdType eq '7'}">selected="selected"</c:if>>护照</option>
									         <option value="8" <c:if test="${info.lrIdType eq '8'}">selected="selected"</c:if>>其他</option>
										</select>																			
									</td>
									<td style="text-align: left;">法定代表人证件号码：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="lrIdNo" name="lrIdNo"  maxlength="20" value="${info.lrIdNo}"/>								
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">法定代表人证件有效期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})" 
											name="lrIdValidity" id="lrIdValidity" maxlength="8" value="${info.lrIdValidity}"/>																		
									</td>
									<td style="text-align: left;">企业账号：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="enterpriseNo" name="enterpriseNo" maxlength="28" value="${info.enterpriseNo}" onkeyup="this.value=this.value.replace(/\D/g,'')" /></td>
								</tr>
								<tr>
									<td style="text-align: left;">控股股东或实际控制人姓名：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="manName" name="manName" value="${info.manName}" maxlength="6" /></td>
									<td style="text-align: left;">控股股东或实际控制人证件种类：</td>
									<td style="text-align: left;">
									<select id="idType1" name="idType1" class="inputext_style" value="${info.idType1}">
											 <option value="1" <c:if test="${info.idType1 eq '1'}">selected="selected"</c:if>>居民身份证</option>
									         <option value="2" <c:if test="${info.idType1 eq '2'}">selected="selected"</c:if>>户口本</option>
									         <option value="3" <c:if test="${info.idType1 eq '3'}">selected="selected"</c:if>>军人身份证</option>
									         <option value="4" <c:if test="${info.idType1 eq '4'}">selected="selected"</c:if>>武装警察身份证</option>
									         <option value="5" <c:if test="${info.idType1 eq '5'}">selected="selected"</c:if>>往来内地通行证</option>
									         <option value="6" <c:if test="${info.idType1 eq '6'}">selected="selected"</c:if>>往来大陆通行证</option>
									         <option value="7" <c:if test="${info.idType1 eq '7'}">selected="selected"</c:if>>护照</option>
									         <option value="8" <c:if test="${info.idType1 eq '8'}">selected="selected"</c:if>>其他</option>
									</select></td>
								</tr>
								<tr>
									<td style="text-align: left;">控股股东或实际控制人证件号码：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="idNo1" name="idNo1" maxlength="20" value="${info.idNo1}"/></td>
									<td style="text-align: left;">控股股东或实际控制人证件有效期截止日：</td>
									<td style="text-align: left;">
									<input type="text" class="inputext_style" readonly="readonly"
										onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
										name="idDeadline1" id="idDeadline1" maxlength="8" value="${info.idDeadline1}" /></td>
								</tr>
								<tr>
									<td style="text-align: left;">营业执照号：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="busLicNo" name="busLicNo" maxlength="30" value="${info.busLicNo}" onkeyup="value=value.replace(/[^\w\/]/ig,'')" /></td>
									<td style="text-align: left;">营业执照名称：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="accXName" name="accXName" maxlength="20" value="${info.accXName}" /></td>
								</tr>								
								<tr>
									<td style="text-align: left;">营业执照年检时间：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})" 
											name="busLicValidity" id="busLicValidity" maxlength="8" value="${info.busLicValidity}"/>																		
									</td>
									<td style="text-align: left;">税务登记证编号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="taxId" name="taxId"  maxlength="30" value="${info.taxId}" onkeyup="value=value.replace(/[^\w\/]/ig,'')"/>								
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">税务登记证年检时间：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})" 
											name="taxIdValidity" id="taxIdValidity" maxlength="8" value="${info.taxIdValidity}"/>																		
									</td>
									<td style="text-align: left;">组织机构证编号：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="orgId" name="orgId"  maxlength="30" value="${info.orgId}" onkeyup="value=value.replace(/[^\w\/]/ig,'')"/>								
									</td>
								</tr>								
								<tr>
									<td style="text-align: left;">组织机构证年检时间：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" readonly="readonly"
											onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})" 
											name="orgValidity" id="orgValidity" maxlength="8" value="${info.orgValidity}"/>																		
									</td>
									<td style="text-align: left;"></td>
									<td style="text-align: left;">
									</td>
								</tr>
								<tr>
									<td><div class="sh_title">修改商户补充信息</div></td><td><div class="sh_title"></div></td>
									<td><div class="sh_title"></div></td><td><div class="sh_title"></div></td>
								</tr>
								<tr>
									<td style="text-align: left;">合同签订日期：</td>
									<td style="text-align: left;">
									<%-- <input name="contractStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
										value="${info.contractStartDate}" onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false});" />
									</td> --%>
									<input type="text" class="inputext_style" readonly="readonly"
										onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
										name="contractStartDate" id="contractStartDate" maxlength="8" value="${info.contractStartDate}" /></td>
									<td style="text-align: left;">合同续约日期：</td>
									<td style="text-align: left;">
									<%-- <input name="contractRenewalDate" type="text" readonly="readonly"
										maxlength="20" class="input-medium Wdate " value="${info.contractRenewalDate}"
										onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false});" />
									</td> --%>
									<input type="text" class="inputext_style" readonly="readonly"
										onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
										name="contractRenewalDate" id="contractRenewalDate" maxlength="8" value="${info.contractRenewalDate}" /></td>
								</tr>
								<tr>
									<td style="text-align: left;">商户公司名称：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="merchantCompanyName" name="merchantCompanyName" maxlength="60" value="${info.merchantCompanyName}"/></td>
									<td style="text-align: left;">续约方式：</td>
									<td style="text-align: left;">
										<select id="renewalType" name="renewalType" class="inputext_style" value="${info.renewalType}">
											<c:forEach var="model" items="${renewalTypeList}">
												<option value="${model.value}" <c:if test="${model.value eq info.renewalType}">selected="selected"</c:if>>
													${model.label}
												</option>
											</c:forEach>
										</select>
									</td>		
								</tr>
								<tr>
									<td style="text-align: left;">保函到期日期：</td>
									<td style="text-align: left;">
									<%-- <input name="guaranteeLastDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
										value="${info.guaranteeLastDate}" onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false});" /> </td> --%>
										<input type="text" class="inputext_style" readonly="readonly"
										onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
										name="guaranteeLastDate" id="guaranteeLastDate" maxlength="8" value="${info.guaranteeLastDate}" /></td>
									<td style="text-align: left;">保函金额：</td>
									<td style="text-align: left;"><input
										class="inputext_style" id="guaranteeAmt" name="guaranteeAmt"  maxlength="10"
										onkeyup="this.value=this.value.replace(/\D/g,'')" value="${info.guaranteeAmt}"/></td>
								</tr>
								<tr>
									<td style="text-align: left;">市场联系人：</td>
									<td style="text-align: left;"><input class="inputext_style" id="marketContactPerson"
										name="marketContactPerson" maxlength="200" value="${info.marketContactPerson}"/></td>
									<td style="text-align: left;">市场联系人电话：</td>
									<td style="text-align: left;">
									<input class="inputext_style" onkeyup="this.value=this.value.replace(/\D/g,'')" id="marketContactMobile" name="marketContactMobile" maxlength="14" value="${info.marketContactMobile}" />
									</td>
								</tr>
								<%-- <tr>
									<td style="text-align: left;">财务联系人：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="financialContactPerson" name="financialContactPerson" maxlength="200" value="${info.financialContactPerson}" />
									</td>
									<td style="text-align: left;">财务联系人电话：</td>
									<td style="text-align: left;">
									<input class="inputext_style" onkeyup="this.value=this.value.replace(/\D/g,'')" id="financialContactMobile" name="financialContactMobile" maxlength="14" value="${info.financialContactMobile}" />
									</td>
								</tr> --%>
								<tr>
									<td style="text-align: left;">押金：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="merchantDeposit" name="merchantDeposit" maxlength="10"
										onkeyup="this.value=this.value.replace(/\D/g,'')" value="${info.merchantDeposit}" /></td>
									<td style="text-align: left;">区域：</td>
									<td style="text-align: left;">
										<select id="merchantArea" name="merchantArea" class="inputext_style" value="${info.merchantArea}" onchange="getManagerList()">
											<c:forEach var="model" items="${merchantAreaList}">
												<option value="${model.value}" <c:if test="${model.value eq info.merchantArea}">selected="selected"</c:if>>
													${model.label}
												</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">区域负责人：</td>
									<td style="text-align: left;">
									<select id="storeManager" name="storeManager" class="inputext_style" value="${info.storeManager}">
											<c:forEach var="model" items="${storeManagerList}">
												<option value="${model.id}" <c:if test="${model.id eq info.storeManager}">selected="selected"</c:if>>
													${model.managerName}
												</option>
											</c:forEach>
										</select>
									</td>
									<td style="text-align: left;">商户顾问：</td>
									<td style="text-align: left;">
										<select id="merchantAdvisor" name="merchantAdvisor" class="inputext_style" value="${info.merchantAdvisor}">
											<c:forEach var="model" items="${managerList}">
												<option value="${model.id}" <c:if test="${model.id eq info.merchantAdvisor}">selected="selected"</c:if>>
													${model.managerName}
												</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">合同结束日期：</td>
									<td style="text-align: left;">
										<input type="text" class="inputext_style" readonly="readonly"
										onFocus="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:false,readOnly:true})"
										name="contractEndDate" id="contractEndDate" maxlength="8" value="${info.contractEndDate}" /></td>
									<td style="text-align: left;">父商户号：</td>
									<td style="text-align: left;">
									${info.fmrchno}
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">企业经营范围：</td>
									<td style="text-align: left;">
										<input class="inputext_style" id="merchant_x_operate" name="merchant_x_operate"  value="${info.merchant_x_operate}" maxlength="165"  />
									</td>
									<td style="text-align: left;">商户分类：</td>
									<td style="text-align: left;">
									<select id="merchant_x_type" name="merchant_x_type" class="inputext_style" value="${info.merchant_x_type}">
											<option value="12" <c:if test="${info.merchant_x_type eq '12'}">selected="selected"</c:if>>单位</option>
									        <option value="11" <c:if test="${info.merchant_x_type eq '11'}">selected="selected"</c:if>>自然人</option>
									</select>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">注册资本金：</td>
									<td style="text-align: left;">
									<input class="inputext_style" id="merchant_x_reg_amt" name="merchant_x_reg_amt" value="${info.merchant_x_reg_amt}" maxlength="18" onchange="clearNoNum(this);" />
									</td>
									<td style="text-align: left;">注册资本金币种：</td>
									<td style="text-align: left;">
									<select id="merchant_x_code" name="merchant_x_code" class="inputext_style" value="${info.merchant_x_code}">
										<c:forEach var="model" items="${merchant_x_code_typeList}">
											<option value="${model.paramValue}" <c:if test="${model.paramValue eq info.merchant_x_code}">selected="selected"</c:if>>
												${model.paramName}
											</option>
										</c:forEach>
									</select>
									</td>
								</tr>
								<tr>
									<td style="text-align: left;">结算账户类型：</td>
									<td style="text-align: left;">
									<select id="merchant_x_acc_type1" name="merchant_x_acc_type1" class="inputext_style" value="${info.merchant_x_acc_type1}">
											<option value="12" <c:if test="${info.merchant_x_acc_type1 eq '12'}">selected="selected"</c:if>>银行账户</option>
									        <option value="11" <c:if test="${info.merchant_x_acc_type1 eq '11'}">selected="selected"</c:if>>支付账户</option>
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