<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_CN" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_CN" class="ie9 no-js"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="zh_CN">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>金融场景化营销平台 - <sitemesh:write property='title' /></title>
<meta name="_csrf" content="${_csrf.token}"/>
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<link rel="shortcut icon" type="image/x-icon" href="${ctxPath}/resources/img/favicon.ico"/>
<%@ include file="/WEB-INF/views/common/css.jsp"%>
<script type="text/javascript">
// 	window.history.forward(-1);
</script>
<!--[if lt IE 9]>
		<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
<!-- <sitemesh:write property='head' /> -->
</head>
<body>
	<div class="centent">
		<div class="header-bg">
			<header class="header">
				<div class="logo">
					<a href="${ctxPath}/"><img src="${ctxPath}/resources/img/logo.png"></a>
				</div>
				<div class="head-news">
	<!-- 				<i class="fa fa-volume-down"></i>系统消息：后台功能已更新，欢迎使用！ -->
				<ul class="nav nav-pills" role="tablist">
	              <li role="presentation"><a href="#"><span class="n-i n-i1"></span>首页</a></li>
	              <li role="presentation"><a href="#"><span class="n-i n-i2"></span>产品服务</a></li>
	              <li role="presentation"><a href="#"><span class="n-i n-i3"></span>案例营销</a></li>
	              <li role="presentation"><a href="#"><span class="n-i n-i4"></span>帮助中心</a></li>
	              <li role="presentation"><a href="#"><span class="n-i n-i5"></span>管理中心</a></li>
	              <li role="presentation"><a href="#"><span class="n-i n-i6"></span>关于我们</a></li>
	            </ul>
	
				</div>
				<div class="head-right">
					<div class="navbar-header">
						<ul class="nav ace-nav">
							<li>
								<%-- onclick="submiturl('${ctxPath}/logout');" --%>
								<a data-toggle="dropdown" href="javascript:;" class="dropdown-toggle" >
	  								<span><img src="${ctxPath}/personalcenter/show"></span> 
									<em>${SPRING_SECURITY_CONTEXT.authentication.principal.cnname}</em>			
	<!-- 								<i class="fa fa-caret-down"></i> -->
									<!-- <i class="fa fa-power-off"></i> -->
								</a>
	 							<ul class="user-menu pull-right dropdown-menu dropdown-caret">
	 								<li> 
	 									<a href="javascript:;" onclick="submiturl('${ctxPath}/personalcenter/init');"> 
	 										<i class="fa fa-key"></i> 
	 										个人信息 
										</a> 
	 									<a href="javascript:;" onclick="submiturl('${ctxPath}/password/init');"> 
	 										<i class="fa fa-key"></i> 
	 										修改密码 
										</a>
										<!-- onclick="submiturl('${ctxPath}/notice/init');" -->
										<a href="javascript:;" data-toggle="modal" data-target="#myModal-notice" > 
	 										<i class="fa fa-key"></i> 
	 										公告消息 
										</a>  
	 									<a href="javascript:;" onclick="submiturl('${ctxPath}/logout');"> 
	 										<i class="fa fa-power-off"></i> 
	 										退出 
										</a> 
	 								</li> 
	 							</ul> 
							</li>
						</ul>					
					</div>
				</div>
		    </header>
	    </div>
		<!--header end-->
		
		<!-- Modal 公告-->
		<div class="modal fade" id="myModal-notice" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">系统公告</h4>
					</div>
					<div class="modal-body">
					   <div class="notice">
					   		<ul id="noticelist">
					   		</ul>
					   </div>
					</div>
					<!-- <div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary">确认</button>
					</div> -->
				</div>
			</div>
		</div>
	    <div class="centainer">
	     	<c:forEach var="menu" items="${SPRING_SECURITY_CONTEXT.authentication.principal.menuList}" varStatus="status">
				<div class="sidebar-menu">
					<ul class="main-menu">
						<c:forEach var="submenu" items="${menu.child}" varStatus="cstatus">
							<c:if test="${empty submenu.child}">
								<c:if test="${submenu.menuurl ne '#'}">
									<li class="${submenu.selected eq '1'? 'open':''}">
										<div class="link">
											<i class="${submenu.icon}" aria-hidden="true"></i>
											<a id="lm${status.index}${cstatus.index}" href="${ctxPath}${submenu.menuurl}">${submenu.cnname}</a>
										</div>
									</li> 
								</c:if>
							</c:if>
							<c:if test="${not empty submenu.child}">
								<li class="${submenu.selected eq '1'? 'open':''}">
									<div class="link">
										<i class="${submenu.icon}" aria-hidden="true"></i>
										<a id="lm${status.index}" href="javascript:;">${submenu.cnname}</a><i class="fa fa-angle-right"></i>
									</div>
									<ul class="submenu" style="display: ${submenu.selected eq '1'? 'block':'none'}">	
										<c:forEach var="sss" items="${submenu.child}" varStatus="cstatus">
											<li class="${sss.selected eq '1'? 'cur':''}">
												<%-- <i class="${sss.icon}" aria-hidden="true"></i> --%>													
												<a id="lm${status.index}${cstatus.index}" href="${ctxPath}${sss.menuurl}"><em></em>${sss.cnname}</a>
											</li> 
										</c:forEach>
									</ul>
								</li> 
							</c:if>
						</c:forEach>						
					</ul>		
			  </div>	
		</c:forEach>	
		<!-- sidebar-menu end-->
		<div class="sidebar-right">
			<sitemesh:write property="body" />
		</div>
	</div>
	<%-- <div class="footer">
		<em>Copyright ©1998-2016 </em>
		<img src="${ctxPath}/resources/img/logo-foot.png">
	</div> --%>
	<!-- 	提示框 -->
		<div class="modal fade" id="myModal-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">提示信息</h4>
					</div>
					<div class="modal-body">
						<div class="modal-prompt deleteAlert">
							<i class="fa fa-check"></i><span id="textmsg"></span>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default deleteCancle"
							data-dismiss="modal">关闭</button>
<!-- 						<button type="button" class="btn btn-primary deleteConfirm delMsgid">确认</button> -->
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="myModal-errmsg" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalerrmsgLabel">提示信息</h4>
					</div>
					<div class="modal-body">
						<div class="modal-prompt deleteAlert">
							<i class="fa fa-exclamation"></i><span id="errtextmsg"></span>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default deleteCancle"
							data-dismiss="modal">关闭</button>
<!-- 						<button type="button" class="btn btn-primary deleteConfirm delMsgid">确认</button> -->
					</div>
				</div>
			</div>
		</div>
		<!-- 加载层 -->
		<div class="loadingbg" style="display:none">
			<div class="web_loading">
				<i class="fa fa-spinner"></i><span>努力加载中...</span>
			</div>
		</div>
		<!-- 提示信息 -->
		<div id="prompt" class="prompt">
			<span id="prompt_text">提示信息</span>
			<i id="prompt_close" class="fa fa-close prompt_close"></i>
		</div>
<%@ include file="/WEB-INF/views/common/js.jsp"%>
<script type="text/javascript">
	
$(function() {
	//屏蔽键盘开始------------------------------------------------
	function disableKey() {
		var src = window.event.srcElement;
        var tag = src.tagName ? src.tagName.toUpperCase() : '';
        var typ = (tag == 'INPUT') ? src.type.toUpperCase() : '';
        var isTextArea = (tag == 'TEXTAREA');
        var isTextField = ((tag == 'INPUT') && ((typ == 'TEXT')|| (typ == 'PASSWORD')|| (typ == 'SEARCH')));
        var isText = isTextField || isTextArea;
        var disabled = isText ? src.disabled : false;
        var readOnly = isText ? src.readOnly : false;
		try{
			if (
			(window.event.keyCode==8&&(!isText || disabled || readOnly))//8 退格键
			||(window.event.keyCode==78&&window.event.ctrlKey)//78 Ctrl N
			||window.event.keyCode==116//116 F5 刷新键
			||(window.event.keyCode==82&&window.event.ctrlKey)//82 Ctrl R
			||window.event.keyCode==121//121 shift F10
			||window.event.keyCode==115//115 屏蔽Alt F4
			||(window.event.keyCode==13&&(event.srcElement.type))//编辑Enter键
			||(window.event.keyCode==13&&(event.srcElement.isTextEdit))//编辑Enter键
			){
				window.event.keyCode=0;
				window.event.returnvalue=false;
				return false;
			}
		}catch(ee){}
	}
	//屏蔽键盘结束------------------------------------------------
	//屏蔽键盘
	document.onkeydown=disableKey;
	//主菜单事件
	$('.main-menu').find('.link').on('click', function(e) {
		var $el = $('#main-menu');
		var $this = $(this);
		var $next = $this.next();
		$next.slideToggle();
		$this.parent().siblings().removeClass('open');
		$this.parent().addClass('open');		
		$el.find('.submenu').not($next).slideUp().parent().removeClass('open');
	});
});

//页面高度
/* $(function(){
	var h=$(document).height();
	var top=$(".header").height()+$(".nav-tabs").height();
	$(".main-content").height(h-top-2);
	$(".sidebar-menu").height(h-top-2);
}); */
$(function(){
	//日期选择datepicker(时间范围2015-10-01 ~ 当天)
	$(".form_datetime").datepicker({
		format: "yyyy-mm-dd",
	    todayBtn: "linked",
	    language: "zh-CN",
	    autoclose: true,
	    startDate: "2015-10-01",
	    endDate: "0d",
	    todayHighlight: true
	});
	//日期选择datepicker(时间范围  long long ago ~ After a long time)
	$(".form_datetime_long").datepicker({
		format: "yyyy-mm-dd",
	    todayBtn: "linked",
	    language: "zh-CN",
	    autoclose: true,
	    //startDate: "2015-10-01",
	    //endDate: "2016-10-01",
	   	todayHighlight: true
	});
	//下拉框bootstrap-select
	$('.selectpicker').selectpicker({
		liveSearch:true,
		container:"body",
		size:10
	});
	$('[data-toggle="popover"]').popover();
	
	//页面提示信息
	function promptClose(){
		$("#prompt").fadeOut("slow");
	};
	//成功提示信息
	if("${resultmsg.resultflag}"==="1"){
		$("#prompt_text").text("${resultmsg.msg}");
		$("#prompt").addClass("prompt");
		$("#prompt").show();
	}
	//错误提示信息
	if("${errorCode}"!="" && "${errorCode}"!="000000"){
		if($("#errorcm").length > 0){
			return;
		}
		$("#prompt_text").text("${errorMsg}");
		$("#prompt").addClass("prompt_t");
		$("#prompt").show();
	}
	$("#prompt_close").click(promptClose);
	window.setTimeout(promptClose, 3000);
	// 请求账户余额信息
	/* $.ajax({
		url:"${ctxPath}/currency/show",
		dataType:"JSON",
		success: function(data, textStatus, jqXHR){
			$(".media-heading").text("账户余额: "+data.obj.currentMoney);
			$(".media-heading").next("span").text("已充值总数: "+data.obj.totalMoney);
		}
	}); */
	//查询公告
	$.ajax({
		url:"${ctxPath}/notice/show",
		dataType:"JSON",
		success: function(data, textStatus, jqXHR){
			$(data.obj).each(function(index){
				$("#noticelist").append("<li id='"+$(this).attr("noticeid")+"'>"+addSpace($(this).attr("noticename"),$(this).attr("noticevalue"))+"</li>");
			})
		}
	});
	
	function addSpace(name,value){
		var size = lenFor(name);
		if(size<10){
			if(size%2 == 1 ){
				for(var i = 0 ;i<10-size ;i++){
					if(i%2 == 1){
						name += '&nbsp;';
					}
					
				}
			}else if(size %2 == 0){
				for(var i = 0 ;i<10-size ;i++){
					if(i%2 == 0){
						name += '&nbsp;';
					}
				}
			}
			
			name += "--->"+value;
		}
		return name;
		
	}
	 function lenFor(str){

		    var byteLen=0,len=str.length;

		    if(str){

		        for(var i=0; i<len; i++){

		            if(/^[\u4e00-\u9fa5]+$/.test(str.charAt(i))){

		                byteLen += 2;

		            }

		            else{

		                byteLen++;

		            }

		        }

		        return byteLen;

		    }

		    else{

		        return 0;

		    }

		}
	
	//form提交时，禁用所有button
	$("form").not(".btnenable").on("submit",function(event){
		//$("button").prop("disabled", true);
		return true;
	});
	
	//控制菜单hover
	$(".submenu li").mouseover(function(){
		$(this).find("em").not(".cur em").css("background","#f9b705");
	});
	$(".submenu li").mouseout(function(){
		$(this).find("em").not(".cur em").css("background","#afacac");
	});
});	
</script>
<!-- appscript -->
<sitemesh:write property="appscript"/>
</body>
</html>