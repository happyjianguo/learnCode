// �˵�����
var sMenuHr="<tr><td align=center valign=middle height=2><TABLE border=0 cellpadding=0 cellspacing=0 width=10 height=2><tr><td height=1 class=HrShadow><\/td><\/tr><tr><td height=1 class=HrHighLight><\/td><\/tr><\/TABLE><\/td><\/tr>";
var sMenu1="<TABLE border=0 cellpadding=0 cellspacing=0 class=Menu width=80 onselectstart=\"return false;\"><tr><td width=10 valign=bottom align=center class=LeftBg><\/td><td width=70 class=RightBg><TABLE border=0 cellpadding=0 cellspacing=0>";
var sMenu2="<\/TABLE><\/td><\/tr><\/TABLE>";
// �˵�
var oPopupMenu = null;
if (BrowserInfo.IsIE55OrMore){
	oPopupMenu = window.createPopup();
}

// ȡ�˵���
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

// ȡ��׼��format�˵���
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

// �Ҽ��˵�
function showContextMenu(event){
	if (!bEditMode) return false;

	var width = 80;
	var height = 0;
	var lefter = event.clientX;
	var topper = event.clientY;

	var oPopDocument = oPopupMenu.document;
	var oPopBody = oPopupMenu.document.body;

	var sMenu="";
	// ���ѡ����ĸ�������ִ�е�URL
	var orgId = getCurrentChannelId("BM_ID");
	

	//��Ŀ¼�Ҽ��˵�
	var creatOrgUrl = "./orgFolderAction.do?method=showFolderInputScree&parentId="+orgId;//����ļ���
	
	//�����Ҽ��˵�
	var UpOrgUrl = "./orgFolderAction.do?method=upFolder&folderId="+orgId;//����
	var DownOrgUrl = "./orgFolderAction.do?method=downFolder&folderId="+orgId;//����
	var delOrgUrl = "./orgFolderAction.do?method=showConfirmDeleteFolder&folderId="+orgId;//ɾ���ļ���
	var modifyOrgUrl = "./orgFolderAction.do?method=showModifyFolderInfo&seqId="+orgId;//�޸��ļ���
	var addOrgFilesUrl = "./orgFileAction.do?method=showFileInputScree&seqId="+orgId;//����ļ����ļ�
	
	

////////////////////////////////////////////////////////////////////////////////////////////////
	if( getCurrentChannelId("S_mode")=="YHGL"){
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+UpOrgUrl+"')", "blink.gif", "&nbsp;����");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+DownOrgUrl+"')", "blink.gif", "&nbsp;����");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+addOrgFilesUrl+"')", "blink.gif", "&nbsp;����ļ�");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+creatOrgUrl+"')", "blink.gif", "&nbsp;����ļ���");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+modifyOrgUrl+"')", "blink.gif", "&nbsp;�༭�ļ���");		
		sMenu += getMenuRow("", "refresh()", "blink.gif", "&nbsp;ˢ�����ڵ�");
		//sMenu += sMenuHr;�Ҽ������˵��ײ�(������ʽ)
		height += 123;
	}
	if( getCurrentChannelId("S_mode")=="YHBM"){
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+UpOrgUrl+"')", "blink.gif", "&nbsp;����");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+DownOrgUrl+"')", "blink.gif", "&nbsp;����");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+addOrgFilesUrl+"')", "blink.gif", "&nbsp;����ļ�");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+modifyOrgUrl+"')", "blink.gif", "&nbsp;�༭�ļ���");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+creatOrgUrl+"')", "blink.gif", "&nbsp;����ļ���");
		sMenu += getMenuRow("", "setSrc('dynamic_frame','"+delOrgUrl+"')", "blink.gif", "&nbsp;ɾ���ļ���");
		
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
