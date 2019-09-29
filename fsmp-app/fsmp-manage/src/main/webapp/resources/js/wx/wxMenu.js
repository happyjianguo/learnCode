$(function(){
	wxMenuaddmenu();
	wxMenuselect();
	wxMenutabs();
	//一级按钮名称回显
	$('#menuOneName').on("change",function(){
		var jsonval = $.parseJSON($('#'+$('#num').val()).next("input").val());
		if(/^[A-Za-z0-9\u4e00-\u9fa5_-]+$/.test($('#menuOneName').val())){
			if($('#menuOneName').val().length<6 && $('#menuOneName').val().length>0){
				$('#'+$('#num').val()).text($('#menuOneName').val());
				jsonval["menuName"]=$('#menuOneName').val();
				$('#'+$('#num').val()).next("input").val(JSON.stringify(jsonval));
			}else{
				$('#menuOneName').val(jsonval["menuName"]);
				$("#errtextmsg").text("请输入1至5位有效名称！");
				$('#myModal-errmsg').modal('show');
			}
		}else{
			$("#errtextmsg").text("只能由中文、字母、数字、“-”、“_”组成，请重新输入！");
			$('#myModal-errmsg').modal('show');
			$('#menuOneName').val(jsonval["menuName"]);
		}
	});
	//二级按钮名回显
	$('#menuName').on("change",function(){
		var jsonval = $.parseJSON($('#'+$('#num').val()).next("input").val());
		if(/^[A-Za-z0-9\u4e00-\u9fa5_-]+$/.test($('#menuName').val())){
			if($('#menuName').val().length<6 && $('#menuName').val().length>0){
				$('#'+$('#num').val()).text($('#menuName').val());
				jsonval["menuName"]=$('#menuName').val();
				$('#'+$('#num').val()).next("input").val(JSON.stringify(jsonval));
			}else{
				$('#menuName').val(jsonval["menuName"]);
				$("#errtextmsg").text("请输入1至5位有效名称！");
				$('#myModal-errmsg').modal('show');
			}
		}else{
			$("#errtextmsg").text("只能由中文、字母、数字、“-”、“_”组成，请重新输入！");
			$('#myModal-errmsg').modal('show');
			$('#menuName').val(jsonval["menuName"]);
		}
	});
	//保存数据进json
	$('.save').on("click",function(){
		if(true){
			$('#'+$('#num').val()).text($('#menuName').val());
		}else{
			$('#'+$('#num').val()).text($('#menuOneName').val());
		}
		var jsonval = $.parseJSON($('#'+$('#num').val()).next("input").val());
		if($(this).parent().parent().children('.frm-group').children('span').attr('class').indexOf("cru") > 0){
			jsonval["menuType"]="click";
			if($('.frm-title li').index($('.frm-title .cru'))=="1"){
				jsonval["msgType"]="0";
				jsonval["msgId"]=$('#textmsgId').val();
			}else{
				jsonval["msgType"]="4";
				jsonval["msgId"]=$('#imagemsgId').val();
			}
			jsonval["menuUrl"]="";
		}else{
			jsonval["menuType"]="view";
			jsonval["msgType"]="";
			jsonval["msgId"]="";
			jsonval["menuUrl"]=$('#menuUrl').val();
		}
		jsonval["menuName"]=$('#menuName').val();
		$('#'+$('#num').val()).next("input").val(JSON.stringify(jsonval));
	});
	//删除按钮
	$(".set-delete").click(function(){
		//删除一级按钮
		if($('#'+$("#num").val()).parent().attr('class')=='menuOne'){
			if($('#'+$("#num").val()).parent('.menuOne').children('ul').children('li .menuThree').length<1){
				$('#'+$("#num").val()).parent('.menuOne').parent('.menuBox').children('.menuTwo').remove();
				if($('#'+$("#num").val()).parent('.menuOne').parent('.menuBox').children('.menuOne').length<4){
					if($('#'+$("#num").val()).parent('.menuOne').parent('.menuBox').children('.menuTwo').length==0){
						$('#'+$("#num").val()).parent('.menuOne').parent('.menuBox').append('<li class="menuTwo"><a class="menua" title="最多添加三个一级菜单" href="javascript:;"><i class="fa fa-plus"></i>&nbsp;添加菜单</a></li>');
					}
				} 
				$('#'+$("#num").val()).parent('.menuOne').remove();
				$(".infoConTwo").hide();
				$(".infoConOne").hide();
			}else{
				$("#errtextmsg").text("请先删除子菜单！");
				$('#myModal-errmsg').modal('show');
			} 
		//删除二级按钮
		}else if($('#'+$("#num").val()).parent().attr('class')=='menuThree'){
			$('#'+$("#num").val()).parent('.menuThree').parent('.menuChildCon').children('.menuFour').remove();
			if($('#'+$("#num").val()).parent('.menuThree').parent('.menuChildCon').children('li .menuThree').length<6){
				if($('#'+$("#num").val()).parent('.menuThree').parent('.menuChildCon').children('.menuFour').length==0){
					$('#'+$("#num").val()).parent('.menuThree').parent('.menuChildCon').append('<li class="menuFour"><a class="menua"href="javascript:;"><i class="fa fa-plus"></i></a></li> ');
					$(".infoConTwo").hide();
					$(".infoConOne").show();
				}
			}
			$('#'+$("#num").val()).parent('li').remove();						
		}
	});
	//控制输入字数
	$(".inputbox").keyup(function() {
		var area = $(this);
		//parseInt 方法返回与保存在 numString 中的数字值相等的整数。如果 numString 的前缀不能解释为整数，则返回 NaN（而不是数字）。
		var max = parseInt(area.attr("maxlength"), 10); //获取maxlength的值 转化为10进制，将输入到textarea的文本长度
		//这个判断可知max得到的是不是数字，设定的大小是多少
		if (max > 0) {
			if (area.val().length > max) { //textarea的文本长度大于maxlength 
				area.val(area.val().substr(0, max)); //截断textarea的文本重新赋值 
			}
			var sheng = max - area.val().length;
			$("#lsheng").html(sheng);
		}
	});
	$(".inputbox").blur(function() {
		var area = $(this);
		var max = parseInt(area.attr("maxlength"), 10); //获取maxlength的值 
		if (max > 0) {
			if (area.val().length > max) { //textarea的文本长度大于maxlength 
				area.val(area.val().substr(0, max)); //截断textarea的文本重新赋值 
			}
			var sheng = max - area.val().length;
			$("#lsheng").html(sheng);
		}
	});
});
function wxMenuaddmenu(){
	var addHtmlMenu1='<li class="menuOne">'+
						'<a class="menua" id="'
	var addHtmlMenu2='" href="javascript:;">'+
						'菜单名称'+
						'</a>'+
						'<input type=\"hidden\" class=\"data\"  value=\'{\"menuName\":\"菜单名称\",\"menuLevel\":\"1\",\"menuType\":\"click\",\"appid\":\"'+
						$('#appid').val()+
						'\"}\'>'+
						'<ul class="menuChildCon">'+
						'<li class="menuFour">'+
							'<a class="menua" title="最多添加五个子菜单" href="javascript:;">'+
								'<i class="fa fa-plus"></i>'+
							'</a>'+
						'</li>'+
					'</ul>'+
					'</li>';
	var addHtmlMenuChild1='<li class="menuThree">'+
							'<a class="menua" id="';
	var addHtmlMenuChild2='" href="javascript:;">菜单名称</a>'+
							'<input type=\"hidden\" class=\"data\" value=\'{\"menuName\":\"菜单名称\",\"menuType\":\"click\",\"msgType\":\"4\",\"menuLevel\":\"2\",\"msgId\":\"\",\"menuUrl\":\"\",\"appid\":\"'+
							$('#appid').val()+
							'\"}\'></li>';
	//增加菜单
	$(".menuBox").on("click",".menuTwo",function(){
		$("#box-del").prev(".brarybox").remove();
		$("#box-del").remove();
		$(".media-cover").show();
		$("#textmsgId").val('');
		$('#menuUrl').val('');
		var lis=$(this).parent(".menuBox").children("li").length;
		var lisobj=$(this).parent(".menuBox").children(".menuOne");
		if(lis<=3){
			if(lis==3){
				$(this).hide();
			}
			lisobj.children(".new").slideUp(300);
			lisobj.children(".menuChildCon").removeClass("new");
			$('#num').val("wxm"+(lis-1));
			$(this).before(addHtmlMenu1+"wxm"+(lis-1)+addHtmlMenu2);
			//var jsonval = $.parseJSON($(this).parent().children("li").eq(1).children("a").next("input").val());
			$("#menuOneName").val("菜单名称");
			//需要填详细信息
			$(".infoConTwo").hide();
			$(".infoConOne").show();
			$(this).parent(".menuBox").children(".menuOne").children(".new").slideDown(300);
			$('.frm-group span:eq(0)').trigger("click");
			$('.frm-title ul li:eq(1)').trigger("click");
		}
	});
	//增加子菜单
	$(".menuBox").on("click",".menuFour",function(){
		$("#box-del").prev(".brarybox").remove();
		$("#box-del").remove();
		$(".media-cover").show();
		$("#textmsgId").val('');
		$('#menuUrl').val('');
		//清楚一级菜单数据
		var num = $(this).parent().parent().children().attr('id');
		var jsonval = $.parseJSON($('#'+num).next("input").val());
		jsonval["menuType"]="click";
		jsonval["msgType"]="";
		jsonval["msgId"]="";
		jsonval["menuUrl"]="";
		$('#'+num).next("input").val(JSON.stringify(jsonval));
		var lics=$(this).parent(".menuChildCon").children("li").length;
		var id=$(this).parent().parent().children().attr('id');
		if(lics<=5){
			$('#').val('');
			if(lics==5){
				$(this).hide();
			}
			$(this).before(addHtmlMenuChild1+id+(lics-1)+addHtmlMenuChild2);
			//需要填详细信息
			$(".infoConTwo").hide();
			var jsonval = $.parseJSON($(this).parent().children("li").eq(-2).children("a").next("input").val());
			$('#num').val(id+(lics-1));
			for(var key in jsonval){
		    	if(key!="msgId"&&key!="menuid"){
		    		$("#"+key).val(jsonval[key]);
		    	}else if(key=="msgId"){
		    		if(jsonval["msgType"]=="0"){
		    			$("#image"+key).val('');
		    			$("#text"+key).val(jsonval[key]);
		    		}else if(jsonval["msgType"]=="4"){
		    			$("#text"+key).val('');
		    			$("#image"+key).val(jsonval[key]);
		    		}
		    	}
		    	if(key=="menuType"){
		    		if(jsonval[key]=="click"){
		    			$('.frm-group span:eq(0)').trigger("click");
		    		}else if(jsonval[key]=="view"){
		    			$('.frm-group span:eq(1)').trigger("click");
		    		}
		    	}
		    	if(key=="msgType"){
		    		if(jsonval[key]=="0"){
		    			$('.frm-title ul li:eq(1)').trigger("click");
		    		}else if(jsonval[key]=="4"){
		    			$('.frm-title ul li:eq(0)').trigger("click");
		    		}
		    	}
		    }
			$(".infoConOne").show();
			$($(this).parent().children("li").eq(-2).children(".menua")).trigger("click");
		}
	});
	//一级菜单点击事件
	$(".menuBox").on("click",".menuOne>.menua",function(){
		var ishide = $(this).parent(".menuOne").children(".menuChildCon").css("display");
		$(this).css("color","#00c7ff");
		$(this).parent(".menuOne").siblings().children(".menua").css("color","#999");
		$(".menuThree .menua").css("color","#999");
		if(ishide=="none"){
			//显示唯一对应一级菜单下的子菜单
			$(".menuChildCon").hide();
			$(this).parent(".menuOne").children(".menuChildCon").slideDown(300);
		}else{
			//$(this).parent(".menuOne").children(".menuChildCon").slideUp(300);
		}
		var jsonval = $.parseJSON($(this).next("input").val());
		$('#num').val(this.id);
		var conlis = $(this).parent(".menuOne").children(".menuChildCon").children(".menuThree").length;
		if(conlis>0){
			//只需要填标题
			$(".infoConOne").hide();
			for(var key in jsonval){
				if(key!="menuid"){
					if(key=="menuName"){
						$("#menuOneName").val(jsonval[key]);
					}else{
						$("#"+key).val(jsonval[key]);
					}
				}
		    }
			$(".infoConTwo").show();
		}else{
			//需要填详细信息
			$(".infoConTwo").hide();
			for(var key in jsonval){
				if(key!="msgId"&&key!="menuid"){
					$("#"+key).val(jsonval[key]);
				}else if(key=="msgId"){
					$("#box-del").prev(".brarybox").remove();
					$("#box-del").remove();
					$(".media-cover").show();
		    		$("#text"+key).val('');
		    		$('#menuUrl').val('');
		    		if(jsonval["msgType"]=="0"){
		    			$("#text"+key).val(jsonval[key]);
		    			//删除图文框内容
		    			$("#box-del").prev(".brarybox").remove();
		    			$("#box-del").remove();
//		    			$(".frm-cover").show();	
		    			$(".media-cover").show();
		    		}else if(jsonval["msgType"]=="4"){
		    			$("#image"+key).val(jsonval[key]);
		    			msgid=jsonval[key];
		    			$.ax({
							url: $('#ctxPath').val() + "/menu/selectMaterialByMsgid",	
							data:{"appid":$("#appid").val(),"msgid":msgid},
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
												htmlData = htmlData + '<div><img src='+$('#ctxPath').val()+'/material/show?url=' + matDetail[index].thumbMediaUrl + '>';
												htmlData = htmlData + '<span>' + matDetail[index].materialTitle + '</span></div>';
											} else {
												// 除第一个以外的素材明细
												htmlData = htmlData + '<div class="brary-template"><ul>';
												htmlData = htmlData + '<li class="brary-template-t">';
												htmlData = htmlData + '<span>' + matDetail[index].materialTitle + '</span>';
												htmlData = htmlData + '<i><img src='+$('#ctxPath').val()	+'/material/show?url=' + matDetail[index].thumbMediaUrl + '></i>';
												htmlData = htmlData + '</li></ul></div>';
											}
										});
										htmlData = htmlData + '<div class="braryboxbg" onclick="braryboxbgclick(this);"><span><i class="icon-ok okTag" id="tag1"></i></span></div>';
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
					        	$("#errtextmsg").text("网络异常！");
								$('#myModal-errmsg').modal('show');
					        }							
						})
		    		}
		    	}
				if(key=="menuType"){
		    		if(jsonval[key]=="click"){
		    			$('.frm-group span:eq(0)').trigger("click");
		    		}else if(jsonval[key]=="view"){
		    			$('.frm-group span:eq(1)').trigger("click");
		    		}
		    	}	
		    	if(key=="msgType"){
		    		if(jsonval[key]=="0"){
		    			$('.frm-title ul li:eq(1)').trigger("click");
		    		}else if(jsonval[key]=="4"){
		    			$('.frm-title ul li:eq(0)').trigger("click");
		    		}
		    	}
		    }
			$(".infoConOne").show();
		}
	});
	//子菜单点击事件
	$(".menuBox").on("click",".menuThree>.menua",function(){
		//需要填详细信息
		$(".infoConTwo").hide();
		$(this).css("color","#00c7ff");
		$(this).parent(".menuThree").siblings().children(".menua").css("color","#999");
		$(".menuOne").children(".menua").css("color","#999")
		var jsonval = $.parseJSON($(this).next("input").val());
		//if(this.id!=$('#num').val()){
			$('#num').val(this.id);
			 var msgid = "";
			 for(var key in jsonval){
			    	if(key!="msgId"&&key!="menuid"){
			    		$("#"+key).val(jsonval[key]);
			    	}else if(key=="msgId"){
			    		$("#box-del").prev(".brarybox").remove();
			    		$("#box-del").remove();
			    		$(".media-cover").show();
			    		$("#text"+key).val('');
			    		$('#menuUrl').val('');
			    		if(jsonval["msgType"]=="0"){
			    			$("#text"+key).val(jsonval[key]);
			    			//删除图文框内容
			    			$("#box-del").prev(".brarybox").remove();
			    			$("#box-del").remove();
//			    			$(".frm-cover").show();	
			    			$(".media-cover").show();
			    		}else if(jsonval["msgType"]=="4"){
			    			$("#image"+key).val(jsonval[key]);
			    			msgid=jsonval[key];
			    			$.ax({
								url: $('#ctxPath').val() + "/menu/selectMaterialByMsgid",	
								data:{"appid":$("#appid").val(),"msgid":msgid},
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
													htmlData = htmlData + '<div><img src='+$('#ctxPath').val()+'/material/show?url=' + matDetail[index].thumbMediaUrl + '>';
													htmlData = htmlData + '<span>' + matDetail[index].materialTitle + '</span></div>';
												} else {
													// 除第一个以外的素材明细
													htmlData = htmlData + '<div class="brary-template"><ul>';
													htmlData = htmlData + '<li class="brary-template-t">';
													htmlData = htmlData + '<span>' + matDetail[index].materialTitle + '</span>';
													htmlData = htmlData + '<i><img src='+$('#ctxPath').val()+'/material/show?url=' + matDetail[index].thumbMediaUrl + '></i>';
													htmlData = htmlData + '</li></ul></div>';
												}
											});
											htmlData = htmlData + '<div class="braryboxbg" onclick="braryboxbgclick(this);"><span><i class="icon-ok okTag" id="tag1"></i></span></div>';
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
						            $("#errtextmsg").text("网络异常！");
									$('#myModal-errmsg').modal('show');
						        }								
							})
			    		}
			    	}
			    	if(key=="menuType"){
			    		if(jsonval[key]=="click"){
			    			$('.frm-group span:eq(0)').trigger("click");
			    		}else if(jsonval[key]=="view"){
			    			$('.frm-group span:eq(1)').trigger("click");
			    		}
			    	}
			    	if(key=="msgType"){
			    		if(jsonval[key]=="0"){
			    			$('.frm-title ul li:eq(1)').trigger("click");
			    		}else if(jsonval[key]=="4"){
			    			$('.frm-title ul li:eq(0)').trigger("click");
			    		}
			    	}
			    	
			    }
		//}
		$(".infoConOne").show();
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
			var jsonval = $.parseJSON($('#'+$('#num').val()).next("input").val());
			jsonval["menuType"]="click";
			jsonval["msgType"]="4";
			jsonval["msgId"]=imageid;
			jsonval["menuUrl"]="";
			jsonval["menuName"]=$('#menuName').val();
			$('#'+$('#num').val()).next("input").val(JSON.stringify(jsonval));
			$('#menuUrl').val('');
			$('#textmsgId').val('');
			addbox.find(".braryboxbg").replaceWith("");
			$(".source-pick").append(addbox);
			$(".source-pick").append(del);	
			$(".close").click();
			$(".media-cover").hide();
		}
	});
	//选择文本消息
	$('#textmsgId').change(function(){
		var jsonval = $.parseJSON($('#'+$('#num').val()).next("input").val());
		jsonval["menuType"]="click";
		jsonval["msgType"]="0";
		jsonval["msgId"]=$('#textmsgId').val();
		jsonval["menuUrl"]="";
		$("#box-del").prev(".brarybox").remove();
		$("#box-del").remove();
		$(".media-cover").show();
		$('#menuUrl').val('');
		$('#'+$('#num').val()).next("input").val(JSON.stringify(jsonval));
	});
	//链接消息
	$('#menuUrl').change(function(){
		var jsonval = $.parseJSON($('#'+$('#num').val()).next("input").val());
		jsonval["menuType"]="view";
		jsonval["msgType"]="";
		jsonval["msgId"]="";
		jsonval["menuUrl"]=$('#menuUrl').val();
		$("#box-del").prev(".brarybox").remove();
		$("#box-del").remove();
		$(".media-cover").show();
		$("#textmsgId").val('');
		$('#'+$('#num').val()).next("input").val(JSON.stringify(jsonval));
	});
	//删除图文消息
	$(".source-pick").on("click","#box-del",function(){
		var jsonval = $.parseJSON($('#'+$('#num').val()).next("input").val());
		var e = $(".braryboxbg").not(":hidden");
		var imageid = e.parent(".brarybox").children('input').val();
		jsonval["msgId"]=imageid;
		$('#'+$('#num').val()).next("input").val(JSON.stringify(jsonval));
		$("#box-del").prev(".brarybox").remove();
		$("#box-del").remove();
//		$(".frm-cover").show();	
		$(".media-cover").show();
	});	
	//保存并发布
	$(".frmbut-release").on("click",".btn-primary",function(){
		var data="[";
		$(".data").each(function(i){
			data=data+$(this).val()+",";
		});
		data = data.substr(0,data.length-1)+"]";
		$.ax({
			url: $('#ctxPath').val()+"/menu/saveAndRelease",
			data:{"data":data},
			successfn: function(result){
				if(result.success){
					$("#textmsg").text(result.msg);
					$('#myModal-confirm').modal('show');
				}else{
					$("#errtextmsg").text(result.msg);
					$('#myModal-errmsg').modal('show');
				}
			},
			errorfn : function(){
				$("#errtextmsg").text("网络异常!");
				$('#myModal-errmsg').modal('show');
			}	
		}); 
	});
}
function wxMenuselect() {
	$(".user-tit").click(function(e) {
		e.stopPropagation();
		$(this).siblings().slideDown(200);
	});
	$(document).click(function() {
		$(".user-list").slideUp(200);
	});
	$(".user-list>li").click(function() {
		var thisval = $(this).text();
		$(this).parent(".user-list").siblings(".user-tit").text(thisval);
	});
}
function wxMenutabs() {
	$(".frm-group span").click(function() {
				var thisIndex = $(this).parent(".frm-group").children("span").index(this);
				$(this).addClass('cru').siblings().removeClass('cru');
				$(".frm-centent").hide();
				$(".frm-centent:eq(" + thisIndex + ")").slideDown(0);
			});
	$(".frm-title ul li").click(function() {
				var thisIndex = $(this).parent("ul").children("li").index(this);
				$(this).addClass('cru').siblings().removeClass('cru');
				$(".frm-box .frm-tab").hide();
				$(".frm-box .frm-tab:eq(" + thisIndex + ")").slideDown(0);
			});
	//
	$(".mobile-nav li span").click(
			function() {
				$(this).siblings(".mobile-nav-list").show();
				$(".mobile-nav li span").not(this).siblings(
						".mobile-nav-list").hide();
			});
	$(".mobile-nav ul li").eq(0).children("span").trigger("click");
	//	
	$(".brarybox").mousemove(function() {
		$(this).children(".braryboxbg").show()
	});
	$(".brarybox").mouseout(function() {
		$(this).children(".braryboxbg").hide()
	});
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
}
