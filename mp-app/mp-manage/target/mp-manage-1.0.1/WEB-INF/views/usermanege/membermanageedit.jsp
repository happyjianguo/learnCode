<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员管理-修改信息</title>
<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<li>会员管理</li>
				<li class="active" id="bill_check">会员管理</li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="row">
				<form:form id="usereditform" modelAttribute="registerReviewModel" action="${ctxPath}/membermanage/editdata" methodParam="post"
					cssClass="form-horizontal">
					<div class="form-group">
						<div class="col-sm-4">
							<form:input path="userid" type="hidden" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">手机号</label>
						<div class="col-sm-4">
							<form:input path="phoneno" cssClass="form-control" readonly="true" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">用户名</label>
						<div class="col-sm-4">
							<form:input path="username" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">姓名</label>
						<div class="col-sm-4">
							<form:input path="cname" cssClass="form-control" />
							<!-- <input type="email" class="form-control" id="inputEmail3" placeholder="Email"> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">性别</label>
						<div class="col-sm-4">
								<select id="mf" name="mf" class="form-control select">
							      	<option value="1" ${'1' eq registerReviewModel.mf ? 'selected="selected"':''} >男</option>
									<option value="2" ${'2' eq registerReviewModel.mf ? 'selected="selected"':''} >女</option>
							    </select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">邮箱</label>
						<div class="col-sm-4">
							<form:input path="email" cssClass="form-control" />
						</div>
					</div>
					<c:choose> 
		            	<c:when test="${registerReviewModel.status eq '3'}">
		            		<div class="form-group">
								<label class="col-sm-4 control-label">用户状态</label>
								<div class="col-sm-4">
										<select id="status" name="status" class="form-control select" disabled="disabled">
									      	<option value="0" ${'0' eq registerReviewModel.status ? 'selected="selected"':''} >失效</option>
											<option value="1" ${'1' eq registerReviewModel.status ? 'selected="selected"':''} >正常</option>
											<option value="2" ${'2' eq registerReviewModel.status ? 'selected="selected"':''} >冻结</option>
											<option value="3" ${'3' eq registerReviewModel.status ? 'selected="selected"':''} >待审核</option>
											<option value="4" ${'4' eq registerReviewModel.status ? 'selected="selected"':''} >审核失败</option>
									    </select>
								</div>
							</div>
		            	</c:when>
		            	<c:when test="${registerReviewModel.status eq '4'}">
		            		<div class="form-group">
								<label class="col-sm-4 control-label">用户状态</label>
								<div class="col-sm-4">
										<select id="status" name="status" class="form-control select" disabled="disabled">
									      	<option value="0" ${'0' eq registerReviewModel.status ? 'selected="selected"':''} >失效</option>
											<option value="1" ${'1' eq registerReviewModel.status ? 'selected="selected"':''} >正常</option>
											<option value="2" ${'2' eq registerReviewModel.status ? 'selected="selected"':''} >冻结</option>
											<option value="3" ${'3' eq registerReviewModel.status ? 'selected="selected"':''} >待审核</option>
											<option value="4" ${'4' eq registerReviewModel.status ? 'selected="selected"':''} >审核失败</option>
									    </select>
								</div>
							</div>
		            	</c:when> 
		            	<c:when test="${registerReviewModel.status eq '0'}">
		            		<div class="form-group">
								<label class="col-sm-4 control-label">用户状态</label>
								<div class="col-sm-4">
										<select id="status" name="status" class="form-control select">
									      	<option value="0" ${'0' eq registerReviewModel.status ? 'selected="selected"':''} >失效</option>
											<option value="1" ${'1' eq registerReviewModel.status ? 'selected="selected"':''} >正常</option>
											<option value="2" ${'2' eq registerReviewModel.status ? 'selected="selected"':''} >冻结</option>
									    </select>
								</div>
							</div>
		            	</c:when> 
		            	<c:when test="${registerReviewModel.status eq '1'}">
		            		<div class="form-group">
								<label class="col-sm-4 control-label">用户状态</label>
								<div class="col-sm-4">
										<select id="status" name="status" class="form-control select">
									      	<option value="0" ${'0' eq registerReviewModel.status ? 'selected="selected"':''} >失效</option>
											<option value="1" ${'1' eq registerReviewModel.status ? 'selected="selected"':''} >正常</option>
											<option value="2" ${'2' eq registerReviewModel.status ? 'selected="selected"':''} >冻结</option>
									    </select>
								</div>
							</div>
		            	</c:when> 
		            	<c:when test="${registerReviewModel.status eq '2'}">
		            		<div class="form-group">
								<label class="col-sm-4 control-label">用户状态</label>
								<div class="col-sm-4">
										<select id="status" name="status" class="form-control select"">
									      	<option value="0" ${'0' eq registerReviewModel.status ? 'selected="selected"':''} >失效</option>
											<option value="1" ${'1' eq registerReviewModel.status ? 'selected="selected"':''} >正常</option>
											<option value="2" ${'2' eq registerReviewModel.status ? 'selected="selected"':''} >冻结</option>
									    </select>
								</div>
							</div>
		            	</c:when> 
		            </c:choose>
		            <div class="form-group">
						<label class="col-sm-4 control-label">是否修改所属机构</label>
						<div class="col-sm-4">
								<select id="mflg" name="mflg" class="form-control select">
							      	<option value="0" >否</option>
									<option value="1" >是</option>
							    </select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">所属机构</label>
						<div class="col-sm-4">
							<select id="orgid" name="orgid" class="form-control select">
								<option value="">-请选择-</option> 		
								<c:forEach var="role" items="${registerReviewModel.cloudpforgList}">
								   <option value="${role.id}"  <c:forEach var="orgidid" items="${registerReviewModel.orgid}">
								   ${orgidid eq role.id  ? 'selected="selected"':''}
								   </c:forEach>										
									>${role.orgname}</option>																	
								</c:forEach>																																																													
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-8">
						<c:if test="${registerReviewModel.email != null and registerReviewModel.email != '' }">
							<button type="submit" formaction="${ctxPath}/membermanage/initPwd" form="usereditform" class="btn btn-primary">重置密码</button>
						</c:if>
							<button type="submit" class="btn btn-primary">确定</button>
							<a href="${ctxPath}/membermanage/init"><button type="button" class="btn default cancel" >取消</button></a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<script type="text/javascript">
		//新增管理员表单验证，提交
		$(document).ready( function () {
			$('#usereditform').validate({
				rules : {
					username : {
						pwsCheck:true,
						notnull : true,
						required : true,
						maxlength : 20
					},
					cname : {
						isRightfulString : true,
						notnull : true,
						required : true,
						pwdMaxlength : 20
					},					
					/* phoneno : {
						mobile : true,
						notnull : true,
						required : true,
						maxlength : 11
					}, */
					email : {
						email : true,
						notnull:true,
						maxlength : 40
					}
				}
			});
			
			$("#mflg").change(function(){
				var mflgval = $('#mflg').val();
				if('0'==mflgval) {
					 $('#orgid').rules("remove");
				}else if('1'==mflgval) {
					$('#orgid').rules("remove");
					$('#orgid').rules("add",{
						notnull:true,
						required:true
					})
				}
			});
			
		});
	</script>
</body>
</html>