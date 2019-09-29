<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>系统管理-模板管理</title>
<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="main-content">
		<div class="list-nav">
			<ol class="breadcrumb">
				<i class="icon-cog"></i>
				<li>活动管理</li>
				<li class="active">活动模板管理</li>
			</ol>
		</div>
		<div class="mainbox">
			<div class="tablebox">
				<div class="table-tit">
					<div class="tit-left">活动管理</div>
					<div class="tit-btn">
						<span><a href="#" class="btn btn-primary" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i>新增模板</a></span>
					</div>
				</div>
				<input type="hidden" value="${cloudGameInitModel.gameid}" id="fristgameid">
<%-- 				<input type="hidden" value="${cloudGameInitModel.gameid}" id="fristgameid"> --%>
				<table id="table_id" class="tablemain">
					<thead>
						<tr class="tit-top">
							<th scope="col">模板编号</th>
							<th scope="col">文件名称</th>
							<th scope="col">模板名称</th>
							<th scope="col">模板分类id</th>
							<th scope="col">模板分类名称</th>
							<th scope="col">图片路径</th>
							<th scope="col">使用次数</th>
							<th scope="col">游戏编号</th>
							<th scope="col">创建时间</th>
							<th scope="col">更新时间</th>
							<th scope="col">状态</th>
							<th scope="col">操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
					</tbody>
				</table>
			</div>
		</div>
		<!-- Modal -->
		 <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">上传模板</h4>
					</div>
					<div class="modal-body">
				          <div class="form-group">
					        <div>
								<label class="for="recipient-name" control-label">模版名称</label>
								<div class="for="recipient-name"">
									<input type="text" id="templatename" name="templatename" class="form-control">
								</div>
							</div>
				            <div>
								<label class="for="recipient-name" control-label">模版分类:</label>
								<div class="for="recipient-name" select-list">
									<select id="tmptypeid" name="tmptypeid" class="form-control select tmptypeid" >
										<option value="">请选择</option>
										<c:forEach items="${cloudGameInitModel.gameTemplateTypelist }" var="gametypelist">
											<option value="${gametypelist.tmptypeid }">${gametypelist.tmptypename}</option>
										</c:forEach>
									</select>
								</div>
							</div>	
				            <label for="recipient-name" class="control-label">选择模板:</label>
				            <input id="tempfile" name="tempfile" type="file" class="file" multiple="multiple" data-show-preview="false">
				            <div id="kv-error-2" style="margin-top:10px;display:none"></div>
				            <div id="kv-success-2" class="alert alert-success fade in" style="margin-top:10px;display:none"><ul></ul></div>
				          </div>
					</div>
					<div class="modal-footer">
						<button type="button" id="closetemplatebutton" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel3">预览图片</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-4 control-label">预览</label>
							<div class="col-sm-6">
								<img id="eform3msgsrc">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
				
		<form:form id="eform3" method="post" action="">
		<div class="modal fade" id="myModal-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<input type="hidden" id="delmsgid" name="delmsgid">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">提示信息</h4>
					</div>
					<div class="modal-body">
						<div class="modal-prompt deleteAlert">
							<i class="fa fa-exclamation"></i><span>确认删除游戏模版<span id="delmsg"></span>吗？ 请谨慎操作！</span>								
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary deleteConfirm delMsgid">确定</button>
						<button type="button" class="btn btn-default deleteCancle" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>
		
	<form:form id="eform2"  methodParam="post" enctype="multipart/form-data" cssClass="form-horizontal" >
		<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel2">上传图片</h4>
					</div>
					<div class="modal-body">
					<input type="hidden" id="eform2msgtempid" name="tempid" class="form-control">
					<input type="hidden" id="eform2msgtempname" name="tempname" class="form-control">
						<div class="form-group">
							<label class="col-sm-4 control-label">图片</label>
							<div class="col-sm-6">
								<input id="exampleInputFile" name="exampleInputFile" class="file" type="file" multiple="multiple" multiple data-max-file-count="1">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<!-- <button type="submit" id="configuploads" class="btn btn-primary">上传</button> -->
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
<!-- 						<button type="button" class="btn btn-primary">保存</button> -->
					</div>
				</div>
			</div>
		</div>
		</form:form>
	</div>
	<!--edit textarea-->
	<form:form id="eform1" modelAttribute="cloudGameTemplateModel">
		<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="eform1myModalLabel">修改活动模版</h4>
					</div>
					<div class="modal-body">
						<!-- 模版id -->
						<input type="hidden" id="eform1msgtempid" name="tempid" class="form-control">
						<div class="form-group">
							<label class="col-sm-2 control-label">文件名称</label>
							<div class="col-sm-8">
								<input type="text" id="eform1msgtempname" name="tempname" readonly="readonly" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">模版名称</label>
							<div class="col-sm-8">
								<input type="text" id="eform1msgtemplatename" name="templatename" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">模版分类:</label>
							<div class="col-sm-8 select-list">
								<select id="eform1msgtmptypename" name="tmptypeid" class="form-control select tmptypeid" >
									<option value="">请选择</option>
									<c:forEach items="${cloudGameInitModel.gameTemplateTypelist }" var="gametypelist">
										<option value="${gametypelist.tmptypeid }">${gametypelist.tmptypename}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">使用次数</label>
							<div class="col-sm-8">
								<input type="text" id="eform1msgusetimes" name="usetimes" readonly="readonly" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">游戏编号</label>
							<div class="col-sm-8">
								<input type="text" id="eform1msggameid" name="gameid" readonly="readonly" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">状态</label>
							<div class="col-sm-8 select-list">
								<select id="eform1tempstatus" name="tempstatus" class="form-control select">
									<option value="0">未生效</option>								
									<option value="1">生效</option>								
								</select>
							</div>
						</div>
						<!-- </form> -->
					</div>
					<div class="modal-footer">
						<button type="submit" id="confirmBtn" class="btn btn-primary">确定</button>
						<button type="button" id="closeBtn" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
		<script type="text/javascript">
			var table;
			var idd="";
			$(document).ready(function() {
				table = $('#table_id').DataTable({
					"ajax": function (data, callback, settings) {
	                	$.ax({
	                		"url":"${ctxPath}/gametemplate/gametemplates",
	                		"data" : function() {
	                			var reqdata = {};
	                			reqdata.gameid=$('#fristgameid').val();
	                			/* alert($('#fristgameid').val()); */
	                			return reqdata;
					        },
					        successfn : function(result) {
					        	callback(result);
					        }
	                	});
	                  },
					"columns" : [
							{'data' : 'tempid','visible' : false},
							{'data' : 'tempname'},
							{'data' : 'templatename'},
							{'data' : 'tmptypeid','visible' : false},
							{'data' : 'tmptypename'},
							{'data' : 'pictrueurl'},
							{'data' : 'usetimes'},
							{'data' : 'gameid'},
							{'data' : 'createtime'},
							{'data' : 'updatetime'},
							{
								'data' : function(obj) {
									var sReturn = obj.tempstatus;
									if (sReturn == "0") {
										sReturn = "未生效";
									} else if (sReturn == "1") {
										sReturn = "生效";
									}
									return sReturn;
								}
							
							},
							{
								'data' : function(obj) { 
									sReturn = 
										"<td>"
										+ "<span><button type='button' class='btn btn-primary editMsg' data-toggle='modal' data-target='#myModal1'><i class='fa fa-edit'></i>编辑</button></span>"
										+ "<span><button type='button' class='btn btn-primary uploadMsg' data-toggle='modal' data-target='#myModal2'><i class='fa fa-plus'></i>上传封面图片</button></span>"
										+ "<span><button type='button' class='btn btn-primary previewMsg' data-toggle='modal' data-target='#myModal3'><i class='fa fa-circle-o'></i>预览图片</button></span>"
										+ "<span><button type='button' class='btn btn-primary delGameTmpMsg' data-toggle='modal' data-target='#myModal-delete'><i class='fa fa-trash'></i>删除</button></span>"
										+ "</td>";
									return sReturn;
								}
							}
						]
				});
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
			});
			//删除
			$("#table_id").on("click", ".delGameTmpMsg", function() {
				var data = table.row($(this).parents('tr')).data();
				$("#delmsgid").val(data.tempid);
				$("#delmsg").html(data.templatename);
			});
			$("#myModal-delete").on("click", ".delMsgid", function() {
				var msgId = $("#delmsgid").val();
				var params = {
					"delmsgid" : msgId
				};
				$("#eform3")[0].reset();
				deletMsg(params);
			});
			function deletMsg(params) {
				$.ax({
					url : '${ctxPath}/gametemplate/ajax/delete',
					data : params,
					successfn : function(result) {
						//1 关闭窗口
						$("#textmsg").empty();
						$('.modal').modal('hide');
						if(result.success){
							table.ajax.reload();
							$("#textmsg").text(result.msg);
							$('#myModal-confirm').modal('show');
						}else{
							$("#errtextmsg").text(result.msg);
							$('#myModal-errmsg').modal('show');
						}
					},
					errorfn : function(result) {
						$('.modal').modal('hide');
						$("#errtextmsg").text("网络异常!");
						$('#myModal-errmsg').modal('show');
					}
				});
			};
			$("#myModal1").on('hidden.bs.modal', function() {
				$("#eform1").validate().resetForm();
				$("#eform1")[0].reset();
			});
			 //修改
		    $(".tablemain").on("click",".editMsg",function(){
		    	var data = table.row($(this).parents('tr')).data();
				$('#eform1msgtempid').val(data.tempid);
				$('#eform1msgtempname').val(data.tempname);
				$('#eform1msgtemplatename').val(data.templatename);
				$('#eform1msgusetimes').val(data.usetimes);
				$('#eform1msggameid').val(data.gameid);
				$('#eform1tempstatus').val(data.tempstatus);
				$('#eform1msgtmptypename').val(data.tmptypeid);
				url='${ctxPath}/gametemplate/ajax/editdata';
		    	//alert(JSON.stringify(data));
		    });
		    $('#eform1').validate({
				//debug: true,
				rules : {
					tmptypeid : {
						notnull : true,
						required : true
					},
		    		templatename : {
						notnull : true,
						required : true,
						qlength:50
					}
				},
				submitHandler : function(form) {
					var params = serializeForm($('#eform1'));
					editTmp(params,url);
				}
			});
		  //预览图片
		    $(".tablemain").on("click",".previewMsg",function(){
		    	var data = table.row($(this).parents('tr')).data();
				picurl = "${ctxPath}/gametemplate/show?url="+data.pictrueurl;
				$("#eform3msgsrc").attr("src",picurl);
		    });
		  //上传图片
		    $(".tablemain").on("click",".uploadMsg",function(){
		    	var data = table.row($(this).parents('tr')).data();
		    	$('#eform2msgtempid').val('');
				$('#eform2msgtempid').val(data.tempid);
		    	$('#eform2msgtempname').val('');
				$('#eform2msgtempname').val(data.tempname);
		    });
		  //上传图片
			$("#exampleInputFile").fileinput({
			    uploadUrl: "${ctxPath}/gametemplate/uploadpic?${_csrf.parameterName}=${_csrf.token}", // server upload action
			    uploadExtraData: function() {
		            var extra1Value = $('#eform2msgtempid').val();
		            var extra2Value = $('#eform2msgtempname').val();
		            return {"tempid": extra1Value,"tempname":extra2Value};
		        }
			}).on('fileuploaded', function(event, data) {
			  	var reuslt=data.response;
				//1 关闭窗口
				$("#textmsg").empty();
				$('.modal').modal('hide');
				if(reuslt.success){
					table.ajax.reload();
					$("#textmsg").text(reuslt.msg);
					$('#myModal-confirm').modal('show');
				}else{
					$("#errtextmsg").text(reuslt.msg);
					$('#myModal-errmsg').modal('show');
				}
			});
		    function editTmp(params,url) {
				$.ax({
					url : url,
					data : params,
					successfn : function(result) {
						//1 关闭窗口
						$("#textmsg").empty();
						$('.modal').modal('hide');
						if(result.success){
							table.ajax.reload();
							$("#textmsg").text(result.msg);
							$('#myModal-confirm').modal('show');
						}else{
							$("#errtextmsg").text(result.msg);
							$('#myModal-errmsg').modal('show');
						}
						
					},
					errorfn : function(result) {
						$('.modal').modal('hide');
						$("#errtextmsg").text("网络异常!");
						$('#myModal-errmsg').modal('show');
					}
				});
			}
			//上传模板
 			$("#tempfile").fileinput({
			    uploadUrl: "${ctxPath}/gametemplate/uploadtemp?${_csrf.parameterName}=${_csrf.token}&gameid=${cloudGameInitModel.gameid}", // server upload action,
			    language: "zh",
			    uploadAsync: true,
			    allowedFileExtensions: ['zip'],
			    maxFileSize:600000,
			    elErrorContainer: '#kv-error-2',
			    uploadExtraData: function() {//添加额外参数
		            var tmptypeid = $('#tmptypeid').val();
		            var templatename = $('#templatename').val();
		            return {"tmptypeid": tmptypeid,"templatename":templatename};
		        }
			}).on('fileuploaded', function(event, data) {
			    var out = '';
			    var frresult = data.response.result;
		        if(frresult!="success"){
			    	out = out + '<li> 上传失败,' +  frresult + '</li>';
			    	$('#kv-error-2').empty();
				    $('#kv-error-2').append(out);
				    $('#kv-error-2').fadeIn('slow');
			    }else{
			    	$.each(data.files, function(key, file) {
				        var fname = file.name;
				        out = out + '<li>' +  fname + ' 上传成功' + '</li>';
				    });
			    	$('#kv-success-2 ul').empty();
				    $('#kv-success-2 ul').append(out);
				    $('#kv-success-2').fadeIn('slow');
				    table.ajax.reload();
			    }
			});
 			$("#tmptypeid").change(function(){
 				var tmptypeid =$("#tmptypeid").val();
 				if(tmptypeid == "" || tmptypeid == undefined || tmptypeid == null){
 				 	
 				}else{
 					$('#kv-error-2').empty();
 					$('#kv-error-2').hide();
 				}
 				var templatename =$("#templatename").val();
 				if(templatename == "" || templatename == undefined || templatename == null){
 					$('#kv-error-2').empty();
 					$('#kv-error-2').hide();
 				}
 			});
 			/*清空上传模版输入框*/
 			$("#closetemplatebutton").click(function(){
 				$("#templatename").val("");
 				$("#tmptypeid").val("");
 				$('#kv-success-2 ul').empty();
 				$('#kv-success-2').hide();
 			});
		    $("#tempfile").click(function(){
 				var tmptypeid =$("#tmptypeid").val();
 				var templatename =$("#templatename").val();
 				if(templatename == "" || templatename == undefined || templatename == null){
 					var out = '';
	 			    out = out + '<li> 模版名称不能为空</li>';
	 			   	$('#kv-error-2').empty();
				    $('#kv-error-2').append(out);
				    $('#kv-error-2').fadeIn('slow');
	 			    return false;
 				}else{
 					if(templatename.length >= 50){
 						var out = '';
 		 			    out = out + '<li> 模版名称不能大于50位</li>';
 		 			   	$('#kv-error-2').empty();
 					    $('#kv-error-2').append(out);
 					    $('#kv-error-2').fadeIn('slow');
 		 			    return false;
 					}else{
 						$('#kv-error-2').empty();
 	 					$('#kv-error-2').hide();
 					}
 				}
 				if(tmptypeid == "" || tmptypeid == undefined || tmptypeid == null){
 				 	var out = '';
	 			    out = out + '<li> 模版分类不能为空</li>';
	 			   	$('#kv-error-2').empty();
				    $('#kv-error-2').append(out);
				    $('#kv-error-2').fadeIn('slow');
	 			    return false;
 				}
 			});
		</script>
</body>
</html>
