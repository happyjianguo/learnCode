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
<!-- <link href="http://plugins.krajee.com/assets/prod/all-krajee.css"> -->
<title>模板参数修改</title>
</head>
<body>
	<div>
		<div class="main-content">
			<div class="mainbox">
				<form:form id="tempParaform"  action="${ctxPath}/mcmanage/gameinit?${_csrf.parameterName}=${_csrf.token}"  
					class="form-horizontal" methodParam="post" enctype="multipart/form-data" >
					
					<input type="hidden" name="gameid" id="gameid" value="${gameInfoModel.gameid}">
					<input type="hidden" name="tempid" id="tempid" value="${gameInfoModel.tempid}">
					<input type="hidden" name="appid" id="appid" value="${gameInfoModel.appid}">
					<input type="hidden" name="gamename" id="gamename" value="${gameInfoModel.gamename}">
					<input type="hidden" name="playname" id="playname" value="${gameInfoModel.playname}">
					
					<div class="form-group">
						<label class="col-sm-4 control-label">模板图片</label>
						<div class="col-sm-3">
							<input type="file" id="myfile" name="myfile"  multiple >
						</div>
					</div>
					
					<div class="modal-footer">
						<input type="button" name="tempBtnfront" value="上一步" /> 
						<input type="button" name="tempbtnNext" value="下一步" />
					</div>
				</form:form>
			</div>
		</div>
		<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
			<script type="text/javascript">
				var $input = $('#myfile');
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
				$(document)
						.ready(
								function() {
									//上一步
									$("input[name='tempBtnfront']")
											.on(
													"click",
													function() {
														/* $('#input-image-1').trigger('filebrowse'); */
														$(
																'form[id=tempParaform]')
																.attr('action',
																		'${ctxPath}/mcmanage/tempinit?${_csrf.parameterName}=${_csrf.token}');
														$("#tempParaform")
																.submit();
													});
									//下一步
									$("input[name='tempbtnNext']").on("click",
											function() {
												$("#tempParaform").submit();
											});
									/*  $('#input-image-1').on('filebrowse', function(event) {
										alert(11111);
									});  */
								});
			</script>
</body>
</html>