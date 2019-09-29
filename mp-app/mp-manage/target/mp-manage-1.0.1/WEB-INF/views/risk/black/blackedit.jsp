<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>黑名单管理-编辑黑名单</title>
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li>黑名单管理</li>
				<li class="active" id="bill_check">编辑黑名单</li>
			</ol>
		</div>
		<div class="mainbox">
					<div class="tablebox">
			<div class="row">
				<form:form id="form" modelAttribute="blacklistModel" action="${ctxPath}/blackmanage/editdata" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<div class="col-sm-3">
							<form:input path="blackid"  type="hidden" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">游戏名称</label>
						<div class="col-sm-3">
							<form:input path="playname"  readonly="true" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">黑名单类型</label>
						<div class="col-sm-3 select-list" >
							<select id="blacktype" name="blacktype" disabled="disabled" class="form-control select blacktype" >
								<option value="1" ${'1' eq blacklistModel.blacktype ? 'selected="selected"':''}>手机号码</option>
								<option value="2" ${'2' eq blacklistModel.blacktype ? 'selected="selected"':''}>IP</option>
								<option value="3" ${'3' eq blacklistModel.blacktype ? 'selected="selected"':''}>OPENID</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">黑名单值</label>
						<div class="col-sm-3">
							<form:input path="blackvalue" cssClass="form-control" readonly="true" id="blackvalue"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">状态</label>
						<div class="col-sm-3 select-list" >
							<select id="status" name="status" class="form-control select blacktype" >
								<option value="0" ${'0' eq blacklistModel.status ? 'selected="selected"':''}>过期</option>
								<option value="1" ${'1' eq blacklistModel.status ? 'selected="selected"':''}>生效</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">过期时间</label>
						<div class="col-sm-6">
							<div class="input-append date date1" style="float:left; width:130px;">								 
							    <input type="hidden" id="begintime" name = "begintime" class="date_1 form_datetime_nl" value="${blacklistModel.begintime}" readonly="readonly"  style="margin-top:0">     
							</div>						
							<div class="input-append date date2" style="float:left">
							    <input type="text" id="overtime" name = "overtime" class="date_2 form_datetime_nl" value="${blacklistModel.overtime}" readonly="readonly"  style="margin-top:0"/>
							</div>							
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">说明</label>
						<div class="col-sm-3">
							<form:textarea path="remark" cssClass="form-control" cols="20" rows="10" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary">确定</button>
							<a href="${ctxPath}/blackmanage/init"><button type="button" class="btn default cancel" >取消</button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<script type="text/javascript">
		$(document).ready( function () {
			var validator = $('#form').validate({
				rules : {
					blacktype : {
						isRightfulString : true,
						notnull : true,
						required : true
					},
					overtime : {
						notnull : true,
						required : true,
						maxlength : 60,
						compareoutDate:begintime
					}
				}
			});
			$("#overtime").on("change",function(){
				/* $('#begintime').valid(); */
				$('#form').validate().element('#overtime');
			}); 
			$("#begintime").on("change",function(){
				/*$('#form').valid();*/
				validator.element( "#overtime" ); 
			});
			
			//日期选择datepicker(时间范围  now ~ After a long time)
			$(".form_datetime_nl").datepicker({
				format: "yyyy-mm-dd",
			    todayBtn: "linked",
			    language: "zh-CN",
			    autoclose: true,
			   	startDate: 'document.getElementById("begintime").value',
			   	todayHighlight: true
			});
			
			//根据黑名单类型判断黑名单值的校验规则
			$(".form-group").on("change","#blacktype",function(){
				//黑名单类型为手机号码时
				if($('#blacktype').val() === '1'){
					//动态删除校验规则
					$("#blackvalue").rules("remove");
					$("#blackvalue").val("");
					//动态添加
					$('#blackvalue').rules("add",{
						notnull : true,
						required : true,
						mobile : true,
							messages : {  
							required  : "该字段不能为空", 
							mobile : "请输入正确的手机号码"
		                } 
					});
				}
				//黑名单类型为IP时
				if($('#blacktype').val() === '2'){
					//动态删除校验规则
					$("#blackvalue").rules("remove");
					$("#blackvalue").val("");
					//动态添加
					$('#blackvalue').rules("add",{
						notnull : true,
						required : true,
						ip : true,
							messages : {  
							required  : "该字段不能为空", 
							ip : "Ip地址格式错误"
		                } 
					});
				}
				//黑名单类型为OPENID时
				if($('#blacktype').val() === '3'){
					//动态删除校验规则
					$("#blackvalue").rules("remove"); 
					$("#blackvalue").val("");
					//动态添加
					$('#blackvalue').rules("add",{
						//未知校验规则
						notnull : true,
						required : true,
						messages : {  
							required  : "该字段不能为空"
		                } 
					});
				}
			});
		});
	</script>
</body>
</html>