package cn.com.jansh.core.service.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.core.entity.sys.IMMenu;
import cn.com.jansh.core.mapper.SysCoreMapper;
import cn.com.jansh.core.menu.MenuAuths;

@Service
public class MenuService {

	@Autowired
	private SysCoreMapper sysCoreMapper;

	/**
	 * 查询菜单列表
	 * 
	 * @param status
	 * @return
	 */
	public List<MenuAuths> select(String status) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("status", status);
		List<IMMenu> menuList = sysCoreMapper.selectMenu(param);
		List<MenuAuths> menuAuthsList = new ArrayList<MenuAuths>();
		for (IMMenu menu : menuList) {
			MenuAuths menuAuths = new MenuAuths();
			BeanUtils.copyProperties(menu, menuAuths);
			menuAuthsList.add(menuAuths);
		}
		// BeanUtils.copyProperties(menuList, menuAuthsList);
		return menuAuthsList;
	}

}
