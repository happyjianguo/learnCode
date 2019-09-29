<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>素材管理</title>
</head>
<body>
	<!-- sidebar-menu end-->
	<div class="sidebar-right">
		<div class="main-content">
			<div class="mainbox">
			<form:form id="eform1" class="form-horizontal">
				<div class="query">
					<!-- <div class="query-tit">查询条件</div> -->
					<div class="query-text">
						<span>素材名称</span><input type="text" class="form-control material-name" name="materialnameQ">
					</div>
					<div class="query-text">
						<span>素材类型</span> 
						<select class="form-control material-select" name="materialselectQ">
							<option value="all">请选择</option>
							<option value="news" >图文素材</option>
							<option value="media">媒体素材</option>
						</select>
					</div>
					<div class="query-btn">
	                    <button type="submit" class="btn btn-primary" id="queryBtn">查询</button>
	                    <button type="button" class="btn btn-primary reset" id="resetBtn">重置</button>
	                </div>
				</div>
			</form:form>
				<div class="tablebox">
					<div class="table-tit">
						<div class="tit-left">素材管理</div>
					</div>
					<div class="material">
						<div class="frm-title">
							<ul>
								<li class="cru"><a href="javascript:;"><i class="fa fa-credit-card"></i>图文消息</a></li>
								<li class=""><a href="javascript:;"><i class="fa fa-photo"></i>图片</a></li>
							</ul>
						</div>
						<div class="frm-tab newsMaterial">
							<div class="material-top">
								<div class="top-left messageNum">图文消息(共2个)</div>
								<div class="tit-btn">
									<a class="btn btn-primary" href="${ctxPath}/material/addMaterial?appid=${appid}"><i class="fa fa-plus"></i>新建图文消息</a>
								</div>							
								<form:form id='eformUpdate' action='${ctxPath}/material/updateMaterial' modelAttribute='materialModel' method='post'>
									<input type='hidden' id='materialId' name='materialId' value='${materialModel.materialId}'>
								</form:form>
							    <form:form id='eformDelte'  action='${ctxPath}/material/delMaterial' modelAttribute='materialModel' method='post'>
									<input type='hidden' id='materialId' name ='materialId' value='${materialModel.materialId}'>
								</form:form>
							</div>
							<div id="gallery-wrapper"></div>
						</div>
						<!-- frm-tab end-->
						<div class="frm-tab mediaMaterial">
							<div class="material-top">
								<div class="top-left mediaNum">图文消息(共2个)</div>
								<div class="tit-btn">
									<span><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
											<i class="fa fa-plus"></i>新增图片
										</button></span>
								</div>
							</div>
						</div>
						<!-- frm-tab end-->
					</div>
					<!--material  -->
				</div>
			</div>
			<!--mainbox  -->
		</div>
	</div>
	<!-- sidebar-right end-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
			<form:form id="eformMedia" class="form-horizontal" action="${ctxPath}/material/uploadImage?${_csrf.parameterName}=${_csrf.token}" modelAttribute="mmModel"  method="POST" enctype="multipart/form-data">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增</h4>
				</div>
				<div class="modal-body">
						<input type="hidden" name="appid" id="appidpictrue"
							value="${mmModel.appid}">
						<div class="form-group" style="display: none;">
							<label class="col-sm-4 control-label">图片名称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="materialname"
									name="materialName" value="${mmModel.materialName}"
									placeholder="请输入图片名称">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">图片标题</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="title" name="title"
									value="${mmModel.title}" placeholder="请输入图片标题">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">选择图片</label>
							<div class="col-sm-6">
								<span class="group-t">（大图片建议尺寸：900*500像素）</span> 
								<input id="exampleInputFile" name="myfiles" type="file" multiple data-max-file-count="1">
<%-- 								<input id="exampleInputFile" name="myfiles" type="file" value="${mmModel.fileName}" multiple data-max-file-count="1"  accept="image/*"> --%>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">图片描述</label>
							<div class="col-sm-6">
								<textarea class="form-control" id="introductionAdd" rows="3"
									name="introduction" value="${mmModel.introduction}"
									placeholder="请输入图片描述"></textarea>
							</div>
						</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" >确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>					
				</div>
				</form:form>
			</div>
		</div>
	</div>
<form:form id="eform" class="form-horizontal">
	<div class="modal fade" id="myModal-update" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">更新</h4>
				</div>
				<div class="modal-body">
						<div class="form-group" style="display: none;">
							<label class="col-sm-4 control-label">图片名称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="materialname-update" name="materialNameU" value="${mmModel.materialName}" placeholder="请输入图片名称">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">图片标题</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="title-update" name="titleU" value="${mmModel.title}" placeholder="请输入图片标题">
							</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-4 control-label">当前图片</label>
						    <div class="col-sm-6">
						      <img src="${mmModel.mediaUrl}" class="pre-img">
						    </div>
						  </div>
						<div class="form-group">
							<label class="col-sm-4 control-label">图片描述</label>
							<div class="col-sm-6">
								<textarea class="form-control" id="introduction-update" rows="3" name="introductionupdate" value="${mmModel.introduction}" placeholder="请输入图片描述"></textarea>
							</div>
						</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>					
				</div>
			</div>
		</div>
	</div>
</form:form>
	<!-- Modal end-->
	<!-- Modal-->
	<div class="modal fade" id="myModal-delete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">提示信息</h4>
				</div>
				<div class="modal-body">
					<div class="modal-prompt deleteAlert">							
						<i class="fa fa-exclamation"></i><span>确认删除<span id="delmsg"></span>吗？</span>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary deleteSure">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<script type="text/javascript" src="<c:url value='/resources/js/wx/material-list.js?v=${jsv}'/>"></script>
	<script type="text/javascript">
		//增加、更新or删除dialog 隐藏
		function modalHide() {
			$('.modal').modal('hide');
		}
		function materialSearchClick(){
			var name = $(".material-name").val();
			var type = $(".material-select option:selected").val();
			var appid = '${appid}';
				showFlag = true;
			var param = {
					"materialName" :$.trim(name),
					"appid" : appid
				};
			if(type == "news"){
				queryMaterialNewsList(param);
			}else if(type == "media"){
				queryMaterialResource(param);
			}else{
				showFlag = false;
				queryMaterialNewsList(param);
				queryMaterialResource(param);
			}
		}
		//更新媒体素材操作
		function updateMediaAction(){
			var name = $("#materialname-update").val();
			var title = $("#title-update").val();
			var introduce = $("#introduction-update").val();
			var params = {
				"materialId" : updateId,
				"materialName" : name,
				"title" : title,
				"introduction" : introduce
			};
			modifyMaterial(params);
		}
		function modifyMaterial(params) {
			$.ax({
				url : '${ctxPath}/material/updatePic',
				data : params,
				dataType : 'json',
				successfn : function(result) {
					if(result.result==1){
						modalHide();
						$("#"+result.obj.materialId).find("#itemTitle").text(result.obj.title);
						$("#"+result.obj.materialId).attr("title",result.obj.title);
						$("#"+result.obj.materialId).find("input").val(result.obj.introduction);
						$("#textmsg").empty();
						$("#textmsg").text("素材信息修改成功");
						$('#myModal-confirm').modal('show');
					}else{
						$("#errtextmsg").text(result.msg);
						$('#myModal-errmsg').modal('show');
					}
				},
				errorfn : function(result) {
						modalHide();
						$("#errtextmsg").text("网络异常！");
						$('#myModal-errmsg').modal('show');
				}
			});
		}
		function deleteMaterial(params) {
			$.ax({
						url : '${ctxPath}/material/delPic?${_csrf.parameterName}=${_csrf.token}',
						data : params,
						dataType : 'json',
						successfn : function(result) {
							modalHide();
							if (result.result == 1) {
								$("#textmsg").empty();
								$("#textmsg").text(result.msg);
								$('#myModal-confirm').modal('show');
								$("#" + params.materialId).remove();
							}else{
								$("#errtextmsg").text(result.msg);
								$('#myModal-errmsg').modal('show');
							}
						},
						errorfn : function(result) {
							modalHide();
							$("#errtextmsg").text("网络异常！");
							$('#myModal-errmsg').modal('show');
						}
					});
		}
		function deleteNewsMaterial(params) {
			$.ax({
						url : '${ctxPath}/material/delMaterial?${_csrf.parameterName}=${_csrf.token}',
						data : params,
						dataType : 'json',
						successfn : function(result) {
							modalHide();
							if (result.result == 1) {
								$("#textmsg").empty();
								$("#textmsg").text(result.msg);
								$('#myModal-confirm').modal('show');
								$("#" + params.materialId).remove();
							}else{
								$("#errtextmsg").text(result.msg);
								$('#myModal-errmsg').modal('show');
							}
						},
						errorfn : function(result) {
							modalHide();
							$("#errtextmsg").text("网络异常！");
							$('#myModal-errmsg').modal('show');
						}
					});
		}
		/* 初始化图片素材 */
		function queryMaterialResource(param) {
			$.ax({
						url : '${ctxPath}/material/selectMeterial',
						cache : false,
						data : param,
						dataType : 'json',
						successfn : function(result) {
							if (result.success) {
								if(showFlag){
									setSeletion(1);
								}
								$(".mediaMaterialItem").remove();
								$(".mediaNum").text("图片(共"+result.data.length+"个)");
								$(result.data)
										.each(
												function() {
													var url = $(this).attr("filePath");
													var name = $(this).attr("materialName");
													var id = $(this).attr("materialId");
													var title = $(this).attr("title");
													var introduce = $(this).attr("introduction");
													$(".mediaMaterial")
															.append(
																	"<div class='material-core col-sm-6 col-md-2 mediaMaterialItem' materialId=" 
																			+ id +" title="+title+" id="+id+">"
																			+ "<div class='brarybox brarybox-material brarybox-p'>"
																			+ "<div class='material-img material-img-p'>"
																			+ "<img src=${ctxPath}/material/show?url="+url+"> <span id='itemTitle'>"
																			+ title
																			+ "</span>"
																			+ "<input type='hidden' id='introduction' name='introduction' value="+introduce+">"
																			+ "</div>"
																			+ "<div class='mate-btm'>"
																			+ "<ul>"
																			+ "<li><span><i class='fa fa-pencil materialUpdate'></i></span></li>"
																			+ "<li><span><i class='fa fa-trash materialDelete'></i></span></li>"
																			+ "</ul>"
																			+ "</div>"
																			+ "</div>"
																			+ "</div>");
												});
							} else {
								$("#errtextmsg").text(result.msg);
								$('#myModal-errmsg').modal('show');
							}
						},
						errorfn : function(result) {
							modalHide();
							$("#errtextmsg").text("网络异常！");
							$('#myModal-errmsg').modal('show');
						}
					});
		}
		/* 初始化图文素材 */
		function queryMaterialNewsList(param) {
			$.ax({
						url : '${ctxPath}/material/selectMaterialNewsListByPlatformId',
						cache : false,
						data : param,
						dataType : 'json',
						successfn : function(result) {
							if (result.result == 1) {
								if(showFlag){
									setSeletion(0);
								}
								$(".newsMaterialItem").remove();
								$(".messageNum").text("图文消息(共"+result.obj.length+"个)");
								$(result.obj)
										.each(
												function(i) {
													var materialName = $(this).attr("materialName");
													var details = $(this).attr("detailModels");
													var time = $(this).attr("createTime");
													var title = $(this).attr("materialName");
													var materialId = $(this).attr("materialId");
													if (i == 0) {
														$(".newsMaterial").find("#gallery-wrapper")
																.append(
																		"<div class='material-core white-panel clear newsMaterialItem' id = "+materialId+">"
																				+ "<div class='brarybox brarybox-material'>"
																				+ "<dl>"
																				+ "<dd>"
																				+ title
																				+ "</dd>"
																				+ "</dl>"
																				+ "<div class='material-img'>"
																				+ "</div>"
																				+ "</div>"
																				+ "<div class='brary-template'>"
																				+ "<ul></ul>"
																				+ "</div>"
																				+ "<div class='mate-btm'>"
																				+ "<ul>"
																				+ "<li id='updateNewsMaterial'><span><i class='fa fa-pencil'></i></span></li>"
																				+ "<li id='deleteNewsMaterial'><span><i class='fa fa-trash'></i></span></li>"
																				+ "</ul>"
																				+ "</div>"
																				+ "</div>");
													} else {
														$(".newsMaterial").find("#gallery-wrapper")
																.append(
																		"<div class='material-core white-panel newsMaterialItem' id = "+materialId+">"
																				+ "<div class='brarybox brarybox-material'>"
																				+ "<dl>"
																				+ "<dd>"
																				+ title
																				+ "</dd>"
																				+ "</dl>"
																				+ "<div class='material-img'>"
																				+ "</div>"
																				+ "</div>"
																				+ "<div class='brary-template'>"
																				+ "<ul></ul>"
																				+ "</div>"
																				+ "<div class='mate-btm'>"
																				+ "<ul>"
																				+ "<li id='updateNewsMaterial'><span><i class='fa fa-pencil'></i></span></li>"
																				+ "<li id='deleteNewsMaterial'><span><i class='fa fa-trash'></i></span></li>"
																				+ "</ul>"
																				+ "</div>"			
																				+ "</div>");
													}
													$(details)
															.each(
																	function(i) {
																		var detailId = $(this).attr("detailId");
																		var materialId = $(this).attr("materialId");
																		var wxd_materialId = $(this).attr("wxd_materialId");
																		var materialTitle = $(this).attr("materialTitle");
																		var thumbMediaId = $(this).attr("thumbMediaId");
																		var thumbMediaUrl = $(this).attr("thumbMediaUrl");
																		var detailSort = $(this).attr("detailSort");
																		var author = $(this).attr("author");
																		var digest = $(this).attr("digest");
																		var showCoverPic = $(this).attr("showCoverPic");
																		var content = $(this).attr("content");
																		var contentSourceUrl = $(this).attr("contentSourceUrl");
																		var createTime = $(this).attr("createTime");
																		var updateTime = $(this).attr("updateTime");
																		if (i == 0) {
																			$("#"+ materialId).find(".material-img").append("<img src=${ctxPath}/material/show?url="+thumbMediaUrl+"> <span>"
																									+ materialTitle
																									+ "</span>");
																		} else
																			($("#"+ materialId).find(".brary-template").find("ul")
																					.append("<li class='brary-template-t'><span>"	
																							+ materialTitle	
																							+ "</span><i><img src=${ctxPath}/material/show?url="+thumbMediaUrl+"/></i></li>"))
																	});
												});
							} else {
								$("#errtextmsg").text(result.msg);
								$('#myModal-errmsg').modal('show');
							}
						},
						errorfn : function(result) {
							$("#errtextmsg").text("网络异常!");
							$('#myModal-errmsg').modal('show');
						}
					});
		}
		var updateId = "";
		var showFlag = false;
		$(function() {
			document.getElementById("appidpictrue").value="${appid}";
			var param = {
				"materialName" :"",
				"appid" : "${appid}"
			};
			queryMaterialResource(param)
			queryMaterialNewsList(param);
			var deleteId = "";
			var deleteFlag = 0;
			$('#eformMedia').validate({
				 rules : {
					title : {
						notnull :true,
						required : true,
						mlength : 100,
						isRightfulString:true
					},
					myfiles : {
							notnull :true,
							required : true,
							mlength : 60
					},
					introduction : {
						mlength : 1000
					}
				},
				submitHandler: function(form) {
					$(".loadingbg").show();//设置加载层开启
					form.submit();
	  			}
			});
			$('#eform').validate({
				 rules : {
					titleU : {
						notnull :true,
						required : true,
						mlength : 100,
						isRightfulString:true
					},
					introductionupdate : {
						mlength : 1000
					}
				},
	  			submitHandler: function(form) {
	  				updateMediaAction();
	  			}
			}); 
			$('#eform1').validate({
				 rules : {
				},
	  			submitHandler: function(form) {
	  				materialSearchClick();
	  			}
			});
			$("#exampleInputFile").on("change", function () {
				$("#exampleInputFile").blur();
	        });
			//设置新增dialog的隐藏监听
			$('#myModal').on('hidden.bs.modal', function (e) {
				$("#title").val("");
				$("#exampleInputFile").val("");
				$("#introductionAdd").val("");
				$("#eformMedia").validate().resetForm();
			});
			//设置更新dialog的隐藏监听
			$('#myModal-update').on('hidden.bs.modal', function (e) {
				$("#eform").validate().resetForm();
			});
			$(".mediaMaterial").on("click", ".materialUpdate", function() {
				var parent = $(this).parents(".mediaMaterialItem");
				var url = $(parent).find("img").attr("src");
				var name = $(parent).find("span").text();
				var id = $(parent).attr("materialId");
				var title = $(parent).attr("title");
				var introduce = $(parent).find("input").val();
				updateId = id;
				$("#materialname-update").val(name);
				$(".pre-img").attr("src",url);
				$("#title-update").val(title);
				
				$("#introduction-update").val(introduce);
				$('#myModal-update').modal('show');
			});
			$(".newsMaterial").on("click","#updateNewsMaterial",function() {
						var materialId = $(this).parents(".newsMaterialItem").attr("id");
						$("#eformUpdate").find("#materialId").val(materialId);
						$("#eformUpdate").submit();
					});
			$(".newsMaterial").on("click","#deleteNewsMaterial",function() {
						var materialId = $(this).parents(".newsMaterialItem").attr("id");
						deleteId = materialId;
						var name = $(this).parents(".newsMaterialItem").find("dd").text();
						deleteFlag = 1;
						$(".deleteAlert span").text("确认删除图文消息 " + name + " 吗？");		
						$("#myModal-delete").modal('show');
					});
			$(".mediaMaterial").on("click", ".materialDelete", function() {
				var parent = $(this).parents(".mediaMaterialItem");
				var name = $(parent).find("span").text();
				var id = $(parent).attr("materialId");
				deleteId = id;
				deleteFlag = 0;
				$(".deleteAlert span").text("确认删除图片 " + name + " 吗？");				
				$("#myModal-delete").modal('show');
			});
			$(".deleteSure").click(function() {
				var params = {
					"materialId" : deleteId
				};
				if (deleteFlag == 0) {
					deleteMaterial(params);
				} else if (deleteFlag == 1) {
					deleteNewsMaterial(params)
				}
			});
		});
		//查询重置按钮
		$(".query-btn").on("click","#resetBtn",function() {
			$(".material-name").val("");
			$(".material-select option").removeAttr("selected");
		});
	</script>
	<script type="text/javascript">
		/* $("#exampleInputFile").fileinput({
	        language: 'zh',
	        uploadUrl: '#',
	        allowedFileExtensions : ['jpg', 'png','gif'],
	        autoReplace:'true'
	    }); */
		var $input = $('#exampleInputFile');
        if ($input.length) {
        	$('#exampleInputFile').fileinput({
        		showUpload : false,
                showRemove : true,
                language : 'zh',
                allowedPreviewTypes: ['image'],
                allowedFileTypes: ['image'],
                allowedFileExtensions:  ['jpg', 'png', 'jpeg'],
                maxFileSize : 2000,		
        	});
        }
		//tab切换
		$(function() {
			tabs();
		});
		function setSeletion(index){
			$(".frm-title ul li").each(function(i){
				if(i==index){
					$(this).addClass('cru');
				}else{
					$(this).removeClass('cru');
				}
			});
			$(".frm-tab").hide();
			$(".frm-tab:eq(" + index + ")").slideDown(0);
		}
		function tabs() {
			$(".frm-title ul li").click(
					function() {
						var thisIndex = $(this).parent("ul").children("li")
								.index(this);
						$(this).addClass('cru').siblings().removeClass('cru');
						$(".frm-tab").hide();
						$(".frm-tab:eq(" + thisIndex + ")").slideDown(0);
					});
			$('.frm-title ul li:eq(0)').trigger("click");
		}
	</script>	
	<script type="text/javascript">
		$(function(){
			$("#gallery-wrapper").pinterest_grid({
				no_columns: 6,
	            padding_x: 20,
	            padding_y: 80,
	            margin_bottom: 50,
	            single_column_breakpoint: 700
			});
			if("${mediaMaterial.resflag}"=="1"){
				$("#textmsg").text("图片新增成功");
				$('#myModal-confirm').modal('show');
			}else if("${mediaMaterial.resflag}"=="2"){
				$("#errtextmsg").text("${mediaMaterial.resMsg}");
				$('#myModal-errmsg').modal('show');
			}
		});
	</script>
</body>
</html>