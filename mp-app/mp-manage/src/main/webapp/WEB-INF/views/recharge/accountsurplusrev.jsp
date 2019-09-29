<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>充值管理-人工充值</title>
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li>充值</li>
				<li class="active" id="bill_check">人工充值</li>
			</ol>
		</div>
		<div class="mainbox">
			<form:form id="eform" action="${ctxPath}/accountsurplusrev/add" method="post" class="form-horizontal" modelAttribute="cloudaccountsurplusrevModel"> 	 
			  
			  <div class="form-group">
			  <label class="col-sm-4 control-label"></label>
			    <div class="col-sm-4">
			    	<input name="any" type="radio" value="1" checked="checked"/><label>指定机构充值</label>
			    	<input name="any" type="radio" value="2" /><label>自定义机构代码充值</label>
			    	<input name="any" type="radio" value="3" /><label>指定手机号充值</label>
			    </div>
			  </div>
			  
			  <div class="form-group" id="orgcssid">
						<label class="col-sm-4 control-label">机构名称</label>
						<div class="col-sm-3">
								<select id="porgid" name="porgid" class="form-control select" >
									<option value="">-请选择-</option> 
									<c:forEach items="${cloudaccountsurplusrevModel.cloudpforgList}" var="showList">
										<option value="${showList.id}">${showList.orgname}</option> 
									</c:forEach>
								</select>
						</div>
			  </div>
			  
			  <div class="form-group" id="orgcodecssid1">
			    <label class="col-sm-4 control-label">机构代码</label>
			    <div class="col-sm-3">
			      <input type="text" id="orgcodecssid" name="orgcodecssid" class="form-control">
			    </div>
			  </div>
			  <div class="form-group" id="phonenocssid1">
			    <label class="col-sm-4 control-label">手机号码</label>
			    <div class="col-sm-3">
			      <input type="text" id="phonenocssid" name="phonenocssid" class="form-control">
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <label class="col-sm-4 control-label">充值金额</label>
			    <div class="col-sm-3">
			      <input type="text" id="pamount" name="pamount" class="form-control">
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-4 control-label">兑换平台币数量</label>
			    <div class="col-sm-3">
			      <input type="text" id="showpamount" name="showpamount" class="form-control" readonly="readonly">
			    </div>
			  </div>
			  <div class="col-sm-offset-4 col-sm-8
			  ">
			  	<button type="submit" class="btn btn-primary" >确定</button>
			  	<a href="${ctxPath}/accountsurplusrev/init"><button type="button" class="btn default cancel" >取消</button></a>
			  </div>  
			</form:form>
		</div>			
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript">
		//修改表单验证，提交
			$(document).ready(function() {
				$('#eform').validate({
					//debug: true,
					rules : {
						/* porgid : {
							required : true
						}, */
						pamount : {
							moneyCheck : true,
							required : true,
							maxlength: 9
						}
					}
				});
				displaynone();
			});
		
			//金额验证
			function dateVerify(par){ 
				var patrn=/^([0-9]{0,9}|[0-9]{1,3}(,[0-9]{3})*)(\.[0-9]{1,2})?$/; 
				if (!patrn.exec(par)) return false 
				return true 
			}
			
			function moneycheck(par){ 
				var smm = par.replace(/^0*,?/g , '');
				if(smm.indexOf(".") == 0){
					var rm = smm.replace(/^.00,?/g , '');
					if(rm == ''){
						$("#pamount").val("");
						$("#showpamount").attr("value","");
					}
				}else{
					if(smm == ''){
						$("#pamount").val("");
						$("#showpamount").attr("value","");
					}
				}
			}
			
			$("#pamount").on("change",function(){
				var pamount = $('#pamount').val();
				moneycheck(pamount);
			}); 
			/*隐藏手机号和机构code输入框并清空值*/
			function displaynone(){
				//隐藏div
				$("#phonenocssid1").css("display", "none");
				$("#orgcodecssid1").css("display", "none"); 
				//$("#orgcssid").css("display", ""); 
				//清空输入内容
				$("#phonenocssid").val("");
				$("#orgcodecssid").val("");
				$("#porgid").val("");
				//去掉校验
				$('#phonenocssid').rules("remove");
				$('#orgcodecssid').rules("remove");
				$('#porgid').rules("remove");
				//添加动态校验
				$('#porgid').rules("add",{
                    required : true,
                   	messages : {  
                       	required  : "该字段不能为空" 
                    } 
             	});
			}
			$(":radio").click(function(){
				var chan = $(this).val();
				displaynone();  
				if("1"==chan){
					$("#orgcssid").css("display", "");
				}else if("2"==chan) {
					$("#orgcssid").css("display", "none");
					$("#orgcodecssid1").css("display", ""); 
					//添加动态校验
					$('#orgcodecssid').rules("add",{
	                    required : true,
	                   	messages : {  
	                       	required  : "该字段不能为空"
	                    } 
	             	});
					//去掉校验
					$('#porgid').rules("remove");
				}else if("3"==chan){
					$("#orgcssid").css("display", "none");
					$("#phonenocssid1").css("display", ""); 
					//添加动态校验
					$('#phonenocssid').rules("add",{
	                    required : true,
	                    mobile : true,
	                   	messages : {  
	                       	required  : "该字段不能为空", 
	                       	mobile : "手机号码格式不正确"
	                    } 
	             	});
					//去掉校验
					$('#porgid').rules("remove");
				}
		    });

			//换算显示
			$("#pamount").bind("input propertychange",function(){
				$("#showpamount").attr("value","");
				var pamount = $('#pamount').val();
				if(dateVerify(pamount)) {
					$("#showpamount").attr("value",pamount*1000);
				}
			}); 
		
		</script>
	</body>
</html>