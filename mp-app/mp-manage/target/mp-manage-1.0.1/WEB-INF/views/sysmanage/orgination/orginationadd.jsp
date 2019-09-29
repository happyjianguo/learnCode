<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>机构管理-新增机构</title>
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li>机构管理</li>
				<li class="active" id="bill_check">新增机构</li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form id="orginationaddform" modelAttribute="cloudplatformorginationModel" action="${ctxPath}/orgination/adddata" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label">机构类别</label>
							<div class="col-sm-4">
							      <select id="orgtype" name="orgtype" class="form-control select">
							      	<option value="" selected>请选择</option>
							      	<option value="0">总行机构</option>
									<option value="1">分行机构</option>
									<option value="2">网点机构</option>
							      </select>
							</div>
					</div>
					<div class="form-group" id="totalorgination">
						<label class="col-sm-4 control-label">总行机构</label> 
						<div class="col-sm-4">
								<select id="parentorgid" name="parentorgid" class="form-control select">
									<option value="" selected>请选择</option>
									<c:forEach var="orgination" items="${cloudplatformorginationModel.cloudplatformorginationList}">
										<option value="${orgination.id}">${orgination.orgname}</option>
									</c:forEach>
								</select>
						</div>
					</div>
					<div class="form-group" id="branchorgination">
						<label class="col-sm-4 control-label">分行机构</label>
						<div class="col-sm-4"> 
							<select id="branchid" name="branchid" class="form-control select branchid" >
								<option value="" selected>请选择</option>
							</select>	
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">机构名</label>
						<div class="col-sm-4">
							<form:input path="orgname" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">机构状态</label>
							<div class="col-sm-4">
							      <select id="orgstatus1" name="orgstatus1" class="form-control select">
							      	<option value="" selected>请选择</option>
							      	<option value="0">未生效</option>
									<option value="1">生效</option>
							      </select>
							</div>
					</div>
					<div class="form-group">
						<input type="hidden" id="orgstatus" name="orgstatus" value="">
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">机构备注</label>
						<div class="col-sm-4">
							<form:textarea path="note" cssClass="form-control" cols="20" rows="10" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
							<button type="submit" class="btn btn-primary">确定</button>
							<a href="${ctxPath}/orgination/init"><button type="button" class="btn default cancel" >取消</button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<script type="text/javascript">
		$(document).ready( function () {
			
			$('#orginationaddform').validate({
				rules : {
					orgname : {
						notnull : true,
						required : true,
						qlength : 50,
						isRightfulString :true
					},
					note : {
						maxlengthname : 395
					}
				}
			});
			//动态添加校验规则
			$('#orgtype').rules("add",{
				notnull : true,
				required : true,
					messages : {  
					required  : "请选择", 
                } 
			});
			$('#orgstatus1').rules("add",{
				notnull : true,
				required : true,
					messages : {  
					required  : "请选择", 
                } 
			});
			//初始化隐藏总行机构和分行机构下拉框
			document.getElementById("totalorgination").style.display="none";//隐藏
			document.getElementById("branchorgination").style.display="none";//隐藏  
			
			//分行机构机构类型初始化
		    $(".form-group").on("change","#orgtype",function(){
		    	document.getElementById("totalorgination").style.display="none";//隐藏
				document.getElementById("branchorgination").style.display="none";//隐藏 
		    	//添加总行机构
				if($('#orgtype').val()==='0'){
					$('#branchid').rules("remove");
					$('#parentorgid').rules("remove");
					$('#branchid').val("");
					$('#parentorgid').val("");
				}
		    	//添加分行机构
				if($('#orgtype').val()==='1'){
					document.getElementById("totalorgination").style.display="";//显示
					$('#branchid').rules("remove");
					$('#parentorgid').rules("remove");
					$('#branchid').val("");
					$('#parentorgid').val("");
					//添加校验
					$('#parentorgid').rules("add",{
						notnull : true,
						required : true,
							messages : {  
							required  : "请选择", 
		                } 
					});
				}
				//添加网点机构
		    	if($('#orgtype').val()==='2'){
					document.getElementById("totalorgination").style.display="";//显示
					document.getElementById("branchorgination").style.display="";//显示
					$('#branchid').val("");
					$('#parentorgid').val("");
					//添加校验
					$('#parentorgid').rules("add",{
						notnull : true,
						required : true,
							messages : {  
							required  : "请选择", 
		                } 
					});
					$('#branchid').rules("add",{
						notnull : true,
						required : true,
							messages : {  
							required  : "请选择", 
		                } 
					});
				}
		    });
			
			//分行机构初始化
		    $(".form-group").on("change","#parentorgid",function(){
		    	setPstatus();
		    	getbranchid();
		    });
			//网点机构状态初始化
		    $(".form-group").on("change","#branchid",function(){
		    	setBstatus();
		    });
			//改变状态向隐藏域赋值
		    $(".form-group").on("change","#orgstatus1",function(){
		    	$("#orgstatus").val($('#orgstatus1').val());
		    });
		   
		  //根据上一级机构状态设置新增机构状态
	    	function setBstatus(){
	    		$('#orgstatus1').attr("disabled",false);
	    		$("#orgstatus").val("");
	    		$("#orgstatus1").val("");
	    		$.ax({
            		url  :"${ctxPath}/orgination/ajax/queryPstatusById",
            		data : {queryparentorgid:$('#branchid').val()},
			        successfn : function(result) {	
			        	 if(result.data.orgstatus=="0"){
			        		 $("#orgstatus").val("0");
			        		 $("#orgstatus1").val("0");
			        		 $('#orgstatus1').attr("disabled",true);
			        	 }
			        }
            	});
	    	}
		  //根据上一级机构状态设置新增机构状态
	    	function setPstatus(){
	    		$('#orgstatus1').attr("disabled",false);
	    		$("#orgstatus").val("");
	    		$("#orgstatus1").val("");
	    		$.ax({
            		url  :"${ctxPath}/orgination/ajax/queryPstatusById",
            		data : {queryparentorgid:$('#parentorgid').val()},
			        successfn : function(result) {	
			        	 if(result.data.orgstatus=="0"){
			        		 $("#orgstatus").val("0");
			        		 $("#orgstatus1").val("0");
			        		 $('#orgstatus1').attr("disabled",true);
			        	 }
			        }
            	});
	    	}
		  //获取分行机构
	    	function getbranchid(){
	    		$.ax({
            		url  :"${ctxPath}/orgination/ajax/initbranchidbystatus",
            		data : {queryparentorgid:$('#parentorgid').val()},
			        successfn : function(result) {	
		        		$(".branchid").find("option").not("[value='']").remove(); 
			        	$.each(result.data, function(i, item){ 
		        			$(".branchid").append("<option value="+item.id+">"+item.orgname+"</option>");
			        	});  	
			        }
            	});
	    	}
		});
	</script>
</body>
</html>