//时间
function clock() {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var hour = date.getHours();
	var minute = date.getMinutes();
	var second = date.getSeconds();
	month = month < 10 ? ("0" + month) : month;
	day = day < 10 ? ("0" + day) : day;
	hour = hour < 10 ? ("0" + hour) : hour;
	minute = minute < 10 ? ("0" + minute) : minute;
	second = second < 10 ? ("0" + second) : second;
	var time = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":"
			+ second;
	$("#clock").html(time);
}
//setInterval("clock()", 100);

// 清除session
var flag = true;
function clearSession() {
	document.getElementsByTagName("body").onclick = function() {
		flag = false;
	}
	if (flag) {
		window.location.href = "/SDFSPlatform";
	}
}
//setInterval("clearSession()", 1000 * 60 * 20);