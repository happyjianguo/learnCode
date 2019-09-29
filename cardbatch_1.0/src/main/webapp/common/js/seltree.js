var ROOT = 0;
var NODE = 1;
var LEAF = 2;

var CLOSED = 0;
var OPENED = 1;

var EDITABLE = false;
var CHECKABLE = true;

var NON_LOADED = 0;
var LOADED = 1;

var ICON_PATH = "";

var BLANK_ICON = "jblank.gif";
var BLANK_WIDTH = 16;
var BLANK_HEIGHT = 12;

var CLOSED_ICON = "closednode.gif";
var OPENED_ICON = "openednode.gif";
var LEAF_ICON = "bookmark_author_icon.gif";

var PLUS_ICON = "jplus.gif";
var SUBTRACT_ICON = "subtract.gif";
var EDIT_ICON = "";

var LOADING_MSG = "Loading...";

var CSS_CLASS = "";
var CSS_HREF = "";

var TARGET = "_blank";
var TARGET_TYPE = "";

function LeafNode(oParent, sID, sName, sLink, sDesc, sIcon) {
  this.parent = oParent;
  this.id = sID || "";
  this.name = sName || "";
  this.link = sLink || "";
  this.iconLink = this.link;
  this.desc = sDesc || this.name;
  this.icon = sIcon || LEAF_ICON;

  this.level = 0;
  this.checked = false;
  this.editing = false;
  this.editable = EDITABLE;
  this.checkable = CHECKABLE;
  this.cssClass = CSS_CLASS;
  this.type = LEAF;
  this.target = TARGET;

  this.draw = draw;
  this.check = check;
  this.update = update;
  this.remove = remove;
  this.isParentOf = isParentOf;
  this.getNode = getNode;
  this.getRoot = getRoot;
  this.getCheckedNode = getCheckedNode;
  this.toEditable = toEditable;
  this.popMenu = null;
  this.showPopMenu = showPopMenu;
  this.hidePopMenu = hidePopMenu;
  this._drawPopMenu = _drawPopMenu;
  this._makeLink = _makeLink;
  this._parseLink = _parseLink;
  this._makeEditLink = _makeEditLink;
  this._makeEditBox = _makeEditBox;
  this._makeCheckBox = _makeCheckBox;

  // construction
  if (oParent) {
    this.level = oParent.level + 1;
  }

  function draw() {
    var iWidth = (this.level + 1) * BLANK_WIDTH;
    var root = this.getRoot();

    root.buffer += '<tr><td nowrap class="' + this.cssClass + '">';
    root.buffer += '<img src="' + ICON_PATH + BLANK_ICON + '" ';
    root.buffer += 'width="' + iWidth + '" height="' + BLANK_HEIGHT + '" ';
    root.buffer += 'border="0" align="absmiddle">';
    root.buffer += '<img border="0" src="' + ICON_PATH + this.icon + '" ';
    root.buffer += 'align="absmiddle">';

    root.buffer += this._makeCheckBox();

    if (this.editing) {
      root.buffer += this._makeEditBox();
    }
    else {
      root.buffer += '&nbsp;<span id="NODE_' + this.id + '" class="node">';
      root.buffer += this._makeLink() + '</span>';
    }

    root.buffer += this._makeEditLink();

    root.buffer += '</td></tr>\n';
  }

  function check(checked) {
    var root = this.getRoot();
    oCheckBox = root.container._theForm["chk_" + this.id];
    if(oCheckBox == null) {
      return;
    }
    oCheckBox.checked = checked;
    this.checked = checked;

    var sFunction = root.getScriptLocation();
    if (checked) {
      sFunction += "onCheckNode";
    }
    else {
      sFunction += "onUnCheckNode";
    }
    if (eval('typeof(' + sFunction + ') == "function"')) {
      var name = this.name.replace(/'/g, "\\'");
      eval(sFunction + "('" + this.id + "', '" + name + "');");
    }
  }

  function update(newValue) {
    var root = this.getRoot();
    var sFunction = root.getScriptLocation();

    if (newValue.regExpTest(/^\s+$/) || newValue == "") {
      alert(MSG_BLANK_ERROR);
      root.draw();
      return;
    }
    if (newValue.indexOf("\\") != -1) {
      alert(MSG_SPEC_CHAR_ERROR);
      root.draw();
      return;
    }

    if (this.id == "unadded") {
      sFunction += "onAddNode";
    }
    else if (this.name != newValue) {
      sFunction += "onUpdateNode";
    }
    else {
      this.editing = false;
      root.draw();
      return;
    }

    // duplicated name isn't allowed
    var count = this.parent.contains(newValue);
    if ( count == 0 ||
       (this.id == "unadded" && count == 1 && newValue == MSG_UNNAMED) ) {

      this.editing = false;
    }
    else {
      alert(MSG_DUP_NAME_ERROR.getMsg(newValue));
      root.draw();
      return;
    }

    if (eval('typeof(' + sFunction + ') == "function"')) {
      root.draw();
      var id = this.id;
      var parentId = this.parent.id;

      // if this add node and onAddNode() function is defined, then just call onAddNode()
      if (this.id == "unadded") {
        this.parent.removeNode(this);
      }
      newValue = newValue.replace(/'/g, "\\'");
      eval(sFunction + "('" + id + "', '" + newValue + "', '" + parentId + "');");
    }
    else {
      this.name = newValue;
      root.draw();
    }
  }

  function remove() {
    this.hidePopMenu();
    if (confirm(MSG_CONFIRM_DEL.getMsg(this.name)) ) {
      var sFunction = this.getRoot().getScriptLocation() + "onRemoveNode";

      if (eval('typeof(' + sFunction + ') == "function"')) {
        eval(sFunction + "('" + this.id + "');");
      }
      else {
        this.parent.removeNode(this);
      }
    }
  }

  function getRoot() {
    if (this.parent != null) {
      return this.parent.getRoot();
    }
    else {
      return this;
    }
  }

  function getNode(sID) {
    if (this.id == sID) {
      return this;
    }
    else {
      return null;
    }
  }

  function isParentOf(node) {
    return false;
  }

  function getCheckedNode() {
    if (this.checked) {
      return this.id + ";";
    }
    return "";
  }

  function setPopMenu(popMenu) {
    this.popMenu = popMenu;
  }

  function toEditable() {
    this.hidePopMenu();
    this.editing = true;
    this.getRoot().draw();
  }

  function showPopMenu() {
    var root = this.getRoot();
    var oPopMenu = root.popMenuDiv;

    oPopMenu.innerHTML = this._drawPopMenu();
    if (root.container.tagName) { // container is div
      oPopMenu.style.pixelLeft = event.clientX + root.container.scrollLeft - 45;
      oPopMenu.style.pixelTop = event.clientY + root.container.scrollTop - 50;
    }
    else { // container is document
      oPopMenu.style.pixelLeft = root.container.parentWindow.event.clientX - 30;
      oPopMenu.style.pixelTop = root.container.parentWindow.event.clientY - 60;
    }
    oPopMenu.style.display = "inline";
  }

  function hidePopMenu() {
    this.getRoot().popMenuDiv.style.display = "none";
  }

  function _drawPopMenu() {
    var root = this.getRoot();
    var sMenuContent = '';

    // ¹w³]ªº pop menu
    if (this.popMenu == null) {
      var menu = new Menu();
      menu.add(new MenuItem(MSG_UPDATE, root.varName + ".getNode('" + this.id + "').toEditable();"));
      menu.add(new MenuItem(MSG_DEL, root.varName + ".getNode('" + this.id + "').remove();"));
      this.popMenu = menu;
    }
    var menuItems = this.popMenu.items;
    sMenuContent += '<table border="0" cellspacing="0" cellpadding="0" class="' + this.cssClass + '" ';
    for (var i=0; i<menuItems.length; i++) {
      if (menuItems[i] != null) {
        sMenuContent += '<tr><td nowrap onclick="' + root.getScriptLocation();
        sMenuContent += menuItems[i].action + '(\'' + this.id + '\');';
        sMenuContent += root.getScriptLocation() + root.varName;
        sMenuContent += '.getNode(\'' + this.id + '\').hidePopMenu();">';
        sMenuContent += menuItems[i].name + '</td><tr>';
      }
    }
    sMenuContent += '<tr><td nowrap onclick="' + root.getScriptLocation() + root.varName;
    sMenuContent += '.getNode(\'' + this.id + '\').hidePopMenu();">';
    sMenuContent += MSG_CANCEL + '</td><tr></table>';

    return sMenuContent;
  }

  function _makeLink() {
    var root = this.getRoot();
    var sResult = this.name;

    if (root.makeLink) {
      if (this.link != "") {
        var sLink = this._parseLink(this.link);
        if (TARGET_TYPE != "IFRAME") {
          sResult = '<a target="' + this.target + '" href="' + sLink + '"';
          sResult += 'onclick="' + root.getScriptLocation() + root.varName;
          sResult += '.setNodeCSS(\'' + this.id + '\', \'clickedNode\');"';
          sResult += ' title="' + this.desc  + '">' + this.name + '</a>';
        }
        else {
          sResult = '<span style="cursor:hand" onclick="';
          if (sLink.indexOf("javascript:") != -1) {
            sLink = sLink.substring(sLink.indexOf(":") + 1, sLink.length);
            sResult += sLink;
          }
          else {
            sResult += this.target + ".src='" + sLink + "';";
          }
          sResult += root.getScriptLocation() + root.varName;
          sResult += '.setNodeCSS(\'' + this.id + '\', \'clickedNode\');"';
          sResult += ' title="' + this.desc  + '">' + this.name + '</span>';
        }
      }
    }

    return sResult;
  }

  function _makeEditLink() {
    var root = this.getRoot();
    var sResult = "";

    if (this.editable) {
      sResult += '&nbsp;<img src="' + ICON_PATH + EDIT_ICON + '" ';
      sResult += 'style="cursor:hand" border="0" align="absmiddle" ';
      sResult += 'onclick="' + root.getScriptLocation() + root.varName;
      sResult += '.getNode(\'' + this.id + '\').showPopMenu();">';
    }

    return sResult;
  }

  function _makeEditBox() {
    var root = this.getRoot();
    var sResult = "";

  var sName = this.name.replace(/"/g, "&quot");
    sResult += '<input class=textbox type="text" size="' + (this.name.length + 3) + '" ';
    sResult += 'onblur="' + root.getScriptLocation() + root.varName;
    sResult += '.getNode(\'' + this.id + '\').update(this.value);" ';
    sResult += 'name="text_update" value="' + sName + '">';

    return sResult;
  }

  function _makeCheckBox() {
    var root = this.getRoot();
    var sResult = "";

    // if it isn't root node adn makeCheckBox mode is enable
    if (this != this.getRoot() &&
          root.makeCheckBox) {

      var strCheck = "";

      if (this.parent != null) {
        //if (this.checked || this.parent.checked) {
        if (this.checked) {
          strCheck = "checked";
          this.checked = true;
        }
      }

      sResult += '<input class="text" type="checkbox" ';
      if (this.checkable) {
        sResult += 'onclick="' + root.getScriptLocation() + root.varName;
        sResult += '.getNode(\'' + this.id + '\').check(this.checked);" ';
      }
      else {
        sResult += 'disabled ';
      }
      sResult += 'name="chk_' + this.id + '" ' + strCheck + '>';
    }

    return sResult;
  }

  function _parseLink(sLink) {
    var sResult = sLink;

    if (sLink.indexOf("javascript:") == 0) {
      sResult = sResult.replace(/\[/ig, "\'");
      sResult = sResult.replace(/\]/ig, "\'");
      
      //very tricky and dirty, need to find better way someday
      //solve problem that sLink contains double quote will cause html parsing error.
      sResult = sResult.replace(/"/ig, "' + String.fromCharCode(34) + '");
    }
    return sResult;
  }

}

function Node(oParent, sID, sName, sLink, sDesc, iMode, bDataLoaded, sClosedIcon, sOpenedIcon) {
  this.base = LeafNode;
  this.base(oParent, sID, sName, sLink, sDesc);

  this.mode = iMode || CLOSED;
  this.dataLoaded = bDataLoaded || NON_LOADED;
  this.closedIcon = sClosedIcon || CLOSED_ICON;
  this.openedIcon = sOpenedIcon || OPENED_ICON;
  this.dataLoading = false;
  this.child = new Array();
  this.type = NODE;

  this.draw = draw;
  this.open = open;
  this.close = close;
  this.addNode = addNode;
  this.addLeafNode = addLeafNode;
  this.addNewNode = addNewNode;
  this.removeNode = removeNode;
  this._check = this.check;
  this.check = check;
  this.contains = contains;
  this.hasChild = hasChild;
  this.isParentOf = isParentOf;
  this.getNode = getNode;
  this.getChildren = getChildren;
  this.getCheckedNode = getCheckedNode;

  // private methods
  this._drawLeafPopMenu = this._drawPopMenu;
  this._drawPopMenu = _drawPopMenu;
  this._preSetting = _preSetting;
  this._drawNode = _drawNode;
  this._drawHead = _drawHead;
  this._drawFoot = _drawFoot;
  this._drawChildren = _drawChildren;
  this._makeLeafLink = this._makeLink;
  this._makeLink = _makeLink;
  this._makeLeafEditLink = this._makeEditLink;
  this._makeEditLink = _makeEditLink;

  // overwrite draw() method
  function draw() {
    var root = this.getRoot();
    this._preSetting();

    // write self-content first
    root.buffer += this._drawNode();
    root.buffer += this._drawHead();
    this._drawChildren();
    root.buffer += this._drawFoot();
  }

  function isParentOf(node) {
    if (this.child) {
      for (var i=0; i<this.child.length; i++) {
        if (this.child[i] == node) {
          return true;
        }
      }
      for (var i=0; i<this.child.length; i++) {
        if (this.child[i] != null) {
          if (this.child[i].isParentOf(node)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  function close() {
    this.mode = CLOSED;
    this.getRoot().draw();
  }

  function open() {
    this.mode = OPENED;
    this.getRoot().draw();
  }

  function addNode(sID, sName, sLink, sDesc, iMode, bDataLoaded, sClosedIcon, sOpenedIcon) {
    var node = null;
    if (typeof(sID) == "string") {
      node = new Node(
        this, sID, sName, sLink, sDesc, iMode, bDataLoaded, sClosedIcon, sOpenedIcon);
    }
    else {
      // for addNode(oNode)
      node = sID;
    }

    this.child[this.child.length] = node;
    return node;
  }

  function addLeafNode(sID, sName, sLink, sDesc, sIcon) {
    var leafNode = new LeafNode(this, sID, sName, sLink, sDesc, sIcon);
    this.child[this.child.length] = leafNode;

    return leafNode;
  }

  function addNewNode() {
    this.hidePopMenu();

    var node = this.addNode("unadded", MSG_UNNAMED);
    node.editing = true;
    node.dataLoaded = true;
    this.getRoot().draw();
  }

  function removeNode(node) {
    for (var i=0; i<this.child.length; i++) {
      if (this.child[i] == node) {
        this.child[i] = null;
      }
    }

    this.getRoot().draw();
  }

  function check(checked) {
    this._check(checked);

    /*for (var i=0; i<this.child.length; i++) {
      if (this.child[i] != null) {
        this.child[i].check(checked);
      }
    }*/
  }

  function contains(name) {
    var count = 0;
    for (var i=0; i<this.child.length; i++) {
      if (this.child[i] != null) {
        var childName = this.child[i].name;
        var pos = childName.lastIndexOf(" (");
        childName = childName.substring(0, pos);
        if (childName == name) {
          count++;
        }
      }
    }

    return count;
  }

  function hasChild() {
    if (this.child.length == 0) {
      return false;
    }
    else {
      for (var i=0; i<this.child.length; i++) {
        if (this.child[i] != null) {
          return true;
        }
      }
    }
    return false;
  }

  function getNode(sID) {
    if (this.id == sID) {
      return this;
    }
    else {
      var node = null;
      for (var i=0; i<this.child.length; i++) {
        if (this.child[i] != null) {
          node = this.child[i].getNode(sID);
        }
        if (node != null)
          break;
      }
      return node;
    }
  }

  function getChildren(sID) {
    this.dataLoading = true;
    this.mode = OPENED;
    this.dataLoaded = LOADED;
    this.getRoot().draw();
    this.dataLoading = false;

    var sFunction = this.getRoot().getScriptLocation() + "onLoadData";
    if (eval('typeof(' + sFunction + ') == "function"')) {
      eval(sFunction + "('" + sID + "');");
    }
    this.getRoot().draw();
  }

  function getCheckedNode() {
    var sResult = "";

    if (this.checked) {
      sResult = this.id + ";";
    }
    else {
      for (var i=0; i<this.child.length; i++) {
        if (this.child[i] != null) {
          sResult += this.child[i].getCheckedNode();
        }
      }
    }

    return sResult;
  }

  function _drawNode() {
    var iWidth = this.level * BLANK_WIDTH;
    var sResult = "";

    sResult += '<tr><td nowrap class="' + this.cssClass + '">';
    sResult += '<img src="' + ICON_PATH + BLANK_ICON + '" ';
    sResult += 'width="' + iWidth + '" height="' + BLANK_HEIGHT + '" ';
    sResult += 'border="0" align="absmiddle">';

    // if this node had loaded and it has no child, then we don't draw + - and link
    if (this.mode == LOADED && !this.hasChild()) {
      sResult += '<img border="0" src="' + ICON_PATH + BLANK_ICON + '" align="absmiddle">';
      sResult += '<img border="0" src="' + ICON_PATH + this.icon + '" align="absmiddle">';
    }
    else {
      sResult += '<span style="cursor:hand" onclick="' + this.iconLink + '">';
      if (this.mode == CLOSED || this.dataLoaded == NON_LOADED) {
        sResult += '<img border="0" src="' + ICON_PATH + PLUS_ICON + '" align="absmiddle">';
      }
      else {
        sResult += '<img border="0" src="' + ICON_PATH + SUBTRACT_ICON + '" align="absmiddle">';
      }
      sResult += '<img border="0" src="' + ICON_PATH + this.icon + '" align="absmiddle"></span>';
    }

    if (this.getRoot().makeCheckBox) {
      sResult += this._makeCheckBox();
    }

    if (this.editing) {
      sResult += this._makeEditBox();
    }
    else {
      sResult += '&nbsp;<span id="NODE_' + this.id + '" class="node">';
      sResult += this._makeLink() + '</span>';
      if (!this.dataLoading) {
        sResult += this._makeEditLink();
      }
    }
    sResult += '</td></tr>\n';

    return sResult;
  }

  function _drawHead() {
    var sResult = "";

    if (this.mode == OPENED && this.child.length > 0) {
      sResult += '<tr><td><div id="' + this.id + '" ';
      sResult += 'style="display:inline"><table border=0>';
    }

    return sResult;
  }

  function _drawFoot() {
    var sResult = "";

    if (this.mode == OPENED && this.child.length > 0) {
      sResult += "</table></div></td></tr>";
    }

    return sResult;
  }

  function _drawChildren() {
    if (this.mode == OPENED) {
      // call children to write their content
      for (var i=0; i<this.child.length; i++) {
        if (this.child[i] != null) {
          this.child[i].draw();
        }
      }
    }
  }

  function _preSetting() {
    var root = this.getRoot();

    if (this.mode == OPENED) {
      this.icon = this.openedIcon;
    }
    else {
      this.icon = this.closedIcon;
    }

    if (this.mode == OPENED) {
      this.iconLink = root.getScriptLocation() + root.varName;
      this.iconLink += ".getNode('" + this.id + "').close();";
    }
    else if (this.dataLoaded == LOADED) {
      this.iconLink = root.getScriptLocation() + root.varName;
      this.iconLink += ".getNode('" + this.id + "').open();";
    }
    else {
      this.iconLink = root.getScriptLocation() + root.varName;
      this.iconLink += ".getNode('" + this.id + "')";
      this.iconLink += ".getChildren('" + this.id + "');";
    }
  }

  function _drawPopMenu() {
    var root = this.getRoot();
    var sMenuContent = "";

    // default pop menu
    if (this.popMenu == null) {
      var menu = new Menu();
      if (this.dataLoaded == LOADED && this.mode == OPENED) {
        menu.add(new MenuItem(MSG_ADD, root.varName + ".getNode('" + this.id + "').addNewNode();"));
      }
      menu.add(new MenuItem(MSG_UPDATE, root.varName + ".getNode('" + this.id + "').toEditable();"));
      menu.add(new MenuItem(MSG_DEL, root.varName + ".getNode('" + this.id + "').remove();"));
      this.popMenu = menu;
    }
    sMenuContent += this._drawLeafPopMenu();

    return sMenuContent;
  }

  function _makeLink() {
    sResult = this._makeLeafLink();

    if (this.dataLoading && this.getRoot().showLoadingMsg) {
      var iWidth = (this.level + 2) * BLANK_WIDTH;

      sResult += '<br><img src="' + ICON_PATH + BLANK_ICON + '" ';
      sResult += 'width="' + iWidth + '" ';
      sResult += 'height="' + BLANK_HEIGHT + '" ';
      sResult += 'border="0" align="absmiddle">';
      sResult += LOADING_MSG;
    }

    return sResult;
  }

  function _makeEditLink() {
    sResult = "";

    if (this.dataLoaded && this.mode == OPENED) {
      sResult = this._makeLeafEditLink();
    }

    return sResult;
  }

}

function Root(sID, sName, sLink, sDesc, iMode, bDataLoaded, oContainer, sClosedIcon, sOpenedIcon) {
  this.base = Node;
  this.base(null, sID, sName, sLink, sDesc, iMode, bDataLoaded, sClosedIcon, sOpenedIcon);

  this.buffer = "";
  this.container = oContainer || document;
  this.popMenuDiv = null;
  // script location (for frame version)
  this.scriptLocation = "";
  this.varName = sID;
  this.preClickedNodeId = sID;
  this.scrollTop = 0;
  this.scrollLeft = 0;
  this.type = ROOT;
  this.makeCheckBox = false;
  this.makeLink = true;
  this.showLoadingMsg = true;

  this.drawNode = this.draw;
  this.draw = draw;
  this.scrollTo = scrollTo;
  this.remove = remove;
  this.checkNodes = checkNodes;
  this.getScriptLocation = getScriptLocation;
  this.setScriptLocation = setScriptLocation;
  this.setRootVarName = setRootVarName;
  this.setNodeCSS = setNodeCSS;
  this.setEditable = setEditable;
  this.setTarget = setTarget;
  this.setTargetType = setTargetType;
  this.setIconPath = setIconPath;
  this.setCSSClass = setCSSClass;
  this.setCSSHref = setCSSHref;
  this._makePopMenu = _makePopMenu;

  // construction
  if (this.container.tagName) {
    DivExtend(oContainer);
  }

  function draw() {
    // record the position of scrollbar
    this.scrollTop = this.container.body.scrollTop;
    this.scrollLeft = this.container.body.scrollLeft;

    this.buffer = "";
    this.buffer += CSS_HREF;
    if (this.makeCheckBox || EDITABLE) {
      this.buffer += '<form name="_theForm" onsubmit="return false;">';
    }

    this.buffer += "<div><table border=0>";
    this.drawNode();
    this.buffer += "</table></div>";

    if (this.makeCheckBox || EDITABLE) {
      this.buffer += "</form>";
      this._makePopMenu();
    }

    this.container.clear();
    this.container.write(this.buffer);
    this.container.close();

    if (this.makeCheckBox || EDITABLE) {
      if (this.container.tagName) {  // div...
        this.container._theForm = document._theForm;
        if (document._theForm.text_update) {
          document._theForm.text_update.focus();
          document._theForm.text_update.select();
        }
        this.popMenuDiv = document.all["TreePopMenu"];
      }
      else {
        this.popMenuDiv = this.container.all["TreePopMenu"];
      }
      if (isIE55up()) {
        this.popMenuDiv.style.filter = 'progid:DXImageTransform.Microsoft.Shadow(' +
          'direction=135, color=#C0C0C0, strength=4)';
      }
    }
    //alert(this.buffer);

    // restore the position of scrollbar
    this.container.body.scrollTop = this.scrollTop;
    this.container.body.scrollLeft = this.scrollLeft;
  }

  function remove() {
    this.hidepopMenuDiv();

    this.child = new Array();
    this.draw();

    var sFunction = this.getRoot().getScriptLocation() + "onRemoveRoot";

    if (eval('typeof(' + sFunction + ') == "function"')) {
      eval(sFunction + "();");
    }
  }

  function checkNodes(aryIDs) {
    for (i=0; i<aryIDs.length; i++) {
      var tempNode;
      if (tempNode=this.getNode(aryIDs[i])) {
        if (tempNode != null) {
          tempNode.check(true);
        }
      }
    }
  }

  function setNodeCSS(nodeId, newCSSClass) {
    if (this.container.tagName) {  // div...
      // pre-clicked node may be deleted
      if (document.all["NODE_" + this.preClickedNodeId]) {
        document.all["NODE_" + this.preClickedNodeId].className = "node";
      }
      document.all["NODE_" + nodeId].className = newCSSClass;
    }
    else {
      if (this.container.all["NODE_" + this.preClickedNodeId]) {
        this.container.all["NODE_" + this.preClickedNodeId].className = "node";
      }
      this.container.all["NODE_" + nodeId].className = newCSSClass;
    }

    this.preClickedNodeId = nodeId;
  }

  function getScriptLocation() {
    if (this.scriptLocation != "") {
      return this.scriptLocation + ".";
    }
    return this.scriptLocation;
  }

  function setScriptLocation(scriptLocation) {
    this.scriptLocation = scriptLocation;
  }

  function setRootVarName(varName) {
    this.varName = varName;
  }

  function setEditable(bEditable) {
    EDITABLE = bEditable;
    this.editable = EDITABLE;
  }

  function setTarget(sTarget) {
    TARGET = sTarget;
    this.target = TARGET;
  }

  function setTargetType(sTargetType) {
    TARGET_TYPE = sTargetType;
  }

  function setIconPath(sIconPath) {
    ICON_PATH = sIconPath + "/";
  }

  function setCSSClass(sCSSClass) {
    CSS_CLASS = sCSSClass;
    this.cssClass = CSS_CLASS;
  }

  function setCSSHref(sCSSHref) {
    CSS_HREF = sCSSHref;
  }

  function _makePopMenu() {
    this.buffer += '<div id="TreePopMenu" class="' + this.cssClass + '" ';
    this.buffer += 'style="display:none; position:absolute; left:0px; top:0px;';
    this.buffer += 'padding-left:3px; padding-right:3px;';
    this.buffer += 'background-color:#FFFF99; cursor:hand; border:1px #999900 double;">';
    //this.buffer += 'onmouseout="this.style.display=\'none\';">';
    this.buffer += 'PopMenu</div>';
  }
}

function MenuItem(sName, sAction) {
  this.name = sName || "";
  this.action = sAction || "";
}

function Menu() {
  this.items = new Array();

  this.add = add;
  this.remove = remove;

  function add(oMenuItem) {
    this.items[this.items.length] = oMenuItem;
  }

  function remove(iIndex) {
    if (iIndex <= this.items.length) {
      this.items[iIndex] = null;
    }
  }
}

function DivExtend(oDiv) {
  oDiv.write = write;
  oDiv.clear = clear;
  oDiv.close = close;
  oDiv.body = oDiv;

  function write(sContent) {
    this.innerHTML += sContent;
  }

  function clear() {
    this.innerHTML = "";
  }

  function close() {
  }
}

// helper functions
function getMsg() {
  var msg = this.toString();

  for (var i=0; i<arguments.length; i++) {
    var key = "\\{" + i + "\\}";
    var value = arguments[i].toString();
    var regExp = new RegExp(key, "ig");
    msg = msg.replace(regExp, value);
  }

  return msg;
}
String.prototype.getMsg = getMsg;

function regExpTest(objRegExp) {
  var strTest = this.toString();
  return objRegExp.test(strTest);
}
String.prototype.regExpTest = regExpTest;

function isIE55up() {
  var agt = navigator.userAgent.toLowerCase();

  // *** BROWSER VERSION ***
  // Note: On IE5, these return 4, so use isIE5up to detect IE5.
  var majorVer = parseInt(navigator.appVersion);
  var minorVer = parseFloat(navigator.appVersion);

  var isIE     = ((agt.indexOf("msie") != -1) && (agt.indexOf("opera") == -1));
  var isIE55   = (isIE && (majorVer == 4) && (agt.indexOf("msie 5.5") !=-1));
  var isIE6    = (isIE && (majorVer == 4) && (agt.indexOf("msie 6.")!=-1) );

  return (isIE55 || isIE6);
}