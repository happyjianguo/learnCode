<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<%@ include file="/WEB-INF/views/common/css.jsp"%>
		<%@ include file="/WEB-INF/views/common/js.jsp"%>
		<title>内管系统-用户注册</title>
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
	<div class="">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li class="active" id="bill_check">用户注册</li>
			</ol>
		</div>
		<div class="mainbox">
			<form:form id="eform" action="${ctxPath}/register/do" method="post" class="form-horizontal">
			
			  <div class="form-group">
			    <label class="col-sm-4 control-label">手机</label>
			    <div class="col-sm-3">
			      <input type="text" id="phoneno" name="phoneno" class="form-control" value="${registerAccountModel.phoneno}">
			    </div>
			  </div>
			
			  <%-- <div class="form-group">
			    <label class="col-sm-4 control-label">用户名</label>
			    <div class="col-sm-3">
			      <input type="text" id="username" name="username" class="form-control" value="${registerAccountModel.username}">
			    </div>
			  </div>  --%>	 
			  <div class="form-group">
			    <label class="col-sm-4 control-label">输入密码</label>
			    <div class="col-sm-3">
			      <input type="password" id="passwd" name="passwd" class="form-control" value="${registerAccountModel.passwd}">
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-4 control-label">确认密码</label>
			    <div class="col-sm-3">
			      <input type="password" id="passwdagain" name="passwdagain" class="form-control" value="${registerAccountModel.passwd}" >
			    </div>
			  </div>
			  <%-- <div class="form-group">
			    <label class="col-sm-4 control-label">姓名</label>
			    <div class="col-sm-3">
			      <input type="text" id="cname" name="cname" class="form-control" value="${registerAccountModel.cname}">
			    </div>
			  </div> --%>
			  <div class="form-group" style="display: none;">
			    <label class="col-sm-4 control-label">性别</label>
			    <div class="col-sm-3">
			      <input type="radio" name="mf" value="1" <c:if test="${registerAccountModel.mf == null or registerAccountModel.mf == '' or  registerAccountModel.mf == '1' }"> checked="checked" </c:if> >男</input>
			      <input type="radio" name="mf" value="2"  <c:if test="${registerAccountModel.mf == '2' }"> checked="checked" </c:if> >女</input>
			    </div>
			  </div>
			  <%-- <div class="form-group">
			    <label class="col-sm-4 control-label">邮箱</label>
			    <div class="col-sm-3">
			      <input type="text" id="email" name="email" class="form-control" value="${registerAccountModel.email}">
			    </div>
			  </div> --%>
			  <div class="form-group">
			    <label class="col-sm-4 control-label">验证码</label>
			    <div class="col-sm-3">
			      <input type="text" id="validcode" name="validcode" class="form-control col-sm-3" value="${registerAccountModel.validcode}">			      
			    </div>
			    <input type="button" id="sendcode" class="btn btn-primary" value="发送验证码">
			  </div>
			  <div class="form-group">
		  		<div class="col-sm-offset-4 col-sm-8">
					<button type="submit" class="btn btn-primary" >马上注册</button>
				</div>								  	
			  </div>  
			</form:form>
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
		
		
		<!-- 提示信息 -->
		<div id="prompt" class="prompt">
			<span id="prompt_text">提示信息</span>
			<i id="prompt_close" class="fa fa-close prompt_close"></i>
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
		//修改表单验证，提交
			$(document).ready(function() {
				var time = 60 ;
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
				
				
				$("#sendcode").click(function(){
					var phoneno =  $("#phoneno").val(); 
						var flag = $("#eform").validate().element($("#phoneno")) ;
						if(phoneno =='' || !/^1\d{10}$/.test(phoneno) || !flag){
							return false
						}
						$.ajax({
	                		url:"${ctxPath}/register/getvalidecode",
	                		data: {"phoneNo":phoneno,"checkFlag":0} ,
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
				function refreshMsg(){
					if(time > 0){
						$("#sendcode").val(time +"秒后点击再次发送");
						$("#sendcode").prop("disabled",true);
						time--;
						setTimeout(refreshMsg,1000);//1000为1秒钟,设置为1分钟。  
					}else{
						$("#sendcode").val("发送验证码");
						$("#sendcode").prop("disabled",false);
						time = 60;
					}
					
				};
				$("#passwd").change(function(){
					validator.element("#passwdagain");
				});
				var validator = $('#eform').validate({
					//debug: true,
					rules : {
						username : {
							required : true,
							pwsCheck : true
							
						},
						passwd : {
							required : true,
							tokenCheck : true,
							pwslength : true,
							pwsformat : true,
							
						},
						passwdagain : {
							required : true,
							tokenCheck : true,
							pwslength : true,
							pwsformat : true,
							comparePws : "#passwd"
						},
						cname : {
							required : true,
							isRightfulString : true,
							maxlength: 30,
							minlength: 2
						},
						phoneno : {
							required : true,
							mobile : true,
							phoneexist : "${ctxPath}/personalcenter/checkphone"
						},
						validcode : {
							required : true,
							validtype : true,
							checkvalidate : "${ctxPath}/personalcenter/checkvalidate"
						}
					}
				});
			});
		</script>
	</body>
</html>