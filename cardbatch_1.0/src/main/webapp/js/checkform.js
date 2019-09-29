/**
* 为金额指定格式
* @param {num} 要转换的金额字符串
* @param {pattern} 指定格式 '#,##0.00'
* @return {String} 转换后的金额字符串
*/
function formatNumber(num,pattern){   
  var strarr = num?num.toString().split('.'):['0'];   
  var fmtarr = pattern?pattern.split('.'):[''];   
  var retstr='';   
   
  // 整数部分   
  var str = strarr[0];   
  var fmt = fmtarr[0];   
  var i = str.length-1;     
  var comma = false;   
  for(var f=fmt.length-1;f>=0;f--){   
    switch(fmt.substr(f,1)){   
      case '#':   
        if(i>=0 ) retstr = str.substr(i--,1) + retstr;   
        break;   
      case '0':   
        if(i>=0) retstr = str.substr(i--,1) + retstr;   
        else retstr = '0' + retstr;   
        break;   
      case ',':   
        comma = true;   
        retstr=','+retstr;   
        break;   
    }   
  }   
  if(i>=0){   
    if(comma){   
      var l = str.length;   
      for(;i>=0;i--){   
        retstr = str.substr(i,1) + retstr;   
        if(i>0 && ((l-i)%3)==0) retstr = ',' + retstr;    
      }   
    }   
    else retstr = str.substr(0,i+1) + retstr;   
  }   
   
  retstr = retstr+'.';   
  // 处理小数部分   
  str=strarr.length>1?strarr[1]:'';   
  fmt=fmtarr.length>1?fmtarr[1]:'';   
  i=0;   
  for(var f=0;f<fmt.length;f++){   
    switch(fmt.substr(f,1)){   
      case '#':   
        if(i<str.length) retstr+=str.substr(i++,1);   
        break;   
      case '0':   
        if(i<str.length) retstr+= str.substr(i++,1);   
        else retstr+='0';   
        break;   
    }   
  }   
  return retstr.replace(/^,+/,'').replace(/\.$/,'');   
} 
function moneyCheck(moneyString)
{		
	var j=0;		
//	var stringTemp = javaTrim(moneyString);	
	var stringTemp = trim(moneyString);
	var stringlength = stringTemp.length;
	if (stringlength == 0) 
	{
		alert("不能输入空字符");
		return(false); 
	}
	//判断金额是否为"0",为"0"时给出出错提示
	if ((stringTemp == "0.00") || (stringTemp == "0.0") || (stringTemp == "0.") || (stringTemp == "0")) 
	{
		alert("金额不能为0，请重新填写");    
		return(false);
	}
	//检查金额不能为"0",且次位非小数点时首位不能为"0"
	alert(stringTemp.charAt(0));
	if (stringTemp.charAt(0)=="0" )
	{		
		if (stringlength == 1)
		{		
		 	alert("金额不能为0，请重新填写！");
	   	return(false);
	  }
	  else
	  {	  	
	  	if (!(stringTemp.charAt(1)=="."))
	  	{	  	
				alert("金额首位不能为0，请重新填写！");
				return(false);
			}						
		}
	}
	//判断每位数字
	for (i = 0 ; i < stringlength ; i++) 
	{ 		
		if(isNaN(parseInt(stringTemp.charAt(i),10)))  
		{		
			if(stringTemp.charAt(i) != ".")  
			{
				alert( "请输入数值型数据");
				return(false); 
			} 
			else  
			{			
				j++;
							
				if(stringlength - i > 4 )
				{
					alert("小数点后只能有三位");
					return(false);
				}
			}						
		}	
	} //End For	
	//检查小数点只能是一个
	if(j > 1) 
	{
		alert( "小数点只能有一个!");			
		return(false);
	}
	return(true);
		
}

/**
 * 检查输入数字的格式，整形或浮点型
 * 浮点型只能输入一个小数点且小数点只能有两位
 * 输入字符串不能为空
 * @param numberString
 * @return true or false
 */
function checkNumber(numberString,msg) {	
	var j=0;		
	var stringTemp = trim(numberString);
	var stringlength = stringTemp.length;
	if (stringlength == 0){
		alert(msg+"不能输入空字符");
		return(false); 
	}
	//判断每位数字
	for (i = 0 ; i < stringlength ; i++){ 		
		if(isNaN(parseInt(stringTemp.charAt(i),10))) {		
			if(stringTemp.charAt(i) != ".") {
				alert( msg+"请输入数值型数据");
				return(false); 
			}else{			
				j++;	
				if(stringlength - i > 4 ){
					alert(msg+"小数点后只能有三位");
					return(false);
				}
			}						
		}	
	} 	
	//检查小数点只能是一个
	if(j > 1){
		alert( msg+"小数点只能有一个!");			
		return(false);
	}
	
	return(true);	
}

function checkNumber2(numberString,msg) {	
	var j=0;		
	var stringTemp = trim(numberString);
	var stringlength = stringTemp.length;
	if (stringlength == 0){
		alert(msg+"不能输入空字符");
		return(false); 
	}
	//判断每位数字
	for (i = 0 ; i < stringlength ; i++){ 		
		if(isNaN(parseInt(stringTemp.charAt(i),10))) {		
			if(stringTemp.charAt(i) != ".") {
				alert( msg+"请输入数值型数据");
				return(false); 
			}else{			
				j++;	
				if(stringlength - i > 3 ){
					alert(msg+"小数点后只能有二位");
					return(false);
				}
			}						
		}	
	} 	
	//检查小数点只能是一个
	if(j > 1){
		alert( msg+"小数点只能有一个!");			
		return(false);
	}
	return(true);	
}

function checkLenElement2(formname,element,msg,len)
{
	if (  window.document.forms[formname].elements[element].value != null 
		&& trim(window.document.forms[formname].elements[element].value) != ""
			&& trim(window.document.forms[formname].elements[element].value).length > len) {
		alert (msg + "只允许为" + len + "位字符！") ;
		try{
			window.document.forms[formname].elements[element].focus() ;
		}catch(e){}
		return false ;
	}
	return true;
}

//检查element1<element2
function check2value(formname,element1,element2,element1caption,element2caption) {
    var firstValue=document.forms[formname].elements[element1].value;
	var secondValue=document.forms[formname].elements[element2].value;
	if (firstValue>=secondValue){
		var msg=element1caption+"必须小于"+element2caption;
		alert(msg);
		return false;
	} else return true;
}
//检测非空
//如为空，则返回false
function checkwh(formname,element)
{
	if ( window.document.forms[formname].elements[element].value == "" || window.document.forms[formname].elements[element].value == null) {
		alert ("文号不能为空！") ;
		if (window.document.forms[formname].elements[element].type == "text"){
			window.document.forms[formname].elements[element].focus() ;
		}
		return false ;
	}
	return true;
}
function checkremark(formname,element)
{
	if ( window.document.forms[formname].elements[element].value == "" || window.document.forms[formname].elements[element].value == null) {
		alert ("备注不能为空！") ;

		window.document.forms[formname].elements[element].focus() ;

		return false ;
	}
	return true;
}
//检测时间选择
//如值为请选择时间，则返回false
function checktime(formname,element)
{
	if ( window.document.forms[formname].elements[element].value == "请选择时间" ) {
		alert ("请选择时间！") ;
		if (window.document.forms[formname].elements[element].type == "text"){
			window.document.forms[formname].elements[element].focus() ;
		}
		return false ;
	}
	return true;
}

//将时间转化为长度为十的格式
function converttime(formname,element)
{
	var str=window.document.forms[formname].elements[element].value;

	var index=str.indexOf("-");
	var last=str.lastIndexOf("-");
	var year=str.substring(0,4);
	var month=str.substring(index+1,last);
	if(month.length<2)
	{
	month="0"+month;
	}
	var day=str.substring(last+1,str.length);
	if(day.length<2)
	{
	day="0"+day;
	}
	str=year+"-"+month+"-"+day;

	return str;
}


//如值为请选择时间，如果element1>element2,则返回false
function checktwotime(formname,element1,element2)
{
	if ( window.document.forms[formname].elements[element1].value == "请选择时间" ||window.document.forms[formname].elements[element2].value == "请选择时间") {
		alert ("请选择时间！") ;
		return false ;

	}
	var time1=converttime(formname,element1);
	var time2=converttime(formname,element2);
	if (time1>time2)
	{
		alert ("结束时间小于开始时间！") ;
		return false ;
	}
	return true;

}

//判断字符串是否为合法钱数
// b-10 isMoney(Object)
function isMoney(obj)
{
	if (ifMoney(trim(obj.value)))
	{
		return true;
	}
	else
	{
		errorMsg(obj, "不是合法的货币数！");
		return false;
	}	
	return true;
}

function errorMsg(obj,msg)
{
	obj.focus();
	obj.select();
	alert(msg);
	return false;
}

//建立者:任勇
//判断字符串是否为合法钱数
// a-10 ifMoeny(String)
function ifMoney(str){
	if ( ( pos = str.indexOf( "." ) ) != -1 ){
	   if (str.length==1)
	     return false;
	     
	   if(str.length - pos > 3)
	   	return false;
	   
	   if ( ( pos = str.indexOf(".", pos + 1) )  != -1 )
	     return false;
	}
	

	for ( var i = 0 ; i < str.length; i ++ ){
	  if (( str.charAt(i) < "0" || str.charAt(i) > "9" )&&(str.charAt(i)!="."))
	    return false;
	}
	if (str.length>1 && ( str.charAt(0) == "0" && str.charAt(1) != "." )){
	    return false;
	}
	
	return true;
}

//检查字符串中是否有规定字符以内/外的字符
function checkChar(charset, val, should_in)
{
    var num = val.length;
    for (i=0; i < num; i++) {
       var char = val.charAt(i);
       char = char.toUpperCase();
       if ((charset.indexOf(char) > -1) && (!should_in))
          return false;
       else if ((charset.indexOf(char) == -1) && (should_in))
          return false;
    }
    return true;
}
//删除提示
function confirmdel()
{
	var msg="删除操作不可逆转，继续吗？";
	return window.confirm(msg);
}
//如为空，则返回false
function checkselect(formname,element)
{
	if ( window.document.forms[formname].elements[element].value == "" || window.document.forms[formname].elements[element].value == null) {
		alert ("下拉列表框没有选择！") ;
		window.document.forms[formname].elements[element].focus() ;
		return false ;
	}
	return true;
}
function pagesubmit(formname,url,pageno,lastpage)
{

window.document.forms[formname].action=url+"?pageno="+pageno+"&lastpage="+lastpage;
//alert(window.document.forms[formname].action);
window.document.forms[formname].submit();

}

function backhome(formname,url)
{

	window.document.forms[formname].action=url;
	window.document.forms[formname].submit();
}
function convertnormal(datestr)
{
	var str=datestr;

	var index=str.indexOf("-");
	var last=str.lastIndexOf("-");
	var year=str.substring(0,4);
	var month=str.substring(index+1,last);

	if(month.length==2&month.substring(0,1)==0)
	{
	month=month.substring(1,2);
	}
	var day=str.substring(last+1,str.length);
	if(day.length==2&day.substring(0,1)==0)
	{
	day=day.substring(1,2);
	}
	str=year+"-"+month+"-"+day;

	return str;
}

function checkelement(formname,element,msg)
{
	if ( trim(window.document.forms[formname].elements[element].value) == "" || window.document.forms[formname].elements[element].value == null) {
		alert (msg+"不能为空！") ;

		window.document.forms[formname].elements[element].focus() ;
		return false ;
	}
	return true;
}

function checkLenElement(formname,element,msg,len)
{
	if ( trim(window.document.forms[formname].elements[element].value) == "" || window.document.forms[formname].elements[element].value == null
			|| trim(window.document.forms[formname].elements[element].value).length != len) {
		alert (msg + "只允许为" + len + "位字符！") ;
		window.document.forms[formname].elements[element].focus() ;
		return false ;
	}
	return true;
}

function checkPoint(formname,element,msg)
{
	if ( trim(window.document.forms[formname].elements[element].value) == "." ) {
		alert (msg+"请输入正确的格式 ， 不能只输入 '.' 号！") ;
		window.document.forms[formname].elements[element].focus() ;
		return false ;
	}
	return true;
}

function checkFeeLogic(formname,element)
{	
	if ( trim(window.document.forms[formname].elements[element].value) >  trim(window.document.forms[formname].elements["ctrl_feemax"].value)) {
		alert ("单笔手续费最大必须 大于单笔手续费最小！") ;
		window.document.forms[formname].elements[element].focus() ;
		return false ;
	}
	return true;
}

/**
 * 描述：比较两个数的大小，第一个参数大于第二个参数返回false,否则返回true
 * 作者：dj
 * 日期：2012-08-23
 * 版本：V1.0
 */
function checkNumberComp(formname,element1,element2,msg){	
	if (trim(window.document.forms[formname].elements[element1].value)!="0"
		&& trim(window.document.forms[formname].elements[element2].value)!="0") {
		if ( trim(window.document.forms[formname].elements[element1].value) >  trim(window.document.forms[formname].elements[element2].value)) {
			alert (msg) ;
			window.document.forms[formname].elements[element1].focus();
			window.document.forms[formname].elements[element2].focus();
			return false ;
		}
	}
	return true;
}

function checkelementmore(formname,element,msg,length)
{
	if ( window.document.forms[formname].elements[element].value == "" || window.document.forms[formname].elements[element].value == null) {
		alert (msg+"不能为空！") ;

		window.document.forms[formname].elements[element].focus() ;

		return false ;
	}
	var str=window.document.forms[formname].elements[element].value;
	if (str.length!=length)
	{
		alert (msg+"的长度应该为"+length+"位！") ;
		window.document.forms[formname].elements[element].focus() ;
		return false ;
	}
	return true;
}
function isnumbermor(formname,element,msg)
{
    var input = document.forms[formname].elements[element].value;
    var pos1 = input.indexOf(".");
    var pos2 = input.lastIndexOf(".");
    var charset = "1234567890.";
    if ((pos1 != pos2)||(!checkChar(charset, input, true))) {
        alert (msg+"只可包含数字和一个“.”！");
        document.forms[formname].elements[element].focus();
        document.forms[formname].elements[element].select();
        return false;
    }
    return true;
}
function isNumber(element,msg)
{
	var value = element.value;
	if (value == "") {
		alert(msg + "不能为空！");
		return false;
	}
    var pos1 = value.indexOf(".");
    var pos2 = value.lastIndexOf(".");
    var charset = "1234567890.";
    if (pos1==0) {
    	alert(msg + "只能以数字开头！");
    	element.focus();
    	element.select();
    	return false;
    }
    if ((pos1 != pos2)||(!checkChar(charset, value, true))) {
        alert (msg + "只可包含数字和一个“.”！");
    	element.focus();
    	element.select();
        return false;
    }
    return true;
}
/*
function isnumberonly(formname,element,msg)
{
    var input = document.forms[formname].elements[element].value;

    var charset = "1234567890";
    if (!checkChar(charset, input, true)) {
        alert (msg+"只可包含数字！");
        document.forms[formname].elements[element].focus();
        document.forms[formname].elements[element].value = "";
        return false;
    }
    return true;
}
*/
function isnumberonly(formname,element,msg)
{ 
	var input = document.forms[formname].elements[element].value;
	var isInteger = RegExp(/^[0-9]+$/);
	if ( !isInteger.test(input) ) {
		alert (msg+"只可包含数字！");
        document.forms[formname].elements[element].focus();
        document.forms[formname].elements[element].value = "";
        return false;
	}
    return true;
}

function isLenNumOnly2(formname,element,msg,len)
{
    var input = document.forms[formname].elements[element].value;
	if (input == "") {
		alert(msg + "不能为空！");
		document.forms[formname].elements[element].focus();
        document.forms[formname].elements[element].value = "";
		return false;
	}
    var charset = "1234567890";
    if (!checkChar(charset, input, true) || input.length > len) {
        alert (msg+"只允许为"+len+"位数字！");
        try{
        	document.forms[formname].elements[element].focus();
            document.forms[formname].elements[element].value = "";
        }catch(e){}
        return false;
    }
    return true;
}

function isnumberonly3(formname,element,msg)
{
	var input = document.forms[formname].elements[element].value;
	if (input == "") {
		alert(msg + "不能为空！");
		document.forms[formname].elements[element].focus();
        document.forms[formname].elements[element].value = "";
		return false;
	}
	var isInteger = RegExp(/^[0-9]+$/);
	if ( !isInteger.test(input) ) {
		alert (msg+"只可包含数字！");
		try{
			document.forms[formname].elements[element].focus();
	        document.forms[formname].elements[element].value = "";
		}catch(e){}
        return false;
	}
    return true;
}

function isLenNumOnly(formname,element,msg,len)
{
    var input = document.forms[formname].elements[element].value;

    var charset = "1234567890";
    if (!checkChar(charset, input, true) || input.length!=len) {
        alert (msg+"只允许为"+len+"位数字！");
        document.forms[formname].elements[element].focus();
        document.forms[formname].elements[element].value = "";
        return false;
    }
    return true;
}

function elementvalue(formname,element)
{
    var str = document.forms[formname].elements[element].value;

    return str;
}

function showQuitMsg(theURL,winName,msg) { //v2.0
  showquiting(msg);
  location.href=theURL;
  //window.open(theURL,winName,features);
}

function showquiting(msg) {
	if (msg == "") msg="处理中，请稍等...";
	document.getElementById("quiting_sub").appendChild( document.createTextNode(msg));
	document.getElementById("quiting").style.visibility="visible";
}

function trim(temp){
    while(temp.charAt(0)==" "){   
        temp=temp.substring(0,1);
    }
    while(temp.charAt(temp.length-1)==" "){ 
        temp=temp.substring(0,temp.length-1);
    }
    return temp;
}

//校验数字和英文字母
function checkCode(str){
	var reg = /^[0-9,a-z,A-Z]+$/;
	if(!reg.test(str)){
		return false;
	}
	return true;
}

/**
 * 校验固定长度数字和英文字母
 * 日期：2012-09-11
 * 作者：dj
 */
function checkCodeLen(formname,element,msg,len){
	var input = document.forms[formname].elements[element].value;
	var reg = /^[0-9,a-z,A-Z]+$/;
    if (!reg.test(input) || input.length!=len) {
        alert (msg+"只允许为"+len+"位数字或字母！");
        document.forms[formname].elements[element].focus();
        document.forms[formname].elements[element].value = "";
        return false;
    }
    return true;
}

function checkAsciiCode(str){
    var reg = /^[\x00-\x7F]*$/;
    if(!reg.test(str))	
	return false;
    return true;
}

/** 
 * 页面选择日期与系统日期相比较(传入参数的日期格式必须为yyyy-mm-dd) 
 * 作者：dj
 * 日期：2012-08-31
 * @param inputDate
 * @return 输入日期大于当前日期返回ture
 * 		   输入日期小于当前日期返回false
 */
function compareDate(inputDate){
	var arr = inputDate.split('-');	
    var currentDate = new Date(); 
    var compYear = currentDate.getYear() <= arr[0]? 0 :1;
    var compMonth = currentDate.getMonth() <= arr[1]? 0 :1;
    var compDate = currentDate.getDate() <= arr[2]? 0 :1;
    
    // 当前的年、月、日都大于输入日期时，返回ture;否则返回false;
    if (compYear==0 && compMonth==0 && compDate==0) 
    	return true;
    else
    	return false;
}

function compareDate8(inputDate){
	var year = inputDate.substr(0,4);
	var mon = inputDate.substr(4,2);
	var day = inputDate.substr(6,2);

	var indate = new Date(year, mon-1, day);

    var currentDate = new Date(); 

    if (currentDate >= indate) {
    	return false;
    } else {
    	return true;
    }
}






