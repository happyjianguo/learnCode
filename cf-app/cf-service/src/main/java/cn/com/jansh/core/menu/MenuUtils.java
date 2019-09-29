package cn.com.jansh.core.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MenuUtils {
	
	private static final Logger logger = LogManager.getLogger(MenuUtils.class);

	/**
	 * 菜单列表转菜单树
	 * 
	 * @param mList
	 * @return
	 */
	public static List<MenuAuths> list2Tree(List<MenuAuths> mList){
		
		List<MenuAuths> mTree = new ArrayList<MenuAuths>();
		for (MenuAuths menu1 : mList) {
			boolean mark = false;
			for (MenuAuths menu2 : mList) {
				if (menu1.getParentid() != null
						&& menu1.getParentid().equals(menu2.getMenuid())) {
					mark = true;
					if (menu2.getChild() == null) {
						menu2.setChild(new ArrayList<MenuAuths>());
					}
					menu2.getChild().add(menu1);
					break;
				}
			}
			if (!mark) {
				mTree.add(menu1);
			}
		}
		return mTree;
	}
	
	/**
	 * 复制MenuList
	 * @param mList
	 * @return
	 */
	public static List<MenuAuths> cloneMenuList(List<MenuAuths> mList){
		List<MenuAuths> ml = new ArrayList<MenuAuths>();
		for (MenuAuths m : mList) {
			MenuAuths menu = new MenuAuths();
			menu.setMenuid(m.getMenuid());
			menu.setEnname(m.getEnname());
			menu.setCnname(m.getCnname());
			menu.setParentid(m.getParentid());
			menu.setMenulevel(m.getMenulevel());
			menu.setMenuorder(m.getMenuorder());
			menu.setMenuurl(m.getMenuurl());
			menu.setIcon(m.getIcon());
			menu.setMenustatus(m.getMenustatus());
			ml.add(menu);
		}
		return ml;
	}
	
	/**
	 * 菜单分组
	 * 
	 * @param mList
	 * @return
	 */
	public static Map<String,List<MenuAuths>> menuGroup(List<MenuAuths> mList){
		Map<String,List<MenuAuths>> menuMap = new HashMap<String,List<MenuAuths>>();
		for (MenuAuths m : mList) {
			String parentid = m.getParentid();
			if(StringUtils.isBlank(parentid)){
				logger.debug("menu Parentid is blank!" + m);
				continue;
			}
			List<MenuAuths> menuGroupList = menuMap.get(parentid);
			if(menuGroupList == null){
				menuGroupList = new ArrayList<MenuAuths>();
			}
			menuGroupList.add(m);
		}
		return menuMap;
	}
}
