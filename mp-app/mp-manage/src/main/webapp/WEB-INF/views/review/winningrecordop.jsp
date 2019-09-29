<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>审核管理-订单充值审核</title>
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li>审核管理</li>
				<li class="active" id="bill_check">充值订单审核</li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form id="form" modelAttribute="cloudwinningrecordModel" action="${ctxPath}/winningrecord/reviewsb" methodParam="post"
					cssClass="form-horizontal">
					<form:input path="id" type="hidden"  cssClass="form-control" />
					<div class="form-group">
						<label class="col-sm-4 control-label">中奖ID</label>
						<div class="col-sm-3">
							<input type="text" id="username" name="username" class="form-control" value="${cloudwinningrecordModel.id}">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label">审核状态</label>
						<div class="col-sm-3">
							<select id="preliminarystatus" name="preliminarystatus" class="form-control select" >
								<option value="1">审核通过</option>
								<option value="2">审核不通过</option>
							</select>
						</div>
					</div>
					
<!-- 					<div class="form-group"> -->
<!-- 						<label class="col-sm-4 control-label">拒绝原因</label> -->
<!-- 						<div class="col-sm-3"> -->
<%-- 							<form:input name="reviewdes" path="reviewdes" cssClass="form-control" /> --%>
<!-- 						</div> -->
<!-- 					</div> -->
					
					<div class="form-group" id="falseDescDiv">
						<label class="col-sm-4 control-label">审核意见</label>
						<div class="col-sm-3">
						    <input type="text" id="reviewdes" name="reviewdes" class="form-control" value="${registerReviewModel.reviewdes}">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary">确定</button>
							<a href="${ctxPath}/winningrecord/init"><button type="button" class="btn default cancel" >取消</button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<script type="text/javascript">
	
		$('#form').validate({
			rules : {
				reviewdes :{
					maxlength : 255
				}
			}
		});
		
		$("#preliminarystatus").on("change",function(){
			var status = $('#preliminarystatus').val();
			if(status == 4) {
				$('#reviewdes').rules("add",{
						notnull : true,
						required : true,
							messages : {  
							required  : "审核不通过时请填写审核意见!", 
		                } 
				});
			} else {
				 $('#reviewdes').rules("remove");
				 $('#reviewdes').rules("add",{
					positiveinteger:true,
				}); 
			}
		}); 
		
	</script>
</body>
</html>