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
			<form:form action="${ctxPath}/drop/modifysecret" method="post"
				id="eform">
				<input type="hidden" id="username" name="username" value="${username}">
				<!-- 新密码-->
				<div class="form-group" style="height: auto; overflow: hidden;">
					<div class="col-sm-offset-4 col-sm-4">
						<div class="input-group">
							<span class="input-group-addon">新密码</span> 
							<input type="password" class="form-control" id="newsecret" name="newsecret" />
						</div>
					</div>
				</div>

				<!-- 新密码确认-->
				<div class="form-group" style="height: auto; overflow: hidden;">
					<div class="col-sm-offset-4 col-sm-4">
						<div class="input-group">
							<span class="input-group-addon">请再次输入密码</span>
							<input type="password" class="form-control" id="newsecretagain" name="newsecretagain" />
						</div>
					</div>
				</div>
				
				<div class="form-group"  style="margin-left: 25px;">
					<input type="button" class="btn btn-primary col-sm-offset-4" value="提交密码" id="submitsecret"></input>
				</div>
				
			</form:form>
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
				
				var validator = $('#eform').validate({
					//debug: true,
					rules : {
						newsecret : {
							required : true,
							tokenCheck : true,
							pwslength : true,
							pwsformat : true,
						},
						newsecretagain : {
							required : true,
							tokenCheck : true,
							pwslength : true,
							pwsformat : true,
							comparePws : "#newsecret"
						}
					}
				});
				
				$("#newsecret").change(function(){
					validator.element("#newsecretagain");
				});
				
				$("#submitsecret").click(function(){
					var newpass =  $("#newsecret").val();
					var newpassagain =  $("#newsecretagain").val();
					if(newpass === newpassagain){
						$("#eform").submit();
					}else{
						$("#errtextmsg").text("两次密码输入不一致！");
						$('#myModal-errmsg').modal('show');
						return false
					}
				});
			})
		</script>
</body>
</html>