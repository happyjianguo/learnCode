<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>系统管理-智能回复</title>
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<form:form id="eform">
		<div class="sidebar-right">
			<div class="main-content">
				<div class="mainbox">
					<div class="query">
						<!-- <div class="query-tit">查询条件</div> -->
						<div class="query-text">
							<span>智能回复名称</span>
							<input type="text" id="resMsgname" class="form-control">
						</div>
						<div class="query-text">
							<span>消息类型</span> 
							<select id="msgType" class="form-control" style="width: 93px;">
								<option value="" selected="selected">全部</option>
								<option value="text">文本</option>
								<option value="image">图文</option>
							</select>
						</div>

						<div class="query-btn">
						<button type="button" class="btn btn-primary" id="queryBtn">查询</button>
						<button type="button" class="btn btn-primary reset" id="resetBtn">重置</button>
					</div>
					</div>
					<div class="tablebox">
						<div class="table-tit">
							<div class="tit-left">自定义智能回复</div>
							<div class="tit-btn">
								<span>
									<button id="addTextMsgBtn" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
										<i class="fa fa-plus"></i>新增智能回复
									</button>
								</span> 
							</div>
						</div>
						<table id="table_id" width="100%" border="0" align="center" valign="middle" class="tablemain">
							<thead>
								<tr class="tit-top">
									<th scope="col"><input type="checkbox" value=""  id="checkAll"></th>
									<th scope="col">智能回复id</th>	
									<th scope="col">智能回复名称</th>
									<th scope="col">关键词</th>
									<th scope="col">消息类型</th>
									<th scope="col">MSGID</th>
									<th scope="col">消息名称</th>
									<th scope="col">更新时间</th>
									<th scope="col">创建时间</th>
									<th scope="col">appid</th>
									<th scope="col">resType</th>
									<th scope="col">操作</th>	
								</tr>
							</thead>
							<tbody id="tbody"></tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form:form>
	<!-- sidebar-right end-->
	
	<!--add textarea-->
	<form:form id="eform1" modelAttribute="resMsg" >
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="eform1myModalLabel">新增智能回复</h4>
					</div>
					<div class="modal-body">
							<input type="hidden" id="eform1resmsgId" name ="resmsgId" class="form-control" >
							<input type="hidden" id="eform1platformid" name =appid class="form-control" >
							<div class="form-group">
								<label class="col-sm-3 control-label">智能回复名称</label>
								<div class="col-sm-8">
									<input type="text" id="eform1resMsgname" name ="resMsgname" class="form-control" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">回复类型</label>
								<div class="col-sm-8">
									<select id="returnType" class="form-control" style="width: 123px;">
										<option value="2">智能回复</option>
										<option value="1">关注回复</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">关键词</label>
								<div class="col-sm-8">
									<input id="eform1resMsgKey" name ="resMsgKey" class="form-control" rows="6">
								</div>
							</div>
							
							<input type="hidden" id="eform1msgType" name ="msgType" class="form-control">
							<input type="hidden" id="eform1msgId" name ="msgId" class="form-control" value="">
							 
						  	<div class="form-group">
						  		<div class="frm-centent">
									<div class="frm-title">
										<ul>
											<li class="cru"><a href="javascript:;"><i class="fa fa-credit-card"></i>图文消息</a></li>
											<li class=""><a href="javascript:;"><i class="fa fa-pencil"></i>文本消息</a></li>
										</ul>
									</div>
									<div class="frm-box frm-box-sc">
										<div class="frm-tab source-pick" style="display: block;">
						                    <div class="media-cover selectMedia" style="display: block;">
						                        <a href="" id="meterialid" data-toggle="modal" data-target="#mediaModal">
						                            <i class="fa fa-plus"></i>
						                            <em>从消息管理中选择</em>
						                        </a>
						                    </div>
						                </div>
										<div class="frm-tab" style="display: none;">
											<select id="textmsgId" class="form-control">
											</select> 
										</div>
									</div>
								</div>
						  	</div>						  	
						<!-- </form> -->
					</div>
					<div class="modal-footer">
						<button type="submit" id="confirmBtn" class="btn btn-primary">确定</button>
						<button type="button" id="closeBtn" class="btn btn-default"
							data-dismiss="modal">取消</button>						
					</div>
				</div>
			</div>
		</div>
	</form:form>
	<!-- Modal-->
	<form:form id="eform3"  modelAttribute="wxdmessagemodel3" method="post" action="">
		<div class="modal fade" id="myModal-delete" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<input type="hidden" id="delresmsgid" name="delresmsgid">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">提示信息</h4>
					</div>
					<div class="modal-body">
						<div class="modal-prompt deleteAlert">							
							<i class="fa fa-exclamation"></i><span>确认删除智能回复<span id="delmsg"></span>吗？</span>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary deleteConfirm delresMsgid">确定</button>
						<button type="button" class="btn btn-default deleteCancle"
							data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>
<!-- Modal-->
	<div class="modal fade" id="mediaModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document" style="width: 750px;">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close2 close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel">选择图文消息</h4>
	            </div>
	            <div class="modal-body">
	                <div class="library">                   
	                    <div class="brary-main clear newsData">
	                    </div>
	                </div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary brarybtn">确定</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	            </div>
	        </div>      
	    </div>
	</div>
<!-- Modal end-->
	<!-- Modal end-->
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<script type="text/javascript">
		var imageMsg='';
		var textMsg='';
		var msgFlag = false;
		$(document).ready( function () {
			var table;
			table=$('#table_id').DataTable({
	                "aaSorting": [[ 0, "esc" ]],//设置第1个元素为默认排
	                "aoColumnDefs": [
						{ 
						  "bSearchable": false, //bSearchable：是否可搜索；
						  "bVisible": true,     //bVisible：是否可见；
						  "aTargets": [ 3 ]   //aTargets：哪一列;
						},
			            {
		                  "targets": [0,1,5,7,8,9,10],//隐藏列
		                  "visible": false
		                }    
					],
	                "ajax": {
	                	"error":function (XMLHttpRequest, textStatus, errorThrown) {
	                		if(XMLHttpRequest.readyState != 0){
								$("#errtextmsg").text("网络异常!");
								$('#myModal-errmsg').modal('show');
							}
	                	},
	                	"url":"${ctxPath}/resMessage/getData", 
	                	"type": "Post",
	                	"beforeSend":function (XMLHttpRequest) {
							XMLHttpRequest.setRequestHeader("${csrfheader}", "${csrftoken}");
						},
	    	            "data": function ( d ) {
	    	            	d.appid = "${appid}";
	    	            	var resMsgname = $('#resMsgname').val();
	    	            	var msgType = $('#msgType').val();
	    	            	if(resMsgname!=""){
	    	            		d.resMsgname = $.trim(resMsgname);  //添加额外的参数传给服务器
	    	            	}
	    	            	if(msgType!=""){
	    	            		d.msgType = msgType;  //添加额外的参数传给服务器
	    	            	}
	    	            } 
	                },
	                "columns": 
		                [  
						   { "defaultContent": "<input type='checkbox' name='subBox' onClick='checkOne()' class='roleCheck'>"},
						   { 'data': 'resmsgId' },
						   { 'data': 'resMsgname' },
						   { 'data': 'resMsgKey' },
						   { 'data': function(obj) {
				                           var sReturn = obj.msgType;
				                           if ( sReturn == "text" ) {
				                               sReturn = "文本";
				                           }else if(sReturn == "image"){
				                        	   sReturn = "图文";
				                           }
				                           return sReturn;
								   	   }
						   },
						   { 'data': 'msgId' },
						   { 'data': 'msgName' },
						   { 'data': 'updateTime' },
						   { 'data': 'createTime' },
						   { 'data': 'appid' }, 
						   { 'data': function(obj){
										   var sReturn = obj.resType;
				                           if ( sReturn == "1" ) {
				                        	   msgFlag = true;
				                           }
				                           return sReturn;
						  			 } 
						   },
		                   { 'data': function(obj) {
		                              sReturn = "<td>"+
				                               		"<span><button type='button' class='btn btn-primary editResMsg' data-toggle='modal' data-target='#myModal'><i class='fa fa-edit'></i>编辑</button></span>"+
										    		"<span><button type='button' class='btn btn-primary delResMsg' data-toggle='modal' data-target='#myModal-delete'><i class='fa fa-trash-o'></i>删除</button></span>"+
										    	 "</td>" ;
			                          return sReturn;
						   	   }
						   }
		                ]  
			    });
				/*获取消息列表*/
				$.ax({
					url: "${ctxPath}/resMessage/getmessages",	
					"data":"appid=${appid}",
					successfn: function(data){
						//var json = eval(data); //数组  
						var json = data.obj 
						textMsg = "<option value=''>请选择文本消息</option>";
						$.each(json, function (index, item) {  
				            //循环获取数据    
				            var msgId = json[index].msgId;  
				            var msgName = json[index].msgName;  
				            if(json[index].msgType=="0"){
				            	textMsg=textMsg+"<option value=" + msgId + ">" + msgName + "</option>";
				            }else{
				            	imageMsg=imageMsg+"<option value=" + msgId + ">" + msgName + "</option>";
				            }
				        });
						$("#textmsgId").append(textMsg);
						$('.selectpicker').selectpicker('refresh');
					},
			        errorfn : function(){
			        	$("#errtextmsg").text("网络异常!");
			        	$('#myModal-errmsg').modal('show');
			        }					
				}); 
				/*条件查询*/
				$(".query-btn").on("click","#queryBtn",function(){
					msgFlag = false;
					table.ajax.reload();
				}); 
				$(".query-btn").on("click", "#resetBtn", function() {
					$("#resMsgname").val("");
					$("#msgType").val("");
				});
				/*点击添加文本按钮时，清空输入框数据（因与文本消息修改使用同一model），修改action为添加文本消息请求路径*/
				$(".tablebox").on("click","#addTextMsgBtn",function(){
					$('#eform1myModalLabel').text('新增智能回复');
					$('#eform1platformid').val('${appid}');
					$('#eform1resmsgId').val('');
					$('#eform1msgType').val('image');
					$('#eform1msgId').val('');
					$('#eform1resMsgname').val('');
					/* 新增时放开智能回复名称 */
					$("#eform1resMsgname").attr("disabled",false);
					$('#eform1resMsgKey').val('');
					$("#box-del").prev(".brarybox").remove();
					$("#box-del").remove();
					$(".media-cover").show();
					$("#textmsgId").val('');
					$('.frm-title ul li:eq(0)').trigger("click");
					if(msgFlag){
						$("#returnType option[value=1]").remove();
					}else{
						if(!$("#returnType option[value=1]")){
							$("#returnType").append("<option value='1'>关注回复</option>");
						}
					}
					$('#eform1').attr("action","${ctxPath}/resMessage/save?${_csrf.parameterName}=${_csrf.token}");
				});
				$("#returnType").change(function(){
					var type = $(this).children("option:selected").val();
					var msgName = $("#eform1resMsgname").val();
					if(type == 1){
						$("#eform1resMsgKey").val("");
						$("#eform1resMsgKey").prop("disabled",true);
					}else{
						$("#eform1resMsgKey").prop("disabled",false);
					}
				});
				$('#eform1').validate({
					rules : {
						resMsgname : {
							isRightfulString :true,
							notnull :true,
							required : true,
							mlength : 20
						},
						resMsgKey : {
							isRightfulString :true,
							notnull :true,
							required : true,
							mlength : 20
						}
					},
					submitHandler : function(form) {
						var type = $("#eform1msgType").val();
						var msgId = $("#eform1msgId").val();
						if(''==msgId){
							$("#errtextmsg").text("请选择回复消息！");
							$('#myModal-errmsg').modal('show');
							return ;
						}
						var params = serializeForm($('#eform1'));
						$.ax({
							url: $('#eform1').attr("action"),
							data: params,
							successfn: function(result){
								$('.modal').modal('hide');
								if (result.success) {
									$("#textmsg").text(result.msg);
									table.ajax.reload();
									$('#myModal-confirm').modal('show');
								} else {
									$("#errtextmsg").text(result.msg);
									$('#myModal-errmsg').modal('show');
								}
							},
					        errorfn : function(){
					        	$("#errtextmsg").text("网络异常!");
					        	$('#myModal-errmsg').modal('show');
					        }			
						});
						return false;//阻止表单提交
					}
				});  
				/*单个删除*/
			    $("#table_id").on("click",".delResMsg",function(){
					var data = table.row( $(this).parents('tr') ).data();
					$("#delresmsgid").val(data.resmsgId);
					$("#delmsg").html(data.resMsgname);
				});
			    $("#myModal-delete").on("click",".delresMsgid",function(){
			    	$('#eform3').attr("action","${ctxPath}/resMessage/delete?${_csrf.parameterName}=${_csrf.token}");
			    	$('#eform3').submit();
			    });
				/*单个修改,初始化数据，修改action为修改路径*/
				$(".tablemain").on("click",".editResMsg",function(){
					var data = table.row( $(this).parents('tr') ).data();
 					if(data.msgType == 'text'){  
 						$('.frm-title ul li:eq(1)').trigger("click");
 						$("#textmsgId").val(data.msgId);
					}else if(data.msgType == 'image'){  
						$('.frm-title ul li:eq(0)').trigger("click");
						$.ax({
							url:  "${ctxPath}/menu/selectMaterialByMsgid",	
							data:{"appid":'${appid}',"msgid":data.msgId},
							successfn: function(data){
								if (data.result == 1) {
									materialData = eval(data.obj);
									$.each(materialData, function (index, item) {
										var htmlData = "";
										//获取图文明细
										var matDetail = materialData[index].detailModels;
										htmlData = htmlData + '<div class="brarybox" onmouseover="mouseover(this);" onmouseout="mouseout(this);">';
										htmlData = htmlData + '<input type="hidden" id="imagemsgId" value="' + materialData[index].msgId + '" >';
										htmlData = htmlData + '<dl><dt>' + materialData[index].materialName + '</dt>';
										htmlData = htmlData + '<dd>' + materialData[index].updateTime + '</dd></dl>';
										$.each(matDetail, function (index, item) {
											if (index == 0) {
												//第一个素材明细
												htmlData = htmlData + '<div class="material-img"><img src=${ctxPath}/material/show?url=' + matDetail[index].thumbMediaUrl + '>';
												htmlData = htmlData + '<span>' + matDetail[index].materialTitle + '</span></div>';
											} else {
												// 除第一个以外的素材明细
												htmlData = htmlData + '<div class="brary-template"><ul>';
												htmlData = htmlData + '<li class="brary-template-t">';
												htmlData = htmlData + '<span>' + matDetail[index].materialTitle + '</span>';
												htmlData = htmlData + '<i><img src=${ctxPath}/material/show?url=' + matDetail[index].thumbMediaUrl + '></i>';
												htmlData = htmlData + '</li></ul></div>';
											}
										});
										htmlData = htmlData + '<div class="braryboxbg" onclick="braryboxbgclick(this);"><span><i class="fa fa-check okTag" id="tag1"></i></span></div>';
										htmlData = htmlData + '</div>';
										$(".source-pick").children('.brarybox').remove();
										$(".source-pick").children('#box-del').remove();
										$(".source-pick").append(htmlData);
										$(".source-pick").append('<span id="box-del">删除</span>');
										$(".media-cover").hide();
										//$(".source-pick").append(del);	
									});
								};
							},
					        errorfn : function(){
					        	$("#errtextmsg").text("网络异常!");
					        	$('#myModal-errmsg').modal('show');
					        }							
						})
					} 
					$('#eform1msgId').val(data.msgId);
					$('#eform1msgType').val(data.msgType);
					$("#returnType option").prop("selected",false);
					$("#returnType option[value="+data.resType+"]").prop("selected",true);
					if(data.resType == "1"){
						if(!$("#returnType option[value=1]")){
							$("#returnType").append("<option value='1'>关注回复</option>");
						}
						$("#eform1resMsgKey").prop("disabled",true);
					}
					if(data.resType == "2" ){
						$("#eform1resMsgKey").prop("disabled",false);
					}
					if(data.resType == "2" && msgFlag){
						$("#returnType option[value=1]").remove();
					}
					$('#eform1myModalLabel').text('修改智能回复');
					$('#eform1platformid').val('${appid}');
					$('#eform1resmsgId').val(data.resmsgId);
					$('#eform1resMsgname').val(data.resMsgname);
					/* 修改时智能回复名称不可修改 */
					$("#eform1resMsgname").attr("disabled",true);
					$('#eform1resMsgKey').val(data.resMsgKey);
					$('#eform1').attr("action","${ctxPath}/resMessage/update?${_csrf.parameterName}=${_csrf.token}");
				});	
		 });
		function setSecond(obj){  
		    var val = obj.value;  
		  	var sec = document.getElementById('eform1msgId'); 
		    if(val == 'text'){  
		        sec.innerHTML = textMsg;  
		    }else{  
		        sec.innerHTML = imageMsg;  
		    }     
		} 
	</script>
<script>
//tab切换
$(function(){
	tabs();
	var materialData;
	var datahtml='';
	$.ax({
		url: "${ctxPath}/menu/selectMaterialByMsgidAndplatformId",	
		data:{"appid":'${appid}'},
		successfn: function(data){
			if (data.result == 1) {
				materialData = data.obj;
				$.each(materialData, function (index, item) {
					var htmlData = "";
					//获取图文明细
					var matDetail = materialData[index].detailModels;
					htmlData = htmlData + '<div class="brarybox" onmouseover="mouseover(this);" onmouseout="mouseout(this);">';
					htmlData = htmlData + '<input type="hidden" id="imagemsgId" value="' + materialData[index].msgId + '" >';
					htmlData = htmlData + '<dl><dt>' + materialData[index].msgName + '</dt>';
					htmlData = htmlData + '<dd>' + materialData[index].updateTime + '</dd></dl>';
					$.each(matDetail, function (index, item) {
						if (index == 0) {
							//第一个素材明细
							htmlData = htmlData + '<div class="material-img"><img src=${ctxPath}/material/show?url=' + matDetail[index].thumbMediaUrl + '>';
							htmlData = htmlData + '<span>' + matDetail[index].materialTitle + '</span></div>';
						} else {
							// 除第一个以外的素材明细
							htmlData = htmlData + '<div class="brary-template"><ul>';
							htmlData = htmlData + '<li class="brary-template-t">';
							htmlData = htmlData + '<span>' + matDetail[index].materialTitle + '</span>';
							htmlData = htmlData + '<i><img src=${ctxPath}/material/show?url=' + matDetail[index].thumbMediaUrl + '></i>';
							htmlData = htmlData + '</li></ul></div>';
						}
					});
					htmlData = htmlData + '<div class="braryboxbg" onclick="braryboxbgclick(this);"><span><i class="fa fa-check okTag" id="tag1"></i></span></div>';
					htmlData = htmlData + '</div>';
					datahtml = datahtml+htmlData;
					$(".newsData").append(htmlData);
				});
			};
		},
        errorfn : function(){
        	$("#errtextmsg").text("网络异常!");
        	$('#myModal-errmsg').modal('show');
        }		
	});
	//点击图文选择加号
	$('#meterialid').click(function(){
		$(".newsData").children().remove();
		$(".newsData").append(datahtml);
	});
	//从素材库选择图文消息(确定按钮)
	$(".brarybtn").click(function(){
		var e = $(".braryboxbg").not(":hidden");
		 if(e.length<=0){
			 $("#errtextmsg").text("请选择素材！");
			 $('#myModal-errmsg').modal('show');
			return;
		}else{
			var del='<span id="box-del">删除</span>';
			var addbox = e.parent(".brarybox");
			var imageid = e.parent(".brarybox").children('input').val();
			$("#eform1msgId").val(imageid);
			$('#eform1msgType').val('image');
			addbox.find(".braryboxbg").replaceWith("");
			$(".source-pick").append(addbox);
			$(".source-pick").append(del);	
			$(".close2").click();
			$(".media-cover").hide();
		} 
	}); 
	$("#myModal").on('hidden.bs.modal',function(){
		$("#eform1").validate().resetForm();
		$("#eform1")[0].reset();
		$("#eform1resMsgKey").prop("disabled",false);
	});
	//删除图文消息
	$(".source-pick").on("click","#box-del",function(){
		var e = $(".braryboxbg").not(":hidden");
		$("#eform2mediaId").val('');
		$("#box-del").prev(".brarybox").remove();
		$("#box-del").remove();
//		$(".frm-cover").show();	
		$(".media-cover").show();
	});
	$('#textmsgId').change(function(){
		$('#eform1msgId').val($('#textmsgId').val());
		$('#eform1msgType').val('text');
	});
});
function tabs(){
	$(".frm-group span").click(function(){
		var thisIndex = $(this).parent(".frm-group").children("span").index(this);
		$(this).addClass ('cru').siblings().removeClass ('cru');		
		$(".frm-centent").hide();
		$(".frm-centent:eq("+thisIndex+")").slideDown(0);
	});
	$('.frm-group span:eq(0)').trigger("click");
	$(".frm-title ul li").click(function(){
		$('#eform1msgId').val('');
		$('#eform1msgType').val('');
		$('#box-del').click();
		$('#textmsgId').val('');
		var thisIndex = $(this).parent("ul").children("li").index(this);
		$(this).addClass ('cru').siblings().removeClass ('cru');		
		$(".frm-box .frm-tab").hide();
		$(".frm-box .frm-tab:eq("+thisIndex+")").slideDown(0);
		if(thisIndex == 0){
			$('#eform1msgType').val('image');
		}else{
			$('#eform1msgType').val('text');
		}
	});
	$('.frm-title ul li:eq(0)').trigger("click");
	$(".mobile-nav li span").click(function(){		
		$(this).siblings(".mobile-nav-list").show();
		$(".mobile-nav li span").not(this).siblings(".mobile-nav-list").hide();		
	});
	$(".mobile-nav ul li").eq(0).children("span").trigger("click");	
}
/*图文素材*/
function mouseover(e){
	var bg=$(e).children(".braryboxbg");
	bg.show();	
	$(".braryboxbg").not(bg).not($(".okTag").not(":hidden").parent().parent("div")).hide();
}
function mouseout(el){
	var e = $(el).find(".okTag");
	if(e.is(":hidden")){
		$(el).children(".braryboxbg").hide();
	}else{
		$(el).children(".braryboxbg").show();
	}
}
function braryboxbgclick(el){
	$(".braryboxbg").not($(el)).hide();
	var e = $(el).find(".okTag");
	$(".okTag").not(e).hide();
	if(e.is(":hidden")){
		e.show();
	}else{
		e.hide();
	}
	$(".query-btn").on("click", "#resetBtn", function() {
		$("#resMsgname").val("");
		$("#msgType").val("");
	});
}
</script>
</body>
</html>