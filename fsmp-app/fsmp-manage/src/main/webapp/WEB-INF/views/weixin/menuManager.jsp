<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>内管系统</title>
</head>
<body>
	<input type="hidden" id="csrfheader" value="${csrfheader}">
	<input type="hidden" id="csrftoken" value="${csrftoken}">
	<input type="hidden" id="ctxPath" value="${ctxPath}">
	<input type="hidden" id="appid" value="${appid3}">
	<!-- sidebar-menu end-->
	<form:form id="eform">
		<div class="sidebar-right">
			<div class="main-content">
				<div class="mainbox">
					<div class="tablebox">
						<div class="table-tit">
							<div class="tit-left">自定义菜单管理</div>
						</div>
						<div class="menu-set">
							<div class="mobile-menu">
								<div class="mobile-phone"></div>
								<div class="screeCon" id="wxKey">
								<div class="wxKeyCon" id="keyTitle"></div>
									<div class="wxKeyCon" id="keyMenu">
										<ul class="menuBox">
											<c:forEach var="menu" items="${menus}" varStatus="status">
												<c:if test="${not empty menu}">
													<c:if test="${empty menu.child}">
														<li class="menuOne">
															<a class="menua" id="wxm${status.index}" href="javascript:;">
 																<!--<i class="fa fa-navicon"></i> -->
																${menu.menuName}
															</a>
															<input type="hidden" class="data" value='{"menuLevel":"${menu.menuLevel}","menuType":"${menu.menuType}","menuid":"${menu.menuid}","menuName":"${menu.menuName}","msgType":"${menu.msgType}","msgId":"${menu.msgId}","menuUrl":"${menu.menuUrl}","appid":"${menu.appid}"}'>
															<ul class="menuChildCon">
																<li class="menuFour">
																	<a class="menua" title="最多添加五个子菜单" href="javascript:;">
																	<i class="fa fa-plus"></i>
																	</a>
																</li>
															</ul>
														</li>
													</c:if>
													<c:if test="${not empty menu.child}">
														<li class="menuOne">
															<a class="menua" id="wxm${status.index}" href="javascript:;">
 																<!--<i class="fa fa-navicon"></i> -->
																${menu.menuName}
															</a>
															<input type="hidden" class="data" value='{"menuLevel":"${menu.menuLevel}","menuType":"${menu.menuType}","menuid":"${menu.menuid}","menuName":"${menu.menuName}","msgType":"${menu.msgType}","msgId":"${menu.msgId}","menuUrl":"${menu.menuUrl}","appid":"${menu.appid}"}'>
															<ul class="menuChildCon" >
																<c:forEach var="submenu" items="${menu.child}" varStatus="cstatus">
																	<li class="menuThree">
																		<a class="menua" id="wxm${status.index}${cstatus.index}" href="javascript:;">${submenu.menuName}</a>
																		<input type="hidden" class="data" value='{"menuLevel":"${submenu.menuLevel}","menuid":"${submenu.menuid}","menuName":"${submenu.menuName}","menuType":"${submenu.menuType}","msgType":"${submenu.msgType}","msgId":"${submenu.msgId}","menuUrl":"${submenu.menuUrl}","appid":"${submenu.appid}"}'>
																	</li>
																</c:forEach>
																<c:choose> 
																	<c:when test="${menu.child.size() < 5}"> 
																		<li class="menuFour"><a class="menua"href="javascript:;"><i class="fa fa-plus"></i></a></li> 
																	</c:when> 
																</c:choose>
															</ul>
														</li>
													</c:if>
												</c:if>
											</c:forEach>
											<!-- 主菜单不足3个添加按钮 -->
											<c:if test="${menus.size() < 3}">
												<li class="menuTwo"><a class="menua" title="最多添加三个一级菜单" href="javascript:;">
													<i class="fa fa-plus"></i>&nbsp;添加菜单</a>
												</li>
											</c:if>
										</ul>
									</div>
									<!-- end-->
								</div>
							</div>
							<div class="con" id="infoBox">
								<input type="hidden" id="num" value="">
								<div class="infoCon infoConOne">
									<div class="menu-form">
										<div class="set-tit">
											<div class="set-tit-t">菜单名称</div>
											<div class="set-delete">
												<a href="#">删除菜单</a>
											</div>
										</div>
										<div class="set-titmain clear">
											<div class="query-text">
												<span>菜单名称：</span> <input type="text" id="menuName" class="form-control" value="菜单名称">
											</div>
										</div>
										<div class="frm-group">
											<em>菜单消息</em> 
											<span class="cur">
												<a href="javascript:;">发送消息</a>
											</span> 
											<span>
												<a href="javascript:;">跳转网页</a>
											</span>
										</div>
										<div class="frm-centent">
											<div class="frm-title">
												<ul>
													<li><a href="javascript:;"><i class="fa fa-credit-card"></i>图文消息</a></li>
													<li><a href="javascript:;"><i class="fa fa-pencil"></i>文字</a></li>
												</ul>
											</div>
											<div class="frm-box">
												<div class="frm-tab source-pick" style="display: none;">
								                    <div class="media-cover selectMedia">
								                        <a href="" data-toggle="modal">
								                            <i class="fa fa-plus"></i>
								                            <em>从消息管理中选择</em>
								                        </a>
								                    </div>
								                </div>
												<div class="frm-tab">
													
													<select id="textmsgId" name="msgId" class="form-control"></select> 
												</div>
											</div>
											<!-- frm-box-->
										</div>
										<!-- frm-centent（跳转页面）-->
										<div class="frm-centent">
											<div class="frm-url">
												<div class="url-tit">订阅者点击该菜单会跳到以下链接</div>
												<div class="set-titmain url-t">
													<div class="query-text">
														<span>页面地址：</span> 
														<input type="text" id="menuUrl" class="form-control" value="">
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- 一级菜单 -->
								<div class="infoCon infoConTwo">
									<div class="menu-form">
										<div class="set-tit">
											<div class="set-tit-t">菜单名称</div>
											<div class="set-delete">
												<a href="#">删除菜单</a>
											</div>
										</div>
										<div class="set-titmain clear">
											<div class="query-text">
												<span>菜单名称：</span> <input type="text" id="menuOneName" class="form-control"
													value="菜单名称">
											</div>
										</div>
									</div>
								</div>								
							</div>
						</div>
						<!-- 保存 -->
						<div class="frmbut-release">
							<button type="button" class="btn btn-primary">保存并发布</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form:form>
	<!-- sidebar-right end-->
	</div>
<!-- Modal-->
	<div class="modal fade" id="mediaModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document" style="width: 750px;">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
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
	<script type="text/javascript" src="<c:url value='/resources/js/wx/wxMenu.js?v=${jsv}'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui.min.js?v=${jsv}'/>"></script>
	<script type="text/javascript">
	/*获取消息列表*/
	var textMsg="";
	$(document).ready( function () {
		$.ax({
        		url: "${ctxPath}/resMessage/getmessages",
        		data: {appid:"${appid3}",msgType:"0"},
        		successfn : function(data){
        			var json = data.obj;//list
    				$.each(json, function (index, item) {  
    		            //循环获取数据    
    		            var msgId = json[index].msgId;  
    		            var msgName = json[index].msgName;
    		            if(json[index].msgType=="0"){
    		            	textMsg=textMsg+"<option value=" + msgId + ">" + msgName + "</option>";
    		            }
    		        });
    				var sec = document.getElementById('textmsgId'); 
    				sec.innerHTML = textMsg;
                },
                errorfn : function(){
                	$("#errtextmsg").text("网络异常!");
                	$('#myModal-errmsg').modal('show');
                },
                
        	});
	});
	var materialData;
	var datahtml='';
	// 选择图文消息
	$(document).ready( function () {
		$.ax({
			url: $('#ctxPath').val() + "/menu/selectMaterialByMsgidAndplatformId",	
			data:{"appid":$("#appid").val()},
			successfn: function(data){
				if (data.result == 1) {
					materialData = eval(data.obj);
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
						//alert(datahtml);
						$(".newsData").append(htmlData);
					});
				};
			},
	        errorfn : function(){
	        	$("#errtextmsg").text("网络异常!");
	        	$('#myModal-errmsg').modal('show');
	        }			
		});
	});	
	$(".selectMedia").click(function(){
		$("#mediaModal").modal('show');
		$(".newsData").children().remove();
		$(".newsData").append(datahtml);
	});
	//菜单拖拽
	$(".menuBox").on("click","li", function() {
	     $(".menuBox").sortable();
	     $(".menuChildCon").sortable();
	 });
	/*链接地址不能输入空格*/
	$("#menuUrl").bind("keyup",function(){
		var isIdCardNo2 = /\s/;
		if(isIdCardNo2.test($("#menuUrl").val())){
			$("#menuUrl").val($.trim($("#menuUrl").val()));
			$("#errtextmsg").text("页面地址不能为空！");
			$('#myModal-errmsg').modal('show')
		}
	});
	/*地址长度超过限制*/
	$("#menuUrl").blur(function(){
		if($("#menuUrl").val().length>200){
			$("#menuUrl").val("");
			$("#errtextmsg").text("地址长度超过限制");
			$('#myModal-errmsg').modal('show')
		}
	});
	</script>
</body>
</html>