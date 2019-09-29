<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String jsv = "201602011299";
	request.setAttribute("jsv", jsv);
%>
<!-- js -->
<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.12.1.min.js?v=${jsv}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.min.js?v=${jsv}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/common.js?v=${jsv}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/ajax.js?v=${jsv}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.validate.min.js?v=${jsv}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/additional-methods.js?v=${jsv}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/messages_zh.min.js?v=${jsv}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.dataTables.min.js?v=${jsv}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/dataTables.bootstrap.min.js?v=${jsv}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap-select.min.js?v=${jsv}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/defaults-zh_CN.min.js?v=${jsv}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap-datepicker.min.js?v=${jsv}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap-datepicker.zh-CN.min.js?v=${jsv}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/fileinput.min.js?v=${jsv}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/fileinput_locale_zh.js?v=${jsv}'/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/resources/js/ajaxfileupload.js?v=${jsv}'/>"></script> --%>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.ztree.core.js?v=${jsv}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.ztree.excheck.js?v=${jsv}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/echarts/echarts-all.js?v=${jsv}'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap-datetimepicker.min.js?v=${jsv}'/>"></script> 
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap-datetimepicker.zh-CN.min.js?v=${jsv}'/>"></script> 

<script type="text/javascript">
	$.extend($.fn.dataTable.defaults, {
		"searching" : false,//搜索框是否打开
		"bDestroy" : true,//用于当要在同一个元素上履行新的dataTable绑按时，将之前的那个数据对象清除掉，换以新的对象设置
		"bStateSave" : false, // save datatable state(pagination, sort, etc) in cookie.
		"processing" : true,
		"ordering" : false, //排序
		"bFilter" : true, // 显示搜索
		"bLengthChange" : true, // 显示每页长度
		"order" : [],
		"lengthMenu" : [ [ 15, 20, 25, 999999999 ], [ "15", "20", "25", "全部" ] ],
		"pageLength":15,//定义初始的页长
		"pagingType":"full_numbers",//定义翻页组件的样式
		"language" : {
			"sProcessing" : "处理中...",
			"sLengthMenu" : "显示 _MENU_ 项结果",
			"sZeroRecords" : "没有匹配结果",
			"sInfo" : "共 _TOTAL_ 条记录，当前第 _PAGE_ 页，共 _PAGES_ 页",
			"sInfoEmpty" : "共 0 条记录",
			"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
			"sInfoPostFix" : "",
			"sSearch" : "搜索:",
			"sUrl" : "",
			"sEmptyTable" : "表中数据为空",
			"sLoadingRecords" : "载入中...",
			"sInfoThousands" : ",",
			"oPaginate" : {
				"sFirst" : "<<",
				"sPrevious" : "<",
			"sNext":     ">",
				"sLast" : ">>"
			},
			"oAria" : {
				"sSortAscending" : ": 以升序排列此列",
				"sSortDescending" : ": 以降序排列此列"
			}
		}
	});
</script>
<script type="text/javascript">
	//设置表单验证默认值
	$.validator.setDefaults({
		errorElement : 'span', //default input error message container
		errorClass : 'help-block', // default input error message class
		focusInvalid : false, // do not focus the last invalid input
		//ignore: "",  // validate all fields including form hidden input
		errorPlacement : function(error, element) {
			/*OBSERVATION (2): note how selection is on the class "selectpicker"*/
			if (element.hasClass('selectpicker')) {
				error.insertAfter('.bootstrap-select');
			} else if (element.parent().hasClass('input-group')) {
				error.insertAfter(element.parent());
			} else {
				error.insertAfter(element);
			}
		},
		highlight : function(element) { // hightlight error inputs
			$(element).closest('.form-group').addClass('has-error'); // set error class to the control group
		},
		unhighlight : function(element) { // revert the change done by hightlight
			$(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
		},
		success : function(label) {
			label.remove();
		},
		onkeyup : function(element) {
			$(element).valid();
		},
		onfocusout : function(element) {
			$(element).valid();
		},
		invalidHandler: function(event, validator) {
		    var errors = validator.numberOfInvalids();
		    if (errors) {
		    	//按钮置为正常
		    	$("button").prop("disabled", false);
		    } else {
		    	
		    }
		 }
	});
</script>