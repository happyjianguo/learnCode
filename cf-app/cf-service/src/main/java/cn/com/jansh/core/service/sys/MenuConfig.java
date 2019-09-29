package cn.com.jansh.core.service.sys;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.core.menu.MenuAuths;

/**
 * 初始化菜单
 * 
 * @author nie
 *
 */
@Service
public class MenuConfig {
	
	@Autowired
	private MenuService menuService;
	
	private List<MenuAuths> menuList = null;
	
//	private static Map<String,List<MenuAuths>> menuGroupList = null;
	
	public List<MenuAuths> getMenuList() {
		return menuList;
	}

//	@Autowired
//	public MenuConfig(MenuService menuService) {
//		menuList = menuService.select("1");
//		setMenuGroupList(MenuUtils.menuGroup(menuList));
//	}
	
	@PostConstruct
	public void loadMenuDefine() {
		menuList = menuService.select("1");
//		setMenuGroupList(MenuUtils.menuGroup(menuList));
	}

//	public static Map<String,List<MenuAuths>> getMenuGroupLis1() {
//		return menuGroupList;
//	}
//
//	public static void setMenuGroupList(Map<String,List<MenuAuths>> menuGroupList) {
//		MenuConfig.menuGroupList = menuGroupList;
//	}


	public void setMenuList(List<MenuAuths> menuList) {
		this.menuList = menuList;
	}

	public MenuConfig() {
	}
}
