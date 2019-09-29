strLocationMon = "Down";
var gdCtrlMon = new Object();
var goSelectTagMon = new Array();
var gcGrayMon   = "#808080";
var gcToggleMon = "#FB8664";
var gcBGMon = "#ffffff";
var previousObjectMon = null;

var gdCurDateMon = new Date();
var giYearMon = gdCurDateMon.getFullYear();
var giMonthMon = gdCurDateMon.getMonth()+1;

function fSetDateMon(iYearMon, iMonthMon){
  VicPopCalMon.style.visibility = "hidden";
  if ((iYearMon == 0) && (iMonthMon == 0)){
  	gdCtrlMon.value = "";
  }else{
  	iMonthMon = iMonthMon + 100 + "";
  	iMonthMon = iMonthMon.substring(1);
  	if(gdCtrlMon.tagName == "INPUT"){
  	  	gdCtrlMon.value = iYearMon+"-"+iMonthMon;
  	}else{
  	  	gdCtrlMon.innerText = iYearMon+"-"+iMonthMon;
  	}
  }
  
  for (i in goSelectTagMon)
  	goSelectTagMon[i].style.visibility = "visible";
  goSelectTagMon.length = 0;
  
  window.returnValue=gdCtrlMon.value;
  //window.close();

}

function HiddenDivMon()
{
	var i;
  VicPopCalMon.style.visibility = "hidden";
  for (i in goSelectTagMon)
  	goSelectTagMon[i].style.visibility = "visible";
  goSelectTagMon.length = 0;

}
function fSetSelectedMon(aCell){
  var iOffset = 0;
  var iYearMon = parseInt(tbSelYear.value);
  var iMonthMon = parseInt(tbSelMonth.value);
 
  fSetDateMon(iYearMon, iMonthMon);
}

function PointMon(iX, iY){
	this.x = iX;
	this.y = iY;
}


function fSetYearMon(iYearMon, iMon){
  tbSelMonth.options[iMon-1].selected = true;
  for (i = 0; i < tbSelYear.length; i++)
	if (tbSelYear.options[i].value == iYearMon)
		tbSelYear.options[i].selected = true;
}

function fPrevMonth(){
  var iMon = tbSelMonth.value;
  var iYearMon = tbSelYear.value;
  
  if (--iMon<1) {
	  iMon = 12;
	  iYearMon--;
  }
  
  fSetYearMon(iYearMon, iMon);
}

function fNextMonthMon(){
  var iMon = tbSelMonth.value;
  var iYearMon = tbSelYear.value;
  
  if (++iMon>12) {
	  iMon = 1;
	  iYearMon++;
  }
  
  fSetYearMon(iYearMon, iMon);
}

function fToggleTagsMon(){
  with (document.all.tags("SELECT")){
 	for (i=0; i<length; i++){
 		if ((item(i).Victor!="Won")&&fTagInBoundMon(item(i))){
 			item(i).style.visibility = "hidden";
 			goSelectTagMon[goSelectTagMon.length] = item(i);
 		}
 	}
  }
}

function fTagInBoundMon(aTag){
  with (VicPopCalMon.style){
  	var l = parseInt(left);
  	var t = parseInt(top);
  	var r = l+parseInt(width);
  	var b = t+parseInt(height);
	var ptLT = fGetXYMon(aTag);
	return !((ptLT.x>r)||(ptLT.x+aTag.offsetWidth<l)||(ptLT.y>b)||(ptLT.y+aTag.offsetHeight<t));
  }
}

function fGetXYMon(aTag){
  var oTmp = aTag;
  var pt = new PointMon(0,0);
  do {
  	pt.x += oTmp.offsetLeft;
  	pt.y += oTmp.offsetTop;
  	oTmp = oTmp.offsetParent;
  } while(oTmp.tagName!="BODY");
  return pt;
}

// Main: popCtrl is the widget beyond which you want this calendar to appear;
//       dateCtrl is the widget into which you want to put the selected date.
// i.e.: <input type="text" name="dc" style="text-align:center" readonly><INPUT type="button" value="V" onclick="fPopCalendarMon(dc,dc);return false">
function fPopCalendarMon(popCtrl, dateCtrl,Location,strDate){
	strLocationMon = "Down";
	if(Location!=null&&Location!="")
		strLocationMon=Location;  
    
  if (popCtrl == previousObjectMon){
	  	if (VicPopCalMon.style.visibility == "visible"){
  		HiddenDivMon();
  		return true;
  	}
  	
  }
  previousObjectMon = popCtrl;
  gdCtrlMon = dateCtrl;
  fSetYearMon(giYearMon, giMonthMon); 
  var PointMon = fGetXYMon(popCtrl);
  with (VicPopCalMon.style) {

	if(strLocationMon=="Up")
	{
		left = PointMon.x;
		top  = PointMon.y-210;
	}	
	else if(strLocationMon=="Down")
	{
		left = PointMon.x;
		top  = PointMon.y+popCtrl.offsetHeight;
	}
	else if(strLocationMon=="Left")
	{
		left = PointMon.x-210;
		top  = PointMon.y;
	}
	else if(strLocationMon=="Right")
	{
		left = PointMon.x+90;
		top  = PointMon.y;
	}
	else if(strLocationMon=="Self")
	{
		left = PointMon.x-60;
		top  = PointMon.y-80;
	}
	else if(strLocationMon=="LeftUp")
	{
		left = PointMon.x-210;
		top  = PointMon.y-155;
	}
	else if(strLocationMon=="LeftSelf")
	{
		left = PointMon.x-210;
		top  = PointMon.y-100;
	}
	else if(strLocationMon=="Self1")
	{
		left = PointMon.x-60;
		top  = PointMon.y-110;
	}
	width = VicPopCalMon.offsetWidth;
	width = 210; // Added by Danian Zhang/SUI
	height = VicPopCalMon.offsetHeight;
	fToggleTagsMon(PointMon); 	
	visibility = 'visible';
  }
}

var gMonthsMon = new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");

with (document) {
write("<Div id='VicPopCalMon' style='OVERFLOW:hidden;POSITION:absolute;VISIBILITY:hidden;border:2px ridge;z-index:100;width=100'>");
write("<table border='0' bgcolor='#ffffff' width='100%'>");
write("<TR>");
write("<td valign='middle' align='center'><input type='button' name='PrevMonth' value='<' style='height:20;width:20;FONT:bold' onClick='fPrevMonth()'>");
write("&nbsp;<SELECT name='tbSelYear' Victor='Won'>");
for(i=2009;i<2026;i++)
	write("<OPTION value='"+i+"'>"+i+" 年</OPTION>");
write("</SELECT>");
write("&nbsp;<select name='tbSelMonth' onChange='fSetSelectedMon(this)' Victor='Won'>");
for (i=0; i<12; i++)
	write("<option value='"+(i+1)+"'>"+gMonthsMon[i]+"</option>");
write("</SELECT>");
write("&nbsp;<input type='button' name='PrevMonth' value='>' style='height:20;width:20;FONT:bold' onclick='fNextMonthMon()'>");
write("</td>");
write("</TR><TR>");
write("<td align='center'>");
write("<DIV style='background-color:#3A6790'><table width='100%' border='0'>");
write("</table></DIV>");
write("</td>");
write("</TR><TR><TD align='center'>");
write("<TABLE width='100%'><TR><TD align='center'>");
write("<B style='cursor:hand' onclick='fSetSelectedMon(this)' onMouseOver='this.style.color=gcToggleMon' onMouseOut='this.style.color=0'>选择</B>");
write("</td></tr></table>");
write("</TD></TR>");
write("</TABLE></Div>");
}
