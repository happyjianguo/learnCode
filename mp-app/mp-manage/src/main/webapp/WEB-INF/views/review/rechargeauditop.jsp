<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>审核管理-人工充值审核</title>
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
				<li class="active" id="bill_check">人工充值审核</li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form id="form" modelAttribute="cloudaccountsurplusrevModel" action="${ctxPath}/rechargeaudit/rechargesb" methodParam="post"
					cssClass="form-horizontal">
					<form:input path="orgid" type="hidden"  cssClass="form-control"  />
					<form:input path="id" type="hidden"  cssClass="form-control"  />
					<form:input path="amount" type="hidden"  cssClass="form-control"  />
					<div class="form-group">
						<label class="col-sm-4 control-label">机构名称</label>
						<div class="col-sm-3">
							<input type="text" id="orgname" name="orgname" class="form-control" value="${cloudaccountsurplusrevModel.orgname}" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">充值金额</label>
						<div class="col-sm-3">
							<input type="text" id="showmoney" name="showmoney" class="form-control" value="${cloudaccountsurplusrevModel.money}" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">平台币数量</label>
						<div class="col-sm-3">
							<input type="text" id="showamount" name="showamount" class="form-control" value="${cloudaccountsurplusrevModel.amount}" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">审核状态</label>
						<div class="col-sm-3">
							<select id="rstatus" name="rstatus" class="form-control select" >
								<option value="0">审核通过</option>
								<option value="1">审核不通过</option>
							</select>
						</div>
					</div>
					<div class="form-group" id="falseDescDiv">
						<label class="col-sm-4 control-label">审核意见</label>
						<div class="col-sm-3">
<%-- 							<form:textarea path="textarea" cssClass="form-control" cols="20" rows="10" /> --%>
						    <input type="textarea" id="audresult" name="audresult" class="form-control">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary">确定</button>
							<a href="${ctxPath}/rechargeaudit/init"><button type="button" class="btn default cancel" >取消</button></a>
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
				audresult :{
					maxlength : 255
				}
			}
		});
		
		$("#rstatus").on("change",function(){
			var status = $('#rstatus').val();
			if(status == 1) {
				$('#audresult').rules("add",{
						notnull : true,
						required : true,
							messages : {  
							required  : "审核不通过时请填写审核意见!", 
		                } 
				});
			} else {
				 $('#audresult').rules("remove");
				 $('#audresult').rules("add",{
					positiveinteger:true,
				}); 
			}
			
		}); 
		
	</script>
</body>
</html>