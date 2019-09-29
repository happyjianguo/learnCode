//菜单反色
var originClassName;
function invertmenu(){
	if (event.srcElement.tagName == "TD"){
		originClassName=event.srcElement.parentElement.className;
		event.srcElement.parentElement.className = "tr3";
	}
	else{
		originClassName=event.srcElement.parentElement.parentElement.className;
		event.srcElement.parentElement.parentElement.className = "tr3";
	}
}
//菜单恢复
function resumemenu(){
	if (event.srcElement.tagName == "TD")
		event.srcElement.parentElement.className=originClassName;
	else
		event.srcElement.parentElement.parentElement.className =originClassName;
}