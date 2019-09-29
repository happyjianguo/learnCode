$(function(){
	//刷新分组组别
	refreshSelected("init");
	//获取分组组别
	function refreshSelected(selType){
		//初始化页面的当前分组下拉列表
		$.ax({
			url: $("#ctx").val() + "/userGroup/ajax/getGroupList",
			data:{"groupSelType":selType,"appid":$('#appid').val()},
			successfn: function(data){
				var json = eval(data); //数组  
				$.each(json, function (index, item) {  
		            //循环获取数据
		            var groupId = json[index].groupId;  
		            var groupName = json[index].groupName;  
		            $('#initGroupName').append("<option value=" + groupId + ">" + groupName + "</option>");
		            $("#initGroupName").val("allUser");
		        });
				$('.selectpicker').selectpicker('refresh');
			},
	        errorfn : function(){
	        	$("#errtextmsg").text("网络异常!");
	        	$('#myModal-errmsg').modal('show');
	        }			
		});
	}
	var msgData;
	var appid = $("#appid").val();
	var msgType = "4";
	var msgContents;
	var materialData;
	//点击图文获取图文消息列表
	$(".selectNews").click(function (){
		msgType = "4";
		$("#msgName").empty();
		$("#msgInputText").text("");
		msgContents = '';
	});
	// 选择图文消息
	$(".selectMedia").click(function(){
		$("#mediaModal").modal('show');
		$(".newsData").children().remove();
		$.ax({
			url: $("#ctx").val() + "/menu/selectMaterialByMsgidAndplatformId",	
			data:{"appid":appid},
			successfn: function(data){
				if (data.result == 1) {
					materialData = eval(data.obj);
					$.each(materialData, function (index, item) {
						var htmlData = "";
						//获取图文明细
						var matDetail = materialData[index].detailModels;
						htmlData = htmlData + '<div class="brarybox" onmouseover="mouseover(this);" onmouseout="mouseout(this);">';
						htmlData = htmlData + '<input type="hidden" id="'+materialData[index].mediaId+'" value="' + materialData[index].msgId + '" >';
						htmlData = htmlData + '<dl><dt>' + materialData[index].msgName + '</dt>';
						htmlData = htmlData + '<dd>' + materialData[index].updateTime + '</dd></dl>';
						$.each(matDetail, function (index, item) {
							if (index == 0) {
								//第一个素材明细
								htmlData = htmlData + '<div class="material-img"><img src='+ $('#ctx').val() +'/material/show?url=' + matDetail[index].thumbMediaUrl + '>';
								htmlData = htmlData + '<span>' + matDetail[index].materialTitle + '</span></div>';
							} else {
								// 除第一个以外的素材明细
								htmlData = htmlData + '<div class="brary-template"><ul>';
								htmlData = htmlData + '<li class="brary-template-t">';
								htmlData = htmlData + '<span>' + matDetail[index].materialTitle + '</span>';
								htmlData = htmlData + '<i><img src='+ $('#ctx').val() +'/material/show?url=' + matDetail[index].thumbMediaUrl + '></i>';
								htmlData = htmlData + '</li></ul></div>';
							}
						});
						htmlData = htmlData + '<div class="braryboxbg" onclick="braryboxbgclick(this);"><span><i class="fa fa-check okTag" id="tag1"></i></span></div>';
						htmlData = htmlData + '</div>';
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
	//点击文本获取消息列表
	$(".selectText").click(function (){
		$("#msgName").empty();
		$("#msgInputText").text("");
		$("#box-del").prev(".brarybox").remove();
		$("#box-del").remove();
		$(".media-cover").show();
		msgContents = '';
		msgType = "0";
		/*获取消息列表*/
		$.ax({
			url: $("#ctx").val() + "/resMessage/getmessages",	
			data:{"appid":appid,"msgType":msgType},
			successfn: function(data){
				msgData = eval(data.obj); //数组  
				$('#msgName').append("<option value=''></option>");
				$.each(msgData, function (index, item) {
					//循环获取数据    
		            var msgId = msgData[index].msgId;
		            var msgName = msgData[index].msgName;
		            $('#msgName').append("<option value=" + msgId + ">" + msgName + "</option>");
		        });
				$('#msgName').val("");
				$('.selectpicker').selectpicker('refresh');
			},
	        errorfn : function(){
	        	$("#errtextmsg").text("网络异常!");
	        	$('#myModal-errmsg').modal('show');
	        }			
		});
	});
	// 选择文字消息
	$("#msgName").change(function(){
		var thisId = this.value;
		if (msgData != null) {
			$.each(msgData, function (index, item) {
				if (msgData[index].msgId == thisId) {
					$("#msgInputText").text(msgData[index].content);
					msgContents = msgData[index].content;
				}
			});
		}
	});
	//发送按钮按下
	$("#sendMsgBtn").click(function(){
		//发送消息
		if (msgType == '4') {
			if($(".source-pick input").val() != undefined && $(".source-pick input").val() != ''){
				msgContents = $(".source-pick input").val();
			} else {
				$("#errtextmsg").text("请选择发送的消息！");
				$('#myModal-errmsg').modal('show');
				return;
			}
		} else {
			if (msgContents == undefined || msgContents == '') {
				$("#errtextmsg").text("请选择发送的消息！");
				$('#myModal-errmsg').modal('show');
				return;
			}
		}
		//  群发次数已达上限
		$.ax({
			url: $("#ctx").val() + "/sendMessage/ajax/sendMsg",	
			data:{"appid":appid, "msgType":msgType, "msgContents":msgContents, "groupId":$("#initGroupName").val()},
			successfn: function(data){
				$("#textmsg").empty();
				if (data.success) {
					$("#textmsg").text("消息发送成功！");
					$('#myModal-confirm').modal('show');
				} else {
					$("#errtextmsg").text("群发次数已达上限，消息发送失败！");
					$('#myModal-errmsg').modal('show');
				}
			},
	        errorfn : function(){
	        	$("#errtextmsg").text("网络异常!");
	        	$('#myModal-errmsg').modal('show');
	        }			
		});
	});
	tabs();
	//图文消息和文本消息切换
	function tabs(){
        $(".frm-group span").click(function(){
            var thisIndex = $(this).parent(".frm-group").children("span").index(this);
            $(this).addClass ('cru').siblings().removeClass ('cru');        
            $(".frm-centent").hide();
            $(".frm-centent:eq("+thisIndex+")").slideDown(0);
        });
        $('.frm-group span:eq(0)').trigger("click");
        $(".frm-title ul li").click(function(){
            var thisIndex = $(this).parent("ul").children("li").index(this);
            $(this).addClass ('cru').siblings().removeClass ('cru');        
            $(".frm-box .frm-tab").hide();
            $(".frm-box .frm-tab:eq("+thisIndex+")").slideDown(0);
        });
        $('.frm-title ul li:eq(0)').trigger("click");
        //
        $(".mobile-nav li span").click(function(){
            $(this).siblings(".mobile-nav-list").show();
            $(".mobile-nav li span").not(this).siblings(".mobile-nav-list").hide();     
        });
        $(".mobile-nav ul li").eq(0).children("span").trigger("click"); 
    }
	//从素材库选择图文消息
	$(".brarybtn").click(function(){
		var e = $(".braryboxbg").not(":hidden");
		if(e.length<=0){
			$("#errtextmsg").text("请选择素材！");
			$('#myModal-errmsg').modal('show');
			return;
		}else{
			var del='<span id="box-del">删除</span>';
			var addbox = e.parent(".brarybox");
			addbox.find(".braryboxbg").replaceWith("");
			$(".source-pick").append(addbox);
			$(".source-pick").append(del);	
			$(".close").click();
			$(".media-cover").hide();
		}
	});
	//删除图文消息
	$(".source-pick").on("click","#box-del",function(){
		$("#box-del").prev(".brarybox").remove();
		$("#box-del").remove();
		$(".media-cover").show();
	});	
});
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