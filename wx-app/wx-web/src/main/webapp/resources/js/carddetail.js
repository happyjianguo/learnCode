$(function() {
//	var currYear = (new Date()).getFullYear();	
//	var opt={};
//	opt.date = {preset : 'date'};
//	opt.datetime = {preset : 'datetime'};
//	opt.time = {preset : 'time'};
//	opt.defaultv = {
//		theme: 'android-ics light', //皮肤样式
//        display: 'modal', //显示方式 
//        mode: 'scroller', //日期选择模式
//		dateFormat: 'yyyy-mm-dd',
//		lang: 'zh',
//		showNow: currYear,
//		nowText: "今天",
//        startYear: currYear - 10, //开始年份
//        endYear: currYear + 10 //结束年份
//	};	
//  	$(".appDate").mobiscroll($.extend(opt['date'], opt['defaultv']));	
  	/*var myDate= new Date;
	var str=""+myDate.getFullYear()+"-";
    str +=(myDate.getMonth()+1)+"-";
    str +=myDate.getDate();
    $(".appDate").val(str);  */
  	
//	$(".current").click(function() {
//		$(".select").slideToggle();
//	});
//	$(".select li").click(function() {
//		$("input[name='cardno']").attr("value", $(this).html());
//		$(".card-selecter li").remove();
//		$(this).clone().prependTo(".card-selecter");
//		$(".select").hide();
//	});
	
	$("#search").click(function(){
		if (!($("select[name='cardno']").val())) {
			info("请先选择卡号！", "确定");
			return;
		}
		var newDate = new Date();
		var sta = $("input[name='startDate']").val();
		var end = $("input[name='endDate']").val();
		
		if(sta==""){
			info("请先选择开始日期", "确定");
			return;
		}
		if(end==""){
			info("请先选择结束日期", "确定");
			return;
		}
		
		var startData = new Date(sta);
		var endData = new Date(end);
		
		
		if (startData > newDate) {
			info("开始日期不能大于当前日期！", "确定");
			return;
		}
		
		if (endData > newDate) {
			info("结束日期不能大于当前日期！", "确定");
			return;
		}
		
		if (startData > endData) {
			info("开始日期必须小于结束日期！", "确定");
			return;
		}
		$("#eform").submit();
	})
	closeDiv();
	//消息提示生成方法
	function info(info,btnval) {
		$("#info").text(info);
		$("#res").val(btnval);
		$("#end").OpenDiv();
		setTimeout(function() {
			$("#end").CloseDiv();
		}, 2000);
	}
	//关闭消息方法
	function closeDiv() {
		$(".closebtn").click(function() {
			var id = $(this).attr("attr-id");
			$("#" + id).CloseDiv();
			return false;
		});
	}
})