package cn.com.jansh.dao.mapper;

import cn.com.jansh.dao.entity.WxdMenu;

public interface WxdMenuMapper {

	/**
	 * 根据MENUTYPE,MENUKEY,PLATFORMID获取WxdMenu对象 
	 * @param menu
	 * @return
	 */
	public  WxdMenu getwxdmenuData( WxdMenu menu );
}
