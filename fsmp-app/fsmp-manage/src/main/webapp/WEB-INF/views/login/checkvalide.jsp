<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="/WEB-INF/views/common/css.jsp"%>
<%@ include file="/WEB-INF/views/common/js.jsp"%>

<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li class="active" id="bill_check">忘记密码</li>
			</ol>
		</div>
		<div class="mainbox">
			<form:form action="${ctxPath}/drop/checkvalidecode" method="post"
				id="eform">
				<!-- 姓名 -->
				<%-- <div class="form-group">
					<div class="input-group input-group-lg">
						<span class="input-group-addon"><li
							class="fa fa-fw fa-user"></li>用户名</span> <input type="text"
							class="form-control" id="username" name="username" value="${username}"/>
					</div>
				</div> --%>

				<!-- 邮箱 -->
				<!-- <div class="form-group">
					<div class="input-group input-group-lg">
						<span class="input-group-addon"><li
							class="fa fa-fw fa-user"></li>邮箱</span> <input type="text"
							class="form-control" id="mailname" name="mailname" />
					</div>
				</div> -->
				<!-- 手机号 -->
				<div class="form-group" style="height: auto; overflow: hidden;">
					<div class="col-sm-offset-4 col-sm-4">
						<div class="input-group">
							<span class="input-group-addon">手机号</span>
							<input type="text" class="form-control" id="phoneno" name="phoneno" value="${phoneno}" />
						</div>
					</div>
				</div>				
				<!-- 验证码 -->
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4">
						<div class="input-group">
							<span class="input-group-addon">验证码</span> 
							<input type="text" class="form-control" id="validecode" name="validecode" value="${validecode}" />								
						</div>												
					</div>
					<input type="button" name="msgbtn" id="msgbtn" class="btn btn-primary" value="发送验证码" />
				</div>
				<div class="form-group"  style="margin-left: 25px;">
					<button type="submit" class="btn btn-primary col-sm-offset-4" value="tijiao">提交</button>
				</div>
			</form:form>
			<!-- 提示信息 -->
			<!-- <div id="prompt" >
				<span id="prompt_text">提示信息</span>
				<i id="prompt_close" class="fa fa-close prompt_close"></i>
			</div> -->
		</div>
		
		<!-- 	提示框 -->
		<div class="modal fade" id="myModal-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">提示信息</h4>
					</div>
					<div class="modal-body">
						<div class="modal-prompt deleteAlert">
							<i class="fa fa-check"></i><span id="textmsg"></span>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default deleteCancle"
							data-dismiss="modal">关闭</button>
<!-- 						<button type="button" class="btn btn-primary deleteConfirm delMsgid">确认</button> -->
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="myModal-errmsg" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalerrmsgLabel">提示信息</h4>
					</div>
					<div class="modal-body">
						<div class="modal-prompt deleteAlert">
							<i class="fa fa-exclamation"></i><span id="errtextmsg"></span>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default deleteCancle"
							data-dismiss="modal">关闭</button>
<!-- 						<button type="button" class="btn btn-primary deleteConfirm delMsgid">确认</button> -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
			<script type="text/javascript">
				$(function(){
					var time = 60;
					
					//页面提示信息
					function promptClose(){
						$("#prompt").fadeOut("slow");
					};
					//错误提示信息
					if("${errorCode}"!="" && "${errorCode}"!="000000"){
						if($("#errorcm").length > 0){
							return;
						}
						$("#prompt_text").text("${errorMsg}");
						$("#prompt").addClass("prompt_t");
						$("#prompt").show();
					}
					$("#prompt_close").click(promptClose);
					window.setTimeout(promptClose, 3000);
					
					function refreshMsg(){
						if(time > 0){
							$("#msgbtn").val(time +"秒后点击再次发送");
							$("#msgbtn").prop("disabled",true);
							time--;
							setTimeout(refreshMsg,1000);//1000为1秒钟,设置为1分钟。  
						}else{
							$("#msgbtn").val("发送");
							$("#msgbtn").prop("disabled",false);
							time = 60;
						}
						
					};
					
					$('#eform').validate({
						//debug: true,
						rules : {
							phoneno : {
								required : true,
								mobile : true,
								checkphoneno : "${ctxPath}/personalcenter/checkphone"
							},
							validecode : {
								required : true,
								validtype : true,
								checkvalidate : "${ctxPath}/personalcenter/checkvalidate"
							}
						}
					});
					
					$("#msgbtn").click(function(){
						var phoneno =  $("#phoneno").val(); 
							var flag =  $("#eform").validate().element($("#phoneno"));
							if(phoneno =='' || !/^1\d{10}$/.test(phoneno) ||!flag){
								return false
							}
							
							$.ajax({
		                		url:"${ctxPath}/drop/getvalidecode",
		                		data: {"phoneNo":phoneno,"checkFlag":1} ,
		                		 success: function(data, textStatus, jqXHR){
									if(data.result == 1){ 
										refreshMsg();
									} else {
										$("#prompt_text").text(data.msg);
										$("#prompt").addClass("prompt_t");
										$("#prompt").show();
									}
		                		 }
		                	});
					});
				})
			</script>
</body>
</html>