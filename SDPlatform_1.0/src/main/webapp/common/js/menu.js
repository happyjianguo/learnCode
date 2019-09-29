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
	var userId = getCurrentChannelId("YH_ID");
	var roleCatalogId = getCurrentChannelId("JSLB_ID");
	var roleId = getCurrentChannelId("JS_ID");

	//根目录右键菜单
		
	var creatDirUrl = "./myDirAction.do?method=showDirInputScreen&parentId="+orgId;//添加目录
	var creatDocUrl = "./myDocAction.do?method=showAllSelectDoc&orgId="+orgId;//添加文档
	var	delDirUrl	= "./myDirAction.do?method=deleteDir&orgId="+orgId;//删除目录
	var modifyDirUrl = "./myDirAction.do?method=showDirmodifyScreen&orgId="+orgId;//update目录

	var creatOrgUrl = "./orgAction.do?method=showOrgInputScree&parentId="+orgId;//添加部门
	var listAllUserUrl = "./orgAction.do?method=GetOrgUsers&orgId="+orgId;//用户列表
	var creatUserUrl = "./orgAction.do?method=showAllNoAddUsers&orgId="+orgId;//添加用户
	var creatPluralizeUrl = "./orgAction.do?method=showAllSelectUsers&orgId="+orgId;//添加兼职
	var shouUsersOrgUrl = "./orgAction.do?method=showUsersInfoAndOrg"//人员位置
	//部门右键菜单
	var UpOrgUrl = "./orgAction.do?method=upOrg&orgId="+orgId;//上移
		//上移目录
	var UpDirUrl = "./myDirAction.do?method=upDir&Id="+orgId;
		//下移目录
	var DownDirUrl = "./myDirAction.do?method=downDir&Id="+orgId;

	var DownOrgUrl = "./orgAction.do?method=downOrg&orgId="+orgId;//下移
	var modifyOrgUrl = "./orgAction.do?method=showModifyOrgInfo&seqId="+orgId;//编辑部门
	var delOrgUrl = "./orgAction.do?method=showConfirmDeleteOrg&orgId="+orgId;//删除部门
	
	var listOrgUserUrl = "./orgAction.do?method=GetOrgUsers&orgId="+orgId;//用户列表
	
	var delUserUrl = "deleteUser.do?orgId="+orgId+"&userId="+userId;
	var delRoleUrl = "deleteRole.do?roleId="+roleId;
	var delAdminUrl = "deleteAdmin.do?orgId="+orgId+"&userId="+userId;
	var delRoleCatalogUrl = "deleteRoleCatalog.do?roleId="+roleCatalogId;
	var creatRoleUrl = "org/CreateRole.jsp?parentId="+roleCatalogId;
	var CreateRoleCatalogUrl = "org/CreateRoleCatalog.jsp?parentId="+roleCatalogId;
	var getUserSelectTreeUrl ="getOrgAndUserTreeView.do?orgId="+orgId+"&type=select";
	var getPluraSelectTreeUrl ="getOrgAndUserTreeView.do?orgId="+orgId+"&type=forUser";
	var moveUserUrl = "getOrgTreeForUser.do?orgId="+orgId+"&userId="+userId;
	var moveOrgUrl = "getOrgTreeForOrg.do?orgId="+orgId;
	var UpUserUrl = "upUser.do?orgId="+orgId+"&userId="+userId;
	var DownUserUrl = "downUser.do?orgId="+orgId+"&userId="+userId;
	var UpRoleUrl = "upRole.do?roleId="+roleId;
	var DownRoleUrl ="downRole.do?roleId="+roleId;
	var UpRoleCatalogUrl = "downRole.do?roleId="+roleCatalogId;
	var DownRoleCatalogUrl ="upRole.do?roleId="+roleCatalogId;
	var listAllRoleUrl = "listRole.do";

////////////////////////////////////////////////////////////////////////////////////////////////
	if( getCurrentChannelId("S_mode")=="YHGL"){
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+listAllUserUrl+"')", "blink.gif", "&nbsp;用户列表");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+creatPluralizeUrl+"')", "blink.gif", "&nbsp;添加用户");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+creatOrgUrl+"')", "blink.gif", "&nbsp;添加部门");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+shouUsersOrgUrl+"')", "blink.gif", "&nbsp;人员位置");
		sMenu += getMenuRow("", "refresh()", "blink.gif", "&nbsp;刷新节点");
		//sMenu += sMenuHr;右键弹出菜单底部(备用样式)
		height += 103;
	}
	if( getCurrentChannelId("S_mode")=="WDGL"){
		//sMenu += getMenuRow("", "setSrc('dynamic_frame','"+listAllUserUrl+"')", "blink.gif", "&nbsp;文档列表");
		//sMenu += getMenuRow("", "setSrc('dynamic_frame','"+creatDocUrl+"')", "blink.gif", "&nbsp;添加文档");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+creatDirUrl+"')", "blink.gif", "&nbsp;添加目录");
		sMenu += getMenuRow("", "refresh()", "blink.gif", "&nbsp;刷新节点");
		//sMenu += sMenuHr;右键弹出菜单底部(备用样式)
		height += 83;
	}
	if( getCurrentChannelId("S_mode")=="YHBM"){
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+DownOrgUrl+"')", "blink.gif", "&nbsp;上移");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+UpOrgUrl+"')", "blink.gif", "&nbsp;下移");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+creatOrgUrl+"')", "blink.gif", "&nbsp;添加部门");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+delOrgUrl+"')", "blink.gif", "&nbsp;删除部门");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+creatUserUrl+"')", "blink.gif", "&nbsp;添加用户");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+creatPluralizeUrl+"')", "blink.gif", "&nbsp;添加兼职");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+modifyOrgUrl+"')", "blink.gif", "&nbsp;编辑部门");
		//sMenu += sMenuHr;
		height += 143;
	}
	if( getCurrentChannelId("S_mode")=="WDML"){
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+DownDirUrl+"')", "blink.gif", "&nbsp;下移");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+UpDirUrl+"')", "blink.gif", "&nbsp;上移");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+creatDirUrl+"')", "blink.gif", "&nbsp;添加目录");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+delDirUrl+"')", "blink.gif", "&nbsp;删除目录");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+modifyDirUrl+"')", "blink.gif", "&nbsp;编辑目录");
		//sMenu += sMenuHr;
		height += 123;
	}	

	if( getCurrentChannelId("S_mode")=="JSGL"){
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+listAllRoleUrl+"')", "blink.gif", "&nbsp;角色列表");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+creatRoleUrl+"')", "blink.gif", "&nbsp;添加角色");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+CreateRoleCatalogUrl+"')", "blink.gif", "&nbsp;添加类别");
		sMenu += getMenuRow("", "refresh()", "blink.gif", "&nbsp;刷新节点");
		sMenu += sMenuHr;
		height += 80;
	}
	
	if( getCurrentChannelId("S_mode")=="YHYH"){
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+UpUserUrl+"')", "blink.gif", "&nbsp;上移");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+DownUserUrl+"')", "blink.gif", "&nbsp;下移");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+moveUserUrl+"')", "blink.gif", "&nbsp;调动部门");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+delUserUrl+"')", "blink.gif", "&nbsp;删除用户");
		sMenu += sMenuHr;
		height += 80;
	}
	if( getCurrentChannelId("S_mode")=="BMGLYH"){
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+getUserSelectTreeUrl+"')", "blink.gif", "&nbsp;添加管理员");
		sMenu += getMenuRow("", "refresh()", "blink.gif", "&nbsp;刷新节点");
		sMenu += sMenuHr;
		height += 40;
	}
	if( getCurrentChannelId("S_mode")=="GLYH"){
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+delAdminUrl+"')", "blink.gif", "&nbsp;删除管理员");
		sMenu += sMenuHr;
		height += 20; 
	}
	if( getCurrentChannelId("S_mode")=="ZDYJS"){
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+UpRoleUrl+"')", "blink.gif", "&nbsp;上移");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+DownRoleUrl+"')", "blink.gif", "&nbsp;下移");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+delRoleUrl+"')", "blink.gif", "&nbsp;删除角色");
		sMenu += sMenuHr;
		height += 60;
	}
	if( getCurrentChannelId("S_mode")=="XTJS"){
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+UpRoleUrl+"')", "blink.gif", "&nbsp;上移");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+DownRoleUrl+"')", "blink.gif", "&nbsp;下移");
		sMenu += sMenuHr;
		height += 40;
	}
	if( getCurrentChannelId("S_mode")=="JSLB"){
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+UpRoleCatalogUrl+"')", "blink.gif", "&nbsp;上移");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+DownRoleCatalogUrl+"')", "blink.gif", "&nbsp;下移");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+creatRoleUrl+"')", "blink.gif", "&nbsp;添加角色");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+CreateRoleCatalogUrl+"')", "blink.gif", "&nbsp;添加类别");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+delRoleCatalogUrl+"')", "blink.gif", "&nbsp;删除类别");
		sMenu += getMenuRow("", "refresh()", "blink.gif", "&nbsp;刷新节点");
		sMenu += sMenuHr;
		height += 120;
	}
	//sMenu += getMenuRow("", "ShowDialog('dialog/img.htm', 350, 315, true)", "img.gif", getCurrentChannelId("cid"));
	//sMenu += sMenuHr;
	//height += 20;


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
