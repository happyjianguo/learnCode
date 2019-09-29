<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<title>收单系统管理平台—内容页</title>
 	<style type="text/css">
        body { font-family:Lucida Sans, Lucida Sans Unicode, Arial, Sans-Serif;  margin:0px auto;}
        iframe { border: 0 px solid ;}
        #tabs { margin:0px; padding:0px; list-style:none; overflow:hidden; }
        #tabs li {text-decoration:none; float:left; display:block; padding-left:4px;padding-right:4px;padding-top:2px;padding-bottom:2px; margin-right:2px;}
        #tabs li a {  text-decoration:none;font-size:14px;color: graytext;font-weight: bold; }
        #tabs li a:HOVER { color: #1275D2; }
        #tabs li.current { text-decoration:none;background-color:#FFF;border-bottom: 1px solid #1275D2;}
        #tabs li.current a {text-decoration:none;font-size:14px; font-weight: bold; color: #1275D2;}
       	#tabs li.current a:HOVER {text-decoration:none;font-size:14px; font-weight: bold; color: #1275D2;}
        #tabs li a.remove { text-decoration:none;margin-left:0px;padding-left:4px;font-weight: bold;}
        #content {margin-left:0px;}
        #content div { margin: 0; padding:0px 0px 0px 0px;}
        #main { width:100%;height:100%; margin:0px auto;  margin-top:0px; -moz-border-radius:0px;  -webkit-border-radius:0px; padding:0px;}
        #wrapper{ float:left; margin:0 0 0 0; width:100%;height:100%; margin-top:0px;}
    </style>
    <script type="text/javascript" src="../common/js/jquery-1.4.min.js" ></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#documents a").click(function() {
                addTab($(this));
            });
            $('#tabs a.tab').live('click', function() {
                // Get the tab name
                var contentname = $(this).attr("id") + "_content";
                // hide all other tabs
                $("#content div").hide();
                $("#tabs li").removeClass("current");
                // show current tab
                $("#" + contentname).show();
                $(this).parent().addClass("current");
            });
            $('#tabs a.remove').live('click', function() {
                // Get the tab name
                var tabid = $(this).parent().find(".tab").attr("id");
                // remove tab and related content
                var contentname = tabid + "_content";
                $("#" + contentname).remove();
                $(this).parent().remove();
                // if there is no current tab and if there are still tabs left, show the first one
                if ($("#tabs li.current").length == 0 && $("#tabs li").length > 0) {
                    // find the first tab    
                    var firsttab = $("#tabs li:last-child");
                    firsttab.addClass("current");
                    // get its link name and show related content
                    var firsttabid = $(firsttab).find("a.tab").attr("id");
                    $("#" + firsttabid + "_content").show();
                }
            });
        });
        function addTab(link) {
            // If tab already exist in the list, return
            if ($("#" + $(link).attr("rel")).length != 0)
                return;
            // hide other tabs
            $("#tabs li").removeClass("current");
            $("#content p").hide();
            // add new tab and related content
            $("#tabs").append("<li class='current'><a class='tab' id='" +
                $(link).attr("rel") + "' href='#'>" + $(link).html() + 
                "</a><a href='#' class='remove'>&nbsp;X</a></li>");
            $("#content").append("<p id='" + $(link).attr("rel") + "_content'>" + 
                $(link).attr("title") + "</p>");
            // set the newly added tab as current
            $("#" + $(link).attr("rel") + "_content").show();
        }
        function addTab(name,id,url) {
            // If tab already exist in the list, return
            if ($("#" + id).length != 0){
                $("#tabs li").removeClass("current");
                $("#" + id).show();
                $("#" + id).parent().addClass("current");
                $("#content div").hide();
            	$("#" + id + "_content").show();
                return;
            }
            // max contrl
            if($(".tab").length >= 8){
				alert("窗口已打开最大个数！");
				return;
            }
            // hide other tabs
            $("#tabs li").removeClass("current");
            $("#content div").hide();
            // add new tab and related content
            $("#tabs").append("<li class='current'><a class='tab' id='" +
                id + "' href='#'>" + name + 
                "</a><a href='#' class='remove'>x</a></li>");
            $("#content").append("<div id='" + id + "_content'><iframe frameborder='0' src='<%=path%>/"+url+"' width='100%' height='95%' >Your browser does not support iframes.</iframe></div>");
			// add hover
            $("#tabs li").hover(
          		  function () {
            		$(this).addClass("hover");
          		  },
          		  function () {
          		    $(this).removeClass("hover");
          		  }
          		);
            // set the newly added tab as current
            $("#" + id + "_content").show();
        }
    </script>
</head>

<body style="overflow: visible">
    <div id="main">
	    <div>
	        <ul id="tabs">
	            <!-- Tabs go here -->
	            <li class='current'><a class='tab' id='hello' href='#'>首页</a></li>
	        </ul>
	    </div>
	    <div id="wrapper">
	        <div id="content">
	            <!-- Tab content goes here -->
	            <div id='hello_content'><iframe frameborder="0" src='' width='100%' height='95%' >Your browser does not support iframes.</iframe></div>
	        </div>
	    </div>
    </div>
</body>
</html>
