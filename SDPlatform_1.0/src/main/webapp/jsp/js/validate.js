// ---------------------------------------------------------
// 本函数用于对sString字符串进行左空格截除
// --------------------------------------------------------
function JHshLTrim(sString)
{
	var sStr,i,iStart,sResult = "";

	sStr = sString.split("");
	iStart = -1 ;
	for (i = 0 ; i < sStr.length ; i++)
	{
		if (sStr[i] != " ")
		{
			iStart = i;
			break;
		}
	}
	if (iStart == -1)
	{
		return "";
	} //表示sString中的所有字符均是空格,则返回空串
	else
	{
		return sString.substring(iStart);
	}
}


// ---------------------------------------------------------
// 本函数用于对sString字符串进行右空格截除
// --------------------------------------------------------
function JHshRTrim(sString)
{
	var sStr,i,sResult = "",sTemp = "";
		// if (sString.length == 0) { return "" ;} // 参数sString是空串
	sStr = sString.split("");
	for (i = sStr.length - 1 ; i >= 0 ; i --) // 将字符串进行倒序
	{
		sResult = sResult + sStr[i];
	}
	sTemp = JHshLTrim(sResult); // 进行字符串前空格截除
	if (sTemp == "") { return "" ;}
	sStr = sTemp.split("");
	sResult = "";
	for (i = sStr.length - 1 ; i >= 0 ; i--) // 将经处理后的字符串再进行倒序
	{
		sResult = sResult + sStr[i];
	}
	return sResult;
}
	
// ---------------------------------------------------------
//  本函数用于对sString字符串进行trim()操作
// --------------------------------------------------------

function JHshTrim(sString)
{
	var strTmp;

	strTmp = JHshRTrim(JHshLTrim(sString));

	return strTmp ;
}

// ---------------------------------------------------------
//  验证输入字符串中是否为空串[
//  验证时除去输入字符串的前后空格]
// --------------------------------------------------------
function RequiredInputExcludeBlank(s)
{
	var tempS = JHshTrim(s);
	if(tempS.length == 0)
	{
		return false;
	}
	else
	{
		return true;	
	}
}

// ---------------------------------------------------------
//  验证输入字符串中是否为空串
//  [验证时不排出空格]
// --------------------------------------------------------
function RequiredInput(s)
{
	if(s.length == 0)
	{
		return false;	
	}
	else
	{
		return true;		
	}
}

// ---------------------------------------------------------
//  对输入数字校验，若有数字之外的字符则返回false
// --------------------------------------------------------
function numericValidate(s)
{
	var charpos = s.search("[^0-9]");
	if(s.length > 0 &&  charpos >= 0)
	{
		return false;
	}
	return true;	
}

//---------------------------------------------------------
//判断是输入字符串中是否有空格
//-----------------------------------------
function stringCheckWithLength(s, maxlen, minlen)//定义字符串,最大长度,最小长度
{
	if(s == null)//如果字符串等于NULL
	{
		return false;
	}
	var checkedString = JHshTrim(s);//效验字符串S里边是否有空格(trim操作)
	if(minlen == null)//如果最小长度为空,
	{                
		if(checkedString.length > maxlen)//则判断TRIM操作长度大于最大长度,如果大于则return false,否则true
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	else//否则判断trim操作长度是否小于最小长度,如果小就false
	{
		if(checkedString.length < minlen)
		{
			return false;
		}
		else
		{
			if(maxlen == null)//否则判断最大长度是否为空,如果为空就true
			{
				return true;
			}
			else//否则
			{
				if(checkedString.length <= maxlen)//判断trim是否小于等于最大长度
				{
					return true;//如果成立运行true;
				}
				else//否则false
				{
					return false;
				}
			}
		}
	}
}