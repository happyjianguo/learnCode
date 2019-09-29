<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><fmt:message key="recharge.batchrecharge.title"/></title>
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
				<div class="main-content">
					<div class="list-nav">
						<ol class="breadcrumb">
							<li><fmt:message key="recharge.batchcurrbusilog.recharge"/></li>
							<li class="active" id="bill_check"><fmt:message key="recharge.batchrecharge.batchrecharge"/></li>
						</ol>
					</div>
						<div class="mainbox">
							<div class="table-tit">
								<div class="tit-left" >
									<fmt:message key="recharge.batchrecharge.batchrecharge"/>
								</div>
								<div class="tit-btn">
<!-- 								<span><button type="button" class="btn btn-primary addImuser" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i>新增</button></span> -->
<%--  									<span><a href="${ctxPath}/batchrecharge/reviewinit" class="btn btn-primary"><i class="fa fa-plus"></i>查询批次</a></span>  --%>
								
								</div>											
							</div>
							<div class="mainbox">
									<div class="row">
										<form:form id="form" modelAttribute="batchinfoModel" action="${ctxPath}/batchrecharge/upload?${_csrf.parameterName}=${_csrf.token}" methodParam="post" enctype="multipart/form-data"
											cssClass="form-horizontal">
											<div class="form-group">
												<label class="col-sm-4 control-label"><fmt:message key="recharge.batchrecharge.pcname"/></label>
												<div class="col-sm-3">
													<form:input path="batchname" cssClass="form-control" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-4 control-label"><fmt:message key="recharge.customer"/></label>
												<div class="col-sm-3 select-list" >
													<select id="cid" name="cid" class="form-control select cid" >
														<option value=""><fmt:message key="recharge.pleasechoose"/></option>
														<c:forEach var="access" items="${batchinfoModel.cfCustomerList}">
															<option value="${access.id }">${access.cname }</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
											<label class="col-sm-4 control-label"><fmt:message key="recharge.acid"/></label>
												<div class="col-sm-3 select-list" >
													<select id="apno" name="apno" class="form-control select acid" >
													</select>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-sm-4 control-label"><fmt:message key="recharge.batchrecharge.choosefile"/></label>
												<div class="col-sm-4" style="padding-top:8px;">
													<span style="float: left; padding-right:5px;"><a href="<c:url value='/resources/templent/templent.xlsx'/>"><fmt:message key="recharge.batchrecharge.downloadfile"/></a></span>
													<span><input type="file" id="file" name="myfile"/></span>	
												</div>
												
											</div>
											<div class="form-group">
												<div class="col-sm-offset-4 col-sm-8">
													<button type="submit" class="btn btn-primary"><fmt:message key="button.submit"/></button>
													<a href="${ctxPath}/batchrecharge/init"><button type="button" class="btn default cancel" ><fmt:message key="button.cancel"/></button></a>
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
				//接入者初始化
			    $(".form-group").on("change",".cid",function(){
			    	getacid();
			    });
				//获取接入者
		    	function getacid(){
		    		$.ax({
		        		url  :"${ctxPath}/batchrecharge/ajax/initClient",
		        		data : {cid:$('#cid').val()},
				        successfn : function(result) {
				        	
			        		$(".acid").find("option").not("[value='']").remove(); 
				        	$.each(result.data, function(i, item){
			        			$(".acid").append("<option value="+item.id+">"+item.acname+"</option>");
				        	});  
				        }
		        	});
		    	}
		    	$('#form').validate({
					rules : {
						batchname : {
							isRightfulString:true,
							maxlength : 20,
							notnull : true,
							required : true
						},
						cid : {
							notnull : true,
							required : true
						},
						apno : {
							notnull : true,
							required : true
						},
						myfile : {
							notnull : true,
							required : true
						}
					}
				});
			});
		</script>
</body>
</html>