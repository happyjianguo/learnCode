<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_CN" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_CN" class="ie9 no-js"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="zh_CN">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>场景化金融营销平台-后台管理</title>
<link rel="shortcut icon" type="image/x-icon" href="${ctxPath}/resources/img/favicon.ico"/>
<%@ include file="/WEB-INF/views/common/css.jsp"%>
<!--[if lt IE 9]>
		<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
<style type="text/css">
	body{
		/* 
		background: url(${ctxPath}/resources/img/loginbg.jpg) repeat;
		*/	
		padding-top: 4%;
		width: auto;
		overflow: hidden; 
	}
	.logo #logo_m{
		width: 30%;
		position: absolute;
		margin-top:80px;
		margin-left: 30px;
	} 
	.logo02_bg{
		padding-top: 1%;
		padding-bottom: 20%;
		background: url(${ctxPath}/resources/img/loginbg02.png) no-repeat;
		background-size: 100%;
		/*height: 500px;*/
		overflow: hidden;
		position: relative;
	}
	#logo_02{
		position: absolute;
		width: 10%;
		top: 7%;
		right: 34%;
	}
	
	#form_bg{
		position: absolute;
		width: 100%;
		margin-top: 10%;	
		margin-bottom: 4%;
		padding: 3% 0 3% 10%;	
		background: linear-gradient(to top,#D5D5D5,#F7F7F7);
		background-size: 100%;		
	}
	.form-yz{margin-left:20px;}	
	#checking{
		position: relative;		
		left: 17px;
	}
	#checking_no{
		padding-left:5%;
		margin-left: 5%;
	}
	#submit{
		position: absolute;
		left: 60%;
		margin-top: 30%;
		width: 90px;
	}	

	.control-label{
		font-weight:normal;
	}
	@media screen and (max-width:768px) {
		
    #submit{
		position: absolute;
		left: 60%;
		bottom: 45%;
		width: 90px;
		left: 48.5%;
		}
		.form-group,.form-inline{
			margin-left: 40% !important;
		}	
		#logo_m{
			margin-top:20px !important;
			margin-left: 30px;		
		}
	}
	
	@media screen and (max-width:1024px) {
		
    #submit{
		position: absolute;
		left: 60%;
		bottom: 48%;
		width: 90px;
		left: 55%;
		}
		.form-group,.form-inline{
			left: 48.5% !important;
		}	
		#logo_m{
			margin-top:20px !important;
			margin-left: 30px;		
		}
	}
	@media screen and (max-width:1922px) {		
    #submit{
		position: absolute;
		left: 60%;
		bottom: 45% !important;
		width: 90px;
		left: 58% !important;
		}
		.form-group,.form-inline{
			left: 48.5% !important;
		}	
		#logo_m{
			margin-top:20px !important;
			margin-left: 30px;		
		}
		#form_bg{
			margin-top: 11% !important;
		}
	}
	@media screen and (max-width:1368px) {		
    #submit{		
		left: 60% !important;
		margin-top: 28% !important;
		width: 90px;
		bottom: 47% !important;
	}
			
		#logo_m{		
		margin-top:80px !important;
			
		}
		#form_bg{
			margin-top: 10% !important;
		}
	}
</style>
</head>
<body>
	<div class="logo">
		<img id="logo_m" src="${ctxPath}/resources/img/logo_m2.png">		
	</div>
	<div class="logo02_bg ">
		<img id="logo_02" src="${ctxPath}/resources/img/logo_02.png" class="img-responsive">
		<form:form class="form-horizontal" method="post" action="${ctxPath}/login" id="form" >
			<div id="form_bg">
				
				<div class="form-group">
					<label for="username" class="col-sm-6 control-label">
						用户名:
					</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" name="username" autofocus="autofocus" style="width: 200px;" placeholder="请输入用户名"/>
					</div>
					
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-6 control-label">密&nbsp;码:</label>
					<div class="col-sm-3">
						<input type="password" class="form-control" name="password" style="width: 200px;" placeholder="请输入密码"/>
					</div>			
				</div>
				<c:if test="${optenable eq '1'}">
					<div class="form-group">
					<label for="password" class="col-sm-6 control-label">动态口令:</label>
					<div class="col-sm-3">
						<input type="password" class="form-control" name="otppwd" style="width: 200px;" placeholder="请输入动态口令"/>
					</div>			
				</div>
				</c:if>
<!-- 				<div class="form-inline " > -->
<!-- 					<label for="checking" class="col-sm-6 control-label">验证码:</label>							 -->
<!-- 						<input type="text" class="form-control " id="checking" name="checking" maxlength="4" style="width: 86px;" title="请输入验证码"/> -->
<!-- 						<span class="form-yz"> -->
<%-- 				        	<img id="validateNoImage" src="${ctxPath }/messagecode/getimagecode?randomKey=<%=String.valueOf(System.currentTimeMillis())%>" alt="" border="0" onclick="changeValidateNoImage();" /> --%>
<!-- 				        </span>						 -->
<!-- 				</div> -->
				<div class="error" style="display: ${param.error == true ? '' : 'none'}">
						<!-- 错误信息 -->
			            <span style="color:red">${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}</span>
				</div>
			</div>
			<div class="form-inline form-btn" >
<!-- 				<label class="sr-only col-sm-6 control-label">登录</label> -->
				<button type="submit" class="form-control " name="login" value="登录" id="submit" >登录</button>	
			</div>
		</form:form>
	</div>
	<%@ include file="/WEB-INF/views/common/js.jsp"%>
	<script type="text/javascript">
	//修改表单验证，提交
	$(document).ready(function() {
		$('#form').validate({
			//debug: true,
			rules : {
				username : {
					notnull:true,
					required : true
				},
				password : {
					notnull:true,
					required : true
				},
				otppwd : {
					notnull:true,
					required : true
				},
			}
		});
	});
		$(function(){
			var bheight=window.screen.availHeight;
			//alert(window.screen.availWidth);
			var s=bheight;
			//alert(bheight);
			$(".logo02_bg").css("height",s);
		});
		$("#form input").keypress(function(e) {
			var thisinput = this;
	        if (e.which == 13) {
	        	var inputs = $("#form input").not(":hidden");
	        	var nextflag = false;
	        	inputs.each(function(i, n){
	        		//光标切换至下一个input
	        		if(nextflag){
	        			n.focus();
	        			return false;
	        		}
	        		//查找当前元素
	        		if($(this).is(thisinput)){
	        			//提交form
		        		if(i===inputs.length-1){
		        			$("#submit").click();
		        			return false;
		        		}
	        			nextflag = true;
	        		}
	        	});
	            return false;
	        }
	    });
// 		function changeValidateNoImage(){  
// 		   var rand=Math.random(); 		   
// 		   document.getElementById("validateNoImage").src = "${ctxPath}/messagecode/getimagecode?randomKey="+rand;
// 		}
	</script>
</body>
</html>
