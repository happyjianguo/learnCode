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
	<div class="sidebar-right">
		<div class="main-content">			
			<div class="mainbox">
				<div class="tablebox">	
						<div class="picture-left pinned">
							<div class="tip-pic">
								<span class="tip">图文导航</span>
							</div>
							<div class="list-pre">
									<input type='hidden' id='materialModelId' name ='materialModelId' value='${newsMaterial.materialId}'>
									<input type="hidden" value='${newsMaterial.detailModelsLength}' id="itemCount" />
									<c:forEach var="NewsMaterialDetai" varStatus="status" items="${newsMaterial.detailModels}">
										<c:if test="${status.index==0}">
											<div class="cover-big border-ctr" id="list0">
												<input type='hidden' id='detailId' name ='detailId' value='${NewsMaterialDetai.detailId}'>
												<input type='hidden' id='materialId' name ='materialId' value='${NewsMaterialDetai.materialId}'>
												<input type='hidden' id='title' name ='materialTitle' value='${NewsMaterialDetai.materialTitle}'>
												<input type='hidden' id='thumb_media_id' name ='thumbMediaId' value='${NewsMaterialDetai.thumbMediaId}'>
												<input type='hidden' id='author' name ='author' value='${NewsMaterialDetai.author}'>
												<input type='hidden' id='digest' name ='digest' value='${NewsMaterialDetai.digest}'>
												<input type='hidden' id='wxd_materialId' name ='wxd_materialId' value='${NewsMaterialDetai.wxd_materialId}'>
												<input type='hidden' id='detailSort' name ='detailSort' value='${NewsMaterialDetai.detailSort}'>
												<input type='hidden' id='showCoverPic' name ='showCoverPic' value='${NewsMaterialDetai.showCoverPic}'>
												<input type='hidden' id='updateTime' name ='updateTime' value='${NewsMaterialDetai.updateTime}'>
												<input type='hidden' id='createTime' name ='createTime' value='${NewsMaterialDetai.createTime}'>
												<input type='hidden' id='thumbUrl' name ='thumbMediaUrl' value='${NewsMaterialDetai.thumbMediaUrl}'>
												<input type='hidden' id='content' name ='content' value='${NewsMaterialDetai.content}'>
												<input type='hidden' id='content_source_url' name ='contentSourceUrl' value='${NewsMaterialDetai.contentSourceUrl}'>
												    <%-- <img class="pic" src="${ctxPath}/resources/img/m_img1.png" alt="..."> --%>
												    <img class="pic" src='${ctxPath}/material/show?url=${NewsMaterialDetai.thumbMediaUrl}' alt="...">
													<div class="text-name">
														<span id="tit0">${NewsMaterialDetai.materialTitle}</span>
													</div>
													<div class="move">
														<span><i class="fa fa-arrow-down" title="下移"></i></span>
													</div>
											</div>
										</c:if>
										<c:if test="${status.index>0}">
											<div class="cover-big border-ctr" id="list${status.index}">
												<input type='hidden' id='detailId' name ='detailId' value='${NewsMaterialDetai.detailId}'>
												<input type='hidden' id='materialId' name ='materialId' value='${NewsMaterialDetai.materialId}'>
												<input type='hidden' id='title' name ='materialTitle' value='${NewsMaterialDetai.materialTitle}'>
												<input type='hidden' id='thumb_media_id' name ='thumbMediaId' value='${NewsMaterialDetai.thumbMediaId}'>
												<input type='hidden' id='author' name ='author' value='${NewsMaterialDetai.author}'>
												<input type='hidden' id='digest' name ='digest' value='${NewsMaterialDetai.digest}'>
												<input type='hidden' id='wxd_materialId' name ='wxd_materialId' value='${NewsMaterialDetai.wxd_materialId}'>
												<input type='hidden' id='detailSort' name ='detailSort' value='${NewsMaterialDetai.detailSort}'>
												<input type='hidden' id='showCoverPic' name ='showCoverPic' value='${NewsMaterialDetai.showCoverPic}'>
												<input type='hidden' id='updateTime' name ='updateTime' value='${NewsMaterialDetai.updateTime}'>
												<input type='hidden' id='createTime' name ='createTime' value='${NewsMaterialDetai.createTime}'>
												<input type='hidden' id='thumbUrl' name ='thumbMediaUrl' value='${NewsMaterialDetai.thumbMediaUrl}'>
												<input type='hidden' id='content' name ='content' value='${NewsMaterialDetai.content}'>
												<input type='hidden' id='content_source_url' name ='contentSourceUrl' value='${NewsMaterialDetai.contentSourceUrl}'>
											    <ul class="mm">
													<li class="m">
														<span id='tit${status.index}'>${NewsMaterialDetai.materialTitle}</span>
														<img id='img${status.index}' class="img-responsive img-default" src='${ctxPath}/material/show?url=${NewsMaterialDetai.thumbMediaUrl}' style='display:inline' >
														<i class="fa fa-picture" style="display: none"></i>
													</li>						
												</ul>
												<div class="move" >						
													<span><i class="fa fa-arrow-down fa-c" title="下移"></i></span>
													<span><i class="fa fa-arrow-up fa-c" title="上移"></i></span>
													<!-- <span><i class="fa fa-trash fa-c" title="删除"></i></span> -->
												</div>
											</div>
										</c:if>
									</c:forEach>
							</div>						
						</div>
						<div class="content-right">
							<div class="editarea" id="list0">
								<c:forEach var="NewsMaterialDetai" varStatus="status" items="${newsMaterial.detailModels}">
								<c:if test="${status.index==0}">
								<div id="temp_content" style="display: none;">${NewsMaterialDetai.content}</div>
								<div class="first-tit ">
									<input type="text" class="biggest materialItemTitle" value="${NewsMaterialDetai.materialTitle}" placeholder="请输入标题">								 
									 <input type="text" class="smallest materialItemAuthor" value="${NewsMaterialDetai.author}" placeholder="请输入作者">								 
								</div>
								<!--富文本-->
								<div class="content-edit" >
									<script type="text/plain" id="myEditor"  class="myEditor" style="width:80%;height:240px;"></script>
									<div class="clear"></div>
								</div>
								<!--富文本end-->
								<div class="link-text">
									<!-- <input type="checkbox" class="text-link" checked="checked" value=""><span>原文链接</span> -->
									<span>原文链接</span>
									<form action="#" method="" class="link-form">
										<div class="select-link">											
												<input type="text" class=" link0 materialItemUrl" value="${NewsMaterialDetai.contentSourceUrl}"/><br>
										</div>
									</form>										
								</div>
							</div>
							<div class="edit-style">
								<span class="word">发布编辑样式</span>								
								<div id="cover">
									<span class="cover">封面</span>
									<span class="cover cover-tip ">大图片尺寸建议：900像素*500像素</span>									
								</div>								
								<div class="file-button">									
									<button type="button" class="libary-up" data-toggle="modal" data-target="#myModal2">从图片库选择</button>
								</div>
								<div></div>
								<div class="abstract">
									<span class="abstract-1">摘要</span>
									<span class="cover-tip abstract-tip">选填，如果不填写会默认抓取正文前66个字</span>
									<div class="abstract-text">
										<textarea class="text-edit form-control materialItemInfor" rows="3" >${NewsMaterialDetai.digest}
										</textarea>
									</div>
								</div>						
							</div>
							</c:if>
							</c:forEach>							
						</div>	
						<div class="save-area">
							<div class="save-all">
								<span class="btn btn-define save-btn" onclick="saveMaterialInfor(false)">保存</span>
							</div>
							<div class="save-all">
								<span class="btn btn-define save-btn" onclick="updateMaterials()">保存并发布</span>
							</div>
							<div class="save-send">
								<span class="btn btn-define send-btn"><a href="${ctxPath}/material/init?appid=${appid }">取消</a></span>
							</div>
						</div>
				</div>  
			</div> 			
		</div>
	</div>
	<!-- Modal1 -->
	<form:form id="tomain" action="${ctxPath}/material/init?appid=${appid }">
	</form:form>
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">从图库中选择图片</h4>
      </div>
      <div class="modal-body">
        <div class="row">
		</div>
      </div>
      <div class="modal-footer">
      	<button type="button" class="btn btn-primary btn-img">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>        
      </div>
    </div>
  </div>
</div>
<!-- Modal1 end-->
<!--Modal2-->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel2">从图库中选择图片</h4>
      </div>
      <div class="modal-body">
        <div class="row">
		</div>
      </div>
      <div class="modal-footer">
      	<button type="button" class="btn btn-primary btnimg">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>        
      </div>
    </div>
  </div>
</div>
<!--Modal2 end-->
	<input type="hidden" value="list0" id="hide" />
	<%@ include file="/WEB-INF/views/common/conmm.jsp"%>
	<!-- sidebar-right end-->
	<script type="text/javascript">
		var i = 0;
		function updateMaterials(){
			if(i!=0){
				return ;
			}
			//var materials = ""
			if(!saveMaterialInfor(true)){
				return;
			}
			i = i+1;
			var materialModel = new Object();
			var detailModels = new Array();
			var materialId = $("#materialModelId").val();
			var materialName = "";
			$(".border-ctr").each(function(i){
				var title = $(this).find("#title").val();
				if(i==0){
					materialName = title;
				}
		   		var thumb_media_id = $(this).find("#thumb_media_id").val();
		   		var author = $(this).find("#author").val();
		   		var digest = $(this).find("#digest").val();
		   		var thumbUrl = $(this).find("#thumbUrl").val();
		   		var content = $(this).find("#content").val();
		   		var content_source_url = $(this).find("#content_source_url").val();
				var wxd_materialId = $(this).find("#wxd_materialId").val();
				var detailSort = $(this).find("#detailSort").val();
				var showCoverPic = $(this).find("#showCoverPic").val();
				var updateTime = $(this).find("#updateTime").val();
				var createTime = $(this).find("#createTime").val();
				var detailId = $(this).find("#detailId").val();
				var materialId = $(this).find("#materialId").val();
		   		var newsDetail=new Object(); 
		   			newsDetail.detailId = $.trim(detailId);
		   			newsDetail.materialId = $.trim(materialId);
					newsDetail.materialTitle = $.trim(title);
					newsDetail.thumbMediaId = $.trim(thumb_media_id);
					newsDetail.author = $.trim(author);
					newsDetail.digest = $.trim(HTMLEncode(digest));
					newsDetail.showCoverPic="1";
					newsDetail.thumbMediaUrl = $.trim(thumbUrl);
					newsDetail.content = $.trim(HTMLEncode(content));
					newsDetail.contentSourceUrl = $.trim(content_source_url);
					newsDetail.wxd_materialId=$.trim(wxd_materialId);
					newsDetail.detailSort=$.trim(detailSort);
					newsDetail.updateTime=$.trim(updateTime);
					newsDetail.createTime=$.trim(createTime);
					detailModels.push(newsDetail);
			});
			if(detailModels.length==0){
				$("#errtextmsg").text("数据错误!");
				$('#myModal-errmsg').modal('show');
				return ;
			}
			materialModel.detailModels= detailModels;
			materialModel.materialName = $.trim(materialName); 
			materialModel.materialId=materialId;
			materialModel.appid = "${appid}";
			var params = {"matList":JSON.stringify(materialModel)}; 
			/* 修改数据 */
			uploadData(params);
		}
		function selectPic(params){
			$.ax({
				url:'${ctxPath}/material/selectPic',
				dataType:'json',
				data:params,
				successfn : function(result) {
					$("#myModal2").find(".row").empty();
					$("#myModal1").find(".row").empty();
					$(result.obj).each(function(i){
						var mediaId = $(this).attr("mediaId");
						var title = $(this).attr("title");
						var mediaUrl = $(this).attr("mediaUrl");
						var filePath = $(this).attr("filePath");
						$("#myModal2").find(".row").append("<div class='col-sm-6 col-md-4 little-rank1' id="+mediaId+">"
								+"<input type='hidden' class='mediaUrl' name='mediaUrl' value="+mediaUrl+">"
								+"<input type='hidden' class='filePath' name='filePath' value="+filePath+">"
								+"<input type='hidden' class='materialName' name='materialName' value="+title+">"
								+"<div class='thumbnail little-pic'>"
								+"<div class='material-img'>"
								+"<img src=${ctxPath}/material/show?url="+filePath+" alt='test' class='little-img'>"
								+"</div>"
								+"<div class='img-info1' id="+i+">"
								+"<i class='fa fa-check'></i>"
								+"</div>"
								+"<div class='caption pic-name'>"
								+"<p>"+title+"</p>"
								+"</div>"
								+"</div>"
								+"</div>");
						$("#myModal1").find(".row").append("<div class='col-sm-6 col-md-4 little-rank' id="+mediaId+">"
								+"<input type='hidden' class='mediaUrl' name='mediaUrl' value="+mediaUrl+">"
								+"<input type='hidden' class='filePath' name='filePath' value="+filePath+">"
								+"<input type='hidden' class='materialName' name='materialName' value="+title+">"
								+"<div class='thumbnail little-pic'>"
								+"<div class='material-img'>"
								+"<img src=${ctxPath}/material/show?url="+filePath+" alt='test' class='little-img'>"
								+"</div>"
								+"<div class='img-info' id=m"+i+">"
								+"<i class='fa fa-check'></i>"
								+"</div>"
								+"<div class='caption pic-name'>"
								+"<p>"+title+"</p>"
								+"</div>"
								+"</div>"
								+"</div>");
					});
				},
				errorfn : function(result) {
					$("#errtextmsg").text("网络异常!");
					$('#myModal-errmsg').modal('show');
				}
			});
		}
		function uploadData(params){
			$.ax({
				url:'${ctxPath}/material/updateNewsMaterialModel?${_csrf.parameterName}=${_csrf.token}',
				data:params,
				dataType:'json',
				successfn : function(result) {
					if(result.result==1){
						$("#textmsg").empty();
						$("#textmsg").text(result.msg);
						$('#myModal-confirm').modal('show');
						$('#myModal-confirm').on('hidden.bs.modal', function (e) {
							$("#tomain").submit();
						});
					}else{
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
		function saveMaterialInfor(flag){
			var listId = $(".editarea").attr("id");
			var title = $(".materialItemTitle").val();
			var author = $(".materialItemAuthor").val();
			var content=editor.getContent();
			var infor = $(".materialItemInfor").val();
			var url = $(".materialItemUrl").val();
			var media_id = $(".list-pre").find("#" + listId).find("#thumb_media_id").val();
			if ($.trim(title).length == 0 ) {
				$("#errtextmsg").text("标题不能为空");
				$('#myModal-errmsg').modal('show');
				return false;
			}
			if(getStringLength(title) > 100){
				$("#errtextmsg").text("标题超出长度限制");
				$('#myModal-errmsg').modal('show');
				return false;
			}
			if(!stringCheck($.trim(title))){
				$("#errtextmsg").text("标题只能由中文、字母、数字及“-”、“_”组成，请重新输入！");
				$('#myModal-errmsg').modal('show');
				return false;
			}
			if ($.trim(author).length == 0) {
				$("#errtextmsg").text("作者不能为空");
				$('#myModal-errmsg').modal('show');
				return false;
			}
			if(getStringLength(author) > 100){
				$("#errtextmsg").text("作者超出长度限制");
				$('#myModal-errmsg').modal('show');
				return false;
			}
			if(!stringCheck($.trim(author))){
				$("#errtextmsg").text("作者只能由中文、字母、数字及“-”、“_”组成，请重新输入！");
				$('#myModal-errmsg').modal('show');
				return false;
			}
			if ($.trim(content).length == 0) {
				$("#errtextmsg").text("内容不能为空");
				$('#myModal-errmsg').modal('show');
				return false;
			}
			if(getStringLength(content) > 4000){
				$("#errtextmsg").text("内容超出长度限制");
				$('#myModal-errmsg').modal('show');
				return false;
			}
			if($.trim(media_id).length == 0 ){
				$("#errtextmsg").text("请选择图片");
				$('#myModal-errmsg').modal('show');
				return false;
			}
			if(getStringLength(infor) > 1000){
				$("#errtextmsg").text("内容摘要超出长度限制");
				$('#myModal-errmsg').modal('show');
				return false;
			}
			if($.trim(infor).length == 0 ){
				infor =editor.getContentTxt();
				infor = getLimitText(infor,200);
			}
			var index = listId.substring(listId.length-1,listId.length);
			$("#tit"+index).text(title);
			$("#"+listId).find("#title").val($.trim(title));
			$("#"+listId).find("#author").val($.trim(author));
			$("#"+listId).find("#digest").val($.trim(infor));
			$("#"+listId).find("#content").val($.trim(content));
			$("#"+listId).find("#content_source_url").val($.trim(url));
			if(!flag){
				$("#textmsg").empty();
				$("#textmsg").text("当前图文消息保存成功");
				$('#myModal-confirm').modal('show');
			}
			return true;
		}
	</script>
	<script type="text/javascript">	
	$(".close-all").click(function(){
		//close text
		/* var str=$("#myEditor").html();
	    var tex=$("#myEditor").text();
		$(".close-all").css("display","none");
		$(".open-all").css("display","inline");				
		$("#myEditor").html(tex.substr(0,78));		
		$(".open-next").css("visibility","visible");
		// click text
		$("#myEditor").click(function(){
			$(".open-all").css("display","none");
			$(".close-all").css("display","inline");
			$("#myEditor").html(str);
		})		
		//open text
			$(".open-all").click(function(){
			$(".open-all").css("display","none");
			$(".close-all").css("display","inline");
			$("#myEditor").html(str);
		}) */
	})
</script>
<script type="text/javascript">
   var num=$("#itemCount").val();
   var editor ;
   $(function(){
	   var param = {
				"appid" : "${appid}"
			};
   	addPre();
   	movePic();
   	editor =UM.getEditor('myEditor');
   	selectPic(param);
   	//点击当前导航 将相应信息展示在右边编辑栏里
   	$(".list-pre").on("click",".border-ctr",function(){
   		var listId = $(this).attr("id");
   		var title = $(this).find("#title").val();
   		var thumb_media_id = $(this).find("#thumb_media_id").val();
   		var author = $(this).find("#author").val();
   		var digest = $(this).find("#digest").val();
   		var thumbUrl = $(this).find("#thumbUrl").val();
   		var content = $(this).find("#content").val();
   		var content_source_url = $(this).find("#content_source_url").val();
   		$("#"+$("#hide").val()).css("border","1px solid #EAEAEA");
   		$("#hide").val(listId);
   		$(".editarea").attr("id",listId);
   		$(".materialItemTitle").val(title);
		$(".materialItemAuthor").val(author);
		$("#myEditor").empty();
		editor.setContent(content);
		//editor.setContent($("#temp_content").text(),true);
		$(".materialItemInfor").val(digest);
		$(".materialItemUrl").val(content_source_url);
		$(".materialItemCover").attr("src",thumbUrl);
		$(".materialItemCover").attr("mediaId",thumb_media_id);
   	});
   });
	function addPre(){		
		$(".content-add").click(function(){
			var addList;
			addList='<div class="list-sm border-ctr" id="list'+num+'">'+
						"<input type='hidden' id='title'>"+
						"<input type='hidden' id='thumb_media_id'>"+
						"<input type='hidden' id='author'>"+
						"<input type='hidden' id='digest'>"+
						"<input type='hidden' id='thumbUrl'>"+
						"<input type='hidden' id='content'>"+
						"<input type='hidden' id='content_source_url'>"+
						"<input type='hidden' id='wxd_materialId' name ='wxd_materialId'>"+
						"<input type='hidden' id='detailSort' name ='detailSort' >"+
						"<input type='hidden' id='title' name ='materialTitle'>"+
						"<input type='hidden' id='showCoverPic' name ='showCoverPic'>"+
						"<input type='hidden' id='updateTime' name ='updateTime'>"+
						"<input type='hidden' id='createTime' name ='createTime'>"+
						'<ul class="mm">'+
							'<li class="m">'+
								'<span id="tit'+num+'">标题</span>'+
								'<img id="img'+num+'" class="img-responsive img-default" src="" >'+
								'<i class="fa fa-picture"></i>'+
							'</li>'+							
						'</ul>'+
						'<div class="move" id="move'+num+'">'+							
							'<span><i class="fa fa-arrow-down fa-c" title="下移"></i></span>'+
							'<span><i class="fa fa-arrow-up fa-c" title="上移"></i></span>'+
							'<span><i class="fa fa-trash fa-c" title="删除"></i></span>'+
						'</div>'+
					'</div>';					
			$(".list-pre").append(addList);
			if(num>=2){
				for (var i=1;i<num;i++) {
					var movId="move"+i;					
					$('#'+movId).children("span").children(".m-down").css("display","inline");
				}
			}
			num++;
		});
		//导航栏的上下移删除
		//删除
		$('.list-pre').on("click",'.fa-trash',function(){			
			var movId=$(this).parent().parent().attr("id");				
			var ulId=$("#"+movId).parent().attr("id");				
			$("#"+ulId).remove();			
		});
		//上移
		$('.list-pre').on("click",'.fa-arrow-up',function(){			
			var cuId=$(this).parent().parent().parent().attr("id");
			var preId=$("#"+cuId).prev().attr("id");
			var mid1,mid2;			
			if (preId=='list0') {				
				var num=cuId.substring(4,5);				
				mid1=$("#tit0").html();
				mid2=$("#tit"+num).html();				
				$("#tit0").html(mid2);
				$("#tit"+num).html(mid1);				
			} else{
				mid1=$("#"+cuId).find("li span").html();
				mid2=$("#"+preId).find("li span").html();
				$("#"+cuId).find("li span").html(mid2);
				$("#"+preId).find("li span").html(mid1);				
			}			
			mid1=$("#"+cuId).find("img").attr("src");
			mid2=$("#"+preId).find("img").attr("src");
			$("#"+cuId).find("img").attr("src",mid2);
			$("#"+preId).find("img").attr("src",mid1);
			$("#"+$("#hide").val()).css("border","1px solid #EAEAEA");
			$("#"+cuId).css("border","1px solid #EAEAEA");
			$("#"+preId).css("border","2px solid forestgreen");
			$("#hide").val(preId);
			//上移下移 参数替换 开始
			var title = $("#"+cuId).find("#title").val();
			var thumb_media_id = $("#"+cuId).find("#thumb_media_id").val();
			var author = $("#"+cuId).find("#author").val();
			var digest = $("#"+cuId).find("#digest").val();
			var thumbUrl = $("#"+cuId).find("#thumbUrl").val();
			var content = $("#"+cuId).find("#content").val();
			var content_source_url = $("#"+cuId).find("#content_source_url").val();
			var title1 = $("#"+preId).find("#title").val();
			var thumb_media_id1 = $("#"+preId).find("#thumb_media_id").val();
			var author1 = $("#"+preId).find("#author").val();
			var digest1 = $("#"+preId).find("#digest").val();
			var thumbUrl1 = $("#"+preId).find("#thumbUrl").val();
			var content1 = $("#"+preId).find("#content").val();
			var content_source_url1 = $("#"+preId).find("#content_source_url").val();
			$("#"+preId).find("#title").val(title);
			$("#"+preId).find("#thumb_media_id").val(thumb_media_id);
			$("#"+preId).find("#author").val(author);
			$("#"+preId).find("#digest").val(digest);
			$("#"+preId).find("#thumbUrl").val(thumbUrl);
			$("#"+preId).find("#content").val(content);
			$("#"+preId).find("#content_source_url").val(content_source_url);
			$("#"+cuId).find("#title").val(title1);
			$("#"+cuId).find("#thumb_media_id").val(thumb_media_id1);
			$("#"+cuId).find("#author").val(author1);
			$("#"+cuId).find("#digest").val(digest1);
			$("#"+cuId).find("#thumbUrl").val(thumbUrl1);
			$("#"+cuId).find("#content").val(content1);
			$("#"+cuId).find("#content_source_url").val(content_source_url1);
			//上移下移 参数替换 结束
			e.stopPropagation();
		});
		//下移
		$('.list-pre').on("click",'.fa-arrow-down',function(){			
			var cuId=$(this).parent().parent().parent().attr("id");
			var nexId=$("#"+cuId).next().attr("id");
			var mid1,mid2;	
			if(!nexId){
				$("#errtextmsg").text("没有下一个元素!");
				$('#myModal-errmsg').modal('show');
				return ;
			}
			if (cuId=='list0') {				
				var num=nexId.substring(4,5);				
				mid1=$("#tit0").html();
				mid2=$("#tit"+num).html();				
				$("#tit0").html(mid2);
				$("#tit"+num).html(mid1);				
			} else{
				mid1=$("#"+cuId).find("li span").html();
				mid2=$("#"+nexId).find("li span").html();
				$("#"+cuId).find("li span").html(mid2);
				$("#"+nexId).find("li span").html(mid1);				
			}			
			mid1=$("#"+cuId).find("img").attr("src");
			mid2=$("#"+nexId).find("img").attr("src");
			$("#"+cuId).find("img").attr("src",mid2);
			$("#"+nexId).find("img").attr("src",mid1);			
			$("#"+$("#hide").val()).css("border","1px solid #EAEAEA");
			$("#"+cuId).css("border","1px solid #EAEAEA");
			$("#"+nexId).css("border","2px solid forestgreen");
			$("#hide").val(nexId);
			//上移下移参数替换开始
			var title = $("#"+cuId).find("#title").val();
			var thumb_media_id = $("#"+cuId).find("#thumb_media_id").val();
			var author = $("#"+cuId).find("#author").val();
			var digest = $("#"+cuId).find("#digest").val();
			var thumbUrl = $("#"+cuId).find("#thumbUrl").val();
			var content = $("#"+cuId).find("#content").val();
			var content_source_url = $("#"+cuId).find("#content_source_url").val();
			var title1 = $("#"+nexId).find("#title").val();
			var thumb_media_id1 = $("#"+nexId).find("#thumb_media_id").val();
			var author1 = $("#"+nexId).find("#author").val();
			var digest1 = $("#"+nexId).find("#digest").val();
			var thumbUrl1 = $("#"+nexId).find("#thumbUrl").val();
			var content1 = $("#"+nexId).find("#content").val();
			var content_source_url1 = $("#"+nexId).find("#content_source_url").val();
			$("#"+nexId).find("#title").val(title);
			$("#"+nexId).find("#thumb_media_id").val(thumb_media_id);
			$("#"+nexId).find("#author").val(author);
			$("#"+nexId).find("#digest").val(digest);
			$("#"+nexId).find("#thumbUrl").val(thumbUrl);
			$("#"+nexId).find("#content").val(content);
			$("#"+nexId).find("#content_source_url").val(content_source_url);
			$("#"+cuId).find("#title").val(title1);
			$("#"+cuId).find("#thumb_media_id").val(thumb_media_id1);
			$("#"+cuId).find("#author").val(author1);
			$("#"+cuId).find("#digest").val(digest1);
			$("#"+cuId).find("#thumbUrl").val(thumbUrl1);
			$("#"+cuId).find("#content").val(content1);
			$("#"+cuId).find("#content_source_url").val(content_source_url1);
			//上移下移参数替换结束
			e.stopPropagation();
		});
	}
	function movePic(){
		$('.cover-big').on("mouseover",function(){			
			$(this).children('.move').fadeIn(100);
		}).on("mouseleave",function(){
			$(this).children('.move').fadeOut(100);
		});		
		$('.list-pre').on("mouseover",'.list-sm',function(){			
			$(this).children('.move').fadeIn(100);
		}).on("mouseleave",'.list-sm',function(){
			$(this).children('.move').fadeOut(100);
		});
	}
</script>
		<script type="text/javascript" charset="utf-8" src="${ctxPath}/resources/js/umeditor.config.js"></script>
		<script type="text/javascript" charset="utf-8" src="${ctxPath}/resources/js/umeditor.min.js"></script>
		<script type="text/javascript" src="${ctxPath}/resources/js/zh-cn.js"></script>
		<link href="${ctxPath}/resources/css/umeditor.css" type="text/css" rel="stylesheet">
	<!--富文本             -->				
		<script type="text/javascript">
		    //实例化编辑器
		    var um = UM.getEditor('myEditor');
		   	$(function(){//文本中插入图片   		
		   		$(".edui-btn-image").attr("data-toggle","modal");
		   		$(".edui-btn-image").attr("data-target","#myModal1");
		   	});
		</script>								
	<!--富文本end -->
<script type="text/javascript">
	$(function(){
		checkText();
		libPic();
		closeModel();
		insertImg();
		editStyle();
	});
	//编辑按钮的可用不可用
	function editStyle(){
		$(".biggest").focus(function(){
			$(".no-edit").css("display","block");
		});
		$(".smallest").focus(function(){
			$(".no-edit").css("display","block");
		});
		$("#myEditor").focus(function(){
			$(".no-edit").css("display","none");
		});
	}
	//原文链接
	function checkText(){		
		$('.text-link').on("click",function(){			
			controlShow();
		});
	}
	//原文链接显示隐藏
	function controlShow(){
		if($('.text-link').is(":checked")){				
			$('.link-form').css("display","inline");
			$('.link-text').css("height","80px");
		}else{				
			$('.link-form').css("display","none");
			$('.link-text').css("height","auto");
		}
	}
	//模态框图片选中
	function libPic(){
		$("#myModal1").on("click",".little-rank",function(){
			var okId=$(this).find(".img-info").attr("id");		
			if($("#"+okId).css("display")=='none'){
				$("#"+okId).css("display","block");
				
			}else{
				$("#"+okId).css("display","none");				
			}
		});
	}
	//关闭模态框初始化图片选中状态
	function closeModel(){		
		$(".close ").on("click",function(){
			$(".img-info").css("display","none");
			$(".img-info1").css("display","none");
		});			
		$(".btn-default ").on("click",function(){
			$(".img-info").css("display","none");
			$(".img-info1").css("display","none");
		});	
	}
    //插入图片
    function insertImg(){
    	var arr=new Array();
    	var i=0,j=0;
    	$(".btn-img ").on("click",function(){
			var ss=$(".img-info");    	
     	for (i=0;i<ss.length;i++) {
     		if(ss.eq(i).css("display")=="block"){     			
     			var im=ss.eq(i).attr("id");     			
     			arr[j] = $("#" + im).prev(".material-img").find("img").attr("src");
     			var mediaUrl = $("#" + im).parents(".little-rank").find("[name='mediaUrl']").val();
				var materialId = $("#" + im).parents(".little-rank").find("[name='materialId']").val();
				var MmaterialId = '<input type="hidden" name="materialId" value="'+materialId+'">'
				/*本地图片路径*/
				var imgAdds = '<img class="imgAdd" src="'+arr[j]+'">';
				$("#myEditor").append(imgAdds);
				$("#myEditor").append(MmaterialId);
				//$("#myEditor").append(mUrl);
     			j++;           
     		}
     	}
     	$(".close").click();
		});	    	
    }
</script>
<script type="text/javascript">
//选中导航栏显示边框
function borderControl(){	  
	$(".list-pre").on("click",".border-ctr",function(){		
		var t=$("#hide").val();
		var bord=$(this).attr("id");		
		if (t=="list0") {
			$("#hide").val(bord);			
			$("#"+bord).css("border","2px solid forestgreen");			
		} else{
			$("#"+t).css("border","1px solid #EAEAEA");
			$("#"+bord).css("border","2px solid forestgreen");
			$("#hide").val(bord);
		}		
	});
}
//从图片库添加封面
function coverPic() {
	//选中图片
	$("#myModal2").on("click",".little-rank1",function(){
		var ok = $(".img-info1");
		var okId = $(this).find(".img-info1").attr("id");
		for (var i = 0; i < ok.length; i++) {
			if (ok.eq(i).css("display") == 'block') {
				if (ok.eq(i).attr("id") == okId) {
					$("#" + okId).css("display", "none");
				} else {
					ok.eq(i).css("display", "none");
					$("#" + okId).css("display", "block");
				}
				break;
			}
		}
		$("#" + okId).css("display", "block");
	});
	//从图库选择图片		
	$(".btnimg").on("click", function() {
		var current = $("#hide").val();
		var i = 0;
		var ss = $(".img-info1");
		for (i = 0; i < ss.length; i++) {
			if (ss.eq(i).css("display") == "block") {
				var im = ss.eq(i).attr("id");
				var arr = $("#" + im).prev(".material-img").find("img").attr("src");
				var mediaId = $("#" + im).parents(".little-rank1").attr("id");
				var mediaUrl = $("#" + im).parents(".little-rank1").find("[name='mediaUrl']").val();
				if (current != 'list0') {
					$("#" + current).find("ul i").hide();
					$("#" + current).find(".img-default").show();
				}
				$("#" + current).find("img").attr("src", arr);
				$("#" + current).find("#thumb_media_id").val(mediaId);
				$("#" + current).find("#thumbUrl").val(mediaUrl);
				break;
			}
		}
		$(".close").click();
	});
}	
$(function (){
		coverPic();
		borderControl();
		$("#myEditor").empty();
		editor.setContent($("#temp_content").text(),true);
	});
function getContent() {
    var arr = [];
    arr.push(UM.getEditor('myEditor').getContent());
    return arr.join("\n");
}
</script>
</body>
</html>