// ������汾���
var BrowserInfo = new Object() ;
BrowserInfo.MajorVer = navigator.appVersion.match(/MSIE (.)/)[1] ;
BrowserInfo.MinorVer = navigator.appVersion.match(/MSIE .\.(.)/)[1] ;
BrowserInfo.IsIE55OrMore = BrowserInfo.MajorVer >= 6 || ( BrowserInfo.MajorVer >= 5 && BrowserInfo.MinorVer >= 5 ) ;

var bEditMode = true;

var eWebEditor = window;

// ȫ�����ö���
var config = new Object() ;
config.StyleMenuHeader = "<head><link href=\"common/js/MenuArea.css\" type=\"text/css\" rel=\"stylesheet\"></head><body scroll=\"no\" onConTextMenu=\"event.returnValue=false;\">";
config.StyleDir = "standard";


// �Ƿ�ѡ��ָ�����͵Ŀؼ�
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

// ��ȡ������ƶ�Ԫ�ص�ֵ
function getCurrentChannelId(elem){
		var oChannel = event.srcElement;
		var s;
		try{
			s = oChannel.getAttribute(elem).toUpperCase();
		}catch(e){}
		
				return s;
		
}

// ��ʽ���༭���е�����
function format(what,opt) {
	if (opt=="RemoveFormat") {
		what=opt;
		opt=null;
	}
	if (opt==null) document.execCommand(what);
	else document.execCommand(what,"",opt);
}

// ��ʾ��ģʽ�Ի���
function ShowDialog(url, width, height, optValidate) {
	var arr = showModalDialog(url, window, "dialogWidth:" + width + "px;dialogHeight:" + height + "px;help:no;scroll:no;status:no");
}

// �����滻
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