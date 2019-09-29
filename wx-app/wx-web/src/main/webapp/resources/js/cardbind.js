$(function(){
	//赋值：
	if ($("#table0 input[name='openid']").val()) {
		selectTag("table0", $("#card").get(0));
		$("#table1 input[name='openid']").val($("#table0 input[name='openid']").val());
	}else if ($("#table1 input[name='openid']").val()) {
		selectTag("table1", $("#credit").get(0));
		$("#table0 input[name='openid']").val($("#table1 input[name='openid']").val());
	};
	
/*	var cardreg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;//身份证正则
	var phonereg=/^1[3|4|5|8][0-9]\d{4,8}$/;//手机号正则
	var namereg=/^[\u4E00-\u9FA5]+$/;//姓名正则
	var mcreg=/^(\d{16}|\d{19})$/;//卡号正则
*/	
	
	var cardreg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)|(^[\d\*]{15}$)|(^[\d\*]{18}$)/;//身份证正则
	var phonereg=/^[0-9\*]{11}$/;//手机号正则
	var namereg=/^[A-Za-z\u4e00-\u9fa5\*]+$/;//姓名正则
	var mcreg=/^[A-Za-z0-9\*]+$/;//卡号正则
	//验证身份证号是否正确
	$(".cardInput").blur(function(){
		if(!cardreg.test($(this).val())&&$(this).val().length>0){
			info("输入的证件号码不合法，请检查！","重新输入");
			$(this).val($(this).val());
		};
	});
	//验证手机号是否正确
	$(".phoneInput").blur(function(){
		if(!phonereg.test($(this).val())&&$(this).val().length>0){
			info("输入的手机号码不合法，请检查！","重新输入");
			$(this).val($(this).val());
		};
	});
	//验证姓名是否正确
	$(".nameInput").blur(function(){
		if((!namereg.test($(this).val()) || $(this).val().length>4 || $(this).val().length<2) &&$(this).val().length>0){
			info("输入的姓名不合法，请检查！","重新输入");
			$(this).val($(this).val());
		};
	});
	//验证卡号是否正确
	$(".mcInput").blur(function(){
		if(!mcreg.test($(this).val())&&$(this).val().length>0){
			info("输入的卡号不合法，请检查！","重新输入");
			$(this).val($(this).val());
		};
	});	
	closeDiv();
	
	/* 获取验证码 end */
	
	//是否开通动帐提醒
	$("input[name='noticestatus']").change(function() {
		var notices = $(this).prop("checked") ? "1" : "0";
		$(this).val(notices);
	});
	//是否同意协议
	//借记卡
	$("#checkbox-1-2").change(function(){
		if ($(this).prop("checked")) {
			$("#table0 .but").removeAttr("disabled");
			$(".but").css("background","#00a2f8");			
		}else{
			$("#table0 .but").attr("disabled","disabled");
			$(".but").css("background","#cccccc");
		}
	});
	//信用卡
	$("#checkbox-1-3").change(function(){
		if ($(this).prop("checked")) {
			$("#table1 .but").removeAttr("disabled");
			$(".but").css("background","#00a2f8");
		}else{
			$("#table1 .but").attr("disabled","disabled");
			$(".but").css("background","#cccccc");
		}
	});
	//借记卡绑定提交
	$("#table0 input[class='but']").click(function() {
		checkedForm("0");
		
	});
	//信用卡绑定提交
	$("#table1 input[class='but']").click(function() {
		checkedForm("1");
	});
	//提交验证
	function checkedForm(obj) {
		var username;
		var idcard;
		var cardnum;
		var phonenum;
		var vercode;
		var form;
		if(obj=="0"){
			//用户名
			username = $("#username").val();
			//证件号码
			idcard = $("#idno").val();
			//卡号
			cardnum =$("#cardno").val();
			//手机号
			phonenum = $("#mobile").val();
			//验证码
			vercode = $("#msgcode").val();
			
			form = $("#table0");
		}else if(obj=="1"){
			
			//用户名
			username = $("#creusername").val();
			//证件号码
			idcard = $("#creidno").val();
			//卡号
			cardnum =$("#crecardno").val();
			//手机号
			phonenum = $("#cremobile").val();
			//验证码
			vercode = $("#cremsgcode").val();
			form = $("#table1");
		}
		
		//用户名不能为空
		if (!username) {
			info("姓名不能为空！", "重新输入");
			return;
		}
		//校验用户名
		if ((!namereg.test(username) || username.length > 4 || username.length < 2)
				&& username.length > 0) {
			info("输入的姓名不合法，请检查！", "重新输入");
			$(this).val($(this).val());
			return;
		}
		;
		//证件号码不能为空
		if (!idcard) {
			info("证件号码不能为空！", "重新输入");
			return;
		}
		//校验证件号码
		if (!cardreg.test(idcard) && idcard.length > 0) {
			info("输入的证件号码不合法，请检查！", "重新输入");
			$(this).val($(this).val());
			return;
		}
		;
		//卡号不能为空
		if (!cardnum) {
			info("卡号不能为空！", "重新输入");
			return;
		}
		//校验卡号
		if (!mcreg.test(cardnum) && cardnum.length > 0) {
			info("输入的卡号不合法，请检查！", "重新输入");
			$(this).val($(this).val());
			return;
		}
		;
		//手机号不能为空
		if (!phonenum) {
			info("手机号码不能为空！", "重新输入");
			return;
		}
		//校验手机号
		if (!phonereg.test(phonenum) && phonenum.length > 0) {
			info("输入的手机号码不合法，请检查！", "重新输入");
			//$(this).val($(this).val());
			return;
		}
		;
		//验证码不能为空
		if (!vercode) {
			info("验证码不能为空！", "重新输入");
			return;
		}
		form.find("#eform").submit();
	}
	//借记卡绑定提交
	$("#table0 input[name='msgbtn']").click(function() {
		getmsgcode("0");
	});
	//信用卡绑定提交
	$("#table1 input[name='msgbtn']").click(function() {
		getmsgcode("1");
	});
	function getmsgcode(obj){
		var username;
		var idcard;
		var cardnum;
		var phonenum;
		var vercode;
		var form;
		if(obj=="0"){
			//用户名
			username = $("#username").val();
			//证件号码
			idcard = $("#idno").val();
			//卡号
			cardnum =$("#cardno").val();
			//手机号
			phonenum = $("#mobile").val();
			
		}else if(obj=="1"){
			
			//用户名
			username = $("#creusername").val();
			//证件号码
			idcard = $("#creidno").val();
			//卡号
			cardnum =$("#crecardno").val();
			//手机号
			phonenum = $("#cremobile").val();
			
		}
		
		//用户名不能为空
		if (!username) {
			info("姓名不能为空！", "重新输入");
			return;
		}
		//校验用户名
		if ((!namereg.test(username) || username.length > 4 || username.length < 2)
				&& username.length > 0) {
			info("输入的姓名不合法，请检查！", "重新输入");
			$(this).val($(this).val());
			return;
		}
		;
		//证件号码不能为空
		if (!idcard) {
			info("证件号码不能为空！", "重新输入");
			return;
		}
		//校验证件号码
		if (!cardreg.test(idcard) && idcard.length > 0) {
			info("输入的证件号码不合法，请检查！", "重新输入");
			$(this).val($(this).val());
			return;
		}
		;
		//卡号不能为空
		if (!cardnum) {
			info("卡号不能为空！", "重新输入");
			return;
		}
		//校验卡号
		if (!mcreg.test(cardnum) && cardnum.length > 0) {
			info("输入的卡号不合法，请检查！", "重新输入");
			$(this).val($(this).val());
			return;
		}
		;
		//手机号不能为空
		if (!phonenum) {
			info("手机号码不能为空！", "重新输入");
			return;
		}
		//校验手机号
		if (!phonereg.test(phonenum) && phonenum.length > 0) {
			info("输入的手机号码不合法，请检查！", "重新输入");
			//$(this).val($(this).val());
			return;
		}
		;
		
		var current;
		if(obj=="0"){
			current = $("#table0 input[name='msgbtn']");
		}else if(obj=="1"){
			current = $("#table1 input[name='msgbtn']");
		}
		var curCount = 10;//间隔函数，1秒执行
		// 设置button效果，开始计时
		current.attr("disabled", "true");
		$(this).css("background","#ccc");
		current.val("重新获取(" + curCount + ")");
		//timer变量，控制时间
		var interValObj = window.setInterval(function(){
			if (curCount == 0) {                
				window.clearInterval(interValObj);// 停止计时器
				current.removeAttr("disabled");// 启用按钮
				$(".checkbut").css("background","#00a2f8");
				current.val("重新获取");
				// 清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
			}else {
				curCount--;
				current.val("重新获取(" + curCount + ")");
			}
		}, 1000); // 启动计时器，1秒执行一次
		
		var rand=Math.random();
		$.get($("#ctx").val() + "/messagecode/getcode?r="+rand,function(data,status){
			if(status == "success"){
				var jd = $.parseJSON(data);
				current.prev().val(jd.msgcode);
			}
		});
	};
	
	
	//错误信息展示
  	if ($("#table0 .errormsg,#table1 .errormsg").children().size()>0) {
		info($("#table0 .errormsg,#table1 .errormsg").text(), "重新输入");
	}
	//消息提示生成方法
	function info(info, btnval) {
		$("#info").text(info);
		$("#res").val(btnval);
		$("#end").OpenDiv();
		setTimeout(function() {
			//$("#end").CloseDiv();
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
});

function selectTag(showContent,selfObj){
	// 操作标签
	var tag = document.getElementById("tags").getElementsByTagName("li");
	var taglength = tag.length;
	for(i=0; i<taglength; i++){
		tag[i].className = "";
	}
	selfObj.parentNode.className = "selectTag";
	// 操作内容
	for(i=0; j=document.getElementById("table"+i); i++){
		j.style.display = "none";
	}
	document.getElementById(showContent).style.display = "block";
}