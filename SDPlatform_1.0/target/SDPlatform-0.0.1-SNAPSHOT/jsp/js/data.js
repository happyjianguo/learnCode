/*首页时间的显示 070201_wht*/
function showtime() {
	var now = new Date();
	var shortdate = now.getYear()+"年"+(now.getMonth()+1)+"月"+now.getDate()+"日";

	var hours = now.getHours();
	var minutes = now.getMinutes();
	var seconds = now.getSeconds()
	var timeValue = "" + ((hours >12) ? hours :hours)
	timeValue += ((minutes < 10) ? ":0" : ":") + minutes
	timeValue += ((seconds < 10) ? ":0" : ":") + seconds

	var tbox = document.getElementById("timetxt");//timetxt显示时间的label名称
	tbox.innerText =shortdate + " " + timeValue;
	timerID = setTimeout("showtime()",1000);
	timerRunning = true;
}
/*end_wht*/

/*去空格*/
function jtrim(s) {
	var i,b=0,e=s.length;
	for(i=0;i<s.length;i++)  if(s.charAt(i)!=' '){b=i;break;}
	if(i==s.length)  return  "";
	for(i=s.length-1;i>b;i--)  if(s.charAt(i)!=' '){e=i;break;}
	return s.substring(b,e+1);
}


/*判断是否为数值　0-9　*/
function IsNum(NUM)
{
var i,j,strTemp;
strTemp="0123456789";
if ( NUM.length== 0)
return 1//没填也认为是数字
for (i=0;i<NUM.length;i++)
{
j=strTemp.indexOf(NUM.charAt(i));
if (j==-1)
{
//说明有字符不是数字
return 0;
}
}
//说明是数字
return 1;
}


/**判断是否为浮点型　0.0
如果是,返回1;
如果不是,返回0;
*/
function check(obj)
{
  if((parseFloat(obj)==obj)&&(obj.split(".").length>1))
  return 1;
  else
  return 0;
}


/********************************** email *****************************************/
/**
*校验字符串是否为email型
*返回值：
*如果为空，定义校验通过，           返回true
*如果字串为email型，校验通过，      返回true
*如果email不合法，                返回false    参考提示信息：Email的格式不正確！
*/
function checkEmail(str)
{
    //如果为空，则通过校验
    if(str == "")
        return true;
    if (str.charAt(0) == "." || str.charAt(0) == "@" || str.indexOf('@', 0) == -1
        || str.indexOf('.', 0) == -1 || str.lastIndexOf("@") == str.length-1 || str.lastIndexOf(".") == str.length-1)
        return false;
    else
        return true;
}


/********************************** email *****************************************/
/**
*校验字符串是否为合法的浮点型　
*　str：被处理的字符串；intLen: 整数位数；floatLen：小数位数；
* 合法,返回1;
* 不合法,返回0;
*/

function isfloat(str,intLen,floatLen){
  var str = str;
  var intLen = intLen;
  var floatLen = floatLen;
  if (str.indexOf(".")<0) { //如果为整数
	if (str.length>4)
		return 0;
	else
		return 1;
  } else {
	  if ((parseInt(str.indexOf(".")) > parseInt(intLen)) || (parseInt((str.length)-(str.indexOf("."))-1) > parseInt(floatLen)))
		return 0;
	  else
		return 1;
	}
}


/********************************** 判断是否为字母 *****************************************/
/**
*校验是否为26个英文字母,不区别大小写　
* 合法,返回 true;
* 不合法,返回 false;
*/
function IsEnLetter(objStr,size) {
  var reg;
    objStr=objStr.toString();
  if((size==null)||(jtrim(size)=="")){
    size="UL";
  } else {
    size=size.toUpperCase();
  }

  switch(size) {
    case "UL"://大小写
    reg=/^[A-Za-z]+$/;
    break;
    case "U": //大写
    reg=/^[A-Z]+$/;
    break;
    case "L": //小写
    reg=/^[a-z]+$/;
    break;
    default:
//    alert("检查大小写参数，只可为(空、UL、U、L)");
    return false;
    break;
  }

  var r=objStr.match(reg);
  if(r==null) {
    return false;
  } else{
    return true;
  }
}

/********************************** 判断文本域所有字符的长度　汉字占两字节 *****************************************/
/**
*结果返回值的长度　
*/
function textLen(strTemp)
{
 var i,sum;
 sum=0;
 for(i=0;i<strTemp.length;i++)
 {
  if ((strTemp.charCodeAt(i)>=0) && (strTemp.charCodeAt(i)<=255))
   sum=sum+1;
  else
   sum=sum+2;
 }
 return sum;
}

/***************************************************************************/
//函数名：fucPWDchk
//功能介绍：检查是否含有非数字或字母
//参数说明：要检查的字符串
//返回值：0：含有非法字符  1：全部为数字或字母

function fucPWDchk(str)
{
  var strSource ="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  var ch;
  var i;
  var temp;

  for (i=0;i<=(str.length-1);i++)
  {
    ch = str.charAt(i);
    temp = strSource.indexOf(ch);
    if (temp==-1)
    {
     return 0;
    }
  }
  if (strSource.indexOf(ch)==-1)
    return 0;
  else
    return 1;
}

