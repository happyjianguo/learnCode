// 浏览器版本检测
var BrowserInfo = new Object() ;
BrowserInfo.MajorVer = navigator.appVersion.match(/MSIE (.)/)[1] ;
BrowserInfo.MinorVer = navigator.appVersion.match(/MSIE .\.(.)/)[1] ;
BrowserInfo.IsIE55OrMore = BrowserInfo.MajorVer >= 6 || ( BrowserInfo.MajorVer >= 5 && BrowserInfo.MinorVer >= 5 ) ;

var bEditMode = true;

var eWebEditor = window;

// 全局设置对象
var config = new Object() ;
config.StyleMenuHeader = "<head><link href=\"common/js/MenuArea.css\" type=\"text/css\" rel=\"stylesheet\"></head><body scroll=\"no\" onConTextMenu=\"event.returnValue=false;\">";
config.StyleDir = "standard";


// 是否选中指定类型的控件
function isControlSelected(tag){
	//if (document.selection.type == "Control") {
		//var oControlRange = document.selection.createRange();
		var oControlRange = event.srcElement;
		//alert(oControlRange.tagName);
		if (oControlRange.tagName.toUpperCase() == tag) {
			return true;
		}	
	//}
	return false;
}

// 获取对象的制定元素的值
function getCurrentChannelId(elem){
		var oChannel = event.srcElement;
		var s;
		try{
			s = oChannel.getAttribute(elem).toUpperCase();
		}catch(e){}
		
				return s;
		
}

// 格式化编辑器中的内容
function format(what,opt) {
	if (opt=="RemoveFormat") {
		what=opt;
		opt=null;
	}
	if (opt==null) document.execCommand(what);
	else document.execCommand(what,"",opt);
}

// 显示无模式对话框
function ShowDialog(url, width, height, optValidate) {
	var arr = showModalDialog(url, window, "dialogWidth:" + width + "px;dialogHeight:" + height + "px;help:no;scroll:no;status:no");
}

// 查找替换
function findReplace(){
	ShowDialog('dialog/findreplace.htm', 320, 165, true)
}

function refresh(){
	//alert();
	history.go(0);
}


function setSrc(obj,urlStr){
	//alert(urlStr);
	parent.document.getElementById(obj).src = urlStr;
}

document.oncontextmenu=new Function("showContextMenu(window.event);return false;");