<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>内管系统-平台币充值</title>

<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="bill">			
			<form:form id="eform" action="${ctxPath}/currency/temp" method="post" class="form-horizontal">
				<div class="bill-li">
					<em>&nbsp;账户余额</em>
					<span class="surplus" readonly="readonly">${currentMoney}</span>
					<span>平台币</span>
				</div>
				<div class="bill-li">
					<em class="prompt-star">充值金额</em>
					<span><input type="text" id="payno" name="payno" class="bill-input"></span>
					<span>元</span>
					<span> 1元1000个平台币</span>
				</div>
				<div class="bill-btn">
					<!-- <button type="submit" class="btn btn-primary bill-btn-r">取消</button> -->
					<a target="_blank" id="gopay" class="btn btn-primary bill-btn-r">去支付</a>
				</div>
			</form:form>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript">
			//修改表单验证，提交
			$(document).ready(function() {
				$("#gopay").on("click",function(){
					var pay =  $("#payno").val();
					if( pay == null || $.trim(pay)==''){
						$('#eform').submit();
						return ;
					}
					$(this).attr('href',"${ctxPath}/currency/temp?payno="+pay);
				});
				$('#eform').validate({
					//debug: true,
				    submitHandler:function(form){
				     return;
				    },
				    errorPlacement: function (error, element){
				    	error.insertAfter(element.parent().parent())
				    },
					rules : {
						payno : {
							required : true,
							money : true							
						}			    
					}				    
				});
				$("#payno").on("change",function(){
					convertMoney2('payno');
				}); 
				
				/**
				* 对金额进行转换，将金额转换为以元为单位，小数点后有两位
				* 例：输入域1234，转换后隐含域为1234.000
				* @param {String} 表单名称
				* @param {String} 要转换的金额输入框名称
				* @param {String} 金额隐藏域名称
				*/
				function convertMoney2(txtmoney){
					var re = /,/g;
					    
				    var txt_money = $("#"+txtmoney);
					var txt_money_val = $.trim(txt_money.val());
					if (txt_money_val == ""){
						//alert("请输入正确的金额数字!");
						txt_money.val("");
						txt_money.focus();
						return;
					}
					
					
					//删除开头的0
					txt_money_val = txt_money_val.replace(/^0*,?/g , '');
					if(txt_money_val == ''){
						txt_money.val("");
						txt_money.focus();
						return;
					}
					
					//删除结尾的0
					if(txt_money_val.indexOf(".") > 0){
						txt_money_val = txt_money_val.replace(/\.*0*$/g,"");
					}
					//删除，号
					txt_money_val = txt_money_val.replace(/,/g,"");
					//在小数点前面自动补0(例如：".25" 自动转换成"0.25")
					var tonumber = txt_money_val.replace(re,"").charAt(0) == "."? "0" + txt_money_val:txt_money_val;

					if (tonumber !=="" && tonumber!=null){
						rep = / /g;
						var amt = tonumber.replace(rep,"");
						var moneyreg = /^([0-9]+|[0-9]{1,3}(,[0-9]{1,3})*)(\.[0-9]{1,2})?$/g;
						if(!moneyreg.test(amt)){
							//alert("2请输入正确的金额数字!");
							return;
						}
					
						re = /,/g;
						var amt1 = amt.replace(re,"");

						var amt2=parseFloat(amt1);		
						if(amt2<0){
							//alert("输入的金额不能小于或等于零,请重新输入!");
							txt_money.focus();
							return;
						}else{		//大于0的正数;
							if(amt1.indexOf(".")!=-1){				
								var str = amt1.substr(amt1.indexOf(".")+1);				
								if(str.length==1){
									amt1=amt1 + "0";
								}
								else if(str.length==2){
									amt1=amt1 ;
								}
								else if(str.length<1){
									amt1=amt1 + "00";
								}
							}else{
								amt1=amt1 + ".00";
							}
							
							var temp=amt1.substring(0,amt1.indexOf("."));
							if (temp.length > 14 || amt1 > '9999999999999.99'){
							    //alert("输入的金额太大，请重新输入!");
							    txt_money.focus();
							    return;
							}
							
							txt_money.val(comma(temp) + amt1.substring(amt1.indexOf(".")));
							return;							
						}
				    }
				}
			});
		</script>
</body>
</html>