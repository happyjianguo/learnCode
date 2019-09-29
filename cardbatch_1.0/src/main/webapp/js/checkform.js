/**
* Ϊ���ָ����ʽ
* @param {num} Ҫת���Ľ���ַ���
* @param {pattern} ָ����ʽ '#,##0.00'
* @return {String} ת����Ľ���ַ���
*/
function formatNumber(num,pattern){   
  var strarr = num?num.toString().split('.'):['0'];   
  var fmtarr = pattern?pattern.split('.'):[''];   
  var retstr='';   
   
  // ��������   
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
  // ����С������   
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
		alert("����������ַ�");
		return(false); 
	}
	//�жϽ���Ƿ�Ϊ"0",Ϊ"0"ʱ����������ʾ
	if ((stringTemp == "0.00") || (stringTemp == "0.0") || (stringTemp == "0.") || (stringTemp == "0")) 
	{
		alert("����Ϊ0����������д");    
		return(false);
	}
	//������Ϊ"0",�Ҵ�λ��С����ʱ��λ����Ϊ"0"
	alert(stringTemp.charAt(0));
	if (stringTemp.charAt(0)=="0" )
	{		
		if (stringlength == 1)
		{		
		 	alert("����Ϊ0����������д��");
	   	return(false);
	  }
	  else
	  {	  	
	  	if (!(stringTemp.charAt(1)=="."))
	  	{	  	
				alert("�����λ����Ϊ0����������д��");
				return(false);
			}						
		}
	}
	//�ж�ÿλ����
	for (i = 0 ; i < stringlength ; i++) 
	{ 		
		if(isNaN(parseInt(stringTemp.charAt(i),10)))  
		{		
			if(stringTemp.charAt(i) != ".")  
			{
				alert( "��������ֵ������");
				return(false); 
			} 
			else  
			{			
				j++;
							
				if(stringlength - i > 4 )
				{
					alert("С�����ֻ������λ");
					return(false);
				}
			}						
		}	
	} //End For	
	//���С����ֻ����һ��
	if(j > 1) 
	{
		alert( "С����ֻ����һ��!");			
		return(false);
	}
	return(true);
		
}

/**
 * ����������ֵĸ�ʽ�����λ򸡵���
 * ������ֻ������һ��С������С����ֻ������λ
 * �����ַ�������Ϊ��
 * @param numberString
 * @return true or false
 */
function checkNumber(numberString,msg) {	
	var j=0;		
	var stringTemp = trim(numberString);
	var stringlength = stringTemp.length;
	if (stringlength == 0){
		alert(msg+"����������ַ�");
		return(false); 
	}
	//�ж�ÿλ����
	for (i = 0 ; i < stringlength ; i++){ 		
		if(isNaN(parseInt(stringTemp.charAt(i),10))) {		
			if(stringTemp.charAt(i) != ".") {
				alert( msg+"��������ֵ������");
				return(false); 
			}else{			
				j++;	
				if(stringlength - i > 4 ){
					alert(msg+"С�����ֻ������λ");
					return(false);
				}
			}						
		}	
	} 	
	//���С����ֻ����һ��
	if(j > 1){
		alert( msg+"С����ֻ����һ��!");			
		return(false);
	}
	
	return(true);	
}

function checkNumber2(numberString,msg) {	
	var j=0;		
	var stringTemp = trim(numberString);
	var stringlength = stringTemp.length;
	if (stringlength == 0){
		alert(msg+"����������ַ�");
		return(false); 
	}
	//�ж�ÿλ����
	for (i = 0 ; i < stringlength ; i++){ 		
		if(isNaN(parseInt(stringTemp.charAt(i),10))) {		
			if(stringTemp.charAt(i) != ".") {
				alert( msg+"��������ֵ������");
				return(false); 
			}else{			
				j++;	
				if(stringlength - i > 3 ){
					alert(msg+"С�����ֻ���ж�λ");
					return(false);
				}
			}						
		}	
	} 	
	//���С����ֻ����һ��
	if(j > 1){
		alert( msg+"С����ֻ����һ��!");			
		return(false);
	}
	return(true);	
}

function checkLenElement2(formname,element,msg,len)
{
	if (  window.document.forms[formname].elements[element].value != null 
		&& trim(window.document.forms[formname].elements[element].value) != ""
			&& trim(window.document.forms[formname].elements[element].value).length > len) {
		alert (msg + "ֻ����Ϊ" + len + "λ�ַ���") ;
		try{
			window.document.forms[formname].elements[element].focus() ;
		}catch(e){}
		return false ;
	}
	return true;
}

//���element1<element2
function check2value(formname,element1,element2,element1caption,element2caption) {
    var firstValue=document.forms[formname].elements[element1].value;
	var secondValue=document.forms[formname].elements[element2].value;
	if (firstValue>=secondValue){
		var msg=element1caption+"����С��"+element2caption;
		alert(msg);
		return false;
	} else return true;
}
//���ǿ�
//��Ϊ�գ��򷵻�false
function checkwh(formname,element)
{
	if ( window.document.forms[formname].elements[element].value == "" || window.document.forms[formname].elements[element].value == null) {
		alert ("�ĺŲ���Ϊ�գ�") ;
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
		alert ("��ע����Ϊ�գ�") ;

		window.document.forms[formname].elements[element].focus() ;

		return false ;
	}
	return true;
}
//���ʱ��ѡ��
//��ֵΪ��ѡ��ʱ�䣬�򷵻�false
function checktime(formname,element)
{
	if ( window.document.forms[formname].elements[element].value == "��ѡ��ʱ��" ) {
		alert ("��ѡ��ʱ�䣡") ;
		if (window.document.forms[formname].elements[element].type == "text"){
			window.document.forms[formname].elements[element].focus() ;
		}
		return false ;
	}
	return true;
}

//��ʱ��ת��Ϊ����Ϊʮ�ĸ�ʽ
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


//��ֵΪ��ѡ��ʱ�䣬���element1>element2,�򷵻�false
function checktwotime(formname,element1,element2)
{
	if ( window.document.forms[formname].elements[element1].value == "��ѡ��ʱ��" ||window.document.forms[formname].elements[element2].value == "��ѡ��ʱ��") {
		alert ("��ѡ��ʱ�䣡") ;
		return false ;

	}
	var time1=converttime(formname,element1);
	var time2=converttime(formname,element2);
	if (time1>time2)
	{
		alert ("����ʱ��С�ڿ�ʼʱ�䣡") ;
		return false ;
	}
	return true;

}

//�ж��ַ����Ƿ�Ϊ�Ϸ�Ǯ��
// b-10 isMoney(Object)
function isMoney(obj)
{
	if (ifMoney(trim(obj.value)))
	{
		return true;
	}
	else
	{
		errorMsg(obj, "���ǺϷ��Ļ�������");
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

//������:����
//�ж��ַ����Ƿ�Ϊ�Ϸ�Ǯ��
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

//����ַ������Ƿ��й涨�ַ�����/����ַ�
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
//ɾ����ʾ
function confirmdel()
{
	var msg="ɾ������������ת��������";
	return window.confirm(msg);
}
//��Ϊ�գ��򷵻�false
function checkselect(formname,element)
{
	if ( window.document.forms[formname].elements[element].value == "" || window.document.forms[formname].elements[element].value == null) {
		alert ("�����б��û��ѡ��") ;
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
		alert (msg+"����Ϊ�գ�") ;

		window.document.forms[formname].elements[element].focus() ;
		return false ;
	}
	return true;
}

function checkLenElement(formname,element,msg,len)
{
	if ( trim(window.document.forms[formname].elements[element].value) == "" || window.document.forms[formname].elements[element].value == null
			|| trim(window.document.forms[formname].elements[element].value).length != len) {
		alert (msg + "ֻ����Ϊ" + len + "λ�ַ���") ;
		window.document.forms[formname].elements[element].focus() ;
		return false ;
	}
	return true;
}

function checkPoint(formname,element,msg)
{
	if ( trim(window.document.forms[formname].elements[element].value) == "." ) {
		alert (msg+"��������ȷ�ĸ�ʽ �� ����ֻ���� '.' �ţ�") ;
		window.document.forms[formname].elements[element].focus() ;
		return false ;
	}
	return true;
}

function checkFeeLogic(formname,element)
{	
	if ( trim(window.document.forms[formname].elements[element].value) >  trim(window.document.forms[formname].elements["ctrl_feemax"].value)) {
		alert ("���������������� ���ڵ�����������С��") ;
		window.document.forms[formname].elements[element].focus() ;
		return false ;
	}
	return true;
}

/**
 * �������Ƚ��������Ĵ�С����һ���������ڵڶ�����������false,���򷵻�true
 * ���ߣ�dj
 * ���ڣ�2012-08-23
 * �汾��V1.0
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
		alert (msg+"����Ϊ�գ�") ;

		window.document.forms[formname].elements[element].focus() ;

		return false ;
	}
	var str=window.document.forms[formname].elements[element].value;
	if (str.length!=length)
	{
		alert (msg+"�ĳ���Ӧ��Ϊ"+length+"λ��") ;
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
        alert (msg+"ֻ�ɰ������ֺ�һ����.����");
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
		alert(msg + "����Ϊ�գ�");
		return false;
	}
    var pos1 = value.indexOf(".");
    var pos2 = value.lastIndexOf(".");
    var charset = "1234567890.";
    if (pos1==0) {
    	alert(msg + "ֻ�������ֿ�ͷ��");
    	element.focus();
    	element.select();
    	return false;
    }
    if ((pos1 != pos2)||(!checkChar(charset, value, true))) {
        alert (msg + "ֻ�ɰ������ֺ�һ����.����");
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
        alert (msg+"ֻ�ɰ������֣�");
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
		alert (msg+"ֻ�ɰ������֣�");
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
		alert(msg + "����Ϊ�գ�");
		document.forms[formname].elements[element].focus();
        document.forms[formname].elements[element].value = "";
		return false;
	}
    var charset = "1234567890";
    if (!checkChar(charset, input, true) || input.length > len) {
        alert (msg+"ֻ����Ϊ"+len+"λ���֣�");
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
		alert(msg + "����Ϊ�գ�");
		document.forms[formname].elements[element].focus();
        document.forms[formname].elements[element].value = "";
		return false;
	}
	var isInteger = RegExp(/^[0-9]+$/);
	if ( !isInteger.test(input) ) {
		alert (msg+"ֻ�ɰ������֣�");
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
        alert (msg+"ֻ����Ϊ"+len+"λ���֣�");
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
	if (msg == "") msg="�����У����Ե�...";
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

//У�����ֺ�Ӣ����ĸ
function checkCode(str){
	var reg = /^[0-9,a-z,A-Z]+$/;
	if(!reg.test(str)){
		return false;
	}
	return true;
}

/**
 * У��̶��������ֺ�Ӣ����ĸ
 * ���ڣ�2012-09-11
 * ���ߣ�dj
 */
function checkCodeLen(formname,element,msg,len){
	var input = document.forms[formname].elements[element].value;
	var reg = /^[0-9,a-z,A-Z]+$/;
    if (!reg.test(input) || input.length!=len) {
        alert (msg+"ֻ����Ϊ"+len+"λ���ֻ���ĸ��");
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
 * ҳ��ѡ��������ϵͳ������Ƚ�(������������ڸ�ʽ����Ϊyyyy-mm-dd) 
 * ���ߣ�dj
 * ���ڣ�2012-08-31
 * @param inputDate
 * @return �������ڴ��ڵ�ǰ���ڷ���ture
 * 		   ��������С�ڵ�ǰ���ڷ���false
 */
function compareDate(inputDate){
	var arr = inputDate.split('-');	
    var currentDate = new Date(); 
    var compYear = currentDate.getYear() <= arr[0]? 0 :1;
    var compMonth = currentDate.getMonth() <= arr[1]? 0 :1;
    var compDate = currentDate.getDate() <= arr[2]? 0 :1;
    
    // ��ǰ���ꡢ�¡��ն�������������ʱ������ture;���򷵻�false;
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






