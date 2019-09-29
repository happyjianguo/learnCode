/*��ҳʱ�����ʾ 070201_wht*/
function showtime() {
	var now = new Date();
	var shortdate = now.getYear()+"��"+(now.getMonth()+1)+"��"+now.getDate()+"��";

	var hours = now.getHours();
	var minutes = now.getMinutes();
	var seconds = now.getSeconds()
	var timeValue = "" + ((hours >12) ? hours :hours)
	timeValue += ((minutes < 10) ? ":0" : ":") + minutes
	timeValue += ((seconds < 10) ? ":0" : ":") + seconds

	var tbox = document.getElementById("timetxt");//timetxt��ʾʱ���label����
	tbox.innerText =shortdate + " " + timeValue;
	timerID = setTimeout("showtime()",1000);
	timerRunning = true;
}
/*end_wht*/

/*ȥ�ո�*/
function jtrim(s) {
	var i,b=0,e=s.length;
	for(i=0;i<s.length;i++)  if(s.charAt(i)!=' '){b=i;break;}
	if(i==s.length)  return  "";
	for(i=s.length-1;i>b;i--)  if(s.charAt(i)!=' '){e=i;break;}
	return s.substring(b,e+1);
}


/*�ж��Ƿ�Ϊ��ֵ��0-9��*/
function IsNum(NUM)
{
var i,j,strTemp;
strTemp="0123456789";
if ( NUM.length== 0)
return 1//û��Ҳ��Ϊ������
for (i=0;i<NUM.length;i++)
{
j=strTemp.indexOf(NUM.charAt(i));
if (j==-1)
{
//˵�����ַ���������
return 0;
}
}
//˵��������
return 1;
}


/**�ж��Ƿ�Ϊ�����͡�0.0
�����,����1;
�������,����0;
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
*У���ַ����Ƿ�Ϊemail��
*����ֵ��
*���Ϊ�գ�����У��ͨ����           ����true
*����ִ�Ϊemail�ͣ�У��ͨ����      ����true
*���email���Ϸ���                ����false    �ο���ʾ��Ϣ��Email�ĸ�ʽ�����_��
*/
function checkEmail(str)
{
    //���Ϊ�գ���ͨ��У��
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
*У���ַ����Ƿ�Ϊ�Ϸ��ĸ����͡�
*��str����������ַ�����intLen: ����λ����floatLen��С��λ����
* �Ϸ�,����1;
* ���Ϸ�,����0;
*/

function isfloat(str,intLen,floatLen){
  var str = str;
  var intLen = intLen;
  var floatLen = floatLen;
  if (str.indexOf(".")<0) { //���Ϊ����
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


/********************************** �ж��Ƿ�Ϊ��ĸ *****************************************/
/**
*У���Ƿ�Ϊ26��Ӣ����ĸ,�������Сд��
* �Ϸ�,���� true;
* ���Ϸ�,���� false;
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
    case "UL"://��Сд
    reg=/^[A-Za-z]+$/;
    break;
    case "U": //��д
    reg=/^[A-Z]+$/;
    break;
    case "L": //Сд
    reg=/^[a-z]+$/;
    break;
    default:
//    alert("����Сд������ֻ��Ϊ(�ա�UL��U��L)");
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

/********************************** �ж��ı��������ַ��ĳ��ȡ�����ռ���ֽ� *****************************************/
/**
*�������ֵ�ĳ��ȡ�
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
//��������fucPWDchk
//���ܽ��ܣ�����Ƿ��з����ֻ���ĸ
//����˵����Ҫ�����ַ���
//����ֵ��0�����зǷ��ַ�  1��ȫ��Ϊ���ֻ���ĸ

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

