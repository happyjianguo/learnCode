//package com.jansh.core.service.sys;
//
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.jansh.core.menu.MenuAuths;
//
///**
// * 初始化菜单
// * 
// * @author nie
// *
// */
//@Service
//public class MenuConfig {
//
//	@Autowired
//	private MenuService menuService;
//
//	private List<MenuAuths> menuList = null;
//
//	public List<MenuAuths> getMenuList() {
//		return menuList;
//	}
//
//	@PostConstruct
//	public void loadMenuDefine() {
//		menuList = menuService.select("1");
//	}
//}
