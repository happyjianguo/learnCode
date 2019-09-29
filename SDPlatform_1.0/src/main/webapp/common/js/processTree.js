/*
 * $Log: processTree.js,v $
 * Revision 1.1  2006/07/22 07:53:38  yh
 * *** empty log message ***
 *
 * Revision 1.9  2002/07/22 14:06:21  bc6ix
 * fix license path, change version reporting to use 2 digits for each level
 *
 * Revision 1.8  2002/07/07 08:23:07  bc6ix
 * fix line endings
 *
 * Revision 1.7  2002/05/14 16:52:52  bc6ix
 * use CVS Log for revision history
 *
 *
 */

/* ***** BEGIN LICENSE BLOCK *****
 * Licensed under Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * Full Terms at http://bclary.com/lib/js/license/mpl-tri-license.txt
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Netscape code.
 *
 * The Initial Developer of the Original Code is
 * Netscape Corporation.
 * Portions created by the Initial Developer are Copyright (C) 2001
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s): Bob Clary <bclary@netscape.com>
 *
 * ***** END LICENSE BLOCK ***** */
 
 
 
 //To customize the tree, overwrite these variables in the configuration file (demoFramesetNode.js, etc.)
var USETEXTLINKS = 0;
var STARTALLOPEN = 0;
var USEFRAMES = 1;
var USEICONS = 1;
var WRAPTEXT = 0;
var PERSERVESTATE = 0; //backward compatibility
var PRESERVESTATE = 0;
//var "common/menu/images/" = '';

var HIGHLIGHT = 0;
var HIGHLIGHT_COLOR = 'white';
var HIGHLIGHT_BG    = 'blue';
var BUILDALL = 0;
var GLOBALTARGET = "R"; // variable only applicable for addChildren uses


//Other variables
var lastClicked = null;
var lastClickedColor;
var lastClickedBgColor;
var indexOfEntries = new Array 
var nEntries = 0 
var browserVersion = 0 
var selectedFolder=0
var lastOpenedFolder=null
var t=5
var doc = document
var supportsDeferral = false
var cookieCutter = '^' //You can change this if you need to use ^ in your xID or treeID values

doc.yPos = 0
 
 

function xbDetectBrowser()
{
  var oldOnError = window.onerror;
  var element = null;

  window.onerror = null;
  
  // work around bug in xpcdom Mozilla 0.9.1
  window.saveNavigator = window.navigator;

  navigator.OS    = '';
  navigator.version  = parseFloat(navigator.appVersion);
  navigator.org    = '';
  navigator.family  = '';

  var platform;
  if (typeof(window.navigator.platform) != 'undefined')
  {
    platform = window.navigator.platform.toLowerCase();
    if (platform.indexOf('win') != -1)
      navigator.OS = 'win';
    else if (platform.indexOf('mac') != -1)
      navigator.OS = 'mac';
    else if (platform.indexOf('unix') != -1 || platform.indexOf('linux') != -1 || platform.indexOf('sun') != -1)
      navigator.OS = 'nix';
  }

  var i = 0;
  var ua = window.navigator.userAgent.toLowerCase();
  
  if (ua.indexOf('safari') != -1)
  {
    i = ua.indexOf('safari');
    navigator.family = 'safari';
    navigator.org = 'safari';
    navigator.version = parseFloat('0' + ua.substr(i+7), 10);
  }
  else if (ua.indexOf('opera') != -1)
  {
    i = ua.indexOf('opera');
    navigator.family  = 'opera';
    navigator.org    = 'opera';
    navigator.version  = parseFloat('0' + ua.substr(i+6), 10);
  }
  else if ((i = ua.indexOf('msie')) != -1)
  {
    navigator.org    = 'microsoft';
    navigator.version  = parseFloat('0' + ua.substr(i+5), 10);
    
    if (navigator.version < 4)
      navigator.family = 'ie3';
    else
      navigator.family = 'ie4'
  }
  else if (ua.indexOf('gecko') != -1)
  {
    navigator.family = 'gecko';
    var rvStart = ua.indexOf('rv:');
    var rvEnd   = ua.indexOf(')', rvStart);
    var rv      = ua.substring(rvStart+3, rvEnd);
    var rvParts = rv.split('.');
    var rvValue = 0;
    var exp     = 1;

    for (var i = 0; i < rvParts.length; i++)
    {
      var val = parseInt(rvParts[i]);
      rvValue += val / exp;
      exp *= 100;
    }
    navigator.version = rvValue;

    if (ua.indexOf('netscape') != -1)
      navigator.org = 'netscape';
    else if (ua.indexOf('compuserve') != -1)
      navigator.org = 'compuserve';
    else
      navigator.org = 'mozilla';
  }
  else if ((ua.indexOf('mozilla') !=-1) && (ua.indexOf('spoofer')==-1) && (ua.indexOf('compatible') == -1) && (ua.indexOf('opera')==-1)&& (ua.indexOf('webtv')==-1) && (ua.indexOf('hotjava')==-1))
  {
    var is_major = parseFloat(navigator.appVersion);
    
    if (is_major < 4)
      navigator.version = is_major;
    else
    {
      i = ua.lastIndexOf('/')
      navigator.version = parseFloat('0' + ua.substr(i+1), 10);
    }
    navigator.org = 'netscape';
    navigator.family = 'nn' + parseInt(navigator.appVersion);
  }
  else if ((i = ua.indexOf('aol')) != -1 )
  {
    // aol
    navigator.family  = 'aol';
    navigator.org    = 'aol';
    navigator.version  = parseFloat('0' + ua.substr(i+4), 10);
  }
  else if ((i = ua.indexOf('hotjava')) != -1 )
  {
    // hotjava
    navigator.family  = 'hotjava';
    navigator.org    = 'sun';
    navigator.version  = parseFloat(navigator.appVersion);
  }

  window.onerror = oldOnError;
}

xbDetectBrowser();

var params="toolbar=no,location=no,directories=no,status=yes,menubar=yes,scrollbars=yes,resizable=yes,copyhistory=no,";
var win,hwin;

function imgOn(image) {
    document[image].src="/mfadmin/images/"+image+"_over.gif";
}

function moduleOn(gifName ) {
    document[gifName].src="/mfadmin/images/"+gifName+".jpg";
}

function moduleOff(gifName) {
    document[gifName].src="/mfadmin/images/"+gifName+".jpg";
}

function imgOff(image) {
    document[image].src="/mfadmin/images/"+image+".gif";
}


function popup2(url,w,h,name) {
  if (w==null) w=500;
  if (h==null) h=350;
  if (name==null) name="popup";
  window.open(url,"name",params + "width=" + w+",height="+h);
}

function popup(url,w,h,x,y,name) {
  if (w==null) w=500;
  if (h==null) h=350;
  if (name==null) name="popup";
  if (win==null || typeof(win != "object"))
    win=window.open(url,name,params + "width=" + w+",height="+h+",top="+y+",left="+x);
  else
    win.location.href=url;
  win.focus();
}

function loadInParent(url, closeSelf) {
  opener.location = url;
  if (closeSelf) {
    self.close();
    opener.focus();
  }
}

function cancel() {
  if (opener!=null) {
    self.close();
    opener.focus();
  }
}

function checkYear(e) {
  value=e.value;
  window.alert(value);
}

function submitAndClose(url) {
  self.close();
  if (opener!=null){
    opener.location.href=url;
    opener.focus();
  }
}

function execNavLink(form) {
  window.location.href=form.navlinks.value;
}

function execTaskLink(baseurl, message, form) {
  if(message.length > 0) {
    if(verifySelection(form.tasklistid, message)) {
      //window.location.href=baseurl+"?"+form.tasklistid.value;
      return true;
    }
  }
  return false;
}

function setLocation(url) {
  window.location.href=url;
}

function closeWin() {
  if(opener!=null) {
   opener.focus(); self.close();
  } else {
    self.close();
  }
}

function help(url) {
  if(url==null)
    url="/wliconsole/html/adminhelp/index.htm";
  if (hwin==null || typeof(hwin != "object"))
    hwin=window.open(url,"helpwin",params+"700,height=500,top=25,left=25");
  else
    hwin.location.href=url;
  hwin.focus();
}

function verify(obj, msg) {
    var conf = confirm(msg);
    if (conf) {
       document.location.href = obj.href;
    }
}

function verify2(obj, msg, msg2) {
    var conf = confirm(msg);
    var param = obj.href;
    if (conf) {
        var conf2 = confirm(msg2);
        if(conf2){
            obj.document.cert.trmks.value = "true";
            param = param + "&trmks=true";
        }
        else{
            obj.document.cert.trmks.value = "false";
            param = param + "&trmks=false";
        }
       document.location.href = param;
    }
}


function selectAll(cb, f) {
    for(var i=0;i<f.elements.length;i++) {
        if (f.elements[i].type=="checkbox") {
            f.elements[i].checked=cb.checked;
        }
    }
}

function selectAllBox(count) {
    if(count>0)
        document.write("<input type=\"checkbox\" name=\"selallcb\" class=\"headerelement\" onClick=\"selectAll(this,this.form);\">");
    else
        document.write("&nbsp;");
}

function verifySelection(f, message) {
    for(var i=0;i<f.form.elements.length;i++) {
        if (f.form.elements[i].type=="checkbox" && f.form.elements[i].name!="selallcb") {
            if (f.form.elements[i].checked==true)
                return true;
        }
    }
    window.alert(message);
    return false;
}

function verifySingleSelection(f, message) {
    var count = 0;
    for(var i=0;i<f.form.elements.length;i++) {
        if (f.form.elements[i].type=="checkbox" && f.form.elements[i].name!="selallcb") {
            if (f.form.elements[i].checked)
                count++;
        }
    }
    if (count == 0 || count > 1) {
        window.alert(message);
        return false;
    } else {
        return true;
    }
}


function verifyDelete(f, message, delmsg) {
    if (verifySelection(f, message))
        return confirm(delmsg);
    else
        return false;
}

function confirmInstanceCommand(command) {
    return confirm("?????? " + command + " ??????????????????");
}

function confirmInstanceCommand2(command) {
    return confirm("??????  " + command + " ??????????????????");
}

function confirmCommand(msg) {
    return confirm("?????????? " + msg + " ?");
}

// functions related to the chooser element
function cloneOption(option) {
  var out = new Option(option.text,option.value);
  out.selected = option.selected;
  out.defaultSelected = option.defaultSelected;
  return out;
}

function shiftSelected(chosen,howFar) {
  var opts = chosen.options;
  var newopts = new Array(opts.length);
  var start; var end; var incr;
  if (howFar > 0) {
    start = 0; end = newopts.length; incr = 1;
  } else {
    start = newopts.length - 1; end = -1; incr = -1;
  }


  for(var sel=start; sel != end; sel+=incr) {
    if (opts[sel].selected) {
      setAtFirstAvailable(newopts,cloneOption(opts[sel]),sel+howFar,-incr);
    }
  }


  for(var uns=start; uns != end; uns+=incr) {
    if (!opts[uns].selected) {
      setAtFirstAvailable(newopts,cloneOption(opts[uns]),start,incr);
    }
  }


   opts.length = 0;
  for(i=0; i<newopts.length; i++) {
    opts[opts.length] = newopts[i];
  }
}

function setAtFirstAvailable(array,obj,startIndex,incr) {
  if (startIndex < 0) startIndex = 0;
  if (startIndex >= array.length) startIndex = array.length -1;
  for(var xxx=startIndex; xxx>= 0 && xxx<array.length; xxx += incr) {
    if (array[xxx] == null) {
      array[xxx] = obj;
      return;
    }
  }
}

function moveSelected(from,to) {

  newTo = new Array();
  for(i=from.options.length - 1; i >= 0; i--) {
    if (from.options[i].selected) {
      newTo[newTo.length] = cloneOption(from.options[i]);
      from.options[i] = null;
    }
  }


  for(i=to.options.length - 1; i >= 0; i--) {
    newTo[newTo.length] = cloneOption(to.options[i]);

    newTo[newTo.length-1].selected = false;
  }

  to.options.length = 0;

  for(i=newTo.length - 1; i >=0 ; i--) {
    to.options[to.options.length] = newTo[i];
  }
  selectionChanged(to,from);


  //var sorted = 'both';
  if (to.name.indexOf("sorted") >= 0)
  {
    sortOptions(to,"up");
  }
}

function presort(l) {
  if (l.name.indexOf("sorted") >= 0)  {
      sortOptions(l, "up");
      }
}


function updateHiddenChooserField(chosen,hidden) {
  hidden.value='';
  var opts = chosen.options;
  for(var i=0; i<opts.length; i++) {
    hidden.value = hidden.value + opts[i].value;
    if (i<opts.length-1) hidden.value = hidden.value + "\n";
  }
}



function selectionChanged(selectedElement,unselectedElement) {
  for(i=0; i<unselectedElement.options.length; i++) {
    unselectedElement.options[i].selected=false;
  }
  enableButton("movefrom_"+selectedElement.name,
               (selectedElement.selectedIndex != -1));
  enableButton("movefrom_"+unselectedElement.name,
               (unselectedElement.selectedIndex != -1));
}


function enableButton(buttonName,enable) {
  var img = document.images[buttonName];
  if (img == null) return;
  var src = img.src;
  var und = src.lastIndexOf("_disabled.gif");

  if (und != -1) {
    if (enable) img.src = src.substring(0,und)+".gif";
  } else {
    if (!enable) {
      var gif = src.lastIndexOf("_clicked.gif");
      if (gif == -1) gif = src.lastIndexOf(".gif");
      img.src = src.substring(0,gif)+"_disabled.gif";
    }
  }
}

function pushButton(buttonName,push) {
  var img = document.images[buttonName];
  if (img == null) return;
  var src = img.src;
  var und = src.lastIndexOf("_disabled.gif");
  if (und != -1) return false;
  und = src.lastIndexOf("_clicked.gif");

  if (und == -1) {
    var gif = src.lastIndexOf(".gif");
    if (push) img.src = src.substring(0,gif)+"_clicked.gif";
  } else {
      if (!push) img.src = src.substring(0,und)+".gif";
  }
}



function deleteOption(object,index) {
    object.options[index] = null;
}

function addOption(object,text,value) {
    var defaultSelected = false;
    var selected = false;
    var optionName = new Option(text, value, defaultSelected, selected)
    object.options[object.length] = optionName;
    object.options[object.length-1].selected = false;

}

function sortOptions(what,direction) {

	//disable the left button, cuz it would get really confused:
	//enableButton("movefrom_chosen_wl_control_weblogic.management.configuration.WebAppComponentMBean.Targets-Server",false);

    var copyOption = new Array();
    for (var i=0;i<what.options.length;i++)
    {
        copyOption[i] = new Array(what.options[i].value,what.options[i].text);
    }

	if (direction == "up")
	{
    	copyOption.sort(sortingFunctionUp);
    }
    else
    {
    	copyOption.sort(sortingFunctionDown);
    }

    for (var i=what.options.length-1;i>-1;i--)
        deleteOption(what,i);

    for (var i=0;i<copyOption.length;i++)
        addOption(what,copyOption[i][1],copyOption[i][0]);
}

function sortingFunctionUp(a,b)
{
	if (a[1] < b[1]) return -1;
	else if (a[1] > b[1]) return 1;
	else return 0;


}

function sortingFunctionDown(a,b)
{
	if (a[1] > b[1]) return -1;
	else if (a[1] < b[1]) return 1;
	else return 0;


}



function openInSmallWindow( frm, instid, url ) {
//   variableWindow = window.open( url, "new_id", 'toolbar=1,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=1,width=750,height=550,left = 50,top = 50');

   variableWindow = window.open( url, "new_id", 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=350,height=350,left = 50,top = 50');

   return false;
}

function openInWindow( frm, instid, url ) {
   variableWindow = window.open( url, "new_id", 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=0,width=750,height=600,left = 50,top = 50');

//   variableWindow = window.open( url, "new_id", 'toolbar=1,scrollbars=1,location=0,statusbar=1,menubar=1,resizable=1,width=350,height=350,left = 50,top = 50');

   return false;
}

function openInMiddleWindow( frm, instid, url ) {
   variableWindow = window.open( url, "new_id", 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=0,width=500,height=500,left = 50,top = 50');

//   variableWindow = window.open( url, "new_id", 'toolbar=1,scrollbars=1,location=0,statusbar=1,menubar=1,resizable=1,width=350,height=350,left = 50,top = 50');

   return false;
}

// Move calendar rules and role condition rule up or down
// For calendar rules, ignoreFirstSelectionField must be false and
// for role condition rule, ignoreFirstSelectionField must be true.
function moveRules(formName, myLocation, direction, ignoreFirstSelectionField) {
  if (myLocation == 0 && direction == 0) return;

  if (myLocation == -1) return;

  var thisForm = document.forms[formName];
  var rows = 0; //caculate how many rules we have
  for (i=0; i < thisForm.elements.length; i++) {
      var e = thisForm.elements[i];
      if (e.name.search("rules_") == 0){
          rows++;
      }
  }

  var toLocation = (direction == 0) ? myLocation - 1 : myLocation + 1;

  if (toLocation < 0 || toLocation >= rows) return;

  //switch text
  var toTexField = thisForm.elements['rules_'+toLocation];
  var fromTexFiled = thisForm.elements['rules_'+myLocation]
  var tmpTextValue = toTexField.value;
  toTexField.value = fromTexFiled.value;
  fromTexFiled.value = tmpTextValue;

  //switch busy/free for calendar rule and and/or for role condition rule
  // For role condition rule, in condition, the command for the 1st element
  // in the condition does not mean anything.
  // When swich occurs between the 1st and 2nd items, the command of the 2nd item
  // should always preserved as the command of the 2nd item.  So do NOT switch
  if (!((ignoreFirstSelectionField) &&
      (((toLocation == 0) && (myLocation == 1)) || ((toLocation == 1) && (myLocation == 0))))) {
      var toSelectedField = thisForm.elements['list_' + toLocation];
      var fromSelectedField = thisForm.elements['list_' + myLocation];
      var tmpSelectValue = toSelectedField.value;
      toSelectedField.value = fromSelectedField.value;
      fromSelectedField.value = tmpSelectValue;
  }

  //switch hidden
  var toHiddenField = thisForm.elements['hidden_' + toLocation];
  var fromHiddenField = thisForm.elements['hidden_' + myLocation];
  var tmpHiddenValue = toHiddenField.value;
  toHiddenField.value = fromHiddenField.value;
  fromHiddenField.value = tmpHiddenValue;
}

function moveUpList(thisform, name) {
	var listField = thisform.elements[name];
	var myarray  = name.split('_');
	var hiddenfieldname = myarray[1];
	var hidden = thisform.elements[hiddenfieldname];
   if ( listField.length == -1) {  // If the list is empty
   } else {
      var selected = listField.selectedIndex;
      if (selected == -1) {
      } else {  // Something is selected
         if ( listField.length == 0 ) {  // If there's only one in the list
         } else {  // There's more than one in the list, rearrange the list order
            if ( selected == 0 ) {
            } else {
               // Get the text/value of the one directly above the hightlighted entry as
               // well as the highlighted entry; then flip them
               var moveText1 = listField[selected-1].text;
               var moveText2 = listField[selected].text;
               var moveValue1 = listField[selected-1].value;
               var moveValue2 = listField[selected].value;
               listField[selected].text = moveText1;
               listField[selected].value = moveValue1;
               listField[selected-1].text = moveText2;
               listField[selected-1].value = moveValue2;
               listField.selectedIndex = selected-1; // Select the one that was selected before
            }  // Ends the check for selecting one which can be moved
         }  // Ends the check for there only being one in the list to begin with
      }  // Ends the check for there being something selected
   }  // Ends the check for there being none in the list
   updateHiddenChooserField(listField, hidden);
}

function moveDownList(thisform, name) {
	var listField = thisform.elements[name];

	var myarray  = name.split('_');
	var hiddenfieldname = myarray[1];
	var hidden = thisform.elements[hiddenfieldname];

   if ( listField.length == -1) {  // If the list is empty
   } else {
      var selected = listField.selectedIndex;
      if (selected == -1) {
      } else {  // Something is selected
         if ( listField.length == 0 ) {  // If there's only one in the list
         } else {  // There's more than one in the list, rearrange the list order
            if ( selected == listField.length-1 ) {
            } else {
               // Get the text/value of the one directly below the hightlighted entry as
               // well as the highlighted entry; then flip them
               var moveText1 = listField[selected+1].text;
               var moveText2 = listField[selected].text;
               var moveValue1 = listField[selected+1].value;
               var moveValue2 = listField[selected].value;
               listField[selected].text = moveText1;
               listField[selected].value = moveValue1;
               listField[selected+1].text = moveText2;
               listField[selected+1].value = moveValue2;
               listField.selectedIndex = selected+1; // Select the one that was selected before
            }  // Ends the check for selecting one which can be moved
         }  // Ends the check for there only being one in the list to begin with
      }  // Ends the check for there being something selected
   }  // Ends the check for there being none in the list
   updateHiddenChooserField(listField, hidden);
}

function addToList(thisform, name, textbox) {
	var listField = thisform.elements[name];
	var textField = thisform.elements[textbox];
  var newText = textField.value;
  var newValue = textField.value;

	var myarray  = name.split('_');
	var hiddenfieldname = myarray[1];
	var hidden = thisform.elements[hiddenfieldname];


   if ( ( newValue == "" ) || ( newText == "" ) ) {
      alert("You cannot add blank values!");
   } else {
      var len = listField.length++; // Increase the size of list and return the size
      listField.options[len].value = newValue;
      listField.options[len].text = newText;
      listField.selectedIndex = len; // Highlight the one just entered (shows the user that it was entered)
   } // Ends the check to see if the value entered on the form is empty
   updateHiddenChooserField(listField, hidden);
}

function removeFromList(thisform, name) {
	var listField = thisform.elements[name];

	var myarray  = name.split('_');
	var hiddenfieldname = myarray[1];
	var hidden = thisform.elements[hiddenfieldname];


   if ( listField.length == -1) {  // If the list is empty
      alert("There are no values which can be removed!");
   } else {
      var selected = listField.selectedIndex;
      if (selected == -1) {
         alert("You must select an entry to be removed!");
      } else {  // Build arrays with the text and values to remain
         var replaceTextArray = new Array(listField.length-1);
         var replaceValueArray = new Array(listField.length-1);
         for (var i = 0; i < listField.length; i++) {
            // Put everything except the selected one into the array
            if ( i < selected) { replaceTextArray[i] = listField.options[i].text; }
            if ( i > selected ) { replaceTextArray[i-1] = listField.options[i].text; }
            if ( i < selected) { replaceValueArray[i] = listField.options[i].value; }
            if ( i > selected ) { replaceValueArray[i-1] = listField.options[i].value; }
         }
         listField.length = replaceTextArray.length;  // Shorten the input list
         for (i = 0; i < replaceTextArray.length; i++) { // Put the array back into the list
            listField.options[i].value = replaceValueArray[i];
            listField.options[i].text = replaceTextArray[i];
         }
      } // Ends the check to make sure something was selected
   } // Ends the check for there being none in the list
   updateHiddenChooserField(listField, hidden);
}


function reloadPage(thisForm) {
    thisForm.submit();
}

//Used by editwfversioning.jsp to show date for the selection of service URI
function showDate(theForm, name, year, month,dayofMonth, hour, minute, second){
   var delim = "###";
   var str = theForm.elements[name].value;
   var mstr = str.split(delim);
   theForm.elements[year].value = mstr[0];
   theForm.elements[month].value = mstr[1];
   theForm.elements[dayofMonth].value = mstr[2];
   theForm.elements[hour].value = mstr[3];
   theForm.elements[minute].value = mstr[4];
   theForm.elements[second].value = mstr[5];
}


function enableDisable(form, enable, disable, enableStyle, disableStyle) {
    var enableArray = null;
    var disableArray = null;
    var i;
    if (enable != null && enable != "") {
      enableArray = enable.split(",");
      for (i = 0; i < enableArray.length; i++) {
          changeInputElement(form, enableArray[i], true, enableStyle);
      }
    }
    if (disable != null && disable != "") {
      disableArray = disable.split(",");
      for (i = 0; i < disableArray.length; i++) {
          changeInputElement(form, disableArray[i], false, disableStyle);
      }
    }
    return true;
}


function enableWhenChecked(checkboxelement, enable, enableStyle, disableStyle) {
    if (checkboxelement.checked == true) {
        // enable the input boxes if this checkbox is checked
        enableDisable(checkboxelement.form, enable, null, enableStyle, disableStyle);
    } else {
        // otherwise disable them
        enableDisable(checkboxelement.form, null, enable, enableStyle, disableStyle);
    }
}


function changeInputElement(form, paramName, enabled, style) {
  for (var i = 0; i < form.elements.length; i++) {
    var target = form.elements[i];
    if (target.name == paramName || target.name.indexOf(paramName + "_") != -1) {
        if (enabled == false) {
            target.disabled = true;
        } else {
            target.disabled = false;
        }
        // target.className=style;
    }
  }
}



//**************************************************************** 
// Keep this copyright notice: 
// This copy of the script is the property of the owner of the 
// particular web site you were visiting.
// Do not download the script's files from there.
// For a free download and full instructions go to: 
// http://www.treeview.net
//**************************************************************** 

 
// Log of changes: 
//
//      08 Jun 04 - Very small change to one error message
//      21 Mar 04 - Support for folder.addChildren allows for much bigger trees
//      12 May 03 - Support for Safari Beta 3
//      01 Mar 03 - VERSION 4.3 - Support for checkboxes
//      21 Feb 03 - Added support for Opera 7
//      22 Sep 02 - Added maySelect member for node-by-node control
//                  of selection and highlight
//      21 Sep 02 - Cookie values are now separated by cookieCutter
//      12 Sep 02 - VERSION 4.2 - Can highlight Selected Nodes and 
//                  can preserve state through external (DB) IDs
//      29 Aug 02 - Fine tune 'supportDeferral' for IE4 and IE Mac
//      25 Aug 02 - Fixes: STARTALLOPEN, and multi-page frameless
//      09 Aug 02 - Fix repeated folder on Mozilla 1.x
//      31 Jul 02 - VERSION 4.1 - Dramatic speed increase for trees 
//      with hundreds or thousands of nodes; changes to the control
//      flags of the gLnk function
//      18 Jul 02 - Changes in pre-load images function
//      13 Jun 02 - Add "common/menu/images/" var to allow for gif subdir
//      20 Apr 02 - Improve support for frame-less layout
//      07 Apr 02 - Minor changes to support server-side dynamic feeding
//                  (example: FavoritesManagerASP)

 
// Definition of class Folder 
// ***************************************************************** 


function Folder(folderDescription, hreference) //constructor 
{ 
  //constant data 
  this.desc = folderDescription; 
  this.hreference = hreference;
  this.id = -1;
  this.navObj = 0;
  this.iconImg = 0; 
  this.nodeImg = 0;
  this.isLastNode = 0;
  this.iconSrc = "common/menu/images/" + "openfolder.gif";
  this.iconSrcClosed = "common/menu/images/" + "closedfolder.gif";
  this.children = new Array;
  this.nChildren = 0;
  this.level = 0;
  this.leftSideCoded = "";
  this.isLastNode=false;
  this.parentObj = null;
  this.maySelect=true;
  this.prependHTML = ""
 
  //dynamic data 
  this.isOpen = false
  this.isLastOpenedFolder = false
  this.isRendered = 0
 
  //methods 
  this.initialize = initializeFolder 
  this.setState = setStateFolder 
  this.addChild = addChild 
  this.addChildren = addChildren
  this.createIndex = createEntryIndex 
  this.escondeBlock = escondeBlock
  this.esconde = escondeFolder 
  this.folderMstr = folderMstr 
  this.renderOb = drawFolder 
  this.totalHeight = totalHeight 
  this.subEntries = folderSubEntries 
  this.linkHTML = linkFolderHTML
  this.blockStartHTML = blockStartHTML
  this.blockEndHTML = blockEndHTML
  this.nodeImageSrc = nodeImageSrc
  this.iconImageSrc = iconImageSrc
  this.getID = getID
  this.forceOpeningOfAncestorFolders = forceOpeningOfAncestorFolders
} 
 
function initializeFolder(level, lastNode, leftSide) 
{ 
  var j=0 
  var i=0       
  nc = this.nChildren 
   
  this.createIndex() 
  this.level = level
  this.leftSideCoded = leftSide

  if (browserVersion == 0 || STARTALLOPEN==1)
    this.isOpen=true;

  if (level>0)
    if (lastNode) //the last child in the children array 
		leftSide = leftSide + "0"
	else
		leftSide = leftSide + "1"

  this.isLastNode = lastNode
 
  if (nc > 0) 
  { 
    level = level + 1 
    for (i=0 ; i < this.nChildren; i++)  
    {
      if (typeof this.children[i].initialize == 'undefined') //document node was specified using the addChildren function
      {
        if (typeof this.children[i][0] == 'undefined' || typeof this.children[i] == 'string')
        {
          this.children[i] = ["item incorrectly defined", ""];
        }

        //Basic initialization of the Item object
        //These members or methods are needed even before the Item is rendered
        this.children[i].initialize=initializeItem;
        this.children[i].createIndex=createEntryIndex;
        if (typeof this.children[i].maySelect == 'undefined')
          this.children[i].maySelect=true
        this.children[i].forceOpeningOfAncestorFolders = forceOpeningOfAncestorFolders
      }
      if (i == this.nChildren-1) 
        this.children[i].initialize(level, 1, leftSide)
      else 
        this.children[i].initialize(level, 0, leftSide)
    } 
  } 
} 
 
function drawFolder(insertAtObj) 
{ 
  var nodeName = ""
  var auxEv = ""
  var docW = ""
  var i=0

  finalizeCreationOfChildDocs(this)

  var leftSide = leftSideHTML(this.leftSideCoded)

  if (browserVersion > 0) 
    auxEv = "<a href='javascript:clickOnNode(\""+this.getID()+"\")'>" 
  else 
    auxEv = "<a>" 

  nodeName = this.nodeImageSrc()
 
  if (this.level>0) 
    if (this.isLastNode) //the last child in the children array 
	    leftSide = leftSide + "<td valign=top>" + auxEv + "<img name='nodeIcon" + this.id + "' id='nodeIcon" + this.id + "' src='" + nodeName + "' width=16 height=22 border=0></a></td>"
    else 
      leftSide = leftSide + "<td valign=top background=" + "common/menu/images/" + "ftv2vertline.gif>" + auxEv + "<img name='nodeIcon" + this.id + "' id='nodeIcon" + this.id + "' src='" + nodeName + "' width=16 height=22 border=0></a></td>"

  this.isRendered = 1

  if (browserVersion == 2) { 
    if (!doc.yPos) 
      doc.yPos=20 
  } 

  docW = this.blockStartHTML("folder");

  docW = docW + "<tr>" + leftSide + "<td valign=top>";
  if (USEICONS)
  {
    docW = docW + this.linkHTML(false) 
    docW = docW + "<img id='folderIcon" + this.id + "' name='folderIcon" + this.id + "' src='" + this.iconImageSrc() + "' border=0></a>"
  }
  else
  {
	  if (this.prependHTML == "")
        docW = docW + "<img src=" + "common/menu/images/" + "ftv2blank.gif height=2 width=2>"
  }
  if (WRAPTEXT)
	  docW = docW + "</td>"+this.prependHTML+"<td valign=middle width=100%>"
  else
	  docW = docW + "</td>"+this.prependHTML+"<td valign=middle nowrap width=100%>"
  if (USETEXTLINKS) 
  { 
    docW = docW + this.linkHTML(true) 
    docW = docW + this.desc + "</a>"
  } 
  else 
    docW = docW + this.desc
  docW = docW + "</td>"

  docW = docW + this.blockEndHTML()

  if (insertAtObj == null)
  {
	  if (supportsDeferral) {
		  doc.write("<div id=domRoot></div>") //transition between regular flow HTML, and node-insert DOM DHTML
		  insertAtObj = getElById("domRoot")
		  insertAtObj.insertAdjacentHTML("beforeEnd", docW)
	  }
	  else
		  doc.write(docW)
  }
  else
  {
      insertAtObj.insertAdjacentHTML("afterEnd", docW)
  }
 
  if (browserVersion == 2) 
  { 
    this.navObj = doc.layers["folder"+this.id] 
    if (USEICONS)
      this.iconImg = this.navObj.document.images["folderIcon"+this.id] 
    this.nodeImg = this.navObj.document.images["nodeIcon"+this.id] 
    doc.yPos=doc.yPos+this.navObj.clip.height 
  } 
  else if (browserVersion != 0)
  { 
    this.navObj = getElById("folder"+this.id)
    if (USEICONS)
      this.iconImg = getElById("folderIcon"+this.id) 
    this.nodeImg = getElById("nodeIcon"+this.id)
  } 
} 
 
function setStateFolder(isOpen) 
{ 
  var subEntries 
  var totalHeight 
  var fIt = 0 
  var i=0 
  var currentOpen
 
  if (isOpen == this.isOpen) 
    return 
 
  if (browserVersion == 2)  
  { 
    totalHeight = 0 
    for (i=0; i < this.nChildren; i++) 
      totalHeight = totalHeight + this.children[i].navObj.clip.height 
      subEntries = this.subEntries() 
    if (this.isOpen) 
      totalHeight = 0 - totalHeight 
    for (fIt = this.id + subEntries + 1; fIt < nEntries; fIt++) 
      indexOfEntries[fIt].navObj.moveBy(0, totalHeight) 
  }  
  this.isOpen = isOpen;

  if (this.getID()!=foldersTree.getID() && PRESERVESTATE && !this.isOpen) //closing
  {
     currentOpen = GetCookie("clickedFolder")
     if (currentOpen != null) {
         currentOpen = currentOpen.replace(this.getID()+cookieCutter, "")
         SetCookie("clickedFolder", currentOpen)
     }
  }
	
  if (!this.isOpen && this.isLastOpenedfolder)
  {
		lastOpenedFolder = null;
		this.isLastOpenedfolder = false;
  }
  propagateChangesInState(this) 
} 
 
function propagateChangesInState(folder) 
{   
  var i=0 

  //Change icon
  if (folder.nChildren > 0 && folder.level>0)  //otherwise the one given at render stays
    folder.nodeImg.src = folder.nodeImageSrc()

  //Change node
  if (USEICONS)
    folder.iconImg.src = folder.iconImageSrc()

  //Propagate changes
  for (i=folder.nChildren-1; i>=0; i--) {
    if (folder.isOpen) 
      folder.children[i].folderMstr(folder.navObj)
    else 
  	  folder.children[i].esconde() 
  }
} 
 
function escondeFolder() 
{ 
  this.escondeBlock()
   
  this.setState(0) 
} 
 
function linkFolderHTML(isTextLink) 
{ 
  var docW = "";

  if (this.hreference) 
  { 
	if (USEFRAMES)
	  docW = docW + "<a href='" + this.hreference + "' TARGET=\"basefrm\" "
	else
	  docW = docW + "<a href='" + this.hreference + "' "
        
    if (isTextLink) {
        docW += "id=\"itemTextLink"+this.id+"\" ";
    }

    if (browserVersion > 0) 
      docW = docW + "onClick='javascript:clickOnFolder(\""+this.getID()+"\")'"

    docW = docW + ">"
  } 
  else 
    docW = docW + "<a>" 

  return docW;
} 
 
function addChild(childNode) 
{ 
  this.children[this.nChildren] = childNode 
  childNode.parentObj = this
  this.nChildren++ 
  return childNode 
} 

//The list can contain either a Folder object or a sub list with the arguments for Item 
function addChildren(listOfChildren) 
{ 
  this.children = listOfChildren 
  this.nChildren = listOfChildren.length
  for (i=0; i<this.nChildren; i++)
    this.children[i].parentObj = this
} 

function folderSubEntries() 
{ 
  var i = 0 
  var se = this.nChildren 
 
  for (i=0; i < this.nChildren; i++){ 
    if (this.children[i].children) //is a folder 
      se = se + this.children[i].subEntries() 
  } 
 
  return se 
} 

function nodeImageSrc() {
  var srcStr = "";

  if (this.isLastNode) //the last child in the children array 
  { 
    if (this.nChildren == 0)
      srcStr = "common/menu/images/" + "ftv2lastnode.gif"
    else
      if (this.isOpen)
        srcStr = "common/menu/images/" + "ftv2mlastnode.gif"  
      else
        srcStr = "common/menu/images/" + "ftv2plastnode.gif"  
  } 
  else 
  { 
    if (this.nChildren == 0)
      srcStr = "common/menu/images/" + "ftv2node.gif"
    else
      if (this.isOpen)
        srcStr = "common/menu/images/" + "ftv2mnode.gif"
      else
        srcStr = "common/menu/images/" + "ftv2pnode.gif"
  }   
  return srcStr;
}

function iconImageSrc() {
  if (this.isOpen)
    return(this.iconSrc)
  else
    return(this.iconSrcClosed)
} 
 
// Definition of class Item (a document or link inside a Folder) 
// ************************************************************* 
 
function Item(itemDescription) // Constructor 
{ 
  // constant data 
  this.desc = itemDescription 

  this.level = 0
  this.isLastNode = false
  this.leftSideCoded = ""
  this.parentObj = null

  this.maySelect=true

  this.initialize = initializeItem;
  this.createIndex = createEntryIndex;
  this.forceOpeningOfAncestorFolders = forceOpeningOfAncestorFolders;

  finalizeCreationOfItem(this)
} 

//Assignments that can be delayed when the item is created with folder.addChildren
//The assignments that cannot be delayed are done in addChildren and in initializeFolder
//Additionaly, some assignments are also done in finalizeCreationOfChildDocs itself
function finalizeCreationOfItem(itemArray)
{

  itemArray.navObj = 0 //initialized in render() 
  itemArray.iconImg = 0 //initialized in render() 
  itemArray.iconSrc = "common/menu/images/" + "url.gif" 
  itemArray.isRendered = 0
  itemArray.nChildren = 0
  itemArray.prependHTML = ""
 
  // methods 
  itemArray.escondeBlock = escondeBlock
  itemArray.esconde = escondeBlock
  itemArray.folderMstr = folderMstr 
  itemArray.renderOb = drawItem 
  itemArray.totalHeight = totalHeight 
  itemArray.blockStartHTML = blockStartHTML
  itemArray.blockEndHTML = blockEndHTML
  itemArray.getID = getID
}

function initializeItem(level, lastNode, leftSide) 
{  
  this.createIndex() 
  this.level = level
  this.leftSideCoded = leftSide
  this.isLastNode = lastNode
} 
 
function drawItem(insertAtObj) 
{ 
  var leftSide = leftSideHTML(this.leftSideCoded)
  var docW = ""

  var fullLink = "href=\""+this.link+"\" target=\""+this.target+"\" onClick=\"clickOnLink('"+this.getID()+"\', '"+this.link+"','"+this.target+"');return false;\"";
  this.isRendered = 1

  if (this.level>0) 
    if (this.isLastNode) //the last 'brother' in the children array 
    { 
      leftSide = leftSide + "<td valign=top><img src='" + "common/menu/images/" + "ftv2lastnode.gif' width=16 height=22></td>"
    } 
    else 
    { 
      leftSide = leftSide + "<td valign=top background=" + "common/menu/images/" + "ftv2vertline.gif><img src='" + "common/menu/images/" + "ftv2node.gif' width=16 height=22></td>"
    } 

  docW = docW + this.blockStartHTML("item")

  docW = docW + "<tr>" + leftSide + "<td valign=top>"
  if (USEICONS)
      docW = docW + "<a " + fullLink  + " id=\"itemIconLink"+this.id+"\">" + "<img id='itemIcon"+this.id+"' " + "src='"+this.iconSrc+"' border=0>" + "</a>"
  else
	  if (this.prependHTML == "")
        docW = docW + "<img src=" + "common/menu/images/" + "ftv2blank.gif height=2 width=3>"

  if (WRAPTEXT)
    docW = docW + "</td>"+this.prependHTML+"<td valign=middle width=100%>"
  else
    docW = docW + "</td>"+this.prependHTML+"<td valign=middle nowrap width=100%>"

  if (USETEXTLINKS) 
    docW = docW + "<a " + fullLink + " id=\"itemTextLink"+this.id+"\">" + this.desc + "</a>"
  else 
    docW = docW + this.desc

  docW = docW + "</td>"

  docW = docW + this.blockEndHTML()
 
  if (insertAtObj == null)
  {
	  doc.write(docW)
  }
  else
  {
      insertAtObj.insertAdjacentHTML("afterEnd", docW)
  }

  if (browserVersion == 2) { 
    this.navObj = doc.layers["item"+this.id] 
    if (USEICONS)
      this.iconImg = this.navObj.document.images["itemIcon"+this.id] 
    doc.yPos=doc.yPos+this.navObj.clip.height 
  } else if (browserVersion != 0) { 
    this.navObj = getElById("item"+this.id)
    if (USEICONS)
      this.iconImg = getElById("itemIcon"+this.id)
  } 
} 
 
 
// Methods common to both objects (pseudo-inheritance) 
// ******************************************************** 
 
function forceOpeningOfAncestorFolders() {
  if (this.parentObj == null || this.parentObj.isOpen)
    return
  else {
    this.parentObj.forceOpeningOfAncestorFolders()
    clickOnNodeObj(this.parentObj)
  }
}

function escondeBlock() 
{ 
  if (browserVersion == 1 || browserVersion == 3) { 
    if (this.navObj.style.display == "none") 
      return 
    this.navObj.style.display = "none" 
  } else { 
    if (this.navObj.visibility == "hidden") 
      return 
    this.navObj.visibility = "hidden" 
  }     
} 
 
function folderMstr(domObj) 
{ 
  if (browserVersion == 1 || browserVersion == 3) { 
    if (t==-1)
      return
    var str = new String(doc.links[t])
    if (str.slice(14,16) != "em")
      return
  }

  if (!this.isRendered)
     this.renderOb(domObj)
  else
    if (browserVersion == 1 || browserVersion == 3) 
      this.navObj.style.display = "block" 
    else 
      this.navObj.visibility = "show" 
} 

function blockStartHTML(idprefix) {
  var idParam = "id='" + idprefix + this.id + "'"
  var docW = ""

  if (browserVersion == 2) 
    docW = "<layer "+ idParam + " top=" + doc.yPos + " visibility=show>"
  else if (browserVersion != 0)
    docW = "<div " + idParam + " style='display:block; position:block;'>"
     
  docW = docW + "<table border=0 cellspacing=0 cellpadding=0 width=100% >"

  return docW
}

function blockEndHTML() {
  var docW = ""

  docW = "</table>"
   
  if (browserVersion == 2) 
    docW = docW + "</layer>"
  else if (browserVersion != 0)
    docW = docW + "</div>"

  return docW
}
 
function createEntryIndex() 
{ 
  this.id = nEntries 
  indexOfEntries[nEntries] = this 
  nEntries++ 
} 
 
// total height of subEntries open 
function totalHeight() //used with browserVersion == 2 
{ 
  var h = this.navObj.clip.height 
  var i = 0 
   
  if (this.isOpen) //is a folder and _is_ open 
    for (i=0 ; i < this.nChildren; i++)  
      h = h + this.children[i].totalHeight() 
 
  return h 
} 


function leftSideHTML(leftSideCoded) {
	var i;
	var retStr = "";

	for (i=0; i<leftSideCoded.length; i++)
	{
		if (leftSideCoded.charAt(i) == "1")
		{
			retStr = retStr + "<td valign=top background=" + "common/menu/images/" + "ftv2vertline.gif><img src='" + "common/menu/images/" + "ftv2vertline.gif' width=16 height=22></td>"
		}
		if (leftSideCoded.charAt(i) == "0")
		{
			retStr = retStr + "<td valign=top><img src='" + "common/menu/images/" + "ftv2blank.gif' width=16 height=22></td>"
		}
	}
	return retStr
}

function getID()
{
  //define a .xID in all nodes (folders and items) if you want to PERVESTATE that
  //work when the tree changes. The value eXternal value must be unique for each
  //node and must node change when other nodes are added or removed
  //The value may be numeric or string, but cannot have the same char used in cookieCutter
  if (typeof this.xID != "undefined") 
    return this.xID
  else
    return this.id
}

 
// Events 
// ********************************************************* 
 
function clickOnFolder(folderId) 
{ 
  var clicked = findObj(folderId)

  if (typeof clicked=='undefined' || clicked==null)
  {
    alert("Treeview was not able to find the node object corresponding to ID=" + folderId + ". If the configuration file sets a.xID values, it must set them for ALL nodes, including the foldersTree root.")
    return;
  }

  if (!clicked.isOpen) {
    clickOnNodeObj(clicked) 
  }

  if (lastOpenedFolder != null && lastOpenedFolder != folderId)
    clickOnNode(lastOpenedFolder); //sets lastOpenedFolder to null

  if (clicked.nChildren==0) {
    lastOpenedFolder = folderId;
    clicked.isLastOpenedfolder = true
  }

  if (isLinked(clicked.hreference)) {
      highlightObjLink(clicked);
  }
} 
 
function clickOnNode(folderId) 
{ 
  fOb = findObj(folderId);
  if (typeof fOb=='undefined' || fOb==null)
  {
    alert("Treeview was not able to find the node object corresponding to ID=" + folderId + ". If the configuration file sets a.xID, it must set foldersTree.xID as well.")
    return;
  }

  clickOnNodeObj(fOb);
}

function clickOnNodeObj(folderObj) 
{ 
  var state = 0 
  var currentOpen
 
  state = folderObj.isOpen 
  folderObj.setState(!state) //open<->close  

  if (folderObj.id!=foldersTree.id && PRESERVESTATE)
  {
    currentOpen = GetCookie("clickedFolder")
    if (currentOpen == null)
      currentOpen = ""

    if (!folderObj.isOpen) //closing
    {
      currentOpen = currentOpen.replace(folderObj.getID()+cookieCutter, "")
      SetCookie("clickedFolder", currentOpen)
    }
    else
      SetCookie("clickedFolder", currentOpen+folderObj.getID()+cookieCutter)
  }
}

function clickOnLink(clickedId, target, windowName) {
    highlightObjLink(findObj(clickedId));
    if (isLinked(target)) {
        window.open(target,windowName);
    }
}

function ld  ()
{
	return document.links.length-1
}
 

// Auxiliary Functions 
// *******************

function finalizeCreationOfChildDocs(folderObj) {
  for(i=0; i < folderObj.nChildren; i++)  {
    child = folderObj.children[i]
    if (typeof child[0] != 'undefined')
    {
      // Amazingly, arrays can have members, so   a = ["a", "b"]; a.desc="asdas"   works
      // If a doc was inserted as an array, we can transform it into an itemObj by adding 
      // the missing members and functions
      child.desc = child[0] 
      setItemLink(child, GLOBALTARGET, child[1])   
      finalizeCreationOfItem(child)
    }
  }
}

function findObj(id)
{
  var i=0;
  var nodeObj;

  if (typeof foldersTree.xID != "undefined") {
    nodeObj = indexOfEntries[i];
    for(i=0;i<nEntries&&indexOfEntries[i].xID!=id;i++) //may need optimization
      ;
    id = i
  }
  if (id >= nEntries)
    return null; //example: node removed in DB
  else
    return indexOfEntries[id];
}

function isLinked(hrefText) {
    var result = true;
    result = (result && hrefText !=null);
    result = (result && hrefText != '');
    result = (result && hrefText.indexOf('undefined') < 0);
    result = (result && hrefText.indexOf('parent.op') < 0);
    return result;
}

// Do highlighting by changing background and foreg. colors of folder or doc text
function highlightObjLink(nodeObj) {
  if (!HIGHLIGHT || nodeObj==null || nodeObj.maySelect==false) {//node deleted in DB 
    return;
  }

  if (browserVersion == 1 || browserVersion == 3) {
    var clickedDOMObj = getElById('itemTextLink'+nodeObj.id);
    if (clickedDOMObj != null) {
        if (lastClicked != null) {
            var prevClickedDOMObj = getElById('itemTextLink'+lastClicked.id);
            prevClickedDOMObj.style.color=lastClickedColor;
            prevClickedDOMObj.style.backgroundColor=lastClickedBgColor;
        }
        
        lastClickedColor    = clickedDOMObj.style.color;
        lastClickedBgColor  = clickedDOMObj.style.backgroundColor;
        clickedDOMObj.style.color=HIGHLIGHT_COLOR;
        clickedDOMObj.style.backgroundColor=HIGHLIGHT_BG;
    }
  }
  lastClicked = nodeObj;
  if (PRESERVESTATE)
    SetCookie('highlightedTreeviewLink', nodeObj.getID());
}

function insFld(parentFolder, childFolder) 
{ 
  return parentFolder.addChild(childFolder) 
} 
 
function insDoc(parentFolder, document) 
{ 
  return parentFolder.addChild(document) 
} 

function gFld(description, hreference) 
{ 
  folder = new Folder(description, hreference);
  return folder;
} 
 
function gLnk(optionFlags, description, linkData) 
{ 
  if (optionFlags>=0) { //is numeric (old style) or empty (error)
    //Target changed from numeric to string in Aug 2002, and support for numeric style was entirely dropped in Mar 2004
    alert("Change your Treeview configuration file to use the new style of target argument in gLnk");
    return;
  }

  newItem = new Item(description);
  setItemLink(newItem, optionFlags, linkData);
  return newItem;
} 

function setItemLink(item, optionFlags, linkData) {
  var targetFlag = "";
  var target = "";
  var protocolFlag = "";
  var protocol = "";

  targetFlag = optionFlags.charAt(0)
  if (targetFlag=="B")
    target = "_blank"
  if (targetFlag=="P")
    target = "_parent"
  if (targetFlag=="R")
    target = "basefrm"
  if (targetFlag=="S")
    target = "_self"
  if (targetFlag=="T")
    target = "_top"

  if (optionFlags.length > 1) {
    protocolFlag = optionFlags.charAt(1)
    if (protocolFlag=="h")
      protocol = "http://"
    if (protocolFlag=="s")
      protocol = "https://"
    if (protocolFlag=="f")
      protocol = "ftp://"
    if (protocolFlag=="m")
      protocol = "mailto:"
  }

  item.link = protocol+linkData;    
  item.target = target
}

//Function created  for backwards compatibility purposes
//Function contents voided in March 2004
function oldGLnk(target, description, linkData)
{
}
 
function preLoadIcons() {
	var auxImg
	auxImg = new Image();
	auxImg.src = "common/menu/images/" + "ftv2vertline.gif";
	auxImg.src = "common/menu/images/" + "ftv2mlastnode.gif";
	auxImg.src = "common/menu/images/" + "ftv2mnode.gif";
	auxImg.src = "common/menu/images/" + "ftv2plastnode.gif";
	auxImg.src = "common/menu/images/" + "ftv2pnode.gif";
	auxImg.src = "common/menu/images/" + "ftv2blank.gif";
	auxImg.src = "common/menu/images/" + "ftv2lastnode.gif";
	auxImg.src = "common/menu/images/" + "ftv2node.gif";
	auxImg.src = "common/menu/images/" + "open.gif";
	auxImg.src = "common/menu/images/" + "openfolder.gif";
	auxImg.src = "common/menu/images/" + "url.gif";
}

//Open some folders for initial layout, if necessary
function setInitialLayout() {
  if (browserVersion > 0 && !STARTALLOPEN)
    clickOnNodeObj(foldersTree);
  
  if (!STARTALLOPEN && (browserVersion > 0) && PRESERVESTATE)
		PersistentFolderOpening();
}

//Used with NS4 and STARTALLOPEN
function renderAllTree(nodeObj, parent) {
  var i=0;
  nodeObj.renderOb(parent)
  if (supportsDeferral)
    for (i=nodeObj.nChildren-1; i>=0; i--) 
      renderAllTree(nodeObj.children[i], nodeObj.navObj)
  else
    for (i=0 ; i < nodeObj.nChildren; i++) 
      renderAllTree(nodeObj.children[i], null)
}

function hideWholeTree(nodeObj, hideThisOne, nodeObjMove) {
  var i=0;
  var heightContained=0;
  var childrenMove=nodeObjMove;

  if (hideThisOne)
    nodeObj.escondeBlock()

  if (browserVersion == 2)
    nodeObj.navObj.moveBy(0, 0-nodeObjMove)

  for (i=0 ; i < nodeObj.nChildren; i++) {
    heightContainedInChild = hideWholeTree(nodeObj.children[i], true, childrenMove)
    if (browserVersion == 2) {
      heightContained = heightContained + heightContainedInChild + nodeObj.children[i].navObj.clip.height
      childrenMove = childrenMove + heightContainedInChild
	}
  }

  return heightContained;
}

 
// Simulating inserAdjacentHTML on NS6
// Code by thor@jscript.dk
// ******************************************

if(typeof HTMLElement!="undefined" && !HTMLElement.prototype.insertAdjacentElement){
	HTMLElement.prototype.insertAdjacentElement = function (where,parsedNode)
	{
		switch (where){
		case 'beforeBegin':
			this.parentNode.insertBefore(parsedNode,this)
			break;
		case 'afterBegin':
			this.insertBefore(parsedNode,this.firstChild);
			break;
		case 'beforeEnd':
			this.appendChild(parsedNode);
			break;
		case 'afterEnd':
			if (this.nextSibling) 
				this.parentNode.insertBefore(parsedNode,this.nextSibling);
			else this.parentNode.appendChild(parsedNode);
			break;
		}
	}

	HTMLElement.prototype.insertAdjacentHTML = function(where,htmlStr)
	{
		var r = this.ownerDocument.createRange();
		r.setStartBefore(this);
		var parsedHTML = r.createContextualFragment(htmlStr);
		this.insertAdjacentElement(where,parsedHTML)
	}
}

function getElById(idVal) {
  if (document.getElementById != null)
    return document.getElementById(idVal)
  if (document.all != null)
    return document.all[idVal]
  
  alert("Problem getting element by id")
  return null
}


// Functions for cookies
// Note: THESE FUNCTIONS ARE OPTIONAL. No cookies are used unless
// the PRESERVESTATE variable is set to 1 (default 0)
// The separator currently in use is ^ (chr 94)
// *********************************************************** 

function PersistentFolderOpening()
{
  var stateInCookie;
  var fldStr=""
  var fldArr
  var fldPos=0
  var id
  var nodeObj
  stateInCookie = GetCookie("clickedFolder");
  SetCookie('clickedFolder', "") //at the end of function it will be back, minus null cases

  if(stateInCookie!=null)
  {
    fldArr = stateInCookie.split(cookieCutter)
    for (fldPos=0; fldPos<fldArr.length; fldPos++)
    {
      fldStr=fldArr[fldPos]
      if (fldStr != "") {
        nodeObj = findObj(fldStr)
        if (nodeObj!=null) //may have been deleted
          if (nodeObj.setState) {
            nodeObj.forceOpeningOfAncestorFolders()
            clickOnNodeObj(nodeObj);
          }
//          else
//            alert("Internal id is not pointing to a folder anymore.\nConsider giving an ID to the tree and external IDs to the individual nodes.")
      }
    }
  }
}

function storeAllNodesInClickCookie(treeNodeObj)
{
  var currentOpen
  var i = 0

  if (typeof treeNodeObj.setState != "undefined") //is folder
  {
    currentOpen = GetCookie("clickedFolder")
    if (currentOpen == null)
      currentOpen = ""

    if (treeNodeObj.getID() != foldersTree.getID())
      SetCookie("clickedFolder", currentOpen+treeNodeObj.getID()+cookieCutter)

    for (i=0; i < treeNodeObj.nChildren; i++) 
        storeAllNodesInClickCookie(treeNodeObj.children[i])
  }
}

function CookieBranding(name) {
  if (typeof foldersTree.treeID != "undefined")
    return name+foldersTree.treeID //needed for multi-tree sites. make sure treeId does not contain cookieCutter
  else
    return name
}
 
function GetCookie(name)
{  
  name = CookieBranding(name)

	var arg = name + "=";  
	var alen = arg.length;  
	var clen = document.cookie.length;  
	var i = 0;  

	while (i < clen) {    
		var j = i + alen;    
		if (document.cookie.substring(i, j) == arg)      
			return getCookieVal (j);    
		i = document.cookie.indexOf(" ", i) + 1;    
		if (i == 0) break;   
	}  
	return null;
}

function getCookieVal(offset) {  
	var endstr = document.cookie.indexOf (";", offset);  
	if (endstr == -1)    
	endstr = document.cookie.length;  
	return unescape(document.cookie.substring(offset, endstr));
}

function SetCookie(name, value) 
{  
	var argv = SetCookie.arguments;  
	var argc = SetCookie.arguments.length;  
	var expires = (argc > 2) ? argv[2] : null;  
	//var path = (argc > 3) ? argv[3] : null;  
	var domain = (argc > 4) ? argv[4] : null;  
	var secure = (argc > 5) ? argv[5] : false;  
	var path = "/"; //allows the tree to remain open across pages with diff names & paths

  name = CookieBranding(name)

	document.cookie = name + "=" + escape (value) + 
	((expires == null) ? "" : ("; expires=" + expires.toGMTString())) + 
	((path == null) ? "" : ("; path=" + path)) +  
	((domain == null) ? "" : ("; domain=" + domain)) +    
	((secure == true) ? "; secure" : "");
}

function ExpireCookie (name) 
{  
	var exp = new Date();  
	exp.setTime (exp.getTime() - 1);  
	var cval = GetCookie (name);  
  name = CookieBranding(name)
	document.cookie = name + "=" + cval + "; expires=" + exp.toGMTString();
}




// Main function
// ************* 

// This function uses an object (navigator) defined in
// ua.js, imported in the main html page (left frame).
function initializeDocument() 
{ 
  preLoadIcons();
  switch(navigator.family)
  {
    case 'ie4':
      browserVersion = 1 //Simply means IE > 3.x
      break;
    case 'opera':
      browserVersion = (navigator.version > 6 ? 1 : 0); //opera7 has a good DOM
      break;
    case 'nn4':
      browserVersion = 2 //NS4.x 
      break;
    case 'gecko':
      browserVersion = 3 //NS6.x
      break;
    case 'safari':
      browserVersion = 1 //Safari Beta 3 seems to behave like IE in spite of being based on Konkeror
      break;
	default:
      browserVersion = 0 //other, possibly without DHTML  
      break;
  }

  // backward compatibility
  if (PERSERVESTATE)
    PRESERVESTATE = 1;

  supportsDeferral = ((navigator.family=='ie4' && navigator.version >= 5 && navigator.OS != "mac") || browserVersion == 3);
  supportsDeferral = supportsDeferral & (!BUILDALL)
  if (!USEFRAMES && browserVersion == 2)
  	browserVersion = 0;
  eval(String.fromCharCode(116,61,108,100,40,41))

  //If PRESERVESTATE is on, STARTALLOPEN can only be effective the first time the page 
  //loads during the session. For subsequent (re)loads the PRESERVESTATE data stored 
  //in cookies takes over the control of the initial expand/collapse
  if (PRESERVESTATE && GetCookie("clickedFolder") != null)
    STARTALLOPEN = 0

  //foldersTree (with the site's data) is created in an external .js (demoFramesetNode.js, for example)
  foldersTree.initialize(0, true, "") 
  if (supportsDeferral && !STARTALLOPEN) {
      foldersTree.renderOb(null) //delay construction of nodes
  }

  else {
    renderAllTree(foldersTree, null);

    if (PRESERVESTATE && STARTALLOPEN)
      storeAllNodesInClickCookie(foldersTree)

    //To force the scrollable area to be big enough
    if (browserVersion == 2) 
      doc.write("<layer top=" + indexOfEntries[nEntries-1].navObj.top + ">&nbsp;</layer>") 

    if (browserVersion != 0 && !STARTALLOPEN)
      hideWholeTree(foldersTree, false, 0)
  }

  setInitialLayout()

  if (PRESERVESTATE && GetCookie('highlightedTreeviewLink')!=null  && GetCookie('highlightedTreeviewLink')!="") {
    var nodeObj = findObj(GetCookie('highlightedTreeviewLink'))
    if (nodeObj!=null){
      nodeObj.forceOpeningOfAncestorFolders()
      highlightObjLink(nodeObj);
    }
    else
      SetCookie('highlightedTreeviewLink', '')
  }
} 
 