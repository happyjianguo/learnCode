<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>内管系统-个人信息修改</title>
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<div class="main-content">
		<div class="mainbox">
			<div class="row">
				<form:form id="usereditform" modelAttribute="userManageModel"  action="${ctxPath}/personalcenter/update?${_csrf.parameterName}=${_csrf.token}" methodParam="post"
					enctype="multipart/form-data" cssClass="form-horizontal" >
					<div class="form-group">
						<div class="col-sm-4">
							<form:input path="userid" type="hidden" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
							<label class="col-sm-4 control-label">头像</label>
							<div class="col-sm-6">
								<input id="exampleInputFile" name="myfiles" type="file" multiple data-max-file-count="1">
							</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">用户名</label>
						<div class="col-sm-3">
							<c:if test="${userManageModel.username != null and userManageModel.username != ''}">  
								<form:input path="username"   cssClass="form-control" readonly="true" />
							</c:if>
							<c:if test="${userManageModel.username == null || userManageModel.username == ''}">  
								<form:input path="username"   cssClass="form-control" />
							</c:if>
							
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">姓名</label>
						<div class="col-sm-3">
							<form:input path="cname" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
				    	<label class="col-sm-4 control-label">性别</label>
					    <div class="col-sm-3">
					      <input type="radio" name="mf" value="1" <c:if test="${userManageModel.mf == null or userManageModel.mf == '' or  userManageModel.mf == '1' }"> checked="checked" </c:if> >男</input>
					      <input type="radio" name="mf" value="2"  <c:if test="${userManageModel.mf == '2' }"> checked="checked" </c:if> >女</input>
					    </div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">手机号</label>
						<div class="col-sm-3">
							<form:input path="phoneno" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">邮箱</label>
						<div class="col-sm-3">
							<form:input path="email" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">验证码</label>
						<div class="col-sm-3">
							<form:input path="validecode" cssClass="form-control" />							
						</div>
						<input id="sendcode" type="button" value="发送" class="btn btn-primary">
					</div>
					<!-- 	暂时不用
							<div class="form-group">
							<label class="col-sm-4 control-label">营业执照</label>
							<div class="col-sm-6">
								<input id="businessLicense" name="mylicense" type="file"  multiple data-max-file-count="1">
							</div>
					</div> -->
					<%-- <div class="form-group">
						<label class="col-sm-4 control-label">角色名</label>
						<div class="col-sm-3">
						<p class="form-control-static">
							<c:forEach var="role" items="${userManageModel.roleList}" varStatus="vs">
								<c:if test="${!vs.first}">,</c:if>
								${role.roleName}
							</c:forEach>
						</p>
							
						</div>
					</div> --%>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary">确定</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript">
			$(document).ready(function() {
				var time =60;
				var $input = $('#exampleInputFile');
		        if ($input.length) {
		            $input.fileinput({
	            		showUpload : false,
	                    showRemove : true,
	                    language : 'zh',
	                    allowedPreviewTypes: ['image'],
	                    allowedFileTypes: ['image'],
	                    allowedFileExtensions:  ['jpg', 'png', 'jpeg'],
	                    maxFileSize : 2000,		
	            });
		        }
		        var $license = $('#businessLicense');
		        if ($license.length) {
		            $license.fileinput({
	            		showUpload : false,
	                    showRemove : true,
	                    language : 'zh',
	                    allowedPreviewTypes: ['image'],
	                    allowedFileTypes: ['image'],
	                    allowedFileExtensions:  ['jpg', 'png', 'jpeg'],
	                    maxFileSize : 2000,		
	            });
		        }
		        
		        function refreshMsg(){
					if(time > 0){
						$("#sendcode").val(time +"秒后点击再次发送");
						$("#sendcode").prop("disabled",true);
						time--;
						setTimeout(refreshMsg,1000);//1000为1秒钟,设置为1分钟。  
					}else{
						$("#sendcode").val("发送");
						$("#sendcode").prop("disabled",false);
						time = 60;
					}
					
				};
		        
				$('#usereditform').validate({
					rules : {
						phoneno : {
							required : true,
							mobile : true,
							checkexist :"${ctxPath}/personalcenter/phoneexist"
						},
						username : {
							required : true,
							pwsCheck : true,
							namelength : true,
							checkname :"${ctxPath}/personalcenter/phoneexist"
						},
						email : {
							required : true,
							email : true,
							checkemail :"${ctxPath}/personalcenter/phoneexist",
							maxlength : 40 
						},
						validecode : {
							required : true,
							validtype : true,
							length : 6,
							checkvalidate : "${ctxPath}/personalcenter/checkvalidate"
						}
					}
				});
				$("#sendcode").click(function(){
					
					$.ajax({
                		url:"${ctxPath}/personalcenter/getvalidcode",
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
			});
		</script>
	</body>
</html>