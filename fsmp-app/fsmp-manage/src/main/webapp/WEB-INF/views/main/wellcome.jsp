<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>内管系统</title>
<style>
	body{ background: #fff;}
</style>
</head>
<body>
	<div class="main-content">
		<div class="welcome">
			<img src="${ctxPath}/resources/img/w-t.png">
		</div>
		<!-- <div class="content_box">
			<ul>
				<li class="oftotalmoney">
					<em id="oftotalmoney"></em>
					<span>欧飞订单总金额</span>
					<i class="fa fa-area-chart"></i>
				</li>
				<li class="wstotalmoney">		
					<em id="wstotalmoney"></em>
					<span>网宿订单总金额</span>
					<i class="fa fa-bar-chart"></i>
				</li>
				<li class="ofbalance">
					<em id="ofbalance"></em>
					<span>欧飞余额</span>
					<i class="fa fa-pie-chart"></i>
				</li>
			</ul>					
		</div> -->
	</div>
	<script type="text/javascript">
		//新增管理员表单验证，提交
		$(document).ready( function () {
		    	/* $.ax({
            		url  :"${ctxPath}/main/ajax/oforderprice",
			        successfn : function(result) {
			        	if(result.errorCode==="000000"){
			        		$("#oftotalmoney").html(result.data.price)
			        	}
			        }
            	});
		    	$.ax({
            		url  :"${ctxPath}/main/ajax/wsorderprice",
			        successfn : function(result) {
			        	if(result.errorCode==="000000"){
			        		$("#wstotalmoney").html(result.data.price)
			        	}
			        }
            	}); 
		    	$.ax({
            		url  :"${ctxPath}/main/ajax/ofbalance",
			        successfn : function(result) { 	
			        	if(result.errorCode==="000000"){
			        		$("#ofbalance").html(result.data.ret_leftcredit);
			        	}
			        }
            	}); */
		});
	</script>
</body>
</html>