/*************************************************************************************

 ***************************************************************************************/

<!--
// =====================================================================================
// Function:	isBlank(objectValue)
// usage 	: Check whether string is empty.
// input		: objectValue       check value	
// output	: True or False
// =====================================================================================
function isBlank(objectValue) {
	var	len = objectValue.length;
	var i;
	for (i=0; i<len; i++) {
	  if (objectValue.charAt(i) != " ") return false;
	}	
	return true;
}

// =====================================================================================
// Function:	checkDecimals(objectValue, fieldLength, decimals)
// usage 	: Check whether field is valid decimal number.
// input		: objectValue       check value
//				  fieldLength       max field length
//				  decimals          no of decimals
// output	: 0  --> True
//			  -1 --> False
//			  -2 --> False
// Example : onChange="checkDecimals(this.value, 15, 2)"
// =====================================================================================
function checkDecimals(objectValue, fieldLength, decimals) {
	var checkLength;
	var dectext;

	if (objectValue == "") {
		return -2;
	} else if (isNaN(objectValue)) {
		return -2;
	} else {
		if (decimals > 0) checkLength = fieldLength - decimals - 1;
		else checkLength = fieldLength - decimals;

		if (parseInt(objectValue) > Math.pow(10, checkLength) - 1) {
			return -2;
		}

		if (objectValue.indexOf('.') == -1) {
			return 0;
		} else {
			dectext = objectValue.substring(objectValue.indexOf('.')+1, objectValue.length);
			if (dectext.length > decimals) return -1;
			else return 0;
		}
    }
}


// =====================================================================================
// Function:	checkOnlyNumber(objectValue)
// usage 	: Check whether field is valid number only (0123456789).
// input		: objectValue       check value 
// output	: True or False
// Example : onChange="checkOnlyNumber(this.value)"
// =====================================================================================
function checkOnlyNumber(objectValue) {
	var str = objectValue;
	var valid = "0123456789";	// Valid value is only these number
	var temp;
	var err = 0;
	
	for (var i=0; i< str.length; i++) {
		temp = "" + str.substring(i, i+1);
		if (valid.indexOf(temp) == "-1") err = 1;
	}

	if (err == 1) return false;
	else return true;
}

// =====================================================================================
// Function:	checkAlphanumeric(objectValue)
// usage 	: Check whether field is valid alphanumeric only.
// input		: objectValue       check value 
// output	: True or False
// Example : onChange="checkAlphanumeric(this.value)"
// =====================================================================================
function checkAlphanumeric(objectValue) {	
	var str = objectValue;

	for (var i=0; i< str.length; i++) {
		temp = "" + str.substring(i, i+1);
		if (temp.search(/[a-zA-Z0-9]/) == -1)
			return false;

	}
	
	return true;
}

// =====================================================================================
// Function:	checkAlphanumericPlusChar(objectValue, checkValue)
// usage 	: Check whether field is valid alphanumeric only.
// input		: objectValue       check value 
// output	: True or False
// Example : onChange="checkAlphanumericPlusChar(this.value, '_-')"
// =====================================================================================
function checkAlphanumericPlusChar(objectValue, checkValue) {	
	var str = objectValue;
	var valid = checkValue;

	for (var i=0; i< str.length; i++) {
		temp = "" + str.substring(i, i+1);
		if (temp.search(/[a-zA-Z0-9]/) == -1 && valid.indexOf(temp) == -1)
			return false;
	}
	
	return true;
}


// =====================================================================================
// Function:	checkCharacters(objectValue, checkValue)
// usage 	: Check whether field is valid characters only in (checkValue).
// input		: objectValue       check value
//                checkValue        valid value
// output	: True or False
// Example : onChange="checkCharacters(this.value, 'ACDI')"
// =====================================================================================
function checkCharacters(objectValue, checkValue) {
	var str = objectValue;
	var valid = checkValue;	// Valid value is only these characters
	var temp;
	var err = 0;
	
	for (var i=0; i< str.length; i++) {
		temp = "" + str.substring(i, i+1);
		if (valid.indexOf(temp) == "-1") {
			err = 1;
			break;
		}
	}

	if (err == 1) return false;
	else return true;
}

// =====================================================================================
// Function:	checkCharacters(objectValue, checkValue, flag)
// usage 	: Check whether field is valid characters only in (checkValue).
// input		: objectValue       check value
//                checkValue        valid value
//                flag              ture or false  -> can the character to be duplicated
// output	: True or False
// Example : onChange="checkCharacters(this.value, 'ACDI', true)"
// =====================================================================================
function checkCharacters(objectValue, checkValue, flag) {
	var str = objectValue;
	var valid = checkValue;	// Valid value is only these characters
	var temp;
	var err = 0;
	var arr = new Array();
	var index;

	if (flag) {
		return checkCharacters(objectValue, checkValue);
	} else {
		for (var i=0; i<checkValue.length; i++) {
			arr[i] = 0;
		}
		for (var i=0; i< str.length; i++) {
			temp = "" + str.substring(i, i+1);
			index = valid.indexOf(temp);
			if (index == -1) {
				err = 1;
				break;
			} else {
				if (arr[index] == 1) {
					err = 1;
					break;
				} else {
					arr[index] = 1;
				}
			}
		}

		if (err == 1) return false;
		else return true;
	}
}

// =====================================================================================
// Function:	checkExcludeCharacters(objectValue, checkExcludeValue)
// usage 	: Check whether field is valid characters not in (checkExcludeValue).
// input		: objectValue       check value
//                checkExcludeValue valid value
// output	: True or False
// Example : onChange="checkExcludeCharacters(this.value, 'ACDI')"
// =====================================================================================
function checkExcludeCharacters(objectValue, checkExcludeValue) {
	var str = objectValue;
	var valid = checkExcludeValue;	// Valid value is not in these characters
	var temp;
	var err = 0;

	for (var i=0; i< str.length; i++) {
		temp = "" + str.substring(i, i+1);
		if (valid.indexOf(temp) != "-1") {
			err = 1;
			break;
		}
	}

	if (err == 1) return false;
	else return true;
}

// =====================================================================================
// Function:	checkNumberRange(objectValue, beginValue, endValue)
// usage 	: Check whether field is within this range (beginValue, endValue).
// input		: objectValue       check value
//                beginValue        begin value
//                endValue          end Value
// output	: True or False
// Example : onChange="checkNumberRange(this.value, 1, 12)"
// =====================================================================================
function checkNumberRange(objectValue, beginValue, endValue) {
	var temp = parseInt(objectValue);
	
	if (temp >= beginValue && temp <= endValue) return true;
	else return false;
}

// =====================================================================================
//formatDecimal function
//Print the floating point number with certain decimal point.

//Syntax
//formatDecimal(number, boolean, decimal)
//number is the floating point number which will be formatted.

//boolean is used to decide whether add "0" at the end of the floating point number or not.

//decimal is how many decimal point you wnat. (Default is 2)


//Description
//This function will print the floating point number passed in with the decimal point that users need.

//Examples
//formatDecimal("123.2333", true, 2);	will return "123.23".
//formatDecimal("123", true, 2);		will return "123.00".
//formatDecimal("123", false, 2);		will return "123".
//formatDecimal("123.2", true, 2);	will return "123.20".
//formatDecimal("123.2", false, 2);	will return "123.2".
//formatDecimal("123.456", true, 2);	will return "123.46".
//formatDecimal(".235", true, 2);		will return "0.24".
//formatDecimal("0.9999", true, 2);	will return "1.00".
//formatDecimal("0.9999", false, 2);	will return "1".
// =====================================================================================
function formatDecimal(argvalue, addzero, decimaln) {
  var numOfDecimal = (decimaln == null) ? 2 : decimaln;
  var number = 1;

  number = Math.pow(10, numOfDecimal);

  argvalue = Math.round(parseFloat(argvalue) * number) / number;
  // If you're using IE3.x, you will get error with the following line.
  // argvalue = argvalue.toString();
  // It works fine in IE4.
  argvalue = "" + argvalue;

  if (argvalue.indexOf(".") == 0)
    argvalue = "0" + argvalue;

  if (addzero == true) {
    if (argvalue.indexOf(".") == -1)
      argvalue = argvalue + ".";

    while ((argvalue.indexOf(".") + 1) > (argvalue.length - numOfDecimal))
      argvalue = argvalue + "0";
  }

  return argvalue;
}

// =====================================================================================
//formatValue function
//Print a number according to the format specified. Used for currency format.

//Syntax
//formatValue(argvalue, format)
//argvalue is the number which will be formatted.

//format is the format of the result.


//Description
//The number passed in will be formatted according to the format specified by the user. This function is written for formatting a currency number.
//And formatDecimal function is needed.


//Examples
//formatValue(1223.434, "$##,###.##")		will return "$1,223.43"
//formatValue(1223.43, "$##,###.##")		will return "$1,223.43"
//formatValue(1223., "$##,###.##")		will return "$1,223.00"
//formatValue(1223, "$##,###.##")		will return "$1,223.00"
//formatValue(23., "$##,###.##")			will return "$23.00"
//formatValue(23.3, "$##,###.##")		will return "$23.30"
//formatValue(124343423.3, "$###,###,###.##")	will return "$124,343,423.30"
// =====================================================================================
function formatValue(argvalue, format) {
  var numOfDecimal = 0;
  if (format.indexOf(".") != -1) {
    numOfDecimal = format.substring(format.indexOf(".") + 1, format.length).length;
  }
  argvalue = formatDecimal(argvalue, true, numOfDecimal);

  argvalueBeforeDot = argvalue.substring(0, argvalue.indexOf("."));
  retValue = argvalue.substring(argvalue.indexOf("."), argvalue.length);

  strBeforeDot = format.substring(0, format.indexOf("."));

  for (var n = strBeforeDot.length - 1; n >= 0; n--) {
    oneformatchar = strBeforeDot.substring(n, n + 1);
    if (oneformatchar == "#") {
      if (argvalueBeforeDot.length > 0) {
        argvalueonechar = argvalueBeforeDot.substring(argvalueBeforeDot.length - 1, argvalueBeforeDot.length);
        retValue = argvalueonechar + retValue;
        argvalueBeforeDot = argvalueBeforeDot.substring(0, argvalueBeforeDot.length - 1);
      }
    }
    else {
      if (argvalueBeforeDot.length > 0 || n == 0)
        retValue = oneformatchar + retValue;
    }
  }

  return retValue;
}


// =====================================================================================
//isEmail function
//Determine an argument if it is an email address format.

//Syntax
//isEmail(testValue)
//testValue is the value that you want to check.


//Description
//This function only check if the argument has email address's format, i.e. id@some.host, it will not connect to some.host to check if it is valid. There is not any method to validate an email address except you ask the user to key in their correct email address.
//It is better you use trim to remove both the leading and the trailing space/s before you pass the value to this function.


//Examples
//The following return "true".
//isEmail("abc@some.host");

//The followings return "false".
//isEmail("abc");
//isEmail("abc@somehost");
//isEmail("abc@.some.host");
//isEmail("abc@some.host.");
// =====================================================================================
function isEmail(argvalue) {

  if (argvalue.indexOf(" ") != -1)
    return false;
  else if (argvalue.indexOf("@") == -1)
    return false;
  else if (argvalue.indexOf("@") == 0)
    return false;
  else if (argvalue.indexOf("@") == (argvalue.length-1))
    return false;

  // arrayString = argvalue.split("@"); (works only in netscape3 and above.)
  var retSize = customSplit(argvalue, "@", "arrayString");

  if (arrayString[1].indexOf(".") == -1)
    return false;
  else if (arrayString[1].indexOf(".") == 0)
    return false;
  else if (arrayString[1].charAt(arrayString[1].length-1) == ".") {
    return false;
  }

  return true;

}

// =====================================================================================
// Function:	move(direction, list)
// usage 	: move data within the select box.
// input		: direction       "UP" or "DOWN"
//                list            select box object
// output	: 
// Example : onclick="move('UP', this.form.selectBox)"
// =====================================================================================
function move(direction, list) {
	var notSelectedIndex = -1;
	var selectedIndex = notSelectedIndex;
	var swapIndex = notSelectedIndex;
	var firstIndex = 0;
	var lastIndex = list.length - 1;
	var option1, option2;

	for (var i=0; i<list.length; i++) {
		if ((list.options[i] != null) && (list.options[i].selected)) {
			selectedIndex = i;
			break;
		}
	}

	if (selectedIndex > notSelectedIndex) {
		if (direction == "UP") {
			if (selectedIndex != firstIndex) {
				swapIndex = selectedIndex - 1;
			}
		} else {
			if (selectedIndex != lastIndex) {
				swapIndex = selectedIndex + 1;
			}
		}

		if (swapIndex != notSelectedIndex) {
			option1 = new Option();
			option1.value = list.options[selectedIndex].value;
			option1.text = list.options[selectedIndex].text;
			
			option2 = new Option();
			option2.value = list.options[swapIndex].value;
			option2.text = list.options[swapIndex].text;

			list.options[selectedIndex] = option2;
			list.options[swapIndex] = option1;
			list.options[swapIndex].selected = true;
		}
	}
}

function textCounter(field, maxlimit) {
	if (field.value.length > maxlimit) 
	field.value = field.value.substring(0, maxlimit);
}

function trim(intxt){
	rtnString = "";
	txtLen = intxt.length;
	headsp = 0;
	tailsp = txtLen-1;
	if (txtLen==1) {
		if (intxt == " ") rtnString="";
		else rtnString=intxt;
	} else {
		for (var i=0; i<txtLen; i++) {
			if (intxt.substring(i, i+1) == " ") headsp++;
			else break;			
		}
		if (headsp<tailsp) {
			for (var j=txtLen-1; j>=0; j--) {
				if (intxt.substring(j, j+1) == " ") tailsp--;
				else break;
			}
			rtnString = intxt.substring(headsp, tailsp+1);
		}
	}
	return rtnString;
}

function padZero(intxt, size){
	var temp = intxt;
	var padStr = "0";

	for (var i=0; i<size-intxt.length; i++) {
		temp = padStr + temp;
	}

	return temp;
}
-->