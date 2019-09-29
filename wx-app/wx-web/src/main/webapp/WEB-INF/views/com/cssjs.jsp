<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String v = "201509171169";
	request.setAttribute("v",v);
%>
<!-- css -->
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/bootstrap.min.css?v=${v}'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/font-awesome.min.css?v=${v}'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/style.css?v=${v}'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/css/main.css?v=${v}'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/date/mobiscroll_002.css?v=${v}'/>" />
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/resources/date/mobiscroll_003.css?v=${v}'/>" />
<!-- js -->
<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.11.1.min.js?v=${v}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.validate.min.js?v=${v}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/additional-methods.js?v=${v}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/messages_zh.min.js?v=${v}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.min.js?v=${v}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.divbox.js?v=${v}'/>" ></script>
<script type="text/javascript" src="<c:url value='/resources/js/area.js?v=${v}'/>" ></script>
<script type="text/javascript" src="<c:url value='http://res.wx.qq.com/open/js/jweixin-1.0.0.js?v=${v}'/>" ></script>

<!-- 日期 -->
<script type="text/javascript" src="<c:url value='/resources/date/mobiscroll_002.js?v=${v}'/>" ></script>
<script type="text/javascript" src="<c:url value='/resources/date/mobiscroll_004.js?v=${v}'/>" ></script>
<script type="text/javascript" src="<c:url value='/resources/date/mobiscroll.js?v=${v}'/>" ></script>
<script type="text/javascript" src="<c:url value='/resources/date/mobiscroll_003.js?v=${v}'/>" ></script>


<script type="text/javascript">
//设置表单验证默认值
	$.validator.setDefaults({
		errorElement: 'span', //default input error message container
        errorClass: 'help-block', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        //ignore: "",  // validate all fields including form hidden input
		highlight: function (element) { // hightlight error inputs
            $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
        },
		unhighlight: function (element) { // revert the change done by hightlight
            $(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
        },
		success: function (label) {
            label.remove();
        },
        onkeyup:function(element){$(element).valid();},
        onfocusout:function(element){$(element).valid();}
	});
	var appid = '${jsApi.appid}';
	var timestamp = '${jsApi.timestamp}';
	var nonceStr = '${jsApi.noncestr}';
	var signature = '${jsApi.signature}';
	var link = '${jsApi.link}';
	var imgUrl = '${jsApi.imgUrl}';
	$(function() {
		wx.config({
			debug : false,
			appId : appid,
			timestamp : timestamp,
			nonceStr : nonceStr,
			signature : signature,
			jsApiList : [ 'hideOptionMenu' ]
		});
		wx.ready(function() {
			wx.hideOptionMenu();
		});
	});
</script>
</script>