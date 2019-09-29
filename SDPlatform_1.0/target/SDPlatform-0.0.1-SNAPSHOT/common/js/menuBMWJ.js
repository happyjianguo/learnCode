// 菜单常量
var sMenuHr="<tr><td align=center valign=middle height=2><TABLE border=0 cellpadding=0 cellspacing=0 width=10 height=2><tr><td height=1 class=HrShadow><\/td><\/tr><tr><td height=1 class=HrHighLight><\/td><\/tr><\/TABLE><\/td><\/tr>";
var sMenu1="<TABLE border=0 cellpadding=0 cellspacing=0 class=Menu width=80 onselectstart=\"return false;\"><tr><td width=10 valign=bottom align=center class=LeftBg><\/td><td width=70 class=RightBg><TABLE border=0 cellpadding=0 cellspacing=0>";
var sMenu2="<\/TABLE><\/td><\/tr><\/TABLE>";
// 菜单
var oPopupMenu = null;
if (BrowserInfo.IsIE55OrMore){
	oPopupMenu = window.createPopup();
}

// 取菜单行
function getMenuRow(s_Disabled, s_Event, s_Image, s_Html) {
	var s_MenuRow = "";
	s_MenuRow = "<tr><td align=center valign=middle><TABLE border=0 cellpadding=0 cellspacing=0 width=70><tr "+s_Disabled+"><td valign=middle height=20 class=MouseOut onMouseOver=this.className='MouseOver'; onMouseOut=this.className='MouseOut';";
	if (s_Disabled==""){
		s_MenuRow += " onclick=\"parent."+s_Event+";parent.oPopupMenu.hide();\"";
	}
	s_MenuRow += ">"
	
	s_MenuRow += s_Html+"<\/td><\/tr><\/TABLE><\/td><\/tr>";
	return s_MenuRow;

}

// 取标准的format菜单行
function getFormatMenuRow(menu, html, image){
	var s_Disabled = "";
	if (!eWebEditor.document.queryCommandEnabled(menu)){
		s_Disabled = "disabled";
	}
	var s_Event = "format('"+menu+"')";
	var s_Image = menu+".gif";
	if (image){
		s_Image = image;
	}

	return getMenuRow(s_Disabled, s_Event, s_Image, html)
}

// 右键菜单
function showContextMenu(event){
	if (!bEditMode) return false;

	var width = 80;
	var height = 0;
	var lefter = event.clientX;
	var topper = event.clientY;

	var oPopDocument = oPopupMenu.document;
	var oPopBody = oPopupMenu.document.body;

	var sMenu="";
	// 获得选中项的各参数和执行的URL
	var orgId = getCurrentChannelId("BM_ID");
	

	//根目录右键菜单
	var creatOrgUrl = "./orgFolderAction.do?method=showFolderInputScree&parentId="+orgId;//添加文件夹
	
	//部门右键菜单
	var UpOrgUrl = "./orgFolderAction.do?method=upFolder&folderId="+orgId;//上移
	var DownOrgUrl = "./orgFolderAction.do?method=downFolder&folderId="+orgId;//下移
	var delOrgUrl = "./orgFolderAction.do?method=showConfirmDeleteFolder&folderId="+orgId;//删除文件夹
	var modifyOrgUrl = "./orgFolderAction.do?method=showModifyFolderInfo&seqId="+orgId;//修改文件夹
	var addOrgFilesUrl = "./orgFileAction.do?method=showFileInputScree&seqId="+orgId;//添加文件夹文件
	
	

////////////////////////////////////////////////////////////////////////////////////////////////
	if( getCurrentChannelId("S_mode")=="YHGL"){
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+UpOrgUrl+"')", "blink.gif", "&nbsp;上移");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+DownOrgUrl+"')", "blink.gif", "&nbsp;下移");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+addOrgFilesUrl+"')", "blink.gif", "&nbsp;添加文件");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+creatOrgUrl+"')", "blink.gif", "&nbsp;添加文件夹");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+modifyOrgUrl+"')", "blink.gif", "&nbsp;编辑文件夹");		
		sMenu += getMenuRow("", "refresh()", "blink.gif", "&nbsp;刷新树节点");
		//sMenu += sMenuHr;右键弹出菜单底部(备用样式)
		height += 123;
	}
	if( getCurrentChannelId("S_mode")=="YHBM"){
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+UpOrgUrl+"')", "blink.gif", "&nbsp;上移");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+DownOrgUrl+"')", "blink.gif", "&nbsp;下移");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+addOrgFilesUrl+"')", "blink.gif", "&nbsp;添加文件");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+modifyOrgUrl+"')", "blink.gif", "&nbsp;编辑文件夹");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+creatOrgUrl+"')", "blink.gif", "&nbsp;添加文件夹");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+delOrgUrl+"')", "blink.gif", "&nbsp;删除文件夹");
		
		height += 123;
	}
	

	


	sMenu = sMenu1 + sMenu + sMenu2;

	oPopDocument.open();
	oPopDocument.write(config.StyleMenuHeader+sMenu);
	oPopDocument.close();

	//height+=2;
	//if(lefter+width > document.body.clientWidth) lefter=lefter-width;
	//if(topper+height > document.body.clientHeight) topper=topper-height;

	oPopupMenu.show(lefter, topper, width, height, document.body);
	return false;

}
