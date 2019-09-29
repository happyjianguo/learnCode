/***************************************************************************************************\
*																									*
* Author : Tam CK																					*
* Version 1.0																						*
* Date created : 29/09/2000																			*
*																									*
* Version 1.1																						*
* Done by : Tam CK																					*
* Date Modify : 07/10/2000																			*

* Purpose : To enable the checking to be done to the object that belong to the form being			*
*			submitted. Previously, checking is done to all the objects on the page,	regardless of	*
*			how many forms are there on the page.													*
*																									*
* Version 1.2																						*
* Done by : Tam CK																					*
* Date Modify : 10/10/2000																			*
* Purpose : Added some method for each type of control such as String, Checkbox, Radio button...	*
*																									*
* Version 1.3																						*
* Done by : Tam CK																					*
* Date Modify : 10/10/2000																			*
* Purpose : Modified function name to avoid	duplicate function name. Netscape does not support 		*
*			overloading in JavaScript. Changes are as follow :										*
*			1. checkSelectionBox => checkSelectionBoxField											*
*			2. checkInteger => checkIntegerField													*
*			3. checkDouble => checkDoubleField														*
*			4. checkPassword => checkPasswordField													*
*			5. checkConfirmPassword => checkConfirmPasswordField									*
*			6. checkRadio => checkRadioField														*
*			7. checkCheckBox => checkCheckBoxField													*
*			8. checkDate => checkDateField															*
*			9. checkEmail => checkEmailField														*
*			10. checkString => checkStringField														*
*			11. function 'validateForm(sFormName)' has been remarked to avoid duplicate function	*
*			name.																					*
*																									*
* Version 1.4																						*
* Done by : Tam CK																					*
* Date Modify : 30/10/2000																			*
* Purpose : Changes in date format. Initially, the acceptable date format is 'yyyy/mm/dd'. This 	*
*			is changed to 'dd/mm/yyyy'.																*
*																									*
* Version 1.5																						*
* Done by : Tam CK																					*
* Date Modify : 29/11/2000																			*
* Purpose : Bug fix in date validation checking.													*
*																									*
* Version 1.6																						*
* Done by : Tam CK																					*
* Date Modify : 14/12/2000																			*
* Purpose : Bug fix in check double value		.													*
*																									*
* Version 1.7
* Done by : Philip Wong
* Date Modify : 31/08/2001
* Purpose : Added function checkAlphaNumeric
*
* Version 1.8
* Done by : David Leung
* Date Modify : 31/08/2001
* Purpose : Added function isWhiteSpace, compareDate
*
* Version 1.9
* Done by : Philip Wong
* Date Modify : 31/08/2001
* Purpose : Added function trim
*
* Version 1.10
* Done by : Nick Kwok
* Date Modify : 23/08/2001
* Purpose : Added function isKeyboardCharacter
*
\***************************************************************************************************/

var alphaNumericPattern = /[^A-Za-z0-9]/
var emailPattern = /^\w+((-\w+)|(\.\w+)|(\/\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/
var datePattern = /\d\d\/\d\d\/\d\d\d\d/
var shortDatePattern = /\d\d\/\d\d\d\d/
var integerPattern = /[^0-9]+/
var doublePattern = /[^0-9.]+/
var timePattern = /\d\d\:\d\d/

var DROPDOWNBOX = "dropdownbox"
var SELECTIONBOX = "selectionbox"
var INTEGER = "integer"
var DOUBLE = "double"
var PASSWORD = "password"
var CONFIRMPASSWORD = "confirmpassword"
var RADIO = "radio"
var CHECKBOX = "checkbox"
var DATE = "date"
var EMAIL = "email"
var STRING = "string"

var formControlArr = new Array()
var ctrlCounter = 0

var whitespace = " \t\n\r";

function isEmpty(s) {
    var str = trim(s)	
    return ((str == null) || (str.length == 0))
}

/**************************************************
* This function will validate the entered string. This function will validate
* the string is space, null or not.
*
* Parameter :
* - s ==> the entered string
*
* Return :
* - true ==> if the string is null or is a whitespace.
* - false ==> if the string is not null and is not a whitespace.
*
**************************************************/
function isWhitespace (s) {
    var i;

    if (isEmpty(s)) return true;

    for (i = 0; i < s.length; i++) {
        // Check that current character isn't whitespace.
        var c = s.charAt(i);

        if (whitespace.indexOf(c) == -1)
            return false;
    }

    // All characters are whitespace.
    return true;
}

/*******************
* This function will compare 2 dates.
*
* Parameter :
* dateString1 and dateString2 should be in 'dd/mm/yyyy' format
*
* return -1 if dateString1 is earlier than dateString2
*         0 if dateString1 is same as dateString2
*         1 if dateString1 is later than dateString2
********************/

var EARLIER = -1
var SAME = 0
var LATER = 1

function convertDate(dateString) {
	var iDay = parseInt(dateString.substr(0, 2), 10)
	var iMonth = parseInt(dateString.substr(3, 2), 10) - 1
	var iYear = parseInt(dateString.substr(6, 4), 10)
//	alert("year == " + iYear + " iMonth == " + iMonth + " iDay == " + iDay)
	return(new Date(iYear, iMonth, iDay))
}

function compareDate(dateString1, dateString2) {
	var date1 = convertDate(dateString1)
	var date2 = convertDate(dateString2)

	if (date1-date2 < 0)
		return EARLIER
	else if(date1-date2 == 0)
		return SAME
	else
		return LATER
}

/**************************************************
* This function will validate the entered date string. This function will validate
* the date to be in the form of 'yyyy/mm/dd'.
*
* Parameter :
* - value ==> the entered date string
*
* Return :
* - true ==> if the date is in the form of 'dd/mm/yyyy' and the date value is valid.
* - false ==> if the date is not in the form of 'dd/mm/yyyy' or the date value is invalid
*             such as '1900/13/45'.
*
**************************************************/
function checkDate(value)
{
	var daysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
	var status = checkPattern(value, datePattern)

	if(!status)
		return false

	var iDay = parseInt(value.substr(0, 2), 10)
	var iMonth = parseInt(value.substr(3, 2), 10)
	var iYear = parseInt(value.substr(6, 4), 10)

	if(iMonth < 1 || iMonth > 12)
		return false

	var maxDay = iMonth != 2 ? daysInMonth[iMonth - 1] :
		((iYear % 4 == 0 && iYear % 100 != 0 || iYear % 400 == 0) ? 29 : 28)

	if(iDay > 0 && iDay <= maxDay)
		return true

	return false
}

/**************************************************
* This function will validate the entered date string. This function will validate
* the date to be in the form of 'mm/yyyy'.
*
* Parameter :
* - value ==> the entered date string
*
* Return :
* - true ==> if the date is in the form of 'mm/yyyy' and the date value is valid.
* - false ==> if the date is not in the form of 'yyyy/mm' or the date value is invalid
*             such as '1900/13'.
*
**************************************************/
function checkShortDate(value) {
    var status = checkPattern(value, shortDatePattern)

    if (!status)
	return false

    var iMonth = parseInt(value.substr(0, 2), 10)
    var iYear = parseInt(value.substr(3, 4), 10)

    if (iMonth < 1 || iMonth > 12)
	return false

    return true;
}

/**************************************************
*
* This function will validate the entered Email string. Valid Email string is in the
* following formats :
*
* alphanumeric@alphanumeric.alphanumeric
* alphanumeric.alphanumeric@alphanumeric.alphanumeric
* alphanumeric-alphanumeric@alphanumeric.alphanumeric
* alphanumeric/alphanumeric@alphanumeric.alphanumeric
*
* alphanumeric == 0-9, a-z, A-Z, '_'
* Parameter :
* - value ==> the entered Email string
*
* Return :
* - true ==> if the entered Email string does match the above patterns
* - false ==> if the entered Email string does match the above patterns.
*
**************************************************/
function checkEmail(value)
{
	return checkPattern(value, emailPattern)
}


/**************************************************
*
* This function will validate the entered Integer string. Valid Integer string contains
* numbers only. Any string that contains character other than numeric characters (0-9) are
* considered as invalid. If the Integer string contains whitespace character (such as space,
* tab) are considered as invalid as well.
*
* Parameter :
* - value ==> the entered Integer string
*
* Return :
* - true ==> if the entered Integer string contains only numeric characters
* - false ==> if the entered Integer string contains any characters other than the numeric
*             characters.
*
**************************************************/
function checkInteger(value)
{
	if(checkPattern(value, integerPattern))
		return false

	return true
}


/**************************************************
*
* This function will validate the entered Double string. Valid Integer string contains
* numbers only and 'a decimal' point. Any string that contains character other than the
* numeric characters (0-9, '.') are considered as invalid. If the Double string contains
* whitespace character (such as space, tab) are considered as invalid as well. If the Double
* string contains two decimal point, it will be considered invalid.
*
* Parameter :
* - value ==> the entered Double string
*
* Return :
* - true ==> if the entered Double string contains only numeric characters and a decimal
*            point.
* - false ==> if the entered Double string contains other alphanumeric characters.
*
**************************************************/
function checkDouble(value)
{
	if(checkPattern(value, doublePattern))
		return false

	var pos = 0
	var currPos = 0
	var count = 0


	do
	{
		pos = value.indexOf(".", currPos)
		currPos = pos + 1

		if(pos != -1)
		{
			count++
			if(count > 1)

				return false
		}
	}while(pos != -1)

	return true
}


/**************************************************
*
* This function is used to check against Radio buttons and Checkboxes. The main checking is
* to determine whether the group of Radio buttons and Checkboxes has been selected. If non of
* the Radio buttons and the Checkboxes is selected, this function will return false.
*
* This function is able to check one or a group of Radio buttons or Checkboxes. A list of
* Checkboxes will be used in form that has a list of objects (for example, a list of
* Category), and the user is allowed to delete a list of the objects. This checking will be
* used to check at least one object is selected for deletion, else, an error will be prompted.
*
* Parameter :
* - form ==> the form object where the Radio buttons or the Checkboxes is located.
* - name ==> the name of the Radio buttons or the Checkboxes.
*
* Return :
* - true ==> if at least one object from the group of Radio buttons or the Checkboxes is
*            selected.
* - false ==> if there is no selected object.
*
**************************************************/
function checkRadioOrCheck(form, name)
{
	var formElements = form.elements

	for(var i=0; i<formElements.length; i++)
	{
		element = formElements[i]

		if(element.name != name)
			continue

		if(element.checked)
			return true
	}

	return false
}


/**************************************************
*
* This function is used to check against Selection Box, whether the user has selected at least
* an item from the Selection box. The Selection box actually refering to Dropdown box and
* List box. For Dropdown box, the function assuming the first element is not suppose to be
* selected. For a Dropdown box, the first element will be a message like 'Please Select' to
* highlight to the user to select an item from the Dropdown box.
*
* As for List box, all the elements in the List box are selectable.
*
* Parameter :
* - selectbox ==> the Selection Box object, a Dropdown box or a List box
* - type ==> a string indicator to determine whether the 'selectbox' is a Dropdown box or
*            a List box.
*
* Return :
* - true ==> if at least one item in the 'selectbox' is selected.
* - false ==> if there is no item being selected.
*
**************************************************/
function checkSelect(selectbox, type)
{
	if(selectbox.selectedIndex < 0)
		return false

	if(type == DROPDOWNBOX && selectbox.selectedIndex <= 0)
		return false

	return true
}


/**************************************************
*
* This function is used to compare a string against a pattern.
*
* Parameter :
* - value ==> the string to be compare.
* - pattern ==> the pattern to match against the 'value'
*
* Return :
* - true ==> if the string matches the pattern.
* - false ==> if the string does not match the pattern.
*
**************************************************/
function checkPattern(value, pattern)
{
	if(value.search(pattern) != -1)
		return true
	else
		return false
}


/**************************************************
*
* This is a constructor function for the object 'FieldToBeChecked'. This object will store all
* the information about the fields that are going to be validated.
*
* Parameter :
* - sName ==> name of the field
* - sPrettyName ==> pretty name, used in the error message.
* - sFormName ==> the form name that contains the field.
* - iMin ==> minimum length of the field. (not used for Radio button, CheckBox, and
*            Selection Box)
*
* - iMax ==> maximum length of the field. (not used for Radio button, CheckBox, and
*            Selection Box)
* - sType ==> a string to determine the type of field.
* - bAllowNull ==> a boolean field indicating the field allow null value or not.
*                  true = allow null value
*                  false = null value not allow
*
* Return :
*
**************************************************/
function FieldToBeChecked(sName, sPrettyName, sFormName, iMin, iMax, sType, bAllowNull)
{
	this.name = sName
	this.prettyName = sPrettyName
	this.formName = sFormName
	this.minLen = iMin
	this.maxLen = iMax
	this.type = sType
	this.allowNull = bAllowNull
}


/**************************************************
*
* To invoke checking on a Drop Down Box.
*
* Parameter :
* - sName ==> name of the field
* - sPrettyName ==> pretty name, used in the error message.
* - sFormName ==> the form name that contains the field.
* - bAllowNull ==> a boolean field indicating the field allow null value or not.
*                  true = allow null value
*                  false = null value not allow
*
* Return :
*
**************************************************/
function checkDropDownBoxField(sName, sPrettyName, sFormName, bAllowNull)
{
	checkField(sName, sPrettyName, sFormName, 0, 0, DROPDOWNBOX, bAllowNull)
}


/**************************************************
*
* To invoke checking on a Selection Box.
*
* Parameter :
* - sName ==> name of the field
* - sPrettyName ==> pretty name, used in the error message.
* - sFormName ==> the form name that contains the field.
* - bAllowNull ==> a boolean field indicating the field allow null value or not.
*                  true = allow null value
*                  false = null value not allow
*
* Return :
*
**************************************************/
function checkSelectionBoxField(sName, sPrettyName, sFormName, bAllowNull)
{
	checkField(sName, sPrettyName, sFormName, 0, 0, SELECTIONBOX, bAllowNull)
}


/**************************************************
*
* To invoke checking on an Integer field.
*
* Parameter :
* - sName ==> name of the field
* - sPrettyName ==> pretty name, used in the error message.
* - sFormName ==> the form name that contains the field.
* - iMin ==> minimum length of the field. (not used for Radio button, CheckBox, and
*            Selection Box)
*
* - iMax ==> maximum length of the field. (not used for Radio button, CheckBox, and
*            Selection Box)
* - bAllowNull ==> a boolean field indicating the field allow null value or not.
*                  true = allow null value
*                  false = null value not allow
*
* Return :
*
**************************************************/
function checkIntegerField(sName, sPrettyName, sFormName, iMin, iMax, bAllowNull)
{
	checkField(sName, sPrettyName, sFormName, iMin, iMax, INTEGER, bAllowNull)
}


/**************************************************
*
* To invoke checking on a Double field.
*
* Parameter :
* - sName ==> name of the field
* - sPrettyName ==> pretty name, used in the error message.
* - sFormName ==> the form name that contains the field.
* - iMin ==> minimum length of the field. (not used for Radio button, CheckBox, and
*            Selection Box)
*
* - iMax ==> maximum length of the field. (not used for Radio button, CheckBox, and
*            Selection Box)
* - bAllowNull ==> a boolean field indicating the field allow null value or not.
*                  true = allow null value
*                  false = null value not allow
*
* Return :
*
**************************************************/
function checkDoubleField(sName, sPrettyName, sFormName, iMin, iMax, bAllowNull)
{
	checkField(sName, sPrettyName, sFormName, iMin, iMax, DOUBLE, bAllowNull)
}


/**************************************************
*
* To invoke checking on a Password field.
*
* Parameter :
* - sName ==> name of the field
* - sPrettyName ==> pretty name, used in the error message.
* - sFormName ==> the form name that contains the field.
* - iMin ==> minimum length of the field. (not used for Radio button, CheckBox, and
*            Selection Box)
*
* - iMax ==> maximum length of the field. (not used for Radio button, CheckBox, and
*            Selection Box)
* - bAllowNull ==> a boolean field indicating the field allow null value or not.

*                  true = allow null value
*                  false = null value not allow
*
* Return :
*
**************************************************/
function checkPasswordField(sName, sPrettyName, sFormName, iMin, iMax, bAllowNull)
{
	checkField(sName, sPrettyName, sFormName, iMin, iMax, PASSWORD, bAllowNull)
}


/**************************************************
*
* To invoke checking on a Confirm Password field.
*
* Parameter :
* - sName ==> name of the field
* - sPrettyName ==> pretty name, used in the error message.
* - sFormName ==> the form name that contains the field.
* - iMin ==> minimum length of the field. (not used for Radio button, CheckBox, and
*            Selection Box)
*
* - iMax ==> maximum length of the field. (not used for Radio button, CheckBox, and
*            Selection Box)
* - bAllowNull ==> a boolean field indicating the field allow null value or not.
*                  true = allow null value
*                  false = null value not allow
*
* Return :
*
**************************************************/
function checkConfirmPasswordField(sName, sPrettyName, sFormName, iMin, iMax, bAllowNull)
{
	checkField(sName, sPrettyName, sFormName, iMin, iMax, CONFIRMPASSWORD, bAllowNull)
}


/**************************************************
*
* To invoke checking on Radio buttons.
*
* Parameter :
* - sName ==> name of the field
* - sPrettyName ==> pretty name, used in the error message.
* - sFormName ==> the form name that contains the field.
* - bAllowNull ==> a boolean field indicating the field allow null value or not.
*                  true = allow null value
*                  false = null value not allow
*
* Return :
*
**************************************************/
function checkRadioField(sName, sPrettyName, sFormName, bAllowNull)
{
	checkField(sName, sPrettyName, sFormName, 0, 0, RADIO, bAllowNull)
}


/**************************************************
*
* To invoke checking on Check Boxes.
*
* Parameter :
* - sName ==> name of the field
* - sPrettyName ==> pretty name, used in the error message.
* - sFormName ==> the form name that contains the field.
* - bAllowNull ==> a boolean field indicating the field allow null value or not.
*                  true = allow null value
*                  false = null value not allow
*
* Return :
*
**************************************************/
function checkCheckBoxField(sName, sPrettyName, sFormName, bAllowNull)
{
	checkField(sName, sPrettyName, sFormName, 0, 0, CHECKBOX, bAllowNull)
}


/**************************************************
*
* To invoke checking on a Date field.
*
* Parameter :
* - sName ==> name of the field
* - sPrettyName ==> pretty name, used in the error message.
* - sFormName ==> the form name that contains the field.
* - bAllowNull ==> a boolean field indicating the field allow null value or not.
*                  true = allow null value
*                  false = null value not allow
*
* Return :
*
**************************************************/
function checkDateField(sName, sPrettyName, sFormName, bAllowNull)
{
	checkField(sName, sPrettyName, sFormName, 10, 10, DATE, bAllowNull)
}


/**************************************************
*
* To invoke checking on an Email field.
*
* Parameter :
* - sName ==> name of the field
* - sPrettyName ==> pretty name, used in the error message.
* - sFormName ==> the form name that contains the field.
* - iMin ==> minimum length of the field. (not used for Radio button, CheckBox, and
*            Selection Box)
*
* - iMax ==> maximum length of the field. (not used for Radio button, CheckBox, and
*            Selection Box)
* - bAllowNull ==> a boolean field indicating the field allow null value or not.
*                  true = allow null value
*                  false = null value not allow
*
* Return :
*
**************************************************/
function checkEmailField(sName, sPrettyName, sFormName, iMin, iMax, bAllowNull)
{
	checkField(sName, sPrettyName, sFormName, iMin, iMax, EMAIL, bAllowNull)
}


/**************************************************
*
* To invoke checking on a String field.
*
* Parameter :
* - sName ==> name of the field
* - sPrettyName ==> pretty name, used in the error message.
* - sFormName ==> the form name that contains the field.
* - iMin ==> minimum length of the field. (not used for Radio button, CheckBox, and
*            Selection Box)
*
* - iMax ==> maximum length of the field. (not used for Radio button, CheckBox, and
*            Selection Box)
* - bAllowNull ==> a boolean field indicating the field allow null value or not.
*                  true = allow null value
*                  false = null value not allow
*
* Return :
*
**************************************************/
function checkStringField(sName, sPrettyName, sFormName, iMin, iMax, bAllowNull)
{
	checkField(sName, sPrettyName, sFormName, iMin, iMax, STRING, bAllowNull)
}


/**************************************************
*
* This function is used to create a FieldToBeChecked object and add the object into
* 'formControlArr' - an array that stores all the field data that is going to be validated.
*
* Parameter :
* - sName ==> name of the field
* - sPrettyName ==> pretty name, used in the error message.
* - sFormName ==> the form name that contains the field.
* - iMin ==> minimum length of the field.
* - iMax ==> maximum length of the field.
* - sType ==> string field to determine the type of field.
* - bAllowNull ==> a boolean field indicating the field allow null value or not.
*                  true = allow null value
*                  false = null value not allow
*
* Return :
*
**************************************************/
function checkField(sName, sPrettyName, sFormName, iMin, iMax, sType, bAllowNull)
{
	var fieldToBeChecked = new FieldToBeChecked(sName, sPrettyName, sFormName, iMin, iMax, sType, bAllowNull)
	formControlArr[ctrlCounter++] = fieldToBeChecked
}


/**************************************************
*
* An overloaded function to call to the 'validateForm' function. This function is called if
* the 'type', 'module' and 'purpose' is not going to be changed.
*
* Parameter :
* - aForm ==> the form object
*
* Return :
*
**************************************************/
/* does not work in Netscape because Netscape can not have two functions with the same name
function validateForm(sFormName)
{
	validateForm(sFormName, "", "", "")
}
*/


/**************************************************
*
* This function is used to update the 'type', 'module' and 'purpose', validate all the fields
* and if all the fields are valid, the form is submitted.
*
* Oftenly, a form can perform several function, for example, delete a list of objects,
* activate a list objects and deactivate a list of objects. In this situation, this function
* can help in passing different 'type',* 'module' and 'purpose' when the form is submitted.
*
* For example,
*
* <input type="button" name="Delete" value="Delete Category"
*   onclick="javascript:validateForm(this.form, 'iPC', 'CATEGORY', 'DELETE')">
*
* <input type="button" name="Activate" value="Activate Category"
*   onclick="javascript:validateForm(this.form, 'iPC', 'CATEGORY', 'ACTIVATE')">
*
* <input type="button" name="Deactivate" value="Deactivate Category"
*   onclick="javascript:validateForm(this.form, 'iPC', 'CATEGORY', 'DEACTIVATE')">
*
* The above method can achieve the purpose of passing different purpose string when
* submitting the form.
*
* However, this function assumes that the form must have 3 hidden variable name 'type',
* 'module' and 'purpose'.
*
* Parameter :
* - aForm ==> the form object
* - type ==> type of presentation layer. Now is limited to JSP, which is 'iPC'.
* - module ==> module name.
* - purpose ==> purpose to request.
*
* Return :
*
**************************************************/
function validateForm(sFormName, type, module, purpose)
{
	var password = ""
	var confirmPassword = ""
	var chkPwd= false
	var pwdAllowNull = false
	var errMessage = ""
	var status = false
	aForm = document.forms[sFormName]

	// Detect IE Browser
	if(document.all)
	{
		if(type != null && type != "")
			aForm.type.value = type

		if(module != null && module != "")
			aForm.module.value = module

		if(purpose != null && purpose != "")
			aForm.purpose.value = purpose
	}
	// Detect Netscape Browser
	else
	{
		if(type != undefined && type != "")
			aForm.type.value = type

		if(module != undefined && module != "")
			aForm.module.value = module

		if(purpose != undefined && purpose != "")
			aForm.purpose.value = purpose
	}




	for(var i=0; i<formControlArr.length; i++)
	{
		currControl = formControlArr[i];

		if(currControl.formName != sFormName)
			continue

		value = aForm[currControl.name].value
		sType = currControl.type

		switch(sType)
		{
			case "dropdownbox" :
			case "selectionbox" :
				if(currControl.allowNull)
					continue

				status = checkSelect(aForm[currControl.name], sType)

				if(!status)
					errMessage += currControl.prettyName + " is not selected\n"

				break

			case "integer" :
			case "double" :
			case "date" :
			case "email" :
			case "string" :
				if(value == "")
				{
					if(currControl.allowNull)
						continue

					errMessage += currControl.prettyName + " is empty\n"
					break
				}

				if(value.length < currControl.minLen)
				{
					errMessage += currControl.prettyName + " should has minimum " +
						currControl.minLen + " characters\n"
					break
				}

				if(value.length > currControl.maxLen)
				{
					errMessage += currControl.prettyName + " should not more than " +
						currControl.maxLen + " characters\n"
					break
				}

				switch(sType)
				{
					case "integer" :
						status = checkInteger(value)
						break
					case "double" :
						status = checkDouble(value)
						break
					case "date" :
						status = checkDate(value)
						break
					case "email" :
						status = checkEmail(value)
						break
					case "string" :
						status = true
						break
				}

				if(!status)
					errMessage += currControl.prettyName + " is invalid\n"

				break

			case "password" :
			case "confirmpassword" :
				chkPwd = true
				pwdAllowNull = currControl.allowNull

				if(sType == "password")
					password = value
				else
					confirmPassword = value

				if(pwdAllowNull && value == "")
					break

				if(value == "")
				{
					errMessage += currControl.prettyName + " is empty\n"
					break
				}

				if(value.length < currControl.minLen)
				{
					errMessage += currControl.prettyName + " should has minimum " +
						currControl.minLen + " characters\n"
					break
				}

				if(value.length > currControl.maxLen)
				{
					errMessage += currControl.prettyName + " should not more than " +

						currControl.maxLen + " characters\n"
					break
				}

				break

			case "radio" :
			case "checkbox" :
				if(currControl.allowNull)
					continue

				status = checkRadioOrCheck(aForm, currControl.name)

				if(!status)
					errMessage += currControl.prettyName + " is not selected\n"

				break


				if(value == "")
				{
					if(currControl.allowNull)
						continue

					errMessage += currControl.prettyName + " is empty\n"
					break
				}

				status = checkEmail(value)

				if(!status)
					errMessage += currControl.prettyName + " is invalid\n"

				break
		}
	}

	if(chkPwd)
	{
		if(password != confirmPassword)
			errMessage += "Password not match\n"
	}

	if(errMessage == "")
		aForm.submit()
	else
	{
		errMessage = "The following errors have encountered\n" +
			"-----------------------------------------------------------------\n" + errMessage ;

		alert(errMessage);
	}
}

/*
	function validate_hkid
	parameter : The NAME of the field(HKID and the check digit)
	return : true for a valid HKID, false and alert box for invalid HKID 

*/
function validate_hkid(HKID,HKID_Ext)  
{
  first_char = new Array("0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
		
   if (HKID.value == "") {
      alert("Please enter HKID card no.\n請 輸 入 香 港 身 份 證 號 碼");
      return false;
   };
   if (HKID.value.toUpperCase().match(/^([A-Z]|([A-Z][A-Z]))\d{6}$/) == null) {
      alert("Please enter a valid HKID card no.\n請 輸 入 正 確 的 香 港 身 份 證 號 碼");
      return false;
   };
   if (HKID_Ext.value == "") {
      alert("Please enter a valid HKID card no.\n請 輸 入 正 確 的 香 港 身 份 證 號 碼");
      return false;
   };
   if (HKID_Ext.value.toUpperCase().match(/^[0-9A]$/) == null) {
      alert("Please enter a valid HKID card no.\n請 輸 入 正 確 的 香 港 身 份 證 號 碼");
      return false;
   };
   
   check_char_digit = parseInt(HKID.value.charAt(1));
   if(isNaN(check_char_digit)){
     for(i=0;i<first_char.length;i++){
       if(first_char[i]==HKID.value.toUpperCase().charAt(0)){
         first_char_value=i;
         break;
       }
     }
     for(i=0;i<first_char.length;i++){
       if(first_char[i]==HKID.value.toUpperCase().charAt(0)){
         second_char_value=i;
         break;
       }
     }
     checksum = (first_char_value*2 + second_char_value*3 + 
              HKID.value.charAt(2)*4 + 
              HKID.value.charAt(3)*5 +
              HKID.value.charAt(4)*6 +
              HKID.value.charAt(5)*7 +
              HKID.value.charAt(6)*8 +
              HKID.value.charAt(7)*9 ) % 11;
   }
   else
   {

     for(i=0;i<first_char.length;i++){
       if(first_char[i]==HKID.value.toUpperCase().charAt(0)){
         first_char_value=i;
         break;
       }
     }
     checksum = (36*2 + first_char_value*3 + 
              HKID.value.charAt(1)*4 + 
              HKID.value.charAt(2)*5 +
              HKID.value.charAt(3)*6 +
              HKID.value.charAt(4)*7 +
              HKID.value.charAt(5)*8 +
              HKID.value.charAt(6)*9 ) % 11;
   }
   if (HKID_Ext.value.toUpperCase()==first_char[checksum]){
     // alert("Valid HKID");
     return true;
   }
   else {
     alert("Please enter a valid HKID card no.\n請 輸 入 正 確 的 香 港 身 份 證 號 碼");
     return false;
   }
}


function getAge(dateString,dateType) {
/*
   function getAge
   parameters: dateString dateType
   returns: boolean

   dateString is a date passed as a string in the following
   formats:

   type 1 : 19970529
   type 2 : 970529
   type 3 : 29/05/1997
   type 4 : 29/05/97

   dateType is a numeric integer from 1 to 4, representing
   the type of dateString passed, as defined above.

   Returns string containing the age in years, months and days
   in the format yyy years mm months dd days.
   Returns empty string if dateType is not one of the expected
   values.
*/

    var now = new Date();
    var today = new Date(now.getYear(),now.getMonth(),now.getDate());

    var yearNow = now.getYear();
    var monthNow = now.getMonth();
    var dateNow = now.getDate();
  	if(yearNow<1000) yearNow+=1900;

    if (dateType == 1)
        var dob = new Date(dateString.substring(0,4),
                            dateString.substring(4,6)-1,
                            dateString.substring(6,8));
    else if (dateType == 2)
        var dob = new Date(dateString.substring(0,2),
                            dateString.substring(2,4)-1,
                            dateString.substring(4,6));
    else if (dateType == 3)
        var dob = new Date(dateString.substring(6,10),
                            dateString.substring(3,5)-1,
                            dateString.substring(0,2));
    else if (dateType == 4)
        var dob = new Date(dateString.substring(6,8),
                            dateString.substring(3,5)-1,
                            dateString.substring(0,2));
    else
        return '';

    var yearDob = dob.getYear();
    var monthDob = dob.getMonth();
    var dateDob = dob.getDate();
  	if(yearDob<1000) yearDob+=1900;
    yearAge = yearNow - yearDob;

    if (monthNow >= monthDob)
        var monthAge = monthNow - monthDob;
    else {
        yearAge--;
        var monthAge = 12 + monthNow -monthDob;
    }

    if (dateNow >= dateDob)
        var dateAge = dateNow - dateDob;
    else {
        monthAge--;
        var dateAge = 31 + dateNow - dateDob;

        if (monthAge < 0) {
            monthAge = 11;
            yearAge--; 
        }
    }

	if (yearAge >= '18')
		return true;
	if (yearAge < '18')
		return false;	
}

function checkTime(value)
{
	var status = checkPattern(value, timePattern)

	if(!status)
		return false

	var iHour = parseInt(value.substr(0, 2), 10)
	var iMin = parseInt(value.substr(3, 2), 10)
	var iSec = parseInt(value.substr(6, 2), 10)

	if(iMin < 0 || iMin > 60)
		return false


	if(iHour <0 || iHour >24)
		return false

	if(iSec < 0 || iSec > 60)
		return false
	return true
}

function checkAlphaNumeric(value)
{
	if(checkPattern(value, alphaNumericPattern))
		return false

	return true
}

/*
	Remove trailing blanks from our string.
*/
function trim(str)
{
        // We don't want to trim JUST spaces, but also tabs,
        // line feeds, etc.  Add anything else you want to
        // "trim" here in Whitespace
        var whitespace = new String(" \t\n\r");

        var s = new String(str);

        if (whitespace.indexOf(s.charAt(s.length-1)) != -1) {
            // We have a string with trailing blank(s)...

            var i = s.length - 1;       // Get length of string

            // Iterate from the far right of string until we
            // don't have any more whitespace...
            while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
                i--;


            // Get the substring from the front of the string to
            // where the last non-whitespace character is...
            s = s.substring(0, i+1);
        }

        return s;
}
function isKeyboardCharacter(str)
{
	for(i=0;i<str.length;i++){	
		if (str.charCodeAt(i) > 126)
			return false;
	}
	return true;
}